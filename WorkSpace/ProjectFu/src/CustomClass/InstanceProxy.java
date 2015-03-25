package CustomClass;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

public class InstanceProxy implements Instance {

	private Instance m_instance;
	private int index = 0;
	
	public InstanceProxy() {
		
	}
	
	public InstanceProxy(Instance ins, int index) {
		this.m_instance = ins;
		this.index = index;
		this.m_instanceID = this.index;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Instance getM_instance() {
		return m_instance;
	}

	public void setM_instance(Instance m_instance) {
		this.m_instance = m_instance;
	}

	@Override
	public Object copy() {
		// TODO Auto-generated method stub
		InstanceProxy rc = new InstanceProxy();
		rc.m_instance = (Instance) this.m_instance.copy();
		rc.index = this.index;
		return rc;
	}

	@Override
	public Attribute attribute(int index) {
		// TODO Auto-generated method stub
		return this.m_instance.attribute(index);
	}

	@Override
	public Attribute attributeSparse(int indexOfIndex) {
		// TODO Auto-generated method stub
		return this.m_instance.attribute(indexOfIndex);
	}

	@Override
	public Attribute classAttribute() {
		// TODO Auto-generated method stub
		return this.m_instance.classAttribute();
	}

	@Override
	public int classIndex() {
		// TODO Auto-generated method stub
		return this.m_instance.classIndex();
	}

	@Override
	public boolean classIsMissing() {
		// TODO Auto-generated method stub
		return this.m_instance.classIsMissing();
	}

	@Override
	public double classValue() {
		// TODO Auto-generated method stub
		return this.m_instance.classValue();
	}

	@Override
	public Instances dataset() {
		// TODO Auto-generated method stub
		return this.m_instance.dataset();
	}

	@Override
	public void deleteAttributeAt(int position) {
		// TODO Auto-generated method stub
		this.m_instance.deleteAttributeAt(position);

	}

	@Override
	public Enumeration<Attribute> enumerateAttributes() {
		// TODO Auto-generated method stub
		return this.m_instance.enumerateAttributes();
	}

	@Override
	public boolean equalHeaders(Instance inst) {
		// TODO Auto-generated method stub
		return this.m_instance.equalHeaders(inst);
	}

	@Override
	public String equalHeadersMsg(Instance inst) {
		// TODO Auto-generated method stub
		return this.m_instance.equalHeadersMsg(inst);
	}

	@Override
	public boolean hasMissingValue() {
		// TODO Auto-generated method stub
		return this.m_instance.hasMissingValue();
	}

	@Override
	public int index(int position) {
		// TODO Auto-generated method stub
		return this.m_instance.index(position);
	}

	@Override
	public void insertAttributeAt(int position) {
		// TODO Auto-generated method stub
		this.m_instance.insertAttributeAt(position);
	}

	@Override
	public boolean isMissing(int attIndex) {
		// TODO Auto-generated method stub
		return this.m_instance.isMissing(attIndex);
	}

	@Override
	public boolean isMissing(Attribute att) {
		// TODO Auto-generated method stub
		return this.m_instance.isMissing(att);
	}

	@Override
	public boolean isMissingSparse(int indexOfIndex) {
		// TODO Auto-generated method stub
		return this.m_instance.isMissingSparse(indexOfIndex);
	}

	@Override
	public Instance mergeInstance(Instance inst) {
		// TODO Auto-generated method stub
		return this.m_instance.mergeInstance(inst);
	}

	@Override
	public int numAttributes() {
		// TODO Auto-generated method stub
		return this.m_instance.numAttributes();
	}

	@Override
	public int numClasses() {
		// TODO Auto-generated method stub
		return this.m_instance.numClasses();
	}

	@Override
	public int numValues() {
		// TODO Auto-generated method stub
		return this.m_instance.numValues();
	}

	@Override
	public Instances relationalValue(int attIndex) {
		// TODO Auto-generated method stub
		return this.m_instance.relationalValue(attIndex);
	}

	@Override
	public Instances relationalValue(Attribute att) {
		// TODO Auto-generated method stub
		return this.m_instance.relationalValue(att);
	}

	@Override
	public void replaceMissingValues(double[] array) {
		// TODO Auto-generated method stub
		this.m_instance.replaceMissingValues(array);
	}

	@Override
	public void setClassMissing() {
		// TODO Auto-generated method stub
		this.m_instance.setClassMissing();
	}

	@Override
	public void setClassValue(double value) {
		// TODO Auto-generated method stub
		this.m_instance.setClassValue(value);
	}

	@Override
	public void setClassValue(String value) {
		// TODO Auto-generated method stub
		this.m_instance.setClassValue(value);
	}

	@Override
	public void setDataset(Instances instances) {
		// TODO Auto-generated method stub
		this.m_instance.setDataset(instances);
	}

	@Override
	public void setMissing(int attIndex) {
		// TODO Auto-generated method stub
		this.m_instance.setMissing(attIndex);
	}

	@Override
	public void setMissing(Attribute att) {
		// TODO Auto-generated method stub
		this.m_instance.setMissing(att);
	}

	@Override
	public void setValue(int attIndex, double value) {
		// TODO Auto-generated method stub
		this.m_instance.setValue(attIndex,value);
	}

	@Override
	public void setValue(int attIndex, String value) {
		// TODO Auto-generated method stub
		this.m_instance.setValue(attIndex,value);
	}

	@Override
	public void setValue(Attribute att, double value) {
		// TODO Auto-generated method stub
		this.m_instance.setValue(att, value);
	}

	@Override
	public void setValue(Attribute att, String value) {
		// TODO Auto-generated method stub
		this.m_instance.setValue(att,value);
	}

	@Override
	public void setValueSparse(int indexOfIndex, double value) {
		// TODO Auto-generated method stub
		this.m_instance.setValueSparse(indexOfIndex,value);
	}

	@Override
	public void setWeight(double weight) {
		// TODO Auto-generated method stub
		this.m_instance.setWeight(weight);
	}

	@Override
	public String stringValue(int attIndex) {
		// TODO Auto-generated method stub
		return this.m_instance.stringValue(attIndex);
	}

	@Override
	public String stringValue(Attribute att) {
		// TODO Auto-generated method stub
		return this.m_instance.stringValue(att);
	}

	@Override
	public double[] toDoubleArray() {
		// TODO Auto-generated method stub
		return this.m_instance.toDoubleArray();
	}

	@Override
	public String toString(int attIndex) {
		// TODO Auto-generated method stub
		return this.m_instance.toString(attIndex);
	}

	@Override
	public String toString(Attribute att) {
		// TODO Auto-generated method stub
		return this.m_instance.toString(att);
	}

	@Override
	public String toString(int attIndex, int afterDecimalPoint) {
		// TODO Auto-generated method stub
		return this.m_instance.toString(attIndex,afterDecimalPoint);
	}

	@Override
	public String toString(Attribute att, int afterDecimalPoint) {
		// TODO Auto-generated method stub
		return this.m_instance.toString(att,afterDecimalPoint);
	}

	@Override
	public String toStringMaxDecimalDigits(int afterDecimalPoint) {
		// TODO Auto-generated method stub
		return this.m_instance.toStringMaxDecimalDigits(afterDecimalPoint);
	}

	@Override
	public String toStringNoWeight() {
		// TODO Auto-generated method stub
		return this.m_instance.toStringNoWeight();
	}

	@Override
	public String toStringNoWeight(int afterDecimalPoint) {
		// TODO Auto-generated method stub
		return this.m_instance.toStringNoWeight(afterDecimalPoint);
	}

	@Override
	public double value(int attIndex) {
		// TODO Auto-generated method stub
		return this.m_instance.value(attIndex);
	}

	@Override
	public double value(Attribute att) {
		// TODO Auto-generated method stub
		return this.m_instance.value(att);
	}

	@Override
	public double valueSparse(int indexOfIndex) {
		// TODO Auto-generated method stub
		return this.m_instance.valueSparse(indexOfIndex);
	}

	@Override
	public double weight() {
		// TODO Auto-generated method stub
		return this.m_instance.weight();
	}
	
	// CustomInstance specifics.
	private ArrayList<Integer> m_label = new ArrayList<Integer>();
	private Integer m_currentLabel = null;
	private int m_calculatedLabelUsed = 0;
	private int m_instanceID;
	
	public void setLabel(int label) {
		m_currentLabel = label;
	}
	public void setLabeltoNull(){
		m_currentLabel = null;
	}
	
	public Integer getCurrentLabel() {
		//System.out.println("Current Label = " + m_currentLabel + " for intance ID = " + m_instanceID);
		return this.m_currentLabel;
	}
	
	public void clearLabel() {
		this.m_label.clear();
	}
	
	public void addLabel(Integer label) {
		this.m_label.add(label);
	}
	
	

	public int getinstanceID() {
		//System.out.println("Instance id = " + m_instanceID);
		return m_instanceID;
	}

	public void setinstanceID(int m_instanceID) {
		this.m_instanceID = m_instanceID;
	}

	public Object calculateMajorityLabel(int numberOfLabels) {
		
		Hashtable<Object, Integer> labelMap = new Hashtable<Object, Integer> ();
		int  indexlabel = 0;
		for(;indexlabel<=numberOfLabels;m_calculatedLabelUsed++,indexlabel++) {
			
			// Prevent Array out of bounce problem;
			if (m_label.size() - 1 <= m_calculatedLabelUsed) {
				break;
			}
			
			if (labelMap.containsKey(m_label.get(m_calculatedLabelUsed))) {
				int count = labelMap.get(m_label.get(m_calculatedLabelUsed));
				labelMap.replace(m_label.get(m_calculatedLabelUsed), ++count);
			}else{
				labelMap.put(m_label.get(m_calculatedLabelUsed), 1);
			}
			
		}
		
		// this will get the maximum count for the majority.
		Object max = null;
		int MaxCount = -1;		
		for (Enumeration<Object> e = labelMap.keys(); e.hasMoreElements();) {
			Object entry = e.nextElement();
			if (MaxCount < labelMap.get(entry)) {
				max = entry;
				MaxCount = labelMap.get(entry);
			}
		}
		
		m_calculatedLabelUsed--;
		// set the Label.
		m_currentLabel = (Integer) max;
		return m_currentLabel;
	}
}
