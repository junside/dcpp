<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="nfri.dcpp.com.util.*"%>
<jsp:useBean id="ctr_data" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
<jsp:useBean id="property_data" class="nfri.dcpp.properties.business.Ctr_Property_Search_Process" scope="page"/>

<%
	/**
	* �׷��� ������ ������ ����ϴ� JSP ������(view)
	* by Junside(J.H Park)
	*/

	String pr_no = "";//"B200900039";//request.getParameter("pr_no");
	String d_index = "1";//request.getParameter("d_index");
	//String xax_cal = "1E0";//request.getParameter("xax_cal");
	//String xay_cal = "1E0";//request.getParameter("xay_cal");
	String param_TB = request.getParameter("tb_option"); //��з�
	String param_TB_exp = ctr_data.getExpName(param_TB); //��з�
	String param_DB = request.getParameter("db_option"); //�����ͺз�
	String param_DB_exp = ctr_data.getExpName(param_DB); //�����ͺз�
	String param_part1 = request.getParameter("part_1_id"); //ǥ������
	String param_part1_sym = request.getParameter("part_1_sym");//ǥ������ ǥ����
	String param_part2 = ComUtil.isNullToDashString(request.getParameter("part_2_id")); //�Ի�����
	String param_MP = request.getParameter("mp_option"); //�����μ���
	String param_MP_exp = ctr_data.getExpName(param_MP); //�����μ���
	String param_srd = request.getParameter("srd_value"); //����ǥ����������	
	String gotoindex = request.getParameter("gotoindex"); //���õǾ��� �׸�
	
	//String param_IC = request.getParameter("ic_option"); //�浹���
	
	
	//String checkDraw = ComUtil.isNullToDashString(request.getParameter("checkDraw")); //���õǾ��� �׸��� �׸��� �÷��� ����
	//üũ�� ����Ʈ �����ϴ� ����
	String checkList = ComUtil.isNullToEmptyString(request.getParameter("checkList"));
	//String before_checkList = checkList;
	//String before_checkList = ComUtil.isNullToEmptyString(request.getParameter("before_checkList"));
	//Where ������
	String whereCondtion = property_data.makeSearchQueryOption(request);
	String str_where_option_order_by = " ORDER BY BASIC.PL_BI_DATA_NUM, BASIC.PL_BI_IMP_CLASS ASC ";
	String str_where_option_data_and = " AND BASIC.PL_BI_DATA_NUM = GRAPH.PL_BI_DATA_NUM ";	
	//����Ʈ�� ������ ������������ ������
	String str_option = whereCondtion + str_where_option_order_by;
	Vector pd_info = property_data.searchPropertyListInfo(str_option); // OK
	
	whereCondtion = whereCondtion + str_where_option_data_and;
	//Y�� ���� ǥ��
	String Y_Title = ctr_data.getExpName(param_DB);
	
	//String whereCondtion = property_data.getConditionInfo(request);
	//System.out.println("whereCondtion : " + whereCondtion);
	
	//����Ʈ�� ���Ե� ���������� ���� �׷��� �����͸� ������	
	//Vector gd_info = graph_data.selectGraphBasicData(pr_no);
	int dt_count = 0;//dt_info.size();
	
	//������ ���� ���� ���� ����
	int totalRecord = 0; //��ü ���ڵ� ��
	int numPerPage = 10; //�������� ���ڵ� ��
	int pagePerBlock = 10; //��ϴ� ������ ��
	int totalPage = 0; //��ü ������ ��
	int totalBlock = 0; //��ü ��� ��
	int nowPage = 0; //���� ������
	int nowBlock = 0; //���� ���
	int beginPerPage = 0; //�������� ���۹�ȣ
	//����Ʈ ���ý� üũ�� ���� Where �������� IN �׸����� �ֱ� ���� �����ϴ� ������.
	String before_page = ComUtil.isNullToDashString(request.getParameter("before_page"));
	String before_block = ComUtil.isNullToDashString(request.getParameter("before_block"));
	if(before_page.equalsIgnoreCase("-")){
		before_page = Integer.toString(nowPage);
	}
	if(before_block.equalsIgnoreCase("-")){
		before_block = Integer.toString(nowBlock);
	}
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
	totalRecord = pd_info.size();
	//�������� �Խù��� ���۹�ȣ ���
	beginPerPage = nowPage * numPerPage;
	//��ü ������ �� ���
	totalPage =(int)Math.ceil((double)totalRecord/numPerPage);
	//��ü ��� �� ���
	totalBlock =(int)Math.ceil((double)totalPage/pagePerBlock);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
<OBJECT width = "0" height = "0"
ID="ZTransferX" CLASSID="CLSID:C7C7225A-9476-47AC-B0B0-FF3B79D55E67" 
codebase="http://dcpp.nfri.re.kr/oz/viewer/ZTransferX.cab#version=2,2,0,8">            <!-- �� ��ġ�ϴ� ZTransferX��ġ,���� ���� -->
<PARAM NAME="download.Server" VALUE="http://dcpp.nfri.re.kr/oz/viewer/">               <!-- ��� ��ġ ���� (��Ʈ����) -->
<PARAM NAME="download.Port" VALUE="80">                                         <!-- ��� ��ġ�� ��Ʈ ���� -->
<PARAM NAME="download.Instruction" VALUE="ozviewer.idf">                       <!-- ��� ���� ���� ���� -->
<PARAM NAME="install.Base" VALUE="<PROGRAMS>/Forcs">
<PARAM NAME="install.Namespace" VALUE="plasma">                          <!-- ���ÿ� ��ġ�� ����̸� -->
</OBJECT>
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

function selectBeforeBlock(nowBlock, nowPage)//���� ��� ����
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "pr_search_property_result.jsp";
    ///form2.encoding = "application/x-www-form-urlencoded";    
	form2.submit();	
}

function selectBlock(nowBlock, nowPage)//��� ����
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "pr_search_property_result.jsp";
    ///form2.encoding = "application/x-www-form-urlencoded";    
	form2.submit();	
}

function selectAfterBlock(nowBlock, nowPage)//���� ��� ����
{
    var form2 = document.form1;
    form2.nowBlock.value = nowBlock;
    form2.nowPage.value = nowPage;
    form2.target = "_self";
    form2.action = "pr_search_property_result.jsp";
    ///form2.encoding = "application/x-www-form-urlencoded";    
	form2.submit();	
}

function viewGraphInfo(pr_no){ //�׷��� ����
	if(saveMsg('0') == true){
		window.open("e_pr_view_graph.jsp?pr_no="+pr_no,"graph_info","width=100%,height=100%,toolbar=no,location=no,status=no");
		return;
	}
}

//function viewArticleInfo(artcl_no){ //������ ����
//	if(saveMsg('1') == true){
//		window.open("pr_view_article_info.jsp?artcl_no="+artcl_no,"article_info","width=100%,height=100%,toolbar=no,location=no,status=no");
//		return;
//	}
//}

//function viewDataInfo(pr_no){ //��ġ���� ����
//	if(saveMsg('2') == true){
//		window.open("pr_view_graph_data.jsp?pr_no="+pr_no,"property_info","width=100%,height=100%,toolbar=no,location=no,status=no");
//		return;
//	}
//}

function viewBasicInfo(pr_no, artcl_no){ //�⺻���� ����
	if(saveMsg('3') == true){
		window.open("e_pr_view_property_info.jsp?pr_no="+pr_no+"&artcl_no="+artcl_no,"property_info","width=100%,height=100%,toolbar=no,location=no,status=no");
		return;
	}
}

function saveMsg(number){
	var num = number;
	switch(num) {
		case '0': //�׷������� ����
			var result = confirm("Would you like to see the information of a graph?");
			return result;
			break;
		case '1': //������ ����
			var result = confirm("Would you like to see the information of a reference?");
			return result;
			break;
		case '2': //��ġ���� ����
			var result = confirm("Would you like to see the information of a numeric data?");
			return result;
			break;
		case '3': //�⺻���� ����
			var result = confirm("Would you like to see the information of a properties?");
			return result;
			break;
		case '4': //Text ���� ����
			var result = confirm("Would you like to see the text type of properties information?");
			return result;
			break;
		default :		
			break;
	}		
}

function addCheckList(){
	var form2 = document.form1;
	var check = form2.check_pr_no;
	var check_string = form2.checkList.value; //����Ʈ�� �����ϴ� �Ķ���� ����
	//var check_before = form2.before_checkList.value;//������ üũ�� �׸���� ��� �ִ� ����
	//alert("������ üũ�� �׸���� ��� �ִ� ���� : " + check_before);
		
	//var st_before_page = form2.before_page.value;
	//var st_before_block = form2.before_block.value;
	//var st_now_page = form2.nowPage.value;
	//var st_now_block = form2.nowBlock.value;
	//alert("st_before_page : "+st_before_page + ", st_now_page : " + st_now_page);
	//alert("st_before_block : "+st_before_block + ", st_now_block : " + st_now_block);
	
	//���� üũ�� �׸���� ��� ����
	var check_now = "";
	var j = 0; //üũ ���� Ȯ�� ����
	for(var i = 0; i < check.length; i++){		
		if(check[i].checked){
			j++;
			if(j==1){
				check_now = check_now + check[i].value;
			}else{
				check_now = check_now + "," + check[i].value;
			}		 
		}
	}
	form2.checkList.value = check_now;
	//if(st_before_page != st_now_page || st_before_block != st_now_block)
	//{
	//	alert("j : " + j);
	//	if(j > 0){ //�Ѱ��� üũ �Ǿ� ������
	//		check_string = check_before + "," + check_now;//check_string + "," + check_now;
	//		form2.checkList.value = check_string;
	//	}else{//�Ѱ��� �ȵǾ� ������ ���� �״��
	//		form2.checkList.value = check_before;
	//	}		
	//}else{
	//	form2.checkList.value = check_now;		
	//}
	//alert("���� ���õ� ����Ʈ �Ķ���� : " + form2.checkList.value);
}

function toggleCheckBox(obj, target){
    if ( !obj ) return false;
    if ( !target ) return false;
    
    if ( target.length ) {
        for ( var i = 0 ; i < target.length ; i++ ){
            //target[i].setAttribute("checked", obj.getAttribute("checked"));
            target[i].checked = obj.checked? true:false
        }
    }
    else{
        target.setAttribute("checked", obj.getAttribute("checked"));
    }
}

function refreshInfo(){
	var form2 = document.form1;
	var check = form2.check_pr_no;
	//form2.checkDraw.value="D";
	var count = 0;
	form2.target = "_self";
    form2.action = "e_pr_search_property_result.jsp";	    
	form2.submit();
	//for(var i = 0; i < check.length; i++){
	//	if(check[i].checked){
			//alert(check[i].value);
	//		count ++;
	//	}
	//}
	//if(count == 0){		
	//	alert("üũ�ڽ��� �����ϼ���.");
	//	return;
	//}else{
	    
	//}
}

function getPropertyInfo(){
	var form2 = document.form1;
	var check = form2.check_pr_no;
	var count = 0;
	var check_value = "";
	for(var i = 0; i < check.length; i++){
		if(check[i].checked==true){
			//alert(check[i].value);			
			/* if(count > 0){
				if(count == check.length){
					check_value += check[i].value";
					//check_value += "&check=" +"'"+check[i].value+"'";
				}else{
					check_value += "&check="+check[i].value+",";
					//check_value += "&check=" +"'"+check[i].value+"',";	
				}
			}else{
				check_value += "'"+check[i].value+"'";
				//check_value += "'"+check[i].value+"'";	
			} */
			check_value += check[i].value+",";
			count++;
		}
	}
	if(count == 0){		
		alert("Please select the checkbox of properties number to see!");
		return;
	}else{
		if(check[0].checked==true && count==1){
			alert("Please select the checkbox of properties number to see!");
			return;
		}else{
			if(saveMsg('4') == true){
				//alert("üũ�ڽ� ���� :" + count);
				//alert(check_value);
				window.open("e_pr_view_text_property_info.jsp?check="+check_value,"property_info","width=700,height=600,scrollbars=yes");
				return;
			}
		}
		
		//window.open("pr_select_target_part.jsp?id="+id+"&sym="+sym+"&db="+db+"&mp="+mp,"select_Part","width=700,height=600,scrollbars=yes");
	}
	
	//var form2 = document.form1;
	//form2.target = "_self";
    //form2.action = "pr_search_property_result.jsp";	    
	//form2.submit();
}

</script>
<script>
//document.oncontextmenu     = new Function('return false');
//document.ondragstart       = new Function('return false');
//document.onselectstart     = new Function('return false');
</script>
<form name="form1" action="" method="POST" >
<input type="hidden" name="pr_no" value="<%=pr_no%>">
<input type="hidden" name="tb_option" value="<%=param_TB%>"/>
<input type="hidden" name="db_option" value="<%=param_DB%>"/>
<input type="hidden" name="part_1_id" value="<%=param_part1%>"/>
<input type="hidden" name="part_1_sym" value="<%=param_part1_sym%>"/>
<input type="hidden" name="part_2_id" value="<%=param_part2%>"/>
<input type="hidden" name="mp_option" value="<%=param_MP%>"/>
<input type="hidden" name="srd_value" value="<%=param_srd%>"/>
<input type="hidden" name="gotoindex" value="<%=gotoindex%>"/>
<input type="hidden" name="checkList" value="<%=checkList%>"/>
<p class="level2">Search Conditions</p>
<span class="help"><font color = red>Categorize : </font><%=param_TB_exp%></span><br/>
<span class="help"><font color = red>Collision data categorize : </font><%=param_DB_exp%></span><br/>
<span class="help"><font color = red>Collision process : </font><%=param_MP_exp%></span><br/>
<span class="help"><font color = red>Target particle expression : </font><%=param_part1_sym%></span><br/>
<p class="level2">Graph</p>
<table class="tab5">
<col width="110">
<col width="540">
  <tr>
    <th colspan="2" class="thc">Graph</th>
    </tr>
  <tr>
  	<td colspan="2" class="tdc">
  	<object id="ozviewer" width="645" height="500" CLASSID="CLSID:0DEF32F8-170F-46f8-B1FF-4BF7443F5F25">
            <param name="connection.servlet" value="http://dcpp.nfri.re.kr/oz/server">
            <param name="connection.reportname" value="/ozr_pr_search_property_result.ozr">
			<param name="viewer.usestatusbar" value="false">	<!-- �Ʒ��� ����ǥ������ ������� �ʽ��ϴ� -->
			<param name="viewer.viewmode" value="Fittocontents">  <!-- ��� ����� ����  �������� ����ϴ� -->
			<param name="viewer.bgcolor" value="ffffff">	<!-- ��� ��׶��� ������ �����մϴ� -->
			<param name="viewer.useoutborder" value="false">	<!-- �ܰ� �׵θ��� ������� �ʽ��ϴ� -->
			<param name="viewer.useinborder" value="false">	<!-- ���� �׵θ��� ������� �ʽ��ϴ� -->
			<param name="toolbar.all" value="true">	<!-- ���ٸ� ��� -->
			<param name="toolbar.about" value="true">
			<param name="toolbar.close" value="false">
			<param name="toolbar.file" value="true">
			<param name="toolbar.jpg" value="true">
			<param name="toolbar.pdf" value="true">
			<param name="toolbar.ppt" value="true">
			<param name="toolbar.tiff" value="true">
			<param name="toolbar.find" value="false">
			<param name="toolbar.help" value="false">
			<param name="toolbar.open" value="false">
			<param name="toolbar.page" value="false">
			<param name="toolbar.print" value="true">
			<param name="toolbar.save" value="false">
			<param name="toolbar.pagenavigator" value="false">
			<param name="toolbar.pageselection" value="false">
			<param name="toolbar.viewmode" value="true">
			<param name="toolbar.option" value="false">
            <param name="viewer.isFrame" value="false">
			<param name="viewer.Namespace" value="plasma\ozviewer">
			<param name="viewer.configmode" value="html">
			<param name="odi.odinames" value="odi_pr_search_property_result">
			<param name="odi.odi_pr_search_property_result.pcount" value="2">
			<param name="odi.odi_pr_search_property_result.args1" value="PM_Y_TITLE=<%=Y_Title%>">
			<param name="odi.odi_pr_search_property_result.args2" value="PM_WHERE_CONDITION=<%=whereCondtion%>">
        </object>
   	</td>
    </tr>
</table>
<div class="bbtn_r">
<a href="javascript:refreshInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('refreshImage','','images/img_cont/eng/refresh_upper.gif',1)"><img src="images/img_cont/eng/refresh_normal.gif" name="refreshImage" width="150" height="24" border="0" id="refreshImage" /></a>
</div>
<p class="level2">List of search result</p>
<span class="help">
If you want to see the numeric data, please click button at the graph field.
</span><br>
<span class="help">
If you want to see the reference data, please click properties number.
</span><br>
<span class="count">
Result count : <%=totalRecord%> 
</span>
<table class="tab5">
<col width="30">
<col width="60">
<col width="60">
<col width="160">
<col width="100">
<col width="100">
<col width="120">
<tr>
	<th class="thc">
	<input type="checkbox" id="" name="check_pr_no" value="" onclick="toggleCheckBox(this, document.getElementsByName('check_pr_no'));" />
   </th >
   <th class="thc">
    	 No
   </th >
   <th class="thc">
     	Graph
   </th>
   <th class="thc">
     	Expression
   </th><th class="thc">
   		Collision type
   </th><th class="thc">
   		Sub Process
   </th>
   <th class="thc">
     	Exp./Theory
   </th>
 </tr>
  <%
	  	for (int i = 0 ; i < pd_info.size(); i++) {
	  	//if(i == totalRecord || totalRecord == 0)
	  	//{
	  	//	break;
	  	//}
	  	Properties_Basic_Info prot = (Properties_Basic_Info) pd_info.elementAt(i);
	  	String seach_pr_no = prot.getPL_BI_DATA_NUM();	 //������ȣ 	
	  	String artcl_no = prot.getPL_RAI_ARTCL_NUM(); //������ ��ȣ
	  	String expresstion = prot.getPL_BI_EXPRESSION(); //ǥ����
	  	String ic_option = prot.getPL_BI_IMP_CLASS(); //�浹���
	  	String mp_option = prot.getPL_BI_MAIN_PROC(); //�����μ���
	  	String sp_option = prot.getPL_BI_SUB_PROC(); //�����μ���
	  	String process_option = sp_option;
	  	String exp_option = prot.getPL_BI_EXP_THE_REC(); // ��������
	  	String flag = ""; //�Է��ʵ�
	  	/* if(prot.getPL_BI_INSERT_FLAG().equalsIgnoreCase("I")){
	  		flag = "�Է���";
	  	}else{
	  		flag = "�Է¿Ϸ�";
	  		 ////ComUtil.getAbbrString(exp_option, 10);
	  	} */

	  %>
	  		<tr>
	  			<td class="tdc">
			     	 <input type="checkbox" name="check_pr_no" value="<%=seach_pr_no%>" onclick="addCheckList()">
			    </td >
			    <td class="tdc">
			    <%//�⺻����%>
			     	<a href="javascript:void(0);"  onClick="viewBasicInfo('<%=seach_pr_no%>','<%=artcl_no%>'); return false;"><%=seach_pr_no%></a>
			    </td >
			    <td class="tdc">
					<a href="javascript:void(0);"  onClick="viewGraphInfo('<%=seach_pr_no%>'); return false;">
						<img src = "images/file_img/graph.jpg" width="16" height="16" border="0"> 
					</a>
			    </td >
			    <td class="tdl">
			     	<%=expresstion%>
			    </td >
			    <td class="tdc">
			     	<%=ic_option%>
			    </td >
			    <td class="tdc">
			     	<%=process_option%>
			    </td >
	     		<td class="tdc">
	     			<%=exp_option%>
	     		</td >	     		
		  	</tr>
	  		<%
	  	}
	  	
	  %>
</table>
<div class="bbtn_r">
<a href="javascript:getPropertyInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('getListImage','','images/img_cont/eng/get_info_list_upper.gif',1)"><img src="images/img_cont/eng/get_info_list_normal.gif" name="getListImage" width="150" height="24" border="0" id="getListImage" /></a>
</div>
</form>
</body>
</html>