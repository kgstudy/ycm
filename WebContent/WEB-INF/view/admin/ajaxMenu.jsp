<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="t" items="${menu }">
	<tr>
		<td style="padding-left: 80px">
			<label onclick="menuCheck()"><input type="radio" name="menuRadio" id="menu${t.MENU }"/>&nbsp;<font id="menu${t.MENU }_">${t.MENU }</font></label>
		</td>
	</tr>
</c:forEach>
<tr>
	<td align="center">
		<i class="fa fa-arrow-up" onclick="position(this)" id="up"></i>&nbsp;
		<i class="fa fa-arrow-down" onclick="position(this)" id="down"></i>&nbsp;
		<input type="button" class="btn btn-default" value="적용" onclick="location.href='/member/login'"/>
	</td>
</tr>