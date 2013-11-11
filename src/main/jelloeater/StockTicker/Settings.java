/**
 * 
 */
package jelloeater.StockTicker;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

import sun.awt.image.ImageWatched.Link;

 /**
 This class holds all the settings of the application, no need for objects, we don't need multiple versions.
 HOWEVER, Settings are dependent throughout the program, they are private so we can sanitize data
 @method
 {@link saveSettings}
 */
class Settings extends App{


	private static int refreshIntervalSeconds;
	private static String quoteSource;
	
	static int getRefreshIntervalSeconds() { // Gets private refresh interval
		
		return refreshIntervalSeconds;
	}


	static void setRefreshIntervalSeconds(int refreshIntervalSecondsIN) { // Sets private refresh interval
		// TODO Add try and catch for int
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
		
		PrintStream diskWriter = new PrintStream(new File("settings.dat"));
		
//		String settingsData =  new JSONObject().put("JSON", "Hello, World!").toString();
		
		String settingsData = "hi";
		diskWriter.print (settingsData);
		diskWriter.close();
		
	}


	static void loadSettings() throws FileNotFoundException {
		// Read settings from disk
		// TODO Finish writing parser
		
		Scanner fileParser = new Scanner("settings.dat");
		
		fileParser.next();
		fileParser.close();
		

		
		
	}


	
	
}
