<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>IAEA TEST PAGE</title>
<script language = javascript>
function viewIAEAInfo(doi_no){ //기본정보 보기
	//window.open("http://172.18.57.118:8080/dcpp/iaea_find_info.jsp?doi_no="+doi_no,"iaea_property_info","width=700,height=600,toolbar=no,location=no,status=no");
	window.open("http://dcpp.nfri.re.kr/iaea_find_info.jsp?doi_no="+doi_no,"iaea_property_info","width=700,height=600,toolbar=no,location=no,status=no");
	//window.open("http://203.230.119.123/iaea_find_info.jsp?doi_no="+doi_no,"iaea_property_info","width=650,height=600,toolbar=no,location=no,status=no");
	return;
}
</script>
</head>
<body>
IAEA 테스트 페이지
<%
	//String doi_no = "10.1029/98JA0271";
	String doi_no = "10.1103/PhysRevA.62.052710";//"10.1029/98JA02721";//2012000060	10.1103/PhysRevA.62.052710

	//String doi_no = "";
%>
<br>
<a href="javascript:void(0);"  onClick="viewIAEAInfo('<%=doi_no%>'); return false;"> 리스트 보기 </a>
</body>
</html>