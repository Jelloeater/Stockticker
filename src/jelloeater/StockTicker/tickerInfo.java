/**
 * 
 */
package jelloeater.StockTicker;

import java.io.IOException;


public class tickerInfo extends App{
	// This class creates and updates objects
	// It is middle management, is it necessary?
	// Go ask Peter about his TPS reports.
		
	private String symbol= null;
	private Double price=null;
	private Double percentChange=null;
	private Double priceChange=null;
		
	/**
	 * 
	 */
	 tickerInfo() {
		// TODO Auto-generated constructor stub
		
	}

	public void setTickerSymbol(String TickerString) {
		TickerString = symbol;
		
	}

	public String getTickerSymbol() {
		// TODO Auto-generated method stub
		
		return symbol;
	}

	public Double getPrice(String ticker) throws IOException {
		// TODO Auto-generated method stub
		price = ScreenScrape.PriceLookup(ticker);
		
		return price;
	}

	public Double getPrecentChange(String ticker) {
		// TODO Auto-generated method stub
		percentChange = ScreenScrape.precentLookup(ticker);
		return percentChange;
	}

	public Double getAmmountChange(String ticker) {
		// TODO Auto-generated method stub
		priceChange = ScreenScrape.priceChangeLookup(ticker);
		return priceChange;
	}

}
