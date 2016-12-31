<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<div class="w3-container" align="center">
	<h2 style="margin-top: 100px; margin-bottom: 50px">Management</h2>
	<div class="w3-container" style="width: 100%;">
		<ul class="w3-navbar w3-black">
			<li><a href="javascript:void(0)" class="tablink w3-red" onclick="openTab(event, 'Student');"><font style="color: white" id="StudentFont" class="font">Student</font></a></li>
			<li><a href="javascript:void(0)" class="tablink" onclick="openTab(event, 'Menu');"><font style="color: red" id="MenuFont" class="font">Menu</font></a></li>
			<li><a href="javascript:void(0)" class="tablink" onclick="openTab(event, 'Tokyo');"><font style="color: red" id="TokyoFont" class="font">Tokyo</font></a></li>
		</ul>
		
		<div id="Student" class="w3-container w3-border tab">
			<div class="w3-row">
				<div class="w3-col s12 m4 l4" style="padding-top: 10px">
					<label>
						<font style="font-size: 20px">회원 리스트</font>
						(총 <font style="color: blue" id="total">${total }</font> 명)
					</label><br/>
					<div align="right">
						<select style="width: 120px; heigth: 5%" id="select">
							<option>이름순</option>
							<option>빈 class 만</option>
						</select>
					</div>
					<table style="width: 100%" id="memTable">
						<c:forEach var="t" items="${list }">
							<tr style="margin-left: 100px">
								<td>
									<p class="w3-tooltip">
										<label><input type="checkbox" id="${t.ID }" onclick="check(this)"/>&nbsp;<font id="${t.ID }_">${t.ID }</font>&nbsp;(${t.NAME })</label>
										<span style="position:absolute; left: 0; top: 20px" class="w3-text w3-tag">
											<font style="font-size: 10px">
												${t.PHONE } / ${t.EMAIL } / 
												<c:choose>
													<c:when test="${t.CLASS == null }">
														없음
													</c:when>
													<c:otherwise>
														${t.CLASS }
													</c:otherwise>
												</c:choose>
											</font>
										</span>
									</p>
								</td>
							</tr>
						</c:forEach>
					</table>
					<span id="memPage">
						<c:forEach var="t" begin="1" end="${size }">
							<label><a onclick="page(this)">${t }</a></label>
						</c:forEach>
					</span><br/>
				</div>
				<hr class="w3-hide-large w3-hide-medium" style="border: solid black 1px"/>
				<div class="w3-col s12 m4 l4" style="padding-top: 10px">
					<label><font style="font-size: 20px">그룹 지정</font></label><br/>
					<label>아이디</label><br/>
					<input type="text" readonly="readonly" id="g_id" style="width: 60%"/><br/><br/>
					<label>Class</label><br/>
					<input type="text" id="g_name" style="width: 60%"><br/><br/>
					<input type="button" value="확인" id="g_btn" class="btn btn-default"/><br/>
					<span id="g_btn_rst" style="display: none"></span><br/>
				</div>
				<hr class="w3-hide-large w3-hide-medium" style="border: solid black 1px"/>
				<div class="w3-col s12 m4 l4" style="padding-top: 10px">
					<label><font style="font-size: 20px">가입 요청 리스트</font></label><br/>
					<c:choose>
						<c:when test="${list2.size() > 0 }">
							<table style="width: 100%" id="joinMemTable">
								<tr>
									<td>
										<label><input type="checkbox" id="checkAll"/>&nbsp;전체선택</label>
									</td>
								</tr>
								<c:forEach var="t" begin="0" end="${list2.size()-1 }">
									<tr style="margin-left: 100px">
										<td>
											<p class="w3-tooltip">
												<label><input type="checkbox" id="${t }_" class="joinCheck"/>&nbsp;<font id="${t }__">${list2.get(t).ID }</font>&nbsp;(${list2.get(t).NAME })</label>
												<span style="position:absolute; left: 0; top: 20px" class="w3-text w3-tag">
													<font style="font-size: 10px">
														${list2.get(t).PHONE } / ${list2.get(t).EMAIL } / 
														<c:choose>
															<c:when test="${list2.get(t).CLASS == null }">
																없음
															</c:when>
															<c:otherwise>
																${list2.get(t).CLASS }
															</c:otherwise>
														</c:choose>
													</font>
												</span>
											</p>
										</td>
									</tr>
								</c:forEach>
								<tr>
									<td align="center">
										<c:forEach var="t" begin="1" end="${size2 }">
											<label><a onclick="page2(this)">${t }</a></label>
										</c:forEach>
									</td>
								</tr>
								<tr>
									<td align="center">
										<input type="button" class="btn btn-default" value="승인" id="joinAccept"/><br/>
										<span id="j_btn_rst" style="display: none"></span>
									</td>
								</tr>
							</table>
						</c:when>
						<c:otherwise>
							<br/>가입 요청 목록이 없습니다.
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		
		<div id="Menu" class="w3-container w3-border tab" style="display: none">
			<div class="w3-row">
				<div class="w3-col m4 l4" style="padding-top: 10px">
					<label><font style="font-size: 20px">위치변경</font></label><br/>
					<table id="menuTable">
						<c:forEach var="t" items="${menu }">
							<tr>
								<td>
									<label><input type="radio" name="menuRadio" id="menu${t }"/>&nbsp;<font id="menu${t }_">${t }</font></label>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td align="center">
								<i class="fa fa-arrow-up" onclick="position(this)" id="up"></i>&nbsp;
								<i class="fa fa-arrow-down" onclick="position(this)" id="down"></i>&nbsp;
								<input type="button" class="btn btn-default" value="적용"/><br/>
								<span id="menuRst" style="display: none"><font style="color: red">메뉴를 선택해주세요.</font></span>
							</td>
						</tr>
					</table><br/>
				</div>
				<hr class="w3-hide-large w3-hide-medium" style="border: solid black 1px"/>
				<div class="w3-col m4 l4" style="padding-top: 10px">
					<label><font style="font-size: 20px">추가</font></label><br/>
					<label>메뉴 이름</label><br/>
					<input type="text" style="width: 60%" id="newMenuName"/><br/><br/>
					<input type="button" class="btn btn-default" value="추가" id="addMenu"/><br/>
					<span id="addMenuSpan" style="display: none"><font style="color: red">메뉴 이름을 입력해주세요.</font></span><br/><br/>
					<label><font style="font-size: 20px">삭제</font></label><br/>
					<label>메뉴 이름</label><br/>
					<input type="text" style="width: 60%" id="removeMenuName"/><br/><br/>
					<input type="button" class="btn btn-default" value="삭제" id="removeMenu"/><br/>
					<span id="removeMenuSpan" style="display: none"><font style="color: red">메뉴 이름을 입력해주세요.</font></span><br/>
				</div>
				<hr class="w3-hide-large w3-hide-medium" style="border: solid black 1px"/>
				<div class="w3-col m4 l4" style="padding-top: 10px">
					<label><font style="font-size: 20px">Class</font></label><br/>
					<c:forEach var="i" begin="0" end="${classes.size()-1 }">
						<input type="checkbox" id="class${i }"/>&nbsp;${classes.get(i).NAME }<br/>
					</c:forEach>
				</div>
			</div>
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
	var menus = new Array();
	
	window.onload = function(){
		$.ajax({
			type : "post",
			url : "/admin/menu",
			async : false,
			success : function(txt){
				for(var i=0; i<txt.length; i++){
					menus[i] = $("#menu"+txt[i]);
				}
			}
		});
	}
	
	$("#removeMenu").click(function(){
		var name = $("#removeMenuName").val();
		var b = false;
		var html = "";
		for(var i=0; i<menus.length; i++){
			var id = menus[i].prop("id");
			id = id.substring(id.indexOf('u')+1);
			if($("#menu"+id+"_").html()==name){
				b = true;
				break;
			}
		}
		if(name.length!=0){
			if(b){
				$.ajax({
					type : "post",
					url : "/admin/removeMenu/"+name,
					async : false,
					success : function(txt){
						$("#menuTable").html(txt);
						menus = new Array();
						$.ajax({
							type : "post",
							url : "/admin/menu",
							async : false,
							success : function(txt){
								for(var i=0; i<txt.length; i++){
									menus[i] = $("#menu"+txt[i]);
								}
							}
						});
						menus[menus.length] = $("#menu"+name);
						$("#removeMenuSpan").hide();
						$("#removeMenuName").val("");
					}
				});
			} else {
				html = "<font style='color: red'>존재하지 않는 메뉴입니다.</font>";
				$("#removeMenuSpan").html(html);
				$("#removeMenuSpan").show();
			}
		} else {
			html = "<font style='color: red'>메뉴 이름을 입력해주세요.</font>";
			$("#removeMenuSpan").html(html);
			$("#removeMenuSpan").show();
		}
	});
	
	$("#addMenu").click(function(){
		var name = $("#newMenuName").val();
		var b = true;
		var html = "";
		for(var i=0; i<menus.length; i++){
			var id = menus[i].prop("id");
			id = id.substring(id.indexOf('u')+1);
			if($("#menu"+id+"_").html()==name){
				b = false;
				break;
			}
		}
		if(name.length!=0){
			if(b){
				$.ajax({
					type : "post",
					url : "/admin/menu/new/"+name,
					async : false,
					success : function(txt){
						$("#menuTable").html(txt);
						menus = new Array();
						$.ajax({
							type : "post",
							url : "/admin/menu",
							async : false,
							success : function(txt){
								for(var i=0; i<txt.length; i++){
									menus[i] = $("#menu"+txt[i]);
								}
							}
						});
						menus[menus.length] = $("#menu"+name);
						$("#newMenuName").val("");
						$("#addMenuSpan").hide();
					}
				});
			} else {
				html = "<font style='color: red'>이미 존재하는 메뉴입니다.</font>";
				$("#addMenuSpan").html(html);
				$("#addMenuSpan").show();
			}
		} else {
			html = "<font style='color: red'>메뉴 이름을 입력해주세요.</font>";
			$("#addMenuSpan").html(html);
			$("#addMenuSpan").show();
		}
	});
	
	function menuCommit(){
		location.href="/member/login";
	}
	
	function position(element){
		var arrow = element.id;
		var menu = "";
		var b = false;
		for(var i=0; i<menus.length; i++){
			if(menus[i].prop("checked")){
				menu = $("#"+menus[i].prop("id")+"_").html();
				b = true;
				$("#menuRst").hide();
				break;
			}
		}
		if(b){
			$.ajax({
				type : "post",
				url : "/admin/menu/"+menu+"/"+arrow,
				async : false,
				success : function(txt){
					$("#menuTable").html(txt);
					$.ajax({
						type : "post",
						url : "/admin/menu",
						async : false,
						success : function(txt){
							for(var i=0; i<txt.length; i++){
								menus[i] = $("#menu"+txt[i]);
							}
						}
					});
					$("#menu"+menu).prop("checked", true);
				}
			});
		} else {
			$("#menuRst").show();
		}
	}
	
	$("#select").change(function(){
		var select = $("#select").val();
		var table = $("#memTable");
		$.ajax({
			type : "post",
			url : "/admin/other/"+1+"/"+select,
			async : false,
			success : function(txt){
				table.html(txt);
				$.ajax({
					type : "post",
					url : "/admin/size/"+select,
					async : false,
					success : function(txt){
						var html = "";
						for(var i=0; i<txt; i++){
							html += "<label><a onclick='page(this)'>"+(i+1)+"</a></label>&nbsp;";
						}
						$("#memPage").html(html);
						$.ajax({
							type : "post",
							url : "/admin/total/"+select,
							async : false,
							success : function(txt){
								$("#total").html(txt);
							}
						});
					}
				});
			}
		});
	});
	
	function page(element){
		var table = $("#memTable");
		var page = element.innerHTML;
		$.ajax({
			type : "post",
			url : "/admin/other/"+page+"/"+$("#select").val(),
			async : false,
			success : function(txt){
				table.html(txt);
			}
		});
	}

	function page2(element){
		var table = $("#joinMemTable");
		var page = element.innerHTML;
		$.ajax({
			type : "post",
			url : "/admin/other/"+page,
			async : false,
			success : function(txt){
				table.html(txt);
			}
		});
	}
	
	$("#checkAll").click(function(){
		var check = $("#checkAll").prop("checked");
		$(".joinCheck").prop("checked", check);
	})
	
	$("#joinAccept").click(function(){
		var n = 0;
		var array = new Array();
		for(var i=0; i<${list2.size()}; i++){
			if($("#"+i+"_").prop("checked")){
				array[i] = $("#"+i+"__").html();
			} else {
				n++;
			}
		}
		if(n!=0){
			var html = "<font style='color: red'>선택된 항목이 없습니다.</font>";
			$("#j_btn_rst").html(html);
			$("#j_btn_rst").show();
		} else {
			$.ajax({
				type : "post",
				url : "/admin/accept/"+array,
				async : false,
				success : function(txt){
					if(txt==true){
						var html = "<font style='color: green'>승인되었습니다.</font>";
						$("#j_btn_rst").html(html);
						$("#j_btn_rst").show();
						setTimeout(function() {
							location.href="/admin";
						}, 700);
					} else {
						var html = "<font style='color: red'>승인에 실패하였습니다<br/>잠시후 다시 시도해주세요.</font>";
						$("#j_btn_rst").html(html);
						$("#j_btn_rst").show();
					}
				}
			});
		}
	});
	
	$("#g_btn").click(function(){
		var id = $("#g_id").val();
		var array = id.split(', ');
		var group = $("#g_name").val();
		if(id.length>0 && group.length>0){
			$.ajax({
				type : "post",
				url : "/admin/group/"+array+"/"+group,
				async : false,
				success : function(txt){
					if(txt==true){
						var html = "<font style='color: green'>그룹설정이 완료되었습니다.</font>";
						$("#g_btn_rst").html(html);
						$("#g_btn_rst").show();
						setTimeout(function() {
							location.href="/admin";
						}, 700);
					} else {
						var html = "<font style='color: red'>그룹설정에 실패하였습니다.<br/>잠시후 다시 시도해주세요.</font>";
						$("#g_btn_rst").html(html);
						$("#g_btn_rst").show();
					}
				}
			});
		} else {
			var html = "<font style='color: red'>이름 또는 class를 입력해주세요.</font>";
			$("#g_btn_rst").html(html);
			$("#g_btn_rst").show();
		}
	});
	
	function check(element){
		var id = element.id;
		id = $("#"+id+"_").html();
		var g_id = $("#g_id").val();
		if(g_id.indexOf(id)>=0){
			var r_g_id = "";
			if(g_id.indexOf(id)==0){
				r_g_id = g_id.substring(0, g_id.indexOf(id));
				r_g_id += g_id.substring(g_id.indexOf(id)+id.length+2);
			} else {
				r_g_id = g_id.substring(0, g_id.indexOf(id)-2);
				r_g_id += g_id.substring(g_id.indexOf(id)-2+id.length+2);
			}
			$("#g_id").val(r_g_id);
		} else {
			if(g_id.length==0){
				id = $("#"+id+"_").html();
			} else {
				id = ", "+$("#"+id+"_").html();
			}
// 			id += "<a onclick='delete(this)' id='"+id+"'>x</a>";
			$("#g_id").val(g_id+id);
		}
	}
	
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