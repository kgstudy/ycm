package question.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import question.model.QreplyService;

@Controller
@RequestMapping("/question")
public class QreplyController {
	@Autowired
	QreplyService qr;
}
