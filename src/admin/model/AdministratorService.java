package admin.model;

import java.util.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class AdministratorService {
	@Autowired
	SqlSessionFactory fac;
	
	public List<HashMap> member(){
		SqlSession ss = fac.openSession();
		List<HashMap> list = ss.selectList("member.member");
		return list;
	}
}
