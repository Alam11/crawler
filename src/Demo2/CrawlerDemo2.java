package Demo2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pl.edu.mimuw.crawler.pm324860.Crawler;


public class CrawlerDemo2 extends Crawler {
	File frontPage;
	HashSet<String> Bufor = new HashSet<String>();
	Integer numVisitedSites;
	
	public CrawlerDemo2(String Sciezka) throws IOException{
		File frontPage=new File(Sciezka);
		add(frontPage.getCanonicalPath());
		numVisitedSites =0;
	}
	
	@Override
	public Document nextPage() throws IOException {
		try {
			numVisitedSites +=1; 
			return nextPageFile();
		} catch (FileNotFoundException e) {
			numVisitedSites -=1;
			throw new FileNotFoundException();
		}
	}

	private String addPath(Document actPage, String relativeURI) throws IOException{
		File tempFile = new File(actPage.baseUri());
			tempFile=tempFile.getCanonicalFile();
			tempFile = new File(tempFile.getParent()+File.separator+ relativeURI);
			return tempFile.getCanonicalPath(); 
		
	}
	@Override
	public void process(Document actPage) {
		Elements hrefs = getAllHref(actPage);
		for(Element href : hrefs){
				String actPAgeURI =null;
				try {
					actPAgeURI = (addPath(actPage,href.attr("href")));
					System.out.println(actPAgeURI);
				} catch (IOException e) {

					// jak sie tu wywali to znaczy ze 
					//mamy odnosnik do internetu albo zly format odnosnika.
					// wtedy nie wrzucamy.
					// zle adresy do plikow przejda. 
				}
				if (actPAgeURI !=null)
						Bufor.add(actPAgeURI);
			}
		}
	public int numOfVisited(){
		return numVisitedSites; 
	}
	}

