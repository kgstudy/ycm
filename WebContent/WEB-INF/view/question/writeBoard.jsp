<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding-top: 10%;">
	<h2 style="padding-left:5%;">${qBoard.TITLE }</h2>
	<b style="padding-left:5%;">�۾��� : ${qBoard.WRITER }</b> (�ۼ���;${qBoard.QDATE } / ��ȸ��;${qBoard.COUNT })
	<hr/>
	<h3 style="padding-left:5%;">����</h3><br/>
	<div style="padding-left:5%;">${qBoard.CONTENT }</div>
</div>
<hr/>
<div style="padding-left:5%;">
	<h4>���</h4>
	<b>${qBoard.WRITER }</b><br/>
	<textarea rows="3" placeholder="������ �Է��ϼ���.." cols="60%" style="resize:none; vertical-align:middle; "></textarea><input type="submit" style="height:72px; vertical_align:middle;" value="���"/>
</div>