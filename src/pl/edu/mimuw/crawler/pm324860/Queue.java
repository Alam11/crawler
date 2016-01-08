package pl.edu.mimuw.crawler.pm324860;

import java.util.HashSet;
import java.util.LinkedList;


public class Queue {
	private java.util.Queue<String> queue;
	private HashSet<String> visited;
	
	public Queue(){
		queue = new LinkedList<String>();
		visited = new HashSet<String>();
	}
	
	boolean isEmpty(){
		return queue.isEmpty();
	}
	boolean add(String AktURL){
		if( visited.contains(AktURL))
		{
			return false;
		}
		else {
			queue.add(AktURL);
			visited.add(AktURL);
			return true; 
		}
	}
	String pool(){
		return queue.poll();
	}
	


	
}
