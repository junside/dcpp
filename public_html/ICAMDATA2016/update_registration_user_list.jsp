<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="nfri.icamdata.common.Ctr_Icamdata_User_Info_Process"%>
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
function gotoSite() {
	
	var form2 = document.form1;
	form2.target = "_self";
	form2.action = "./registration_list.jsp";
	form2.submit();	
}
</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<jsp:useBean id="userInfo" class="nfri.icamdata.common.Basic_Icam_User_Info" scope="page"/>
<%	
Ctr_Icamdata_User_Info_Process inputControl = new Ctr_Icamdata_User_Info_Process();
	//��� ���Ͽ� Boolean �Լ�
	boolean returnValue = false;
	
	String returnMsg = "";
	
	String email = request.getParameter("email");
	String time = ComUtil.getTimeNow();
	
	System.out.println("Start");	
	returnValue = inputControl.updateUserStatus(email, time);
	
	if(returnValue!=false){
		%>
		<script>
			alert("���� ���� ����");
		</script>		
		<%		
		//returnMsg = "Thank you for your registration.\n Send confirm email to your email";
	}else{
		//returnMsg = "Registration fail. Please ask administrator.";
		%>
		<script>
			alert("���� ���� ����! �����ڿ��� ���ǿ��..");
		</script>		
		<%
	}
	%>
	<script>	
	gotoSite();
	</script>
%>
</FORM>
</body>
</html>