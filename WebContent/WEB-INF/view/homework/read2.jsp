<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<div id="view" style='height: 300px; background-color: #efefef'>
${list.TITLE }<br/>
${list.CONTENT }
</div>
<br/>
<input id='homeworkModify' type='button' value='수정' />
<script>
	$.get("/homework/readJSON", function(r){
		console.log(r);
		var content = r.CONTENT.replace(/\r\n/g, "<br/>");		
	});
	$("#homeworkModify").on("click", function(){
		$("#view").replaceWith("<textarea cols='100' rows='100'>"+$("#view").html()+"</textarea>");
	});
</script>