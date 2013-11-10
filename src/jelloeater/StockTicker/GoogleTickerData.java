package jelloeater.StockTicker;



import com.google.gson.*;

public class GoogleTickerData {
	private String id;
	private String t;
	private String e;
	private String l;
	private String l_fix;
	private String l_cur;
	private String s;
	private String ltt;
	private String lt;
	private String c;
	private String cp;
	private String ccol;



	public GoogleTickerData() {
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * Converts JSON data
	 * @param quertyString
	 * @return price from JSON
	 */
	public static String price(String quertyString) {
		String returnPrice = null;
		
		Gson gson = new Gson(); // Initializes object
		
		GoogleTickerData obj2 = gson.fromJson(quertyString, GoogleTickerData.class); 
		
		
		System.err.println("Break Here");
		
		
		return returnPrice;
	}

}
