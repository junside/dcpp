package nfri.dcpp.member.db;

import java.util.Vector;

import nfri.dcpp.com.db.ComResultSet;
import nfri.dcpp.com.db.ComSQLExecution;
import nfri.dcpp.member.model.User_Info;

/**
 *
 * @Project : dcpp_web
 * @Title : DAO_USER_INFO.java
 * @Description : 사용자와 관련된 DB 작업을 처리하는 DAO 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 06. 15
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class DAO_USER_INFO {
	//DB 실행 클래스 선언
	ComSQLExecution comSQL;
	
	public DAO_USER_INFO(){
		comSQL = new ComSQLExecution();
	}
	
	/**
	 * @MethodName : Select_User_ID
	 * @Desc : 유효한 ID 체크
	 * @param id
	 * @return
	 */
	public boolean Select_User_ID(String id){
		String sqlQuery = "SELECT PL_UI_ID, PL_UI_PASSWD FROM PLASMA.PL_USER_INFO " +
		"WHERE PL_UI_ID = ?";
		
		//Query 파라미터 벡터		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(id);

		ComResultSet rs = comSQL.executeSelect(sqlQuery, ve_1);
				
		boolean return_value = false;
		
		if(rs.next()){				
			return_value = true; //정상
		}else{
			return_value = false; //잘못된 ID
		}
				
		return return_value;
	}
	
	/**
	 * @MethodName  : SelectUserName
	 * @Date   : 2010. 09. 30 
	 * @MethodDescription : 사용자의 이름을 가져오는 메소드
	 * @param id
	 * @return
	 * @History  : - 
	 */
	public String SelectUserName(String id){
		String r_value = "";
		String sqlQuery = "SELECT PL_UI_NAME FROM PLASMA.PL_USER_INFO WHERE PL_UI_ID = ?";
		
		//Query 파라미터 벡터		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(id);

		r_value = comSQL.executeSelectOneStr(sqlQuery, ve_1);
				
		return r_value;
	}
	
	/**
	 * @MethodName : Select_User_Passwd
	 * @Desc : 유효한 Password 체크
	 * @param id
	 * @param pwd
	 * @return
	 */
	public boolean Select_User_Passwd(String id, String pwd){
		
		String sqlQuery = "SELECT PL_UI_ID, PL_UI_PASSWD, PL_AI_GRADE FROM PLASMA.PL_USER_INFO " +
		"WHERE PL_UI_ID = ? AND PL_UI_PASSWD = ?";
		
		//Query 파라미터 벡터		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(id);
		ve_1.addElement(pwd);

		ComResultSet rs = comSQL.executeSelect(sqlQuery, ve_1);
				
		boolean return_value = false;
		
		if(rs.next()){				
			return_value = true; //정상
		}else{
			return_value = false; //잘못된 ID
		}
				
		return return_value;
	}
	
	public User_Info Select_User_Info(String id, String pwd){
		String sqlQuery = "SELECT PL_UI_SEQ, PL_UI_ID, PL_UI_PASSWD, PL_UI_NAME, PL_UI_ORG, PL_UI_CPHONE," +
				" PL_UI_OPHONE, PL_UI_ADDRESS, PL_AI_GRADE, PL_UI_EMAIL" +
				" FROM PLASMA.PL_USER_INFO" +
				" WHERE PL_UI_ID = ? AND PL_UI_PASSWD = ?";
		
		//Query 파라미터 벡터		
		Vector<String> sqlOption = new Vector<String>();
		sqlOption.addElement(id);
		sqlOption.addElement(pwd);

		ComResultSet rs = comSQL.executeSelect(sqlQuery, sqlOption);
		User_Info userInfo = new User_Info();
		while(rs.next()){					
			userInfo.setPL_UI_SEQ(rs.getString(1));
			userInfo.setPL_UI_ID(rs.getString(2));
			userInfo.setPL_UI_PASSWD(rs.getString(3));
			userInfo.setPL_UI_NAME(rs.getString(4));
			userInfo.setPL_UI_ORG(rs.getString(5));
			userInfo.setPL_UI_CPHONE(rs.getString(6));
			userInfo.setPL_UI_OPHONE(rs.getString(7));
			userInfo.setPL_UI_ADDRESS(rs.getString(8));
			userInfo.setPL_AI_GRADE(rs.getString(9));
			userInfo.setPL_UI_EMAIL(rs.getString(10));
		}
		
		return userInfo;
	}
	
	/**
	 * @MethodName : userCheck
	 * @Desc : 사용자가 있는지 여부를 체크하는 메소드
	 * @param id
	 * @param pwd
	 * @return
	 *//*
	public String userCheck(String id, String pwd){
		Connection con = null;
		PreparedStatement pstmt_id = null;
		PreparedStatement pstmt_pwd = null;
		ResultSet rs_id = null;
		ResultSet rs_pwd = null;		
		String return_value = "";
		try {
			con = pool.getConnection();
			String sqlQuery_id = "SELECT PL_UI_ID, PL_UI_PASSWD FROM PLASMA.PL_USER_INFO " +
					"WHERE PL_UI_ID = ?";
			pstmt_id = con.prepareStatement(sqlQuery_id);
			pstmt_id.setString(1, id);			
			rs_id = pstmt_id.executeQuery();
			if(rs_id.next()){
				String sqlQuery_pwd = "SELECT PL_UI_ID, PL_UI_PASSWD, PL_AI_GRADE FROM PLASMA.PL_USER_INFO " +
				"WHERE PL_UI_ID = ? AND PL_UI_PASSWD = ?";
				pstmt_pwd = con.prepareStatement(sqlQuery_pwd);
				pstmt_pwd.setString(1, id);
				pstmt_pwd.setString(2, pwd);
				rs_pwd = pstmt_pwd.executeQuery();
				if(rs_pwd.next()){
					return_value = ComVar.USER_PWD_VAILD; //정상
				}else{
					return_value = ComVar.USER_PWD_INVAILD; //잘못된 pwd
				}
			}else{
				return_value = ComVar.USER_ID_INVAILD; //잘못된 ID
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(rs_id != null) try{rs_id.close();}catch(SQLException sqle){}
			if(rs_pwd != null) try{rs_pwd.close();}catch(SQLException sqle){}
			if(pstmt_id != null) try{pstmt_id.close();}catch(SQLException sqle){}
			if(pstmt_pwd != null) try{pstmt_pwd.close();}catch(SQLException sqle){}
			if(con != null) try{con.close();}catch(SQLException sqle){}
		}
		return return_value;
	}*/
}
