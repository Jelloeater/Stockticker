package jelloeater.StockTicker;

import static org.junit.Assert.*;

import org.junit.Test;

public class SaveSettings extends App{

	@Test
	public void testNoFile() {
		App.setLookAndFeel();
		debugMode= true;
		
		settingsProperties.setDefaults();
		settingsProperties.saveSettings("settings.cfg");
		settingsProperties.loadSettings("settings.cfg");
		
		assertNotNull(settingsProperties);
	}	

}
