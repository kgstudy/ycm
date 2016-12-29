<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- �������� �������� �������� �дº�  <br/> -->
<br/>
<b>���� : </b>${noticeview.NOTICE_TITLE } <br/>
<b>��ȸ�� : </b>${noticeview.NOTICE_COUNT} <br/>
<b>�ۼ��� : </b>${noticeview.NOTICE_WRITER} <br/>
<b>����  </b>${noticeview.NOTICE_CONTENT } <br/>



<hr/>
	<input type="button"  class="btn btn-default" value="���" onclick="javascript:location.href='/notice'"/>
<c:if test="${login.CLASS=='master'}">	
	<input type="button"  class="btn btn-default" value="�ۼ���" onclick="javascript:location.href='/notice/rewrite/${noticeview.NOTICE_NUM}'"/>
	<button type="button"  class="btn btn-default" onclick="deleteN('notice')" >�ۻ���</button>
<!-- 	data-toggle="modal" data-target="#myModal"  -->
</c:if>

<hr/>
<form action="/comment/input" method="post">
	<input type="hidden" name="num" value="${noticeview.NOTICE_NUM}"/> 
	<input type="hidden" name="writer" value="${login.NAME}">
	<input type="text" name="content" required="required"> <input type="submit"  class="btn btn-default" value="��۵��" />
</form> 
<div>
	<c:forEach var="i" items="${commentlist}">
	 �� �� �� :  ${i.COMMENT_WRITER}  (<fmt:formatDate value="${i.COMMENT_DATE}" pattern="yy/MM/dd" />)<br/>
		<font id="${i.COMMENT_NUM }_" size="4px">- ${i.COMMENT_CONTENT}</font>
		<input type="text" value="${i.COMMENT_CONTENT }" style="display: none" id="${i.COMMENT_NUM }"/>
			<c:if test="${login.NAME==i.COMMENT_WRITER}">
				<input type="button" class="btn btn-default" value="����" id="recom${i.COMMENT_NUM }" onclick="recom(this)">
				<input type="button" class="btn btn-default" value="�Ϸ�" id="finish${i.COMMENT_NUM }"  style="display: none" onclick="finish(this)" >
				<input type="button" class="btn btn-default" value="����" id="delete${i.COMMENT_NUM }" onclick="deleteN('${i.COMMENT_NUM}')">
			</c:if>
		<br/>
		<hr/>
	</c:forEach>
</div>


  <!-- �� ���� ��ư Modal -->
  <div class="w3-modal" id="myModal" style="display: none">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <span onclick="document.getElementById('myModal').style.display='none'" 
       			 class="w3-closebtn">&times;</span>
        </div>
        <div class="modal-body">
          <p>�����Ͻðڽ��ϱ�?</p>
        </div>
        <div class="modal-footer">
          <span id="buttonSpan"></span>
          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="document.getElementById('myModal').style.display='none'">���</button>
        </div>
      </div>
      
    </div>
  </div>
  <script>
  	function deleteN(txt){
  		var html = "";
  		if(txt=="notice"){
  			html = "<input type='button' class='btn btn-default' onclick='deleteYes()' value='Ȯ��'/>";
  		} else {
  			html = "<input type='button' class='btn btn-default' onclick='deleteF("+txt+")' value='Ȯ��'/>";
  		}
		$("#myModal").show();
		$("#buttonSpan").html(html);
  	}
  	
  	function deleteF(txt){
  		$("#myModal").hide();
  		location.href="/comment/delete/"+txt+"/${noticeview.NOTICE_NUM}";
  	}
  
  	function recom(element){
  		var id = element.id.substring(element.id.indexOf('m')+1);
  		$("#finish"+id).show();
  		$("#"+element.id).hide();
  		$("#delete"+id).hide();
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
  				$("#finish"+id).hide();
  				$("#"+id).hide();
  				$("#recom"+id).show();
  				$("#delete"+id).show();
  				$("#"+id+"_").html("- "+$("#"+id).val());
  				$("#"+id+"_").show();
  			}
  		});
  	}
  </script>
  
 <script>
 function deleteYes() {
	 	location.href="/notice/delete/${noticeview.NOTICE_NUM}";
	 }
</script>
