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

	String pr_no = "B201100917";//B201100900 B201100917
	String user_id = "test_user";
	Vector dt_info = data_info.selectGraphBasicData(pr_no);
	int v_dt_count = dt_info.size();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>플라즈마 물성 수치 데이터 확인</title>
</head>
<body>
<script language = javascript>

function confirmInfo(){ //확인
	self.close();
}


</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST" >
<div id="content">
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
<input type="hidden" name="user_id" value='<%=user_id%>'>
<input type="hidden" name="v_dt_count" value='<%=v_dt_count%>'>
<p class="level2">물성 그래프 데이터 확인</p>
<table class="tab2">
<col width="120">
<col width="530">

  <tr>
    <th class="thc">물성정보번호</th>
    <td class="tdl"><%=pr_no%>  
    </td>
  </tr>
</table>

<p class="level2">물성 그래프 데이터</p>
<span class="count">
데이터 : <%=v_dt_count%>건 
</span>
<table class="tab2">
<col width="50">
<col width="70">
<col width="70">
<col width="100">
<col width="100">
<col width="100">
<col width="70">
<col width="70">
<tr>
   <th class="thc">
    	 No
   </th >
   <th class="thc">
     	X값
   </th>
   <th class="thc">
     	Y값
   </th><th class="thc">
   		X축오차
   </th>
   <th class="thc">
     	Y축 최대오차
   </th>
   <th class="thc">
     	Y축 최소오차
   </th>
   <th class="thc">
     	생성비
   </th>
   <th class="thc">
     	압력
   </th>
 </tr>
 <%
	  	for (int i = 0 ; i < v_dt_count; i++) {
			Graph_Data_Info dti = (Graph_Data_Info) dt_info.elementAt(i);
			String pro_no = dti.getPL_BI_DATA_NUM();
			String dt_no = dti.getPL_BGD_SEQ_NUM();
			String xval = dti.getPL_BGD_X_AX_VAL();
			String yval = dti.getPL_BGD_Y_AX_VAL();
			String xerr = dti.getPL_BGD_X_ERR();
			String yerrmax = dti.getPL_BGD_Y_ERR_MAX();
			String yerrmin = dti.getPL_BGD_Y_ERR_MIN();
			String s_ratio = dti.getPL_BGD_RATIO();
			String s_press = dti.getPL_BGD_PRESS();
			String s_backdata = dti.getPL_BGD_BACKUP_DATA();
			
	  		%>
	  		<tr onmouseover="this.bgColor='#E9E9F3'" onmouseout="this.bgColor='#FFFFFF'">
			    <td class="tdc">
			     	<A HREF="javascript:checkData('<%=dt_no%>')"><%=i+1%></A>
			    </td >
			    <td class="tdc">
			     	 <A HREF="javascript:checkData('<%=dt_no%>')"><%=xval%></A>
			    </td >
			    <td class="tdc">
			     	<A HREF="javascript:checkData('<%=dt_no%>')"><%=ComUtil.getStringByDoubleFormat(yval)%></A>
			    </td >
			    <td class="tdc">
			     	 <A HREF="javascript:checkData('<%=dt_no%>')"><%=ComUtil.getStringByDoubleFormat(xerr)%></A>
			    </td >
			    <td class="tdc">
			     	 <A HREF="javascript:checkData('<%=dt_no%>')"><%=ComUtil.getStringByDoubleFormat(yerrmax)%></A>
			    </td >
			    <td class="tdc">
			     	 <A HREF="javascript:checkData('<%=dt_no%>')"><%=ComUtil.getStringByDoubleFormat(yerrmin)%></A>
			    </td >
			    <td class="tdc">
			     	 <A HREF="javascript:checkData('<%=dt_no%>')"><%=s_ratio%></A>
			    </td >
			    <td class="tdc">
			     	 <A HREF="javascript:checkData('<%=dt_no%>')"><%=s_press%></A>
			    </td >
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
</body>
</html>