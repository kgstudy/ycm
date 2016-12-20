package question.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionService {
	@Autowired
	SqlSessionFactory fac;
	
	public String write() {
		SqlSession session = fac.openSession();
		session.close();
		return "";
	}
}
