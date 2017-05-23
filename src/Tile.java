import java.util.ArrayList;
import java.util.Random;

public class Tile {
	private boolean needsAnotherMap = false;
	private Map tileMap;
	public boolean isLoaded = false;
	private String text;
	private ArrayList<ItemData> items;
	private boolean hasDungeon;
	
	public Tile() {
		
	}
	
	public Tile(String _text, ArrayList<ItemData> _items) {
		text = _text;
		generateItemList(_items);
		
	}
	
	public Tile(String _text, ArrayList<ItemData> _items, boolean genDungeon) {
		text = _text;
		generateItemList(_items);
		if(genDungeon) {
			text += "\n\tWatch out for the Dungeon!";
		}
		hasDungeon = genDungeon;
		
	}
	
	public Tile(Map m) {
		tileMap = m;
	}
	
	public boolean doesItHaveDungeon() { return hasDungeon; }
	
	public void generateItemList(ArrayList<ItemData> _items) {
		Random r = new Random();
		int itemAmt = r.nextInt(_items.size());
		items = new ArrayList<ItemData>(); 
		for(int i = 0; i < itemAmt; i++) {
			items.add(_items.remove(r.nextInt(_items.size())));
		}
	}
	
	public String printItemList() {
		String retVal = "\n";
		for(ItemData item : items) {
			retVal += item.toString() + "\n";
		}
		return retVal;
	}
	
	public Object[] tryPickup(Player p, String checkItem) {
		Object[] retVal = {p, "That item does not exist in this tile"};
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).toString().equals(checkItem)) {
				((Player)retVal[0]).addToInventory(items.remove(i));
				retVal[1] = "Congrats, you got a " + checkItem;
			}
		}
		return retVal;
	}
	
	public String toString() {
		String retVal = this.getClass().toString().substring(6);
		return retVal;
	}
	
	public String getText() { return text; }
	
	
	
}
