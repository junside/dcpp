<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.properties.db.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>   
<jsp:useBean id="graph_info" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>

<%

	String pr_st_no = "B201000000";
	String pr_ed_no = "B201026000";
	
	//Vector pt_info = graph_info.selectModifyNumberList(pr_st_no, pr_ed_no); // OK
	//전체 개수 저장
	//int totalRecord = pt_info.size();
	//String whereCondtion = property_data.getConditionInfo(request);
	//System.out.println("whereCondtion : " + whereCondtion);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
<script language = javascript>

function updateInfo(){ //다음 이동
	var form2 = document.form1;	
	form2.action = "pr_update_numeric_count_check.jsp";
	form2.submit();
}

function init(total){
	if(total == 0){
		alert("데이터가 존재하지 않습니다.");
		return;
	}
}
</script>
<form name="form1" action="" method="POST" >
<table class="tab5">
<col width="400">
<col width="150">
<tr height="40">
		<th class="thr"><font color = blue>시작 물성 정보 번호 (ex : B201000000)</font></th>		
		<td class="tdl"><input type='text' name='st_pr_no' size='50' value=''></td>
</tr>
<tr height="40">
		<th class="thr"><font color = blue>끝 물성 정보 번호 (ex : B201026000)</font></th>		
		<td class="tdl"><input type='text' name='ed_pr_no' size='50' value=''></td>
	</tr>
<tr>
</table>
<div class="bbtn_c">
<a href="javascript:updateInfo()"> 입력 </a>
</div>
</form>
</body>
</html>