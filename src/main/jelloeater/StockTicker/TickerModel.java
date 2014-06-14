package jelloeater.StockTicker;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import java.io.IOException;

/**
 * Holds all of the ticker data for each iteration
 * Setters are private
 * But getters are default viability
 * 
 * @author Jesse
 * 
 */
// TODO Maybe make it an abstract class?

class TickerModel extends TickerController {

	private String symbol;
	private String price;
	private String percentChange;
	private String priceChange;
	private String rawData;




	/**
	 * When fed a tickerSymbol, parses net for info for setup
	 * @param tickerSymbol
	 */
	TickerModel(String tickerSymbol) {
		this.symbol = tickerSymbol;
		int retryCounter = 0;
		int maxRetry = 3;

		if (settingsProperties.isSourceGoogle()) {

			do {
				try {
					this.rawData = GoogleTickerData.getGoogleJSONfromWeb(tickerSymbol);
					// FIXME Validate ticker call
					break;
				} catch (IOException e) {
					//e.printStackTrace();
					System.err.println("Connection Failure - Retry: " + retryCounter);
					retryCounter++;
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

			if (retryCounter <= maxRetry) {
				GoogleTickerData tempObj = GoogleTickerData.mapJsonDataToObject(rawData);
				this.percentChange = tempObj.getPercentChange();
				this.price = tempObj.getPrice();
				this.priceChange = tempObj.getPriceChange();
			} else { // If quote source is not set in settingsProperties
				this.percentChange = "err";
				this.price = "err";
				this.priceChange = "err";
			}
		}
	}

	// FIXME Write validate ticker method


	// Getters and Setters below
	String getTickerSymbol() {
		return this.symbol;
	}

	String getPrice() {
		return this.price;
	}

	String getPercentChange() {
		return this.percentChange;
	}

	String getPriceChange() {
		return this.priceChange;
	}


	void getTickerInfoDataConsole() {
		System.out.println(
				"Symbol: " + this.getTickerSymbol() + "\n" + "Price: "
						+ this.getPrice() + "\n" + "% Change: "
						+ this.getPercentChange() + "%" + "\n"
						+ "Price Change: " + this.getPriceChange());
	}

	/**
	 * Holds methods to be used by TickerModel Specific to mapping Google
	 * supplied JSON data It is a helper class
	 * 
	 * @see TickerModel
	 * @author Jesse
	 * 
	 */
	public static class GoogleTickerData {

		private String l;
		private String c;
		private String cp;


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
				jsonCleanOut = m.group(1); // OUTPUT
			}
			// </regex vodo>


			assert jsonCleanOut != null;
			return jsonCleanOut.replace("[", "").replace("]", "");
			// Clean off brackets
		}

	}
}
