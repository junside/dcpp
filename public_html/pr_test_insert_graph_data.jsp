<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="data_info" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<%
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
<script language = javascript>
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function saveInfo(){ //������ ���� ����
	var form2 = document.form1;				
	if(checkFile() == true){
		alert("���Ϸ� ������ �Է½� DB�� ���� �Էµ˴ϴ�.\n������ ������ �����Ͻñ� �ٶ��ϴ�.");
		form2.action = "pr_test_insert_graph_data_check.jsp?d_index=1&file=1";
		form2.encoding = "multipart/form-data";
	}else{
		alert("���� üũ!!!");
	}
	form2.submit();		
}
function checkFile(){
	var form2 = document.form1;	
	var checkV = new Boolean(false);
	if(form2.g_data_file.value.length < 1)
	{
		checkV = false;
	}else{
		checkV = true;
	}
	return checkV;
}
function confirmMsg(number){
	var num = number;
	switch(num) {
		case '0': //���� �����ϱ�
			var result = confirm("�Է��Ͻ� ������ �����Ͻðڽ��ϱ�?");
			return result;
			break;
		case '1': //�����ܰ�� �̵�
			var result = confirm("���� �ܰ�� �̵��Ͻðڽ��ϱ�?");
			return result;
			break;
		case '2': //�۾�����
			var result = confirm("�۾��� �����Ͻðڽ��ϱ�?");
			return result;
			break;
		case '3': //�����۾�
			var result = confirm("�����͸� �����Ͻðڽ��ϱ�?");
			return result;
			break;
		default :		
			break;
	}		
}

</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST" >
<p class="level2">���� �׷��� ������ �Է� �׽�Ʈ</p>
<span class="help">
	������ ÷���ϰ� �����ϸ� ȭ�鿡 �Է��Ͻ� �����ʹ� ���� �Էµ��� �ʰ� ÷������ ���븸 ����˴ϴ�.
</span>
<table class="tab2">
<col width="120">
<col width="205">
<col width="120">
<col width="205">

  <tr>
  	<th class="thr">��ġ����������</th>   
  	<td colspan="3"> 
  	  <input type="file" name="g_data_file" size="25" value=""><font color = red> Excel ����(xls,csv)�� ����</font>
  	</td>
  </tr>
</table>
<div class="bbtn_r">
<a href="javascript:saveInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('saveImage','','images/img_cont/save_upper.gif',1)"><img src="images/img_cont/save_normal.gif" name="saveImage" width="80" height="24" border="0" id="saveImage" /></a>
</div>
</form>
</body>
</html>