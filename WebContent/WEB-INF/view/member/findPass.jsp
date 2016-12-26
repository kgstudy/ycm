<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="w3-modal" id="findPwModal" style="display: none; padding-left: 30%; padding-right: 30%">
	<form class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin">
		<h2 class="w3-center">비밀번호 찾기</h2>
		
		<div class="w3-row w3-section">
			<div class="w3-col" style="width: 50px">
				<i class="w3-xxlarge fa fa-user"></i>
			</div>
			<div class="w3-rest">
				<input class="w3-input w3-border" name="findPw_id" type="text" placeholder="Id" required="required" id="findPw_id"/>
			</div>
		</div>

		<div class="w3-row w3-section">
			<div class="w3-col" style="width: 50px">
				<i class="w3-xxlarge fa fa-user"></i>
			</div>
			<div class="w3-rest">
				<input class="w3-input w3-border" name="findPw_email" type="email" placeholder="Email" required="required" id="findPw_email"/>
			</div>
		</div>
		
		<span id="findPw_fail" style="display: none"><font style="color: red" id="findPw_fail_content">입력하신 정보와 일치하는 회원이 없습니다.</font></span>
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="pw_sendNum" value="인증번호 전송"/>
		<span id="pw_numMessage" style="display: none">전송되었습니다.</span>
		<span id="pw_reNumMessage" style="display: none"><font style="color: red">재전송되었습니다.</font></span>
		<div class="w3-row w3-section" style="display: none" id="pw_writeNum">
			<div class="w3-col" style="width: 50px">
				<i class="w3-xxlarge fa fa-user"></i>
			</div>
			<div class="w3-rest">
				<input class="w3-input w3-border" name="pw_num" type="text" placeholder="인증번호" required="required" id="pw_num"/>
			</div>
		</div>
		<span id="pw_rst_failMessage" style="display: none"><font style="color: red">인증번호를 확인해주세요.</font></span>
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="pw_checkNum" value="인증번호 확인" style="display: none"/>
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="repw_sendNum" value="인증번호 재전송" style="display: none"/>
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="findPwCancel" value="Cancel"/>
		
		
		<div class="w3-row w3-section" style="display: none" id="pw_rst_message">
			<div class="w3-col" style="width: 50px">
				<i class="w3-xxlarge fa fa-user"></i>
			</div>
			<div class="w3-rest" align="center">
				<label><font style="color: black">새로운 비밀번호를 입력해주세요.</font></label>
			</div>
		</div>
		<div class="w3-row w3-section" style="display: none" id="findPw_rst">
			<div class="w3-col" style="width: 50px">
				<i class="w3-xxlarge fa fa-user"></i>
			</div>
			<div class="w3-rest">
				<input class="w3-input w3-border" name="rstPw" type="password" id="rstPw" placeholder="Password (8자 이상)"/>
			</div>
		</div>
		<div class="w3-row w3-section" style="display: none" id="findPw_rst2">
			<div class="w3-col" style="width: 50px">
				<i class="w3-xxlarge fa fa-user"></i>
			</div>
			<div class="w3-rest">
				<input class="w3-input w3-border" name="rstPw2" type="password" id="rstPw2" placeholder="Password 확인"/>
			</div>
		</div>
		<span id="pw_pwCheck" style="display: none"><font style="color: red">비밀번호와 비밀번호 확인이 일치하지 않습니다.</font></span>
		<span id="pw_pwCheck2" style="display: none"><font style="color: red">8자 이상으로 설정해주세요.</font></span>
		<span id="pw_pwCheck3" style="display: none"><font style="color: red">잠시후 다시 시도해주세요.</font></span>
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="findPw_ok" value="확인" style="display: none"	/>
	</form>
</div>

<div class="w3-modal" id="finishPwModal" style="display: none; padding-left: 30%; padding-right: 30%; padding-top: 20%; background-color: black" align="center">
	<h3 style="color: white">변경되었습니다.<br/>변경된 비밀번호로 로그인 하십시오.</h3>
</div>

<script>
	$("#pw_sendNum").click(function(){
		var id = $("#findPw_id").val();
		var email = $("#findPw_email").val();
		if(id.length>0 && email.length>0){
			if(email.indexOf('@')<0 || email.indexOf('.')<0 || email.indexOf('@')-email.indexOf('.')>=-1){
				$("#findPw_fail_content").html("올바른 이메일을 입력해주세요.");
				$("#findPw_fail").show();
			} else {
				$.ajax({
					type : "post",
					url : "/member/findPw/"+id+"?email="+email,
					async : false,
					success : function(txt){
						if(txt==true){
							$("#pw_sendNum").hide();
							$("#pw_checkNum").show();
							$("#repw_sendNum").show();
							$("#pw_numMessage").show();
							$("#pw_writeNum").show();
							$("#findPw_fail").hide();
						} else {
							$("#findPw_fail_content").html("입력하신 정보와 일치하는 회원이 없습니다.");
							$("#findPw_fail").show();
						}
					}
				});
			}
		} else {
			$("#findPw_fail_content").html("모든 항목을 입력해주세요.");
			$("#findPw_fail_content").show();
		}
	});
	
	$("#pw_checkNum").click(function(){
		var id = $("#findPw_id").val();
		var num = $("#pw_num").val();
		if(num.length>0){
			$.ajax({
				type : "post",
				url : "/member/checkNum2/"+id+"/"+num,
				async : false,
				success : function(txt){
					if(txt==true){
						$("#findPw_id").prop("disabled", true);
						$("#findPw_email").prop("disabled", true);
						$("#pw_num").prop("disabled", true);
						$("#pw_checkNum").hide();
						$("#repw_sendNum").hide();
						$("#pw_numMessage").hide();
						$("#findPwCancel").hide();
						$("#pw_rst_message").show();
						$("#findPw_rst").show();
						$("#findPw_rst2").show();
						$("#findPw_ok").show();
						$("#pw_rst_failMessage").hide();
						$("#pw_reNumMessage").hide();
					} else {
						$("#pw_rst_failMessage").show();
					}
				}
			});
		} else {
			$("#pw_rst_failMessage").show();
		}
	});
	
	$("#findPw_ok").click(function(){
		var id = $("#findPw_id").val();
		var pw = $("#rstPw").val();
		var pw2 = $("#rstPw2").val();
		if(pw.length>7 && pw2.length>7){
			if(pw==pw2){
				$.ajax({
					type : "post",
					url : "/member/changePw/"+id+"/"+pw,
					async : false,
					success : function(txt){
						if(txt == true){
							$("#finishPwModal").fadeIn(300).delay(500).fadeOut(300);
							setTimeout(function() {
								$("#findPwModal").fadeOut(300);
								$("#findPw_id").prop("disabled", false);
								$("#findPw_id").val("");
								$("#findPw_name").prop("disabled", false);
								$("#findPw_email").val("");
								$("#findPw_email").prop("disabled", false);
								$("#pw_num").val("");
								$("#pw_num").prop("disabled", false);
								$("#pw_sendNum").show();
								$("#pw_checkNum").hide();
								$("#repw_sendNum").hide();
								$("#pw_numMessage").hide();
								$("#pw_reNumMessage").hide();
								$("#pw_writeNum").val("");
								$("#pw_writeNum").hide();
								$("#pw_rst_message").hide();
								$("#findPw_rst").hide();
								$("#findPw_rst2").hide();
								$("#findPw_ok").hide();
								$("#pw_pwCheck").hide();
								$("#pw_pwCheck2").hide();
								$("#pw_pwCheck3").hide();
								$("#findPwCancel").show();
								$("#rstPw").val("");
								$("#rstPw2").val("");
								$("#pw_rst_failMessage").hide();
							}, 1300);
						} else {
							$("#pw_pwCheck").hide();
							$("#pw_pwCheck2").hide();
							$("#pw_pwCheck3").show();
						}
					}
				});
			} else {
				$("#pw_pwCheck").show();
				$("#pw_pwCheck2").hide();
				$("#pw_pwCheck3").hide();
			}
		} else {
			$("#pw_pwCheck").hide();
			$("#pw_pwCheck2").show();
			$("#pw_pwCheck3").hide();
		}
	});
	
	$("#repw_sendNum").click(function(){
		$("#pw_numMessage").hide();
		$("#pw_reNumMessage").show();
	});
	
	$("#findPwCancel").click(function(){
		$("#findPwModal").fadeOut(300);
		$("#pw_sendNum").show();
		$("#pw_checkNum").hide();
		$("#repw_sendNum").hide();
		$("#pw_numMessage").hide();
		$("#pw_reNumMessage").hide();
		$("#pw_writeNum").val("");
		$("#pw_writeNum").hide();
		$("#findPw_name").val("");
		$("#findPw_email").val("");
		$("#pw_pwCheck").hide();
		$("#pw_pwCheck2").hide();
		$("#pw_pwCheck3").hide();
		$("#pw_rst_failMessage").hide();
	});
</script>