<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<%
	/**
	* �⺻ ���� ������ �Է��ϴ� JSP ������(view)
	* by Junside(J.H Park)
	*/
	
	String expression = "";
	String addcode_id = request.getParameter("addcode_id");
	String pr_no = "";
	String index = "";
	String pro_no = request.getParameter("pr_no");
	
	String user_id = (String)session.getAttribute("user_id");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
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

function getReferenceArtcl(){ //������ ��������
	window.open("pr_article_search.jsp","article_search","width=700,height=700");
	
}

function getAtomicInfo(no){//�������� ��������
	var part_no = "part_no[" + no + "]";//value
	var part_id = "part_id[" + no + "]";//id
	window.open("pr_select_part_info.jsp?id="+part_id+"&value="+part_no,"reference_search","width=700,height=600,toolbar=no,location=no,status=no");
}
function insertElcExpress(no){	
	var part_elc = "part_elc[" + no + "]";//id
	window.open("pr_insert_expression.jsp?id="+part_elc,"insert_elc_expression","width=700,height=600,toolbar=no,location=no,status=no");
}
function insertExpress(no){
	var part_oth = "part_oth[" + no + "]";//id
	window.open("pr_insert_expression.jsp?id="+part_oth,"insert_expression","width=700,height=600,toolbar=no,location=no,status=no");
}

function makeSup(sup_org){ //sup �±� ����ϱ�
	return sup_org.sup();
}

function addExpression(){ //������ ǥ�� ����� ��ũ��Ʈ
	var form2 = document.form1;
	var part_express = "";
	var exp_text = document.getElementById("part_exp");
	
	for(var i=0; i < form2.part_no.length ; i++){
		var part_no_name = form2.part_no[i].value;
		
		if(form2.part_no[i].value.length > 0)
		{
			//������ ���� ��ȣ �ο�
			if(i==1 || i==3 || i==5){
				part_express = part_express + " + ";
			}else if(i==2){
				part_express = part_express + " -&gt; ";
			}else if(i==4){
				part_express = part_express + " " + form2.part_sp_option.value + " ";
			}
			
			part_express = part_express + part_no_name;
			//�̿�ȭ ��
			if(form2.part_chg[i].value.length > 0)
			{
				var f_sup = "<sup>";
				var e_sup = "</sup>";
				//var part_no_chg = form2.part_chg[i].value;
				var part_no_chg = makeSup(form2.part_chg[i].value);
				//var part_no_chg = f_sup + form2.part_chg[i].value + e_sup;
				part_express = part_express + part_no_chg;
			}
			//���ڹ�ġ ��
			if(form2.part_elc[i].value.length > 0)
			{
				var part_no_elc = form2.part_elc[i].value;
				
				if(part_no_elc == '��')
				{
					part_no_elc = "&Sigma;";
				}else if(part_no_elc == '��')
				{
					part_no_elc = "&Pi;";
				}else if(part_no_elc == '��')
				{

					part_no_elc = "&Delta;";										
				}
											
				
				//���ڹ�ġ�� ���� ��� �̼����� �� 
				if(form2.part_oth[i].value.length > 0)
				{
					var part_no_oth = form2.part_oth[i].value;
					part_express = part_express + "(" + part_no_elc;				
					part_express = part_express + "," + part_no_oth;
					part_express = part_express + ")";
				}else{
					part_express = part_express + "(" + part_no_elc;				
					part_express = part_express + ")";
				}
			}
			else{ 
				//���ڹ�ġ�� ���� ��� �̼����� ��
				if(form2.part_oth[i].value.length > 0)
				{
					var part_no_oth = form2.part_oth[i].value;
					part_express = part_express + "(" + part_no_oth;				
					part_express = part_express + ")";
				}
			}				
			//form2.part_exp.value = part_express;
			//var full_exp = encodeURI(part_express);
			
			exp_text.innerHTML = part_express;
			
		}
	}
	form2.pr_expression.value = part_express;
}

function nextInfo(){ //���� �̵�
	addExpression();
	if(saveInfoCheck() == false){
			var form2 = document.form1;	
			form2.action = "pr_insert_property_info_check.jsp?index=2";
			form2.submit();
	}else{
		return;
	}
}

function exitInfo(){ //����
	if(exitMsg() == true){
		var form2 = document.form1;	
		form2.target = "_self";
		form2.action = "pr_common_list.jsp";
		form2.submit();	
	}
}

function nextMsg(){
	var result = confirm("���� �ܰ�� �����Ͻðڽ��ϱ�?\n�Է��Ͻ� ������ ������ �Ұ����Ͽ��� �ѹ��� Ȯ���ϼ���.");
	return result;
}

function exitMsg(){
	var result = confirm("�۾��� �����Ͻðڽ��ϱ�?\n�Է��Ͻ� ������ ������� �ʽ��ϴ�.");
	return result;
}

function saveInfoCheck(){ //�Է� ������ ��ȿ������
	var form2 = document.form1;	
	var errorV = new Boolean(false); //������ ��Ÿ���� ������ �⺻���� false�� ����	
	
	//0. �⺻ ���� �Է� üũ
	if(form2.tb_option.value=='TB_00'){
		errorV = true;
		alert("��з� ������ �����ϼ���!");
		form2.tb_option.focus();
		return;
	}
	if(form2.db_option.value=='DB_00'){
		errorV = true;
		alert("�����ͺз� ������ �����ϼ���!");
		form2.db_option.focus();
		return;
	}
	if(form2.mp_option.value=='MP_00'){
		errorV = true;
		alert("�����μ��� ������ �����ϼ���!");
		form2.mp_option.focus();
		return;
	}
	if(form2.sp_option.value=='SP_00'){
		errorV = true;
		alert("�����μ��� ������ �����ϼ���!");
		form2.sp_option.focus();
		return;
	}
	if(form2.ic_option.value=='IC_00'){
		errorV = true;
		alert("�浹��� ������ �����ϼ���!");
		form2.ic_option.focus();
		return;
	}
	if(form2.et_option.value=='ET_00'){
		errorV = true;
		alert("�������� ������ �����ϼ���!");
		form2.et_option.focus();
		return;
	}
	
	//1. ���� ��ȣ üũ
	if(form2.pr_ref_artcl_no.value.length < 1){
		errorV = true;
		alert("������ ������ �����ϼ���!");
		window.open("pr_article_search.jsp","article_search","width=700,height=500,toolbar=no,location=no,status=no");
		return;
	}
	
	//2. ���� üũ
	if(form2.part_no[0].value.length < 1){	
		var no = '0';		
		errorV = true;
		alert("�Ի� 1�� ���� �Է��ϼ���");
		window.open("pr_select_part_info.jsp?no="+no,"reference_search","width=700,height=500,toolbar=no,location=no,status=no");
		return;
	}
	if(form2.part_no[1].value.length < 1){
		var no = '1';			
		errorV = true;
		alert("ǥ�� 2�� ���� �Է��ϼ���");
		window.open("pr_select_part_info.jsp?no="+no,"reference_search","width=700,height=500,toolbar=no,location=no,status=no");		
		return;
	}	
	return errorV;
}

</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<input type="hidden" name="pr_expression" value=""/>
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
<input type="hidden" name="index" value='<%=index%>'>
<input type="hidden" name="user_id" value='<%=user_id%>'>
<jsp:useBean id="option_code" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
<p class="level2">���� �⺻ ���� �Է�</p>
<table class="tab2">
<col width="100">
<col width="190">
<col width="100">
<col width="200">
<col width="60">
  <tr>
    <th class="thc"><font color = red>����������ȣ</font></th>
    <td class="tdc">
    <%
    	if(ComUtil.isNull(pr_no)){
    %>
    	<input type="text" name="pr_no" value="�ڵ����� ���õ˴ϴ�." size="24" readonly> 
    <%
    	}else{
    %>
    	<input type="text" name="pr_no" value="<%=pr_no%>" size="24" readonly> 
    <%
    	}
    %>
    </td>
    <th class="thc"><font color = red>������</font></th>
    <td class="tdc">
    	<input type="text" name="pr_ref_artcl_no" value="" size="24" readonly>
    </td>
    <td class="tdc">
	    <a href="javascript:getReferenceArtcl()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','images/img_cont/search_s_upper.gif',1)"><img src="images/img_cont/search_s_normal.gif" name="Image1" width="60" height="20" border="0" id="Image1" />		</a>    </td>
  </tr>
</table>
<table class="tab2">
<col width="90">
<col width="185">
<col width="90">
<col width="265">
  <tr>
  	<th class="thc"><font color = red>��з�</font></th>
     	<td class="tdl">
		<select name="tb_option" onChange="">
			<%
				Vector tb_code = option_code.getTBOption();
				int tb_count = tb_code.size();
				for (int i = 0; i < tb_count; i++) {
					Code_Info code = (Code_Info) tb_code.elementAt(i);
					String code_id = code.getPL_CI_ID();
					String code_name = code.getPL_CI_NAME();
					String code_exp = code.getPL_CI_ID_EXP();
			%>
			<option value='<%=code_id%>'<%=code_id.equals("TB_00")?"selected":""%>><%=code_exp%></option>
			<%
				}
			%>
		</select>
   	</td>
   	<th class="thc"><font color = red>�����ͺз�</font></th>
     	<td class="tdl">
		<select name="db_option" onChange="">
			<%
				Vector db_code = option_code.getDBOption();
				int db_count = db_code.size();
				for (int i = 0; i < db_count; i++) {
					Code_Info code = (Code_Info) db_code.elementAt(i);
					String code_id = code.getPL_CI_ID();
					String code_name = code.getPL_CI_NAME();
					String code_exp = code.getPL_CI_ID_EXP();
			%>
			<option value='<%=code_id%>'<%=code_id.equals("DB_00")?"selected":""%>><%=code_exp%></option>
			<%
				}
			%>
		</select>
   	</td>
  </tr>
  <tr>
  <th class="thc"><font color = red>�����μ���</font></th>
     	<td class="tdl">
		<select name="mp_option" onChange="">
			<%
				Vector mp_code = option_code.getMPOption();
				int mp_count = mp_code.size();
				for (int i = 0; i < mp_count; i++) {
					Code_Info code = (Code_Info) mp_code.elementAt(i);
					String code_id = code.getPL_CI_ID();
					String code_name = code.getPL_CI_NAME();
					String code_exp = code.getPL_CI_ID_EXP();
			%>
			<option value='<%=code_id%>'<%=code_id.equals("MP_00")?"selected":""%>><%=code_exp%></option>
			<%
				}
			%>
		</select>
   	</td>
   	<th class="thc"><font color = red>�����μ���</font></th>
     	<td class="tdl">
		<select name="sp_option" onChange="">
			<%
				Vector sp_code = option_code.getSPOption();
				int sp_count = sp_code.size();
				for (int i = 0; i < sp_count; i++) {
					Code_Info code = (Code_Info) sp_code.elementAt(i);
					String code_id = code.getPL_CI_ID();
					String code_name = code.getPL_CI_NAME();
					String code_exp = code.getPL_CI_ID_EXP();
			%>
			<option value='<%=code_id%>'<%=code_id.equals("SP_00")?"selected":""%>><%=code_exp%></option>
			<%
				}
			%>
		</select>
   	</td>
  </tr>
  <tr>
   	<th class="thc"><font color = red>�浹���</font></th>
     	<td class="tdl">
		<select name="ic_option" onChange="">
			<%
				Vector ic_code = option_code.getICOption();
				int ic_count = ic_code.size();
				for (int i = 0; i < ic_count; i++) {
					Code_Info code = (Code_Info) ic_code.elementAt(i);
					String code_id = code.getPL_CI_ID();
					String code_name = code.getPL_CI_NAME();
					String code_exp = code.getPL_CI_ID_EXP();
			%>
			<option value='<%=code_id%>'<%=code_id.equals("IC_00")?"selected":""%>><%=code_exp%></option>
			<%
				}
			%>
		</select>
   	</td>
   	<th class="thc"><font color = red>��������</font></th>
     	<td class="tdl">
		<select name="et_option" onChange="">
			<%
				Vector et_code = option_code.getETOption();
				int et_count = et_code.size();
				for (int i = 0; i < et_count; i++) {
					Code_Info code = (Code_Info) et_code.elementAt(i);
					String code_id = code.getPL_CI_ID();
					String code_name = code.getPL_CI_NAME();
					String code_exp = code.getPL_CI_ID_EXP();
			%>
			<option value='<%=code_id%>'<%=code_id.equals("ET_00")?"selected":""%>><%=code_exp%></option>
			<%
				}
			%>
		</select>
   	</td>
  </tr>
</table>
<p class="level2">������ ���� (���������� ����, ���� ����) �Է�</p>
<table class="tab2">
  <col width="100">
  <col width="550">
  <tr>
    <th width="150" class="thc">���� ������ ǥ��</th>
    <td width="500" class="tdl" id="part_exp"></td>
  </tr>
</table>
<table class="tab2">
  <col width="50">
  <col width="50">
  <col width="110">
  <col width="90">
  <col width="50">
  <col width="50">
  <col width="110">
  <col width="90">
  <tr>
    <th width="50" rowspan="4" class="thca">�浹��</th>
    <td width="50" rowspan="4" class="tdca"><font color = red>�Ի�1</font></td>
    <td width="110" class="tdc">�����̸�</td>
    <td width="90" class="tdc">
    	<input type="text" name="part_no" onclick="getAtomicInfo(0)" onchange="addExpression()" value="" size="15" maxlength="50" readonly>
    	<input type="hidden" name="part_id" value="">
    </td>
    <th width="50" rowspan="4" class="thca">+</th>
    <td width="50" rowspan="4" class="tdca"><font color = red>ǥ��2</font></td>
    <td width="110" class="tdc">�����̸�</td>
    <td width="90" class="tdc">
    	<input type="text" name="part_no" onclick="getAtomicInfo(1)" onchange="addExpression()" value="" size="15" maxlength="50" readonly>
    	<input type="hidden" name="part_id" value="">
    </td>
  </tr>
  <tr>
    <td width="110" class="tdc">�̿�ȭ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_chg" onkeyup="addExpression()" value="" size="15" maxlength="500">
    </td>
    <td width="110" class="tdc">�̿�ȭ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_chg" onkeyup="addExpression()" value="" size="15" maxlength="500">
    </td>
  </tr>
  <tr>
    <td width="110" class="tdc">���ڹ�ġ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_elc" onclick="" onkeyup="addExpression()" value="" size="15" maxlength="500">
      <%
      //<input type="text" name="part_elc" onclick="insertElcExpress(0)" onkeyup="addExpression()" value="" size="15" maxlength="500">
      %>
    </td>
    <td width="110" class="tdc">���ڹ�ġ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_elc" onclick="" onkeyup="addExpression()" value="" size="15" maxlength="500">
      <%
      //<input type="text" name="part_elc" onclick="insertElcExpress(1)" onkeyup="addExpression()" value="" size="15" maxlength="500">
      %>
    </td>
  </tr>
  <tr>
    <td width="110" class="tdc">�̼�����</td>
    <td width="90" class="tdc">
      <input type="text" name="part_oth" onclick="" onkeyup="addExpression()" value="" size="15" maxlength="500">
      <%
      //<input type="text" name="part_oth" onclick="insertExpress(0)" onkeyup="addExpression()" value="" size="15" maxlength="500">
      %>
    </td>
    <td width="110" class="tdc">�̼�����</td>
    <td width="90" class="tdc">
      <input type="text" name="part_oth" onclick="" onkeyup="addExpression()" value="" size="15" maxlength="500">
      <%
      //<input type="text" name="part_oth" onclick="insertExpress(1)" onkeyup="addExpression()" value="" size="15" maxlength="500">
      %>
    </td>
  </tr>
  <tr>
    <th rowspan="4" class="thca">�浹��</th>
    <td rowspan="4" class="tdca">����3</td>
    <td width="110" class="tdc">�����̸�</td>
    <td width="90" class="tdc">
    	<input type="text" name="part_no" onclick="getAtomicInfo(2)" onchange="addExpression()" value="" size="15" readonly>
    	<input type="hidden" name="part_id" value="">
    </td>
    <th rowspan="4" class="thca">+</th>
    <td rowspan="4" class="tdca">����4</td>
    <td width="110" class="tdc">�����̸�</td>
    <td width="90" class="tdc">
    	<input type="text" name="part_no" onclick="getAtomicInfo(3)" onchange="addExpression()" value="" size="15" maxlength="50" readonly>
    	<input type="hidden" name="part_id" value="">
    </td>
  </tr>
  <tr>
    <td width="110" class="tdc">�̿�ȭ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_chg" onkeyup="addExpression()" value="" size="15" maxlength="500">
    </td>
    <td width="110" class="tdc">�̿�ȭ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_chg" onkeyup="addExpression()" value="" size="15" maxlength="500">
    </td>
    </tr>
  <tr>
    <td width="110" class="tdc">���ڹ�ġ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_elc" onclick="" onkeyup="addExpression()" value="" size="15" maxlength="500">
    </td>
    <td width="110" class="tdc">���ڹ�ġ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_elc" onclick="" onkeyup="addExpression()" value="" size="15" maxlength="500">
    </td>
    </tr>
  <tr>
    <td width="110" class="tdc">�̼�����</td>
    <td width="90" class="tdc">
      <input type="text" name="part_oth" onclick="" onkeyup="addExpression()" value="" size="15" maxlength="500">
    </td>
    <td width="110" class="tdc">�̼�����</td>
    <td width="90" class="tdc">
      <input type="text" name="part_oth" onclick="" onkeyup="addExpression()" value="" size="15" maxlength="500">
    </td>
    </tr>
  <tr>
    <th rowspan="4" class="thca"><span class="tdc">
      <select name="part_sp_option" onChange="addExpression()">
			<%
				Vector ap_code = option_code.getAPOption();
				int ap_count = ap_code.size();
				for (int i = 0; i < ap_count; i++) {
					Code_Info code = (Code_Info) ap_code.elementAt(i);
					addcode_id = code.getPL_CI_ID();
					String code_name = code.getPL_CI_NAME();
					String code_exp = code.getPL_CI_ID_EXP();
			%>
			<option value='<%=code_exp%>'<%=addcode_id.equals("AP_01")?"selected":""%>><%=code_exp%></option>
			
			<%
				}
			%>
		</select>
		<input type="hidden" name="addcode_id" value=<%=addcode_id%>></input>
    </span></th>
    <td rowspan="4" class="tdca">����5</td>
    <td width="110" class="tdc">�����̸�</td>
    <td width="90" class="tdc">
    	<input type="text" name="part_no" onclick="getAtomicInfo(4)" onchange="addExpression()" value="" size="15" maxlength="50" readonly>
    	<input type="hidden" name="part_id" value="">
    </td>
    <th rowspan="4" class="thca">+</th>
    <td rowspan="4" class="tdca">����6</td>
    <td width="110" class="tdc">�����̸�</td>
    <td width="90" class="tdc">
    	<input type="text" name="part_no" onclick="getAtomicInfo(5)" onchange="addExpression()" value="" size="15" maxlength="50" readonly>
    	<input type="hidden" name="part_id" value="">
    </td>
  </tr>
  <tr>
    <td width="110" class="tdc">�̿�ȭ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_chg" onkeyup="addExpression()" value="" size="15" maxlength="500">
    </td>
    <td width="110" class="tdc">�̿�ȭ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_chg" onkeyup="addExpression()" value="" size="15" maxlength="500">
    </td>
  </tr>
  <tr>
    <td width="110" class="tdc">���ڹ�ġ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_elc" onclick="" onkeyup="addExpression()" value="" size="15" maxlength="500">
    </td>
    <td width="110" class="tdc">���ڹ�ġ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_elc" onclick="" onkeyup="addExpression()" value="" size="15" maxlength="500">
    </td>
  </tr>
  <tr>
    <td width="110" class="tdc">�̼�����</td>
    <td width="90" class="tdc">
      <input type="text" name="part_oth" onclick="" onkeyup="addExpression()" value="" size="15" maxlength="500">
    </td>
    <td width="110" class="tdc">�̼�����</td>
    <td width="90" class="tdc">
      <input type="text" name="part_oth" onclick="" onkeyup="addExpression()" value="" size="15" maxlength="500">
    </td>
  </tr>
</table>
<div class="bbtn_r">
<a href="javascript:nextInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('nextImage','','images/img_cont/next_upper.gif',1)"><img src="images/img_cont/next_normal.gif" name="nextImage" width="80" height="24" border="0" id="nextImage" /></a>
<a href="javascript:exitInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('exitImage','','images/img_cont/exit_upper.gif',1)"><img src="images/img_cont/exit_normal.gif" name="exitImage" width="80" height="24" border="0" id="exitImage" /></a>
</div>
<script language="javascript">
	addExpression();
</script>
</form>
</body>
</html>