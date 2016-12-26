package homework.controller;

import java.util.HashMap;
import java.util.Map;

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
		map.put("pojo", aSvc.read(num));
		return "t:homework/default";
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
	
	@RequestMapping("/modify/answer")
	@ResponseBody
	public int homeworkmodiAnswer(Map map, String answer){
		
		return aSvc.modiAnswer(answer);
	}
	
	@RequestMapping("/modify/source")
	@ResponseBody
	public int homeworkmodiSource(Map map, String source){
		
		return aSvc.modiSource(source);
	}
}
