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
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="checkNum" value="인증번호 확인"/>
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="reSendNum" value="인증번호 재전송"/>
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="searchCancel" value="Cancel" onclick="cancel(this)"/>
	</form>
</div>

<script>
	
</script>