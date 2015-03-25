package CustomClass;

import java.util.ArrayList;

import weka.core.DistanceFunction;
import weka.core.Instance;

public class Cluster {
	
	// This final is use to calculate if the centriod has move.
	private final double m_CentroidMoveTolerance = 0.001;
	
	private Instance m_Centroid;
	private ArrayList<InstanceProxy> m_Instances = new ArrayList<InstanceProxy>();
	//private 
	
	
	public Instance getCentriod() {
		return m_Centroid;
	}
	
	public Cluster() {
		super();
		// TODO Auto-generated constructor stub
		InitializeClusterFromInstanceAttr();
	}
	
	public void setCentriod(Instance m_Centroid) {
		this.m_Centroid = m_Centroid;
	}
	
	private void InitializeClusterFromInstanceAttr() {
		m_Instances = new ArrayList<InstanceProxy>();
	}
	
	public ArrayList<InstanceProxy> getInstances() {
		return m_Instances;
	}
	
	public void addInstance(InstanceProxy instance) {
		this.m_Instances.add(instance);
		
	}
	
	public void removeInstance(InstanceProxy ins){
		this.m_Instances.remove(ins);
	}
	
	public void removeInstance(int instanceId){
		InstanceProxy rc = null;
		for(InstanceProxy ins: this.m_Instances){
			if(ins.getinstanceID() == instanceId){
				rc = ins;
				break;
				
			}
		}
		if(rc != null){
			this.m_Instances.remove(rc);
		}
		
		
	}
	
	public void removeAllInstace() {
		// Clear all value
		this.m_Instances.clear();
	}
	

	public int isInstanceExists(int instanceId) {
		
		int index = 0;
		for(InstanceProxy ins: this.m_Instances){
			if(ins.getinstanceID() == instanceId){
				return index;
			}
			index++;
		}
		
		return -1;
	}
	
	public int isInstanceExists(InstanceProxy data) {
		
		if (m_Instances.contains(data)) {
			
			return m_Instances.indexOf(data);

		}
		
		return -1;
	}
	
	public boolean calculateNewCentriod(DistanceFunction func) {
		double sum = 0;
		
		if (m_Instances.size() <= 0) {
			System.out.println("No Instance");
			return false;
		}
		
		// We want to copy.
		Instance orig_centroid = null;
		
		if (m_Centroid != null) {
			orig_centroid = (Instance) m_Centroid.copy();
		}else{
			m_Centroid = m_Instances.get(0);
		}
		
		for(int index=0; index < m_Centroid.numAttributes();index++){
			
			if (!m_Centroid.attribute(index).isAveragable()) {
				// If we cant perform avarage.
				continue;
			}
			
			// Initialize sum
			sum = 0;
			for(InstanceProxy ins:m_Instances){
				sum += ins.value(index);		
			}
			
			if (m_Centroid.attribute(index).isNumeric()) {
				double average = sum/m_Instances.size();
				m_Centroid.setValue(index, average);
			}
					
		}
		
		// calculate if the new centriod is the same as the old one.
		if(orig_centroid != null) {
		double dist =  Math.abs(func.distance(orig_centroid, m_Centroid));
		
		if ( orig_centroid != null && dist <= this.m_CentroidMoveTolerance){
			return false;
		}
		}
		
		return true;
		
	}
}
