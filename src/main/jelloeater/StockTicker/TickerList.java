package jelloeater.StockTicker;

import com.google.gson.Gson;
import jwsUtils.Utils;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Created by Jesse Laptop on 12/17/13.
 */
public class TickerList extends App{
	/** Holds all the tickerInfo Objects */
	ArrayList<TickerInfo> tickerList = new ArrayList<TickerInfo>();
	/** Sets up ticker index object*/
	TickerInfo indexTicker;
	private String indexSymbol; // You would think it would be in TickerList, you'd be right
	// TODO replace all calls to index symbol with indexTicker.getSymbol()

	private static TickerList singletonRef; // This stores the state of the singleton, and blocks additional ones from being created

	/**
	 * Singleton constructor
	 * @return Settings Object
	 */
	public static TickerList makeSingleton() {
		if (singletonRef == null)
			singletonRef = new TickerList();
		return singletonRef;
	}


	// FIXME write load method
	// FIXME write save method

	void addStockToListGUI() {
		// FIXME Add check to see if duplicate symbols exist
		String tickerSymbolInput = JOptionPane.showInputDialog("Set Symbol", "GOOG");
		addStockToList(tickerSymbolInput);
	}

	void addStockToList(String stockToAdd){
		boolean duplicateSymbol;
		TickerInfo myStock;

		do {
			myStock = TickerInfo.makeTickerObject(stockToAdd);
			duplicateSymbol = isSymbolDuplicate(myStock.getTickerSymbol());
		} while (duplicateSymbol == true);

		tickerList.add(myStock);

	}

	void removeStockFromList(String stockToRemove){
		int stockLocationToRemove=0;
		// TODO write loop to match string and remove object from array

		// LOOP GOES HERE

		tickerList.remove(stockLocationToRemove);
	}


	/**
	 * Updates tickerList using for loop
	 * @throws Exception
	 */
	void updateTickerList() {
		for (int i = 0; i < tickerList.size(); i++) {
			TickerInfo myStock = tickerList.get(i);
			myStock=myStock.updateTickerObject(myStock);
			tickerList.set(i, myStock);
		}
	}

	void updateIndexInfo() {
		indexTicker = indexTicker.updateTickerObject(indexTicker);
	}

	boolean isSymbolDuplicate(String tickerSymbol) {
		boolean isDuplicate = false;
		// TODO Write duplicate checker

		return isDuplicate;
	}

	void outputIndexToConsole(){
		indexTicker.getTickerInfoDataConsole(indexTicker);
	}

	void outputTickerListToConsole() {
		if (App.debugMode){
			for (int i = 0; i < tickerList.size(); i++) {
				// Get tickerList contents with for loop
				TickerInfo x = tickerList.get(i);
				x.getTickerInfoDataConsole(x); // use getTickerInfoDataConsole for output
				System.out.println();

			}
		}
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
		indexSymbol = JOptionPane.showInputDialog("Set Index Symbol", App.tickerList.getIndexSymbol());
	}



	void saveList(){

		PrintStream diskWriter = null;
		try {
			diskWriter = new PrintStream(new File(App.tickerListFilePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Save settings to disk didn't work");
		} // Makes new file / overwrites and assigns object

		ArrayList<String> tickerListData = new ArrayList<String>();
		// Initialize array for storing ticker symbols

		for (int i = 0; i < tickerList.size(); i++) {
			// Get tickerList contents with for loop
			TickerInfo x = tickerList.get(i);
			String symbolToStore = x.getTickerSymbol(); // use getTickerSymbol for output
			tickerListData.add(symbolToStore);
		}

		if (debugMode){
			System.err.println("Contents of TickerList Symbols");
		for (int i = 0; i < tickerListData.size(); i++) {
			// Get tickerList contents with for loop
			String x = tickerListData.get(i);
			System.out.println(x);
		}
		}

		Gson gson = new Gson(); // Initializes object

		String settingsData = gson.toJson(tickerListData); // Takes static object variables and converts them


		diskWriter.print (settingsData); // Writes string to file
		diskWriter.close();	// Closes process

        System.err.print("SaveList breakpoint");
    }


    static void loadList(){

		File config = new File(App.tickerListFilePath);

		if(config.exists()){

			try {
				String diskReaderInput = Utils.readFile(App.tickerListFilePath);

				Gson gson = new Gson(); // Initializes object

                ArrayList <TickerList> tickerList = gson.fromJson(diskReaderInput, ArrayList.class);

                //App.tickerList=tickerList;

                // FIXME ---(1)--- Fix casting problem when loading list from file                     ----------------

                System.err.print("break here");
            } catch (IOException e) {
				e.printStackTrace();
			}

		}else{
			// load the settings fail safe, this is in case the file path is set wrong

			 // TODO might need to tweak this down the road
			//tickerList.setDefaults();
			//tickerList.saveSettings("settings.cfg");
			//App.tickerListFilePath = "settings.cfg";


			JOptionPane.showMessageDialog(null, "Portfolio missing, defaults set.");
		}
	}

	private void deleteList(String configFilePath){
		File file = new File(configFilePath);
		file.delete();
	}

}

