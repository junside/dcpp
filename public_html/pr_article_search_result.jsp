<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>
<%request.setCharacterEncoding("EUC-KR");%>
<jsp:useBean id="searchControl" class="nfri.dcpp.properties.business.Ctr_Article_Process" scope="page"/>
<%
	Vector search_info = searchControl.searchInfo(request);
	
	//페이지 설정 관련 변수 선언
	int totalRecord = 0; //전체 레코드 수
	int numPerPage = 10; //페이지당 레코드 수
	int pagePerBlock = 10; //블록당 페이지 수
	int totalPage = 0; //전체 페이지 수
	int totalBlock = 0; //전체 블록 수
	int nowPage = 0; //현재 페이지
	int nowBlock = 0; //현재 블록
	int beginPerPage = 0; //페이지의 시작번호

	//현재 페이지 설정
	if(request.getParameter("nowPage") != null)
	{
		nowPage = Integer.parseInt(request.getParameter("nowPage"));
	}
	//현재 블록 설정
	if(request.getParameter("nowBlock") != null)
	{
		nowBlock = Integer.parseInt(request.getParameter("nowBlock"));
	}
	//전체 개수 저장
	totalRecord = search_info.size();
	//페이지별 게시물의 시작번호 계산
	beginPerPage = nowPage * numPerPage;
	//전체 페이지 수 계산
	totalPage =(int)Math.ceil((double)totalRecord/numPerPage);
	//전체 블록 수 계산
	totalBlock =(int)Math.ceil((double)totalPage/pagePerBlock);
	//Ordering 관련 변수
	String _oCol 		= request.getParameter("_oCol"); //Order 대상
	String _oDir 		= request.getParameter("_oDir"); //Ordrer 순서
	//기존 검색 값 저장 변수
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
	//파일 다운로드를 위한 변수
	String file_path = "";
	String file_name = "";
	String file_ext = "";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="./css/common.css" rel="stylesheet" type="text/css" />
<title>참고문헌 정보 조회 결과</title>
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
function selectBeforeBlock(nowBlock, nowPage)//이전 목록 가기
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "pr_article_search_result.jsp";
	form2.submit();	
}

function selectBlock(nowBlock, nowPage)//목록 가기
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "pr_article_search_result.jsp";
	form2.submit();	
}

function selectAfterBlock(nowBlock, nowPage)//다음 목록 가기
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

function downloadArtcl(path, name, ext){ //참고문헌 다운로드
	var form2 = document.form1;
	//form에 값을 넘기기 위하여 폼객체에 페이지의 객체값을 넣는다.
	form2.file_path.value = path;
	form2.file_name.value = name;
	form2.file_ext.value = ext;
	form2.target = "_self";
	form2.action = "pr_article_download_file.jsp";
	form2.submit();
}

function selectArtcl(artcl_no){ //참고문헌 선택
	opener.form1.pr_ref_artcl_no.value=artcl_no;
	self.close();
}

function newArticleInsert(){ //새로운 참고문헌 입력
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
검색 결과 : <%=totalRecord%>건 (<font color=red> <%=totalRecord==0?"0":nowPage + 1%>/<%=totalPage %> Pages</font>)
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
	      <A HREF="javascript:setOrderDirection('artcl_no')">문헌번호</A>
	    </th>
	    <th class="thc">
	      <A HREF="javascript:setOrderDirection('artcl_name')">제목</A>
	    </th>
	    <th class="thc">
	      <A HREF="javascript:setOrderDirection('artcl_author')">저자</A>
	    </th>
	    <th class="thc">
	      <A HREF="javascript:setOrderDirection('artcl_jn_name')">저널명</A>
	    </th>
	    <th class="thc">
	      <A HREF="javascript:setOrderDirection('artcl_jn_name')">연도</A>
	    </th>
	    <th class="thc">
	      <A HREF="javascript:setOrderDirection('artcl_jn_name')">Vol</A>
	    </th>
	    <th class="thc">
	      <A HREF="javascript:setOrderDirection('artcl_jn_year')">Page</A>
	    </th>
	    <th class="thc">
	      선택
	    </th>
	  </tr>
	  <%
	  	for (int i = beginPerPage ; i < (beginPerPage+numPerPage); i++) {
			if(i == totalRecord || totalRecord == 0)
			{
				break;
			}
			
			Article_Info artcl = (Article_Info) search_info.elementAt(i);
			//참고문헌 번호
			String artcl_no = artcl.getPL_RAI_ARTCL_NUM();
			//참고문헌 제목
			String artcl_name = artcl.getPL_RAI_ARTCL_TITLE();
			//참고문헌 저자
			String artcl_author = artcl.getPL_RAI_ARTCL_AUTH_ENAME() + ", " + artcl.getPL_RAI_ARTCL_AUTH_FNAME() + ".";
			//참고문헌 저널명
			String artcl_jn_name = artcl.getPL_RAI_JOUR_TITLE();
			//참고문헌 Vol
			String artcl_vol = artcl.getPL_RAI_JOUR_VOL();
			//참고문헌 page
			String artcl_page = artcl.getPL_RAI_ARTCL_ST_PAGE();
			//참고문헌 출판년도
			String artcl_jn_year = artcl.getPL_RAI_JOUR_YEAR();
			//참고문헌 파일위치
			String artcl_file_path = artcl.getPL_RAI_ORG_FILE_PATH();
			//참고문헌 파일명(전체)
			String artcl_file_name = artcl.getPL_RAI_ORG_FILE_NAME();
			//참고문헌 확장자
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
			     	<a href="javascript:void(0);"  onClick="selectArtcl('<%=artcl_no%>'); return false;"> 선택 </a>
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
			<A HREF="javascript:selectBeforeBlock('<%=nowBlock-1%>','<%=(nowBlock-1)*pagePerBlock %>')">이전 <%=pagePerBlock%>개</A>
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
			<A HREF="javascript:selectAfterBlock('<%=nowBlock+1%>','<%=(nowBlock+1)*pagePerBlock %>')">다음 <%=pagePerBlock%>개</A>
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