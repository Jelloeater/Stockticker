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
		 
		 if (Settings.getQuoteSource() == "Google") {
			 this.symbol = symbolIN;
			// TODO Add validation for valid symbol
			 this.rawData= GoogleTickerData.getGoogleJSONfromWeb(symbol); // gets JSON data for object
			 // This is good, we are doing OOP
			 
			 GoogleTickerData dataContainer = new GoogleTickerData(rawData);
			 this.price = dataContainer.l; // Gets Price from JSON data
			 this.percentChange = dataContainer.cp; // Gets percent change from JSON data
			 this.priceChange = dataContainer.c; // Gets price change from JSON data
		 }	 
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



	public String getRawData() {
		return rawData;
	}



	public void setRawData(String rawData) {
		this.rawData = rawData;
	}


}
