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
class TickerInfo {
	
		
	private String symbol;
	private String price;
	private String percentChange;
	private String priceChange;
	private String rawData;
	
	/**Constructor for setting up ticker symbol objects
	* @throws Throwable 
	* */
	TickerInfo(String symbolIN) throws Throwable {
		this.symbol = symbolIN;
		// TODO Add validation for valid symbol
		
		if (Settings.getQuoteSource() == "Google") { 	 
			 
			this.rawData = GoogleTickerData.getGoogleJSONfromWeb(symbol); // Gets JSON Data
			 
			GoogleTickerData dataStore = new GoogleTickerData(); // Creates dataStore Objects
			 
			dataStore=dataStore.mapJsonDataToObject(rawData); // Sends raw data to JSON parser to be converted to object
			 
			this.setPrice(dataStore.getPrice()); // Sets value for constructor
			this.setPercentChange(dataStore.getPercentChange()); // Sets value for constructor
			this.setPriceChange(dataStore.getPriceChange());// Sets value for constructor	 
		}
	}

	/**
	 *  Opens pop-up text box, takes input from text box and feeds it to the TickerInfo constructor,
	 *  then returns a myStock object that can be parsed via getters
	 * @return myStock
	 * @throws Throwable
	 */
	static TickerInfo createNewTickerGui() throws Throwable{
		String tickerSymbolInput=null;
		boolean validSymbolInput = false;
			
		while(validSymbolInput =false);{
			try {
				
				tickerSymbolInput= JOptionPane.showInputDialog("Set Symbol","GOOG");
				
		    	// Lookup logic is dependent on Settings.quoteSource
				validSymbolInput=true;
			} catch (Exception e) {
				// validSymbolInput = false;
				System.err.println("validInput:" + validSymbolInput);
				System.err.println("Enter a valid symbol");
				// FIXME: handle exception
				}
			}
				
		TickerInfo myStock = new TickerInfo(tickerSymbolInput);

		return myStock;
	}
	
	/**
	 * Updates TickerInfo objects. Each source will have its own unique flow.
	 * @param myStock
	 * @return myStock object with updated data
	 * @throws Exception
	 */
	static TickerInfo updateTicker(TickerInfo myStock) throws Exception {
		String symbol = myStock.getTickerSymbol();
		
		if (Settings.getQuoteSource() == "Google") { 
			 
			String rawData = GoogleTickerData.getGoogleJSONfromWeb(symbol); // Gets JSON Data
			 
			GoogleTickerData dataStore = new GoogleTickerData(); // Creates dataStore Objects
			 
			dataStore=dataStore.mapJsonDataToObject(rawData); // Sends raw data to JSON parser to be converted to object
			 
			myStock.setPrice(dataStore.getPrice()); // Sets value for constructor
			myStock.setPercentChange(dataStore.getPercentChange()); // Sets value for constructor
			myStock.setPriceChange(dataStore.getPriceChange());// Sets value for constructor	 
		}
			
		return myStock;	
	}
	 
	// Getters and Setter below
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


	public void setPrice(String price) {
		this.price = price;
	}


	public void setPercentChange(String percentChange) {
		this.percentChange = percentChange;
	}


	public void setPriceChange(String priceChange) {
		this.priceChange = priceChange;
	}


	


}
