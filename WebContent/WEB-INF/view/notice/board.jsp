<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="w3-container" align="center">
	<h2 style="margin-top: 100px; margin-bottom: 50px">Title</h2>

	<table class="w3-table-all w3-hoverable">
		<thead style="border: solid gray 2px">
			<tr class="w3-light-grey">
				<td><label>#</label></td>
				<td><label>Title</label></td>
				<td><label>Writer</label></td>
				<td><label>Date</label></td>
				<td><label>Count</label></td>
			</tr>
		</thead>
		<tbody>
			<!-- forEach 주석풀고 작업 -->
		<c:forEach  var="i" items="${noticelist }" >
				<tr>
					<td>${i.NOTICE_NUM }</td>
					<td><a href="/notice/view/${i.NOTICE_NUM }"><b>${i.NOTICE_TITLE }</b></a></td>
					<td>${i.NOTICE_WRITER }</td>
					<td>${i.NOTICE_DATE }</td>
					<td>${i.NOTICE_COUNT }</td>
				</tr>
 		</c:forEach> 
		</tbody>
	</table><br/>
	
	<fmt:parseNumber var="var3" value="${(size-1)/5}" integerOnly="true"/>
	<div align="center">
	
	<c:if test="${size > 5}">
		<input type="button" class="w3-btn w3-light-gray" value="이전" onclick="javascript:backpage()">
	</c:if>
	<!-- 주석 풀고 작업 -->
		<c:forEach var="i" begin="${var3*5+1 }" end="${size }">
			<c:choose>
				<c:when test="${i==size}">
					<a href="/notice/page/${i }/${size}/${param.search }">${i }</a>
				</c:when>
				<c:otherwise>
					<a href="/notice/page/${i }/${size}/${param.search }">${i }</a> |
				</c:otherwise>
			</c:choose>
		</c:forEach> 

	<c:if test="${lastsize != size}">
		<input type="button" class="w3-btn w3-light-gray" value="다음" onclick="javascript:nextpage()">
	</c:if>		
	</div>
	
	<c:if test="${login.ID=='admin'}">	
		<div align="right">
			<input type="button" class="btn btn-default" value="글작성" onclick="javascript:location.href='/notice/write'"/>
		</div>
	</c:if>
	
	<div align="center">
		<form action="/notice" method="post"><!--  여기 경로 수정 -->
			<input type="text" placeholder="검색" style="width: 20%; padding-left: 10px" name="search" value="${param.search }"/>&nbsp;&nbsp;
			<input type="submit" class="btn btn-default" value="검색"/>
		</form>
	</div>
</div>

<c:if test="${lastsize == size }">
	 <fmt:parseNumber var="var3" value="${(size-1)/5}"  integerOnly="true" />
     <fmt:parseNumber var="size" value="${(var3+1)*5}"  integerOnly="true" />
</c:if>

<!-- <script>
function nextpage() {
    endp = ${size + 5 };
    p = ${size + 1 };
    location.href = "/notice?p="+ p + "&endp=" + endp+"&search=${param.search }";
 }
 function backpage() {
    endp = ${size - 5 };
    p = endp - 4;
    location.href = "/notice?p="+ p + "&endp=" + endp+"&search=${param.search }";;
 }
</script> -->


