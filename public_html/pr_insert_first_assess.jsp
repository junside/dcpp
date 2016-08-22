<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<%@page import="nfri.dcpp.com.util.ComVar"%>
<%
	//���� ������ Ȯ���ؼ� �α��� ������ ������ �α��� â���� �̵�
	String user_id = ComUtil.isNullToNullString((String)session.getAttribute("user_id"));
	String auth = ComUtil.isNullToNullString((String)session.getAttribute("auth"));
	//if(user_id == null || auth.equalsIgnoreCase("AU")){
		//System.out.println("i_user_id : " + user_id);
		//System.out.println("i_auth : " + auth);
		//response.sendRedirect("c_login.jsp?flag="+auth);
	//}
	if(user_id.equalsIgnoreCase(ComVar.STRING_NULL_B)){
		response.sendRedirect("c_login.jsp?flag=IU");			
	}else{
		if(!auth.equalsIgnoreCase("AIU") && !auth.equalsIgnoreCase("IAU") && !auth.equalsIgnoreCase("IU")){
			response.sendRedirect("c_login.jsp?flag=IU");
		}
	}
%>
<%
	/**
	* ���� ������ ���� �����͸� DB�� �Է��ϴ� JSP ������
	* by Junside(J.H Park)
	*/
	//��� ���Ͽ� Boolean �Լ�
	boolean returnValue = false;	
	String returnMsg = "";
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function init(returnMsg, returnValue) {
	var form2 = document.form1;	
	alert(returnMsg);
	if(returnValue == 'true'){
		form2.target = "_self";
		form2.action = "pr_common_list.jsp";
		form2.submit();
	}else if(returnValue == 'false'){
		history.back();
	}
}
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<body>
<form name="form1" action="" method="POST">
<jsp:useBean id="inputControl" class="nfri.dcpp.properties.business.Ctr_Property_Assess_Info" scope="page"/>
<jsp:useBean id="property_info" class="nfri.dcpp.properties.business.Ctr_Property_Info_Process" scope="page"/>
<%	
	//String user_id = (String)session.getAttribute("user_id");
	Properties_Assess_Info info = new Properties_Assess_Info();
	String pr_no = request.getParameter("pr_no");
	//System.out.println("============== > pr_no : " + pr_no);
	//Ctr_Graph_Info_Process data_info = new Ctr_Graph_Info_Process();
	//�⺻ ���� ��������
	Properties_Basic_Info spec_info = property_info.selectViewPropertySpecInfo(pr_no);
	//�����ͺз�
	String db_value = spec_info.getPL_BI_DATA_BRANCH();
	//�����μ���
	String mp_value = spec_info.getPL_BI_MAIN_PROC();
	//�����μ���
	String sp_value = spec_info.getPL_BI_SUB_PROC();
	//�浹���
	String imp_value = spec_info.getPL_BI_IMP_CLASS();
	//��������
	String exp_value = spec_info.getPL_BI_EXP_THE_REC();
	//ǥ���� ���� ��������
	String expression = spec_info.getPL_BI_EXPRESSION();//(String)property_info.selectEquationData(pr_no);
	
	//���μ��� ���� ����
	String process = imp_value + ", " + mp_value + ", " + sp_value;
	
	String x_unit = request.getParameter("x_unit");
	String y_unit = request.getParameter("y_unit");
	//���� ���� ��������
	//Graph_Basic_Info basic_info = data_info.selectGraphBasicInfo(pr_no);
	
	//String x_unit = basic_info.getPL_BGI_X_AX_UNIT();
	//String y_unit = basic_info.getPL_BGI_Y_AX_UNIT();
	
	//���� ���� ��ȣ
	info.setPL_BI_DATA_NUM(pr_no);
	//������ �з���
	info.setPL_PEI_DATA_BRANCH(db_value);
	//���μ�����
	info.setPL_PEI_PROCESS(process);
	//�������а�
	info.setPL_PEI_EXP_THE_REC(exp_value);
	//X������
	info.setPL_PEI_X_UNIT(x_unit);
	//Y������
	info.setPL_PEI_Y_UNIT(y_unit);
	//ǥ����
	info.setPL_PEI_EXPRESS(expression);
	
	//���� ���
	info.setPL_PEI_1STEP(ComUtil.convertKorean(request.getParameter("1step")));
	//���� ���
	info.setPL_PEI_2STEP(ComUtil.convertKorean(request.getParameter("2step")));
	//�ұ޼�
	info.setPL_PEI_3STEP(ComUtil.convertKorean(request.getParameter("3step")));
	//��Ȯ��
	info.setPL_PEI_4STEP(ComUtil.convertKorean(request.getParameter("4step")));
	//������
	info.setPL_PEI_5STEP(ComUtil.convertKorean(request.getParameter("5step")));
	//�ϰ���
	info.setPL_PEI_6STEP(ComUtil.convertKorean(request.getParameter("6step")));		
	//�������
	info.setPL_PEI_METHOD(ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("method"))));
	//�ٰ�
	info.setPL_PEI_SCIENT_BASIS(ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("scient_basis"))));
	//�Ѱ�
	info.setPL_PEI_SCIENT_LIMIT(ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("scient_limit"))));
	//����
	info.setPL_PEI_PRIMARY_FACT(ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("primary_fact"))));
	//�м�
	info.setPL_PEI_DATA_ANALY(ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("data_analy"))));
	//������
	info.setPL_PEI_MEASUREMENT(ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("measurement"))));
	//���� Ȯ��
	info.setPL_PEI_DIRECT(ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("direct"))));
	//����Ȯ��
	info.setPL_PEI_INDIRECT(ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("indirect"))));
	//1�� �򰡰�� ����
	info.setPL_PEI_PRIM_EVAL(ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("prim_eval"))));
	//1�� �򰡰�� �÷���
	String final_flag = request.getParameter("final");
	if(final_flag.equalsIgnoreCase("validate")){ //��ȿ�� �������� ���
		info.setPL_PEI_FINAL_FLAG("V");
	}else if(final_flag.equalsIgnoreCase("reject")){ //�Ⱒ �������� ���
		info.setPL_PEI_FINAL_FLAG("R");
	}else{//�� ������ ���
		info.setPL_PEI_FINAL_FLAG("H");
	}
	
	//����
	info.setPL_PEI_ASSESS_USER(user_id);
	info.setPL_PEI_ASSESS_DATE(ComUtil.getTimeNow());
	
	
	returnValue = inputControl.insertPropertyAssessInfo(info);
	if(returnValue == true){
		returnMsg = "������ �Է¿� �����߽��ϴ�.";
	}else{
		returnMsg = "������ �Է¿� �����߽��ϴ�.";
	}
%>
</FORM>
<script language="javascript">
	init('<%=returnMsg%>','<%=returnValue%>');
</script>
</body>
</html>