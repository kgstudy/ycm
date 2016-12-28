package notice.model;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentService {
	@Autowired
	SqlSessionFactory fac;
	
	public boolean commtentInput(int num, String content, String writer){
		SqlSession ss = fac.openSession();
		HashMap<String,Object> map = new HashMap<>();
		map.put("num", num);
		map.put("content", content);
		map.put("writer", writer);
		try{
		ss.insert("notice.cominput", map);
		ss.commit();
		ss.close();
		return true;
		}catch (Exception e){
			ss.rollback();
			ss.close();
			e.printStackTrace();
		}
		return false;
	}
}
