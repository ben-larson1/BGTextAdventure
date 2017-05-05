import java.io.*;
import java.util.Scanner;

/*
 * Ben Larson
 * 5/4/2017
 * BG Text Adventure Game
 */

public class game {

	public static void main(String[] args) {
		initialize(); 
		Scanner input = new Scanner(System.in);
		
	}
	
	public static void initialize() {
		Map m = new Map(10,10);
		m.borderWarning();
	}

}
