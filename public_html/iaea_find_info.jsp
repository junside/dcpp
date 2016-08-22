<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.board.model.Board_Info"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>
<%request.setCharacterEncoding("EUC-KR");%>
<jsp:useBean id="searchControl" class="nfri.dcpp.properties.business.Ctr_Property_Search_Process" scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String doi_no = request.getParameter("doi_no");
	//System.out.println("1. doi_no : " + doi_no);
	
	Vector searchData = searchControl.selectIAEASearchPropertyList(doi_no);
	int data_size = searchData.size();	
	
	
	//������ ���� ���� ���� ����
	int iaea_totalRecord = 0; //��ü ���ڵ� ��
	int iaea_numPerPage = 10; //�������� ���ڵ� ��
	int iaea_pagePerBlock = 10; //��ϴ� ������ ��
	int iaea_totalPage = 0; //��ü ������ ��
	int iaea_totalBlock = 0; //��ü ��� ��
	int iaea_nowPage = 0; //���� ������
	int iaea_nowBlock = 0; //���� ���
	int iaea_beginPerPage = 0; //�������� ���۹�ȣ

	//���� ������ ����
	if(request.getParameter("iaea_nowPage") != null)
	{
		iaea_nowPage = Integer.parseInt(request.getParameter("iaea_nowPage"));
	}
	//���� ��� ����
	if(request.getParameter("iaea_nowBlock") != null)
	{
		iaea_nowBlock = Integer.parseInt(request.getParameter("iaea_nowBlock"));
	}
	
	//��ü ���� ����
	iaea_totalRecord = searchData.size();
	//�������� �Խù��� ���۹�ȣ ���
	iaea_beginPerPage = iaea_nowPage * iaea_numPerPage;
	//��ü ������ �� ���
	iaea_totalPage =(int)Math.ceil((double)iaea_totalRecord/iaea_numPerPage);
	//��ü ��� �� ���
	iaea_totalBlock =(int)Math.ceil((double)iaea_totalPage/iaea_pagePerBlock);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>Data Center for Plasma Properties - IAEA Linked Page</title>
<script language = javascript>
function selectBeforeBlock(iaea_nowBlock, iaea_nowPage)//���� ��� ����
{
    var form2 = document.form1;
    form2.iaea_nowBlock.value = iaea_nowBlock;
    form2.iaea_nowPage.value = iaea_nowPage;
    form2.target = "_self";
    form2.action = "iaea_find_info.jsp";
	form2.submit();	
}

function selectBlock(iaea_nowBlock, iaea_nowPage)//��� ����
{
    var form2 = document.form1;
    form2.iaea_nowBlock.value = iaea_nowBlock;
    form2.iaea_nowPage.value = iaea_nowPage;
    form2.target = "_self";
    form2.action = "iaea_find_info.jsp";
	form2.submit();	
}

function selectAfterBlock(iaea_nowBlock, iaea_nowPage)//���� ��� ����
{
    var form2 = document.form1;
    form2.iaea_nowBlock.value = iaea_nowBlock;
    form2.iaea_nowPage.value = iaea_nowPage;
    form2.target = "_self";
    form2.action = "iaea_find_info.jsp";
	form2.submit();	
}

function viewBasicInfo(pr_no, artcl_no){ //�⺻���� ����
	if(saveMsg('1') == true){
		window.open("e_pr_view_property_info.jsp?pr_no="+pr_no+"&artcl_no="+artcl_no,"property_info","width=500,height=400,toolbar=no,location=no,status=no");
		return;
	}
}

function viewDataInfo(pr_no){ //��ġ���� ����
	if(saveMsg('2') == true){
		window.open("e_pr_view_graph_data.jsp?pr_no="+pr_no,"property_info","width=100%,height=100%,toolbar=no,location=no,status=no");
		return;
	}
}

function viewArticleInfo(artcl_no){ //������ ����
	if(saveMsg('3') == true){
		window.open("e_pr_view_article_info.jsp?artcl_no="+artcl_no,"article_info","width=100%,height=100%,toolbar=no,location=no,status=no,scrollbar=auto");
		return;
	}
}

function viewGraphInfo(pr_no){ //�׷��� ����
	if(saveMsg('4') == true){
		window.open("e_pr_view_graph.jsp?pr_no="+pr_no,"graph_view","width=100%,height=100%,toolbar=no,location=no,status=no");
		return;
		//var form2 = document.form1;
		//form2.action = "pr_draw_graph_data.jsp?pr_no="+pr_no;
		//form2.submit();
	}
}

function saveMsg(number){
	var num = number;
	switch(num) {
		case '1': //�⺻���� ����
			var result = confirm("Would you like to see the basic information of property?");
			return result;
			break;
		case '2': //��ġ���� ����
			var result = confirm("Would you like to see the numeric information of property?");
			return result;
			break;
		case '3': //������ ����
			var result = confirm("Would you like to see the bibliography information of property?");
			return result;
			break;
		case '4': //�׷��� ����
			var result = confirm("Would you like to see the graph of property?");
			return result;
			break;
		default :		
			break;
	}		
}
function init(count){
	if(count ==0){
		alert("No Search Result!");	
		self.close();
	}
}
</script>
</head>
<body>
<form name="form1" action="" method="POST">
<input type="hidden" name="nowPage" value="<%=iaea_nowPage%>"/>
<input type="hidden" name="nowBlock" value="<%=iaea_nowBlock%>"/>
<input type="hidden" name="doi_no" value="<%=doi_no%>"/>
<p class="level2">Search Result</p>
<span class="help">
Count : <%=iaea_totalRecord%> (<font color=red> <%=iaea_totalRecord==0?"0":iaea_nowPage + 1%>/<%=iaea_totalPage %> Pages</font>)
</span>
<table class="tab2">
	<col width="50">
	<col width="90">
	<col width="240">
	<col width="60">
	<col width="60">
	<col width="80">
	<col width="60">
	  <tr>
	    <th class="thc">
	     	 No
	    </th >
	    <th class="thc">
	     	 Property No
	    </th >
	    <th class="thc">
	      	Expression
	    </th>
	    <th class="thc">
	     	Basics
	    </th>	    
	    <th class="thc">
	      	Figure
	    </th>
	    <th class="thc">
	      	Bibliography
	    </th>
	    <th class="thc">
	      	Graph
	    </th>
	  </tr>
	  <%
	  	for (int i = iaea_beginPerPage ; i < (iaea_beginPerPage+iaea_numPerPage); i++) {
	  	if(i == iaea_totalRecord || iaea_totalRecord == 0)
	  	{
	  		break;
	  	}
	  	Properties_Basic_Info prot = (Properties_Basic_Info) searchData.elementAt(i);
	  	
	  	String pro_no = prot.getPL_BI_DATA_NUM(); //����������ȣ	  	
	  	String artcl_no = prot.getPL_RAI_ARTCL_NUM(); //�������ȣ
	  	String flag = prot.getPL_BI_INSERT_FLAG(); //�Է¿Ϸ��÷���
	  	
	  	//����������ȣ
	  	String str_PL_BI_DATA_NUM = prot.getPL_BI_DATA_NUM();
	  	//�������ȣ
	  	String str_PL_RAI_ARTCL_NUM = prot.getPL_RAI_ARTCL_NUM();
	  	//ǥ����
	  	String str_PL_BI_EXPRESSION = prot.getPL_BI_EXPRESSION();
	    //�����μ���
	  	String str_PL_BI_MAIN_PROC = prot.getPL_BI_MAIN_PROC();
	    //�����μ���
	  	String str_PL_BI_SUB_PROC = prot.getPL_BI_SUB_PROC();	  	
	  	//��������
	  	String str_PL_BI_EXP_THE_REC = prot.getPL_BI_EXP_THE_REC();
	  	//�Է� �÷���
	  	String str_PL_BI_INSERT_FLAG = prot.getPL_BI_INSERT_FLAG();
	  	//�Է³�¥
	  	String str_PL_BI_INSERT_DATE = prot.getPL_BI_INSERT_DATE();

	  %>
	  		<tr>
			    <td class="tdc">
			     	<%=i+1%>
			    </td >
			    <td class="tdc">
			     	<%//������ȣ%>
			     	<%=str_PL_BI_DATA_NUM%>
			     	<input type="hidden" name="pr_no" value='<%=str_PL_BI_DATA_NUM%>'>
			    </td >
			    <td class="tdl">
			    	<%//ǥ����%>
			    	<%=ComUtil.getAbbrString(str_PL_BI_EXPRESSION, 120)%>		     	 
			    </td >
			    <td class="tdc">
			     	<%//�⺻����%>
			     	<a href="javascript:void(0);"  onClick="viewBasicInfo('<%=str_PL_BI_DATA_NUM%>','<%=str_PL_RAI_ARTCL_NUM%>'); return false;"> View </a>		     	 
			    </td >
			    <td class="tdc">
			    	<%//��ġ����%>
			     	<a href="javascript:void(0);"  onClick="viewDataInfo('<%=str_PL_BI_DATA_NUM%>'); return false;"> View </a>			     	 
			    </td >
			    <td class="tdc">
			    	<%//����������%>
			     	<a href="javascript:void(0);"  onClick="viewArticleInfo('<%=str_PL_RAI_ARTCL_NUM%>'); return false;"> View </a>		     	 
			    </td >
			    <td class="tdc">
			    	<%//�׷�������%>
			     	<a href="javascript:void(0);"  onClick="viewGraphInfo('<%=str_PL_BI_DATA_NUM%>'); return false;"> View </a>		     	 
			    </td >
	  		<%
	  	}
	  	
	  %>
</table>
<div class="bbtn_c">
<%
	if(iaea_totalRecord !=0)
	{
		if(iaea_nowBlock > 0){
			%>
			<A HREF="javascript:selectBeforeBlock('<%=iaea_nowBlock-1%>','<%=(iaea_nowBlock-1)*iaea_pagePerBlock %>')">���� <%=iaea_pagePerBlock%>��</A>
			<%
		}
		for(int i = 0; i<iaea_pagePerBlock; i++){
			%>
			<A HREF="javascript:selectBlock('<%=iaea_nowBlock%>','<%=(iaea_nowBlock*iaea_pagePerBlock) + i%>')">[<%=(iaea_nowBlock*iaea_pagePerBlock)+i+1%>]</A>
			<%
			if((iaea_nowBlock*iaea_pagePerBlock) + i + 1 == iaea_totalPage){
				break;
			}
		}
		if(iaea_totalBlock > iaea_nowBlock + 1){
		%>
			<A HREF="javascript:selectAfterBlock('<%=iaea_nowBlock+1%>','<%=(iaea_nowBlock+1)*iaea_pagePerBlock %>')">���� <%=iaea_pagePerBlock%>��</A>
		<%
		}
	}
%>
</div>
<script type="text/javascript">
	init(<%=data_size%>);	
</script>
</form>
</body>
</html>