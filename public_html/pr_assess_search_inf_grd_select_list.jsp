<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="ctr_data" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
<jsp:useBean id="property_assess_data" class="nfri.dcpp.properties.business.Ctr_Property_Assess_Info" scope="page"/>
<jsp:useBean id="oz_option" class="nfri.dcpp.properties.model.OZ_Final_Assess_Where_Option" scope="page"/>
<%
	/**
	* ��� ������ ���� ������ ���ǿ� ���� �˻��Ͽ� �����ִ� JSP ������(view)
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
		
	//String user_id = (String)session.getAttribute("user_id");

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
	String param_tg_ionic      = ComUtil.isNullToNullString(request.getParameter("tg_ionic")); // ǥ������ �̿�ȭ
	String param_tg_elec       = ComUtil.isNullToNullString(request.getParameter("tg_elec")); // ǥ������ ���ڹ�ġ
	String param_tg_fine       = ComUtil.isNullToNullString(request.getParameter("tg_fine")); // ǥ������ �̼�����
	String param_pd_name       = ComUtil.isNullToNullString(request.getParameter("pd_name")); // �������� 
	String param_pd_name_id    = ComUtil.isNullToNullString(request.getParameter("pd_name_id")); // �������� ID 
	String param_pd_ionic      = ComUtil.isNullToNullString(request.getParameter("pd_ionic")); // �������� �̿�ȭ
	String param_pd_elec       = ComUtil.isNullToNullString(request.getParameter("pd_elec")); // �������� ���ڹ�ġ
	String param_pd_fine       = ComUtil.isNullToNullString(request.getParameter("pd_fine")); // �������� �̼�����

	//X,Y �ּ�, �ִ밪 ����
	String param_x_min_v      = ComUtil.isNullToDashString(request.getParameter("graph_xrange_min_value")); // X�� �ּ�
	String param_x_max_v       = ComUtil.isNullToDashString(request.getParameter("graph_xrange_max_value")); // X�� �ִ�
	String param_y_min_v       = ComUtil.isNullToDashString(request.getParameter("graph_yrange_min_value")); // Y�� �ּ�
	String param_y_max_v       = ComUtil.isNullToDashString(request.getParameter("graph_yrange_max_value")); // Y�� �ִ� 

	//����Ʈ�� ������ ������������ ������
	Vector pd_info = property_assess_data.searchAssessPropertyListInfo(request);
	
	int pr_count = pd_info.size();
	
	//ǥ���� �����
	String pd_expression = property_assess_data.makeAssessExpression(request);
	
	//X�� ���� ǥ��
	String X_Title = ctr_data.getExpName(param_xax_unit);
	//Y�� ���� ǥ��
	String Y_Title = ctr_data.getExpName(param_xay_unit);	
	
	String whereCondtion = property_assess_data.getConditionInfo(request);
	
	//����Ʈ�� ���Ե� ���������� ���� �׷��� �����͸� ������	
	int dt_count = pd_info.size();//dt_count = 0;//dt_info.size();
	
	String param_v_pro_no     = ComUtil.isNullToNullString(request.getParameter("v_pro_no")); //������� ������ȣ
	
	//������ ���� ���� ���� ����
	int ps_totalRecord = 0; //��ü ���ڵ� ��
	int ps_numPerPage = 10; //�������� ���ڵ� ��
	int ps_pagePerBlock = 10; //��ϴ� ������ ��
	int ps_totalPage = 0; //��ü ������ ��
	int ps_totalBlock = 0; //��ü ��� ��
	int ps_nowPage = 0; //���� ������
	int ps_nowBlock = 0; //���� ���
	int ps_beginPerPage = 0; //�������� ���۹�ȣ

	//���� ������ ����
	if(request.getParameter("ps_nowPage") != null)
	{
		ps_nowPage = Integer.parseInt(request.getParameter("ps_nowPage"));
	}
	//���� ��� ����
	if(request.getParameter("ps_nowBlock") != null)
	{
		ps_nowBlock = Integer.parseInt(request.getParameter("ps_nowBlock"));
	}
	//��ü ���� ����
	ps_totalRecord = dt_count;//pd_info.size();
	//�������� �Խù��� ���۹�ȣ ���
	ps_beginPerPage = ps_nowPage * ps_numPerPage;
	//��ü ������ �� ���
	ps_totalPage =(int)Math.ceil((double)ps_totalRecord/ps_numPerPage);
	//��ü ��� �� ���
	ps_totalBlock =(int)Math.ceil((double)ps_totalPage/ps_pagePerBlock);
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

function viewArticleInfo(artcl_no){ //������ ����
	if(confirmMsg('1') == true){
		window.open("pr_view_article_info.jsp?artcl_no="+artcl_no,"article_info","width=700,height=650,toolbar=no,location=no,status=no");
		return;
	}
}

function viewDataInfo(pr_no){ //��ġ���� ����
	if(confirmMsg('2') == true){
		window.open("pr_view_graph_data.jsp?pr_no="+pr_no,"property_info","width=700,height=650,toolbar=no,location=no,status=no");
		return;
	}
}

function viewBasicInfo(pr_no, artcl_no){ //�⺻���� ����
	if(confirmMsg('3') == true){
		window.open("pr_view_property_info.jsp?pr_no="+pr_no+"&artcl_no="+artcl_no,"property_info","width=500,height=400,toolbar=no,location=no,status=no");
		return;
	}
}

function backInfo()//�ڷ� ����
{
	if(confirmMsg('4') == true){
	    var form2 = document.form1;
	    form2.target = "_self";
	    form2.action = "pr_assess_search_inf_grd_data.jsp";
	    ///form2.encoding = "application/x-www-form-urlencoded";    
		form2.submit();	
	}
}

function nextInfo(seach_pr_no)//���� �����ϱ�
{
	if(checkCheckBox() == true){
		if(confirmMsg('5') == true){	
			var target = document.getElementsByName('check_pr_no');
			var form2 = document.form1;
		    form2.target = "_self";		  
		    var search_pr_no = document.getElementsByName('search_pr_no'); 
		    var checkedValue = "";
		    
			var count = 0;
		    for ( var i = 0 ; i < target.length ; i++ ){
				if(target[i].checked == true){
					count++;
					checkedValue = search_pr_no[i].value;
					//alert(checkedValue);
				}	
			}			

			if(count > 1){		
			    form2.action = "pr_assess_make_inf_grd_data.jsp";
			}else{	
				var pr_count = 1;
				form2.action = "pr_assess_final_inf_grd_data.jsp?seach_pr_no="+seach_pr_no+"&pr_count="+pr_count;
			}
		    ///form2.encoding = "application/x-www-form-urlencoded";    
			form2.submit();	
		}
	}
}

function datagenInfo()//������ �����ϱ�
{
	alert("������ �����ϱ� ����� ���� �����Դϴ�.");
	/*
	if(confirmMsg('6') == true){
		if(checkCheckBox() == true){
		    var form2 = document.form1;
		    form2.target = "_self";
		    form2.action = "pr_assess_fitting_inf_grd_data.jsp";
		    ///form2.encoding = "application/x-www-form-urlencoded";    
			form2.submit();	
		}
 	}
 	*/
}

function confirmMsg(number){
	var num = number;
	switch(num) {
		case '0': //������ ����
			var result = confirm("1�� �������� Ȯ���Ͻðڽ��ϱ�?");
			return result;
			break;
		case '1': //������ ����
			var result = confirm("������ ������ Ȯ���Ͻðڽ��ϱ�?");
			return result;
			break;
		case '2': //��ġ���� ����
			var result = confirm("���� �׷��� ��ġ ������ Ȯ���Ͻðڽ��ϱ�?");
			return result;
			break;
		case '3': //�⺻���� ����
			var result = confirm("���� �⺻ ������ Ȯ���Ͻðڽ��ϱ�?");
			return result;
			break;
		case '4': //�ڷ� �̵�
			var result = confirm("���� �������� �̵��Ͻðڽ��ϱ�?");
			return result;
			break;
		case '5': //���ϱ�
			var result = confirm("���õ� �����Ϳ� ���� �򰡸� �Ͻðڽ��ϱ�?");
			return result;
			break;
		case '6': //������ �����ϱ�
			var result = confirm("Fitting �����͸� �����Ͻðڽ��ϱ�?");
			return result;
			break;
		default :		
			break;
	}		
}

function checkCheckBox(){ //�������� üũ�ڽ��� ���õǾ� �ִ��� Ȯ��
	//var form2 = document.form1;
	var target = document.getElementsByName('check_pr_no');//form2.check_pr_no;
	
	for ( var i = 0 ; i < target.length ; i++ ){
		if(target[i].checked == true){
			return true;
		}	
	}
	if(target.length > 0){
		alert("�� �� �̻��� ���� �����͸� �����ϼ���!");
	}else{
		alert("���� �����Ͱ� �����ϴ�!");
	}
	return false;
}

function toggleCheckBox(obj, target){ //üũ�ڽ� ���
    if ( !obj ) return false;
    if ( !target ) return false;
    
    if ( target.length ) {
        for ( var i = 0 ; i < target.length ; i++ ){
            //target[i].setAttribute("checked", obj.getAttribute("checked"));
            target[i].checked = obj.checked? true:false
        }
    }
    else{
        target.setAttribute("checked", obj.getAttribute("checked"));
    }
}

function viewAssessInfo(pr_no){ //������ ����
	checkSessionUserID();
	if(confirmMsg('0') == true){
		window.open("pr_view_property_assess_info.jsp?pr_no="+pr_no,"property_info","width=680,height=800,toolbar=no,scrollbars=yes");
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
	//var count = 0;
	
	//if(check_XY_range() == false){
		form2.target = "_self";
    	form2.action = "pr_assess_search_inf_grd_select_list.jsp";	    
		form2.submit();
	//}
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
</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
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
<input type="hidden" name="pr_count"       value="<%=pr_count%>"/>
<input type="hidden" name="nowPage" value="<%=ps_nowPage%>"/>
<input type="hidden" name="nowBlock" value="<%=ps_nowBlock%>"/>
<table class="tab2">
<col width="50">
<col width="180">
<col width="60">
<col width="200">
<col width="50">
<col width="100">
<tr>
    <th class="thc">Title :</th>
    <td class="tdc2"><%=process_option%></td>
    <th class="thc">������ :</th>
    <td class="tdc2"><%=pd_expression%></td>
    <th class="thc">No :</th>
    <td class="tdc2">�ڵ� ����</td>
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
            <param name="connection.reportname" value="ozr_pr_assess_search_inf_grd_select_list.ozr">
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
            <param name="odi.odinames" value="odi_pr_assess_search_inf_grd_select_list">
			<param name="odi.odi_pr_assess_search_inf_grd_select_list.pcount" value="7">
			<param name="odi.odi_pr_assess_search_inf_grd_select_list.args1" value="PM_X_TITLE=<%=X_Title%>">
			<param name="odi.odi_pr_assess_search_inf_grd_select_list.args2" value="PM_Y_TITLE=<%=Y_Title%>">
			<param name="odi.odi_pr_assess_search_inf_grd_select_list.args3" value="PM_WHERE_CONDITION=<%=whereCondtion%>">
        </object>
   	</td>
    </tr>
</table>
<p class="level2">�׷��� ���� �缳��</p>
<span class="help">�⺻ �������� ��ü �����Ϳ� ���� ������ ǥ�õ˴ϴ�.</span><br>
<span class="help">X, Y���� ǥ�� ������ �����Ͻ÷��� MAX, MIN�� �����ϼ���.</span>
<table class="tab2">
<col width="80">
<col width="100">
<col width="250">
<tr>
   <td class = "tdc" rowspan = "2"><font color=green>X</font></td>
   <td class = "tdc">MIN</td>
   <td>
	    <%if(param_x_min_v.equalsIgnoreCase("-")){ %>
	   	<input type="text" name= "graph_xrange_min_value" value="" size="40" />
	   	<%}else{%>
	   	<input type="text" name= "graph_xrange_min_value" value="<%=param_x_min_v%>" size="40" />
	   	<%} %>
   </td>
</tr>
<tr>
	<td class = "tdc">MAX</td>
    <td>
	    <%if(param_x_max_v.equalsIgnoreCase("-")){ %>
	   	<input type="text" name= "graph_xrange_max_value" value="" size="40" />
	   	<%}else{%>
	   	<input type="text" name= "graph_xrange_max_value" value="<%=param_x_max_v%>" size="40" />
	   	<%} %>    
    </td>
</tr>
<tr>
   <td class = "tdc" rowspan = "2"><font color=green>Y</font></td>
   <td class = "tdc">MIN</td>
   <td>
	   	<%if(param_y_min_v.equalsIgnoreCase("-")){ %>
	   	<input type="text" name= "graph_yrange_min_value" value="" size="40" />
	   	<%}else{%>
	   	<input type="text" name= "graph_yrange_min_value" value="<%=param_y_min_v%>" size="40" />
	   	<%} %>
   </td>
</tr>
<tr>
    <td class = "tdc">MAX</td>
    <td>
	    <%if(param_y_max_v.equalsIgnoreCase("-")){ %>
	   	<input type="text" name= "graph_yrange_max_value" value="" size="40" />
	   	<%}else{%>
	   	<input type="text" name= "graph_yrange_max_value" value="<%=param_y_max_v%>" size="40" />
	   	<%} %>
    </td>
</tr>
</table>
<div class="bbtn_r">
<a href="javascript:refreshInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('refreshImage','','images/img_cont/refresh_upper.gif',1)"><img src="images/img_cont/refresh_normal.gif" name="refreshImage" width="150" height="24" border="0" id="refreshImage" /></a>
</div>
<p class="level2">���� �׷��� ������</p>
<span class="count">
������ : <%=ps_totalRecord%>�� 
</span>
<table class="tab2">
<col width="50">
<col width="100">
<col width="200">
<col width="100">
<col width="100">
<col width="100">
<tr>
	<th class="thc">
	<input type="checkbox" id="" name="" value="" onclick="toggleCheckBox(this, document.getElementsByName('check_pr_no'));" />
   </th >
   <th class="thc">
    	 �⺻ ����
   </th >
   <th class="thc">
     	���ڸ�
   </th>
   <th class="thc">
     	�̷�/����
   </th><th class="thc">
   		��ġ ����
   </th>
   <th class="thc">
     	�� ����
   </th>
 </tr>
  <%
	  	//for (int i = ps_beginPerPage ; i < (ps_beginPerPage+ps_numPerPage); i++) {
	  	//if(i == ps_totalRecord || ps_totalRecord == 0)
	  	//{
	  	//	break;
	  	//}
	  	for (int i = 0 ; i < dt_count; i++) {	  	
	  	Properties_Assess_Search_List prot = (Properties_Assess_Search_List) pd_info.elementAt(i);
	  	String search_pr_no = prot.getPL_BI_DATA_NUM();	 //������ȣ 	
	  	String artcl_no = prot.getPL_RAI_ARTCL_NUM(); //������ ��ȣ
	  	String artcl_author = prot.getPL_RAI_ARTCL_AUTH_NAME(); //����������
	  	String exp_option = prot.getPL_BI_EXP_THE_REC(); // ��������
	  	String mgmt_num = prot.getPL_BI_MGMT_DATA_NUM(); //����������ǥ�ø�

	  %>
	  		<tr>
	  			<td class="tdc">
	  			<%//üũ�ڽ�%>
			     	 <input type="checkbox" name="check_pr_no" value="<%=search_pr_no%>">
			    </td >
			    <td class="tdc">
			    <%//�⺻����(��ȣ)%>
			     	<a href="javascript:void(0);"  onClick="viewBasicInfo('<%=search_pr_no%>','<%=artcl_no%>'); return false;"><%=mgmt_num%></a>
			     	<input type="hidden" name="search_pr_no"       value="<%=search_pr_no%>"/>
			    </td >
			    <td class="tdc">
			    <%//����%>			     	
			     	<a href="javascript:void(0);"  onClick="viewArticleInfo('<%=artcl_no%>'); return false;"> <%=artcl_author%> </a>
			    </td >
			    <td class="tdc">
			    <%//�̷�/����%>
			     	<%=ComUtil.getAbbrString(exp_option, 10)%>
			    </td >
	     		<td class="tdc">
	     		<%//��ġ����%>	     			
	     			<a href="javascript:void(0);"  onClick="viewDataInfo('<%=search_pr_no%>'); return false;"> ���� </a>
	     		</td >
	     		<td class="tdc">
	     		<%//������%>
	     			<a href="javascript:void(0);"  onClick="viewAssessInfo('<%=search_pr_no%>'); return false;"> ���� </a>
			    </td >
		  	</tr>
	  		<%
	  	}
	  	
	  %>
</table>
<div class="bbtn_c">
<a href="javascript:backInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('backImage','','images/img_cont/back_upper.gif',1)"><img src="images/img_cont/back_normal.gif" name="backImage" width="80" height="24" border="0" id="backImage" /></a>
<a href="javascript:nextInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('nextImage','','images/img_cont/next_upper.gif',1)"><img src="images/img_cont/next_normal.gif" name="nextImage" width="80" height="24" border="0" id="nextImage" /></a>
<!--
<a href="javascript:datagenInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('datagenImage','','images/img_cont/datagen_upper.gif',1)"><img src="images/img_cont/datagen_normal.gif" name="datagenImage" width="150" height="24" border="0" id="datagenImage" /></a>
<a href="javascript:assessInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('assessImage','','images/img_cont/assess_upper.gif',1)"><img src="images/img_cont/assess_normal.gif" name="assessImage" width="80" height="24" border="0" id="assessImage" /></a>
-->
</div>
</form>
</body>
</html>
<%}%>