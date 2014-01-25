package jwsUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * This is a package that contains handy bits of code to reuse over long periods of time
 * @author Jesse Laptop
 *
 */
public class Utils {
	/**
	 * Just a handy method for reading text files to strings, takes pathname
	 * @param pathname Path to file
	 * @return String
	 */
	public static String readFile(String pathname) {

		File file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// FIXME handle file not found exception
			e.printStackTrace();

		}
		String lineSeparator = System.getProperty("line.separator");

		try {
			if (scanner != null) {
				while (scanner.hasNextLine()) {
					fileContents.append(scanner.nextLine()).append(lineSeparator);
				}
			}
			return fileContents.toString();

		} finally {
			if (scanner != null) scanner.close();
		}
	}

	/**
	 * Just a handy method for writing text files to strings, takes pathname
	 *
	 * @param pathname          Path to file
	 * @param stringDataToWrite Data to be written in the form of a String
	 */
	public static void writeFile(String pathname, String stringDataToWrite) {

		PrintStream diskWriter = null;

		try {
			diskWriter = new PrintStream(new File(pathname));
		} catch (FileNotFoundException e) {
			// FIXME what to do if file not found

			e.printStackTrace();
			System.err.println("Save data to disk didn't work");
		} // Makes new file / overwrites and assigns object

		if (diskWriter != null) {
			diskWriter.print(stringDataToWrite); // Writes string to file
			diskWriter.close();	// Closes process
		}
	}
	

}
