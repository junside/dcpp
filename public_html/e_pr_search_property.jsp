<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<%
	/**
	* 물성 정보를 검색하는 새로운 JSP 페이지(view)
	* by Junside(J.H Park)
	*/
	
	/*
	 * 작업 시나리오
	 * 1. 입사 입자에 값이 있는지 확인(NULL이 아닌 값이 있을 경우)
	 * 1-1. 입사 입자에 값이 e 이면 Electron Impact 항만 보이는 페이지로 이동
	 * 1-2. 입사 입자에 값이 hv 이면 Photon Impact 항만 보이는 페이지로 이동
	 * 1-3. 입사 입자에 값이 입자 코드 이면 Heavy particle Impact 항만 보이는 페이지로 이동
	 * 2. 값이 없을 경우
	 * 2-1. 대분류, 데이터분류, 표적입자에 입력된 값을 토대로 조회
	 * 3. 프로세스와 참조표준인증데이터 여부는 옵션으로 처리
	 */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
<script type="text/javascript">
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

	function searchInfo(){ //검색
		if(searchInfoCheck("1") == false){
			if(confirmMsg() == true){
				var form2 = document.form1;
				form2.action = "e_pr_search_property_result.jsp";
				//form2.action = "pr_search_property_all_test.jsp";
				form2.submit();
				
				//var form2 = document.form1;	
				//if(form2.part_2_sym.value.toLowerCase()  == 'e'){ //e
				//	form2.action = "pr_search_property_ei.jsp";
				//	form2.submit();
				//}else if(form2.part_2_sym.value.toLowerCase() == 'hv'){ //hv
				//	form2.action = "pr_search_property_pi.jsp";
				//	form2.submit();
				//}else if(form2.part_2_sym.value.length > 0){ //입자 코드 입력에 값이 있으면,
				//	form2.action = "pr_search_property_hpi.jsp";
				//	form2.submit();
				//}else{
				//	form2.action = "pr_search_property_all.jsp";
				//	//form2.action = "pr_search_property_all_test.jsp";
				//	form2.submit();
				//}			
			}
		}else{
			return;
		}	
	}

	function confirmMsg(){
		var result = confirm("Are you OK?");
		return result;
	}

	function searchInfoCheck(flag){ //입력 데이터 유효성검증
		var form2 = document.form1;	
		var errorV = new Boolean(false); //에러를 나타내는 변수로 기본값은 false로 설정	
		
		//1. 입자 기호 체크
		if(form2.tb_option.value == 'TB_00'){
			errorV = true;
			alert("Please Select Categorize!");
			form2.tb_option.focus();
			return;
		}
		if(form2.db_option.value == 'DB_00'){
			errorV = true;
			alert("Please Select Collision Data Categorize!");
			form2.db_option.focus();
			return;
		}
		if(form2.mp_option.value == 'MP_00'){
			errorV = true;
			alert("Please Select Collision Process!");
			form2.db_option.focus();
			return;
		}
		if(flag == '2'){
			if(form2.part_1_id.value.length <= 0){
				errorV = true;
				alert("Please Select Target Particle!");
				form2.part_1_sym.focus();
				return;
			}
		}
		return errorV;
	}
	
	function selectPartID(part_id){
		var form2 = document.form1;	
		var db = form2.db_option.value;
		//var tb = form2.tb_option.value;
		var mp = form2.mp_option.value;
		var t_id = form2.part_1_id.value;
		
		if(part_id == 'part_1_sym'){
			//var id = "part_1_id";//id
			var sym = "part_1_sym";//sym	
			//var mp = "mp_option";//mp
			window.open("e_pr_select_target_part_new.jsp?db="+db+"&sym="+sym,"select_t_part","width=700,height=600,scrollbars=yes");
			//window.open("pr_select_target_part_new.jsp?id="+id+"&sym="+sym+"&db="+db+"&tb="+tb+"&mp="+mp,"select_t_part","width=700,height=600,scrollbars=yes");
		}else{
			if(searchInfoCheck("1") == false){
				//var id = "part_2_id";//id
				var sym = "part_2_sym";//sym
				//var mp = "mp_option";//mp
				window.open("e_pr_select_projectile_part_new.jsp?id="+t_id+"&sym="+sym+"&db="+db+"&mp="+mp,"select_p_part","width=700,height=600,scrollbars=yes");
			}

		//alert(db);
		//alert(mp);
		
		
		//window.open("pr_insert_expression.jsp?no="+no,"insert_expression","width=650,height=450,toolbar=no");
		}
	}
	
	function addExpression(part_select){ //표현 만드는 스크립트
		var form2 = document.form1;
	
		if(part_select == 'part_1_sym'){
			var part_exp = document.getElementById('part1_exp');
			if(form2.part_1_sym.value.length > 0)
			{
				part_exp.innerHTML = form2.part_1_sym.value;
				
			}
		}else{
			var part_exp = document.getElementById('part2_exp');
			if(form2.part_2_sym.value.length > 0)
			{
				part_exp.innerHTML = form2.part_2_sym.value;
				
			}
		}
	}

</script>
</head>
<body>
<form name="form1" action="" method="POST">
<input type="hidden" name="pr_expression" value=""/>		
<jsp:useBean id="option_code" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
<p class="level2">Insert Search Option for Plasma Properties </p>
<span class="help">If you click button(target or projectile), open the select window of particle.</span><br>
<span class="help">If you want to insert text 'e', 'hv' at projectile particle, please direct typing in the text field of projectile particle.</span><br>
<span class="help">If you want to select another particle at projectile particle field, please click button at projectile particle field.</span><br>
<span class="help">If you insert text 'e' at projectile particle field, search for Electron Impact.</span><br>
<span class="help">If you insert text 'hv' at projectile particle field, search for Photon Impact.</span><br>
<span class="help">If you insert particle code at projectile particle field, search for Heavy Particle Impact.</span><br>
<table class="tab5">
<col width="150">
<col width="200">
<col width="100">
<col width="200">
  <tr>
  	<th class="thc"><font color = red>Categorize</font></th>
    <td class="tdl">
		<select name="tb_option" onChange="" >
			<%
				Vector tb_code = option_code.getTBOption();
				int tb_count = tb_code.size();
				for (int i = 0; i < tb_count; i++) {
					Code_Info code = (Code_Info) tb_code.elementAt(i);
					String code_id = code.getPL_CI_ID();
					String code_name = code.getPL_CI_NAME();
					String code_exp = code.getPL_CI_ID_EXP();
			%>
			<option value='<%=code_id%>'<%=code_id.equals("TB_00")?"selected":""%>><%=code_exp%></option>
			<%
				}
			%>
		</select>
   	</td>
   	<th class="thc"><font color = red>Collision data categorize</font></th>
     	<td class="tdl">
		<select name="db_option" onChange="">
			<%
				Vector db_code = option_code.getDBOption();
				int db_count = db_code.size();
				for (int i = 0; i < db_count; i++) {
					Code_Info code = (Code_Info) db_code.elementAt(i);
					String code_id = code.getPL_CI_ID();
					String code_name = code.getPL_CI_NAME();
					String code_exp = code.getPL_CI_ID_EXP();
			%>
			<option value='<%=code_id%>'<%=code_id.equals("DB_00")?"selected":""%>><%=code_exp%></option>
			<%
				}
			%>
		</select>
   	</td>
  </tr>  
  <tr>
  <th class="thc"><font color = red>Target particle</font></th>
  	<td class="tdl">
  		<input type="text" name="part_1_sym" value="" onfocus="addExpression('part_1_sym')" size="15" maxlength="200" readOnly>
  		<input type="hidden" name="part_1_id" value=""/>
  		<input type="hidden" name="mp_option" value=""/>
		<input type="button" name="part1_select" onclick="selectPartID('part_1_sym')" value="Click">
  	</td>  	
  <th class="thc">Projectile particle</th>
  	<td class="tdl">
		<input type="text" name="part_2_sym" value="" onfocus="addExpression('part_2_sym')" size="15" maxlength="200">
  		<input type="hidden" name="part_2_id" value=""/>
		<input type="button" name="part2_select" onclick="selectPartID('part_2_sym')" value="Click"></td>  	
  </tr>
  <tr>
  <th class="thc">Target particle expression</th>
  	<td class="tdl" id="part1_exp"></td>   	
  <th class="thc">Projectile particle expression</th>
  	<td class="tdl" id="part2_exp"></td>  	
  </tr>
  <tr>
   	<th class="thc">Standard Reference Data</th>
     	<td class="tdl">
     		<input type="checkbox" name="srd_value" value="" disabled>
     	</td>
    <th class="thc"></th>
     	<td class="tdl">
     		
     	</td>
  </tr>
</table>
<div class="bbtn_r">
<a href="javascript:searchInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('searchImage','','images/img_cont/eng/search_upper.gif',1)"><img src="images/img_cont/eng/search_normal.gif" name="searchImage" width="80" height="24" border="0" id="searchImage" /></a>
</div>
</form>
</body>
</html>