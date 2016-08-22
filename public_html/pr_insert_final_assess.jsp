<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("euc-kr"); %>
<%@page import="nfri.dcpp.com.util.*"%>
<%@page import="nfri.dcpp.com.util.ComVar"%>
<%
	
	String user_id = ComUtil.isNullToDashString((String)session.getAttribute("user_id"));
	if(user_id.equalsIgnoreCase("-")){
		%>
		<script language = javascript>
		alert("다시 로그인 하세요.");
		</script>
		<%
		response.sendRedirect("c_login_inner.jsp?flag=AU");
	}else{

	/**
	* 물성 정보를 최종 평가한 데이터를 DB에 입력하는 JSP 페이지
	* by Junside(J.H Park)
	*/
	//결과 리턴용 Boolean 함수
	boolean returnValue = false;	
	String returnMsg = "";
	//String pr_no = request.getParameter("pr_no");
	String v_pr_no     = ComUtil.isNullToNullString(request.getParameter("v_pr_no")); //등급유력 물성번호

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function init(returnMsg, returnValue) {
	var form2 = document.form1;	
	alert(returnMsg);
	if(returnValue == 'true'){
		form2.target = "_self";
		form2.action = "pr_common_assess_list.jsp";
		form2.submit();
	}else if(returnValue == 'false'){
		history.back();
	}
}
</script>
<body>
<form name="form1" action="" method="POST">
<input type="hidden" name="v_pr_no"       value="<%=v_pr_no%>"/>
<jsp:useBean id="inputControl" class="nfri.dcpp.properties.business.Ctr_Property_Assess_Info" scope="page"/>
<%	
	//String user_id = ComUtil.isNullToDashString((String)session.getAttribute("user_id"));
	if(user_id.equalsIgnoreCase("-")){
		%>
		<script language = javascript>
		alert("다시 로그인 하세요.");
		</script>
		<%
		response.sendRedirect("c_login_inner.jsp?flag=AU");
	}else{		
	
		returnValue = inputControl.insertPropertyFinalAssessInfo(request, user_id);
		if(returnValue == true){
			returnMsg = "최종 평가정보 입력에 성공했습니다.";
		}else{
			returnMsg = "최종 평가정보 입력에 실패했습니다.";
		}
	}
%>
</FORM>
<script language="javascript">
	init('<%=returnMsg%>','<%=returnValue%>');
</script>
</body>
</html>
<%}%>