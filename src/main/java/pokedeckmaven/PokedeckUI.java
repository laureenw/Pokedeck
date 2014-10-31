package pokedeckmaven;

import java.util.Scanner;

public class PokedeckUI {
	Pokedeck pokedeck = new Pokedeck();
	Scanner scanner = new Scanner(System.in);
	Player p;
	int numCard = 0;
	String nameCard = "";
	int numCardSearch;
	String nameCardSearch;
	
	public void start() {
		boolean stop = false;
		System.out.println("Enter your name : ");
		String playerName = scanner.next();
		p = new Player(playerName);
		pokedeck.setP(p);
		while (!stop) {
			UserChoice choice = user_menu_choice();
			stop = pick_choice(choice);
		}
	}

	private UserChoice user_menu_choice() {
		System.out.println("[1] Add new card\n"
							+ "[2] Delete card\n"
							+ "[3] Update card\n"
							+ "[4] See collection\n"
							+ "[5] Search card\n"
							+ "[6] Save collection\n"
							+ "[7] Upload collection\n"
							+ "[8] Quit");
		int user_choice = scanner.nextInt();
		scanner.nextLine();
		return UserChoice.values()[user_choice-1];
	}

	private boolean pick_choice(UserChoice option) {
		boolean quit = false;
		switch (option) {
		case AddCard:
			do {
				System.out.println("Card name :");
				nameCard = scanner.next();
				scanner.nextLine();
				pokedeck.setNameCard(nameCard);
			} while (pokedeck.getCollectCard().toString().contains(nameCard));
			pokedeck.addCard();
			System.out.println(pokedeck.getMyCard());
			pokedeck.writeCollectCardInFile();					
			break;
		case RemoveCard:
			System.out.println("Enter card number you want to delete : ");
			numCard = scanner.nextInt();
			pokedeck.setNumCard(numCard);
			pokedeck.removeCard();
			System.out.println(pokedeck.getCardDelete() + " has been removed");
			pokedeck.writeCollectCardInFile();					
			break;
		case ModifyCard:
			System.out.println("Enter card number you want to update : ");
			numCard = scanner.nextInt();
			pokedeck.setNumCard(numCard);
			System.out.println("New card name :");
			nameCard = scanner.next();
			scanner.nextLine();
			pokedeck.setNameCard(nameCard);
			pokedeck.modifyCard();
			System.out.println(pokedeck.getCardUpdate() + " has been updated");
			pokedeck.writeCollectCardInFile();
			break;
		case SeeCollection:
			pokedeck.readCollectCardInFile();
			System.out.println("Collection : "+pokedeck.getCollectCard());
			break;
		case SearchCard:
			System.out.println("Enter card number you want to search :");
			numCardSearch = scanner.nextInt();
			pokedeck.setNumCardSearch(numCardSearch);
			System.out.println("Enter card name you want to search : ");
			nameCardSearch = scanner.next();
			scanner.nextLine();
			pokedeck.setNameCardSearch(nameCardSearch);
			if (pokedeck.searchCard() == true) {
				System.out.println("Your card : "+new Card(nameCardSearch, numCardSearch).toString());
			} else {
				System.out.println("Your collection does not contain card : "+new Card(nameCardSearch, numCardSearch).toString());
			}
			break;
		case SaveCollection:
			System.out.println("Backup file : "+p.getName()+".txt");
			pokedeck.writeCollectCardInFile();
			System.exit(0);
			break;
		case UploadCollection:
			System.out.println("Loading file : "+p.getName()+".txt");
			pokedeck.readCollectCardInFile();
			user_menu_choice();
			break;
		case Stop:
			quit = true;
			break;
		default:
			System.out.println("We didn't understand your choice");
			break;
		}
		return quit;
	}
}
