package main.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

	@RequestMapping("/")
	public String index(){
		return "t:index";
	}
}
