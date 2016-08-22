<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="nfri.dcpp.properties.business.Ctr_Part_Info_Process"%>
<%@ page import="java.util.Enumeration"%>
<%
	/**
	* 기본 물성 정보의 그래프 데이터의 수정된 데이터를 DB에 입력하는 JSP 페이지
	* by Junside(J.H Park)
	*/
	//결과 리턴용 Boolean 함수
	boolean returnValue = false;	
	String returnMsg = "";
	String pr_no = request.getParameter("pr_no");//"B201000001";//
	String gotoindex = request.getParameter("gotoindex");
	String db_option = request.getParameter("db_option");
	String mp_option = request.getParameter("mp_option");	
	String sp_option = request.getParameter("sp_option");
	String ic_option = request.getParameter("ic_option");
	String et_option = request.getParameter("et_option");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function fn_success() {
	var form2 = document.form1;
	form2.target = "_self";
	form2.action = "pr_delete_property_info.jsp";
	form2.submit();
}

function fn_success_number(){
	window.close(); //창닫고,
	opener.location.href="pr_modify_graph_number.jsp"; //부모창 리프레쉬하기.
}

function fn_fail(){
	history.back();	
}
</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
<jsp:useBean id="graphControl" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<jsp:useBean id="propertyControl" class="nfri.dcpp.properties.business.Ctr_Property_Info_Process" scope="page"/>

<%	
	if(gotoindex.equalsIgnoreCase("pr_del")){//물성정보 삭제
		//물성정보 삭제
		returnValue = propertyControl.deletePropertyInfo(pr_no);
		if(returnValue == true){
		%>
			<script language="javascript">
			alert('물성 정보 삭제에 성공했습니다.');
			//fn_success(pr_no, d_index);
			fn_success();
			</script>
		<%
		}else{
		%>
			<script language="javascript">
			alert('삭제에 실패했습니다. 관리자에게 문의하세요');
			fn_fail();
			</script>
		<%
		}		
	}else if(gotoindex.equalsIgnoreCase("num_del")){//수치데이터 삭제
		//수치정보 삭제
		returnValue = graphControl.deleteGraphData(pr_no);
	
		if(returnValue == true){
			returnValue = propertyControl.modifyPropertyFlag("I", pr_no);
			if(returnValue == true){
		%>
			<script language="javascript">
			alert('수치 정보 삭제에 성공했습니다.');
			fn_success();
			</script>
		<%
			}else{
			%>
				<script language="javascript">
				alert('삭제에 실패했습니다. 관리자에게 문의하세요');
				fn_fail();
				</script>
			<%
			}
		}else{
		%>
			<script language="javascript">
			alert('삭제에 실패했습니다. 관리자에게 문의하세요');
			fn_fail();
			</script>
		<%
		}
		
	}else if(gotoindex.equalsIgnoreCase("db_modify")){//데이터분류 값 변경
		returnValue = propertyControl.modifyPropertyDB(db_option, pr_no);
		if(returnValue == true){
		%>
			<script language="javascript">
			alert('데이터분류 값 변경에 성공했습니다.');
			//fn_success(pr_no, d_index);
			fn_success();
			</script>
		<%
		}else{
		%>
			<script language="javascript">
			alert('데이터분류 값 변경에 실패했습니다. 관리자에게 문의하세요');
			fn_fail();
			</script>
		<%
		}
	}else if(gotoindex.equalsIgnoreCase("mp_modify")){//주프로세스 값 변경
		returnValue = propertyControl.modifyPropertyMP(mp_option, pr_no);
		if(returnValue == true){
		%>
			<script language="javascript">
			alert('주프로세스 변경에 성공했습니다.');
			//fn_success(pr_no, d_index);
			fn_success();
			</script>
		<%
		}else{
		%>
			<script language="javascript">
			alert('주프로세스 변경에 실패했습니다. 관리자에게 문의하세요');
			fn_fail();
			</script>
		<%
		}
	}else if(gotoindex.equalsIgnoreCase("sp_modify")){//부프로세스 값 변경
		returnValue = propertyControl.modifyPropertySP(sp_option, pr_no);
		if(returnValue == true){
		%>
			<script language="javascript">
			alert('부프로세스 변경에 성공했습니다.');
			//fn_success(pr_no, d_index);
			fn_success();
			</script>
		<%
		}else{
		%>
			<script language="javascript">
			alert('부프로세스 변경에 실패했습니다. 관리자에게 문의하세요');
			fn_fail();
			</script>
		<%
		}
	}else if(gotoindex.equalsIgnoreCase("ic_modify")){//충돌방식 값 변경
		returnValue = propertyControl.modifyPropertyIC(ic_option, pr_no);
		if(returnValue == true){
		%>
			<script language="javascript">
			alert('충돌방식 변경에 성공했습니다.');
			//fn_success(pr_no, d_index);
			fn_success();
			</script>
		<%
		}else{
		%>
			<script language="javascript">
			alert('충돌방식 변경에 실패했습니다. 관리자에게 문의하세요');
			fn_fail();
			</script>
		<%
		}
	}else if(gotoindex.equalsIgnoreCase("et_modify")){//검증 구분 값 변경
		returnValue = propertyControl.modifyPropertyEXP_THE_REC(et_option, pr_no);
		if(returnValue == true){
		%>
			<script language="javascript">
			alert('검증구분 변경에 성공했습니다.');
			//fn_success(pr_no, d_index);
			fn_success();
			</script>
		<%
		}else{
		%>
			<script language="javascript">
			alert('검증구분 변경에 실패했습니다. 관리자에게 문의하세요');
			fn_fail();
			</script>
		<%
		}
	}else if(gotoindex.equalsIgnoreCase("num_mod")){//수치데이터 소수점 수정하기
		//수치정보 수정
		returnValue = graphControl.modifyGraphPointData(pr_no);
	

			if(returnValue == true){
		%>
			<script language="javascript">
			alert('수치 소수점 수정에 성공했습니다.');
			fn_success_number();
			</script>
		<%
			}else{
			%>
				<script language="javascript">
				alert('수정에 실패했습니다. 관리자에게 문의하세요');
				fn_fail();
				</script>
			<%
			}
		}else{
		%>
			<script language="javascript">
			alert('수정에 실패했습니다. 관리자에게 문의하세요');
			fn_fail();
			</script>
		<%
		}
		
	
%>
</FORM>
</body>
</html>