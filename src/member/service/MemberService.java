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
			e.printStackTrace();
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
			ss.close();
			return false;
		} else {
			ss.close();
			return true;
		}
	}
	
	public boolean findId(String name, String email){
		SqlSession ss = fac.openSession();
		HashMap<String, String> map = new HashMap<>();
		map.put("name", name);
		map.put("email", email);
		List<HashMap> list = ss.selectList("member.findId", map);
		if(list.size()!=0){
			String uuid = UUID.randomUUID().toString().substring(0, 8);
			map.put("uuid", uuid);
			ss.update("member.emailAuth", map);
			ss.commit();
			ss.close();
			return true;
		} else {
			ss.close();
			return false;
		}
	}
	
	public String checkNum(String name, String num){
		SqlSession ss = fac.openSession();
		HashMap<String, String> map = new HashMap<>();
		map.put("name", name);
		map.put("num", num);
		List<HashMap> list = ss.selectList("member.checkNum", map);
		if(list.size()!=0){
			ss.close();
			return (String)list.get(0).get("ID");
		} else {
			ss.close();
			return "";
		}
	}
	
	public boolean findPw(String id, String email){
		SqlSession ss = fac.openSession();
		HashMap<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("email", email);
		List<HashMap> list = ss.selectList("member.findPw", map);
		if(list.size()!=0){
			String uuid = UUID.randomUUID().toString().substring(0, 8);
			map.put("uuid", uuid);
			ss.update("member.emailAuth2", map);
			ss.commit();
			ss.close();
			return true;
		} else {
			ss.close();
			return false;
		}
	}
	
	public boolean checkNum2(String id, String num){
		SqlSession ss = fac.openSession();
		HashMap<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("num", num);
		List<HashMap> list = ss.selectList("member.checkNum2", map);
		if(list.size()!=0){
			ss.close();
			return true;
		} else {
			ss.close();
			return false;
		}
	}
	
	public boolean changePw(String id, String pw){
		SqlSession ss = fac.openSession();
		HashMap<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pw", pw);
		int n = ss.update("member.changePw", map);
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
	
	public List<HashMap> menu(){
		SqlSession ss = fac.openSession();
		String menu = ss.selectOne("admin.menu");
		String[] ar = menu.split(",");
		String href = "";
		String id = "";
		List<HashMap> list = new Vector<>();
		for(String s : ar){
			HashMap<String, String> map = new HashMap<>();
			map.put(s, s);
			switch(s){
			case "Home":
				href = "/member/login";
				id = "home";
				break;
			case "Notice":
				href = "/notice";
				id = "notice";
				break;
			case "Question":
				href = "/question";
				id = "question";
				break;
			case "Homework":
				href = "/homework";
				id = "homework";
				break;
			case "Practice":
				href = "/practice";
				id = "practice";
				break;
			case "Storage":
				href = "/storage";
				id = "storage";
				break;
			case "Video":
				href = "/video";
				id = "video";
				break;
			case "Admin":
				href = "/admin";
				id = "admin";
				break;
			case "Class":
				href = "/member/login";
				id = "drop";
				break;
			}
			map.put("href", href);
			list.add(map);
		}
		return list;
	}
}
