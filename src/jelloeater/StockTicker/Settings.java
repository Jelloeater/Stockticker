/**
 * 
 */
package jelloeater.StockTicker;

public class Settings extends App{

	private int refreshIntervalSeconds;
	private String quoteSource;
	

	public Settings() {
	// TODO Auto-generated constructor stub
		Settings settingsStore = new Settings(); 
		settingsStore.refreshIntervalSeconds = refreshIntervalSeconds;
		settingsStore.quoteSource = quoteSource;
	}

	public static int getInterval() {
	// TODO Auto-generated method stub
		int refreshInterval = 0;
		return refreshInterval;
	}
	
	public void setInterval(int refreshInterval) {
		// TODO Auto-generated method stub
		this.refreshIntervalSeconds = refreshInterval;
	}

	public static String getQuoteSource() {
		// TODO Auto-generated method stub
			
		return null;
	}

	public void setQuoteSource(String quoteSource) {
		this.quoteSource = quoteSource;
	}
}
