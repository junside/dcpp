<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.com.util.*"%>
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
	* �⺻ �׷��� ������ ���� DB�� �Է��ϴ� JSP ������
	* by Junside(J.H Park)
	*/
	//��� ���Ͽ� Boolean �Լ�
	boolean returnValue = false;	

	String pr_no = request.getParameter("pr_no");
	
	String g_index = request.getParameter("g_index");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function fn_success() {
	var form2 = document.form1;
	form2.target = "_self";
	form2.action = "pr_insert_graph_data.jsp";
	form2.submit();
}
function fn_fail(){
	history.back();	
}
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
<jsp:useBean id="inputControl" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<%	
		returnValue = inputControl.insertGraphBasicInfo(request);
		
		if(returnValue == true){
		%>
			<script language="javascript">
			alert('�׷��� �⺻ ������ �Է��߽��ϴ�.');
			fn_success();
			</script>
		<%
		}else{
		%>
			<script language="javascript">
			alert('�׷��� �⺻ ������ �Է¿� �����߽��ϴ�.');
			fn_fail();
			</script>
		<%
		}	
%>
</FORM>
</body>
</html>