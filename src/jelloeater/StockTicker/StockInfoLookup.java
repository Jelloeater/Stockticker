package jelloeater.StockTicker;

import java.util.regex.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 	All of these methods take a ticker symbol
 * AND the Settings.getQuoteSource
	It is a helper class :)
 * @author Jesse
 */
class StockInfoLookup extends App{

	static String priceLookup(String symbol) throws Throwable{ 
		String priceString = null;
		/*
		if (Settings.getQuoteSource() == "Google") {
			priceString = GoogleLookup.price(symbol);
		}    
		*/
		if (Settings.getQuoteSource() == "MarketWatch") {
			// TODO Write marketwatch parser	
		}

		if (Settings.getQuoteSource() == "Yahoo") {
			// TODO Write Yahoo price parser
			// Yahoo only offers csv files
			//"http://download.finance.yahoo.com/d/quotes.csv?s="+symbol+"&f=l1"; // URL to lookup
		}
		
		
		return priceString; // Return price in string format and adds dollar sign
	}
		    
			

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
}


	/* DEPCERCIATED
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
	    */

