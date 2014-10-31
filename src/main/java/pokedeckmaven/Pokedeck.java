package pokedeckmaven;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

	public Pokedeck() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setP(Player p) {
		Pokedeck.p = p;
	}
	
	public static ArrayList<Card> getCollectCard() {
		return collectCard;
	}
	
	public static void setCollectCard(ArrayList<Card> collectCard) {
		Pokedeck.collectCard = collectCard;
	}
	
	public static void setNumCard(int numCard) {
		Pokedeck.numCard = numCard;
	}
	
	public static void setNameCard(String nameCard) {
		Pokedeck.nameCard = nameCard;
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
	
	public static void setNumCardSearch(int numCardSearch) {
		Pokedeck.numCardSearch = numCardSearch;
	}
	
	public static void setNameCardSearch(String nameCardSearch) {
		Pokedeck.nameCardSearch = nameCardSearch;
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
	
	public static void addCard() {		
		numCard = 1 + random.nextInt(1000 - 0);
		collectCard.add(new Card(nameCard, numCard));
		for (int i = 0; i < collectCard.size(); i++) {
			myCard = collectCard.get(i);
		}
	}
	
	public static void removeCard() {
		
		for (int i = 0; i < collectCard.size(); i++) {
			if (collectCard.get(i).toString().contains(Integer.toString(numCard))) {
				cardDelete = collectCard.remove(i);
			}			
		}
	}
	
	public static void modifyCard() {
		for (int i = 0; i < collectCard.size(); i++) {
			if (collectCard.get(i).toString().contains(Integer.toString(numCard))) {
				cardUpdate = collectCard.set(i, new Card(nameCard, numCard));
			}
		}
	}
	
	public static boolean searchCard() {
		if (collectCard.toString().contains(new Card(nameCardSearch, numCardSearch).toString())) {
			return true;
		} else {
			return false;
		}
	}
}
