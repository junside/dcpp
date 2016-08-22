<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.board.model.Board_Info"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>
<%request.setCharacterEncoding("EUC-KR");%>
<jsp:useBean id="searchControl" class="nfri.dcpp.properties.business.Ctr_Property_Search_Process" scope="page"/>
<jsp:useBean id="noticeControl" class="nfri.dcpp.board.business.Ctr_Board_Process" scope="page"/>
<%
	//새로 입력된 물성 데이터 가져오기
	Vector search_info = searchControl.selectSearchNewPropertyList();
	int data_size = search_info.size();
	
	Vector notice_info = noticeControl.select_NT_Article();
	int notice_size = notice_info.size();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Datacenter for Plasma Properties</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link href="css/common.css" rel="stylesheet" type="text/css" />
</head>
<LINK REL="stylesheet" HREF='style.css'>
<script>
function saveMsg(number){
	var num = number;
	switch(num) {
		case '1': //기본정보 보기
			var result = confirm("Would you like to see the information of a properties?");
			return result;
			break;
		default :		
			break;
	}		
}
function viewBasicInfo(pr_no, artcl_no){ //기본정보 보기
	if(saveMsg('1') == true){
		window.open("e_pr_view_property_info_main.jsp?pr_no="+pr_no+"&artcl_no="+artcl_no,"property_info","width=100%,height=100%,toolbar=no,location=no,status=no");
		return;
	}
}

function viewNoticeInfo(num){ //공지글 보기
	window.open("ntb_view_content.jsp?num="+num,"notice_info","width=100%,height=100%,toolbar=no,location=no,status=no");
	return;
}
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr background="images/img_cont/top_bg02.gif"> 
    <td width="925" height="16">&nbsp;</td>
    <td height="16">&nbsp;</td>
  </tr>
  <tr> 
    <td width="925"> 
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr> 
          <td width="10%"><img src="images/img_cont/dcpp_logo.gif" width="234" height="62" usemap="#Map" border="0"></td>
          <td width="691"><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0" width="691" height="62">
              <param name=movie value="swf/eng/center_menu.swf">
              <param name=quality value=high>
              <embed src="swf/eng/center_menu.swf" quality=high pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="691" height="62">
              </embed> 
            </object></td>
        </tr>
      </table>
    </td>
    <td>&nbsp;</td>
  </tr>
  <tr> 
    <td width="925" height="3"><img src="images/img_cont/top_bg03.gif" width="47" height="3"></td>
    <td height="3"></td>
  </tr>
  <tr> 
    <td width="925">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="21%" align="left" valign="top"><img src="images/img_cont/text_bg01.gif" width="16" height="16"></td>
          <td width="79%"><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0" width="909" height="341">
              <param name=movie value="swf/eng/center_text.swf">
              <param name=quality value=high>
              <embed src="swf/eng/center_text.swf" quality=high pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="909" height="341">
              </embed> 
            </object></td>
        </tr>
      </table>
    </td>
    <td>&nbsp;</td>
  </tr>
  <tr> 
    <td width="925">
		<table>
		   <tr> 
			<td valign = "top">
				<table class="tab10">
				   <tr> 
					<td>
						<img src="images/img_cont/notice.gif" width="342" height="51" usemap="#Map2" border="0"> 
					</td>
				   </tr>
				   <%
				   	if(notice_size > 0){
					  	for (int i = 0 ; i < notice_size; i++) {
													
					  		Board_Info basic = (Board_Info) notice_info.elementAt(i);
							//글번호
							int num = basic.getNum();						
							String subject = basic.getSubject();
							int readcount = basic.getReadcount();
							int date = ComUtil.getBetweenDateCount(basic.getReg_date());
					  		%>
					  		<tr>
								<td class = "tdl1"> 
									<a href="javascript:void(0);"  onClick="viewNoticeInfo('<%=num%>'); return false;">
									 <%=subject%>
									</a>
									<% if(date < 15){%>
							           <img src="images/icon_cont/new.gif" border="0"  height="9">
							     	<%}%>
									<% if(readcount >= 40){%>
							           <img src="images/icon_cont/hot.gif" border="0"  height="13">
							     	<%}%>
								</td>
							</tr>
					  		<%
					  	}
				   	}else{
				   		%>
				  		<tr>
							<td class = "tdl1"> 
								We have not a notice.
							</td>
						</tr>
				  		<%
				   	}
				  %>
				</table>
			</td>
			<td valign = "top">
				<table class="tab10">
				   <tr> 
					<td>
						<img src="images/img_cont/newplasma.gif" width="326" height="51" usemap="#Map3" border="0"> 
					</td>
				   </tr>
				   <%
				  	for (int i = 0 ; i < data_size; i++) {
												
				  		Properties_Basic_Info basic = (Properties_Basic_Info) search_info.elementAt(i);
						//물성번호
						String pro_no = basic.getPL_BI_DATA_NUM();
						//참고문헌번호
						String artcl_no = basic.getPL_RAI_ARTCL_NUM();
						//표현식
						String expression = basic.getPL_BI_EXPRESSION();
				  		//입력날짜
						int pr_in_date = ComUtil.getBetweenDateCount(basic.getPL_BI_INSERT_DATE()); 
				  		%>
						<tr>
								<td class = "tdl1"> 
									<a href="javascript:void(0);"  onClick="viewBasicInfo('<%=pro_no%>','<%=artcl_no%>'); return false;"> <%=ComUtil.getAbbrString(expression, 80)%> </a>
									<% if(pr_in_date < 15){%>
							           <img src="images/icon_cont/new.gif" border="0"  height="9">
							     	<%}%>
								</td>
						</tr>
				  		<%
				  	}
				  %>				   
				</table>
			</td>
			<td valign = "top">
				<table class="tab11">
				   <tr> 
					<td>
						<img src="images/img_cont/welcome.gif" width="257" height="51"> 
					</td>
				   </tr>
				   <tr>
					<td>                      	                       
						<font color="#FF3300">Welcome to Datacenter for Plasma Properties(DCPP).</font> DCPP was designated for development of the standard reference data in the area of physics and chemistry from the Ministry of Knowledge Economy.
					</td>
				   </tr>
				   <tr>
					<td>                      	                       
						<a href="e_c_intro.jsp"><img src="images/img_cont/more.gif" width="36" height="14" border="0"></a>
					</td>
				   </tr>			   
				</table>
			</td>
		  </tr>
		</table>
    </td>
  </tr>
  <tr> 
    <td width="925" background="images/img_cont/bottom_bg01.gif"><img src="images/img_cont/bottom_bg01.gif" width="16" height="20"></td>
    <td background="images/img_cont/bottom_bg01.gif">&nbsp;</td>
  </tr>
  <tr background="images/img_cont/bottom_bg02.gif"> 
    <td width="925">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><img src="images/img_cont/bottom01.gif" width="668" height="52" usemap="#Map4" border="0"></td>
          <td><img src="images/img_cont/bottom02.gif" width="257" height="52"></td>
        </tr>
      </table>
    </td>
    <td background="images/img_cont/bottom_bg02.gif">&nbsp;</td>
  </tr>
</table>
<map name="Map">
  <area shape="rect" coords="21,4,161,58" href="e_index.jsp">
</map>
<map name="Map2">
  <area shape="rect" coords="276,32,327,50" href="#">
</map>
<map name="Map3"> 
  <area shape="rect" coords="259,30,310,49" href="e_pr_search.jsp">
</map>
<map name="Map4">
  <area shape="rect" coords="26,8,121,46" href="http://www.nfri.re.kr" target="_blank">
</map>
</body>
</html>