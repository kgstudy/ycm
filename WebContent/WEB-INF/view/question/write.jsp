<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<h3>글 작성</h3>
<hr/>
<form action="/question/qwrite">
<b>제목 : </b><input type="text" name="title" required="required"/> <input type="checkbox" id="check" name="check"/>비공개<br/>
<b>내용</b><br/>
<textarea cols="80" rows="20" name="content" style="resize:none;" required="required"></textarea><br/>
<input type="submit" value="글 남기기"/>
</form>