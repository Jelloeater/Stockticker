package jelloeater.StockTicker;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.JOptionPane;

import java.io.IOException;
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
	 * When fed a tickerSymbol, parses net for info for setup
	 * @param displayGUI 
	 */
	private TickerInfo(String tickerSymbol, boolean displayGUI){
		this.symbol = tickerSymbol;
		int retryCounter = 0;
		int maxRetry = 3;
		
		if (settingsProperties.isSourceGoogle()){

			do {
				try {
					this.rawData = GoogleTickerData.getGoogleJSONfromWeb(tickerSymbol);
					// FIXME Validate ticker call
					break;
				} catch (IOException e) {
					//e.printStackTrace();
					System.err.println("Connection Failure - Retry: " + retryCounter);
					retryCounter ++;
					try {
						Thread.sleep(2000); // Wait 2 seconds
						// TODO is this okay when we go multi-thread?
					} catch (InterruptedException e1) {
						e1.printStackTrace();
						System.err.println("OH SHIT!!!");
						System.err.println("This is a problem related to the thread sleep, I think?");
					}
				}
			} while (retryCounter <= maxRetry);

			if (retryCounter <= maxRetry ){
			GoogleTickerData tempObj = GoogleTickerData.mapJsonDataToObject(rawData);
			this.percentChange = tempObj.getPercentChange();
			this.price = tempObj.getPrice();
			this.priceChange = tempObj.getPriceChange();
			}else{
				if (displayGUI) JOptionPane.showMessageDialog(null, "Internet Connection Failure", "Error", JOptionPane.ERROR_MESSAGE);
			}	
		}
		
		else{ // If quote source is not set in settingsProperties
			this.percentChange="err";
			this.price="err";
			this.priceChange="err";
			if (displayGUI) JOptionPane.showMessageDialog(null, "Quote source error", "Error", 0);
		}
	}
	

	
	/**Creates ticker object, due to constructor being private
     * Flag on call sets popup logic
	 * @param tickerSymbolInput
	 * @return TickerInfo*/
	public static TickerInfo makeTickerObject(String tickerSymbolInput) {

        return new TickerInfo(tickerSymbolInput, false);
	}
	
	// FIXME Validate ticker method
	public boolean validateSymbol(String tickerSymbol) {
		boolean isSymbolVaild = false;

		if (settingsProperties.isSourceGoogle()) {
			
			
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
	public TickerInfo updateTickerObject(TickerInfo myStock) {
		String symbol = myStock.getTickerSymbol();
        myStock = new TickerInfo(symbol,false);

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
	
	void getTickerInfoDataConsole(TickerInfo myStock) {
		System.out.println(
				"Symbol: " + myStock.getTickerSymbol() + "\n" + "Price: "
						+ myStock.getPrice() + "\n" + "% Change: "
						+ myStock.getPercentChange() + "%" + "\n"
						+ "Price Change: " + myStock.getPriceChange());
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

            // Call and return
            return gson.fromJson(rawJsonData, GoogleTickerData.class);
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
		private static String getGoogleJSONfromWeb(String symbol) throws IOException {
			String url = "http://finance.google.com/finance/info?client=ig&q="
					+ symbol; // URL to lookup
			Document document = Jsoup.connect(url).get(); // Pull in dirty JSON
															// data
			String jsonDirtyIN = document.select("*").text(); // Gets page text

            return cleanGoogleJSONdata(jsonDirtyIN);
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



			return jsonCleanOut.replace("[", "").replace("]", "");
			// Clean off brackets
		}

	}
}
