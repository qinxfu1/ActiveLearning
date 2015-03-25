package utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteAcc {
	private String mstrfileName;
	private FileWriter mfw;
	private BufferedWriter out;
	
	public WriteAcc(String fn){
		this.mstrfileName = fn;
		try {
			this.mfw = new FileWriter(this.mstrfileName,true);
			this.out = new BufferedWriter(mfw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void write(String str){
		try {
			this.out.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void close(){
		try {
			this.out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
