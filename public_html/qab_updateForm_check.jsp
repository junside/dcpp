<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "nfri.dcpp.board.db.DAO_BOARD_INFO" %>
<%@ page import = "nfri.dcpp.board.business.Ctr_Board_Process" %>
<%
	/**
	* �Խù� ������ ���� DB�� ������Ʈ�ϴ� JSP ������
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
function init(returnMsg, index) {
	var form2 = document.form1;
	alert(returnMsg);
	if(index == '0'){//����
		history.back();
	}else if(index == '1'){//����
		form2.target = "_self";
		form2.action = "qab_list.jsp";
		form2.submit();	
	}
	
}
</script>
<form name="form1" action="" method="POST">
<jsp:useBean id="article" scope="page" class="nfri.dcpp.board.model.Board_Info">
</jsp:useBean>
<%
	//��� ���Ͽ� Boolean �Լ�
	boolean returnValue = false;
	String returnMsg = "";
	int option = 0;
	
	Ctr_Board_Process inputControl = new Ctr_Board_Process();
	returnValue = inputControl.update_QA_Article(request);
	if(returnValue!=false){
		returnMsg = "Content update is success!";
		option = 1;
	}else{
		returnMsg = "Your password is missmatch!";
		option = 0;
	}
%>

</FORM>
<script language="javascript">
	init('<%=returnMsg%>', '<%=option%>');
</script>
</body>
</html>
 