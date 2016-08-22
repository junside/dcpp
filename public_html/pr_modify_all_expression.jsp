<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.properties.db.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>   
<jsp:useBean id="property_info" class="nfri.dcpp.properties.business.Ctr_Property_Info_Process" scope="page"/>

<%

	String pr_st_no = "B201220000";
	String pr_ed_no = "B201223000";
	
	boolean value = property_info.updateAllExpressionChange(pr_st_no, pr_ed_no); // OK
	Vector pt_info = property_info.getPropertyListInfo(pr_st_no, pr_ed_no); // OK
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
	var form2 = document.form1;
//    form2.target = "_self";
	form2.target = "_blank";
    form2.action = pr_modify_target_part_info_update.jsp?pr_no="+pr_no;
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
<span class="help">
시작 : <%=pr_st_no%> ~ 끝 : <%=pr_ed_no%>  
</span>
<span class="count">
데이터 : <%=totalRecord%>건 
</span>
<table class="tab2">
<col width="70">
<col width="500">
<col width="80">
<tr>
   <th class="thc">
    	 번호
   </th >
   <th class="thc">
     	반응식 / 조합식
   </th>
   <th class="thc">
     	수정
   </th>
 </tr>
  <%
  //pt_info.size()
	  	for (int i = 0 ; i < pt_info.size(); i++) {

	  	Properties_Basic_Info prot = (Properties_Basic_Info) pt_info.elementAt(i);
	  	String pro_no = prot.getPL_BI_DATA_NUM();	 //물성번호 	
	  	String expresstion = prot.getPL_BI_EXPRESSION(); //표현식
	  	String comb_exp = prot.getPL_BI_COMB_EXP(); //조합된 표현식 	
	  	
		//System.out.println("물성정보 : " + seach_pr_no);
		//System.out.println("표현식정보 : " + expresstion);
		//System.out.println("조합식정보 : " + comb_exp);
		//<a href="pr_modify_target_part_info.jsp?pr_no="+pr_no>수정</a>
		//<a href="javascript:modifyDataInfo('<%=pro_no
		//>'); return false;">수정</a>

	  %>
	  		<tr>	  			
			    <td class="tdc" rowspan="3">
			    	<%=pro_no%>
			    </td>			    
			    <td class="tdl" bgcolor = "green">			    
			     	<font color = "white">반응식:<%=expresstion%></font>
			    </td>		    			    
			    <td class="tdc" rowspan="3">
			    	<a href="pr_modify_target_part_info_update.jsp?pr_no=<%=pro_no%>" target="_blank" onfocus='this.blur()'>입자수정</a>
			    </td >
		  	</tr>
		  	<tr>		  	
		  	<td class="tdl" bgcolor = "grey">
			     	<font color = "white">조합식:<%=comb_exp%></font>
			</td >		  		  	
		  	</tr>
		  	<tr>		  	
		  	<td class="tdl" bgcolor = "yellow">
			     	<font size="2"><xmp><%=comb_exp%></xmp></font>
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