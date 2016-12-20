<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


공지사항 글쓰기 버튼 눌렀을때 뷰 /  작성자는 관리자겠쥐 ~_~ <br/>
<form action="/noinput" method="post">
<b>제목 : </b><input type="text" name="title" required="required"><br/>
<b>내용  </b> <br/>
<textarea rows="15" cols="115" name="content" required="required"></textarea>

<input type="submit" class="btn btn-default" value="글등록"/>
</form>
