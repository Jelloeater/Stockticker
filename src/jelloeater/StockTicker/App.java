package jelloeater.StockTicker;

import java.util.*;

// This is a basic stock ticker app. It ticks stocks n stuff


public class App {
	
    public static void main(String[] args) throws Exception {
    	// This runs first
             
       Settings settingsStore = new Settings(); // Constructor for settings object
       settingsStore.setQuoteSource("mw"); // default setting for quote source Marketwatch
       settingsStore.setRefreshIntervalSeconds(30); // default interval 30 seconds
       Settings.saveSettings(); // Saves settings to disk (DOES NOTHING RIGHT NOW)
       
       
       
       System.out.println("Refresh intervel input"); // Basic console IO
       Scanner myScanner = new Scanner(System.in); // System.in is an InputStream
       settingsStore.setRefreshIntervalSeconds(myScanner.nextInt()); // Sets refresh interval with user input
       myScanner.close(); //Closes Scanner Stream
		
		Settings.saveSettings();
		// TODO Create proper GUI input
		
       
       
       
       /*
    	int refreshInterval = Settings.getInterval(); // Gets refresh time for list from XML settings file to display in GUI
    	String quoteSource = Settings.getQuoteSource(); // Gets quote source from XML settings file
		// TODO Get Symbols from XML, load into ArrayList?
    	*/
       
    	tickerInfo myStock = new tickerInfo("GOOG"); // Constructor for myStock object 
    	// Also Setting ticker manually
    	   


    	
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
    	
    	
    	
    	// TickerWindow.main(null); // Calls Main GUI Window
    	

        
    }




}




//    	System.out.println("Type your symbol"); // Basic console IO
//    	Scanner myScanner2 = new Scanner(System.in); // System.in is an InputStream
//    	myStock.setTickerSymbol(myScanner2.nextLine()); // Setting ticker with user input
//    	myScanner2.close(); //Closes Scanner Stream