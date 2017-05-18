import java.util.ArrayList;
import java.util.Random;

public class Tile {
	private boolean needsAnotherMap = false;
	private Map tileMap;
	public boolean isLoaded = false;
	private String text;
	private ArrayList<ItemData> items;
	
	public Tile() {
		
	}
	
	public Tile(String _text, ArrayList<ItemData> _items) {
		text = _text;
		generateItemList(_items);
		
	}
	
	public Tile(Map m) {
		tileMap = m;
	}
	
	public void generateItemList(ArrayList<ItemData> _items) {
		Random r = new Random();
		int itemAmt = (int)(r.nextInt(_items.size()));
		items = new ArrayList<ItemData>(); 
		for(int i = 0; i < itemAmt; i++) {
			items.add(_items.remove(r.nextInt(_items.size())));
		}
	}
	
	
	
	public String toString() {
		String retVal = this.getClass().toString().substring(6);
		return retVal;
	}
	
	public String getText() { return text; }
	
	
	
}
