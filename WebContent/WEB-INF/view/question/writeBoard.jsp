<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${qBoard.QCHECK==0 }">
		<div style="padding-top: 10%;">
			<h2>${qBoard.TITLE }</h2>
			<b>�۾��� : ${qBoard.WRITER }</b> (�ۼ���;${qBoard.QDATE } / ��ȸ��;${qBoard.COUNT })
			<hr/>
			<b>����</b><br/>
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