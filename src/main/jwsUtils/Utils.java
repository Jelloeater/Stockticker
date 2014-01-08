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
	public static String readFile(String pathname) throws IOException {

	    File file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    Scanner scanner = new Scanner(file);
	    String lineSeparator = System.getProperty("line.separator");

	    try {
	        while(scanner.hasNextLine()) {        
	            fileContents.append(scanner.nextLine() + lineSeparator);
	        }
	        return fileContents.toString();
	    } finally {
	        scanner.close();
	    }
	}

	public static void writeFile(String pathname, String stringDataToWrite) throws IOException {

		PrintStream diskWriter = null;

		try {
			diskWriter = new PrintStream(new File(pathname));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Save data to disk didn't work");
		} // Makes new file / overwrites and assigns object

		diskWriter.print (stringDataToWrite); // Writes string to file
		diskWriter.close();	// Closes process
	}
	

}
