<%@ page language="java" contentType="text/html; charset=EUC-KR"  pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="nfri.dcpp.properties.db.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<%request.setCharacterEncoding("EUC-KR");%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="./css/common.css" rel="stylesheet" type="text/css" />
<title>������ ���� ��ȸ</title>
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

function search(){ //�˻�
	var form2 = document.form1;	
	//alert(form2.pr_no.value);
	var pr_no = form2.pr_no.value;
	var flag = false; //�����Ͱ� ������,

	if(form2.pr_no.value.length > 0){		
		//alert(document.form1.jour_title.value.length);
		flag = true;
		//return;
		/* alert("���θ��� �Է��ϼ���!!");
		document.form1.jour_title.focus();
		return; */
	}
	
	if(flag == false){
		alert("�ݵ�� ������ȣ�� �Է��ϼž� �մϴ�.");
		return;
	}else{
		form2.target = "_self";
		form2.action = "pr_number_search_to_assess_result.jsp?pr_no="+pr_no;
		form2.submit();
	}
}

//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</SCRIPT>
<form name="form1" action="" method="POST">
<div id="content">
<span class="help">
	������ȣ�� ���� �Է��Ͽ� �ش� ������ ���� ���� ���ϱ� ���� ������ �Դϴ�.
</span><br>
<span class="help">
	�ݵ�� ������ȣ�� �Է��ϼž� �մϴ�.
</span>
<table class="tab2">
	<col width="25%">
	<col width="75%">
	<tr>
		<th class="thca">������ȣ</th>
		<td class="tdl">
			<input type='text' name='pr_no' size='55' value=''>
		</td>
	</tr>
</table>
<div class="bbtn_c">
	<a href="javascript:search();"> ��ȸ </a>
</div>
</div>
</form>
</body>
</html>