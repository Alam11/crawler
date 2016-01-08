package Demo1;


import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pl.edu.mimuw.crawler.pm324860.Crawler;

public class CrawlerDemo1 extends Crawler {
	String baseDomain;
	HashMap<String,Integer> hrefOutsideDomain = new HashMap<String,Integer>();
	public CrawlerDemo1(String domain){
		try {
			baseDomain = addDomain(domain);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		add(domain); // exceptions !
	}
	
	
	@Override
	public void process(Document actPage) {
		Elements hrefs = getAllHref(actPage);
			for(Element href : hrefs){
				String absActPage = href.absUrl("href");

				try {
					String actDomain= addDomain(absActPage);
					System.out.println(actDomain);
					System.out.println(absActPage);
					if (!baseDomain.equals(actDomain)){
						// obsluga przychodz�cych obcych domen
						if (hrefOutsideDomain.containsKey(actDomain)){
							hrefOutsideDomain.put(actDomain, hrefOutsideDomain.get(actDomain)+1);
						}
						else 
							hrefOutsideDomain.put(actDomain, 1);
					}	
					else 
						add(absActPage);
				} catch (URISyntaxException e) {
					System.out.println("Niepoprawny adres Uri");
				}
				 
			}
		}
		

	@Override
	public Document nextPage() {
		return nextInternetSite();
	}
	public void printDomains(){
		ArrayList<NumOfOccurrences> domainsList=new ArrayList<NumOfOccurrences>();
		if (hrefOutsideDomain.containsKey(null)){
			System.out.println("Niepoprawne odnosniki "+ hrefOutsideDomain.get(null));
			hrefOutsideDomain.remove(null);
		}
		for(String s : hrefOutsideDomain.keySet()){
			domainsList.add(new NumOfOccurrences(s, hrefOutsideDomain.get(s) ));
		}
		Collections.sort(domainsList);
		for(NumOfOccurrences i : domainsList){
			System.out.println(i);
		}
	}
	

}
