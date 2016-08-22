<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="graph_info" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<%
	/**
	* 평가 데이터를 출력하는 JSP 페이지(view)
	* by Junside(J.H Park)
	*/

	String pr_no = request.getParameter("pr_no");

	Graph_Basic_Info basic_info = (Graph_Basic_Info)graph_info.selectViewGraphBasicInfo(pr_no);	
	String x_unit = basic_info.getPL_BGI_X_AX_UNIT();
	String x_cal = basic_info.getPL_BGI_X_AX_CAL();
	String y_unit = basic_info.getPL_BGI_Y_AX_UNIT();
	String y_cal = basic_info.getPL_BGI_Y_AX_CAL();
	String y_comm = basic_info.getPL_BGI_Y_AX_COMM();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>평가 문서 출력</title>
</head>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<body>
<OBJECT width = "0" height = "0"
ID="ZTransferX" CLASSID="CLSID:C7C7225A-9476-47AC-B0B0-FF3B79D55E67" 
codebase="http://dcpp.nfri.re.kr/oz/viewer/ZTransferX.cab#version=2,2,0,8">            <!-- 뷰어를 설치하는 ZTransferX위치,버전 지정 -->
<PARAM NAME="download.Server" VALUE="http://dcpp.nfri.re.kr/oz/viewer/">               <!-- 뷰어 위치 지정 (포트제외) -->
<PARAM NAME="download.Port" VALUE="80">                                         <!-- 뷰어 위치의 포트 지정 -->
<PARAM NAME="download.Instruction" VALUE="ozviewer.idf">                       <!-- 뷰어 버전 정보 파일 -->
<PARAM NAME="install.Base" VALUE="<PROGRAMS>/Forcs">
<PARAM NAME="install.Namespace" VALUE="plasma">                          <!-- 로컬에 설치할 뷰어이름 -->
</OBJECT>
<form name="form1" action="" method="POST" >
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
<object id="ozviewer" width="800" height="900" CLASSID="CLSID:0DEF32F8-170F-46f8-B1FF-4BF7443F5F25">
            <param name="connection.servlet" value="http://dcpp.nfri.re.kr/oz/server">
            <param name="connection.reportname" value="/ozr_pr_print_first_assess_info_ex.ozr">
			<param name="viewer.usestatusbar" value="false">	<!-- 아래쪽 상태표시줄을 사용하지 않습니다 -->
			<param name="viewer.viewmode" value="normal">  <!-- 뷰어 사이즈를 보고서  컨텐츠에 맞춥니다 -->
			<param name="viewer.bgcolor" value="ffffff">	<!-- 뷰어 백그라운드 색상을 지정합니다 -->
			<param name="viewer.useoutborder" value="false">	<!-- 외곽 테두리를 사용하지 않습니다 -->
			<param name="viewer.useinborder" value="false">	<!-- 내부 테두리를 사용하지 않습니다 -->
			<param name="viewer.zoom" value="100">	<!-- 확대비율 -->
			<param name="toolbar.all" value="true">	<!-- 툴바를 사용 -->
			<param name="toolbar.about" value="false">
			<param name="toolbar.close" value="false">
			<param name="toolbar.file" value="true">
			<param name="toolbar.jpg" value="true">
			<param name="toolbar.pdf" value="true">
			<param name="toolbar.ppt" value="true">
			<param name="toolbar.tiff" value="true">
			<param name="toolbar.find" value="false">
			<param name="toolbar.help" value="false">
			<param name="toolbar.open" value="false">
			<param name="toolbar.page" value="false">
			<param name="toolbar.print" value="true">
			<param name="toolbar.save" value="false">
			<param name="toolbar.pagenavigator" value="true">
			<param name="toolbar.pageselection" value="true">
			<param name="toolbar.viewmode" value="true">
			<param name="toolbar.option" value="false">
            <param name="viewer.isFrame" value="false">
			<param name="viewer.Namespace" value="plasma\ozviewer">
			<param name="viewer.configmode" value="html">
            <param name="odi.odinames" value="odi_pr_print_first_assess_info">
			<param name="odi.odi_pr_print_first_assess_info.pcount" value="3">
			<param name="odi.odi_pr_print_first_assess_info.args1" value="PM_PRO_NO=<%=pr_no%>">
			<param name="odi.odi_pr_print_first_assess_info.args2" value="PM_X_TITLE=<%=x_unit%>">
			<param name="odi.odi_pr_print_first_assess_info.args3" value="PM_Y_TITLE=<%=y_unit%>">	
</object>
</form>
</body>
</html>