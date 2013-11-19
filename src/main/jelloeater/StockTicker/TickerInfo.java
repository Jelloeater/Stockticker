package jelloeater.StockTicker;

import javax.swing.JOptionPane;

//import java.io.IOException;

/** 
 *  Takes Symbol
 *  Holds all of the ticker data for each iteration
 *  @param symbol 1-4 Letter stock identifier
 *	@author Jesse
 *
 */
//TODO Maybe make it an abstract class?
class TickerInfo extends App{
		
	private String symbol;
	private String price;
	private String percentChange;
	private String priceChange;
	private String rawData;
	
	/**
	 * Constructor for creating up TickerInfo objects
	* @throws Throwable 
	* */
	public TickerInfo() throws Throwable {
	}
	
	/**
	 *  Opens pop-up text box, takes input from text box and create a new object to pass the update method,
	 *  then returns a myStock object that can be parsed via getters
	 *  Lookup logic is dependent on Settings.quoteSource
	 * @return myStock
	 * @throws Throwable
	 */
	public static TickerInfo createNewTickerGui(){
		String tickerSymbolInput=null;
		TickerInfo myStock = null;
		// FIXME: handle exception for no Internet access
				
		tickerSymbolInput= JOptionPane.showInputDialog("Set Symbol","GOOG");

		try {
			myStock = new TickerInfo();
			myStock.setTickerSymbol(tickerSymbolInput);
			myStock = TickerInfo.updateTicker(myStock);
			
		} catch (Throwable e) {
			if (debugMode=true)e.printStackTrace();
		}
		
		return myStock;
	}
	
	
	/**
	 * Updates TickerInfo objects. Each source will have its own unique flow.
	 * @param myStock
	 * @return myStock object with updated data
	 * @throws Exception
	 */
	public static TickerInfo updateTicker(TickerInfo myStock) throws Exception {
		String symbol = myStock.getTickerSymbol();
		
		if (settingsProperties.getQuoteSource() == "Google") { 
			 
			String rawData = GoogleTickerData.getGoogleJSONfromWeb(symbol); // Gets JSON Data
			 
			GoogleTickerData dataStore = new GoogleTickerData(); // Creates dataStore Objects
			 
			dataStore=dataStore.mapJsonDataToObject(rawData); // Sends raw data to JSON parser to be converted to object
			 
			myStock.setPrice(dataStore.getPrice()); // Sets value for constructor
			myStock.setPercentChange(dataStore.getPercentChange()); // Sets value for constructor
			myStock.setPriceChange(dataStore.getPriceChange());// Sets value for constructor	 
		}
			
		return myStock;		
	}
	
	 
	// Getters and Setters below
	public String getTickerSymbol() {
		return symbol;
	}
	
	public String getPrice() {
		return price;
	}
	
	public String getPercentChange() {
		return percentChange;
	}
	
	public String getPriceChange() {
		return priceChange;
	}
	
	public String getRawData() {
		return rawData;
	}

	public void setTickerSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setPrice(String price) {
		this.price = price;
	}


	public void setPercentChange(String percentChange) {
		this.percentChange = percentChange;
	}


	public void setPriceChange(String priceChange) {
		this.priceChange = priceChange;
	}

	/**
	 * Pop-up window that displays data from myStock Object
	 * @param TickerInfo Object
	 */
	static void displayTickerInfoDataGUI(TickerInfo myStock){
		JOptionPane.showMessageDialog(null, 
	    		"Symbol: "+myStock.getTickerSymbol() +"\n"+
	    		"Price: " +myStock.getPrice() +"\n"+
	    		"% Change: "+ myStock.getPercentChange()+"%"+"\n"+
	    		"Price Change: "+ myStock.getPriceChange()
	    		,"LOL OUTPUT", JOptionPane.PLAIN_MESSAGE); 
	}


}
