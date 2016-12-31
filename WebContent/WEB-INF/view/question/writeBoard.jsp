<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding-top: 10%;">
	<h2 style="padding-left:5%;">${qBoard.TITLE }</h2>
	<b style="padding-left:5%;">글쓴이 : ${qBoard.WRITER }</b> (작성일;${qBoard.QDATE } / 조회수;${qBoard.COUNT })
	<hr/>
	<h3 style="padding-left:5%;">내용</h3><br/>
	<div style="padding-left:5%;">${qBoard.CONTENT }</div>
</div>
<hr/>
<%-- 댓글 --%>
<h3 style="padding-left:5%;">댓글</h3>

<%-- 댓글 작성 --%>
<c:if test="${login.NAME!=null }">
	<form action="/question/reply1">
	<div style="padding-left:5%;">
		<input type="hidden" name="qnum" value="${qBoard.NUM }"/>
		<input type="hidden" name="qrwriter" value="${login.NAME }"/><b>${login.NAME }</b><br/>
		<textarea rows="3" name="qrcontent" placeholder="내용을 입력하세요." cols="60%" style="resize:none; vertical-align:middle; "></textarea><input type="submit" style="height:72px; vertical_align:middle;" value="등록"/>
	</div>
	</form>
</c:if>
<br/><br/>
<%-- 댓글 목록 --%>
<c:forEach var="r" items="${qReply1 }">
	<div style="padding-left:5%;">
		<b>${r.QRWRITER }</b> (${r.QRDATE })
		<c:if test="${r.QRWRITER==login.NAME }">
			<input type="button" value="수정" id="${r.QRNUM }" kind="upR1"/>
			<input type="button" value="삭제" id="${r.QRNUM }" kind="delR1"/>
		</c:if>
		<br/>
		<div id="qrcontent_${r.QRNUM }">${r.QRCONTENT }</div>
		<div id="div${r.QRNUM }" style="display: none">
			<textarea id="ta${r.QRNUM }" rows="3" cols="60%" style="resize: none; vertical-align: middle">${r.QRCONTENT }</textarea>
			<input type="button" id="btn${r.QRNUM }" value="등록" class="btn btn-default"/>
		</div>
		<hr/>
	</div>
</c:forEach>

<script>
	//댓글 수정
	$("input[kind=upR1]").click(function() {
		updateR1($(this), $(this).attr("id"));
	});
	
	//댓글 삭제
	$("input[kind=delR1]").click(function() {
		alert($(this).attr("id"));
	})
	
	//댓글용 박스
// 	var replyBox = document.createElement("div");
// 		replyBox.innerHTML = "<textarea rows='3' cols='60%' id='replyBox' style='resize:none; vertical-align:middle;'></textarea>"
// 			+"<input type='button' id='upbt' style='height:72px; vertical_align:middle;' value='등록'/>";
	
	//수정 function
	function updateR1(upr1, id) {
// 		var replyBox = document.createElement("div");
// 		upr1.parent().append(replyBox);
// 		var tar = document.getElementById("qrcontent_"+id).innerHTML;
// 		replyBox.innerHTML = "<textarea rows='3' cols='60%' id='replyBox"+id+"' style='resize:none; vertical-align:middle;'>"+tar+"</textarea><input type='button' id='upbt' style='height:72px; vertical_align:middle;' value='등록'/>";
		$("#div"+id).show();
// 		document.getElementById("upbt").addEventListener("click", function() {
// 			alert(replyBox);
// 			alert(document.getElementById("replyBox"+id).innerHTML);
// 			$.ajax({
// 				method : "get",
// 				url : "/question/upReply1?qrcontent="+replyBox.innerHTML+"&qrnum="+upr1.attr("id")
// 			});
// 			location.reload();
// 		});
	}
	
	function update(element){
		
	}
</script>