<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="property_info" class="nfri.dcpp.properties.business.Ctr_Property_Info_Process" scope="page"/>
<jsp:useBean id="data_info" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<jsp:useBean id="option_code" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
<jsp:useBean id="assess_info" class="nfri.dcpp.properties.business.Ctr_Property_Assess_Info" scope="page"/>

<%
	/**
	* 1차 평가 정보를 확인하는 JSP 페이지(view)
	* by Junside(J.H Park)
	*/
	
	String pr_no = request.getParameter("pr_no");
	
	//기본 정보 가져오기
	Properties_Basic_Info spec_info = (Properties_Basic_Info)property_info.selectViewPropertySpecInfo(pr_no);
	//데이터분류
	String db_value = spec_info.getPL_BI_DATA_BRANCH();
	//주프로세스
	String mp_value = spec_info.getPL_BI_MAIN_PROC();
	//부프로세스
	String sp_value = spec_info.getPL_BI_SUB_PROC();
	//충돌방식
	String imp_value = spec_info.getPL_BI_IMP_CLASS();
	//검증구분
	String exp_value = spec_info.getPL_BI_EXP_THE_REC();
	//표현식 정보 가져오기
	String expression = spec_info.getPL_BI_EXPRESSION();//(String)property_info.selectEquationData(pr_no);
	//참고문헌 번호
	String artcl_no = spec_info.getPL_RAI_ARTCL_NUM();
	
	//단위 정보 가져오기
	Graph_Basic_Info basic_info = (Graph_Basic_Info)data_info.selectViewGraphBasicInfo(pr_no);
	
	String x_unit = basic_info.getPL_BGI_X_AX_UNIT();
	//String y_unit = ComUtil.convertSupTag(basic_info.getPL_BGI_Y_AX_UNIT());
	String y_unit = basic_info.getPL_BGI_Y_AX_UNIT();
	
	Article_Info art_Info = (Article_Info)property_info.selectArticleInfo(pr_no);
	
	String a_path = art_Info.getPL_RAI_ORG_FILE_PATH();
	String a_name = art_Info.getPL_RAI_ORG_FILE_NAME();
	String a_ext = art_Info.getPL_RAI_ORG_FILE_EXT();
	
	//파일 다운로드를 위한 변수
	String file_path = "";
	String file_name = "";
	String file_ext = "";
	
	//평가 정보
	Properties_Assess_Info info = assess_info.selectAssessInfo(pr_no);
	//1.1 판단
	String step1_v = ComUtil.isNullToEmptyString(info.getPL_PEI_1STEP());
	//2.1 측정방식
	String method_v = ComUtil.isNullToEmptyString(info.getPL_PEI_METHOD());
	//2.2 실험 근거 제시
	String scient_basis_v = ComUtil.isNullToEmptyString(info.getPL_PEI_SCIENT_BASIS());
	//2.3 판단
	String step2_v = ComUtil.isNullToEmptyString(info.getPL_PEI_2STEP());
	//3.1 판단
	String step3_v = ComUtil.isNullToEmptyString(info.getPL_PEI_3STEP());
	//4.1 한계
	String scient_limit_v = ComUtil.isNullToEmptyString(info.getPL_PEI_SCIENT_LIMIT());
	//4.2 변수
	String primary_fact_v = ComUtil.isNullToEmptyString(info.getPL_PEI_PRIMARY_FACT());
	//4.3 불확도
	String measurement_v = ComUtil.isNullToEmptyString(info.getPL_PEI_MEASUREMENT());
	//4.4 판단
	String step4_v = ComUtil.isNullToEmptyString(info.getPL_PEI_4STEP());
	//5.1 분석
	String data_analy_v = ComUtil.isNullToEmptyString(info.getPL_PEI_DATA_ANALY());
	//5.2 판단
	String step5_v = ComUtil.isNullToEmptyString(info.getPL_PEI_5STEP());
	//6.1 간접
	String indirect_v = ComUtil.isNullToEmptyString(info.getPL_PEI_INDIRECT());
	//6.2 직접
	String direct_v = ComUtil.isNullToEmptyString(info.getPL_PEI_DIRECT());
	//6.3 판단
	String step6_v = ComUtil.isNullToEmptyString(info.getPL_PEI_6STEP());
	//7.1 평가내용
	String prim_eval_v = ComUtil.isNullToEmptyString(info.getPL_PEI_PRIM_EVAL());
	//7.2 평가결과
	String final_v = ComUtil.isNullToEmptyString(info.getPL_PEI_FINAL_FLAG());
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>1차 평가 결과 보기</title>
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

function exitInfo(){ //종료
	if(confirmMsg('2') == true){
		self.close();
	}
}

function printAssessInfo(pr_no){
	if(confirmMsg('1') == true){
		window.open("pr_print_first_assess_info.jsp?pr_no="+pr_no,"assess_info","width=800,height=900,toolbar=no,location=no,status=no");
		return;
	}
}

function confirmMsg(number){
	var num = number;
	switch(num) {
		case '1': //문서출력하기
			var result = confirm("평가 결과를 문서로 출력하시겠습니까?\n필요에 따라 문서 출력에 필요한 ActiveX Control이 설치됩니다.");
			return result;
			break;
		case '2': //종료
			var result = confirm("평가 결과 보기를 종료하시겠습니까?");
			return result;
			break;
		case '3': //참고문헌 보기
			var result = confirm("원문 정보를 확인하시겠습니까?");
			return result;
			break;
		default :		
			break;
	}		
}

function viewArticleInfo(artcl_no){ //참고문헌 보기
	if(confirmMsg('3') == true){
		window.open("pr_view_article_info.jsp?artcl_no="+artcl_no,"article_info","width=700,height=500,toolbar=no,location=no,status=no,scrollbar=auto");
		return;
	}
}

function downloadArtcl(path, name, ext){ //참고문헌 다운로드
	var form2 = document.form1;
	//form에 값을 넘기기 위하여 폼객체에 페이지의 객체값을 넣는다.
	form2.file_path.value = path;
	form2.file_name.value = name;
	form2.file_ext.value = ext;
	form2.target = "_self";
	form2.action = "pr_article_download_file.jsp";
	form2.submit();
}
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<body>
<form name="form1" action="" method="POST">
<div id="content">
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
<input type="hidden" name="file_path" value="<%=file_path%>"/>
<input type="hidden" name="file_name" value="<%=file_name%>"/>
<input type="hidden" name="file_ext" value="<%=file_ext%>"/>
<p class="level2">1차 평가 정보</p>
<table class="tab2">
<col width="130">
<col width="520">
  <tr>
    <th class="thc"><font color = red>물성정보번호</font></th>
    <td class="tdl">
    <%
    	if(ComUtil.isNull(pr_no)){
    %>
    	자동으로 선택됩니다.
    <%
    	}else{
    %>
    	<%=pr_no%>
    <%
    	}
    %>
    </td>
  </tr>
</table>
<table class="tab2">
  <col width="120">
  <col width="100">
  <col width="430">
  <tr>
    <th class="thc">구분</th>
    <th class="thc">내용</th>
    <th class="thc">평가 내역</th>
  </tr>
  <tr>
    <th rowspan="7" class="thl">1.측정 대상 명시</th>
    <td class="tdl">데이터구분</td>
    <td class="tdl"><%=db_value %></td>
  </tr>
  <tr>
    <td class="tdl">프로세스</td>
    <td class="tdl"><%=imp_value %>, <%=mp_value %>, <%=sp_value %></td>
  </tr>
  <tr>
    <td class="tdl">반응식</td>
    <td class="tdl"><%=expression %></td>
  </tr>
  <tr>
    <td class="tdl">X 단위</td>
    <td class="tdl"><%=x_unit %></td>
  </tr>
  <tr>
    <td class="tdl">Y 단위</td>
    <td class="tdl"><%=y_unit %></td>
  </tr>
  <tr>
    <td class="tdl">원문 정보</td>
	<td class="tdl" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
    	<a href="javascript:void(0);"  onClick="viewArticleInfo('<%=artcl_no%>'); return false;">보기</a>			    
    </td>
  </tr>
  <tr>
    <td class="tdl">판단</td>
    <td> 
    <%
    	if(step1_v.equalsIgnoreCase("pass")){
    %>
    	적절
    <%
    	}else{
    %>
    	부적절
    <%
    	}
    %>  	  
  	</td>
  </tr>
  <tr>
  	<th rowspan="4" class="thl">2.측정 방법 명시</th>
    <td class="tdl">검증구분</td>
    <td class="tdl"><%=exp_value %></td>
  </tr>  
  <tr>
    <td class="tdl">측정방식</td>
    <td colspan="3"> 
  	  <textarea name="method" rows="3" cols="55" readonly><%=ComUtil.isNullStringToEmptyString(method_v)%></textarea>
  	</td>
  </tr>
  <tr>
    <td class="tdl">실험근거 제시</td>
    <td colspan="3"> 
  	  <textarea name="scient_basis" rows="3" cols="55" readonly><%=ComUtil.isNullStringToEmptyString(scient_basis_v)%></textarea>
  	</td>
  </tr>
  <tr>
    <td class="tdl">판단</td>
    <td> 
    <%
    	if(step2_v.equalsIgnoreCase("pass")){
    %>
    	적절
    <%
    	}else{
    %>
    	부적절
    <%
    	}
    %>  
  	</td>
  </tr>
  <tr>
  	<th rowspan="1" class="thl">3.소급성</th>
    <td class="tdl">판단</td>
    <td> 
    <%
    	if(step3_v.equalsIgnoreCase("pass")){
    %>
    	적절
    <%
    	}else{
    %>
    	부적절
    <%
    	}
    %>  
  	</td>
  </tr>
  <tr>
  	<th rowspan="4" class="thl">4.정확도 제시</th>
    <td class="tdl">방식의 한계</td>
    <td colspan="3"> 
  	  <textarea name="scient_limit" rows="3" cols="55" readonly><%=ComUtil.isNullStringToEmptyString(scient_limit_v)%></textarea>
  	</td>
  </tr>  
  <tr>
    <td class="tdl">실험변수 제시</td>
    <td colspan="3"> 
  	  <textarea name="primary_fact" rows="3" cols="55" readonly><%=ComUtil.isNullStringToEmptyString(primary_fact_v)%></textarea>
  	</td>
  </tr>
  <tr>
    <td class="tdl">불확도 제시</td>
    <td colspan="3"> 
  	  <textarea name="measurement" rows="3" cols="55" readonly><%=ComUtil.isNullStringToEmptyString(measurement_v)%></textarea>
  	</td>
  </tr>
  <tr>
    <td class="tdl">판단</td>
    <td> 
    <%
    	if(step4_v.equalsIgnoreCase("pass")){
    %>
    	적절
    <%
    	}else{
    %>
    	부적절
    <%
    	}
    %>  
  	</td>
  </tr>  
  <tr>
  	<th rowspan="2" class="thl">5.재현성</th>
    <td class="tdl">데이터분석</td>
    <td colspan="3"> 
  	  <textarea name="data_analy" rows="3" cols="55" readonly><%=ComUtil.isNullStringToEmptyString(data_analy_v)%></textarea>
  	</td>
  </tr>  
  <tr>
    <td class="tdl">판단</td>
    <td> 
    <%
    	if(step5_v.equalsIgnoreCase("pass")){
    %>
    	적절
    <%
    	}else{
    %>
    	부적절
    <%
    	}
    %>  
  	</td>
  </tr> 
  <tr>
  	<th rowspan="3" class="thl">6.일관성</th>
    <td class="tdl">간접평가</td>
    <td colspan="3"> 
  	  <textarea name="indirect" rows="3" cols="55" readonly><%=ComUtil.isNullStringToEmptyString(indirect_v)%></textarea>
  	</td>
  </tr>  
  <tr>
    <td class="tdl">직접평가</td>
    <td colspan="3"> 
  	  <textarea name="direct" rows="3" cols="55" readonly><%=ComUtil.isNullStringToEmptyString(direct_v)%></textarea>
  	</td>
  </tr>
  <tr>
    <td class="tdl">판단</td>
    <td> 
    <%
    	if(step6_v.equalsIgnoreCase("pass")){
    %>
    	적절
    <%
    	}else{
    %>
    	부적절
    <%
    	}
    %>  
  	</td>
  </tr>
  <tr>
  	<th rowspan="1" class="thl">1차평가 내용</th>
    <td colspan="3">
  	  <textarea name="prim_eval" rows="3" cols="70" readonly><%=ComUtil.isNullStringToEmptyString(prim_eval_v)%></textarea>
  	</td>
  </tr>  
  <tr>
  	<th rowspan="1" class="thl">1차평가 결과</th>
    <td colspan="3">
    <%
    	if(final_v.equalsIgnoreCase("V")){
    %>
      	유효한 데이터
    <%
    	}else if(final_v.equalsIgnoreCase("R")){
    %>
    	 기각
    <%
    	}else{
	%>
      	평가유보
    <%
    	}
    %>  	  
  	</td>
  </tr> 
</table>
<div class="bbtn_r">
<a href="javascript:printAssessInfo('<%=pr_no%>')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('printAssessImage','','images/img_cont/doc_print_upper.gif',1)"><img src="images/img_cont/doc_print_normal.gif" name="printAssessImage" width="80" height="24" border="0" id="printAssessImage" /></a>
<a href="javascript:exitInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('exitImage','','images/img_cont/exit_upper.gif',1)"><img src="images/img_cont/exit_normal.gif" name="exitImage" width="80" height="24" border="0" id="exitImage" /></a>
</div>
</div>
</form>

</body>
</html>