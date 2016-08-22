<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("euc-kr"); %>
<%@ page import = "nfri.dcpp.board.model.Board_Info" %>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>  
<jsp:useBean id="board_process" class="nfri.dcpp.board.business.Ctr_Board_Process" scope="page"/>

<html>
<head>
<title>게시판</title>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="./css/common.css" rel="stylesheet" type="text/css" />
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
	
  	Board_Info article = board_process.select_NT_Article_Content(num);
       
	int ref=article.getRef();
	int re_step=article.getRe_step();
	int re_level=article.getRe_level();
	int number = article.getNum();
%>

<form>
<input type="hidden" name="nowPage" value="<%=nowPage%>"/>
<input type="hidden" name="nowBlock" value="<%=nowBlock%>"/>
<p class="level2">View Content</p>
<table class="tab2">
<col width="125">
<col width="200">
<col width="125">
<col width="200">
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
  <tr height="30">  
  	<td class="tdc" colspan="4">
    	<a href="ntb_updateForm.jsp?num=<%=num%>">Modify</a>
    	<a href="ntb_deleteForm.jsp?num=<%=num%>">Delete</a>
    	<a href="ntb_writeForm.jsp?num=<%=num%>&ref=<%=ref%>&re_step=<%=re_step%>&re_level=<%=re_level%>">Reply</a>
    	<a href="ntb_list.jsp">List</a>
   	</td>    
  </tr>
</table>    
</form>      
</body>
</html>      
