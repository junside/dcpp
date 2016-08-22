 <%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	String flag = request.getParameter("flag");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>플라즈마물성데이터센터</title>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR"></head>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<LINK REL="stylesheet" HREF='style.css'>
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
function check() {
	var form2 = document.form1;
	var org_id = form2.user_id.value;
	var org_pwd = form2.user_pwd.value;
	
	if (org_id=="") {
		alert("ID를 입력해 주십시오.");
		form2.user_id.focus();
		return ;
	}
	
	if(CheckChar(org_id)==false){
		alert("ID는 영문/숫자만 허용됩니다.");
		form2.user_id.value="";
		form2.user_pwd.value="";
		form2.user_id.focus();
		return;
	}

	if (org_pwd=="") {
		alert("비밀번호를 입력해 주십시오.");
		form2.user_pwd.focus();
		return ;
	}

	form2.action = "c_login_check.jsp";
	form2.submit();
}


function CheckChar(str) { 
    strarr = new Array(str.length); 
    var flag = true; 
    for (i=0; i<str.length; i++) { 
        strarr[i] = str.charAt(i) 
        if (i==0) { 
            if (!((strarr[i]>='a')&&(strarr[i]<='z'))) { 
                flag = false; 
            } 
        } else { 
            if (!((strarr[i]>='a')&&(strarr[i]<='z')||(strarr[i]>='0')&&(strarr[i]<='9')||(strarr[i]=='-')||(strarr[i]=='_')||(strarr[i]=='^'))) { 
                flag = false; 
            } 
        } 
    } 
    if (flag) { 
        return true; 
    } else { 
        return false; 
    } 
}

document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');

</script>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" onLoad="form1.user_id.focus()" >
<form name="form1" action="" method="POST" >
<input type="hidden" name="flag" value='<%=flag%>'>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="925"><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0" width="925" height="80">
        <param name=movie value="swf/center_menu_sub.swf">
        <param name=quality value=high>
        <embed src="swf/center_menu_sub.swf" quality=high pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="925" height="80">
        </embed> 
      </object></td>
    <td background="images/img_cont/top_bg02.gif" height="80">&nbsp;</td>
  </tr>
  <tr>
    <td width="925"><img src="images/img_cont/pic_01.gif" width="925" height="145"></td>
    <td background="images/img_cont/pic_bg01.gif">&nbsp;</td>
  </tr>
  <tr>
    <td width="925">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="239" align="left" valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/left_cont/c_login_left_02.gif" width="239" height="500"></td>
              </tr>
            </table>
          </td>
          <td width="686" align="left" valign="top">
            <table class="login">
            <col width="300">
			<col width="100">
			<col width="190">
			<col width="60">
              <tr> 
                <td class="tdc" rowspan="3"><img src="images/main_cont/dcpp_login_left.jpg" width="280" height="280" border="0"></td>
                <td class="tdc" colspan="3"><img src="images/main_cont/dcpp_login_right_top.jpg" width="360" height="80" border="0"></td>
              </tr> 
              <tr>
              	<td class="tdr">아이디</td>
              	<td class="tdl"><input class="text" tabindex=1 name="user_id" type="text" id="user_id" onkeydown="if(event.keyCode==13) event.keyCode=9;" >
              	</td>
              	<td class="tdl" rowspan="2">              		
						<a href="javascript:check()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('loginImage','','images/img_cont/login_upper.gif',1)"><img src="images/img_cont/login_normal.gif" name="loginImage" width="80" height="50" border="0" id="loginImage" tabindex=3 /></a>					
              	</td>
              </tr>  
              <tr>
              	<td class="tdr">패스워드</td>
              	<td class="tdl"><input class="text" tabindex=2 name="user_pwd" type="password" id="user_pwd" onkeydown="if(event.keyCode==13) check();"></td>
              </tr>          
            </table>            
          </td>
        </tr>
      </table>
    </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="925">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr> 
          <td><img src="images/img_cont/bottom01.gif" width="668" height="52" usemap="#Map4" border="0"></td>
          <td><img src="images/img_cont/bottom02.gif" width="257" height="52"></td>
        </tr>
      </table>
      <map name="Map4"> 
        <area shape="rect" coords="26,8,121,46" href="http://www.nfri.re.kr" target="_blank">
      </map>
    </td>
    <td background="images/img_cont/bottom_bg02.gif">&nbsp;</td>
  </tr>
</table>
</form>
</body>
</html>

