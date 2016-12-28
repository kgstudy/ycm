<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="w3-container" align="center">
	<h2 style="margin-top: 100px; margin-bottom: 50px">질문 게시판</h2>

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
<c:forEach var="q" items="${qread }">
				<tr>
					<td>${q.NUM }</td>
					<td>
					<%-- 관리자 --%>
					<c:choose>
						<c:when test="${login.CLASS == 'master' }">
							<a href="/question/writeBoard/${q.NUM }"><b>${q.TITLE }</b></a>
						</c:when>
						<c:otherwise>
							<%-- 관리자 이 외 --%>
							<c:choose>
								<%-- 비밀글 체크 시 --%>
								<c:when test="${q.QCHECK == 1 }">
									<c:choose>
										<%-- 로그인한 이름과 작성자가 같을 때 --%>
										<c:when test="${login.NAME == q.WRITER }">
											<a href="/question/writeBoard/${q.NUM }"><b>${q.TITLE }</b></a>
										</c:when>
										<%-- 로그인한 이름과 같지 않을 때 --%>
										<c:otherwise>
											<b>${q.TITLE }</b>
										</c:otherwise>
									</c:choose>
								</c:when>
								<%-- 공개글일 때 --%>
								<c:when test="${q.QCHECK == 0 }">
									<a href="/question/writeBoard/${q.NUM }"><b>${q.TITLE }</b></a>
								</c:when>
							</c:choose>
						</c:otherwise>
					</c:choose>
					</td>
					<td>${q.WRITER }</td>
					<td>${q.QDATE }</td>
					<td>${q.COUNT }</td>
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
		<input type="button" class="btn btn-default" value="글작성" id="write"/>
	</div>
	<div align="center">
		<input type="text" placeholder="검색" style="width: 20%; padding-left: 10px"/>&nbsp;&nbsp;
		<input type="button" class="btn btn-default" value="검색"/>
	</div>
</div>
<script>
	document.getElementById("write").addEventListener("click", function() {
		location.href="/question/write";
	})
</script>