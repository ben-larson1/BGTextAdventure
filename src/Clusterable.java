
public interface Clusterable {
	boolean isClustered = false;
	int clusterSize = 0;//0=not (1 tile), 1=small (2-3 tiles), 2=medium (4-5 tiles), 3=large (6 tiles)
	public void Cluster(int size);
}
