package utility;


import java.util.ArrayList;
import java.util.Comparator;

import weka.core.DistanceFunction;
import CustomClass.Cluster;
import CustomClass.InstanceProxy;
import bin.ceka.core.Dataset;

public class OutEdgeInstances implements iPickInstances{
	private DistanceFunction m_distancefunction;

	/**
	 * @return the m_distancefunction
	 */
	public DistanceFunction getDistancefunction() {
		return m_distancefunction;
	}

	/**
	 * @param m_distancefunction the m_distancefunction to set
	 */
	public void setDistancefunction(DistanceFunction m_distancefunction) {
		this.m_distancefunction = m_distancefunction;
	}
	
	
	public ArrayList<InstanceProxy> calculateEdge(Cluster A, Cluster B, double instancesPercentage) {
		//double disToACentroid = 0;
		double disToBCentroid = 0;
		//double deltaDis = 0;
		ArrayList<InternalCentriodDistance> instances = new ArrayList<InternalCentriodDistance> ();
		
		for(InstanceProxy ins:A.getInstances())
		{
		    //disToACentroid = m_distancefunction.distance(ins, A.getCentriod());
			disToBCentroid = m_distancefunction.distance(ins, B.getCentriod());
			//deltaDis = Math.abs(disToACentroid - disToBCentroid);
			instances.add(new InternalCentriodDistance(disToBCentroid, ins));	
		}
		
		instances.sort(new InternalCentriodDistanceComp());
		long numOfreturn = Math.round(instances.size()*instancesPercentage);
		
		ArrayList<InstanceProxy> returnInstances = new ArrayList<InstanceProxy>();
		for(int count = 1; count <= numOfreturn; count++){
			returnInstances.add(instances.get(instances.size() - count).m_ins);
		}
		
		return returnInstances;
	}
	
	private class InternalCentriodDistance {
		public double m_distance;
		public InstanceProxy m_ins;
		
		public InternalCentriodDistance(double distance, InstanceProxy ins){
			this.m_distance = distance;
			this.m_ins = ins;
		}
	}
	
	private class InternalCentriodDistanceComp implements Comparator<InternalCentriodDistance> {

		@Override
		public int compare(InternalCentriodDistance o1,
				InternalCentriodDistance o2) {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			if (o1.m_distance > o2.m_distance) {
				return 1;
			}else if(o1.m_distance < o2.m_distance) {
				return -1;
			}else{
				return 0;
			}
		}
		
	}
}
