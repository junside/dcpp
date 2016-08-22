<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("euc-kr"); %>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="property_info" class="nfri.dcpp.properties.business.Ctr_Property_Info_Process" scope="page"/>
<jsp:useBean id="data_info" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<jsp:useBean id="assess_info" class="nfri.dcpp.properties.business.Ctr_Property_Assess_Info" scope="page"/>

<%
	/**
	* 1�� �� ������ �Է��ϴ� JSP ������(view)
	* by Junside(J.H Park)
	*/
	
	String pr_no = request.getParameter("pr_no");
	
	//�⺻ ���� ��������
	Properties_Basic_Info spec_info = (Properties_Basic_Info)property_info.selectViewPropertySpecInfo(pr_no);
	//���ο� ���� ��ȣ
	String mgmt_num = spec_info.getPL_BI_MGMT_DATA_NUM();
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
	//���� ���� ��������
	Graph_Basic_Info basic_info = (Graph_Basic_Info)data_info.selectViewGraphBasicInfo(pr_no);
	
	String x_unit = basic_info.getPL_BGI_X_AX_UNIT();
	String y_unit = basic_info.getPL_BGI_Y_AX_UNIT();
	
	Article_Info art_Info = (Article_Info)property_info.selectArticleInfo(pr_no);
	
	String a_path = art_Info.getPL_RAI_ORG_FILE_PATH();
	String a_name = art_Info.getPL_RAI_ORG_FILE_NAME();
	String a_ext = art_Info.getPL_RAI_ORG_FILE_EXT();
	
	//���� �ٿ�ε带 ���� ����
	String file_path = "";
	String file_name = "";
	String file_ext = "";
	
	//�� ����
	Properties_Assess_Info info = assess_info.selectAssessInfo(pr_no);
	//1.1 �Ǵ�
	String step1_v = ComUtil.isNullToEmptyString(info.getPL_PEI_1STEP());
	//2.1 �������
	String method_v = ComUtil.isNullToEmptyString(info.getPL_PEI_METHOD());
	//2.2 ���� �ٰ� ����
	String scient_basis_v = ComUtil.isNullToEmptyString(info.getPL_PEI_SCIENT_BASIS());
	//2.3 �Ǵ�
	String step2_v = ComUtil.isNullToEmptyString(info.getPL_PEI_2STEP());
	//3.1 �Ǵ�
	String step3_v = ComUtil.isNullToEmptyString(info.getPL_PEI_3STEP());
	//4.1 �Ѱ�
	String scient_limit_v = ComUtil.isNullToEmptyString(info.getPL_PEI_SCIENT_LIMIT());
	//4.2 ����
	String primary_fact_v = ComUtil.isNullToEmptyString(info.getPL_PEI_PRIMARY_FACT());
	//4.3 ��Ȯ��
	String measurement_v = ComUtil.isNullToEmptyString(info.getPL_PEI_MEASUREMENT());
	//4.4 �Ǵ�
	String step4_v = ComUtil.isNullToEmptyString(info.getPL_PEI_4STEP());
	//5.1 �м�
	String data_analy_v = ComUtil.isNullToEmptyString(info.getPL_PEI_DATA_ANALY());
	//5.2 �Ǵ�
	String step5_v = ComUtil.isNullToEmptyString(info.getPL_PEI_5STEP());
	//6.1 ����
	String indirect_v = ComUtil.isNullToEmptyString(info.getPL_PEI_INDIRECT());
	//6.2 ����
	String direct_v = ComUtil.isNullToEmptyString(info.getPL_PEI_DIRECT());
	//6.3 �Ǵ�
	String step6_v = ComUtil.isNullToEmptyString(info.getPL_PEI_6STEP());
	//7.1 �򰡳���
	String prim_eval_v = ComUtil.isNullToEmptyString(info.getPL_PEI_PRIM_EVAL());
	//7.2 �򰡰��
	String final_v = ComUtil.isNullToEmptyString(info.getPL_PEI_FINAL_FLAG());
	
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

function assessInfo(){ //��
	//1.1 �Ǵ�
	//var form2 = document.form1;
	//2.1 �������
	//var method_v = form2.method.value;
	//alert("������� ���� : " + method_v + " [ " + cal_length(method_v) + " byte]");
	//var total = total + method_v;

	//2.2 ����ٰ� ����
	//var scient_basis_v = form2.scient_basis.value;
	//alert("����ٰ� ���� : " + scient_basis_v + " [ " + cal_length(scient_basis_v) + " byte]");
	//var total = total + scient_basis_v;

	//4.1 �Ѱ�
	//var scient_limit_v = form2.scient_limit.value;
	//alert("�Ѱ� ���� : " + scient_limit_v + " [ " + cal_length(scient_limit_v) + " byte]");
	//var total = total + scient_limit_v;

	//4.2 ����
	//var primary_fact_v = form2.primary_fact.value;
	//alert("���� ���� : " + primary_fact_v + " [ " + cal_length(primary_fact_v) + " byte]");
	//var total = total + primary_fact_v;

	//4.3 ��Ȯ��
	//var measurement_v = form2.measurement.value;
	//alert("��Ȯ�� ���� : " + measurement_v + " [ " + cal_length(measurement_v) + " byte]");
	//var total = total + measurement_v;

	//5.1 �м�
	//var data_analy_v = form2.data_analy.value;
	//alert("�м� ���� : " + data_analy_v + " [ " + cal_length(data_analy_v) + " byte]");
	//var total = total + data_analy_v;

	//6.1 ����
	//var indirect_v = form2.indirect.value;
	//alert("���� ���� : " + indirect_v + " [ " + cal_length(indirect_v) + " byte]");
	//var total = total + indirect_v;

	//6.2 ����
	//var direct_v = form2.direct.value;
	//alert("���� ���� : " + direct_v + " [ " + cal_length(direct_v) + " byte]");
	//var total = total + direct_v;

	//7.1 �򰡳���
	//var prim_eval_v = form2.prim_eval.value;
	//alert("�򰡳��� ���� : " + prim_eval_v + " [ " + cal_length(prim_eval_v) + " byte]");
	//var total = total + prim_eval_v;

	//7.2 ��ü�հ�
	//alert("�Է� ���� �� �հ� Byte : [ " + cal_length(total) + " byte]");

	
	if(confirmMsg('1') == true){
		var form2 = document.form1;
		form2.action = "pr_insert_first_assess.jsp";
		form2.submit();
	}
}

function cal_length(val)
{
	// �Է¹��� ���ڿ��� escape() �� �̿��Ͽ� ��ȯ�Ѵ�.
	// ��ȯ�� ���ڿ� �� �����ڵ�(�ѱ� ��)�� ���������� %uxxxx�� ��ȯ�ȴ�.
	var temp_estr = escape(val);
	var s_index = 0;
	var e_index = 0;
	var temp_str = "";
	var cnt = 0;
	
	// ���ڿ� �߿��� �����ڵ带 ã�� �����ϸ鼭 ������ ����.
	while ((e_index = temp_estr.indexOf("%u", s_index)) >= 0) // ������ ���ڿ��� �����Ѵٸ�
	{
	temp_str += temp_estr.substring(s_index, e_index);
	s_index = e_index + 6;
	cnt ++;
	}
	
	temp_str += temp_estr.substring(s_index);
	
	temp_str = unescape(temp_str); // ���� ���ڿ��� �ٲ۴�.
	
	// �����ڵ�� 2����Ʈ �� ����ϰ� �������� 1����Ʈ�� ����Ѵ�.
	return ((cnt * 2) + temp_str.length) + "";
}

function exitInfo(){ //����
	if(confirmMsg('2') == true){
		var form2 = document.form1;	
		form2.action = "pr_common_list.jsp";
		form2.submit();
	}
}

function confirmMsg(number){
	var num = number;
	switch(num) {
		case '1': //���ϱ�
			var result = confirm("�Է��Ͻ� �������� ���������� ���Ͻðڽ��ϱ�?\n�Է��Ͻ� ������ �ѹ� �� Ȯ���Ͻ÷��� ��Ҹ� ��������.\nȮ���� �����ø� �򰡰� �Ϸ�˴ϴ�.");
			return result;
			break;
		case '2': //����
			var result = confirm("1�� �� �Է��� �����Ͻðڽ��ϱ�?\n�Է��Ͻ� ������ ������� �ʽ��ϴ�.\n�����ϼ���");
			return result;
			break;
		default :		
			break;
	}		
}
function downloadArtcl(path, name, ext){ //������ �ٿ�ε�
	var form2 = document.form1;
	//form�� ���� �ѱ�� ���Ͽ� ����ü�� �������� ��ü���� �ִ´�.
	form2.file_path.value = path;
	form2.file_name.value = name;
	form2.file_ext.value = ext;
	form2.target = "_self";
	form2.action = "pr_article_download_file.jsp";
	form2.submit();
}
</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<body>
<form name="form1" action="" method="POST">
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
<input type="hidden" name="file_path" value="<%=file_path%>"/>
<input type="hidden" name="file_name" value="<%=file_name%>"/>
<input type="hidden" name="file_ext" value="<%=file_ext%>"/>
<input type="hidden" name="x_unit" value="<%=x_unit%>"/>
<input type="hidden" name="y_unit" value="<%=y_unit%>"/>
<p class="level2">1�� �� ���� �Է�</p>
<table class="tab2">
<col width="125">
<col width="200">
<col width="125">
<col width="200">
  <tr>
    <th class="thc"><font color = red>����������ȣ</font></th>
    <td class="tdl">
    <%
    	if(ComUtil.isNull(pr_no)){
    %>
    	�ڵ����� ���õ˴ϴ�.
    <%
    	}else{
    %>
    	<%=pr_no%> 
    <%
    	}
    %>
    </td>
    <th class="thc"><font color = blue>����ǥ�ù�ȣ</font></th>
    <td class="tdl">
    <%
    	if(ComUtil.isNull(mgmt_num)){
    %>
    	�ڵ����� ���õ˴ϴ�.
    <%
    	}else{
    %>
    	<%=mgmt_num%> 
    <%
    	}
    %>
    </td>
  </tr>
</table>
<table class="tab2">
  <col width="120">
  <col width="100">
  <col width="430">
  <tr>
    <th class="thc">����</th>
    <th class="thc">����</th>
    <th class="thc">�� ����</th>
  </tr>
  <tr>
    <th rowspan="7" class="thl">1.���� ��� ���</th>
    <td class="tdl">�����ͱ���</td>
    <td class="tdl"><%=db_value %></td>
  </tr>
  <tr>
    <td class="tdl">���μ���</td>
    <td class="tdl"><%=process %></td>
  </tr>
  <tr>
    <td class="tdl">������</td>
    <td class="tdl"><%=expression %></td>
  </tr>
  <tr>
    <td class="tdl">X ����</td>
    <td class="tdl"><%=x_unit %></td>
  </tr>
  <tr>
    <td class="tdl">Y ����</td>
    <td class="tdl"><%=y_unit %></td>
  </tr>
  <tr>
    <td class="tdl">����</td>
    <td class="tdl">			
	     	<img src = "images/file_img/<%=ComUtil.getFileImgName(a_ext)%>" width="16" height="16" border="0"> 
	</td>
  </tr>
  <tr>
    <td class="tdl">�Ǵ�</td>
    <td> 
    <%
    	if(step1_v.equalsIgnoreCase("pass")){
    %>
    	<input type="radio" name="1step" value="pass" checked>����<br> 
  	  	<input type="radio" name="1step" value="reject">������
    <%
    	}else{
    %>
    	<input type="radio" name="1step" value="pass" >����<br> 
  	  	<input type="radio" name="1step" value="reject" checked>������
    <%
    	}
    %>  	  
  	</td>
  </tr>
  <tr>
  	<th rowspan="4" class="thl">2.���� ��� ���</th>
    <td class="tdl">��������</td>
    <td class="tdl"><%=exp_value %></td>
  </tr>  
  <tr>
    <td class="tdl">�������</td>
    <td colspan="3"> 
  	  <textarea name="method" rows="3" cols="55" ><%=ComUtil.isNullStringToEmptyString(method_v)%></textarea>
  	</td>
  </tr>
  <tr>
    <td class="tdl">����ٰ� ����</td>
    <td colspan="3"> 
  	  <textarea name="scient_basis" rows="3" cols="55" ><%=ComUtil.isNullStringToEmptyString(scient_basis_v)%></textarea>
  	</td>
  </tr>
  <tr>
    <td class="tdl">�Ǵ�</td>
    <td> 
    <%
    	if(step2_v.equalsIgnoreCase("pass")){
    %>
    	<input type="radio" name="2step" value="pass" checked>����<br> 
  	  	<input type="radio" name="2step" value="reject">������
    <%
    	}else{
    %>
    	<input type="radio" name="2step" value="pass" >����<br> 
  	  	<input type="radio" name="2step" value="reject" checked>������
    <%
    	}
    %>  
  	</td>
  </tr>
  <tr>
  	<th rowspan="1" class="thl">3.�ұ޼�</th>
    <td class="tdl">�Ǵ�</td>
    <td> 
    <%
    	if(step3_v.equalsIgnoreCase("pass")){
    %>
    	<input type="radio" name="3step" value="pass" checked>����<br> 
  	  	<input type="radio" name="3step" value="reject">������
    <%
    	}else{
    %>
    	<input type="radio" name="3step" value="pass" >����<br> 
  	  	<input type="radio" name="3step" value="reject" checked>������
    <%
    	}
    %>  
  	</td>
  </tr>
  <tr>
  	<th rowspan="4" class="thl">4.��Ȯ�� ����</th>
    <td class="tdl">����� �Ѱ�</td>
    <td colspan="3"> 
  	  <textarea name="scient_limit" rows="3" cols="55" ><%=ComUtil.isNullStringToEmptyString(scient_limit_v)%></textarea>
  	</td>
  </tr>  
  <tr>
    <td class="tdl">���躯�� ����</td>
    <td colspan="3"> 
  	  <textarea name="primary_fact" rows="3" cols="55" ><%=ComUtil.isNullStringToEmptyString(primary_fact_v)%></textarea>
  	</td>
  </tr>
  <tr>
    <td class="tdl">��Ȯ�� ����</td>
    <td colspan="3"> 
  	  <textarea name="measurement" rows="3" cols="55" ><%=ComUtil.isNullStringToEmptyString(measurement_v)%></textarea>
  	</td>
  </tr>
  <tr>
    <td class="tdl">�Ǵ�</td>
    <td> 
    <%
    	if(step4_v.equalsIgnoreCase("pass")){
    %>
    	<input type="radio" name="4step" value="pass" checked>����<br> 
  	  	<input type="radio" name="4step" value="reject">������
    <%
    	}else{
    %>
    	<input type="radio" name="4step" value="pass" >����<br> 
  	  	<input type="radio" name="4step" value="reject" checked>������
    <%
    	}
    %>  
  	</td>
  </tr>  
  <tr>
  	<th rowspan="2" class="thl">5.������</th>
    <td class="tdl">�����ͺм�</td>
    <td colspan="3"> 
  	  <textarea name="data_analy" rows="3" cols="55" ><%=ComUtil.isNullStringToEmptyString(data_analy_v)%></textarea>
  	</td>
  </tr>  
  <tr>
    <td class="tdl">�Ǵ�</td>
    <td> 
    <%
    	if(step5_v.equalsIgnoreCase("pass")){
    %>
    	<input type="radio" name="5step" value="pass" checked>����<br> 
  	  	<input type="radio" name="5step" value="reject">������
    <%
    	}else{
    %>
    	<input type="radio" name="5step" value="pass" >����<br> 
  	  	<input type="radio" name="5step" value="reject" checked>������
    <%
    	}
    %>  
  	</td>
  </tr> 
  <tr>
  	<th rowspan="3" class="thl">6.�ϰ���</th>
    <td class="tdl">������</td>
    <td colspan="3"> 
  	  <textarea name="indirect" rows="3" cols="55" ><%=ComUtil.isNullStringToEmptyString(indirect_v)%></textarea>
  	</td>
  </tr>  
  <tr>
    <td class="tdl">������</td>
    <td colspan="3"> 
  	  <textarea name="direct" rows="3" cols="55" ><%=ComUtil.isNullStringToEmptyString(direct_v)%></textarea>
  	</td>
  </tr>
  <tr>
    <td class="tdl">�Ǵ�</td>
    <td> 
    <%
    	if(step6_v.equalsIgnoreCase("pass")){
    %>
    	<input type="radio" name="6step" value="pass" checked>����<br> 
  	  	<input type="radio" name="6step" value="reject">������
    <%
    	}else{
    %>
    	<input type="radio" name="6step" value="pass" >����<br> 
  	  	<input type="radio" name="6step" value="reject" checked>������
    <%
    	}
    %>  
  	</td>
  </tr>
  <tr>
  	<th rowspan="1" class="thl">1���� ����</th>
    <td colspan="3">
  	  <textarea name="prim_eval" rows="3" cols="70" ><%=ComUtil.isNullStringToEmptyString(prim_eval_v)%></textarea>
  	</td>
  </tr>  
  <tr>
  	<th rowspan="1" class="thl">1���� ���</th>
    <td colspan="3">
    <%
    	if(final_v.equalsIgnoreCase("V")){
    %>
      <input type="radio" name="final" value="validate" checked>��ȿ�� ������<br>
  	  <input type="radio" name="final" value="reject">�Ⱒ<br>
  	  <input type="radio" name="final" value="holdover">������
    <%
    	}else if(final_v.equalsIgnoreCase("R")){
    %>
      <input type="radio" name="final" value="validate" >��ȿ�� ������<br>
  	  <input type="radio" name="final" value="reject" checked>�Ⱒ<br>
  	  <input type="radio" name="final" value="holdover">������
    <%
    	}else{
	%>
      <input type="radio" name="final" value="validate" >��ȿ�� ������<br>
  	  <input type="radio" name="final" value="reject">�Ⱒ<br>
  	  <input type="radio" name="final" value="holdover" checked>������
    <%
    	}
    %>  	  
  	</td>
  </tr> 
</table>
<div class="bbtn_r">
<a href="javascript:assessInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('assessImage','','images/img_cont/assess_upper.gif',1)"><img src="images/img_cont/assess_normal.gif" name="assessImage" width="80" height="24" border="0" id="assessImage" /></a>
<a href="javascript:exitInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('exitImage','','images/img_cont/exit_upper.gif',1)"><img src="images/img_cont/exit_normal.gif" name="exitImage" width="80" height="24" border="0" id="exitImage" /></a>
</div>
</form>
</body>
</html>