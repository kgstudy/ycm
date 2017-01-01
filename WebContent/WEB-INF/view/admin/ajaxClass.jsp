<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="t" items="${classes }">
	<tr>
		<td align="center">
			<label><input type="radio" name="classRadio" id="class${t }"/></label>
		</td>
		<td>
			<label onclick="$('#class${t }').prop('checked', true)"><font id="class${t }_">${t }</font></label>
		</td>
	</tr>
</c:forEach>
<tr>
	<td align="center" colspan="2">
		<i class="fa fa-arrow-up" onclick="position2(this)" id="up2"></i>&nbsp;
		<i class="fa fa-arrow-down" onclick="position2(this)" id="down2"></i>&nbsp;
		<input type="button" class="btn btn-default" value="적용"/><br/>
		<span id="menuRst2" style="display: none"><font style="color: red">메뉴를 선택해주세요.</font></span>
	</td>
</tr>