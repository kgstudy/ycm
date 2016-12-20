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
//		HashMap<String,Object> map = new HashMap<>(); ��������
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
	
	// num�Ѱ� �޾Ƽ� ������ �����ֱ� 
	public HashMap getOneByNum(int num) {
		SqlSession ss = fac.openSession();
			HashMap map = ss.selectOne("notice.noticeone", num);
			ss.commit();
			ss.close();
		return map;
	}
	
	// ���� Ŭ���Ҷ� ��ȸ�� �÷��ִ°� 
	public int countup(int num){
		SqlSession ss = fac.openSession();
		int i = ss.update("notice.noticecountup",num);	//int�� �����µ�.. 1�� ���� 0�� ����  -> ��������..������?
		ss.commit();
		ss.close();
		return i;
	}
	
	// ���� �ۼ���~~ 
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
	 
	 //  ���� �ۻ��� 
	 public boolean noticedelete(int num){
			SqlSession ss = fac.openSession();
			int i = ss.delete("notice.noticedelete", num);
			if(i!=0){	// �� ���� ���������� 
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
