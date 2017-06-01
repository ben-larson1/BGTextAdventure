import java.util.ArrayList;
import java.util.Random;

public class Field extends Tile implements Clusterable {
	private int clusterSize;
	
	public Field( int clstrSize) {
		super("You are in an open field", items());
		clusterSize = clstrSize;
		
	}
	
	public Field() {
		super("You are in an open field", items());
	}
	
	/**
	 * this generates the initial list of items a given tile can have
	 * @return ArrayList that contains all of the possible items in the tile type
	 */
	public static ArrayList<ItemData> items() {
		ArrayList<ItemData> retVal = new ArrayList<ItemData>();
		retVal.add(new ItemData("rock", 1));
		retVal.add(new ItemData("rock", 1));
		retVal.add(new ItemData("bird"));
		retVal.add(new ItemData("mouse"));
		retVal.add(new ItemData("snake"));
		retVal.add(new ItemData("shovel"));
		retVal.add(new ItemData("key"));
		return retVal;
	}

	/**
	 * This generates a cluster of Field, centered around the given coordinates
	 * @param x : the x coordinate
	 * @param y : the y coordinate
	 * @param grid : the map that is being clustered on
	 */
	public Tile[][] Cluster(Tile[][] grid, int x, int y) {
		int size = clusterSize;
		int _x = x, _y = y;
		while(size > 0) {
			_x = x; _y = y;
			Random r = new Random();
			int rDir = r.nextInt(8);
			if(rDir == 0) {
				_x++;
			} else if(rDir == 1) {
				_x--;
			} else if(rDir == 2) {
				_y++;
			} else if(rDir == 3) {
				_y--;
			}
			if (_x >= 0 && _y >= 0 && _x < grid[0].length && _y < grid.length) {
				if(grid[_y][_x] == null) {
					grid[_y][_x] = new Field();
				}
				size--;
			}
		}
		return grid;
	}
	
	
	
	
	
}
