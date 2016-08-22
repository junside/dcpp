<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="data_info" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<%
	/**
	* 그래프 데이터 정보를 입력하는 JSP 페이지(view)
	* by Junside(J.H Park)
	*/

	String v_pr_no = request.getParameter("v_pr_no");
	
	Vector dt_info = data_info.selectViewInfGrdGraphData(v_pr_no);
	int vid_dt_count = dt_info.size();
	
	//페이지 설정 관련 변수 선언
	int vid_totalRecord = 0; //전체 레코드 수
	int vid_numPerPage = 10; //페이지당 레코드 수
	int vid_pagePerBlock = 10; //블록당 페이지 수
	int vid_totalPage = 0; //전체 페이지 수
	int vid_totalBlock = 0; //전체 블록 수
	int vid_nowPage = 0; //현재 페이지
	int vid_nowBlock = 0; //현재 블록
	int vid_beginPerPage = 0; //페이지의 시작번호

	//현재 페이지 설정
	if(request.getParameter("vid_nowPage") != null)
	{
		vid_nowPage = Integer.parseInt(request.getParameter("vid_nowPage"));
	}
	//현재 블록 설정
	if(request.getParameter("vid_nowBlock") != null)
	{
		vid_nowBlock = Integer.parseInt(request.getParameter("vid_nowBlock"));
	}
	//전체 개수 저장
	vid_totalRecord = vid_dt_count;
	//페이지별 게시물의 시작번호 계산
	vid_beginPerPage = vid_nowPage * vid_numPerPage;
	//전체 페이지 수 계산
	vid_totalPage =(int)Math.ceil((double)vid_totalRecord/vid_numPerPage);
	//전체 블록 수 계산
	vid_totalBlock =(int)Math.ceil((double)vid_totalPage/vid_pagePerBlock);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>등급유력 플라즈마 물성 수치 데이터 확인</title>
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

function confirmInfo(){ //확인
	self.close();
}

function selectBeforeBlock(vid_nowBlock, vid_nowPage)//이전 목록 가기
{
    var form2 = document.form1;
    form2.vid_nowBlock.value = vid_nowBlock;
    form2.vid_nowPage.value = vid_nowPage;
    form2.target = "_self";
    form2.action = "pr_view_inf_grd_graph_data.jsp";   
	form2.submit();	
}

function selectBlock(vid_nowBlock, vid_nowPage)//목록 가기
{
    var form2 = document.form1;
    form2.vid_nowBlock.value = vid_nowBlock;
    form2.vid_nowPage.value = vid_nowPage;
    form2.target = "_self";
    form2.action = "pr_view_inf_grd_graph_data.jsp"; 
	form2.submit();	
}

function selectAfterBlock(vid_nowBlock, vid_nowPage)//다음 목록 가기
{
    var form2 = document.form1;
    form2.vid_nowBlock.value = vid_nowBlock;
    form2.vid_nowPage.value = vid_nowPage;
    form2.target = "_self";
    form2.action = "pr_view_inf_grd_graph_data.jsp"; 
	form2.submit();	
}

</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST" >
<div id="content">
<input type="hidden" name="v_pr_no" value='<%=v_pr_no%>'>
<input type="hidden" name="v_dt_count" value='<%=vid_dt_count%>'>
<input type="hidden" name="vid_nowPage" value="<%=vid_nowPage%>"/>
<input type="hidden" name="vid_nowBlock" value="<%=vid_nowBlock%>"/>
<p class="level2">등급유력 플라즈마 물성 그래프 데이터 확인</p>
<table class="tab2">
<col width="120">
<col width="530">

  <tr>
    <th class="thc">등급 유력 번호</th>
    <td class="tdl"><%=v_pr_no%>  
    </td>
  </tr>
</table>

<p class="level2">등급 유력 플라즈마 물성 그래프 데이터</p>
<span class="count">
데이터 : <%=vid_totalRecord%>건 (<font color=red> <%=vid_totalRecord==0?"0":vid_nowPage + 1%>/<%=vid_totalPage %> Pages</font>)
</span>
<table class="tab2">
<col width="50">
<col width="70">
<col width="70">
<col width="100">
<col width="100">
<col width="100">
<col width="70">
<col width="70">
<tr>
   <th class="thc">
    	 No
   </th >
   <th class="thc">
     	X값
   </th>
   <th class="thc">
     	Y값
   </th><th class="thc">
   		X축오차
   </th>
   <th class="thc">
     	Y축 최대오차
   </th>
   <th class="thc">
     	Y축 최소오차
   </th>
   <th class="thc">
     	생성비
   </th>
   <th class="thc">
     	압력
   </th>
 </tr>
 <%
	  	for (int i = vid_beginPerPage ; i < (vid_beginPerPage+vid_numPerPage); i++) {
			if(i == vid_totalRecord || vid_totalRecord == 0)
			{
				break;
			}
			Inf_Grd_Graph_Data_Info dti = (Inf_Grd_Graph_Data_Info) dt_info.elementAt(i);
			String pro_no =		dti.getPL_IGBI_DATA_NUM();
			String dt_no =		dti.getPL_IGGD_SEQ_NUM();
			String xval =		dti.getPL_IGGD_X_AX_VAL();
			String yval =		dti.getPL_IGGD_Y_AX_VAL();
			String xerr =		dti.getPL_IGGD_X_ERR();
			String yerrmax =	dti.getPL_IGGD_Y_ERR_MAX();
			String yerrmin =	dti.getPL_IGGD_Y_ERR_MIN();
			String s_ratio =	dti.getPL_IGGD_RATIO();
			String s_press =	dti.getPL_IGGD_PRESS();
			String s_backdata =	dti.getPL_IGGD_BACKUP_DATA();
			
	  		%>
	  		<tr onmouseover="this.bgColor='#E9E9F3'" onmouseout="this.bgColor='#FFFFFF'">
			    <td class="tdc">
			     	<A HREF="javascript:checkData('<%=dt_no%>')"><%=i+1%></A>
			    </td >
			    <td class="tdc">
			     	 <A HREF="javascript:checkData('<%=dt_no%>')"><%=xval%></A>
			    </td >
			    <td class="tdc">
			     	<A HREF="javascript:checkData('<%=dt_no%>')"><%=ComUtil.getStringByDoubleFormat(yval)%></A>
			    </td >
			    <td class="tdc">
			     	 <A HREF="javascript:checkData('<%=dt_no%>')"><%=ComUtil.getStringByDoubleFormat(xerr)%></A>
			    </td >
			    <td class="tdc">
			     	 <A HREF="javascript:checkData('<%=dt_no%>')"><%=ComUtil.getStringByDoubleFormat(yerrmax)%></A>
			    </td >
			    <td class="tdc">
			     	 <A HREF="javascript:checkData('<%=dt_no%>')"><%=ComUtil.getStringByDoubleFormat(yerrmin)%></A>
			    </td >
			    <td class="tdc">
			     	 <A HREF="javascript:checkData('<%=dt_no%>')"><%=s_ratio%></A>
			    </td >
			    <td class="tdc">
			     	 <A HREF="javascript:checkData('<%=dt_no%>')"><%=s_press%></A>
			    </td >
		  	</tr>
	  		<%
	  	}
	  	
	  %>
</table>
<div class="bbtn_c">
<%
	if(vid_totalRecord !=0)
	{
		if(vid_nowBlock > 0){
			%>
			<A HREF="javascript:selectBeforeBlock('<%=vid_nowBlock-1%>','<%=(vid_nowBlock-1)*vid_pagePerBlock %>')">이전 <%=vid_pagePerBlock%>개</A>
			<%
		}
		for(int i = 0; i<vid_pagePerBlock; i++){
			%>
			<A HREF="javascript:selectBlock('<%=vid_nowBlock%>','<%=(vid_nowBlock*vid_pagePerBlock) + i%>')">[<%=(vid_nowBlock*vid_pagePerBlock)+i+1%>]</A>
			<%
			if((vid_nowBlock*vid_pagePerBlock) + i + 1 == vid_totalPage){
				break;
			}
		}
		if(vid_totalBlock > vid_nowBlock + 1){
		%>
			<A HREF="javascript:selectAfterBlock('<%=vid_nowBlock+1%>','<%=(vid_nowBlock+1)*vid_pagePerBlock %>')">다음 <%=vid_pagePerBlock%>개</A>
		<%
		}
	}
%>
</div>
<div class="bbtn_c">
<a href="javascript:confirmInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('confirmImage','','images/img_cont/confirm_upper.gif',1)"><img src="images/img_cont/confirm_normal.gif" name="confirmImage" width="80" height="24" border="0" id="confirmImage" /></a>
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