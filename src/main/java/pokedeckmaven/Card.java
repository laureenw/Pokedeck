package pokedeckmaven;

import java.io.Serializable;

public class Card implements Serializable{

	private int num;
	private String name;
	
	public Card(String name, int num) {
		this.num = num;
		this.name = name;
	}
	
	public void Show() {
		System.out.print("Nom de la carte : "+this.name);
	}
	
	public String toString(){
    	String S = this.num+" "+this.name;
    	return S;
    }
}

