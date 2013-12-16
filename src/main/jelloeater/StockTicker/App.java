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

		

        tickerList.add(TickerInfo.makeTickerObject("JCP"));
        tickerList.add(TickerInfo.makeTickerObject("TSLA"));
        tickerList.add(TickerInfo.makeTickerObject("GOOG"));
		TickerHelper.outputTickerListToConsole();
		TickerHelper.outputIndexToConsole();
		// Good up until here
		// Simulate loading list from file


		// FUCK THIS METHOD CALL
		// WHY YOU NO WORK!?!?!
		// The solution is probably simple, but I'm tired
		// It was calling the shutdown method at the end of main... DERP!

		Scheduler myScheduler = new Scheduler(); // Starts up Scheduler
		// Starts Scheduler and runs updates to tickerList
		

		//addStockToListGUI(); // Should get called by + button in GUI

        //TickerHelper.outputTickerListToConsole();

		//TickerWindow.launchGui(null); // FIRE ZE INTERFACE!!! Off to GUI GUI land

		myScheduler.shutdownThread(); // Shuts down the scheduler
		// TODO move to shutdown script when done testing


        System.err.println("brake");

		//TODO re-enable when GUI is working
		//System.exit(0); // Makes sure program ends



		//exampleTicker.updateListTask();
		//exampleTicker.task2();
		//exampleTicker.shutdownThread();


    }




    /////////////////////////////////////////////////////
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

    static class TickerHelper {

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

        /**
         * Updates tickerList using for loop
         * @throws Exception
         */
        static void updateTickerList() {
            for (int i = 0; i < tickerList.size(); i++) {
                TickerInfo myStock =tickerList.get(i);
                myStock=myStock.updateTickerObject(myStock);
                tickerList.set(i,myStock);
            }
        }
		static void updateIndexInfo() {
			indexTicker = indexTicker.updateTickerObject(indexTicker);
		}

		static void outputIndexToConsole(){
			indexTicker.getTickerInfoDataConsole(indexTicker);
		}

        static void outputTickerListToConsole() {
            if (debugMode){
                for (int i = 0; i < tickerList.size(); i++) {
                    // Get tickerList contents with for loop
                    TickerInfo x =tickerList.get(i);
                    x.getTickerInfoDataConsole(x); // use getTickerInfoDataConsole for output
                    System.out.println();

                }
            }
        }
	}
}
	
