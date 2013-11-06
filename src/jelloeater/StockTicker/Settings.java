/**
 * 
 */
package jelloeater.StockTicker;

public class Settings extends App{
	// This class hold all the apps settings in an object

	private int refreshIntervalSeconds;
	private String quoteSource;
	

	public Settings() {
	// TODO Auto-generated constructor stub
	}

	public int getRefreshIntervalSeconds() { // Gets private refresh interval
		
		return refreshIntervalSeconds;
	}


	public void setRefreshIntervalSeconds(int refreshIntervalSecondsIN) { // Sets private refresh interval
		this.refreshIntervalSeconds = refreshIntervalSecondsIN;
	}
	

	public String getQuoteSource() {
		return quoteSource;
	}


	public void setQuoteSource(String quoteSourceIN) {
		this.quoteSource = quoteSourceIN;
	}
	
	public static void saveSettings() { 
	// TODO Write settings to XML file
	}
	
	
}
