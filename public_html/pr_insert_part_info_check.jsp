<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="nfri.dcpp.properties.business.Ctr_Part_Info_Process"%>
<%@ page import="nfri.dcpp.com.util.ComUtil"%>
<%
	/**
	* �⺻ ���� ������ ���� DB�� �Է��ϴ� JSP ������
	* by Junside(J.H Park)
	*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="nfri.dcpp.com.util.ComUtil;"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function init(returnMsg) {
	var f = document.form1;
	alert(returnMsg);
	//�θ�â �ٽ� ���ε�
	opener.location.reload();
	//â �ݱ�
	parent.close();
}
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<jsp:useBean id="partInfo" class="nfri.dcpp.properties.model.Basic_Part_Info" scope="page"/>
<%	
	Ctr_Part_Info_Process inputControl = new Ctr_Part_Info_Process();
	//��� ���Ͽ� Boolean �Լ�
	boolean returnValue = false;
	
	String returnMsg = "";
	
	partInfo.setPL_CPBI_ELE_SYMBOL(request.getParameter("pr_part_symbol"));
	partInfo.setPL_CPBI_ELE_NAME(request.getParameter("pr_part_name"));
	partInfo.setPL_CPBI_ELE_MASS(request.getParameter("pr_part_mass"));	
	partInfo.setPL_CPBI_ELE_TYPE(request.getParameter("pr_part_type_code"));
	partInfo.setPL_CPBI_ELE_AMNUM(ComUtil.isNullToEmptyString(request.getParameter("pr_part_amnum")));
	partInfo.setPL_CPBI_ELE_AMCOUNT(ComUtil.isNullToEmptyString(request.getParameter("pr_part_amcount")));
		
	returnValue = inputControl.insertPartInfo(partInfo);
	
	if(returnValue!=false){
		returnMsg = "�������� �Է¿� �����߽��ϴ�.";
	}else{
		returnMsg = "�������� �Է¿� �����߽��ϴ�.";
	}
%>
</FORM>
<script language="javascript">
	init('<%=returnMsg%>');
</script>
</body>
</html>