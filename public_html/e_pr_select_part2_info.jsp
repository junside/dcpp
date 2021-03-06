<%@ page language="java" contentType="text/html; charset=EUC-KR"  pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="nfri.dcpp.properties.db.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<%
request.setCharacterEncoding("EUC-KR");
String id = request.getParameter("id"); //textfield ID값
String sym = request.getParameter("sym"); //원소가 표시되는 형태의 value 값
/* Properties_Part_Count_Info info = (Properties_Part_Count_Info)pt_info.elementAt(i);
String pt_num = info.getPL_CPBI_ELE_NUM();
String pt_count = info.getCOUNT_NUM();
String pt_sym = info.getPL_CPBI_ELE_SYMBOL(); */
%>
<%
	/**
	 * 수식을 입력하는 JSP 페이지
	 * by Junside(J.H Park)
	 */
	//String codeid = request.getParameter("code_id");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="./css/common.css" rel="stylesheet" type="text/css" />
<title>Select Target Particle</title>
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

//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</SCRIPT>
<form name="form1" action="" method="POST">
<jsp:useBean id="searchControl" class="nfri.dcpp.properties.business.Ctr_Property_Search_Process" scope="page"/>
<div id="content">
<input type="hidden" name="id" value="<%=id%>"/>
<input type="hidden" name="sym" value="<%=sym%>"/>
<p class="level2">Select Target Particle</p>
<span class="help">This is all entered list in database.</span><br>
<span class="help">Please click the particle name.</span><br>
<table class="tab1">
	<col width="20%">
	<col width="20%">
	<col width="20%">
	<col width="20%">
	<col width="20%">
	<%
		Vector pt_info = searchControl.searchPropertyPartCountInfo();		
		int j = 0;
		for(int i = 0; i < pt_info.size(); i++){
			Properties_Part_Count_Info info = (Properties_Part_Count_Info)pt_info.elementAt(i);
			String pt_num = info.getPL_CPBI_ELE_NUM();
			String pt_count = info.getCOUNT_NUM();
			String pt_sym = info.getPL_CPBI_ELE_SYMBOL();
			String print_value = pt_sym + " (" + pt_count + ") ";
			if(j==0){
			%>
				<tr>			
			<%	
			}						
			if(j<5){
			%>
				<td class="tdl">
					<a href="javascript:void(0);" onClick="selectPartInfo('<%=pt_num%>','<%=pt_sym%>'); return false;"><%=print_value%></a>
				</td>
			<%
			}else{
			%>
				</tr>
			<%
			}
			j++;
			if(j==5){
				j=0;
			}						
		}
	%>
</table>
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

window.onload = function(){
	  init();
}
</script>
</body>
</html>