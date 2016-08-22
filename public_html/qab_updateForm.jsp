<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("euc-kr"); %>
<%@ page import = "nfri.dcpp.board.model.Board_Info" %>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>  
<jsp:useBean id="board_process" class="nfri.dcpp.board.business.Ctr_Board_Process" scope="page"/>

<html>
<head>
<title>글수정</title>
<link href="./css/common.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
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
	
		Board_Info article = board_process.select_QA_Article_Content(num);
	   
	int ref=article.getRef();
	int re_step=article.getRe_step();
	int re_level=article.getRe_level();
	int number = article.getNum();
	
	String writer = article.getWriter();
	String subject = article.getSubject();
	String email = article.getEmail();
	String content = article.getContent();
	String passwd = article.getPasswd();
%>
<script language = javascript>
function writeSave(){
	
	if(document.writeform.writer.value==""){
	  alert("Insert the Writer!");
	  document.writeform.writer.focus();
	  return false;
	}
	if(document.writeform.subject.value==""){
	  alert("Insert the Subject!");
	  document.writeform.subject.focus();
	  return false;
	}
	
	if(document.writeform.content.value==""){
	  alert("Insert the Content!");
	  document.writeform.content.focus();
	  return false;
	}
        
	if(document.writeform.passwd.value==""){
	  alert("Insert the Password!");
	  document.writeform.passwd.focus();
	  return false;
	}
 }	
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form method="post" name="writeform" action="qab_updateForm_check.jsp" onsubmit="return writeSave()">
<input type="hidden" name="nowPage" value="<%=nowPage%>"/>
<input type="hidden" name="nowBlock" value="<%=nowBlock%>"/>
<input type="hidden" name="num" value="<%=num%>">
<p class="level2">Modify Content</p>
<table class="tab2">
<col width="20%">
<col width="80%">
  <tr>
    <th class="thc">Writer</th>
    <td class="tdl">
    	<input type="text" size="10" maxlength="10" name="writer" value="<%=writer%>">
    </td>
  </tr>
  <tr>
    <th class="thc">Subject</th>
    <td class="tdl">
    	<input type="text" size="40" maxlength="50" name="subject" value="<%=subject%>">
    </td>
  </tr>
  <tr>
    <th class="thc">Email</th>
    <td class="tdl">
    	<input type="text" size="40" maxlength="30" name="email" value="<%=email%>">
    </td>
  </tr>
  <tr>
    <th class="thc">Content</th>
    <td class="tdl">
    	<textarea name="content" rows="13" cols="40"><%=content%></textarea>
    </td>
  </tr>
  <tr>
    <th class="thc">Password</th>
	<td class="tdl"><input type="password" size="8" maxlength="12" name="passwd"></td>
  </tr>
  <tr>  
  	<td class="tdc" colspan="2">
     <input type="submit" value="Modify" >  
     <input type="reset" value="Rewrite">
     <input type="button" value="go to list" onclick="document.location.href='qab_list.jsp'">
   	</td>    
  </tr>
</table>    
</form>      
</body>
</html>   
