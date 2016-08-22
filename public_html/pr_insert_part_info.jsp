<%@ page language="java" contentType="text/html; charset=EUC-KR"  pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="nfri.dcpp.properties.db.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<%
/**
* 입자 정보 데이터를 입력하는 폼 JSP 페이지
* by Junside(J.H Park)
*/
	String codeid = request.getParameter("code_id");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>새로운 입자 정보 입력</title>
</head>
<body>
<script language = javascript>
function uploadPartInfo(){ //업로드
	var form2 = document.form1;	
	var errorV = 'F'; //에러를 나타내는 변수로 기본값은 F로 설정	
	
	//1. 입자 기호 체크
	if(form2.pr_part_symbol.value.length < 1){
		alert("입자 기호를 입력하세요!");
		form2.pr_part_symbol.focus();
		errorV = 'T';
		return;
	}
	
	//2. 입자 이름 체크
	if(form2.pr_part_name.value.length < 1){
		alert("입자 이름을 입력하세요!");
		form2.pr_part_name.focus();
		errorV = 'T';
		return;
	}
	
	//3. 입자 질량 체크
	if(form2.pr_part_mass.value.length < 1){
		alert("입자 질량을 입력하세요!");
		form2.pr_part_mass.focus();
		errorV = 'T';
		return;
	}
	
	//4. 원자 전자 구분 체크
	if(form2.pr_part_type_code.value == 'AM_00'){
		alert("원자 전자 구분을 선택하세요!");
		form2.pr_part_type_code.focus();
		errorV = 'T';
		return;
	}
	
	if(errorV == 'F'){
		var bAnswer = confirm("정보를 제대로 입력하셨나요?")
    	if (bAnswer == true){		
			form2.action = 'pr_insert_part_info_check.jsp';
			form2.submit();
		}else{
			return;
		}
	}
}

function selectItem(){
	var form2 = document.form1;
	if(form2.pr_part_type_code.value =="AM_02"){
		form2.pr_part_amnum.value = "";
		form2.pr_part_amnum.disabled = true;
		return;
	}
	else if(form2.pr_part_type_code.value =="AM_01"){
		form2.pr_part_amnum.value = "";
		form2.pr_part_amnum.disabled = false;
		return;
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
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<div id="content">
<img src="images/main_cont/pr_insert_part_main_t_01.gif" width="686" height="47"><br>
<span class="help">입자 기호의 입력 예 1) H<sub>2</sub>이면 H&lt;sub&gt;2&lt;/sub&gt;로 입력하세요.</span><br>
<span class="help">입자 기호의 입력 예 2) H<sup>2</sup>이면 H&lt;sup&gt;2&lt;/sup&gt;로 입력하세요.</span>
<table class="tab2">
	<col width="20%">
	<col width="80%">
	<tr>
		<th class="thla">입자 기호</th>
		<td class="tdl"><input type='text' class='txtl300' name='pr_part_symbol' size='50' maxlength='500' value=''></td>
	</tr>
	<tr>
		<th class="thla">입자 이름(영문)</th>
		<td class="tdl"><input type='text' class='txtl300' name='pr_part_name' size='50' maxlength='500' value=''></td>
	</tr>
	<tr>
		<th class="thla">입자 질량</th>
		<td class="tdl"><input type='text' class='txtr300' name='pr_part_mass' size='15' maxlength='20' value=''> g/mol</td>
	</tr>
    <tr>
      <th class="thla">원자/전자 구분</th>
      <td class="tdl">
          <jsp:useBean id="part_code" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
          <select name="pr_part_type_code" onChange="selectItem()" class="txtl">
			<%
				Vector pt_code = part_code.getAMOption();
				int pt_count = pt_code.size();
				for (int i = 0; i < pt_count; i++) {
					Code_Info code = (Code_Info) pt_code.elementAt(i);
					String code_id = code.getPL_CI_ID();
					String code_name = code.getPL_CI_NAME();
					String code_exp = code.getPL_CI_ID_EXP();
			%>
			<option value='<%=code_id%>'<%=code_id.equals("AM_00")?"selected":""%>><%=code_exp%></option>
			<%
				}
			%>
      </select></td>
    </tr>
    <tr>
		<th class="thla">원자 기호 번호</th>
		<td class="tdl"><input type='text' class='txtl300' name='pr_part_amnum' size='5' value=''></td>
	</tr>
	<tr>
		<th class="thla">원자/분자 개수</th>
		<td class="tdl"><input type='text' class='txtl300' name='pr_part_amcount' size='5' value=''></td>
	</tr>
</table>
<div class="bbtn_c">
<a href="javascript:uploadPartInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','images/img_cont/save_upper.gif',1)"><img src="images/img_cont/save_normal.gif" name="Image1" width="80" height="24" border="0" id="Image1">
</a></div>
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