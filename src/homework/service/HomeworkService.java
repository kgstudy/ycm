package homework.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomeworkService {
	@Autowired
	SqlSessionFactory fac;
	
	public int write(Map map){
		SqlSession sql = fac.openSession();
		
		int r = sql.insert("homework.write", map);
		sql.close();
		return r;
	}
	
	public Map read(){
		SqlSession sql = fac.openSession();
		Map map = sql.selectOne("homework.read");
		sql.close(); 
		return map;
	}
}
