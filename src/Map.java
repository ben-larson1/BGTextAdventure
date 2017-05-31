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
		game.p("Its too treacharous to continue on this path, maybe I should turn around...");
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
	
	
	/**
	 * generates the map at the beginning of the game
	 */
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
		grid[y - 1][x - 1] = new BossRoom();
		
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
			//ignore this, it does nothing currently
			grid[_y][_x] = null;
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

		String retVal = "\n";
		
		for(int j = 1; j <= grid[0].length; j++) {
			retVal += "\t" + j + "\t";
		}
		
		retVal += "\n\n";
		
		for(int i = 0; i < grid.length; i++) {
			retVal += "" + (i + 1);
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

	/**
	 * moves you in a certain direction
	 * @param dir
	 * @return
	 */
	public boolean moveTo(String dir) {
		int[] currLoc = {currY, currX};
		if(dir.equalsIgnoreCase("n") || dir.equalsIgnoreCase("north")){
			if(currY <= 0) {
				borderWarning();
				return false;
			}
			currY--;
		} else if (dir.equalsIgnoreCase("s") || dir.equalsIgnoreCase("south")) {
			if(currY >= grid.length - 1) {
				borderWarning();
				return false;
			}
			currY++;
		} else if (dir.equalsIgnoreCase("e")||dir.equalsIgnoreCase("East")) {
			if(currX >= grid[0].length - 1) {
				borderWarning();
				return false;
			}
			currX++;
		} else if (dir.equalsIgnoreCase("w")||dir.equalsIgnoreCase("west")) {
			if(currX <= 0) {
				borderWarning();
				return false;
			}
			currX--;
		} else if(dir.equalsIgnoreCase("ne") || dir.equalsIgnoreCase("northeast")){
			if(currY <= 0 || currX >= grid[0].length - 1) {
				borderWarning();
				return false;
			}
			currY--;
			currX++;
		} else if (dir.equalsIgnoreCase("se") || dir.equalsIgnoreCase("southeast")) {
			if(currY >= grid.length - 1 || currX >= grid[0].length - 1) {
				borderWarning();
				return false;
			}
			currY++;
			currX++;
		} else if (dir.equalsIgnoreCase("nw")||dir.equalsIgnoreCase("northwest")) {
			if(currY <= 0 || currX <= 0) {
				borderWarning();
				return false;
			}
			currX--;
			currY--;
		} else if (dir.equalsIgnoreCase("sw")||dir.equalsIgnoreCase("southwest")) {
			if(currX <= 0 || currY >= grid.length - 1) {
				borderWarning();
				return false;
			}
			currX--;
			currY++;
		} else {
			game.pl("invalid direction");
		}
		
		if(currX == grid[0].length - 1 && currY == grid.length - 1 && !checkAllDungeonsCleared()) {
			currX = currLoc[1];
			currY = currLoc[0];
			game.pl("You have to clear all of the dungeons to take on the boss");
		}
		game.pl(checkAllDungeonsCleared());
		return true;
	}
	
	/**
	 * checks to see if all dungeons on the map are cleared
	 * @return boolean
	 */
	public boolean checkAllDungeonsCleared() {
		boolean retVal = true;
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].doesItHaveDungeon() && !grid[i][j].getDungeon().isCleared()) {
					retVal = false;
				}
			}
		}
		return retVal;
	}

	public Tile getCurrentTile() { return grid[currY][currX]; }
	
	public int[] currLoc() {
		int[] retVal = {currY + 1, currX + 1};
		return retVal;
	}
}
