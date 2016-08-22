<%@ page language="java" contentType="text/html; charset=EUC-KR"  pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="nfri.dcpp.properties.db.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<%request.setCharacterEncoding("EUC-KR");%>
<%
	/**
	 * 참고문헌 데이터를 검색하는 JSP 페이지
	 * by Junside(J.H Park)
	 */

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="./css/common.css" rel="stylesheet" type="text/css" />
<title>참고문헌 정보 조회</title>
</head>
<body>
<SCRIPT LANGUAGE="JavaScript">

if (window.addEventListener) {
	window.addEventListener("load", setupEvents, false);
}else if(window.attachEvent){
	window.attachEvent("onload", setupEvents);
}else{
	window.onload=setupEvents;
}

function setupEvents(evnt){
	document.form1.jour_title.onblur
}

function search(){ //검색
	//var form2 = document.form1;	
	//alert(document.form1.jour_title.value.length);

	var flag = false; //데이터가 없으면,

	if(document.form1.jour_title.value.length > 0){		
		//alert(document.form1.jour_title.value.length);
		flag = true;
		//return;
		/* alert("저널명을 입력하세요!!");
		document.form1.jour_title.focus();
		return; */
	}else{
		if(document.form1.jour_vol.value.length > 0){
			/* alert("볼륨을 입력하세요!!"); 
			document.form1.jour_title.focus();
			return; */
			//alert(document.form1.jour_vol.value.length);
			flag = true;
			//return;
		}else{
			if(document.form1.jour_st_page.value.length > 0){
				/* alert("저널명을 입력하세요!!");
				document.form1.jour_title.focus();
				return; */
				//alert(document.form1.jour_st_page.value.length);
				flag = true;
				//return;
			}else{
				if(document.form1.publish_year.value.length > 0){	
					/* alert("저널명을 입력하세요!!");
					document.form1.jour_title.focus();
					return; */
					///alert(document.form1.publish_year.value.length);
					flag = true;
					//return;
				}
			}
		}
	}
	
	if(flag == false){
		alert("검색항목에 한가지 이상을 입력하셔야 합니다.");
		return;
	}else{
		document.form1.action = "pr_article_search_to_assess_result.jsp";
		document.form1.submit();
	}
}

//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</SCRIPT>
<form name="form1" action="" method="POST">
<div id="content">
<span class="help">
			평가된 논문을 확인하기 위한 검색 페이지 입니다.
</span><br>
<span class="help">
	반드시 한가지 이상의 검색 항목을 입력하셔야 합니다.
</span>
<table class="tab2">
	<col width="25%">
	<col width="75%">
	<tr>
		<th class="thca">저널명</th>
		<td class="tdl">
			<input type='text' name='jour_title' size='55' value=''>
		</td>
	</tr>
	<tr>
		<th class="thca">볼륨</th>
		<td class="tdl">
			<input type='text' name='jour_vol' size='55' value=''>
		</td>
	</tr>
	<tr>
		<th class="thca">시작페이지</th>
		<td class="tdl">
			<input type='text' name='jour_st_page' size='55' value=''>
		</td>
	</tr>
	<tr>
		<th class="thca">출판연도</th>
		<td class="tdl" colspan="2">
			<input type='text' name='publish_year' size='55' value=''>
		</td>
	</tr>
</table>
<div class="bbtn_c">
	<a href="javascript:search();"> 조회 </a>
</div>
</div>
</form>
</body>
</html>