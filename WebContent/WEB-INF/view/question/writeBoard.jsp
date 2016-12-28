<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding-top: 10%;">
	<h2 style="padding-left:5%;">${qBoard.TITLE }</h2>
	<b style="padding-left:5%;">글쓴이 : ${qBoard.WRITER }</b> (작성일;${qBoard.QDATE } / 조회수;${qBoard.COUNT })
	<hr/>
	<h3 style="padding-left:5%;">내용</h3><br/>
	<div style="padding-left:5%;">${qBoard.CONTENT }</div>
</div>
<hr/>
<div style="padding-left:5%;">
	<h4>댓글</h4>
	<b>${qBoard.WRITER }</b><br/>
	<textarea rows="3" placeholder="내용을 입력하세요.." cols="60%" style="resize:none; vertical-align:middle; "></textarea><input type="submit" style="height:72px; vertical_align:middle;" value="등록"/>
</div>