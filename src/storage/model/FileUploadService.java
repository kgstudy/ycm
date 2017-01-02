package storage.model;

import java.io.*;
import java.util.*;

import javax.annotation.*;
import javax.servlet.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import com.amazonaws.auth.*;
import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.*;

@Component
public class FileUploadService {
	@Autowired
	ServletContext application;
	
	Properties config;
	
	@PostConstruct
	public void init(){
		config = new Properties();
		config.setProperty("AWS_BUCKETNAME", "ycm");
		config.setProperty("AWS_ACCESS_KEY", "AKIAIWALZGI5LC6MO33A");
		config.setProperty("AWS_SECRET_KEY", "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");
	}
	
	public void upload(MultipartFile f){
		File temp = saveTempServer(f);
		String name = f.getOriginalFilename();
		
		AWSCredentials credentials = new BasicAWSCredentials(config.getProperty("AWS_ACCESS_KEY"),
				config.getProperty("AWS_SECRET_KEY"));
		AmazonS3 s3 = new AmazonS3Client(credentials);
		
		try{
			PutObjectRequest request = new PutObjectRequest(config.getProperty("AWS_BUCKETNAME"), "storage/"+name, temp);
			
			s3.putObject(request);
			System.out.println("Upload OK");
			temp.delete();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public File saveTempServer(MultipartFile f){
		if(f.isEmpty()){
			return null;
		} else {
			try{
				String uuid = UUID.randomUUID().toString().substring(0, 10);
				File temp = new File(application.getRealPath("/storage"), uuid);
				f.transferTo(temp);
				return temp;
			} catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
	}
}
