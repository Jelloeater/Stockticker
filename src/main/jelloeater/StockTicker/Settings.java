/**
 * 
 */
package jelloeater.StockTicker;

import java.io.*;
//import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;


 /**
 This class holds all the settings of the application, no need for objects, we don't need multiple versions.
 HOWEVER, Settings are dependent throughout the program, they are private so we can sanitize data
 Settings are something that there should only be one of, no need to make objects.
 @method
 {@link saveSettings}
 */
class Settings extends App{


	private static int refreshIntervalSeconds;
	private static String quoteSource;
	
	static void setDefaults(){
		Settings.setQuoteSource("Google"); // default setting for quote source Google
    	Settings.setRefreshIntervalSeconds(30); // default interval 30 seconds
	}
	
	
	static int getRefreshIntervalSeconds() { // Gets private refresh interval
		
		return refreshIntervalSeconds;
	}


	static void setRefreshIntervalSeconds(int refreshIntervalSecondsIN) { // Sets private refresh interval
		// TODO Add try and catch for int
		refreshIntervalSeconds = refreshIntervalSecondsIN;
	}
	
	static void setRefreshIntervalSecondsGUI(){
		int refreshIntervalSecondsIN = Integer.parseInt( (JOptionPane.showInputDialog("Set Interval", Settings.getRefreshIntervalSeconds())));
		refreshIntervalSeconds = refreshIntervalSecondsIN;
	}

	static String getQuoteSource() {
		return quoteSource;
	}


	static void setQuoteSource(String quoteSourceIN) {
		quoteSource = quoteSourceIN;
	}
	
	/**Writes settings to file in JSON*/
	static void saveSettings() throws FileNotFoundException {
		// TODO Should write on program close
		
		PrintStream diskWriter = new PrintStream(new File("settings.dat"));
		
//		String settingsData =  new JSONObject().put("JSON", "Hello, World!").toString();
		
		String settingsData = "hi";
		diskWriter.print (settingsData);
		diskWriter.close();
		
	}

	static void loadSettings() throws FileNotFoundException {
		// Reads settings from disk
		// FIXME Write settings read method
		
		Scanner fileParser = new Scanner("settings.dat");
		
		fileParser.next();
		fileParser.close();
	}


	public static void setQuoteSourceGUI() {
		String[] quoteSourceChoices = { "MarketWatch", "Yahoo", "Google"}; 
	       // Dialog box choices array
	       Settings.setQuoteSource((String) JOptionPane.showInputDialog(null, null,
	           "Choose Quote Source", JOptionPane.QUESTION_MESSAGE, null, 
	           quoteSourceChoices, // Array of choices
	           Settings.getQuoteSource())); // Initial choice	
	}


	
	
}
