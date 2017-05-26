import java.util.ArrayList;

public class Town extends Tile { // make pregenerated Towns
	
	
	public Town() {
		super("You have entered the town", items());
	}

	
	
	/**
	 * this generates the initial list of items a given tile can have
	 * @return ArrayList that contains all of the possible items in the tile type
	 */
	public static ArrayList<ItemData> items() {
		ArrayList<ItemData> retVal = new ArrayList<ItemData>();
		retVal.add(new ItemData("Todd_from_NASA"));
		retVal.add(new ItemData("Mr.Todd"));
		retVal.add(new ItemData("Todd_from_NASA"));
		retVal.add(new ItemData("Mr.Todd"));
		retVal.add(new ItemData("Todd_from_NASA"));
		retVal.add(new ItemData("Mr.Todd"));
		return retVal;
	}
}
