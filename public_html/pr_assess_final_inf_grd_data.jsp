<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("euc-kr"); %>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="ctr_data" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
<jsp:useBean id="property_assess_data" class="nfri.dcpp.properties.business.Ctr_Property_Assess_Info" scope="page"/>
<jsp:useBean id="inf_grd_property_data" class="nfri.dcpp.properties.business.Ctr_Inf_Grd_Property_Info_Process" scope="page"/>
<jsp:useBean id="ctr_graph" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<%
	/**
	* 최종 평가하는 페이지
	* by Junside(J.H Park)
	*/
	String user_id = ComUtil.isNullToDashString((String)session.getAttribute("user_id"));
	if(user_id.equalsIgnoreCase("-")){
		%>
		<script language = javascript>
		alert("다시 로그인 하세요.");
		</script>
		<%
		response.sendRedirect("c_login_inner.jsp?flag=AU");
	}else{
	
	// 1. 등급유력 물성정보 입력을 위한 정보수집	
	// 1-1. 새로운 등급 유력 물성 번호 생성
	
	String v_pr_no     = ComUtil.isNullToNullString(request.getParameter("v_pr_no")); //등급유력 물성번호

	if(v_pr_no.equalsIgnoreCase("NULL")){ 
		v_pr_no = property_assess_data.makeVaildPropertyNum(ComUtil.getTimeNow());
	}
	
	int count = Integer.parseInt(ComUtil.isNullToZeroString(request.getParameter("pr_count")));
	
	//System.out.println("count : " + count);
	//1-5. 등급유력물성정보 가져오기
	//데이터가 한개 선택되었을 경우에 DB에 입력작업
	boolean mov_value = false;
	if(count == 1){
		String seach_pr_no = request.getParameter("search_pr_no");
		mov_value = property_assess_data.insertOneFinalPropertyData(request, v_pr_no, seach_pr_no);
		
		if(mov_value == false){
			%>
			<script language = javascript>
			alert("데이터 입력에 실패했습니다.");
			</script>
			<%
			response.sendRedirect("pr_common_assess_list.jsp.jsp?flag=AU");	
		}
	}
	
	//1-4.입력할 물성정보들을 가져옴
	//Vector pd_info = property_assess_data.searchAssessPropertyListInfo(request);	
			
	String param_projectile    = request.getParameter("projectile"); // 입사입자
	String param_projectile_id = request.getParameter("projectile_id"); // 입사입자ID
	
	String param_tg_name       = request.getParameter("tg_name"); // 표적입자
	String param_tg_name_id    = request.getParameter("tg_name_id"); // 표적입자 ID
	String param_tg_ionic      = ComUtil.isNullToNullString(request.getParameter("tg_ionic")); // 표적입자 이온화
	String param_tg_elec       = ComUtil.isNullToNullString(request.getParameter("tg_elec")); // 표적입자 전자배치
	String param_tg_fine       = ComUtil.isNullToNullString(request.getParameter("tg_fine")); // 표적입자 미세구조
	
	String param_pd_name       = ComUtil.isNullToNullString(request.getParameter("pd_name")); // 생성입자 
	String param_pd_name_id    = ComUtil.isNullToNullString(request.getParameter("pd_name_id")); // 생성입자 ID 
	String param_pd_ionic      = ComUtil.isNullToNullString(request.getParameter("pd_ionic")); // 생성입자 이온화
	String param_pd_elec       = ComUtil.isNullToNullString(request.getParameter("pd_elec")); // 생성입자 전자배치
	String param_pd_fine       = ComUtil.isNullToNullString(request.getParameter("pd_fine")); // 생성입자 미세구조
	
	//X, Y 최소, 최대값 체크 설정
	String param_graph_xrange_v      = ComUtil.isNullToNullString(request.getParameter("graph_xrange_value"));
	String param_graph_yrange_v      = ComUtil.isNullToNullString(request.getParameter("graph_yrange_value"));

	//X,Y 최소, 최대값 설정
	String param_x_min_v      = ComUtil.isNullToNullString(request.getParameter("graph_xrange_min_value")); // X축 최소
	String param_x_max_v       = ComUtil.isNullToNullString(request.getParameter("graph_xrange_max_value")); // X축 최대
	String param_y_min_v       = ComUtil.isNullToNullString(request.getParameter("graph_yrange_min_value")); // Y축 최소
	String param_y_max_v       = ComUtil.isNullToNullString(request.getParameter("graph_yrange_max_value")); // Y축 최대 

	String param_xax_unit      = ComUtil.isNullToNullString(request.getParameter("xax_unit")); // X 단위
	String param_xay_unit      = ComUtil.isNullToNullString(request.getParameter("xay_unit")); // Y 단위
	
	//기본 정보 가져오기
	Inf_Grd_Graph_Basic_Info graph = (Inf_Grd_Graph_Basic_Info)ctr_graph.selectViewInfGrdGraphInfo(v_pr_no);
	
	//X축 제목 표시
	String X_Title = graph.getPL_IGGI_X_AX_UNIT();
	//Y축 제목 표시
	String Y_Title = graph.getPL_IGGI_Y_AX_UNIT();
	
	//1.등급유력 기본 정보 가져오기
	Inf_Grd_Properties_Basic_Info basic_info = (Inf_Grd_Properties_Basic_Info)inf_grd_property_data.selectViewInfGrdPropertyInfo(v_pr_no);
	 // 충돌방식
	String ic_option  = basic_info.getPL_IGBI_IMP_CLASS();
	// 주프로세스
	String mp_value = basic_info.getPL_IGBI_MAIN_PROC();
	// 부프로세스
	String sp_value = basic_info.getPL_IGBI_SUB_PROC();
	// 표현식
	String expression = basic_info.getPL_IGBI_EXPRESSION();
	// 입력자
	String user = basic_info.getPL_UI_ID();
	// 관리번호리스트
	String mt_list = basic_info.getPL_IGBI_MGMT_NUM_LIST();
	// 물성번호리스트
	String dt_list = basic_info.getPL_IGBI_DATA_NUM_LIST();
	// 물성정보 개수
	String dt_cnt = basic_info.getPL_IGBI_DATA_NUM_CNT();
	
	String process_option = ComUtil.getAbbrString(mp_value, 10) + ", " + ComUtil.getAbbrString(sp_value, 10);
	
	
	//2.등급유력 그래프 정보 가져오기
	//Inf_Grd_Graph_Basic_Info graph_info = (Inf_Grd_Graph_Basic_Info)dao_graph.selectViewInfGrdGraphInfo(v_pr_no);
	
	
	
	//System.out.println("OZ Where Condtion : "+whereCondtion);
	
	//리스트에 포함된 물성정보를 토대로 그래프 데이터를 가져옴	
	//Vector gd_info = graph_data.selectGraphBasicData(pr_no);
	//int dt_count = pd_info.size();//dt_count = 0;//dt_info.size();
	/*
	String block_state = "T";
	
	//1. 기존 Temp 데이터 지워버리기
	boolean del_value = false;
	del_value = property_assess_data.deleteFittingTempData(user_id);		
	System.out.println("Fitting Temp Delete : "+del_value);
	if(del_value == true){ //2. Temp Data가 제대로 지워졌으면..
	}else{
		System.out.println("Temp Data Delete Fail!!!");
	}
	*/
	//4. 평가를 위하여 선택된 Source Data 이동.
	
	
	/*
	if(property_assess_data.checkFittingState(user_id, block_state) == true){ //4. 사용자ID로 진행중인 것이 있다면..Update
		//4.1. 상태값 Update
		boolean upd_value = false;
		if(mov_value == true){
			upd_value = property_assess_data.updateFittingState(param_v_pro_no, user_id, "-", block_state);
			System.out.println("Fitting Temp Update : "+upd_value);
		}
	}else{ //5. 사용자 ID로 진행중인 것이 없다면.. Insert
		//5.1. 상태값 Insert
		boolean ins_value = false;
		if(mov_value == true){
			ins_value = property_assess_data.insertFittingState(param_v_pro_no, user_id, "-", block_state);
			System.out.println("Fitting Temp Insert : "+ins_value);
		}
	}
	*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
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

function assessInfo(){ //평가
	if(confirmMsg('1') == true){
		var form2 = document.form1;	
		form2.action = "pr_insert_final_assess.jsp";
		form2.submit();
	}
}

function exitInfo(){ //종료
	if(confirmMsg('2') == true){
		var form2 = document.form1;	
		form2.action = "pr_common_assess_list.jsp";
		//form2.action = "pr_assess_search_inf_grd_data.jsp";
		form2.submit();
	}
}

function confirmMsg(number){
	var num = number;
	switch(num) {
		case '1': //평가하기
			var result = confirm("입력하신 내용으로 물성정보를 최종 평가하시겠습니까?\n입력하신 내용을 한번 더 확인하시려면 취소를 누르세요.\n확인을 누르시면 평가가 완료됩니다.");
			return result;
			break;
		case '2': //종료
			var result = confirm("평가를 종료하시겠습니까?\n입력하신 내용은 저장되지 않습니다.\n주의하세요");
			return result;
			break;
		default :		
			break;
	}		
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
</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
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
<form name="form1" action="" method="POST">
<input type="hidden" name="v_pr_no"       value="<%=v_pr_no%>"/>
<p class="level2">그래프 결과</p>
<span class="help">그래프 값 위에 마우스 포인터를 올리면 해당 값에 대한 정보가 표시됩니다.</span>
<table class="tab2">
<col width="110">
<col width="540">
  <tr>
    <th colspan="2" class="thc">그래프</th>
    </tr>
  <tr>
  	<td colspan="2" class="tdc">
  	<%
  	if(dt_cnt.equalsIgnoreCase("1")){ //데이터가 1개가 들어오는 경우  		
  	%>
  	<object id="ozviewer" width="645" height="500" CLASSID="CLSID:0DEF32F8-170F-46f8-B1FF-4BF7443F5F25">
        <param name="connection.servlet" value="http://dcpp.nfri.re.kr/oz/server">
        <param name="connection.reportname" value="/ozr_pr_view_inf_grd_graph.ozr">
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
        <param name="odi.odinames" value="odi_pr_view_inf_grd_graph">
		<param name="odi.odi_pr_view_inf_grd_graph.pcount" value="3">
		<param name="odi.odi_pr_view_inf_grd_graph.args1" value="PM_PRO_NO=<%=v_pr_no%>">
		<param name="odi.odi_pr_view_inf_grd_graph.args2" value="PM_X_TITLE=<%=X_Title%>">
		<param name="odi.odi_pr_view_inf_grd_graph.args3" value="PM_Y_TITLE=<%=Y_Title%>">			
    </object>
  	<%
  	}else{ //데이터가 여러개 들어오는 경우,
  		//IN Option 가져오기
  		String whereCondtion = property_assess_data.getFinalCondition(dt_list);
  		//System.out.println("whereCondtion : " + whereCondtion);
  	%>
		<object id="ozviewer" width="645" height="500" CLASSID="CLSID:0DEF32F8-170F-46f8-B1FF-4BF7443F5F25">
            <param name="connection.servlet" value="http://dcpp.nfri.re.kr/oz/server">
            <param name="connection.reportname" value="ozr_pr_assess_final_inf_grd_data.ozr">
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
            <param name="odi.odinames" value="odi_pr_assess_final_inf_grd_data">
			<param name="odi.odi_pr_assess_final_inf_grd_data.pcount" value="4">
			<param name="odi.odi_pr_assess_final_inf_grd_data.args1" value="PM_PRO_NO=<%=v_pr_no%>">
			<param name="odi.odi_pr_assess_final_inf_grd_data.args2" value="PM_X_TITLE=<%=X_Title%>">
			<param name="odi.odi_pr_assess_final_inf_grd_data.args3" value="PM_Y_TITLE=<%=Y_Title%>">
			<param name="odi.odi_pr_assess_final_inf_grd_data.args4" value="PM_WHERE_CONDITION=<%=whereCondtion%>">
        </object>
    <%
  		}
  	%>
   	</td>
    </tr>
</table>
<p class="level2">최종 평가 정보 입력</p>
<table class="tab2">
<col width="200">
<col width="100">
<col width="350">
  <tr>
    <th class="thc">Title</th>
    <th class="thc" colspan="2">Note</th>
  </tr>
  <tr>
  	<th class="thl">1. New Number</th>
    <td class="tdl" colspan="2"><%=v_pr_no%></textarea></td>
  </tr> 
  <tr>
    <th rowspan="2" class="thl">2. Indirect Production Method</th>
    <td class="tdl">Best Value</td>
    <td class="tdl">
    <%
    	if(dt_cnt.equalsIgnoreCase("1")){
   		%>
   			<input type="text" name="final_best_value" value="<%=mt_list%>" size="40" maxlength="50">
   		<%
    	}else{
   		%>
   			<input type="text" name="final_best_value" value="-" size="40" maxlength="50">
   		<%
    	}
    %>
    </td>    
  </tr>
  <tr>
    <td class="tdl">Average Value</td>
     <td class="tdl">
    <%
    	if(dt_cnt.equalsIgnoreCase("1")){
   		%>
   			<input type="text" name="final_average_value" value="-" size="40" maxlength="50">
   		<%
    	}else{
   		%>
   			<input type="text" name="final_average_value" value="<%=mt_list%>" size="40" maxlength="50">
   		<%
    	}
    %>
    </td>    
  </tr>
  <tr>
  	<th class="thl">3. Assessment</th>
    <td class="tdl" colspan="2"><textarea name="final_assess" rows="5" cols="55" ></textarea></td>
  </tr>  
  <tr>
    <th rowspan="2" class="thl">4. Energy Range (X Value)</th>
    <td class="tdl">Max Value</td>
    <td class="tdl">
      		<input type="text" name="final_x_max_v" value="" size="20" maxlength="20">
    </td>
  </tr>
  <tr>
    <td class="tdl">Min Value</td>
    <td class="tdl">
    	<input type="text" name="final_x_min_v" value="" size="20" maxlength="20">
	</td>
  </tr>
  <tr>
    <th class="thl">5. Collection Period</th>
    <td colspan="2"> 
  		<input type="text" name="final_col_period" value="" size="20" maxlength="20">
  	</td>
  </tr>
  <tr>
    <th class="thl">6. Final Opinion</th>
    <td colspan="2"> 
  	  <textarea name="final_opinion" rows="5" cols="55" ></textarea>
  	</td>
  </tr>
  <tr>
    <th class="thl">7. Standard Reference Data</th>
    <td colspan="2"> 
  	  <textarea name="final_std_ref_data" rows="5" cols="55" ></textarea>
  	</td>
  </tr>
</table>
<div class="bbtn_r">
<a href="javascript:assessInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('assessImage','','images/img_cont/assess_upper.gif',1)"><img src="images/img_cont/assess_normal.gif" name="assessImage" width="80" height="24" border="0" id="assessImage" /></a>
<a href="javascript:exitInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('exitImage','','images/img_cont/exit_upper.gif',1)"><img src="images/img_cont/exit_normal.gif" name="exitImage" width="80" height="24" border="0" id="exitImage" /></a>
</div>
</form>
</body>
</html>
<%}%>