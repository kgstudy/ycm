<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<c:forEach var="qb" items="${qBoard }">
		<h2>${qb.TITLE }</h2>
		<hr/>
		<b>글쓴이 : ${qb.WRITER }</b> (작성일;${qb.QDATE } / 조회수;${qb.COUNT })<br/>
		<b>내용</b><br/>
		${qb.CONTENT }
	</c:forEach>
</div>