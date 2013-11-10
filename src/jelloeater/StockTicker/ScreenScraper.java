package jelloeater.StockTicker;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sun.java_cup.internal.runtime.Symbol;
//import java.io.IOException;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
import java.util.regex.*;


//import java.io.IOException;

import javax.swing.JOptionPane;


/**
 * 	This Class exists only to take input and return output.
 	There does not have to be any objects
	It is a helper class :)
 * @author Jesse
 */
class ScreenScraper extends App{

		
	// TODO get quote source pick from dialogue in main
	

	/** Looks up price and returns price in form of a string. 
	 * 	Uses global Settings preference for quote source
	 * @param symbol
	 * @return priceString
	 * @throws Throwable
	 * Jsoup needs a throw, should look into why
	 */
	static String priceLookup(String symbol) throws Throwable{ 
		
		String priceString = "0"; // Output String initializer

		if (Settings.getQuoteSource() == "Google") {
			priceString = GoogleLookup.price(symbol);
		}    
		
		if (Settings.getQuoteSource() == "MarketWatch") {
			// TODO Write marketwatch parser	
		}

		if (Settings.getQuoteSource() == "Yahoo") {
			// TODO Write Yahoo price parser
			// https://finance.google.com/finance/info?client=ig&q=JCP
		}
		
		
		return "$"+ priceString; // Return price in string format and adds dollar sign
	}
		    
			
		    
	
	/**
	 * Looks up the percentage change of a ticker symbol
	 * @param ticker
	 * @return percent
	 * @throws Throwable
	 */
	static String precentLookup(String ticker) throws Throwable {
		// Logic goes here Should scrape ticker to find percent change
		String percent= "0"; // Initialize Variable
		
		if (Settings.getQuoteSource() == "Google") {
			// TODO Google percent parser
			
			
		}	
		
		// TODO Add additional quote sources
		return percent;	// OUTPUT
	}



	static String priceChangeLookup(String ticker) {
		String priceChange = "0"; // Initialize Variable
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


