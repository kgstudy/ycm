<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link href="/css/homework.css" rel="stylesheet" >
<div id='problemView'>
HW student#<br/>
${list.CONTENT }
<%
	Enumeration e = request.getAttributeNames();
	while(e.hasMoreElements()){
		out.print(e.nextElement()+"\n");
	}
%>
a
${pageContext.request.remoteAddr }
${request }
</div>
<form id='homeworkForm' action='/homework/write' >
	<textarea id='homeworkSource' name='content' cols='100' rows='20' >		
		class Homework{
			public static void main(String[] args){
				System.out.println("Hello! WebCoding~~");
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
		sendHomework();		
	});
// handle <tab> in textarea
	$(document).delegate('#homeworkSource', 'keydown', function(e) {
	  var keyCode = e.keyCode || e.which;
	  console.log(keyCode);
	  if (keyCode == 9) {
	    e.preventDefault();
	    var start = $(this).get(0).selectionStart;
	    var end = $(this).get(0).selectionEnd;
	
	    // set textarea value to: text before caret + tab + text after caret
	    $(this).val($(this).val().substring(0, start)
	                + "\t"
	                + $(this).val().substring(end));
	
	    // put caret at right position again
	    $(this).get(0).selectionStart =
	    $(this).get(0).selectionEnd = start + 1;
	  }
	});
	
	function sendHomework(){
 		var $source = $("#homeworkSource").val(); 		
		
		$.ajax({
			url : "/homework/compile",
			type : "post",
			data : {
				"java" : $source,
				"ip" : "${header.host}"
			},
			success : function(r){
		 		$("#consoleView").empty();
		 		$("#consoleView").append(r+"<br/>");
		 		checkAnswer(r);
			}
		});
	}
	
	function checkAnswer(r){
		var rightAnswer = 5;
		if(r==rightAnswer){
			$("#consoleView").append("<span class='correct' >Correct!!!</span><br/>");
			recordRank();
		}else{
			$("#consoleView").append("<span class='incorrect' >incorrect..</span><br/>");
		}
	}
	
	function recordRank(){
		ws.send("aaa");
		$("#rankView").append((lastRank+1), " aaa", "<br/>");
		lastRank++;
	}
	
	
	
</script>

