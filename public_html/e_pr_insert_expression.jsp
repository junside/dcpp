<%@ page language="java" contentType="text/html; charset=EUC-KR"  pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="nfri.dcpp.properties.db.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<%
request.setCharacterEncoding("EUC-KR");
String id_value = request.getParameter("id");
String exp_value = "";
%>
<%
	/**
	 * 수식을 입력하는 JSP 페이지
	 * by Junside(J.H Park)
	 */
	//String codeid = request.getParameter("code_id");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="./css/common.css" rel="stylesheet" type="text/css" />
<title>Insert Particle Expression</title>
</head>
<body>
<SCRIPT LANGUAGE="JavaScript">
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
	
function addExpression(){ //표현1 만드는 스크립트
	var form2 = document.form1;	
	var expression = document.getElementById("expression");
	var part = form2.part.value;
	if(part.length > 0)
	{
		expression.innerHTML = part;		
	}
	
	if(part.length == 0)
	{
		expression.innerHTML = "";
		
	}	
	form2.exp_value.value = part;
}

function clear(){
	var form2 = document.form1;
	form2.part.value = "";
	expression.innerHTML = "";
}
function sup(){
	var form2 = document.form1;
	var expression = document.getElementById("expression");
	var part = form2.part.value;
	if(part.length > 0)
	{		
		part = part + "A<sup>b</sup>";	
	}else if(part.length == 0)
	{
		part = "A<sup>b</sup>";		
	}
	
	form2.part.value = part;
	expression.innerHTML = part;
	form2.exp_value.value = part;
	return;
}

function sub(){
	var form2 = document.form1;
	var expression = document.getElementById("expression");
	var part = form2.part.value;
	if(part.length > 0)
	{		
		part = part + "A<sub>b</sub>";	
	}else if(part.length == 0)
	{
		part = "A<sub>b</sub>";		
	}
	
	form2.part.value = part;
	expression.innerHTML = part;
	form2.exp_value.value = part;
	return;
}

function sigma(){
	var form2 = document.form1;
	var expression = document.getElementById("expression");
	var part = form2.part.value;
	if(part.length > 0)
	{		
		part = part + "&sigma;";	
	}else if(part.length == 0)
	{
		part = "&sigma;";		
	}
	
	form2.part.value = part;
	expression.innerHTML = part;
	form2.exp_value.value = part;
	return;
}

function insert(){
	var form2 = document.form1;
	//var id_value = form2.id_num.value;
	var value_v = form2.exp_value.value;
	opener.form1.<%=id_value%>.value=value_v;	
	self.close();
	//opener.form1.<%=id_value%>.value=value;
	//if(id_value == 0){	
	//	opener.form1.part_1.value=value_v;
		
	//}else{
	//	opener.form1.part_2.value=value_v;
	//	self.close();
	//}
}

document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</SCRIPT>
<form name="form1" action="" method="POST">
<div id="content">
<input type="hidden" name="id_value" value="<%=id_value%>"/>
<input type="hidden" name="exp_value" value="<%=exp_value%>"/>
<p class="level2">Insert Expression of Plasma Properties Particle</p>
<span class="help">First, Select the Type and modify tag value.</span><br>
<table class="tab1">
	<col width="25%">
	<col width="25%">
	<col width="25%">
	<col width="25%">
	<tr height="40">
		<th class="thc">Select Type</th>	
		<td class="tdl"><a href="javascript:sup()">A<sup>b</sup>&nbsp;Type</a></td>
		<td class="tdl"><a href="javascript:sub()">A<sub>b</sub>&nbsp;Type</a></td>
		<td class="tdl"><a href="javascript:sigma()">&sigma;&nbsp;(sigma)</a></td>
	</tr>
	<tr height="40">
		<th class="thc">Particle</th>
		<td class="tdl" colspan="3">
			<input type='text' name='part' size='50' onkeyup="addExpression()" value=''>
			<a href="javascript:clear()">delete</a>
		</td>
	</tr>	
	<tr height="40">
		<th class="thc">Particle Expression</th>
  		<td class="tdl" id="expression" colspan="3"></td>  
	</tr>
</table>
<div class="bbtn_c"><a href="javascript:insert()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('insert','','images/img_cont/eng/insert_upper.gif',1)"><img src="images/img_cont/eng/insert_normal.gif" name="insert" width="80" height="24" border="0" id="insert" /></a></div>
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