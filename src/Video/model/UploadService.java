package Video.model;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadService {
	
	@Autowired
	SqlSessionFactory fac;
	
	@Autowired
	ServletContext application;
	
	public void upload(HashMap map){
		SqlSession sql = fac.openSession();
		
		int ok = sql.insert("video.upload",map);
		sql.close();
	}
	

	public String execute(MultipartFile f) {
		System.out.println(f);
		if(f.isEmpty()) {
			return null;
		}
		try {
			String uid = UUID.randomUUID().toString();
			System.out.println(uid.substring(0,23));
			// Application ��ü�� ������.. WAS �� ��ġ�� ���� ��������
			// �� ������ �����Ҽ� ������ ������.. ��� Ȯ��..
			String fileDir = application.getRealPath("/video");
//			String fileDir = application.getRealPath("/WEB-INF/files");
			String fileName = uid.substring(0,23);
			String exe = f.getOriginalFilename().substring(f.getOriginalFilename().indexOf('.'));
			File dest = new File(fileDir, fileName+exe);
			System.out.println(fileDir);
			f.transferTo(dest);
			System.out.println(fileName);
			return fileName;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String check(String fileName){
		System.out.println("����");
		System.out.println(fileName);
		
		
		String[] arr = fileName.split("\\."); // .�� split �Ϸ��� \\ �������
		
		String type= arr[1];
		System.out.println(type);
		
//		System.out.println(fileName.split(".")[1]);
		if(type.equals("avi") || type.equals("wmv") || type.equals("mp4")){
			
			return "O";
		} else{
			return "X";
		}
	}
}
