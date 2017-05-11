import java.io.*;
import java.util.Scanner;

/*
 * Ben Larson
 * 5/4/2017
 * BG Text Adventure Game
 */

public class game {
	private static Map m = new Map(10,10);
	
	public static void main(String[] args) {
		initialize(); 
		Scanner input = new Scanner(System.in);
		System.out.println(m.toString());
	}
	
	public static void initialize() {
//		Map m = new Map(10,10);
		m.generateInitialMap();
		m.borderWarning();
	}

}
