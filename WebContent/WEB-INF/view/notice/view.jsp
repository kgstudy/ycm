<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- �������� �������� �������� �дº�  <br/> -->

<b>���� : </b>${noticeview.NOTICE_TITLE } <br/>
<b>��ȸ�� : </b>${noticeview.NOTICE_COUNT} <br/>
<b>�ۼ��� : </b>${noticeview.NOTICE_WRITER} <br/>
<b>����  </b>${noticeview.NOTICE_CONTENT } <br/>



<hr/>
	<input type="button"  class="btn btn-default" value="���" onclick="javascript:location.href='/notice'"/>
<c:if test="${login.CLASS=='master'}">	
	<input type="button"  class="btn btn-default" value="�ۼ���" onclick="javascript:location.href='/notice/rewrite/${noticeview.NOTICE_NUM}'"/>
	<button type="button"  class="btn btn-default" data-toggle="modal" data-target="#myModal" >�ۻ���</button>
</c:if>

<hr/>
<form action="/comment/input" method="post">
	<input type="hidden" name="num" value="${noticeview.NOTICE_NUM}"/> 
	<input type="hidden" name="writer" value="${login.NAME}">
	<input type="text" name="content" required="required"> <input type="submit"  class="btn btn-default" value="��۵��" />
</form> 
<div>
	<c:forEach var="i" items="${commentlist}">
		<font id="${i.COMMENT_NUM }_">- ${i.COMMENT_CONTENT}</font> 
		<input type="text" value="${i.COMMENT_CONTENT }" style="display: none" id="${i.COMMENT_NUM }"/>
			<c:if test="${login.ID==i.COMMENT_WRITER}">
				<input type="button" class="btn btn-default" value="����" id="recom${i.COMMENT_NUM }" onclick="recom(this)">
				<input type="button" class="btn btn-default" value="�Ϸ�" id="finish${i.COMMENT_NUM }"  style="display: none" onclick="finish(this)" >
				<input type="button" class="btn btn-default" value="����" id="delete">
			</c:if>
		<br/>
	</c:forEach>
</div>


  <!-- �� ���� ��ư Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <p>�����Ͻðڽ��ϱ�?</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="javascript:deleteYes()">Ȯ��</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" >���</button>
        </div>
      </div>
      
    </div>
  </div>
  <script>
  	function recom(element){
  		var id = element.id.substring(element.id.indexOf('m')+1);
  		$("#finish"+id).show();
  		$("#"+element.id).hide();
  		$("#delete").hide();
  		$("#"+id).show();
  		$("#"+id+"_").hide();
  		/* alert($("#"+id).val()); */
  	}
  	
  	function finish(element){
  		var id = element.id.substring(element.id.indexOf('h')+1);
  		$.ajax({
  			type : "post",
  			url : "/comment/recom/"+$("#"+id).val()+"/"+id,
  			async : false,
  			success : function(txt){
  				alert(txt);
  			}
  		});
  	}
  </script>
  
 <script>
 function deleteYes() {
	 	location.href="/notice/delete/${noticeview.NOTICE_NUM}";
	 }
</script>
  
  