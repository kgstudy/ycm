package notice.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
		boolean b = ns.noticeInput(title, content);
		if(b){
			mav.setViewName("redirect:/notice");
		}
		return mav;
	}
	
	// ���� ���� �������� ���� �����ִ°� + ��ȸ�� �ø��°� 
	// ���⵵ ��������..... 
	@RequestMapping("/view/{num}")
	public ModelAndView noticeview(@PathVariable(name = "num") int num, HttpServletRequest req,
			HttpServletResponse resp){
		ModelAndView mav = new ModelAndView();
		Cookie[] ar = req.getCookies();
	      int n =0;
	      for(Cookie c : ar){
	         if(c.getName().equals("notice#"+num)){
	            n=1;
	            break;
	         }
	      }
	      if(n==0){
	         int upcount = ns.countup(num);  // ��ȸ�� �÷��ִ°�
	         Cookie cc = new Cookie("notice#"+num,"notice#"+num); // Ű,����
	         cc.setMaxAge(60); //60��
	         cc.setPath("/");
	         resp.addCookie(cc);
	      }
		mav.addObject("noticeview",ns.getOneByNum(num));
		mav.setViewName("t:notice/view");
		return mav;
	}
	
	// ���� �����ϴ� ���..
	@RequestMapping("/rewrite/{num}")
	public ModelAndView rewrite(@PathVariable(name = "num") int num){
		ModelAndView mav = new ModelAndView();
		mav.addObject("noticeview",ns.getOneByNum(num));
		mav.setViewName("t:notice/rewrite");
		return mav;
	}
	// ���� �����ϰ� �۵��.
	@RequestMapping("/reinput/{num}")
	public ModelAndView reinput(@PathVariable(name = "num") int num, String title, String content){
		ModelAndView mav = new ModelAndView();
		boolean b = ns.noticeUpdate(title, content, num);
		if(b){
			mav.setViewName("redirect:/notice");
		}
		return mav;
	}

}
