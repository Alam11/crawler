package pl.edu.mimuw.crawler.pm324860;
/**
 * Glówna klasa abstrakcjyja biblioteki 
 * Znajduja sie w niej wszyskie metody oraz deklaracje 
 * metod. Zawiera takze dwie zaimplementowane metody sluzace do 
 * parsowania strony na podstawie adresu. 
 * Klasa korzysta z instancji klasy Kolejka aby przechowywac
 * adresy do odwiedzenia oraz pamietac o odwiedzonych adresach 
 * Zawiera takze funcje odnajdujaca wszsytke adresy w drzewei DOM
 * Oraz kilka przydatnych metod ktore dzialaja na klasie Kolejka. 
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public abstract class Crawler {
	public Kolejka Kolejka=new Kolejka();
	
	public abstract Document nastepnaStrona() throws IOException; 
	public abstract void Przetworz(Document AktStrona) throws IOException;
	
	public Document nastepnaStronaInternet(){
		Document StronaWynikowa = new Document(""); 
		String Adres = Kolejka.pool();
		try {
			StronaWynikowa = Jsoup.connect(Adres).get();
		} catch (java.net.SocketTimeoutException e) {
			System.out.println("Przekroczenie czasu oczekiwania na lacze");
		}
		catch (org.jsoup.HttpStatusException e){
			System.out.println("Blad 404");
		}
		catch( IOException e){
			e.printStackTrace(); 
		}
		
		return StronaWynikowa; 
	}
	
	public Document nastepnaStronaPlik() throws IOException{ 
		String Adres = Kolejka.pool();
		File stronaBazowa=new File(Adres);
		return Jsoup.parse(stronaBazowa, "UTF-8" );
		
	}
	
	public String dajDomene(String AktStrona) throws URISyntaxException{
		URI uri = new URI(AktStrona); 
		String Domena =uri.getHost(); 
		if (Domena!=null)
			return Domena.startsWith("www.") ? Domena.substring(4) : Domena;
		return Domena; 
		}
	
	public boolean czyPusta(){
		return Kolejka.isEmpty(); 
	}
	public String nstepnyAdres(){
		return Kolejka.pool(); 
	}
	
	public void Dodaj(String url){
		Kolejka.Dodaj(url);
		} 
	
	public Elements znajdzWszystkieOdnosniki(Document AktStrona){
		return AktStrona.select("a"); 
	}

	
	}