<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="w3-container" align="center">
	<h2 style="margin-top: 100px; margin-bottom: 50px">자료실</h2>

	<script>
		function getSelectValue(frm) {
			frm.textValue.value = frm.selectBox.options[frm.selectBox.selectedIndex].text;
			frm.optionValue.value = frm.selectBox.options[frm.selectBox.selectedIndex].value;
		}
	</script>
	<div align="left">
		<form action="/storage" id="ctg" method="post">
			<select name="mode" id="sel"
				style="width: 10%; height: 25px; border: 1px solid #ccc; border-radius: 5px">
				<option value="" ${storagemode eq ''?'selected':'' }>카테고리</option>
				<option value="설치파일" ${storagemode eq '설치파일'?'selected':'' }>설치파일</option>
				<option value="jar파일" ${storagemode eq 'jar파일'?'selected':'' }>jar파일</option>
			</select>
		</form>
	</div>

	<!-- <script>
		document.getElementById("sel").addfreeboardListener("change",
				function() {
					document.getElementById("ctg").submit();
				});
	</script> -->


	<table class="w3-table-all w3-hoverable">
		<thead style="border: solid gray 2px">
			<tr class="w3-light-grey">
				<td><label>#</label></td>
				<td><label>category</label></td>
				<td><label>Title</label></td>
				<td><label>Writer</label></td>
				<td><label>Date</label></td>
				<td><label>Download</label></td>
				<td><label>Count</label></td>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="storage" items="${storagedata }">
				<tr align="center">
					<td align="center"><label>${storage.NUM }</label></td>
					<td align="center"><label>${storage.CATEGORY }</label></td>
					<td><label>${storage.TITLE }</label></td>
					<td><label>${storage.ID }</label></td>
					<td><label>${storage.TIME }</label></td>
					<td><input type="button" value="내려받기" class="btn btn-default" onclick="down('${storage.TITLE }', '${storage.URL}')"/></td>
					<td><label>${storage.COUNT }</label></td>
			</c:forEach>
		</tbody>
	</table><br/>
	
	<div align="center">
		<label id="page"> <!-- 페이징 처리 --> <c:choose>
				<c:when test="${storagebestsize >= 5 }">
					<c:forEach var="i" begin="${storagesize-2}"
						end="${storagesize+2 }">
						<c:choose>
							<c:when test="${param.p == i }">
								<a style="color: red;"
									href="/storage?mode=${storagemode }&search=${storagesearch }&p=${i }">${i }</a>
							</c:when>
							<c:otherwise>
								<a
									href="/storage?mode=${storagemode }&search=${storagesearch }&p=${i }">${i }</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="i" begin="1" end="${storagesize }">
						<c:choose>
							<c:when test="${param.p == i }">
								<a style="color: red;"
									href="/storage?mode=${storagemode }&search=${storagesearch }&p=${i }&paging=${storagesize }">${i }</a>
							</c:when>
							<c:otherwise>
								<a
									href="/storage?mode=${storagemode }&search=${storagesearch }&p=${i }&paging=${storagesize }">${i }&nbsp;</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</label>
	</div>
	<c:if test="${login.CLASS == 'master' }">
		<div align="right">
			<form action="/storage/write" method="post">
			<input type="submit" value="글작성" class="btn btn-default" />
			</form>
		</div>
	</c:if>
	<div align="center">
		<input type="text" placeholder="검색" style="width: 20%; padding-left: 10px" id="searchText"/>&nbsp;&nbsp;
		<input type="button" class="btn btn-default" value="검색" id="search"/>
	</div>
</div>

<script>
	function down(title, url){
		$.ajax({
			type : "post",
			url : "/storage/count?title="+title,
			async : true,
			success : function(){
				location.href="/storage/down?url="+url;
			}
		});
	}
</script>