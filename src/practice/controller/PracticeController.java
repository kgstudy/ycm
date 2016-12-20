package practice.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import practice.model.*;

@Controller
@RequestMapping("/practice")
public class PracticeController {
	@Autowired
	PracticeService ps;
	
//	@RequestMapping("/{content}")
//	@ResponseBody
//	public String content(@PathVariable(name="content")String content){
//		return ps.content(content);
//	}
	@RequestMapping("/run/{title}")
	@ResponseBody
	public String run(@RequestBody()String content, @PathVariable(name="title")String title){
		return ps.content(content, title);
	}
}
