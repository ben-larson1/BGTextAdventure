
public class Mountain extends Tile implements Clusterable{
	private boolean isClustered;
	private int clusterSize;
	
	public Mountain(boolean doesItCluster, int clstrSize) {
		isClustered = doesItCluster;
		clusterSize = clstrSize;
		if (isClustered) {
			Cluster(clusterSize);
		}
	}
	
	public Mountain() {
		// TODO Auto-generated constructor stub
	}

	public void Cluster(int size) {
		
	}
}
