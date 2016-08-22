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

	String pr_no = request.getParameter("pr_no");//"B201000001";//request.getParameter("pr_no");//"B201000210";//
	String dt_no = request.getParameter("dt_no");//"2";//request.getParameter("dt_no");//"2";//
	String d_index = request.getParameter("d_index");
	String dt_count = request.getParameter("dt_count");
	String i_nowPage = request.getParameter("i_nowPage");
	String i_nowBlock = request.getParameter("i_nowBlock");

	Graph_Basic_Info basic_info = (Graph_Basic_Info)data_info.selectGraphBasicInfo(pr_no);	
	String x_unit = basic_info.getPL_BGI_X_AX_UNIT();
	String x_cal = basic_info.getPL_BGI_X_AX_CAL();
	String y_unit = basic_info.getPL_BGI_Y_AX_UNIT();
	String y_cal = basic_info.getPL_BGI_Y_AX_CAL();
	String y_comm = basic_info.getPL_BGI_Y_AX_COMM();

	
	Graph_Data_Info dt_info = data_info.selectGraphBasicData(pr_no, dt_no);
	//Graph_Data_Info dti = (Graph_Data_Info) dt_info.elementAt(1);
	//데이터 수정을 위한 변수 선언
	
	String t_xval =		dt_info.getPL_BGD_X_AX_VAL(); //0
	String t_yval =		dt_info.getPL_BGD_Y_AX_VAL(); //0
	String t_xerr =		dt_info.getPL_BGD_X_ERR(); //0
	String t_yerrmax =	dt_info.getPL_BGD_Y_ERR_MAX(); //0
	String t_yerrmin =	dt_info.getPL_BGD_Y_ERR_MIN(); //0
	String t_s_ratio =	dt_info.getPL_BGD_RATIO(); //-
	String t_s_press =	dt_info.getPL_BGD_PRESS(); //-
	String t_s_backdata =	dt_info.getPL_BGD_BACKUP_DATA(); //-

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>그래프 데이터 수정</title>
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

function modifyInfo(){ //데이터 정보 저장
	if(saveInfoCheck() == false){
		if(confirmMsg('0') == true){
			var form2 = document.form1;	
			form2.target = "_self";			
			form2.action = "pr_modify_graph_data_check.jsp";
			form2.submit();
		}
	}else{
		return;
	}	
}

function backInfo(){ //뒤로
	//self.close();
	var form2 = document.form1;
	if(confirmMsg('1') == true){
		form2.target = "_self";
		form2.action = "pr_insert_graph_data.jsp";
		form2.submit();
	}
}

function confirmMsg(number){
	var num = number;
	switch(num) {
		case '0': //정보 저장하기
			var result = confirm("수정하신 내용을 저장하시겠습니까?");
			return result;
			break;
		case '1': //작업종료
			var result = confirm("수정 작업을 취소하시겠습니까?");
			return result;
			break;
		default :		
			break;
	}		
}

function saveInfoCheck(){ //입력 데이터 유효성검증
	var form2 = document.form1;	
	var errorV = new Boolean(false); //에러를 나타내는 변수로 기본값은 false로 설정	
	//1. X단위 체크
	if(form2.x_val.value.length < 1){
		errorV = true;
		alert("X 값을 입력하세요!");
		form2.x_val.focus();
		return;
	}
	
	//1. Y단위 체크
	if(form2.y_val.value.length < 1){
		errorV = true;
		alert("Y 값을 입력하세요!");
		form2.y_val.focus();
		return;
	}
	return errorV;
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
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST" >
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
<input type="hidden" name="dt_no" value='<%=dt_no%>'>
<input type="hidden" name="xax_cal" value="<%=x_cal%>"/>
<input type="hidden" name="xay_cal" value="<%=y_cal%>"/>
<input type="hidden" name="dt_count" value='<%=dt_count%>'>
<input type="hidden" name="d_index" value='<%=d_index%>'>
<input type="hidden" name="i_nowPage" value="<%=i_nowPage%>"/>
<input type="hidden" name="i_nowBlock" value="<%=i_nowBlock%>"/>
<p class="level2">물성 그래프 데이터 수정</p>
<table class="tab2">
<col width="120">
<col width="530">

  <tr>
    <th class="thc">물성정보번호</th>
    <td class="tdl">
    <%
    	if(ComUtil.isNull(pr_no)){
    %>
    	<input type="text" name="pr_no" value="자동으로 선택됩니다." size="25" readonly> 
    <%
    	}else{
    %>
    	<input type="text" name="pr_no" value="<%=pr_no%>" size="25" readonly> 
    <%
    	}
    %> 
    </td>
  </tr>
</table>
<span class="help">
	X값과 Y값 실수형태로 입력하세요.<br>
</span>
<table class="tab2">
<col width="120">
<col width="205">
<col width="120">
<col width="205">
  <tr>
  	<th class="thr"><font color = red>X 값</font></th>
    <td class="tdl">
		<input type="text" name="x_val" value=<%=ComUtil.getStringByDecimalFormat(t_xval)%> size="15">X <%=ComUtil.getStringByDoubleFormat(x_cal)%>
	</td>
   	<th class="thr"><font color = red>Y 값</font></th>
    <td class="tdl">
		<input type="text" name="y_val" value=<%=ComUtil.getStringByDecimalFormat(t_yval)%> size="15">X <%=ComUtil.getStringByDoubleFormat(y_cal)%>
	</td>
  </tr>
  <tr>
	<th class="thr">X축 오차값</th>   
  	<td colspan="3"> 
  		<%
  			if(t_xerr.equalsIgnoreCase("0")){
  		%>
  			<input type="text" name="x_err" value="" size="25">		
  		<%  				
  			}else{
  		%>
  			<input type="text" name="x_err" value=<%=ComUtil.getStringByDecimalFormat(t_xerr)%> size="25">
  		<%
  			}
  		%> 	  
  	</td>
  <tr>
  <tr>
  	<th class="thr">Y축 최대오차값</th>
    <td class="tdl">
    	<%
  			if(t_yerrmax.equalsIgnoreCase("0")){
  		%>
  			<input type="text" name="y_err_max" value="" size="25">		
  		<%  				
  			}else{
  		%>
  			<input type="text" name="y_err_max" value=<%=ComUtil.getStringByDecimalFormat(t_yerrmax)%> size="25">
  		<%
  			}
  		%>
	</td>
   	<th class="thr">Y축 최소오차값</th>
    <td class="tdl">
    	<%
  			if(t_yerrmin.equalsIgnoreCase("0")){
  		%>
  			<input type="text" name="y_err_min" value="" size="25">		
  		<%  				
  			}else{
  		%>
  			<input type="text" name="y_err_min" value=<%=ComUtil.getStringByDecimalFormat(t_yerrmin)%> size="25">
  		<%
  			}
  		%>
	</td>
  </tr>
  <tr>
  	<th class="thr">생성비</th>
    <td class="tdl">
    	<%
  			if(t_s_ratio.equalsIgnoreCase("-")){
  		%>
  			<input type="text" name="ratio" value="" size="25">		
  		<%  				
  			}else{
  		%>
  			<input type="text" name="ratio" value=<%=t_s_ratio%> size="25">
  		<%
  			}
  		%>
	</td>
   	<th class="thr">압력</th>
    <td class="tdl">
    	<%
  			if(t_s_press.equalsIgnoreCase("-")){
  		%>
  			<input type="text" name="press" value="" size="25">		
  		<%  				
  			}else{
  		%>
  			<input type="text" name="press" value=<%=t_s_press%> size="25">
  		<%
  			}
  		%>
	</td>
  </tr>
  <tr>
  	<th class="thr">백업데이터</th>
    <td colspan="3"> 
		<%
  			if(t_s_backdata.equalsIgnoreCase("-")){
  		%>
  			<input type="text" name="back_data" value="" size="25">		
  		<%  				
  			}else{
  		%>
  			<input type="text" name="back_data" value=<%=t_s_backdata%> size="25">
  		<%
  			}
  		%>
	</td>
  </tr>
</table>
<div class="bbtn_c">
<a href="javascript:modifyInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('modifyImage','','images/img_cont/modify_upper.gif',1)"><img src="images/img_cont/modify_normal.gif" name="modifyImage" width="80" height="24" border="0" id="modifyImage" /></a>
<a href="javascript:backInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('backImage','','images/img_cont/back_upper.gif',1)"><img src="images/img_cont/back_normal.gif" name="backImage" width="80" height="24" border="0" id="backImage" /></a>
</div>
</form>
</body>
</html>