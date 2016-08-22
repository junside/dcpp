<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.properties.db.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>    
<jsp:useBean id="property_info" class="nfri.dcpp.properties.business.Ctr_Property_Info_Process" scope="page"/>
<jsp:useBean id="assess_info" class="nfri.dcpp.properties.business.Ctr_Property_Assess_Info" scope="page"/>
<%
	String _oCol = request.getParameter("_oCol");
	String _oDir = ComUtil.isNullToAscString(request.getParameter("_oDir"));

	Vector pt_info = property_info.selectPropertyList(_oCol, _oDir); 
	int pt_count = pt_info.size();
	String pr_no = request.getParameter("pr_no");
	String user_id = (String)session.getAttribute("user_id");
	
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
totalRecord = pt_info.size();
//페이지별 게시물의 시작번호 계산
beginPerPage = nowPage * numPerPage;
//전체 페이지 수 계산
totalPage =(int)Math.ceil((double)totalRecord/numPerPage);
//전체 블록 수 계산
totalBlock =(int)Math.ceil((double)totalPage/pagePerBlock);
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
function setOrderDirection(column){	
	var dir = "asc";
	var form2 = document.form1;
	var direction = form2._oDir.value;
	//alert("direction="+direction+"/column="+column);
	if(direction != null ){
		if(direction !='' && direction == 'asc'){
			direction = 'desc';
		}else if(direction == null){
			direction = 'desc'
		}else{direction = 'asc'}		
		dir = direction;
	}
	form2._oDir.value = dir;
	form2._oCol.value = column;
	form2.action = 'pr_common_list.jsp';
	form2.submit();
}
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
    form2.action = "pr_common_list.jsp";
	form2.submit();	
}

function selectBlock(nowBlock, nowPage)//목록 가기
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "pr_common_list.jsp";
	form2.submit();	
}

function selectAfterBlock(nowBlock, nowPage)//다음 목록 가기
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "pr_common_list.jsp";
	form2.submit();	
}

function insertNewProperty(){ //새로운 물성정보 입력
	if(saveMsg('0') == true){
		var form2 = document.form1;
		//form2.href = "_self";
		form2.action = "pr_insert_property_info.jsp";
		form2.submit();
	}
}

function viewBasicInfo(pr_no, artcl_no){ //기본정보 보기
	if(saveMsg('1') == true){
		window.open("pr_view_property_info.jsp?pr_no="+pr_no+"&artcl_no="+artcl_no,"property_info","width=500,height=400,toolbar=no,location=no,status=no");
		return;
	}
}

function viewUnitInfo(pr_no){ //단위정보 보기
	if(saveMsg('2') == true){
		window.open("pr_view_graph_unit.jsp?pr_no="+pr_no,"unit_info","width=100%,height=100%,toolbar=no,location=no,status=no");
		return;
	}
}

function insertUnitInfo(pr_no){ //단위정보 입력
	if(saveMsg('3') == true){
		var form2 = document.form1;
		form2.action = "pr_insert_graph_info.jsp?pr_no="+pr_no;
		form2.submit();
	}
}

function viewDataInfo(pr_no){ //수치정보 보기
	if(saveMsg('4') == true){
		window.open("pr_view_graph_data.jsp?pr_no="+pr_no,"property_info","width=100%,height=100%,toolbar=no,location=no,status=no");
		return;
	}
}

function insertDataInfo(pr_no){ //수치정보 입력
	if(saveMsg('5') == true){
		var form2 = document.form1;
		form2.action = "pr_insert_graph_data.jsp?pr_no="+pr_no;
		form2.submit();
	}
}

function viewGraphInfo(pr_no){ //그래프 보기
	if(saveMsg('6') == true){
		window.open("pr_view_graph.jsp?pr_no="+pr_no,"graph_view","width=100%,height=100%,toolbar=no,location=no,status=no");
		return;
		//var form2 = document.form1;
		//form2.action = "pr_draw_graph_data.jsp?pr_no="+pr_no;
		//form2.submit();
	}
}

function viewCompleteGraphInfo(pr_no){ //완료그래프 보기
	if(saveMsg('6') == true){
		window.open("pr_view_graph.jsp?pr_no="+pr_no,"complete_graph_view","width=700,height=100%,toolbar=no,location=no,status=no");
		//window.open("pr_draw_graph_data_complete.jsp?pr_no="+pr_no,"complete_graph_view","width=650,height=600,toolbar=no");
		return;
		//var form2 = document.form1;
		//form2.action = "pr_draw_graph_data_complete.jsp?pr_no="+pr_no;
		//form2.submit();
	}
}
function viewAssessInfo(pr_no){ //평가정보 보기
	checkSessionUserID();
	if(saveMsg('7') == true){
		window.open("pr_view_property_assess_info.jsp?pr_no="+pr_no,"property_info","width=700,height=800,toolbar=no,location=no,status=no,scrollbars=yes");
		return;
	}
}

function insertAssessInfo(pr_no){ //평가 정보 입력
	checkSessionUserID();
	if(saveMsg('8') == true){
		var form2 = document.form1;
		//window.open("pr_insert_property_assess.jsp?pr_no="+pr_no,"property_info","width=650,height=650,toolbar=no");
		form2.target = "_self";
		form2.action = "pr_insert_property_assess.jsp?pr_no="+pr_no;
		form2.submit();
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
			var result = confirm("새로운 물성정보를 입력하시겠습니까?");
			return result;
			break;
		case '1': //기본정보 보기
			var result = confirm("물성 기본 정보를 확인하시겠습니까?");
			return result;
			break;
		case '2': //단위정보 보기
			var result = confirm("물성 그래프 단위 정보를 확인하시겠습니까?");
			return result;
			break;
		case '3': //단위정보 입력
			var result = confirm("물성 그래프 단위 정보를 입력하시겠습니까?");
			return result;
			break;
		case '4': //수치정보 보기
			var result = confirm("물성 그래프 수치 정보를 확인하시겠습니까?");
			return result;
			break;
		case '5': //수치정보 입력
			var result = confirm("물성 그래프 수치 정보를 입력하시겠습니까?");
			return result;
			break;
		case '6': //그래프정보 보기
			var result = confirm("그래프 정보를 확인하시겠습니까?");
			return result;
			break;
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
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<input type="hidden" name="nowPage" value="<%=nowPage%>"/>
<input type="hidden" name="nowBlock" value="<%=nowBlock%>"/>
<input type="hidden" name="pr_no" value="<%=pr_no%>"/>
<input type="hidden" name="user_id" value="<%=user_id%>"/>
<input type="hidden" name="_oDir" value="<%=_oDir%>"/>
<input type="hidden" name="_oCol" value="<%=_oCol%>"/>
<span class="help">
검색 결과 : <%=totalRecord%>건 (<font color=red> <%=totalRecord==0?"0":nowPage + 1%>/<%=totalPage %> Pages</font>)
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
	     	<A HREF="javascript:setOrderDirection('pr_no')">물성정보번호</A>
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
	    	<A HREF="javascript:setOrderDirection('assess_state')">평가상태</A>
	    </th>
	    <th class="thc">
	      	평가정보
	    </th>
	  </tr>
	  <%
	  	for (int i = beginPerPage ; i < (beginPerPage+numPerPage); i++) {
	  	if(i == totalRecord || totalRecord == 0)
	  	{
	  		break;
	  	}
	  	Properties_Basic_List_Info prot = (Properties_Basic_List_Info) pt_info.elementAt(i);
	  	
	  	String pro_no = prot.getPL_BI_DATA_NUM();
	  	
	  	String artcl_no = prot.getPL_RAI_ARTCL_NUM();
	  	boolean unitdata = prot.isPL_GRAPH_UNIT_DATA();
	  	boolean data = prot.isPL_GRAPH_DATA();
	  	String flag = prot.getPL_BI_INSERT_FLAG();

	  %>
	  		<tr>
			    <td class="tdc">
			     	<%=i+1%>
			    </td >
			    <td class="tdc">
			     	 <%=pro_no%>
			     	 <input type="hidden" name="pr_no" value='<%=pro_no%>'>
			    </td >
			    
			    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
			     	<%//기본정보%>
			     	<a href="javascript:void(0);"  onClick="viewBasicInfo('<%=pro_no%>','<%=artcl_no%>'); return false;"> 보기 </a>
			    </td >
			    <%
			    	//미 완료 상태일 경우
			     	if(!flag.equalsIgnoreCase("C")){
			     		%>
			     		<td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
					     	<%
					     	//단위정보
					     	 if(unitdata == true){
						     	%>
						     	<a href="javascript:void(0);"  onClick="viewUnitInfo('<%=pro_no%>'); return false;"> 보기 </a>
						     	<a href="javascript:void(0);"  onClick="insertUnitInfo('<%=pro_no%>'); return false;"> 수정 </a>
						     	<%			     		 
					     	 }else{
					     		%>
						     	<a href="javascript:void(0);"  onClick="insertUnitInfo('<%=pro_no%>'); return false;"> 입력 </a>
						     	<%
					     	 }
					     	%>
					    </td >
					    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
					     	<%
					     	//단위 정보가 있을 경우만
					     	if(unitdata == true){
						     	//수치정보
						     	 if(data == true){
							     	%>
							     	<a href="javascript:void(0);"  onClick="viewDataInfo('<%=pro_no%>'); return false;"> 보기 </a>
							     	<a href="javascript:void(0);"  onClick="insertDataInfo('<%=pro_no%>'); return false;"> 수정 </a>
							     	<%			     		 
						     	 }else{
						     		%>
							     	<a href="javascript:void(0);"  onClick="insertDataInfo('<%=pro_no%>'); return false;"> 입력 </a>
							     	<%
						     	 }
					     	}
					     	%>
					    </td >
					    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
					     	<%
					     	//그래프 정보
					     	 if(data == true){
						     	%>
						     	<a href="javascript:void(0);"  onClick="viewGraphInfo('<%=pro_no%>'); return false;"> 보기 </a>
						     	<%
					     	 }
					     	%>
					    </td >
					    <td class="tdc">
					    -
					    </td>
					    <td class="tdc">
					    -
					    </td>
			     		<%			     		
			     	}else{ //완료 상태 일경우
			     		Properties_Assess_List_Info proa = (Properties_Assess_List_Info) assess_info.selectAssessBasicInfo(pro_no);
			    	  	
			    	  	String assess = proa.getPL_ASSESS_DATA();
			     		%>
			     		<td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
			     			<a href="javascript:void(0);"  onClick="viewUnitInfo('<%=pro_no%>'); return false;"> 보기 </a>
					    </td >
					    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
					    	<a href="javascript:void(0);"  onClick="viewDataInfo('<%=pro_no%>'); return false;"> 보기 </a>
					    </td >
					    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
					     	<a href="javascript:void(0);"  onClick="viewCompleteGraphInfo('<%=pro_no%>'); return false;"> 보기 </a>
					    </td >
					    <td class="tdc">
					     	<%			     	
					     	 if(assess.equalsIgnoreCase("V")){//유효한 데이터
					     	%>
					     		유효데이터
					     	<%			     		 
					     	 }else if(assess.equalsIgnoreCase("R")){//기각
							%>
						    	기각
						    <%			     		 
						     }else if(assess.equalsIgnoreCase("H")){//평가유보
							%>
								평가유보
							<%			     		 
						     }else if(assess.equalsIgnoreCase("N")){//평가대기
							%>
								대기
							<%			     		 
						     }
					     	%>
					    </td >
					    <td class="tdc" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
					     	<%
					     	//평가정보
					     	 if(assess.equalsIgnoreCase("H")){
						     	%>
						     		<a href="javascript:void(0);"  onClick="viewAssessInfo('<%=pro_no%>'); return false;"> 보기 </a>
						     		<a href="javascript:void(0);"  onClick="insertAssessInfo('<%=pro_no%>'); return false;"> 평가 </a>
						     	<%
					     	 }else if(assess.equalsIgnoreCase("R") || assess.equalsIgnoreCase("V")){
						     	%>
						     		<a href="javascript:void(0);"  onClick="viewAssessInfo('<%=pro_no%>'); return false;"> 보기 </a>
						     		<a href="javascript:void(0);"  onClick="insertAssessInfo('<%=pro_no%>'); return false;"> 재평가 </a>
						     	<%
					     	 }else if(assess.equalsIgnoreCase("N")){
						     	%>
						     		<a href="javascript:void(0);"  onClick="insertAssessInfo('<%=pro_no%>'); return false;"> 평가 </a>
						     	<%
					     	 }else{
					     		%>
						     		<a href="javascript:void(0);"  onClick="viewAssessInfo('<%=pro_no%>'); return false;"> 보기 </a>
						     	<%
					     	 }
					     	%>
					    </td >
			     		<%
			     	}
			    %>
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
    	<div align="right"><a href="javascript:insertNewProperty()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','images/img_cont/new_property_upper.gif',1)"><img src="images/img_cont/new_property_normal.gif" name="Image1" width="150" height="24" border="0" id="Image1" /></a></div>
    </td>
   </tr>
</table>
</form>
</body>
</html>