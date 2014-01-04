package jelloeater.StockTicker;

import com.google.gson.Gson;
import jwsUtils.Utils;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Jesse Laptop on 12/17/13.
 */
public class TickerList{
	/** Holds all the tickerInfo Objects */
	ArrayList<TickerInfo> tickerList = new ArrayList<TickerInfo>(); // Arraylist made up of TickerInfo Objects
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

		/* Old file save method
		PrintStream diskWriter = null;
		try {
			diskWriter = new PrintStream(new File(App.tickerListFilePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Save settings to disk didn't work");
		} // Makes new file / overwrites and assigns object

		Gson gson = new Gson(); // Initializes object

		String settingsData = gson.toJson(tickerList); // Takes static object variables and converts them

		diskWriter.print (settingsData); // Writes string to file
		diskWriter.close();	// Closes process

        System.err.print("SaveList breakpoint");
		*/

		// Serialize data
		try {

			FileOutputStream fileOut = new FileOutputStream(App.tickerListFilePath);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(tickerList);
			out.close();
			fileOut.close();

			}catch (IOException e){
			System.err.print("File conversion error");
			e.printStackTrace();
		}

		System.err.print("SaveList breakpoint");

	}


	void loadList(){

		File config = new File(App.tickerListFilePath);

		if(config.exists()){

			TickerList tickerListData = null;
			try
			{
				FileInputStream fileIn = new FileInputStream(App.tickerListFilePath);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				tickerListData = (TickerList) in.readObject();
				in.close();
				fileIn.close();
			}catch(IOException i)
			{
				i.printStackTrace();
				return;
			}catch(ClassNotFoundException c)
			{
				System.out.println("TickerList class not found");
				c.printStackTrace();
			}


			/*
			try {
				String diskReaderInput = Utils.readFile(App.tickerListFilePath);
				Gson gson = new Gson(); // Initializes object
				ArrayList <TickerList> tickerList = gson.fromJson(diskReaderInput, ArrayList.class);
				//App.tickerList=tickerList;
				// ---(1)--- Fix casting problem when loading list from file                     ----------------

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
		*/
		}

	}

	private void deleteList(String configFilePath){
		File file = new File(configFilePath);
		file.delete();
	}

}

