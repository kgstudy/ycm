package homework.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class CompileService {	
	
	static final String JAVA_HOME = System.getenv().get("JAVA_HOME");
	
	public String javaCompile(String java, String classPath){
		System.out.println(java);
		System.out.println(classPath);
//		Properties prop = System.getProperties();		
//		System.out.println(prop);		
		try {			
			PrintWriter fw = new PrintWriter(classPath+"\\Homework.java");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(java);
			bw.close();
			Runtime rt = Runtime.getRuntime();
			Process ps = rt.exec(JAVA_HOME+"\\bin\\javac.exe "+classPath+"\\Homework.java");
			ps.waitFor();
			Process ps2 = rt.exec(JAVA_HOME+"\\bin\\java.exe -classpath "+classPath+" Homework");
			
			String line;
			
			BufferedReader error = new BufferedReader(new InputStreamReader(ps2.getErrorStream()));			
			while((line = error.readLine()) != null){
			    System.out.println(line);
			}
			error.close();
			
			BufferedReader br = new BufferedReader( new InputStreamReader(ps2.getInputStream()) );
			String result = "";
			while((line=br.readLine())!=null){
				System.out.println(line);
				result += line;
			}			
			br.close();
			
//			OutputStream outputStream = ps2.getOutputStream();
//			PrintStream printStream = new PrintStream(outputStream);
//			printStream.println();
//			printStream.flush();
//			printStream.close();
			
			return result;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return "";
	}
}
