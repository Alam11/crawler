package Demo1;

public class NumOfOccurrences implements Comparable<NumOfOccurrences> {

	private String domain;
	private Integer quantity;
	
	public NumOfOccurrences(String domain, Integer num){
		this.domain = domain;
		quantity = num;
	}

	@Override
	public String toString(){
		return domain +" " + quantity;
	}
	
	@Override
	public int compareTo(NumOfOccurrences numOfOccurrences) {
		if (this.quantity.compareTo(numOfOccurrences.quantity)==0) {
			return this.domain.compareTo(numOfOccurrences.domain) ;
		}
		else return numOfOccurrences.quantity.compareTo(this.quantity);
	}

}
