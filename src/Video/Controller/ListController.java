package Video.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Video.model.VideoListService;

@Controller
public class ListController {
	@Autowired
	VideoListService vs;
	
	@RequestMapping("/video")
	public ModelAndView video(){
		ModelAndView mav = new ModelAndView("t:video/board");
		mav.addObject("list",vs.AllList());
		
		return mav;
		
		
	}
}
