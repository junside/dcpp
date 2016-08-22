<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>
<%request.setCharacterEncoding("EUC-KR");%>
<jsp:useBean id="searchControl" class="nfri.dcpp.properties.business.Ctr_Article_Process" scope="page"/>
<%

	Vector search_info = searchControl.searchJournal_AssessInfo(request);


	//������ ���� ���� ���� ����
	int totalRecord = 0; //��ü ���ڵ� ��
	int numPerPage = 10; //�������� ���ڵ� ��
	int pagePerBlock = 10; //��ϴ� ������ ��
	int totalPage = 0; //��ü ������ ��
	int totalBlock = 0; //��ü ��� ��
	int nowPage = 0; //���� ������
	int nowBlock = 0; //���� ���
	int beginPerPage = 0; //�������� ���۹�ȣ

	//���� ������ ����
	if(request.getParameter("nowPage") != null)
	{
		nowPage = Integer.parseInt(request.getParameter("nowPage"));
	}
	//���� ��� ����
	if(request.getParameter("nowBlock") != null)
	{
		nowBlock = Integer.parseInt(request.getParameter("nowBlock"));
	}
	//��ü ���� ����
	totalRecord = search_info.size();
	//�������� �Խù��� ���۹�ȣ ���
	beginPerPage = nowPage * numPerPage;
	//��ü ������ �� ���
	totalPage =(int)Math.ceil((double)totalRecord/numPerPage);
	//��ü ��� �� ���
	totalBlock =(int)Math.ceil((double)totalPage/pagePerBlock);
	//Ordering ���� ����
	//String _oCol 		= request.getParameter("_oCol"); //Order ���
	//String _oDir 		= request.getParameter("_oDir"); //Ordrer ����
	//���� �˻� �� ���� ����
	String jour_title = request.getParameter("jour_title");	
	String jour_vol	= request.getParameter("jour_vol");	
	String jour_st_page	= request.getParameter("jour_st_page");
	String publish_year = request.getParameter("publish_year");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="./css/common.css" rel="stylesheet" type="text/css" />
<title>������ ���� ��ȸ ���</title>
</head>
<body>
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
function selectBeforeBlock(nowBlock, nowPage)//���� ��� ����
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "pr_article_search_to_assess_result.jsp";
	form2.submit();	
}

function selectBlock(nowBlock, nowPage)//��� ����
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "pr_article_search_to_assess_result.jsp";
	form2.submit();	
}

function selectAfterBlock(nowBlock, nowPage)//���� ��� ����
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "pr_article_search_to_assess_result.jsp";
	form2.submit();	
}

function viewAssessInfo(pr_no){ //������ ����
	//checkSessionUserID();
	if(saveMsg('7') == true){
		window.open("pr_view_property_assess_info.jsp?pr_no="+pr_no,"property_info","width=700,height=800,toolbar=no,location=no,status=no,scrollbars=yes");
		return;
	}
}

function insertAssessInfo(pr_no){ //�� ���� �Է�
	//checkSessionUserID();
	if(saveMsg('8') == true){
		var form2 = document.form1;
		//window.open("pr_insert_property_assess.jsp?pr_no="+pr_no,"property_info","width=700,height=650,toolbar=no");
		form2.target = "_self";
		form2.action = "pr_insert_property_assess.jsp?pr_no="+pr_no;
		form2.submit();
	}
}

function saveMsg(number){
	var num = number;
	switch(num) {
		case '7': //������ ����
			var result = confirm("���� �� ������ Ȯ���Ͻðڽ��ϱ�?");
			return result;
			break;
		case '8': //������ �Է�
			var result = confirm("���� �� ������ �Է��Ͻðڽ��ϱ�?");
			return result;
			break;
		default :		
			break;
	}		
}

document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<div id="content">
<input type="hidden" name="nowPage" value="<%=nowPage%>"/>
<input type="hidden" name="nowBlock" value="<%=nowBlock%>"/>
<input type="hidden" name="jour_title" value="<%=jour_title%>"/>
<input type="hidden" name="jour_vol" value="<%=jour_vol%>"/>
<input type="hidden" name="jour_st_page" value="<%=jour_st_page%>"/>
<input type="hidden" name="publish_year" value="<%=publish_year%>"/>

<span class="help">
�˻� ��� : <%=totalRecord%>�� (<font color=red> <%=totalRecord==0?"0":nowPage + 1%>/<%=totalPage %> Pages</font>)
</span>
<table class="tab2">
	<col width="30">
	<col width="50">
	<col width="60">
	<col width="170">
	<col width="60">
	<col width="100">
	<col width="40">
	<col width="30">
	<col width="40">
	<col width="50">
	<col width="50">
	  <tr>
	    <th class="thc">
	     	 No
	    </th >
	    <th class="thc">
	      	������ȣ
	    </th>
	    <th class="thc">
	      	�����ȣ
	    </th>
	    <th class="thc">
	      	����
	    </th>
	    <th class="thc">
	      	����
	    </th>
	    <th class="thc">
      		���θ�
	    </th>
	    <th class="thc">
	      	����
	    </th>
	    <th class="thc">
	      	Vol
	    </th>
	    <th class="thc">
	      	Page
	    </th>
	    <th class="thc">
	      	����
	    </th>
	    <th class="thc">
	     	��
	    </th>
	  </tr>
	  <%
	  	for (int i = beginPerPage ; i < (beginPerPage+numPerPage); i++) {
			if(i == totalRecord || totalRecord == 0)
			{
				break;
			}
			
			Article_Info artcl = (Article_Info) search_info.elementAt(i);
			//�������� ��ȣ
			String pr_no = artcl.getPL_BI_DATA_NUM();
			//������ ��ȣ
			String artcl_no = artcl.getPL_RAI_ARTCL_NUM();
			//������ ����
			String artcl_name = artcl.getPL_RAI_ARTCL_TITLE();
			//������ ����
			String artcl_author = artcl.getPL_RAI_ARTCL_AUTH_FNAME();
			//������ ���θ�
			String artcl_jn_name = artcl.getPL_RAI_JOUR_TITLE();			
			//������ ���ǳ⵵
			String artcl_jn_year = artcl.getPL_RAI_JOUR_YEAR();			
			//������ Vol
			String artcl_vol = artcl.getPL_RAI_JOUR_VOL();
			//������ page
			String artcl_page = artcl.getPL_RAI_ARTCL_ST_PAGE();
			//�� ����
			String artcl_assess = artcl.getPL_PEI_FINAL_FLAG();
			 

	  		%>
	  		<tr>
			    <td class="tdc">
			     	<%=i+1%>
			    </td >
			    <td class="tdc">
			     	 <%=pr_no%>
			    </td >
			    <td class="tdc">
			     	 <%=artcl_no%>
			    </td >
			    <td class="tdl">
			     	<%=ComUtil.getAbbrString(artcl_name, 25)%>
			    </td >
			    <td class="tdc">
			     	 <%=ComUtil.getAbbrString(artcl_author, 6)%>
			    </td >
			    <td class="tdc">
			     	 <%=ComUtil.getAbbrString(artcl_jn_name, 12)%>
			    </td >
			    <td class="tdc">
			     	 <%=artcl_jn_year%>
			    </td >
			    <td class="tdc">
			     	 <%=artcl_vol%>
			    </td >
			    <td class="tdc">
			     	 <%=artcl_page%>
			    </td >
			    <td class="tdc">
					     	<%			     	
					     	 if(artcl_assess.equalsIgnoreCase("V")){//��ȿ�� ������
					     	%>
					     		��ȿ
					     	<%			     		 
					     	 }else if(artcl_assess.equalsIgnoreCase("R")){//�Ⱒ
							%>
						    	�Ⱒ
						    <%			     		 
						     }else if(artcl_assess.equalsIgnoreCase("H")){//������
							%>
								����
							<%			     		 
						     }else if(artcl_assess.equalsIgnoreCase("N")){//�򰡴��
							%>
								���
							<%			     		 
						     }
					     	%>
					    </td >
			    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
			    	<%
			    		if("V".equalsIgnoreCase(artcl_assess)){
			    			%>
					     	<a href="javascript:void(0);"  onClick="viewAssessInfo('<%=pr_no%>'); return false;"> 
					     		<font color="blue"> ����</font>
					     	</a>
					     	<%	
			    		}else{
			    			%>
					     	<a href="javascript:void(0);"  onClick="insertAssessInfo('<%=pr_no%>'); return false;"> 
					     		<font color="red"> ��</font> 
					     	</a>
					     	<%			     		
			     		}
			     	%>
			    </td >
		  	</tr>
	  		<%
	  	}
	  %>
</table>
<div class="bbtn_c">
<%
	if(totalRecord !=0)
	{
		if(nowBlock > 0){
			%>
			<A HREF="javascript:selectBeforeBlock('<%=nowBlock-1%>','<%=(nowBlock-1)*pagePerBlock %>')">���� <%=pagePerBlock%>��</A>
			<%
		}
		for(int i = 0; i<pagePerBlock; i++){
			%>
			<A HREF="javascript:selectBlock('<%=nowBlock%>','<%=(nowBlock*pagePerBlock) + i%>')">[<%=(nowBlock*pagePerBlock)+i+1%>]</A>
			<%
			if((nowBlock*pagePerBlock) + i + 1 == totalPage){
				break;
			}
		}
		if(totalBlock > nowBlock + 1){
		%>
			<A HREF="javascript:selectAfterBlock('<%=nowBlock+1%>','<%=(nowBlock+1)*pagePerBlock %>')">���� <%=pagePerBlock%>��</A>
		<%
		}
	}
%>
</div>
</div>
</form>
<script type="text/javascript">
/%function init(){
	  var doc = document.getElementById('content');
	  if(doc.offsetHeight == 0){
	  } else {
		 //pagewidth = doc.offsetWidth;
		 pageheight = doc.offsetHeight + 90;
		 //alert(pageheight);
		 //parent.document.getElementById("content").height = pageheight+"px";
		 window.resizeTo(650,pageheight);
	  }
	}

window.onload = function(){
	  init();
}%/
</script>
</body>
</html>