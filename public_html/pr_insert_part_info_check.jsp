<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="nfri.dcpp.properties.business.Ctr_Part_Info_Process"%>
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
function init(returnMsg) {
	var f = document.form1;
	alert(returnMsg);
	//부모창 다시 리로드
	opener.location.reload();
	//창 닫기
	parent.close();
}
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<jsp:useBean id="partInfo" class="nfri.dcpp.properties.model.Basic_Part_Info" scope="page"/>
<%	
	Ctr_Part_Info_Process inputControl = new Ctr_Part_Info_Process();
	//결과 리턴용 Boolean 함수
	boolean returnValue = false;
	
	String returnMsg = "";
	
	partInfo.setPL_CPBI_ELE_SYMBOL(request.getParameter("pr_part_symbol"));
	partInfo.setPL_CPBI_ELE_NAME(request.getParameter("pr_part_name"));
	partInfo.setPL_CPBI_ELE_MASS(request.getParameter("pr_part_mass"));	
	partInfo.setPL_CPBI_ELE_TYPE(request.getParameter("pr_part_type_code"));
	partInfo.setPL_CPBI_ELE_AMNUM(ComUtil.isNullToEmptyString(request.getParameter("pr_part_amnum")));
	partInfo.setPL_CPBI_ELE_AMCOUNT(ComUtil.isNullToEmptyString(request.getParameter("pr_part_amcount")));
		
	returnValue = inputControl.insertPartInfo(partInfo);
	
	if(returnValue!=false){
		returnMsg = "입자정보 입력에 성공했습니다.";
	}else{
		returnMsg = "입자정보 입력에 실패했습니다.";
	}
%>
</FORM>
<script language="javascript">
	init('<%=returnMsg%>');
</script>
</body>
</html>