<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("euc-kr"); %>
<%@ page import = "nfri.dcpp.board.model.Board_Info" %>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>  
<jsp:useBean id="board_process" class="nfri.dcpp.board.business.Ctr_Board_Process" scope="page"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Data Center for Plasma Properties</title>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="./css/common.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%
	int nowPage = 0; //현재 페이지
	int nowBlock = 0; //현재 블록
	
	if(request.getParameter("nowPage") != null)
	{
		nowPage = Integer.parseInt(request.getParameter("nowPage"));
	}
	//현재 블록 설정
	if(request.getParameter("nowBlock") != null)
	{
		nowBlock = Integer.parseInt(request.getParameter("nowBlock"));
	}

	int num = Integer.parseInt(request.getParameter("num"));
	
  	Board_Info article = board_process.select_NT_Article_Content(num);
       
	int ref=article.getRef();
	int re_step=article.getRe_step();
	int re_level=article.getRe_level();
	int number = article.getNum();
%>
<script language = javascript>
function confirmInfo(){ //확인
	self.close();
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
<form>
<div id="content">
<input type="hidden" name="nowPage" value="<%=nowPage%>"/>
<input type="hidden" name="nowBlock" value="<%=nowBlock%>"/>
<p class="level2">Notice</p>
<table class="tabnote">
<col width="100">
<col width="150">
<col width="100">
<col width="150">
  <tr>
    <th class="thc">No</th>
    <td class="tdc">
	     <%=article.getNum()%></td>
    <th class="thc">Read</th>
    <td class="tdc"><%=article.getReadcount()%></td>
  </tr>
  <tr>
    <th class="thc">Writer</td>
    <td class="tdc"><%=article.getWriter()%></td>
    <th class="thc">Reg_Date</th>
    <td class="tdc"><%=article.getReg_date()%></td>
  </tr>
  <tr>
    <th class="thc">Subject</th>
	<td class="tdl" colspan="3"><%=article.getSubject()%></td>
  </tr>
  <tr>
    <th class="thc">Content</th>
    <td class="tdl" colspan="3"><pre><%=article.getContent()%></pre></td>
  </tr>
</table>
<div class="bbtn_c">
<a href="javascript:confirmInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('confirmImage','','images/img_cont/confirm_upper.gif',1)"><img src="images/img_cont/confirm_normal.gif" name="confirmImage" width="80" height="24" border="0" id="confirmImage" /></a>
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
		 window.resizeTo(500,pageheight);
	  }
	}

window.onload = function(){
	  init();
}
</script>    
</body>
</html>      
