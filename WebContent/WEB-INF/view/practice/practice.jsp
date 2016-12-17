<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="w3-container" align="center">
	<h2 style="margin-top: 100px; margin-bottom: 50px">Practice Coding</h2>
	<div class="w3-row">
		<div class="w3-col l4 m3">
			<div class="w3-row">
				<input type="button" class="btn btn-default" value="생성" style="width: 40%" onclick="create()" id="newClassBtn"/>
			</div>
			<div class="w3-row" id="classGroup" align="center">
				<input type="button" class="btn btn-default" value="Main" style="width: 40%" onclick="viewClass(this)"/><br/>
<!-- 				<input type="button" class="btn btn-default" value="Controller" style="width: 40%" onclick="viewClass(this)"/><br/> -->
<!-- 				<input type="button" class="btn btn-default" value="Model" style="width: 40%" onclick="viewClass(this)"/><br/> -->
			</div>
		</div>
		<div class="w3-col l8 m9">
			<div class="w3-row" id="contentGroup">
				<textArea class="form-control Content" rows="25" style="resize: none; border: solid gray 2px" id="MainContent">
public String void(String[] args){}
				</textArea>
			</div>
			<div class="w3-row">
				<textArea class="form-control" rows="9" style="resize: none; border: solid gray 2px" readonly="readonly"></textArea>
			</div>
		</div>
	</div>
</div>

<div class="w3-modal" id="modal" style="width: 100%; height: 100%" align="center">
	<div class="w3-row" style="width: 50%; background-color: white; border-radius: 15px; margin-top: 30px">
		<form class="form-group">
			<label style="font-size: 22px">CLASS NAME</label>
			<input type="text" placeholder="Class Name" class="form-control" id="className" style="width: 50%"/><br/>
			<input type="button" class="btn btn-default" value="생성" id="create"/>&nbsp;&nbsp;
			<input type="button" class="btn btn-default" value="취소" onclick="$('#modal').fadeOut(500)"/>
		</form>
	</div>
</div>

<script>
	function viewClass(element){
// 		$("#MainContent").hide();
		alert(element.value);
		var cls = element.value;
// 		$("#"+cls+"Content").show();
		var content = $("#"+cls+"Content").val();
		$.ajax({
			"method" : "get",
			"url" : "/practice/"+cls+"/"+content,
			"async" : false
		}).done(function(text){
			$("#"+cls+"Content").val(text);
		});
	}
	
	function create(){
		$("#newClassBtn").hide();
// 		$("#modal").fadeIn(500);
		var cg = $("#classGroup");
		var html = cg.html();
		var newClass = "<input type='text' placeholder='Class Name' style='width: 40%' id='newClass'/>";
		newClass += "<input type='button' value='생성' class='btn btn-default' id='create' onclick='commit()''/>";
		newClass += "<input type='button' value='취소' class='btn btn-default' onclick='cancel()' id='cancel'/><br id='br'/>";
		cg.html(html+newClass);
	}
	
	function commit(){
		$("#newClassBtn").show();
		var val = $("#newClass").val();
		var cg = $("#classGroup");
		var html = cg.html();
		var newClass = "<input type='button' class='btn btn-default' value='"+val+"' style='width: 40%' onclick='viewClass(this)''/><br/>";
		cg.html(html+newClass);
		var newContent = "<textArea class='form-control' rows='25' style='resize: none; border: solid gray 2px' id='"+val+"Content'>";
		newContent += "public String void(String[] args){}";
		newContent += "</textArea>";
		$("#"+val+"content").hide();
		$("#contentGroup").html(newContent);
		$("#newClass").remove();
		$("#create").remove();
		$("#cancel").remove();
		$("#br").remove();
	}
	
	function cancel(){
		$("#newClass").remove();
		$("#create").remove();
		$("#cancel").remove();
		$("#br").remove();
		$("#newClassBtn").show();
	}
</script>