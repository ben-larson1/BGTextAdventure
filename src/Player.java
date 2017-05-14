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
			if(inventory.get(i).getType().equalsIgnoreCase(_item)) {
				return inventory.get(i).use();
			}
		}
		return null;
	}
}
