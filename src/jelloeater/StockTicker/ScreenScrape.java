package jelloeater.StockTicker;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScreenScrape extends App{
	
		
//	private static final Object TickerInfo = null;
	
	
	
	ScreenScrape tickerInfo = new ScreenScrape();
	tickerInfo.price (0);
	
		
	public ScreenScrape() {
		// TODO Auto-generated constructor stub
		String TickerSymbol = "SPX";
		int price = 0;
		int dailyChange = 0;
		
		
		//ScreenScrape TickerInfo = new ScreenScrape();	
	}
	
	
	public static Object getInfo(String TickerSymbol) {
		int TickerPrice = PriceLookup(TickerSymbol);
		
		
				
		return TickerInfo;
		// TODO Auto-generated method stub
	}
	
	
	
	static int PriceLookup(String Symbol) throws IOException {
		int price = 0;
	
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

