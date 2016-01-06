package Demo1;

public class IloscWystapien implements Comparable<IloscWystapien> {

	private String Domena; 
	private Integer Ilosc; 
	
	public IloscWystapien(String Domena, Integer ile){
		this.Domena=Domena; 
		Ilosc=ile; 
	}
	
//	public Integer getIlosc(){
//		return Ilosc;
//	}
//	public void setIlosc(Integer ile){
//		Ilosc=ile; 
//	}
	@Override
	public String toString(){
		return Domena +" " + Ilosc; 
	}
	
	@Override
	public int compareTo(IloscWystapien arg0) {
		if (this.Ilosc.compareTo(arg0.Ilosc)==0) {
			return this.Domena.compareTo(arg0.Domena) ;	
		}
		else return arg0.Ilosc.compareTo(this.Ilosc);
	}

}
