<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	li:hover{
		border-bottom: 2px solid #888888;
	}
</style>
<nav class="navbar navbar-default" style='margin : 0px;'>
  <div class="container-fluid">
    <div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/member/login">Yoon's Class</a>
	</div>
	<div class="collapse navbar-collapse" id="myNavbar">
	    <ul id='homeworkNav' class="nav navbar-nav">
	      <li class="active"><a href="/homework">HomeWork</a></li>
	    </ul>
	</div>
  </div>
</nav>