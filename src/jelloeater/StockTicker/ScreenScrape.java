package jelloeater.StockTicker;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.regex.*;


import java.io.IOException;

import javax.swing.JOptionPane;



public class ScreenScrape{
	// This Class exists only to take input and return output.
	// There does not have to be any objects
	// It is a simple class :)
		
	// TODO get quote source pick from dialogue in main
	

	private static final String String = null;



	static String priceLookup(String symbol, String quoteSource) throws Throwable{
		//Use symbol for lookup info
		String priceString = null; // Output String 
		
		
		if (quoteSource == "MarketWatch") {
			
			String url = "http://www.marketwatch.com/investing/stock/"+symbol;
		    Document document = Jsoup.connect(url).get();
		    // Query symbol page
				

   
		    priceString = document.select("#pricewrap .data bgLast").text(); // Searches for price string
		    //			<p class="data bgLast">1,026.00</p>
		    
		    JOptionPane.showMessageDialog(null, priceString); 
			// TODO Remove debug output
		}

		
		if (quoteSource == "Yahoo") {
			// TODO Yahoo price parser
			// https://finance.google.com/finance/info?client=ig&q=JCP
			
			
			priceString = "999.99";
		}
		
		
		
		if (quoteSource == "Google") {
			// TODO Google price parser
			
			String url = "http://finance.google.com/finance/info?client=ig&q="+symbol;
		    Document document = Jsoup.connect(url).get();
		    // Query symbol page
				

   
		    String quertyString = document.select("*").text(); // Parses for price string
		    
		    /*
		     * // [ { "id": "26944" ,"t" : "JCP" ,"e" : "NYSE" ,"l" : "8.31" ,"l_cur" : "8.31" ,"s": "2" ,"ltt":"4:00PM EST" ,"lt" : "Nov 5, 4:00PM EST" ,"c" : "-0.05" ,"cp" : "-0.60" ,"ccol" : "chr" ,"el": "8.26" ,"el_cur": "8.26" ,"elt" : "Nov 5, 7:59PM EST" ,"ec" : "-0.05" ,"ecp" : "-0.60" ,"eccol" : "chr" ,"div" : "" ,"yld" : "" } ] 
		     */

		    String txt = quertyString; // Sets Regex string
		    
		    String re1=".*?";	// Non-greedy match on filler
		    String re2="\".*?\"";	// Uninteresting: string
		    String re3=".*?";	// Non-greedy match on filler
		    String re4="\".*?\"";	// Uninteresting: string
		    String re5=".*?";	// Non-greedy match on filler
		    String re6="\".*?\"";	// Uninteresting: string
		    String re7=".*?";	// Non-greedy match on filler
		    String re8="\".*?\"";	// Uninteresting: string
		    String re9=".*?";	// Non-greedy match on filler
		    String re10="\".*?\"";	// Uninteresting: string
		    String re11=".*?";	// Non-greedy match on filler
		    String re12="\".*?\"";	// Uninteresting: string
		    String re13=".*?";	// Non-greedy match on filler
		    String re14="\".*?\"";	// Uninteresting: string
		    String re15=".*?";	// Non-greedy match on filler
		    String re16="(\".*?\")";	// Double Quote String 1

		    Pattern p = Pattern.compile(re1+re2+re3+re4+re5+re6+re7+re8+re9+re10+re11+re12+re13+re14+re15+re16,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		    Matcher m = p.matcher(txt);
		    if (m.find())
		    {
		        String string1=m.group(1);
		        priceString=string1.toString();
		    }

		         
		      
		
		    
		    
		    
		    // JOptionPane.showMessageDialog(null,url);
		    JOptionPane.showMessageDialog(null, priceString); 
			// TODO Remove debug output
		}    
	
		
		return priceString; // Should return a string
	}
		    
			
		    
	
	


	
	
	

	public static String precentLookup(String ticker, String quoteSource) {
		// Logic goes here Should scrape ticker to find percent change
		
		if (quoteSource == "Google") {
			// TODO Google percent parser
			
			
			
			
		}
			
		String percent= null;
		return percent ;
	}



	public static String priceChangeLookup(String ticker, String quoteSource) {
		String priceChange = "-85.3"; //TODO SHOULD BE NULL DUMMY DATA
		// Should scrape ticker to find price change
		
		return priceChange;
	}
}



	
	
			
			


//		<span class="bgChange">-0.11</span>
//		<span class="bgPercentChange">-0.01%</span>


