package practice.model;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class PracticeService {
	@Autowired
	SqlSessionFactory fac;
	
	public String content(String content){
		String s = content.substring(content.indexOf('{')+1, content.lastIndexOf('}'));
		if(s.contains("public void main(String[] args)")){
			s = s.substring(s.indexOf('{')+1, s.lastIndexOf('}'));
		} else {
			return "";
		}
		if(s.contains("System")){
			if(s.contains("out")){
				if(s.contains("println")){
					if(s.contains("\"")){
						return s.substring(s.indexOf('"')+1, s.lastIndexOf('"'));
					} else {
						return s.substring(s.indexOf('(')+1, s.lastIndexOf(')'));
					}
				}
			}
		}
		return content;
	}
}
