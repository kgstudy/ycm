<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


<!-- �������� �۾��� ��ư �������� �� /  �ۼ��ڴ� �����ڰ��� ~_~ <br/> -->
<form action="/notice/reinput/${noticeview.NOTICE_NUM }" method="post">
<b>���� : </b><input type="text" name="title" required="required" value="${noticeview.NOTICE_TITLE}"><br/>
<b>����  </b> <br/>
<textarea rows="15" cols="115" name="content" required="required">${noticeview.NOTICE_CONTENT }</textarea>

<input type="submit" class="btn btn-default" value="�۵��"/>
</form>
