package jelloeater.StockTicker;

//import java.io.IOException;

class tickerInfo {
	// This class creates and updates objects for each ticker object
		
	private String symbol;
	private String price;
	private String percentChange;
	private String priceChange;
	private String quoteSource;
		
	
	 tickerInfo(String symbolIN) {
		 // Sets up ticker with basic symbol
		 // TODO Add validation for valid symbol
		 // Don't need to extend the App Class
		 this.symbol = symbolIN;
		 this.quoteSource = Settings.getQuoteSource();
	 }

	 


	String getTickerSymbol() {
		// Returns symbol user entered

		return symbol;
	}

	
	String getPrice() throws Throwable{
			price = ScreenScraper.priceLookup(symbol, quoteSource);

		return "$"+price; // Add USD to return
	}


	
	String getPercentChange() throws Throwable{
		// Looks up percent change using ticker symbol
		percentChange = ScreenScraper.precentLookup(symbol, quoteSource);
		return percentChange;
	}

	String getAmmountChange() throws Throwable{
		// Looks up amount change using ticker symbol
		priceChange = ScreenScraper.priceChangeLookup(symbol, quoteSource);
		return priceChange;
	}

}
