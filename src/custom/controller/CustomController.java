package custom.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
@RequestMapping("/custom")
public class CustomController {
	
	@RequestMapping("/{menu}")
	public ModelAndView customMenu(@PathVariable(name="menu")String menu){
		ModelAndView mav = new ModelAndView("/custom/custom.jsp");
		return mav;
	}
}
