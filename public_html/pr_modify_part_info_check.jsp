<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="nfri.dcpp.properties.business.Ctr_Part_Info_Process"%>
<%@ page import="nfri.dcpp.com.util.ComUtil"%>
<%@ page import="java.util.Enumeration"%>
<%@page import="nfri.dcpp.com.util.ComVar"%>

<%
	/**
	* 기본 물성 정보를 실제 DB에 입력하는 JSP 페이지
	* by Junside(J.H Park)
	*/
	//결과 리턴용 Boolean 함수
	boolean returnValue = false;	
	String returnMsg = "";
	String seq = request.getParameter("seq");
	
	String part_no = request.getParameter("part_no"); //입자 번호
	String part_symbol = request.getParameter("part_symbol"); //입자 기호
	String part_name = request.getParameter("part_name"); //입자 이름
	String part_mass = request.getParameter("part_mass"); //입자 질량
	String part_type = request.getParameter("part_type"); //입자 타입
	String part_amnum = request.getParameter("part_amnum"); //입자 원소 번호
	String part_amcount = request.getParameter("part_amcount"); //입자 개수
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function init(returnMsg, part_no) {
	var form2 = document.form1;

	alert(returnMsg);

	//history.back();
	form2.target = "_self";
	form2.action = "pr_modify_part_info_update.jsp?part_no="+part_no;
	form2.submit();
	//self.close();
	//form2.target = "_self";
	//form2.action = "pr_modify_target_part_list.jsp";
	//form2.submit();
	
	//if(index == '1'){//저장
	//	history.back();
	//}else if(index == '2'){//다음
	//	form2.target = "_self";
	//	form2.action = "pr_modify_target_part_info_update.jsp?pr_no="+pr_no;
	//	form2.submit();
	//}else if(index == '3'){//종료
	//	form2.target = "_self";
	//	form2.action = "pr_modify_target_part_list.jsp";
	//	form2.submit();	
	//}
	
}

</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">

<jsp:useBean id="partControl" class="nfri.dcpp.properties.business.Ctr_Part_Info_Process" scope="page"/>
<%	
	if("0".equalsIgnoreCase(seq)){ //입자 기호
		//System.out.println("수정");
		returnValue = partControl.updatePartInfo(part_no, seq, part_symbol);
		
		if(returnValue == true){
			returnMsg = "원자/분자 기호 정보 변경에 성공했습니다.";
			//pr_no = returnValue;
		}else{
			returnMsg = "원자/분자 기호 정보 변경에 실패했습니다.";
		}
	}else if("1".equalsIgnoreCase(seq)){ //입자 이름
		//System.out.println("입력");
		returnValue = partControl.updatePartInfo(part_no, seq, part_name);
		
		if(returnValue == true){
			returnMsg = "원자/분자 이름 변경에 성공했습니다.";
			//pr_no = returnValue;
		}else{
			returnMsg = "원자/분자 이름 변경에 실패했습니다.";
		}
	}else if("2".equalsIgnoreCase(seq)){ //입자 질량
		//System.out.println("입력");
		returnValue = partControl.updatePartInfo(part_no, seq, part_mass);
		
		if(returnValue == true){
			returnMsg = "원자/분자 질량 변경에 성공했습니다.";
			//pr_no = returnValue;
		}else{
			returnMsg = "원자/분자 질량 변경에 실패했습니다.";
		}
	}else if("3".equalsIgnoreCase(seq)){ //입자 유형
		//System.out.println("입력");
		returnValue = partControl.updatePartInfo(part_no, seq, part_type);
		
		if(returnValue == true){
			returnMsg = "원자/분자 유형 변경에 성공했습니다.";
			//pr_no = returnValue;
		}else{
			returnMsg = "원자/분자 유형 변경에 실패했습니다.";
		}
	}else if("4".equalsIgnoreCase(seq)){ //입자 원자번호
		//System.out.println("입력");
		returnValue = partControl.updatePartInfo(part_no, seq, part_amnum);
		
		if(returnValue == true){
			returnMsg = "원자번호 변경에 성공했습니다.";
			//pr_no = returnValue;
		}else{
			returnMsg = "원자번호 변경에 실패했습니다.";
		}
	}else if("5".equalsIgnoreCase(seq)){ //입자 개수
		//System.out.println("입력");
		returnValue = partControl.updatePartInfo(part_no, seq, part_amcount);
		
		if(returnValue == true){
			returnMsg = "원자/분자 개수 변경에 성공했습니다.";
			//pr_no = returnValue;
		}else{
			returnMsg = "원자/분자 개수 변경에 실패했습니다.";
		}
	}
	//returnValue = partControl.updatePartInfo();

	
%>
</FORM>
<script language="javascript">
	init('<%=returnMsg%>', '<%=part_no%>');
</script>
</body>
</html>