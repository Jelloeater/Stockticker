package jelloeater.StockTicker;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GoogleLookup extends StockInfoLookup{ //TODO remove extends (it's only for debugMode)
	
	public static String price(String symbol) throws IOException {
		// TODO Auto-generated method stub
		
		String quertyString=GoogleLookup.getGoogleJSONfromWeb(symbol); // Gets JSON data from web
	    String priceString = GoogleTickerData.price(quertyString); // Gets price out of JSON
	     
	    return priceString;
	}

	private static String getGoogleJSONfromWeb(String symbol) throws IOException{
		String url = "http://finance.google.com/finance/info?client=ig&q="+symbol; // URL to lookup
	    Document document = Jsoup.connect(url).get(); // Pull in dirty JSON data
	    String jsonData = document.select("*").text(); // Gets page text
	    
	    
	    
	    GoogleLookup.cleanGoogleJSON(jsonData); // Cleans data
	    
		return jsonData;
	}
	
	// TODO Remove debug code
	private static String cleanGoogleJSON(String jsonDirtyIN){
		String jsonCleanOut = null; // Output variable
		if (debugMode = true) {System.err.println("jsonDirtyIN:"+jsonDirtyIN);}
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

