<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="w3-sidenav w3-black w3-collapse w3-top w3-large w3-padding"
	style="z-index: 3; width: 300px; font-weight: bold" id="mySidenav"><br/>
	<a href="javascript:void(0)" onclick="w3_close()" class="w3-padding-xlarge w3-hide-large w3-display-topleft w3-hover-gray"
		style="width: 100%; font-size: 22px"><font style="color: white">Close Menu</font></a>
	<div class="w3-container" align="center">
		<h4 style="margin-top: 60px">
			<b>${login.NAME }</b>
		</h4>
		님 반갑습니다.<br/>
		<input type="button" class="btn btn-default" value="내정보" id="info"/>&nbsp;&nbsp;
		<input type="button" class="btn btn-default" value="로그아웃" id="logout"/>
	</div><br/>
	<c:forEach var="t" items="${menu }">
		<c:choose>
			<c:when test="${t.ID == 'drop' }">
				<a onclick="drop()" class="w3-padding w3-hover-gray"><font style="color: white" id="${t.ID }">${t.MENU }</font></a>
				<div id="class" class="w3-dropdown-content w3-border" style='background-color: black'>
					<c:forEach var="t" items="${classes }">
				    	<a href="${t.HREF }" class="w3-hover-gray"><font style="color: white" id="class_${t.ID }">${t.NAME }</font></a>
					</c:forEach>
			    </div>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${t.ID == 'admin' }">
						<c:choose>
					    	<c:when test="${login.NAME=='admin' }">
								<a href="${t.HREF }" class="w3-padding w3-hover-gray"><font style="color: white" id="${t.ID }">${t.MENU }</font></a>
					    	</c:when>
					    	<c:otherwise>
					    		<a class="w3-padding w3-hover-gray"><font style="color: white">${t.MENU }</font></a>
					    	</c:otherwise>
					    </c:choose>
					</c:when>
					<c:otherwise>
						<a href="${t.HREF }" class="w3-padding w3-hover-gray"><font style="color: white" id="${t.ID }">${t.MENU }</font></a>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</nav>

<!-- Top menu on small screens -->
<header
	class="w3-container w3-top w3-hide-large w3-black w3-xlarge w3-padding">
	<a href="javascript:void(0)"
		class="w3-btn w3-black w3-border w3-border-white w3-margin-right"
		onclick="w3_open()">☰</a> <span>Yoon's Class Management</span>
</header>

<!-- Overlay effect when opening sidenav on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()"
	style="cursor: pointer" title="close side menu" id="myOverlay"></div>
	
<script>
	function drop(){
		var x = document.getElementById("class");
	    if (x.className.indexOf("w3-show") == -1) {
	        x.className += " w3-show";
	    } else { 
	        x.className = x.className.replace(" w3-show", "");
	    }
	}
	
	$("#logout").click(function(){
		location.href="/member/logout";
	});
</script>