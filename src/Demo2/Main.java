package Demo2;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jsoup.nodes.Document;


public class Main {

	public static void main(String[] args) {
		CrawlerDemo2 Crawler;
		try {
		Crawler = new CrawlerDemo2 (args[0]);
		int depth= 0;
		while (depth<Integer.parseInt(args[1])){
			while (!Crawler.isEmpty()){
				Document actPage=null;
				try {
					actPage = Crawler.nextPage();
					Crawler.process(actPage);
				} catch (FileNotFoundException fe) {

					System.out.println("Blad odczytu pliku");
				}
			}
			depth+=1;
			for(String page : Crawler.Bufor)
				Crawler.add(page);
			Crawler.Bufor.clear();
		}
		System.out.println(Crawler.numOfVisited());
		} catch (IOException e1) {
			System.out.print("Brak pliku.");
		} 
	}

}
