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

	String pr_no = request.getParameter("pr_no");
	String d_index = request.getParameter("d_index");
	
	//String pr_no = request.getParameter("pr_no");//"B201000001";//
	//String dt_no = request.getParameter("dt_no");//"1";//
	//String d_index = request.getParameter("d_index");
	String dt_count = request.getParameter("dt_count");
	String x_cal = request.getParameter("xax_cal");
	String y_cal = request.getParameter("xay_cal");
		
	Graph_Basic_Info basic_info = (Graph_Basic_Info)data_info.selectGraphBasicInfo(pr_no);	
	String x_unit = basic_info.getPL_BGI_X_AX_UNIT();
	x_cal = basic_info.getPL_BGI_X_AX_CAL();
	//String x_cal = basic_info.getPL_BGI_X_AX_CAL();
	String y_unit = basic_info.getPL_BGI_Y_AX_UNIT();
	y_cal = basic_info.getPL_BGI_Y_AX_CAL();
	//String y_cal = basic_info.getPL_BGI_Y_AX_CAL();
	String y_comm = basic_info.getPL_BGI_Y_AX_COMM();

	
	Vector dt_info = data_info.selectGraphBasicData(pr_no);
	int i_dt_count = dt_info.size();
	
	//페이지 설정 관련 변수 선언
	int i_totalRecord = 0; //전체 레코드 수
	int i_numPerPage = 10; //페이지당 레코드 수
	int i_pagePerBlock = 10; //블록당 페이지 수
	int i_totalPage = 0; //전체 페이지 수
	int i_totalBlock = 0; //전체 블록 수
	int i_nowPage = 0; //현재 페이지
	int i_nowBlock = 0; //현재 블록
	int i_beginPerPage = 0; //페이지의 시작번호

	//현재 페이지 설정
	if(request.getParameter("i_nowPage") != null)
	{
		i_nowPage = Integer.parseInt(request.getParameter("i_nowPage"));
	}
	//현재 블록 설정
	if(request.getParameter("i_nowBlock") != null)
	{
		i_nowBlock = Integer.parseInt(request.getParameter("i_nowBlock"));
	}
	//전체 개수 저장
	i_totalRecord = dt_info.size();
	//페이지별 게시물의 시작번호 계산
	i_beginPerPage = i_nowPage * i_numPerPage;
	//전체 페이지 수 계산
	i_totalPage =(int)Math.ceil((double)i_totalRecord/i_numPerPage);
	//전체 블록 수 계산
	i_totalBlock =(int)Math.ceil((double)i_totalPage/i_pagePerBlock);
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

function saveInfo(){ //데이터 정보 저장
	if(saveInfoCheck() == false){
		if(confirmMsg('0') == true){
			var form2 = document.form1;				
			if(checkFile() == true){
				alert("파일로 데이터 입력시 DB에 직접 입력됩니다.\n데이터 수정시 주의하시기 바랍니다.");
				form2.action = "pr_insert_graph_data_check.jsp?d_index=1&file=1";
				form2.encoding = "multipart/form-data";
			}else{
				form2.action = "pr_insert_graph_data_check.jsp?d_index=1&file=2";
			}
			form2.submit();
		}
	}else{
		return;
	}	
}

function nextInfo(){ //다음 이동
	var form2 = document.form1;
	var count = form2.i_dt_count.value;	
	if(confirmMsg('1') == true){
		if(count > 0){
			if(checkFile() == true){
				alert("파일로 데이터 입력시 DB에 직접 입력됩니다.\n데이터 수정시 주의하시기 바랍니다.");
				form2.action = "pr_insert_graph_data_check.jsp?d_index=2&file=1";
				form2.encoding = "multipart/form-data";			
			}else{
				form2.target = "_self";
				form2.action = "pr_draw_graph_data.jsp";
			}
			form2.submit();
		}else if(checkFile() == true){
			alert("파일로 데이터 입력시 DB에 직접 입력됩니다.\n데이터 수정시 주의하시기 바랍니다.");
			form2.action = "pr_insert_graph_data_check.jsp?d_index=2&file=1";
			form2.encoding = "multipart/form-data";
			form2.submit();
		}else{
			alert("그래프 데이터는 적어도 1개 이상을 입력하셔야 합니다.");
			return;
		}	
	}
}

function exitInfo(){ //종료
	var form2 = document.form1;
	if(confirmMsg('2') == true){
		form2.target = "_self";
		form2.action = "pr_common_list.jsp";
		form2.submit();	
	}
}

function confirmMsg(number){
	var num = number;
	switch(num) {
		case '0': //정보 저장하기
			var result = confirm("입력하신 내용을 저장하시겠습니까?");
			return result;
			break;
		case '1': //다음단계로 이동
			var result = confirm("다음 단계로 이동하시겠습니까?");
			return result;
			break;
		case '2': //작업종료
			var result = confirm("작업을 종료하시겠습니까?");
			return result;
			break;
		case '3': //수정작업
			var result = confirm("데이터를 수정하시겠습니까?");
			return result;
			break;
		default :		
			break;
	}		
}

function saveInfoCheck(){ //입력 데이터 유효성검증
	var form2 = document.form1;	
	var errorV = new Boolean(false); //에러를 나타내는 변수로 기본값은 false로 설정	
	if(form2.g_data_file.value.length < 1){
		//1. X단위 체크
		if(form2.x_val.value.length < 1){
			errorV = true;
			alert("X 값을 입력하세요!");
			form2.x_val.focus();
			return;
		}
		
		//1. Y단위 체크
		if(form2.y_val.value.length < 1){
			errorV = true;
			alert("Y 값을 입력하세요!");
			form2.y_val.focus();
			return;
		}
	}
	
	return errorV;
}


function checkFile(){
	var form2 = document.form1;	
	var checkV = new Boolean(false);
	if(form2.g_data_file.value.length < 1)
	{
		checkV = false;
	}else{
		checkV = true;
	}
	return checkV;
}

function typcheck(typ, inp)
{
	 var lastidx = -1;
	 lastidx = inp.lastIndexOf('.');
	 var extension = inp.substring(lastidx+1, inp.length);
	 
	 if((lastidx != -1) && (extension.toLowerCase() == typ))
	  return true;
	 return false;
}

function checkData(pr_no, dt_no){
	//alert(dt_no);
	if(confirmMsg('3') == true){
		var form2 = document.form1;
		form2.target = "_self";
		form2.action = "pr_modify_graph_data.jsp?pr_no="+pr_no+"&dt_no="+dt_no;
		form2.submit();
		//window.open("pr_modify_graph_data.jsp?pr_no="+pr_no+"&dt_no="+dt_no,"modify_info","width=650,height=350,toolbar=no");
		//return;	
	}	
}

function selectBeforeBlock(i_nowBlock, i_nowPage)//이전 목록 가기
{
    var form2 = document.form1;
    form2.i_nowBlock.value = i_nowBlock;
    form2.i_nowPage.value = i_nowPage;
    form2.target = "_self";
    form2.action = "pr_insert_graph_data.jsp";
    ///form2.encoding = "application/x-www-form-urlencoded";    
	form2.submit();	
}

function selectBlock(i_nowBlock, i_nowPage)//목록 가기
{
    var form2 = document.form1;
    form2.i_nowBlock.value = i_nowBlock;
    form2.i_nowPage.value = i_nowPage;
    form2.target = "_self";
    form2.action = "pr_insert_graph_data.jsp";
    ///form2.encoding = "application/x-www-form-urlencoded";    
	form2.submit();	
}

function selectAfterBlock(i_nowBlock, i_nowPage)//다음 목록 가기
{
    var form2 = document.form1;
    form2.i_nowBlock.value = i_nowBlock;
    form2.i_nowPage.value = i_nowPage;
    form2.target = "_self";
    form2.action = "pr_insert_graph_data.jsp";
    ///form2.encoding = "application/x-www-form-urlencoded";    
	form2.submit();	
}

</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST" >
<input type="hidden" name="pr_no" value='<%=pr_no%>'>
<input type="hidden" name="i_dt_count" value='<%=i_dt_count%>'>
<input type="hidden" name="d_index" value='<%=d_index%>'>
<input type="hidden" name="i_nowPage" value="<%=i_nowPage%>"/>
<input type="hidden" name="i_nowBlock" value="<%=i_nowBlock%>"/>
<input type="hidden" name="xax_cal" value="<%=x_cal%>"/>
<input type="hidden" name="xay_cal" value="<%=y_cal%>"/>

<p class="level2">물성 그래프 데이터 입력</p>
<table class="tab2">
<col width="120">
<col width="530">

  <tr>
    <th class="thc">물성정보번호</th>
    <td class="tdl">
    <%
    	if(ComUtil.isNull(pr_no)){
    %>
    	<input type="text" name="pr_no" value="자동으로 선택됩니다." size="25" readonly> 
    <%
    	}else{
    %>
    	<input type="text" name="pr_no" value="<%=pr_no%>" size="25" readonly> 
    <%
    	}
    %> 
    </td>
  </tr>
</table>
<span class="help">
	X값과 Y값 실수형태로 입력하세요.<br>
</span>
<span class="help">
	파일을 첨부하고 저장하면 화면에 입력하신 데이터는 동시 입력되지 않고 첨부파일 내용만 저장됩니다.
</span>
<table class="tab2">
<col width="120">
<col width="205">
<col width="120">
<col width="205">
  <tr>
  	<th class="thr"><font color = red>X 값</font></th>
    <td class="tdl">
		<input type="text" name="x_val" value="" size="15">X <%=ComUtil.getStringByDoubleFormat(x_cal)%>
	</td>
   	<th class="thr"><font color = red>Y 값</font></th>
    <td class="tdl">
		<input type="text" name="y_val" value="" size="15">X <%=ComUtil.getStringByDoubleFormat(y_cal)%>
	</td>
  </tr>
  <tr>
	<th class="thr">X축 오차값</th>   
  	<td colspan="3"> 
  	  <input type="text" name="x_err" value="" size="25">
  	</td>
  <tr>
  <tr>
  	<th class="thr">Y축 최대오차값</th>
    <td class="tdl">
		<input type="text" name="y_err_max" value="" size="25">
	</td>
   	<th class="thr">Y축 최소오차값</th>
    <td class="tdl">
		<input type="text" name="y_err_min" value="" size="25">
	</td>
  </tr>
  <tr>
  	<th class="thr">생성비</th>
    <td class="tdl">
		<input type="text" name="ratio" value="" size="25">
	</td>
   	<th class="thr">압력</th>
    <td class="tdl">
		<input type="text" name="press" value="" size="25">
	</td>
  </tr>
  <tr>
  	<th class="thr">백업데이터</th>
    <td colspan="3"> 
		<input type="text" name="back_data" value="" size="25">
	</td>
  </tr>
  <tr>
  	<th class="thr">수치데이터파일</th>   
  	<td colspan="3"> 
  	  <input type="file" name="g_data_file" size="25" value=""><font color = red> Excel 파일(xls,csv)만 가능</font>
  	</td>
  </tr>
</table>
<div class="bbtn_r">
<a href="javascript:saveInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('saveImage','','images/img_cont/save_upper.gif',1)"><img src="images/img_cont/save_normal.gif" name="saveImage" width="80" height="24" border="0" id="saveImage" /></a>
<a href="javascript:nextInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('nextImage','','images/img_cont/next_upper.gif',1)"><img src="images/img_cont/next_normal.gif" name="nextImage" width="80" height="24" border="0" id="nextImage" /></a>
<a href="javascript:exitInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('exitImage','','images/img_cont/exit_upper.gif',1)"><img src="images/img_cont/exit_normal.gif" name="exitImage" width="80" height="24" border="0" id="exitImage" /></a>
</div>
<p class="level2">물성 그래프 데이터</p>
<span class="count">
데이터 : <%=i_totalRecord%>건 (<font color=red> <%=i_totalRecord==0?"0":i_nowPage + 1%>/<%=i_totalPage %> Pages</font>)
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
	  	for (int i = i_beginPerPage ; i < (i_beginPerPage+i_numPerPage); i++) {
			if(i == i_totalRecord || i_totalRecord == 0)
			{
				break;
			}
			Graph_Data_Info dti = (Graph_Data_Info) dt_info.elementAt(i);
			String pro_no = dti.getPL_BI_DATA_NUM();
			String dt_no = dti.getPL_BGD_SEQ_NUM();
			String xval = dti.getPL_BGD_X_AX_VAL();
			String yval = dti.getPL_BGD_Y_AX_VAL();
			String xerr = dti.getPL_BGD_X_ERR();
			String yerrmax = dti.getPL_BGD_Y_ERR_MAX();
			String yerrmin = dti.getPL_BGD_Y_ERR_MIN();
			String s_ratio = dti.getPL_BGD_RATIO();
			String s_press = dti.getPL_BGD_PRESS();
			String s_backdata = dti.getPL_BGD_BACKUP_DATA();
			
	  		%>
	  		
	  		<tr onmouseover="this.bgColor='#E9E9F3';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'" onClick="checkData('<%=pro_no%>','<%=dt_no%>');">
	  		
			    <td class="tdc">
			     	<%=i+1%>
			    </td >
			    <td class="tdc">
			     	<%=ComUtil.getStringByDecimalFormat(xval)%>
			    </td >
			    <td class="tdc">
			     	<%=ComUtil.getStringByDoubleFormat(yval)%>
			    </td >
			    <td class="tdc">
			     	 <%=ComUtil.getStringByDoubleFormat(xerr)%>
			    </td >
			    <td class="tdc">
			     	 <%=ComUtil.getStringByDoubleFormat(yerrmax)%>
			    </td >
			    <td class="tdc">
			     	 <%=ComUtil.getStringByDoubleFormat(yerrmin)%>
			    </td >
			    <td class="tdc">
			     	 <%=s_ratio%>
			    </td >
			    <td class="tdc">
			     	 <%=s_press%>
			    </td >
			    
		  	</tr>
	  		<%
	  	}
	  	
	  %>
</table>
<div class="bbtn_c">
<%
	if(i_totalRecord !=0)
	{
		if(i_nowBlock > 0){
			%>
			<A HREF="javascript:selectBeforeBlock('<%=i_nowBlock-1%>','<%=(i_nowBlock-1)*i_pagePerBlock %>')">이전 <%=i_pagePerBlock%>개</A>
			<%
		}
		for(int i = 0; i<i_pagePerBlock; i++){
			%>
			<A HREF="javascript:selectBlock('<%=i_nowBlock%>','<%=(i_nowBlock*i_pagePerBlock) + i%>')">[<%=(i_nowBlock*i_pagePerBlock)+i+1%>]</A>
			<%
			if((i_nowBlock*i_pagePerBlock) + i + 1 == i_totalPage){
				break;
			}
		}
		if(i_totalBlock > i_nowBlock + 1){
		%>
			<A HREF="javascript:selectAfterBlock('<%=i_nowBlock+1%>','<%=(i_nowBlock+1)*i_pagePerBlock %>')">다음 <%=i_pagePerBlock%>개</A>
		<%
		}
	}
%>
</div>
</form>
</body>
</html>