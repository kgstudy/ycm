package homework.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import homework.service.HomeworkService;

@Controller
@RequestMapping("/homework/student")
public class StudentController {
	@Autowired
	HomeworkService hws;
	
	@RequestMapping("/writeForm")
	public String homeworkWriteForm(){
		return "/homework/writing.jsp";
	}
	@RequestMapping("/title")
	public String homeworkWrite(Map map){		
		map.putAll(hws.read());		
		return "t:hw/student";
	}
	@RequestMapping("/read")
	public String homeworkRead(Map map){
		map.put("list", hws.read());
		return "/homework/read.jsp";
	}
	
	@RequestMapping("/readJSON")
	@ResponseBody
	public Map homeworkReadJSON(){
		Map map = hws.read();
		return map;
	}	
}
