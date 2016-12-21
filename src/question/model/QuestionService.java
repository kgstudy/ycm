package question.model;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionService {
	@Autowired
	SqlSessionFactory fac;
	
	//�� ���
	public List qread() {
		SqlSession session = fac.openSession();
		List list = session.selectList("question.list");
		session.close();
		return list;
	}
	
	//�� ����
	public boolean qwrite(String title, HttpSession login, String content, boolean check) {
		SqlSession session = fac.openSession();
		HashMap map = new HashMap();
		HashMap<String, String> smap = (HashMap)login.getAttribute("login");
		String writer = smap.get("NAME");
		map.put("title", title);
		map.put("writer", writer);
		map.put("content", content);
		map.put("check", check);
		
		try {
			session.insert("question.write", map);
			session.commit();
			session.close();
			return true;
		} catch(Exception e) {
			session.rollback();
			session.close();
			e.printStackTrace();
		}
		return false;
	}
	
	//�� �б�
	public HashMap qBoard(int num) {
		SqlSession session = fac.openSession();
		HashMap map = session.selectOne("question.qBoard", num);
		session.close();
		return map;
	}
}
