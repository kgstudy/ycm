<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="t" items="${list }">
	<tr style="margin-left: 100px">
		<td>
			<p class="w3-tooltip">
				<label><input type="checkbox" id="${t.ID }" onclick="check(this)"/>&nbsp;<font id="${t.ID }_">${t.ID }</font>&nbsp;(${t.NAME })</label>
				<span style="position:absolute; left: 0; top: 20px" class="w3-text w3-tag">
					<font style="font-size: 10px">
						${t.PHONE } / ${t.EMAIL } / 
						<c:choose>
							<c:when test="${t.CLASS == null }">
								없음
							</c:when>
							<c:otherwise>
								${t.CLASS }
							</c:otherwise>
						</c:choose>
					</font>
				</span>
			</p>
		</td>
	</tr>
</c:forEach>