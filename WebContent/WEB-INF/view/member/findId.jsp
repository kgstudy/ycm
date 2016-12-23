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
		
		<span id="findId_fail" style="display: none"><font style="color: red" id="findId_fail_content">입력하신 정보와 일치하는 회원이 없습니다.</font></span>
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
		<span id="rst_failMessage" style="display: none"><font style="color: red">인증번호를 확인해주세요.</font></span>
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="checkNum" value="인증번호 확인" style="display: none"/>
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="reSendNum" value="인증번호 재전송" style="display: none"/>
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="findIdCancel" value="Cancel"/>
		
		
		<div class="w3-row w3-section" style="display: none" id="rst_message">
			<div class="w3-col" style="width: 50px">
				<i class="w3-xxlarge fa fa-user"></i>
			</div>
			<div class="w3-rest" align="center">
				<label><font style="color: black">입력하신 정보와 일치하는 아이디입니다.</font></label>
			</div>
		</div>
		<div class="w3-row w3-section" style="display: none" id="findId_rst">
			<div class="w3-col" style="width: 50px">
				<i class="w3-xxlarge fa fa-user"></i>
			</div>
			<div class="w3-rest">
				<input class="w3-input w3-border" name="rstId" type="text" id="rstId" disabled="disabled"/>
			</div>
		</div>
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="findId_ok" value="확인" style="display: none"	/>
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="findId_pw" value="비밀번호 찾기" style="display: none"/>
	</form>
</div>

<script>
	$("#sendNum").click(function(){
		var name = $("#findId_name").val();
		var email = $("#findId_email").val();
		if(name.length>0 && email.length>0){
			if(email.indexOf('@')<0 || email.indexOf('.')<0 || email.indexOf('@')-email.indexOf('.')>=-1){
				$("#findId_fail_content").html("올바른 이메일을 입력해주세요.");
				$("#findId_fail").show();
			} else {
				$.ajax({
					type : "post",
					url : "/member/findId/"+name+"?email="+email,
					async : false,
					success : function(txt){
						if(txt==true){
							$("#sendNum").hide();
							$("#checkNum").show();
							$("#reSendNum").show();
							$("#numMessage").show();
							$("#writeNum").show();
							$("#findId_fail").hide();
						} else {
							$("#findId_fail_content").html("입력하신 정보와 일치하는 회원이 없습니다.");
							$("#findId_fail").show();
						}
					}
				});
			}
		} else {
			$("#findId_fail_content").html("모든 항목을 입력해주세요.");
			$("#findId_fail_content").show();
		}
	});
	
	$("#checkNum").click(function(){
		var name = $("#findId_name").val();
		var num = $("#num").val();
		if(num.length>0){
			$.ajax({
				type : "post",
				url : "/member/checkNum/"+name+"/"+num,
				async : false,
				success : function(txt){
					if(txt!=""){
						$("#findId_name").prop("disabled", true);
						$("#findId_email").prop("disabled", true);
						$("#num").prop("disabled", true);
						$("#checkNum").hide();
						$("#reSendNum").hide();
						$("#numMessage").hide();
						$("#findIdCancel").hide();
						$("#rst_message").show();
						$("#findId_rst").show();
						$("#rstId").val(txt);
						$("#findId_ok").show();
						$("#findId_pw").show();
						$("#rst_failMessage").hide();
						$("#reNumMessage").hide();
					} else {
						$("#rst_failMessage").show();
					}
				}
			});
		} else {
			$("#rst_failMessage").show();
		}
	});
	
	$("#findId_ok").click(function(){
		$("#findIdModal").fadeOut(300);
		$("#findId_name").val("");
		$("#findId_name").prop("disabled", false);
		$("#findId_email").val("");
		$("#findId_email").prop("disabled", false);
		$("#num").val("");
		$("#num").prop("disabled", false);
		$("#sendNum").show();
		$("#checkNum").hide();
		$("#reSendNum").hide();
		$("#numMessage").hide();
		$("#reNumMessage").hide();
		$("#writeNum").val("");
		$("#writeNum").hide();
		$("#rst_message").hide();
		$("#findId_rst").hide();
		$("#rstId").val("");
		$("#findId_ok").hide();
		$("#findId_pw").hide();
		$("#findIdCancel").show();
	});
	
	$("#findId_pw").click(function(){
		$("#findIdModal").fadeOut(300);
		$("#findId_name").val("");
		$("#findId_name").prop("disabled", false);
		$("#findId_email").val("");
		$("#findId_email").prop("disabled", false);
		$("#num").val("");
		$("#num").prop("disabled", false);
		$("#sendNum").show();
		$("#checkNum").hide();
		$("#reSendNum").hide();
		$("#numMessage").hide();
		$("#reNumMessage").hide();
		$("#writeNum").val("");
		$("#writeNum").hide();
		$("#rst_message").hide();
		$("#findId_rst").hide();
		$("#rstId").val("");
		$("#findId_ok").hide();
		$("#findId_pw").hide();
		$("#findIdCancel").show();
		setTimeout(function() {
			$("#findPwModal").fadeIn(300);
		}, 500);
	});
	
	$("#reSendNum").click(function(){
		$("#numMessage").hide();
		$("#reNumMessage").show();
	});
	
	$("#findIdCancel").click(function(){
		$("#findIdModal").fadeOut(300);
		$("#sendNum").show();
		$("#checkNum").hide();
		$("#reSendNum").hide();
		$("#numMessage").hide();
		$("#reNumMessage").hide();
		$("#writeNum").val("");
		$("#writeNum").hide();
		$("#findId_name").val("");
		$("#findId_email").val("");
	});
</script>