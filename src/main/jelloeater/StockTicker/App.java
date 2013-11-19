package jelloeater.StockTicker;

// import java.net.*; // To be used for net connection checking

import java.util.ArrayList;

import javax.swing.JOptionPane;

import jwsUtils.*; // Holds neat bits of code to be reused over time


/*
 * This is a basic stock ticker application. It ticks stocks n stuff
 */
class App {
	
	/**
	 * Debug flag
	 */
	static boolean debugMode = true;
	
	static boolean applicationRunState = true;
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
		
		// TODO implement quit dialogue box
		while (applicationRunState = false) {
		}
	
		UtilsGUI.setLookAndFeel(); // Sets look and feel
		addShutdownHook(); // Adds Shutdown hook
	
		settingsProperties.loadSettings(configFilePath); // Loads the program settings from disk
		
		TickerWindow.launchGui(null); // FIRE ZE INTERFACE!!! Off to GUI land
		
		//addStockToList();
			
		System.err.println("brake");
    }
	
	static void addStockToList(){
		TickerInfo myStock = TickerInfo.createNewTickerGui(); // Input Window
		
		tickerList.add(myStock);
		
		tickerList.set(0, myStock);
	}


	public static void shutdownScript() {	
		settingsProperties.saveSettings(configFilePath);	
	}
	
	/** Shutdown Hook, used to override application close behavior
	 * Runs when System.exit(0) is called or all windows have been disposed of*/
	static void addShutdownHook(){
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {	
			@Override
			public void run() { // Main Application class name
				shutdownScript(); // Executes shutdown script
				}
		}));
	}
	
	public static int shutdownWindow(){
		int shutdownCode = JOptionPane.showConfirmDialog(null, "Do you want to quit?", null, 0);
		return shutdownCode;
	}
}
	
