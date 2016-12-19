<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>

<body>
<form id='homeworkForm' action='/homework/admin/write' >
<input type='text' name='title' value='${list.TITLE }' >
<textarea id="view" name='content' cols='100' rows='20' disabled="disabled" >
${list.CONTENT }
</textarea>
<textarea id='homeworkAnswer' name='answer' rows='10' cols='100' ></textarea>
<br/>
<input id='homeworkModify' type='button' value='수정' />
</form>
</body>

<script>	

	$.get("/homework/admin/readJSON", function(r){
		console.log(r);
		var content = r.CONTENT.replace(/\r\n/g, "<br/>");		
	});
	$("#homeworkModify").on("click", function(){
		if(this.value=="수정"){
			console.log($("#view").attr("disabled"));
			$("#view").attr("disabled", false);
			this.value="완료";
		}else{
			$("#homeworkForm").submit();
			this.value="수정";
			$("#view").attr("disabled", true);
			
		}
		
	});
</script>