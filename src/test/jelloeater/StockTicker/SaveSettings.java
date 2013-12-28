package jelloeater.StockTicker;

import static org.junit.Assert.*;

import org.junit.Test;
import jwsUtils.*;

public class SaveSettings extends App{

	@Test
	public void testNoFile() {
		UtilsGUI.setLookAndFeel();
		debugMode= true;
		
		settingsProperties.setDefaults();
		settingsProperties.saveSettings();
		settingsProperties.loadSettings();
		
		assertNotNull(settingsProperties);
	}	

}
