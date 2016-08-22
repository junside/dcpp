<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="nfri.dcpp.com.util.ComUtil"%>
<%@ page import="nfri.icamdata.common.Basic_Icam_User_Info"%>
<%@page import="java.util.*"%>
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
<TITLE> ICAMDATA 2016 </TITLE>
<link rel="stylesheet" type="text/css" href="css/main.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/print.css" media="print" />
</head>
<body>
<script type="text/javascript">

function checkUser(email){
	var form2 = document.form1;	
	
	var bAnswer = confirm("등록자의 상태를 바꾸시겠어요?")
	if (bAnswer == true){		
		form2.target = "_self";
		form2.action = 'update_registration_user_list.jsp?email='+email;
		form2.submit();
	}else{
		return;
	}
	
}

//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<jsp:useBean id="searchControl" class="nfri.icamdata.common.Ctr_Icamdata_User_Info_Process" scope="page"/>

<%	
	Vector userInfo;
	userInfo = searchControl.searchUserInfo();
		
	int j = 0;
	if(userInfo.size() == 0){
	%>
		<script>alert("신규로 등록요청한 사용자가 없습니다.");</script>		
	<%
	}else{

	//System.out.println("firstname : " + request.getParameter("firstname"));
	//System.out.println("lastname : " + request.getParameter("lastname"));
	//System.out.println("affiliation : " + request.getParameter("affiliation"));
	//System.out.println("emailaddress : " + request.getParameter("emailaddress"));
	//System.out.println("country : " + request.getParameter("country"));
	//System.out.println("salutation : " + request.getParameter("salutation"));
	//System.out.println("comments : " + request.getParameter("comments"));
	%>
	<h1>등록자 리스트</h1>
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="table10">
				<tr>
					<th width="5%">No.</th>
					<th width="10%">First_Name</th>
					<th width="10%">Last_Name</th>
					<th width="10%">Affiliation</th>
					<th width="20%">Email</th>
					<th width="10%">Country</th>
					<th width="10%">Salutation</th>
					<th width="10%">Comments</th>
					<th width="10%">reg_date</th>
					<th width="10%">Status</th>
					<th width="5%"class="ttn">Check</th>
				</tr>				

	<%
		for(int i = 0; i < userInfo.size(); i++){
			Basic_Icam_User_Info info = (Basic_Icam_User_Info)userInfo.elementAt(i);
			
			String first_name = info.getICAM_First_Name();
			String last_name = info.getICAM_Last_Name();
			String affiliation = info.getICAM_Affiliation();	
			String email = info.getICAM_Email();
			String country = info.getICAM_Country();
			String salutation = info.getICAM_Salutation();
			String comment = info.getICAM_Comments();
			String reg_date = info.getICAM_Reg_Date();
			String status = info.getICAM_Reg_Process();
			%>
			<tr>
				<td><%=i+1%></td>
				<td><%=first_name%></td>
				<td><%=last_name%></td>
				<td><%=affiliation%></td>
				<td><a href="mailto:<%=email%>"><%=email%></a></td>
				<td><%=country%></td>
				<td><%=salutation%></td>
				<td><%=comment%></td>
				<td><%=reg_date%></td>
				<td><%=status%></td>
				<td class="ttn">
				<%
				if("P".equalsIgnoreCase(status)){
					%>
					<a href="javascript:checkUser('<%=email%>')">변경</a>
					<%
				}else{
					%>
					Done
					<%
				}
				%>
				
				</td>
			</tr>
			
			<%
		}
	%>
	</table>
	<%
	}
	%>
	
</FORM>

</body>
</html>