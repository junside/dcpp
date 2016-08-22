<%@ page contentType="text/html; charset=euc-kr" %>

<html>
<head>
<title>°Ô½ÃÆÇ</title>
<link href="./css/common.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>

<% 
  int num=0,ref=1,re_step=0,re_level=0;
  try{  
    if(request.getParameter("num")!=null){
	num=Integer.parseInt(request.getParameter("num"));
	ref=Integer.parseInt(request.getParameter("ref"));
	re_step=Integer.parseInt(request.getParameter("re_step"));
	re_level=Integer.parseInt(request.getParameter("re_level"));
	}
%>
   
<body>  
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
<form method="post" name="writeform" action="ntb_writeForm_check.jsp" onsubmit="return writeSave()">
<input type="hidden" name="num" value="<%=num%>">
<input type="hidden" name="ref" value="<%=ref%>">
<input type="hidden" name="re_step" value="<%=re_step%>">
<input type="hidden" name="re_level" value="<%=re_level%>">
<p class="level2">Write the Content</p>
<table class="tab2">
	<col width="20%">
	<col width="80%">
	<tr>
		<th class="thc">Writer</th>
		<td class="tdl"><input type="text" size="10" maxlength="10" name="writer"></td>
	</tr>
	<tr>
		<th class="thc">Subject</th>
		<td class="tdl">
			<%if(request.getParameter("num")==null){%>
	       <input type="text" size="40" maxlength="50" name="subject"></td>
			<%}else{%>
		   <input type="text" size="40" maxlength="50" name="subject" value="[Reply]"></td>
			<%}%>
		</td>
	</tr>
	<tr>
		<th class="thc">Email</th>
		<td class="tdl"><input type="text" size="40" maxlength="30" name="email" ></td>
	</tr>
	<tr>
		<th class="thc">Content</th>
		<td class="tdl"><textarea name="content" rows="13" cols="40"></textarea></td>
	</tr>
	<tr>
		<th class="thc">Password</th>
		<td class="tdl"><input type="password" size="8" maxlength="12" name="passwd"></td>
	</tr>
	<tr>	
		<td class="tdc" colspan="2">
		  <input type="submit" value="Write" >  
		  <input type="reset" value="Rewrite">
		  <input type="button" value="go to list" OnClick="window.location='ntb_list.jsp'">
		</td>
	</tr>
<tr>      
</table>    
 <%
  }catch(Exception e){}
%>     
</form>      
</body>
</html>      
