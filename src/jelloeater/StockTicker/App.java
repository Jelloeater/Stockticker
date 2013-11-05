package jelloeater.StockTicker;


/**
 * This is a basic stock ticker app. It ticks stocks n stuff
 *
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;


public class App {
	
// Application Variables	
public static int refreshInterval = 0; // Stores refresh interval

	// This runs first
    public static void main(String[] args) throws Exception {
       String ticker;
       Double price = null;
       Double precentChange = null;
       Double ammountChange = null;
    	
    	int refreshInterval = SettingsParser.getInterval(); // Gets refresh time for list from XML data file
		// TODO Get Symbols from XML, load into ArrayList
    	
    	tickerInfo myStock = new tickerInfo();
    	myStock.setTickerSymbol("GOOG"); //Setting ticker
    	ticker = myStock.getTickerSymbol();
    	price = myStock.getPrice();
    	
    	
    	
    	
    	System.out.println(Ticker);
    	System.out.println(Price);
    	System.out.println(PrecentChange);
    	System.out.println(AmmountChange);
        
    }




}
