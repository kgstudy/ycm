<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
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
body, h1, h2, h3, h4, h5 {
	font-family: "Poppins", sans-serif
}

body {
	font-size: 16px;
}

.w3-half img {
	margin-bottom: -6px;
	margin-top: 16px;
	opacity: 0.8;
	cursor: pointer
}

.w3-half img:hover {
	opacity: 1
}

#loading {
	height: 100%;
	position: fixed;
	left: 0px;
	top: 0px;
	width: 100%;
	filter:alpha(opacity=50);
	-moz-opacity:0.5;
	opacity : 0.5;
}
.loading {
	background-color: white;
	z-index: 199;
}
#loading_img{
	position:absolute; 
	top:50%;
	left:50%;
	margin-top:-75px;
	margin-left:-75px;
	z-index: 200;
} 
</style>
<body>
	<c:if test="${login == null }">
		<script>
			location.href="/"
		</script>
	</c:if>

	<!-- Sidenav/menu -->
	<tile:insertAttribute name="sideNav"/>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 340px; margin-right: 40px">
		<tile:insertAttribute name="article"/>
		<!-- End page content -->
	</div>
	
<!-- 	<!-- W3.CSS Container -->
<!-- 	<div class="w3-light-grey w3-container w3-padding-32" -->
<!-- 		style="margin-top: 75px; padding-right: 58px"> -->
<!-- 		<p class="w3-right"> -->
<!-- 			Powered by <a href="http://www.w3schools.com/w3css/default.asp" -->
<!-- 				title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a> -->
<!-- 		</p> -->
<!-- 	</div> -->

<script>
// Script to open and close sidenav
function w3_open() {
    document.getElementById("mySidenav").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";
}
 
function w3_close() {
    document.getElementById("mySidenav").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
}

// Modal Image Gallery
function onClick(element) {
  document.getElementById("img01").src = element.src;
  document.getElementById("modal01").style.display = "block";
  var captionText = document.getElementById("caption");
  captionText.innerHTML = element.alt;
}

var loading = $('<div id="loading" class="loading"></div><img id="loading_img" src="/image/ajax-loader.gif" />').appendTo(document.body).hide();
$(window).ajaxStart(function (){
	loading.show();
}).ajaxStop(function (){
	loading.hide();
}); 
// jQuery(this).ajaxStart(function(){
// 	jQuery("#loading-mask").show();
// });
// jQuery(this).ajaxStop(function() {
// 	jQuery("#loading-mask").fadeOut();
// });
</script>

</body>
</html>
