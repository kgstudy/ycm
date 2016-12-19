<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link href="/css/homework.css" rel="stylesheet" >
<div id='problemView'>
HW student#<br/>
${list.CONTENT }
</div>
<form id='homeworkForm' action='/homework/write' >
	<textarea id='homeworkContent' name='content' cols='100' rows='20' >		
		Class Homework{
			public static void main(String[] args){
				SW code
			}
		}
	</textarea>	
	<c:if test='false' >
		<textarea rows="7" cols="30"></textarea><br/>
	</c:if>	
<br/>
<input id='run' type='button' value='실행' >
<input type='hidden' value='글쓰기' >
</form>
<br/>
<div id='consoleView' ></div>
<div id='rankView' ></div>


<script>
	var lastRank = 0;
	
// WebSocket	
	var ws = new WebSocket("ws://${header.host}/homework/homesocket");
	ws.onopen = function(args){
		console.log("connect.");
	};
	ws.onmessage = function(e){
		console.log(e.data);
	}
// event handler	
	$("#run").on("click", function(){
		checkAnswer();		
	});
	
	function checkAnswer(){
		var $answer = $("#homeworkContent").val();
		console.log($answer);
		$("#consoleView").empty();
		$("#consoleView").append($answer+"<br/>");
		if($answer=="love"){
			$("#consoleView").append("<span class='correct' >Correct!!!</span><br/>");
			recordRank();
		}else{
			$("#consoleView").append("<span class='incorrect' >incorrect..</span><br/>");
		}
		
		$.ajax({
			url : "/homework/compile",
			type : "post",
			data : {
				"java" : "Class Homework{"+
					"public static void(String[] args){ System.out.println(\"Hello WebCoding!!\") }"+
					"}"
			}
		});
	}
	
	function recordRank(){
		ws.send("aaa");
		$("#rankView").append((lastRank+1), " aaa", "<br/>");
		lastRank++;
	}
	
	
	
</script>

