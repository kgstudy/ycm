package notice.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		if(b){
			mav.setViewName("redirect:/notice/view/"+num);
		}else{
			mav.addObject("commentfail", "commentfail");
		}
		
		return mav;
	}
	
	// ��� �����Ҷ�
	@RequestMapping("/recom/{content}/{num}")
	@ResponseBody
	public boolean comment(@PathVariable(name = "content") String content, @PathVariable(name = "num") int num){
		return cs.commentFinish(content, num);
	}
	
	

}
