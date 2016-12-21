package member.controller;

import java.util.*;

import javax.annotation.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import member.service.*;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberService ms;
	
	@RequestMapping("/joinForm")
	public String joinForm(){
		return "t:join";
	}
	
	@RequestMapping("/join/{id}/{password}/{name}/{phone}/{email}")
	public boolean join(@PathVariable(name="id")String id, @PathVariable(name="password")String password, @PathVariable(name="name")String name,
									@PathVariable(name="phone")String phone, @PathVariable(name="email")String email){
		return ms.join(id, password, name, phone, email);
	}
	
	@RequestMapping("/login/{id}/{password}")
	@ResponseBody
	public boolean login(@PathVariable(name="id")String id, @PathVariable(name="password")String password){
		List<HashMap> list = ms.login(id, password);
		if(list.size()!=0){
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping("/login/{id}")
	public ModelAndView endLogin(@PathVariable(name="id")String id, HttpSession session){
		System.out.println("aa");
		ModelAndView mav = new ModelAndView("t:index");
		List<HashMap> list = ms.endLogin(id);
		mav.addObject("list", list);
		session.setAttribute("login", list);
		return mav;
	}
}
