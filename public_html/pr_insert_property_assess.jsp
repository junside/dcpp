<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("euc-kr"); %>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="property_info" class="nfri.dcpp.properties.business.Ctr_Property_Info_Process" scope="page"/>
<jsp:useBean id="data_info" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<jsp:useBean id="assess_info" class="nfri.dcpp.properties.business.Ctr_Property_Assess_Info" scope="page"/>

<%
	/**
	* 1차 평가 정보를 입력하는 JSP 페이지(view)
	* by Junside(J.H Park)
	*/
	
	String pr_no = request.getParameter("pr_no");
	
	//기본 정보 가져오기
	Properties_Basic_Info spec_info = (Properties_Basic_Info)property_info.selectViewPropertySpecInfo(pr_no);
	//새로운 관리 번호
	String mgmt_num = spec_info.getPL_BI_MGMT_DATA_NUM();
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
	
	//프로세스 정보 정리
	String process = imp_value + ", " + mp_value + ", " + sp_value;
	//단위 정보 가져오기
	Graph_Basic_Info basic_info = (Graph_Basic_Info)data_info.selectViewGraphBasicInfo(pr_no);
	
	String x_unit = basic_info.getPL_BGI_X_AX_UNIT();
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
<title>Insert title here</title>
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

function assessInfo(){ //평가
	//1.1 판단
	//var form2 = document.form1;
	//2.1 측정방식
	//var method_v = form2.method.value;
	//alert("측정방식 내용 : " + method_v + " [ " + cal_length(method_v) + " byte]");
	//var total = total + method_v;

	//2.2 실험근거 제시
	//var scient_basis_v = form2.scient_basis.value;
	//alert("실험근거 내용 : " + scient_basis_v + " [ " + cal_length(scient_basis_v) + " byte]");
	//var total = total + scient_basis_v;

	//4.1 한계
	//var scient_limit_v = form2.scient_limit.value;
	//alert("한계 내용 : " + scient_limit_v + " [ " + cal_length(scient_limit_v) + " byte]");
	//var total = total + scient_limit_v;

	//4.2 변수
	//var primary_fact_v = form2.primary_fact.value;
	//alert("변수 내용 : " + primary_fact_v + " [ " + cal_length(primary_fact_v) + " byte]");
	//var total = total + primary_fact_v;

	//4.3 불확도
	//var measurement_v = form2.measurement.value;
	//alert("불확도 내용 : " + measurement_v + " [ " + cal_length(measurement_v) + " byte]");
	//var total = total + measurement_v;

	//5.1 분석
	//var data_analy_v = form2.data_analy.value;
	//alert("분석 내용 : " + data_analy_v + " [ " + cal_length(data_analy_v) + " byte]");
	//var total = total + data_analy_v;

	//6.1 간접
	//var indirect_v = form2.indirect.value;
	//alert("간접 내용 : " + indirect_v + " [ " + cal_length(indirect_v) + " byte]");
	//var total = total + indirect_v;

	//6.2 직접
	//var direct_v = form2.direct.value;
	//alert("직접 내용 : " + direct_v + " [ " + cal_length(direct_v) + " byte]");
	//var total = total + direct_v;

	//7.1 평가내용
	//var prim_eval_v = form2.prim_eval.value;
	//alert("평가내용 내용 : " + prim_eval_v + " [ " + cal_length(prim_eval_v) + " byte]");
	//var total = total + prim_eval_v;

	//7.2 전체합계
	//alert("입력 내용 총 합계 Byte : [ " + cal_length(total) + " byte]");

	
	if(confirmMsg('1') == true){
		var form2 = document.form1;
		form2.action = "pr_insert_first_assess.jsp";
		form2.submit();
	}
}

function cal_length(val)
{
	// 입력받은 문자열을 escape() 를 이용하여 변환한다.
	// 변환한 문자열 중 유니코드(한글 등)는 공통적으로 %uxxxx로 변환된다.
	var temp_estr = escape(val);
	var s_index = 0;
	var e_index = 0;
	var temp_str = "";
	var cnt = 0;
	
	// 문자열 중에서 유니코드를 찾아 제거하면서 갯수를 센다.
	while ((e_index = temp_estr.indexOf("%u", s_index)) >= 0) // 제거할 문자열이 존재한다면
	{
	temp_str += temp_estr.substring(s_index, e_index);
	s_index = e_index + 6;
	cnt ++;
	}
	
	temp_str += temp_estr.substring(s_index);
	
	temp_str = unescape(temp_str); // 원래 문자열로 바꾼다.
	
	// 유니코드는 2바이트 씩 계산하고 나머지는 1바이트씩 계산한다.
	return ((cnt * 2) + temp_str.length) + "";
}

function exitInfo(){ //종료
	if(confirmMsg('2') == true){
		var form2 = document.form1;	
		form2.action = "pr_common_list.jsp";
		form2.submit();
	}
}

function confirmMsg(number){
	var num = number;
	switch(num) {
		case '1': //평가하기
			var result = confirm("입력하신 내용으로 물성정보를 평가하시겠습니까?\n입력하신 내용을 한번 더 확인하시려면 취소를 누르세요.\n확인을 누르시면 평가가 완료됩니다.");
			return result;
			break;
		case '2': //종료
			var result = confirm("1차 평가 입력을 종료하시겠습니까?\n입력하신 내용은 저장되지 않습니다.\n주의하세요");
			return result;
			break;
		default :		
			break;
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
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<body>
<form name="form1" action="" method="POST">
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
<input type="hidden" name="file_path" value="<%=file_path%>"/>
<input type="hidden" name="file_name" value="<%=file_name%>"/>
<input type="hidden" name="file_ext" value="<%=file_ext%>"/>
<input type="hidden" name="x_unit" value="<%=x_unit%>"/>
<input type="hidden" name="y_unit" value="<%=y_unit%>"/>
<p class="level2">1차 평가 정보 입력</p>
<table class="tab2">
<col width="125">
<col width="200">
<col width="125">
<col width="200">
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
    <th class="thc"><font color = blue>물성표시번호</font></th>
    <td class="tdl">
    <%
    	if(ComUtil.isNull(mgmt_num)){
    %>
    	자동으로 선택됩니다.
    <%
    	}else{
    %>
    	<%=mgmt_num%> 
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
    <td class="tdl"><%=process %></td>
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
    <td class="tdl">원문</td>
    <td class="tdl">			
	     	<img src = "images/file_img/<%=ComUtil.getFileImgName(a_ext)%>" width="16" height="16" border="0"> 
	</td>
  </tr>
  <tr>
    <td class="tdl">판단</td>
    <td> 
    <%
    	if(step1_v.equalsIgnoreCase("pass")){
    %>
    	<input type="radio" name="1step" value="pass" checked>적절<br> 
  	  	<input type="radio" name="1step" value="reject">부적절
    <%
    	}else{
    %>
    	<input type="radio" name="1step" value="pass" >적절<br> 
  	  	<input type="radio" name="1step" value="reject" checked>부적절
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
  	  <textarea name="method" rows="3" cols="55" ><%=ComUtil.isNullStringToEmptyString(method_v)%></textarea>
  	</td>
  </tr>
  <tr>
    <td class="tdl">실험근거 제시</td>
    <td colspan="3"> 
  	  <textarea name="scient_basis" rows="3" cols="55" ><%=ComUtil.isNullStringToEmptyString(scient_basis_v)%></textarea>
  	</td>
  </tr>
  <tr>
    <td class="tdl">판단</td>
    <td> 
    <%
    	if(step2_v.equalsIgnoreCase("pass")){
    %>
    	<input type="radio" name="2step" value="pass" checked>적절<br> 
  	  	<input type="radio" name="2step" value="reject">부적절
    <%
    	}else{
    %>
    	<input type="radio" name="2step" value="pass" >적절<br> 
  	  	<input type="radio" name="2step" value="reject" checked>부적절
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
    	<input type="radio" name="3step" value="pass" checked>적절<br> 
  	  	<input type="radio" name="3step" value="reject">부적절
    <%
    	}else{
    %>
    	<input type="radio" name="3step" value="pass" >적절<br> 
  	  	<input type="radio" name="3step" value="reject" checked>부적절
    <%
    	}
    %>  
  	</td>
  </tr>
  <tr>
  	<th rowspan="4" class="thl">4.정확도 제시</th>
    <td class="tdl">방식의 한계</td>
    <td colspan="3"> 
  	  <textarea name="scient_limit" rows="3" cols="55" ><%=ComUtil.isNullStringToEmptyString(scient_limit_v)%></textarea>
  	</td>
  </tr>  
  <tr>
    <td class="tdl">실험변수 제시</td>
    <td colspan="3"> 
  	  <textarea name="primary_fact" rows="3" cols="55" ><%=ComUtil.isNullStringToEmptyString(primary_fact_v)%></textarea>
  	</td>
  </tr>
  <tr>
    <td class="tdl">불확도 제시</td>
    <td colspan="3"> 
  	  <textarea name="measurement" rows="3" cols="55" ><%=ComUtil.isNullStringToEmptyString(measurement_v)%></textarea>
  	</td>
  </tr>
  <tr>
    <td class="tdl">판단</td>
    <td> 
    <%
    	if(step4_v.equalsIgnoreCase("pass")){
    %>
    	<input type="radio" name="4step" value="pass" checked>적절<br> 
  	  	<input type="radio" name="4step" value="reject">부적절
    <%
    	}else{
    %>
    	<input type="radio" name="4step" value="pass" >적절<br> 
  	  	<input type="radio" name="4step" value="reject" checked>부적절
    <%
    	}
    %>  
  	</td>
  </tr>  
  <tr>
  	<th rowspan="2" class="thl">5.재현성</th>
    <td class="tdl">데이터분석</td>
    <td colspan="3"> 
  	  <textarea name="data_analy" rows="3" cols="55" ><%=ComUtil.isNullStringToEmptyString(data_analy_v)%></textarea>
  	</td>
  </tr>  
  <tr>
    <td class="tdl">판단</td>
    <td> 
    <%
    	if(step5_v.equalsIgnoreCase("pass")){
    %>
    	<input type="radio" name="5step" value="pass" checked>적절<br> 
  	  	<input type="radio" name="5step" value="reject">부적절
    <%
    	}else{
    %>
    	<input type="radio" name="5step" value="pass" >적절<br> 
  	  	<input type="radio" name="5step" value="reject" checked>부적절
    <%
    	}
    %>  
  	</td>
  </tr> 
  <tr>
  	<th rowspan="3" class="thl">6.일관성</th>
    <td class="tdl">간접평가</td>
    <td colspan="3"> 
  	  <textarea name="indirect" rows="3" cols="55" ><%=ComUtil.isNullStringToEmptyString(indirect_v)%></textarea>
  	</td>
  </tr>  
  <tr>
    <td class="tdl">직접평가</td>
    <td colspan="3"> 
  	  <textarea name="direct" rows="3" cols="55" ><%=ComUtil.isNullStringToEmptyString(direct_v)%></textarea>
  	</td>
  </tr>
  <tr>
    <td class="tdl">판단</td>
    <td> 
    <%
    	if(step6_v.equalsIgnoreCase("pass")){
    %>
    	<input type="radio" name="6step" value="pass" checked>적절<br> 
  	  	<input type="radio" name="6step" value="reject">부적절
    <%
    	}else{
    %>
    	<input type="radio" name="6step" value="pass" >적절<br> 
  	  	<input type="radio" name="6step" value="reject" checked>부적절
    <%
    	}
    %>  
  	</td>
  </tr>
  <tr>
  	<th rowspan="1" class="thl">1차평가 내용</th>
    <td colspan="3">
  	  <textarea name="prim_eval" rows="3" cols="70" ><%=ComUtil.isNullStringToEmptyString(prim_eval_v)%></textarea>
  	</td>
  </tr>  
  <tr>
  	<th rowspan="1" class="thl">1차평가 결과</th>
    <td colspan="3">
    <%
    	if(final_v.equalsIgnoreCase("V")){
    %>
      <input type="radio" name="final" value="validate" checked>유효한 데이터<br>
  	  <input type="radio" name="final" value="reject">기각<br>
  	  <input type="radio" name="final" value="holdover">평가유보
    <%
    	}else if(final_v.equalsIgnoreCase("R")){
    %>
      <input type="radio" name="final" value="validate" >유효한 데이터<br>
  	  <input type="radio" name="final" value="reject" checked>기각<br>
  	  <input type="radio" name="final" value="holdover">평가유보
    <%
    	}else{
	%>
      <input type="radio" name="final" value="validate" >유효한 데이터<br>
  	  <input type="radio" name="final" value="reject">기각<br>
  	  <input type="radio" name="final" value="holdover" checked>평가유보
    <%
    	}
    %>  	  
  	</td>
  </tr> 
</table>
<div class="bbtn_r">
<a href="javascript:assessInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('assessImage','','images/img_cont/assess_upper.gif',1)"><img src="images/img_cont/assess_normal.gif" name="assessImage" width="80" height="24" border="0" id="assessImage" /></a>
<a href="javascript:exitInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('exitImage','','images/img_cont/exit_upper.gif',1)"><img src="images/img_cont/exit_normal.gif" name="exitImage" width="80" height="24" border="0" id="exitImage" /></a>
</div>
</form>
</body>
</html>