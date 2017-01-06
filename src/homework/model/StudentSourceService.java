package homework.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sourceGallery.model.pojo.StudentSourcePojo;

@Component
public class StudentSourceService {
	@Autowired
	SqlSessionFactory fac;
	
	public boolean write(int num, String source, String id){
		SqlSession sql = fac.openSession();
		String name = sql.selectOne("studentSource.name", id);
		StudentSourcePojo pojo = new StudentSourcePojo();
		pojo.setHomeworkNum(num);
		pojo.setId(id);
		pojo.setName("wook");
		pojo.setSource(source);
		
		int r = sql.insert("studentSource.write", pojo);
		sql.close();
		return r==1? true: false;
	}
	
	public List read(int num){
		SqlSession sql = fac.openSession();
		List<StudentSourcePojo> list = sql.selectList("studentSource.read", num);
		sql.close();
		return list;
	}
	
	public Map sourceInfo(String id){
		SqlSession sql = fac.openSession();
		Map map = sql.selectOne("studentSource.sourceInfo", id);
		sql.close();
		return null;
	}
}
