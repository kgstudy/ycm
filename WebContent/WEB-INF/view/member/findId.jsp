<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="w3-modal" id="findIdModal" style="display: none; padding-left: 30%; padding-right: 30%">
	<form class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin">
		<h2 class="w3-center">아이디 찾기</h2>
		
		<div class="w3-row w3-section">
			<div class="w3-col" style="width: 50px">
				<i class="w3-xxlarge fa fa-user"></i>
			</div>
			<div class="w3-rest">
				<input class="w3-input w3-border" name="findId_name" type="text" placeholder="Name" required="required" id="findId_name"/>
			</div>
		</div>

		<div class="w3-row w3-section">
			<div class="w3-col" style="width: 50px">
				<i class="w3-xxlarge fa fa-user"></i>
			</div>
			<div class="w3-rest">
				<input class="w3-input w3-border" name="findId_email" type="email" placeholder="Email" required="required" id="findId_email"/>
			</div>
		</div>
		
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="sendNum" value="인증번호 전송"/>
		<span id="numMessage" style="display: none">전송되었습니다.</span>
		<span id="reNumMessage" style="display: none"><font style="color: red">재전송되었습니다.</font></span>
		<div class="w3-row w3-section" style="display: none" id="writeNum">
			<div class="w3-col" style="width: 50px">
				<i class="w3-xxlarge fa fa-user"></i>
			</div>
			<div class="w3-rest">
				<input class="w3-input w3-border" name="num" type="text" placeholder="인증번호" required="required" id="num"/>
			</div>
		</div>
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="checkNum" value="인증번호 확인" style="display: none"/>
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="reSendNum" value="인증번호 재전송" style="display: none"/>
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="findIdCancel" value="Cancel"/>
	</form>
</div>

<script>
	$("#sendNum").click(function(){
		$("#sendNum").hide();
		$("#checkNum").show();
		$("#reSendNum").show();
		$("#numMessage").show();
		$("#writeNum").show();
	});
	
	$("#reSendNum").click(function(){
		$("#numMessage").hide();
		$("#reNumMessage").show();
	});
	
	$("#findIdCancel").click(function(){
		$("#sendNum").show();
		$("#checkNum").hide();
		$("#reSendNum").hide();
		$("#numMessage").hide();
		$("#reNumMessage").hide();
		$("#writeNum").val("");
		$("#writeNum").hide();
		$("#findId_name").val("");
		$("#findId_email").val("");
		$("#findIdModal").fadeOut(300);
	});
</script>