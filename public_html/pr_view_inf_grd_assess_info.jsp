<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("euc-kr"); %>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="ctr_data" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
<jsp:useBean id="property_info" class="nfri.dcpp.properties.business.Ctr_Inf_Grd_Property_Info_Process" scope="page"/>
<jsp:useBean id="assess_info" class="nfri.dcpp.properties.business.Ctr_Property_Assess_Info" scope="page"/>
<%
	/**
	* 최종 평가정보를 확인하는 페이지
	* by Junside(J.H Park)
	*/
	
	String v_pr_no     = ComUtil.isNullToDashString(request.getParameter("v_pr_no")); //등급유력 물성번호
	//System.out.println("v_pro_no : "+ v_pr_no);
	
	//기본 정보 가져오기
	Inf_Grd_Properties_Basic_Info spec_info = (Inf_Grd_Properties_Basic_Info)property_info.selectViewInfGrdPropertyInfo(v_pr_no);
	//String data_num =  spec_info.getPL_IGBI_DATA_NUM();
	String mp_value = spec_info.getPL_IGBI_MAIN_PROC();
	String sp_value = spec_info.getPL_IGBI_SUB_PROC();
	String expression = spec_info.getPL_IGBI_EXPRESSION();
	String user = spec_info.getPL_UI_ID();
	String dt_list = spec_info.getPL_IGBI_DATA_NUM_LIST();
	
	String pt_list = property_info.makeReferenceLink(dt_list);
	
	//String exp_param_mp_option = ctr_data.getExpName(mp_value);
	//String exp_param_sp_option = ctr_data.getExpName(sp_value);
	//String process_option = ComUtil.getAbbrString(exp_param_mp_option, 10) + ", " + ComUtil.getAbbrString(exp_param_sp_option, 10);
	
	//평가 정보 가져오기
	Properties_Final_Assess_Info f_assess_info = (Properties_Final_Assess_Info)assess_info.selectFinalAssessInfo(v_pr_no);
	String sei_method_best = f_assess_info.getPL_SEI_PROD_METHOD_BEST();
	String sei_content = f_assess_info.getPL_SEI_ASSESS_CONTENT();
	String sei_energy = f_assess_info.getPL_SEI_ENERGY_RANGE();
	String sei_opinion = f_assess_info.getPL_SEI_FINAL_OPINION();
	String sei_std_data = f_assess_info.getPL_SEI_STD_REF_DATA();
	String sei_period = f_assess_info.getPL_SEI_COLLECT_PERIOD();
	String sei_method_average = f_assess_info.getPL_SEI_PROD_METHOD_AVERAGE();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>최종 평가 확인 페이지</title>
</head>
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

function printAssessInfo(v_pr_no){ //문서 출력
	if(confirmMsg('1') == true){
		window.open("pr_print_final_assess_info.jsp?v_pr_no="+v_pr_no,"assess_info","width=800,height=900,toolbar=no,location=no,status=no");
		return;
	}
}

function viewPropertyInfo(pr_no){ //물성정보 보기
	if(confirmMsg('2') == true){
		window.open("pr_view_property_info.jsp?pr_no="+pr_no,"article_info","width=700,height=500,toolbar=no,location=no,status=no,scrollbar=auto");
		return;
	}
}

function confirmInfo(){ //종료
	self.close();
	//if(confirmMsg('3') == true){		
	//}
}

function confirmMsg(number){
	var num = number;
	switch(num) {
		case '1': //문서출력하기
			var result = confirm("평가 결과를 문서로 출력하시겠습니까?\n필요에 따라 문서 출력에 필요한 ActiveX Control이 설치됩니다.");
			return result;
			break;
		case '2': //물성정보 보기
			var result = confirm("물성 정보를 확인하시겠습니까?");
			return result;
			break;
		case '3': //종료
			var result = confirm("평가 결과 보기를 종료하시겠습니까?");
			return result;
			break;
		default :		
			break;
	}		
}

document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<body>
<form name="form1" action="" method="POST">
<div id="content">
<p class="level2">최종 평가 정보 확인</p>
<table class="tab2">
<col width="250">
<col width="100">
<col width="300">
  <tr>
    <th class="thc">Title</th>
    <th class="thc" colspan="2">Note</th>
  </tr>
  <tr>
    <th rowspan="6" class="thl">1. Basic Information</th>
    <td class="tdr">등급유력번호</td>
    <td class="tdl"><%=v_pr_no%></td>
  </tr>
  <tr>
    <td class="tdr">주프로세스</td>
    <td class="tdl"><%=mp_value%></td>
  </tr>
  <tr>
    <td class="tdr">부프로세스</td>
    <td class="tdl"><%=sp_value%></td>
  </tr>
  <tr>
    <td class="tdr">물성 반응식 표현</td>
    <td class="tdl"><%=expression%></td>
  </tr>
  <tr>
    <td class="tdr">관련 물성번호</td>
    <td class="tdl" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
    	<%=pt_list%>			    
    </td>
  </tr>
  <tr>
    <td class="tdr">평가자</td>
    <td class="tdl"><%=user%></td>
  </tr>
  <tr>
    <th rowspan="2" class="thl">2. Indirect Production Method</th>
    <td class="tdr">Best Value</td>
    <td class="tdl"><%=sei_method_best%></td>
  </tr>
  <tr>
    <td class="tdr">Average Value</td>
    <td class="tdl"><%=sei_method_average%></td>
  </tr>
    <tr>
    <th class="thl">3. Energy Range</th>
    <td class="tdr">X MAX / X MIN</td>
    <td class="tdl"><%=sei_energy%></td>
  </tr>
  <tr>
  	<th class="thl">4. Assessment</th>
    <td class="tdl" colspan="2"><%=sei_content%></td>
  </tr>  
  <tr>
    <th class="thl">5. Collection Period</th>
    <td colspan="2"><%=sei_period%></td>
  </tr>
  <tr>
    <th class="thl">6. Final Opinion</th>
    <td colspan="2"><%=sei_opinion%></td>
  </tr>
  <tr>
    <th class="thl">7. Standard Reference Data</th>
    <td colspan="2"><%=sei_std_data%></td>
  </tr>
</table>
<div class="bbtn_r">
<a href="javascript:printAssessInfo('<%=v_pr_no%>')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('printAssessImage','','images/img_cont/doc_print_upper.gif',1)"><img src="images/img_cont/doc_print_normal.gif" name="printAssessImage" width="80" height="24" border="0" id="printAssessImage" /></a>
<a href="javascript:confirmInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('confirmImage','','images/img_cont/confirm_upper.gif',1)"><img src="images/img_cont/confirm_normal.gif" name="confirmImage" width="80" height="24" border="0" id="confirmImage" /></a>
</div>
</div>
</form>
</body>
</html>