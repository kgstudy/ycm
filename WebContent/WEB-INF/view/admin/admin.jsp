<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="w3-container" align="center" style="margin-top: 100px; margin-bottom: 50px">
	<div class="w3-container">
		<h2>Management</h2>
		
		<ul class="w3-navbar w3-black">
			<li><a href="javascript:void(0)" class="tablink w3-red" onclick="openTab(event, 'Homepage');"><font style="color: white" id="HomepageFont" class="font">Homepage</font></a></li>
			<li><a href="javascript:void(0)" class="tablink" onclick="openTab(event, 'Student');"><font style="color: red" id="StudentFont" class="font">Student</font></a></li>
			<li><a href="javascript:void(0)" class="tablink" onclick="openTab(event, 'Tokyo');"><font style="color: red" id="TokyoFont" class="font">Tokyo</font></a></li>
		</ul>
		
		<div id="Homepage" class="w3-container w3-border tab">
			<div class="w3-row">
				<div class="w3-col m3 l3 w3-padding w3-black" style="z-index: 3; font-weight: bold; height: 40%">
					<label class="w3-padding w3-hover-gray">
						<input type="checkbox" class="w3-check"/>&nbsp;&nbsp;
						<font style="color: white" id="homeM">Home</font>
					</label><br/>
					<label class="w3-padding w3-hover-gray">
						<input type="checkbox" class="w3-check"/>&nbsp;&nbsp;
						<font style="color: white" id="noticeM">공지사항</font>
					</label><br/>
					<label class="w3-padding w3-hover-gray">
						<input type="checkbox" class="w3-check"/>&nbsp;&nbsp;
						<font style="color: white" id="questionM">질문 게시판</font>
					</label><br/>
					<label class="w3-padding w3-hover-gray">
						<input type="checkbox" class="w3-check"/>&nbsp;&nbsp;
						<font style="color: white" id="homeworkM">과제 게시판</font>
					</label><br/>
					<label class="w3-padding w3-hover-gray">
						<input type="checkbox" class="w3-check"/>&nbsp;&nbsp;
						<font style="color: white" id="practiceM">코딩연습</font>
					</label><br/>
					<label class="w3-padding w3-hover-gray">
						<input type="checkbox" class="w3-check"/>&nbsp;&nbsp;
						<font style="color: white" id="storageM">자료실</font>
					</label><br/>
					<label class="w3-padding w3-hover-gray">
						<input type="checkbox" class="w3-check"/>&nbsp;&nbsp;
						<font style="color: white" id="videoM">동영상 자료실</font>
					</label><br/>
					<label onclick="dropM()" class="w3-padding w3-hover-gray">
						<input type="checkbox" class="w3-check"/>&nbsp;&nbsp;
						<font style="color: white" id="dropM">기수 게시판</font>
					</label><br/>
					<div id="classM" class="w3-dropdown-content w3-border" style='background-color: black'>
				    	<label class="w3-hover-gray">
				    		<input type="checkbox" class="w3-check"/>&nbsp;&nbsp;
				    		<font style="color: white" id="class_1M">1기</font>
						</label><br/>
						<label class="w3-hover-gray">
							<input type="checkbox" class="w3-check"/>&nbsp;&nbsp;
							<font style="color: white" id="class_2M">2기</font>
						</label><br/>
						<label class="w3-hover-gray">
							<input type="checkbox" class="w3-check"/>&nbsp;&nbsp;
							<font style="color: white" id="class_3M">3기</font>
						</label><br/>
				    </div>
				    <label class="w3-padding w3-hover-gray">
				    	<input type="checkbox" class="w3-check"/>&nbsp;&nbsp;
				    	<font style="color: white" id="adminM">관리자 전용</font>
				    	</label>
				</div>
				<div class="w3-col m4 l4">
					<h3>Content</h3>
				</div>
				<div class="w3-col m5 l5">
					form
				</div>
			</div>
		</div>
		
		<div id="Student" class="w3-container w3-border tab" style="display:none">
			<h2>Student</h2>
			<p>Paris is the capital of France.</p> 
		</div>
		
		<div id="Tokyo" class="w3-container w3-border tab" style="display:none">
			<h2>Tokyo</h2>
			<p>Tokyo is the capital of Japan.</p>
		</div>
	</div>
</div>

<script>
	var home = $("#home").html();
	var notice = $("#notice").html();
	var question = $("#question").html();
	var homework = $("#homework").html();
	var practice = $("#practice").html();
	var storage = $("#storage").html();
	var video = $("#video").html();
	var classes = new Array($("#class_1").html(), $("#class_2").html(), $("#class_3").html());
	var admin = $("#admin").html();
	window.onload = function(){
		
	};
	
	function openTab(evt, tabName) {
	  var i, x, tablinks, font;
	  x = document.getElementsByClassName("tab");
	  for (i = 0; i < x.length; i++) {
	      x[i].style.display = "none";
	  }
	  tablinks = document.getElementsByClassName("tablink");
	  for (i = 0; i < x.length; i++) {
	      tablinks[i].className = tablinks[i].className.replace(" w3-red", "");
	  }
	  font = document.getElementsByClassName("font");
	  for (i = 0; i < font.length; i++) {
		  font[i].style.color = "red";
	  }
	  document.getElementById(tabName).style.display = "block";
	  evt.currentTarget.className += " w3-red";
	  document.getElementById(tabName+"Font").style.color = "white";
	}
	
	function dropM(){
		var x = document.getElementById("classM");
	    if (x.className.indexOf("w3-show") == -1) {
	        x.className += " w3-show";
	    } else { 
	        x.className = x.className.replace(" w3-show", "");
	    }
	}
</script>