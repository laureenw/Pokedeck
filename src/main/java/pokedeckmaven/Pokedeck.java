package pokedeckmaven;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class Pokedeck {

	private static Random random = new Random();
	private static Player p;
	private static ArrayList<Card> collectCard = new ArrayList<Card>();
	private static int numCard = 0;
	private static String nameCard = "";
	private static Card myCard;
	private static Object cardDelete;
	private static Object cardUpdate;
	private static int numCardSearch;
	private static String nameCardSearch;
	private static String pokemonTypeSearch;

	public Pokedeck() {
		
	}
	
	public static void setP(Player p) {
		Pokedeck.p = p;
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

	public static void writeCollectCardInFile() {
		try {
			FileOutputStream file = new FileOutputStream(p.getName()+".txt");
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
			FileInputStream file = new FileInputStream(p.getName()+".txt");
			ObjectInputStream ois = new ObjectInputStream(file);
			collectCard = (ArrayList<Card>) ois.readObject();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void addCard(String nameCard, PokemonType choice_pokemon_type) {		
		numCard = 1 + random.nextInt(1000 - 0);
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
	
	public static boolean searchCard(int numCardSearch, String nameCardSearch, String pokemonTypeSearch) {
		if (collectCard.toString().contains(new Card(nameCardSearch, numCardSearch, pokemonTypeSearch).toString())) {
			return true;
		} else {
			return false;
		}
	}
}
