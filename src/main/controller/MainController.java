package main.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import admin.model.*;
import homework.model.HomeworkService;
import notice.model.NoticeService;
import question.model.QuestionService;
import storage.model.StorageService;

@Controller
public class MainController {
	@Autowired
	NoticeService ns;
	@Autowired
	QuestionService qs;
	@Autowired
	HomeworkService hwSvc;
	@Autowired
	StorageService ss;
	@Autowired
	AdministratorService as;

	@RequestMapping("/")
	public String index(){
//		return "t:index";
		return "/main/intro.jsp";
	}
	/*@RequestMapping("/notice")
	public ModelAndView notice(@RequestParam(defaultValue = "1") int p, @RequestParam(defaultValue = "5") int endp, String search){
		ModelAndView mav = new ModelAndView();
		if (search == null || search.equals("")) {
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
	
	// 5개씩 찍어주는거랑..  페이지 사이즈... 
	@RequestMapping("/notice")
	public ModelAndView noticelist(@RequestParam(defaultValue = "1") int p, @RequestParam(defaultValue = "5") int endp){
		ModelAndView mav = new ModelAndView();
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
	public String homework(Map map){
		map.put("list", hwSvc.list());
		return "t:homework/board";
	}
	@RequestMapping("/practice")
	public String practice(){
		return "t:practice/practice";
	}
	@RequestMapping("/storage")
	public ModelAndView storageList(@RequestParam(defaultValue = "1") int p, @RequestParam(defaultValue = "") String mode,
			@RequestParam(defaultValue = "") String search) {
		ModelAndView mav = new ModelAndView();
		if (mode.equals("")) {
			if (search.equals("")) {
				List list = ss.GetRnage(p);
				int size = ss.size();
				if (size >= 5) {
					if (p - 2 < 1) {
						size = 3;
					} else if (p + 2 > size) {
						size = size - 2;
					} else {
						size = p;
					}
				} else {
					size = size;
				}
				int bestsize = ss.size();
				mav.addObject("storagebestsize", bestsize);
				mav.addObject("storagedata", list);
				mav.addObject("storagesize", size);
				mav.addObject("storagesetlist", p);
				mav.setViewName("t:storage/board");
				return mav;
			} else {
				List list = ss.searchstorage(search, p);
				int size = ss.searchstoragesize(search);
				if (size >= 5) {
					if (p - 2 < 1) {
						size = 3;
					} else if (p + 2 > size) {
						size = size - 2;
					} else {
						size = p;
					}
				} else {
					size = size;
				}
				int bestsize = ss.searchstoragesize(search);
				mav.addObject("storagebestsize", bestsize);
				mav.addObject("storagedata", list);
				mav.addObject("storagesize", size);
				mav.addObject("storagesearch", search);
				mav.setViewName("t:storage/board");
				return mav;
			}
		} else {
			if (search.equals("")) {
				List list = ss.GetMode(p, mode);
				int size = ss.casize(mode);
				if (size >= 5) {
					if (p - 2 < 1) {
						size = 3;
					} else if (p + 2 > size) {
						size = size - 2;
					} else {
						size = p;
					}
				} else {
					size = size;
				}
				int bestsize = ss.casize(mode);
				mav.addObject("storagebestsize", bestsize);
				mav.addObject("storagedata", list);
				mav.addObject("storagesize", size);
				mav.addObject("storagemode", mode);
				mav.setViewName("t:storage/board");
				return mav;
			} else {
				List list = ss.searchstoragemode(search, p, mode);
				int size = ss.searchstoragesizemode(search, mode);
				if (size >= 5) {
					if (p - 2 < 1) {
						size = 3;
					} else if (p + 2 > size) {
						size = size - 2;
					} else {
						size = p;
					}
				} else {
					size = size;
				}
				int bestsize = ss.searchstoragesizemode(search, mode);
				mav.addObject("storagebestsize", bestsize);
				mav.addObject("storagedata", list);
				mav.addObject("storagesize", size);
				mav.addObject("storagesearch", search);
				mav.addObject("storagemode", mode);
				mav.setViewName("t:storage/board");
				return mav;
			}
		}
	}

	/*@RequestMapping("/video")
	public String video(){
		return "t:video/board";
	}*/
	@RequestMapping("/classes")
	public String classes(){
		return "t:classes/board";
	}
	@RequestMapping("/admin")
	public ModelAndView admin(){
		ModelAndView mav = new ModelAndView("t:admin/admin");
		List<HashMap> list = as.member(1, null);
		List<HashMap> list2 = as.joinMember(1);
		int size = as.memberSize(null);
		int size2 = as.joinMemSize();
		int total = as.size();
		mav.addObject("list", list);
		mav.addObject("list2", list2);
		mav.addObject("size", size);
		mav.addObject("size2", size2);
		mav.addObject("total", total);
		List<String> menu = as.menu(null, null);
		mav.addObject("menu", menu);
		return mav;
	}
}
