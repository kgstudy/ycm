package admin.model;

import java.util.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class AdministratorService {
	@Autowired
	SqlSessionFactory fac;
	
	public List<HashMap> member(int p, String category){
		if(category == null){
			category = "name";
		} else if(category.equals("ÀÌ¸§¼ø")){
			category = "name";
		} else if(category.equals("ºó class ¸¸")){
			category = "name";
		}
		SqlSession ss = fac.openSession();
		HashMap map = new HashMap();
		map.put("start", (p-1)*10+1);
		map.put("end", p*10);
		map.put("category", category);
		List<HashMap> list = ss.selectList("admin.member", map);
		ss.close();
		return list;
	}
	
	public List<HashMap> joinMember(int p){
		SqlSession ss = fac.openSession();
		HashMap map = new HashMap();
		map.put("start", (p-1)*10+1);
		map.put("end", p*10);
		List<HashMap> list = ss.selectList("admin.joinMember", map);
		ss.close();
		return list;
	}
	
	public int memberSize(){
		SqlSession ss = fac.openSession();
		int n = ss.selectOne("admin.memSize");
		ss.close();
		return n/10==0 ? n/10 : n/10+1;
	}
	
	public int joinMemSize(){
		SqlSession ss = fac.openSession();
		int n = ss.selectOne("admin.joinMemSize");
		ss.close();
		return n/10==0 ? n/10 : n/10+1;
	}
	
	public boolean group(String[] ar, String group){
		SqlSession ss = fac.openSession();
		int n = 0;
		for(String id : ar){
			HashMap<String, String> map = new HashMap<>();
			map.put("id", id);
			map.put("class", group);
			n += ss.update("admin.class", map);
		}
		if(n==ar.length){
			ss.commit();
			ss.close();
			return true;
		} else {
			ss.rollback();
			ss.close();
			return false;
		}
	}
	
	public boolean accept(String[] ar){
		SqlSession ss = fac.openSession();
		int n = 0;
		for(String id : ar){
			n += ss.update("admin.joinAccept", id);
		}
		if(n==ar.length){
			ss.commit();
			ss.close();
			return true;
		} else {
			ss.rollback();
			ss.close();
			return false;
		}
	}
}
