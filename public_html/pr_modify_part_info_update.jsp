<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="part_info" class="nfri.dcpp.properties.business.Ctr_Part_Info_Process" scope="page"/>

<%
	/**
	* 기본 물성 정보를 입력하는 JSP 페이지(view)
	* by Junside(J.H Park)
	*/
	
	String expression = "";
	String addcode_id = "";//request.getParameter("addcode_id");
	String pr_no = "";
	String index = "";
	String part_no = request.getParameter("part_no"); //"B201000075";//
	//Properties_Equation_Get_DbInfo
	//String org_exp = property_info.selectEquationData()
	Basic_Part_Info part_data = part_info.selectPartInfo(part_no);	
	
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

function MM_swapImage(){ //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function updateInfo(seq){ //업데이트 작업
	//var form2 = document.form1;	
	//form2.action = "pr_modify_part_info_check.jsp?flag=1&seq="+seq;
	//form2.submit();
	//alert(seq);
	//addExpression();
	//if(saveInfoCheck() == false){
		var form2 = document.form1;	
		form2.action = "pr_modify_part_info_check.jsp?seq="+seq;
		form2.submit();
	//}else{
	//	return;
	//}
}

function insertExpression(){//입자 표현식 정보 가져오기
	var form2 = document.form1;	

	//alert("part_symbol");
	//var part_id = "part_symbol";
	//window.open("pr_insert_expression.jsp?id=part_symbol","insert_expression",toolbar=no,location=no,status=no");
}

</script>

<form name="form1" action="" method="POST">
<input type="hidden" name="pr_expression" value=""/>
<input type="hidden" name="part_no" value='<%=part_no%>'>
<input type="hidden" name="index" value='<%=index%>'>
<jsp:useBean id="option_code" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
<p class="level2">원자 분자 정보 수정</p>
<table class="tab2">
  <col width="120">
  <col width="450">
  <col width="70"> 
  <tr>
    <th class="thca">입자 관리 번호</th>
    <td class="tdl" colspan="2">
    	<%=part_no%>
    </td>
  </tr>
  <tr>
    <th class="thca" rowspan="2">입자 기호</th>
    <td class="tdl" id="part_exp">
    <span class="help">입자 기호의 입력 예 1) H<sub>2</sub>이면 H&lt;sub&gt;2&lt;/sub&gt;로 입력하세요.</span><br>
	<span class="help">입자 기호의 입력 예 2) H<sup>2</sup>이면 H&lt;sup&gt;2&lt;/sup&gt;로 입력하세요.</span><br>
    <span class="help">현재 DB 입력 값 : <%=ComUtil.isNullToEmptyString(part_data.getPL_CPBI_ELE_SYMBOL())%></span>
    </td>
    <td class="tdc" rowspan="2">
    	<a href="javascript:void(0);"  onClick="updateInfo('0'); return false;"> 수정 </a>
    </td>
  </tr>
  <tr>
    <td class="tdl">
    	<input type="text" name="part_symbol" value="<%=ComUtil.isNullToEmptyString(part_data.getPL_CPBI_ELE_SYMBOL())%>" size="60" maxlength="500" >
    </td>
  </tr>
  <tr>
    <th class="thca">입자 이름</th>
    <td class="tdl">
    	<input type="text" name="part_name" value="<%=ComUtil.isNullToEmptyString(part_data.getPL_CPBI_ELE_NAME())%>" size="60" maxlength="100" >
    </td>
    <td class="tdc">
      	<a href="javascript:updateInfo('1');">수정 </a>
    </td>
  </tr>
  <tr>
    <th class="thca">입자 질량</th>
    <td class="tdl">
    	<input type="text" name="part_mass" value="<%=ComUtil.isNullToEmptyString(part_data.getPL_CPBI_ELE_MASS())%>" size="60" maxlength="100" >
    </td>
    <td class="tdc">
      	<a href="javascript:updateInfo('2');">수정 </a>
    </td>
  </tr>
  <tr>
    <th class="thca" rowspan="2">원자/전자 구분</th>
    <td class="tdl">
    	<span class="help">반드시 다음 선택 : 원자 일 경우 "A" 입력, 분자일 경우 "M" 을 입력하세요.</span>
    </td>
    <td class="tdc" rowspan="2">
      	<a href="javascript:updateInfo('3');">수정 </a>
    </td>
  </tr>
  <tr>
    <td class="tdl">
    	<input type="text" name="part_type" value="<%=ComUtil.isNullToEmptyString(part_data.getPL_CPBI_ELE_TYPE())%>" size="60" maxlength="100" >
    </td>
  </tr>
  <tr>
    <th class="thca" rowspan="2">원자 번호</th>
    <td class="tdl">
    	<span class="help">반드시 주기율표에 존재하는 원자번호만 입력하고 그외는 입력 하지 않습니다.</span>
    </td>
    <td class="tdc" rowspan="2">
      	<a href="javascript:updateInfo('4');">수정</a>
    </td>
  </tr>
  <tr>
    <td class="tdl">
    	<input type="text" name="part_amnum" value="<%=ComUtil.isNullToEmptyString(part_data.getPL_CPBI_ELE_AMNUM())%>" size="60" maxlength="100" >
    </td>
  </tr>
  <tr>
    <th class="thca">원자/전자 개수</th>
    <td class="tdl">
    	<input type="text" name="part_amcount" value="<%=ComUtil.isNullToEmptyString(part_data.getPL_CPBI_ELE_AMCOUNT())%>" size="60" maxlength="100" >
    </td>
    <td class="tdc">
      	<a href="javascript:updateInfo('5');">수정 </a>
    </td>
  </tr>
</table>
<script language="javascript">
	//addExpression();
</script>
</form>
</body>
</html>