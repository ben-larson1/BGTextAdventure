import java.util.Random;

public class ItemData {
	private String type;
	private Map m;
	private int attackVal;
	
	public ItemData(String _type, Map _m) {
		type = _type;
		m = _m;
		attackVal = 0;
	}
	
	public ItemData(String _type) {
		type = _type;
		attackVal = 0;
	}
	
	public ItemData(String _type, int _attackVal) {
		type = _type;
		attackVal = _attackVal;
	}
	
	public String toString() {
		String retVal = type;
		if(attackVal != 0) {
			if(type.length() >= 8)
			{
				retVal += "\t\tAttack Value: " + attackVal;
			} else {
				retVal += "\t\t\tAttack Value: " + attackVal;
			}
		} 
		return retVal;
	}
	
	/**
	 * triggers the item selected
	 * @return the value that will be printed
	 */
	public String use(Player p) {
		String _type = type.toLowerCase();
		switch(_type) {
		case "map":
			return m.toString() + "You are at row " + m.currLoc()[0] + ", column " + m.currLoc()[1] + "\n";
		case "key":
			if(p.has("treasure")) {
				p.drop("key");
				p.drop("treasure");
				p.addToInventory(loot());
				return "You opened the treasure chest";
			}
		case "rock":
		default:
			return "Idiot, you can't use that";
		}
		
	}
	
	public String getType() { return type; }
	
	public int getAttack() { return attackVal; }
	
	public ItemData loot() {
		Random r = new Random();
		switch(r.nextInt(10)) {
		case 0:
		case 1:
			return new ItemData("GoldenSword", 13);
		case 2:
			return new ItemData("BattleAxe",20);
		default:
			return new ItemData("ShinyRock", 10000);
		}
	}
	
	
}
