package homework.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class CompileService {	
	
	static final String JAVA_HOME = System.getenv().get("JAVA_HOME");	
	static String result = "";
	
	public String javaCompile(String java, String className, String methodName, String classPath) {
		return javaCompile(java, className, methodName, classPath, true);
	}
	public String getAnswer(String java, String className, String methodName, String classPath) {
		return javaCompile(java, className, methodName, classPath, false);
	}
	public String javaCompile(String java, String className, String methodName, String classPath, boolean comp) {
		
		BufferedWriter bw = null;
		BufferedReader error = null;
		BufferedReader br = null;
		BufferedWriter writer = null;
		ProcessBuilder builder = null;
		Process ps = null;
		String line;
		
		File dir = new File(classPath);
		if(!dir.exists()){
			dir.mkdirs();
		}		
		System.out.println("dir: "+dir.toString());
		
		try {
			if(comp){
				// 자바 파일 만들기
				PrintWriter fw = new PrintWriter(classPath+"\\"+className+".java");
				bw = new BufferedWriter(fw);
				bw.write(java);
				bw.close();
				// 자바 컴파일
				builder = new ProcessBuilder("javac", dir.getPath()+"\\"+className+".java");			
				ps = builder.start();			
				
				error = new BufferedReader(new InputStreamReader(ps.getErrorStream()));
				if((line=error.readLine())!=null){
					String[] ar = line.split("\\\\");
					line = ar[ar.length-1];
					result += line+"<br/>";
				}			
				while((line = error.readLine()) != null){
					System.out.println("while : "+line);
					result +=line+"<br/>";
				}
				error.close();
				if(result.length()>0){
					System.out.println("error : "+result);
					return result;
				}			
				ps.waitFor(); // compile complete
			}

// class run
			String[] args = {
					
			};
			builder = new ProcessBuilder("java", "-classpath", dir.toString(), className);
			
			ps = builder.start();
			
			OutputStreamWriter stdin = new OutputStreamWriter(ps.getOutputStream());
			writer = new BufferedWriter( stdin );			
			error = new BufferedReader( new InputStreamReader(ps.getErrorStream()) );
			br = new BufferedReader( new InputStreamReader(ps.getInputStream()) );		

			System.out.println("stream gobbler");

			StreamGobbler readGobbler = new StreamGobbler(br);
			StreamGobbler errGobbler = new StreamGobbler(error);	
			new Thread(readGobbler).start();
			new Thread(errGobbler).start();
			writer.write("aaa");
			writer.write("\r\n");
			writer.flush();
			System.out.println("5넣기 시작");			
			ps.waitFor();
			System.out.println("result: "+result);
			return result;
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		return result;
		
//		Scanner sc = new Scanner(System.in);
//		try{			
//			while((line = br.readLine()) != null){
//				System.out.println("console : "+line);
//				if(line.equals("start builder"))
//					writer.flush();
//				result+=line;			    
//			}			
//			return result;
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}		
		
			
//			Runtime rt = Runtime.getRuntime();
//			System.out.println(java);
//		
//			System.out.println("\""+JAVA_HOME+"\\bin\\javac.exe\" "+classPath+"\\"+hwFile+".java");
//			Process ps = rt.exec(JAVA_HOME+"\\bin\\javac.exe "+classPath+"\\"+hwFile+".java");
//			System.out.println("컴파일 중...");
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
//			ps.waitFor();			
			
			
			// Class Run
//			result="";
//			Process ps2 = rt.exec(JAVA_HOME+"\\bin\\java.exe -classpath "+classPath+" "+hwFile);			
//			
//			error = new BufferedReader( new InputStreamReader(ps2.getErrorStream()) );			
//			
//			while((line = error.readLine()) != null){
//				System.out.println("jre : "+line);
//				result+=line;			    
//			}
//			if(result.length()>0){
//				return result;
//			}				
//			br = new BufferedReader( new InputStreamReader(ps2.getInputStream()) );
//			
//			while((line=br.readLine())!=null){
//				System.out.println("result : "+line);
//				result += line;
//			}		
		
	}
}
