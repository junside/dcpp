<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="ctr_data" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
<jsp:useBean id="property_assess_data" class="nfri.dcpp.properties.business.Ctr_Property_Assess_Info" scope="page"/>

<%
	/**
	* Fitting�� �غ� �ϴ� ������
	* by Junside(J.H Park)
	*/
	String user_id = ComUtil.isNullToDashString((String)session.getAttribute("user_id"));
	if(user_id.equalsIgnoreCase("-")){
		%>
		<script language = javascript>
		alert("�ٽ� �α��� �ϼ���.");
		</script>
		<%
		response.sendRedirect("c_login_inner.jsp?flag=AU");
	}else{

	String param_ic_option     = request.getParameter("ic_option"); // �浹���
	String param_projectile    = request.getParameter("projectile"); // �Ի�����
	String param_projectile_id = request.getParameter("projectile_id"); // �Ի�����ID
	String param_mp_option     = request.getParameter("mp_option"); // �����μ���
	String param_sp_option     = request.getParameter("sp_option"); // �����μ���
	
	String exp_param_mp_option = ctr_data.getExpName(param_mp_option);
	String exp_param_sp_option = ctr_data.getExpName(param_sp_option);
	String process_option = ComUtil.getAbbrString(exp_param_mp_option, 10) + ", " + ComUtil.getAbbrString(exp_param_sp_option, 10);
	
	String param_xax_unit      = request.getParameter("xax_unit"); // X ����
	String param_xay_unit      = request.getParameter("xay_unit"); // Y ����
	String param_tg_name       = request.getParameter("tg_name"); // ǥ������
	String param_tg_name_id    = request.getParameter("tg_name_id"); // ǥ������ ID
	String param_tg_ionic      = ComUtil.isNullToDashString(request.getParameter("tg_ionic")); // ǥ������ �̿�ȭ
	String param_tg_elec       = ComUtil.isNullToDashString(request.getParameter("tg_elec")); // ǥ������ ���ڹ�ġ
	String param_tg_fine       = ComUtil.isNullToDashString(request.getParameter("tg_fine")); // ǥ������ �̼�����
	String param_pd_name       = ComUtil.isNullToDashString(request.getParameter("pd_name")); // �������� 
	String param_pd_name_id    = ComUtil.isNullToDashString(request.getParameter("pd_name_id")); // �������� ID 
	String param_pd_ionic      = ComUtil.isNullToDashString(request.getParameter("pd_ionic")); // �������� �̿�ȭ
	String param_pd_elec       = ComUtil.isNullToDashString(request.getParameter("pd_elec")); // �������� ���ڹ�ġ
	String param_pd_fine       = ComUtil.isNullToDashString(request.getParameter("pd_fine")); // �������� �̼�����
	
	//X, Y �ּ�, �ִ밪 üũ ����
	String param_graph_xrange_v      = ComUtil.isNullToDashString(request.getParameter("graph_xrange_value"));
	String param_graph_yrange_v      = ComUtil.isNullToDashString(request.getParameter("graph_yrange_value"));

	//X,Y �ּ�, �ִ밪 ����
	String param_x_min_v      = ComUtil.isNullToDashString(request.getParameter("graph_xrange_min_value")); // X�� �ּ�
	String param_x_max_v       = ComUtil.isNullToDashString(request.getParameter("graph_xrange_max_value")); // X�� �ִ�
	String param_y_min_v       = ComUtil.isNullToDashString(request.getParameter("graph_yrange_min_value")); // Y�� �ּ�
	String param_y_max_v       = ComUtil.isNullToDashString(request.getParameter("graph_yrange_max_value")); // Y�� �ִ� 
	
	//System.out.println("param_graph_xrange_v : " + param_graph_xrange_v);
	//System.out.println("param_graph_yrange_v : " + param_graph_yrange_v);
	//System.out.println("param_x_min_v : " + param_x_min_v);
	//System.out.println("param_x_max_v : " + param_x_max_v);
	//System.out.println("param_y_min_v : " + param_y_min_v);
	//System.out.println("param_y_max_v : " + param_y_max_v);
	
	//Vector search_condition = new Vector();
	//search_condition.addElement();
	
	//����Ʈ�� ������ ������������ ������
	Vector pd_info = property_assess_data.searchAssessPropertyListInfo(request);
	
	//ǥ���� �����
	String pd_expression = property_assess_data.makeAssessExpression(request);
	
	//X�� ���� ǥ��
	String X_Title = ctr_data.getExpName(param_xax_unit);
	//Y�� ���� ǥ��
	String Y_Title = ctr_data.getExpName(param_xay_unit);
	
	String whereCondtion = property_assess_data.getConditionInfo(request);
	//System.out.println("whereCondtion : " + whereCondtion);
	
	//����Ʈ�� ���Ե� ���������� ���� �׷��� �����͸� ������	
	//Vector gd_info = graph_data.selectGraphBasicData(pr_no);
	int dt_count = pd_info.size();//dt_count = 0;//dt_info.size();
	
	//���ο� ��� ���� ���� ��ȣ ����
	
	String param_v_pro_no     = ComUtil.isNullToDashString(request.getParameter("v_pro_no")); //������� ������ȣ
	//System.out.println("v_pro_no : "+ param_v_pro_no);
	
	String block_state = "T";
	
	//1. ���� Temp ������ ����������
	boolean del_value = false;
	del_value = property_assess_data.deleteFittingTempData(user_id);		
	System.out.println("Fitting Temp Delete : "+del_value);
	if(del_value == true){ //2. Temp Data�� ����� ����������..
	}else{
		System.out.println("Temp Data Delete Fail!!!");
	}
	
	if(param_v_pro_no.equalsIgnoreCase("-")){ // 3. ������� ������ȣ�� �������� ���� ���..
		//3.1 ���ο� ������ȣ ����
		param_v_pro_no = property_assess_data.makeVaildPropertyNum(ComUtil.getTimeNow());
	}	
	
	//4. Fitting�� ���� Source Data �̵�
	boolean mov_value = false;
	mov_value = property_assess_data.makeFittingTemp(param_v_pro_no, user_id, pd_info);
	System.out.println("Fitting Temp Move : "+mov_value);
	
	if(property_assess_data.checkFittingState(user_id, block_state) == true){ //4. �����ID�� �������� ���� �ִٸ�..Update
		//4.1. ���°� Update
		boolean upd_value = false;
		if(mov_value == true){
			upd_value = property_assess_data.updateFittingState(param_v_pro_no, user_id, "-", block_state);
			System.out.println("Fitting Temp Update : "+upd_value);
		}
	}else{ //5. ����� ID�� �������� ���� ���ٸ�.. Insert
		//5.1. ���°� Insert
		boolean ins_value = false;
		if(mov_value == true){
			ins_value = property_assess_data.insertFittingState(param_v_pro_no, user_id, "-", block_state);
			System.out.println("Fitting Temp Insert : "+ins_value);
		}
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
<OBJECT width = "0" height = "0"
ID="ZTransferX" CLASSID="CLSID:C7C7225A-9476-47AC-B0B0-FF3B79D55E67" 
codebase="http://dcpp.nfri.re.kr/oz/viewer/ZTransferX.cab#version=2,2,0,8">            <!-- �� ��ġ�ϴ� ZTransferX��ġ,���� ���� -->
<PARAM NAME="download.Server" VALUE="http://dcpp.nfri.re.kr/oz/viewer/">               <!-- ��� ��ġ ���� (��Ʈ����) -->
<PARAM NAME="download.Port" VALUE="80">                                         <!-- ��� ��ġ�� ��Ʈ ���� -->
<PARAM NAME="download.Instruction" VALUE="ozviewer.idf">                       <!-- ��� ���� ���� ���� -->
<PARAM NAME="install.Base" VALUE="<PROGRAMS>/Forcs">
<PARAM NAME="install.Namespace" VALUE="plasma">                          <!-- ���ÿ� ��ġ�� ����̸� -->
</OBJECT>
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

function checkInfo(){
	var form2 = document.form1;
	var count = form2.dt_count;
	if(dt_count == 0){
		alert("�˻� ���ǿ� �´� ����� �����ϴ�.");
		history.back();
	}
}

function selectBeforeBlock(nowBlock, nowPage)//���� ��� ����
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "pr_search_property_result.jsp";
    ///form2.encoding = "application/x-www-form-urlencoded";    
	form2.submit();	
}

function selectBlock(nowBlock, nowPage)//��� ����
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "pr_search_property_result.jsp";
    ///form2.encoding = "application/x-www-form-urlencoded";    
	form2.submit();	
}

function selectAfterBlock(nowBlock, nowPage)//���� ��� ����
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "pr_search_property_result.jsp";
    ///form2.encoding = "application/x-www-form-urlencoded";    
	form2.submit();	
}

//function viewGraphInfo(pr_no){ //�׷��� ����
//	if(confirmMsg('0') == true){
//		window.open("pr_view_graph.jsp?pr_no="+pr_no,"graph_info","width=650,height=750,toolbar=no");
//		return;
//	}
//}

function backInfo()//�ڷ� ����
{
	if(confirmMsg('0') == true){
	    var form2 = document.form1;
	    form2.target = "_self";
	    form2.action = "pr_assess_search_inf_grd_select_list.jsp";
		form2.submit();	
	}
}

function modifyInfo(){ //����
	if(confirmMsg('1') == true){
		var form2 = document.form1;
	    form2.target = "_self";
	    form2.action = "pr_assess_fitting_inf_grd_data.jsp";
		form2.submit();	
	}
}

function decideInfo(){ //����
	if(confirmMsg('2') == true){
		var form2 = document.form1;
		    form2.target = "_self";
		    form2.action = "pr_assess_final_inf_grd_data.jsp";
			form2.submit();	
	}
}

function exitInfo(){ //����
	if(confirmMsg('3') == true){
		var form2 = document.form1;
	    form2.target = "_self";
	    form2.action = "pr_assess_search_inf_grd_data.jsp";
		form2.submit();	
	}
}

function confirmMsg(number){
	var num = number;
	switch(num) {
		case '0': //�ڷ� �̵�
			var result = confirm("���� �������� �̵��Ͻðڽ��ϱ�?");
			return result;
			break;
		case '1': //����
			var result = confirm("�Է°��� �����Ͻðڽ��ϱ�?");
			return result;
			break;
		case '2': //����
			var result = confirm("�� ���·� �����Ͻðڽ��ϱ�?");
			return result;
			break;
		case '3': //����
			var result = confirm("�۾��� �����Ͻðڽ��ϱ�?");
			return result;
			break;
		default :		
			break;
	}		
}

function fittingInfo(seq){ //fitting �����ϱ�
	//alert(seq + "��° Fitting");	
	//var fitting_value = document.getElementsByName("fitting_max");
	//var fitting_value = document.form1.elements["a["+num+"]"].value;
	//var fitting_min = form2.fitting_min+'['+s_fit_v+']'.value;
	//var fitting_max = form2.fitting_max+'['+seq+']'.value;
	//var fitting_value = document.form1.elements["a["+num+"]"].value;
		
	var fitting_seq = seq;
	var form2 = document.form1;
	var fitting_min = form2.elements["fitting_min["+fitting_seq+"]"].value;
	var fitting_max = form2.elements["fitting_max["+fitting_seq+"]"].value;
	var user_id = form2.user_id.value;
	
	if(fitting_min.length < 0){
		alert(fitting_seq + "��° Fitting ������ Min ���� �Է��ϼ���.");
		return;
	}
	
	if(fitting_max.length < 0){
		alert(fitting_seq + "��° Fitting ������ Max ���� �Է��ϼ���.");
		return;
	}

	if(confirm( fitting_seq + '��° Fitting �� �Ͻðڽ��ϱ�?') == true){
		window.open("pr_assess_fitting_processing.jsp.jsp?user_id="+user_id,"fitting_processing","width=700,height=750,toolbar=no,location=no,status=no");
		return;
	}
}

function checkSessionUserID(){ //�α��� ���̵� üũ
	var form2 = document.form1;
    if(form2.user_id.value == null){    
	    form2.target = "_self";
	    form2.action = "c_login.jsp?flag=AU";
		form2.submit();
	}
}
function refreshInfo(){ //�ٽ� �׸���
	var form2 = document.form1;
	var check = form2.check_pr_no;
	var count = 0;
	
	if(check_XY_range() == false){
		form2.target = "_self";
    	form2.action = "pr_assess_search_inf_grd_select_list.jsp";	    
		form2.submit();
	}
	//for(var i = 0; i < check.length; i++){
	//	if(check[i].checked){
			//alert(check[i].value);
	//		count ++;
	//	}
	//}
	//if(count == 0){		
	//	alert("üũ�ڽ��� �����ϼ���.");
	//	return;
	//}else{
	    
	//}
}

function check_XY_range(){ // X, Y �� �ִ�, �ּҰ� üũ
	var form2 = document.form1;
	
	if(form2.graph_xrange_value.checked)
	{
		if(form2.graph_xrange_min_value.length <= 0){
			alert("X�� �ּҰ��� �����ϼ���.");
			return true;
		}
		if(form2.graph_xrange_max_value.length <= 0){
			alert("X�� �ִ밪�� �����ϼ���.");
			return true;
		}
	}	
	else if(form2.graph_yrange_value.checked)
	{
		if(form2.graph_yrange_min_value.length <= 0){
			alert("Y�� �ּҰ��� �����ϼ���.");
			return true;
		}
		if(form2.graph_yrange_max_value.length <= 0){
			alert("Y�� �ִ밪�� �����ϼ���.");
			return true;
		}
	}
	return false;
}

function xCheckBoxSelect(){ //X�� �ִ�, �ּ� üũ�ڽ� üũ
	
	var form2 = document.form1;
	
	if(form2.graph_xrange_value.checked)
	{
		form2.graph_xrange_min_value.readOnly=false;
		form2.graph_xrange_max_value.readOnly=false;
	}	
	else
	{
		form2.graph_xrange_min_value.readOnly=true;
		form2.graph_xrange_max_value.readOnly=true;
	} 
}

function yCheckBoxSelect(){ //Y�� �ִ�, �ּ� üũ�ڽ� üũ
	
	var form2 = document.form1;
	
	if(form2.graph_yrange_value.checked)
	{
		form2.graph_yrange_min_value.readOnly=false;
		form2.graph_yrange_max_value.readOnly=false;
	}	
	else
	{
		form2.graph_yrange_min_value.readOnly=true;
		form2.graph_yrange_max_value.readOnly=true;
	} 
}

var pos = 2;
function addFittingInfo(tab_name){
	if(pos < 11){
		var tbody = document.getElementById(tab_name).getElementsByTagName("TBODY")[0];
	    var row = document.createElement("TR");
	    	row.setAttribute("id","tr_"+pos);
	    var td1 = document.createElement("TD");
	     	td1.setAttribute("class","tdc");
	     	td1.appendChild (document.createElement("<input type=text name='fitting_min["+pos+"]' size=10 maxlength=32>"));
	    var td2 = document.createElement("TD");
	     	td2.setAttribute("class","tdc");
	     	td2.appendChild (document.createElement("<input type=text name='fitting_max["+pos+"]' size=10 maxlength=32>"));
	    var td3 = document.createElement("TD");
	     	td3.setAttribute("class","tdc");
	     	td3.appendChild (document.createTextNode(pos));
	    var td4 = document.createElement("TD");
			td4.setAttribute("class","tdc");
			var newDiv = document.createElement("div");
			newDiv.innerHTML = "<a href=\"javascript:fittingInfo("+pos+")\" onMouseOut=\"MM_swapImgRestore()\" onMouseOver=\"MM_swapImage('fittingImage"+pos+"','','images/img_cont/fitting_s_upper.gif',1)\"><img src=\"images/img_cont/fitting_s_normal.gif\" name=\"fittingImage"+pos+"\" width=\"60\" height=\"20\" border=\"0\" id=\"fittingImage"+pos+"\"/></a>";
			td4.appendChild(newDiv); //(document.createElement("<a href=\"javascript:fittingInfo()\" onMouseOut=\"MM_swapImgRestore()\" onMouseOver=\"MM_swapImage('fittingImage2','','images/img_cont/fitting_s_upper.gif',1)\"><img src=\"images/img_cont/fitting_s_normal.gif\" name=\"fittingImage2\" width=\"60\" height=\"20\" border=\"0\" id=\"fittingImage2\"/></a>"));
	    var td5 = document.createElement("TD");
	     	td5.setAttribute("class","tdc");
	     	td5.appendChild (document.createTextNode("Fitting formula and parameter"));
	    row.appendChild(td1);
	    row.appendChild(td2);
	    row.appendChild(td3);
	    row.appendChild(td4);
	    row.appendChild(td5);
	    tbody.appendChild(row);
	    pos++;
	}else{
		alert("�� �̻� Fitting ������ �߰��� �� �����ϴ�.");
	}
}
function delFittingInfo(tab_name){
	if(pos > 2){
		pos--;
		var div = document.getElementById("div_tab");
		var tr_pos = "tr_" + pos;
		var tr_v = document.getElementById(tr_pos);//+pos);
		tr_v.parentNode.removeChild(tr_v);
	}else{
		alert("�� �̻� Fitting ������ ������ �� �����ϴ�.");
	}	
}
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
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
<input type="hidden" name="v_pro_no"       value="<%=param_v_pro_no%>"/>
<table class="tab2">
<col width="80">
<col width="170">
<col width="80">
<col width="170">
<col width="50">
<col width="100">
<tr>
    <th class="thc">Title :</th>
    <td class="tdc2"><%=process_option%></td>
    <th class="thc">������ :</th>
    <td class="tdc2"><%=ComUtil.getAbbrString(pd_expression, 40)%></td>
    <th class="thc">No :</th>
    <td class="tdc2"><%=param_v_pro_no%></td>
</tr>
</table>
<p class="level2">�׷��� ���</p>
<span class="help">�׷��� �� ���� ���콺 �����͸� �ø��� �ش� ���� ���� ������ ǥ�õ˴ϴ�.</span>
<table class="tab2">
<col width="110">
<col width="540">
  <tr>
    <th colspan="2" class="thc">�׷���</th>
    </tr>
  <tr>
  	<td colspan="2" class="tdc">
  	<object id="ozviewer" width="645" height="500" CLASSID="CLSID:0DEF32F8-170F-46f8-B1FF-4BF7443F5F25">
            <param name="connection.servlet" value="http://dcpp.nfri.re.kr/oz/server">
            <param name="connection.reportname" value="assess_search_list_graph_data.ozr">
			<param name="viewer.usestatusbar" value="false">	<!-- �Ʒ��� ����ǥ������ ������� �ʽ��ϴ� -->
			<param name="viewer.viewmode" value="Fittocontents">  <!-- ��� ����� ����  �������� ����ϴ� -->
			<param name="viewer.bgcolor" value="ffffff">	<!-- ��� ��׶��� ������ �����մϴ� -->
			<param name="viewer.useoutborder" value="false">	<!-- �ܰ� �׵θ��� ������� �ʽ��ϴ� -->
			<param name="viewer.useinborder" value="false">	<!-- ���� �׵θ��� ������� �ʽ��ϴ� -->
			<param name="toolbar.all" value="true">	<!-- ���ٸ� ��� -->
			<param name="toolbar.about" value="true">
			<param name="toolbar.close" value="false">
			<param name="toolbar.file" value="true">
			<param name="toolbar.jpg" value="true">
			<param name="toolbar.pdf" value="true">
			<param name="toolbar.ppt" value="true">
			<param name="toolbar.tiff" value="true">
			<param name="toolbar.find" value="false">
			<param name="toolbar.help" value="false">
			<param name="toolbar.open" value="false">
			<param name="toolbar.page" value="false">
			<param name="toolbar.print" value="true">
			<param name="toolbar.save" value="false">
			<param name="toolbar.pagenavigator" value="false">
			<param name="toolbar.pageselection" value="false">
			<param name="toolbar.viewmode" value="true">
			<param name="toolbar.option" value="false">
            <param name="viewer.isFrame" value="false">
			<param name="viewer.Namespace" value="plasma\ozviewer">
			<param name="viewer.configmode" value="html">
            <param name="odi.odinames" value="db_plasma_info">
			<param name="odi.db_plasma_info.pcount" value="7">
			<param name="odi.db_plasma_info.args1" value="PM_X_TITLE=<%=X_Title%>">
			<param name="odi.db_plasma_info.args2" value="PM_Y_TITLE=<%=Y_Title%>">
			<param name="odi.db_plasma_info.args3" value="PM_WHERE_CONDITION=<%=whereCondtion%>">
			<param name="odi.db_plasma_info.args4" value="PM_X_MIN_VALUE=<%=param_x_min_v%>">
			<param name="odi.db_plasma_info.args5" value="PM_X_MAX_VALUE=<%=param_x_max_v%>">
			<param name="odi.db_plasma_info.args6" value="PM_Y_MIN_VALUE=<%=param_y_min_v%>">
			<param name="odi.db_plasma_info.args7" value="PM_Y_MAX_VALUE=<%=param_y_max_v%>">
        </object>
   	</td>
    </tr>
</table>
<p class="level2">Fitting ���� ����</p>
<span class="help">�⺻ �������� 1���̸� �ִ� �߰� ������ Fitting ���� ���� 10���Դϴ�.</span>
<div class="bbtn_l">
<a href="javascript:addFittingInfo('tab2')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('addFittingImage','','images/img_cont/add_fitting_s_upper.gif',1)"><img src="images/img_cont/add_fitting_s_normal.gif" name="addFittingImage" width="120" height="20" border="0" id="addFittingImage" /></a>
<a href="javascript:delFittingInfo('tab2')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('delFittingImage','','images/img_cont/del_fitting_s_upper.gif',1)"><img src="images/img_cont/del_fitting_s_normal.gif" name="delFittingImage" width="120" height="20" border="0" id="delFittingImage" /></a>
</div>
<div id="div_tab">
<table class="tab2" id="tab2">
<col width="100">
<col width="100">
<col width="100">
<col width="50">
<col width="100">
<col width="200">
<tr>
	<th class="thc">
	
   </th >
   <th class="thc">
    	 MIN
   </th >
   <th class="thc">
     	MAX
   </th>
   <th class="thc">
     	����
   </th><th class="thc">
   		Calculation
   </th>
   <th class="thc">
     	Result
   </th>
 </tr>
  	<tr>
 		<td class="tdc4">
 			����������
	    </td >
	    <td class="tdc">
	    <%//������ ���� MIN%>
	     	<%=param_x_min_v%>
	    </td >
	    <td class="tdc">
	    <%//������ ���� MAX%>			     	
	     	<%=param_x_max_v%>
	    </td >
	    <td class="tdc">
	    <%//����%>
	     	
	    </td >
   		<td class="tdc">
   		<%//calculation%>	     			
   			<a href="javascript:selectInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('selectImage','','images/img_cont/select_s_upper.gif',1)"><img src="images/img_cont/select_s_normal.gif" name="selectImage" width="60" height="20" border="0" id="selectImage" /></a>
   		</td >
   		<td class="tdc">
   		<%//result%>
   			Numerical data
	    </td >
  	</tr>
  	<tr id="tr_1">  		
 		<td class="tdc4" rowspan = "10">
 			Fitting ����
	    </td >
	    <td class="tdc">
	    <%//Fitting MIN%>
	     	<input type="text" name="fitting_min[1]" size="10" value="">
	    </td >
	    <td class="tdc">
	    <%//Fitting MAX%>			     	
	     	<input type="text" name="fitting_max[1]" size="10" value="">
	    </td >
	    <td class="tdc">
	    <%//����%>
	     	1
	    </td >
   		<td class="tdc">
   		<%//calculation%>	     			
   			<a href="javascript:fittingInfo(1)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('fittingImage1','','images/img_cont/fitting_s_upper.gif',1)"><img src="images/img_cont/fitting_s_normal.gif" name="fittingImage1" width="60" height="20" border="0" id="fittingImage1" /></a>
   		</td >
   		<td class="tdc">
   		<%//result%>
   			Fitting formula and parameter
	    </td >
  	</tr>
</table>
</div>
<div class="bbtn_c">
<a href="javascript:backInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('backImage','','images/img_cont/back_upper.gif',1)"><img src="images/img_cont/back_normal.gif" name="backImage" width="80" height="24" border="0" id="backImage" /></a>
<a href="javascript:modifyInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('assessImage','','images/img_cont/modify_upper.gif',1)"><img src="images/img_cont/modify_normal.gif" name="modifyImage" width="80" height="24" border="0" id="modifyImage" /></a>
<a href="javascript:decideInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('datagenImage','','images/img_cont/decide_upper.gif',1)"><img src="images/img_cont/decide_normal.gif" name="decideImage" width="80" height="24" border="0" id="decideImage" /></a>
<a href="javascript:exitInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('datagenImage','','images/img_cont/exit_upper.gif',1)"><img src="images/img_cont/exit_normal.gif" name="exitImage" width="80" height="24" border="0" id="exitImage" /></a>
</div>
</form>
</body>
</html>
<%}%>