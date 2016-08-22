<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.properties.db.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>   
<jsp:useBean id="graph_info" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>

<%

	String pr_st_no = "B201000000";
	String pr_ed_no = "B201026000";
	
	Vector pt_info = graph_info.selectModifyNumberList(pr_st_no, pr_ed_no); // OK
	//전체 개수 저장
	int totalRecord = pt_info.size();
	//String whereCondtion = property_data.getConditionInfo(request);
	//System.out.println("whereCondtion : " + whereCondtion);
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
function modifyDataInfo(String pr_no){ //수정하기
	var num_mod = "num_mod";
	var form2 = document.form1;
//    form2.target = "_self";

	form2.target = "_blank";
    form2.action = pr_delete_info_check.jsp?pr_no="+pr_no+"&gotoindex="+num_mod;
	form2.submit();	
	//alert(pr_no);2
	//window.open("pr_modify_target_part_info.jsp?pr_no="+pr_no,"equa_info","width=100%,height=100%,toolbar=no,location=no,status=no");
	//return;
}

function init(total){
	if(total == 0){
		alert("데이터가 존재하지 않습니다.");
		return;
	}
}
</script>
<form name="form1" action="" method="POST" >
<span class="count">
데이터 : <%=totalRecord%>건 
</span>
<table class="tab7">
<col width="200">
<col width="150">
<tr>
   <th class="thc">
    	 번호
   </th >
   <th class="thc">
     	수정
   </th>
 </tr>
  <%
	  	for (int i = 0 ; i < pt_info.size(); i++) {

	  	String pr_no = (String) pt_info.elementAt(i);
		String mod_val = "num_mod";
	  %>
	  		<tr>	  			
			    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'" >
			    	<%=pr_no%>
			    </td>			    	    			    
			    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
			    	<a href="pr_delete_property_info_check.jsp?pr_no=<%=pr_no%>&gotoindex=<%=mod_val%>" target="_blank" onfocus='this.blur()'>소수점수정</a>
			    </td >
		  	</tr>		  	
	  		<%
		
	  	}
	  	
	  %>
</table>
</form>
<script language="javascript">
		init('<%=totalRecord%>');
	</script>	
</body>
</html>