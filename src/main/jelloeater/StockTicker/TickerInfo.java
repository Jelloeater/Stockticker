package jelloeater.StockTicker;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import java.io.IOException;

/**
 * Holds all of the ticker data for each iteration
 * Setters are private
 * But getters are default viability
 * 
 * @param symbol
 *            1-4 Letter stock identifier
 * @author Jesse
 * 
 */
// TODO Maybe make it an abstract class?
class TickerInfo extends App {

	private String symbol;
	private String price;
	private String percentChange;
	private String priceChange;
	private String rawData;

	/**
	 * Constructor for creating up TickerInfo objects
	 * It's empty, like my head, derp.
	 * @throws Throwable
	 * */
	public TickerInfo() {
	}

	/**
	 * When fed a tickerSymbol, parses net for info for setup
	 */
	public TickerInfo(String tickerSymbol) throws Exception {
		this.symbol = tickerSymbol;
		// FIXME: handle exception for no Internet access
		if (settingsProperties.getQuoteSource().equals("Google")) {
			//TODO Validate ticker
			this.rawData = GoogleTickerData.getGoogleJSONfromWeb(tickerSymbol);

			GoogleTickerData tempObj = GoogleTickerData
					.mapJsonDataToObject(rawData);
			this.percentChange = tempObj.getPercentChange();
			this.price = tempObj.getPrice();
			this.priceChange = tempObj.getPriceChange();
		}
	}
	
	/**
	 * Opens pop-up text box, takes input from text box and create a new object
	 * to pass the update method, then returns a myStock object that can be
	 * parsed via getters Lookup logic is dependent on Settings.quoteSource
	 * Basically the same thing as calling the constructor with a ticker symbol
	 * 
	 * @return myStock
	 * @throws Throwable
	 */
	public TickerInfo setTickerViaGui() {

		String tickerSymbolInput = JOptionPane.showInputDialog("Set Symbol", "GOOG");
		TickerInfo myStock = null;
		// FIXME: handle exception for no Internet access
		try {
			myStock = new TickerInfo(tickerSymbolInput);
			// Passes input to constructor
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myStock;
	}

	public boolean validateSymbol(String tickerSymbol) {
		boolean isSymbolVaild = false;

		if (settingsProperties.getQuoteSource().equals("Google")) {
			
			
		}

		return isSymbolVaild;
	}

	/**
	 * Updates TickerInfo objects. Each source will have its own unique flow.
	 * 
	 * @param myStock
	 * @return myStock object with updated data
	 * @throws Exception
	 */
	public TickerInfo updateTicker(TickerInfo myStock) throws Exception {
		String symbol = myStock.getTickerSymbol();

		if (settingsProperties.getQuoteSource() == "Google") {

			String rawData = GoogleTickerData.getGoogleJSONfromWeb(symbol); 
			 //Gets	JSON Data

			GoogleTickerData dataStore = new GoogleTickerData(); 
			// Creates dataStore Objects

			dataStore = GoogleTickerData.mapJsonDataToObject(rawData); 
			// Sends raw data to JSON parser to be converted to object
	
			myStock.setPrice(dataStore.getPrice()); 
			// Sets value for constructor
			myStock.setPercentChange(dataStore.getPercentChange()); 
			// Sets value for constructor
			myStock.setPriceChange(dataStore.getPriceChange());
			// Sets value for constructor
		}

		return myStock;
	}

	// Getters and Setters below
	String getTickerSymbol() {
		return symbol;
	}

	String getPrice() {
		return price;
	}

	String getPercentChange() {
		return percentChange;
	}

	String getPriceChange() {
		return priceChange;
	}
	
	/*
	private String getRawData() {
		return rawData;
	}

	private void setTickerSymbol(String symbol) {
		this.symbol = symbol;
	}
	*/
	
	private void setPrice(String price) {
		this.price = price;
	}

	private void setPercentChange(String percentChange) {
		this.percentChange = percentChange;
	}

	private void setPriceChange(String priceChange) {
		this.priceChange = priceChange;
	}

	/**
	 * Pop-up window that displays data from myStock Object
	 * 
	 * @param myStock
	 *            Object from TickerInfo
	 */
	void getTickerInfoDataGUI(TickerInfo myStock) {
		JOptionPane.showMessageDialog(
				null,
				"Symbol: " + myStock.getTickerSymbol() + "\n" + "Price: "
						+ myStock.getPrice() + "\n" + "% Change: "
						+ myStock.getPercentChange() + "%" + "\n"
						+ "Price Change: " + myStock.getPriceChange(),
				"LOL OUTPUT", JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Holds methods to be used by TickerInfo Specific to mapping Google
	 * supplied JSON data It is a helper class
	 * 
	 * @see jelloeater.StockTicker.TickerInfo
	 * @author Jesse
	 * 
	 */
	public static class GoogleTickerData {

		private String l;
		private String c;
		private String cp;

		/*
		 * //Unused but useful JSON values private String id; private String t;
		 * private String e; private String l_fix; private String l_cur; private
		 * String s; private String ltt; private String lt; private String ccol;
		 */
		/**
		 * Constructor used to set up object variables
		 */
		GoogleTickerData() {
		}

		/**
		 * Maps JSON data to Google Ticker object using Gson library. Uses
		 * private class variable names to make the map
		 * 
		 * @param rawJsonData
		 * @return tickerDataStore
		 */
		private static GoogleTickerData mapJsonDataToObject(String rawJsonData) {
			// Takes raw JSON data
			Gson gson = new Gson(); // Initializes object
			GoogleTickerData tickerDataStore = gson.fromJson(rawJsonData,
					GoogleTickerData.class);

			return tickerDataStore;
		}

		private String getPrice() {
			return l;
		}

		private String getPercentChange() {
			return cp;
		}

		private String getPriceChange() {
			return c;
		}

		/**
		 * Gets JSON data from Google and cleans it
		 * 
		 * @param symbol
		 * @return
		 * @throws java.io.IOException
		 */
		private static String getGoogleJSONfromWeb(String symbol) throws Exception {
			String url = "http://finance.google.com/finance/info?client=ig&q="
					+ symbol; // URL to lookup
			Document document = Jsoup.connect(url).get(); // Pull in dirty JSON
															// data
			String jsonDirtyIN = document.select("*").text(); // Gets page text

			String finalJsonOutput = cleanGoogleJSONdata(jsonDirtyIN);
			return finalJsonOutput;
		}

		private static String cleanGoogleJSONdata(String jsonDirtyIN) {
			// Cleans JSON data
			String jsonCleanOut = null; // Output variable
			// <regex vodo>
			String re1 = ".*?"; // Non-greedy match on filler
			String re2 = "(\\[.*?\\])"; // Square Braces 1
			String re3 = "(.)"; // Any Single Character 1
			Pattern p = Pattern.compile(re1 + re2 + re3,
					Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
			Matcher m = p.matcher(jsonDirtyIN); // INPUT
			if (m.find()) {
				String sbraces1 = m.group(1);
				jsonCleanOut = sbraces1.toString(); // OUTPUT
			}
			// </regex vodo>

			String finalJsonOutput = jsonCleanOut.replace("[", "").replace("]",
					""); // Clean off brackets

			return finalJsonOutput;
		}

	}
}
