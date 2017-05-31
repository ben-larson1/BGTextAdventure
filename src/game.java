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
		bossBattle();
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
			pl("Wow such a loser. You can't even play this game WHERE YOU CAN'T DIE without cheating...");
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
					+ "attack\t\t\tattacks the monster, only use if there is a dungeon\n\t\t\tafter this input the item you are attacking with\n";
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
				while(true) {
					String askVal = input.nextLine();
					if(askVal.equalsIgnoreCase("yes")) {
						challenge();
					}
				}
				
			} else {
				pl("You cannot challenge anything here");
			}
			
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
		Tile saveTile = m.getCurrentTile();
		m = new Map(1,1, saveTile);
		endVal = false;
	}
	
	public static void bossBattle() {
		while(player.getHealth() > 0 && ((BossRoom)m.getCurrentTile()).getBoss().getHealth() > 0) {
			
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
			p(m.getCurrentTile().accessItems(sel1).getTrades() + "\nDo you wish to trade for anything (if yes then put 0 = top, 1 = next top, etc, if no then put -1):");
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
