package experiment;

import java.util.ArrayList;

import utility.Kmean;
import utility.WriteAcc;
import weka.core.Instances;
import CustomClass.Cluster;
import CustomClass.InstanceProxy;
import CustomClass.InstancesLoader;
import CustomClass.Label;
import CustomClass.LabelContainer;

public class ExperimentTwo extends IExperiment {

	@Override
	public void execute(int attempt) throws Exception {
		// TODO Auto-generated method stub
		// set this data to something from the file.
		setMloader(new InstancesLoader("/Users/Jessie/Documents/GitHub/ActiveLearning/WorkSpace/test.arff",
				"/Users/Jessie/Documents/GitHub/ActiveLearning/WorkSpace/income94.response.txt",getLOADER_FILTER()));

		Instances data = getMloader().getInstances();
		
		System.out.println(data.numInstances()+".......number of 0: " + getMloader().getNumoflabel0());
		
	
		// Create Kmeans without centroid.
		Kmean k = new Kmean();
		Cluster[] cluster  = k.buildCluster(data);
		System.out.println("cluster 0 size: "+cluster[0].getInstances().size());
		System.out.println("cluster 1 size: "+cluster[1].getInstances().size());
//		for(InstanceProxy ins: cluster[0].getInstances()){
//			System.out.println("cluster 0 : "+ins.getinstanceID());
//		}
//		
//		for(InstanceProxy ins: cluster[1].getInstances()){
//			System.out.println("cluster 1 : "+ins.getinstanceID());
//		}
		
		ArrayList<InstanceProxy> cdata = k.m_cData;
		
		
		
		WriteAcc recordAcc = new WriteAcc(MAJORITY_VOTE_COUNT+"edgeTTTTT.csv");
		
		//
		for(int x=0;x< attempt;x++) {
			LabelContainer lcontainer = getMloader().getLabelContainer();
			
			//*****************contrast experiment: only use majority, no cluster or classification*****************
			//LabelContainer lcontainer = mloader.getLabelContainer();
			for(InstanceProxy ins: cluster[0].getInstances()) {
				Label l = lcontainer.getLabel(ins.getIndex());
				ins.setLabel((int)l.calculateMajorityLabel(1,1));
				
			}
			for(InstanceProxy ins: cluster[1].getInstances()) {
				Label l = lcontainer.getLabel(ins.getIndex());
				ins.setLabel((int)l.calculateMajorityLabel(1,1));
			}

			int count0;
			int count1;
			
			System.out.println("Confusion matrix 0: " + (count0 = this.getConfusionMatrix(cluster[0])));
			System.out.println("Confusion matrix 1: " + (count1 = this.getConfusionMatrix(cluster[1])));

			double acc = (double)(count0 + count1)/(double)(getMloader().getOriginalData().numInstances());
			System.out.println("Accuracy is: " + acc);
			//write acc to a file
			recordAcc.write(Double.toString(acc)+",");
			
			System.out.println("Done");
			// Get the Centroid.
			
			cluster[0].calculateNewCentriod(k.getDistancefunction());
			cluster[1].calculateNewCentriod(k.getDistancefunction());
			
			Instances newcentriods = new Instances(data,0);
			newcentriods.add(cluster[0].getCentriod());
			newcentriods.add(cluster[1].getCentriod());
			k.setnumIteration(1000);
			k.setInitialCetroid(newcentriods);
			cluster = k.buildCluster(cdata);
			
			
		}
		recordAcc.write("\n");
		recordAcc.close();
	}

}
