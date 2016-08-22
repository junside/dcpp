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
	form2.action = "https://icamdata2016.nfri.re.kr/registration.html";
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
	
	//System.out.println("firstname : " + request.getParameter("firstname"));
	//System.out.println("lastname : " + request.getParameter("lastname"));
	//System.out.println("affiliation : " + request.getParameter("affiliation"));
	//System.out.println("emailaddress : " + request.getParameter("emailaddress"));
	//System.out.println("country : " + request.getParameter("country"));
	//System.out.println("salutation : " + request.getParameter("salutation"));
	//System.out.println("comments : " + request.getParameter("comments"));
	
	userInfo.setICAM_First_Name(request.getParameter("firstname"));
	userInfo.setICAM_Last_Name(request.getParameter("lastname"));
	userInfo.setICAM_Affiliation(request.getParameter("affiliation"));	
	userInfo.setICAM_Email(request.getParameter("emailaddress"));
	userInfo.setICAM_Country(request.getParameter("country"));
	userInfo.setICAM_Salutation(ComUtil.isNullToDashString(request.getParameter("salutation")));
	userInfo.setICAM_Comments(ComUtil.isNullToDashString(request.getParameter("comments")));
	userInfo.setICAM_Reg_Date(ComUtil.getTimeNow());
	userInfo.setICAM_Reg_Process("P");
	
	returnValue = inputControl.insertIcamUserInfo(userInfo);
	
	if(returnValue!=false){
		%>
		<script>
			alert("Thank you for your registration.");	
			//window.location.replace('http://icamdata2016.nfri.re.kr/registration.html');
		</script>		
		<%		
		//returnMsg = "Thank you for your registration.\n Send confirm email to your email";
	}else{
		//returnMsg = "Registration fail. Please ask administrator.";
		%>
		<script>
			alert("Registration fail. Please ask administrator.");
			//window.location.replace('http://icamdata2016.nfri.re.kr/registration.html');
		</script>		
		<%
	}
	%>
	<script>	
	//gotoSite();
	//window.opener.top.location.href="http://icamdata2016.nfri.re.kr/registration.html";
	window.location.replace('http://icamdata2016.nfri.re.kr/registration.html');
	//window.close();
	</script>

</FORM>

</body>
</html>