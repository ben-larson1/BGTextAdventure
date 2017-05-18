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
	
	public static ArrayList<ItemData> items() {
		return null;
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
					grid[_y][_x] = new Field();
				}
				size--;
			}
		}
		return grid;
	}
	
	
	
	
	
}
