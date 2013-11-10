package jelloeater.StockTicker;


import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import sun.misc.IOUtils;


/** Used for cleaning parsing and writing JSON
 * Just a helper class
 * The extend is just so it can read the debug flag
 * @author Jesse*/
class JsonHelper extends App{


	/**
	 * Cleans dirty JSON data from Google
	 * @param jsonDirtyIN
	 * @return
	 */
	public static String jsonCleanGoogle(String jsonDirtyIN){
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

	    if (debugMode = true) {System.err.println("finalJsonOutput:"+finalJsonOutput);}
	    return finalJsonOutput;
	}

	public static String googlePrice(String quertyString) {
		// TODO Auto-generated method stub
		String priceOut = null;
		
		
		
		return priceOut;
	}
	
	
	
}
	
	
