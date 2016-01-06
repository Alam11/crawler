package Demo2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pl.edu.mimuw.crawler.pm324860.Crawler;


public class CrawlerDemo2 extends Crawler {
	File  stronaBazowa;
	HashSet<String> Bufor=new HashSet<String>();
	Integer Odwiedzone;
	
	public CrawlerDemo2(String Sciezka) throws IOException{
		File stronaBazowa=new File(Sciezka);
		Dodaj(stronaBazowa.getCanonicalPath()); 
		Odwiedzone=0;
	}
	
	@Override
	public Document nastepnaStrona() throws IOException {
		try {
			Odwiedzone+=1; 
			return nastepnaStronaPlik();
		} catch (FileNotFoundException e) {
			Odwiedzone-=1;
			throw new FileNotFoundException();
		}
	}

	private String DajSciezke(Document AktStrona,String adresRelatywny) throws IOException{
		File tempFile = new File(AktStrona.baseUri());
			tempFile=tempFile.getCanonicalFile();
			tempFile = new File(tempFile.getParent()+File.separator+adresRelatywny);
			return tempFile.getCanonicalPath(); 
		
	}
	@Override
	public void Przetworz(Document AktStrona) {
		Elements Odnosniki=znajdzWszystkieOdnosniki(AktStrona);
		for(Element Odnosnik : Odnosniki){
				String AktStronaString=null;
				try {
					AktStronaString = (DajSciezke(AktStrona,Odnosnik.attr("href")));
					System.out.println(AktStronaString);
				} catch (IOException e) {

					// jak sie tu wywali to znaczy ze 
					//mamy odnosnik do internetu albo zly format odnosnika.
					// wtedy nie wrzucamy.
					// zle adresy do plikow przejda. 
				}
				if (AktStronaString!=null)
						Bufor.add(AktStronaString); 
			}
		}
	public int IloscOdwiedzonych(){
		return Odwiedzone; 
	}
	}

