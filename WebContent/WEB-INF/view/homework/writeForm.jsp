<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link href="/css/homework.css" rel="stylesheet" >

<form id='homeworkForm' action='/homework/admin/write' >	
	<div id='problemViewWrap' style='float: left;' >
		<div id='problemView' ></div>	
		<section id='inputExam' class='exam' style='float: left;'>
			<div class='headline' >출력 예제</div>
			<pre class='examPre' ></pre>
		</section>
		<section id='outputExam' class='exam' style='float: left;'>
			<div class='headline' >입력 예제</div>
			<pre class='examPre' ></pre>
		</section>	
	</div>
<br/>
<div class='clearfix' ></div>
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
//Event Handler	
	$("#run").on("click", function(){
		checkAnswer();		
	});	
	
	function recordRank(){
		$("#rankView").append((lastRank+1), " aaa", "<br/>");
		lastRank++;
	}	
	
</script>

