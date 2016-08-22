<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="nfri.icamdata.common.Ctr_Icamdata_User_Info_Process"%>
<%@ page import="nfri.dcpp.com.util.ComUtil"%>
<%
	/**
	* 기본 입자 정보를 실제 DB에 입력하는 JSP 페이지
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
	//결과 리턴용 Boolean 함수
	boolean returnValue = false;
	
	String returnMsg = "";
	
	String email = request.getParameter("email");
	String time = ComUtil.getTimeNow();
	
	System.out.println("Start");	
	returnValue = inputControl.updateUserStatus(email, time);
	
	if(returnValue!=false){
		%>
		<script>
			alert("정보 변경 성공");
		</script>		
		<%		
		//returnMsg = "Thank you for your registration.\n Send confirm email to your email";
	}else{
		//returnMsg = "Registration fail. Please ask administrator.";
		%>
		<script>
			alert("정보 변경 실패! 관리자에게 문의요망..");
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