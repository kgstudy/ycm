package main.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

	@RequestMapping("/")
	public String index(){
		return "t:index";
//		return "/main/intro.jsp";
	}
	
	@RequestMapping("/notice")
	public String notice(){
		return "t:notice/board";
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
		return "t:practice/board";
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
		return "t:admin/board";
	}
}
