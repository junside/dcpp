<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<%
	/**
	* �ű� ���������� ������ (Excel����) �Է��ϴ� JSP ������(view)
	* by Junside(J.H Park)
	*/

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
	if(confirmMsg('0') == true){
		var form2 = document.form1;				
		if(checkFile() == true){
			alert("���Ϸ� ������ �Է½� DB�� ���� �Էµ˴ϴ�.\n������ ������ �����Ͻñ� �ٶ��ϴ�.");
			form2.action = "pr_insert_new_property_check.jsp?file=1";
			form2.encoding = "multipart/form-data";
			form2.submit();
		}else{
			alert("������ �����ϼž� �մϴ�.");
			return;
		}			
	}

}


function exitInfo(){ //����
	var form2 = document.form1;
	if(confirmMsg('2') == true){
		form2.target = "_self";
		form2.action = "pr_common_list.jsp";
		form2.submit();	
	}
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

function typcheck(typ, inp)
{
	 var lastidx = -1;
	 lastidx = inp.lastIndexOf('.');
	 var extension = inp.substring(lastidx+1, inp.length);
	 
	 if((lastidx != -1) && (extension.toLowerCase() == typ))
	  return true;
	 return false;
}


</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST" >


<p class="level2">�ű� ���� ������ Excel �Է�</p>
<span class="help">
	<font color = red> Excel ����(xls,csv)�� ����</font>
</span>
<table class="tab2">
<col width="100">
<col width="400">
  <tr>
  	<th class="thr">����������</th>   
  	<td colspan="3"> 
  	  <input type="file" name="g_data_file" size="50" value="">
  	</td>
  </tr>
</table>
<div class="bbtn_r">
<a href="javascript:saveInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('saveImage','','images/img_cont/save_upper.gif',1)"><img src="images/img_cont/save_normal.gif" name="saveImage" width="80" height="24" border="0" id="saveImage" /></a>
<a href="javascript:exitInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('exitImage','','images/img_cont/exit_upper.gif',1)"><img src="images/img_cont/exit_normal.gif" name="exitImage" width="80" height="24" border="0" id="exitImage" /></a>
</div>
</form>
</body>
</html>