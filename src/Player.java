import java.util.ArrayList;

public class Player extends Entity{
	private String name;
	private ArrayList<ItemData> inventory = new ArrayList<ItemData>();
	private int health;
	
	public Player(String _name) {
		name = _name;
		health = 100;
	}
	
	public String getName() { return name; }
	
	public int getHealth() { return health; }
	
	public void damage(int amount) {
		health -= amount;
	}
	
	public void heal(int amount) {
		health += amount;
	}
	
	public void setHealth(int _health) {
		health = _health;
	}
	
	/**
	 * prints out the inventory of the player
	 * @return the inventory in a single String document
	 */
	public String getInventory() {
		String retVal = "";
		for(int i = 0; i < inventory.size();i++ ) {
			retVal += "\t" + inventory.get(i).getType() + "\n";
		}
		
		return retVal;
	}
	
	/**
	 * adds an item to the inventory
	 * @param item : the item added to the inventory
	 */
	public void addToInventory(ItemData item) {
		inventory.add(item);
	}
	
	/**
	 * uses the inputed item and returns the result of the use
	 * @param _item : the item that is used
	 * @return the result of the use of the item
	 */
	public String use(String _item) {//takes the item the player chooses to use and triggers ItemData.use()
		
		for(int i = 0; i < inventory.size();i++) {
			if(inventory.get(i).getType().equalsIgnoreCase(_item)) {
				return inventory.get(i).use(this);
			}
		}
		return null;
	}
	
	/**
	 * returns the data on a specific item
	 * @param _item : the item to be inspected
	 * @return String value of the data of the item
	 */
	public String inspect(String _item) {
		String retVal = "";
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).getType().equalsIgnoreCase(_item)) {
				return inventory.get(i).toString();
			}
		}
		return retVal;
	}
	
	public boolean has(String _item) {
		for(int i = 0; i < inventory.size();i++) {
			if(inventory.get(i).getType().equalsIgnoreCase(_item)) {
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
			if(inventory.get(i).getType().equalsIgnoreCase(selectedItem)) {
				inventory.remove(i);
			}
		}
	}
	
	/**
	 * attacks the enemy if there is one
	 * @param attackWith : the item you attack with
	 * @param m : the map used in the game
	 * @return array of objects that contains the map used in the game, and the string value to be outputted
	 */
	public Object[] attack(String attackWith, Map m) {
		Object[] retVal = new Object[2];
		if(has(attackWith)) {
			if(!m.getCurrentTile().doesItHaveDungeon()) {
				retVal[0] = "There are no monsters on this tile";
			} else {
				retVal[0] = "\n\tHEATH OF MONSTER:\t" + m.getCurrentTile().getDungeon().getMonster().getHealth();
			}
		}
		
		retVal[1] = m;
		return retVal;
	}
}
