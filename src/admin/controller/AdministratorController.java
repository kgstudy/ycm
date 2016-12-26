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
	
	@RequestMapping("/other/{p}/{category}")
	@ResponseBody
	public List<HashMap> other(@PathVariable(name="p")int p, @PathVariable(name="category")String category){
		return as.member(p, category);
	}
	
	@RequestMapping("/group/{ar}/{group}")
	@ResponseBody
	public boolean group(@PathVariable(name="ar")String[] ar, @PathVariable(name="group")String group){
		return as.group(ar, group);
	}
	
	@RequestMapping("/accept/{ar}")
	@ResponseBody
	public boolean accept(@PathVariable(name="ar")String[] ar){
		return as.accept(ar);
	}
}
