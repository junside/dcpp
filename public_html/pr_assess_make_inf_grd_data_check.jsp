<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.com.util.*"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="nfri.dcpp.com.util.ComVar"%>
<jsp:useBean id="inputControl" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<jsp:useBean id="property_assess_data" class="nfri.dcpp.properties.business.Ctr_Property_Assess_Info" scope="page"/>
<%
String user_id = ComUtil.isNullToDashString((String)session.getAttribute("user_id"));
if(user_id.equalsIgnoreCase("-")){
	%>
	<script language = javascript>
	alert("다시 로그인 하세요.");
	</script>
	<%
	response.sendRedirect("c_login.jsp?flag=AU");
}else{
%>
<%
	/**
	* 그래프 데이터 정보를 실제 DB에 입력하는 JSP 페이지
	* by Junside(J.H Park)
	*/
	//결과 리턴용 Boolean 함수
	boolean returnValue = false;	
	String returnMsg = "";
//	String v_pr_no = request.getParameter("v_pr_no");
	String file = request.getParameter("file");
	
	ServletContext context = getServletContext();	
	//Web Application 저장경로  지정
	String realFolder = "";
	//Web Application 절대경로
	String contextpath = context.getRealPath("/");
	//파일이 업로드 되는 경로 지정
	String uploadFolder = "data_file";		
	//인코딩 타입 지정
	String encType ="euc-kr";
	//최대 업로드될 파일 크기 지정 = 10MB
	int maxSize = 10*1024*1024;

	//현재 페이지의 Web Application 상의 절대 경로
	realFolder = context.getRealPath(uploadFolder);	
	MultipartRequest multi = null;
	
	/*
	* 전송을 담당할 컴포넌트 생성
	* 전송할 파일명을 가지고 있는 객체. 서버상의 절대 경로, 최대 업로드될 파일 크기
	* 문자코드, 기본 보안 적용
	*/ 
	multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
	String v_pr_no = multi.getParameter("v_pr_no");
	String xax_cal = multi.getParameter("xax_cal");
	String xay_cal = multi.getParameter("xay_cal");
	
	String param_ic_option     = multi.getParameter("ic_option"); // 충돌방식
	String param_projectile    = multi.getParameter("projectile"); // 입사입자
	String param_projectile_id = multi.getParameter("projectile_id"); // 입사입자ID
	String param_mp_option     = multi.getParameter("mp_option"); // 주프로세스
	String param_sp_option     = multi.getParameter("sp_option"); // 부프로세스

	String param_xax_unit      = multi.getParameter("xax_unit"); // X 단위
	String param_xay_unit      = multi.getParameter("xay_unit"); // Y 단위
	String param_tg_name       = multi.getParameter("tg_name"); // 표적입자
	String param_tg_name_id    = multi.getParameter("tg_name_id"); // 표적입자 ID
	String param_tg_ionic      = ComUtil.isNullToNullString(multi.getParameter("tg_ionic")); // 표적입자 이온화
	String param_tg_elec       = ComUtil.isNullToNullString(multi.getParameter("tg_elec")); // 표적입자 전자배치
	String param_tg_fine       = ComUtil.isNullToNullString(multi.getParameter("tg_fine")); // 표적입자 미세구조
	String param_pd_name       = ComUtil.isNullToNullString(multi.getParameter("pd_name")); // 생성입자 
	String param_pd_name_id    = ComUtil.isNullToNullString(multi.getParameter("pd_name_id")); // 생성입자 ID 
	String param_pd_ionic      = ComUtil.isNullToNullString(multi.getParameter("pd_ionic")); // 생성입자 이온화
	String param_pd_elec       = ComUtil.isNullToNullString(multi.getParameter("pd_elec")); // 생성입자 전자배치
	String param_pd_fine       = ComUtil.isNullToNullString(multi.getParameter("pd_fine")); // 생성입자 미세구조

	//X,Y 최소, 최대값 설정
	String param_x_min_v      = ComUtil.isNullToDashString(multi.getParameter("graph_xrange_min_value")); // X축 최소
	String param_x_max_v       = ComUtil.isNullToDashString(multi.getParameter("graph_xrange_max_value")); // X축 최대
	String param_y_min_v       = ComUtil.isNullToDashString(multi.getParameter("graph_yrange_min_value")); // Y축 최소
	String param_y_max_v       = ComUtil.isNullToDashString(multi.getParameter("graph_yrange_max_value")); // Y축 최대
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function init(returnMsg, returnValue) {
	var form2 = document.form1;
	alert(returnMsg);
	if(returnValue == 'true'){//다음단계
		form2.target = "_self";
		form2.action = "pr_assess_final_inf_grd_data.jsp";
		form2.submit();
	}else{ //오류
		history.back();
	}
	
}
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<%		
	returnValue = inputControl.inserFinalGraphExcelData(multi, contextpath);

	if(returnValue == true){
		returnMsg = "그래프 정보 입력에 성공했습니다.";		
		returnValue = property_assess_data.updateStateFinalAssess(v_pr_no,"AI");
		
	}else{
		returnMsg = "그래프 정보 입력에 실패했습니다.";
	}
%>
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
</FORM>
<script language="javascript">
	init('<%=returnMsg%>', '<%=returnValue%>');
</script>
</body>
</html>
<%}%>