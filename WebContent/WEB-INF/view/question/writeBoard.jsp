<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<c:forEach var="qb" items="${qBoard }">
		<h2>${qb.TITLE }</h2>
		<hr/>
		<b>�۾��� : ${qb.WRITER }</b> (�ۼ���;${qb.QDATE } / ��ȸ��;${qb.COUNT })<br/>
		<b>����</b><br/>
		${qb.CONTENT }
	</c:forEach>
</div>