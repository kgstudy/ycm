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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import notice.model.CommentService;
import notice.model.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	NoticeService ns;
	@Autowired
	CommentService cs;
	
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
	
	// �������� ������ ����������
	@RequestMapping("/page/{p}/{size}")
	public ModelAndView page(@PathVariable(name = "p") int p,@PathVariable(name = "size") int endp){
		ModelAndView mav = new ModelAndView();
			List<HashMap> list = ns.noticesearch("",p);
			int size = ns.getSPageSize("");
			int lastsize = ns.getSPageSize("");
			if(size > endp){
				size=endp;
			}
		mav.addObject("lastsize",lastsize);
		mav.addObject("size", size);
		mav.addObject("noticelist",list);
		mav.setViewName("t:notice/board");
		
		return mav;
	}
	
	@RequestMapping("/search/{p}/{size}")
	public ModelAndView page(@PathVariable(name = "p") int p,@PathVariable(name = "size") int endp, String search){
		ModelAndView mav = new ModelAndView();
			List<HashMap> list = ns.noticesearch(search,p);
			int size = ns.getSPageSize(search);
			int lastsize = ns.getSPageSize(search);
			if(size > endp){
				size=endp;
			}
		mav.addObject("lastsize",lastsize);
		mav.addObject("size", size);
		mav.addObject("noticelist",list);
		mav.setViewName("t:notice/board");
		return mav;
	}
	
	// �������׿��� �˻�������.. �˻��ϰ� ������ ����������
	@RequestMapping("/search")
	public ModelAndView search(@RequestParam(defaultValue = "1") int p, @RequestParam(defaultValue = "5") int endp,String search){
		ModelAndView mav = new ModelAndView();
		List<HashMap> list = ns.noticesearch(search, p);
		int size = ns.getSPageSize(search);
		int lastsize = ns.getSPageSize(search);
		if(size > endp){
			size=endp;
		}
		mav.addObject("lastsize",lastsize);
		mav.addObject("size", size);
		mav.addObject("noticelist",list);
		mav.setViewName("t:notice/board");
		
		return mav;
	}
	
	@RequestMapping("/write") // �������� �۾��� ��ư �������� 
	public ModelAndView writeview(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("t:notice/write");
		return mav;
	}
	
	@RequestMapping("/input") //�۵���̴ϱ�... DB�� ����! 
	public ModelAndView noticeinput(String title, String content, String writer){
		ModelAndView mav = new ModelAndView();
		boolean b = ns.noticeInput(title, content, writer);
		if(b){
			mav.setViewName("redirect:/notice");
		}
		return mav;
	}
	
	// ���� ���� �������� ���� �����ִ°� + ��ȸ�� �ø��°� 
	// ��� ����Ʈ�� ���� �ѱ�.
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
	    mav.addObject("commentlist", cs.commentList(num));
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
	public ModelAndView reinput(@PathVariable(name = "num") int num, String title, String content, String writer){
		ModelAndView mav = new ModelAndView();
		boolean b = ns.noticeUpdate(title, content, num, writer);
		if(b){
			mav.setViewName("redirect:/notice");
		}
		return mav;
	}
	
	// �ۻ�����ư �������� 
	@RequestMapping("/delete/{num}")
	public ModelAndView delete(@PathVariable(name = "num") int num){
			ModelAndView mav = new ModelAndView();
			boolean b = ns.noticedelete(num);
			if(b){
				mav.setViewName("redirect:/notice");
			}else{
				mav.setViewName("redirect:/view/"+num);
			}
			
		return mav;
	}

}
