<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<h3>글 작성</h3>
<hr/>
<b>제목 : </b><input type="text" id="title"/><br/>
<b>내용</b> <input type="checkbox"/>비공개
<textarea></textarea><br/>
<input type="button" id="write" value="글 남기기"/>
<script>
	document.getElementById("write").addEventListener("click", function() {
		write();
	});
	function write() {
		
	}
</script>