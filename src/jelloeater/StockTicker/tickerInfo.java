package jelloeater.StockTicker;

//import java.io.IOException;

public class tickerInfo {
	// This class creates and updates objects for each ticker object
		
	private String symbol;
	private String price;
	private String percentChange;
	private String priceChange;
	private String quoteSource;
		
	
	 tickerInfo(String symbolIN, String sourceIN) {
		// Sets up ticker with basic symbol
		 // TODO Add validation for valid symbol
		 this.symbol = symbolIN;
		 this.quoteSource = sourceIN;
	 }

	 


	public String getTickerSymbol() {
		// Returns symbol user entered

		return symbol;
	}

	
	public String getPrice() throws Throwable{
			price = ScreenScrape.priceLookup(symbol, quoteSource);

		return "$"+price; // Add USD to return
	}


	
	public String getPercentChange() throws Throwable{
		// Looks up percent change using ticker symbol
		percentChange = ScreenScrape.precentLookup(symbol, quoteSource);
		return percentChange;
	}

	public String getAmmountChange() throws Throwable{
		// Looks up amount change using ticker symbol
		priceChange = ScreenScrape.priceChangeLookup(symbol, quoteSource);
		return priceChange;
	}

}
