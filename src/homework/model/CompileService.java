package homework.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import org.springframework.stereotype.Component;

@Component
public class CompileService {	
	
	static final String JAVA_HOME = System.getenv().get("JAVA_HOME");	
	static String result = "";
	
	public String javaCompile(String java, String className, String methodName, String classPath, String args) {
		return javaCompile(java, className, methodName, classPath, args, true);
	}
	public String getAnswer(String java, String className, String methodName, String classPath, String args) {
		return javaCompile(java, className, methodName, classPath, args, false);
	}
	public String javaCompile(String java, String className, String methodName, String classPath, String args, boolean comp) {
		
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
				String compileResult = "";
				
				error = new BufferedReader(new InputStreamReader(ps.getErrorStream()));
				if((line=error.readLine())!=null){
					String[] ar = line.split("\\\\");
					line = ar[ar.length-1];
					compileResult += line+"<br/>";
				}			
				while((line = error.readLine()) != null){
					System.out.println("while : "+line);
					compileResult +=line+"<br/>";
				}
				error.close();
				if(compileResult.length()>0){
					System.out.println("error : "+result);
					return compileResult;
				}			
				ps.waitFor(); // compile complete
			}
// class run
			String[] ar = args.split(",");			
			
			List list = new Vector();
			list.add("java");			
			list.add("-classpath");
			list.add(dir.toString());
			list.add(className);
			for(String str : ar){
				System.out.println("args: "+str);
				list.add(str);								
			}
			
			builder = new ProcessBuilder(list);
			ps = builder.start();
			
			OutputStreamWriter stdin = new OutputStreamWriter(ps.getOutputStream());
			writer = new BufferedWriter( stdin );			
			error = new BufferedReader( new InputStreamReader(ps.getErrorStream()) );
			br = new BufferedReader( new InputStreamReader(ps.getInputStream()) );		

			System.out.println("stream gobbler");
			
			result = "";
			StreamGobbler readGobbler = new StreamGobbler(br);
			StreamGobbler errGobbler = new StreamGobbler(error);	
			new Thread(readGobbler).start();
			new Thread(errGobbler).start();
// Scanner writer			
//			writer.write("aaa");
//			writer.write("\r\n");
//			writer.flush();
			ps.waitFor();
			System.out.println("result: "+result);
			return result;
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		return result;	
		
	}
}
