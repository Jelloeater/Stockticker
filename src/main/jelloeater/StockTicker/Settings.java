/**
 * 
 */
package jelloeater.StockTicker;

import com.google.gson.Gson;
import jwsUtils.Utils;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

//import java.util.Map;
//import java.util.Scanner;

/**
 This class allows us to manipulate any settings objects we create.
 Settings are dependent throughout the program, they are private so we can sanitize data
 Settings are something that there should only be one of, no need to make more then one object container in the main App class.
 It is "OBJECT Oriented Programming" after all.
 Rather then having to pass multiple values through getters, we can just pass the "bag" object
 @method
 */
class Settings extends App{
	// TODO eventually remove extends, is it really needed?

	private String tickerListFilePath = "stockList.cfg"; // Fail safe default
	private static Settings singletonRef; // This stores the state of the singleton, and blocks additional ones from being created
	private int refreshIntervalSeconds;
	private boolean sourceGoogle; // Used for selection logic
	//private boolean refreshEnabled; // Might enable in the future
	private String indexSymbol; // You would think it would be in settingsProperties,
		// you'd be right
	
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
		setSourceGoogle(); // default setting for quote source Google
    	setRefreshIntervalSeconds(30); // default interval 30 seconds
    	// Yes it's a magic number, yes I don't care -_-
    	setIndexSymbol("GOOG"); // default index symbol
		setTickerListFilePath("stockList.cfg"); // default list path
	}

	String getIndexSymbol() {
		//FIXME Clicking cancel erased string from object
		return indexSymbol;
	}


	void setIndexSymbol(String indexSymbolIN) {
		indexSymbol = indexSymbolIN;
	}

	/** Only sets string value, doesn't actually set object*/
	void setIndexSymbolGUI(){
		// TODO Might need to get moved or reworked
		indexSymbol = JOptionPane.showInputDialog("Set Index Symbol", getIndexSymbol());
	}

	public boolean isSourceGoogle() {
		return sourceGoogle;
	}

	public void setSourceGoogle() {
		this.sourceGoogle = true; //Changed to self reference
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

	String getTickerListFilePath() {
		return tickerListFilePath;
	}

	void setTickerListFilePath(String tickerListFilePathINPUT) {
		tickerListFilePath = tickerListFilePathINPUT;
	}

	int getRefreshIntervalSeconds() {
		return refreshIntervalSeconds;
	}

	private void setRefreshIntervalSeconds(int refreshIntervalSecondsIN) { // Sets private refresh interval
		refreshIntervalSeconds = refreshIntervalSecondsIN;
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
				JOptionPane.showMessageDialog(null, "Please enter a valid number", null, JOptionPane.INFORMATION_MESSAGE);
				inputFail = true;
				}
		} while (inputFail);
	}
	
	
	/**
	 * Writes the App singleton settingsProperties to the specified configuration file path
	 */
	void saveSettings(){
			
		PrintStream diskWriter = null;
		try {
			diskWriter = new PrintStream(new File(App.configFilePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Save settings to disk didn't work", null, JOptionPane.ERROR_MESSAGE);
		} // Makes new file / overwrites and assigns object
		
		Gson gson = new Gson(); // Initializes object
		
		String settingsData = gson.toJson(settingsProperties); // Takes static object variables and converts them
		
		diskWriter.print (settingsData); // Writes string to file
		diskWriter.close();	// Closes process
	}

	/**Reads settings from configuration file and copies them to the singleton in App class
	 * @throws IOException 
	 */
	void loadSettings(){
	
		File config = new File(App.configFilePath);
		 
		  if(config.exists()){

			  String diskReaderInput = Utils.readFile(App.configFilePath);
			  Gson gson = new Gson(); // Initializes object
			settingsProperties = gson.fromJson(diskReaderInput, Settings.class);

		  }else{ 
			  // load the settings fail safe, this is in case the file path is set wrong
			  failsafeLoadSettings();
			  JOptionPane.showMessageDialog(null, "Config missing, defaults set.", null, JOptionPane.INFORMATION_MESSAGE);
		  }	
	}


	private void failsafeLoadSettings() {
		settingsProperties.setDefaults();
		App.configFilePath = "settings.cfg";
		settingsProperties.saveSettings();
	}


	private void deleteSettingsFile(){
		File file = new File(App.configFilePath);
		file.delete();
		JOptionPane.showMessageDialog(null, "Config file removed", null, JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	void restoreDefaultsGUI(){
		int option = JOptionPane.showConfirmDialog(null, "Restore Defaults?", null, JOptionPane.YES_NO_OPTION);

		switch (option) {
		case 0:
			settingsProperties.deleteSettingsFile();
			settingsProperties.failsafeLoadSettings();
			JOptionPane.showMessageDialog(null, "Defaults Restored.");
			break;
		default:
			break;
		}
	}
	
	
}
