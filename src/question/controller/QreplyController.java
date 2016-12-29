package question.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import question.model.QreplyService;

@Controller
@RequestMapping("/question")
public class QreplyController {
	@Autowired
	QreplyService qr;
	
	//´ñ±Û ÀÛ¼º
	@RequestMapping("/reply1")
	public String qReplyIs(String qrwriter, String qrcontent, String qnum) {
		HashMap<String, String> map = new HashMap<>();
		map.put("qnum", qnum);
		map.put("qrwriter", qrwriter);
		map.put("qrcontent", qrcontent);
		qr.qReplyIs(map);
		return "redirect:/question//writeBoard/"+qnum;
	}
	
	//´ñ±Û ¼öÁ¤
	@RequestMapping("/upReply1")
	@ResponseBody
	public boolean upReply1(String qrcontent, String qrnum) {
		HashMap<String, String> map = new HashMap<>();
		map.put("qrcontent", qrcontent);
		map.put("qrnum", qrnum);
		boolean ur = qr.upReply1(map)==true? true : false;
		return ur;
	}
}
