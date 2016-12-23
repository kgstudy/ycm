package storage.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StorageService {
	@Autowired
	SqlSessionFactory fac;
	
	public int write(String title, String content, String id, String category) {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("title", title);
		map.put("content", content);
		map.put("id", id);
		map.put("category", category);
		SqlSession sql = fac.openSession();
		try {
			int rst = sql.insert("storage.write", map);
			sql.commit();
			sql.close();
			return rst;
		} catch (Exception e) {
			sql.rollback();
			sql.close();
			return -1;
		}
	}
	
	public List GetRnage(int p){
		
		int endpage = 10*p;
		int startpage = endpage-9;
		HashMap<String, Integer> map = new HashMap<>();
			map.put("start", startpage);
			map.put("end", endpage);
		SqlSession sql = fac.openSession();
		List<HashMap> li = sql.selectList("storage.pageNum",map);
		sql.close();
		for(int i=0; i<li.size(); i++){
			Date date = (Date)li.get(i).get("TIME");
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
			String day = sdf.format(date);
			li.get(i).put("TIME", day);
			li.set(i, li.get(i));
		}
		return li;
	}
	
	public List GetMode(int p,String mode){
		int endpage = 10*p;
		int startpage = endpage-9;
		HashMap map = new HashMap();
			map.put("start", startpage);
			map.put("end", endpage);
			map.put("mode", mode);
		SqlSession sql = fac.openSession();
		List<HashMap> li = sql.selectList("storage.modeNum",map);
		sql.close();
		for(int i=0; i<li.size(); i++){
			Date date = (Date)li.get(i).get("TIME");
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
			String day = sdf.format(date);
			li.get(i).put("TIME", day);
			li.set(i, li.get(i));
		}
		return li;
	}
		
	// 댓글 보기 차후추가
//	public List Getcommentpage(int p, int num) {
//		int endpage = 10 * p;
//		int startpage = endpage - 9;
//		HashMap map = new HashMap();
//		map.put("start", startpage);
//		map.put("end", endpage);
//		map.put("freeboardnum", num);
//		SqlSession sql = fac.openSession();
//		List list = sql.selectList("freeboard.freeboardcommentlist", map);
//		sql.close();
//		return list;
//	}

	// 댓글 페이지 숫자
//	public int commentsize(int num) {
//		SqlSession sql = fac.openSession();
//		int size = sql.selectOne("storage.commentpagesize", num);
//		int psize = size % 10 == 0 ? size / 10 : size / 10 + 1;
//		sql.close();
//		return psize;
//	}
	
	// 상세보기
	public HashMap storagedetails(int num){
		SqlSession sql = fac.openSession();
		HashMap data = sql.selectOne("storage.storagedetails", num);
		sql.close();
		return data;
		
	}
	
	// 페이지 숫자
	public int size(){ 	
		SqlSession sql = fac.openSession();
		int size = sql.selectOne("storage.pagesize");
		int psize = size % 10 == 0? size/10 : size/10+1;
		sql.close();
		return psize;
	}
	
	// 카테고리 페이지 숫자
	public int casize(String mode){ 	
		SqlSession sql = fac.openSession();
		int size = sql.selectOne("storage.capagesize",mode);
		int psize = size % 10 == 0? size/10 : size/10+1;
		sql.close();
		return psize;
	}
	
	// 검색
	public List searchstorage(String search, int p) {
		int endpage = 10 * p;
		int startpage = endpage - 9;
		String search1 = "%" + search + "%";
		HashMap map = new HashMap();
		map.put("search", search1);
		map.put("start", startpage);
		map.put("end", endpage);
		SqlSession sql = fac.openSession();
		List<HashMap> li = sql.selectList("storage.storagesearch", map);
		sql.close();
		for(int i=0; i<li.size(); i++){
			Date date = (Date)li.get(i).get("TIME");
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
			String day = sdf.format(date);
			li.get(i).put("TIME", day);
			li.set(i, li.get(i));
		}
		return li;
	}
	
	// 검색 페이지 숫자
	public int searchstoragesize(String search) {
		SqlSession sql = fac.openSession();
		String search1 = "%" + search + "%";
		int size = sql.selectOne("storage.searchstoragesize", search1);
		int psize = size % 10 == 0 ? size / 10 : size / 10 + 1;
		sql.close();
		return psize;

	}
	
	// 검색 카테고리
	public List searchstoragemode(String search, int p, String mode) {
		int endpage = 10 * p;
		int startpage = endpage - 9;
		String search1 = "%" + search + "%";
		HashMap map = new HashMap();
		map.put("search", search1);
		map.put("start", startpage);
		map.put("end", endpage);
		map.put("mode", mode);
		SqlSession sql = fac.openSession();
		List li = sql.selectList("storage.storagesearchmode", map);
		sql.close();
		return li;
	}
	
	// 검색 카테고리 페이지
	public int searchstoragesizemode(String search, String mode) {
		SqlSession sql = fac.openSession();
		String search1 = "%" + search + "%";
		HashMap map = new HashMap();
		map.put("mode", mode);
		map.put("search", search1);
		int size = sql.selectOne("storage.storagesearchmodesize", map);
		int psize = size % 10 == 0 ? size / 10 : size / 10 + 1;
		sql.close();
		return psize;

	}
	
	
}
