<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${qBoard.QCHECK==0 }">
		<div style="padding-top: 10%;">
			<h2>${qBoard.TITLE }</h2>
			<b>글쓴이 : ${qBoard.WRITER }</b> (작성일;${qBoard.QDATE } / 조회수;${qBoard.COUNT })
			<hr/>
			<b>내용</b><br/>
			${qBoard.CONTENT }
		</div>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${qBoard.WRITER==name }">
			</c:when>
		</c:choose>
	</c:otherwise>
</c:choose>
<div>
	
</div>