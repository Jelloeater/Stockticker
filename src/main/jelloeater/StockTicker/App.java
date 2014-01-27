package jelloeater.StockTicker;

// import java.net.*; // To be used for net connection checking

import jwsUtils.UtilsGUI;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;


/*
 * This is a basic stock ticker application. It ticks stocks n stuff
 */
class App {

	/** Debug flag*/
	static int debugMode = 0;
	// 0 = off, 1 = console output only 2 = testing mode

	static boolean shutdownFlag;


	/** Global configuration file path, used for various settings operations */
	static String configFilePath = "settings.cfg"; // Fail safe default

	/** Holds the ticker list, kinda important, should always load first*/
	static TickerList tickerListController = TickerList.makeSingleton();

	/** Holds all the settings for the application in a singleton object */
	static Settings settingsProperties = Settings.makeSingleton();
	// Got a null pointer exception, because I tried to set a variable that didn't exist yet
	// Well fuck me right?




	// Cannot initialize early, nothing to load at this point -_-


	/**
	 * This runs first
	 * There should be a minimal amount of methods here, variables should be in objects
	 * @throws Throwable
	 */
	public static void main(String[] args) throws Throwable {
		// TODO Write update method to loop getting info
		// TODO IDEA Get Symbols from JSON, load into ArrayList?

		App.startupScript();


		//tickerListHolder.addStockToList("JCP");

		if (debugMode >= 2) {
			do {
			int exit = JOptionPane.showConfirmDialog(null, "Add stock to list", null,
					JOptionPane.YES_NO_OPTION);
			if (exit == 1) break;
            tickerListController.addStockToListGUI();
		}
		while (true);

		tickerListController.outputTickerListToConsole();
		tickerListController.outputIndexToConsole();
		}


		if (debugMode >= 1) System.out.print(tickerListController.outputTickerListToString()); // For testing window output


		// Good up until here
		// Simulate loading list from file


		Scheduler myScheduler = new Scheduler(); // Starts up Scheduler
		// Starts Scheduler and runs updates to tickerListHolder


		//addStockToListGUI(); // Should get called by + button in GUI

		while (shutdownFlag) myScheduler.shutdownThread(); // Shuts down the scheduler when flag is set
		// TODO is there a better way to do this?
		myScheduler.updateListTask();


		TickerWindow.launchGui(null); // FIRE ZE INTERFACE!!! Off to GUI GUI land


		if (debugMode >= 1) System.err.println("end of main");

		//TODO re-enable when GUI is working

		if (debugMode >= 2) {

			final CountDownLatch latch = new CountDownLatch(1);
		final Thread t = new Thread(new Runnable() {
			public void run() {
				System.out.println("qwerty");
				latch.countDown();
			}
		});

		t.start();
		latch.await();

		System.out.println("absolutely sure, qwerty as been printed");

		}


	}

	//////////////////////////////////////////////////////

	private static void startupScript() {
		UtilsGUI.setLookAndFeel(); // Sets look and feel
		addShutdownHook(); // Adds Shutdown hook
		// TODO write failsafe incase load fails w/ null pointer
		settingsProperties.loadSettings(); // Loads the program settings from disk

		// FIXME fix casting problem


		// TODO remove comment out
		tickerListController.loadList(settingsProperties.getTickerListFilePath());
		// Should call file path to allow for multi lists in the future
		tickerListController.updateTickerList();

		tickerListController.setupIndexTicker();
		// The index ticker object is stored in TickerList, as that's the type of object
		// The symbol to get saved and loaded at the start and end is stored in settingsProperties, as it's easier to retrieve via JSON
		tickerListController.updateIndexInfo();
	}


	private static void shutdownScript() {
		settingsProperties.saveSettings();
		if (debugMode >= 1) System.err.println("shutdownScript");
		tickerListController.saveList(settingsProperties.getTickerListFilePath());
		shutdownFlag = true;

		// TODO Add code for shutting down threads

		// TODO move to shutdown script when done testing

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
	
