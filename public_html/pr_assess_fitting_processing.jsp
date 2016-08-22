<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.com.util.*"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%
	/**
	* Fitting�� ó���ϴ� ������
	* by Junside(J.H Park)
	*/
	//��� ���Ͽ� Boolean �Լ�
	boolean returnValue = false;	
	String returnMsg = "";
	String pr_no = request.getParameter("pr_no");
	String d_index = request.getParameter("d_index");
	String file = request.getParameter("file");
	String xax_cal = request.getParameter("xax_cal");
	String xay_cal = request.getParameter("xay_cal");
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
	var index = form2.d_index.value;
	alert(returnMsg);
	if(index == '1'){//����
		form2.target = "_self";
		form2.action = "pr_insert_graph_data.jsp";
		form2.submit();	
	}else if(index == '2'){//����
		form2.target = "_self";
		form2.action = "pr_draw_graph_data.jsp";
		form2.submit();
	}else if(index == '0'){ //����
		history.back();
	}
	
}
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<jsp:useBean id="inputControl" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
Fitting ó����..
<%	
	if(file.equalsIgnoreCase("1")){ //������ ������
		ServletContext context = getServletContext();	
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
		
		returnValue = inputControl.insertGraphExcelData(multi, contextpath);
		
		pr_no = multi.getParameter("pr_no");
		xax_cal = multi.getParameter("xax_cal");
		xay_cal = multi.getParameter("xay_cal");
		
	}else if(file.equalsIgnoreCase("2")){ //������ ������
		returnValue = inputControl.insertGraphDataInfo(request);
	
		pr_no = request.getParameter("pr_no");
		xax_cal = request.getParameter("xax_cal");
		xay_cal = request.getParameter("xay_cal");
	}

	if(returnValue == true){
		returnMsg = "�׷��� ���� �Է¿� �����߽��ϴ�.";
	}else{
		returnMsg = "�׷��� ���� �Է¿� �����߽��ϴ�.";
		d_index = "0";
	}
%>
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
<input type="hidden" name="xax_cal" value='<%=xax_cal%>'>
<input type="hidden" name="xay_cal" value='<%=xay_cal%>'>
<input type="hidden" name="d_index" value='<%=d_index%>'>
</FORM>
<script language="javascript">
	init('<%=returnMsg%>');
</script>
</body>
</html>