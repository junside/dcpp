<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("euc-kr"); %>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="ctr_data" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
<jsp:useBean id="property_assess_data" class="nfri.dcpp.properties.business.Ctr_Property_Assess_Info" scope="page"/>
<%
	/**
	* ���� ���ϴ� ������
	* by Junside(J.H Park)
	*/
	String user_id = ComUtil.isNullToDashString((String)session.getAttribute("user_id"));
	if(user_id.equalsIgnoreCase("-")){
		%>
		<script language = javascript>
		alert("�ٽ� �α��� �ϼ���.");
		</script>
		<%
		response.sendRedirect("c_login.jsp?flag=AU");
	}else{		
	
	// 1. ������� �������� �Է��� ���� ��������
	
	// 1-1. ���ο� ��� ���� ���� ��ȣ ����
	
	String v_pr_no     = ComUtil.isNullToNullString(request.getParameter("v_pr_no")); //������� ������ȣ
	
	// 1-3. ǥ����,�����μ���,�����μ���, ��  ���޹��� �Ķ���� ������ ����.	
	String param_ic_option     = "";
	String param_projectile    = "";
	String param_projectile_id = "";
	String param_mp_option     = "";
	String param_sp_option     = "";

	String exp_param_mp_option = "";
	String exp_param_sp_option = "";
	String process_option	   = "";

	//������±׷�������
	String param_xax_unit      = "";
	String param_xay_unit      = "";
	String param_tg_name       = "";
	String param_tg_name_id    = "";
	String param_tg_ionic      = "";
	String param_tg_elec       = "";
	String param_tg_fine       = "";
	String param_pd_name       = "";
	String param_pd_name_id    = "";
	String param_pd_ionic      = "";
	String param_pd_elec       = "";
	String param_pd_fine       = "";
	
	//X, Y �ּ�, �ִ밪 üũ ����
	String param_graph_xrange_v      = "";
	String param_graph_yrange_v      = "";

	//X,Y �ּ�, �ִ밪 ����
	String param_x_min_v      = ""; // X�� �ּ�
	String param_x_max_v       = ""; // X�� �ִ�
	String param_y_min_v       = ""; // Y�� �ּ�
	String param_y_max_v       = ""; // Y�� �ִ� 
	
	//X�� ���� ǥ��
	String X_Title = "";
	//Y�� ���� ǥ��
	String Y_Title = "";

	
	// 1-2. ������� ������ȣ�� �������� ���� ��� ���ο� ������ȣ ����
	if(v_pr_no.equalsIgnoreCase("NULL")){ 
		v_pr_no = property_assess_data.makeVaildPropertyNum(ComUtil.getTimeNow());
		
		// 1-3. ǥ����,�����μ���,�����μ���, ��  ���޹��� �Ķ���� ������ ����.
		param_ic_option     = request.getParameter("ic_option"); // �浹���
		param_projectile    = request.getParameter("projectile"); // �Ի�����
		param_projectile_id = request.getParameter("projectile_id"); // �Ի�����ID
		param_mp_option     = request.getParameter("mp_option"); // �����μ���
		param_sp_option     = request.getParameter("sp_option"); // �����μ���
		
		exp_param_mp_option = ctr_data.getExpName(param_mp_option);
		exp_param_sp_option = ctr_data.getExpName(param_sp_option);
		process_option = ComUtil.getAbbrString(exp_param_mp_option, 10) + ", " + ComUtil.getAbbrString(exp_param_sp_option, 10);
		
		//������±׷�������
		param_xax_unit      = request.getParameter("xax_unit"); // X ����
		param_xay_unit      = request.getParameter("xay_unit"); // Y ����
		param_tg_name       = request.getParameter("tg_name"); // ǥ������
		param_tg_name_id    = request.getParameter("tg_name_id"); // ǥ������ ID
		param_tg_ionic      = ComUtil.isNullToNullString(request.getParameter("tg_ionic")); // ǥ������ �̿�ȭ
		param_tg_elec       = ComUtil.isNullToNullString(request.getParameter("tg_elec")); // ǥ������ ���ڹ�ġ
		param_tg_fine       = ComUtil.isNullToNullString(request.getParameter("tg_fine")); // ǥ������ �̼�����
		param_pd_name       = ComUtil.isNullToNullString(request.getParameter("pd_name")); // �������� 
		param_pd_name_id    = ComUtil.isNullToNullString(request.getParameter("pd_name_id")); // �������� ID 
		param_pd_ionic      = ComUtil.isNullToNullString(request.getParameter("pd_ionic")); // �������� �̿�ȭ
		param_pd_elec       = ComUtil.isNullToNullString(request.getParameter("pd_elec")); // �������� ���ڹ�ġ
		param_pd_fine       = ComUtil.isNullToNullString(request.getParameter("pd_fine")); // �������� �̼�����
		
		//X, Y �ּ�, �ִ밪 üũ ����
		param_graph_xrange_v      = ComUtil.isNullToNullString(request.getParameter("graph_xrange_value"));
		param_graph_yrange_v      = ComUtil.isNullToNullString(request.getParameter("graph_yrange_value"));

		//X,Y �ּ�, �ִ밪 ����
		param_x_min_v      = ComUtil.isNullToNullString(request.getParameter("graph_xrange_min_value")); // X�� �ּ�
		param_x_max_v       = ComUtil.isNullToNullString(request.getParameter("graph_xrange_max_value")); // X�� �ִ�
		param_y_min_v       = ComUtil.isNullToNullString(request.getParameter("graph_yrange_min_value")); // Y�� �ּ�
		param_y_max_v       = ComUtil.isNullToNullString(request.getParameter("graph_yrange_max_value")); // Y�� �ִ� 

		//ǥ���� �����
		//String pd_expression = property_assess_data.makeAssessExpression(request);
		
		//X�� ���� ǥ��
		X_Title = ctr_data.getExpName(param_xax_unit);
		//Y�� ���� ǥ��
		Y_Title = ctr_data.getExpName(param_xay_unit);
		
		//String whereCondtion = property_assess_data.getConditionInfo(request);
		
		//1-4.�Է��� ������������ ������
		//Vector pd_info = property_assess_data.searchAssessPropertyListInfo(request);
		
		//����Ʈ�� ���Ե� ���������� ���� �׷��� �����͸� ������	
		//Vector gd_info = graph_data.selectGraphBasicData(pr_no);
		//int dt_count = pd_info.size();//dt_count = 0;//dt_info.size();
		
		//String block_state = "T";
		
		//1. ���� Temp ������ ����������
		//boolean del_value = false;
		//del_value = property_assess_data.deleteFittingTempData(user_id);		
		//System.out.println("Fitting Temp Delete : "+del_value);
		//if(del_value == true){ //2. Temp Data�� ����� ����������..
		//}else{
		//	System.out.println("Temp Data Delete Fail!!!");
		//}
		
		ServletContext context = getServletContext();
		
		boolean made_OK = property_assess_data.makeAssessDataInfo(request, context, v_pr_no, user_id);
		
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<script language = javascript>
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function assessInfo(v_pr_no){ //��
	if(confirmMsg('1') == true){
		var form2 = document.form1;
		//8. ���� ���ε� ���� ÷�� ���� üũ
		if(form2.pl_excel_file.value.length < 1){
			alert("��������(Excel)�� �߰��ϼ���!");
			form2.pl_excel_file.focus();
			return;
		}			
		form2.action = "pr_assess_make_inf_grd_data_check.jsp?v_pr_no="+v_pr_no;
		form2.submit();
	}
}

function exitInfo(){ //����
	if(confirmMsg('2') == true){
		var form2 = document.form1;	
		form2.action = "pr_common_assess_list.jsp";
		form2.submit();
	}
}

function downloadText(v_pro_no){ //���� �ٿ�ε�
	//window.open("pr_text_download_file.jsp?v_pro_no="+v_pro_no,"txt_down_info","width=1,height=1,toolbar=no");
	//return;
	//���� �������� ���� ���ε带 ���� multipart �̱� ������ �Ʒ��� ���� ������δ� �Ķ���Ͱ� ����� ������ �ȵǾ� ���� ���� ����� �̿���
	var form2 = document.form1;
	//form�� ���� �ѱ�� ���Ͽ� ����ü�� �������� ��ü���� �ִ´�.
	//alert(v_pro_no);
	//form2.v_pro_no.value = v_pro_no;
	//alert(form2.v_pro_no.value);
	form2.target = "_self";
	form2.action = "pr_text_download_file.jsp?v_pro_no="+v_pro_no;
	form2.submit();
}

function confirmMsg(number){
	var num = number;
	switch(num) {
		case '1': //���ϱ�
			var result = confirm("�Է��� ���������͸� ������µ����ͷ� ����Ͻðڽ��ϱ�?\n�Է��Ͻ� ������ �ѹ� �� Ȯ���Ͻ÷��� ��Ҹ� ��������.\nȮ���� �����ø� �Է��� �Ϸ�˴ϴ�.");
			return result;
			break;
		case '2': //����
			var result = confirm("�򰡸� �����Ͻðڽ��ϱ�?\n�����ϼ���");
			return result;
			break;
		default :		
			break;
	}		
}

</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<body>
<form name="form1" method="POST" enctype="multipart/form-data">
<input type="hidden" name="user_id" value="<%=user_id%>"/>
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
<p class="level2">������ ���� ���</p>
<table class="tab2">
	<col width="30%">
	<col width="70%">
	<col width="30%">
	<col width="70%">
	<tr>
		<th class="thla">����������</th>
		<td class="tdl">
			<a href="javascript:void(0);"  onClick="downloadText('<%=v_pr_no%>'); return false;">
	     	<img src = "images/file_img/<%=ComUtil.getFileImgName("txt")%>" width="16" height="16" border="0"> 
	     	</a>
		</td>
	</tr>
	<tr>
		<th class="thla">��������(xls �� ������)</th>
		<td class="tdl"><input type='file' name='pl_excel_file' size='40' value=''></td>
	</tr>
</table>
<div class="bbtn_r">
<a href="javascript:assessInfo('<%=v_pr_no%>')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('assessImage','','images/img_cont/assess_upper.gif',1)"><img src="images/img_cont/assess_normal.gif" name="assessImage" width="80" height="24" border="0" id="assessImage" /></a>
<a href="javascript:exitInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('exitImage','','images/img_cont/exit_upper.gif',1)"><img src="images/img_cont/exit_normal.gif" name="exitImage" width="80" height="24" border="0" id="exitImage" /></a>
</div>
</form>
</body>
</html>
<%}%>