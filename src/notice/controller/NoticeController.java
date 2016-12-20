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
	
	// ���� ����Ʈ �Ѹ��°� 
	// ������ó��, ��������, �˻� ������ ������ ó��
	/*@RequestMapping("/notice")
	public ModelAndView noticelist(){
		ModelAndView mav = new ModelAndView();
		List<HashMap> list = ns.noticelist();
		mav.addObject("noticelist",list);
		mav.setViewName("t:notice/board");
		return mav;
	}*/

	@RequestMapping("/write") // �������� �۾��� ��ư �������� 
	public ModelAndView writeview(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("t:notice/write");
		return mav;
	}
	
	@RequestMapping("/input") //�۵���̴ϱ�... DB�� ����! 
	// ������ �̸� �ִ°� ���� ����
	public ModelAndView noticeinput(String title, String content){
		ModelAndView mav = new ModelAndView();
		boolean b = ns.noinput(title, content);
		if(b){
			mav.setViewName("redirect:/notice");
		}
		return mav;
	}
}
