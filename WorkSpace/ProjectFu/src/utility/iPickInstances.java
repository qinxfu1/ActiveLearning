package utility;

import java.util.ArrayList;

import weka.core.DistanceFunction;
import CustomClass.Cluster;
import CustomClass.InstanceProxy;

public interface iPickInstances {

	/**
	 * @return the m_distancefunction
	 */
	public abstract DistanceFunction getDistancefunction();

	/**
	 * @param m_distancefunction the m_distancefunction to set
	 */
	public abstract void setDistancefunction(DistanceFunction m_distancefunction);

	public abstract ArrayList<InstanceProxy> calculateEdge(Cluster A,
			Cluster B, double instancesPercentage);

}