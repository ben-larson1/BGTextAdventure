import java.util.ArrayList;
import java.util.Random;

public class ItemData {
	private String type;
	private Map m;
	private int attackVal;
	private ArrayList<String> tradeList = new ArrayList<String>();
	private ArrayList<ItemData> trades = new ArrayList<ItemData>();
	private int tradeVal;
	
	public ItemData(String _type, Map _m) {
		type = _type;
		m = _m;
		attackVal = 0;
	}
	
	public ItemData(String _type) {
		type = _type;
		attackVal = 0;
		if(_type.equalsIgnoreCase("mr.todd")||_type.equalsIgnoreCase("todd_from_nasa")) {
			generateTradeList();
		}
	}
	
	public ItemData(String _type, int _attackVal) {
		type = _type;
		attackVal = _attackVal;
	}
	
	public ItemData(String _type, int _attackVal, int _tradeVal) {
		type = _type;
		attackVal = _attackVal;
		tradeVal = _tradeVal;
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
	
	public void generateTradeList() {
		Random r = new Random();
		int size = r.nextInt(5) + 1;
		for(int i = 0; i < size; i++) {
			ItemData I = loot();
			boolean temp = true;
			while(temp && i > 0) {
				temp = false;
				for(int j = 0; j< trades.size(); j++) {
					if(I.getType().equalsIgnoreCase(trades.get(j).getType())) {
						temp = false;
						I = loot();
						break;
					}
				}
				
			}
			int value = r.nextInt(100) + 100;
			trades.add(new ItemData(I.getType(), I.getAttack(), value));
			tradeList.add("\t" + I.getType() + " for " + value + "gold");
			
		}
	}
	
	public String getType() { return type; }
	
	public int getAttack() { return attackVal; }
	
	public int getCost() { return tradeVal; }
	
	public ItemData loot() {
		Random r = new Random();
		switch(r.nextInt(10)) {
		case 0:
		case 1:
			return new ItemData("GoldenSword", 13);
		case 2:
			return new ItemData("BattleAxe",20);
		case 3:
			return new ItemData("NerfGun", 2);
		case 4:
			return new ItemData("Sickle", 15);
		case 5:
			return new ItemData("Trident", 22);
		case 6:
			return new ItemData("Scythe", 11);
		case 7:
			return new ItemData("BadMusic", 25);
		case 8:
			return new ItemData("CareBearStare", -10);
		case 9:
			return new ItemData("DiamondSword", 17);
		default:
			return new ItemData("ShinyRock", 10);
		}
	}
	
	
	
	public String getTrades() {
		if(tradeList.size() != 0) {
			String retVal = "TRADES\n";
			for(String trade : tradeList) {
				retVal += "\t" + trade + "\n";
			}
			return retVal;
		}
		return "There are no trades";
	}
	
	public boolean trade(Player p, int buyThis) {
		if(trades.get(buyThis).getCost() < p.getCashMoney()) {
			p.charge(trades.get(buyThis).getCost());
			p.addToInventory(trades.remove(buyThis));
			return true;
		}
		return false;
	}
	
	
}
