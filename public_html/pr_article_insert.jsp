<%@ page language="java" contentType="text/html; charset=EUC-KR"  pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="nfri.dcpp.properties.db.*"%>
<%@page import="nfri.dcpp.properties.model.*"%>
<%@page import="java.util.*"%>
<%
/**
* ������ �����͸� �Է��ϴ� �� JSP ������
* by Junside(J.H Park)
*/
	String codeid = request.getParameter("code_id");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>������ ���� �Է�</title>
</head>
<body>
<script language = javascript>
function uploadArtclInfo(){ //���ε�
	var form2 = document.form1;	
	var errorV = 'F'; //������ ��Ÿ���� ������ �⺻���� F�� ����
	
	//0. ���� ���� ���� üũ
	if(form2.pl_raci_code_id.value.length < 1){
		alert("���� ���� ���� �ڵ��� �����ϼ���!");
		form2.pl_raci_code_id.focus();
		errorV = 'T';
		return;
	}
	
	//1. �� ���� üũ
	if(form2.pl_ra_artcl_title.value.length < 1){
		alert("�� ������ �Է��ϼ���!");
		form2.pl_ra_artcl_title.focus();
		errorV = 'T';
		return;
	}
	
	//2. �� ���� üũ
	if(form2.pl_ra_artcl_auth.value.length < 1){
		alert("�� ���ڸ� �Է��ϼ���!");
		form2.pl_ra_artcl_auth.focus();
		errorV = 'T';
		return;
	}
	
	//3. �� ���θ� üũ
	if(form2.pl_ra_jour_title.value.length < 1){
		alert("�� ���θ��� �Է��ϼ���!");
		form2.pl_ra_jour_title.focus();
		errorV = 'T';
		return;
	}
	
	//4. �� ���� ���� üũ
	if(form2.pl_ra_jour_vol.value.length < 1){
		alert("�� ���� ������ �Է��ϼ���!");
		form2.pl_ra_jour_vol.focus();
		errorV = 'T';
		return;
	}
	
	//5. �� ���������� üũ
	if(form2.pl_ra_artcl_st_page.value.length < 1){
		alert("�� ������������ �Է��ϼ���!");
		form2.pl_ra_artcl_st_page.focus();
		errorV = 'T';
		return;
	}
	
	//6. �� ���� ���� üũ
	if(form2.pl_ra_jour_year.value.length < 1){
		alert("�� ���� ������ �Է��ϼ���!");
		form2.pl_ra_jour_year.focus();
		errorV = 'T';
		return;
	}
	
	//7. ���� ���ε� ���� ÷�� ���� üũ
	if(form2.pl_ra_org_file.value.length < 1){
		alert("���������� �߰��ϼ���!");
		form2.pl_ra_org_file.focus();
		errorV = 'T';
		return;
	}
	if(errorV == 'F'){
		alert("�ʵ� üũ �Ϸ�!");
		form2.action = 'pr_article_insert_fileupload.jsp';
		form2.submit();
	}
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
document.oncontextmenu     = new Function('return false');
document.ondragstart       = new Function('return false');
document.onselectstart     = new Function('return false');
</script>
<form name="form1" method="POST" enctype="multipart/form-data">
<div id="content">
<p class="level2">������ ���� �Է�</p>
<span class="help">�� ���� �Է½� ������ ���� &lt;�� &gt;�� ǥ���� ���� �ݵ�� HTML �ڵ带 ����ؾ� �մϴ�.</span><br>
<table class="tab2">
	<col width="20%">
	<col width="80%">
	<tr>
		<th class="thla">���� ���� ���� �ڵ�</th>
		<td class="tdl">
		<select	name="pl_raci_code_id" onChange="selectSubmit()" class="txtl">
		<jsp:useBean id="artcle_code" class="nfri.dcpp.properties.business.Ctr_Option_Process" scope="page"/>
		<%
			Vector vlist = artcle_code.getRefArticleCode();
			int count = vlist.size();
			for(int i = 0 ; i < count ; i++)
			{
				Article_Code code = (Article_Code)vlist.elementAt(i);
				String code_id = code.getPL_RACI_CODE_ID();
				String code_exp = code.getPL_RACI_CODE_EXP();
				%>
				<option value='<%=code_id%>' <%=code_id.equals(codeid)?"selected":""%>><%=code_exp%></option>
				<%
			}			
		%>
		</select></td>
	</tr>
	<tr>
		<th class="thla">�� ����</th>		
		<td class="tdl"><textarea name='pl_ra_artcl_title' cols='40' rows='6'></textarea></td>
	</tr>
	<tr>
		<th class="thla">�� ����</th>
		<td class="tdl"><input type='text' class='txtl300' name='pl_ra_artcl_auth' size='55' value=''></td>
	</tr>
	<tr>
		<th class="thla">���θ�</th>
		<td class="tdl"><input type='text' class='txtl300' name='pl_ra_jour_title' size='55' value=''></td>
	</tr>
    <tr>
		<th class="thla">ISSN_P</th>
		<td class="tdl"><input type='text' class='txtl300' name='pl_ra_issn_p' size='20' value=''></td>
	</tr>
	<tr>
		<th class="thla">ISSN_N</th>
		<td class="tdl"><input type='text' class='txtl300' name='pl_ra_issn_n' size='20' value=''></td>
	</tr>
	<tr>
		<th class="thla">ISBN</th>
		<td class="tdl"><input type='text' class='txtl300' name='pl_ra_isbn' size='20' value=''></td>
	</tr>
	<tr>
		<th class="thla">NDSL</th>
		<td class="tdl"><input type='text' class='txtl300' name='pl_ra_ndsl' size='20' value=''></td>
	</tr>
	<tr>
		<th class="thla">����</th>
		<td class="tdl"><input type='text' name='pl_ra_jour_vol' size='5' value=''></td>
	</tr>
	<tr>
		<th class="thla">ȣ</th>
		<td class="tdl"><input type='text' name='pl_ra_jour_num' size='5' value=''></td>
	</tr>
	<tr>
		<th class="thla">����������</th>
		<td class="tdl"><input type='text' name='pl_ra_artcl_st_page' size='10' value=''></td>
	</tr>
	<tr>
		<th class="thla">��������</th>
		<td class="tdl"><input type='text' name='pl_ra_artcl_ed_page' size='10' value=''></td>
	</tr>
	<tr>
		<th class="thla">���ǿ���</th>
		<td class="tdl"><input type='text' name='pl_ra_jour_year' size='4' value=''></td>
	</tr>
	<tr>
		<th class="thla">DOI</th>
		<td class="tdl"><input type='text' class='txtl300' name='pl_ra_artcl_doi' size='20' value=''></td>
	</tr>
	<tr>
		<th class="thla">��������</th>
		<td class="tdl"><input type='file' name='pl_ra_org_file' size='40' value=''></td>
	</tr>
</table>
<div class="bbtn_c">
<a href="javascript:uploadArtclInfo()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','images/img_cont/save_upper.gif',1)"><img src="images/img_cont/save_normal.gif" name="Image1" width="80" height="24" border="0" id="Image1"></a>
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
		 window.resizeTo(650,pageheight);
	  }
	}

window.onload = function(){
	  init();
}
</script>
</body>
</html>