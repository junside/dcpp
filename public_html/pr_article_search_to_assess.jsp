<%@ page language="java" contentType="text/html; charset=EUC-KR"  pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="nfri.dcpp.properties.db.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<%request.setCharacterEncoding("EUC-KR");%>
<%
	/**
	 * ������ �����͸� �˻��ϴ� JSP ������
	 * by Junside(J.H Park)
	 */

%>
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
	//var form2 = document.form1;	
	//alert(document.form1.jour_title.value.length);

	var flag = false; //�����Ͱ� ������,

	if(document.form1.jour_title.value.length > 0){		
		//alert(document.form1.jour_title.value.length);
		flag = true;
		//return;
		/* alert("���θ��� �Է��ϼ���!!");
		document.form1.jour_title.focus();
		return; */
	}else{
		if(document.form1.jour_vol.value.length > 0){
			/* alert("������ �Է��ϼ���!!"); 
			document.form1.jour_title.focus();
			return; */
			//alert(document.form1.jour_vol.value.length);
			flag = true;
			//return;
		}else{
			if(document.form1.jour_st_page.value.length > 0){
				/* alert("���θ��� �Է��ϼ���!!");
				document.form1.jour_title.focus();
				return; */
				//alert(document.form1.jour_st_page.value.length);
				flag = true;
				//return;
			}else{
				if(document.form1.publish_year.value.length > 0){	
					/* alert("���θ��� �Է��ϼ���!!");
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
		alert("�˻��׸� �Ѱ��� �̻��� �Է��ϼž� �մϴ�.");
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
			�򰡵� ���� Ȯ���ϱ� ���� �˻� ������ �Դϴ�.
</span><br>
<span class="help">
	�ݵ�� �Ѱ��� �̻��� �˻� �׸��� �Է��ϼž� �մϴ�.
</span>
<table class="tab2">
	<col width="25%">
	<col width="75%">
	<tr>
		<th class="thca">���θ�</th>
		<td class="tdl">
			<input type='text' name='jour_title' size='55' value=''>
		</td>
	</tr>
	<tr>
		<th class="thca">����</th>
		<td class="tdl">
			<input type='text' name='jour_vol' size='55' value=''>
		</td>
	</tr>
	<tr>
		<th class="thca">����������</th>
		<td class="tdl">
			<input type='text' name='jour_st_page' size='55' value=''>
		</td>
	</tr>
	<tr>
		<th class="thca">���ǿ���</th>
		<td class="tdl" colspan="2">
			<input type='text' name='publish_year' size='55' value=''>
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