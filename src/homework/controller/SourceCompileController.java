package homework.controller;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import homework.service.CompileService;

@Controller
@RequestMapping("/homework")
public class SourceCompileController {
	@Autowired
	CompileService csvc;
	@Autowired
	ServletContext context;	
	
	@RequestMapping("/compile")
	@ResponseBody
	public String getSource(String java, String ip){
		
		return csvc.javaCompile(java, context.getRealPath("/classpath"), ip); 
	}
}
