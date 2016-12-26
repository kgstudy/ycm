package homework.service;

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
	
	public String javaCompile(String java, String classPath, String ip) {
	
		BufferedWriter bw = null;
		BufferedReader error = null;
		BufferedReader br = null;
		String line;
		String result = "";
//		Properties prop = System.getProperties();		
//		System.out.println(prop);
		String hwFile = "Homework"+ip.replace(".", "");		
		try {			
			PrintWriter fw = new PrintWriter(classPath+"\\"+hwFile+".java");
			bw = new BufferedWriter(fw);
			bw.write(java);			
			Runtime rt = Runtime.getRuntime();
			System.out.println(java);
			// Compile
			System.out.println("\""+JAVA_HOME+"\\bin\\javac.exe\" "+classPath+"\\"+hwFile+".java");
			Process ps = rt.exec(JAVA_HOME+"\\bin\\javac.exe "+classPath+"\\"+hwFile+".java");
			System.out.println("컴파일 중...");
//			error = new BufferedReader( new InputStreamReader(ps.getErrorStream()) );
//			while((line = error.readLine()) != null){
//				System.out.println(line);
//				result+=line;			    
//			}
//			if(result.length()>0){
//				return result;
//			}	
//			br = new BufferedReader( new InputStreamReader(ps.getInputStream()) );
//			
//			while((line=br.readLine())!=null){
//				System.out.print("compile: ");
//				System.out.println(line);
//				result += line;
//			}			
			ps.waitFor();			
			
			
			// Class Run
			result="";
			Process ps2 = rt.exec(JAVA_HOME+"\\bin\\java.exe -classpath "+classPath+" "+hwFile);			
			
			error = new BufferedReader( new InputStreamReader(ps2.getErrorStream()) );			
			
			while((line = error.readLine()) != null){
				System.out.println("jre : "+line);
				result+=line;			    
			}
			if(result.length()>0){
				return result;
			}				
			br = new BufferedReader( new InputStreamReader(ps2.getInputStream()) );
			
			while((line=br.readLine())!=null){
				System.out.println("result : "+line);
				result += line;
			}			
			
//			OutputStream outputStream = ps2.getOutputStream();
//			PrintStream printStream = new PrintStream(outputStream);
//			printStream.println();
//			printStream.flush();
//			printStream.close();
			
			return result;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}finally{
			try{
				bw.close();
				error.close();			
				br.close();
			}catch (IOException e) {
				e.printStackTrace();
			}		
		}
		return "";
	}
}
