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
	String pokemonTypeSearch;
	PokemonType choice_pokemon_type;
	
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
	
	private PokemonType pokemon_type() {
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
		int pokemon_type_choice = scanner.nextInt();
		scanner.nextLine();
		return PokemonType.values()[pokemon_type_choice-1];
	}
	
	public void pick_choice_type(PokemonType option_type) {
		switch (option_type) {
		case grass:
			break;
		case fire:
			break;
		case water:
			break;
		case lightning:
			break;
		case psychic:
			break;
		case fighting:
			break;
		case darkness:
			break;
		case metal:
			break;
		case fairy:
			break;
		case dragon:
			break;
		case colorless:
			break;
		default:
		}
	}
	
	public void addCardUI() {
		do {
			System.out.println("Card name :");
			nameCard = scanner.next();
			scanner.nextLine();
		} while (pokedeck.getCollectCard().toString().contains(nameCard));
		System.out.println("Pokemon type :");
		choice_pokemon_type = pokemon_type();
		pick_choice_type(choice_pokemon_type);
		pokedeck.addCard(nameCard, choice_pokemon_type);
		System.out.println(pokedeck.getMyCard());
		pokedeck.writeCollectCardInFile();				
	}

	public void removeCardUI() {
		System.out.println("Enter card number you want to delete : ");
		numCard = scanner.nextInt();
		pokedeck.removeCard(numCard);
		System.out.println(pokedeck.getCardDelete() + " has been removed");
		pokedeck.writeCollectCardInFile();	
	}
	
	public void modifyCardUI() {
		System.out.println("Enter card number you want to update : ");
		numCard = scanner.nextInt();
		System.out.println("New card name :");
		nameCard = scanner.next();
		scanner.nextLine();
		System.out.println("New pokemon type :");
		choice_pokemon_type = pokemon_type();
		pick_choice_type(choice_pokemon_type);
		pokedeck.modifyCard(numCard, nameCard, choice_pokemon_type);
		System.out.println(pokedeck.getCardUpdate() + " has been updated");
		pokedeck.writeCollectCardInFile();
	}
	
	public void seeCollectionUI() {
		pokedeck.readCollectCardInFile();
		System.out.println("Collection : "+pokedeck.getCollectCard());
	}
	
	public void searchCardUI() {
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
			System.out.println("Your collection does not contain card : "+new Card(nameCardSearch, numCardSearch, pokemonTypeSearch).toString());
		}
	}
	
	public void saveCollectionUI() {
		System.out.println("Backup file : "+p.getName()+".json");
		pokedeck.writeCollectCardInFile();
		System.exit(0);
	}
	
	public void uploadCollectionUI() {
		System.out.println("Loading file : "+p.getName()+".json");
		pokedeck.readCollectCardInFile();
		user_menu_choice();
	}
	
	private boolean pick_choice(UserChoice option) {
		boolean quit = false;
		switch (option) {
		case AddCard:
			addCardUI();
			break;
		case RemoveCard:
			removeCardUI();			
			break;
		case ModifyCard:
			modifyCardUI();
			break;
		case SeeCollection:
			seeCollectionUI();
			break;
		case SearchCard:
			searchCardUI();
			break;
		case SaveCollection:
			saveCollectionUI();
			break;
		case UploadCollection:
			uploadCollectionUI();
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
