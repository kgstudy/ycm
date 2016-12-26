<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link href="/css/homework.css" rel="stylesheet" >

<form id='homeworkForm' action='/hw/admin/write' >
	<input type='hidden' name='writer' value='test' >
	<div id='problemViewWrap' style='float: left;' >
		제목 : <input type='text' size='30' name='title' value='${map.title }' disabled required><br/>
		<textarea id='problemView' name='content' disabled="disabled" >
			${map.content }
		</textarea><br/>
		<section id='inputExam' class='exam' style='float: left;'>
			<div class='headline' >입력 예제</div>
			<textarea class='examPre' disabled="disabled" name='input'>
				${map.input }
			</textarea>
		</section>
		<section id='outputExam' class='exam' style='float: left;'>
			<div class='headline' >출력 예제</div>
			<textarea class='examPre' disabled="disabled" name='output' >
				${map.output }
			</textarea>
		</section>
	</div>
	<br/>
	<div class='clearfix' ></div>
	<input id='run' type='button' value='실행' >
</form>
<br/>
<form id='sourceForm' >
	<textarea id='sourceCode' name='content' cols='100' rows='20' >		
		class Homework{
			public static void main(String[] args){
				System.out.println("Hello! WebCoding~~");
			}
		}
	</textarea>	
	<br/>
	<input id='run' type='button' value='실행' >
	<input type='hidden' value='글쓰기' >
</form>
<div id='consoleView' ></div>
<div id='rankView' ></div>


<script>
	var lastRank = 0;
	var path = location.pathname.split("/");
	console.log(path);

	switch(path[2]){
	case "admin" :
		if (path[3] == 'writeForm') {
			$("textarea").attr("disabled", false).css("background-color", "white");
			$("#run").after("<input type='submit' value='글쓰기' >");
		}else if(path[3] == "read" ){
			$("#run").after("<input id='modi' type='button' value='수정' >");
			$("#modi").on("click", function(){
				$("textarea").attr("disabled", false).css("background-color", "white");
				$("#run").after("<input type='submit' value='글쓰기' >");
			});
		}
		break;
	case "student" :
		
		break;		
	default:
		break;
	}
	

	$("#homeworkForm").submit(function(e) {
		$.ajax({
			url : "/hw/admin/write",
			type : "post",
			data : $("homeworkForm").serializeArray(),
			success : function(data) {
				if (data = 0)
					alert("입력 실패")
				else {
					location.href = '/hw/read/' + data;
				}
			},
			error : function(data, source, err) {
				console.log(data);
				console.log(source);
				alert(err + "\n입력 실패하였습니다.");
			}
		});
		e.preventDefault();
	});

	// handle <tab> in textarea
	$(document).delegate('#sourceCod', 'keydown', function(e) {
	  var keyCode = e.keyCode || e.which;
	  console.log(e.keycode);
	  console.log(e.which);
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
	
	function checkAnswer(){
 		var $answer = $("#sourceCode").val();
// 		$("#consoleView").empty();
// 		$("#consoleView").append($answer+"<br/>");
// 		if($answer=="love"){
// 			$("#consoleView").append("<span class='correct' >Correct!!!</span><br/>");
// 			recordRank();
// 		}else{
// 			$("#consoleView").append("<span class='incorrect' >incorrect..</span><br/>");
// 		}
		
		$.ajax({
			url : "/hw/compile",
			type : "post",
			data : {
				"java" : $answer
			},
			success : function(r){
		 		$("#consoleView").empty();
		 		$("#consoleView").append(r+"<br/>");
			}
		});
	}
	
	function recordRank(){
		ws.send("aaa");
		$("#rankView").append((lastRank+1), " aaa", "<br/>");
		lastRank++;
	}
	
	// WebSocket	
	var ws = new WebSocket("ws://${header.host}/homework/homesocket");
	ws.onopen = function(args) {
		console.log("connect.");
	};
	ws.onmessage = function(e) {
		console.log(e.data);
	}
	//Event Handler	
	$("#run").on("click", function() {
		checkAnswer();
	});
	
</script>

