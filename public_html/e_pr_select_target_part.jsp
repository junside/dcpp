<%@ page language="java" contentType="text/html; charset=EUC-KR"  pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="nfri.dcpp.properties.db.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<%
request.setCharacterEncoding("EUC-KR");
String id = request.getParameter("id"); //textfield ID��
String sym = request.getParameter("sym"); //���Ұ� ǥ�õǴ� ������ value ��
String db = request.getParameter("db"); //�����ͺз�
String mp = request.getParameter("mp"); //�����μ���
/* Properties_Part_Count_Info info = (Properties_Part_Count_Info)pt_info.elementAt(i);
String pt_num = info.getPL_CPBI_ELE_NUM();
String pt_count = info.getCOUNT_NUM();
String pt_sym = info.getPL_CPBI_ELE_SYMBOL(); */
%>
<%
	/**
	 * ������ �Է��ϴ� JSP ������
	 * by Junside(J.H Park)
	 */
	//String codeid = request.getParameter("code_id");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="./css/common.css" rel="stylesheet" type="text/css" />
<title>Selection of Target Particle</title>
</head>
<body>
<SCRIPT LANGUAGE="JavaScript">
function selectPartInfo(part_no, part_sym){
	//var form2 = document.form1;
	//var id_value = form2.id_num.value;part_1_expression
	//alert(part_no);
	//alert(part_sym);
	//var expression = opener.getElementById("part_1_expression");
	//alert(expression);
	//expression.innerHTML = part_sym;
	opener.form1.<%=id%>.value=part_no;
	opener.form1.<%=sym%>.value=part_sym;
	opener.addExpression('<%=sym%>');
	self.close();
}

function selfClose(){	
	alert("Can't find the particle information!");
	self.close();
}

//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</SCRIPT>
<form name="form1" action="" method="POST">
<jsp:useBean id="searchControl" class="nfri.dcpp.properties.business.Ctr_Property_Search_Process" scope="page"/>
<div id="content">
<input type="hidden" name="id" value="<%=id%>"/>
<input type="hidden" name="sym" value="<%=sym%>"/>
<p class="level2">Selection of Target Particle</p>
<span class="help">This is count of all particle in the database.</span><br>
<span class="help">Please click the particle name for searching.</span><br>
<span class="help">The count of result varies with the search condition.</span><br>

	<%
		Vector pt_info = searchControl.searchPropertyPartCountInfo(db, mp, id);		
		int j = 0;
		if(pt_info.size() == 0){
		%>
			<script>selfClose();</script>		
		<%
		}
		
		//���ں��� ������ ���� ���̺��� ���� ���� �����
		String temp_amcount = "0";
		//pt_info.size()
		for(int i = 0; i < pt_info.size(); i++){
			Properties_Part_Count_Info info = (Properties_Part_Count_Info)pt_info.elementAt(i);
			String pt_num = info.getPL_CPBI_ELE_NUM();
			String pt_count = info.getCOUNT_NUM();
			String pt_sym = info.getPL_CPBI_ELE_SYMBOL();
			String pt_amcount = info.getPL_CPBI_ELE_AMCOUNT(); 
			String print_value = pt_sym + "<font color ='6666FF'> (" + pt_count + ") </font>";		
			
			if(temp_amcount.equalsIgnoreCase("0")){ //���ں��� ������ 0 �̸� ó�� �׸���
				//System.out.println("���ں��� ����[1] : "+pt_amcount);
				%>
				<span class="help">Atom / Molecule Count : <%=pt_amcount %></span><br>
				<table class="tab5">
					<col width="20%">
					<col width="20%">
					<col width="20%">
					<col width="20%">
					<col width="20%">								
				<%
			}else if(!pt_amcount.equalsIgnoreCase(temp_amcount)){ //���ں��� ������ �ٸ��� ���̺� �ٽ� �׸���
				//System.out.println("���ں��� ����[2] : "+pt_amcount);
				j=0;
				%>
				</table>
				<span class="help">Atom / Molecule Count : <%=pt_amcount %></span><br>
				<table class="tab5">
					<col width="20%">
					<col width="20%">
					<col width="20%">
					<col width="20%">
					<col width="20%">								
				<%
			}			
			if(j==0){					
				%>
				<tr>
				<%				
			}						
			if(j<5){
				//System.out.println("j : " + j + " = "+print_value);
			%>
				<td class="tdl" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'" onClick="selectPartInfo('<%=pt_num%>','<%=pt_sym%>');">
					<a href="javascript:void(0);" onClick="selectPartInfo('<%=pt_num%>','<%=pt_sym%>'); return false;"><%=print_value%></a>
				</td>
			<%
			}
			j++;
			if(j==5){				
				%>
				</tr>
				<%
				j=0;
			}
			
			
			
			if(j==5){
													
			}
			%>
			
			<%
			temp_amcount = pt_amcount;
			
		
		}
	%>
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
		 window.resizeTo(650,pageheight);
	  }
	}

/* window.onload = function(){
	  init();
} */
</script>
</body>
</html>