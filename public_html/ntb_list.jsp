<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("euc-kr"); %>
<%@ page import = "nfri.dcpp.board.model.Board_Info" %>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>  
<jsp:useBean id="board_process" class="nfri.dcpp.board.business.Ctr_Board_Process" scope="page"/>

  	
<%
Vector bd_info = board_process.select_NT_Article();
int bd_count = bd_info.size();

//������ ���� ���� ���� ����
int totalRecord = 0; //��ü ���ڵ� ��
int numPerPage = 10; //�������� ���ڵ� ��
int pagePerBlock = 10; //��ϴ� ������ ��
int totalPage = 0; //��ü ������ ��
int totalBlock = 0; //��ü ��� ��
int nowPage = 0; //���� ������
int nowBlock = 0; //���� ���
int beginPerPage = 0; //�������� ���۹�ȣ

//���� ������ ����
if(request.getParameter("nowPage") != null)
{
	nowPage = Integer.parseInt(request.getParameter("nowPage"));
}
//���� ��� ����
if(request.getParameter("nowBlock") != null)
{
	nowBlock = Integer.parseInt(request.getParameter("nowBlock"));
}
//��ü ���� ����
totalRecord = bd_info.size();
//�������� �Խù��� ���۹�ȣ ���
beginPerPage = nowPage * numPerPage;
//��ü ������ �� ���
totalPage =(int)Math.ceil((double)totalRecord/numPerPage);
//��ü ��� �� ���
totalBlock =(int)Math.ceil((double)totalPage/pagePerBlock);
%>
 	
<html>
<head>
<title>�Խ���</title>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="./css/common.css" rel="stylesheet" type="text/css" />
</head>
<body>

<script language = javascript>
function selectBeforeBlock(nowBlock, nowPage)//���� ��� ����
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "ntb_list.jsp";
	form2.submit();	
}

function selectBlock(nowBlock, nowPage)//��� ����
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "ntb_list.jsp";
	form2.submit();	
}

function selectAfterBlock(nowBlock, nowPage)//���� ��� ����
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "ntb_list.jsp";
	form2.submit();	
}

function viewContent(num){ //�Խù�  ����
	var form2 = document.form1;
	form2.action = "ntb_content.jsp?num="+num;
	form2.submit();
}
</script>
<script>
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<input type="hidden" name="nowPage" value="<%=nowPage%>"/>
<input type="hidden" name="nowBlock" value="<%=nowBlock%>"/>
<p class="level2">Notice Board</p>
 <span class="help">
	Total count : <%=totalRecord%> (<font color=red> <%=totalRecord==0?"0":nowPage + 1%>/<%=totalPage %> Pages</font>)
	</span>
<table class="boardtab">
  <tr>
   <td class="tdr">
    <a href="ntb_writeForm.jsp">WRITE</a>
   </td>
  </tr>
</table>
  
  <%
      if (bd_count == 0) {
  %>
<table class="boardtab">
  <tr>
      <td class="tdc">
      This board has not content.
      </td>
  </table>
  
  <%  } else {    %>
  <table class="tab2"> 
	<col width="50">
	<col width="300">
	<col width="100">
	<col width="150">
	<col width="50"> 
	<tr>
	    <th class="thc">
	     	 No
	    </th >
	    <th class="thc">
	     	 Subject
	    </th>
	    <th class="thc">
	      	Writer
	    </th>
	    <th class="thc">
	      	Reg_Date
	    </th>
	    <th class="thc">
	      	Read
	    </th>
	  </tr> 
  <%  
  	for (int i = beginPerPage ; i < (beginPerPage+numPerPage); i++) {
	  	if(i == totalRecord || totalRecord == 0)
	  	{
	  		break;
	  	}
	  	Board_Info bd_data = (Board_Info) bd_info.elementAt(i);
	  	
	  	int sNUM = bd_data.getNum();
	  	String sWRITER     = bd_data.getWriter();   
	  	String sEMAIL	   = bd_data.getEmail();    
	  	String sSUBJECT	   = bd_data.getSubject();  
	  	String sPASSWD	   = bd_data.getPasswd();   
	  	String sREG_DATE   = bd_data.getReg_date(); 
	  	int sREADCOUNT  = bd_data.getReadcount();
	  	int sREF	   = bd_data.getRef();      
	  	int sRE_STEP	   = bd_data.getRe_step();  
	  	int sRE_LEVEL   = bd_data.getRe_level(); 
	  	String sCONTENT	   = bd_data.getContent();  
	  	//String sIP	   = bd_data.getIp();  
	  	
	  	//System.out.println("sNUM : " + sNUM);
	  	//System.out.println("sWRITER : " + sWRITER);
	  	//System.out.println("sEMAIL : " + sEMAIL);
	  	//System.out.println("sSUBJECT : " + sSUBJECT);
	  	//System.out.println("sPASSWD : " + sPASSWD);
	  	//System.out.println("sREG_DATE : " + sREG_DATE);
	  	//System.out.println("sREADCOUNT : " + sREADCOUNT);
	  	//System.out.println("sREF : " + sREF);
	  	//System.out.println("sRE_STEP : " + sRE_STEP);
	  	//System.out.println("sRE_LEVEL : " + sRE_LEVEL);
	  	//System.out.println("sCONTENT : " + sCONTENT);
	  	//System.out.println("sIP : " + sIP);
	  	
	  	
  %>
  		<tr>
		    <td class="tdc">
		     	<%=sNUM%>
		    </td >
		    <td class="tdl" onmouseover="this.bgColor='#FFCCAA';this.style.cursor='hand'" onmouseout="this.bgColor='#FFFFFF'">
			     <%
			  	      int wid=0; 
			  	      if(sRE_LEVEL >0){
			  	        wid=5*sRE_LEVEL;
			  	%>
			  	  <img src="images/icon_cont/level.gif" width="<%=wid%>" height="16">
			  	  <img src="images/icon_cont/re.gif">
			  	<%}else{%>
			  	  <img src="images/icon_cont/level.gif" width="<%=wid%>" height="16">
			  	<%}%>
			     <a href="javascript:void(0);"  onClick="viewContent('<%=sNUM%>'); return false;"> <%=sSUBJECT%></a>
			     <% if(sREADCOUNT>=20){%>
			           <img src="images/icon_cont/hot.gif" border="0"  height="16">
			     <%}%>	
		    </td >
			<td class="tdc">
		     	<a href="mailto:<%=sEMAIL%>"><%=sWRITER%></a>
		    </td >
			<td class="tdc">
		     	<%=sREG_DATE%>
		    </td >
      		<td class="tdc">
		     	<%=sREADCOUNT%>
		    </td >			
    </tr>
       <%}%>
  </table>
  <%}%>
  
<div class="bbtn_c">
<%
	if(totalRecord !=0)
	{
		if(nowBlock > 0){
			%>
			<A HREF="javascript:selectBeforeBlock('<%=nowBlock-1%>','<%=(nowBlock-1)*pagePerBlock %>')">���� <%=pagePerBlock%>��</A>
			<%
		}
		for(int i = 0; i<pagePerBlock; i++){
			%>
			<A HREF="javascript:selectBlock('<%=nowBlock%>','<%=(nowBlock*pagePerBlock) + i%>')">[<%=(nowBlock*pagePerBlock)+i+1%>]</A>
			<%
			if((nowBlock*pagePerBlock) + i + 1 == totalPage){
				break;
			}
		}
		if(totalBlock > nowBlock + 1){
		%>
			<A HREF="javascript:selectAfterBlock('<%=nowBlock+1%>','<%=(nowBlock+1)*pagePerBlock %>')">���� <%=pagePerBlock%>��</A>
		<%
		}
	}
%>
</div>  
</form>
</body>
</html>
