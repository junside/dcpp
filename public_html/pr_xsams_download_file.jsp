<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page import="java.io.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page import="nfri.dcpp.com.lang.*" %>
<%@page import="nfri.dcpp.com.util.ComUtil"%>
<%! public String name_replace(String line, String oldString, String newString) { 
        int index = 0; 
        while ((index = line.indexOf(oldString, index)) >= 0) { 
            line = line.substring(0, index) + newString + line.substring(index + oldString.length()); 
            index += newString.length(); 
        } 
        return line; 
    } 	
%>    
<%
	BufferedInputStream   in= null;
	BufferedOutputStream os	= null;
	File file= null;
	boolean skip= false;
	String client= "";
	
	String fname1 		= "";
	String fname2 		= "";
	String fname       	= "";
	String fpath       	= "";
	
try {
	System.out.println("save : 3!!! ");
			
	fname 	= request.getParameter("file_name"); 
	fpath 	= request.getParameter("file_path");
	
	//String fext = "xsams";
	//String fext = "xml";
	String new_fname = fname + ".xml";
	fname1   = Lang.han(new_fname);
	//path = request.getSession().getServletContext().getRealPath("/") + "xsams_file/" ;
	
	//path = "c:\\";
	
	System.out.println("fpath : " + fpath);
	System.out.println("fname1 : " + fname1);
	//path = request.getContextPath(); 
	//String path = "http://dcpp.nfri.re.kr/article_file/";
	
	//System.out.println(fpath);

	try{
		file= new File(fpath, fname1);
		in= new BufferedInputStream(new FileInputStream(file));
	}catch(FileNotFoundException fe){
		skip= true;
	}
	//fname2 = name_replace(fname1,"+"," ");
	fname2 = name_replace("xsams_"+fname1,"+"," ");
	//fname2 = java.net.URLEncoder.encode(fname2,"MS949");//CP949

	response.reset() ;
	client= request.getHeader("User-Agent");
	response.setContentType("application/x-msdownload;");
	response.setHeader("Content-Description", "JSP Generated Data");

	if(!skip){
		if(client.indexOf("MSIE 5.5") != -1){
			response.setHeader("Content-Type", "doesn/matter; charset=euc-kr");
			response.setHeader("Content-Disposition", "filename="+fname2+";");
		}else{
			response.setHeader("Content-Type", "application/octet-stream; charset=euc-kr");
			response.setHeader ("Content-Disposition", "attachment; filename="+fname2+";");
		}
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader ("Content-Length", ""+file.length());
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
		
		out.clear();
		os= new BufferedOutputStream(response.getOutputStream());
		byte b[]= new byte[4096]; 
		int leng= 0;
		while( (leng = in.read(b)) > 0 ){
			os.write(b,0,leng);
		}
	}else{
		out.println("<script language='javascript'>");
		out.println("alert('File Downloading Fail !! \n File Not Found !!');");
		out.println("history.back();");
		out.println("</script>");
	}

	}catch(Exception e) {
		System.out.println(e);
	}
	finally {
		if(in != null) in.close();
		if(os != null) os.close();
		
		boolean flag2 = ComUtil.removeFile(fpath, fname1); //저장된 파일 삭제하기
		
		//System.out.println(flag2);
		out.print("<SCRIPT LANGUAGE='JavaScript'>"); 
		out.print("self.close();"); 
		out.print("</SCRIPT>"); 
		//
		//<script>
		//closewindow();
  		//</script>		
		//
	}	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/common.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
<form name="form1" action="" method="POST" >
<div id="content">
</div>
</form>
<script language = javascript>
//function downloadfile(name){ //XSAMS 파일 다운로드
function closewindow(){
	self.close();
}

//window.onload = function(){	
	//downloadfile(file_path, file_name);
//}
</script>
