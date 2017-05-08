
public class Field extends Tile implements Clusterable{
	private boolean isClustered;
	private int clusterSize;
	
	public Field(boolean doesItCluster, int clstrSize) {
		isClustered = doesItCluster;
		clusterSize = clstrSize;
		if (isClustered) {
			Cluster(clusterSize);
		}
	}
	
	public void Cluster(int size) {
		
	}
}
