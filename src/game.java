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
	
	public static void main(String[] args) {
		initialize(); 
		while(endVal) {
			String cmd = input.nextLine();
			command(cmd);
		}
	}
	
	/**
	 * t
	 */
	public static void initialize() { //initializes the game
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
		switch(cmd[0].toLowerCase()) {
		case "inventory":
			pl(player.getInventory());
			break;
		case "exit":
			endVal = false;
			 break;
		case "go":
			m.moveTo(cmd[1]);
			pl(m.getCurrentTile().getText());
			break;
		case "use":
			pl(player.use(cmd[1]));
			break;
		case "look":
			if(m.getCurrentTile().doesItHaveDungeon()) {
				pl("There is a dungeon");
			}
			pl(m.getCurrentTile().printItemList());
			break;
		case "take":
		case "pick_up":
			Object[] tempVal = m.getCurrentTile().tryPickup(player, cmd[1].toLowerCase());
			player = (Player)tempVal[0];
			pl((String)tempVal[1]);
			break;
		case "help":
		case "?":
			String pVal = "Here are the commands you can use:\n\n"
					+ "inventory\t\tprints out your inventory\n"
					+ "exit\t\t\tquits the game\n"
					+ "go\t\t\tmoves you in a direction\n\t\t\t\tcan be entered as:\n\t\t\t\tn, s, e, w, ne, nw, etc\n\t\t\t\tor north, south, east, west, northeast, northwest, etc\n"
					+ "use\t\t\tuses the selected item\n"
					+ "look\t\t\ttells you the data of the current tile\n"
					+ "take, pick_up\t\tremoves an item from the current tile and adds it to your inventory\n";
			pl(pVal);
					
			
			
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
