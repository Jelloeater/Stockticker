package jelloeater.StockTicker;

import java.util.*;

// This is a basic stock ticker app. It ticks stocks n stuff
import javax.swing.JOptionPane; // Just for debugging, actual GUI is in its own class

public class App {
	
    public static void main(String[] args) throws Exception {
    	// This runs first
             
       Settings settingsStore = new Settings(); // Constructor for settings object
       settingsStore.setQuoteSource("mw"); // default setting for quote source Marketwatch
       settingsStore.setRefreshIntervalSeconds(30); // default interval 30 seconds
       Settings.saveSettings(); // Saves settings to disk (DOES NOTHING RIGHT NOW)
       
       
       

       String INPUT = JOptionPane.showInputDialog("Set Interval"); //Can only take Strings
       settingsStore.setRefreshIntervalSeconds(Integer.parseInt(INPUT)); //Parses ints from strings
       // Also sets object
		
		
		
		Settings.saveSettings();
		// TODO Create proper GUI input
		
       
       
       
       /*
    	int refreshInterval = Settings.getInterval(); // Gets refresh time for list from XML settings file to display in GUI
    	String quoteSource = Settings.getQuoteSource(); // Gets quote source from XML settings file
		// TODO Get Symbols from XML, load into ArrayList?
    	*/
       
    	tickerInfo myStock = new tickerInfo(
    			JOptionPane.showInputDialog("Set Symbol")); // Basic input diag
    	// Constructor for myStock object 
    	// Also Setting ticker manually
    	   


    	
    	String ticker = myStock.getTickerSymbol(); // Get ticker symbol for output, will have validation in setting at some point
    	Double price = myStock.getPrice(ticker); // Get price from ticker, uses scraper
    	Double percentChange = myStock.getPercentChange(ticker); // Get percent change from ticker, uses scraper
    	Double priceChange = myStock.getAmmountChange(ticker);
    	// Output of ticker data
    		

    	JOptionPane.showMessageDialog(null, 
    			"Symbol: "+ticker +"\n"+
    			"Price: " +price +"\n"+
    			"% Change: "+ percentChange+"\n"+
    			"Price Change: "+ priceChange
    			,"LOL OUTPUT", JOptionPane.PLAIN_MESSAGE); 
    	//Display a message dialog box, NULL = No specific position on screen
    	// TODO Replace with Proper GUI
    	
    	
    	
    	
    	// TickerWindow.main(null); // Calls Main GUI Window
    	

        
    }




}




//    	System.out.println("Type your symbol"); // Basic console IO
//    	Scanner myScanner2 = new Scanner(System.in); // System.in is an InputStream
//    	myStock.setTickerSymbol(myScanner2.nextLine()); // Setting ticker with user input
//    	myScanner2.close(); //Closes Scanner Stream