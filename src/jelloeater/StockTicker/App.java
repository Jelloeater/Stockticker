package jelloeater.StockTicker;

import java.io.IOException;
import java.util.*;

// This is a basic stock ticker app. It ticks stocks n stuff
import javax.swing.JOptionPane; // Just for debugging, actual GUI is in its own class

public class App {
	
    public static void main(String[] args) throws Exception {
    	// This runs first
             
       Settings settingsStore = new Settings(); // Constructor for settings object
       
       settingsStore.setQuoteSource("MarketWatch"); // default setting for quote source Marketwatch
       settingsStore.setRefreshIntervalSeconds(30); // default interval 30 seconds
       Settings.saveSettings(); // Saves settings to disk (DOES NOTHING RIGHT NOW)
       
       
       
       // TODO Create proper GUI input

       settingsStore.setRefreshIntervalSeconds(
    		   (Integer.parseInt(
    		   (JOptionPane.showInputDialog("Set Interval", 5)))
    		   )
    	); //Sets setter with GUI box
       
       
       
       
       String[] quoteSourceChoices = { "MarketWatch", "Yahoo", "Google"}; 
       // Dialog box choices array
       settingsStore.setQuoteSource((String) JOptionPane.showInputDialog(null, null,
           "Choose Quote Source", JOptionPane.QUESTION_MESSAGE, null, 
           quoteSourceChoices, // Array of choices
           quoteSourceChoices[2])); // Initial choice
       
 
       
       
       
       
		
		Settings.saveSettings(); // TODO Write XML writer for settings file
		
		
       
       
       
       /*
    	int refreshInterval = Settings.getInterval(); // Gets refresh time for list from XML settings file to display in GUI
    	String quoteSource = Settings.getQuoteSource(); // Gets quote source from XML settings file
		// TODO Get Symbols from XML, load into ArrayList?
    	*/
       
		
		
		
    	tickerInfo myStock = new tickerInfo(
    			JOptionPane.showInputDialog("Set Symbol"), settingsStore.getQuoteSource()); // Basic input box
    	// Constructor for myStock object
    	// Also Passes quote source
    	// Holds all of the ticker data for each iteration

    	
    	// OUTPUT BELOW!!!
    		

    	JOptionPane.showMessageDialog(null, 
    			"Symbol: "+myStock.getTickerSymbol() +"\n"+
    			"Price: " +myStock.getPrice() +"\n"+
    			"% Change: "+ myStock.getPercentChange()+"\n"+
    			"Price Change: "+ myStock.getAmmountChange()
    			,"LOL OUTPUT", JOptionPane.PLAIN_MESSAGE); 
    	//Display a message dialog box, NULL = No specific position on screen
    	// TODO Replace with Proper GUI
    	
    	// TODO Write update method to loop getting info
    	
    	System.out.println("meh");  
    	//TODO DELETE ME
    	
    	
    	// TickerWindow.main(null); // Calls Main GUI Window
    	

        
    }




}




//    	System.out.println("Type your symbol"); // Basic console IO
//    	Scanner myScanner2 = new Scanner(System.in); // System.in is an InputStream
//    	myStock.setTickerSymbol(myScanner2.nextLine()); // Setting ticker with user input
//    	myScanner2.close(); //Closes Scanner Stream