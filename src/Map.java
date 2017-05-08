import java.util.Random;

public class Map {
	private int x, y;
	private int[][] grid;
	private String mapType;
	private Random r = new Random();
	
	public Map(int xVal, int yVal) {
		x = xVal;
		y = yVal;
		mapType = "default";
		generateMap();
	}
	
	public Map(int xVal, int yVal, String type) {
		x = xVal;
		y = yVal;
		mapType = type;
		generateMap();
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	
	public void borderWarning() { //triggers when the player tries to go beyond the map boundaries
		switch(mapType) {
		case "default": //if it is the generic map
			System.out.println("Its too treacharous to continue on this path, maybe I should turn around...");
			break;
		case "BuildingMap": //if it is a building map
			System.out.println("Watch out for the wall");
			break;
		case "TownMap": //if it is a town map
			System.out.println("Try leaving the town through the gate");
		}
	}
	
	public void generateMap() {
		grid = new int[y][x];
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				
			}
		}
	}
	
	public void generateInitialMap() {
		grid = new int[y][x];
		
		
	}
	
	
	public void generateCluster(Tile t) {
		
	}
}
