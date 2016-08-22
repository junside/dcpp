<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="nfri.dcpp.properties.db.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.com.util.ComUtil"%>
<jsp:useBean id="part_ctr" class="nfri.dcpp.properties.business.Ctr_Part_Info_Process" scope="page"/>
<%

	Vector pt_info = new Vector();// = part_ctr.selectPartInfo();
	String first = ComUtil.isNullToDashString(request.getParameter("first"));
	String part = ComUtil.isNullToEmptyString(request.getParameter("part"));	
		
	//System.out.println("first : " + first);
	//System.out.println("part : " + part);
	//if(first.equalsIgnoreCase("1")){	
	pt_info = part_ctr.selectPartInfo(request);		
		//first = "";
	//}else{
	//	pt_info = part_ctr.selectPartInfo();
	///	first = "";
	//}
	int pt_count = pt_info.size();
	
	String id = request.getParameter("id"); //textfield ID값
	String value = request.getParameter("value"); //원소가 표시되는 형태의 value 값
	
	String _oCol = request.getParameter("_oCol");
	String _oDir = ComUtil.isNullToAscString(request.getParameter("_oDir"));
	
	//System.out.println("count : " + pt_count);
	//System.out.println("id : " + id);
	//System.out.println("value : " + value);
	//System.out.println("repart : " + re_part);
	//Null 체크
	//if(ComUtil.isNull(id_num)){
	//	id_num = request.getParameter("id_num");		
	//}
	
		//페이지 설정 관련 변수 선언
	int selp_totalRecord = 0; //전체 레코드 수
	int selp_numPerPage = 10; //페이지당 레코드 수
	int selp_pagePerBlock = 10; //블록당 페이지 수
	int selp_totalPage = 0; //전체 페이지 수
	int selp_totalBlock = 0; //전체 블록 수
	int selp_nowPage = 0; //현재 페이지
	int selp_nowBlock = 0; //현재 블록
	int selp_beginPerPage = 0; //페이지의 시작번호
	
	//현재 페이지 설정
	if(request.getParameter("selp_nowPage") != null)
	{
		selp_nowPage = Integer.parseInt(request.getParameter("selp_nowPage"));
	}
	//현재 블록 설정
	if(request.getParameter("selp_nowBlock") != null)
	{
		selp_nowBlock = Integer.parseInt(request.getParameter("selp_nowBlock"));
	}
	//전체 개수 저장
	selp_totalRecord = pt_info.size();
	//페이지별 게시물의 시작번호 계산
	selp_beginPerPage = selp_nowPage * selp_numPerPage;
	//전체 페이지 수 계산
	selp_totalPage =(int)Math.ceil((double)selp_totalRecord/selp_numPerPage);
	//전체 블록 수 계산
	selp_totalBlock =(int)Math.ceil((double)selp_totalPage/selp_pagePerBlock);
	//	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>입자 정보 선택</title>
</head>
<body> 
<script language = javascript>
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

function selectBeforeBlock(selp_nowBlock, selp_nowPage)//이전 목록 가기
{

    var form2 = document.form1;
    form2.selp_nowBlock.value = selp_nowBlock;
    form2.selp_nowPage.value = selp_nowPage;
    form2.target = "_self";
    form2.action = "pr_select_part_info.jsp";
	form2.submit();	
}

function selectBlock(selp_nowBlock, selp_nowPage)//목록 가기
{

    var form2 = document.form1;
    form2.selp_nowBlock.value = selp_nowBlock;
    form2.selp_nowPage.value = selp_nowPage;
    form2.target = "_self";
    form2.action = "pr_select_part_info.jsp";
	form2.submit();	
}

function selectAfterBlock(selp_nowBlock, selp_nowPage)//다음 목록 가기
{

    var form2 = document.form1;
    form2.selp_nowBlock.value = selp_nowBlock;
    form2.selp_nowPage.value = selp_nowPage;
    form2.target = "_self";
    form2.action = "pr_select_part_info.jsp";
	form2.submit();	
}

function hitEnterKey(e){
	  if(e.keyCode == 13){
		searchPart();
	  }else{
	   e.keyCode == 0;
	  return;
	  }
	 } 

function newPropertyInsert(){
	window.open("pr_insert_part_info.jsp","part_insert","width=100%,height=100%,toolbar=no,location=no,status=no");
}

function insertExpress(){
	var part = "part";//id
	window.open("pr_insert_expression.jsp?id="+part,"insert_expression","width=100%,height=100%,toolbar=no,location=no,status=no");
}

function selectPartInfo(no, value){
	///var obj = eval("opener.form1.part_no[<%=id%>]");
	//obj.value = value;

	//var id = form1.id_num.value;
	//var value = form1.id_value.value;
	opener.form1.<%=id%>.value=no;
	opener.form1.<%=value%>.value=value;
	opener.addExpression();
	self.close()
}

function searchPart()
{
	var form2 = document.form1;
	form2.first.value = "1";
	//var part = form2.part.value;
	//alert("part : " + form2.part.value); 
    form2.target = "_self";
    form2.action = "pr_select_part_info.jsp";
	form2.submit();	
}

function setOrderDirection(column){	
	//var dir = "asc";
	//var form2 = document.form1;
	//var direction = form2._oDir.value;
	//alert("direction="+direction+"/column="+column);
	//if(direction != null ){
	//	if(direction !='' && direction == 'asc'){
	//		direction = 'desc';
	//	}else if(direction == null){
	//		direction = 'desc'
	//	}else{direction = 'asc'}		
	//	dir = direction;
	//}
	//form2._oDir.value = dir;
	//form2._oCol.value = column;
	//form2.action = 'pr_common_list.jsp';
	//form2.submit();
}

</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST">
<div id="content">
<input type="hidden" name="selp_nowPage" value="<%=selp_nowPage%>"/>
<input type="hidden" name="selp_nowBlock" value="<%=selp_nowBlock%>"/>
<input type="hidden" name="id" value="<%=id%>"/>
<input type="hidden" name="value" value="<%=value%>"/>
<input type="hidden" name="first" value="<%=first%>"/>

<img src="images/main_cont/pr_select_part_main_t_01.gif" width="686" height="47"><br>
<span class="help">
		검색버튼을 클릭하지 않고 "Enter" 키로 검색가능합니다. 
</span><br>
<table>
<col width="80">
<col width="300">
<col width="100">
<col width="170">
<tr>
	<td>
		<span class="help">
			입자 : 
			</span>
		</td >
	<td class="tdc"><input type="text" name="part" size="40" value="<%=part%>" onKeypress="hitEnterKey(event)">
	</td> 
	<td class="tdc">
		<a href="javascript:searchPart()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('searchImage1','','images/img_cont/search_s_upper.gif',1)"><img src="images/img_cont/search_s_normal.gif" name="searchImage1" width="60" height="20" border="0" id="searchImage1" />
		</a>
	</td>
	<td class="tdc">
		<a href="javascript:newPropertyInsert()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('insertImage1','','images/img_cont/new_part_upper.gif',1)"><img src="images/img_cont/new_part_normal.gif" name="insertImage1" width="170" height="20" border="0" id="insertImage1" />
		</a>
	</td >
</tr>
</table>
<span class="help">
		검색 결과 : <%=selp_totalRecord%>건 (<font color=red> <%=selp_totalRecord==0?"0":selp_nowPage + 1%>/<%=selp_totalPage %> Pages</font>)
</span>
<table class="tab2">
	<col width="50">
	<col width="100">
	<col width="100">
	<col width="100">
	<col width="100">	
	  <tr>
	    <th class="thc">
	     	 No
	    </th >
	    <th class="thc">
	      <A HREF="javascript:setOrderDirection('part_num')">입자기호</A>
	    </th>
	    <th class="thc">
	      <A HREF="javascript:setOrderDirection('part_name')">입자이름</A>
	    </th>
	    <th class="thc">
	    	원자/전자
	    </th>
	    <th class="thc">
	      	선택
	    </th>
	  </tr>
	  <%
	  	for (int i = selp_beginPerPage ; i < (selp_beginPerPage + selp_numPerPage); i++) {
	  		try{
	  	
			if(i == selp_totalRecord || selp_totalRecord == 0)
			{
				break;
			}
		  //for (int i = 0; i < pt_count; i++) {
			Basic_Part_Info part_info = (Basic_Part_Info) pt_info.elementAt(i);
			String part_no = part_info.getPL_CPBI_ELE_NUM();
			String part_id = part_info.getPL_CPBI_ELE_SYMBOL();
			String part_name = part_info.getPL_CPBI_ELE_NAME();
			String part_type = part_info.getPL_CPBI_ELE_TYPE();
			//System.out.println("i : " + i);	
			//System.out.println("part_no : " + part_no);
	  		%>
	  		<tr>
			    <td class="tdc">
			     	<%=i+1%>
			    </td >
			    <td class="tdc">
			     	 <%=part_id%>
			    </td >
			    <td class="tdc">
			     	<%=part_name%>
			    </td >
			    <td class="tdc">
			     	 <%=part_type%>
			    </td >
			    <td class="tdc">
			     	<a href="javascript:void(0);"  onClick="selectPartInfo('<%=part_no%>','<%=part_id%>'); return false;"> 선택 </a>
			    </td >
		  	</tr>
	  		<%
		  }catch(ArrayIndexOutOfBoundsException e)
		  {
			  	System.out.println("========================Exception=====================");
			    System.out.println("selp_totalRecord : " + selp_totalRecord);		
				System.out.println("selp_beginPerPage : " + selp_beginPerPage);
				System.out.println("selp_numPerPage : " + selp_numPerPage);	
				System.out.println("selp_nowPage : " + selp_nowPage);	
				System.out.println("selp_nowBlock : " + selp_nowBlock);	
				System.out.println("selp_beginPerPage + selp_numPerPage : " + (selp_beginPerPage+selp_totalRecord));
				System.out.println("i : " + i);
				System.out.println("========================         =====================");
				e.printStackTrace();
		  }
	  	}
	  %>
</table>
<div class="bbtn_c">
<%
	if(selp_totalRecord !=0)
	{
		if(selp_nowBlock > 0){
			%>
			<A HREF="javascript:selectBeforeBlock('<%=selp_nowBlock-1%>','<%=(selp_nowBlock-1)*selp_pagePerBlock %>')">이전 <%=selp_pagePerBlock%>개</A>
			<%
		}
		for(int i = 0; i<selp_pagePerBlock; i++){
			%>
			<A HREF="javascript:selectBlock('<%=selp_nowBlock%>','<%=(selp_nowBlock*selp_pagePerBlock) + i%>')">[<%=(selp_nowBlock*selp_pagePerBlock)+i+1%>]</A>
			<%
			if((selp_nowBlock*selp_pagePerBlock) + i + 1 == selp_totalPage){
				break;
			}
		}
		if(selp_totalBlock > selp_nowBlock + 1){
		%>
			<A HREF="javascript:selectAfterBlock('<%=selp_nowBlock+1%>','<%=(selp_nowBlock+1)*selp_pagePerBlock %>')">다음 <%=selp_pagePerBlock%>개</A>
		<%
		}
	}
%>
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
		 window.resizeTo(700,pageheight);
	  }

	  document.form1.part.focus();
}

window.onload = function(){
	  init();
}
</script>
</body>
</html>