import java.util.Random;

public class Map {
	private int x, y;
	private int currX,currY;
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
				grid[i][j] = null;
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
				grid[i][j] = null;
			}
		}
		for(int i = 0; i < y; i++) { //i represents y
			for(int j = 0; j < x; j++) {//j represents x
				Random r = new Random();
				int sendVal = 3;
				if(r.nextInt(20) == 1) {
					sendVal = 2;
				}
				generateTile(i, j, sendVal);
				
			}
		}
		
	}
	
	
	/**
	 * loc should be 1 if tile is inside, 2 if its in a town, 3 if its outside
	 * @param y
	 * @param x
	 * @param loc
	 */
	public void generateTile(int _y, int _x, int loc) {
		Random r = new Random();
		if(loc == 1) { //if the Tile is deemed to be inside
			grid[_y][_x] = new Room();
		} else if(loc == 2) { //if the Tile is deemed to be in a town
			grid[_y][_x] = new Town();
		} else if(loc == 3) {
			int selection = r.nextInt(4); //0 = Field, 1 = Forest, 2 = Mountain, 3 = Swamp
			if(selection == 0) {
				boolean checkForCluster = r.nextBoolean();
				int size = r.nextInt(4);
				grid[_y][_x] = new Field(size);
				if(checkForCluster){
					generateCluster(selection, _x, _y);
				}
			} else if(selection == 1) {
				boolean checkForCluster = r.nextBoolean();
				int size = r.nextInt(4);
				grid[_y][_x] = new Forest(size);
				if(checkForCluster) {
					generateCluster(selection, _x, _y);
				}
			} else if(selection == 2) {
				boolean checkForCluster = r.nextBoolean();
				int size = r.nextInt(4);
				grid[_y][_x] = new Mountain(size);
				if(checkForCluster) {
					generateCluster(selection, _x, _y);
				}
			} else if(selection == 3) {
				boolean checkForCluster = r.nextBoolean();
				int size = r.nextInt(4);
				grid[_y][_x] = new Swamp(size);
				if(checkForCluster) {
					generateCluster(selection, _x, _y);
				}
			} 
		}
	}
	
	/** This method generates a cluster of Tiles that are Clusterable
	 * 		 type: input a number that is the SubClass of Tile (0 = Field, 1 = Forest, 2 = Mountain, 3 = Swamp)
	 */
	public void generateCluster(int type, int _x, int _y) {
		if(type == 0) {
			grid = ((Field)grid[_y][_x]).Cluster(grid, _x, _y);
		} else if(type == 1) {
			grid = ((Forest)grid[_y][_x]).Cluster(grid, _x, _y);
		} else if(type == 2) {
			grid = ((Mountain)grid[_y][_x]).Cluster(grid, _x, _y);
		} else if(type == 3) {
			grid = ((Swamp)grid[_y][_x]).Cluster(grid, _x, _y);
		}
	}
	
	public String toString() {

		String retVal = "";
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				retVal += "\t";
				if(grid[i][j] == null) {
					retVal += "null\t";
				} else {
					
					retVal += grid[i][j].toString();
					if(grid[i][j].toString().length() < 8) {
						retVal += "\t";
					}
				}
			
			}
			retVal += "\n\n";
		}
		return retVal;
	}

	public void moveTo(String dir) {
		if(dir.equalsIgnoreCase("n") || dir.equalsIgnoreCase("north")){
			if(currX <= 0) {
				borderWarning();
				return;
			}
			currX--;
		} else if (dir.equals("s") || dir.equalsIgnoreCase("south")) {
			
		}
	}
	
	public Tile currLoc() {
		return null;
	}
}
