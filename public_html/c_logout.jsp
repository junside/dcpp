<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	String id = (String)session.getAttribute("user_id");

	if(id != null && id.length() !=0){
		session.removeAttribute("user_id");
		session.removeAttribute("auth");
	}
%>
<script type="text/javascript">
alert("로그아웃되었습니다.");
location.href = "index.jsp";
</script>
</body>
</html>