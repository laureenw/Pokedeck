package pokedeck;

import java.util.*;

public class PokedeckUI {
	Pokedeck pokedeck = new Pokedeck();
	Scanner scanner = new Scanner(System.in);
	Player p;
	int numCard = 0;
	String nameCard = "";
	String pokemonType = "";
	int numCardSearch;
	String nameCardSearch;
	String pokemonTypeSearch;
	PokemonType choice_pokemon_type;
	Search search;
	Search_onecriteria search_onecriteria;
	int user_choice;
	int pokemon_type_choice;
	int search_choice;
	int search_onecriteria_choice;
	
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
		do {
			System.out.println("[1] Add new card\n"
					+ "[2] Delete card\n"
					+ "[3] Update card\n"
					+ "[4] See collection\n"
					+ "[5] Search card\n"
					+ "[6] Save collection\n"
					+ "[7] Upload collection\n"
					+ "[8] Quit");
			user_choice = scanner.nextInt();
			scanner.nextLine();
		
		} while (user_choice < 1 || user_choice > 8);

		return UserChoice.values()[user_choice-1];
	}
	
	private PokemonType pokemon_type() {
		do {
			System.out.println("[1] Grass\n"
								+ "[2] Fire\n"
								+ "[3] Water\n"
								+ "[4] Lightning\n"
								+ "[5] Psychic\n"
								+ "[6] Fighting\n"
								+ "[7] Darkness\n"
								+ "[8] Metal\n"
								+ "[9] Fairy\n"
								+ "[10] Dragon\n"
								+ "[11] Colorless");
			pokemon_type_choice = scanner.nextInt();
			scanner.nextLine();
		} while (pokemon_type_choice < 1 || pokemon_type_choice > 11);
		return PokemonType.values()[pokemon_type_choice-1];
	}
	
	private Search search_criteria() {
		System.out.println("[1] Search on single criteria\n"
							+ "[2] Search on multiple criteria");
		search_choice = scanner.nextInt();
		scanner.nextLine();
		return Search.values()[search_choice-1];
	}
	
	private Search_onecriteria search_onecriteria() {
		System.out.println("[1] Search by num\n"
							+ "[2] Search by name\n"
							+ "[3] Search by type");
		search_onecriteria_choice = scanner.nextInt();
		scanner.nextLine();
		return Search_onecriteria.values()[search_onecriteria_choice-1];
	}
	
	public void addCardUI() {
		do {
			System.out.println("Card number :");
			numCard = scanner.nextInt();
			scanner.nextLine();
		} while (pokedeck.getCollectCard().toString().contains(Integer.toString(numCard)));
		do {
			System.out.println("Card name :");
			nameCard = scanner.next();
			scanner.nextLine();
		} while (pokedeck.getCollectCard().toString().contains(nameCard));
		System.out.println("Pokemon type :");
		choice_pokemon_type = pokemon_type();
		pokedeck.addCard(numCard, nameCard, choice_pokemon_type);
		System.out.println(pokedeck.getMyCard());				
	}

	public void removeCardUI() {
		System.out.println("Enter card number you want to delete : ");
		numCard = scanner.nextInt();
		pokedeck.removeCard(numCard);
		System.out.println(pokedeck.getCardDelete() + " has been removed");	
	}
	
	public void modifyCardUI() {
		System.out.println("Enter card number you want to update : ");
		numCard = scanner.nextInt();
		System.out.println("New card name :");
		nameCard = scanner.next();
		scanner.nextLine();
		System.out.println("New pokemon type :");
		choice_pokemon_type = pokemon_type();
		pokedeck.modifyCard(numCard, nameCard, choice_pokemon_type);
		System.out.println(pokedeck.getCardUpdate() + " has been updated");
	}
	
	public void seeCollectionUI() {
		System.out.println("Collection : "+pokedeck.getCollectCard());
	}
	
	public void searchCardUI() {
		System.out.println("Select search type :");
		search = search_criteria();
		if (search_choice == 1) {
			System.out.println("Select the search criteria : ");
			search_onecriteria = search_onecriteria();
			if (search_onecriteria_choice == 1) {
				System.out.println("Enter card number you want to search :");
				numCardSearch = scanner.nextInt();
				scanner.nextLine();
				if (pokedeck.searchCardNum(numCardSearch) == true) {
					System.out.println("Your card : "+pokedeck.getCardSearch());
				} else {
					System.out.println("Your collection does not contain card with number: "+numCardSearch);
				}
			} else if (search_onecriteria_choice == 2) {
				System.out.println("Enter card name you want to search : ");
				nameCardSearch = scanner.next();
				scanner.nextLine();
				if (pokedeck.searchCardName(nameCardSearch) == true) {
					System.out.println("Your card : "+pokedeck.getCardSearch());
				} else {
					System.out.println("Your collection does not contain card with name: "+nameCardSearch);
				}
			} else if (search_onecriteria_choice == 3) {
				System.out.println("Enter pokemon type you want to search : ");
				pokemonTypeSearch = scanner.next();
				scanner.nextLine();
				if (pokedeck.searchCardType(pokemonTypeSearch) == true) {
					System.out.println("Your card : "+pokedeck.getCardSearch());
				} else {
					System.out.println("Your collection does not contain card with pokemon type: "+pokemonTypeSearch);
				}
			}
		} else if (search_choice == 2) {
			System.out.println("Enter card number you want to search :");
			numCardSearch = scanner.nextInt();
			System.out.println("Enter card name you want to search : ");
			nameCardSearch = scanner.next();
			scanner.nextLine();
			System.out.println("Enter pokemon type you want to search : ");
			pokemonTypeSearch = scanner.next();
			scanner.nextLine();
			if (pokedeck.searchCard(numCardSearch, nameCardSearch, pokemonTypeSearch) == true) {
				System.out.println("Your card : "+new Card(nameCardSearch, numCardSearch, pokemonTypeSearch).toString());
			} else {
				System.out.println("Your collection does not contain card");
			}
		}
	}
	
	public void saveCollectionUI() {
		System.out.println("Backup file : "+p.getName()+".txt");
		pokedeck.writeCollectCardInFile();
		System.exit(0);
	}
	
	public void uploadCollectionUI() {
		System.out.println("Loading file : "+p.getName()+".txt");
		pokedeck.readCollectCardInFile();
		user_menu_choice();
	}
	
	private boolean pick_choice(UserChoice option) {
		boolean quit = false;
		switch (option) {
		case ADDCARD:
			addCardUI();
			break;
		case REMOVECARD:
			removeCardUI();			
			break;
		case MODIFYCARD:
			modifyCardUI();
			break;
		case SEECOLLECTION:
			seeCollectionUI();
			break;
		case SEARCHCARD:
			searchCardUI();
			break;
		case SAVECOLLECTION:
			saveCollectionUI();
			break;
		case UPLOADCOLLECTION:
			uploadCollectionUI();
			break;
		case STOP:
			quit = true;
			break;
		default:
			break;
		}
		return quit;
	}
}
