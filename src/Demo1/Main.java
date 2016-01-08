package Demo1;

import org.jsoup.nodes.Document;

public class Main {

	public static void main(String[] args) {
		CrawlerDemo1 Crawler=new CrawlerDemo1 (args[0]);
		while (!Crawler.isEmpty()) {
			Document actPage =Crawler.nextPage();
				Crawler.process(actPage);
		}
		Crawler.printDomains();
	}

}
