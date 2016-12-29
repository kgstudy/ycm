package notice.model;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentService {
	@Autowired
	SqlSessionFactory fac;
	
	// ��� ����ϴ°�
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
	
	// ��� ����Ʈ �Ѹ��°� 
	public List<HashMap> commentList(int num){
		SqlSession ss = fac.openSession();
		List<HashMap> list = ss.selectList("notice.comlist", num);
		ss.close();
		return list;
	}
	
	// ��� �����ϴ°� 
	public boolean commentFinish(String content, int num){
		SqlSession ss = fac.openSession();
		HashMap<Object,Object> map = new HashMap<>();
		map.put("num", num);
		map.put("content", content);
		int n = ss.update("notice.comupdate", map);
		if(n>0){
			ss.commit();
			ss.close();
			return true;
		} else {
			ss.rollback();
			ss.close();
			return false;
		}
	}
	
	// ��� �����ϴ°�
	public boolean commentDelete(int comnum){
		SqlSession ss = fac.openSession();
		int i = ss.delete("notice.comdelete", comnum);
		if(i!=0){	// ���� ���������� 
			ss.commit();
			ss.close();
			return true;
		}else{
			ss.rollback();
			ss.close();
			return false;
		}
	}
	
}
