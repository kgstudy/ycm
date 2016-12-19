package practice.model;

import java.io.*;
import java.util.*;

import javax.imageio.stream.*;
import javax.servlet.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class PracticeService {
	@Autowired
	SqlSessionFactory fac;
	@Autowired
	ServletContext application;
	
	public String content(String content){
		String uuid = UUID.randomUUID().toString().substring(0, 10);
		String dir = application.getRealPath("/files");
		File f = new File(dir, uuid+".java");
		try{
			FileOutputStream fos = new FileOutputStream(f);
			byte[] ar = content.getBytes();
			for(int i=0; i<ar.length; i++){
				fos.write(ar[i]);
			}
//			FileInputStream fis = new FileInputStream(f);
			System.out.println("파일생성완료");
			System.out.println(uuid);
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
//		String s = content.substring(content.indexOf('{')+1, content.lastIndexOf('}'));
//		if(s.contains("public void main(String[] args)")){
//			s = s.substring(s.indexOf('{')+1, s.lastIndexOf('}'));
//		} else {
//			return "";
//		}
//		if(s.contains("System")){
//			if(s.contains("out")){
//				if(s.contains("println")){
//					if(s.contains("\"")){
//						return s.substring(s.indexOf('"')+1, s.lastIndexOf('"'));
//					} else {
//						return s.substring(s.indexOf('(')+1, s.lastIndexOf(')'));
//					}
//				}
//			}
//		}
		return content;
	}
}
