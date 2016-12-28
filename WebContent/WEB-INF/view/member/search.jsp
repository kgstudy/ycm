<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="w3-modal" id="searchModal" style="display: none; padding-left: 30%; padding-right: 30%">
	<form class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin">
		<h2 class="w3-center">아이디 / 비밀번호 찾기</h2>
		
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="idSearch" value="아이디 찾기"/>
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="passSearch" value="비밀번호 찾기"/>
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="searchCancel" value="Cancel" onclick="cancel(this)"/>
	</form>
</div>

<script>
	$("#idSearch").click(function(){
		$("#searchModal").fadeOut(300);
		setTimeout(function() {
			$("#findIdModal").fadeIn(300);
		}, 500);
	});
	
	$("#passSearch").click(function(){
		$("#searchModal").fadeOut(300);
		setTimeout(function() {
			$("#findPwModal").fadeIn(300);
		}, 500);
	});
</script>