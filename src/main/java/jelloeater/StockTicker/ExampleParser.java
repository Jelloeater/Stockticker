package jelloeater.StockTicker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
//import java.util.*;

public class ExampleParser{

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		
//		This is an example parser
		String url = "http://stackoverflow.com/questions/2835505";
	    Document document = Jsoup.connect(url).get();
	    String question = document.select("#question .post-text").text();
	    System.out.println("Question: " + question);
	    Elements answerers = document.select("#answers .user-details a");
	    for (Element answerer : answerers) {
	        System.out.println("Answerer: " + answerer.text());
	    }

	}

}
