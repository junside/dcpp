<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="data_info" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<%
	/**
	* 그래프 데이터 정보를 입력하는 JSP 페이지(view)
	* by Junside(J.H Park)
	*/

	String pr_no = request.getParameter("pr_no");
	//String pr_no = "B201200144";
	
	Vector dt_info = data_info.selectGraphBasicData(pr_no);
	int v_dt_count = dt_info.size();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />


 <style type="text/css">
  span.tab{
    padding: 0 30px; /* Or desired space*/
  }
 </style>
<title>플라즈마 물성 수치 데이터 확인</title>
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

function selectBeforeBlock(v_nowBlock, v_nowPage)//이전 목록 가기
{
    var form2 = document.form1;
    form2.v_nowBlock.value = v_nowBlock;
    form2.v_nowPage.value = v_nowPage;
    form2.target = "_self";
    form2.action = "pr_view_graph_data.jsp";   
	form2.submit();	
}

function selectBlock(v_nowBlock, v_nowPage)//목록 가기
{
    var form2 = document.form1;
    form2.v_nowBlock.value = v_nowBlock;
    form2.v_nowPage.value = v_nowPage;
    form2.target = "_self";
    form2.action = "pr_view_graph_data.jsp"; 
	form2.submit();	
}

function selectAfterBlock(v_nowBlock, v_nowPage)//다음 목록 가기
{
    var form2 = document.form1;
    form2.v_nowBlock.value = v_nowBlock;
    form2.v_nowPage.value = v_nowPage;
    form2.target = "_self";
    form2.action = "pr_view_graph_data.jsp"; 
	form2.submit();	
}

</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST" >
<div id="content">
<input type="hidden" name="pr_no" value='<%=pr_no%>'>

<p class="level2">물성 수치 데이터</p>
<span class="count">
데이터 : <%=v_dt_count%>건 
</span>
<table class="tab7">
<col width="125">
<col width="225">
<tr>
    <th class="thr">물성정보번호 : </th>
    <td class="tdl"><%=pr_no%>  
    </td>
  </tr>
   	<tr>
    <th class="thl">
    X
    </th>
    <th class="thl">
    Y
    </th>
  </tr>
  <%
  	//수치정보 처리
  	for(int j=0; j < v_dt_count; j++){
  		Graph_Data_Info dti = (Graph_Data_Info) dt_info.elementAt(j);
		String pro_no = dti.getPL_BI_DATA_NUM();
		String dt_no = dti.getPL_BGD_SEQ_NUM();
		String xval = dti.getPL_BGD_X_AX_VAL();
		String yval = dti.getPL_BGD_Y_AX_VAL();
		
		//double t_yval = Math.log10(Double.valueOf(yval));
		//System.out.println(ComUtil.getStringByDoubleFormat(yval));
		
		String xerr = dti.getPL_BGD_X_ERR();
		String yerrmax = dti.getPL_BGD_Y_ERR_MAX();
		String yerrmin = dti.getPL_BGD_Y_ERR_MIN();
		String s_ratio = dti.getPL_BGD_RATIO();
		String s_press = dti.getPL_BGD_PRESS();
		String s_backdata = dti.getPL_BGD_BACKUP_DATA();
  		%>
  		<tr>
	    <td class="tdl">
	    <%=xval %>
	    </td>
	    <td class="tdl">
	    <%=yval%>
	    </td>
	  </tr>
  		<% 		
  	}
  %>	 
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