/**
 * 
 */
package jelloeater.StockTicker;

import java.io.*;
//import java.util.Map;
//import java.util.Scanner;

import javax.swing.JOptionPane;

import jwsUtils.Utils;

import com.google.gson.Gson;

 /**
 This class allows us to manipulate any settings objects we create.
 Settings are dependent throughout the program, they are private so we can sanitize data
 Settings are something that there should only be one of, no need to make more then one object container in the main App class.
 It is "OBJECT Oriented Programming" after all.
 Rather then having to pass multiple values through getters, we can just pass the "bag" object
 @method
 {@link saveSettings}
 */
class Settings extends App{
	
	private static Settings singletonRef; // This stores the state of the singleton, and block additional ones from being created
	private int refreshIntervalSeconds;
	private String quoteSource;
	private String indexSymbol;
	//private boolean refreshEnabled; // Might enable in the future
	
	private Settings(){ // You can't access the constructor
		setDefaults(); // But it does set the defaults
	}
	
	/**
	 * Singleton constructor
	 * @return Settings Object
	 */
	public static Settings makeSingleton() {
      if (singletonRef == null)
          singletonRef = new Settings();
      return singletonRef;
    }

	/** Sets defaults for settings */
	void setDefaults(){
		setQuoteSource("Google"); // default setting for quote source Google
    	setRefreshIntervalSeconds(30); // default interval 30 seconds
    	setIndexSymbol("GOOG");
	}
	
	/*
	// TODO Implement in the future, maybe...
	boolean isRefreshEnabled() {
		return refreshEnabled;
	}
	
	private void setRefreshEnabled(boolean refreshEnabled) {
		this.refreshEnabled = refreshEnabled;
	}
	*/

	public String getIndexSymbol() {
		//FIXME Clicking cancel erased string from object
		return indexSymbol;
	}
	
	
	private void setIndexSymbol(String indexSymbolIN) {
		indexSymbol = indexSymbolIN;
	}
	
	int getRefreshIntervalSeconds() {	
		return refreshIntervalSeconds;
	}
	
	void setQuoteSource(String quoteSourceIN) {
		quoteSource = quoteSourceIN;
	}

	private void setRefreshIntervalSeconds(int refreshIntervalSecondsIN) { // Sets private refresh interval
		refreshIntervalSeconds = refreshIntervalSecondsIN;
	}
	
	void setIndexSymbolGUI(){
		// TODO Might need to get moved or reworked
		 indexSymbol = JOptionPane.showInputDialog("Set Index Symbol", settingsProperties.getIndexSymbol());
			
	}
	
	void setRefreshIntervalSecondsGUI(){	
		boolean inputFail = false;
		do {
			String refreshIntervalSecondsIN = JOptionPane.showInputDialog("Set Interval", settingsProperties.getRefreshIntervalSeconds());
	
			try {
				refreshIntervalSeconds = Integer.parseInt(refreshIntervalSecondsIN);
				inputFail = false;
				} 
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid number", null, 0);
				inputFail = true;
				}
		} while (inputFail == true);		
	}
	
			
	String getQuoteSource() {
		return quoteSource;
	}

	
	/**
	 * Writes the App singleton settingsProperties to the specified configuration file path
	 * @param configFileName
	 */
	void saveSettings(String configFileName){
			
		PrintStream diskWriter = null;
		try {
			diskWriter = new PrintStream(new File(configFileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} // Makes new file / overwrites and assigns object
		
		Gson gson = new Gson(); // Initializes object
		
		String settingsData = gson.toJson(settingsProperties); // Takes static object variables and converts them
		
		diskWriter.print (settingsData); // Writes string to file
		diskWriter.close();	// Closes process
	}

	/**Reads settings from configuration file and copies them to the singleton in App class
	 * @throws IOException 
	 */
	void loadSettings(String configFileName){
	
		File config = new File(configFileName);
		 
		  if(config.exists()){
	
			try {
				String diskReaderInput = Utils.readFile(configFileName);
				  
				  Gson gson = new Gson(); // Initializes object
				  
				  settingsProperties = gson.fromJson(diskReaderInput, Settings.class);
				  
			} catch (IOException  e) {
				e.printStackTrace();
			}
			  
		  }else{ 
			  // load the settings fail safe, this is in case the file path is set wrong
			  settingsProperties.setDefaults();
			  settingsProperties.saveSettings("settings.cfg");
			  App.configFilePath = "settings.cfg";
			  JOptionPane.showMessageDialog(null, "Config missing, defaults set.");
		  }	
	}
	
	private void deleteSettingsFile(String configFilePath){
			File file = new File(configFilePath);
			file.delete();		
	}
	
	
	void restoreDefaultsGUI(){
		int option = JOptionPane.showConfirmDialog(null, "Restore Defaults?", null, 2);
		
		switch (option) {
		case 0:
			settingsProperties.deleteSettingsFile(configFilePath);
			settingsProperties.setDefaults();
			settingsProperties.saveSettings("settings.cfg");
			App.configFilePath = "settings.cfg";
			JOptionPane.showMessageDialog(null, "Defaults Restored.");
			break;
		default:
			break;
		}
	}
	
	
}
