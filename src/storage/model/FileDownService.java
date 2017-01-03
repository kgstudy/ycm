package storage.model;

import java.io.*;
import java.math.*;
import java.util.*;

import javax.annotation.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.view.*;

import com.amazonaws.auth.*;
import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.*;

@Component
public class FileDownService extends AbstractView {
	@Autowired
	ServletContext application;
	Properties config;
	
	@PostConstruct
	public void init(){
		config = new Properties();
		config.setProperty("BUCKET_NAME", "ycm99");
		config.setProperty("ACCESS_KEY", "AKIAJS2ODEWZTDQBROUQ");
		config.setProperty("SECRET_KEY", "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");
	}
	
	protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String url = (String)map.get("url");
		String filename = url.substring(url.lastIndexOf('/')+1);
		AWSCredentials credentials = new BasicAWSCredentials(config.getProperty("ACCESS_KEY"),
				config.getProperty("SECRET_KEY"));
		AmazonS3 s3 = new AmazonS3Client(credentials);
		S3Object s3object = s3.getObject(new GetObjectRequest(config.getProperty("BUCKET_NAME"), "storage/"+filename));
		
		resp.setHeader("content-Disposition", "attatchment;fileName=\""+filename+"\"");

		S3Object object = s3.getObject(new GetObjectRequest(config.getProperty("BUCKET_NAME"), "storage/"+filename));
        File temp = displayTextInputStream(object.getObjectContent(), filename, application);
        FileInputStream fis = new FileInputStream(temp);
		FileCopyUtils.copy(fis, resp.getOutputStream());	//이거 한줄이면 파일 인풋해서 아웃풋 바로 가능
//		temp.delete();
		File ff = new File(application.getRealPath("/"), filename);
		if(ff.exists()){
			ff.delete();
		}
	}
	
	private static File displayTextInputStream(InputStream input, String filename, ServletContext application) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        File f = new File(application.getRealPath("/"), filename);
        FileOutputStream fos = new FileOutputStream(f);
        while (true) {
            String line = reader.readLine();
            if (line == null) {
            	break;
            }
            line += "\r\n";
            fos.write(line.getBytes());
        }
        return f;
    }
}
