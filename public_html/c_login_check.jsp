<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.com.util.ComVar"%>
<%
	/**
	* �α��� ������ DB�� ���ϴ� JSP ������
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
		returnMsg = id + "�� �α��� �Ǿ����ϴ�.";
	}
	if(flag.equalsIgnoreCase("IU")){ //�Է��� üũ
		if(check.equalsIgnoreCase(ComVar.USER_ID_INVAILD)){
			returnMsg = "��ϵ� ����ڰ� �ƴմϴ�";
		}else if(check.equalsIgnoreCase(ComVar.USER_PWD_INVAILD)){
			returnMsg = "�н����尡 Ʋ���ϴ�";
		}else if(check.equalsIgnoreCase(ComVar.USER_AUTH_INVAILD)){
			returnMsg = "�̿� ������ �����ϴ�.";
		}else if(check.equalsIgnoreCase(ComVar.USER_AUTH_CERTIFIED_ALL)){
			session.setAttribute("user_id", id);
			session.setAttribute("auth", "IAU");
			returnMsg = id + "�� �α��� �Ǿ����ϴ�.";
		}else{
			session.setAttribute("user_id", id);
			session.setAttribute("auth", flag);
			returnMsg = id + "�� �α��� �Ǿ����ϴ�.";
		}
	}else if(flag.equalsIgnoreCase("AU")){ //���� üũ
		if(check.equalsIgnoreCase(ComVar.USER_ID_INVAILD)){
			returnMsg = "��ϵ� ���ڰ� �ƴմϴ�";
		}else if(check.equalsIgnoreCase(ComVar.USER_PWD_INVAILD)){
			returnMsg = "�н����尡 Ʋ���ϴ�";
		}else if(check.equalsIgnoreCase(ComVar.USER_AUTH_INVAILD)){
			returnMsg = "�̿� ������ �����ϴ�.";
		}else if(check.equalsIgnoreCase(ComVar.USER_AUTH_CERTIFIED_ALL)){
			session.setAttribute("user_id", id);
			session.setAttribute("auth", "AIU");
			returnMsg = id + "�� �α��� �Ǿ����ϴ�.";
		}else{
			session.setAttribute("user_id", id);
			session.setAttribute("auth", flag);
			returnMsg = id + "�� �α��� �Ǿ����ϴ�.";
		}
	}
	
%>
</FORM>
<script language="javascript">
	init('<%=returnMsg%>', '<%=flag%>');
</script>
</body>
html>