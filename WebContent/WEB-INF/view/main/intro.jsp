<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="shortcut icon" href="/image/ycm.ico"/>
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Poppins">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- <div style="background-image: /image/intro.png"> -->
<div>
	ID:<br/>
	<input type="text" id="id" name="id" placeholder="ID" style="width: 30%; heigth: 10%; border-radius: 5px; padding-left: 10px"/><br/>
	PW:<br/>
	<input type="password" id="password" name="password" style="width: 30%; heigth: 10%; border-radius: 5px; padding-left: 10px"/><br/>
	<input type="button" id="join" value="Join" class="btn btn-default"/>&nbsp;&nbsp;
	<input type="button" id="login" value="Login" class="btn btn-default"/><br/>
	<div id="fail" style="display: none">
		<font style="color: red">아이디 또는 비밀번호를 확인해주세요.</font>
	</div>
</div>

<script>
	$("#join").click(function(){
		location.href="/member/joinForm";
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
					location.href="/login/"+id;
				} else {
					$("#fail").show();
				}
			}
		});
	});
</script>