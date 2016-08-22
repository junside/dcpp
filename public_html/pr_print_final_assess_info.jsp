<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="property_assess_data" class="nfri.dcpp.properties.business.Ctr_Property_Assess_Info" scope="page"/>
<jsp:useBean id="inf_grd_property_data" class="nfri.dcpp.properties.business.Ctr_Inf_Grd_Property_Info_Process" scope="page"/>
<jsp:useBean id="ctr_graph" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<%
	/**
	* �� �����͸� ����ϴ� JSP ������(view)
	* by Junside(J.H Park)
	*/

	String v_pr_no = request.getParameter("v_pr_no");
	//�⺻ ���� ��������
	Inf_Grd_Graph_Basic_Info graph = (Inf_Grd_Graph_Basic_Info)ctr_graph.selectViewInfGrdGraphInfo(v_pr_no);
	
	//X�� ���� ǥ��
	String X_Title = graph.getPL_IGGI_X_AX_UNIT();
	//Y�� ���� ǥ��
	String Y_Title = graph.getPL_IGGI_Y_AX_UNIT();
	
	//1.������� �⺻ ���� ��������
	Inf_Grd_Properties_Basic_Info basic_info = (Inf_Grd_Properties_Basic_Info)inf_grd_property_data.selectViewInfGrdPropertyInfo(v_pr_no);
	 // �浹���
	String ic_option  = basic_info.getPL_IGBI_IMP_CLASS();
	// �����μ���
	String mp_value = basic_info.getPL_IGBI_MAIN_PROC();
	// �����μ���
	String sp_value = basic_info.getPL_IGBI_SUB_PROC();
	// ǥ����
	String expression = basic_info.getPL_IGBI_EXPRESSION();
	// �Է���
	String user = basic_info.getPL_UI_ID();
	// ������������Ʈ
	String dt_list = basic_info.getPL_IGBI_DATA_NUM_LIST();
	// �������� ����
	String dt_cnt = basic_info.getPL_IGBI_DATA_NUM_CNT();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>�� ���� ���</title>
</head>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<body>
<OBJECT width = "0" height = "0"
ID="ZTransferX" CLASSID="CLSID:C7C7225A-9476-47AC-B0B0-FF3B79D55E67" 
codebase="http://dcpp.nfri.re.kr/oz/viewer/ZTransferX.cab#version=2,2,0,8">            <!-- �� ��ġ�ϴ� ZTransferX��ġ,���� ���� -->
<PARAM NAME="download.Server" VALUE="http://dcpp.nfri.re.kr/oz/viewer/">               <!-- ��� ��ġ ���� (��Ʈ����) -->
<PARAM NAME="download.Port" VALUE="80">                                         <!-- ��� ��ġ�� ��Ʈ ���� -->
<PARAM NAME="download.Instruction" VALUE="ozviewer.idf">                       <!-- ��� ���� ���� ���� -->
<PARAM NAME="install.Base" VALUE="<PROGRAMS>/Forcs">
<PARAM NAME="install.Namespace" VALUE="plasma">                          <!-- ���ÿ� ��ġ�� ����̸� -->
</OBJECT>
<form name="form1" action="" method="POST" >
<input type="hidden" name="v_pr_no" value='<%=v_pr_no%>'>
<%
  		String whereCondtion = property_assess_data.getFinalCondition(dt_list);
%>
		<object id="ozviewer" width="800" height="900" CLASSID="CLSID:0DEF32F8-170F-46f8-B1FF-4BF7443F5F25">
            <param name="connection.servlet" value="http://dcpp.nfri.re.kr/oz/server">
            <param name="connection.reportname" value="/ozr_pr_print_final_assess_info_ex.ozr">
			<param name="viewer.usestatusbar" value="false">	<!-- �Ʒ��� ����ǥ������ ������� �ʽ��ϴ� -->
			<param name="viewer.viewmode" value="normal">  <!-- ��� ����� ����  �������� ����ϴ� -->
			<param name="viewer.bgcolor" value="ffffff">	<!-- ��� ��׶��� ������ �����մϴ� -->
			<param name="viewer.useoutborder" value="false">	<!-- �ܰ� �׵θ��� ������� �ʽ��ϴ� -->
			<param name="viewer.useinborder" value="false">	<!-- ���� �׵θ��� ������� �ʽ��ϴ� -->
			<param name="viewer.zoom" value="100">	<!-- Ȯ����� -->
			<param name="toolbar.all" value="true">	<!-- ���ٸ� ��� -->
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
            <param name="odi.odinames" value="odi_pr_print_final_assess_info">
			<param name="odi.odi_pr_print_final_assess_info.pcount" value="4">
			<param name="odi.odi_pr_print_final_assess_info.args1" value="PM_PRO_NO=<%=v_pr_no%>">
			<param name="odi.odi_pr_print_final_assess_info.args2" value="PM_X_TITLE=<%=X_Title%>">
			<param name="odi.odi_pr_print_final_assess_info.args3" value="PM_Y_TITLE=<%=Y_Title%>">
			<param name="odi.odi_pr_print_final_assess_info.args4" value="PM_WHERE_CONDITION=<%=whereCondtion%>">
		</object>
</form>
</body>
</html>