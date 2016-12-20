<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


공지제목 눌렀을때 공지사항 읽는뷰  <br/>

<b>제목 : </b>${noticeview.NOTICE_TITLE } <br/>
<b>조회수 : </b>${noticeview.NOTICE_COUNT} <br/>
<b>작성자 : </b>${noticeview.NOTICE_WRITER} <br/>
<b>내용  </b>${noticeview.NOTICE_CONTENT } <br/>



<hr/>
관리자로 들어왔을때 글삭제,글수정버튼 <br/>
<input type="button" class="btn btn-default" value="글수정" onclick="javascript:location.href='/notice/rewrite/${noticeview.NOTICE_NUM}'"/>
<input type="button" class="btn btn-default" value="글삭제" onclick="javascript:location.href='/notice/delete/${noticeview.NOTICE_NUM}'"/>