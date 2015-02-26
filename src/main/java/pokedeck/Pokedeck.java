package pokedeck;

import java.io.*;
import java.util.*;

public class Pokedeck {

	private static Random random = new Random();
	private static Player p;
	private static ArrayList<Card> collectCard = new ArrayList<Card>();
	private static int numCard = 0;
	private static String nameCard = "";
	private static Card myCard;
	private static Object cardDelete;
	private static Object cardUpdate;
	private static Object cardSearch;
	private static int numCardSearch;
	private static String nameCardSearch;
	private static String pokemonTypeSearch;

	public static void setP(Player p) {
		Pokedeck.p = p;
	}
	
	public static Player getP() {
		return p;
	}
		
	public static ArrayList<Card> getCollectCard() {
		return collectCard;
	}
	
	public static Card getMyCard() {
		return myCard;
	}
	
	public static Object getCardDelete() {
		return cardDelete;
	}
	
	public static Object getCardUpdate() {
		return cardUpdate;
	}
	

	public static Object getCardSearch() {
		return cardSearch;
	}

	public static void writeCollectCardInFile() {
		try {
			FileOutputStream file = new FileOutputStream("src/main/resources/"+p.getName()+".txt");
			ObjectOutputStream oos = new ObjectOutputStream(file);
			oos.writeObject(collectCard);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readCollectCardInFile() {
		try {
			FileInputStream file = new FileInputStream("src/main/resources/"+p.getName()+".txt");
			ObjectInputStream ois = new ObjectInputStream(file);
			collectCard = (ArrayList<Card>) ois.readObject();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void addCard(int numCard, String nameCard, PokemonType choice_pokemon_type) {		
		//numCard = 1 + random.nextInt(1000 - 0);
		collectCard.add(new Card(nameCard, numCard, choice_pokemon_type.toString()));
		for (int i = 0; i < collectCard.size(); i++) {
			myCard = collectCard.get(i);
		}
	}
	
	public static void removeCard(int numCard) {
		for (int i = 0; i < collectCard.size(); i++) {
			if (collectCard.get(i).toString().contains(Integer.toString(numCard))) {
				cardDelete = collectCard.remove(i);
			}			
		}
	}
	
	public static void modifyCard(int numCard, String nameCard, PokemonType choice_pokemon_type) {
		for (int i = 0; i < collectCard.size(); i++) {
			if (collectCard.get(i).toString().contains(Integer.toString(numCard))) {
				cardUpdate = collectCard.set(i, new Card(nameCard, numCard, choice_pokemon_type.toString()));
			}
		}
	}
	
	public static boolean searchCardNum(int numCardSearch) {
		for (int i = 0; i < collectCard.size(); i++) {
			if (collectCard.get(i).toString().contains(Integer.toString(numCardSearch))) {
				cardSearch = collectCard.get(i);
				return true;
			}			
		}
		return false;
	}
	
	public static boolean searchCardName(String nameCardSearch) {
		for (int i = 0; i < collectCard.size(); i++) {
			if (collectCard.get(i).toString().contains(nameCardSearch)) {
				cardSearch = collectCard.get(i);
				return true;
			}			
		}
		return false;
	}
	
	public static boolean searchCardType(String pokemonTypeSearch) {
		for (int i = 0; i < collectCard.size(); i++) {
			if (collectCard.get(i).toString().contains(pokemonTypeSearch)) {
				cardSearch = collectCard.get(i);
				return true;
			}			
		}
		return false;
	}
	
	public static boolean searchCard(int numCardSearch, String nameCardSearch, String pokemonTypeSearch) {
		if (collectCard.toString().contains(new Card(nameCardSearch, numCardSearch, pokemonTypeSearch).toString())) {
			return true;
		} else {
			return false;
		}
	}
}
