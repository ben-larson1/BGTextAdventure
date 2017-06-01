import java.util.ArrayList;

public class BossRoom extends Tile {
	private Monster boss;
	private Dungeon dungeon;
	
	public BossRoom() {
		super("You have entered the BossRoom, if you wish to face the boss type \"Challenge\"", items(), true);
		boss = new Monster(200,15);
		dungeon = new Dungeon(4);
	}
	
	public static ArrayList<ItemData> items() {
		ArrayList<ItemData> retVal = new ArrayList<ItemData>();
		retVal.add(new ItemData("VOID")); 
		return retVal;
	}
	
	public Monster getBoss() { return boss; }
	
	 
}
