package pl.edu.mimuw.crawler.pm324860;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


  public class Kolejka {
	private Queue<String> Kolejka; 
	private HashSet<String> Odwiedzone; 
	
	public Kolejka(){
		Kolejka=new LinkedList<String>(); 
		Odwiedzone=new HashSet<String>(); 
	}
	
	boolean isEmpty(){
		return Kolejka.isEmpty(); 
	}
	boolean Dodaj(String AktURL){
		if( Odwiedzone.contains(AktURL))
		{
			return false;
		}
		else {
			Kolejka.add(AktURL); 
			Odwiedzone.add(AktURL); 
			return true; 
		}
	}
	String pool(){
		return Kolejka.poll(); 
	}
	


	
}
