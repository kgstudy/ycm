<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<h3>�� �ۼ�</h3>
<hr/>
<form action="/question/qwrite">
<b>���� : </b><input type="text" name="title" required="required"/> <input type="checkbox" id="check" name="check"/>�����<br/>
<b>����</b><br/>
<textarea cols="80" rows="20" name="content" style="resize:none;" required="required"></textarea><br/>
<input type="submit" value="�� �����"/>
</form>