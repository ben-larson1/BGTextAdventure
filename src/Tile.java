
public class Tile {
	boolean needsAnotherMap = false;
	Map tileMap;
	boolean isLoaded = false;
	public Tile() {
		
	}
	
	public Tile(Map m) {
		tileMap = m;
	}
	
	public String toString() {
		String retVal = this.getClass().toString();
		return retVal.substring(6);
	}
	
	
}
