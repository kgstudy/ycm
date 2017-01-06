package sourceGallery.controller;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sourceGallery.model.sourceGalleryService;

@Controller
@RequestMapping("/gallery")
public class SourceGalleryController {	
	@Autowired
	sourceGalleryService sgSvc;	
	
	@RequestMapping("/read/{num}")
	public String enterGallery(@PathVariable int num, Map map){
		System.out.println("enterGallery num: "+num);
		map.put("list", sgSvc.read(num));		
		return "/homework/gallery/board.jsp";
	}
	
}
