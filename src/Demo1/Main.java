package Demo1;

import org.omg.Dynamic.Parameter;
import org.jsoup.nodes.Document;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CrawlerDemo1 Crawler=new CrawlerDemo1 (args[0]);
		while (!Crawler.czyPusta()) {
			Document AktStrona =Crawler.nastepnaStrona();
				Crawler.Przetworz(AktStrona);
		}
		Crawler.wypiszDomeny();
	}

}
