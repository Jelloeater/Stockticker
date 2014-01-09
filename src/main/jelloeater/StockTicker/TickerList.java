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
	private ArrayList<TickerInfo> tickerListHolder = new ArrayList<TickerInfo>();
	/** Sets up ticker index object*/
	private TickerInfo indexTicker;

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

	void setupIndexTicker(){

		indexTicker=indexTicker.makeTickerObject(settingsProperties.getIndexSymbol());

	}

	void updateIndexTickerInfo(){
		indexTicker=indexTicker.updateTickerObject(indexTicker);
		// FIXME This SHOULD work?!?
	}

	void outputIndexTickerConsole(){
		if (App.debugMode){
			//tickerList.indexTicker.getTickerInfoDataGUI(indexTicker); // full path of object

			indexTicker.getTickerInfoDataConsole(indexTicker);
			// use getTickerInfoDataConsole for output

		}
	}


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

		tickerListHolder.add(myStock);

	}

	void removeStockFromList(String stockToRemove){
		int stockLocationToRemove=0;
		// TODO write loop to match string and remove object from array

		// LOOP GOES HERE

		tickerListHolder.remove(stockLocationToRemove);
	}


	/**
	 * Updates tickerListHolder using for loop
	 * @throws Exception
	 */
	void updateTickerList() {
		for (int i = 0; i < tickerListHolder.size(); i++) {
			TickerInfo myStock = tickerListHolder.get(i);
			myStock=myStock.updateTickerObject(myStock);
			tickerListHolder.set(i, myStock);
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
			for (int i = 0; i < tickerListHolder.size(); i++) {
				// Get tickerListHolder contents with for loop
				TickerInfo x = tickerListHolder.get(i);
				x.getTickerInfoDataConsole(x); // use getTickerInfoDataConsole for output
				System.out.println();

			}
		}
	}




	void saveList(String tickerListFilePath) {



		ArrayList<String> tickerListData = new ArrayList<String>();
		// Initialize array for storing ticker symbols

		for (int i = 0; i < tickerListHolder.size(); i++) {
			// Get tickerListHolder contents with for loop
			TickerInfo x = tickerListHolder.get(i);
			String symbolToStore = x.getTickerSymbol(); // use getTickerSymbol for output
			tickerListData.add(symbolToStore);
		}

		if (debugMode){
			System.err.println("Contents of TickerList Symbols");
		for (int i = 0; i < tickerListData.size(); i++) {
			// Get tickerListHolder contents with for loop
			String x = tickerListData.get(i);
			System.out.println(x);
		}
		}

		Gson gson = new Gson(); // Initializes object

		String tickerListDataJSON = gson.toJson(tickerListData); // Takes static object variables and converts them


		Utils.writeFile(tickerListFilePath,tickerListDataJSON,debugMode);

        System.err.print("SaveList breakpoint");
    }


	void loadList(String tickerListFilePath) {

		File config = new File(tickerListFilePath);

		if(config.exists()){

			String diskReaderInput = Utils.readFile(tickerListFilePath,debugMode);

			Gson gson = new Gson(); // Initializes object

			ArrayList tickerSymbolList = new ArrayList();

			tickerSymbolList = gson.fromJson(diskReaderInput, ArrayList.class);


			ArrayList<String> tickerListData = new ArrayList<String>();
			// Initialize array for storing ticker symbols

			for (int i = 0; i < tickerSymbolList.size(); i++) {
				// Get tickerListHolder contents with for loop

				String stockToAdd = (String) tickerSymbolList.get(i);
				// Gets value from last and casts it to a string

				App.tickerList.addStockToList(stockToAdd); // Add to list in App class
			}

			if (!tickerListFilePath.equals(settingsProperties.getTickerListFilePath())) {
				JOptionPane.showMessageDialog(null,"Custom list loaded");}
				// Will not popup if loading main list, useful for loading custom lists


		}else{
			 // FIXME Set empty list

			JOptionPane.showMessageDialog(null, "Portfolio missing, defaults set.");
		}

	}


	private void deleteList(String configFilePath){
		File file = new File(configFilePath);
		file.delete();
	}

}

