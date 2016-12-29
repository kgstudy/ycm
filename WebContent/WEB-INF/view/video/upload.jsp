<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<br/><br/>
		<form action="/video/upload/OK" class="w3-container" method="post" enctype="multipart/form-data">
	  <h2>동영상 올리기</h2>
	
	  <p> 
	 
	  <input type="text" class="w3-input" name="title" style="width: 200px" placeholder="제목" required="required"/>
	 
	 
	
		<input type="file" id="fileup" style="display: none" name="file"/>	<br/>
	  <input type="button" class="w3-btn w3-white w3-border w3-border-blue w3-round-xlarge" 
	  id="fileup_bt" value="파일선택" style="padding: 5px" required="required"/>
		 <label id="filename" style="width: auto;"></label>
	  <p>    
	  <h3>내용</h3>
	  <textarea rows="10" cols="100" name="content" placeholder=" 1000자 이내" required="required"></textarea>
	  
	  <p>      
	  <button type="submit" id="OK" class="w3-btn w3-blue">등록</button></p>
</form>
<script>
  		$("#fileup_bt").click(function(){
  			$("#fileup").trigger("click");
  		});
  		
  		$("#fileup").change(function() {
  			
  			var t = $("#fileup").val();
  			$("#filename").html(t);
  		});
  
  		
  		
  		
  
  </script>
</body>
</html>

