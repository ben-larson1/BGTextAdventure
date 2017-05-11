
public interface Clusterable {
//	boolean isClustered = false;
	int clusterSize = 0;//# of extra tiles
	public Tile[][] Cluster(Tile[][] grid, int x, int y);
//	public boolean checkForSpace(Tile[][] grid, int x, int y);
}
