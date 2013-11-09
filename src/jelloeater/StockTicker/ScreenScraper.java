package jelloeater.StockTicker;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//import java.io.IOException;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
import java.util.regex.*;


//import java.io.IOException;

import javax.swing.JOptionPane;



class ScreenScraper extends App{
	// This Class exists only to take input and return output.
	// There does not have to be any objects
	// It is a simple class :)
		
	// TODO get quote source pick from dialogue in main
	


	static String priceLookup(String symbol, String quoteSource) throws Throwable{ // Jsoup needs a throw, should look into why
		//Use symbol for lookup info
		String priceString = null; // Output String 
		
		
		if (quoteSource == "MarketWatch") {
			//TODO Finish MarketWatch price parser
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
			
			String url = "http://finance.google.com/finance/info?client=ig&q="+symbol;
		    Document document = Jsoup.connect(url).get();
		    // Query symbol page
				
		    String quertyString = document.select("*").text(); 
		    // Gets page text
	    
		    
		    
		    // TODO Remove debug window	
		    if (debugMode=true) {
		    	System.err.println("dirty");
				System.err.println("quertyString: " + quertyString);    	
		    }
		    
		    quertyString=JsonParsing.jsonClean(quertyString); // Cleans the junk off the string
		    
		    // TODO Remove debug window	
		    if (debugMode=true) {
		    	System.err.println("clean");
		    	System.err.println("quertyString: " + quertyString);
			}
		    
		    
		    // TODO Remove old lookup method
		    //priceString= googlePriceLookRegex(quertyString); // Old RegEx Lookup
		}    
		
	
		
			
		
		
		return priceString; // Return price in string format
	}
		    
			
		    
	

	static String precentLookup(String ticker, String quoteSource) throws Throwable {
		// Logic goes here Should scrape ticker to find percent change
		String percent= null; // Initialize Variable
		
		if (quoteSource == "Google") {
			// TODO Google percent parser
			
			String url = "http://finance.google.com/finance/info?client=ig&q="+ticker;
		    Document document = Jsoup.connect(url).get();
		    // Query symbol page
				
		    String quertyString = document.select("*").text(); 
		    // INPUT 
		    // Gets page text

		    
		    String txt = quertyString; // Sets Regex string
			
		    
		    
		    String re1=".*?";	// Non-greedy match on filler
		    String re2="(\"-0\\.60\")";	// Double Quote String 1

		    Pattern p = Pattern.compile(re1+re2,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		    Matcher m = p.matcher(txt);
		    if (m.find())
		    {
		        String string1=m.group(1);
		        percent =string1.toString();
		    }
		}	
		
		// TODO Add additional quote sources
		return percent;	// OUTPUT
	}



	static String priceChangeLookup(String ticker, String quoteSource) {
		String priceChange = "-85.3"; //TODO SHOULD BE NULL DUMMY DATA
		// Should scrape ticker to find price change
		
		return priceChange;
		}
	

	static String googlePriceLookRegex(String quertyString){
		// TODO Remove me, I'm outdated
		
		String priceStringOut = null;
		String txt = quertyString; // Sets Regex string
	    
	    
	    // Messy auto generated regex from http://txt2re.com
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
	        priceStringOut=string1.toString();
	    }   
	    
	    // End of messy auto generated regex from http://txt2re.com
	    
	    priceStringOut = priceStringOut.replaceAll("^\"|\"$", ""); // Trim off quotes
	    return priceStringOut;
	    }

	
	
}



	
	
/* JSON DATA EXAMPLE

// [ { "id": "694653" ,"t" : "GOOG" ,"e" : "NASDAQ" ,"l" : "1,016.03" ,"l_fix" : "1016.03" ,"l_cur" : "1,016.03" ,"s": "0" ,"ltt":"4:00PM EST" ,"lt" : "Nov 8, 4:00PM EST" ,"c" : "+8.08" ,"cp" : "0.80" ,"ccol" : "chg" } ] 

*/
			
			


//		<span class="bgChange">-0.11</span>
//		<span class="bgPercentChange">-0.01%</span>


