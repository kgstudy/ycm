package practice.model;

import java.io.*;
import java.util.*;

import javax.imageio.stream.*;
import javax.servlet.*;

import org.apache.commons.exec.*;
import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class PracticeService {
	@Autowired
	SqlSessionFactory fac;
	@Autowired
	ServletContext application;
	
	/*public String content(String content, String title){
//		String uuid = UUID.randomUUID().toString().substring(0, 10);
		String dir = application.getRealPath("/files");
		String s;
		File f = new File(dir, title+".java");
		try{
			FileOutputStream fos = new FileOutputStream(f);
			byte[] ar = content.getBytes();
			for(int i=0; i<ar.length; i++){
				fos.write(ar[i]);
			}
//			FileInputStream fis = new FileInputStream(f);
			System.out.println("파일생성완료");
			System.out.println(title);
			System.out.println(dir);
			
			Process proc = new ProcessBuilder("cmd", "cd ", dir).start();
			System.out.println("cmd 경로이동");
			proc = new ProcessBuilder("cmd", "javac ", title, ".java").start();
			System.out.println("cmd 컴파일완료");
			proc = new ProcessBuilder("cmd", "java ", title).start();
			System.out.println("cmd 실행완료");
			BufferedReader br   = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			System.out.println(br);
			while ((s =   br.readLine()) != null) System.out.println(s);
			System.out.println("======");
//			System.out.println(proc.exitValue());
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return content;
	}*/
	public String content(String content, String title){
		String dir = application.getRealPath("/files");
		File f = new File(dir, title+".java");
		try{
			FileOutputStream fos = new FileOutputStream(f);
			byte[] ar = content.getBytes();
			for(int i=0; i<ar.length; i++){
				fos.write(ar[i]);
			}
//			FileInputStream fis = new FileInputStream(f);
			System.out.println("파일생성완료");
			System.out.println(title);
			System.out.println(dir);
		} catch(Exception e){
			e.printStackTrace();
		}
		DefaultExecutor executor = new DefaultExecutor();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(baos);
        executor.setStreamHandler(streamHandler);
        CommandLine commandLine = CommandLine.parse("javac");

        // 파라미터가 필요할경우

        commandLine.addArguments(dir+"\\"+title+".java");

        try {
        	System.out.println(commandLine);
            int exitCode = executor.execute(commandLine);
            System.out.println(new String(baos.toByteArray()));
            System.out.println("exit " + exitCode);
            
            commandLine = CommandLine.parse("cd");
            commandLine.addArgument(dir);
            System.out.println(commandLine);
            exitCode = executor.execute(commandLine);
            System.out.println("exit2 "+exitCode);
            
            commandLine = CommandLine.parse("java");
            commandLine.addArgument(title);
            System.out.println(commandLine);
            exitCode = executor.execute(commandLine);
            System.out.println("exit3 "+exitCode);
        } catch (ExecuteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return content;
	}
}
