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
//		System.out.println(m.toString());
		while(endVal) {
			String cmd = input.nextLine();
			command(cmd);
		}
	}
	
	public static void initialize() { //initializes the game
		System.out.println("Please enter your name...");
		String inputName = input.nextLine();
		player = new Player(inputName);
		System.out.println("Hello " + player.getName() + ", welcome to Canadia");//name is a work in progress
		m.generateInitialMap();
		player.addToInventory(new ItemData("Map",m));
		p(m.getCurrentTile().getText());
	}
	
	public static void command(String cmdLn) {
		String[] cmd = cmdLn.split(" ");
		switch(cmd[0]) {
		case "inventory":
			p(player.getInventory());
			break;
		case "exit":
			endVal = false;
			 break;
		case "go":
			m.moveTo(cmd[1]);
			p(m.getCurrentTile().getText());
			break;
		case "use":
			p(player.use(cmd[1]));
		}
	}
	
	public static void p(Object e) {
		System.out.println(e);
	}

}
