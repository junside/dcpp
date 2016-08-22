<%@ page language="java" contentType="text/html; charset=EUC-KR"  pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="nfri.dcpp.properties.db.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<%request.setCharacterEncoding("EUC-KR");%>
<%
	/**
	 * 참고문헌 데이터를 검색하는 JSP 페이지
	 * by Junside(J.H Park)
	 */
	String codeid = request.getParameter("code_id");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="./css/common.css" rel="stylesheet" type="text/css" />
<title>참고문헌 정보 조회</title>
</head>
<body>
<SCRIPT LANGUAGE="JavaScript">
function search(){ //검색
	var form2 = document.form1;	
	form2.action = "pr_article_search_result.jsp";
	form2.submit();
}

function validationYear(){ //년도 입력 오류 체크
	var form2 = document.form1;
	var fr_year = form2.publish_year_from.value;
	var to_year = form2.publish_year_to.value;	
	if(to_year < fr_year)
	{
		if(form2.publish_year_from.options[0].selected == true)
		{
			alert("검색 마지막 년도를 입력하시면 시작년도는 전체로 지정하실수 없습니다.");
			form2.publish_year_to.options[0].selected = true; //특정 값(전체)으로 지정
		}
		else{
			alert("년도 별 검색시 마지막 년도가 더 커야 합니다.");
			form2.publish_year_to.options[0].selected = true; //특정 값(전체)으로 지정
		}
	}
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

//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</SCRIPT>
<form name="form1" action="" method="POST">
<div id="content">
<jsp:useBean id="option_code" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
<jsp:useBean id="artcle_code" class="nfri.dcpp.properties.business.Ctr_Article_Process" scope="page"/>
<input type='hidden' name='_oCol' value='artcl_no'/>
<input type='hidden' name='_oDir' value='asc'/>
<img src="images/main_cont/pr_article_search_main_t_01.gif" width="686" height="47"><br>
<table class="tab0">
	<tr>
		<td>
		<span class="help">
			검색 방법은 Like 검색이며, 입력조건이 없으면 전체검색합니다.
		</span>
		</td>
	</tr>
	<tr>
		<td>
		<span class="help">
			띄어쓰기하여 입력할 경우 두 단어를 포함한 모든 결과를 검색합니다.
		</span>
		</td>
	</tr>
	<tr>
		<td>
		<span class="help">
			검색 시작년도는 DB에 저장된 가장 오래된 논문 출판 년도부터 보여줍니다.
		</span>
		</td >
	</tr>
</table>
<table class="tab1">
	<col width="25%">
	<col width="65%">
	<col width="10%">
	<tr>
		<th class="thla">논문 제목</th>		
		<td class="tdl">
			<input type='text' class='txtl300' name='pl_ra_artcl_title' size='55' value=''>
		</td>
		<td class="tdc">
			<select name="option1_oper" onChange=""	class="txtl">
				<%
					Vector opt_code = option_code.getSOOption();
					int o_count = opt_code.size();
					for (int i = 0; i < o_count; i++) {
						Code_Info code = (Code_Info) opt_code.elementAt(i);
						String code_id = code.getPL_CI_ID();
						String code_name = code.getPL_CI_NAME();
				%>
				<option value='<%=code_id%>'<%=code_id.equals("SO_01")?"selected":""%>><%=code_name%></option>
				<%
					}
				%>
			</select>
		</td>
	</tr>
	<tr>
		<th class="thla">논문 저자 Family 명</th>
		<td class="tdl">
			<input type='text' class='txtl300' name='pl_ra_artcl_auth_fname' size='55' value=''>
		</td>
		<td class="tdc">
			<select name="option2_oper" onChange=""	class="txtl">
				<%
					for (int i = 0; i < o_count; i++) {
						Code_Info code = (Code_Info) opt_code.elementAt(i);
						String code_id = code.getPL_CI_ID();
						String code_name = code.getPL_CI_NAME();
				%>
				<option value='<%=code_id%>'<%=code_id.equals("SO_01")?"selected":""%>><%=code_name%></option>
				<%
					}
				%>
			</select>
		</td>
	</tr>
	<tr>
		<th class="thla">논문 저자 이름</th>
		<td class="tdl">
			<input type='text' class='txtl300' name='pl_ra_artcl_auth_ename' size='55' value=''>
		</td>
		<td class="tdc">
			<select name="option3_oper" onChange=""	class="txtl">
				<%
					for (int i = 0; i < o_count; i++) {
						Code_Info code = (Code_Info) opt_code.elementAt(i);
						String code_id = code.getPL_CI_ID();
						String code_name = code.getPL_CI_NAME();
				%>
				<option value='<%=code_id%>'<%=code_id.equals("SO_01")?"selected":""%>><%=code_name%></option>
				<%
					}
				%>
			</select>
		</td>
	</tr>
	<tr>
		<th class="thla">저널명</th>
		<td class="tdl">
			<input type='text' class='txtl300' name='pl_ra_jour_title' size='55' value=''>
		</td>
		<td class="tdc">
			<select name="option4_oper" onChange=""	class="txtl">
				<%
					for (int i = 0; i < o_count; i++) {
						Code_Info code = (Code_Info) opt_code.elementAt(i);
						String code_id = code.getPL_CI_ID();
						String code_name = code.getPL_CI_NAME();
				%>
				<option value='<%=code_id%>'<%=code_id.equals("SO_01")?"selected":""%>><%=code_name%></option>
				<%
					}
				%>
			</select>
		</td>
	</tr>
	<tr>
		<th class="thla">출판연도</th>
		<td class="tdl">
			<select name="publish_year_from" onChange="" class="txtl">
				<%
					HashMap condition_year = (HashMap)artcle_code.getYearList();
					String[] yearKey = (String[]) condition_year.get("yearKey");
					String[] year = (String[]) condition_year.get("year");
					for (int i = 0; i < yearKey.length; i++) {
						String condition_yearid = yearKey[i];
						String condition_yearvalue = year[i];
				%>
					<option value='<%=condition_yearid%>' <%=condition_yearid.equals("All")?"selected":""%>><%=condition_yearvalue%></option>
				<%
					}
				%>
			</select>
			-
			<select name="publish_year_to" onChange="validationYear()" class="txtl">
				<%
					for (int i = 0; i < yearKey.length; i++) {
						String condition_yearid = yearKey[i];
						String condition_yearvalue = year[i];
				%>
					<option value='<%=condition_yearid%>' <%=condition_yearid.equals("All")?"selected":""%>><%=condition_yearvalue%></option>
				<%
					}
				%>
			</select>
		</td>
		<td class="tdc">
			<select name="option5_oper" onChange=""	class="txtl">
				<%
					for (int i = 0; i < o_count; i++) {
						Code_Info code = (Code_Info) opt_code.elementAt(i);
						String code_id = code.getPL_CI_ID();
						String code_name = code.getPL_CI_NAME();
				%>
				<option value='<%=code_id%>'<%=code_id.equals("SO_01")?"selected":""%>><%=code_name%></option>
				<%
					}
				%>
			</select>
		</td>
	</tr>
	<tr>
		<th class="thla">참고 문헌 종류 코드 선택</th>
		<td class="tdl">
			<select name="pl_raci_code_id" onChange="selectSubmit()" class="txtl">
				<%
					Vector art_code = option_code.getRefArticleCode();
					int a_count = art_code.size();
					for (int i = 0; i < a_count; i++) {
						Article_Code code = (Article_Code) art_code.elementAt(i);
						String code_id = code.getPL_RACI_CODE_ID();
						String code_exp = code.getPL_RACI_CODE_EXP();
				%>
				<option value='<%=code_id%>'<%=code_id.equals(codeid)?"selected":""%>><%=code_exp%></option>
				<%
					}
				%>
			</select>
		</td>
		<td class="tdc">
			
		</td>
	</tr>
</table>
<div class="bbtn_c">
	<a href="javascript:search()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('search','','images/img_cont/search_upper.gif',1)"><img src="images/img_cont/search_normal.gif" name="search" width="80" height="24" border="0" id="search" /></a>
</div>
</div>
</form>
<script type="text/javascript">
function init(){
	  var doc = document.getElementById('content');
	  if(doc.offsetHeight == 0){
	  } else {
		 //pagewidth = doc.offsetWidth;
		 pageheight = doc.offsetHeight + 120;
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