<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.com.util.*"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="nfri.dcpp.com.util.ComVar"%>
<%
	/**
	* �׷��� ������ ������ ���� DB�� �Է��ϴ� JSP ������
	* by Junside(J.H Park)
	*/
	//��� ���Ͽ� Boolean �Լ�
	boolean returnValue = false;	
	String returnMsg = "";
	String file = request.getParameter("file");
	String article_number = request.getParameter("article_number");
	String doi_number = request.getParameter("doi_number");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function init(returnMsg) {
	var form2 = document.form1;
	alert(returnMsg);
	form2.target = "_self";
	form2.action = "pr_article_insert_doi_data.jsp";
	form2.submit();	
	
}
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<jsp:useBean id="inputControl" class="nfri.dcpp.properties.business.Ctr_Article_Process" scope="page"/>
<%	
	if(file.equalsIgnoreCase("1")){ //������ ������
		//request.getSession().getServletContext().getRealPath("/")
		ServletContext context = request.getSession().getServletContext();	
		//Web Application ������  ����
		String realFolder = "";
		//Web Application ������
		String contextpath = context.getRealPath("/");
		//������ ���ε� �Ǵ� ��� ����
		String uploadFolder = "data_file";		
		//���ڵ� Ÿ�� ����
		String encType ="euc-kr";
		//�ִ� ���ε�� ���� ũ�� ���� = 10MB
		int maxSize = 10*1024*1024;

		//���� �������� Web Application ���� ���� ���
		realFolder = context.getRealPath(uploadFolder);	
		MultipartRequest multi = null;
		
		/*
		* ������ ����� ������Ʈ ����
		* ������ ���ϸ��� ������ �ִ� ��ü. �������� ���� ���, �ִ� ���ε�� ���� ũ��
		* �����ڵ�, �⺻ ���� ����
		*/ 
		multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		
		returnValue = inputControl.insertDOIExcelData(multi, contextpath);
		
	}else if(file.equalsIgnoreCase("2")){ //������ ������
		returnValue = inputControl.insertDOINumber(article_number, doi_number);
	}

	if(returnValue == true){
		returnMsg = "�׷��� ���� �Է¿� �����߽��ϴ�.";
	}else{
		returnMsg = "�׷��� ���� �Է¿� �����߽��ϴ�.";
	}
%>
</FORM>
<script language="javascript">
	init('<%=returnMsg%>');
</script>
</body>
</html>