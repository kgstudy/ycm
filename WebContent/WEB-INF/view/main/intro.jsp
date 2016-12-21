<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<title>Yoon's Class Management</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="/image/ycm.ico"/>
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Poppins">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
	body{
		background-image: url("/image/intro.png");
		background-size: cover;
		width: 100%;
		height: 100%;
	}
</style>

<body>
	<div class="w3-row">
		<div style="width: 100%; heigth: 100%; padding-top: 25%; padding-left: 30%" align='center'>
			<label><font style="color: white; font-size: 20px;">ID:</font></label><br/>
			<input type="text" id="id" name="id" placeholder="ID" style="width: 40%; height: 5%; border-radius: 5px; padding-left: 10px"/><br/><br/>
			<label><font style="color: white; font-size: 20px;">PW:</font></label><br/>
			<input type="password" id="password" name="password" style="width: 40%; height: 5%; border-radius: 5px; padding-left: 10px"/><br/><br/>
			<input type="button" id="join" value="Join" class="btn btn-default" style="width: 10%"/>&nbsp;&nbsp;
			<input type="button" id="login" value="Login" class="btn btn-default" style="width: 10%"/><br/><br/>
			<div id="fail" style="display: none">
				<label><font style="color: red; font-size: 15px"><b>아이디 또는 비밀번호를 확인해주세요.</b></font></label>
			</div>
		</div>
	</div>
	
	<div id="joinModal" class="w3-modal" style="display: none">
		<form class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin">
			<h2 class="w3-center">Join</h2>
		
			<div class="w3-row w3-section">
				<div class="w3-col" style="width: 50px">
					<i class="w3-xxlarge fa fa-user"></i>
				</div>
				<div class="w3-rest">
					<input class="w3-input w3-border" name="joinId" type="text" placeholder="Id (6자 이상)" required="required" id="joinId"/>
					<span id="idCheck" style="display: none"></span>
				</div>
			</div>
		
			<div class="w3-row w3-section">
				<div class="w3-col" style="width: 50px">
					<i class="w3-xxlarge fa fa-lock"></i>
				</div>
				<div class="w3-rest">
					<input class="w3-input w3-border" name="joinPassword" type="text" placeholder="Password (8자 이상)" required="required" id="joinPassword">
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
					<input class="w3-input w3-border" name="phone" type="text" placeholder="Phone (010-1234-5678)" required="required" id="phone">
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
			<input type="button" class="w3-btn-block w3-section w3-blue w3-ripple w3-padding" id="joinComplete" value="Join"/>
		</form>
	</div>
	
	<div class="w3-modal" id="successModal" style="width: 100%; height: 100%; background-color: white; display: none" align="center">
		<div class="w3-row">
			<label><b>회원가입 요청이 완료되었습니다.<br/>관리자 승인 후 로그인 가능합니다.</b></label>
		</div>
	</div>
	
	<script>
		$("#join").click(function(){
	// 		location.href="/member/join";
			$("#joinModal").show();
		});
		
		$("#login").click(function(){
			var id = $("#id").val();
			var password = $("#password").val();
			$.ajax({
				type : "post",
				url : "/member/login/"+id+"/"+password,
				async : false,
				success : function(txt){
					if(txt==true){
						location.href="/member/login";
					} else {
						$("#fail").show();
					}
				}
			});
		});
		
		$("#password").keydown(function(txt){
			if(txt.keyCode==13){
				$("#login").trigger("click");
			}
		});
		
		$("#joinComplete").click(function(){
			var id = $("#joinId").val();
			var password = $("#joinPassword").val();
			var name = $("#name").val();
			var phone = $("#phone").val();
			var email = $("#email").val();
			if(id.length<1){
				var html = "<font style='color: red'>아이디를 입력해주세요.</font>";
				$("#idCheck").show();
				$("#idCheck").html(html);
			}else if(password.length<8){
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
							$("#successModal").fadeIn(500).delay(1500).fadeOut(500);
							setTimeout(function(){location.href="/"}, 2400);
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
		
		$("#joinId").blur(function(){
			var id = $("#joinId").val();
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
</body>
</html>