<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="ctr_data" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
<jsp:useBean id="property_data" class="nfri.dcpp.properties.business.Ctr_Property_Search_Process" scope="page"/>

<%
	/**
	* 그래프 데이터 정보를 출력하는 JSP 페이지(view)
	* by Junside(J.H Park)
	*/

	String pr_no = "";//"B200900039";//request.getParameter("pr_no");
	String d_index = "1";//request.getParameter("d_index");
	//String xax_cal = "1E0";//request.getParameter("xax_cal");
	//String xay_cal = "1E0";//request.getParameter("xay_cal");
	String param_TB = request.getParameter("tb_option"); //대분류
	String param_TB_exp = ctr_data.getExpName(param_TB); //대분류
	String param_DB = request.getParameter("db_option"); //데이터분류
	String param_DB_exp = ctr_data.getExpName(param_DB); //데이터분류
	String param_part1 = request.getParameter("part_1_id"); //표적입자
	String param_part1_sym = request.getParameter("part_1_sym");//표적입자 표현식
	String param_part2 = ComUtil.isNullToDashString(request.getParameter("part_2_id")); //입사입자
	String param_MP = request.getParameter("mp_option"); //주프로세스
	String param_MP_exp = ctr_data.getExpName(param_MP); //주프로세스
	String param_srd = request.getParameter("srd_value"); //참조표준인증여부	
	String gotoindex = request.getParameter("gotoindex"); //선택되어진 항목값
	
	//String param_IC = request.getParameter("ic_option"); //충돌방식
	
	
	//String checkDraw = ComUtil.isNullToDashString(request.getParameter("checkDraw")); //선택되어진 항목값을 그리는 플래그 변수
	//체크된 리스트 저장하는 변수
	String checkList = ComUtil.isNullToEmptyString(request.getParameter("checkList"));
	//String before_checkList = checkList;
	//String before_checkList = ComUtil.isNullToEmptyString(request.getParameter("before_checkList"));
	//Where 조건절
	String whereCondtion = property_data.makeSearchQueryOption(request);
	String str_where_option_order_by = " ORDER BY BASIC.PL_BI_DATA_NUM, BASIC.PL_BI_IMP_CLASS ASC ";
	String str_where_option_data_and = " AND BASIC.PL_BI_DATA_NUM = GRAPH.PL_BI_DATA_NUM ";	
	//리스트로 보여줄 물성정보들을 가져옴
	String str_option = whereCondtion + str_where_option_order_by;
	Vector pd_info = property_data.searchPropertyListInfo(str_option); // OK
	
	whereCondtion = whereCondtion + str_where_option_data_and;
	//Y축 제목 표시
	String Y_Title = ctr_data.getExpName(param_DB);
	
	//String whereCondtion = property_data.getConditionInfo(request);
	//System.out.println("whereCondtion : " + whereCondtion);
	
	//리스트에 포함된 물성정보를 토대로 그래프 데이터를 가져옴	
	//Vector gd_info = graph_data.selectGraphBasicData(pr_no);
	int dt_count = 0;//dt_info.size();
	
	//페이지 설정 관련 변수 선언
	int totalRecord = 0; //전체 레코드 수
	int numPerPage = 10; //페이지당 레코드 수
	int pagePerBlock = 10; //블록당 페이지 수
	int totalPage = 0; //전체 페이지 수
	int totalBlock = 0; //전체 블록 수
	int nowPage = 0; //현재 페이지
	int nowBlock = 0; //현재 블록
	int beginPerPage = 0; //페이지의 시작번호
	//리스트 선택시 체크된 값을 Where 조건절에 IN 항목으로 넣기 위해 설정하는 변수들.
	String before_page = ComUtil.isNullToDashString(request.getParameter("before_page"));
	String before_block = ComUtil.isNullToDashString(request.getParameter("before_block"));
	if(before_page.equalsIgnoreCase("-")){
		before_page = Integer.toString(nowPage);
	}
	if(before_block.equalsIgnoreCase("-")){
		before_block = Integer.toString(nowBlock);
	}
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
	totalRecord = pd_info.size();
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
<OBJECT width = "0" height = "0"
ID="ZTransferX" CLASSID="CLSID:C7C7225A-9476-47AC-B0B0-FF3B79D55E67" 
codebase="http://dcpp.nfri.re.kr/oz/viewer/ZTransferX.cab#version=2,2,0,8">            <!-- 뷰어를 설치하는 ZTransferX위치,버전 지정 -->
<PARAM NAME="download.Server" VALUE="http://dcpp.nfri.re.kr/oz/viewer/">               <!-- 뷰어 위치 지정 (포트제외) -->
<PARAM NAME="download.Port" VALUE="80">                                         <!-- 뷰어 위치의 포트 지정 -->
<PARAM NAME="download.Instruction" VALUE="ozviewer.idf">                       <!-- 뷰어 버전 정보 파일 -->
<PARAM NAME="install.Base" VALUE="<PROGRAMS>/Forcs">
<PARAM NAME="install.Namespace" VALUE="plasma">                          <!-- 로컬에 설치할 뷰어이름 -->
</OBJECT>
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
    form2.action = "pr_search_property_result.jsp";
    ///form2.encoding = "application/x-www-form-urlencoded";    
	form2.submit();	
}

function selectBlock(nowBlock, nowPage)//목록 가기
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "pr_search_property_result.jsp";
    ///form2.encoding = "application/x-www-form-urlencoded";    
	form2.submit();	
}

function selectAfterBlock(nowBlock, nowPage)//다음 목록 가기
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "pr_search_property_result.jsp";
    ///form2.encoding = "application/x-www-form-urlencoded";    
	form2.submit();	
}

function viewGraphInfo(pr_no){ //그래프 보기
	if(saveMsg('0') == true){
		window.open("e_pr_view_graph.jsp?pr_no="+pr_no,"graph_info","width=100%,height=100%,toolbar=no,location=no,status=no");
		return;
	}
}

//function viewArticleInfo(artcl_no){ //참고문헌 보기
//	if(saveMsg('1') == true){
//		window.open("pr_view_article_info.jsp?artcl_no="+artcl_no,"article_info","width=100%,height=100%,toolbar=no,location=no,status=no");
//		return;
//	}
//}

//function viewDataInfo(pr_no){ //수치정보 보기
//	if(saveMsg('2') == true){
//		window.open("pr_view_graph_data.jsp?pr_no="+pr_no,"property_info","width=100%,height=100%,toolbar=no,location=no,status=no");
//		return;
//	}
//}

function viewBasicInfo(pr_no, artcl_no){ //기본정보 보기
	if(saveMsg('3') == true){
		window.open("e_pr_view_property_info.jsp?pr_no="+pr_no+"&artcl_no="+artcl_no,"property_info","width=100%,height=100%,toolbar=no,location=no,status=no");
		return;
	}
}

function saveMsg(number){
	var num = number;
	switch(num) {
		case '0': //그래프정보 보기
			var result = confirm("Would you like to see the information of a graph?");
			return result;
			break;
		case '1': //참고문헌 보기
			var result = confirm("Would you like to see the information of a reference?");
			return result;
			break;
		case '2': //수치정보 보기
			var result = confirm("Would you like to see the information of a numeric data?");
			return result;
			break;
		case '3': //기본정보 보기
			var result = confirm("Would you like to see the information of a properties?");
			return result;
			break;
		case '4': //Text 정보 보기
			var result = confirm("Would you like to see the text type of properties information?");
			return result;
			break;
		default :		
			break;
	}		
}

function addCheckList(){
	var form2 = document.form1;
	var check = form2.check_pr_no;
	var check_string = form2.checkList.value; //리스트를 저장하는 파라미터 변수
	//var check_before = form2.before_checkList.value;//이전에 체크된 항목들을 담고 있는 변수
	//alert("이전에 체크된 항목들을 담고 있는 변수 : " + check_before);
		
	//var st_before_page = form2.before_page.value;
	//var st_before_block = form2.before_block.value;
	//var st_now_page = form2.nowPage.value;
	//var st_now_block = form2.nowBlock.value;
	//alert("st_before_page : "+st_before_page + ", st_now_page : " + st_now_page);
	//alert("st_before_block : "+st_before_block + ", st_now_block : " + st_now_block);
	
	//현재 체크된 항목들을 담는 변수
	var check_now = "";
	var j = 0; //체크 여부 확인 변수
	for(var i = 0; i < check.length; i++){		
		if(check[i].checked){
			j++;
			if(j==1){
				check_now = check_now + check[i].value;
			}else{
				check_now = check_now + "," + check[i].value;
			}		 
		}
	}
	form2.checkList.value = check_now;
	//if(st_before_page != st_now_page || st_before_block != st_now_block)
	//{
	//	alert("j : " + j);
	//	if(j > 0){ //한개도 체크 되어 있으면
	//		check_string = check_before + "," + check_now;//check_string + "," + check_now;
	//		form2.checkList.value = check_string;
	//	}else{//한개도 안되어 있으면 예전 그대로
	//		form2.checkList.value = check_before;
	//	}		
	//}else{
	//	form2.checkList.value = check_now;		
	//}
	//alert("현재 선택된 리스트 파라미터 : " + form2.checkList.value);
}

function toggleCheckBox(obj, target){
    if ( !obj ) return false;
    if ( !target ) return false;
    
    if ( target.length ) {
        for ( var i = 0 ; i < target.length ; i++ ){
            //target[i].setAttribute("checked", obj.getAttribute("checked"));
            target[i].checked = obj.checked? true:false
        }
    }
    else{
        target.setAttribute("checked", obj.getAttribute("checked"));
    }
}

function refreshInfo(){
	var form2 = document.form1;
	var check = form2.check_pr_no;
	//form2.checkDraw.value="D";
	var count = 0;
	form2.target = "_self";
    form2.action = "e_pr_search_property_result.jsp";	    
	form2.submit();
	//for(var i = 0; i < check.length; i++){
	//	if(check[i].checked){
			//alert(check[i].value);
	//		count ++;
	//	}
	//}
	//if(count == 0){		
	//	alert("체크박스를 선택하세요.");
	//	return;
	//}else{
	    
	//}
}

function getPropertyInfo(){
	var form2 = document.form1;
	var check = form2.check_pr_no;
	var count = 0;
	var check_value = "";
	for(var i = 0; i < check.length; i++){
		if(check[i].checked==true){
			//alert(check[i].value);			
			/* if(count > 0){
				if(count == check.length){
					check_value += check[i].value";
					//check_value += "&check=" +"'"+check[i].value+"'";
				}else{
					check_value += "&check="+check[i].value+",";
					//check_value += "&check=" +"'"+check[i].value+"',";	
				}
			}else{
				check_value += "'"+check[i].value+"'";
				//check_value += "'"+check[i].value+"'";	
			} */
			check_value += check[i].value+",";
			count++;
		}
	}
	if(count == 0){		
		alert("Please select the checkbox of properties number to see!");
		return;
	}else{
		if(check[0].checked==true && count==1){
			alert("Please select the checkbox of properties number to see!");
			return;
		}else{
			if(saveMsg('4') == true){
				//alert("체크박스 선택 :" + count);
				//alert(check_value);
				window.open("e_pr_view_text_property_info.jsp?check="+check_value,"property_info","width=700,height=600,scrollbars=yes");
				return;
			}
		}
		
		//window.open("pr_select_target_part.jsp?id="+id+"&sym="+sym+"&db="+db+"&mp="+mp,"select_Part","width=700,height=600,scrollbars=yes");
	}
	
	//var form2 = document.form1;
	//form2.target = "_self";
    //form2.action = "pr_search_property_result.jsp";	    
	//form2.submit();
}

</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST" >
<input type="hidden" name="pr_no" value="<%=pr_no%>">
<input type="hidden" name="tb_option" value="<%=param_TB%>"/>
<input type="hidden" name="db_option" value="<%=param_DB%>"/>
<input type="hidden" name="part_1_id" value="<%=param_part1%>"/>
<input type="hidden" name="part_1_sym" value="<%=param_part1_sym%>"/>
<input type="hidden" name="part_2_id" value="<%=param_part2%>"/>
<input type="hidden" name="mp_option" value="<%=param_MP%>"/>
<input type="hidden" name="srd_value" value="<%=param_srd%>"/>
<input type="hidden" name="gotoindex" value="<%=gotoindex%>"/>
<input type="hidden" name="checkList" value="<%=checkList%>"/>
<p class="level2">Search Conditions</p>
<span class="help"><font color = red>Categorize : </font><%=param_TB_exp%></span><br/>
<span class="help"><font color = red>Collision data categorize : </font><%=param_DB_exp%></span><br/>
<span class="help"><font color = red>Collision process : </font><%=param_MP_exp%></span><br/>
<span class="help"><font color = red>Target particle expression : </font><%=param_part1_sym%></span><br/>
<p class="level2">Graph</p>
<table class="tab5">
<col width="110">
<col width="540">
  <tr>
    <th colspan="2" class="thc">Graph</th>
    </tr>
  <tr>
  	<td colspan="2" class="tdc">
  	<object id="ozviewer" width="645" height="500" CLASSID="CLSID:0DEF32F8-170F-46f8-B1FF-4BF7443F5F25">
            <param name="connection.servlet" value="http://dcpp.nfri.re.kr/oz/server">
            <param name="connection.reportname" value="/ozr_pr_search_property_result.ozr">
			<param name="viewer.usestatusbar" value="false">	<!-- 아래쪽 상태표시줄을 사용하지 않습니다 -->
			<param name="viewer.viewmode" value="Fittocontents">  <!-- 뷰어 사이즈를 보고서  컨텐츠에 맞춥니다 -->
			<param name="viewer.bgcolor" value="ffffff">	<!-- 뷰어 백그라운드 색상을 지정합니다 -->
			<param name="viewer.useoutborder" value="false">	<!-- 외곽 테두리를 사용하지 않습니다 -->
			<param name="viewer.useinborder" value="false">	<!-- 내부 테두리를 사용하지 않습니다 -->
			<param name="toolbar.all" value="true">	<!-- 툴바를 사용 -->
			<param name="toolbar.about" value="true">
			<param name="toolbar.close" value="false">
			<param name="toolbar.file" value="true">
			<param name="toolbar.jpg" value="true">
			<param name="toolbar.pdf" value="true">
			<param name="toolbar.ppt" value="true">
			<param name="toolbar.tiff" value="true">
			<param name="toolbar.find" value="false">
			<param name="toolbar.help" value="false">
			<param name="toolbar.open" value="false">
			<param name="toolbar.page" value="false">
			<param name="toolbar.print" value="true">
			<param name="toolbar.save" value="false">
			<param name="toolbar.pagenavigator" value="false">
			<param name="toolbar.pageselection" value="false">
			<param name="toolbar.viewmode" value="true">
			<param name="toolbar.option" value="false">
            <param name="viewer.isFrame" value="false">
			<param name="viewer.Namespace" value="plasma\ozviewer">
			<param name="viewer.configmode" value="html">
			<param name="odi.odinames" value="odi_pr_search_property_result">
			<param name="odi.odi_pr_search_property_result.pcount" value="2">
			<param name="odi.odi_pr_search_property_result.args1" value="PM_Y_TITLE=<%=Y_Title%>">
			<param name="odi.odi_pr_search_property_result.args2" value="PM_WHERE_CONDITION=<%=whereCondtion%>">
        </object>
   	</td>
    </tr>
</table>
<div class="bbtn_r">
<a href="javascript:refreshInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('refreshImage','','images/img_cont/eng/refresh_upper.gif',1)"><img src="images/img_cont/eng/refresh_normal.gif" name="refreshImage" width="150" height="24" border="0" id="refreshImage" /></a>
</div>
<p class="level2">List of search result</p>
<span class="help">
If you want to see the numeric data, please click button at the graph field.
</span><br>
<span class="help">
If you want to see the reference data, please click properties number.
</span><br>
<span class="count">
Result count : <%=totalRecord%> 
</span>
<table class="tab5">
<col width="30">
<col width="60">
<col width="60">
<col width="160">
<col width="100">
<col width="100">
<col width="120">
<tr>
	<th class="thc">
	<input type="checkbox" id="" name="check_pr_no" value="" onclick="toggleCheckBox(this, document.getElementsByName('check_pr_no'));" />
   </th >
   <th class="thc">
    	 No
   </th >
   <th class="thc">
     	Graph
   </th>
   <th class="thc">
     	Expression
   </th><th class="thc">
   		Collision type
   </th><th class="thc">
   		Sub Process
   </th>
   <th class="thc">
     	Exp./Theory
   </th>
 </tr>
  <%
	  	for (int i = 0 ; i < pd_info.size(); i++) {
	  	//if(i == totalRecord || totalRecord == 0)
	  	//{
	  	//	break;
	  	//}
	  	Properties_Basic_Info prot = (Properties_Basic_Info) pd_info.elementAt(i);
	  	String seach_pr_no = prot.getPL_BI_DATA_NUM();	 //물성번호 	
	  	String artcl_no = prot.getPL_RAI_ARTCL_NUM(); //참고문헌 번호
	  	String expresstion = prot.getPL_BI_EXPRESSION(); //표현식
	  	String ic_option = prot.getPL_BI_IMP_CLASS(); //충돌방식
	  	String mp_option = prot.getPL_BI_MAIN_PROC(); //주프로세스
	  	String sp_option = prot.getPL_BI_SUB_PROC(); //부프로세스
	  	String process_option = sp_option;
	  	String exp_option = prot.getPL_BI_EXP_THE_REC(); // 검증구분
	  	String flag = ""; //입력필드
	  	/* if(prot.getPL_BI_INSERT_FLAG().equalsIgnoreCase("I")){
	  		flag = "입력중";
	  	}else{
	  		flag = "입력완료";
	  		 ////ComUtil.getAbbrString(exp_option, 10);
	  	} */

	  %>
	  		<tr>
	  			<td class="tdc">
			     	 <input type="checkbox" name="check_pr_no" value="<%=seach_pr_no%>" onclick="addCheckList()">
			    </td >
			    <td class="tdc">
			    <%//기본정보%>
			     	<a href="javascript:void(0);"  onClick="viewBasicInfo('<%=seach_pr_no%>','<%=artcl_no%>'); return false;"><%=seach_pr_no%></a>
			    </td >
			    <td class="tdc">
					<a href="javascript:void(0);"  onClick="viewGraphInfo('<%=seach_pr_no%>'); return false;">
						<img src = "images/file_img/graph.jpg" width="16" height="16" border="0"> 
					</a>
			    </td >
			    <td class="tdl">
			     	<%=expresstion%>
			    </td >
			    <td class="tdc">
			     	<%=ic_option%>
			    </td >
			    <td class="tdc">
			     	<%=process_option%>
			    </td >
	     		<td class="tdc">
	     			<%=exp_option%>
	     		</td >	     		
		  	</tr>
	  		<%
	  	}
	  	
	  %>
</table>
<div class="bbtn_r">
<a href="javascript:getPropertyInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('getListImage','','images/img_cont/eng/get_info_list_upper.gif',1)"><img src="images/img_cont/eng/get_info_list_normal.gif" name="getListImage" width="150" height="24" border="0" id="getListImage" /></a>
</div>
</form>
</body>
</html>