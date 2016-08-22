<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="property_info" class="nfri.dcpp.properties.business.Ctr_Property_Info_Process" scope="page"/>
<jsp:useBean id="data_info" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<jsp:useBean id="articleControl" class="nfri.dcpp.properties.business.Ctr_Article_Process" scope="page"/>

<%
	/**
	* 기본 물성 정보를 확인하는 JSP 페이지(view)
	* by Junside(J.H Park)
	*/
	
	String[] check = ComUtil.splitString(request.getParameter("check"), ",");
	boolean first_check = ComUtil.isNull(check[0]);
	int check_length = 0;
	int k = 0;
	if(first_check == true){
		check_length = check.length-1;
		k = 1;
	}else{
		check_length = check.length;
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>플라즈마 물성 기본 정보 보기</title>
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
function viewArticleInfo(artcl_no){ //참고문헌 보기
	if(saveMsg('1') == true){
		window.open("pr_view_article_info.jsp?artcl_no="+artcl_no,"article_info","width=700,height=500,toolbar=no,location=no,status=no,scrollbar=auto");
		return;
	}
}
function downloadXSAMS(pr_no){ //XSAMS 문서 다운로드
	if(saveMsg('2') == true){
		//var url = "pr_xsams_make_xmldata.jsp?pr_no="+pr_no;
		//var popup = window.open(url, "hiddenframe", "width=0, height=0, top=0, statusbar=no, scrollbars=no, toolbar=no");
		//popup.focus(); 
		//document.location.href = "pr_xsams_make_xmldata.jsp?pr_no=" + pr_no;
		//history.back();
		//window.open("pr_xsams_make_xmldata.jsp?pr_no="+pr_no, "hiddenframe", "width=0, height=0, top=0, statusbar=no, scrollbars=no, toolbar=no");
		window.open("pr_xsams_make_xmldata.jsp?pr_no="+pr_no, "xsams_info","width=500,height=150, statusbar=no, scrollbars=no, toolbar=no, location=no,status=no");
		return;
	}
}

function saveMsg(number){
	var num = number;
	switch(num) {
		case '1': //참고문헌 보기
			var result = confirm("참고문헌 정보를 확인하시겠습니까?");
			return result;
			break;
		case '2': //수치정보 보기
			var result = confirm("XSAMS 문서를 다운로드 하시겠습니까?");
			return result;
			break;
		default :		
			break;
	}		
}

</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<div id="content">
<p class="level2">물성 TEXT 정보</p>
<span class="help">총 검색 개수 : <%=check_length%></span><br>
<%
	//System.out.println("length : " + check.length);
	
	for( int i = k ; i < check.length; i++){
		String pr_no = check[i];
		//String artcl_no = request.getParameter("artcl_no");
		
		//기본 정보 가져오기
		Properties_Basic_Info spec_info = (Properties_Basic_Info)property_info.selectViewPropertySpecInfo(pr_no);
		//대분류
		String tb_value = spec_info.getPL_BI_TOP_BRANCH();
		//데이터분류
		String db_value = spec_info.getPL_BI_DATA_BRANCH();
		//주프로세스
		String mp_value = spec_info.getPL_BI_MAIN_PROC();
		//부프로세스
		String sp_value = spec_info.getPL_BI_SUB_PROC();
		//충돌방식
		String imp_value = spec_info.getPL_BI_IMP_CLASS();
		//검증구분
		String exp_value = spec_info.getPL_BI_EXP_THE_REC();
		//표현식 정보 가져오기
		String expression = spec_info.getPL_BI_EXPRESSION();//(String)property_info.selectEquationData(pr_no);
		//참고문헌 번호
		String artcl_no = spec_info.getPL_RAI_ARTCL_NUM();
		
		//수치데이터 가져오기
		Vector dt_info = data_info.selectGraphBasicData(pr_no);
		int v_dt_count = dt_info.size();
		
		//참고문헌 정보 가져오기
		Article_Info art_Info = (Article_Info)articleControl.searchInfo(artcl_no);
		
		String j_code = art_Info.getPL_RACI_CODE_ID();
		String a_title = art_Info.getPL_RAI_ARTCL_TITLE();
		String a_auth_f = art_Info.getPL_RAI_ARTCL_AUTH_FNAME();
		String a_auth_e = art_Info.getPL_RAI_ARTCL_AUTH_ENAME();
		//String a_auth = a_auth_f + ", " + a_auth_e; 
		String j_title = art_Info.getPL_RAI_JOUR_TITLE();
		String issn_p = art_Info.getPL_RAI_ISSN_P();
		String issn_n = art_Info.getPL_RAI_ISSN_N();
		String isbn = art_Info.getPL_RAI_ISBN();
		String ndsl = art_Info.getPL_RAI_NDSL();
		String j_vol = art_Info.getPL_RAI_JOUR_VOL();
		String j_num = art_Info.getPL_RAI_JOUR_NUM();
		String a_stp = art_Info.getPL_RAI_ARTCL_ST_PAGE();
		String a_edp = art_Info.getPL_RAI_ARTCL_ED_PAGE();
		String j_year = art_Info.getPL_RAI_JOUR_YEAR();
		String a_doi = art_Info.getPL_RAI_ARTCL_DOI();
		
		int m = i + 1;
		/* if(i==0){
			m = i + 1;
		}
		else{
			m = m + i;
		} */
%>
<span class="count">
<%=m%> 번째 물성정보
</span>
<table class="tab6">
<col width="150">
<col width="250">
<col width="250">
<tr>
    <th class="thr">물성정보번호 : </th>
    <td class="tdl" colspan="2"><font color="red"><%=pr_no%></font></td>
    </tr>
      <tr>
    <th class="thr">표현식 : </th>
    <td class="tdl" colspan="2"><%=expression%></td>
  </tr>
  <tr>
    <th class="thr">Title : </th>
    <td class="tdl" colspan="2"><%=a_title%></td>
  </tr>
  <tr>
    <th class="thr">Author(s) : </th>
    <td class="tdl" colspan="2"><%=a_auth_e%></td>
  </tr>
  <tr>
    <th class="thr">Journal Name : </th>
    <td class="tdl" colspan="2"><%=j_title%></td>
  </tr>
  <tr>
    <th class="thr">Publication Year : </th>
    <td class="tdl" colspan="2"><%=j_year%></td>
  </tr>
  <tr>
    <th class="thr">Volume : </th>
    <td class="tdl" colspan="2"><%=j_vol%></td>
  </tr>
  <tr>
    <th class="thr">Issue No : </th>
    <td class="tdl" colspan="2"><%=j_num%></td>
  </tr>
    <tr>
    <th class="thr">Page : </th>
    <td class="tdl" colspan="2">pp.<%=a_stp%> ~ pp.<%=a_edp%></td>
  </tr>
  <tr>
  	<th class="thr">대분류 : </th>
    <td class="tdl" colspan="2"><%=tb_value%></td>
  </tr>
  <tr>
   	<th class="thr">데이터분류 : </th>
   	<td class="tdl" colspan="2"><%=db_value%></td>
  </tr>
  <tr>
  	<th class="thr">주프로세스 : </th>
    <td class="tdl" colspan="2"><%=mp_value%></td>
  </tr>
  <tr>
   	<th class="thr">부프로세스 : </th>
    <td class="tdl" colspan="2"><%=sp_value%></td>
  </tr>
  <tr>
   	<th class="thr">충돌방식 : </th>
   	<td class="tdl" colspan="2"><%=imp_value%></td>
  </tr>
  <tr>
   	<th class="thr">검증구분 : </th>
    <td class="tdl" colspan="2"><%=exp_value%></td>
  </tr>
    <tr>
   	<th class="thr">XSAMS 문서 : </th>
	<td class="tdl"  colspan="2" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
    	<a href="javascript:void(0);"  onClick="downloadXSAMS('<%=pr_no%>'); return false;">다운로드</a>			    
    </td>    
    </tr>
	<tr>
   	<th class="thrt" rowspan="<%=v_dt_count+1%>">수치정보 : </th>
    <th class="thc">
    X
    </th>
    <th class="thc">
    Y
    </th>
  </tr>
  <%
  	//수치정보 처리
  	for(int j=0; j < v_dt_count; j++){
  		Graph_Data_Info dti = (Graph_Data_Info) dt_info.elementAt(j);
		String pro_no = dti.getPL_BI_DATA_NUM();
		String dt_no = dti.getPL_BGD_SEQ_NUM();
		String xval = dti.getPL_BGD_X_AX_VAL();
		String yval = dti.getPL_BGD_Y_AX_VAL();
		
		//double t_yval = Math.log10(Double.valueOf(yval));
		//System.out.println(ComUtil.getStringByDoubleFormat(yval));
		
		String xerr = dti.getPL_BGD_X_ERR();
		String yerrmax = dti.getPL_BGD_Y_ERR_MAX();
		String yerrmin = dti.getPL_BGD_Y_ERR_MIN();
		String s_ratio = dti.getPL_BGD_RATIO();
		String s_press = dti.getPL_BGD_PRESS();
		String s_backdata = dti.getPL_BGD_BACKUP_DATA();
  		%>
  		<tr>
	    <td class="tdl">
	    <%=xval %>
	    </td>
	    <td class="tdl">
	    <%=yval%>
	    </td>
	  </tr>
  		<% 		
  	}
  %>


</table>

<%
}
%>

<div class="bbtn_c">
<a href="javascript:confirmInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('confirmImage','','images/img_cont/confirm_upper.gif',1)"><img src="images/img_cont/confirm_normal.gif" name="confirmImage" width="80" height="24" border="0" id="confirmImage" /></a>
</div>
</div>
</form>
</body>
</html>