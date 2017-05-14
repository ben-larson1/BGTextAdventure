
public class ItemData {
	private String type;
	private Map m;
	public ItemData(String _type, Map _m) {
		type = _type;
		m = _m;
	}
	
	public String toString() {
		String retVal = type;
		return retVal;
	}
	
	public String use() {
		if(type.equalsIgnoreCase("Map")) {
			return m.toString();
		}
		return null;
	}
	
	public String getType() {
		return type;
	}
}
