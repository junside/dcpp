<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.com.util.*"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="nfri.dcpp.com.util.ComVar"%>
<jsp:useBean id="inputControl" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<jsp:useBean id="property_assess_data" class="nfri.dcpp.properties.business.Ctr_Property_Assess_Info" scope="page"/>
<%
String user_id = ComUtil.isNullToDashString((String)session.getAttribute("user_id"));
if(user_id.equalsIgnoreCase("-")){
	%>
	<script language = javascript>
	alert("�ٽ� �α��� �ϼ���.");
	</script>
	<%
	response.sendRedirect("c_login.jsp?flag=AU");
}else{
%>
<%
	/**
	* �׷��� ������ ������ ���� DB�� �Է��ϴ� JSP ������
	* by Junside(J.H Park)
	*/
	//��� ���Ͽ� Boolean �Լ�
	boolean returnValue = false;	
	String returnMsg = "";
//	String v_pr_no = request.getParameter("v_pr_no");
	String file = request.getParameter("file");
	
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
	String v_pr_no = multi.getParameter("v_pr_no");
	String xax_cal = multi.getParameter("xax_cal");
	String xay_cal = multi.getParameter("xay_cal");
	
	String param_ic_option     = multi.getParameter("ic_option"); // �浹���
	String param_projectile    = multi.getParameter("projectile"); // �Ի�����
	String param_projectile_id = multi.getParameter("projectile_id"); // �Ի�����ID
	String param_mp_option     = multi.getParameter("mp_option"); // �����μ���
	String param_sp_option     = multi.getParameter("sp_option"); // �����μ���

	String param_xax_unit      = multi.getParameter("xax_unit"); // X ����
	String param_xay_unit      = multi.getParameter("xay_unit"); // Y ����
	String param_tg_name       = multi.getParameter("tg_name"); // ǥ������
	String param_tg_name_id    = multi.getParameter("tg_name_id"); // ǥ������ ID
	String param_tg_ionic      = ComUtil.isNullToNullString(multi.getParameter("tg_ionic")); // ǥ������ �̿�ȭ
	String param_tg_elec       = ComUtil.isNullToNullString(multi.getParameter("tg_elec")); // ǥ������ ���ڹ�ġ
	String param_tg_fine       = ComUtil.isNullToNullString(multi.getParameter("tg_fine")); // ǥ������ �̼�����
	String param_pd_name       = ComUtil.isNullToNullString(multi.getParameter("pd_name")); // �������� 
	String param_pd_name_id    = ComUtil.isNullToNullString(multi.getParameter("pd_name_id")); // �������� ID 
	String param_pd_ionic      = ComUtil.isNullToNullString(multi.getParameter("pd_ionic")); // �������� �̿�ȭ
	String param_pd_elec       = ComUtil.isNullToNullString(multi.getParameter("pd_elec")); // �������� ���ڹ�ġ
	String param_pd_fine       = ComUtil.isNullToNullString(multi.getParameter("pd_fine")); // �������� �̼�����

	//X,Y �ּ�, �ִ밪 ����
	String param_x_min_v      = ComUtil.isNullToDashString(multi.getParameter("graph_xrange_min_value")); // X�� �ּ�
	String param_x_max_v       = ComUtil.isNullToDashString(multi.getParameter("graph_xrange_max_value")); // X�� �ִ�
	String param_y_min_v       = ComUtil.isNullToDashString(multi.getParameter("graph_yrange_min_value")); // Y�� �ּ�
	String param_y_max_v       = ComUtil.isNullToDashString(multi.getParameter("graph_yrange_max_value")); // Y�� �ִ�
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function init(returnMsg, returnValue) {
	var form2 = document.form1;
	alert(returnMsg);
	if(returnValue == 'true'){//�����ܰ�
		form2.target = "_self";
		form2.action = "pr_assess_final_inf_grd_data.jsp";
		form2.submit();
	}else{ //����
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
<%		
	returnValue = inputControl.inserFinalGraphExcelData(multi, contextpath);

	if(returnValue == true){
		returnMsg = "�׷��� ���� �Է¿� �����߽��ϴ�.";		
		returnValue = property_assess_data.updateStateFinalAssess(v_pr_no,"AI");
		
	}else{
		returnMsg = "�׷��� ���� �Է¿� �����߽��ϴ�.";
	}
%>
<input type="hidden" name="ic_option"     value="<%=param_ic_option%>"/>
<input type="hidden" name="projectile"    value="<%=param_projectile%>"/>
<input type="hidden" name="projectile_id" value="<%=param_projectile_id%>"/>
<input type="hidden" name="mp_option"     value="<%=param_mp_option%>"/>
<input type="hidden" name="sp_option"     value="<%=param_sp_option%>"/>
<input type="hidden" name="xax_unit"      value="<%=param_xax_unit%>"/>
<input type="hidden" name="xay_unit"      value="<%=param_xay_unit%>"/>
<input type="hidden" name="tg_name"       value="<%=param_tg_name%>"/>
<input type="hidden" name="tg_name_id"    value="<%=param_tg_name_id%>"/>
<input type="hidden" name="tg_ionic"      value="<%=param_tg_ionic%>"/>
<input type="hidden" name="tg_elec"       value="<%=param_tg_elec%>"/>
<input type="hidden" name="tg_fine"       value="<%=param_tg_fine%>"/>
<input type="hidden" name="pd_name"       value="<%=param_pd_name%>"/>
<input type="hidden" name="pd_name_id"    value="<%=param_pd_name_id%>"/>
<input type="hidden" name="pd_ionic"      value="<%=param_pd_ionic%>"/>
<input type="hidden" name="pd_elec"       value="<%=param_pd_elec%>"/>
<input type="hidden" name="pd_fine"       value="<%=param_pd_fine%>"/>
<input type="hidden" name="v_pr_no"       value="<%=v_pr_no%>"/>
</FORM>
<script language="javascript">
	init('<%=returnMsg%>', '<%=returnValue%>');
</script>
</body>
</html>
<%}%>