<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


�������� �������� �������� �дº�  <br/>

<b>���� : </b>${noticeview.NOTICE_TITLE } <br/>
<b>��ȸ�� : </b>${noticeview.NOTICE_COUNT} <br/>
<b>�ۼ��� : </b>${noticeview.NOTICE_WRITER} <br/>
<b>����  </b>${noticeview.NOTICE_CONTENT } <br/>



<hr/>
�����ڷ� �������� �ۻ���,�ۼ�����ư <br/>
<input type="button" class="btn btn-default" value="�ۼ���" onclick="javascript:location.href='/notice/rewrite/${noticeview.NOTICE_NUM}'"/>
<input type="button" class="btn btn-default" value="�ۻ���" onclick="javascript:location.href='/notice/delete/${noticeview.NOTICE_NUM}'"/>