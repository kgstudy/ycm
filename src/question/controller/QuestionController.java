package question.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import question.model.QuestionService;

@Controller
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	QuestionService qs;
	
	//글 쓰기 페이지
	@RequestMapping("/write")
	public ModelAndView write() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("t:question/write");
		return mav;
	}

	//DB 저장
	@RequestMapping("/qwrite")
	public ModelAndView qwrite(String title, String content, boolean check) {
		ModelAndView mav = new ModelAndView();
		boolean b = qs.qwrite(title, content, check);
		if(b) {
			mav.setViewName("redirect:/question");
		} else {
			mav.setViewName("redirect:/question");
		}
		return mav;
	}
	
	//글 읽기
	@RequestMapping("/writeBoard")
	public ModelAndView writeBoard() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("t:question/writeBoard");
		return mav;
	}
}
