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
<!-- 
<script>
	var title = document.getElementById("title").value;
	var content = document.getElementById("content").value;
	var check = document.getElementById("chekc").value;
	document.getElementById("bt").addEventListener("click", function() {
		alert(title);
	})
	function write() {
		$.ajax({
			method : "get",
			url : "/question/qwrite?title="+title.value+"&content="+content.value+"check="+check.value
		}).done(function() {
			alert("���� �ۼ��Ǿ����ϴ�.");
			location.href="/question";
		})
	}
</script>
 -->