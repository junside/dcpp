<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="property_info" class="nfri.dcpp.properties.business.Ctr_Property_Info_Process" scope="page"/>

<%
	/**
	* �⺻ ���� ������ �Է��ϴ� JSP ������(view)
	* by Junside(J.H Park)
	*/
	
	String expression = "";
	String addcode_id = "";//request.getParameter("addcode_id");
	String pr_no = "";
	String index = "";
	String pro_no = request.getParameter("pr_no"); //"B201000075";//
	//Properties_Equation_Get_DbInfo
	//String org_exp = property_info.selectEquationData()
	Vector equa_info = property_info.selectEquationInfo(pro_no);	
	Properties_Basic_Info info = property_info.selectViewPropertySpecInfo(pro_no);
	String org_exp = info.getPL_BI_EXPRESSION();
	
	String[] seq_exp = ComUtil.getSeparatedText(org_exp);
	String text[] = {"�Ի�0","", "ǥ��1", "", "����2", "", "����3", "", "����4", "", "����5"};
	String exp_tag = "";
	for(int j = 0; j< seq_exp.length; j++){
		if(j==1 || j==3 || j==5|| j==7 || j==9){
			
		}else{
			exp_tag = exp_tag + " <tr><th width='150' class='thca'>";
			exp_tag = exp_tag + text[j];
			exp_tag = exp_tag + "<td width='500' class='tdl'><font size='3'><xmp>";
			exp_tag = exp_tag + seq_exp[j];
			exp_tag = exp_tag +"</xmp></font></td></tr>";
		}
	}
	/*

	*/

	String[] part_no = new String[6]; //���ڹ�ȣ
	String[] part_id = new String[6]; //�����̸�
	String[] part_chg = new String[6]; //�̿�ȭ
	String[] part_elc = new String[6]; //���ڹ�ġ
	String[] part_oth = new String[6]; //�̼�����
	
	String new_part_no = ""; //�ű� ���ڹ�ȣ
	String new_part_id = ""; //�ű� �����̸�
	String new_part_chg = ""; //�ű� �̿�ȭ
	String new_part_elc = ""; //�ű� ���ڹ�ġ
	String new_part_oth = ""; //�ű� �̼�����
	
	//System.out.println(equa_info.size());
	
	for(int i = 0; i<equa_info.size(); i++){
		Properties_Equation_Get_DbInfo db_info = (Properties_Equation_Get_DbInfo)equa_info.get(i);
		int j = Integer.parseInt(db_info.getPL_BEI_SEQ()); //������ �����ͼ� ��
		j = j-1;
		String symbol =  ComUtil.isNullStringToEmptyString(db_info.getPL_CPBI_ELE_SYMBOL());
		String num = ComUtil.isNullStringToEmptyString(db_info.getPL_CPBI_ELE_NUM()); //"NULL"���� ����
		String chg = ComUtil.isNullStringToEmptyString(db_info.getPL_BEI_CHG_STATE());
		String elc = ComUtil.isNullStringToEmptyString(db_info.getPL_BEI_ELC_STATE());
		String oth = ComUtil.isNullStringToEmptyString(db_info.getPL_BEI_OTH_STRUC());
		/*if("-".equalsIgnoreCase(symbol)){
			symbol = "";
		}
		if("-".equalsIgnoreCase(num)){
			num = "";
		}
		if("-".equalsIgnoreCase(chg)){
			chg = "";
		}
		if("-".equalsIgnoreCase(elc)){
			elc = "";
		}
		if("-".equalsIgnoreCase(oth)){
			oth = "";
		}*/	
		
		part_no[j] = symbol;
		part_id[j] = num;
		part_chg[j] = chg;
		part_elc[j] = elc;
		part_oth[j] = oth;
		addcode_id = ComUtil.isNullToEmptyString(db_info.getPL_CI_ID());		
	}
	//System.out.println("addcode_id : " + addcode_id);
	
	if(addcode_id.equalsIgnoreCase("+")){
		addcode_id = "AP_02";
	}else{
		addcode_id = "AP_01";
	}
	

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

function updateInfo(seq){ //������Ʈ �۾�
	//addExpression();
	//if(saveInfoCheck() == false){
		var form2 = document.form1;	
		form2.action = "pr_modify_target_part_info_check.jsp?flag=1&seq="+seq;
		form2.submit();
	//}else{
	//	return;
	//}
}

function insertInfo(seq){ //�Է� �۾�
	//addExpression();
	//if(saveInfoCheck() == false){
		var form2 = document.form1;	
		form2.action = "pr_modify_target_part_info_check.jsp?flag=2&seq="+seq;
		form2.submit();
	//}else{
	//	return;
	//}
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
	
	//1. ���� ��ȣ üũ
	
	//2. ���� üũ
	if(form2.part_no[0].value.length < 1){	
		var no = '0';		
		errorV = true;
		alert("�Ի� 1�� ���� �Է��ϼ���");
		window.open("pr_select_part_info.jsp?no="+no,"reference_search","width=100%,height=100%,toolbar=no,location=no,status=no");
		return;
	}
	if(form2.part_no[1].value.length < 1){
		var no = '1';			
		errorV = true;
		alert("ǥ�� 2�� ���� �Է��ϼ���");
		window.open("pr_select_part_info.jsp?no="+no,"reference_search","width=100%,height=100%,toolbar=no,location=no,status=no");		
		return;
	}	
	return errorV;
}

</script>

<form name="form1" action="" method="POST">
<input type="hidden" name="pr_expression" value=""/>
<input type="hidden" name="pr_no" value='<%=pro_no%>'>
<input type="hidden" name="index" value='<%=index%>'>
<jsp:useBean id="option_code" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
<p class="level2">������ ���� (���������� ����, ���� ����) ����</p>
<table class="tab2">
  <col width="150">
  <col width="500">
  <tr>
    <th width="150" class="thca">���� ��ȣ</th>
    <td width="500" class="tdl"><%=pro_no%></td>
  </tr>
  <%=exp_tag%>
  <tr>
    <th width="150" class="thca">���� ������ ǥ��</th>
    <td width="500" class="tdl"><%=org_exp%></td>
  </tr>
  <tr>
    <th width="150" class="thca">���� ������ ǥ��</th>
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
    <th width="50" rowspan="5" class="thca">�浹��</th>
    <td width="50" rowspan="5" class="tdca"><font color = red>�Ի�0</font></td>
    <td width="110" class="tdc">�����̸�</td>
    <td width="90" class="tdc">
    	<input type="text" name="part_no" onclick="getAtomicInfo(0)" onchange="addExpression()" value="<%=part_no[0]%>" size="15" maxlength="50" >
    	<input type="hidden" name="part_id" value="<%=ComUtil.isNullToEmptyString(part_id[0])%>">
    </td>
    <th width="50" rowspan="5" class="thca">+</th>
    <td width="50" rowspan="5" class="tdca"><font color = red>ǥ��1</font></td>
    <td width="110" class="tdc">�����̸�</td>
    <td width="90" class="tdc">
    	<input type="text" name="part_no" onclick="getAtomicInfo(1)" onchange="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_no[1])%>" size="15" maxlength="50" >
    	<input type="hidden" name="part_id" value="<%=part_id[1]%>">
    </td>
  </tr>
  <tr>
    <td width="110" class="tdc">�̿�ȭ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_chg" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_chg[0])%>" size="15" maxlength="500">
    </td>
    <td width="110" class="tdc">�̿�ȭ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_chg" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_chg[1])%>" size="15" maxlength="500">
    </td>
  </tr>
  <tr>
    <td width="110" class="tdc">���ڹ�ġ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_elc" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_elc[0])%>" size="15" maxlength="500">
    </td>
    <td width="110" class="tdc">���ڹ�ġ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_elc" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_elc[1])%>" size="15" maxlength="500">
    </td>
  </tr>
  <tr>
    <td width="110" class="tdc">�̼�����</td>
    <td width="90" class="tdc">
      <input type="text" name="part_oth" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_oth[0])%>" size="15" maxlength="500">
    </td>
    <td width="110" class="tdc">�̼�����</td>
    <td width="90" class="tdc">
      <input type="text" name="part_oth" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_oth[1])%>" size="15" maxlength="500">
    </td>
  </tr>
    <tr>
    <td width="110" class="tdc">======></td>
    <td width="90" class="tdc">
      	<a href="javascript:updateInfo(0)">���� </a> / / / / / / <a href="javascript:insertInfo(0)"> �Է� </a>
    </td>
    <td width="110" class="tdc">======></td>
    <td width="90" class="tdc">
      	<a href="javascript:updateInfo(1)">���� </a> / / / / / / <a href="javascript:insertInfo(1)"> �Է� </a>
    </td>
  </tr>
  <tr>
    <th rowspan="5" class="thca">�浹��</th>
    <td rowspan="5" class="tdca">����2</td>
    <td width="110" class="tdc">�����̸�</td>
    <td width="90" class="tdc">
    	<input type="text" name="part_no" onclick="getAtomicInfo(2)" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_no[2])%>" size="15" >
    	<input type="hidden" name="part_id" value="<%=part_id[2]%>">
    </td>
    <th rowspan="5" class="thca">+</th>
    <td rowspan="5" class="tdca">����3</td>
    <td width="110" class="tdc">�����̸�</td>
    <td width="90" class="tdc">
    	<input type="text" name="part_no" onclick="getAtomicInfo(3)" onChange="addExpression()" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_no[3])%>" size="15" maxlength="50" >
    	<input type="hidden" name="part_id" value="<%=part_id[3]%>">
    </td>
  </tr>
  <tr>
    <td width="110" class="tdc">�̿�ȭ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_chg" onkeyup="addExpression()"value="<%=ComUtil.isNullToEmptyString(part_chg[2])%>" size="15" maxlength="500">
    </td>
    <td width="110" class="tdc">�̿�ȭ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_chg" onkeyup="addExpression()"value="<%=ComUtil.isNullToEmptyString(part_chg[3])%>" size="15" maxlength="500">
    </td>
    </tr>
  <tr>
    <td width="110" class="tdc">���ڹ�ġ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_elc" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_elc[2])%>" size="15" maxlength="500">
    </td>
    <td width="110" class="tdc">���ڹ�ġ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_elc" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_elc[3])%>" size="15" maxlength="500">
    </td>
    </tr>
  <tr>
    <td width="110" class="tdc">�̼�����</td>
    <td width="90" class="tdc">
      <input type="text" name="part_oth" onclick="insertExpress(2)" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_oth[2])%>" size="15" maxlength="500">
    </td>
    <td width="110" class="tdc">�̼�����</td>
    <td width="90" class="tdc">
      <input type="text" name="part_oth" onclick="insertExpress(3)" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_oth[3])%>" size="15" maxlength="500">
    </td>
  </tr>
  <tr>
    <td width="110" class="tdc">======></td>
    <td width="90" class="tdc">
      	<a href="javascript:updateInfo(2)">���� </a> / / / / / / <a href="javascript:insertInfo(2)"> �Է� </a>
    </td>
    <td width="110" class="tdc">======></td>
    <td width="90" class="tdc">
      	<a href="javascript:updateInfo(3)">���� </a> / / / / / / <a href="javascript:insertInfo(3)"> �Է� </a>
    </td>
  </tr>
  <tr>
    <th rowspan="5" class="thca"><span class="tdc">
      <select name="part_sp_option" onChange="addExpression()">
			<%
				Vector ap_code = option_code.getAPOption();
				int ap_count = ap_code.size();
				for (int i = 0; i < ap_count; i++) {
					Code_Info code = (Code_Info) ap_code.elementAt(i);
					String code_id = code.getPL_CI_ID();
					String code_name = code.getPL_CI_NAME();
					String code_exp = code.getPL_CI_ID_EXP();					
			%>
			<option value='<%=code_exp%>'<%=code_id.equals(addcode_id)?"selected":""%>><%=code_exp%></option>
			
			<%
				}
			%>
		</select>
		<input type="hidden" name="addcode_id" value=<%=addcode_id%>></input>
    </span></th>
    <td rowspan="5" class="tdca">����4</td>
    <td width="110" class="tdc">�����̸�</td>
    <td width="90" class="tdc">
    	<input type="text" name="part_no" onclick="getAtomicInfo(4)" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_no[4])%>" size="15" maxlength="50" >
    	<input type="hidden" name="part_id" value="<%=part_id[4]%>">
    </td>
    <th rowspan="5" class="thca">+</th>
    <td rowspan="5" class="tdca">����5</td>
    <td width="110" class="tdc">�����̸�</td>
    <td width="90" class="tdc">
    	<input type="text" name="part_no" onclick="getAtomicInfo(5)" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_no[5])%>" size="15" maxlength="50" >
    	<input type="hidden" name="part_id" value="<%=part_id[5]%>">
    </td>
  </tr>
  <tr>
    <td width="110" class="tdc">�̿�ȭ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_chg" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_chg[4])%>" size="15" maxlength="500">
    </td>
    <td width="110" class="tdc">�̿�ȭ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_chg" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_chg[5])%>" size="15" maxlength="500">
    </td>
  </tr>
  <tr>
    <td width="110" class="tdc">���ڹ�ġ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_elc" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_elc[4])%>" size="15" maxlength="500">
    </td>
    <td width="110" class="tdc">���ڹ�ġ</td>
    <td width="90" class="tdc">
      <input type="text" name="part_elc" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_elc[5])%>" size="15" maxlength="500">
    </td>
  </tr>
  <tr>
    <td width="110" class="tdc">�̼�����</td>
    <td width="90" class="tdc">
      <input type="text" name="part_oth" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_oth[4])%>" size="15" maxlength="500">
    </td>
    <td width="110" class="tdc">�̼�����</td>
    <td width="90" class="tdc">
      <input type="text" name="part_oth" onkeyup="addExpression()" value="<%=ComUtil.isNullToEmptyString(part_oth[5])%>" size="15" maxlength="500">
    </td>
  </tr>
  <tr>
    <td width="110" class="tdc">======></td>
    <td width="90" class="tdc">
      	<a href="javascript:updateInfo(4)">���� </a> / / / / / / <a href="javascript:insertInfo(4)"> �Է� </a>
    </td>
    <td width="110" class="tdc">======></td>
    <td width="90" class="tdc">
      	<a href="javascript:updateInfo(5)">���� </a> / / / / / / <a href="javascript:insertInfo(5)"> �Է� </a>
    </td>
  </tr>
</table>
<script language="javascript">
	addExpression();
</script>
</form>
</body>
</html>