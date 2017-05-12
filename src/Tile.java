
public class Tile {
	private boolean needsAnotherMap = false;
	private Map tileMap;
	public boolean isLoaded = false;
	
	
	public Tile() {
		
	}
	
	public Tile(Map m) {
		tileMap = m;
	}
	
	public String toString() {
		String retVal = this.getClass().toString().substring(6);
		return retVal;
	}
	
	
	
}
