package notice.model;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoticeService {
	@Autowired
	SqlSessionFactory fac;
	
	public List noticeList(){
		SqlSession ss = fac.openSession();
//		HashMap<String,Object> map = new HashMap<>(); 쓸일있음
		List<HashMap> list = ss.selectList("notice.nolist");
		ss.close();
		return list;
	}
	
	
	public boolean noticeInput(String title, String content){
		SqlSession ss = fac.openSession();
		HashMap<String,String> map = new HashMap<>();
		map.put("title", title);
		map.put("content", content);
		try{
		ss.insert("notice.noinput", map);
		ss.commit();
		ss.close();
		return true;
		}catch (Exception e){
			ss.rollback();
			ss.close();
			e.printStackTrace();
		}
		return false;
	}
	
	// num넘겨 받아서 공지뷰 보여주기 
	public HashMap getOneByNum(int num) {
		SqlSession ss = fac.openSession();
			HashMap map = ss.selectOne("notice.noticeone", num);
			ss.commit();
			ss.close();
		return map;
	}
	
	// 공지 클릭할때 조회수 올려주는거 
	public int countup(int num){
		SqlSession ss = fac.openSession();
		int i = ss.update("notice.noticecountup",num);	//int로 나오는데.. 1이 성공 0이 실패  -> 실패할일..없을듯?
		ss.commit();
		ss.close();
		return i;
	}
	
	// 공지 글수정~~ 
	 public boolean noticeUpdate(String title, String content, int num){
		SqlSession ss = fac.openSession();
			HashMap<String,Object> map = new HashMap<>();
			map.put("title", title);
			map.put("content", content);
			map.put("num", num);
			try{
				ss.update("notice.writeupdate", map);
				ss.commit();
				ss.close();
				return true;
			}catch (Exception e) {
				ss.rollback();
				ss.close();
				e.printStackTrace();
				return false;
			}
	}
	 
	 //  공지 글삭제 
	 public boolean noticedelete(int num){
			SqlSession ss = fac.openSession();
			int i = ss.delete("notice.noticedelete", num);
			if(i!=0){	// 글 삭제 성공했을때 
				ss.commit();
				ss.close();
				return true;
			}else{
				ss.rollback();
				ss.close();
				return false;
			}
		}
	
	
}
