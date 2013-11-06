/**
 * 
 */
package jelloeater.StockTicker;

import java.io.IOException;


public class tickerInfo extends App {
	// This class creates and updates objects for each ticker object
		
	private String symbol;
	private Double price;
	private Double percentChange;
	private Double priceChange;
	private String quoteSource;
		
	
	 tickerInfo(String symbolIN, String sourceIN) {
		// Sets up ticker with basic symbol
		 this.symbol = symbolIN;
		 this.quoteSource = sourceIN;
	 }

	 


	public String getTickerSymbol() {
		// Returns symbol user entered
		// TODO Add validation for valid symbol
		
		return symbol;
	}

	
	public Double getPrice() throws NumberFormatException, IOException {
		// Looks up price using ticker symbol
		price = Double.parseDouble(ScreenScrape.PriceLookup(symbol, quoteSource));
		
		return price;
	}


	
	public Double getPercentChange() {
		// Looks up percent change using ticker symbol
		percentChange = Double.parseDouble(ScreenScrape.precentLookup(symbol, quoteSource));
		return percentChange;
	}

	public Double getAmmountChange() {
		// Looks up amount change using ticker symbol
		priceChange = Double.parseDouble(ScreenScrape.priceChangeLookup(symbol, quoteSource));
		return priceChange;
	}

}
