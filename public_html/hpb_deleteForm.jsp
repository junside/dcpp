<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("euc-kr"); %>
<%@ page import = "nfri.dcpp.board.model.Board_Info" %>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>  
<jsp:useBean id="board_process" class="nfri.dcpp.board.business.Ctr_Board_Process" scope="page"/>

<html>
<head>
<title>글삭제</title>
<link href="./css/common.css" rel="stylesheet" type="text/css">
</head>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
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
%>
<script language="JavaScript"> 
  function deleteSave(){	
	if(document.delForm.passwd.value==''){
	alert("Insert the Password!");
	document.delForm.passwd.focus();
	return false;
 }
}         
</script>
<form method="POST" name="delForm"  action="hpb_deleteForm_check.jsp" onsubmit="return deleteSave()"> 
<input type="hidden" name="nowPage" value="<%=nowPage%>"/>
<input type="hidden" name="nowBlock" value="<%=nowBlock%>"/>
<input type="hidden" name="num" value="<%=num%>">
<p class="level2">Delete Content</p>
<table class="tab2">
<col width="20%">
<col width="80%">
   <tr>
     <td class="tdc" colspan="2">
       <b>Insert the Password!</b></td>
  </tr>
  <tr>
    <th class="thc">Password : </th>
    <td class="tdl">
    	<input type="password" name="passwd" size="8" maxlength="12">
    </td>
  </tr>
  <tr>  
  	<td class="tdc" colspan="2">
     <input type="submit" value="Delete" >  
     <input type="button" value="go to list" onclick="document.location.href='hpb_list.jsp'">
   	</td>    
  </tr>
  </table>
</form>
</body>
</html> 
