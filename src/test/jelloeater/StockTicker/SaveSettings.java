package jelloeater.StockTicker;

import jwsUtils.UtilsGUI;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SaveSettings extends main {

	@Test
	public void testNoFile() {
		UtilsGUI.setLookAndFeel();
		debugMode = 2;

		settingsProperties.setDefaults();
		settingsProperties.saveSettings();
		settingsProperties.loadSettings();
		
		assertNotNull(settingsProperties);
	}	

}
