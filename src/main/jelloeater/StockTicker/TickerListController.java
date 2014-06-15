package jelloeater.StockTicker;

/**
 * Created by Jesse on 6/14/2014.
 */
public class TickerListController extends TickerListView{

	static void addTickerToList(String symbol){
		TickerListModel.TickerList.add(new TickerModel(symbol));
	}

	static void removeTickerFromList(String symbol){
		for (TickerModel i : TickerListModel.TickerList) {
			if (i.getTickerSymbol().equals(symbol)) {
				TickerListModel.TickerList.remove(i);
			}
		}
	}
}
