<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="nfri.dcpp.com.util.*"%>
<%@ page import="nfri.dcpp.properties.business.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%
	/**
	* 참고문헌 정보를 실제 DB에 입력하는 JSP 페이지(참고 문헌 검색에서 호출시)
	* by Junside(J.H Park)
	*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function init(search_page, returnMsg, returnValue) {
	var f = document.form1;
	alert(returnMsg);
 	f.action = 'pr_article_insert.jsp';
 	f.submit();
}
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<FORM METHOD=POST NAME="form1" ACTION="">
<jsp:useBean id="inputControl" class="nfri.dcpp.properties.business.Ctr_Article_Process" scope="page"/>
<%
	ServletContext context = getServletContext();

	String returnValue = inputControl.insertRefArticle(request, context);
	
	String returnMsg = "";
	if(!returnValue.equalsIgnoreCase(ComVar.MSG_ERROR)){
		returnMsg = "참고문헌 입력에 성공했습니다.";
	}else{
		returnMsg = "참고문헌 입력에 실패했습니다.";
	}
%>
</FORM>
<script language="javascript">
	init('<%=returnMsg%>', '<%=returnValue%>');
</script>
</body>
</html>