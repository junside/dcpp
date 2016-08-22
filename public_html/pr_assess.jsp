<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>
<%@page import="nfri.dcpp.com.util.ComVar"%>
<%
	//세션 정보를 확인해서 로그인 정보가 없으면 로그인 창으로 이동
	String user_id = ComUtil.isNullToNullString((String)session.getAttribute("user_id"));
	String auth = ComUtil.isNullToNullString((String)session.getAttribute("auth"));
	//if(user_id == null){
		//if(auth.equalsIgnoreCase("IU")){
		//	response.sendRedirect("c_login.jsp?flag="+auth);
		//}else{
		//	response.sendRedirect("c_login.jsp?flag=AU");
		//}		
	//}
	
	if(user_id.equalsIgnoreCase(ComVar.STRING_NULL_B)){		
		response.sendRedirect("c_login.jsp?flag=AU");				
	}else{
		//if(auth.equalsIgnoreCase("IAU") || auth.equalsIgnoreCase("AIU") || auth.equalsIgnoreCase("AU")){
		//	response.sendRedirect("pr_assess.jsp?flag="+auth);
		//}else{
		//	response.sendRedirect("c_login.jsp?flag=AU");
		//}
		if(!auth.equalsIgnoreCase("IAU") && !auth.equalsIgnoreCase("AIU") && !auth.equalsIgnoreCase("AU")){
			response.sendRedirect("c_login.jsp?flag=AU");
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>플라즈마물성데이터센터</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link href="css/common.css" rel="stylesheet" type="text/css" />
</head>
<script language='JavaScript' type='text/javascript'>
function autoResize(i)
{
    var iframeHeight=
    (i).contentWindow.document.body.scrollHeight;
    (i).height=iframeHeight+20;
}
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<LINK REL="stylesheet" HREF='style.css'>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0">
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
                <td><img src="images/left_cont/pr_intro_left_01.gif" width="239" height="55"></td>
              </tr>
              <tr> 
                <td><a href="pr_intro.jsp"><img src="images/left_cont/pr_intro_left_02.gif" width="239" height="44" border="0"></a></td>
              </tr>
              <tr> 
                <td><a href="pr_search.jsp"><img src="images/left_cont/pr_intro_left_03.gif" width="239" height="44" border="0"></a></td>
              </tr>
              <tr> 
                <td><a href="pr_insert.jsp"><img src="images/left_cont/pr_intro_left_08.gif" width="239" height="44" border="0"></a></td>
              </tr>
              <tr> 
                <td><a href="pr_assess.jsp"><img src="images/left_cont/pr_intro_left_09.gif" width="239" height="44" border="0"></a></td>
              </tr>
              <tr> 
                <td><img src="images/left_cont/pr_intro_left_06.gif" width="239" height="217" border="0"></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
            </table>
          </td>
          <td width="686" align="left" valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr> 
                <td colspan="2" height="10">                  
				</td>
              </tr>
              <tr> 
                <td colspan="2">
                  <div class="subtitle">
						플라즈마 물성 정보 최종 평가 
				  </div>
				</td>
              </tr>
              <tr> 
                <td width="3%">&nbsp;</td>
                <td width="97%">&nbsp;</td>
              </tr>
              <tr> 
                <td width="3%">&nbsp;</td>
                <td width="97%"><iframe src="pr_common_assess_list.jsp" name="assess" width="100%" height="100%" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" onload="autoResize(this)"></iframe></td>
              </tr>
              <tr> 
                <td width="3%">&nbsp;</td>
                <td width="97%">&nbsp;</td>
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
        <area shape="rect" coords="132,25,241,40" href="mailto:pjh1126@nfri.re.kr">
      </map>
    </td>
    <td background="images/img_cont/bottom_bg02.gif">&nbsp;</td>
  </tr>
</table>
</body>
</html>

