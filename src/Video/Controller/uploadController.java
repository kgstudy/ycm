package Video.Controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import Video.model.UploadService;

@Controller
public class uploadController {
	
	@Autowired
	UploadService us;
	
	
	@RequestMapping("/video/upload")
	public String qwer(){
		return "t:video/upload";
	}
	
	
	@RequestMapping("/video/upload/OK")
	public ModelAndView video(@RequestParam(name="file")MultipartFile file,String writer,String title, String content, HttpSession hs){
		ModelAndView mav = new ModelAndView(/*"redirect:/video"*/);
		
		String fileName = file.getOriginalFilename();
		
		String check = us.check(fileName);
		
		if(check.equals("O")){
			HashMap map = new HashMap<>();
			map.put("title", title);
			map.put("writer", writer);
			map.put("content", content);
			map.put("id",((HashMap)hs.getAttribute("login")).get("NAME"));
			System.out.println(((HashMap)hs.getAttribute("login")).get("NAME"));
			
			map.put("fileuuid", us.execute(file));
			map.put("filename", file.getOriginalFilename());
			System.out.println(file.getOriginalFilename());
			
			us.upload(map);
			mav.setViewName("t:video/uploadOK");
		}else{
			mav.setViewName("t:video/uploadFail");
		}
		
		return mav;
	}
}
