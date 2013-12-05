package jelloeater.StockTicker;

import static org.junit.Assert.*;

//import java.io.File;

import java.io.IOException;

//import java.util.Scanner;
import java.util.regex.*;

import jelloeater.StockTicker.TickerInfo.GoogleTickerData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import jwsUtils.*;


@SuppressWarnings("unused")
public class GoogleTickerDataTest extends App{
	/*
	@Test
	public void testGoogleJsonParser() throws Throwable { // Is the parser broken?
		//Assumes client is online
		// TODO rewrite test to work with private vars?
		UtilsGUI.setLookAndFeel();
		debugMode= true;
	
		String dirtyQuertyString= Utils.readFile("src/test/jelloeater/StockTicker/rawJsonDataForTesting.txt"); // Reads raw file to string
		// It's a mess to try storing it in software, JSON has lots of escape characters that makes java throw up
		//String rawQuertyString = TickerInfo.GoogleTickerData.cleanGoogleJSONdata(dirtyQuertyString);
        
		
		TickerInfo.GoogleTickerData dataStore = new TickerInfo.GoogleTickerData(); // Creates dataStore Objects
		dataStore=GoogleTickerData.mapJsonDataToObject(rawQuertyString); // Sends raw data to JSON parser to be converted to object
		String correctValue = dataStore.getPrice(); // Gets value from process in tickerInfo
		
		

		String regexOutputPrice = GoogleTickerDataTest.oldOfflineGoogleWebRegexParser(rawQuertyString);
		
		assertTrue(null, regexOutputPrice.equals(correctValue));
	}
	*/
	
	public static String oldOfflineGoogleWebRegexParser(String queryString) throws IOException{
		//String txt = quertyString; // Sets Regex string
	    
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
	    Matcher m = p.matcher(queryString);
	    String priceString = null;
		if (m.find())
	    {
	        String string1=m.group(1);
	        priceString=string1.toString();
	    }   
	    
	    // End of messy auto generated regex from http://txt2re.com
	    
	    return priceString = priceString.replaceAll("^\"|\"$", ""); // Trim off quotes
	    
	}
	

	public static String oldOnlineGoogleWebRegexParser(String symbol) throws IOException{
		String url = "http://finance.google.com/finance/info?client=ig&q="+symbol;
	    Document document = Jsoup.connect(url).get();
	    // Query symbol page
			
	    String quertyString = document.select("*").text(); 
	    // Gets page text

	    
	    
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
	    Matcher m = p.matcher(quertyString);
	    String priceString = null;
		if (m.find())
	    {
	        String string1=m.group(1);
	        priceString=string1.toString();
	    }   
	    
	    // End of messy auto generated regex from http://txt2re.com
	    
	    return priceString = priceString.replaceAll("^\"|\"$", ""); // Trim off quotes
	    
	}
}


