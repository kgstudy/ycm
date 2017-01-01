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
	
	// ��� ����Ʈ
	@RequestMapping("/other/{p}/{category}")
	public ModelAndView other(@PathVariable(name="p")int p, @PathVariable(name="category")String category){
		ModelAndView mav = new ModelAndView("/admin/ajaxMem.jsp");
		mav.addObject("list", as.member(p, category));
		return mav;
	}

	// ���Կ�û ��� ����Ʈ
	@RequestMapping("/other/{p}")
	public ModelAndView other2(@PathVariable(name="p")int p){
		ModelAndView mav = new ModelAndView("/admin/ajaxMem2.jsp");
		mav.addObject("list2", as.joinMember(p));
		return mav;
	}
	
	// �׷�����
	@RequestMapping("/group/{ar}/{group}")
	@ResponseBody
	public boolean group(@PathVariable(name="ar")String[] ar, @PathVariable(name="group")String group){
		return as.group(ar, group);
	}
	
	// ���Կ�û ����
	@RequestMapping("/accept/{ar}")
	@ResponseBody
	public boolean accept(@PathVariable(name="ar")String[] ar){
		return as.accept(ar);
	}
	
	// ��� ������
	@RequestMapping("/size/{category}")
	@ResponseBody
	public int size(@PathVariable(name="category")String category){
		return as.memberSize(category);
	}
	
	// �� ȸ����
	@RequestMapping("/total/{category}")
	@ResponseBody
	public int total(@PathVariable(name="category")String category){
		if(category.equals("�� class ��")){
			return as.size2();
		} else {
			return as.size();
		}
	}
	
	// �޴� ��ġ ����
	@RequestMapping("/menu/{menu}/{arrow}")
	public ModelAndView menu(@PathVariable(name="menu")String menu, @PathVariable(name="arrow")String arrow){
		ModelAndView mav = new ModelAndView("/admin/ajaxMenu.jsp");
		mav.addObject("menu", as.menu(menu, arrow));
		return mav;
	}
	
	// �޴� �߰�
	@RequestMapping("/menu/new/{name}")
	public ModelAndView newMenu(@PathVariable(name="name")String name){
		ModelAndView mav = new ModelAndView("/admin/ajaxMenu.jsp");
		mav.addObject("menu", as.newMenu(name));
		return mav;
	}
	
	// �޴� ����Ʈ
	@RequestMapping("/menu")
	@ResponseBody
	public List<String> menuList(){
		return as.menuList();
	}
	
	// �޴� ����
	@RequestMapping("/removeMenu/{name}")
	public ModelAndView removeMenu(@PathVariable(name="name")String name){
		ModelAndView mav = new ModelAndView("/admin/ajaxMenu.jsp");
		mav.addObject("menu", as.removeMenu(name));
		return mav;
	}
	
	// class ����Ʈ
	@RequestMapping("/class")
	@ResponseBody
	public List<String> classList(){
		return as.classList();
	}
	
	// class ��ġ ����
	@RequestMapping("/class/{menu}/{arrow}")
	public ModelAndView classPosition(@PathVariable(name="menu")String menu, @PathVariable(name="arrow")String arrow){
		ModelAndView mav = new ModelAndView("/admin/ajaxClass.jsp");
		mav.addObject("classes", as.classPosition(menu, arrow));
		return mav;
	}
}
