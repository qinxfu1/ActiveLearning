package utility;

import java.util.ArrayList;
import java.util.Random;

import weka.core.DistanceFunction;
import CustomClass.Cluster;
import CustomClass.InstanceProxy;

public class RandomInstances implements iPickInstances{
	
	public ArrayList<InstanceProxy> getRandomInstances(Cluster A, double instancesPercentage){
		int num_instances = (int)(A.getInstances().size()*instancesPercentage);
		ArrayList<InstanceProxy> returnInstances = new ArrayList<InstanceProxy>();
		ArrayList<Integer> randNum = new ArrayList<Integer>();
		Random rand = new Random(); 
		
		while(randNum.size() <= num_instances){
			Integer temp = rand.nextInt(A.getInstances().size());
			if(!randNum.contains(temp)){
				randNum.add(temp);
			}
		}
		
		for(Integer i : randNum){
			returnInstances.add(A.getInstances().get(i));
		}
		return returnInstances;
	}

	@Override
	public DistanceFunction getDistancefunction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDistancefunction(DistanceFunction m_distancefunction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<InstanceProxy> calculateEdge(Cluster A, Cluster B,
			double instancesPercentage) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
