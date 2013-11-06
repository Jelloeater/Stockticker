package jelloeater.StockTicker;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//import java.util.*;



public class ScreenScrape extends App{
	// This Class exists only to take input and return output.
	// There does not have to be any objects
	// It is a simple class :)
		
	// TODO get quote source pick from dialoug in main
	

	static String PriceLookup(String symbol, String quoteSource) throws IOException {
		//Use symbol for lookup info
		String priceString = "23.36"; // Output String 
		
		// Data sources"MarketWatch", "Yahoo", "Google"
		
		if (quoteSource == "MarketWatch") {
			
			String url = "http://www.marketwatch.com/investing/stock/"+symbol;
		    Document document = Jsoup.connect(url).get();
		    // Query symbol page
				
			
	
	
		    
		    // Below = Work in progress
		    
		    
		    
		    String priceString = document.select("#question .post-text").text(); // Searches for price string
		    
		    Elements answerers = document.select("#answers .user-details a");
		    for (Element answerer : answerers) {
		        System.out.println("Answerer: " + answerer.text());
		    }
		    
		    
			// Above = Work in progress
		}
	    
	    
	    
	    
	   	return priceString; // Should return a string
	}


	
	
	

	public static String precentLookup(String ticker, String quoteSource) {
		String precent = "66.6"; // TODO SHOULD BE NULL DUMMY DATA
		// Logic goes here Should scrape ticker to find percent change
		
		return precent;
	}



	public static String priceChangeLookup(String ticker, String quoteSource) {
		String priceChange = "-85.3"; //TODO SHOULD BE NULL DUMMY DATA
		// Should scrape ticker to find price change
		
		return priceChange;
	}
}



	
	
			
//Info to scrape
//	<div class="lastprice">
//	<div class="pricewrap">
//			<p class="currency">$</p>			
//			<p class="data bgLast">1,026.00</p>
//		</div>
//	<p style="clear:left" class="priceclear"></p>
//</div>
//<div class="lastpricedetails">
//	<p class="vertelement column">Change</p>
//	<p class="lastcolumn data">
//		<span class="bgChange">-0.11</span>
//		<span class="bgPercentChange">-0.01%</span>
//	</p>
//<p class="column vertelement">Volume</p>
//<p class="lastcolumn data"><span class="horzelement">Volume </span><span>70,776</span></p>
// <p class="lastcolumn bgTimestamp longformat">Nov 4, 2013, 6:22 p.m.</p>
//<p class="lastcolumn vertelement">Quotes are delayed by 20 min</p>
//</div>
//The default URL is 
//http://www.marketwatch.com/investing/stock/GOOG

