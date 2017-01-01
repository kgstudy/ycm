package homework.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import homework.model.pojo.HomeworkPojo;

@Component
public class AdminService {
	@Autowired
	SqlSessionFactory fac;
	
	HomeworkPojo pojo;
	
	public int write(HomeworkPojo pojo){
		System.out.println("write: "+pojo.getSource());
		SqlSession sql = fac.openSession();
		if(sql.insert("homework.write", pojo)!=1)
			return 0;
		int num = sql.selectOne("homework.currval");		
		sql.close();
		return num;
	}
			
	public HomeworkPojo read(int num){
		SqlSession sql = fac.openSession();
		pojo = sql.selectOne("homework.read", num);
		sql.close(); 
		return pojo;
	}
	
	public int update(HomeworkPojo pojo){
		System.out.println("update: "+pojo.getSource());
		SqlSession sql = fac.openSession();
		int r = sql.update("homework.update", pojo);
		sql.close();		
		return r;
	}
	
	public int modiAnswer(String answer){
		SqlSession sql = fac.openSession();
		
		int r = sql.update("homework.modiAnswer", answer);
		sql.close();
		return r;
	}
	
	public int modiSource(String source){
		SqlSession sql = fac.openSession();
		
		int r = sql.update("homework.modiSource", source);
		sql.close();
		return r;
	}
	public int delete(int num){
		SqlSession sql = fac.openSession();
		
		int r = sql.update("homework.delete", num);
		sql.close();
		return r;
	}
}
