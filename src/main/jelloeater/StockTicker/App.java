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
	 * There should be a minimal amount of methods here, variables should be in objects
	 * @param args
	 * @throws Throwable
	 */
	public static void main(String[] args) throws Throwable {
		// TODO Write update method to loop getting info
		// TODO IDEA Get Symbols from JSON, load into ArrayList?

		UtilsGUI.setLookAndFeel(); // Sets look and feel
		addShutdownHook(); // Adds Shutdown hook
		settingsProperties.loadSettings(configFilePath); // Loads the program settings from disk
		
		
				// If you declare it as a static class instead, 
				// then it's a "nested" class, which doesn't need a particular object instance.
				/*
				TickerInfo testTickerGUI = new TickerInfo();
				testTickerGUI=testTickerGUI.setTickerViaGui(); // GUI based constructor
				testTickerGUI.getTickerInfoDataGUI(testTickerGUI);
				*/
		
		
		
		
		Scheduler.startTasks();

		
		
		
		
		addStockToList(); // Should get called by + button in GUI
		// TODO write code to print out contents of ArrayList to console as a test
		
		
		
		
		
		
		
		TickerWindow.launchGui(null); // FIRE ZE INTERFACE!!! Off to GUI land
			
		
			
		
		
		System.err.println("brake");
		
		//TODO re-enable when GUI is working
		//System.exit(0); // Makes sure program ends
    }

	
		
	
	

	
	
	
	
	
	
	
	
	static void addStockToList(){
		// TODO Needs symbol verification, try and catch?
		TickerInfo myStock = new TickerInfo(); 
		myStock=myStock.setTickerViaGui(); // Input Window
		
		tickerList.add(myStock);
		
		tickerList.set(0, myStock);
	}


	public static void shutdownScript() {	
		settingsProperties.saveSettings(configFilePath);
		// TODO Add code for shutting down threads 
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
	
