<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<h2 class="w3-padding-64 w3-text-grey" style="margin-top: 50px"
	align="center">자료실</h2>

<div class="w3-row" style="padding-left: 30px; padding-right: 30px; padding-top: 40px" align="center">
	
	<form action="/storage/make" method="post">
		<label>카테고리 </label>&nbsp;&nbsp;
		<select name="category" style="width: 12%; height: 25px; border: 1px solid #ccc; border-radius: 5px">
			<option>설치파일</option>
			<option>jar파일</option>
		</select><br /><br /> 
		<div class="table-responsive" align="center">
			<label for="title" style="width: 15%"><font size="4">제목</font></label>
			<input type="text" id="title" name="title"
					class="form-control" style="width: 25%" placeholder="제목을 입력하세요." required="required"/><br/>
			<label for="content" style="width: 15%"><font size="4">파일 올리기</font></label><br />
			<input type="file" name="content"><br />
			<input type="submit" class="btn btn-default" value="등록" />&nbsp;&nbsp;
			<input type="button" class="btn btn-default" value="취소" onclick="javascript:location.href='/storage'"/>
		</div>
		
		
	</form>
</div>