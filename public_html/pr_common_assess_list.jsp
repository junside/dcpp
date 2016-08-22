<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.properties.db.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>    
<jsp:useBean id="assess_info" class="nfri.dcpp.properties.business.Ctr_Property_Assess_Info" scope="page"/>
<%
	Vector pt_info = assess_info.selectFinalAssessList();
	int pt_count = pt_info.size();
	String v_pr_no = request.getParameter("v_pr_no");
	String user_id = (String)session.getAttribute("user_id");
//페이지 설정 관련 변수 선언
int fi_as_totalRecord = 0; //전체 레코드 수
int fi_as_numPerPage = 10; //페이지당 레코드 수
int fi_as_pagePerBlock = 10; //블록당 페이지 수
int fi_as_totalPage = 0; //전체 페이지 수
int fi_as_totalBlock = 0; //전체 블록 수
int fi_as_nowPage = 0; //현재 페이지
int fi_as_nowBlock = 0; //현재 블록
int fi_as_beginPerPage = 0; //페이지의 시작번호

//현재 페이지 설정
if(request.getParameter("fi_as_nowPage") != null)
{
	fi_as_nowPage = Integer.parseInt(request.getParameter("fi_as_nowPage"));
}
//현재 블록 설정
if(request.getParameter("fi_as_nowBlock") != null)
{
	fi_as_nowBlock = Integer.parseInt(request.getParameter("fi_as_nowBlock"));
}
//전체 개수 저장
fi_as_totalRecord = pt_info.size();
//페이지별 게시물의 시작번호 계산
fi_as_beginPerPage = fi_as_nowPage * fi_as_numPerPage;
//전체 페이지 수 계산
fi_as_totalPage =(int)Math.ceil((double)fi_as_totalRecord/fi_as_numPerPage);
//전체 블록 수 계산
fi_as_totalBlock =(int)Math.ceil((double)fi_as_totalPage/fi_as_pagePerBlock);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
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

function selectBeforeBlock(fi_as_nowBlock, fi_as_nowPage)//이전 목록 가기
{
    var form2 = document.form1;
    form2.fi_as_nowBlock.value = fi_as_nowBlock;
    form2.fi_as_nowPage.value = fi_as_nowPage;
    form2.target = "_self";
    form2.action = "pr_common_assess_list.jsp";
	form2.submit();	
}

function selectBlock(fi_as_nowBlock, fi_as_nowPage)//목록 가기
{
    var form2 = document.form1;
    form2.fi_as_nowBlock.value = fi_as_nowBlock;
    form2.fi_as_nowPage.value = fi_as_nowPage;
    form2.target = "_self";
    form2.action = "pr_common_assess_list.jsp";
	form2.submit();	
}

function selectAfterBlock(fi_as_nowBlock, fi_as_nowPage)//다음 목록 가기
{
    var form2 = document.form1;
    form2.fi_as_nowBlock.value = fi_as_nowBlock;
    form2.fi_as_nowPage.value = fi_as_nowPage;
    form2.target = "_self";
    form2.action = "pr_common_assess_list.jsp";
	form2.submit();	
}

function insertNewFinalAssess(){ //새로운 물성정보 입력
	if(saveMsg('0') == true){
		var form2 = document.form1;
		//form2.href = "_self";
		form2.action = "pr_assess_search_inf_grd_data.jsp";
		form2.submit();
	}
}

function viewBasicInfo(v_pr_no){ //기본정보 보기
	if(saveMsg('1') == true){
		window.open("pr_view_inf_grd_property_info.jsp?v_pr_no="+v_pr_no,"property_info","width=100%,height=100%,toolbar=no,location=no,status=no");
		return;
	}
}

function viewUnitInfo(v_pr_no){ //단위정보 보기
	if(saveMsg('2') == true){
		window.open("pr_view_inf_grd_graph_unit.jsp?v_pr_no="+v_pr_no,"unit_info","width=100%,height=100%,toolbar=no,location=no,status=no");
		return;
	}
}

function viewDataInfo(v_pr_no){ //수치정보 보기
	if(saveMsg('3') == true){
		window.open("pr_view_inf_grd_graph_data.jsp?v_pr_no="+v_pr_no,"property_info","width=100%,height=100%,toolbar=no,location=no,status=no");
		return;
	}
}

function insertDataInfo(v_pr_no){ //수치정보 입력
	checkSessionUserID();
	if(saveMsg('6') == true){
		var form2 = document.form1;
		//window.open("pr_insert_property_assess.jsp?pr_no="+pr_no,"property_info","width=650,height=650,toolbar=no");
		form2.target = "_self";
		form2.action = "pr_assess_make_inf_grd_data.jsp?v_pr_no="+v_pr_no;
		form2.submit();
	}
}

function viewGraphInfo(v_pr_no){ //그래프 보기
	if(saveMsg('4') == true){
		window.open("pr_view_inf_grd_graph.jsp?v_pr_no="+v_pr_no,"graph_view","width=700,height=100%,toolbar=no,location=no,status=no");
		return;
		//var form2 = document.form1;
		//form2.action = "pr_draw_graph_data.jsp?v_pr_no="+v_pr_no;
		//form2.submit();
	}
}

function viewAssessInfo(v_pr_no){ //평가정보 보기
	checkSessionUserID();
	if(saveMsg('5') == true){
		window.open("pr_view_inf_grd_assess_info.jsp?v_pr_no="+v_pr_no,"property_info","width=700,height=500,toolbar=no,location=no,status=no,scrollbars=yes");
		return;
	}
}

function insertAssessInfo(v_pr_no){ //평가 정보 입력
	checkSessionUserID();
	if(saveMsg('7') == true){
		var form2 = document.form1;
		//window.open("pr_insert_property_assess.jsp?pr_no="+pr_no,"property_info","width=650,height=650,toolbar=no");
		form2.target = "_self";
		form2.action = "pr_assess_final_inf_grd_data.jsp?v_pr_no="+v_pr_no;
		form2.submit();
	}
}

function printAssessInfo(v_pr_no){ //평가정보 출력
	checkSessionUserID();
	if(saveMsg('6') == true){
		window.open("pr_view_property_assess_info.jsp?v_pr_no="+v_pr_no,"property_info","width=700,height=100%,toolbar=no,location=no,status=no,scrollbars=yes");
		return;
	}
}

function checkSessionUserID(){
	var form2 = document.form1;
	var member_id = "<%=(String)session.getAttribute("user_id")%>";
    if(member_id == null){    
	    form2.target = "_self";
	    form2.action = "c_login.jsp?flag=AU";
		form2.submit();
	}
}


function saveMsg(number){
	var num = number;
	switch(num) {
		case '0': //새로운 물성정보 입력하기
			var result = confirm("새로운 최종평가정보를 입력하시겠습니까?");
			return result;
			break;
		case '1': //기본정보 보기
			var result = confirm("등급유력 물성 기본 정보를 확인하시겠습니까?");
			return result;
			break;
		case '2': //단위정보 보기
			var result = confirm("등급유력 물성 그래프 단위 정보를 확인하시겠습니까?");
			return result;
			break;
		case '3': //수치정보 보기
			var result = confirm("등급유력 물성 그래프 수치 정보를 확인하시겠습니까?");
			return result;
			break;
		case '4': //그래프정보 보기
			var result = confirm("등급유력 그래프 정보를 확인하시겠습니까?");
			return result;
			break;
		case '5': //평가정보 보기
			var result = confirm("등급유력 물성 평가 정보를 확인하시겠습니까?");
			return result;
			break;
		case '6': //수치정보 입력
			var result = confirm("등급유력 물성 그래프 수치 정보를 입력하시겠습니까?");
			return result;
			break;
		case '7': //평가 입력
			var result = confirm("등급유력 물성 평가 정보를 입력하시겠습니까?");
			return result;
			break;
		default :		
			break;
	}		
}
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<input type="hidden" name="fi_as_nowPage" value="<%=fi_as_nowPage%>"/>
<input type="hidden" name="fi_as_nowBlock" value="<%=fi_as_nowBlock%>"/>
<input type="hidden" name="v_pr_no" value="<%=v_pr_no%>"/>
<input type="hidden" name="user_id" value="<%=user_id%>"/>
<span class="help">
검색 결과 : <%=fi_as_totalRecord%>건 (<font color=red> <%=fi_as_totalRecord==0?"0":fi_as_nowPage + 1%>/<%=fi_as_totalPage %> Pages</font>)
</span>
<table class="tab2">
	<col width="50">
	<col width="100">
	<col width="80">
	<col width="95">
	<col width="95">
	<col width="60">
	<col width="95">
	<col width="95">
	  <tr>
	    <th class="thc">
	     	 No
	    </th >
	    <th class="thc">
	     	 등급유력번호
	    </th>
	    <th class="thc">
	      	기본정보
	    </th>
	    <th class="thc">
	      	단위정보
	    </th>
	    <th class="thc">
	      	수치정보
	    </th>
	    <th class="thc">
	      	그래프
	    </th>
	    <th class="thc">
	      	평가정보
	    </th>
	  </tr>
	  <%
	  	for (int i = fi_as_beginPerPage ; i < (fi_as_beginPerPage+fi_as_numPerPage); i++) {
	  	if(i == fi_as_totalRecord || fi_as_totalRecord == 0)
	  	{
	  		break;
	  	}
	  	Inf_Grd_Properties_Basic_Info prot = (Inf_Grd_Properties_Basic_Info) pt_info.elementAt(i);
	  	
	  	String v_pro_no = prot.getPL_IGBI_DATA_NUM();
	  	String artcl_no = prot.getPL_IGBI_DATA_NUM_LIST();
	  	String expression = prot.getPL_IGBI_EXPRESSION();
	  	String unit = prot.getPL_IGBI_MAIN_PROC();
	  	String user = prot.getPL_UI_ID();
	  	String insert_Flag = prot.getPL_IGBI_INSERT_FLAG();
	  %>
	  		<tr>
			    <td class="tdc">
			     	<%=i+1%>
			    </td >
			    <td class="tdc">
			     	 <%=v_pro_no%>
			     	 <input type="hidden" name="v_pr_no" value='<%=v_pro_no%>'>
			    </td >			    
			    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
			     	<%//기본정보%>
			     	<a href="javascript:void(0);"  onClick="viewBasicInfo('<%=v_pro_no%>'); return false;"> 보기 </a>
			    </td >
	     		<td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
	     			<%//단위정보%>
	     			<a href="javascript:void(0);"  onClick="viewUnitInfo('<%=v_pro_no%>'); return false;"> 보기 </a>
			    </td >
			    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
			    	
			    	<%
			     	//수치정보
			     	 if(insert_Flag.equalsIgnoreCase("NI")){
				     	%>				     		
				     		<a href="javascript:void(0);"  onClick="insertDataInfo('<%=v_pro_no%>'); return false;"> 입력 </a>
				     	<%
			     	 }else{
			     		%>
				     		<a href="javascript:void(0);"  onClick="viewDataInfo('<%=v_pro_no%>'); return false;"> 보기 </a>
				     	<%
			     	 }
			     	%>
			    </td >
			    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
			     	<%//그래프정보%>
			     	<a href="javascript:void(0);"  onClick="viewGraphInfo('<%=v_pro_no%>'); return false;"> 보기 </a>
			    </td >
			    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">			    
				    <%
			     	//평가정보
			     	 if(insert_Flag.equalsIgnoreCase("AI")){
				     	%>
				     		<a href="javascript:void(0);"  onClick="insertAssessInfo('<%=v_pro_no%>'); return false;"> 입력 </a>
				     	<%
			     	 }else if(insert_Flag.equalsIgnoreCase("CC")){
			     		%>
				     		<a href="javascript:void(0);"  onClick="viewAssessInfo('<%=v_pro_no%>'); return false;"> 보기 </a>
				     	<%
			     	 }else{
			     		%>
			     		-
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
	if(fi_as_totalRecord !=0)
	{
		if(fi_as_nowBlock > 0){
			%>
			<A HREF="javascript:selectBeforeBlock('<%=fi_as_nowBlock-1%>','<%=(fi_as_nowBlock-1)*fi_as_pagePerBlock %>')">이전 <%=fi_as_pagePerBlock%>개</A>
			<%
		}
		for(int i = 0; i<fi_as_pagePerBlock; i++){
			%>
			<A HREF="javascript:selectBlock('<%=fi_as_nowBlock%>','<%=(fi_as_nowBlock*fi_as_pagePerBlock) + i%>')">[<%=(fi_as_nowBlock*fi_as_pagePerBlock)+i+1%>]</A>
			<%
			if((fi_as_nowBlock*fi_as_pagePerBlock) + i + 1 == fi_as_totalPage){
				break;
			}
		}
		if(fi_as_totalBlock > fi_as_nowBlock + 1){
		%>
			<A HREF="javascript:selectAfterBlock('<%=fi_as_nowBlock+1%>','<%=(fi_as_nowBlock+1)*fi_as_pagePerBlock %>')">다음 <%=fi_as_pagePerBlock%>개</A>
		<%
		}
	}
%>
</div>
<table width="99%" border="0" cellspacing="1" cellpadding="0">
   <tr> 
    <td width="30%">&nbsp;</td>
    <td width="30%">&nbsp;</td>
    <td width="30%">&nbsp;</td>
  </tr>
  <tr>
    <td width="30%"></td>
    <td width="30%"></td>
    <td width="30%">
    	<div align="right"><a href="javascript:insertNewFinalAssess()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('final_assess','','images/img_cont/new_final_assess_upper.gif',1)"><img src="images/img_cont/new_final_assess_normal.gif" name="final_assess" width="150" height="24" border="0" id="final_assess" /></a></div>
    </td>
   </tr>
</table>
</form>
</body>
</html>