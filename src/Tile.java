import java.util.ArrayList;
import java.util.Random;

public class Tile {
	private boolean needsAnotherMap = false;
	private Map tileMap;
	public boolean isLoaded = false;
	private String text;
	private ArrayList<ItemData> items;
	private boolean hasDungeon;
	private Dungeon dungeon;
	
	public Tile() {
		
	}
	
	public Tile(String _text, ArrayList<ItemData> _items) {
		text = _text;
		generateItemList(_items);
		
	}
	
	public Tile(String _text, ArrayList<ItemData> _items, boolean genDungeon) {
		text = _text;
		generateItemList(_items);
		Random r = new Random();
		if(genDungeon) {
			text += "\n\tWatch out for the Dungeon!\n";
			dungeon = new Dungeon(r.nextInt(3) + 1);
			
		}
		hasDungeon = genDungeon;
		
		
	}
	
	public Tile(Map m) {
		tileMap = m;
	}
	
	public boolean doesItHaveDungeon() { return hasDungeon; }
	
	public Dungeon getDungeon() { return dungeon; }
	
	public void attackMonster(int damage) { dungeon.damageMonster(damage);}
	
	public void generateItemList(ArrayList<ItemData> _items) {
		Random r = new Random();
		int itemAmt = r.nextInt(_items.size());
		items = new ArrayList<ItemData>(); 
		for(int i = 0; i < itemAmt; i++) {
			items.add(_items.remove(r.nextInt(_items.size())));
		}
	}
	
	/**
	 * 
	 * @return returns a String value containing all of the Tile's items
	 */
	public String printItemList() {
		String retVal = "\n";
		for(ItemData item : items) {
			retVal += item.getType() + "\n";
		}
		return retVal;
	}
	
	/**
	 * attempts to move an item from the Tile to the player's inventory
	 * @param p : the player object
	 * @param checkItem : the item that is attempted to be moved
	 * @return returns and Object array containing the a String value and a player object
	 */
	public Object[] tryPickup(Player p, String checkItem) {
		Object[] retVal = {p, "That item does not exist in this tile"};
		if(checkItem.equalsIgnoreCase("mr.todd") || checkItem.equalsIgnoreCase("Todd_from_NASA")) {
			retVal[1] = "He is a person, not your property. Treat him with some decency";
			return retVal;
		}
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getType().equalsIgnoreCase(checkItem)) {
				((Player)retVal[0]).addToInventory(items.remove(i));
				retVal[1] = "Congrats, you got a " + checkItem;
			}
		}
		return retVal;
	}
	
	public String toString() { return this.getClass().toString().substring(6); }
	
	public String getText() { return text; }
	
	public ItemData accessItems(int nth) {
		return items.get(nth);
	}
	
}
