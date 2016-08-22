<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.properties.db.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>   
<jsp:useBean id="part_info" class="nfri.dcpp.properties.business.Ctr_Part_Info_Process" scope="page"/>

<%

	String pr_st_no = "B201223000";
	String pr_ed_no = "B201226000";
	
	Vector pt_info = part_info.selectPartInfo(); // OK
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
function modifyDataInfo(String part_no){ //수정하기
	var form2 = document.form1;
//    form2.target = "_self";
	form2.target = "_blank";
    form2.action = pr_modify_part_info_update.jsp?part_no="+part_no;
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
<table class="tab2">
<col width="70">
<col width="500">
<col width="80">
<tr>
   <th class="thc">
    	 번호
   </th >
   <th class="thc">
     	입자정보
   </th>
   <th class="thc">
     	수정
   </th>
 </tr>
  <%
	  	for (int i = 0 ; i < pt_info.size(); i++) {

	  	Basic_Part_Info p_info = (Basic_Part_Info) pt_info.elementAt(i);
	  	String part_no = p_info.getPL_CPBI_ELE_NUM();	 //원자분자 관리번호 	
	  	String part_symbol = p_info.getPL_CPBI_ELE_SYMBOL(); //원자분자 표현
	  	String part_count = p_info.getPL_CPBI_ELE_AMCOUNT(); //원자분자 개수
	  	String part_num = p_info.getPL_CPBI_ELE_NUM(); //원자분자 번호
	  	String part_name = p_info.getPL_CPBI_ELE_NAME(); //원자분자 이름
	  	String part_mass = p_info.getPL_CPBI_ELE_MASS(); //원자분자 질량
	  	String part_type = p_info.getPL_CPBI_ELE_TYPE(); //입자 유형(원자/분자)
	  	
		//System.out.println("입자 유형 : " + part_type);
		//if("0".equalsIgnoreCase(part_count)){

	  %>
	  		<tr>	  			
			    <td class="tdc" rowspan="3" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'" >
			    	<%=part_no%>
			    </td>			    
			    <td class="tdl" bgcolor = "green">			    
			     	<font color = "white">원자 / 분자 표현 : <%=part_symbol%></font>
			    </td>		    			    
			    <td class="tdc" rowspan="3"  onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
			    	<a href="pr_modify_part_info_update.jsp?part_no=<%=part_no%>" target="_blank" onfocus='this.blur()'>입자수정</a>
			    </td >
		  	</tr>
		  	<tr>		  	
		  	<td class="tdl" bgcolor = "darkgrey">
			     	<font color = "white">원자 / 분자 이름: <%=part_name%></font>
			</td >		  		  	
		  	</tr>
		  	<tr>
		  	<%if("0".equalsIgnoreCase(part_count)){ %>
		  	<td class="tdl" bgcolor = "orange">
			   	<font color = "white">원자 / 분자 개수: <%=part_count%></font>
			</td >			     	
			<%}else{ %>
			<td class="tdl" bgcolor = "skyblue">
			 	<font color = "white">원자 / 분자 개수: <%=part_count%></font>
			</td >	
			<%} %>		  	
		  		  		  	
		  	</tr>		  	
	  		<%
		//	}
	  	}
	  	
	  %>
</table>
</form>
<script language="javascript">
		init('<%=totalRecord%>');
	</script>	
</body>
</html>