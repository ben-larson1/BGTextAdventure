import java.util.ArrayList;
import java.util.Random;

public class Mountain extends Tile implements Clusterable{
	private boolean isClustered;
	private int clusterSize;
	
	public Mountain(int clstrSize) {
		super("You are in a rocky mountain", items(), doesGenDungeon());
		clusterSize = clstrSize;
	}
	
	public Mountain() {
		super("You are in a rocky mountain", items(), doesGenDungeon());
	}
	
	public static boolean doesGenDungeon() {
		Random r = new Random();
		return r.nextBoolean();
	}

	public static ArrayList<ItemData> items() {
		ArrayList<ItemData> retVal = new ArrayList<ItemData>();
		retVal.add(new ItemData("rock", 1));
		retVal.add(new ItemData("rock", 1));
		retVal.add(new ItemData("rock", 1));
		retVal.add(new ItemData("rock", 1)); 
		retVal.add(new ItemData("rock", 1));
		retVal.add(new ItemData("rock", 1));
		retVal.add(new ItemData("rock", 1));
		retVal.add(new ItemData("rock", 1));
		retVal.add(new ItemData("bird"));
		retVal.add(new ItemData("club", 2));
		retVal.add(new ItemData("sword", 3));
		retVal.add(new ItemData("key"));
		retVal.add(new ItemData("treasure"));
		return retVal;
	}

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
					grid[_y][_x] = new Mountain();
				}
				size--;
			}
		}
		return grid;
	}
	
	
	
}
