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
	
	public List content(String content, String title){
		String uuid = UUID.randomUUID().toString().substring(0, 10);
		String dir = application.getRealPath("/files");
		File f = new File(dir, title+".java");
		String line = "";
		List<String> list = new Vector<>();
		try{
			FileOutputStream fos = new FileOutputStream(f);
			byte[] ar = content.getBytes();
			for(int i=0; i<ar.length; i++){
				fos.write(ar[i]);
			}
			System.out.println("파일생성완료");
			
			ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "javac", title+".java");
			System.out.println("빌더생성");
			builder.directory(new File(dir));
			System.out.println("빌더 경로 설정");
			Process process  = builder.start();
			System.out.println("프로세스 실행");
			process.waitFor();
			BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			if(error.readLine() != null){
				while((line = error.readLine()) != null){
					list.add(line);
				}
				return list;
			} else {
				builder = new ProcessBuilder("cmd", "/c", "java", title);
				builder.directory(new File(dir));
				process = builder.start();
				BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				while((line = reader.readLine()) != null){
					list.add(line);
				}
				return list;
			}
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
