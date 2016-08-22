<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.com.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<jsp:useBean id="graph_info" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<%
	/**
	* �׷��� �ʱ� ������ �Է��ϴ� JSP ������(view)
	* by Junside(J.H Park)
	*/

	String pr_no = request.getParameter("pr_no");
	String g_index = request.getParameter("g_index");
	
	String x_unit = "";
	String x_cal = "";
	String y_unit = "";
	String y_cal = "";
	String y_comm = "";
	
	int countflag = graph_info.selectGraphBasicInfoCount(pr_no);
	if(countflag > 0){
		Graph_Basic_Info basic_info = (Graph_Basic_Info)graph_info.selectGraphBasicInfo(pr_no);	
		x_unit = basic_info.getPL_BGI_X_AX_UNIT();
		x_cal = basic_info.getPL_BGI_X_AX_CAL();
		y_unit = basic_info.getPL_BGI_Y_AX_UNIT();
		y_cal = basic_info.getPL_BGI_Y_AX_CAL();
		y_comm = basic_info.getPL_BGI_Y_AX_COMM();
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

function nextInfo(){ //���� �̵�
	var form2 = document.form1;	
	if(saveInfoCheck() == false){
		if(confirmMsg('1') == true){
			form2.action = "pr_insert_graph_info_check.jsp";
			form2.submit();
		}else{
			return;
		}		
	}else{
		return;
	}
}

function exitInfo(){ //����
	var form2 = document.form1;	
	if(confirmMsg('2') == true){
		form2.action = "pr_common_list.jsp";
		form2.submit();
	}else{
		return;
	}	
}

function confirmMsg(number){
	var num = number;
	switch(num) {
		case '1': //�����ܰ�� �̵�
			var result = confirm("���� �ܰ�� �̵��Ͻðڽ��ϱ�?\n���� �ܰ� ����� �ڵ� ����˴ϴ�.");
			return result;
			break;
		case '2': //�۾�����
			var result = confirm("�۾��� �����Ͻðڽ��ϱ�?\n�Է��Ͻ� ������ ������� �ʽ��ϴ�.");
			return result;
			break;
		default :		
			break;
	}		
}

function saveInfoCheck(){ //�Է� ������ ��ȿ������
	var form2 = document.form1;	
	var errorV = new Boolean(false); //������ ��Ÿ���� ������ �⺻���� false�� ����	
	
	//1. X���� üũ
	if(form2.xax_unit.value == '����'){
		errorV = true;
		alert("X������ �Է��ϼ���!");
		form2.xax_unit.focus();
		return;
	}
	
	//1. Y���� üũ
	if(form2.xay_unit.value == '����'){
		errorV = true;
		alert("Y������ �Է��ϼ���!");
		form2.xay_unit.focus();
		return;
	}
	
	return errorV;
}

</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<jsp:useBean id="option_code" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
<input type="hidden" name="g_index" value='<%=g_index%>'>
<p class="level2">���� �׷��� ���� ���� �Է�</p>
<table class="tab2">
<col width="100">
<col width="550">
  <tr>
    <th class="thc">����������ȣ</th>
    <td class="tdl">
    <%
    	if(ComUtil.isNull(pr_no)){
    %>
    	<input type="text" name="pr_no" value="�ڵ����� ���õ˴ϴ�." size="25" readonly> 
    <%
    	}else{
    %>
    	<input type="text" name="pr_no" value="<%=pr_no%>" size="25" readonly> 
    <%
    	}
    %>
    </td>
  </tr>
</table>
<span class="help">
	Xȯ��� Yȯ�� �⺻ ���� '1 = 1E0'�̸�, �������·� �Է��ϼž� �մϴ�.
</span>
<table class="tab2">
<col width="100">
<col width="300">
<col width="120">
<col width="130">
  <tr>
  	<th class="thc"><font color = red>X ����</font></th>
	<td class="tdl">
		<select name="xax_unit" onChange="">
			<%
				Vector xu_code = option_code.getXUOption();
				int xu_count = xu_code.size();
				for (int i = 0; i < xu_count; i++) {
					Code_Info code = (Code_Info) xu_code.elementAt(i);
					String code_id = code.getPL_CI_ID();
					String code_name = code.getPL_CI_NAME();
					String code_exp = code.getPL_CI_ID_EXP();
					if(ComUtil.isNull(x_unit)){
						%>
						<option value='<%=code_id%>'<%=code_id.equals("XU_00")?"selected":""%>><%=code_exp%></option>
						<%					
					}
					else{
						%>
						<option value='<%=code_id%>'<%=code_id.equals(x_unit)?"selected":""%>><%=code_exp%></option>
						<%
					}
				}
			%>
		</select>
   	</td>
   	<th class="thc">X ȯ��(����ǥ��)</th>
     <td class="tdl">
		<%
	    	if(ComVar.STRING_EMPTY.equalsIgnoreCase(x_cal)){
	    %>
	    	<input type="text" name="xax_cal" value="1E0" size="14"> 
	    <%
	    	}else{
	    %>
	    	<input type="text" name="xax_cal" value="<%=x_cal %>" size="14">
	    <%
	    	}
	    %> 
	 </td>
  </tr>
  <tr>
  	<th class="thc"><font color = red>Y ����</font></th>
    <td class="tdl">
    	<select name="xay_unit" onChange="">
			<%
				Vector yu_code = option_code.getYUOption();
				int yu_count = yu_code.size();
				for (int i = 0; i < yu_count; i++) {
					Code_Info code = (Code_Info) yu_code.elementAt(i);
					String code_id = code.getPL_CI_ID();
					String code_name = code.getPL_CI_NAME();
					String code_exp = code.getPL_CI_ID_EXP();					
					if(ComUtil.isNull(y_unit)){
			%>
						<option value='<%=code_id%>'<%=code_id.equals("YU_00")?"selected":""%>><%=code_exp%></option>
			<%					
					}
					else{
			%>
						<option value='<%=code_id%>'<%=code_id.equals(y_unit)?"selected":""%>><%=code_exp%></option>
			<%
					}
				}
			%>
		</select>
	</td>		
   	<th class="thc">Y ȯ��(����ǥ��)</th>
    <td class="tdl">  
	    <%
	    	if(ComVar.STRING_EMPTY.equalsIgnoreCase(y_cal)){
	    %>
	    	<input type="text" name="xay_cal" value="1E0" size="14"> 
	    <%
	    	}else{
	    %>
	    	<input type="text" name="xay_cal" value="<%=y_cal %>" size="14">
	    <%
	    	}
	    %> 			
   	</td>
  </tr>
  <tr>
  	<th class="thc">Y �������</th>   
  	<td colspan="3"> 
  		<%
	    	if(y_comm.equalsIgnoreCase("-") || ComUtil.isNull(y_comm)){
	    %>
	    	<textarea name="xay_com" rows="5" cols="74"></textarea>
	    <%
	    	}else{
	    %>
	    	<textarea name="xay_com" rows="5" cols="74"><%=y_comm%></textarea>
	    <%
	    	}
	    %>
  	  </td>
   	</tr>
</table>
<div class="bbtn_r">
<a href="javascript:nextInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('nextImage','','images/img_cont/next_upper.gif',1)"><img src="images/img_cont/next_normal.gif" name="nextImage" width="80" height="24" border="0" id="nextImage" /></a>
<a href="javascript:exitInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('exitImage','','images/img_cont/exit_upper.gif',1)"><img src="images/img_cont/exit_normal.gif" name="exitImage" width="80" height="24" border="0" id="exitImage" /></a>
</div>
</form>
</body>
</html>