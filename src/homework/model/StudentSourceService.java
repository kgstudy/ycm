package homework.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import homework.model.pojo.StudentSourcePojo;

@Component
public class StudentSourceService {
	@Autowired
	SqlSessionFactory fac;
	
	public boolean write(StudentSourcePojo pojo){
		SqlSession sql = fac.openSession();
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
