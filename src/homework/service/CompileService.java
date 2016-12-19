package homework.service;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lowagie.text.pdf.codec.Base64.InputStream;
import com.lowagie.text.pdf.codec.Base64.OutputStream;

@Component
public class CompileService {	
	
	public String javaCompile(String java, String classPath){
		System.out.println(classPath);
		try {
			FileWriter fw = new FileWriter(classPath+"\\homework.java");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(java);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
