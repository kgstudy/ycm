package question.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/question")
public class QuestionController {

	@RequestMapping("/write")
	public ModelAndView write() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("t:question/write");
		return mav;
	}
}
