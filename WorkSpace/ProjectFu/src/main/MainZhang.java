package main;

import java.io.IOException;
import java.util.ArrayList;

import CustomClass.Cluster;
import CustomClass.InstanceProxy;
import CustomClass.InstancesLoader;
import CustomClass.Label;
import CustomClass.LabelContainer;
import utility.EdgeInstances;
import utility.Kmean;
import utility.RandomInstances;
import weka.core.Instances;

public class MainZhang {

	
	private double EDGE_PERCENTAGE = 0.2;
	public double getEDGE_PERCENTAGE() {
		return EDGE_PERCENTAGE;
	}

	public void setEDGE_PERCENTAGE(double eDGE_PERCENTAGE) {
		EDGE_PERCENTAGE = eDGE_PERCENTAGE;
	}
	
	
	private String LOADER_FILTER = "";
	public String getLOADER_FILTER() {
		return LOADER_FILTER;
	}

	public void setLOADER_FILTER(String lOADER_FILTER) {
		LOADER_FILTER = lOADER_FILTER;
	}

	public void execute(int attempt) throws IOException {
		// set this data to something from the file.
		//BufferedReader reader = new BufferedReader(new FileReader("/home/parallels/Desktop/WorkSpace/test.arff"));
		InstancesLoader loader = new InstancesLoader("/Users/Jessie/Desktop/WorkSpace/test.arff",
													"/Users/Jessie/Desktop/WorkSpace/income94.response.txt",LOADER_FILTER);
		Instances data = loader.getInstances();
		
		//int number_attributes = data.get(0).numAttributes();
		
		EdgeInstances edge = new EdgeInstances();
		RandomInstances randPoints = new RandomInstances();
	
		// Create Kmeans without centroid.
		Kmean k = new Kmean();
		Cluster[] m_cluster  = k.buildCluster(data);
		
		ArrayList<InstanceProxy> cdata = k.m_cData;
		
		edge.setDistancefunction(k.getDistancefunction());
		
		for(int x=0;x< attempt;x++) {
			System.out.println("---------------------Attempt: "+x+"-------------------------" );
			// Calculation the edge.
			//ArrayList<InstanceProxy> edage0 = edge.calculateEdge(m_cluster[0],m_cluster[1], EDGE_PERCENTAGE);	
			//ArrayList<InstanceProxy> edage1 = edge.calculateEdge(m_cluster[1],m_cluster[0], EDGE_PERCENTAGE);
			
			
			
			
			ArrayList<InstanceProxy> edage0 = randPoints.getRandomInstances(m_cluster[0],EDGE_PERCENTAGE);
			ArrayList<InstanceProxy> edage1 = randPoints.getRandomInstances(m_cluster[1],EDGE_PERCENTAGE);
			System.out.println("Cluster A Edges =  "+ edage0.size());
			System.out.println("Cluster B Edges = "+ edage1.size());
			
			// Get the Label.
			LabelContainer lcontainer = loader.getLabelContainer();
			//System.out.println(lcontainer.getSize());
			Cluster A = new Cluster();
			
			Cluster B = new Cluster();
			
			
			for (InstanceProxy ins: edage0) {

				Label l = lcontainer.getLabel(ins.getIndex());
				if((int) l.calculateMajorityLabel(5) == 0) {
					A.addInstance(ins);
				}else{
					B.addInstance(ins);
				}
			}

			
			for (InstanceProxy ins: edage1) {
				
				Label l = lcontainer.getLabel(ins.getIndex());
				if((int) l.calculateMajorityLabel(5) == 0) {
					A.addInstance(ins);
				}else{
					B.addInstance(ins);
				}
				
			}
			
			System.out.println("number of instances with label 0 is: " + A.getInstances().size());
			System.out.println("number of instances with label 1 is: " + B.getInstances().size());
			
			// Calculate Centriod.
			A.calculateNewCentriod(k.getDistancefunction());

			
			B.calculateNewCentriod(k.getDistancefunction());
			
			
			A.removeAllInstace();
			B.removeAllInstace();
			
			Instances centriods = new Instances(data,0);
			centriods.add(A.getCentriod());
			centriods.add(B.getCentriod());
			k.setnumIteration(1);
			k.setInitialCetroid(centriods);
			m_cluster = k.buildCluster(cdata);
			
			
			
			System.out.println("Cluster A Instances = "+ m_cluster[0].getInstances().size());
			System.out.println("Cluster B Instances = "+ m_cluster[1].getInstances().size());
			
			int count0 = 0;
			int count1 = 1;
			
			
			for(InstanceProxy ins : m_cluster[0].getInstances()){
				if(loader.getOriginalData().get(ins.getinstanceID()).value(14) == 0 ){
					count0++;
				}
			}
			for(InstanceProxy ins : m_cluster[1].getInstances()){
				if(loader.getOriginalData().get(ins.getinstanceID()).value(14) == 1 ){
					count1++;
				}
			}
			

			double acc = (double)(count0 + count1)/(double)(loader.getOriginalData().numInstances());
			System.out.println("Accuracy is: " + acc);
			
			System.out.println("Done");
			// Get the Centroid.
			
			m_cluster[0].calculateNewCentriod(k.getDistancefunction());
			m_cluster[1].calculateNewCentriod(k.getDistancefunction());
			
			Instances newcentriods = new Instances(data,0);
			newcentriods.add(m_cluster[0].getCentriod());
			newcentriods.add(m_cluster[1].getCentriod());
			k.setnumIteration(1000);
			k.setInitialCetroid(newcentriods);
			m_cluster = k.buildCluster(cdata);
			
		}

	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainZhang mainC = new MainZhang();
		try {
			mainC.LOADER_FILTER = "4,8,9,14,15";
			//attribute count starts from 1
			mainC.execute(4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
