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
		
		indexTicker = TickerInfo.makeTickerObject(settingsProperties.getIndexSymbol());
		// creates initial index
		// FIXME update index along with arrayList
		
		
		
		//Scheduler updateIndex = new Scheduler();
		/*
		Scheduler exampleTicker = new Scheduler();
		exampleTicker.updateSymbol();
		exampleTicker.task2();
		*/
		//exampleTicker.shutdownThread();
		
		
		//TickerInfo testStock = TickerInfo.makeTickerObjectViaGui();
		//testStock.getTickerInfoDataGUI(testStock);


        tickerList.add(TickerInfo.makeTickerObject("JCP"));
        tickerList.add(TickerInfo.makeTickerObject("TSLA"));
        tickerList.add(TickerInfo.makeTickerObject("GOOG"));


		//TickerInfo testStock2 = TickerInfo.makeTickerObject("GOOG");
		//testStock2.getTickerInfoDataConsole(testStock2);
		// Still works :)
		

		//addStockToListGUI(); // Should get called by + button in GUI

        outputTickerListToConsole();
        
	
		
		// TickerWindow.launchGui(null); // FIRE ZE INTERFACE!!! Off to GUI land


        System.err.println("brake");

		//TODO re-enable when GUI is working
		//System.exit(0); // Makes sure program ends
    }

    private static void outputTickerListToConsole() {
        // FIXME write code to print out contents of ArrayList to console as a test
        // Get tickerList contents with for loop
        // use getTickerInfoDataConsole for output
    }


    /////////////////////////////////////////////////////
	static void addStockToListGUI() {
        // FIXME Add check to see if duplicate symbols exist
        boolean duplicateSymbol;
        TickerInfo myStock;

        do {
            myStock = TickerInfo.makeTickerObjectViaGui();
            duplicateSymbol = isSymbolDuplicate(myStock.getTickerSymbol());
        } while (duplicateSymbol == true);

        tickerList.add(myStock);

    }

    private static boolean isSymbolDuplicate(String tickerSymbol) {
        boolean isDuplicate = false;
        // TODO Write duplicate checker

        return isDuplicate;
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
		return JOptionPane.showConfirmDialog(null, "Do you want to quit?", null, JOptionPane.YES_NO_OPTION);
	}
}
	
