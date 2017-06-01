import java.io.*;
import java.util.*;

/*
 * Ben Larson
 * 5/4/2017
 * BG Text Adventure Game
 */

public class game {
	public static Map m = new Map(10, 10);
	private static Player player;
	private static Scanner input = new Scanner(System.in);
	public static boolean endVal = true;
	public static boolean checkForExit = false;
	
	/**
	 * this is what runs the game
	 * @param args
	 */
	public static void main(String[] args) {
		initialize(); 
		while(endVal) {
			if(player.getHealth() <= 0) {
				break;
			}
			String cmd = input.nextLine();
			command(cmd);
		}
		if(!checkForExit) {
			bossBattle();
		}
	}
	
	/**
	 * this is what sets up the game
	 */
	public static void initialize() { 
		System.out.println("Please enter your name...");
		String inputName = input.nextLine();
		player = new Player(inputName);
		System.out.println("Hello " + player.getName() + ", welcome to Canadia");//name is a work in progress
		m.generateInitialMap();
		m.setTile(9, 9, new BossRoom());
		player.addToInventory(new ItemData("Map",m));
		pl(m.getCurrentTile().getText());
	}
	
	/**
	 * this is what runs the stuff you trigger
	 * @param cmdLn : input the command given by the user
	 */
	public static void command(String cmdLn) {
		String[] cmd = cmdLn.split(" ");
		Object[] tempVal;
		switch(cmd[0].toLowerCase()) {
		case "inventory":
			pl(player.getInventory());
			break;
		case "exit":
			endVal = false;
			 break;
		case "go":
			if(cmd.length > 1) {
				m.moveTo(cmd[1]);
			}
			pl(m.getCurrentTile().getText());
			player.heal(1);
			if(m.getCurrentTile().getClass().toString().equalsIgnoreCase("class Town")) {
				player.setHealth(100);
				pl("The townsfolk have healed you with the mystical power of aspectualization");
			}
			break;
		case "use":
			if(cmd.length > 1) {
				pl(player.use(cmd[1]));
			} else {
				pl("Insufficient parameters");
			}
			break;
		case "look":
			if(m.getCurrentTile().doesItHaveDungeon()) { 
				pl("\nThere is a dungeon");
			}
			pl(m.getCurrentTile().printItemList());
			break;
		case "take":
		case "pick_up":
			if(cmd.length > 1) {
				tempVal = m.getCurrentTile().tryPickup(player, cmd[1].toLowerCase());
				player = (Player)tempVal[0];
				pl((String)tempVal[1]);
			} else {
				pl("Insufficient parameters");
			}
			break;
		case "inspect":
			if(cmd.length > 1) {
				if(player.has(cmd[1])) {
					pl(player.inspect(cmd[1]));
				} else {
					pl("you don't have that item...");
				}
			} else {
				pl("Insufficient Parameters");
			}
			
			break;
		case "attack":
			if(m.getCurrentTile().getClass().toString().equalsIgnoreCase("class BossRoom")) {
				if(cmd.length > 1 ) {
					tempVal = player.attack(cmd[1], m);
					m = (Map)tempVal[1];
					pl(tempVal[0]);
				} else {
					pl("Please enter what you wish to attack with");
				}
			}
			
			
			break;
		case "cheat":
			player.addToInventory(new ItemData("EpicSword",10000));
			pl("Wow such a loser. You can't even play this game without cheating...");
			break;
		case "skip":
			m.skip();
			pl("You have skipped to the BossRoom");
			break;
		case "reflect":
			pl("Player Health:\t" + player.getHealth() + "\nCashMoneys:\t" + player.getCashMoney());
			break;
		case "help":
		case "?":
			String pVal = "Here are the commands you can use:\n\n"
					+ "inventory\t\tprints out your inventory\n"
					+ "exit\t\t\tquits the game\n"
					+ "go\t\t\tmoves you in a direction\n\t\t\t\tcan be entered as:\n\t\t\t\tn, s, e, w, ne, nw, etc\n\t\t\t\tor north, south, east, west, northeast, northwest, etc\n"
					+ "use\t\t\tuses the selected item\n"
					+ "look\t\t\ttells you the data of the current tile\n"
					+ "take, pick_up\t\tremoves an item from the current tile and adds it to your inventory\n"
					+ "inspect\t\t\tprints out all of the data for an imputted item\n"
					+ "attack\t\t\tattacks the monster, only use if there is a dungeon\n\t\t\tafter this input the item you are attacking with\n"
					+ "reflect\t\t\tprints your health and amount of Ca$hMoneys\n"
					+ "interact\t\tlets you access the shops owned by the villagers (who are conveniently named Todd)\n"
					+ "challenge\t\tstarts the final boss battle. can only be used in the BossRoom.\n\t\t\tonce you use this command, there is no going back\n";
			pl(pVal);
			break;
		case "stupid":
			pl("Nice try");
			break;
		case "interact":
			pl(interact());
			break;
		case "challenge":
			if(m.getCurrentTile().getClass().toString().equalsIgnoreCase("class BossRoom")) {
				p("Are you sure you are ready, you cannot leave once you challenge the Boss: ");
				
				String askVal = input.nextLine();
				if(askVal.equalsIgnoreCase("yes")) {
					checkForExit = false;
					challenge();
					break;
				} else {
					pl("Prepare before challenging next time");
				}
				
				
			} else {
				pl("You cannot challenge anything here");
			}
			break;
			
		default:
			pl("Wat?");
			
			
		}
	}
	
	/**
	 * this method executes when you challenge the Boss.
	 * it re-defines the map as being 1x1 with the only tile being the BossRoom from the original map
	 * once this finishes, the game will terminate
	 */
	public static void challenge() {
		pl("You have initiated the challenge");
		Tile saveTile = m.getCurrentTile();
		m = new Map(1,1, saveTile);
		endVal = false;
	}
	
	/**
	 * this is the loop for when the player is engaged in the Boss battle, when it ends the program ends
	 */
	public static void bossBattle() {
		while(player.getHealth() > 0 && m.getCurrentTile().getDungeon().getMonster().getHealth() > 0) {
			bossBattleCMD(input.nextLine());
		}
	}
	
	/**
	 * this is the special command
	 */
	public static void bossBattleCMD(String command) {
		String[] cmd = command.split(" ");
		Object[] tempVal;
		switch(cmd[0].toLowerCase()) {
		case "inventory":
			pl(player.getInventory());
			break;
		case "exit":
			pl("You can't exit the game in the BossRoom");
			 break;
		case "go":
			pl("You can't leave the BossRoom during the challenge");
			break;
		case "use":
			if(cmd.length > 1) {
				pl(player.use(cmd[1]));
			} else {
				pl("Insufficient parameters");
			}
			break;
		case "look":
			pl("You are engaged in the Boss battle, I would suggest you hurry up");
			break;
		case "take":
		case "pick_up":
			pl("You can't take anything here");
			break;
		case "inspect":
			if(cmd.length > 1) {
				if(player.has(cmd[1])) {
					pl(player.inspect(cmd[1]));
				} else {
					pl("you don't have that item...");
				}
			} else {
				pl("Insufficient Parameters");
			}
			
			break;
		case "attack":
			if(cmd.length > 1 ) {
				tempVal = player.attack(cmd[1], m);
				m = (Map)tempVal[1];
				pl(tempVal[0]);
			} else {
				pl("Please enter what you wish to attack with");
			}
			
			break;
		case "cheat":
			player.addToInventory(new ItemData("EpicSword",10000));
			pl("Wow such a loser. You can't even play this game without cheating...");
			break;
		case "reflect":
			pl("Player Health:\t" + player.getHealth() + "\nCashMoneys:\t" + player.getCashMoney());
			break;
		case "help":
		case "?":
			String pVal = "Here are the commands you can use:\n\n"
					+ "inventory\t\tprints out your inventory\n"
					+ "exit\t\t\tquits the game\n"
					+ "go\t\t\tmoves you in a direction\n\t\t\t\tcan be entered as:\n\t\t\t\tn, s, e, w, ne, nw, etc\n\t\t\t\tor north, south, east, west, northeast, northwest, etc\n"
					+ "use\t\t\tuses the selected item\n"
					+ "look\t\t\ttells you the data of the current tile\n"
					+ "take, pick_up\t\tremoves an item from the current tile and adds it to your inventory\n"
					+ "attack\t\t\tattacks the monster, only use if there is a dungeon\n\t\t\tafter this input the item you are attacking with\n";
			pl(pVal);
			break;
		case "stupid":
			pl("Nice try");
			break;
		case "interact":
			pl("If you are trying to interact with the Boss, you can't. He will kill you unless you kill it");
			break;
		default:
			pl("Wat?");
			
			
		}
	}
	
	/**
	 * interacts with an NPC
	 * @param interactWith : the NPC you are interacting with
	 * @return the value that is printed
	 */
	public static String interact() {
		String retVal = "";
		if (!m.getCurrentTile().getClass().toString().equalsIgnoreCase("class Town")) {
			return "You cannot interact with anything here";
		} else {
			p("\nPlease enter the NPC you wish to trade with (0 = top, 1 = next top, etc): ");
			Scanner tempIn = new Scanner(System.in);
			int sel1 = tempIn.nextInt();
			p(m.getCurrentTile().accessItems(sel1).getTrades() + "\nDo you wish to trade for anything (if yes then put 0 = top, 1 = next top, etc, if no then put -1): ");
			if(m.getCurrentTile().accessItems(sel1).trade(player, tempIn.nextInt())) {
				return "Purchase Successful!";
			} else {
				return "You need more Ca$hMoneys";
			}
		}
	}
	
	/**
	 * ie: System.out.println()
	 * @param e : the Object that will be printed
	 */
	public static void pl(Object e) { System.out.println(e); }
	
	/**
	 * ie: System.out.print()
	 * @param e : the Object that will be printed
	 */
	public static void p(Object e) { System.out.print(e); }

}
