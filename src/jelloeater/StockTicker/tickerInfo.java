package jelloeater.StockTicker;

//import java.io.IOException;

/** tickerInfo
 *  Holds all of the ticker data for each iteration
 *  @param symbol 1-4 Letter stock identifier
 * @author Jesse
 *
 */
class tickerInfo {
		
	private String symbol;
	private String price;
	private String percentChange;
	private String priceChange;
	private String rawData;
	
	/**Constructor for setting up ticker symbol objects
	 * @throws Throwable */
	 tickerInfo(String symbolIN) throws Throwable {
		 
		 this.symbol = symbolIN;
		 
		 if (Settings.getQuoteSource() == "Google") {
			 
			 this.rawData = GoogleTickerData.getGoogleJSONfromWeb(symbol); // Gets JSON Data
			 
			 
			 
			 GoogleTickerData dataStore = new GoogleTickerData(); // Creates dataStore Objects
			 
			 dataStore=dataStore.mapJsonDataToObject(rawData); // Sends raw data to JSON parser to be converted to object
			 
			 this.price = dataStore.getPrice();
			 this.percentChange = dataStore.getPercentChange();
			 this.priceChange = dataStore.getPriceChange();
			 
			 
			 /*
			 this.price = StockInfoLookup.priceLookup(symbol);
			 this.priceChange = StockInfoLookup.priceChangeLookup(symbol);
			 this.percentChange = StockInfoLookup.precentLookup(symbol);
			 */
			 
			 System.err.println("mapping");
			 
			 // Above is correct
			 

			 
			 //GoogleTickerData.getGoogleJSONfromWeb(rawData);
			 
			 // get object data out of JSON parser
	 
			 
			 
		 }
		 
		 
		 
		 
		 
		 // TODO Add validation for valid symbol
	 }


	
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


}
