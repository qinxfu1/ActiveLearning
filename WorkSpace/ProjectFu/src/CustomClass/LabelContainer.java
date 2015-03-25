package CustomClass;

import java.util.ArrayList;

import weka.core.Instance;

public class LabelContainer {
	private ArrayList<Label> m_Labels;
	
	public LabelContainer(ArrayList<Label> labels){
		this.m_Labels = labels;
	}
	
	public int getSize() {
		return this.m_Labels.size();
	}

	// do value comparison.
	public Label getLabel(Instance instance){
		Instance current;
		boolean equals = true;
		for(Label l:m_Labels){
			current = l.getinstancePointer();
			for(int attrNum = 0; attrNum < l.getinstancePointer().numAttributes(); attrNum++) {
				
				if(current.value(attrNum) == instance.value(attrNum)) {
					equals = true;
				}else {
					equals = false;
					break;
				}
				
			}

			if(equals) {
				return l;
			}
		}
		return null;
	}
	
	public Label getLabel(int id){
		for(Label l:m_Labels){
			if(l.getinstanceID() == id){
				return l;
			}
		}
		return null;
		
	}
}
