package notice.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import notice.model.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	NoticeService ns;
	
	// 공지 리스트 뿌리는거 
	// 페이지처리, 이전다음, 검색 누르고 페이지 처리
	/*@RequestMapping("/notice")
	public ModelAndView noticelist(){
		ModelAndView mav = new ModelAndView();
		List<HashMap> list = ns.noticelist();
		mav.addObject("noticelist",list);
		mav.setViewName("t:notice/board");
		return mav;
	}*/

	@RequestMapping("/write") // 공지사항 글쓰기 버튼 눌렀을때 
	public ModelAndView writeview(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("t:notice/write");
		return mav;
	}
	
	@RequestMapping("/input") //글등록이니까... DB에 저장! 
	// 관리자 이름 넣는거 아직 안함
	public ModelAndView noticeinput(String title, String content){
		ModelAndView mav = new ModelAndView();
		boolean b = ns.noinput(title, content);
		if(b){
			mav.setViewName("redirect:/notice");
		}
		return mav;
	}
}
