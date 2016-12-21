package member.service;

import java.util.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class MemberService {
	@Autowired
	SqlSessionFactory fac;
	
	public boolean join(String id, String password, String name, String phone, String email){
		SqlSession ss = fac.openSession();
		HashMap<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("password", password);
		map.put("name", name);
		map.put("phone", phone);
		map.put("email", email);
		try{
			ss.insert("member.join", map);
			ss.commit();
			ss.close();
			return true;
		} catch(Exception e){
			ss.rollback();
			ss.close();
			return false;
		}
	}
	
	public List login(String id, String password){
		SqlSession ss = fac.openSession();
		HashMap<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("password", password);
		List<HashMap> list = ss.selectList("member.login", map);
		ss.close();
		return list;
	}
	
	public List endLogin(String id){
		SqlSession ss = fac.openSession();
		List<HashMap> list = ss.selectList("member.info", id);
		ss.close();
		return list;
	}
	
	public boolean idCheck(String id){
		SqlSession ss = fac.openSession();
		List<HashMap> list = ss.selectList("member.info", id);
		if(list.size()!=0){
			return false;
		} else {
			return true;
		}
	}
}
