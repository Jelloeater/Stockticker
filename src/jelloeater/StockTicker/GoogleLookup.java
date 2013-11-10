package jelloeater.StockTicker;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GoogleLookup extends ScreenScraper{

	public static String price(String symbol) throws IOException {
		// TODO Auto-generated method stub
		
		
		String url = "http://finance.google.com/finance/info?client=ig&q="+symbol; // URL to lookup
	    Document document = Jsoup.connect(url).get(); // Pull in dirty JSON data

	    String quertyString = document.select("*").text(); // Gets page text

	    quertyString=JsonHelper.jsonCleanGoogle(quertyString); // Cleans the junk off the string
	    
	    String priceString = JsonHelper.googlePrice(quertyString); // Gets price out of JSON
	    
	    return priceString;
	}


	
	
}
