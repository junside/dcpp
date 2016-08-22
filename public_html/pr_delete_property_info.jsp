<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<%
	String gotoindex = "";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>플라즈마 물성, 수치 정보 삭제 및 검증 구분 변경</title>
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
function delete_process(){
	var form2 = document.form1;

	var button_value="";
	var buttons = document.form1.select;
	var db_buttons = document.form1.db_option.value;
	var mp_buttons = document.form1.mp_option.value;
	var sp_buttons = document.form1.sp_option.value;
	var ic_buttons = document.form1.ic_option.value;
	var et_buttons = document.form1.et_option.value;
	var pr_no_value = document.form1.pr_no.value;
	//var part_mod_value = document.form1.part_mod.value;
	var button_flag = false;
	
	for(var i = 0; i < buttons.length; i++){
		if(buttons[i].checked){
			button_value = buttons[i].value;
		}
	}
	
	var result = confirm("해당 작업을 다시 한번 확인하시 맞으시면 진행하세요!!");

	if(result==true){
		form2.gotoindex.value = button_value;
		if(button_value =='part_mod'){
			window.open("pr_modify_target_part_info_update.jsp?pr_no="+pr_no_value,"equa_info","width=700,height=900");
			return;
			//form2.target = "_selft";
		   // form2.action = "pr_modify_target_part_info_update.jsp?pr_no="+pr_no_value;		
		    //return;    
			//form2.submit();	
		}
		if(button_value =='db_modify'){
			if(sp_buttons == 'DB_00'){
				alert("데이터분류 값을 선택하세요.");
				return;
			}
		}
		if(button_value =='mp_modify'){
			if(sp_buttons == 'MP_00'){
				alert("주프로세스 값을 선택하세요.");
				return;
			}
		}
		if(button_value =='sp_modify'){
			if(sp_buttons == 'SP_00'){
				alert("부프로세스 값을 선택하세요.");
				return;
			}
		}
		if(button_value =='ic_modify'){
			if(ic_buttons == 'IC_00'){
				alert("충돌방식 값을 선택하세요.");
				return;
			}
		}
		if(button_value =='et_modify'){
			if(et_buttons == 'ET_00'){
				alert("검증 구분 값을 선택하세요.");
				return;
			}
		}
		if(pr_no_value.length==0){
			alert("처리할 물성정보 번호를 입력하세요.");
			return;
		}
		//alert(form2.gotoindex.value);
		form2.target = "_self";
		form2.action = "pr_delete_property_info_check.jsp";
		form2.submit();		
	}else{
		return;
	}	
}
</script>
</head>
<body>
<form name="form1" action="" method="POST">
<input type="hidden" name="gotoindex" value="<%=gotoindex%>"/>
<jsp:useBean id="option_code" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
<div id="content">
<p class="level2">플라즈마 물성 정보 수정</p>
<span class="help">반드시 한번에 하나 씩만 처리합니다.</span><br>
<span class="help"><font color="red">물성정보와 수치정보 삭제 시에는 더더욱 주의하여 주세요.</font></span><br>
<table class="tab1">
	<col width="25%">
	<col width="25%">
	<tr height="40">
		<th class="thc" rowspan="9"><font color = red>작업 선택</font></th>
		<td>
			<input type ="radio" name="select" value="part_mod" checked><font color="blue">반응식 정보 (물성반응식 정보, 입자 정보) 수정</font>			
		</td>
	</tr>
	<tr>
		<td>
			<input type ="radio" name="select" value="db_modify"><font color="green">데이터분류 정보 변경</font>
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
		<td>
			<input type ="radio" name="select" value="mp_modify"><font color="green">주프로세스 정보 변경</font>			     	
			<select name="mp_option" onChange="">
				<%
					Vector mp_code = option_code.getMPOption();
					int mp_count = mp_code.size();
					for (int i = 0; i < mp_count; i++) {
						Code_Info code = (Code_Info) mp_code.elementAt(i);
						String code_id = code.getPL_CI_ID();
						String code_name = code.getPL_CI_NAME();
						String code_exp = code.getPL_CI_ID_EXP();
				%>
				<option value='<%=code_id%>'<%=code_id.equals("MP_00")?"selected":""%>><%=code_exp%></option>
				<%
					}
				%>
			</select>
		</td>
	</tr>	
	<tr>
		<td>
			<input type ="radio" name="select" value="sp_modify"><font color="green">부프로세스 정보 변경</font>			     	
			<select name="sp_option" onChange="">
				<%
					Vector sp_code = option_code.getSPOption();
					int sp_count = sp_code.size();
					for (int i = 0; i < sp_count; i++) {
						Code_Info code = (Code_Info) sp_code.elementAt(i);
						String code_id = code.getPL_CI_ID();
						String code_name = code.getPL_CI_NAME();
						String code_exp = code.getPL_CI_ID_EXP();
				%>
				<option value='<%=code_id%>'<%=code_id.equals("SP_00")?"selected":""%>><%=code_exp%></option>
				<%
					}
				%>
			</select>
		</td>
	</tr>
	<tr>
		<td>
			<input type ="radio" name="select" value="ic_modify"><font color="green">충돌방식 정보 변경</font>			     	
			<select name="ic_option" onChange="">
				<%
					Vector ic_code = option_code.getICOption();
					int ic_count = ic_code.size();
					for (int i = 0; i < ic_count; i++) {
						Code_Info code = (Code_Info) ic_code.elementAt(i);
						String code_id = code.getPL_CI_ID();
						String code_name = code.getPL_CI_NAME();
						String code_exp = code.getPL_CI_ID_EXP();
				%>
				<option value='<%=code_id%>'<%=code_id.equals("IC_00")?"selected":""%>><%=code_exp%></option>
				<%
					}
				%>
			</select>
		</td>
	</tr>
	<tr>
		<td>
			<input type ="radio" name="select" value="et_modify"><font color="green">검증구분 정보 변경</font>
			<select name="et_option" onChange="">
				<%
					Vector et_code = option_code.getETOption();
					int et_count = et_code.size();
					for (int i = 0; i < et_count; i++) {
						Code_Info code = (Code_Info) et_code.elementAt(i);
						String code_id = code.getPL_CI_ID();
						String code_name = code.getPL_CI_NAME();
						String code_exp = code.getPL_CI_ID_EXP();
				%>
				<option value='<%=code_id%>'<%=code_id.equals("ET_00")?"selected":""%>><%=code_exp%></option>
				<%
					}
				%>
				</select>
		</td>
	</tr>
		<tr>
		<td>
			<input type ="radio" name="select" value="num_del"><font color="red">수치 정보 삭제</font>
		</td>
	</tr>
	<tr>
		<td>
			<input type ="radio" name="select" value="num_mod"><font color="blue">수치 정보 (소수점) 수정 >>> 테스트중</font>			
		</td>
	</tr>
	<tr>
		<td>
			<input type ="radio" name="select" value="pr_del"><font color="red">물성정보 전체삭제</font>
		</td>
	</tr>
	<tr height="40">
		<th class="thc"><font color = red>처리할 물성 정보 번호</font></th>		
		<td class="tdl"><input type='text' name='pr_no' size='50' value=''></td>
	</tr>
</table>
<div class="bbtn_c">
	<a href="javascript:delete_process()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('delete','','images/img_cont/decide_upper.gif',1)"><img src="images/img_cont/decide_normal.gif" name="delete" width="80" height="24" border="0" id="delete" /></a>
</div>
</div>
</form>
</body>
</html>