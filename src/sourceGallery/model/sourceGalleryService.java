package sourceGallery.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sourceGallery.model.pojo.StudentSourcePojo;

@Component
public class sourceGalleryService {
	@Autowired
	SqlSessionFactory fac;
	
	public List<StudentSourcePojo> read(int num){
		SqlSession sql = fac.openSession();
		List<StudentSourcePojo> list = sql.selectList("studentSource.read", num);
		sql.close();
		System.out.println("gallery read list.size : "+list.size());
		return list;
	}
}
