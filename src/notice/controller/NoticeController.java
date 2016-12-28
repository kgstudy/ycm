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
	
	// 공지에서 페이지 선택했을때
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
	
	// 공지사항에서 검색했을때.. 검색하고 페이지 선택했을때
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
	
	@RequestMapping("/write") // 공지사항 글쓰기 버튼 눌렀을때 
	public ModelAndView writeview(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("t:notice/write");
		return mav;
	}
	
	@RequestMapping("/input") //글등록이니까... DB에 저장! 
	public ModelAndView noticeinput(String title, String content, String writer){
		ModelAndView mav = new ModelAndView();
		boolean b = ns.noticeInput(title, content, writer);
		if(b){
			mav.setViewName("redirect:/notice");
		}
		return mav;
	}
	
	// 공지 제목 눌렀을때 내용 보여주는거 + 조회수 올리는거 
	// 댓글 리스트도 같이 넘김.
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
	         int upcount = ns.countup(num);  // 조회수 올려주는것
	         Cookie cc = new Cookie("notice#"+num,"notice#"+num); // 키,벨류
	         cc.setMaxAge(60); //60초
	         cc.setPath("/");
	         resp.addCookie(cc);
	      }
	    mav.addObject("commentlist", cs.commentList(num));
		mav.addObject("noticeview",ns.getOneByNum(num));
		mav.setViewName("t:notice/view");
		return mav;
	}
	
	// 공지 수정하는 뷰로..
	@RequestMapping("/rewrite/{num}")
	public ModelAndView rewrite(@PathVariable(name = "num") int num){
		ModelAndView mav = new ModelAndView();
		mav.addObject("noticeview",ns.getOneByNum(num));
		mav.setViewName("t:notice/rewrite");
		return mav;
	}
	
	// 공지 수정하고 글등록.
	@RequestMapping("/reinput/{num}")
	public ModelAndView reinput(@PathVariable(name = "num") int num, String title, String content, String writer){
		ModelAndView mav = new ModelAndView();
		boolean b = ns.noticeUpdate(title, content, num, writer);
		if(b){
			mav.setViewName("redirect:/notice");
		}
		return mav;
	}
	
	// 글삭제버튼 눌렀을때 
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
