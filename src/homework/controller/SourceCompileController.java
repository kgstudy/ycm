package homework.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import homework.model.CompileService;

@Controller
@RequestMapping("/hw")
public class SourceCompileController {
	@Autowired
	CompileService csvc;
	@Autowired
	ServletContext context;	
	
	@RequestMapping("/compile")
	@ResponseBody
	public String getSource(HttpSession session, HttpServletRequest req, String java, String className, String methodName){
		String ip = req.getRemoteAddr().replace(".", "");
		
		return csvc.javaCompile(java, className, methodName, context.getRealPath("/classpath/"+ip)); 
	}
}
