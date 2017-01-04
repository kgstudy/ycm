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
		} else {
			sql = "admin.selClass2";
		}
		SqlSession ss = fac.openSession();
		HashMap map = new HashMap();
		map.put("start", (p-1)*10+1);
		map.put("end", p*10);
		map.put("category", category);
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
	
	// Class 회원수
	public int size3(String category){
		SqlSession ss = fac.openSession();
		int n = ss.selectOne("admin.size3", category);
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
		} else {
			sql = "admin.memSize3";
		}
		SqlSession ss = fac.openSession();
		int n = ss.selectOne(sql, category);
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
	public List<HashMap> menu(String menu, String arrow){
		SqlSession ss = fac.openSession();
		List<HashMap> menuList = ss.selectList("admin.menu");
//		String[] menus = oldMenu.split(",");
//		List<String> menuList = new Vector<>();
//		for(int i=0; i<menus.length; i++){
//			menuList.add(menus[i]);
//		}
		if(menu==null || arrow==null){
			return menuList;
		} else {
			if(arrow.equals("up")){
				if(menu.equals(menuList.get(0).get("MENU"))){
					ss.close();
					return menuList;
				} else {
					return menuWork(ss, menuList, menu, -1);
				}
			} else{
				if(menu.equals(menuList.get(menuList.size()-1).get("MENU"))){
					ss.close();
					return menuList;
				} else {
					return menuWork(ss, menuList, menu, 1);
				}
			}
		}
	}
	
	// 메뉴 추가
	public List<HashMap> newMenu(String name, String id){
		SqlSession ss = fac.openSession();
		List<HashMap> menu = ss.selectList("admin.menu");
		HashMap newMenu = new HashMap();
		String href = "/custom/"+id;
		newMenu.put("menu", name);
		newMenu.put("id", id);
		newMenu.put("href", href);
		newMenu.put("num", menu.size()+1);
		try{
			ss.insert("admin.menuAdd", newMenu);
			ss.commit();
			menu = ss.selectList("admin.menu");
			ss.close();
		} catch(Exception e){
			ss.rollback();
			ss.close();
			e.printStackTrace();
		}
		return menu;
	}
	
	// Class 추가
	public List<String> newClass(String name){
		SqlSession ss = fac.openSession();
		String menu = ss.selectOne("admin.classList");
		String[] ar = menu.split(",");
		List<String> list = new Vector<>();
		for(String s : ar){
			list.add(s);
		}
		menu += ","+name;
		int n = ss.update("admin.classUpdate", menu);
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
	public List<HashMap> menuList(){
		SqlSession ss = fac.openSession();
		List<HashMap> menu = ss.selectList("admin.menu");
		ss.close();
		return menu;
	}
	
	// 메뉴 삭제
	public List<HashMap> removeMenu(String name){
		SqlSession ss = fac.openSession();
		List<HashMap> menu = ss.selectList("admin.menu");
		if(ss.delete("admin.removeMenu", name)>0){
			ss.commit();
			menu = ss.selectList("admin.menu");
			ss.close();
		} else {
			ss.rollback();
			ss.close();
		}
		return menu;
	}
	
	// Class 삭제
	public List<String> removeClass(String name){
		SqlSession ss = fac.openSession();
		String menu = ss.selectOne("admin.classList");
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
		int n = ss.update("admin.classUpdate", menu);
		if(n>0){
			ss.commit();
			menu = ss.selectOne("admin.classList");
			ar = menu.split(",");
			list = new Vector<>();
			for(String s : ar){
				list.add(s);
			}
			ss.close();
			return list;
		} else {
			ss.rollback();
			menu = ss.selectOne("admin.classList");
			ar = menu.split(",");
			list = new Vector<>();
			for(String s : ar){
				list.add(s);
			}
			ss.close();
			return list;
		}
	}
	
	// Class 리스트
	public List<HashMap> classList(){
		SqlSession ss = fac.openSession();
		List<HashMap> classes= ss.selectList("admin.classList");
		return classes;
	}
	
	// class 위치 변경
	public List<HashMap> classPosition(String menu, String arrow){
		SqlSession ss = fac.openSession();
		List<HashMap> classList = ss.selectList("admin.classList");
		if(menu==null || arrow==null){
			return classList;
		} else {
			if(arrow.equals("up2")){
				if(menu.equals(classList.get(0).get("NAME"))){
					ss.close();
					return classList;
				} else {
					return classWork(ss, classList, menu, -1);
				}
			} else{
				if(menu.equals(classList.get(classList.size()-1).get("NAME"))){
					ss.close();
					return classList;
				} else {
					return classWork(ss, classList, menu, 1);
				}
			}
		}
	}
	
	// 메뉴 위치변경 내부메서드
	public List<HashMap> menuWork(SqlSession ss, List<HashMap> menuList, String menu, int a){
		HashMap newMap = new HashMap<>();
		int n = 0;
		for(int i=0; i<menuList.size(); i++){
			if(menu.equals(menuList.get(i).get("MENU"))){
				n = i;
				break;
			}
		}
		newMap = menuList.get(n);
		menuList.remove(n);
		menuList.add(n+a, newMap);
		for(int i=0; i<menuList.size(); i++){
			menuList.get(i).put("NUM", i+1);
			ss.update("admin.menuUpdate", menuList.get(i));
		}
		ss.commit();
		ss.close();
		return menuList;
	}
	
	// class 위치변경 내부메서드
	public List<HashMap> classWork(SqlSession ss, List<HashMap> classList, String menu, int a){
		HashMap newMap = new HashMap<>();
		int n = 0;
		for(int i=0; i<classList.size(); i++){
			if(menu.equals(classList.get(i).get("NAME"))){
				n = i;
				break;
			}
		}
		newMap = classList.get(n);
		classList.remove(n);
		classList.add(n+a, newMap);
		for(int i=0; i<classList.size(); i++){
			classList.get(i).put("NUM", i+1);
			ss.update("admin.classUpdate", classList.get(i));
		}
		ss.commit();
		ss.close();
		return classList;
	}
}
