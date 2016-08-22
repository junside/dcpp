<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="data_info" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<%
	/**
	* �׷��� ������ ������ �Է��ϴ� JSP ������(view)
	* by Junside(J.H Park)
	*/

	String pr_no = request.getParameter("pr_no");
	String d_index = request.getParameter("d_index");
	
	//String pr_no = request.getParameter("pr_no");//"B201000001";//
	//String dt_no = request.getParameter("dt_no");//"1";//
	//String d_index = request.getParameter("d_index");
	String dt_count = request.getParameter("dt_count");
	String x_cal = request.getParameter("xax_cal");
	String y_cal = request.getParameter("xay_cal");
		
	Graph_Basic_Info basic_info = (Graph_Basic_Info)data_info.selectGraphBasicInfo(pr_no);	
	String x_unit = basic_info.getPL_BGI_X_AX_UNIT();
	x_cal = basic_info.getPL_BGI_X_AX_CAL();
	//String x_cal = basic_info.getPL_BGI_X_AX_CAL();
	String y_unit = basic_info.getPL_BGI_Y_AX_UNIT();
	y_cal = basic_info.getPL_BGI_Y_AX_CAL();
	//String y_cal = basic_info.getPL_BGI_Y_AX_CAL();
	String y_comm = basic_info.getPL_BGI_Y_AX_COMM();

	
	Vector dt_info = data_info.selectGraphBasicData(pr_no);
	int i_dt_count = dt_info.size();
	
	//������ ���� ���� ���� ����
	int i_totalRecord = 0; //��ü ���ڵ� ��
	int i_numPerPage = 10; //�������� ���ڵ� ��
	int i_pagePerBlock = 10; //��ϴ� ������ ��
	int i_totalPage = 0; //��ü ������ ��
	int i_totalBlock = 0; //��ü ��� ��
	int i_nowPage = 0; //���� ������
	int i_nowBlock = 0; //���� ���
	int i_beginPerPage = 0; //�������� ���۹�ȣ

	//���� ������ ����
	if(request.getParameter("i_nowPage") != null)
	{
		i_nowPage = Integer.parseInt(request.getParameter("i_nowPage"));
	}
	//���� ��� ����
	if(request.getParameter("i_nowBlock") != null)
	{
		i_nowBlock = Integer.parseInt(request.getParameter("i_nowBlock"));
	}
	//��ü ���� ����
	i_totalRecord = dt_info.size();
	//�������� �Խù��� ���۹�ȣ ���
	i_beginPerPage = i_nowPage * i_numPerPage;
	//��ü ������ �� ���
	i_totalPage =(int)Math.ceil((double)i_totalRecord/i_numPerPage);
	//��ü ��� �� ���
	i_totalBlock =(int)Math.ceil((double)i_totalPage/i_pagePerBlock);
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

function saveInfo(){ //������ ���� ����
	if(saveInfoCheck() == false){
		if(confirmMsg('0') == true){
			var form2 = document.form1;				
			if(checkFile() == true){
				alert("���Ϸ� ������ �Է½� DB�� ���� �Էµ˴ϴ�.\n������ ������ �����Ͻñ� �ٶ��ϴ�.");
				form2.action = "pr_insert_graph_data_check.jsp?d_index=1&file=1";
				form2.encoding = "multipart/form-data";
			}else{
				form2.action = "pr_insert_graph_data_check.jsp?d_index=1&file=2";
			}
			form2.submit();
		}
	}else{
		return;
	}	
}

function nextInfo(){ //���� �̵�
	var form2 = document.form1;
	var count = form2.i_dt_count.value;	
	if(confirmMsg('1') == true){
		if(count > 0){
			if(checkFile() == true){
				alert("���Ϸ� ������ �Է½� DB�� ���� �Էµ˴ϴ�.\n������ ������ �����Ͻñ� �ٶ��ϴ�.");
				form2.action = "pr_insert_graph_data_check.jsp?d_index=2&file=1";
				form2.encoding = "multipart/form-data";			
			}else{
				form2.target = "_self";
				form2.action = "pr_draw_graph_data.jsp";
			}
			form2.submit();
		}else if(checkFile() == true){
			alert("���Ϸ� ������ �Է½� DB�� ���� �Էµ˴ϴ�.\n������ ������ �����Ͻñ� �ٶ��ϴ�.");
			form2.action = "pr_insert_graph_data_check.jsp?d_index=2&file=1";
			form2.encoding = "multipart/form-data";
			form2.submit();
		}else{
			alert("�׷��� �����ʹ� ��� 1�� �̻��� �Է��ϼž� �մϴ�.");
			return;
		}	
	}
}

function exitInfo(){ //����
	var form2 = document.form1;
	if(confirmMsg('2') == true){
		form2.target = "_self";
		form2.action = "pr_common_list.jsp";
		form2.submit();	
	}
}

function confirmMsg(number){
	var num = number;
	switch(num) {
		case '0': //���� �����ϱ�
			var result = confirm("�Է��Ͻ� ������ �����Ͻðڽ��ϱ�?");
			return result;
			break;
		case '1': //�����ܰ�� �̵�
			var result = confirm("���� �ܰ�� �̵��Ͻðڽ��ϱ�?");
			return result;
			break;
		case '2': //�۾�����
			var result = confirm("�۾��� �����Ͻðڽ��ϱ�?");
			return result;
			break;
		case '3': //�����۾�
			var result = confirm("�����͸� �����Ͻðڽ��ϱ�?");
			return result;
			break;
		default :		
			break;
	}		
}

function saveInfoCheck(){ //�Է� ������ ��ȿ������
	var form2 = document.form1;	
	var errorV = new Boolean(false); //������ ��Ÿ���� ������ �⺻���� false�� ����	
	if(form2.g_data_file.value.length < 1){
		//1. X���� üũ
		if(form2.x_val.value.length < 1){
			errorV = true;
			alert("X ���� �Է��ϼ���!");
			form2.x_val.focus();
			return;
		}
		
		//1. Y���� üũ
		if(form2.y_val.value.length < 1){
			errorV = true;
			alert("Y ���� �Է��ϼ���!");
			form2.y_val.focus();
			return;
		}
	}
	
	return errorV;
}


function checkFile(){
	var form2 = document.form1;	
	var checkV = new Boolean(false);
	if(form2.g_data_file.value.length < 1)
	{
		checkV = false;
	}else{
		checkV = true;
	}
	return checkV;
}

function typcheck(typ, inp)
{
	 var lastidx = -1;
	 lastidx = inp.lastIndexOf('.');
	 var extension = inp.substring(lastidx+1, inp.length);
	 
	 if((lastidx != -1) && (extension.toLowerCase() == typ))
	  return true;
	 return false;
}

function checkData(pr_no, dt_no){
	//alert(dt_no);
	if(confirmMsg('3') == true){
		var form2 = document.form1;
		form2.target = "_self";
		form2.action = "pr_modify_graph_data.jsp?pr_no="+pr_no+"&dt_no="+dt_no;
		form2.submit();
		//window.open("pr_modify_graph_data.jsp?pr_no="+pr_no+"&dt_no="+dt_no,"modify_info","width=650,height=350,toolbar=no");
		//return;	
	}	
}

function selectBeforeBlock(i_nowBlock, i_nowPage)//���� ��� ����
{
    var form2 = document.form1;
    form2.i_nowBlock.value = i_nowBlock;
    form2.i_nowPage.value = i_nowPage;
    form2.target = "_self";
    form2.action = "pr_insert_graph_data.jsp";
    ///form2.encoding = "application/x-www-form-urlencoded";    
	form2.submit();	
}

function selectBlock(i_nowBlock, i_nowPage)//��� ����
{
    var form2 = document.form1;
    form2.i_nowBlock.value = i_nowBlock;
    form2.i_nowPage.value = i_nowPage;
    form2.target = "_self";
    form2.action = "pr_insert_graph_data.jsp";
    ///form2.encoding = "application/x-www-form-urlencoded";    
	form2.submit();	
}

function selectAfterBlock(i_nowBlock, i_nowPage)//���� ��� ����
{
    var form2 = document.form1;
    form2.i_nowBlock.value = i_nowBlock;
    form2.i_nowPage.value = i_nowPage;
    form2.target = "_self";
    form2.action = "pr_insert_graph_data.jsp";
    ///form2.encoding = "application/x-www-form-urlencoded";    
	form2.submit();	
}

</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST" >
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
<input type="hidden" name="i_dt_count" value='<%=i_dt_count%>'>
<input type="hidden" name="d_index" value='<%=d_index%>'>
<input type="hidden" name="i_nowPage" value="<%=i_nowPage%>"/>
<input type="hidden" name="i_nowBlock" value="<%=i_nowBlock%>"/>
<input type="hidden" name="xax_cal" value="<%=x_cal%>"/>
<input type="hidden" name="xay_cal" value="<%=y_cal%>"/>

<p class="level2">���� �׷��� ������ �Է�</p>
<table class="tab2">
<col width="120">
<col width="530">

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
	X���� Y�� �Ǽ����·� �Է��ϼ���.<br>
</span>
<span class="help">
	������ ÷���ϰ� �����ϸ� ȭ�鿡 �Է��Ͻ� �����ʹ� ���� �Էµ��� �ʰ� ÷������ ���븸 ����˴ϴ�.
</span>
<table class="tab2">
<col width="120">
<col width="205">
<col width="120">
<col width="205">
  <tr>
  	<th class="thr"><font color = red>X ��</font></th>
    <td class="tdl">
		<input type="text" name="x_val" value="" size="15">X <%=ComUtil.getStringByDoubleFormat(x_cal)%>
	</td>
   	<th class="thr"><font color = red>Y ��</font></th>
    <td class="tdl">
		<input type="text" name="y_val" value="" size="15">X <%=ComUtil.getStringByDoubleFormat(y_cal)%>
	</td>
  </tr>
  <tr>
	<th class="thr">X�� ������</th>   
  	<td colspan="3"> 
  	  <input type="text" name="x_err" value="" size="25">
  	</td>
  <tr>
  <tr>
  	<th class="thr">Y�� �ִ������</th>
    <td class="tdl">
		<input type="text" name="y_err_max" value="" size="25">
	</td>
   	<th class="thr">Y�� �ּҿ�����</th>
    <td class="tdl">
		<input type="text" name="y_err_min" value="" size="25">
	</td>
  </tr>
  <tr>
  	<th class="thr">������</th>
    <td class="tdl">
		<input type="text" name="ratio" value="" size="25">
	</td>
   	<th class="thr">�з�</th>
    <td class="tdl">
		<input type="text" name="press" value="" size="25">
	</td>
  </tr>
  <tr>
  	<th class="thr">���������</th>
    <td colspan="3"> 
		<input type="text" name="back_data" value="" size="25">
	</td>
  </tr>
  <tr>
  	<th class="thr">��ġ����������</th>   
  	<td colspan="3"> 
  	  <input type="file" name="g_data_file" size="25" value=""><font color = red> Excel ����(xls,csv)�� ����</font>
  	</td>
  </tr>
</table>
<div class="bbtn_r">
<a href="javascript:saveInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('saveImage','','images/img_cont/save_upper.gif',1)"><img src="images/img_cont/save_normal.gif" name="saveImage" width="80" height="24" border="0" id="saveImage" /></a>
<a href="javascript:nextInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('nextImage','','images/img_cont/next_upper.gif',1)"><img src="images/img_cont/next_normal.gif" name="nextImage" width="80" height="24" border="0" id="nextImage" /></a>
<a href="javascript:exitInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('exitImage','','images/img_cont/exit_upper.gif',1)"><img src="images/img_cont/exit_normal.gif" name="exitImage" width="80" height="24" border="0" id="exitImage" /></a>
</div>
<p class="level2">���� �׷��� ������</p>
<span class="count">
������ : <%=i_totalRecord%>�� (<font color=red> <%=i_totalRecord==0?"0":i_nowPage + 1%>/<%=i_totalPage %> Pages</font>)
</span>
<table class="tab2">
<col width="50">
<col width="70">
<col width="70">
<col width="100">
<col width="100">
<col width="100">
<col width="70">
<col width="70">
<tr>
   <th class="thc">
    	 No
   </th >
   <th class="thc">
     	X��
   </th>
   <th class="thc">
     	Y��
   </th><th class="thc">
   		X�����
   </th>
   <th class="thc">
     	Y�� �ִ����
   </th>
   <th class="thc">
     	Y�� �ּҿ���
   </th>
   <th class="thc">
     	������
   </th>
   <th class="thc">
     	�з�
   </th>
 </tr>
 <%
	  	for (int i = i_beginPerPage ; i < (i_beginPerPage+i_numPerPage); i++) {
			if(i == i_totalRecord || i_totalRecord == 0)
			{
				break;
			}
			Graph_Data_Info dti = (Graph_Data_Info) dt_info.elementAt(i);
			String pro_no = dti.getPL_BI_DATA_NUM();
			String dt_no = dti.getPL_BGD_SEQ_NUM();
			String xval = dti.getPL_BGD_X_AX_VAL();
			String yval = dti.getPL_BGD_Y_AX_VAL();
			String xerr = dti.getPL_BGD_X_ERR();
			String yerrmax = dti.getPL_BGD_Y_ERR_MAX();
			String yerrmin = dti.getPL_BGD_Y_ERR_MIN();
			String s_ratio = dti.getPL_BGD_RATIO();
			String s_press = dti.getPL_BGD_PRESS();
			String s_backdata = dti.getPL_BGD_BACKUP_DATA();
			
	  		%>
	  		
	  		<tr onmouseover="this.bgColor='#E9E9F3';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'" onClick="checkData('<%=pro_no%>','<%=dt_no%>');">
	  		
			    <td class="tdc">
			     	<%=i+1%>
			    </td >
			    <td class="tdc">
			     	<%=ComUtil.getStringByDecimalFormat(xval)%>
			    </td >
			    <td class="tdc">
			     	<%=ComUtil.getStringByDoubleFormat(yval)%>
			    </td >
			    <td class="tdc">
			     	 <%=ComUtil.getStringByDoubleFormat(xerr)%>
			    </td >
			    <td class="tdc">
			     	 <%=ComUtil.getStringByDoubleFormat(yerrmax)%>
			    </td >
			    <td class="tdc">
			     	 <%=ComUtil.getStringByDoubleFormat(yerrmin)%>
			    </td >
			    <td class="tdc">
			     	 <%=s_ratio%>
			    </td >
			    <td class="tdc">
			     	 <%=s_press%>
			    </td >
			    
		  	</tr>
	  		<%
	  	}
	  	
	  %>
</table>
<div class="bbtn_c">
<%
	if(i_totalRecord !=0)
	{
		if(i_nowBlock > 0){
			%>
			<A HREF="javascript:selectBeforeBlock('<%=i_nowBlock-1%>','<%=(i_nowBlock-1)*i_pagePerBlock %>')">���� <%=i_pagePerBlock%>��</A>
			<%
		}
		for(int i = 0; i<i_pagePerBlock; i++){
			%>
			<A HREF="javascript:selectBlock('<%=i_nowBlock%>','<%=(i_nowBlock*i_pagePerBlock) + i%>')">[<%=(i_nowBlock*i_pagePerBlock)+i+1%>]</A>
			<%
			if((i_nowBlock*i_pagePerBlock) + i + 1 == i_totalPage){
				break;
			}
		}
		if(i_totalBlock > i_nowBlock + 1){
		%>
			<A HREF="javascript:selectAfterBlock('<%=i_nowBlock+1%>','<%=(i_nowBlock+1)*i_pagePerBlock %>')">���� <%=i_pagePerBlock%>��</A>
		<%
		}
	}
%>
</div>
</form>
</body>
</html>