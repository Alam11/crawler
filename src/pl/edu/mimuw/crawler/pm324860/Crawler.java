package pl.edu.mimuw.crawler.pm324860;
/**
 * Gl�wna klasa abstrakcjyja biblioteki 
 * Znajduja sie w niej wszyskie metody oraz deklaracje 
 * metod. Zawiera takze dwie zaimplementowane metody sluzace do 
 * parsowania strony na podstawie adresu. 
 * Klasa korzysta z instancji klasy Queue aby przechowywac
 * adresy do odwiedzenia oraz pamietac o odwiedzonych adresach 
 * Zawiera takze funcje odnajdujaca wszsytke adresy w drzewei DOM
 * Oraz kilka przydatnych metod ktore dzialaja na klasie Queue.
 * 
 */
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public abstract class Crawler {
	public Queue Queue =new Queue();
	
	public abstract Document nextPage() throws IOException;
	public abstract void process(Document actPage) throws IOException;
	
	public Document nextInternetSite(){
		Document resultPage = new Document("");
		String adress = Queue.pool();
		try {
			resultPage = Jsoup.connect(adress).get();
		} catch (java.net.SocketTimeoutException e) {
			System.out.println("Przekroczenie czasu oczekiwania na lacze");
		}
		catch (org.jsoup.HttpStatusException e){
			System.out.println("Blad 404");
		}
		catch (org.jsoup.UnsupportedMimeTypeException e){
			// jak cos nie jest strona to nie ma linkow
		}
		catch( IOException e){
			e.printStackTrace(); 
		}
		
		return resultPage;
	}
	
	public Document nextPageFile() throws IOException{
		String adress = Queue.pool();
		File mainSite =new File(adress);
		return Jsoup.parse(mainSite, "UTF-8" );
		
	}
	
	public String addDomain(String actPage) throws URISyntaxException{
		URI uri = new URI(actPage);
		String Domena =uri.getHost();
		if (Domena!=null)
			return Domena.startsWith("www.") ? Domena.substring(4) : Domena;
		return Domena;
		}
	
	public boolean isEmpty(){
		return Queue.isEmpty();
	}
	public String nextAderss(){
		return Queue.pool();
	}
	
	public void add(String url){
		Queue.add(url);
		} 
	
	public Elements getAllHref(Document actPAge){
		return actPAge.select("a");
	}

	
	}