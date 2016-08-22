<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>
<%request.setCharacterEncoding("EUC-KR");%>
<jsp:useBean id="searchControl" class="nfri.dcpp.properties.business.Ctr_Article_Process" scope="page"/>
<%
	Vector search_info = searchControl.searchInfo(request);
	
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
	String _oCol 		= request.getParameter("_oCol"); //Order ���
	String _oDir 		= request.getParameter("_oDir"); //Ordrer ����
	//���� �˻� �� ���� ����
	String pl_ra_artcl_title = request.getParameter("pl_ra_artcl_title");
	String option1_oper	= request.getParameter("option1_oper");
	String pl_ra_artcl_auth_fname	= request.getParameter("pl_ra_artcl_auth_fname");
	String option2_oper = request.getParameter("option2_oper");
	String pl_ra_artcl_auth_ename	= request.getParameter("pl_ra_artcl_auth_ename");
	String option3_oper = request.getParameter("option3_oper");
	String pl_ra_jour_title	= request.getParameter("pl_ra_jour_title");
	String option4_oper	= request.getParameter("option4_oper");
	String publish_year_from = request.getParameter("publish_year_from");
	String publish_year_to = request.getParameter("publish_year_to");
	String option5_oper	= request.getParameter("option5_oper");
	String pl_raci_code_id = request.getParameter("pl_raci_code_id");
	//���� �ٿ�ε带 ���� ����
	String file_path = "";
	String file_name = "";
	String file_ext = "";
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
    form2.action = "pr_article_search_result.jsp";
	form2.submit();	
}

function selectBlock(nowBlock, nowPage)//��� ����
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "pr_article_search_result.jsp";
	form2.submit();	
}

function selectAfterBlock(nowBlock, nowPage)//���� ��� ����
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "pr_article_search_result.jsp";
	form2.submit();	
}

function setOrderDirection(column){	
	var dir = "asc";
	var form2 = document.form1;
	var direction = form2._oDir.value;
	if(direction != null ){
		if(direction !='' && direction == 'asc'){
			direction = 'desc';
		}else{direction = 'asc'}
		dir = direction;
	}
	form2._oDir.value = dir;
	form2._oCol.value = column;
	form2.action = 'pr_article_search_result.jsp';
	form2.submit();
}

function downloadArtcl(path, name, ext){ //������ �ٿ�ε�
	var form2 = document.form1;
	//form�� ���� �ѱ�� ���Ͽ� ����ü�� �������� ��ü���� �ִ´�.
	form2.file_path.value = path;
	form2.file_name.value = name;
	form2.file_ext.value = ext;
	form2.target = "_self";
	form2.action = "pr_article_download_file.jsp";
	form2.submit();
}

function selectArtcl(artcl_no){ //������ ����
	opener.form1.pr_ref_artcl_no.value=artcl_no;
	self.close();
}

function newArticleInsert(){ //���ο� ������ �Է�
	//window.open("pr_article_insert.jsp","article_insert","width=700,height=650,toolbar=no");
	var form2 = document.form1;	
	form2.action = "pr_article_insert_search.jsp";
	form2.submit();
}

document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<div id="content">
<input type="hidden" name="nowPage" value="<%=nowPage%>"/>
<input type="hidden" name="nowBlock" value="<%=nowBlock%>"/>
<input type="hidden" name="_oDir" value="<%=_oDir%>"/>
<input type="hidden" name="_oCol" value="<%=_oCol%>"/>
<input type="hidden" name="pl_ra_artcl_title" value="<%=pl_ra_artcl_title%>"/>
<input type="hidden" name="option1_oper" value="<%=option1_oper%>"/>
<input type="hidden" name="pl_ra_artcl_auth_fname" value="<%=pl_ra_artcl_auth_fname%>"/>
<input type="hidden" name="pl_ra_artcl_auth_ename" value="<%=pl_ra_artcl_auth_ename%>"/>
<input type="hidden" name="option2_oper" value="<%=option2_oper%>"/>
<input type="hidden" name="pl_ra_jour_title" value="<%=pl_ra_jour_title%>"/>
<input type="hidden" name="option3_oper" value="<%=option3_oper%>"/>
<input type="hidden" name="publish_year_from" value="<%=publish_year_from%>"/>
<input type="hidden" name="publish_year_to" value="<%=publish_year_to%>"/>
<input type="hidden" name="option4_oper" value="<%=option4_oper%>"/>
<input type="hidden" name="option5_oper" value="<%=option5_oper%>"/>
<input type="hidden" name="pl_raci_code_id" value="<%=pl_raci_code_id%>"/>
<input type="hidden" name="file_path" value="<%=file_path%>"/>
<input type="hidden" name="file_name" value="<%=file_name%>"/>
<input type="hidden" name="file_ext" value="<%=file_ext%>"/>
<img src="images/main_cont/pr_search_result_main_t_01.gif" width="686" height="47"><br>
<span class="help">
�˻� ��� : <%=totalRecord%>�� (<font color=red> <%=totalRecord==0?"0":nowPage + 1%>/<%=totalPage %> Pages</font>)
</span>
<div align = "right">
	<a href="javascript:newArticleInsert()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('artcl','','images/img_cont/new_artcl_upper.gif',1)"><img src="images/img_cont/new_artcl_normal.gif" name="artcl" width="170" height="20" border="0" id="artcl"/>
    </a>
</div>
<table class="tab2">
	<col width="40">
	<col width="60">
	<col width="180">
	<col width="70">
	<col width="100">
	<col width="50">
	<col width="50">
	<col width="50">
	<col width="50">
	  <tr>
	    <th class="thc">
	     	 No
	    </th >
	    <th class="thc">
	      <A HREF="javascript:setOrderDirection('artcl_no')">�����ȣ</A>
	    </th>
	    <th class="thc">
	      <A HREF="javascript:setOrderDirection('artcl_name')">����</A>
	    </th>
	    <th class="thc">
	      <A HREF="javascript:setOrderDirection('artcl_author')">����</A>
	    </th>
	    <th class="thc">
	      <A HREF="javascript:setOrderDirection('artcl_jn_name')">���θ�</A>
	    </th>
	    <th class="thc">
	      <A HREF="javascript:setOrderDirection('artcl_jn_name')">����</A>
	    </th>
	    <th class="thc">
	      <A HREF="javascript:setOrderDirection('artcl_jn_name')">Vol</A>
	    </th>
	    <th class="thc">
	      <A HREF="javascript:setOrderDirection('artcl_jn_year')">Page</A>
	    </th>
	    <th class="thc">
	      ����
	    </th>
	  </tr>
	  <%
	  	for (int i = beginPerPage ; i < (beginPerPage+numPerPage); i++) {
			if(i == totalRecord || totalRecord == 0)
			{
				break;
			}
			
			Article_Info artcl = (Article_Info) search_info.elementAt(i);
			//������ ��ȣ
			String artcl_no = artcl.getPL_RAI_ARTCL_NUM();
			//������ ����
			String artcl_name = artcl.getPL_RAI_ARTCL_TITLE();
			//������ ����
			String artcl_author = artcl.getPL_RAI_ARTCL_AUTH_ENAME() + ", " + artcl.getPL_RAI_ARTCL_AUTH_FNAME() + ".";
			//������ ���θ�
			String artcl_jn_name = artcl.getPL_RAI_JOUR_TITLE();
			//������ Vol
			String artcl_vol = artcl.getPL_RAI_JOUR_VOL();
			//������ page
			String artcl_page = artcl.getPL_RAI_ARTCL_ST_PAGE();
			//������ ���ǳ⵵
			String artcl_jn_year = artcl.getPL_RAI_JOUR_YEAR();
			//������ ������ġ
			String artcl_file_path = artcl.getPL_RAI_ORG_FILE_PATH();
			//������ ���ϸ�(��ü)
			String artcl_file_name = artcl.getPL_RAI_ORG_FILE_NAME();
			//������ Ȯ����
			String artcl_file_ext = artcl.getPL_RAI_ORG_FILE_EXT(); 

	  		%>
	  		<tr>
			    <td class="tdc">
			     	<%=i+1%>
			    </td >
			    <td class="tdc">
			     	 <%=artcl_no%>
			    </td >
			    <td class="tdl">
			     	<%=ComUtil.getAbbrString(artcl_name, 25)%>
			    </td >
			    <td class="tdl">
			     	 <%=ComUtil.getAbbrString(artcl_author, 6)%>
			    </td >
			    <td class="tdl">
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
			     	<a href="javascript:void(0);"  onClick="selectArtcl('<%=artcl_no%>'); return false;"> ���� </a>
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
function init(){
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
}
</script>
</body>
</html>