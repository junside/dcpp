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
	window.close(); //â�ݰ�,
	opener.location.href="pr_modify_graph_number.jsp"; //�θ�â ���������ϱ�.
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
	if(gotoindex.equalsIgnoreCase("pr_del")){//�������� ����
		//�������� ����
		returnValue = propertyControl.deletePropertyInfo(pr_no);
		if(returnValue == true){
		%>
			<script language="javascript">
			alert('���� ���� ������ �����߽��ϴ�.');
			//fn_success(pr_no, d_index);
			fn_success();
			</script>
		<%
		}else{
		%>
			<script language="javascript">
			alert('������ �����߽��ϴ�. �����ڿ��� �����ϼ���');
			fn_fail();
			</script>
		<%
		}		
	}else if(gotoindex.equalsIgnoreCase("num_del")){//��ġ������ ����
		//��ġ���� ����
		returnValue = graphControl.deleteGraphData(pr_no);
	
		if(returnValue == true){
			returnValue = propertyControl.modifyPropertyFlag("I", pr_no);
			if(returnValue == true){
		%>
			<script language="javascript">
			alert('��ġ ���� ������ �����߽��ϴ�.');
			fn_success();
			</script>
		<%
			}else{
			%>
				<script language="javascript">
				alert('������ �����߽��ϴ�. �����ڿ��� �����ϼ���');
				fn_fail();
				</script>
			<%
			}
		}else{
		%>
			<script language="javascript">
			alert('������ �����߽��ϴ�. �����ڿ��� �����ϼ���');
			fn_fail();
			</script>
		<%
		}
		
	}else if(gotoindex.equalsIgnoreCase("db_modify")){//�����ͺз� �� ����
		returnValue = propertyControl.modifyPropertyDB(db_option, pr_no);
		if(returnValue == true){
		%>
			<script language="javascript">
			alert('�����ͺз� �� ���濡 �����߽��ϴ�.');
			//fn_success(pr_no, d_index);
			fn_success();
			</script>
		<%
		}else{
		%>
			<script language="javascript">
			alert('�����ͺз� �� ���濡 �����߽��ϴ�. �����ڿ��� �����ϼ���');
			fn_fail();
			</script>
		<%
		}
	}else if(gotoindex.equalsIgnoreCase("mp_modify")){//�����μ��� �� ����
		returnValue = propertyControl.modifyPropertyMP(mp_option, pr_no);
		if(returnValue == true){
		%>
			<script language="javascript">
			alert('�����μ��� ���濡 �����߽��ϴ�.');
			//fn_success(pr_no, d_index);
			fn_success();
			</script>
		<%
		}else{
		%>
			<script language="javascript">
			alert('�����μ��� ���濡 �����߽��ϴ�. �����ڿ��� �����ϼ���');
			fn_fail();
			</script>
		<%
		}
	}else if(gotoindex.equalsIgnoreCase("sp_modify")){//�����μ��� �� ����
		returnValue = propertyControl.modifyPropertySP(sp_option, pr_no);
		if(returnValue == true){
		%>
			<script language="javascript">
			alert('�����μ��� ���濡 �����߽��ϴ�.');
			//fn_success(pr_no, d_index);
			fn_success();
			</script>
		<%
		}else{
		%>
			<script language="javascript">
			alert('�����μ��� ���濡 �����߽��ϴ�. �����ڿ��� �����ϼ���');
			fn_fail();
			</script>
		<%
		}
	}else if(gotoindex.equalsIgnoreCase("ic_modify")){//�浹��� �� ����
		returnValue = propertyControl.modifyPropertyIC(ic_option, pr_no);
		if(returnValue == true){
		%>
			<script language="javascript">
			alert('�浹��� ���濡 �����߽��ϴ�.');
			//fn_success(pr_no, d_index);
			fn_success();
			</script>
		<%
		}else{
		%>
			<script language="javascript">
			alert('�浹��� ���濡 �����߽��ϴ�. �����ڿ��� �����ϼ���');
			fn_fail();
			</script>
		<%
		}
	}else if(gotoindex.equalsIgnoreCase("et_modify")){//���� ���� �� ����
		returnValue = propertyControl.modifyPropertyEXP_THE_REC(et_option, pr_no);
		if(returnValue == true){
		%>
			<script language="javascript">
			alert('�������� ���濡 �����߽��ϴ�.');
			//fn_success(pr_no, d_index);
			fn_success();
			</script>
		<%
		}else{
		%>
			<script language="javascript">
			alert('�������� ���濡 �����߽��ϴ�. �����ڿ��� �����ϼ���');
			fn_fail();
			</script>
		<%
		}
	}else if(gotoindex.equalsIgnoreCase("num_mod")){//��ġ������ �Ҽ��� �����ϱ�
		//��ġ���� ����
		returnValue = graphControl.modifyGraphPointData(pr_no);
	

			if(returnValue == true){
		%>
			<script language="javascript">
			alert('��ġ �Ҽ��� ������ �����߽��ϴ�.');
			fn_success_number();
			</script>
		<%
			}else{
			%>
				<script language="javascript">
				alert('������ �����߽��ϴ�. �����ڿ��� �����ϼ���');
				fn_fail();
				</script>
			<%
			}
		}else{
		%>
			<script language="javascript">
			alert('������ �����߽��ϴ�. �����ڿ��� �����ϼ���');
			fn_fail();
			</script>
		<%
		}
		
	
%>
</FORM>
</body>
</html>