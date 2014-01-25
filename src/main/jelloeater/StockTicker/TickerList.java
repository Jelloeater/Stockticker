package jelloeater.StockTicker;

import com.google.gson.Gson;
import jwsUtils.Utils;

import javax.swing.*;
import java.io.File;
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




	void setupIndexTicker(){
		indexTicker = TickerInfo.makeTickerObject(settingsProperties.getIndexSymbol());
	}

	void setIndexTickerSymbol(String symbolToSet){
		indexTicker = TickerInfo.makeTickerObject(symbolToSet);
	}

	void updateIndexInfo() {
		indexTicker = indexTicker.updateTickerObject(indexTicker);
	}

	/*
	void outputIndexTickerConsole(){
		if (debugMode >= 1) {
			//tickerList.indexTicker.getTickerInfoDataGUI(indexTicker); // full path of object

			indexTicker.getTickerInfoDataConsole(indexTicker);
			// use getTickerInfoDataConsole for output

		}
	}
	*/

	void addStockToListGUI() {
		// FIXME Add check to see if duplicate symbols exist
		String tickerSymbolInput = JOptionPane.showInputDialog("Enter Symbol", "GOOG");
		addStockToList(tickerSymbolInput);
	}

	void addStockToList(String stockToAdd){
		boolean duplicateSymbol;
		TickerInfo myStock;

		do {
			myStock = TickerInfo.makeTickerObject(stockToAdd);
			duplicateSymbol = isSymbolDuplicate(myStock.getTickerSymbol());
		} while (duplicateSymbol);

		tickerListHolder.add(myStock);

	}


	void removeStockFromList(String stockToRemove){
		int stockLocationToRemove=0;
		// FIXME write loop to match string and remove object from array

		// LOOP GOES HERE

		tickerListHolder.remove(stockLocationToRemove);
	}

	/**
	 * Updates tickerListHolder using for loop
	 */
	void updateTickerList() {
		for (int i = 0; i < tickerListHolder.size(); i++) {
			TickerInfo myStock = tickerListHolder.get(i);
			myStock=myStock.updateTickerObject(myStock);
			tickerListHolder.set(i, myStock);
		}
	}

	void clearList() {
		// FIXME Write list clear method USE LOOP? :)

		// FIXME Set empty list

	}

	void deleteListFIle(String listFilePath) {
		File file = new File(listFilePath);
		file.delete();
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
		if (debugMode >= 1) {
			for (int i = 0; i < tickerListHolder.size(); i++) {
				// Get tickerListHolder contents with for loop
				TickerInfo x = tickerListHolder.get(i);
				x.getTickerInfoDataConsole(x); // print individual ticker item to console
				System.out.println(); // Blank line

			}
		}
	}


	String outputTickerListToString() {
		String outputString = "";

		for (int i = 0; i < tickerListHolder.size(); i++) {
			// Get tickerListHolder contents with for loop
			TickerInfo tickerToGet = tickerListHolder.get(i);
			outputString = outputString + tickerToGet.getTickerSymbol() + ":\n" + "Price: "
					               + tickerToGet.getPrice() + "\n" + "% Change: "
					               + tickerToGet.getPercentChange() + "%" + "\n"
					               + "Price Change: " + tickerToGet.getPriceChange() + "\n" + "\n";

		}
		return outputString;
	}


	String outputIndexToString() {
		String outputString;

		TickerInfo tickerToGet = indexTicker;
		outputString = tickerToGet.getTickerSymbol() + ":  $" +
				               tickerToGet.getPrice() + "  " +
				               tickerToGet.getPercentChange() + "%  $" +
				               tickerToGet.getPriceChange();
		return outputString;
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

		if (debugMode >= 1) {
			System.err.println("Contents of TickerList Symbols");
		for (int i = 0; i < tickerListData.size(); i++) {
			// Get tickerListHolder contents with for loop
			String x = tickerListData.get(i);
			System.out.println(x);
		}
		}

		Gson gson = new Gson(); // Initializes object

		String tickerListDataJSON = gson.toJson(tickerListData); // Takes static object variables and converts them


		Utils.writeFile(tickerListFilePath, tickerListDataJSON);

		if (debugMode >= 1) System.err.print("SaveList breakpoint");
	}


	void loadList(String tickerListFilePath) {

		File config = new File(tickerListFilePath);

		if(config.exists()){

			String diskReaderInput = Utils.readFile(tickerListFilePath);

			Gson gson = new Gson(); // Initializes object

			ArrayList tickerSymbolList; // Initialize array for storing ticker symbols

			tickerSymbolList = gson.fromJson(diskReaderInput, ArrayList.class);


			for (int i = 0; i < tickerSymbolList.size(); i++) { // Get tickerListHolder contents with for loop

				String stockToAdd = (String) tickerSymbolList.get(i);
				// Gets value from last and casts it to a string

				App.tickerList.addStockToList(stockToAdd); // Add to list in App class
			}

			if (!tickerListFilePath.equals(settingsProperties.getTickerListFilePath())) {
				JOptionPane.showMessageDialog(null,"Custom list loaded");}
				// Will not popup if loading main list, useful for loading custom lists


		} else {

			tickerList.clearList();

			JOptionPane.showMessageDialog(null, "Portfolio missing, defaults set.");
		}

	}

}

