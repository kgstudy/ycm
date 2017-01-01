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
	
	// 메뉴 위치 변경
	public List<String> menu(String menu, String arrow){
		SqlSession ss = fac.openSession();
		String oldMenu = ss.selectOne("admin.menu");
		String[] menus = oldMenu.split(",");
		List<String> menuList = new Vector<>();
		for(int i=0; i<menus.length; i++){
			menuList.add(menus[i]);
		}
		if(menu==null || arrow==null){
			return menuList;
		} else {
			if(arrow.equals("up")){
				if(menu.equals(menus[0])){
					ss.close();
					return menuList;
				} else {
					return menuWork(ss, menuList, menu, -1);
				}
			} else{
				if(menu.equals(menus[menus.length-1])){
					ss.close();
					return menuList;
				} else {
					return menuWork(ss, menuList, menu, 1);
				}
			}
		}
	}
	
	// 메뉴 추가
	public List<String> newMenu(String name){
		SqlSession ss = fac.openSession();
		String menu = ss.selectOne("admin.menu");
		String[] ar = menu.split(",");
		List<String> list = new Vector<>();
		for(String s : ar){
			list.add(s);
		}
		menu += ","+name;
		int n = ss.update("admin.menuUpdate", menu);
		if(n>0){
			list.add(name);
			ss.commit();
			ss.close();
		} else {
			ss.rollback();
			ss.close();
		}
		return list;
	}
	
	// 메뉴 리스트
	public List<String> menuList(){
		SqlSession ss = fac.openSession();
		String menu = ss.selectOne("admin.menu");
		String[] ar = menu.split(",");
		List<String> list = new Vector<>();
		for(String s : ar){
			list.add(s);
		}
		ss.close();
		return list;
	}
	
	// 메뉴 삭제
	public List<String> removeMenu(String name){
		SqlSession ss = fac.openSession();
		String menu = ss.selectOne("admin.menu");
		String[] ar = menu.split(",");
		List<String> list = new Vector<>();
		for(int i=0; i<ar.length; i++){
			if(ar[i].equals(name)){
				continue;
			} else {
				list.add(ar[i]);
			}
		}
		menu = "";
		for(int i=0; i<list.size(); i++){
			menu += list.get(i);
			if(i!=list.size()-1){
				menu += ",";
			}
		}
		int n = ss.update("admin.menuUpdate", menu);
		if(n>0){
			ss.commit();
			menu = ss.selectOne("admin.menu");
			ar = menu.split(",");
			list = new Vector<>();
			for(String s : ar){
				list.add(s);
			}
			ss.close();
			return list;
		} else {
			ss.rollback();
			menu = ss.selectOne("admin.menu");
			ar = menu.split(",");
			list = new Vector<>();
			for(String s : ar){
				list.add(s);
			}
			ss.close();
			return list;
		}
	}
	
	// Class 메뉴
	public List<String> classList(){
		SqlSession ss = fac.openSession();
		String classes= ss.selectOne("admin.classList");
		String[] ar = classes.split(",");
		List<String> list = new Vector<>();
		for(int i=0; i<ar.length; i++){
			list.add(ar[i]);
		}
		ss.close();
		return list;
	}
	
	// class 위치 변경
	public List<String> classPosition(String menu, String arrow){
		SqlSession ss = fac.openSession();
		String oldClass = ss.selectOne("admin.classList");
		String[] ar = oldClass.split(",");
		List<String> classList = new Vector<>();
		for(int i=0; i<ar.length; i++){
			classList.add(ar[i]);
		}
		if(menu==null || arrow==null){
			return classList;
		} else {
			if(arrow.equals("up2")){
				if(menu.equals(ar[0])){
					ss.close();
					return classList;
				} else {
					return classWork(ss, classList, menu, -1);
				}
			} else{
				if(menu.equals(ar[ar.length-1])){
					ss.close();
					return classList;
				} else {
					return classWork(ss, classList, menu, 1);
				}
			}
		}
	}
	
	// 메뉴 위치변경 내부메서드
	public List<String> menuWork(SqlSession ss, List<String> menuList, String menu, int a){
		String newMenu = "";
		int n = 0;
		for(int i=0; i<menuList.size(); i++){
			if(menu.equals(menuList.get(i))){
				n = i;
				break;
			}
		}
		menuList.remove(menu);
		menuList.add(n+a, menu);
		for(int i=0; i<menuList.size(); i++){
			newMenu += menuList.get(i);
			if(i!=menuList.size()-1){
				newMenu += ",";
			}
		}
		ss.update("admin.menuUpdate", newMenu);
		ss.commit();
		ss.close();
		return menuList;
	}
	
	// class 위치변경 내부메서드
	public List<String> classWork(SqlSession ss, List<String> classList, String menu, int a){
		String newClass = "";
		int n = 0;
		for(int i=0; i<classList.size(); i++){
			if(menu.equals(classList.get(i))){
				n = i;
				break;
			}
		}
		classList.remove(menu);
		classList.add(n+a, menu);
		for(int i=0; i<classList.size(); i++){
			newClass += classList.get(i);
			if(i!=classList.size()-1){
				newClass += ",";
			}
		}
		ss.update("admin.classUpdate", newClass);
		ss.commit();
		ss.close();
		return classList;
	}
}
