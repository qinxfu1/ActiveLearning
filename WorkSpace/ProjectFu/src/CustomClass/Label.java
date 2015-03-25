package CustomClass;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import weka.core.Instance;

public class Label {
	
	private ArrayList<Integer> m_label = new ArrayList<Integer>();
	private Object m_currentLabel;
	private int m_calculatedLabelUsed = 0;
	private int m_instanceID;
	private Instance m_instancePointer;
	
	public Label(int m_instanceID, Instance m_instancePointer) {
		super();
		this.m_instanceID = m_instanceID;
		this.m_instancePointer = m_instancePointer;
	}

	public Object calculateMajorityLabel(int numberOfLabels) {
		
		Hashtable<Object, Integer> labelMap = new Hashtable<Object, Integer> ();
		m_calculatedLabelUsed += numberOfLabels;
		for(int indexlabel = 0;indexlabel < m_calculatedLabelUsed;indexlabel++) {
			
			// Prevent Array out of bounce problem;
			if (m_label.size() - 1 < indexlabel) {
				break;
			}
			
			if (labelMap.containsKey(m_label.get(indexlabel))) {
				int count = labelMap.get(m_label.get(indexlabel));
				labelMap.replace(m_label.get(indexlabel), ++count);
			}else{
				labelMap.put(m_label.get(indexlabel), 1);
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
		
		// set the Label.
		m_currentLabel = max;
		return m_currentLabel;
	}
	
	public void setLabel(int label) {
		m_currentLabel = label;
	}
	
	public Object getCurrentLabel() {
		return this.m_currentLabel;
	}
	
	public void clearLabel() {
		this.m_label.clear();
	}
	
	public void addLabel(Integer label) {
		this.m_label.add(label);
	}

	public int getinstanceID() {
		return m_instanceID;
	}

	public void setinstanceID(int m_instanceID) {
		this.m_instanceID = m_instanceID;
	}

	public Instance getinstancePointer() {
		return m_instancePointer;
	}

	public void setinstancePointer(Instance m_instancePointer) {
		this.m_instancePointer = m_instancePointer;
	}
	
	
}
