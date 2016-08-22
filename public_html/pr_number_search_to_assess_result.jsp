<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.properties.db.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>    
<jsp:useBean id="property_info" class="nfri.dcpp.properties.business.Ctr_Property_Info_Process" scope="page"/>
<jsp:useBean id="assess_info" class="nfri.dcpp.properties.business.Ctr_Property_Assess_Info" scope="page"/>
<%
	String pr_no = request.getParameter("pr_no");
	String user_id = (String)session.getAttribute("user_id");

	Vector pt_info = property_info.selectPropertyInfo(pr_no); 
	int pt_count = pt_info.size();

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

function viewBasicInfo(pr_no, artcl_no){ //�⺻���� ����
	if(saveMsg('1') == true){
		window.open("pr_view_property_info.jsp?pr_no="+pr_no+"&artcl_no="+artcl_no,"property_info","width=500,height=400,toolbar=no,location=no,status=no");
		return;
	}
}

function viewUnitInfo(pr_no){ //�������� ����
	if(saveMsg('2') == true){
		window.open("pr_view_graph_unit.jsp?pr_no="+pr_no,"unit_info","width=100%,height=100%,toolbar=no,location=no,status=no");
		return;
	}
}

function insertUnitInfo(pr_no){ //�������� �Է�
	if(saveMsg('3') == true){
		var form2 = document.form1;
		form2.action = "pr_insert_graph_info.jsp?pr_no="+pr_no;
		form2.submit();
	}
}

function viewDataInfo(pr_no){ //��ġ���� ����
	if(saveMsg('4') == true){
		window.open("pr_view_graph_data.jsp?pr_no="+pr_no,"property_info","width=100%,height=100%,toolbar=no,location=no,status=no");
		return;
	}
}

function insertDataInfo(pr_no){ //��ġ���� �Է�
	if(saveMsg('5') == true){
		var form2 = document.form1;
		form2.action = "pr_insert_graph_data.jsp?pr_no="+pr_no;
		form2.submit();
	}
}

function viewGraphInfo(pr_no){ //�׷��� ����
	if(saveMsg('6') == true){
		window.open("pr_view_graph.jsp?pr_no="+pr_no,"graph_view","width=100%,height=100%,toolbar=no,location=no,status=no");
		return;
		//var form2 = document.form1;
		//form2.action = "pr_draw_graph_data.jsp?pr_no="+pr_no;
		//form2.submit();
	}
}

function viewCompleteGraphInfo(pr_no){ //�Ϸ�׷��� ����
	if(saveMsg('6') == true){
		window.open("pr_view_graph.jsp?pr_no="+pr_no,"complete_graph_view","width=700,height=100%,toolbar=no,location=no,status=no");
		//window.open("pr_draw_graph_data_complete.jsp?pr_no="+pr_no,"complete_graph_view","width=650,height=600,toolbar=no");
		return;
		//var form2 = document.form1;
		//form2.action = "pr_draw_graph_data_complete.jsp?pr_no="+pr_no;
		//form2.submit();
	}
}
function viewAssessInfo(pr_no){ //������ ����
	checkSessionUserID();
	if(saveMsg('7') == true){
		window.open("pr_view_property_assess_info.jsp?pr_no="+pr_no,"property_info","width=700,height=800,toolbar=no,location=no,status=no,scrollbars=yes");
		return;
	}
}

function insertAssessInfo(pr_no){ //�� ���� �Է�
	checkSessionUserID();
	if(saveMsg('8') == true){
		var form2 = document.form1;
		//window.open("pr_insert_property_assess.jsp?pr_no="+pr_no,"property_info","width=700,height=650,toolbar=no");
		form2.target = "_self";
		form2.action = "pr_insert_property_assess.jsp?pr_no="+pr_no;
		form2.submit();
	}
}
function checkSessionUserID(){
	var form2 = document.form1;
	var member_id = "<%=(String)session.getAttribute("user_id")%>";
    if(member_id == null){    
	    form2.target = "_self";
	    form2.action = "c_login.jsp?flag=AU";
		form2.submit();
	}
}
function saveMsg(number){
	var num = number;
	switch(num) {
		case '0': //���ο� �������� �Է��ϱ�
			var result = confirm("���ο� ���������� �Է��Ͻðڽ��ϱ�?");
			return result;
			break;
		case '1': //�⺻���� ����
			var result = confirm("���� �⺻ ������ Ȯ���Ͻðڽ��ϱ�?");
			return result;
			break;
		case '2': //�������� ����
			var result = confirm("���� �׷��� ���� ������ Ȯ���Ͻðڽ��ϱ�?");
			return result;
			break;
		case '3': //�������� �Է�
			var result = confirm("���� �׷��� ���� ������ �Է��Ͻðڽ��ϱ�?");
			return result;
			break;
		case '4': //��ġ���� ����
			var result = confirm("���� �׷��� ��ġ ������ Ȯ���Ͻðڽ��ϱ�?");
			return result;
			break;
		case '5': //��ġ���� �Է�
			var result = confirm("���� �׷��� ��ġ ������ �Է��Ͻðڽ��ϱ�?");
			return result;
			break;
		case '6': //�׷������� ����
			var result = confirm("�׷��� ������ Ȯ���Ͻðڽ��ϱ�?");
			return result;
			break;
		case '7': //������ ����
			var result = confirm("���� �� ������ Ȯ���Ͻðڽ��ϱ�?");
			return result;
			break;
		case '8': //������ �Է�
			var result = confirm("���� �� ������ �Է��Ͻðڽ��ϱ�?");
			return result;
			break;
		default :		
			break;
	}		
}
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<input type="hidden" name="pr_no" value="<%=pr_no%>"/>
<input type="hidden" name="user_id" value="<%=user_id%>"/>

<table class="tab2">
	<col width="50">
	<col width="100">
	<col width="80">
	<col width="95">
	<col width="95">
	<col width="60">
	<col width="95">
	<col width="95">
	  <tr>
	    <th class="thc">
	     	 No
	    </th >
	    <th class="thc">
	     	����������ȣ
	    </th>
	    <th class="thc">
	      	�⺻����
	    </th>
	    <th class="thc">
	      	��������
	    </th>
	    <th class="thc">
	      	��ġ����
	    </th>
	    <th class="thc">
	      	�׷���
	    </th>
	    <th class="thc">
	    	�򰡻���
	    </th>
	    <th class="thc">
	      	������
	    </th>
	  </tr>
	  <%
	  	Properties_Basic_List_Info prot = (Properties_Basic_List_Info) pt_info.elementAt(0);
	  	
	  	String pro_no = prot.getPL_BI_DATA_NUM();
	  	
	  	String artcl_no = prot.getPL_RAI_ARTCL_NUM();
	  	boolean unitdata = prot.isPL_GRAPH_UNIT_DATA();
	  	boolean data = prot.isPL_GRAPH_DATA();
	  	String flag = prot.getPL_BI_INSERT_FLAG();

	  %>
	  		<tr>
			    <td class="tdc">
			     	1
			    </td >
			    <td class="tdc">
			     	 <%=pro_no%>
			     	 <input type="hidden" name="pr_no" value='<%=pro_no%>'>
			    </td >
			    
			    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
			     	<%//�⺻����%>
			     	<a href="javascript:void(0);"  onClick="viewBasicInfo('<%=pro_no%>','<%=artcl_no%>'); return false;"> ���� </a>
			    </td >
			    <%
			    	//�� �Ϸ� ������ ���
			     	if(!flag.equalsIgnoreCase("C")){
			     		%>
			     		<td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
					     	<%
					     	//��������
					     	 if(unitdata == true){
						     	%>
						     	<a href="javascript:void(0);"  onClick="viewUnitInfo('<%=pro_no%>'); return false;"> ���� </a>
						     	<a href="javascript:void(0);"  onClick="insertUnitInfo('<%=pro_no%>'); return false;"> ���� </a>
						     	<%			     		 
					     	 }else{
					     		%>
						     	<a href="javascript:void(0);"  onClick="insertUnitInfo('<%=pro_no%>'); return false;"> �Է� </a>
						     	<%
					     	 }
					     	%>
					    </td >
					    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
					     	<%
					     	//���� ������ ���� ��츸
					     	if(unitdata == true){
						     	//��ġ����
						     	 if(data == true){
							     	%>
							     	<a href="javascript:void(0);"  onClick="viewDataInfo('<%=pro_no%>'); return false;"> ���� </a>
							     	<a href="javascript:void(0);"  onClick="insertDataInfo('<%=pro_no%>'); return false;"> ���� </a>
							     	<%			     		 
						     	 }else{
						     		%>
							     	<a href="javascript:void(0);"  onClick="insertDataInfo('<%=pro_no%>'); return false;"> �Է� </a>
							     	<%
						     	 }
					     	}
					     	%>
					    </td >
					    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
					     	<%
					     	//�׷��� ����
					     	 if(data == true){
						     	%>
						     	<a href="javascript:void(0);"  onClick="viewGraphInfo('<%=pro_no%>'); return false;"> ���� </a>
						     	<%
					     	 }
					     	%>
					    </td >
					    <td class="tdc">
					    -
					    </td>
					    <td class="tdc">
					    -
					    </td>
			     		<%			     		
			     	}else{ //�Ϸ� ���� �ϰ��
			     		Properties_Assess_List_Info proa = (Properties_Assess_List_Info) assess_info.selectAssessBasicInfo(pro_no);
			    	  	
			    	  	String assess = proa.getPL_ASSESS_DATA();
			     		%>
			     		<td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
			     			<a href="javascript:void(0);"  onClick="viewUnitInfo('<%=pro_no%>'); return false;"> ���� </a>
					    </td >
					    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
					    	<a href="javascript:void(0);"  onClick="viewDataInfo('<%=pro_no%>'); return false;"> ���� </a>
					    </td >
					    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
					     	<a href="javascript:void(0);"  onClick="viewCompleteGraphInfo('<%=pro_no%>'); return false;"> ���� </a>
					    </td >
					    <td class="tdc">
					     	<%			     	
					     	 if(assess.equalsIgnoreCase("V")){//��ȿ�� ������
					     	%>
					     		��ȿ������
					     	<%			     		 
					     	 }else if(assess.equalsIgnoreCase("R")){//�Ⱒ
							%>
						    	�Ⱒ
						    <%			     		 
						     }else if(assess.equalsIgnoreCase("H")){//������
							%>
								������
							<%			     		 
						     }else if(assess.equalsIgnoreCase("N")){//�򰡴��
							%>
								���
							<%			     		 
						     }
					     	%>
					    </td >
					    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
					     	<%
					     	//������
					     	 if(assess.equalsIgnoreCase("H")){
						     	%>
						     		<a href="javascript:void(0);"  onClick="viewAssessInfo('<%=pro_no%>'); return false;"> ���� </a>
						     		<a href="javascript:void(0);"  onClick="insertAssessInfo('<%=pro_no%>'); return false;"> �� </a>
						     	<%
					     	 }else if(assess.equalsIgnoreCase("R") || assess.equalsIgnoreCase("V")){
						     	%>
						     		<a href="javascript:void(0);"  onClick="viewAssessInfo('<%=pro_no%>'); return false;"> ���� </a>
						     		<a href="javascript:void(0);"  onClick="insertAssessInfo('<%=pro_no%>'); return false;"> ���� </a>
						     	<%
					     	 }else if(assess.equalsIgnoreCase("N")){
						     	%>
						     		<a href="javascript:void(0);"  onClick="insertAssessInfo('<%=pro_no%>'); return false;"> �� </a>
						     	<%
					     	 }else{
					     		%>
						     		<a href="javascript:void(0);"  onClick="viewAssessInfo('<%=pro_no%>'); return false;"> ���� </a>
						     	<%
					     	 }
					     	%>
					    </td >
			     		<%
			     	}
			    %>
		  	</tr>
	  		<%
	  		
	  %>
</table>
</form>
</body>
</html>