<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>

<body>
<form id='homeworkForm' action='/homework/admin/write' >
<input type='text' name='title' value='${list.TITLE }' ><br/>
<textarea id="view" name='content' cols='100' rows='20' disabled="disabled" >
${list.CONTENT }
</textarea>
<textarea id='homeworkAnswer' name='answer' rows='10' cols='100' ></textarea>
<br/>
</form>
</body>

<script>
	
</script>