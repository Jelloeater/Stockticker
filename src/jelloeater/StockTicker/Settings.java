/**
 * 
 */
package jelloeater.StockTicker;

public class Settings extends App{

	private int refreshIntervalSeconds;
	private String quoteSource;
	

	public Settings() {
	// TODO Auto-generated constructor stub
	}

	public int getRefreshIntervalSeconds() { // Gets private refresh interval
		
		return refreshIntervalSeconds;
	}


	public void setRefreshIntervalSeconds(int refreshIntervalSeconds) { // Sets private refresh interval
		this.refreshIntervalSeconds = refreshIntervalSeconds;
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
