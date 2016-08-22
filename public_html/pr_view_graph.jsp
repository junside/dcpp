<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="data_info" class="nfri.dcpp.properties.business.Ctr_Property_Info_Process" scope="page"/>
<jsp:useBean id="graph_info" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<%
	/**
	* �׷��� �����͸� �׸��� JSP ������(view)
	* by Junside(J.H Park)
	*/
	
	String pr_no = request.getParameter("pr_no");
//String pr_no = "B201200144";
	
	Graph_Basic_Info basic_info = (Graph_Basic_Info)graph_info.selectViewGraphBasicInfo(pr_no);	
	String x_unit = basic_info.getPL_BGI_X_AX_UNIT();
	//String xax_cal = basic_info.getPL_BGI_X_AX_CAL();
	String y_unit = basic_info.getPL_BGI_Y_AX_UNIT();
	//String xay_cal = basic_info.getPL_BGI_Y_AX_CAL();
	//String y_comm = basic_info.getPL_BGI_Y_AX_COMM();
	
	//String xax_cal = request.getParameter("xax_cal");
	//String xay_cal = request.getParameter("xay_cal");
	//�⺻ ���� ��������
	Properties_Basic_Info spec_info = (Properties_Basic_Info)data_info.selectViewPropertySpecInfo(pr_no);
	
	//Y�� ���� ǥ��
	//String Y_Title = spec_info.getPL_BI_DATA_BRANCH();

	//ǥ���� ���� ��������
	String expression = spec_info.getPL_BI_EXPRESSION();//(String)data_info.selectEquationData(pr_no);
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>�ö�� ���� �׷��� ����</title>
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

function viewDataInfo(pr_no){ //��ġ���� ����
	//window.open("pr_modify_graph_data.jsp?pr_no="+pr_no,"property_info","width=650,height=650,toolbar=no");
	window.open("pr_view_graph_data.jsp?pr_no="+pr_no,"property_info","width=700,height=650,toolbar=no,location=no,status=no");
	return;
}

function viewTxtDataInfo(pr_no){ //��ġ���� ����
	//window.open("pr_modify_graph_data.jsp?pr_no="+pr_no,"property_info","width=650,height=650,toolbar=no");
	window.open("pr_view_graph_data_txt.jsp?pr_no="+pr_no);
	return;
}

function exitInfo(){ //����
	self.close();	
}

</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST" >
<div id="content">
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
<p class="level2">���� ������ �� �׷��� ǥ��</p>
<table class="tab2">
<col width="110">
<col width="540">
  <tr>
    <th class="thc">����������ȣ</th>
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
	    %>  </td>
  </tr>
  <tr>
  	<th class="thc">������</th>
    <td class="tdl">
		<%=expression%></td>
  </tr>
  <tr>
	<th class="thc">��ġ������</th>   
  	<td class="tdl"> 
  	  <a href="javascript:viewDataInfo('<%=pr_no%>')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('viewImage','','images/img_cont/view_s_upper.gif',1)"><img src="images/img_cont/view_s_normal.gif" name="viewImage" width="60" height="20" border="0" id="viewImage" /></a>
  	</td>
  </tr>
  <tr>
  	<th class="thc">��ġ������(txt)</th>
    <td class="tdl">
		<a href="javascript:viewTxtDataInfo('<%=pr_no%>')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('viewNumData','','images/img_cont/view_text_upper.gif',1)"><img src="images/img_cont/view_text_normal.gif" name="viewNumData" width="100" height="20" border="0" id="viewNumData" /></a>
	</td>
  </tr>
  <tr>
    <th colspan="2" class="thc">�׷���</th>
    </tr>
  <tr>
  	<td colspan="2" class="tdc">
   	  <object id="ozviewer" width="645" height="500" CLASSID="CLSID:0DEF32F8-170F-46f8-B1FF-4BF7443F5F25">
            <param name="connection.servlet" value="http://dcpp.nfri.re.kr/oz/server">
            <param name="connection.reportname" value="/ozr_pr_view_graph.ozr">
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
            <param name="odi.odinames" value="odi_pr_view_graph">
			<param name="odi.odi_pr_view_graph.pcount" value="3">
			<param name="odi.odi_pr_view_graph.args1" value="PM_PRO_NO=<%=pr_no%>">
			<param name="odi.odi_pr_view_graph.args2" value="PM_X_TITLE=<%=x_unit%>">
			<param name="odi.odi_pr_view_graph.args3" value="PM_Y_TITLE=<%=y_unit%>">			
        </object>
     </td>
   </tr>
</table>
<div class="bbtn_c">
<a href="javascript:exitInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('confirmImage','','images/img_cont/confirm_upper.gif',1)"><img src="images/img_cont/confirm_normal.gif" name="confirmImage" width="80" height="24" border="0" id="confirmImage" /></a>
</div>
</div>
</form>
<script type="text/javascript">
function init(){
	  var doc = document.getElementById('content');
	  if(doc.offsetHeight == 0){
	  } else {
		 //pagewidth = doc.offsetWidth;
		 pageheight = doc.offsetHeight + 90;
		 //alert(pageheight);
		 //parent.document.getElementById("content").height = pageheight+"px";
		 window.resizeTo(680,pageheight);
	  }
	}

window.onload = function(){
	  init();
}
</script>
</body>
</html>