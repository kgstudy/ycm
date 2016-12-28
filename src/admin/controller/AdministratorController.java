package admin.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import admin.model.*;

@Controller
@RequestMapping("/admin")
public class AdministratorController {
	@Autowired
	AdministratorService as;
	
	@RequestMapping("/other/{p}/{category}")
	public ModelAndView other(@PathVariable(name="p")int p, @PathVariable(name="category")String category){
		ModelAndView mav = new ModelAndView("/admin/ajaxMem.jsp");
		mav.addObject("list", as.member(p, category));
		return mav;
	}

	@RequestMapping("/other/{p}")
	public ModelAndView other2(@PathVariable(name="p")int p){
		ModelAndView mav = new ModelAndView("/admin/ajaxMem2.jsp");
		mav.addObject("list2", as.joinMember(p));
		return mav;
	}
	
	@RequestMapping("/group/{ar}/{group}")
	@ResponseBody
	public boolean group(@PathVariable(name="ar")String[] ar, @PathVariable(name="group")String group){
		return as.group(ar, group);
	}
	
	@RequestMapping("/accept/{ar}")
	@ResponseBody
	public boolean accept(@PathVariable(name="ar")String[] ar){
		return as.accept(ar);
	}
}
