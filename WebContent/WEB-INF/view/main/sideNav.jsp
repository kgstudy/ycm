<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="w3-sidenav w3-red w3-collapse w3-top w3-large w3-padding"
	style="z-index: 3; width: 300px; font-weight: bold" id="mySidenav">
	<br> <a href="javascript:void(0)" onclick="w3_close()"
		class="w3-padding-xlarge w3-hide-large w3-display-topleft w3-hover-white"
		style="width: 100%; font-size: 22px">Close Menu</a>
	<div class="w3-container">
		<h3 class="w3-padding-64">
			<b>Company<br>Name
			</b>
		</h3>
	</div>
	<a href="#" onclick="w3_close()" class="w3-padding w3-hover-white">Home</a>
	<a href="#showcase" onclick="w3_close()"
		class="w3-padding w3-hover-white">Showcase</a> <a href="#services"
		onclick="w3_close()" class="w3-padding w3-hover-white">Services</a> <a
		href="#designers" onclick="w3_close()"
		class="w3-padding w3-hover-white">Designers</a> <a href="#packages"
		onclick="w3_close()" class="w3-padding w3-hover-white">Packages</a> <a
		href="#contact" onclick="w3_close()" class="w3-padding w3-hover-white">Contact</a>
</nav>

<!-- Top menu on small screens -->
<header
	class="w3-container w3-top w3-hide-large w3-red w3-xlarge w3-padding">
	<a href="javascript:void(0)"
		class="w3-btn w3-red w3-border w3-border-white w3-margin-right"
		onclick="w3_open()">☰</a> <span>Company Name</span>
</header>

<!-- Overlay effect when opening sidenav on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()"
	style="cursor: pointer" title="close side menu" id="myOverlay"></div>