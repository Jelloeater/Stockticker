package jelloeater.StockTicker;

//import java.io.IOException;
//import java.util.*;

// This is a basic stock ticker app. It ticks stocks n stuff
import javax.swing.JOptionPane; // Just for debugging, actual GUI is in its own class

class App {
	
	static boolean debugMode = true; // Controls debug mode
	
	public static void main(String[] args) throws Throwable {
    	// This runs first
    	
		
		
    	// There should be a minimal amount of methods here, and preferably no variables,
    	// They should be stored in a class
             
       
    	// Settings are something that there should only be one of, no need to make objects.
    	Settings.setQuoteSource("Google"); // default setting for quote source Google
    	Settings.setRefreshIntervalSeconds(30); // default interval 30 seconds
       
       
       Settings.loadSettings();
       // FIXME Write settings read method
       
       
       // TODO Create proper GUI input

       Settings.setRefreshIntervalSeconds(
    		   (Integer.parseInt(
    		   (JOptionPane.showInputDialog("Set Interval", Settings.getRefreshIntervalSeconds()
    			)))));	//Sets setter with GUI box
       
       
       
       
       
       String[] quoteSourceChoices = { "MarketWatch", "Yahoo", "Google"}; 
       // Dialog box choices array
       Settings.setQuoteSource((String) JOptionPane.showInputDialog(null, null,
           "Choose Quote Source", JOptionPane.QUESTION_MESSAGE, null, 
           quoteSourceChoices, // Array of choices
           Settings.getQuoteSource())); // Initial choice
       
 
       
		
		Settings.saveSettings(); // TODO Should write on program close
		
		
		// INPUT Section
		// TODO convert to method
		
		String tickerSymbolInput=null;
		
		
		
		// STOCK INPUT HERE
		boolean validSymbolInput = false;
		while(validSymbolInput=false);{
		try {
			
			tickerSymbolInput= JOptionPane.showInputDialog("Set Symbol","GOOG");
			
	    	// Lookup logic is dependent on Settings.quoteSource
			validSymbolInput=true;
		} catch (Exception e) {
			validSymbolInput = false;
			System.err.println("validInput:" + validSymbolInput);
			System.err.println("Enter a valid symbol");
			// FIXME: handle exception
			}
		}

    	
		TickerInfo myStock = new TickerInfo(tickerSymbolInput); 
		//Create ticker info object using symbol, takes input from window
    	

    	// OUTPUT BELOW!!!
    	
		
		
		
		
   	
    	JOptionPane.showMessageDialog(null, 
    		"Symbol: "+myStock.getTickerSymbol() +"\n"+
    		"Price: " +myStock.getPrice() +"\n"+
    		"% Change: "+ myStock.getPercentChange()+"%"+"\n"+
    		"Price Change: "+ myStock.getPriceChange()
    		,"LOL OUTPUT", JOptionPane.PLAIN_MESSAGE); 

    	
    	System.err.println("END BREAK");
    	
    
    	
    	TickerWindow.launchGui(null);
    	
    	// TODO End of program breakpoint
    	
    	//Display a message dialog box, NULL = No specific position on screen
    	// TODO Replace with Proper GUI
    	
    	// TODO Write update method to loop getting info

    	// TickerWindow.main(null); // Calls Main GUI Window
    	

        
    }

}





/*
int refreshInterval = Settings.getInterval(); // Gets refresh time for list from XML settings file to display in GUI
String quoteSource = Settings.getQuoteSource(); // Gets quote source from XML settings file
// TODO IDEA Get Symbols from XML, load into ArrayList?
*/






//    	System.out.println("Type your symbol"); // Basic console IO
//    	Scanner myScanner2 = new Scanner(System.in); // System.in is an InputStream
//    	myStock.setTickerSymbol(myScanner2.nextLine()); // Setting ticker with user input
//    	myScanner2.close(); //Closes Scanner Stream

