package homework.controller;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import homework.model.CheckAnswer;
import homework.model.CompileService;

@Controller
@RequestMapping("/hw")
public class SourceCompileController {
	@Autowired
	CompileService cSvc;
	@Autowired
	CheckAnswer ca;
	@Autowired
	ServletContext context;	
	
	@RequestMapping("/student/compile")
	@ResponseBody
	public Map studentCompile(HttpSession session, HttpServletRequest req, String java, String className, String methodName){
		String ip = req.getRemoteAddr().replace(".", "");		
		return ca.check(java, className, methodName, context.getRealPath("/classpath"), ip);
	}
	@RequestMapping("/admin/compile")
	@ResponseBody
	public Map adminCompile(HttpSession session, HttpServletRequest req, String java, String className, String methodName){
		String ip = req.getRemoteAddr().replace(".", "");		
		return ca.compile(java, className, methodName, context.getRealPath("/classpath"), ip);
	}
}
