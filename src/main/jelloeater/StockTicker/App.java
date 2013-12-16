package jelloeater.StockTicker;

// import java.net.*; // To be used for net connection checking
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jwsUtils.*; // Holds neat bits of code to be reused over time


/*
 * This is a basic stock ticker application. It ticks stocks n stuff
 */
class App {

	/** Debug flag*/
	static boolean debugMode = true;

	/** Holds all the settings for the application in a singleton object */
	static Settings settingsProperties = Settings.makeSingleton();

	/** Global configuration file path, used for various settings operations */
	static String configFilePath = "settings.cfg";

	/** Holds all the tickerInfo Objects */
	static ArrayList<TickerInfo> tickerList = new ArrayList<TickerInfo>();

	/** Sets up ticker index object*/
	static TickerInfo indexTicker;
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



        TickerList.addStockToList("TSLA");
		TickerList.addStockToList("WMT");
		TickerList.addStockToList("JCP");
		TickerList.addStockToListGUI();

		TickerList.outputTickerListToConsole();
		TickerList.outputIndexToConsole();
		// Good up until here
		// Simulate loading list from file


		Scheduler myScheduler = new Scheduler(); // Starts up Scheduler
		// Starts Scheduler and runs updates to tickerList


		//addStockToListGUI(); // Should get called by + button in GUI

		//TickerWindow.launchGui(null); // FIRE ZE INTERFACE!!! Off to GUI GUI land

		myScheduler.shutdownThread(); // Shuts down the scheduler
		// TODO move to shutdown script when done testing


        System.err.println("brake");

		//TODO re-enable when GUI is working
		//System.exit(0); // Makes sure program ends



    }

	//////////////////////////////////////////////////////

	private static void startupScript() {
		UtilsGUI.setLookAndFeel(); // Sets look and feel
		addShutdownHook(); // Adds Shutdown hook
		settingsProperties.loadSettings(configFilePath); // Loads the program settings from disk
		indexTicker = TickerInfo.makeTickerObject(settingsProperties.getIndexSymbol());
		//tickerList = TickerList.makeTickerList(settingsProperties.getTickerList);

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

	static class TickerList extends App{

		// FIXME write load method
		// FIXME write save method

		static void addStockToListGUI() {
			// FIXME Add check to see if duplicate symbols exist
			String tickerSymbolInput = JOptionPane.showInputDialog("Set Symbol", "GOOG");
			addStockToList(tickerSymbolInput);
		}

		static void addStockToList(String stockToAdd){
			boolean duplicateSymbol;
			TickerInfo myStock;

			do {
				myStock = TickerInfo.makeTickerObject(stockToAdd);
				duplicateSymbol = isSymbolDuplicate(myStock.getTickerSymbol());
			} while (duplicateSymbol == true);

			App.tickerList.add(myStock);

		}

		static void removeStockFromList(String stockToRemove){
			int stockLocationToRemove=0;
			// TODO write loop to match string and remove object from array

			// LOOP GOES HERE

			App.tickerList.remove(stockLocationToRemove);
		}


		/**
		 * Updates tickerList using for loop
		 * @throws Exception
		 */
		static void updateTickerList() {
			for (int i = 0; i < App.tickerList.size(); i++) {
				TickerInfo myStock = App.tickerList.get(i);
				myStock=myStock.updateTickerObject(myStock);
				App.tickerList.set(i,myStock);
			}
		}

		static void updateIndexInfo() {
			App.indexTicker = App.indexTicker.updateTickerObject(App.indexTicker);
		}

		static boolean isSymbolDuplicate(String tickerSymbol) {
			boolean isDuplicate = false;
			// TODO Write duplicate checker

			return isDuplicate;
		}

		static void outputIndexToConsole(){
			App.indexTicker.getTickerInfoDataConsole(App.indexTicker);
		}

		static void outputTickerListToConsole() {
			if (App.debugMode){
				for (int i = 0; i < App.tickerList.size(); i++) {
					// Get tickerList contents with for loop
					TickerInfo x = App.tickerList.get(i);
					x.getTickerInfoDataConsole(x); // use getTickerInfoDataConsole for output
					System.out.println();

				}
			}
		}
	}
}
	
