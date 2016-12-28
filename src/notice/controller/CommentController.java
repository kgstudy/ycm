package notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import notice.model.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired 
	CommentService cs;
	// ��� ��� ��ư ��������....
	// notice_num�� ���� �Ϸο�..
	@RequestMapping("/input")
	public ModelAndView input(@RequestParam(name = "num") int num,String writer,String content){
		ModelAndView mav = new ModelAndView();
		boolean b = cs.commtentInput(num, content, writer);
		System.out.println(b);
		mav.setViewName("t:notice/board");
		
		return mav;
	}

}
