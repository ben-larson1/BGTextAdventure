import java.util.ArrayList;

public class BossRoom extends Tile {
	private Monster boss;
	
	public BossRoom() {
		super("You have entered the BossRoom, if you wish to face the boss type \"Challenge\"", items());
		boss = new Monster(200,15);
	}
	
	public static ArrayList<ItemData> items() {
		ArrayList<ItemData> retVal = new ArrayList<ItemData>();
		retVal.add(new ItemData("VOID")); 
		return retVal;
	}
	
	public Monster getBoss() { return boss; }
	
	 
}
