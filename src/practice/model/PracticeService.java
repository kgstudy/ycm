package practice.model;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class PracticeService {
	@Autowired
	SqlSessionFactory fac;
	
	public String content(String cls, String content){
		return content;
	}
}
