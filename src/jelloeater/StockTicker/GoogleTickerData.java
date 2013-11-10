package jelloeater.StockTicker;



import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.*;

public class GoogleTickerData{

	private String id;
	private String t;
	private String e;
	private String l;
	private String l_fix;
	private String l_cur;
	private String s;
	private String ltt;
	private String lt;
	private String c;
	private String cp;
	private String ccol;


	/**
	 * Parse JSON data to public vars
	 * @param quertyString
	 * @return price from JSON
	 */
	public GoogleTickerData(String rawData) {
		// TODO Auto-generated constructor stub
		Gson gson = new Gson(); // Initializes object
		
		GoogleTickerData obj2 = gson.fromJson(rawData, GoogleTickerData.class); 
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