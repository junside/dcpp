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
	String pr_no = request.getParameter("pr_no");
	String flag = request.getParameter("flag");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function init(returnMsg, pr_no) {
	var form2 = document.form1;

	alert(returnMsg);

	//history.back();
	form2.target = "_self";
	form2.action = "pr_modify_target_part_info_update.jsp?pr_no="+pr_no;
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

<jsp:useBean id="propertyControl" class="nfri.dcpp.properties.business.Ctr_Property_Info_Process" scope="page"/>
<%	
	if("1".equalsIgnoreCase(flag)){ //����
		System.out.println("����");
		returnValue = propertyControl.updateEquationInfo(request);
	}else if("2".equalsIgnoreCase(flag)){ //�Է�
		System.out.println("�Է�");
		returnValue = propertyControl.insertEquationInfo(request);
	}
	

	if(returnValue == true){
		returnMsg = "Equation ���� ���濡 �����߽��ϴ�.";
		//pr_no = returnValue;
	}else{
		returnMsg = "Equation ���� ���濡 �����߽��ϴ�.";
	}
%>
</FORM>
<script language="javascript">
	init('<%=returnMsg%>', '<%=pr_no%>');
</script>
</body>
</html>