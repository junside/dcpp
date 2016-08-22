<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.com.util.*"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
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
	* 그래프 데이터 정보를 실제 DB에 입력하는 JSP 페이지
	* by Junside(J.H Park)
	*/
	//결과 리턴용 Boolean 함수
	boolean returnValue = false;	
	String returnMsg = "";
	String file = request.getParameter("file");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function init(returnMsg) {
	var form2 = document.form1;
	form2.target = "_self";
	form2.action = "pr_insert_inchikey_formula.jsp";
	form2.submit();		
}
</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<jsp:useBean id="inputControl" class="nfri.dcpp.properties.business.Ctr_XSAMS_Process" scope="page"/>
<%	
	if(file.equalsIgnoreCase("1")){ //파일이 있으면
		//request.getSession().getServletContext().getRealPath("/")
		ServletContext context = request.getSession().getServletContext();	
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
		
		returnValue = inputControl.insertInchikeyExcelData(multi, contextpath);
		
	}/* else if(file.equalsIgnoreCase("2")){ //파일이 없으면
		returnValue = inputControl.insertGraphDataInfo(request);
	
		pr_no = request.getParameter("pr_no");
		xax_cal = request.getParameter("xax_cal");
		xay_cal = request.getParameter("xay_cal");
	} */

	if(returnValue == true){
		returnMsg = "그래프 정보 입력에 성공했습니다.";
	}else{
		returnMsg = "그래프 정보 입력에 실패했습니다.";		
	}
%>

</FORM>
<script language="javascript">
	init('<%=returnMsg%>');
</script>
</body>
</html>