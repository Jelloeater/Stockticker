package jelloeater.StockTicker;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScreenScrape extends tickerInfo{
	// This Class exists only to take input and return output.
	// There does not have to be any objects
	// It is a simple class :)
		
			
	public ScreenScrape() {
		// TODO Auto-generated constructor stub
	}
	
	
	static Double PriceLookup(String Symbol) throws IOException {
		Double price = null;
	
		String url = "http://www.marketwatch.com/investing/stock/"+Symbol;
	    Document document = Jsoup.connect(url).get();
	    // Query symbol page
	    
	    String question = document.select("#question .post-text").text();
	    System.out.println("Question: " + question);
	    Elements answerers = document.select("#answers .user-details a");
	    for (Element answerer : answerers) {
	        System.out.println("Answerer: " + answerer.text());
	    }
		// Above = Work in progress
		
		return price;
	}



	public static Double precentLookup(String ticker) {
		Double precent = null;
		// TODO Auto-generated method stub
		// Should scrape ticker to find percent change
		return precent;
	}



	public static Double priceChangeLookup(String ticker) {
		Double priceChange = null;
		// TODO Auto-generated method stub
		// Should scrape ticker to find price change
		
		return priceChange;
	}





}



	
	
			
// This is an example parser
//	String url = "http://stackoverflow.com/questions/2835505";
//    Document document = Jsoup.connect(url).get();
//    String question = document.select("#question .post-text").text();
//    System.out.println("Question: " + question);
//    Elements answerers = document.select("#answers .user-details a");
//    for (Element answerer : answerers) {
//        System.out.println("Answerer: " + answerer.text());
//    }


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

