package jelloeater.StockTicker;


import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import sun.misc.IOUtils;



class JsonParsing extends ScreenScraper{



	static String jsonClean(String jsonDirtyIN){

			String JSONcleanOUT = null; // Output variable
		
						
		    String re1=".*?";	// Non-greedy match on filler
		    String re2="(\\[.*?\\])";	// Square Braces 1
		    String re3="(.)";	// Any Single Character 1

		    Pattern p = Pattern.compile(re1+re2+re3,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		    Matcher m = p.matcher(jsonDirtyIN);
		    if (m.find())
		    {
		        String sbraces1=m.group(1);
		        String c1=m.group(2);
		        JSONcleanOUT="("+sbraces1.toString()+")"+"("+c1.toString()+")"+"\n";
		        
		        System.err.println("sbraces1: " + sbraces1);
		        System.err.println("c1: " + c1);
		    }
		    
	    // TODO ** Fix JSON Cleaner, Output should ONLY HAVE {}, that's it.
	    
	    return JSONcleanOUT;

	}
	
	static Object googleParse (String stringIn){
		// TODO parse JSON out of string and spit out object
		
		Object objectOut = null;
		
	
		
		if (debugMode = true) {
				JOptionPane.showConfirmDialog(null, objectOut);	
		}

		return objectOut;
	}
	
	
}
	
	
