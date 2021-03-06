<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="property_info" class="nfri.dcpp.properties.business.Ctr_Inf_Grd_Property_Info_Process" scope="page"/>
<%
	/**
	* 기본 물성 정보를 확인하는 JSP 페이지(view)
	* by Junside(J.H Park)
	*/
	
	String v_pr_no = request.getParameter("v_pr_no");
	//String artcl_no = request.getParameter("artcl_no");
	
	//기본 정보 가져오기
	Inf_Grd_Properties_Basic_Info spec_info = (Inf_Grd_Properties_Basic_Info)property_info.selectViewInfGrdPropertyInfo(v_pr_no);
	//String data_num =  spec_info.getPL_IGBI_DATA_NUM();
	String mp_value = spec_info.getPL_IGBI_MAIN_PROC();
	String sp_value = spec_info.getPL_IGBI_SUB_PROC();
	String expression = spec_info.getPL_IGBI_EXPRESSION();
	String user = spec_info.getPL_UI_ID();
	String dt_list = spec_info.getPL_IGBI_DATA_NUM_LIST();
	
	String pt_list = property_info.makeReferenceLink(dt_list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>등급유력 물성  기본 정보 보기</title>
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
function viewPropertyInfo(pr_no){ //물성정보 보기
	if(saveMsg('1') == true){
		window.open("pr_view_property_info.jsp?pr_no="+pr_no,"article_info","width=700,height=500,toolbar=no,location=no,status=no,scrollbar=auto");
		return;
	}
}

function viewBasicInfo(pr_no){ //기본물성정보 보기
	if(saveMsg('1') == true){
		window.open("pr_view_property_info.jsp?pr_no="+pr_no,"property_info","width=500,height=400,toolbar=no,location=no,status=no");
		return;
	}
}
function saveMsg(number){
	var num = number;
	switch(num) {
		case '1': //물성정보 보기
			var result = confirm("물성 정보를 확인하시겠습니까?");
			return result;
			break;
		default :		
			break;
	}		
}

document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<div id="content">
<p class="level2">등급유력 물성  기본 정보</p>
<table class="tab2">
<col width="20%">
<col width="80%">
<tr>
    <th class="thr">등급유력번호</th>
    <td class="tdl"><%=v_pr_no%></td>
    </tr>
  <tr>
  	<th class="thr">주프로세스</th>
    <td class="tdl"><%=mp_value%></td>
  </tr>
  <tr>
   	<th class="thr">부프로세스</th>
    <td class="tdl"><%=sp_value%></td>
  </tr>
  <tr>
    <th class="thr">물성 반응식 표현</th>
    <td class="tdl"><%=expression%></td>
  </tr>
   <tr>
    <th class="thr">관련 물성번호</th>
    <td class="tdl" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
    	<%=pt_list%>			    
    </td>
  </tr>
  <tr>
    <th class="thr">평가자</th>
    <td class="tdl"><%=user%></td>
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