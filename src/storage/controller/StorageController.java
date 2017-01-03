package storage.controller;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.*;

import storage.model.*;

@Controller
@RequestMapping("/storage")
public class StorageController {
	
	@Autowired
	StorageService ss ;
	
	@Autowired
	FileUploadService fus;
	
	@Autowired
	FileDownService fds;
	

	@RequestMapping("/write")
	public ModelAndView write() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("t:storage/write");
		return mav;
	}
	
	@RequestMapping("/make")
	public ModelAndView make(@RequestParam(name="content")MultipartFile file, String title, HttpSession session, String category) {
		String id = (String) session.getAttribute("id");
//		int r = ss.write(title, content, id, category);
		ModelAndView mav = new ModelAndView();
		String result = fus.upload(file);
		ss.write(title, result, id, category);
		mav.setViewName("redirect:/storage");
		return mav;
	}
	
	@RequestMapping("/down")
	public ModelAndView down(@RequestParam(name="url")String url){
		ModelAndView mav = new ModelAndView("fileDownService");
		mav.addObject("url", url);
		return mav;
	}
	
	@RequestMapping("/count")
	@ResponseBody
	public boolean count(@RequestParam(name="title")String title){
		return ss.count(title);
	}
}
