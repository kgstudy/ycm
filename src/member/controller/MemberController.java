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
	
	@RequestMapping("/join")
	public String joinForm(){
		return "t:join";
	}
	
	@RequestMapping("/join/{id}/{password}/{name}/{phone}")
	@ResponseBody
	public boolean join(@PathVariable(name="id")String id, @PathVariable(name="password")String password, @PathVariable(name="name")String name,
									@PathVariable(name="phone")String phone, @RequestParam(name="email")String email){
		return ms.join(id, password, name, phone, email);
	}
	
	@RequestMapping("/login/{id}/{password}")
	@ResponseBody
	public boolean login(@PathVariable(name="id")String id, @PathVariable(name="password")String password, HttpSession session){
		List<HashMap> list = ms.login(id, password);
		if(list.size()!=0){
			session.setAttribute("id", id);
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping("/login")
	public ModelAndView endLogin(HttpSession session){
		ModelAndView mav = new ModelAndView("t:index");
		String id = (String)session.getAttribute("id");
		List<HashMap> list = ms.endLogin(id);
		mav.addObject("list", list);
		session.setAttribute("login", list.get(0));
		return mav;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("login");
		return "redirect:/";
	}
	
	@RequestMapping("/idcheck/{id}")
	@ResponseBody
	public boolean idCheck(@PathVariable(name="id")String id){
		return ms.idCheck(id);
	}
	
	@RequestMapping("/findId/{name}")
	@ResponseBody
	public boolean findId(@PathVariable(name="name")String name, @RequestParam(name="email")String email){
		return ms.findId(name, email);
	}
	
	@RequestMapping("/checkNum/{name}/{num}")
	@ResponseBody
	public String checkNum(@PathVariable(name="name")String name, @PathVariable(name="num")String num){
		return ms.checkNum(name, num);
	}
	
	@RequestMapping("/findPw/{id}")
	@ResponseBody
	public boolean findPw(@PathVariable(name="id")String id, @RequestParam(name="email")String email){
		return ms.findPw(id, email);
	}
	
	@RequestMapping("/checkNum2/{id}/{num}")
	@ResponseBody
	public boolean checkNum2(@PathVariable(name="id")String id, @PathVariable(name="num")String num){
		return ms.checkNum2(id, num);
	}
	
	@RequestMapping("/changePw/{id}/{pw}")
	@ResponseBody
	public boolean changePw(@PathVariable(name="id")String id, @PathVariable(name="pw")String pw){
		return ms.changePw(id, pw);
	}
}
