package storage.model;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StorageService {
	@Autowired
	SqlSessionFactory fac;
	
	public int write(String title, String content, String id, String category) {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("title", title);
		map.put("content", content);
		map.put("id", id);
		map.put("category", category);
		SqlSession sql = fac.openSession();
		try {
			int rst = sql.insert("storage.write", map);
			sql.commit();
			sql.close();
			return rst;
		} catch (Exception e) {
			sql.rollback();
			sql.close();
			return -1;
		}
	}
	
	
}
