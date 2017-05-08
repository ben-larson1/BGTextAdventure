
public class Swamp extends Tile implements Clusterable {
	private boolean isClustered;
	private int clusterSize;
	
	public Swamp(boolean doesItCluster, int clstrSize) {
		isClustered = doesItCluster;
		clusterSize = clstrSize;
		if (isClustered) {
			Cluster(clusterSize);
		}
	}
	
	public void Cluster(int size) {
		
	}
}
