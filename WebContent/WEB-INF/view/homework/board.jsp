<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="w3-container" align="center">
	<h2 style="margin-top: 100px; margin-bottom: 50px">Title</h2>

	<table class="w3-table-all w3-hoverable">
		<thead style="border: solid gray 2px">
			<tr class="w3-light-grey">
				<td><label>#</label></td>
				<td><label>Title</label></td>				
				<td><label>Date</label></td>				
			</tr>
		</thead>
		<tbody>
			<c:forEach var="one" items="${list }" >
				<tr>
					<td>${one.NUM }</td>
					<td>
						<c:choose>
							<c:when test="${login.CLASS=='master' }">
								<a href="/hw/admin/read/${one.NUM }">${one.TITLE }</a>
							</c:when>
							<c:otherwise>
								<a href="/hw/student/read/${one.NUM }">${one.TITLE }</a>
							</c:otherwise>
						</c:choose>						
					</td>				
					<td><fmt:formatDate value="${one.WRITEDATE }" pattern="MM / dd"/></td>					
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
		<c:if test="${login.CLASS=='master' }">
			<a href='/hw/admin/writeForm' ><input id='quizWrite' type="button" class="btn btn-default" value="글작성"/></a>
		</c:if>		
	</div>
	<div align="center">
		<input type="text" placeholder="검색" style="width: 20%; padding-left: 10px"/>&nbsp;&nbsp;
		<input type="button" class="btn btn-default" value="검색"/>
	</div>
</div>

<script>
	
</script>
