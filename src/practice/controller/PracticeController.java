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
	
	@RequestMapping("/{class}/{content}")
	@ResponseBody
	public String content(@PathVariable(name="class")String cls, @PathVariable(name="content")String content){
		return ps.content(cls, content);
	}
}
