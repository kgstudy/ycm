package homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import homework.model.StudentSourceService;
import homework.model.pojo.StudentSourcePojo;

@Controller
@RequestMapping("hw/source")
public class StudentSourceController {
	@Autowired
	StudentSourceService ssSvc;
	
	@RequestMapping("/write")
	@ResponseBody
	public boolean insert(StudentSourcePojo pojo){		
		return ssSvc.write(pojo);
	}
	@RequestMapping("/read/{num }")
	public ModelAndView read(@PathVariable int num){
		ModelAndView mav = new ModelAndView("t:hw/source/read");
		mav.addObject("pojo", ssSvc.read(num));
		return mav;
	}
}
