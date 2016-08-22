<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="data_info" class="nfri.dcpp.properties.business.Ctr_Graph_Info_Process" scope="page"/>
<%
	/**
	* 참고문헌의 DOI정보를 입력하는 JSP 페이지(view)
	* by Junside(J.H Park)
	*/


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
				form2.action = "pr_article_insert_doi_data_check.jsp?file=1";
				form2.encoding = "multipart/form-data";
			}else{
				form2.action = "pr_article_insert_doi_data_check.jsp?file=2";
			}
			form2.submit();
		}
	}else{
		return;
	}	
}

function exitInfo(){ //종료
	var form2 = document.form1;
	if(confirmMsg('2') == true){
			
	}
}

function confirmMsg(number){
	var num = number;
	switch(num) {
		case '0': //정보 저장하기
			var result = confirm("입력하신 내용을 저장하시겠습니까?");
			return result;
			break;
		case '2': //작업종료
			var result = confirm("작업을 종료하시겠습니까?");
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
		//1. 참고문헌번호 체크
		if(form2.article_number.value.length < 1){
			errorV = true;
			alert("참고문헌번호를 입력하세요!");
			form2.article_number.focus();
			return;
		}
		
		//1. DOI 체크
		if(form2.doi_number.value.length < 1){
			errorV = true;
			alert("DOI 번호를 입력하세요!");
			form2.doi_number.focus();
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

</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST" >

<p class="level2">참고문헌 DOI 정보 입력</p>
<span class="help">
	참고문헌 번호와 DOI 번호를 입력하세요<br>
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
  	<th class="thr"><font color = red>참고문헌 번호</font></th>
    <td class="tdl">
		<input type="text" name="article_number" value="" size="25">
	</td>
   	<th class="thr"><font color = red>DOI 번호</font></th>
    <td class="tdl">
		<input type="text" name="doi_number" value="" size="25">
	</td>
  </tr>
  <tr>
  	<th class="thr">파일업로드</th>   
  	<td colspan="3"> 
  	  <input type="file" name="g_data_file" size="25" value=""><font color = red> Excel 파일(xls,csv)만 가능</font>
  	</td>
  </tr>
</table>
<div class="bbtn_r">
<a href="javascript:saveInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('saveImage','','images/img_cont/save_upper.gif',1)"><img src="images/img_cont/save_normal.gif" name="saveImage" width="80" height="24" border="0" id="saveImage" /></a>
<a href="javascript:exitInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('exitImage','','images/img_cont/exit_upper.gif',1)"><img src="images/img_cont/exit_normal.gif" name="exitImage" width="80" height="24" border="0" id="exitImage" /></a>
</div>
</form>
</body>
</html>