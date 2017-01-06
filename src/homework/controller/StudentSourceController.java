package homework.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import homework.model.StudentSourceService;
import sourceGallery.model.pojo.StudentSourcePojo;

@Controller
@RequestMapping("hw/source")
public class StudentSourceController {
	@Autowired
	StudentSourceService ssSvc;
	
	@RequestMapping("/report")
	@ResponseBody	
	public boolean sourceReport(HttpSession session, int num, String source){
		
		return ssSvc.write(num, source, (String)session.getAttribute("id"));
	}
	@RequestMapping("/read/{num }")
	public ModelAndView read(@PathVariable int num){
		ModelAndView mav = new ModelAndView("t:hw/source/read");
		mav.addObject("pojo", ssSvc.read(num));
		return mav;
	}
}
