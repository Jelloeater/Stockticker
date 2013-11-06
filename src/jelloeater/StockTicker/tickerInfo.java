/**
 * 
 */
package jelloeater.StockTicker;

import java.io.IOException;


public class tickerInfo extends App{
	// This class creates and updates objects for each ticker
		
	private String symbol;
	private Double price;
	private Double percentChange;
	private Double priceChange;
		
	
	 tickerInfo(String symbolIN) {
		// Sets up ticker with basic symbol
		 this.symbol = symbolIN;
	 }


	public String getTickerSymbol() {
		// Returns symbol user entered
		// TODO Add validation for valid symbol
		
		return symbol;
	}

	public Double getPrice(String ticker) throws IOException {
		// Looks up price using ticker symbol
		price = ScreenScrape.PriceLookup(ticker);
		
		return price;
	}

	public Double getPercentChange(String ticker) {
		// Looks up percent change using ticker symbol
		percentChange = ScreenScrape.precentLookup(ticker);
		return percentChange;
	}

	public Double getAmmountChange(String ticker) {
		// Looks up amount change using ticker symbol
		priceChange = ScreenScrape.priceChangeLookup(ticker);
		return priceChange;
	}

}
