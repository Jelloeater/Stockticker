package jelloeater.StockTicker;

//import java.io.IOException;
//import java.util.*;

// import java.net.*; // To be used for net connection checking

// This is a basic stock ticker application. It ticks stocks n stuff
import javax.swing.JOptionPane; // Just for debugging, actual GUI is in its own class

class App {
	
	static boolean debugMode = true; // Controls debug mode
	
	/**
	 * This runs first
	 * There should be a minimal amount of methods here, and preferably no variables,
	 * They should be stored in a class
	 * @param args
	 * @throws Throwable
	 */
	public static void main(String[] args) throws Throwable {
		// TODO Write update method to loop getting info
    	
		
		Settings.setDefaults(); 
       
		Settings.loadSettings();
 
		Settings.setRefreshIntervalSecondsGUI();

		Settings.setQuoteSourceGUI();
   
		Settings.saveSettings(); 

		
		
		TickerInfo myStock = TickerInfo.createNewTickerGui(); // Input Window
		App.displayGuiInfoWindow(myStock);// Output Window
		
		TickerInfo myStock2 = TickerInfo.createNewTickerGui(); // Input Window 
		App.displayGuiInfoWindow(myStock2);// Output Window
       
		myStock = TickerInfo.updateTicker(myStock); // Update method
		
		
		
       
		TickerWindow.launchGui(null); // FIRE ZE INTERFACE!!!
    }

	

	/**
	 * Pop-up window that displays data from myStock Object
	 * @param TickerInfo Object
	 */
	static void displayGuiInfoWindow(TickerInfo myStock){
		JOptionPane.showMessageDialog(null, 
	    		"Symbol: "+myStock.getTickerSymbol() +"\n"+
	    		"Price: " +myStock.getPrice() +"\n"+
	    		"% Change: "+ myStock.getPercentChange()+"%"+"\n"+
	    		"Price Change: "+ myStock.getPriceChange()
	    		,"LOL OUTPUT", JOptionPane.PLAIN_MESSAGE); 
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

