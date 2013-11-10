package jelloeater.StockTicker;

//import java.io.IOException;

/** tickerInfo
 * 
 * @author Jesse
 *
 */
class tickerInfo {
		
	private String symbol;
	private String price;
	private String percentChange;
	private String priceChange;
	
	/**Constructor for setting up ticker symbol objects
	 * @throws Throwable */
	 tickerInfo(String symbolIN) throws Throwable {
		 // Don't need to extend the App Class
		 this.symbol = symbolIN;
		 this.price = ScreenScraper.priceLookup(symbol);
		 this.priceChange = ScreenScraper.priceChangeLookup(symbol);
		 this.percentChange = ScreenScraper.precentLookup(symbol);
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
