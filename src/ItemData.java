
public class ItemData {
	private String type;
	public ItemData(String _type, String data) {
		type = _type;
	}
	
	public String toString() {
		String retVal = type;
		return retVal;
	}
	
	public String use() {
		return null;
	}
}
