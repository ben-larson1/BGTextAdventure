
public class Tile {
	private boolean needsAnotherMap = false;
	private Map tileMap;
	public boolean isLoaded = false;
	private String text;
	private ItemData[] items;
	
	public Tile() {
		
	}
	
	public Tile(String _text, ItemData[] _items) {
		text = _text;
		items = _items;
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
