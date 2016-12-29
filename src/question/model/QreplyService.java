package question.model;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QreplyService {
	@Autowired
	SqlSessionFactory fac;
	
	//¥Ò±€ ∏Ò∑œ
	public List qReply1(int num) {
		SqlSession session = fac.openSession();
		List list = session.selectList("question.Rlist", num);
		session.close();
		return list;
	}
	
	//¥Ò±€ ¿€º∫
	public boolean qReplyIs(HashMap map) {
		SqlSession session = fac.openSession();
		boolean r = session.insert("question.Rwrite", map)==1? true : false;
		session.close();
		return r;
	}
}
