package jelloeater.StockTicker;

//import java.io.IOException;
//import java.util.*;

// import java.net.*; // To be used for net connection checking

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
//import javax.imageio.ImageIO;
// This is a basic stock ticker application. It ticks stocks n stuff
import javax.swing.JOptionPane; // For pop-ups
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


class App {
	
	/**
	 * Debug flag
	 */
	static boolean debugMode = true;
	/**
	 * Holds all the settings for the application in a singleton object
	 * You can try and make another, but it won't let you
	 */
	 static Settings settingsProperties = Settings.makeSingleton();
	 
	 /** Global configuration file path, used for various settings operations*/
	 static String configFilePath = "settings.cfg";
	
	
	 static ArrayList<TickerInfo> tickerList = new ArrayList<TickerInfo>();


	/**
	 * This runs first
	 * There should be a minimal amount of methods here, and preferably no variables,
	 * They should be stored in a class
	 * @param args
	 * @throws Throwable
	 */
	public static void main(String[] args) throws Throwable {
		// TODO Write update method to loop getting info
		// TODO IDEA Get Symbols from JSON, load into ArrayList?
		
		App.setLookAndFeel(); // Sets look and feel
	
		settingsProperties.loadSettings(configFilePath); // Loads the program settings from disk
		
		TickerWindow.launchGui(null); // FIRE ZE INTERFACE!!!
		
		
		
		
		/*
		App.displayGuiInfoWindow(myStock);// Output Window
		myStock = TickerInfo.updateTicker(myStock); // Update method
		*/
       
		
		
		
		if (debugMode= true )System.err.println("Brake");
		
		// END OF THE LINE
    }
	
	static void addStockToList(){
		TickerInfo myStock = TickerInfo.createNewTickerGui(); // Input Window
		
		tickerList.add(myStock);
		
		tickerList.set(0, myStock);
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
	
	/**
	 * Just a handy method for reading text files to strings
	 * @param pathname
	 * @return String
	 * @throws IOException
	 */
	public static String readFile(String pathname) throws IOException {

	    File file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    Scanner scanner = new Scanner(file);
	    String lineSeparator = System.getProperty("line.separator");

	    try {
	        while(scanner.hasNextLine()) {        
	            fileContents.append(scanner.nextLine() + lineSeparator);
	        }
	        return fileContents.toString();
	    } finally {
	        scanner.close();
	    }
	}
	
	
	/**
	 * Sets look and feel to system default
	 */
	static void setLookAndFeel(){
		try {
	        // Set System L&F
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
	}

	public static int shutdown() {
		// TODO Auto-generated method stub
		settingsProperties.saveSettings(configFilePath);
		System.err.println("we are shuting down now!");
		System.exit(0);
		return 0;
		
		
	}
	
	
}
	
