package jelloeater.StockTicker;

import static org.junit.Assert.*;

import org.junit.Test;


public class UnitTests{

	@Test
	public void testMain() throws Throwable {
		

		Settings.setQuoteSource("Google");
		String tickerSymbolInput = "GOOG";
		tickerInfo myStock = new tickerInfo(tickerSymbolInput); //Create ticker info object using symbol
		
		
		
		
		String outputPrice = myStock.getPrice();
		String correctValue = "1,016.03";
		assertTrue(null, outputPrice.equals(correctValue));		
		
	}
	


}
