package storage.model;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.annotation.*;
import javax.servlet.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import com.amazonaws.*;
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
		config.setProperty("BUCKET_NAME", "ycm99");
		config.setProperty("ACCESS_KEY", "AKIAJS2ODEWZTDQBROUQ");
		config.setProperty("SECRET_KEY", "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");
	}
	
	public String upload(MultipartFile f){
		String name = f.getOriginalFilename();
		
		AWSCredentials credentials = new BasicAWSCredentials(config.getProperty("ACCESS_KEY"),
				config.getProperty("SECRET_KEY"));
		AmazonS3 s3 = new AmazonS3Client(credentials);
		String result = "";
		
		// 파일 업로드 설정 제대로 바꾸기
		try{
			S3Object s3object = new S3Object();
			
			ObjectMetadata omd = new ObjectMetadata();
			omd.setContentType(f.getContentType());
			omd.setContentLength(f.getSize());
			omd.setHeader("filename", name);
			
			ByteArrayInputStream bis = new ByteArrayInputStream(f.getBytes());
			
			PutObjectRequest request = new PutObjectRequest(config.getProperty("BUCKET_NAME"), "storage/"+name, bis, omd);
			request.setCannedAcl(CannedAccessControlList.PublicRead);
			
			s3.putObject(request);
			s3object.close();
			
			Date date = new Date();
			GeneratePresignedUrlRequest generate = new GeneratePresignedUrlRequest(config.getProperty("BUCKET_NAME"), "storage/"+name);
			generate.setMethod(HttpMethod.GET);
			generate.setExpiration(date);
			URL url = s3.generatePresignedUrl(generate);
			result = url.toString().substring(0, url.toString().indexOf('?')).replace("https://", "http://");
			System.out.println("Upload OK");
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
