<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="t" items="${classes }">
	<tr>
		<td style="padding-left: 100px">
			<label><input type="radio" name="classRadio" id="class${t }" onchange="classCheck()"/></label>
			<label onclick="$('#class${t }').prop('checked', true), classCheck()"><font id="class${t }_">${t }</font></label>
		</td>
	</tr>
</c:forEach>