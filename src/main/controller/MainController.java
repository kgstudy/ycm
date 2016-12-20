package main.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import notice.model.NoticeService;

@Controller
public class MainController {
	@Autowired
	NoticeService ns;

	@RequestMapping("/")
	public String index(){
		return "t:index";
//		return "/main/intro.jsp";
	}
	@RequestMapping("/notice")
	public ModelAndView notice(){
		ModelAndView mav = new ModelAndView();
		List<HashMap> list = ns.noticeList();
		mav.addObject("noticelist",list);
		mav.setViewName("t:notice/board");
		return mav;
	}
	@RequestMapping("/question")
	public String question(){
		return "t:question/board";
	}
	@RequestMapping("/homework")
	public String homework(){
		return "t:homework/board";
	}
	@RequestMapping("/practice")
	public String practice(){
		return "t:practice/practice";
	}
	@RequestMapping("/storage")
	public String storage(){
		return "t:storage/board";
	}
	@RequestMapping("/video")
	public String video(){
		return "t:video/board";
	}
	@RequestMapping("/classes")
	public String classes(){
		return "t:classes/board";
	}
	@RequestMapping("/admin")
	public String admin(){
		return "t:admin/admin";
	}
}
