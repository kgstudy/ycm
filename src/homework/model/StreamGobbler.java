package homework.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;

import org.springframework.beans.factory.annotation.Autowired;

public class StreamGobbler implements Runnable{	
	@Autowired
	CompileService cSvc;
	
	String line;
	BufferedReader br;
	
	public StreamGobbler(BufferedReader br) {
		this.br = br;
	}
	
	@Override
	public void run() {
		try {
			while((line = br.readLine()) != null){
				System.out.println("console : "+line);
				if(line.equals("start builder")){
					
				}
				cSvc.result+=line+"<br/>";			    
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
