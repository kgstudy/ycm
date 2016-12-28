<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tr>
	<td>
		<label><input type="checkbox" id="checkAll"/>&nbsp;전체선택</label>
	</td>
</tr>
<c:forEach var="t" begin="0" end="${list2.size()-1 }">
	<tr style="margin-left: 100px">
		<td>
			<p class="w3-tooltip">
				<label><input type="checkbox" id="${t }_" class="joinCheck"/>&nbsp;<font id="${t }__">${list2.get(t).ID }</font>&nbsp;(${list2.get(t).NAME })</label>
				<span style="position:absolute; left: 0; top: 20px" class="w3-text w3-tag">
					<font style="font-size: 10px">
						${list2.get(t).PHONE } / ${list2.get(t).EMAIL } / 
						<c:choose>
							<c:when test="${list2.get(t).CLASS == null }">
								없음
							</c:when>
							<c:otherwise>
								${list2.get(t).CLASS }
							</c:otherwise>
						</c:choose>
					</font>
				</span>
			</p>
		</td>
	</tr>
</c:forEach>
<tr>
	<td align="center">
		<c:forEach var="t" begin="0" end="${size2 }">
			<label><a onclick="page2(this)">${t+1 }</a></label>
		</c:forEach>
	</td>
</tr>
<tr>
	<td align="center">
		<input type="button" class="btn btn-default" value="승인" id="joinAccept"/><br/>
		<span id="j_btn_rst" style="display: none"></span>
	</td>
</tr>