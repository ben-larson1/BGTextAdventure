import java.util.ArrayList;
import java.util.Random;

public class Player extends Entity{
	private String name;
	private ArrayList<ItemData> inventory = new ArrayList<ItemData>();
	private int health;
	private int cashMoney;
	
	public Player(String _name) {
		name = _name;
		health = 100;
		cashMoney = 0;
	}
	
	public String getName() { return name; }
	
	public int getHealth() { return health; }
	
	public int getCashMoney() { return cashMoney; }
	
	public void charge(int amount) {
		cashMoney -= amount;
	}
	
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
	
	/**
	 * checks to see if the player has a specific ItemData
	 * @param _item : the specific item you are looking for
	 * @return returns true if the player has it, and false if the player does not
	 */
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
		int attackVal = 0;;
		if(has(attackWith)) {
			if(!m.getCurrentTile().doesItHaveDungeon()) {
				retVal[0] = "There are no monsters on this tile";
			} else {
				for(int i = 0; i < inventory.size(); i++) {
					if(inventory.get(i).getType().equalsIgnoreCase(attackWith)) {
						attackVal = inventory.get(i).getAttack();
						break;
					}
				}
				if(attackWith.equalsIgnoreCase("ShinyRock")) {
					drop("ShinyRock");
				}
				m.getCurrentTile().getDungeon().damageMonster(attackVal);
				retVal[0] = "\n\tHEALTH OF MONSTER:\t" + m.getCurrentTile().getDungeon().getMonster().getHealth() + "\n";
				if(m.getCurrentTile().getDungeon().getMonster().getHealth() <= 0){
					retVal[0] = "\nThe monster is dead, CONGRAGULATIONS!!!!!!!\nHere is your prize\n";
					cashMoney += loot(); 
				} else {
					damage(m.getCurrentTile().getDungeon().getMonster().attackBack());
					if(health <= 0) {
						game.pl("You ded, RIP");
					} else {
						game.pl("Health = " + health);
					}
				}
			}
		}
		
		retVal[1] = m;
		return retVal;
	}
	
	/**
	 * generates the random loot when you kill the monster
	 * @return the ItemData that is added to the inventory
	 */
	public int loot() {
		Random r = new Random();
		int retVal = r.nextInt(50) + 75;
		return retVal;
	}
}
