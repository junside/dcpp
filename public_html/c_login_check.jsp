<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.com.util.ComVar"%>
<%
	/**
	* 로그인 정보를 DB와 비교하는 JSP 페이지
	* by Junside(J.H Park)
	*/
	
	String flag = request.getParameter("flag");
	//System.out.println("flag : " + flag);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">

function init(returnMsg, flag) {
	var f = document.form1;
	alert(returnMsg);
	if(flag == "AU" || flag == "AIU"){
		f.target = '_parent';
		f.action = 'pr_assess.jsp';
		f.submit();
	}else{
		f.target = '_parent';
		f.action = 'pr_insert.jsp';
		f.submit();
	}	
}
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');

</script>
<FORM METHOD=POST NAME="form1" ACTION="">
<input type="hidden" name="flag" value='<%=flag%>'>
<jsp:useBean id="user_mgr" class="nfri.dcpp.member.business.Ctr_Member_Process" scope="page"/>
<%
	String id = request.getParameter("user_id");
	String pwd = request.getParameter("user_pwd");
	String returnMsg = "";

	String check = user_mgr.selectUserCheck(id, pwd, flag);
	
	if(flag.equalsIgnoreCase("IAU") ||flag.equalsIgnoreCase("AIU")){
		session.setAttribute("user_id", id);
		session.setAttribute("auth", flag);
		returnMsg = id + "님 로그인 되었습니다.";
	}
	if(flag.equalsIgnoreCase("IU")){ //입력자 체크
		if(check.equalsIgnoreCase(ComVar.USER_ID_INVAILD)){
			returnMsg = "등록된 사용자가 아닙니다";
		}else if(check.equalsIgnoreCase(ComVar.USER_PWD_INVAILD)){
			returnMsg = "패스워드가 틀립니다";
		}else if(check.equalsIgnoreCase(ComVar.USER_AUTH_INVAILD)){
			returnMsg = "이용 권한이 없습니다.";
		}else if(check.equalsIgnoreCase(ComVar.USER_AUTH_CERTIFIED_ALL)){
			session.setAttribute("user_id", id);
			session.setAttribute("auth", "IAU");
			returnMsg = id + "님 로그인 되었습니다.";
		}else{
			session.setAttribute("user_id", id);
			session.setAttribute("auth", flag);
			returnMsg = id + "님 로그인 되었습니다.";
		}
	}else if(flag.equalsIgnoreCase("AU")){ //평가자 체크
		if(check.equalsIgnoreCase(ComVar.USER_ID_INVAILD)){
			returnMsg = "등록된 평가자가 아닙니다";
		}else if(check.equalsIgnoreCase(ComVar.USER_PWD_INVAILD)){
			returnMsg = "패스워드가 틀립니다";
		}else if(check.equalsIgnoreCase(ComVar.USER_AUTH_INVAILD)){
			returnMsg = "이용 권한이 없습니다.";
		}else if(check.equalsIgnoreCase(ComVar.USER_AUTH_CERTIFIED_ALL)){
			session.setAttribute("user_id", id);
			session.setAttribute("auth", "AIU");
			returnMsg = id + "님 로그인 되었습니다.";
		}else{
			session.setAttribute("user_id", id);
			session.setAttribute("auth", flag);
			returnMsg = id + "님 로그인 되었습니다.";
		}
	}
	
%>
</FORM>
<script language="javascript">
	init('<%=returnMsg%>', '<%=flag%>');
</script>
</body>
html>