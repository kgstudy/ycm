package storage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import storage.model.StorageService;

@Controller
public class StorageController {
	
	@Autowired
	StorageService ss ;
	

	@RequestMapping("/storage/write")
	public ModelAndView write() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("t:storage/write");
		return mav;
	}
	
	@RequestMapping("storage/make")
	public ModelAndView make(String title, String content, HttpSession session, String category) {
		String id = (String) session.getAttribute("id");
		int r = ss.write(title, content, id, category);
		ModelAndView mav = new ModelAndView();
		mav.addObject("storagesessionid", r);
		mav.setViewName("redirect:/storage");

		return mav;

	}
	
	
}
