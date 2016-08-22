<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*" %>

<html>
<head>
<title>SELECT TEST PAGE</title>
</head>
<body>
<h3>
SELECT TEST PAGE
</h3>
<table width = "550" border = "1" cellspacing = "0" cellpadding ="3" align = "left">
<tr>
	<td width = "100">No.</td>	
	<td width = "100">아이디</td>
	<td width = "100">패스워드</td>
	<td width = "100">이름</td>
</tr>
<%
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try{
		
		//String jdbcUrl = "jdbc:oracle:thin:@plasmachem.nfri.re.kr:1521:orcl";
		String jdbcUrl = "jdbc:oracle:thin:@203.230.119.123:1521:orcl";
		String dbID = "hr";
		String dbPWD = "hr";
		//String dbID = "plasma";
		//String dbPWD = "nfriadmin";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		conn = DriverManager.getConnection(jdbcUrl, dbID, dbPWD);
		
		//String sql = "select * from pl_user_info order by PL_UI_SEQ";
		String sql = "select * from customer";
		
		pstmt = conn.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		ResultSetMetaData rsmd = rs.getMetaData();
		
		int count = rsmd.getColumnCount();
		
		while(rs.next()){
			String s_no = rs.getString("code");
			String s_id = rs.getString("name");
			String s_passwd = rs.getString("email");
			String s_name = rs.getString("tel");
			//out.println(count);
			%>
				<tr>
					<td width = "100"><%=s_no %></td>	
					<td width = "100"><%=s_id %></td>
					<td width = "100"><%=s_passwd %></td>
					<td width = "100"><%=s_name %></td>
				</tr>
			<%
			
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(rs != null) try{rs.close();}catch(SQLException sqle){}
		if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
		if(conn != null) try{conn.close();}catch(SQLException sqle){}		
	}
%>
</table>
</body>
</html>
