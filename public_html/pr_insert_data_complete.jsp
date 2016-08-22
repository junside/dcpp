<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="nfri.dcpp.properties.business.Ctr_Part_Info_Process"%>
<%@ page import="java.util.Enumeration"%>
<%
	/**
	* 기본 물성 정보 입력을 완료하는 JSP 페이지
	* by Junside(J.H Park)
	*/
	//결과 리턴용 Boolean 함수
	boolean returnValue = false;	
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
function init(returnValue) {
	var form2 = document.form1;
	if(returnValue == false){
		history.back();
	}else {
		form2.target = "_self";
		form2.action = "pr_common_list.jsp";
		form2.submit();	
	}
		
}
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<jsp:useBean id="inputControl" class="nfri.dcpp.properties.business.Ctr_Property_Info_Process" scope="page"/>
<%	
	returnValue = inputControl.insertInfoComplete(pr_no);
%>
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
</FORM>
<script language="javascript">
	init('<%=returnValue%>');
</script>
</body>
</html>