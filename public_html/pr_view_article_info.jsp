<%@ page language="java" contentType="text/html; charset=EUC-KR"  pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="nfri.dcpp.properties.db.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>
<%request.setCharacterEncoding("EUC-KR");%>
<jsp:useBean id="articleControl" class="nfri.dcpp.properties.business.Ctr_Article_Process" scope="page"/>
<%
/**
* 참고문헌 정보를 확인하는 페이지
* by Junside(J.H Park)
*/
	//String artcle_no = "2012000146";
	String artcle_no = request.getParameter("artcl_no");
	//Vector search_info = articleControl.searchInfo(artcle_no);
	//전체 개수 저장
	//int totalCount = search_info.size();
	
	Article_Info art_Info = (Article_Info)articleControl.searchInfo(artcle_no);
	
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
	String a_path = art_Info.getPL_RAI_ORG_FILE_PATH();
	String a_name = art_Info.getPL_RAI_ORG_FILE_NAME();
	String a_ext = art_Info.getPL_RAI_ORG_FILE_EXT();
	String a_doi = art_Info.getPL_RAI_ARTCL_DOI();
	
	//파일 다운로드를 위한 변수
	String file_path = "";
	String file_name = "";
	String file_ext = "";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>원문 정보 보기</title>
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

function confirmInfo(){
	self.close();
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
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" method="POST">
<div id="content">
<input type="hidden" name="file_path" value="<%=file_path%>"/>
<input type="hidden" name="file_name" value="<%=file_name%>"/>
<input type="hidden" name="file_ext" value="<%=file_ext%>"/>
<p class="level2">원문 정보</p>
<table class="tab5">
	<col width="20%">
	<col width="80%">
	<tr>
		<th class="thr">원문 종류 코드</th>
		<td class="tdl"><%=j_code%></td>
	</tr>
	<tr>
		<th class="thr">논문 제목</th>		
		<td class="tdl"><%=a_title%></td>
	</tr>
	<tr>
		<th class="thr">논문 저자</th>
		<td class="tdl"><%=a_auth_e%></td>
	</tr>
	<tr>
		<th class="thr">저널명</th>
		<td class="tdl"><%=j_title%></td>
	</tr>
	<tr>
		<th class="thr">DOI</th>
		<td class="tdl">
			<%
				if("-".equalsIgnoreCase(a_doi)){
					%>
						<%=a_doi%>
					<%
				}else{
					%>
						<a href="http://dx.doi.org/<%=a_doi%>" target="_blank"><%=a_doi%></a>
					<%
				}
			%>
		</td>
	</tr>
    <tr>
		<th class="thr">ISSN_P</th>
		<td class="tdl"><%=issn_p%></td>
	</tr>
	<tr>
		<th class="thr">ISSN_N</th>
		<td class="tdl"><%=issn_n%></td>
	</tr>
	<tr>
		<th class="thr">ISBN</th>
		<td class="tdl"><%=isbn%></td>
	</tr>
	<tr>
		<th class="thr">NDSL</th>
		<td class="tdl"><%=ndsl%></td>
	</tr>
	<tr>
		<th class="thr">볼륨</th>
		<td class="tdl"><%=j_vol%></td>
	</tr>
	<tr>
		<th class="thr">호</th>
		<td class="tdl"><%=j_num%></td>
	</tr>
	<tr>
		<th class="thr">시작페이지</th>
		<td class="tdl"><%=a_stp%></td>
	</tr>
	<tr>
		<th class="thr">끝페이지</th>
		<td class="tdl"><%=a_edp%></td>
	</tr>
	<tr>
		<th class="thr">출판연도</th>
		<td class="tdl"><%=j_year%></td>	</tr>

</table>
<div class="bbtn_c">
<a href="javascript:confirmInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','images/img_cont/confirm_upper.gif',1)"><img src="images/img_cont/confirm_normal.gif" name="Image1" width="80" height="24" border="0" id="Image1" /></a>
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