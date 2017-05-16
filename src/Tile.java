
public class Tile {
	private boolean needsAnotherMap = false;
	private Map tileMap;
	public boolean isLoaded = false;
	private String text;
	
	public Tile() {
		
	}
	
	public Tile(String _text) {
		text = _text;
	}
	
	public Tile(Map m) {
		tileMap = m;
	}
	
	public String toString() {
		String retVal = this.getClass().toString().substring(6);
		return retVal;
	}
	
	public String getText() { return text; }
	
	
	
}
