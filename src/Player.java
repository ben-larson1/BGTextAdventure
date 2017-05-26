import java.util.ArrayList;

public class Player extends Entity{
	private String name;
	private ArrayList<ItemData> inventory = new ArrayList<ItemData>();
	
	public Player(String _name) {
		name = _name;
	}
	
	public String getName() { return name; }
	
	public String getInventory() {
		String retVal = "";
		for(int i = 0; i < inventory.size();i++ ) {
			retVal += "\t" + inventory.get(i).toString() + "\n";
		}
		
		return retVal;
	}
	
	public void addToInventory(ItemData item) {
		inventory.add(item);
	}
	
	public String use(String _item) {//takes the item the player chooses to use and triggers ItemData.use()
		
		for(int i = 0; i < inventory.size();i++) {
			if(inventory.get(i).toString().equalsIgnoreCase(_item)) {
				return inventory.get(i).use(this);
			}
		}
		return null;
	}
	
	public boolean has(String _item) {
		for(int i = 0; i < inventory.size();i++) {
			if(inventory.get(i).toString().equalsIgnoreCase(_item)) {
				inventory.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * removes an item from your inventory
	 * @param selectedItem : the item to be removed
	 */
	public void drop(String selectedItem) {
		for(int i = 0; i < inventory.size();i++) {
			if(inventory.get(i).toString().equalsIgnoreCase(selectedItem)) {
				inventory.remove(i);
			}
		}
	}
}
