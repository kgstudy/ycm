<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<h3>글 작성</h3>
<hr/>
<b>제목 : </b><input type="text" id="title"/> <input type="checkbox"/>비공개<br/>
<b>내용</b><br/>
<textarea cols="80" rows="20" style="resize:none;"></textarea><br/>
<input type="button" id="write" value="글 남기기"/>
<script>
	document.getElementById("write").addEventListener("click", function() {
		write();
	});
	function write() {
		
	}
</script>