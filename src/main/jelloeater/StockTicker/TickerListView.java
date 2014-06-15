package jelloeater.StockTicker;

/**
 * Created by Jesse on 6/15/2014.
 */
public class TickerListView extends Main{
	static void printTickersFromList(){
		for(TickerModel i : TickerListModel.TickerList){
			i.getTickerInfoDataConsole();
		}
	}
}
