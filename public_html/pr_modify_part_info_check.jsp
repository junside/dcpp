<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="nfri.dcpp.properties.business.Ctr_Part_Info_Process"%>
<%@ page import="nfri.dcpp.com.util.ComUtil"%>
<%@ page import="java.util.Enumeration"%>
<%@page import="nfri.dcpp.com.util.ComVar"%>

<%
	/**
	* �⺻ ���� ������ ���� DB�� �Է��ϴ� JSP ������
	* by Junside(J.H Park)
	*/
	//��� ���Ͽ� Boolean �Լ�
	boolean returnValue = false;	
	String returnMsg = "";
	String seq = request.getParameter("seq");
	
	String part_no = request.getParameter("part_no"); //���� ��ȣ
	String part_symbol = request.getParameter("part_symbol"); //���� ��ȣ
	String part_name = request.getParameter("part_name"); //���� �̸�
	String part_mass = request.getParameter("part_mass"); //���� ����
	String part_type = request.getParameter("part_type"); //���� Ÿ��
	String part_amnum = request.getParameter("part_amnum"); //���� ���� ��ȣ
	String part_amcount = request.getParameter("part_amcount"); //���� ����
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function init(returnMsg, part_no) {
	var form2 = document.form1;

	alert(returnMsg);

	//history.back();
	form2.target = "_self";
	form2.action = "pr_modify_part_info_update.jsp?part_no="+part_no;
	form2.submit();
	//self.close();
	//form2.target = "_self";
	//form2.action = "pr_modify_target_part_list.jsp";
	//form2.submit();
	
	//if(index == '1'){//����
	//	history.back();
	//}else if(index == '2'){//����
	//	form2.target = "_self";
	//	form2.action = "pr_modify_target_part_info_update.jsp?pr_no="+pr_no;
	//	form2.submit();
	//}else if(index == '3'){//����
	//	form2.target = "_self";
	//	form2.action = "pr_modify_target_part_list.jsp";
	//	form2.submit();	
	//}
	
}

</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">

<jsp:useBean id="partControl" class="nfri.dcpp.properties.business.Ctr_Part_Info_Process" scope="page"/>
<%	
	if("0".equalsIgnoreCase(seq)){ //���� ��ȣ
		//System.out.println("����");
		returnValue = partControl.updatePartInfo(part_no, seq, part_symbol);
		
		if(returnValue == true){
			returnMsg = "����/���� ��ȣ ���� ���濡 �����߽��ϴ�.";
			//pr_no = returnValue;
		}else{
			returnMsg = "����/���� ��ȣ ���� ���濡 �����߽��ϴ�.";
		}
	}else if("1".equalsIgnoreCase(seq)){ //���� �̸�
		//System.out.println("�Է�");
		returnValue = partControl.updatePartInfo(part_no, seq, part_name);
		
		if(returnValue == true){
			returnMsg = "����/���� �̸� ���濡 �����߽��ϴ�.";
			//pr_no = returnValue;
		}else{
			returnMsg = "����/���� �̸� ���濡 �����߽��ϴ�.";
		}
	}else if("2".equalsIgnoreCase(seq)){ //���� ����
		//System.out.println("�Է�");
		returnValue = partControl.updatePartInfo(part_no, seq, part_mass);
		
		if(returnValue == true){
			returnMsg = "����/���� ���� ���濡 �����߽��ϴ�.";
			//pr_no = returnValue;
		}else{
			returnMsg = "����/���� ���� ���濡 �����߽��ϴ�.";
		}
	}else if("3".equalsIgnoreCase(seq)){ //���� ����
		//System.out.println("�Է�");
		returnValue = partControl.updatePartInfo(part_no, seq, part_type);
		
		if(returnValue == true){
			returnMsg = "����/���� ���� ���濡 �����߽��ϴ�.";
			//pr_no = returnValue;
		}else{
			returnMsg = "����/���� ���� ���濡 �����߽��ϴ�.";
		}
	}else if("4".equalsIgnoreCase(seq)){ //���� ���ڹ�ȣ
		//System.out.println("�Է�");
		returnValue = partControl.updatePartInfo(part_no, seq, part_amnum);
		
		if(returnValue == true){
			returnMsg = "���ڹ�ȣ ���濡 �����߽��ϴ�.";
			//pr_no = returnValue;
		}else{
			returnMsg = "���ڹ�ȣ ���濡 �����߽��ϴ�.";
		}
	}else if("5".equalsIgnoreCase(seq)){ //���� ����
		//System.out.println("�Է�");
		returnValue = partControl.updatePartInfo(part_no, seq, part_amcount);
		
		if(returnValue == true){
			returnMsg = "����/���� ���� ���濡 �����߽��ϴ�.";
			//pr_no = returnValue;
		}else{
			returnMsg = "����/���� ���� ���濡 �����߽��ϴ�.";
		}
	}
	//returnValue = partControl.updatePartInfo();

	
%>
</FORM>
<script language="javascript">
	init('<%=returnMsg%>', '<%=part_no%>');
</script>
</body>
</html>