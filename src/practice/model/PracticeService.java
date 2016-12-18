package practice.model;

import java.util.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class PracticeService {
	@Autowired
	SqlSessionFactory fac;
	
	public String content(String content){
		System.out.println(content);
		StringTokenizer st = new StringTokenizer(content);
		while(st.hasMoreTokens()){
			System.out.println(st.nextToken());
		}
//		String s = content.substring(content.indexOf('{')+1, content.lastIndexOf('}'));
//		System.out.println(s);
		return content;
	}
}
