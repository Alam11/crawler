package Demo1;


import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pl.edu.mimuw.crawler.pm324860.Crawler;

public class CrawlerDemo1 extends Crawler {
	String DomenaZrodlowa;
	HashMap<String,Integer> odnosnikiZPozaDomeny=new HashMap<String,Integer>();
	public CrawlerDemo1(String Domena){
		try {
			DomenaZrodlowa=dajDomene(Domena);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dodaj(Domena); // exceptions ! 
	}
	
	
	@Override
	public void Przetworz(Document AktStrona) {
		Elements Odnosniki=znajdzWszystkieOdnosniki(AktStrona);
			for(Element Odnosnik : Odnosniki){
				String absAktStrona=Odnosnik.absUrl("href");
				try {
					String AktDomena=dajDomene(absAktStrona);
					System.out.println(AktDomena);
					if (!DomenaZrodlowa.equals(AktDomena)){
						// obsluga przychodz�cych obcych domen
						if (odnosnikiZPozaDomeny.containsKey(AktDomena)){
							odnosnikiZPozaDomeny.put(AktDomena, odnosnikiZPozaDomeny.get(AktDomena)+1);
						}
						else 
							odnosnikiZPozaDomeny.put(AktDomena, 1);
					}	
					else 
						Dodaj(absAktStrona);
				} catch (URISyntaxException e) {
					System.out.println("Niepoprawny adres Uri");
				}
				 
			}
		}
		

	@Override
	public Document nastepnaStrona() {
		return nastepnaStronaInternet();
	}
	public void wypiszDomeny(){
		ArrayList<IloscWystapien> ListaDomen=new ArrayList<IloscWystapien>();
		if (odnosnikiZPozaDomeny.containsKey(null)){
			System.out.println("Niepoprawne odnosniki "+odnosnikiZPozaDomeny.get(null));
			odnosnikiZPozaDomeny.remove(null);
		}
		for(String s : odnosnikiZPozaDomeny.keySet()){
			ListaDomen.add(new IloscWystapien(s, odnosnikiZPozaDomeny.get(s) ));
		}
		Collections.sort(ListaDomen);
		for(IloscWystapien i : ListaDomen){
			System.out.println(i);
		}
	}
	

}
