package question.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import question.model.QreplyService;
import question.model.QuestionService;

@Controller
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	QuestionService qs;
	
	@Autowired
	QreplyService qr;
	
	//글 쓰기 페이지
	@RequestMapping("/write")
	public ModelAndView write() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("t:question/write");
		return mav;
	}

	//DB 저장
	@RequestMapping("/qwrite")
	public ModelAndView qwrite(String title, HttpSession login, String content, boolean check) {
		ModelAndView mav = new ModelAndView();
		boolean b = qs.qwrite(title, login, content, check);
		if(b) {
			mav.setViewName("redirect:/question");
		} else {
			mav.setViewName("redirect:/question");
		}
		return mav;
	}
	
	//글 읽기
	@RequestMapping("/writeBoard/{num}")
	public ModelAndView writeBoard(@PathVariable int num, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("t:question/writeBoard");
		HashMap map = qs.qBoard(num);
		List<HashMap> list1 = qr.qReply1(num);
		mav.addObject("qBoard", map);
		mav.addObject("qReply1", list1);
		if(session.getAttribute("login")!=null) {
			
		}
		return mav;
	}
}
