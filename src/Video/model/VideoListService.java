package Video.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideoListService {

	@Autowired
	SqlSessionFactory fac;
	
	public List AllList(){
		SqlSession sql = fac.openSession();
		
		List list = sql.selectList("video.list");
		sql.close();
		return list;
		
	}
}
