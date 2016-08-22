<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<%
/**
* 등급 유력한 물성 정보를 조건에 따라 검색하는 JSP 페이지(view)
* by Junside(J.H Park)
*/
	
	//String user_id = (String)session.getAttribute("user_id");
String user_id = ComUtil.isNullToDashString((String)session.getAttribute("user_id"));
if(user_id.equalsIgnoreCase("-")){
	%>
	<script language = javascript>
	alert("다시 로그인 하세요.");
	</script>
	<%
	response.sendRedirect("c_login.jsp?flag=AU");
}else{
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

function makeSup(sup_org){ //sup 태그 사용하기
	return sup_org.sup();
}

function changeProjectile(){
	var form2 = document.form1;
	var ic_value = form2.ic_option.value;
	//var ic_value = document.getElementsById("ic_option");	
	//var project_express = document.getElementById("projectile");	
	//alert(ic_value);
	
	if(ic_value == "IC_00"){
		var pro_exp = "";
		var pro_exp_id = "";
		form2.projectile.value = pro_exp;
		form2.projectile_id.value = pro_exp_id;
	}
	if(ic_value == "IC_01"){
		var pro_exp = "e";
		var pro_exp_id = "E0021";
		form2.projectile.value = pro_exp;	
		form2.projectile_id.value = pro_exp_id;	
		return;
		//project_express.innerHTML = pro_exp;
		//project_express.value = pro_exp;
	}
	if (ic_value == "IC_02"){
		var pro_exp = "hv";
		var pro_exp_id = "P0067";
		form2.projectile.value = pro_exp;
		form2.projectile_id.value = pro_exp_id;
		return;
	}
	if (ic_value == "IC_03"){
		var pro_exp = "";
		var pro_exp_id = "";
		form2.projectile.value = pro_exp;
		form2.projectile_id.value = pro_exp_id;
		getAtomicInfo('projectile');
		return;
	}
}

function searchInfo(){ //검색
	if(searchInfoCheck() == false){
		var form2 = document.form1;	
		form2.action = "pr_assess_search_inf_grd_select_list.jsp";
		form2.submit();			
	}
	return;	
}

function searchInfoCheck(){ //입력 데이터 유효성검증
	var form2 = document.form1;	
	var errorV = new Boolean(false); //에러를 나타내는 변수로 기본값은 false로 설정
	//1. 콤보 값 체크	
	if(form2.ic_option.value=='IC_00'){
		errorV = true;
		alert("충돌 정보를 선택하세요!");
		form2.ic_option.focus();
		return;
	}
	if(form2.mp_option.value=='MP_00'){
		errorV = true;
		alert("주프로세스 정보를 선택하세요!");
		form2.mp_option.focus();
		return;
	}
	if(form2.sp_option.value=='SP_00'){
		errorV = true;
		alert("부프로세스 정보를 선택하세요!");
		form2.sp_option.focus();
		return;
	}
	if(form2.xax_unit.value=='XU_00'){
		errorV = true;
		alert("X 단위 정보를 선택하세요!");
		form2.xax_unit.focus();
		return;
	}
	if(form2.xay_unit.value=='YU_00'){
		errorV = true;
		alert("Y 단위 정보를 선택하세요!");
		form2.xay_unit.focus();
		return;
	}
	
	//2. 입사입자 체크
	if(form2.projectile.value.length < 1){	
		//var no = '0';		
		errorV = true;
		alert("Projectile 값을 입력하세요");
		return;
	}
	//3. 표적입자 체크
	if(form2.tg_name.value.length < 1){	
		//var no = '0';		
		errorV = true;
		alert("Target 값을 입력하세요");
		return;
	}
	//4. 생성입자 체크
	if(form2.pd_name.value.length < 1){
		if(form2.pd_ionic.value.length > 0 || form2.pd_elec.value.length > 0 || form2.pd_fine.value.length > 0)
		{
			errorV = true;
			alert("Product 값을 입력하세요");	
			return;	
		}	
	}
	
	//if(form2.pd_name.value.length < 1){
		//var no = '1';			
		//errorV = true;
		//alert("Product 값을 입력하세요");
		//window.open("pr_select_part_info.jsp?no="+no,"reference_search","width=650,height=450,toolbar=no");		
		//return;
	//}	
	return errorV;
}

function getAtomicInfo(name){//입자정보 가져오기
	var part_no = name;//value
	var part_id = name + "_id";//id
	window.open("pr_select_part_info.jsp?id="+part_id+"&value="+part_no,"reference_search","width=700,height=450,toolbar=no,location=no,status=no");
}

function insertExpress(id){
	//var part_1 = "part_1";//id
	window.open("pr_insert_expression.jsp?id="+id,"insert_expression","width=700,height=450,toolbar=no,location=no,status=no");

	//window.open("pr_insert_expression.jsp?no="+no,"insert_expression","width=650,height=450,toolbar=no");
}
</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<input type="hidden" name="user_id" value='<%=user_id%>'>
<jsp:useBean id="option_code" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
<p class="level2">Search Condition</p>
<span class="help">붉은 색 부분은 반드시 입력해야 되는 필드입니다.</span><br>
<span class="help">표적 입자 및 입사 입자 입력시 입력 필드를 클릭하면 입력창이 나타납니다.</span><br>
<table class="tab2">
<col width="100">
<col width="150">
<col width="100">
<col width="300">
  <tr>
   	<th class="thc"><font color = red>Impact</font></th>
     	<td class="tdl">
		<select name="ic_option" onChange="changeProjectile()">
			<%
				Vector ic_code = option_code.getICOption();
				int ic_count = ic_code.size();
				for (int i = 0; i < ic_count; i++) {
					Code_Info code = (Code_Info) ic_code.elementAt(i);
					String code_id = code.getPL_CI_ID();
					String code_name = code.getPL_CI_NAME();
					String code_exp = "";
					if(code_id.equals("IC_00")){
						code_exp = "Select";
					}else{
						code_exp = code.getPL_CI_ID_EXP();
					}
					//String code_exp = code.getPL_CI_ID_EXP();
			%>
			<option value='<%=code_id%>'<%=code_id.equals("IC_00")?"selected":""%>><%=code_exp%></option>
			<%
				}
			%>
		</select>
   	</td>
   	<th class="thc"><font color = red>Projectile</font></th>
     	<td class="tdl">
     	<input type="text" name="projectile" value="" size="30" readonly>
     	<input type="hidden" name="projectile_id" value="">
   	</td>
  </tr>
  <tr>
  <th class="thc"><font color = red>Main Process</font></th>
     	<td class="tdl">
		<select name="mp_option" onChange="">
			<%
				Vector mp_code = option_code.getMPOption();
				int mp_count = mp_code.size();
				for (int i = 0; i < mp_count; i++) {
					Code_Info code = (Code_Info) mp_code.elementAt(i);
					String code_id = code.getPL_CI_ID();
					String code_name = code.getPL_CI_NAME();
					String code_exp = "";
					if(code_id.equals("MP_00")){
						code_exp = "Select";
					}else{
						code_exp = code.getPL_CI_ID_EXP();
					}
					//String code_exp = code.getPL_CI_ID_EXP();
			%>
			<option value='<%=code_id%>'<%=code_id.equals("MP_00")?"selected":""%>><%=code_exp%></option>
			<%
				}
			%>
		</select>
   	</td>
   	<th class="thc"><font color = red>Sub Process</font></th>
     	<td class="tdl">
		<select name="sp_option" onChange="">
			<%
				Vector sp_code = option_code.getSPOption();
				int sp_count = sp_code.size();
				for (int i = 0; i < sp_count; i++) {
					Code_Info code = (Code_Info) sp_code.elementAt(i);
					String code_id = code.getPL_CI_ID();
					String code_name = code.getPL_CI_NAME();
					String code_exp = "";
					if(code_id.equals("SP_00")){
						code_exp = "Select";
					}else{
						code_exp = code.getPL_CI_ID_EXP();
					}
					//String code_exp = code.getPL_CI_ID_EXP();
			%>
			<option value='<%=code_id%>'<%=code_id.equals("SP_00")?"selected":""%>><%=code_exp%></option>
			<%
				}
			%>
		</select>
   	</td>
  </tr>
  <tr>
  	<th class="thc"><font color = red>X Unit</font></th>
	<td class="tdl">
		<select name="xax_unit" onChange="">
			<%
				Vector xu_code = option_code.getXUOption();
				int xu_count = xu_code.size();
				for (int i = 0; i < xu_count; i++) {
					Code_Info code = (Code_Info) xu_code.elementAt(i);
					String code_id = code.getPL_CI_ID();
					String code_name = code.getPL_CI_NAME();
					String code_exp = "";
					if(code_id.equals("XU_00")){
						code_exp = "Select";
					}else{
						code_exp = code.getPL_CI_ID_EXP();
					}
					//String code_exp = code.getPL_CI_ID_EXP();
			%>
			<option value='<%=code_id%>'<%=code_id.equals("XU_00")?"selected":""%>><%=code_exp%></option>
			<%
				}
			%>
		</select>
   	</td>
   	<th class="thc"><font color = red>Y Units</font></th>
    <td class="tdl">
    	<select name="xay_unit" onChange="">
			<%
				Vector yu_code = option_code.getYUOption();
				int yu_count = yu_code.size();
				for (int i = 0; i < yu_count; i++) {
					Code_Info code = (Code_Info) yu_code.elementAt(i);
					String code_id = code.getPL_CI_ID();
					String code_name = code.getPL_CI_NAME();
					String code_exp = "";
					if(code_id.equals("YU_00")){
						code_exp = "Select";
					}else{
						code_exp = code.getPL_CI_ID_EXP();
					}
					//String code_exp = code.getPL_CI_ID_EXP();
			%>
			<option value='<%=code_id%>'<%=code_id.equals("YU_00")?"selected":""%>><%=code_exp%></option>
			<%
				}
			%>
		</select>
	</td>
 </tr>
   <tr>
   	<th colspan ="2" class="thr"><font color = red>1. Target : </font></th>
     	<td colspan ="2" class="tdl">
     	<input type="text" name="tg_name" value="" onclick="getAtomicInfo('tg_name')" size="50" readonly>		
		<input type="hidden" name="tg_name_id" value="">
   	</td>
  </tr>
  <tr>
    <td></td>
   	<td class="tdr">Ionic State :</td>
    <td colspan ="2" class="tdl">
		<input type="text" name="tg_ionic" value="" size="50">
   	</td>
  </tr>
  <tr>
    <td></td>
   	<td class="tdr">Electron Configuration :</td>
    <td colspan ="2" class="tdl">
		<input type="text" name="tg_elec" value="" size="50">
   	</td>
  </tr>
  <tr>
    <td></td>
   	<td class="tdr">Fine Structure or <br>Vibrational Rotational State :</td>
    <td colspan ="2" class="tdl">
		<input type="text" name="tg_fine" value="" size="50">
   	</td>
  </tr>
  <tr>
   	<th colspan ="2" class="thr"><font color = green>2. Product : </font></th>
     	<td colspan ="2" class="tdl">	
     	<input type="text" name="pd_name" value="" onclick="getAtomicInfo('pd_name')" size="50" readonly>	
		<input type="hidden" name="pd_name_id" value="">
   	</td>
  </tr>
  <tr>
    <td></td>
   	<td class="tdr">Ionic State :</td>
    <td colspan ="2" class="tdl">
		<input type="text" name="pd_ionic" value="" size="50">
   	</td>
  </tr>
  <tr>
    <td></td>
   	<td class="tdr">Electron Configuration :</td>
    <td colspan ="2" class="tdl">
		<input type="text" name="pd_elec" value="" size="50">
   	</td>
  </tr>
  <tr>
    <td></td>
   	<td class="tdr">Fine Structure or <br>Vibrational Rotational State :</td>
    <td colspan ="2" class="tdl">
		<input type="text" name="pd_fine" value="" size="50">
   	</td>
  </tr>
</table>
<div class="bbtn_c">
<a href="javascript:searchInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('searchImage','','images/img_cont/search_upper.gif',1)"><img src="images/img_cont/search_normal.gif" name="searchImage" width="80" height="24" border="0" id="searchImage" /></a>
</div>
</form>
</body>
</html>
<%}%>