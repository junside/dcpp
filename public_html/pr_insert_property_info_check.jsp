<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="nfri.dcpp.properties.business.Ctr_Part_Info_Process"%>
<%@ page import="nfri.dcpp.com.util.ComUtil"%>
<%@ page import="java.util.Enumeration"%>
<%@page import="nfri.dcpp.com.util.ComVar"%>
<%
	//���� ������ Ȯ���ؼ� �α��� ������ ������ �α��� â���� �̵�
	String user_id = ComUtil.isNullToNullString((String)session.getAttribute("user_id"));
	String auth = ComUtil.isNullToNullString((String)session.getAttribute("auth"));
	//if(user_id == null || auth.equalsIgnoreCase("AU")){
		//System.out.println("i_user_id : " + user_id);
		//System.out.println("i_auth : " + auth);
		//response.sendRedirect("c_login.jsp?flag="+auth);
	//}
	if(user_id.equalsIgnoreCase(ComVar.STRING_NULL_B)){
		response.sendRedirect("c_login.jsp?flag=IU");			
	}else{
		if(!auth.equalsIgnoreCase("AIU") && !auth.equalsIgnoreCase("IAU") && !auth.equalsIgnoreCase("IU")){
			response.sendRedirect("c_login.jsp?flag=IU");
		}
	}
%>
<%
	/**
	* �⺻ ���� ������ ���� DB�� �Է��ϴ� JSP ������
	* by Junside(J.H Park)
	*/
	//��� ���Ͽ� Boolean �Լ�
	String returnValue = "false";	
	String returnMsg = "";
	String pr_no = request.getParameter("pr_no");
	
	String[] part_id = request.getParameterValues("part_id");
	String r_part_id = ComUtil.isNullToDashString(part_id[1]);
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

	if(pr_no != "false"){
		form2.target = "_self";
		form2.action = "pr_insert_graph_info.jsp?pr_no="+pr_no;
		form2.submit();
	}else{
		history.back();
	}
	
	//if(index == '1'){//����
	//	history.back();
	//}else if(index == '2'){//����
	//	form2.target = "_self";
	//	form2.action = "pr_insert_graph_info.jsp?pr_no="+pr_no;
	//	form2.submit();
	//}else if(index == '3'){//����
	//	form2.target = "_self";
	//	form2.action = "pr_common_list.jsp";
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
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
<jsp:useBean id="propertyControl" class="nfri.dcpp.properties.business.Ctr_Property_Info_Process" scope="page"/>
<jsp:useBean id="graphControl" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<%	
	if("-".equalsIgnoreCase(r_part_id)){ //ǥ������ ���� Null�� ������ �ٽ� ����
		returnMsg = "���ڰ� ����. ǥ������ ���� �ٽ� Ȯ�����ּ���.";
	}else{
		returnValue = propertyControl.insertPropertyInfo(request);

		if(!returnValue.equalsIgnoreCase("false")){
			returnMsg = "�������� �Է¿� �����߽��ϴ�.";
			pr_no = returnValue;
		}else{
			returnMsg = "�������� �Է¿� �����߽��ϴ�.";
		}
	}
	//returnValue = propertyControl.insertPropertyInfo(request);

	//if(!returnValue.equalsIgnoreCase("false")){
	//	returnMsg = "�������� �Է¿� �����߽��ϴ�.";
	//	pr_no = returnValue;
	//}else{
	//	returnMsg = "�������� �Է¿� �����߽��ϴ�.";
	//}
%>
</FORM>
<script language="javascript">
	init('<%=returnMsg%>','<%=pr_no%>');
</script>
</body>
</html>