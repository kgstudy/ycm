<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="t" items="${menu }">
	<tr>
		<td>
			<label><input type="radio" name="menuRadio" id="menu${t }"/>&nbsp;<font id="menu${t }_">${t }</font></label>
		</td>
	</tr>
</c:forEach>
<tr>
	<td align="center">
		<i class="fa fa-arrow-up" onclick="position(this)" id="up"></i>&nbsp;
		<i class="fa fa-arrow-down" onclick="position(this)" id="down"></i>&nbsp;
		<input type="button" class="btn btn-default" value="적용" onclick="menuCommit()"/>
	</td>
</tr>