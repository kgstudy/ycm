package main.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import notice.model.NoticeService;
import question.model.QuestionService;

@Controller
public class MainController {
	@Autowired
	NoticeService ns;
	@Autowired
	QuestionService qs;

	@RequestMapping("/")
	public String index(){
//		return "t:index";
		return "/main/intro.jsp";
	}
	/*@RequestMapping("/notice")
	public ModelAndView notice(@RequestParam(defaultValue = "1") int p, @RequestParam(defaultValue = "5") int endp, String search){
		ModelAndView mav = new ModelAndView();
		if (search == null || search.equals("")) {
		List<HashMap> list = ns.noticeList();
			List<HashMap> list = ns.noticesearch("",p);
			int size = ns.getSPageSize("");
			int lastsize = ns.getSPageSize("");
			if(size > endp){
				size=endp;
			}
		mav.addObject("lastsize",lastsize);
		mav.addObject("size", size);
		mav.addObject("noticelist",list);
		mav.setViewName("t:notice/board");
		}else{
			List<HashMap> list = ns.noticesearch(search,p);
			int size = ns.getSPageSize(search);
			int lastsize = ns.getSPageSize(search);
			if(size > endp){
				size=endp;
			}
			mav.addObject("lastsize",lastsize);
			mav.addObject("size", size);
			mav.addObject("noticelist",list);
			mav.setViewName("t:notice/board");
			
		}
		return mav;
	}*/
	@RequestMapping("/notice")
	public ModelAndView noticelist(){
		ModelAndView mav = new ModelAndView();
		List<HashMap> list = ns.noticeList();
		mav.addObject("noticelist",list);
		mav.setViewName("t:notice/board");
		return mav;
	}
	
	
	@RequestMapping("/question")
	public ModelAndView question(){
		ModelAndView mav = new ModelAndView();
		List list = qs.qread();
		mav.addObject("qread", list);
		mav.setViewName("t:question/board");
		return mav;
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
