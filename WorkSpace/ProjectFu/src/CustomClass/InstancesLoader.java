package CustomClass;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class InstancesLoader {
	
	private ArrayList<Label> m_Labels = new ArrayList<Label>();
	private Instances m_data;
	private Instances m_OriginalData;
	
	public InstancesLoader(String datafilePath, String LabelFilePath, String filter){
		
		// read the dataFile.
		try {
			BufferedReader reader = new BufferedReader(new FileReader(datafilePath));
			this.m_data = new Instances(reader);
			
			this.m_data = this.createCustomInstancefromInstances(this.m_data);
			
			this.m_OriginalData = m_data;
			this.m_OriginalData.setClassIndex(this.m_OriginalData.numAttributes()-1);
			if(!filter.isEmpty()) {
				doFilter(filter);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// read the label.
		this.loaderLabel(LabelFilePath);
	}

	private void loaderLabel(String path){
		BufferedReader reader = null;
		Stream<String> sline = null;
		try {
			reader = new BufferedReader(new FileReader(path));
			sline = reader.lines();
			
			Iterator<String> lineIterator = sline.iterator();
			
			String line;
			while(lineIterator.hasNext()) {
				line = lineIterator.next();
				this.ProcessLabelLine(line);
			}
			
			reader.close();
			sline.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally{
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (sline != null) {
				sline.close();
			}
			
		}
	}
		
		
	
	public Instances getInstances() throws IOException{
		
		return m_data;
	}
	
	private int id = 0;
	private void ProcessLabelLine(String line) {
		
		String pattern = "([0-9]+)(\\s?)([0-9]+)(\\s?)([0-9]+)";
		Pattern regexpattern = Pattern.compile(pattern);
		Matcher m = regexpattern.matcher(line);
		
		if (m.matches()) {
			String instanceId = m.group(3);
			String labelvalue = m.group(5);
		
			//Label l = this.getLabel(instanceId);//for adult dataset
			Label l = this.getLabel(""+id/10);//for tilia and poplar dataset
			l.addLabel(Integer.parseInt(labelvalue));
			id++;
		}
				
	}
	
	

	private Label getLabel(String instanceId) {
		
		Label l = null;
		int nInstanceID = Integer.parseInt(instanceId);
		
		for(Label ilabel:m_Labels) {
			if (ilabel.getinstanceID() == nInstanceID) {
				l = ilabel;
				break;
			}
		}
		
		if(l == null) {
			l = new Label(nInstanceID, this.m_data.get(nInstanceID));
			this.m_Labels.add(l);
		}
		
		return l;
	}
	
	public LabelContainer getLabelContainer(){
		return new LabelContainer(this.m_Labels);
	}
	
	public void doFilter(String attributesWillbeRemoved) {
		// TODO Auto-generated method stub
		//set the filter
		String[] options = new String[2];
		options[0] = "-R";                                    // "range"
		options[1] = attributesWillbeRemoved;                 // first attribute
		Remove remove = new Remove();                         // new instance of filter
		try {
			remove.setOptions(options);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                           // set options
		try {
			remove.setInputFormat(m_data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                          // inform filter about dataset **AFTER** setting options
		//Instances newData = Filter.useFilter(data, remove);
		try {
			m_data = Filter.useFilter(m_data, remove);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public Instances getOriginalData() {
		if(m_OriginalData == null) {
			System.out.println("Original Data is null");
		}
		return m_OriginalData;
	}
	
	private Instances createCustomInstancefromInstances(Instances data) {
		
		int num_instance = data.numInstances();
		for(int index = 0; index < num_instance; index++) {
			data.set(index, new InstanceProxy(data.instance(index),index));
		}
		
		return data;
		
	}
	
	public int getNumoflabel0() {
		int numof0 = 0;
		for(int i = 0; i < this.m_OriginalData.numInstances(); i++) {
			if(this.m_OriginalData.instance(i).classValue() == 0) {
				numof0 ++;
			}
		}
		return numof0;
	}
	
	
}
