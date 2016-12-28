package homework.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import homework.model.AdminService;
import homework.model.HomeworkService;
import homework.model.pojo.HomeworkPojo;

@Controller
@RequestMapping("/hw/admin")
public class AdminController {
	@Autowired
	AdminService aSvc;
	@Autowired
	HomeworkService hwSvc;
	
	@RequestMapping("/read/{num}")
	public String admin(Map map, @PathVariable int num){
		System.out.println("index");
		map.put("pojo", hwSvc.read(num));
		return "t:hw/default";
	}
	@RequestMapping("/writeForm")
	public String writeForm(){
		return "t:hw/default";
	}
	@RequestMapping("/write")
	@ResponseBody
	public int homeworkWrite(Map map, HomeworkPojo pojo){		
		return aSvc.write(pojo);
	}
	@RequestMapping("/modify")
	@ResponseBody
	public int homeworkModi(Map map, HomeworkPojo pojo){
		return aSvc.update(pojo);
	}
	
	@RequestMapping("/modify/answer")
	@ResponseBody
	public int homeworkModiAnswer(Map map, String answer){
		
		return aSvc.modiAnswer(answer);
	}	
	@RequestMapping("/modify/source")
	@ResponseBody
	public int homeworkModiSource(Map map, String source){
		
		return aSvc.modiSource(source);
	}
	@RequestMapping("/delete")
	@ResponseBody
	public int homeworkDelete(Map map, HttpServletRequest req, int num){		
		System.out.println(req.getContextPath());
		Enumeration e = req.getAttributeNames();
		while(e.hasMoreElements()){
			System.out.println("attr: "+e.nextElement());
		}
		System.out.println("param : "+req.getParameterMap());
		
		Enumeration e2 = req.getHeaderNames();
		while(e2.hasMoreElements()){
			System.out.println("header : "+e2.nextElement());
		}
		return aSvc.delete(num);
	}
}
