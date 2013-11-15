package jelloeater.StockTicker;

import static org.junit.Assert.*;

import org.junit.Test;

public class SaveSettings extends App{

	@Test
	public void testNoFile() {
		App.setLookAndFeel();
		debugMode= true;
		
		settingsProperties.deleteSettingsFile();
		settingsProperties.saveSettings();
		settingsProperties.loadSettings();
		
		assertNotNull(settingsProperties);
	}	

}
