/**
 * 
 */
package jelloeater.StockTicker;

import java.io.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Settings extends App{
	// This class holds all the settings of the application, no need for objects, we don't need multiple versions.
	// HOWEVER, Settings are dependent throughout the program, they are private so we can sanitize data

	private static int refreshIntervalSeconds;
	private static String quoteSource;
	
	public static int getRefreshIntervalSeconds() { // Gets private refresh interval
		
		return refreshIntervalSeconds;
	}


	public static void setRefreshIntervalSeconds(int refreshIntervalSecondsIN) { // Sets private refresh interval
		refreshIntervalSeconds = refreshIntervalSecondsIN;
	}
	

	public static String getQuoteSource() {
		return quoteSource;
	}


	public static void setQuoteSource(String quoteSourceIN) {
		quoteSource = quoteSourceIN;
	}
	
	public static void saveSettings() throws FileNotFoundException { 
	// Writes settings to file		
		PrintStream diskWriter = new PrintStream(new File("settings.dat"));
		diskWriter.print (quoteSource+":"+refreshIntervalSeconds);
		diskWriter.close();
		
	}


	public static void loadSettings() throws FileNotFoundException {
		// Read settings from disk
		// TODO Finish writing parser
		
		Scanner fileParser = new Scanner("settings.dat");
		
		fileParser.next();
		fileParser.close();
		

		
		
	}


	
	
}
