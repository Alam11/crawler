package Demo2;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jsoup.nodes.Document;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CrawlerDemo2 Crawler;
		try {
		Crawler = new CrawlerDemo2 (args[0]);
		int glembokosc= 0;
		while (glembokosc<Integer.parseInt(args[1])){
			while (!Crawler.czyPusta()){
				Document AktStrona=null;
				try {
					AktStrona = Crawler.nastepnaStrona();
					Crawler.Przetworz(AktStrona);
				} catch (FileNotFoundException fe) {
					System.out.println("Blad odczytu pliku");
				}
			}
			glembokosc+=1; 
			for(String Strona : Crawler.Bufor)
				Crawler.Dodaj(Strona);
			Crawler.Bufor.clear();
		}
		System.out.println(Crawler.IloscOdwiedzonych()); 
		} catch (IOException e1) {
			System.out.print("Brak pliku.");
		} 
	}

}
