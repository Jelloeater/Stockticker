package jelloeater.StockTicker;

// import java.net.*; // To be used for net connection checking
import javax.swing.JOptionPane;
import jwsUtils.*; // Holds neat bits of code to be reused over time


/*
 * This is a basic stock ticker application. It ticks stocks n stuff
 */
class App {

	/** Debug flag*/
	static boolean debugMode = true;



	/** Holds the ticker list, kinda important, should always load first*/
	static TickerList tickerList = TickerList.makeSingleton();

	/** Holds all the settings for the application in a singleton object */
	static Settings settingsProperties = Settings.makeSingleton();
	// Got a null pointer exception, because I tried to set a variable that didn't exist yet
	// Well fuck me right?

	/** Global configuration file path, used for various settings operations */
	static String configFilePath = "settings.cfg"; // Failsafe default
	public static String tickerListFilePath = "stockList.cfg"; // Failsafe default


	// Cannot initialize early, nothing to load at this point -_-


	/**
	 * This runs first
	 * There should be a minimal amount of methods here, variables should be in objects
	 * @param args
	 * @throws Throwable
	 */
	public static void main(String[] args) throws Throwable {
		// TODO Write update method to loop getting info
		// TODO IDEA Get Symbols from JSON, load into ArrayList?

		App.startupScript();




        tickerList.addStockToList("TSLA");
		tickerList.addStockToList("WMT");
		tickerList.addStockToList("JCP");


		tickerList.addStockToListGUI();

		tickerList.outputTickerListToConsole();
		tickerList.outputIndexToConsole();
		// Good up until here
		// Simulate loading list from file


		Scheduler myScheduler = new Scheduler(); // Starts up Scheduler
		// Starts Scheduler and runs updates to tickerList


		//addStockToListGUI(); // Should get called by + button in GUI

		//TickerWindow.launchGui(null); // FIRE ZE INTERFACE!!! Off to GUI GUI land

		myScheduler.shutdownThread(); // Shuts down the scheduler
		// TODO move to shutdown script when done testing


		//App.shutdownScript();

        System.err.println("end of main");

		//TODO re-enable when GUI is working
		//System.exit(0); // Makes sure program ends



    }

	//////////////////////////////////////////////////////

	private static void startupScript() {
		UtilsGUI.setLookAndFeel(); // Sets look and feel
		addShutdownHook(); // Adds Shutdown hook
		// TODO write failsafe incase load fails w/ null pointer
		settingsProperties.loadSettings(configFilePath); // Loads the program settings from disk

		// FIXME fix casting problem
		//tickerList.loadList(tickerListFilePath);

		tickerList.indexTicker = TickerInfo.makeTickerObject(tickerList.getIndexSymbol());
		//tickerList = TickerList.makeTickerList(settingsProperties.getTickerList);

	}


	private static void shutdownScript() {
		settingsProperties.saveSettings(configFilePath);
		System.err.println("shutdownScript");

		//FIXME fix casting problem
		//tickerList.saveList(tickerListFilePath);

		// TODO Add code for shutting down threads
	}


	/** Shutdown Hook, used to override application close behavior
	 * Runs when System.exit(0) is called or all windows have been disposed of*/
	private static void addShutdownHook(){
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
			// Main Application class name
				shutdownScript(); // Executes shutdown script
				}
		}));
	}

	public static int shutdownWindow(){
		return JOptionPane.showConfirmDialog(null, "Do you want to quit?", null,
                JOptionPane.YES_NO_OPTION);
	}

}
	
