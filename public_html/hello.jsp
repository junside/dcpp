<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<title>Insert title here</title>
</head>
<body>
<%
request.getParameter("name");
String name = request.getParameter("name");
%>
안녕하세요, <%=name %> 회원님!
</body>
</html>

