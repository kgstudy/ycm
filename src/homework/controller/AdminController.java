package homework.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import homework.service.HomeworkService;

@Controller
@RequestMapping("/hw/admin")
public class AdminController {
	@Autowired
	HomeworkService hws;
	
	@RequestMapping("/")
	public String admin(Map map){
		System.out.println("index");
		map.put("list", hws.read());
		return "t:hw/admin";
	}
	@RequestMapping("/write")
	public String homeworkWrite(String title, String content){
		Map map = new HashMap();
		map.put("title", title);
		map.put("content", content);
		hws.write(map);
		return "redirect:/homework/admin/read";
	}
	@RequestMapping("/read")
	public String homeworkRead(Map map){
		map.put("list", hws.read());
		return "/homework/read.jsp";
	}
}
