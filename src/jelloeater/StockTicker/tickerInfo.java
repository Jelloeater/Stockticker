/**
 * 
 */
package jelloeater.StockTicker;


public class tickerInfo extends App{
		
	private String symbol="TSLA";
	private Double price=100.0;
	private Double percentChange=2.3;
	private Double priceChange=1.99;
		
	/**
	 * 
	 */
	 tickerInfo() {
		// TODO Auto-generated constructor stub
		
	}

	public void setTickerSymbol(String TickerString) {
		TickerString = symbol;
		
	}

	public String getTickerSymbol() {
		// TODO Auto-generated method stub
		
		return symbol;
	}

	public Double getPrice() {
		// TODO Auto-generated method stub
		return price;
	}

}
