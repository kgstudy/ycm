<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="margin-top: 100px">
	<form class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin">
		<h2 class="w3-center">Join</h2>
	
		<div class="w3-row w3-section">
			<div class="w3-col" style="width: 50px">
				<i class="w3-xxlarge fa fa-user"></i>
			</div>
			<div class="w3-rest">
				<input class="w3-input w3-border" name="id" type="text" placeholder="Id(6자 이상)" required="required" id="id"/>
				<span id="idCheck" style="display: none"></span>
			</div>
		</div>
	
		<div class="w3-row w3-section">
			<div class="w3-col" style="width: 50px">
				<i class="w3-xxlarge fa fa-lock"></i>
			</div>
			<div class="w3-rest">
				<input class="w3-input w3-border" name="password" type="text" placeholder="Password(8자 이상)" required="required" id="password">
				<span id="passCheck" style="display: none"></span>
			</div>
		</div>
	
		<div class="w3-row w3-section">
			<div class="w3-col" style="width: 50px">
				<i class="w3-xxlarge fa fa-pencil"></i>
			</div>
			<div class="w3-rest">
				<input class="w3-input w3-border" name="name" type="text" placeholder="Name" required="required" id="name">
				<span id="nameCheck" style="display: none"></span>
			</div>
		</div>
	
		<div class="w3-row w3-section">
			<div class="w3-col" style="width: 50px">
				<i class="w3-xxlarge fa fa-phone"></i>
			</div>
			<div class="w3-rest">
				<input class="w3-input w3-border" name="phone" type="text" placeholder="Phone(010-1234-5678)" required="required" id="phone">
				<span id="phoneCheck" style="display: none"></span>
			</div>
		</div>
	
		<div class="w3-row w3-section">
			<div class="w3-col" style="width: 50px">
				<i class="w3-xxlarge fa fa-envelope-o"></i>
			</div>
			<div class="w3-rest">
				<input class="w3-input w3-border" name="email" type="email" placeholder="Email" required="required" id="email">
				<span id="emailCheck" style="display: none"></span>
			</div>
		</div>
		<div class="w3-row w3-section" id="fail" style="display: none" align="center">
			<label><b><font style="color: red">회원가입에 실패하였습니다.<br/>잠시 후 다시 시도해주세요.</font></b></label>
		</div>
		<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="join" value="Join"/>
	</form>
</div>

<div class="w3-modal" id="modal" style="width: 100%; height: 100%; background-color: white" align="center">
	<div class="w3-row" style="width: 50%; heigth: 50%">
		<label><b>회원가입이 완료되었습니다.</b></label>
	</div>
</div>

<script>
	$("#join").click(function(){
		var id = $("#id").val();
		var password = $("#password").val();
		var name = $("#name").val();
		var phone = $("#phone").val();
		var email = $("#email").val();
		if(password.length<8){
			var html = "<font style='color: red'>8자 이상으로 설정해주세요.</font>";
			$("#passCheck").show();
			$("#passCheck").html(html);
		} else if(name.length<1){
			var html = "<font style='color: red'>올바른 이름을 입력해주세요.</font>";
			$("#nameCheck").show();
			$("#nameCheck").html(html);
		} else if(phone.length!=13){
			alert("aa")
			var html = "<font style='color: red'>올바른 핸드폰번호를 입력해주세요.</font>";
			$("#phoneCheck").show();
			$("#phoneCheck").html(html);
		} else if(phone.length==13 && phone.indexOf('-')!=3 && phone.lastIndexOf('-')!=8){
			alert("cc");
			var html = "<font style='color: red'>올바른 핸드폰번호를 입력해주세요.</font>";
			$("#phoneCheck").show();
			$("#phoneCheck").html(html);
		} else if(email.length<0 || email.indexOf('@')<0 || email.indexOf('.')<0 || email.indexOf('@')-email.indexOf('.')>=-1){
			var html = "<font style='color: red'>올바른 이메일을 입력해주세요.</font>";
			$("#emailCheck").show();
			$("#emailCheck").html(html);
		} else {
			$.ajax({
				type : "post",
				url : "/member/join/"+id+"/"+password+"/"+name+"/"+phone+"?email="+email,
				async : false,
				success : function(txt){
					if(txt==true){
						$("#modal").fadeIn(500).delay(1000).fadeOut(500);
						setTimeout(function(){location.href="/"}, 1900);
					} else {
						$("#fail").show();
					}
				},
				error : function(txt){
					console.log(txt);
				}
			});
		}
	});
	
	$("#id").blur(function(){
		var id = $("#id").val();
		if(id.length<6){
			var html = "<font style='color: red'>사용할 수 없는 아이디입니다.</font>";
			$("#idCheck").show();
			$("#idCheck").html(html);
		} else {
			$.ajax({
				type : "post",
				url : "/member/idcheck/"+id,
				async : false,
				success : function(txt){
					var html = "";
					if(txt==true){
						html = "<font style='color: green'>사용할 수 있는 아이디입니다.</font>";
					} else {
						html = "<font style='color: red'>이미 사용중인 아이디입니다.</font>";
					}
					$("#idCheck").show();
					$("#idCheck").html(html);
				}
			});
		}
	});
</script>