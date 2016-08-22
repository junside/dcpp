<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="graph_info" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<%
	/**
	* 플라즈마 물성 그래프 단위 정보를 확인하는 JSP 페이지(view)
	* by Junside(J.H Park)
	*/
	
	String v_pr_no = request.getParameter("v_pr_no");
	
	Inf_Grd_Graph_Basic_Info basic_info = (Inf_Grd_Graph_Basic_Info)graph_info.selectViewInfGrdGraphInfo(v_pr_no);	
	String x_unit = basic_info.getPL_IGGI_X_AX_UNIT();
	String x_cal = basic_info.getPL_IGGI_X_AX_CAL();
	String y_unit = basic_info.getPL_IGGI_Y_AX_UNIT();
	String y_cal = basic_info.getPL_IGGI_Y_AX_CAL();
	String y_comm = basic_info.getPL_IGGI_Y_AX_COMM();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>등급유력 플라즈마 물성 정보의 그래프 단위 정보</title>
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

function confirmInfo(){ //확인
	self.close();
}

</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<div id="content">
<p class="level2">등급유력 플라즈마 물성 정보의 그래프 단위 정보</p>
<table class="tab2">
<col width="20%">
<col width="80%">
<tr>
    <th class="thr">등급유력번호</th>
    <td class="tdl"><%=v_pr_no%></td>
    </tr>
    <tr>
    <th class="thr">X 단위</th>
    <td class="tdl"><%=x_unit%></td>
  </tr>
  <tr>
  	<th class="thr">X 환산</th>
    <td class="tdl"><%=x_cal%></td>
  </tr>
  <tr>
   	<th class="thr">Y 단위</th>
   	<td class="tdl"><%=y_unit%></td>
  </tr>
  <tr>
  	<th class="thr">Y 환산</th>
    <td class="tdl"><%=y_cal%></td>
  </tr>
  <tr>
   	<th class="thr">Y 참고사항</th>
    <td class="tdl"><%=y_comm%></td>
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