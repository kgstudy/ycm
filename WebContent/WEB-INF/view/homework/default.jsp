<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link href="/css/homework.css" rel="stylesheet" >

<form id='homeworkForm' action='/hw/admin/write' >
	<input type='hidden' name='writer' value='test' >
	<div id='problemViewWrap' style='float: left;' >
		제목 : <input type='text' size='30' name='title'><br/>
		<textarea id='problemView' name='content' disabled="disabled" ></textarea><br/>
		<section id='inputExam' class='exam' style='float: left;'>
			<div class='headline' >입력 예제</div>
			<textarea class='examPre' disabled="disabled" name='input'></textarea>
		</section>
		<section id='outputExam' class='exam' style='float: left;'>
			<div class='headline' >출력 예제</div>
			<textarea class='examPre' disabled="disabled" name='output' ></textarea>
		</section>
	</div>
	<br/>
	<div class='clearfix' ></div>
	<input id='run' type='button' value='실행' >
</form>
<br/>
<div id='consoleView' ></div>
<div id='rankView' ></div>


<script>
	var lastRank = 0;
	var path = location.pathname.split("/");
	if(path[3]=='writeForm'){
		console.log(path);
		$("textarea").attr("disabled", false).css("background-color", "white");
		$("#run").after("<input type='submit' value='글쓰기' >");
	}
	
	$("#homeworkForm").submit(function(e){
		$.ajax({
			url : "/hw/admin/write",
			type : "post",
			data : $("homeworkForm").serializeArray(),
			success : function(data){
				if(data=0)
					alert("입력 실패")
				else{
					location.href='/hw/read/'+data;
				}				
			},
			error : function(data,source, err){
				console.log(data);
				console.log(source);
				alert(err+"\n입력 실패하였습니다.");				
			}
		});
		e.preventDefault();
	});
	
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

