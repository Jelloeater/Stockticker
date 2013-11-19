package jwsUtils;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 * Contains handy bits of code for GUI programming
 * @author Jesse Laptop
 *
 */
public class UtilsGUI {
	/**
	 * Sets look and feel to system default
	 */
	public static void setLookAndFeel(){
		try {
	        // Set System L&F
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
	}
}
