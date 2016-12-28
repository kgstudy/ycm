<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<title>Yoon's Class Management</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Poppins">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
/* body, h1, h2, h3, h4, h5 { */
/* 	font-family: "Poppins", sans-serif */
/* } */

/* body { */
/* 	font-size: 16px; */
/* } */

/* .w3-half img { */
/* 	margin-bottom: -6px; */
/* 	margin-top: 16px; */
/* 	opacity: 0.8; */
/* 	cursor: pointer */
/* } */

/* .w3-half img:hover { */
/* 	opacity: 1 */
/* } */
</style>
<body>	
	<tile:insertAttribute name="header"/>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin: -13px 0 0 0;" >
		<tile:insertAttribute name="article"/>
		<!-- End page content -->
	</div>
	<footer>
		<tile:insertAttribute name="footer" />
	</footer>
	
<!-- 	<!-- W3.CSS Container -->
<!-- 	<div class="w3-light-grey w3-container w3-padding-32" -->
<!-- 		style="margin-top: 75px; padding-right: 58px"> -->
<!-- 		<p class="w3-right"> -->
<!-- 			Powered by <a href="http://www.w3schools.com/w3css/default.asp" -->
<!-- 				title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a> -->
<!-- 		</p> -->
<!-- 	</div> -->

</body>
</html>
