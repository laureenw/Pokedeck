package pokedeckmaven;

import java.io.Serializable;

public class Card implements Serializable{

	private int num;
	private String name;
	private String type;
	
	public Card(String name, int num, String type) {
		this.num = num;
		this.name = name;
		this.type = type;
	}
	
	public void Show() {
		System.out.print("Nom de la carte : "+this.name);
	}
	
	public String toString(){
    	String S = this.num+" "+this.name+" "+this.type;
    	return S;
    }
}

