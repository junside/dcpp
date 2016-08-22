package nfri.dcpp.com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import nfri.dcpp.com.util.ComUtil;
import nfri.dcpp.com.util.ComVar;

/**
 *
 * @Project : dcpp_web
 * @Title : ComSQLExecution.java
 * @Description : Query 를 실행하는 SQL 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 09
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class ComSQLExecution {
	private DBConMgr pool = null;
	
	public ComSQLExecution(){
		try {
			pool = DBConMgr.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @MethodName : executeSelect
	 * @Desc : 다중 Select 실행 메소드
	 * @param sqlQuery
	 * @return
	 */
	public ComResultSet executeSelect(String sqlQuery){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rmd = null;
		int columnCount = 0;
		
		ComResultSet comrs = new ComResultSet();
		Vector<Hashtable<Integer, Comparable>> rowData = new Vector<Hashtable<Integer, Comparable>>();
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sqlQuery);
			rs = pstmt.executeQuery();
			rmd = rs.getMetaData();
			columnCount = rmd.getColumnCount();
			while(rs.next()){
				Hashtable<Integer, Comparable> columnData = new Hashtable<Integer, Comparable>();
				for(int i = 1; i <= columnCount; i++){
					switch(rmd.getColumnType(i)){
					case Types.VARCHAR:
						columnData.put(i, rs.getString(i));
						break;
					case Types.NUMERIC:
						columnData.put(i, String.valueOf(rs.getInt(i)));
						break;
					case Types.INTEGER:
						columnData.put(i, String.valueOf(rs.getInt(i)));
						break;
					case Types.NULL:
						columnData.put(i, ComVar.NULLINT);
						break;
					}
				}
				rowData.addElement(columnData);
			}
			comrs.setRowData(rowData);
			comrs.setColCount(columnCount);			
		} catch (SQLException e) {
			System.out.println("error code : " + e);
			System.out.println(" sql : " + sqlQuery);
			e.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(con != null) try{con.close();}catch(SQLException sqle){}
		}
		return comrs;
	}
	
	/**
	 * @MethodName : executeSelect
	 * @Desc : 여러 옵션을 가진 단일 Query문을 실행하여 여러 값을 리턴하는 SQL문 수행
	 * @param sqlQuery
	 * @param sqlOption
	 * @return
	 */
	public ComResultSet executeSelect(String sqlQuery, Vector<?> sqlOption){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rmd = null;
		int columnCount = 0;
		
		ComResultSet comrs = new ComResultSet();
		Vector<Hashtable<Integer, Comparable>> rowData = new Vector<Hashtable<Integer, Comparable>>();
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sqlQuery);
			
			for(int i = 0 ; i < sqlOption.size(); i++){
				if(ComUtil.getFileType(sqlOption.get(i)) == ComVar.OBJ_STRING){
					pstmt.setString(i+1, (String)sqlOption.get(i));
				}else if(ComUtil.getFileType(sqlOption.get(i)) == ComVar.OBJ_INTEGER){
					pstmt.setInt(i+1, (Integer)sqlOption.get(i));					
				}else if(ComUtil.getFileType(sqlOption.get(i)) == ComVar.OBJ_DOUBLE){
					pstmt.setDouble(i+1, (Double)sqlOption.get(i));
				}
			}
			
			rs = pstmt.executeQuery();			
			rmd = rs.getMetaData();
			columnCount = rmd.getColumnCount();
			while(rs.next()){
				Hashtable<Integer, Comparable> columnData = new Hashtable<Integer, Comparable>();
				for(int i = 1; i <= columnCount; i++){
					switch(rmd.getColumnType(i)){					
					case Types.VARCHAR:
						columnData.put(i, rs.getString(i));
						break;
					case Types.NUMERIC:
						columnData.put(i, String.valueOf(rs.getInt(i)));
						break;
					case Types.INTEGER:
						columnData.put(i, String.valueOf(rs.getInt(i)));
						break;
					case Types.NULL:
						columnData.put(i, ComVar.NULLINT);
						break;
					}
				}
				rowData.addElement(columnData);
			}
			comrs.setRowData(rowData);
			comrs.setColCount(columnCount);
		} catch (SQLException e) {
			System.out.println("error code : " + e);
			System.out.println(" sql : " + sqlQuery);
			e.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(con != null) try{con.close();}catch(SQLException sqle){}
		}
		return comrs;
	}
	
	/**
	 * @MethodName : executeSelectCount
	 * @Desc : 해당 쿼리 내용의 카운트를 가져오는 메소드
	 * @param sqlQuery
	 * @param sqlOption
	 * @return
	 *//*
	public int executeSelectCount(String sqlQuery, Vector sqlOption){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Vector rowData = new Vector();
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sqlQuery);
			
			for(int i = 0 ; i < sqlOption.size(); i++){
				if(ComUtil.getFileType(sqlOption.get(i)) == ComVar.OBJ_STRING){
					pstmt.setString(i+1, (String)sqlOption.get(i));
				}else if(ComUtil.getFileType(sqlOption.get(i)) == ComVar.OBJ_INTEGER){
					pstmt.setInt(i+1, (Integer)sqlOption.get(i));					
				}else if(ComUtil.getFileType(sqlOption.get(i)) == ComVar.OBJ_DOUBLE){
					pstmt.setDouble(i+1, (Double)sqlOption.get(i));
				}
			}
			
			rs = pstmt.executeQuery();			
			
			while(rs.next()){
				Hashtable columnData = new Hashtable();
				rowData.addElement(columnData);
			}

		} catch (SQLException e) {
			System.out.println("error code : " + e);
			e.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(con != null) try{con.close();}catch(SQLException sqle){}
		}
		return rowData.size();
	}*/
	
	/**
	 * @MethodName : executeSelectOneStr
	 * @Desc : 단일 Select(String) 실행 메소드
	 * @param sqlQuery
	 * @return
	 */
	public String executeSelectOneStr(String sqlQuery){
		String resultStr = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//ResultSetMetaData rmd = null;
		//int columnCount = 0;
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sqlQuery);
			rs = pstmt.executeQuery();
			//rmd = rs.getMetaData();
			//columnCount = rmd.getColumnCount();
			
			if(rs.next()){
				resultStr = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("error code : " + e);
			System.out.println(" sql : " + sqlQuery);
			e.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(con != null) try{con.close();}catch(SQLException sqle){}
		}
		
		return resultStr;
	}
	
	/**
	 * @MethodName : executeSelectOneStr
	 * @Desc : 여러 옵션을 가진 단일 SQL문 실행하여 하나의 STRING 값을 리턴하는 SQL문
	 * @param sqlQuery
	 * @param sqlOption
	 * @return
	 */
	public String executeSelectOneStr(String sqlQuery, Vector<?> sqlOption){
		String resultStr = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//ResultSetMetaData rmd = null;
		//int columnCount = 0;
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sqlQuery);
			
			for(int i = 0 ; i < sqlOption.size(); i++){
				if(ComUtil.getFileType(sqlOption.get(i)) == ComVar.OBJ_STRING){
					pstmt.setString(i+1, (String)sqlOption.get(i));
				}else if(ComUtil.getFileType(sqlOption.get(i)) == ComVar.OBJ_INTEGER){
					pstmt.setInt(i+1, (Integer)sqlOption.get(i));					
				}else if(ComUtil.getFileType(sqlOption.get(i)) == ComVar.OBJ_DOUBLE){
					pstmt.setDouble(i+1, (Double)sqlOption.get(i));
				}
			}			
			
			rs = pstmt.executeQuery();
			//rmd = rs.getMetaData();
			//columnCount = rmd.getColumnCount();
			
			if(rs.next()){
				resultStr = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("error code : " + e);
			System.out.println(" sql : " + sqlQuery);
			e.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(con != null) try{con.close();}catch(SQLException sqle){}
		}
		
		return resultStr;
	}
	
	public int executeSelectOneInt(String sqlQuery, Vector<?> sqlOption){
		int resultInt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//ResultSetMetaData rmd = null;
		//int columnCount = 0;
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sqlQuery);
			
			for(int i = 0 ; i < sqlOption.size(); i++){
				if(ComUtil.getFileType(sqlOption.get(i)) == ComVar.OBJ_STRING){
					pstmt.setString(i+1, (String)sqlOption.get(i));
				}else if(ComUtil.getFileType(sqlOption.get(i)) == ComVar.OBJ_INTEGER){
					pstmt.setInt(i+1, (Integer)sqlOption.get(i));					
				}else if(ComUtil.getFileType(sqlOption.get(i)) == ComVar.OBJ_DOUBLE){
					pstmt.setDouble(i+1, (Double)sqlOption.get(i));
				}
			}			
			
			rs = pstmt.executeQuery();
			//rmd = rs.getMetaData();
			//columnCount = rmd.getColumnCount();
			
			if(rs.next()){
				resultInt = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("error code : " + e);
			System.out.println(" sql : " + sqlQuery);
			e.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(con != null) try{con.close();}catch(SQLException sqle){}
		}
		
		return resultInt;
	}
	
	public int executeSelectOneInt(String sqlQuery, String sqlOption){
		int resultInt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//ResultSetMetaData rmd = null;
		//int columnCount = 0;
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sqlQuery);
			
			pstmt.setString(1, sqlOption);		
			
			rs = pstmt.executeQuery();
			//rmd = rs.getMetaData();
			//columnCount = rmd.getColumnCount();
			
			if(rs.next()){
				resultInt = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("error code : " + e);
			System.out.println(" sql : " + sqlQuery);
			e.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(con != null) try{con.close();}catch(SQLException sqle){}
		}
		
		return resultInt;
	}
	
	/**
	 * @MethodName : executeSelectOneInt
	 * @Desc : 단일 Select(Integer) 실행 메소드
	 * @param sqlQuery
	 * @return
	 */
	public int executeSelectOneInt(String sqlQuery){
		int resultInt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//ResultSetMetaData rmd = null;
		//int columnCount = 0;
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sqlQuery);
			rs = pstmt.executeQuery();
			//rmd = rs.getMetaData();
			//columnCount = rmd.getColumnCount();
			
			if(rs.next()){
				resultInt = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("error code : " + e);
			System.out.println(" sql : " + sqlQuery);
			e.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(con != null) try{con.close();}catch(SQLException sqle){}
		}
		
		return resultInt;
	}
	
	/**
	 * @MethodName : executeSelectOneDouble
	 * @Desc : 단일 Select(double) 실행 메소드
	 * @param sqlQuery
	 * @return
	 */
	public double executeSelectOneDouble(String sqlQuery){
		double resultDouble = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//ResultSetMetaData rmd = null;
		//int columnCount = 0;
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sqlQuery);
			rs = pstmt.executeQuery();
			//rmd = rs.getMetaData();
			//columnCount = rmd.getColumnCount();
			
			if(rs.next()){
				resultDouble = rs.getDouble(1);
			}
		} catch (SQLException e) {
			System.out.println("error code : " + e);
			System.out.println(" sql : " + sqlQuery);
			e.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(con != null) try{con.close();}catch(SQLException sqle){}
		}
		
		return resultDouble;
	}
	
	/**
	 * @MethodName  : executeTransact
	 * @Date   : 2011. 4. 6. 
	 * @MethodDescription : 단순 SQL 문을 실행하는 메소드 (Sequence Alter)
	 * @param sqlQuery
	 * @return
	 * @History  : - 
	 */
	public boolean executeTransact(String sqlQuery){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = pool.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sqlQuery);
			pstmt.executeUpdate();
			con.commit();
			return true;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException re) {
				re.printStackTrace();
			}
			System.out.println("error code : " + e);
			System.out.println(" sql : " + sqlQuery);			
			e.printStackTrace();
			return false;
		} finally {
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(con != null) try{con.close();}catch(SQLException sqle){}
		}
	}
	
	/**
	 * @MethodName : executeTransact
	 * @Desc : insert, delete, update 문을 실행하는 메소드
	 * @param sqlQuery
	 * @return
	 */
	public boolean executeTransact(String sqlQuery, Vector<?> sqlOption){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = pool.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sqlQuery);
			
			for(int i = 0 ; i < sqlOption.size(); i++){
				if(ComUtil.getFileType(sqlOption.get(i)) == ComVar.OBJ_STRING){
					pstmt.setString(i+1, (String)sqlOption.get(i));
				}else if(ComUtil.getFileType(sqlOption.get(i)) == ComVar.OBJ_INTEGER){
					pstmt.setInt(i+1, (Integer)sqlOption.get(i));	
				}else if(ComUtil.getFileType(sqlOption.get(i)) == ComVar.OBJ_DOUBLE){
					pstmt.setDouble(i+1, (Double)sqlOption.get(i));
				}
			}
			pstmt.executeUpdate();
			con.commit();
			return true;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException re) {
				re.printStackTrace();
			}
			System.out.println(" sql : " + sqlQuery);
			System.out.println("error code : " + e);
			e.printStackTrace();
			return false;
		} finally {
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(con != null) try{con.close();}catch(SQLException sqle){}
		}
	}
	
	/**
	 * @MethodName : executeTransact
	 * @Desc : 1개의 insert, delete, update 문을 여러번 반복 실행하는 메소드
	 * @param sqlQuery
	 * @param totalParam
	 * @return
	 */
	public boolean executeTransact(String sqlQuery, ArrayList<?> totalParam){
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = null;
		Vector<?> option = new Vector<Object>();
		String option_str = "";
		int option_int = 0;
		double option_double = 0;
	
		//System.out.println("======================= start ========================");
		//System.out.println(">>> Time : " + ComUtil.getTimeNow());
		//System.out.println(">>> totalParam.size() : " + totalParam.size());

		try {
			con = pool.getConnection();
			con.setAutoCommit(false);			
			
			//System.out.println(">>> totalParam.size() : " + totalParam.size());
			for(int i = 0; i < totalParam.size(); i++){
				query = sqlQuery;	
				 //PreparedStatement pstmt = query.getPreparedStatement(SQL());
				option = (Vector<?>)totalParam.get(i);				
				
				pstmt = con.prepareStatement(query);
				
				//System.out.println(">>> " + i);
				//System.out.println("======================= start ========================");
				for(int j = 0 ; j < option.size(); j++){
					//System.out.println(">>> totalParam i : " + i);
					//System.out.println(">>> option j : " + j + " = " + option.get(j).toString());
					
					if(ComUtil.isNull(option.get(j).toString())){
						System.out.println("======================= Error ========================");
						System.out.println(">>> Time : " + ComUtil.getTimeNow());
						System.out.println("option_str is Null!! [ "+ i+", "+ j + " ] : " + option_str);
						System.out.println("======================= ----- ========================");
					}else{
						//System.out.println("option_str [ "+ j + " ] : " + option_str);
					}
					if(ComUtil.getFileType(option.get(j)) == ComVar.OBJ_STRING){
						option_str = (String)option.get(j);
						//System.out.println("option_str [ "+ j + " ] : " + option_str);
						pstmt.setString(j+1, option_str);
					}else if(ComUtil.getFileType(option.get(j)) == ComVar.OBJ_INTEGER){
						option_int = (Integer)option.get(j);
						//System.out.println("option_int [ "+ j + " ] : " + option_int);
						pstmt.setInt(j+1, option_int);
					}else if(ComUtil.getFileType(option.get(j)) == ComVar.OBJ_DOUBLE){
						option_double = (Double)option.get(j);
						//System.out.println("option_double [ "+ j + " ] : "+option_double);
						pstmt.setDouble(j+1, option_double);
					}
				}				
				pstmt.executeUpdate();
				pstmt.close();
				//con.commit();
				//con.close();
				//pstmt.addBatch();
				
			}
			//pstmt.executeBatch();
			//System.out.println("======================== end =========================");
			//pstmt.close();
			con.commit();
			con.close();
			try {
                if (pstmt != null)
                	pstmt.close();
            }
            catch(Exception e)
            {
            	pstmt=null;
            }; 

			return true;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException re) {
				re.printStackTrace();
			}			
			System.out.println(">>> error code : " + e);
			System.out.println(">>> sql : " + query);
			System.out.println(">>> pstmt.toString() : " + pstmt.toString());
			e.printStackTrace();
			return false;
		} finally {
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(con != null) try{con.close();}catch(SQLException sqle){}
		}
	}
	
	/**
	 * @MethodName : executeTransact
	 * @Desc : 여러개의 insert, delete, update 문을 여러개의 파라미터로 실행하는 메소드
	 * @param sqlQuery[]
	 * @return
	 */
	public boolean executeTransact(ArrayList<?> totalquery, ArrayList<?> totalParam){
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = null;
		Vector<?> option = new Vector<Object>();
		String option_str = "";
		int option_int = 0;
		double option_double = 0;
		
		try {
			con = pool.getConnection();
			con.setAutoCommit(false);
			for(int i = 0; i < totalquery.size(); i++){
				query = (String)totalquery.get(i);		
				option = (Vector<?>)totalParam.get(i);
				pstmt = con.prepareStatement(query);
				for(int j = 0 ; j < option.size(); j++){
					if(ComUtil.getFileType(option.get(j)) == ComVar.OBJ_STRING){
						option_str = (String)option.get(j);
						pstmt.setString(j+1, option_str);
					}else if(ComUtil.getFileType(option.get(j)) == ComVar.OBJ_INTEGER){
						option_int = (Integer)option.get(j);
						pstmt.setInt(j+1, option_int);
					}else if(ComUtil.getFileType(option.get(j)) == ComVar.OBJ_DOUBLE){
						option_double = (Double)option.get(j);
						pstmt.setDouble(j+1, option_double);
					}
				}
				pstmt.executeUpdate();
			}
			con.commit();
			return true;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException re) {
				re.printStackTrace();
			}			
			System.out.println("error code : " + e);
			System.out.println(" sql : " + query);
			e.printStackTrace();
			return false;
		} finally {
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(con != null) try{con.close();}catch(SQLException sqle){}
		}
	}
	
	/**
	 * @MethodName  : executeTransact
	 * @Date   : 2012. 1. 13. 
	 * @MethodDescription : 여러개의 insert, delete, update 문을 한 개의 파라미터로 실행하는 메소드
	 * @param totalquery
	 * @param sqlOption
	 * @return
	 * @History  : - 
	 */
	public boolean executeTransact(ArrayList<?> totalquery, Vector<?> sqlOption){
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = null;
		Vector<?> option = new Vector<Object>();
		String option_str = "";
		int option_int = 0;
		double option_double = 0;
		
		try {
			con = pool.getConnection();
			con.setAutoCommit(false);
			for(int i = 0; i < totalquery.size(); i++){
				query = (String)totalquery.get(i);		
				pstmt = con.prepareStatement(query);
				for(int j = 0 ; j < sqlOption.size(); j++){
					if(ComUtil.getFileType(sqlOption.get(j)) == ComVar.OBJ_STRING){
						pstmt.setString(j+1, (String)sqlOption.get(j));
					}else if(ComUtil.getFileType(sqlOption.get(j)) == ComVar.OBJ_INTEGER){
						pstmt.setInt(j+1, (Integer)sqlOption.get(j));	
					}else if(ComUtil.getFileType(sqlOption.get(j)) == ComVar.OBJ_DOUBLE){
						pstmt.setDouble(j+1, (Double)sqlOption.get(j));
					}
				}
				pstmt.executeUpdate();
			}
			con.commit();
			return true;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException re) {
				re.printStackTrace();
			}
			System.out.println("error code : " + e);
			System.out.println(" sql : " + query);
			e.printStackTrace();
			return false;
		} finally {
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(con != null) try{con.close();}catch(SQLException sqle){}
		}
	}
	
	
}
