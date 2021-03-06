<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="w3-container" align="center">
	<h2 style="margin-top: 100px; margin-bottom: 50px">동영상 게시판</h2>
	
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
 			<c:forEach var="t" items="${list }"> 
				<tr>
					<td>${t.NUM }</td>
					<td><a href="#"><b>${t.TITLE }</b></a></td>
					<td>${t.WRITER }</td>
					<td>${t.DAY }</td>
					<td>${t.COUNT}</td>
				</tr>
			</c:forEach> 
		</tbody>
	</table><br/>
	<div align="center">
	<!-- 주석 풀고 작업 -->
<%-- 		<c:forEach> --%>
		<a href="#">1</a>
<%-- 		</c:forEach> --%>
	</div>
	<div align="right">
		<input type="button" class="btn btn-default" value="글작성" onclick="location='/video/upload'"/>
	</div>
	<div align="center">
		<input type="text" placeholder="검색" style="width: 20%; padding-left: 10px"/>&nbsp;&nbsp;
		<input type="button" class="btn btn-default" value="검색"/>
	</div>
</div>
<script>

</script>
