package homework.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import homework.model.HomeworkService;
import homework.model.StudentSourceService;
import homework.model.pojo.HomeworkPojo;

@Controller
@RequestMapping("/hw/student")
public class StudentController {
	@Autowired
	HomeworkService hwSvc;
	@Autowired
	StudentSourceService ssSvc;
	
	@RequestMapping("/read/{num}")
	public String homeworkRead(HttpSession session, Map map, @PathVariable int num){
		map.put("pojo", hwSvc.read(num));
		System.out.println("sctrl: "+session.getAttribute("id"));
		map.put("sourceInfo", ssSvc.sourceInfo((String)session.getAttribute("id")) );
		return "t:hw/default";
	}	
	
	@RequestMapping("/readJSON")
	@ResponseBody
	public HomeworkPojo homeworkReadJSON(int num){
		HomeworkPojo pojo = hwSvc.read(num);
		return pojo;
	}	
}
