package admin.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import admin.model.*;

@Controller
@RequestMapping("/admin")
public class AdministratorController {
	@Autowired
	AdministratorService as;
	
	@RequestMapping("/other")
	@ResponseBody
	public List<HashMap> other(int p, String category){
		return as.member(p, category);
	}
}
