package jelloeater.StockTicker;

// This is a basic stock ticker app. It ticks stocks n stuff





public class App {
	
    public static void main(String[] args) throws Exception {
    	// This runs first
             
       Settings settingsStore = new Settings(); // Constructor for settings object
       settingsStore.setQuoteSource("mw"); // default setting for quote source Marketwatch
       settingsStore.setInterval(30); // default interval 30 seconds
       
    	int refreshInterval = Settings.getInterval(); // Gets refresh time for list from XML settings file
    	String quoteSource = Settings.getQuoteSource(); // Gets quote source from XML settings file
		// TODO Get Symbols from XML, load into ArrayList?
    	
    	
    	
    	tickerInfo myStock = new tickerInfo(); // Constructor for myStock object
    	myStock.setTickerSymbol("GOOG"); //Setting ticker
    	
    	
    	String ticker = myStock.getTickerSymbol(); // Get ticker symbol for output, will have validation in setting at some point
    	Double price = myStock.getPrice(ticker); // Get price from ticker, uses scraper
    	Double percentChange = myStock.getPercentChange(ticker); // Get percent change from ticker, uses scraper
    	Double priceChange = myStock.getAmmountChange(ticker);
    	// Output of ticker data
    		

    	
    	System.out.println(ticker);
    	System.out.println(price);
    	System.out.println(percentChange);
    	System.out.println(priceChange);
    	// Console output
    	// TODO Replace with GUI
    	
    	
    	
    	TickerWindow.main(null); // Calls Main GUI Window
    	

        
    }




}
