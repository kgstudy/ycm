package storage.controller;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.*;

import storage.model.*;

@Controller
public class StorageController {
	
	@Autowired
	StorageService ss ;
	
	@Autowired
	FileUploadService fus;
	

	@RequestMapping("/storage/write")
	public ModelAndView write() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("t:storage/write");
		return mav;
	}
	
	@RequestMapping("storage/make")
	public ModelAndView make(@RequestParam(name="content")MultipartFile file, String title, HttpSession session, String category) {
		String id = (String) session.getAttribute("id");
//		int r = ss.write(title, content, id, category);
		fus.upload(file);
		ModelAndView mav = new ModelAndView();
//		mav.addObject("storagesessionid", r);
		mav.setViewName("redirect:/storage");

		return mav;

	}
	
	
}
