<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


<!-- �������� �۾��� ��ư �������� �� /  �ۼ��ڴ� �����ڰ��� ~_~ <br/> -->
<form action="/notice/input" method="post">
<b>���� : </b><input type="text" name="title" required="required"><br/>
<b>�ۼ��� : </b>${login.NAME}<input type="hidden" name="writer" value="${login.NAME}"><br/>
<b>����  </b> <br/>
<textarea rows="15" cols="115" name="content" required="required" style="resize: none;"></textarea>

<input type="button"  class="btn btn-default" value="���" onclick="javascript:location.href='/notice'"/>
<input type="submit" class="btn btn-default" value="�۵��"/>
</form>
