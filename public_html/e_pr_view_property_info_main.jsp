<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="property_info" class="nfri.dcpp.properties.business.Ctr_Property_Info_Process" scope="page"/>
<jsp:useBean id="graph_info" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<%
	/**
	* 기본 물성 정보를 확인하는 JSP 페이지(view)
	* by Junside(J.H Park)
	*/
	
	String pr_no = request.getParameter("pr_no");
	//String artcl_no = request.getParameter("artcl_no");
	
	//기본 정보 가져오기
	Properties_Basic_Info spec_info = (Properties_Basic_Info)property_info.selectViewPropertySpecInfo(pr_no);
	//대분류
	String tb_value = spec_info.getPL_BI_TOP_BRANCH();
	//데이터분류
	String db_value = spec_info.getPL_BI_DATA_BRANCH();
	//주프로세스
	String mp_value = spec_info.getPL_BI_MAIN_PROC();
	//부프로세스
	String sp_value = spec_info.getPL_BI_SUB_PROC();
	//충돌방식
	String imp_value = spec_info.getPL_BI_IMP_CLASS();
	//검증구분
	String exp_value = spec_info.getPL_BI_EXP_THE_REC();
	//표현식 정보 가져오기
	String expression = spec_info.getPL_BI_EXPRESSION();//(String)property_info.selectEquationData(pr_no);
	//참고문헌 번호
	String artcl_no = spec_info.getPL_RAI_ARTCL_NUM();
	
	Graph_Basic_Info basic_info = (Graph_Basic_Info)graph_info.selectViewGraphBasicInfo(pr_no);	
	String x_unit = basic_info.getPL_BGI_X_AX_UNIT();
	//String xax_cal = basic_info.getPL_BGI_X_AX_CAL();
	String y_unit = basic_info.getPL_BGI_Y_AX_UNIT();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>Information Viewer for Plasma Properties</title>
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
function viewArticleInfo(artcl_no){ //참고문헌 보기
	if(saveMsg('1') == true){
		window.open("e_pr_view_article_info_main.jsp?artcl_no="+artcl_no,"article_info","width=100%,height=100%,toolbar=no,location=no,status=no,scrollbar=auto");
		return;
	}
}
function saveMsg(number){
	var num = number;
	switch(num) {
		case '1': //참고문헌 보기
			var result = confirm("Would you like to see the information of a reference?");
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
<input type="hidden" name="pr_expression" value=""/>
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
<input type="hidden" name="artcl_no" value='<%=artcl_no%>'>
<p class="level2">Basic Information for Plasma Properties</p>
<table class="tab2">
<col width="150">
<col width="500">
<tr>
    <th class="thr">Prop. No.</th>
    <td class="tdl"><%=pr_no%></td>
    </tr>
    <tr>
    <th class="thr">Reference</th>
    <td class="tdl" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
    	<a href="javascript:void(0);"  onClick="viewArticleInfo('<%=artcl_no%>'); return false;">View</a>			    
    </td>
  </tr>
  <tr>
  	<th class="thr">Categorize</th>
    <td class="tdl"><%=tb_value%></td>
  </tr>
  <tr>
   	<th class="thr">Collision data categorize</th>
   	<td class="tdl"><%=db_value%></td>
  </tr>
  <tr>
  	<th class="thr">Collision process</th>
    <td class="tdl"><%=mp_value%></td>
  </tr>
  <tr>
   	<th class="thr">Sub process</th>
    <td class="tdl"><%=sp_value%></td>
  </tr>
  <tr>
   	<th class="thr">Collision type</th>
   	<td class="tdl"><%=imp_value%></td>
  </tr>
  <tr>
   	<th class="thr">Exp./Theory</th>
    <td class="tdl"><%=exp_value%></td>
  </tr>
  <tr>
    <th class="thr">Expression</th>
    <td class="tdl"><%=expression%></td>
  </tr>
  <tr>
  	<td colspan="2" class="tdc">
   	  <object id="ozviewer" width="645" height="500" CLASSID="CLSID:0DEF32F8-170F-46f8-B1FF-4BF7443F5F25">
            <param name="connection.servlet" value="http://dcpp.nfri.re.kr/oz/server">
            <param name="connection.reportname" value="/ozr_pr_view_graph.ozr">
			<param name="viewer.usestatusbar" value="false">	<!-- 아래쪽 상태표시줄을 사용하지 않습니다 -->
			<param name="viewer.viewmode" value="Fittocontents">  <!-- 뷰어 사이즈를 보고서  컨텐츠에 맞춥니다 -->
			<param name="viewer.bgcolor" value="ffffff">	<!-- 뷰어 백그라운드 색상을 지정합니다 -->
			<param name="viewer.useoutborder" value="false">	<!-- 외곽 테두리를 사용하지 않습니다 -->
			<param name="viewer.useinborder" value="false">	<!-- 내부 테두리를 사용하지 않습니다 -->
			<param name="toolbar.all" value="true">	<!-- 툴바를 사용 -->
			<param name="toolbar.about" value="false">
			<param name="toolbar.close" value="false">
			<param name="toolbar.file" value="false">
			<param name="toolbar.jpg" value="false">
			<param name="toolbar.pdf" value="false">
			<param name="toolbar.ppt" value="false">
			<param name="toolbar.tiff" value="false">
			<param name="toolbar.find" value="false">
			<param name="toolbar.help" value="false">
			<param name="toolbar.open" value="false">
			<param name="toolbar.page" value="false">
			<param name="toolbar.print" value="false">
			<param name="toolbar.save" value="false">
			<param name="toolbar.pagenavigator" value="false">
			<param name="toolbar.pageselection" value="false">
			<param name="toolbar.viewmode" value="true">
			<param name="toolbar.option" value="false">
            <param name="viewer.isFrame" value="false">
			<param name="viewer.Namespace" value="plasma\ozviewer">
			<param name="viewer.configmode" value="html">
            <param name="odi.odinames" value="odi_pr_view_graph">
			<param name="odi.odi_pr_view_graph.pcount" value="3">
			<param name="odi.odi_pr_view_graph.args1" value="PM_PRO_NO=<%=pr_no%>">
			<param name="odi.odi_pr_view_graph.args2" value="PM_X_TITLE=<%=x_unit%>">
			<param name="odi.odi_pr_view_graph.args3" value="PM_Y_TITLE=<%=y_unit%>">				
        </object>
     </td>
   </tr>
</table>
<div class="bbtn_c">
<a href="javascript:confirmInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('confirmImage','','images/img_cont/eng/confirm_upper.gif',1)"><img src="images/img_cont/eng/confirm_normal.gif" name="confirmImage" width="80" height="24" border="0" id="confirmImage" /></a>
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
		 window.resizeTo(660,pageheight);
	  }
	}

window.onload = function(){
	  init();
}
</script>
</body>
</html>