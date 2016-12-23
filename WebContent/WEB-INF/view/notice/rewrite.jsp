<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


<!-- 공지사항 글쓰기 버튼 눌렀을때 뷰 /  작성자는 관리자겠쥐 ~_~ <br/> -->
<form action="/notice/reinput/${noticeview.NOTICE_NUM }" method="post">
<b>제목 : </b><input type="text" name="title" required="required" value="${noticeview.NOTICE_TITLE}"><br/>
<b>내용  </b> <br/>
<textarea rows="15" cols="115" name="content" required="required">${noticeview.NOTICE_CONTENT }</textarea>

<input type="submit" class="btn btn-default" value="글등록"/>
</form>
