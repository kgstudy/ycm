package homework.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class CompileService {	
	
	public String javaCompile(String java, String classPath){
		System.out.println(classPath);
		Properties prop = System.getProperties();
		Map map = System.getenv();
		System.out.println(prop);
		System.out.println(map.toString());
		try {
			
			PrintWriter fw = new PrintWriter(classPath+"\\Homework.java");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(java);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
