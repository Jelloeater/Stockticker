/**
 * 
 */
package jelloeater.StockTicker;

import java.io.*;
//import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;

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
	
	private int refreshIntervalSeconds;
	private String quoteSource;
	private static Settings singletonRef; // This stores the state of the singleton, and block additional ones from being created
	
	private Settings(){ // You can't access the constructor
		setDefaults();
	}
	/**
	 * We set up the singleton with this
	 * Sets up singleton
	 * We can only do this once, hence the name
	 * We can call this our constructor
	 * @return
	 */
	public static Settings makeSingleton() {
      if (singletonRef == null)
          singletonRef = new Settings();
      return singletonRef;
    }

	/** Sets defaults for settings */
	private void setDefaults(){
		setQuoteSource("Google"); // default setting for quote source Google
    	setRefreshIntervalSeconds(30); // default interval 30 seconds
	}
	
	
	int getRefreshIntervalSeconds() {	
		return refreshIntervalSeconds;
	}


	void setRefreshIntervalSeconds(int refreshIntervalSecondsIN) { // Sets private refresh interval
		// TODO Add try and catch for int
		refreshIntervalSeconds = refreshIntervalSecondsIN;
	}
	
	void setRefreshIntervalSecondsGUI(){
		int refreshIntervalSecondsIN = Integer.parseInt( (JOptionPane.showInputDialog("Set Interval", getRefreshIntervalSeconds())));
		refreshIntervalSeconds = refreshIntervalSecondsIN;
	}

	String getQuoteSource() {
		return quoteSource;
	}


	void setQuoteSource(String quoteSourceIN) {
		quoteSource = quoteSourceIN;
	}
	
	/**Writes settings to file in JSON*/
	void saveSettings(){
		// TODO Should write on program close also
			
		PrintStream diskWriter = null;
		try {
			diskWriter = new PrintStream(new File("settings.cfg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Makes new file / overwrites and assigns object
		
		Gson gson = new Gson(); // Initializes object
		
		String settingsData = gson.toJson(settingsProperties); // Takes static object variables and converts them
		
		diskWriter.print (settingsData); // Writes string to file
		diskWriter.close();	// Closes process
	}

	/**Reads settings from config file 
	 * @throws IOException 
	 */
	void loadSettings(){
	
		File config = new File("settings.cfg");
		 
		  if(config.exists()){
			  // FIXME Throws error :(
			  
			  PrintStream diskReaderInput = null;
			try {
				diskReaderInput = new PrintStream(readFile("settings.cfg"));
				String diskReaderData = diskReaderInput.toString();
				  
				  Gson gson = new Gson(); // Initializes object
				  
				  settingsProperties = gson.fromJson(diskReaderData, Settings.class);
				  
				  diskReaderInput.close();
			} catch (IOException  e) {
				// TODO Auto-generated catch block
				if (debugMode=true)e.printStackTrace();
			}
			  
		  }else{ // load the settings
			  settingsProperties.setDefaults();
			  settingsProperties.saveSettings();
			  JOptionPane.showMessageDialog(null, "Config missing, defaults set.");
		  }	
	}
	
	
	
	void deleteSettingsFile(){
		int option = JOptionPane.showConfirmDialog(null, "Delete settings", null, 2);
		
		switch (option) {
		case 0:
			File file = new File("settings.cfg");
			file.delete();
			JOptionPane.showMessageDialog(null, "Settings deleted");
			break;
		default:
			break;
		}
	}

	void setQuoteSourceGUI() {
		String[] quoteSourceChoices = { "MarketWatch", "Yahoo", "Google"}; 
	       // Dialog box choices array
		settingsProperties.setQuoteSource((String) JOptionPane.showInputDialog(null, null,
	           "Choose Quote Source", JOptionPane.QUESTION_MESSAGE, null, 
	           quoteSourceChoices, // Array of choices
	           settingsProperties.getQuoteSource())); // Initial choice	
	}


	
	
}
