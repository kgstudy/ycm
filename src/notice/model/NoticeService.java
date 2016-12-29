package notice.model;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	
	// 공지 글 쓰기.
	public boolean noticeInput(String title, String content, String writer){
		SqlSession ss = fac.openSession();
		HashMap<String,String> map = new HashMap<>();
		map.put("title", title);
		map.put("content", content);
		map.put("writer", writer);
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
	 public boolean noticeUpdate(String title, String content, int num, String writer){
		SqlSession ss = fac.openSession();
			HashMap<String,Object> map = new HashMap<>();
			map.put("title", title);
			map.put("content", content);
			map.put("num", num);
			map.put("writer", writer);
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
				ss.delete("notice.nocomdelete", num);
				ss.commit();
				ss.close();
				return true;
			}else{
				ss.rollback();
				ss.close();
				return false;
			}
		}
	
	 public List noticesearch(String search, int p){
			SqlSession ss = fac.openSession();
			HashMap<String,Object> map = new HashMap<>();
			map.put("start", (p-1)*5 + 1);
			map.put("end", p*5);
			String search2 = "%"+search+"%";
			map.put("search", search2);
			List<HashMap> list = ss.selectList("notice.noticesearch", map);
			/*for (int i = 0; i < list.size(); i++) {
				Date date = (Date) list.get(i).get("NOTICE_DATE");
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
				String aa = sdf.format(date);
				// System.out.println(aa);
				list.get(i).put("NOTICE_DATE", aa);
				list.set(i, list.get(i));
			}*/
			ss.close();
			return list;
		}
	 
	 
	 
	 public int getSPageSize(String search){
			SqlSession ss = fac.openSession();
			String search2 = "%"+search+"%";
			HashMap map = new HashMap();
			map.put("search", search2);
			int tot = ss.selectOne("notice.searchpage",map);
			ss.close();
			return tot%5==0? tot/5 : tot/5+1;
		}
	 
	 
}
