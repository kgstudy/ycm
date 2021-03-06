<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="/css/homework.css" rel="stylesheet" >
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/9.9.0/styles/default.min.css">
	<!-- 	<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/9.9.0/highlight.min.js"></script> 
	<script> 
 		hljs.initHighlightingOnLoad();		
 	</script> -->
<!--  	<script src="https://cdn.rawgit.com/google/code-prettify/master/loader/run_prettify.js?lang=java&amp;skin=doxy"></script>	 -->
	<link rel="stylesheet" href="/css/prettify.css" >
	<script src="/js/prettify.js" ></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ace.js"></script>
</head>
<style>
#editor { 
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        font-size: 14pt;
}
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
section{
	border: 1px solid gray;	
}

label{
	padding : 5px;
}
</style>

<div class = 'w3-container w3-row' style='margin : 0px; padding : 0px;'>
	<form id='homeworkForm' >
	<div id='problemViewWrap' class='w3-col l5 m6' >
			
			<input type='submit' style="display: none; " >
			<input type='hidden' name='writer' value='${login.ID }' required >
			<input type='hidden' name='loginClass' value='${login.CLASS }'>			
			<input type='hidden' name='num' value='${pojo.num }' >
			<input type="hidden" value='${pojo.answer }'>
			
			 <label>제목 :</label><input id='title' type='text' name='title' value='${pojo.title }'
				readonly="readonly" required ><br />	
			
			<div id="starEmpty" style="width: 80; overflow: hidden; 
				background-image: url('/image/empty stars.jpg');
					background-size: 80px 15px;" >
				<div id="starScore" style="width: ${(80/5)*pojo.adminLevel }; height: 15; display: inline-block; 
					background-image: url('/image/5 stars.jpg');
					background-size: 80px 15px;" >
					<input id="adminLevel" type="text" name="adminLevel" required value="${pojo.adminLevel }" 
				 	style="height 0px; opacity: 0; position: absolute;" >
				</div>
			</div>
			
			<textarea id='problemView' name='content' readonly="readonly" style='width: 100%' required
			 >${pojo.content }</textarea>
			<br />
			<section id='inputExam' class='w3-col l6 m12' >
				<div class='headline'>입력 예제</div>
				<textarea id='inputText' class='examPre' readonly="readonly" name='input'
				>${pojo.input }</textarea>
			</section>
			<section id='outputExam' class='w3-col l6 m12' >
				<div class='headline'>출력 예제</div>
					<textarea id='outputText' class='examPre' readonly="readonly" name='output'
					>${pojo.output }</textarea>
			</section>
			<br/>
		<div id='rankView' class='w3-col l12' ></div>
	</div>
	
<!-- SourceCode -->	
	<div id='sourceCodeWrap' class='w3-col l7 m6' >
		<label>Class Name &nbsp;&nbsp; : </label>
		<input id='className' type="text" name="className" readonly="readonly" required value="${pojo.className }" ><br/>
		<label>Method Name : </label>
		<input id='methodName' type="text" name="methodName" readonly="readonly"
			 value="${pojo.methodName }" style="margin-left: -2px;">			 	
<!-- 		<textarea id="sourceCode" cols='80' rows='50' -->
<%-- 		>${pojo.source }		 --%>
<!-- 		</textarea>		 -->
<!-- 		<div>			 -->
<!-- 			<pre id="sourceCode" class="prettyprint linenums:1" contenteditable="true" style="height: 58%;" -->
<%-- 			>${fn:replace(pojo.source, '<', '&lt;') }</pre> --%>
<!-- 		</div> -->
		<div id='editor' style="widhth: 300px; height: 400px; position: relative;"
		>${fn:replace(pojo.source, '<', '&lt;') }
		</div>
		<div id='consoleView' ></div>
		<div id='controllBar' align='center' >			
			<nav class="navbar navbar-inverse" style='margin : 0px;'>
			  <div class="container-fluid">
			    <div class="navbar-header" style="border-bottom: 2px solid #000000">						
						<a id="gallery" class="navbar-brand" href="#" >소스 갤러리</a>
				</div>
				<div class="navbar-nav" id="myNavbar">
				    <ul id='homeworkNav' class="nav navbar-nav">
				      <li class="active"><a id="run" href="#">실 행</a></li>
				      <li class="active"><a id="report" href="#">제 출</a></li>
				    </ul>
				</div>
			  </div>
			</nav>
		</div>	
	</div>
</form> <!-- homeworkForm -->
</div> <!-- container -->

<!-- Modal dialog -->
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-sm">    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">경 고</h4>
        </div>
        <div class="modal-body">
          <p>정말 삭제 하시겠습니까?</p>
        </div>
        <div class="modal-footer" >
          <button id="deleteConfirm" type="button" class="btn btn-danger" >Delete</button>
          <button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
        </div>
      </div>
      
    </div>
</div><!-- Modal -->


<script>
	var editor;
	var lastRank = new Number(0);	
	
	var path = location.pathname.split("/");
	var auth = path[2];
	console.log(path);
	console.log(path[2]);
	console.log()
	$(document).ready(function(){
		console.log($("#headtemp").html());
		switch(auth){
		case "admin" :
			if (path[3] == 'writeForm') {
				$("input[type='hidden'][name='num']").removeAttr("name");
				$("textarea, input[type='text']").attr("readonly", false).css("background-color", "white")
					.css("border", "1px solid aqua");
				$("#run").after("<input id='regist' type='button' value='글쓰기' >");
				$("#starEmpty").on("click", function(e){
					starScore(e);
				});				
				$("#regist").click(function(e) {
					insertHomework("write");					
				});
			}else if(path[3] == "read" ){
				$("#homeworkNav").append("<li id='modi' ><a>수정</a></li>")
					.append("<li id='delete' ><a>삭제</a></li>");
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
				console.log($("textarea"));
				console.log($("input[type='text']"));
				$("textarea, input[type='text']").attr("readonly", false).css("background-color", "white")
					.css("border", "1px solid aqua");
				$("#starEmpty").on("click", function(e){
					starScore(e);
				});	
				$("#modi > a").html("완료");
				break;
			case "완료":
				insertHomework("modify");
				break;
			case "삭제":
				$("#myModal").modal();
				break;
			default :
				break;
			}
		});
	// sourceCode height css
		var height = $("#problemViewWrap").css("height");
		console.log("height: "+height);
		$("#sourceCodeWrap").css("height", height);
		$("#sourceCode").css("height", "60%");
		$("#consoleView").css("height", "25%");
		
		ws.send("init");
	
		editor = ace.edit("editor");
	    editor.setTheme("ace/theme/monokai");
	    editor.getSession().setMode("ace/mode/java");
	});	// document onload
	
	//Event Handler	
	$('#deleteConfirm').click(function(e){
		insertHomework("delete");
	});	
	$("#run").on("click", function() {
		compile();		
	});
	$("#report").on("click", function(){
		report();
	});
	
	$(document).on("keypress", ":input:not(textarea)", function(event) {
	    return event.keyCode != 13;
	});
	
	function starScore(e){
		var adminLevel = parseInt(e.offsetX/16)+1;		
		$("#starScore").animate({ width : 80*adminLevel/5 });
		document.getElementById("adminLevel").value = adminLevel;
		console.log($("#adminLevel").val());
	}
	
	function insertHomework(insertType){
		var hwData;
		switch(insertType){
		case "write":
			$("#homeworkForm").submit(function(e){
				e.preventDefault();
			});
			$("input[type='submit']").click();
			hwData = $("#homeworkForm").serializeArray();
			hwData.push({
				name : "source",
				value : editor.getValue()
			});
			break;
		case "modify":				
			$("#homeworkForm").submit(function(e){			
				e.preventDefault();
			});
 			$("input[type='submit']").click();		
			hwData = $("#homeworkForm").serializeArray();
			hwData.push({
				name : "source",
				value : editor.getValue()
			});
			break;
		case "delete":
			hwData = { num : $("input[type='hidden'][name='num']").val() };
			break;
		default:
			break;
		}
		console.log("hwdata...");
		console.log(hwData);
		
		$.ajax({
			url : "/hw/admin/"+insertType,
			type : "post",
			async : false,
			data : hwData,
			success : function(data) {
				console.log(data);
				if (data == 0)
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
	$(document).delegate('#sourceCode', 'keydown', function(e) {
	  var keyCode = e.keyCode || e.which;	  
	  console.log(keyCode);
	  if (keyCode == 9) {
		e.preventDefault();
	    var start = $(this).get(0).selectionStart;
	    var end = $(this).get(0).selectionEnd;
		console.log($(this));
		console.log(start);
		console.log(end);
		console.log($(this).val());
		console.log($(this).text());
	    // set textarea value to: text before caret + tab + text after caret
	    $(this).val($(this).val().substring(0, start)
	                + "\t"
	                + $(this).val().substring(end));
	    
	    // put caret at right position again
	    $(this).get(0).selectionStart =
	    $(this).get(0).selectionEnd = start + 1;
	  }
	});
	
	function compile(){
		var $answer = editor.getValue();
 		var className = $("#className").val();
 		var methodName = $("#methodName").val();
		var sourceData = {
			"java" : $answer,
			"className" : className,
			"methodName" : methodName,
			"args" : $("#inputText").val()
		}	
		console.log(sourceData);
		
		$.ajax({
			url : "/hw/"+auth+"/compile",
			type : "post",
			data : sourceData,
			success : function(r){
				console.log(r);
				r.result = r.result.replace(/\s/g, "&nbsp;"); 
				console.log(r.result);
		 		$("#consoleView").empty();
		 		$("#consoleView").append(r.result+"<br/>");
			}
		});		
		
		
	}
	function checkAnswer(r){ 		
		
		if(r){
			$("#consoleView").append("<span class='correct' >Correct!!!</span><br/>");
			recordRank();
			$("report").css("color", "yellow");
			$("report").on("click", function(e){
				report();
			});
			var studentLevel = prompt("얼마나 어려우셨나요?(난이도 입력)", "1~5");			
			console.log(studentLevel);
			$.get("/hw/student/level/"+${num }+"/"+studentLevel, function(r){
				console.log(r);
			});
			
			$.ajax({
				url : "/hw/source/report",
				data : {
					num : ${num },
					source : editor.getValue()
					
				},
				success : function(r){
					alert("Success!!");
					console.log(r);
				},
				error : function(data, r, err){
					alert(err);
					console.log(r);
				}
			});
			$("#gallery").attr("href", "/gallery/read/"+${num });
			
		}else{
			$("#consoleView").append("<span class='incorrect' >incorrect..</span><br/>");
		}	
	}
	
	function recordRank(){
// 		ws.send("${login.ID}");
// 		$("#rankView").append((lastRank+1), " ${login.ID}", "<br/>");
// 		lastRank++;
	}
	function report(){
		var $answer = editor.getValue();
 		var className = $("#className").val();
 		var methodName = $("#methodName").val();
		var sourceData = {
			"java" : $answer,
			"className" : className,
			"methodName" : methodName,
			"args" : $("#inputText").val()
		}	
		console.log(sourceData);
		
		$.ajax({
			url : "/hw/"+auth+"/compile",
			type : "post",
			data : sourceData,
			success : function(r){
				console.log(r);
				r.result = r.result.replace(/\s/g, "&nbsp;"); 
				console.log(r.result);
		 		$("#consoleView").empty();
		 		$("#consoleView").append(r.result+"<br/>");
		 		if(auth=="student")
		 			checkAnswer(r.check);
			}
		});
		
	}
	
	// WebSocket	
	var ws = new WebSocket("ws://${header.host}/homework/homesocket");
	ws.onopen = function(args) {
		console.log("connect.");
	};
	ws.onmessage = function(e) {
		console.log(e.data);
		var rankMap = new Object();
		try{
			rankMap = JSON.parse(e.data);
			rank = Object.keys(rankMap);
			console.log(rank[0]);
			
			console.log(rankId);
			for(var i in rank){
				var rankId = rankMap[rank[i]];
				$("#rankView").append(i, "등: ", rankId, "<br/>");
			}			
// 	 		lastRank++;
		}catch(err){
			console.log(err);
		}		
	}
	ws.onclose = function(e) {
		console.log(e);
		console.log("Ws is closed.");
	}
	ws.onerror = function(e) {
		console.log(e);
	}
	
	
</script>

