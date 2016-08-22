<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("euc-kr"); %>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="ctr_data" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
<jsp:useBean id="property_assess_data" class="nfri.dcpp.properties.business.Ctr_Property_Assess_Info" scope="page"/>
<%
	/**
	* 최종 평가하는 페이지
	* by Junside(J.H Park)
	*/
	String user_id = ComUtil.isNullToDashString((String)session.getAttribute("user_id"));
	if(user_id.equalsIgnoreCase("-")){
		%>
		<script language = javascript>
		alert("다시 로그인 하세요.");
		</script>
		<%
		response.sendRedirect("c_login.jsp?flag=AU");
	}else{		
	
	// 1. 등급유력 물성정보 입력을 위한 정보수집
	
	// 1-1. 새로운 등급 유력 물성 번호 생성
	
	String v_pr_no     = ComUtil.isNullToNullString(request.getParameter("v_pr_no")); //등급유력 물성번호
	
	// 1-3. 표현식,주프로세스,부프로세스, 등  전달받은 파라미터 변수에 저장.	
	String param_ic_option     = "";
	String param_projectile    = "";
	String param_projectile_id = "";
	String param_mp_option     = "";
	String param_sp_option     = "";

	String exp_param_mp_option = "";
	String exp_param_sp_option = "";
	String process_option	   = "";

	//등급유력그래프정보
	String param_xax_unit      = "";
	String param_xay_unit      = "";
	String param_tg_name       = "";
	String param_tg_name_id    = "";
	String param_tg_ionic      = "";
	String param_tg_elec       = "";
	String param_tg_fine       = "";
	String param_pd_name       = "";
	String param_pd_name_id    = "";
	String param_pd_ionic      = "";
	String param_pd_elec       = "";
	String param_pd_fine       = "";
	
	//X, Y 최소, 최대값 체크 설정
	String param_graph_xrange_v      = "";
	String param_graph_yrange_v      = "";

	//X,Y 최소, 최대값 설정
	String param_x_min_v      = ""; // X축 최소
	String param_x_max_v       = ""; // X축 최대
	String param_y_min_v       = ""; // Y축 최소
	String param_y_max_v       = ""; // Y축 최대 
	
	//X축 제목 표시
	String X_Title = "";
	//Y축 제목 표시
	String Y_Title = "";

	
	// 1-2. 등급유력 물성번호가 존재하지 않을 경우 새로운 관리번호 생성
	if(v_pr_no.equalsIgnoreCase("NULL")){ 
		v_pr_no = property_assess_data.makeVaildPropertyNum(ComUtil.getTimeNow());
		
		// 1-3. 표현식,주프로세스,부프로세스, 등  전달받은 파라미터 변수에 저장.
		param_ic_option     = request.getParameter("ic_option"); // 충돌방식
		param_projectile    = request.getParameter("projectile"); // 입사입자
		param_projectile_id = request.getParameter("projectile_id"); // 입사입자ID
		param_mp_option     = request.getParameter("mp_option"); // 주프로세스
		param_sp_option     = request.getParameter("sp_option"); // 부프로세스
		
		exp_param_mp_option = ctr_data.getExpName(param_mp_option);
		exp_param_sp_option = ctr_data.getExpName(param_sp_option);
		process_option = ComUtil.getAbbrString(exp_param_mp_option, 10) + ", " + ComUtil.getAbbrString(exp_param_sp_option, 10);
		
		//등급유력그래프정보
		param_xax_unit      = request.getParameter("xax_unit"); // X 단위
		param_xay_unit      = request.getParameter("xay_unit"); // Y 단위
		param_tg_name       = request.getParameter("tg_name"); // 표적입자
		param_tg_name_id    = request.getParameter("tg_name_id"); // 표적입자 ID
		param_tg_ionic      = ComUtil.isNullToNullString(request.getParameter("tg_ionic")); // 표적입자 이온화
		param_tg_elec       = ComUtil.isNullToNullString(request.getParameter("tg_elec")); // 표적입자 전자배치
		param_tg_fine       = ComUtil.isNullToNullString(request.getParameter("tg_fine")); // 표적입자 미세구조
		param_pd_name       = ComUtil.isNullToNullString(request.getParameter("pd_name")); // 생성입자 
		param_pd_name_id    = ComUtil.isNullToNullString(request.getParameter("pd_name_id")); // 생성입자 ID 
		param_pd_ionic      = ComUtil.isNullToNullString(request.getParameter("pd_ionic")); // 생성입자 이온화
		param_pd_elec       = ComUtil.isNullToNullString(request.getParameter("pd_elec")); // 생성입자 전자배치
		param_pd_fine       = ComUtil.isNullToNullString(request.getParameter("pd_fine")); // 생성입자 미세구조
		
		//X, Y 최소, 최대값 체크 설정
		param_graph_xrange_v      = ComUtil.isNullToNullString(request.getParameter("graph_xrange_value"));
		param_graph_yrange_v      = ComUtil.isNullToNullString(request.getParameter("graph_yrange_value"));

		//X,Y 최소, 최대값 설정
		param_x_min_v      = ComUtil.isNullToNullString(request.getParameter("graph_xrange_min_value")); // X축 최소
		param_x_max_v       = ComUtil.isNullToNullString(request.getParameter("graph_xrange_max_value")); // X축 최대
		param_y_min_v       = ComUtil.isNullToNullString(request.getParameter("graph_yrange_min_value")); // Y축 최소
		param_y_max_v       = ComUtil.isNullToNullString(request.getParameter("graph_yrange_max_value")); // Y축 최대 

		//표현식 만들기
		//String pd_expression = property_assess_data.makeAssessExpression(request);
		
		//X축 제목 표시
		X_Title = ctr_data.getExpName(param_xax_unit);
		//Y축 제목 표시
		Y_Title = ctr_data.getExpName(param_xay_unit);
		
		//String whereCondtion = property_assess_data.getConditionInfo(request);
		
		//1-4.입력할 물성정보들을 가져옴
		//Vector pd_info = property_assess_data.searchAssessPropertyListInfo(request);
		
		//리스트에 포함된 물성정보를 토대로 그래프 데이터를 가져옴	
		//Vector gd_info = graph_data.selectGraphBasicData(pr_no);
		//int dt_count = pd_info.size();//dt_count = 0;//dt_info.size();
		
		//String block_state = "T";
		
		//1. 기존 Temp 데이터 지워버리기
		//boolean del_value = false;
		//del_value = property_assess_data.deleteFittingTempData(user_id);		
		//System.out.println("Fitting Temp Delete : "+del_value);
		//if(del_value == true){ //2. Temp Data가 제대로 지워졌으면..
		//}else{
		//	System.out.println("Temp Data Delete Fail!!!");
		//}
		
		ServletContext context = getServletContext();
		
		boolean made_OK = property_assess_data.makeAssessDataInfo(request, context, v_pr_no, user_id);
		
	}
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

function assessInfo(v_pr_no){ //평가
	if(confirmMsg('1') == true){
		var form2 = document.form1;
		//8. 원문 업로드 파일 첨부 여부 체크
		if(form2.pl_excel_file.value.length < 1){
			alert("생성파일(Excel)을 추가하세요!");
			form2.pl_excel_file.focus();
			return;
		}			
		form2.action = "pr_assess_make_inf_grd_data_check.jsp?v_pr_no="+v_pr_no;
		form2.submit();
	}
}

function exitInfo(){ //종료
	if(confirmMsg('2') == true){
		var form2 = document.form1;	
		form2.action = "pr_common_assess_list.jsp";
		form2.submit();
	}
}

function downloadText(v_pro_no){ //파일 다운로드
	//window.open("pr_text_download_file.jsp?v_pro_no="+v_pro_no,"txt_down_info","width=1,height=1,toolbar=no");
	//return;
	//현재 페이지가 파일 업로드를 위한 multipart 이기 때문에 아래와 같은 방법으로는 파라미터가 제대로 전송이 안되어 위와 같은 방법을 이용함
	var form2 = document.form1;
	//form에 값을 넘기기 위하여 폼객체에 페이지의 객체값을 넣는다.
	//alert(v_pro_no);
	//form2.v_pro_no.value = v_pro_no;
	//alert(form2.v_pro_no.value);
	form2.target = "_self";
	form2.action = "pr_text_download_file.jsp?v_pro_no="+v_pro_no;
	form2.submit();
}

function confirmMsg(number){
	var num = number;
	switch(num) {
		case '1': //평가하기
			var result = confirm("입력한 물성데이터를 등급유력데이터로 등록하시겠습니까?\n입력하신 내용을 한번 더 확인하시려면 취소를 누르세요.\n확인을 누르시면 입력이 완료됩니다.");
			return result;
			break;
		case '2': //종료
			var result = confirm("평가를 종료하시겠습니까?\n주의하세요");
			return result;
			break;
		default :		
			break;
	}		
}

</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<body>
<form name="form1" method="POST" enctype="multipart/form-data">
<input type="hidden" name="user_id" value="<%=user_id%>"/>
<input type="hidden" name="ic_option"     value="<%=param_ic_option%>"/>
<input type="hidden" name="projectile"    value="<%=param_projectile%>"/>
<input type="hidden" name="projectile_id" value="<%=param_projectile_id%>"/>
<input type="hidden" name="mp_option"     value="<%=param_mp_option%>"/>
<input type="hidden" name="sp_option"     value="<%=param_sp_option%>"/>
<input type="hidden" name="xax_unit"      value="<%=param_xax_unit%>"/>
<input type="hidden" name="xay_unit"      value="<%=param_xay_unit%>"/>
<input type="hidden" name="tg_name"       value="<%=param_tg_name%>"/>
<input type="hidden" name="tg_name_id"    value="<%=param_tg_name_id%>"/>
<input type="hidden" name="tg_ionic"      value="<%=param_tg_ionic%>"/>
<input type="hidden" name="tg_elec"       value="<%=param_tg_elec%>"/>
<input type="hidden" name="tg_fine"       value="<%=param_tg_fine%>"/>
<input type="hidden" name="pd_name"       value="<%=param_pd_name%>"/>
<input type="hidden" name="pd_name_id"    value="<%=param_pd_name_id%>"/>
<input type="hidden" name="pd_ionic"      value="<%=param_pd_ionic%>"/>
<input type="hidden" name="pd_elec"       value="<%=param_pd_elec%>"/>
<input type="hidden" name="pd_fine"       value="<%=param_pd_fine%>"/>
<input type="hidden" name="v_pr_no"       value="<%=v_pr_no%>"/>
<p class="level2">데이터 생성 결과</p>
<table class="tab2">
	<col width="30%">
	<col width="70%">
	<col width="30%">
	<col width="70%">
	<tr>
		<th class="thla">데이터파일</th>
		<td class="tdl">
			<a href="javascript:void(0);"  onClick="downloadText('<%=v_pr_no%>'); return false;">
	     	<img src = "images/file_img/<%=ComUtil.getFileImgName("txt")%>" width="16" height="16" border="0"> 
	     	</a>
		</td>
	</tr>
	<tr>
		<th class="thla">생성파일(xls 만 가능함)</th>
		<td class="tdl"><input type='file' name='pl_excel_file' size='40' value=''></td>
	</tr>
</table>
<div class="bbtn_r">
<a href="javascript:assessInfo('<%=v_pr_no%>')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('assessImage','','images/img_cont/assess_upper.gif',1)"><img src="images/img_cont/assess_normal.gif" name="assessImage" width="80" height="24" border="0" id="assessImage" /></a>
<a href="javascript:exitInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('exitImage','','images/img_cont/exit_upper.gif',1)"><img src="images/img_cont/exit_normal.gif" name="exitImage" width="80" height="24" border="0" id="exitImage" /></a>
</div>
</form>
</body>
</html>
<%}%>