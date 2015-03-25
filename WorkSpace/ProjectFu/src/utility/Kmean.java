package utility;

import java.util.ArrayList;

import weka.clusterers.SimpleKMeans;
import weka.core.DistanceFunction;
import weka.core.EuclideanDistance;
import weka.core.Instance;
import weka.core.Instances;
import CustomClass.Cluster;
import CustomClass.InstanceProxy;

/*
 * This K mean class is design only for creating 2 clusters.
 */
public class Kmean {
	
	private int m_numIteration = 1000; 
	private int m_numOfCluster = 2;
	private Cluster[] m_cluster;
	private Instances m_initialCetroid;
	private DistanceFunction m_distancefunction;
	public ArrayList<InstanceProxy> m_cData = new ArrayList<InstanceProxy>();
	
	public Kmean(){
		
		this.m_distancefunction = new  EuclideanDistance();
	
	}
	
	public Cluster[] buildCluster(Instances data) {
		m_cluster = null;
		// if we didnt set the initial centroid.
		// Then we are going to use the weka simpleKeyMeans to perform the operation.
		try {
			WekaSimpleKeyMeans(data);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return m_cluster;
	}
	
	public Cluster[] buildCluster(ArrayList<InstanceProxy> data) {
		m_cluster = null;
		
		CustomKmeanCluster(data);
		
		return m_cluster;
	}
	
	public int getnumIteration() {
		return m_numIteration;
	}
	
	public void setnumIteration(int m_numIteration) {
		this.m_numIteration = m_numIteration;
	}
	
	public int getnumOfCluster() {
		return m_numOfCluster;
	}
	
	public void setnumOfCluster(int m_numOfCluster) {
		this.m_numOfCluster = m_numOfCluster;
	}
	
	public DistanceFunction getDistancefunction() {
		return m_distancefunction;
	}
	
	public void setDistancefunction(DistanceFunction m_distancefunction) {
		this.m_distancefunction = m_distancefunction;
	}
	
	
	private void WekaSimpleKeyMeans(Instances data) throws Exception {
		
		SimpleKMeans simpleKMeans = new SimpleKMeans();
		
		// Initialize the Kmeans Instance
		simpleKMeans.setDistanceFunction(m_distancefunction);
		simpleKMeans.setNumClusters(m_numOfCluster);
		simpleKMeans.setMaxIterations(m_numIteration);
		
		// Need to preserve order of instances.
		simpleKMeans.setPreserveInstancesOrder(true);
		
		// Now execute the clustering.
		simpleKMeans.buildClusterer(data);
		
		Instances simpleKMeansCenteriod =  simpleKMeans.getClusterCentroids();
		
		// Set the Centered for each cluster.
		m_cluster = new Cluster[simpleKMeansCenteriod.size()];
		int index = 0;
		for(Instance centroid: simpleKMeansCenteriod) {
			// Initialize the Centered
			m_cluster[index] = new Cluster();
			m_cluster[index].setCentriod(centroid);				
			index++;
		}
		
		// Add the instance into the cluster.
		index = 0;
		for(int assignment:simpleKMeans.getAssignments()) {
			//m_cluster[assignment].addInstance(data.get(index));
			InstanceProxy cins = new InstanceProxy(data.get(index), index);
			m_cluster[assignment].addInstance(cins);
			m_cData.add(cins);
			index ++;
		}
		
	}
	
	
	public Instances getInitialCetroid() {
		return m_initialCetroid;
	}

	public void setInitialCetroid(Instances m_initialCetroid) {
		this.m_initialCetroid = m_initialCetroid;
	}

	private void CustomKmeanCluster(ArrayList<InstanceProxy> data) {
		
		// Create a cluster
		Cluster[] tempCluster = new Cluster[m_numOfCluster];
		
		// Initialize the Centered of each Cluster.
		int index = 0;
		for(@SuppressWarnings("unused") Cluster centroid: tempCluster) {
			tempCluster[index] = new Cluster();
			tempCluster[index].setCentriod(m_initialCetroid.get(index));
			index++;
		}
		
		boolean centroid_move;
		// Perform K-Mean Iteration
		int iteration = 0;
		for(iteration = 0; iteration < this.getnumIteration();iteration++) {
			
			
			// Add each instance to a cluster.
			for (InstanceProxy instance: data) {
				Cluster ClusterAssignment = CheckClosestCentriod(tempCluster, instance);
				ClusterAssignment.addInstance(instance);
			}
			
			// Calculation for the new centroid.
			centroid_move = false; // <-- This needs to be false because we.
			
			// Determined if the we would like to remove all the instance and begin
			// on the next iteration.
			System.out.print("Begin : ");
			for(Cluster c:tempCluster) {

				if (iteration != this.getnumIteration()-1) {
					boolean c_move = c.calculateNewCentriod(m_distancefunction);
					System.out.print("c_move = " + c_move + " ");
					// This will set the value to false if even one centriod moved.
					centroid_move = centroid_move || c_move;	
				}
			}
			
			for(Cluster c:tempCluster) {
				if (iteration != this.getnumIteration()-1&&centroid_move) {
						c.removeAllInstace();
					}
			}
			
			// If the centroid has'nt move.
			// then break the loop.
			if(!centroid_move) {
				break;
			}
			
		}
		
		iteration++; // add one for readability.
		System.out.println("\nKmean Iteration = " + iteration);
		this.m_cluster = tempCluster;
		
	}

	
	private Cluster CheckClosestCentriod(Cluster[] tempCluster, InstanceProxy data) {

		double previous = 0;
		
		// lets initialize the first cluster as the closest.
		Cluster Closest = tempCluster[0];
		
		// lets initialize the previous.
		previous = Math.abs(m_distancefunction.distance(Closest.getCentriod(), data));
		
		for(Cluster ct:tempCluster) {
			
			double current = Math.abs(m_distancefunction.distance(ct.getCentriod(), data));
			
			if (current < previous ) {
				Closest = ct;
				previous = current;
			}
			
		}
		
		return Closest;
	}
	
}
