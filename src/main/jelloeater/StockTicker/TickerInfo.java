package jelloeater.StockTicker;

//import java.io.IOException;

/** tickerInfo
 *  Takes Symbol
 *  Holds all of the ticker data for each iteration
 *  @param symbol 1-4 Letter stock identifier
 * @author Jesse
 *
 */
//TODO Maybe make it an abstract class?
class TickerInfo {
		
	private String symbol;
	private String price;
	private String percentChange;
	private String priceChange;
	private String rawData;
	
	/**Constructor for setting up ticker symbol objects
	 * @throws Throwable 
	 * */
	 TickerInfo(String symbolIN) throws Throwable {
		 this.symbol = symbolIN;
		 // TODO Add validation for valid symbol
		 
		 if (Settings.getQuoteSource() == "Google") { 
			 
			 
			 this.rawData = GoogleTickerData.getGoogleJSONfromWeb(symbol); // Gets JSON Data
			 
			 
			 
			 GoogleTickerData dataStore = new GoogleTickerData(); // Creates dataStore Objects
			 
			 dataStore=dataStore.mapJsonDataToObject(rawData); // Sends raw data to JSON parser to be converted to object
			 
			 this.price = dataStore.getPrice(); // Sets value for constructor
			 this.percentChange = dataStore.getPercentChange(); // Sets value for constructor
			 this.priceChange = dataStore.getPriceChange();// Sets value for constructor	 
		 }
	 
		 
	 }


	// Getters and Setter below
	public String getTickerSymbol() {
		return symbol;
	}
	
	public String getPrice() {
		return price;
	}
	
	public String getPercentChange() {
		return percentChange;
	}
	
	public String getPriceChange() {
		return priceChange;
	}
	
	public String getRawData() {
		return rawData;
	}


}
