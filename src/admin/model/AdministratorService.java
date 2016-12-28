package admin.model;

import java.util.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class AdministratorService {
	@Autowired
	SqlSessionFactory fac;
	
	// ��� ����Ʈ
	public List<HashMap> member(int p, String category){
		String sql = "";
		if(category == null){
			sql = "admin.member";
		} else if(category.equals("�̸���")){
			sql ="admin.member";
		} else if(category.equals("�� class ��")){
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
	
	// ���Կ�û ����Ʈ
	public List<HashMap> joinMember(int p){
		SqlSession ss = fac.openSession();
		HashMap map = new HashMap();
		map.put("start", (p-1)*10+1);
		map.put("end", p*10);
		List<HashMap> list = ss.selectList("admin.joinMember", map);
		ss.close();
		return list;
	}
	
	// �� ȸ����
	public int size(){
		SqlSession ss = fac.openSession();
		int n = ss.selectOne("admin.size");
		ss.close();
		return n;
	}
	
	// �� class ȸ����
	public int size2(){
		SqlSession ss = fac.openSession();
		int n = ss.selectOne("admin.size2");
		ss.close();
		return n;
	}
	
	// ��� ������
	public int memberSize(String category){
		String sql = "";
		if(category == null){
			sql = "admin.memSize";
		} else if(category.equals("�� class ��")){
			sql = "admin.memSize2";
		} else if(category.equals("�̸���")){
			sql = "admin.memSize";
		}
		SqlSession ss = fac.openSession();
		int n = ss.selectOne(sql);
		ss.close();
		return n%10==0 ? n/10 : n/10+1;
	}
	
	// ���Կ�û ������
	public int joinMemSize(){
		SqlSession ss = fac.openSession();
		int n = ss.selectOne("admin.joinMemSize");
		ss.close();
		return n%10==0 ? n/10 : n/10+1;
	}
	
	// �׷� ����
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
	
	// ���Կ�û ����
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
