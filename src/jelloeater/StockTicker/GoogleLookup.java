package jelloeater.StockTicker;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;



public class GoogleLookup extends App{ //TODO remove extends (it's only for debugMode)
	
	
	public static String price(String rawData) throws IOException {
	    
		
		String priceString = GoogleTickerData.price(rawData); // Takes JSON Give price
	    
		
		
		
		
		
		
		
		return priceString;
	}
	
	
	
	
	
	public static String percentChange(String rawData) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static String priceChange(String rawData) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

	/**
	 * Gets JSON data from Google and cleans it
	 * @param symbol
	 * @return
	 * @throws IOException
	 */
	public static String getGoogleJSONfromWeb(String symbol) throws IOException{
		String url = "http://finance.google.com/finance/info?client=ig&q="+symbol; // URL to lookup
	    Document document = Jsoup.connect(url).get(); // Pull in dirty JSON data
	    String jsonDirtyIN = document.select("*").text(); // Gets page text
	    
	    // Cleans JSON data
	    String jsonCleanOut = null; // Output variable
		if (debugMode = true) {System.err.println("jsonDirtyIN:"+jsonDirtyIN);}  //TODO Remove debuging code
		//<regex vodo>
	    String re1=".*?";	// Non-greedy match on filler
	    String re2="(\\[.*?\\])";	// Square Braces 1
	    String re3="(.)";	// Any Single Character 1
	    Pattern p = Pattern.compile(re1+re2+re3,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	    Matcher m = p.matcher(jsonDirtyIN); //INPUT
		    if (m.find())
		    {
		        String sbraces1=m.group(1);
		        jsonCleanOut=sbraces1.toString(); //OUTPUT
		    }
		//</regex vodo>
		    
	    String finalJsonOutput = jsonCleanOut.replace("[", "").replace("]", ""); //Clean off brackets
		return finalJsonOutput;
	}


	


	
	

}

