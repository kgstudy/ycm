package admin.model;

import java.util.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class AdministratorService {
	@Autowired
	SqlSessionFactory fac;
	
	public List<HashMap> member(int p, String category){
		if(category == null){
			category = "ÀÌ¸§¼ø";
		}
		SqlSession ss = fac.openSession();
		HashMap map = new HashMap();
		map.put("start", (p-1)*10+1);
		map.put("end", p*10);
		map.put("category", category);
		List<HashMap> list = ss.selectList("admin.member", map);
		return list;
	}
}
