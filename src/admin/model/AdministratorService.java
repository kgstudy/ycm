package admin.model;

import java.util.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class AdministratorService {
	@Autowired
	SqlSessionFactory fac;
	
	// 멤버 리스트
	public List<HashMap> member(int p, String category){
		String sql = "";
		if(category == null){
			sql = "admin.member";
		} else if(category.equals("이름순")){
			sql ="admin.member";
		} else if(category.equals("빈 class 만")){
			sql = "admin.selClass";
		}
		SqlSession ss = fac.openSession();
		HashMap map = new HashMap();
		map.put("start", (p-1)*10+1);
		map.put("end", p*10);
		List<HashMap> list = ss.selectList(sql, map);
		ss.close();
		return list;
	}
	
	// 가입요청 리스트
	public List<HashMap> joinMember(int p){
		SqlSession ss = fac.openSession();
		HashMap map = new HashMap();
		map.put("start", (p-1)*10+1);
		map.put("end", p*10);
		List<HashMap> list = ss.selectList("admin.joinMember", map);
		ss.close();
		return list;
	}
	
	// 총 회원수
	public int size(){
		SqlSession ss = fac.openSession();
		int n = ss.selectOne("admin.size");
		ss.close();
		return n;
	}
	
	// 빈 class 회원수
	public int size2(){
		SqlSession ss = fac.openSession();
		int n = ss.selectOne("admin.size2");
		ss.close();
		return n;
	}
	
	// 멤버 사이즈
	public int memberSize(String category){
		String sql = "";
		if(category == null){
			sql = "admin.memSize";
		} else if(category.equals("빈 class 만")){
			sql = "admin.memSize2";
		} else if(category.equals("이름순")){
			sql = "admin.memSize";
		}
		SqlSession ss = fac.openSession();
		int n = ss.selectOne(sql);
		ss.close();
		return n%10==0 ? n/10 : n/10+1;
	}
	
	// 가입요청 사이즈
	public int joinMemSize(){
		SqlSession ss = fac.openSession();
		int n = ss.selectOne("admin.joinMemSize");
		ss.close();
		return n%10==0 ? n/10 : n/10+1;
	}
	
	// 그룹 설정
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
	
	// 가입요청 수락
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
