<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 공지제목 눌렀을때 공지사항 읽는뷰  <br/> -->

<b>제목 : </b>${noticeview.NOTICE_TITLE } <br/>
<b>조회수 : </b>${noticeview.NOTICE_COUNT} <br/>
<b>작성자 : </b>${noticeview.NOTICE_WRITER} <br/>
<b>내용  </b>${noticeview.NOTICE_CONTENT } <br/>



<hr/>
	<input type="button"  class="btn btn-default" value="목록" onclick="javascript:location.href='/notice'"/>
<c:if test="${login.CLASS=='master'}">	
	<input type="button"  class="btn btn-default" value="글수정" onclick="javascript:location.href='/notice/rewrite/${noticeview.NOTICE_NUM}'"/>
	<button type="button"  class="btn btn-default" data-toggle="modal" data-target="#myModal" >글삭제</button>
</c:if>

  <!-- 글 삭제 버튼 Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <p>삭제하시겠습니까?</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="javascript:deleteYes()">확인</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" >취소</button>
        </div>
      </div>
      
    </div>
  </div>
  
 <script>
 function deleteYes() {
	 	location.href="/notice/delete/${noticeview.NOTICE_NUM}";
	 }
</script>
  
  