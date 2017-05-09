import java.util.Random;

public class Map {
	private int x, y;
	private Tile[][] grid;
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
		grid = new Tile[y][x];
		for(int i = 0; i < y; i++) { //i represents y
			for(int j = 0; j < x; j++) {//j represents x
				grid[y][x] = null;
			}
		}
		for(int i = 0; i < y; i++) { //i represents y
			for(int j = 0; j < x; j++) {//j represents x
				
			}
		}
	}
	
	
	public void generateInitialMap() {
		grid = new Tile[y][x];
		for(int i = 0; i < y; i++) { //i represents y
			for(int j = 0; j < x; j++) {//j represents x
				grid[y][x] = null;
			}
		}
		for(int i = 0; i < y; i++) { //i represents y
			for(int j = 0; j < x; j++) {//j represents x
				generateTile(y, x, 3);
				
			}
		}
		
	}
	
	/**
	 * loc should be 1 if tile is inside, 2 if its in a town, 3 if its outside
	 * @param y
	 * @param x
	 * @param loc
	 */
	public void generateTile(int y, int x, int loc) {
		Random r = new Random();
		if(loc == 1) { //if the Tile is deemed to be inside
			grid[y][x] = new Room();
		} else if(loc == 2) { //if the Tile is deemed to be in a town
			//----------------------------------TO-BE-IMPLEMENTED----------------------------
		} else if(loc == 3) {
			int selection = r.nextInt(5);
			if(selection == 0) {
				boolean checkForCluster = r.nextBoolean();
				int size = r.nextInt(4);
				grid[y][x] = new Field(checkForCluster,size);
				if(checkForCluster){
					
				}
			}
		}
	}
	
	public void generateCluster(Tile t) {
		
	}
}
