package homework.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckAnswer {
	@Autowired
	CompileService cSvc;
	
	public Map check(String java, String className, String methodName, String classPath, String ip){
		String answer = cSvc.getAnswer(java, className, methodName, classPath+"/admin");
		String report = cSvc.javaCompile(java, className, methodName, classPath+"/"+ip);
		Map map = new HashMap();
		map.put("check", false);
		if(answer.equals(report)){
			map.put("check", true);
		}
		map.put("result", report);
		
		return map;
	}
	public Map compile(String java, String className, String methodName, String classPath, String ip){
		String answer = cSvc.javaCompile(java, className, methodName, classPath+"/admin");
		Map map = new HashMap();		
		map.put("result", answer);
		
		return map;
	}
}
