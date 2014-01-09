package jwsUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * This is a package that contains handy bits of code to reuse over long periods of time
 * @author Jesse Laptop
 *
 */
public class Utils {
	/**
	 * Just a handy method for reading text files to strings
	 * @param pathname
	 * @return String
	 * @throws IOException
	 */
	public static String readFile(String pathname, boolean debugMode){

	    File file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// FIXME handle file not found exception
			if(debugMode) e.printStackTrace();

		}
		String lineSeparator = System.getProperty("line.separator");

	    try {
	        while(scanner.hasNextLine()) {        
	            fileContents.append(scanner.nextLine() + lineSeparator);
	        }
	        return fileContents.toString();


	    }
		finally {
	        scanner.close();
	    }
	}

	public static void writeFile(String pathname, String stringDataToWrite, boolean debugModeFlag){

		PrintStream diskWriter = null;

		try {
			diskWriter = new PrintStream(new File(pathname));
		} catch (FileNotFoundException e) {
			// FIXME what to do if file not found


			if (debugModeFlag) e.printStackTrace();
			System.err.println("Save data to disk didn't work");
		} // Makes new file / overwrites and assigns object

		diskWriter.print (stringDataToWrite); // Writes string to file
		diskWriter.close();	// Closes process
	}
	

}
