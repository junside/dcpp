<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="nfri.dcpp.properties.business.Ctr_Part_Info_Process"%>
<%@ page import="java.util.Enumeration"%>
<%
	/**
	* �⺻ ���� ������ �׷��� �������� ������ �����͸� DB�� �Է��ϴ� JSP ������
	* by Junside(J.H Park)
	*/
	//��� ���Ͽ� Boolean �Լ�
	boolean returnValue = false;	
	String returnMsg = "";
	String pr_no = request.getParameter("pr_no");//"B201000001";//
	String dt_no = request.getParameter("dt_no");//"1";//
	String d_index = request.getParameter("d_index");
	String dt_count = request.getParameter("dt_count");
	String x_cal = request.getParameter("xax_cal");
	String y_cal = request.getParameter("xay_cal");
	String i_nowPage = request.getParameter("i_nowPage");
	String i_nowBlock = request.getParameter("i_nowBlock");
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
	form2.action = "pr_insert_graph_data.jsp";
	form2.submit();
	
	
	//parent.list.location.replace('pr_insert_graph_data.jsp');
	//history.back();	
	//opener.reload();
	//history.back();
	//function fn_success(pr_no, d_index) {
	//function fn_success() {
	//var f = document.form1;
	//�θ�â �ٽ� ���ε�
	//opener.location.replace("pr_insert_graph_data.jsp?pr_no="+pr_no+"&artcl_no="+d_index);
	//opener.document.location.href=opener.document.location;
	//opener.location.reload();
	//opener.parent.location.reload(); 
	//opener.location.reload(); 
	//opener.parent.location.reload(); 
	
	
	//â �ݱ�
	//self.close();

	//var form2 = opener.document.form1;
 	//form2.target = "_self";
	//form2.action = "pr_insert_graph_data.jsp?pr_no="+pr_no+"&d_index="+d_index;
	//form2.submit();
	//window.close();
}
//}

function fn_fail(){
	//alert(returnMsg);
	//self.close();
	history.back();	
}
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
<input type="hidden" name="dt_no" value='<%=dt_no%>'>
<input type="hidden" name="xax_cal" value="<%=x_cal%>"/>
<input type="hidden" name="xay_cal" value="<%=y_cal%>"/>
<input type="hidden" name="dt_count" value='<%=dt_count%>'>
<input type="hidden" name="d_index" value='<%=d_index%>'>
<input type="hidden" name="nowPage" value="<%=i_nowPage%>"/>
<input type="hidden" name="nowBlock" value="<%=i_nowBlock%>"/>
<jsp:useBean id="updateControl" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<%	

	returnValue = updateControl.updateGraphDataInfo(request);
	
	if(!returnValue == false){
		returnMsg = "�׷������� ������ �����߽��ϴ�.";
	}else{
		returnMsg = "�׷������� ������ �����߽��ϴ�.";
	}
%>

<%	
		returnValue = updateControl.updateGraphDataInfo(request);
		if(returnValue == true){
		%>
			<script language="javascript">
			alert('�׷��������� ������ �����߽��ϴ�.');
			//fn_success(pr_no, d_index);
			fn_success();
			</script>
		<%
		}else{
		%>
			<script language="javascript">
			alert('�׷��������� ������ �����߽��ϴ�.');
			fn_fail();
			</script>
		<%
		}	
%>
</FORM>
</body>
</html>