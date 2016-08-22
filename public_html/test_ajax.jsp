<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<form name="form1" action="" method="POST" >

<span>
응원팀 투표 
</span>
<table width = "650">
<tr>
응원하는 팀을 선택해 주십시요 (중복투표가능)<br>
<input type ="radio" name="TEAM" value="한화이글스" checked>
<input type ="radio" name="TEAM" value="두산베어스">
<input type ="radio" name="TEAM" value="LG트윈스">
<input type ="button" value="투표">
</tr>
<table  width = "650">
<tr>
결과<br>
<input type ="radio" name="Result" value="한화이글스">
<input type ="radio" name="Result" value="두산베어스">
<input type ="radio" name="Result" value="LG트윈스">
</tr>
</table>
</form>
</body>
</html>