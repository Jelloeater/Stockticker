package jelloeater.StockTicker;

// import java.net.*; // To be used for net connection checking

import com.sun.xml.internal.bind.v2.TODO;
import jwsUtils.UtilsGUI;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;


/*
 * This is a basic stock ticker application. It ticks stocks n stuff
 */
class Main {

	/**
	 * Debug flag
	 */
	static int debugMode = 0;
	// 0 = off, 1 = console output only 2 = testing mode

	static boolean shutdownFlag;


	/**
	 * Global configuration file path, used for various settings operations
	 */
	static String configFilePath = "settings.cfg"; // Fail safe default

	/** Holds the ticker list, kinda important, should always load first*/
	// TODO Maybe use singleton

	/**
	 * Holds all the settings for the application in a singleton object
	 */
	static Settings settingsProperties = Settings.makeSingleton();
	// Got a null pointer exception, because I tried to set a variable that didn't exist yet
	// Well fuck me right??


	// Cannot initialize early, nothing to load at this point -_-


	/**
	 * This runs first
	 * There should be a minimal amount of methods here, variables should be in objects
	 *
	 * @throws Throwable
	 */
	public static void main(String[] args) throws Throwable {
		// TODO Write update method to loop getting info
		// TODO IDEA Get Symbols from JSON, load into ArrayList?

		Main.startupScript();



		//tickerListHolder.addStockToList("JCP");
		TickerModel stock = new TickerModel("JCP");

		stock.getTickerInfoDataConsole();
		System.out.print("EOP");
	}


	// Good up until here
	// Simulate loading list from file


	//////////////////////////////////////////////////////

	private static void startupScript() {
		UtilsGUI.setLookAndFeel(); // Sets look and feel
		addShutdownHook(); // Adds Shutdown hook
		// TODO write failsafe incase load fails w/ null pointer
		settingsProperties.loadSettings(); // Loads the program settings from disk

		// FIXME fix casting problem


		// TODO remove comment out

		// TODO Load List
		// Should call file path to allow for multi lists in the future
		//TODO Setup list

		//TODO Setup index
		// The index ticker object is stored in mainGuiController, as that's the type of object
		// The symbol to get saved and loaded at the start and end is stored in settingsProperties, as it's easier to retrieve via JSON
		//TODO update Index
	}


	private static void shutdownScript() {
		settingsProperties.saveSettings();
		if (debugMode >= 1) System.err.println("shutdownScript");
		// TODO Save List
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
	
