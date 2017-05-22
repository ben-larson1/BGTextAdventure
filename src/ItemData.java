
public class ItemData {
	private String type;
	private Map m;
	
	public ItemData(String _type, Map _m) {
		type = _type;
		m = _m;
	}
	
	public ItemData(String _type) {
		type = _type;
	}
	
	public String toString() {
		String retVal = type;
		return retVal;
	}
	
	public String use() {
		String _type = type.toLowerCase();
		switch(_type) {
		case "map":
			return m.toString() + "You are at row " + m.currLoc()[0] + ", column " + m.currLoc()[1] + "\n";
		case "rock":
			return "Idiot, you can't use a rock";
		}
		
		return null;
	}
	
}
