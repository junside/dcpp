<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="nfri.dcpp.properties.business.Ctr_Part_Info_Process"%>
<%@ page import="nfri.dcpp.com.util.ComUtil"%>
<%@ page import="java.util.Enumeration"%>
<%@page import="nfri.dcpp.com.util.ComVar"%>

<%
	/**
	* �⺻ ���� ������ ���� DB�� �Է��ϴ� JSP ������
	* by Junside(J.H Park)
	*/
	//��� ���Ͽ� Boolean �Լ�
	boolean returnValue = false;	
	String returnMsg = "";
	String st_pr_no = request.getParameter("st_pr_no");
	String ed_pr_no = request.getParameter("ed_pr_no");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function init(returnMsg) {
	var form2 = document.form1;

	alert(returnMsg);

	//history.back();
	form2.target = "_self";
	form2.action = "pr_update_numeric_count.jsp;
	form2.submit();
	//self.close();
	//form2.target = "_self";
	//form2.action = "pr_modify_target_part_list.jsp";
	//form2.submit();
	
	//if(index == '1'){//����
	//	history.back();
	//}else if(index == '2'){//����
	//	form2.target = "_self";
	//	form2.action = "pr_modify_target_part_info_update.jsp?pr_no="+pr_no;
	//	form2.submit();
	//}else if(index == '3'){//����
	//	form2.target = "_self";
	//	form2.action = "pr_modify_target_part_list.jsp";
	//	form2.submit();	
	//}
	
}
</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">

<jsp:useBean id="graphControl" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<%	
	returnValue = graphControl.updateGraphDataTotalCountInfo(st_pr_no, ed_pr_no);
	
	

	if(returnValue == true){
		returnMsg = "��ġ ���� �Է¿� �����߽��ϴ�.";
		//pr_no = returnValue;
	}else{
		returnMsg = "��ġ ���� �Է¿� �����߽��ϴ�.";
	}
%>
</FORM>
<script language="javascript">
	init('<%=returnMsg%>');
</script>
</body>
</html>