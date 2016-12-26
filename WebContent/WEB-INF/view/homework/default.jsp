<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<link href="/css/homework.css" rel="stylesheet" >
</head>
<style>
input[type="text"], textarea{	
	background-color : white;
	border: none;
	padding : 5px;
}
#porblemView{
	style : background-color: white;
}
div{
	margin : 0px;
	padding : 0px;
}
label{
	padding : 5px;
}
</style>

<div class = 'w3-container' style='margin : 0px; padding : 0px;'>
	<div id='problemViewWrap' class='w3-col l4 m3' >
		<form id='homeworkForm' >
			<input type='hidden' name='writer' value='${login.id }'>
			<input type='hidden' name='num' value='${map.num }' >
			 <label>제목 :</label><input id='title' type='text' size='10' name='title' value='${map.title }'
				readonly="readonly" required><br />
			<textarea id='problemView' name='content' readonly="readonly">
				${map.content }
			</textarea>
			<br />
			<section id='inputExam' class='exam' >
				<div class='headline'>입력 예제</div>
				<textarea class='examPre' readonly="readonly" name='input'>
					${map.input }
				</textarea>
			</section>
			<section id='outputExam' class='exam' >
				<div class='headline'>출력 예제</div>
					<textarea class='examPre' readonly="readonly" name='output'>
					${map.output }
				</textarea>
			</section>
		</form>
		<div id='rankView'></div>
	</div>
<div id='sourceCodeWrap' class='w3-col l5 m3' style='display : inline-block;'>
<form id='sourceForm' >
	
		<textarea id='sourceCode' name='content' cols='80' rows='20' style='margin: 0px'>
			class Homework{
				public static void main(String[] args){
					System.out.println("Hello! WebCoding~~");
				}
			}
		</textarea>	
		<br/>
		<input id='run' type='button' value='실행' >
		<input type='hidden' value='글쓰기' >
		<div id='consoleView' ></div>		
	
</form>
</div>
</div> <!-- container -->



<script>
	var lastRank = 0;
	var path = location.pathname.split("/");
	console.log(path);
	console.log(path[2]);
	$(document).ready(function(){
		console.log($("#headtemp").html());
		switch(path[2]){
		case "admin" :
			if (path[3] == 'writeForm') {
				$("textarea").attr("disabled", false).css("background-color", "white");
				$("#run").after("<input type='submit' value='글쓰기' >");
			}else if(path[3] == "read" ){
				$("#homeworkNav").append("<li id='modi' ><a>수정</a></li>")
					.append("<li id='delete' ><a>삭제</a></li>");			
// 				$("#modi").on("click", function(){
// 					if($("#modi > a").html()=="수정"){
// 						$("textarea").attr("readonly", false).css("background-color", "white");
// 						$("#title").attr("readonly", false);
						 
// 						$("#modi > a").html("완료");
// 					}else if($("#modi > a").html()=="완료"){
// 						$("#modi > a").html("수정");
// 						insertHomework(modify);
// 					}					
// 				});
			}
			break;
		case "student" :
			
			break;		
		default:
			break;
		}
		$("#homeworkNav li:gt(0)").on("click", function(){
			switch($(this).find("a").html()){
			case "수정":
				$("textarea", "input[type='text']").attr("readonly", false).css("background-color", "white")
					.css("border", "aqua");				 
					$("#modi > a").html("완료");
				break;
			case "완료":
				insertHomework("modify");
				break;
			case "삭제":
				insertHomework("delete");
				break;
			}
		});
	});
	

	$("#homeworkForm").submit(function(e) {
		insertHomework(write);
		e.preventDefault();
	});
	function insertHomework(insertType){
		var hwData;
		if(insertType=="modify"){
			hwData = $("#homeworkForm").serializeArray();
		}else if(insertType=="delete"){
			hwData = $("input[type='hidden'][name='num']").val();
		}
		console.log("hwdata : "+hwData);
		
		$.ajax({
			url : "/hw/admin/"+insertType,
			type : "post",
			async : false,
			data : 111,
			success : function(data) {
				if (data = 0)
					alert("입력 실패")
				else {
					alert("Success!!!");
					$("textarea", "input").attr("readonly", "readonly");
				}
			},
			error : function(data, source, err) {
				console.log(data);
				console.log(source);
				alert(err + "\n입력 실패하였습니다.");
			}
		});
	}
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

