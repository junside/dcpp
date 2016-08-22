<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<%@page import="nfri.dcpp.com.util.ComVar"%>
<%
	//세션 정보를 확인해서 로그인 정보가 없으면 로그인 창으로 이동
	String user_id = ComUtil.isNullToNullString((String)session.getAttribute("user_id"));
	String auth = ComUtil.isNullToNullString((String)session.getAttribute("auth"));
	//if(user_id == null || auth.equalsIgnoreCase("AU")){
		//System.out.println("i_user_id : " + user_id);
		//System.out.println("i_auth : " + auth);
		//response.sendRedirect("c_login.jsp?flag="+auth);
	//}
	if(user_id.equalsIgnoreCase(ComVar.STRING_NULL_B)){
		response.sendRedirect("c_login.jsp?flag=IU");			
	}else{
		if(!auth.equalsIgnoreCase("AIU") && !auth.equalsIgnoreCase("IAU") && !auth.equalsIgnoreCase("IU")){
			response.sendRedirect("c_login.jsp?flag=IU");
		}
	}
%>
<%
	/**
	* 물성 정보를 평가한 데이터를 DB에 입력하는 JSP 페이지
	* by Junside(J.H Park)
	*/
	//결과 리턴용 Boolean 함수
	boolean returnValue = false;	
	String returnMsg = "";
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function init(returnMsg, returnValue) {
	var form2 = document.form1;	
	alert(returnMsg);
	if(returnValue == 'true'){
		form2.target = "_self";
		form2.action = "pr_common_list.jsp";
		form2.submit();
	}else if(returnValue == 'false'){
		history.back();
	}
}
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<body>
<form name="form1" action="" method="POST">
<jsp:useBean id="inputControl" class="nfri.dcpp.properties.business.Ctr_Property_Assess_Info" scope="page"/>
<jsp:useBean id="property_info" class="nfri.dcpp.properties.business.Ctr_Property_Info_Process" scope="page"/>
<%	
	//String user_id = (String)session.getAttribute("user_id");
	Properties_Assess_Info info = new Properties_Assess_Info();
	String pr_no = request.getParameter("pr_no");
	//System.out.println("============== > pr_no : " + pr_no);
	//Ctr_Graph_Info_Process data_info = new Ctr_Graph_Info_Process();
	//기본 정보 가져오기
	Properties_Basic_Info spec_info = property_info.selectViewPropertySpecInfo(pr_no);
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
	
	String x_unit = request.getParameter("x_unit");
	String y_unit = request.getParameter("y_unit");
	//단위 정보 가져오기
	//Graph_Basic_Info basic_info = data_info.selectGraphBasicInfo(pr_no);
	
	//String x_unit = basic_info.getPL_BGI_X_AX_UNIT();
	//String y_unit = basic_info.getPL_BGI_Y_AX_UNIT();
	
	//물성 정보 번호
	info.setPL_BI_DATA_NUM(pr_no);
	//데이터 분류값
	info.setPL_PEI_DATA_BRANCH(db_value);
	//프로세스값
	info.setPL_PEI_PROCESS(process);
	//검증구분값
	info.setPL_PEI_EXP_THE_REC(exp_value);
	//X단위값
	info.setPL_PEI_X_UNIT(x_unit);
	//Y단위값
	info.setPL_PEI_Y_UNIT(y_unit);
	//표현식
	info.setPL_PEI_EXPRESS(expression);
	
	//측정 대상
	info.setPL_PEI_1STEP(ComUtil.convertKorean(request.getParameter("1step")));
	//측정 방법
	info.setPL_PEI_2STEP(ComUtil.convertKorean(request.getParameter("2step")));
	//소급성
	info.setPL_PEI_3STEP(ComUtil.convertKorean(request.getParameter("3step")));
	//정확도
	info.setPL_PEI_4STEP(ComUtil.convertKorean(request.getParameter("4step")));
	//재현성
	info.setPL_PEI_5STEP(ComUtil.convertKorean(request.getParameter("5step")));
	//일관성
	info.setPL_PEI_6STEP(ComUtil.convertKorean(request.getParameter("6step")));		
	//측정방식
	info.setPL_PEI_METHOD(ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("method"))));
	//근거
	info.setPL_PEI_SCIENT_BASIS(ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("scient_basis"))));
	//한계
	info.setPL_PEI_SCIENT_LIMIT(ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("scient_limit"))));
	//변수
	info.setPL_PEI_PRIMARY_FACT(ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("primary_fact"))));
	//분석
	info.setPL_PEI_DATA_ANALY(ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("data_analy"))));
	//측정값
	info.setPL_PEI_MEASUREMENT(ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("measurement"))));
	//직접 확인
	info.setPL_PEI_DIRECT(ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("direct"))));
	//간접확인
	info.setPL_PEI_INDIRECT(ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("indirect"))));
	//1차 평가결과 내용
	info.setPL_PEI_PRIM_EVAL(ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("prim_eval"))));
	//1차 평가결과 플래그
	String final_flag = request.getParameter("final");
	if(final_flag.equalsIgnoreCase("validate")){ //유효한 데이터일 경우
		info.setPL_PEI_FINAL_FLAG("V");
	}else if(final_flag.equalsIgnoreCase("reject")){ //기각 데이터일 경우
		info.setPL_PEI_FINAL_FLAG("R");
	}else{//평가 유보일 경우
		info.setPL_PEI_FINAL_FLAG("H");
	}
	
	//평가자
	info.setPL_PEI_ASSESS_USER(user_id);
	info.setPL_PEI_ASSESS_DATE(ComUtil.getTimeNow());
	
	
	returnValue = inputControl.insertPropertyAssessInfo(info);
	if(returnValue == true){
		returnMsg = "평가정보 입력에 성공했습니다.";
	}else{
		returnMsg = "평가정보 입력에 실패했습니다.";
	}
%>
</FORM>
<script language="javascript">
	init('<%=returnMsg%>','<%=returnValue%>');
</script>
</body>
</html>