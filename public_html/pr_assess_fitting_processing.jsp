<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.com.util.*"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%
	/**
	* Fitting을 처리하는 페이지
	* by Junside(J.H Park)
	*/
	//결과 리턴용 Boolean 함수
	boolean returnValue = false;	
	String returnMsg = "";
	String pr_no = request.getParameter("pr_no");
	String d_index = request.getParameter("d_index");
	String file = request.getParameter("file");
	String xax_cal = request.getParameter("xax_cal");
	String xay_cal = request.getParameter("xay_cal");
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
	var index = form2.d_index.value;
	alert(returnMsg);
	if(index == '1'){//저장
		form2.target = "_self";
		form2.action = "pr_insert_graph_data.jsp";
		form2.submit();	
	}else if(index == '2'){//다음
		form2.target = "_self";
		form2.action = "pr_draw_graph_data.jsp";
		form2.submit();
	}else if(index == '0'){ //오류
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
<jsp:useBean id="inputControl" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
Fitting 처리중..
<%	
	if(file.equalsIgnoreCase("1")){ //파일이 있으면
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
		
		returnValue = inputControl.insertGraphExcelData(multi, contextpath);
		
		pr_no = multi.getParameter("pr_no");
		xax_cal = multi.getParameter("xax_cal");
		xay_cal = multi.getParameter("xay_cal");
		
	}else if(file.equalsIgnoreCase("2")){ //파일이 없으면
		returnValue = inputControl.insertGraphDataInfo(request);
	
		pr_no = request.getParameter("pr_no");
		xax_cal = request.getParameter("xax_cal");
		xay_cal = request.getParameter("xay_cal");
	}

	if(returnValue == true){
		returnMsg = "그래프 정보 입력에 성공했습니다.";
	}else{
		returnMsg = "그래프 정보 입력에 실패했습니다.";
		d_index = "0";
	}
%>
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
<input type="hidden" name="xax_cal" value='<%=xax_cal%>'>
<input type="hidden" name="xay_cal" value='<%=xay_cal%>'>
<input type="hidden" name="d_index" value='<%=d_index%>'>
</FORM>
<script language="javascript">
	init('<%=returnMsg%>');
</script>
</body>
</html>