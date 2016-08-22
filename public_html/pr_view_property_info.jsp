<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="property_info" class="nfri.dcpp.properties.business.Ctr_Property_Info_Process" scope="page"/>
<%
	/**
	* �⺻ ���� ������ Ȯ���ϴ� JSP ������(view)
	* by Junside(J.H Park)
	*/
	
	String pr_no = request.getParameter("pr_no");
	//String artcl_no = request.getParameter("artcl_no");
	
	//�⺻ ���� ��������
	Properties_Basic_Info spec_info = (Properties_Basic_Info)property_info.selectViewPropertySpecInfo(pr_no);
	//��з�
	String tb_value = spec_info.getPL_BI_TOP_BRANCH();
	//�����ͺз�
	String db_value = spec_info.getPL_BI_DATA_BRANCH();
	//�����μ���
	String mp_value = spec_info.getPL_BI_MAIN_PROC();
	//�����μ���
	String sp_value = spec_info.getPL_BI_SUB_PROC();
	//�浹���
	String imp_value = spec_info.getPL_BI_IMP_CLASS();
	//��������
	String exp_value = spec_info.getPL_BI_EXP_THE_REC();
	//ǥ���� ���� ��������
	String expression = spec_info.getPL_BI_EXPRESSION();//(String)property_info.selectEquationData(pr_no);
	//������ ��ȣ
	String artcl_no = spec_info.getPL_RAI_ARTCL_NUM();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>�ö�� ���� �⺻ ���� ����</title>
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

function confirmInfo(){ //Ȯ��
	self.close();
}
function viewArticleInfo(artcl_no){ //������ ����
	if(saveMsg('1') == true){
		window.open("pr_view_article_info.jsp?artcl_no="+artcl_no,"article_info","width=700,height=500,toolbar=no,location=no,status=no,scrollbar=auto");
		return;
	}
}
function saveMsg(number){
	var num = number;
	switch(num) {
		case '1': //������ ����
			var result = confirm("������ ������ Ȯ���Ͻðڽ��ϱ�?");
			return result;
			break;
		case '2': //��ġ���� ����
			var result = confirm("���� �׷��� ��ġ ������ Ȯ���Ͻðڽ��ϱ�?");
			return result;
			break;
		default :		
			break;
	}		
}
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<div id="content">
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
<input type="hidden" name="artcl_no" value='<%=artcl_no%>'>

<p class="level2">���� �⺻ ����</p>
<span class="help">
�������� Ȯ���Ͻ÷��� �������ȣ�� Ŭ���ϼ���.
</span><br>
<table class="tab5">
<col width="20%">
<col width="80%">
<tr>
    <th class="thr">����������ȣ</th>
    <td class="tdl"><%=pr_no%></td>
    </tr>
    <tr>
    <th class="thr">�������ȣ</th>
    <td class="tdl" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
    	<a href="javascript:void(0);"  onClick="viewArticleInfo('<%=artcl_no%>'); return false;"><%=artcl_no%></a>			    
    </td>
  </tr>
  <tr>
  	<th class="thr">��з�</th>
    <td class="tdl"><%=tb_value%></td>
  </tr>
  <tr>
   	<th class="thr">�����ͺз�</th>
   	<td class="tdl"><%=db_value%></td>
  </tr>
  <tr>
  	<th class="thr">�����μ���</th>
    <td class="tdl"><%=mp_value%></td>
  </tr>
  <tr>
   	<th class="thr">�����μ���</th>
    <td class="tdl"><%=sp_value%></td>
  </tr>
  <tr>
   	<th class="thr">�浹���</th>
   	<td class="tdl"><%=imp_value%></td>
  </tr>
  <tr>
   	<th class="thr">��������</th>
    <td class="tdl"><%=exp_value%></td>
  </tr>
  <tr>
    <th class="thr">���� ������ ǥ��</th>
    <td class="tdl"><%=expression%></td>
  </tr>
</table>
<div class="bbtn_c">
<a href="javascript:confirmInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('confirmImage','','images/img_cont/confirm_upper.gif',1)"><img src="images/img_cont/confirm_normal.gif" name="confirmImage" width="80" height="24" border="0" id="confirmImage" /></a>
</div>
</div>
</form>
<script type="text/javascript">
function init(){
	  var doc = document.getElementById('content');
	  if(doc.offsetHeight == 0){
	  } else {
		 //pagewidth = doc.offsetWidth;
		 pageheight = doc.offsetHeight + 90;
		 //alert(pageheight);
		 //parent.document.getElementById("content").height = pageheight+"px";
		 window.resizeTo(650,pageheight);
	  }
	}

window.onload = function(){
	  init();
}
</script>
</body>
</html>