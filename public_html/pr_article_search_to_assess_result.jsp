<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>
<%request.setCharacterEncoding("EUC-KR");%>
<jsp:useBean id="searchControl" class="nfri.dcpp.properties.business.Ctr_Article_Process" scope="page"/>
<%

	Vector search_info = searchControl.searchJournal_AssessInfo(request);


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
	//String _oCol 		= request.getParameter("_oCol"); //Order 대상
	//String _oDir 		= request.getParameter("_oDir"); //Ordrer 순서
	//기존 검색 값 저장 변수
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
    form2.action = "pr_article_search_to_assess_result.jsp";
	form2.submit();	
}

function selectBlock(nowBlock, nowPage)//목록 가기
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "pr_article_search_to_assess_result.jsp";
	form2.submit();	
}

function selectAfterBlock(nowBlock, nowPage)//다음 목록 가기
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "pr_article_search_to_assess_result.jsp";
	form2.submit();	
}

function viewAssessInfo(pr_no){ //평가정보 보기
	//checkSessionUserID();
	if(saveMsg('7') == true){
		window.open("pr_view_property_assess_info.jsp?pr_no="+pr_no,"property_info","width=700,height=800,toolbar=no,location=no,status=no,scrollbars=yes");
		return;
	}
}

function insertAssessInfo(pr_no){ //평가 정보 입력
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
		case '7': //평가정보 보기
			var result = confirm("물성 평가 정보를 확인하시겠습니까?");
			return result;
			break;
		case '8': //평가정보 입력
			var result = confirm("물성 평가 정보를 입력하시겠습니까?");
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
검색 결과 : <%=totalRecord%>건 (<font color=red> <%=totalRecord==0?"0":nowPage + 1%>/<%=totalPage %> Pages</font>)
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
	      	물성번호
	    </th>
	    <th class="thc">
	      	문헌번호
	    </th>
	    <th class="thc">
	      	제목
	    </th>
	    <th class="thc">
	      	저자
	    </th>
	    <th class="thc">
      		저널명
	    </th>
	    <th class="thc">
	      	연도
	    </th>
	    <th class="thc">
	      	Vol
	    </th>
	    <th class="thc">
	      	Page
	    </th>
	    <th class="thc">
	      	상태
	    </th>
	    <th class="thc">
	     	평가
	    </th>
	  </tr>
	  <%
	  	for (int i = beginPerPage ; i < (beginPerPage+numPerPage); i++) {
			if(i == totalRecord || totalRecord == 0)
			{
				break;
			}
			
			Article_Info artcl = (Article_Info) search_info.elementAt(i);
			//물성정보 번호
			String pr_no = artcl.getPL_BI_DATA_NUM();
			//참고문헌 번호
			String artcl_no = artcl.getPL_RAI_ARTCL_NUM();
			//참고문헌 제목
			String artcl_name = artcl.getPL_RAI_ARTCL_TITLE();
			//참고문헌 저자
			String artcl_author = artcl.getPL_RAI_ARTCL_AUTH_FNAME();
			//참고문헌 저널명
			String artcl_jn_name = artcl.getPL_RAI_JOUR_TITLE();			
			//참고문헌 출판년도
			String artcl_jn_year = artcl.getPL_RAI_JOUR_YEAR();			
			//참고문헌 Vol
			String artcl_vol = artcl.getPL_RAI_JOUR_VOL();
			//참고문헌 page
			String artcl_page = artcl.getPL_RAI_ARTCL_ST_PAGE();
			//평가 유무
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
					     	 if(artcl_assess.equalsIgnoreCase("V")){//유효한 데이터
					     	%>
					     		유효
					     	<%			     		 
					     	 }else if(artcl_assess.equalsIgnoreCase("R")){//기각
							%>
						    	기각
						    <%			     		 
						     }else if(artcl_assess.equalsIgnoreCase("H")){//평가유보
							%>
								유보
							<%			     		 
						     }else if(artcl_assess.equalsIgnoreCase("N")){//평가대기
							%>
								대기
							<%			     		 
						     }
					     	%>
					    </td >
			    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
			    	<%
			    		if("V".equalsIgnoreCase(artcl_assess)){
			    			%>
					     	<a href="javascript:void(0);"  onClick="viewAssessInfo('<%=pr_no%>'); return false;"> 
					     		<font color="blue"> 보기</font>
					     	</a>
					     	<%	
			    		}else{
			    			%>
					     	<a href="javascript:void(0);"  onClick="insertAssessInfo('<%=pr_no%>'); return false;"> 
					     		<font color="red"> 평가</font> 
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