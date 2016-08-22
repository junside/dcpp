package nfri.dcpp.member.db;

import java.util.Vector;

import nfri.dcpp.com.db.ComResultSet;
import nfri.dcpp.com.db.ComSQLExecution;
import nfri.dcpp.member.model.User_Info;

/**
 *
 * @Project : dcpp_web
 * @Title : DAO_USER_INFO.java
 * @Description : ����ڿ� ���õ� DB �۾��� ó���ϴ� DAO Ŭ����
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 06. 15
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @�����̷� : -
 *
 */
public class DAO_USER_INFO {
	//DB ���� Ŭ���� ����
	ComSQLExecution comSQL;
	
	public DAO_USER_INFO(){
		comSQL = new ComSQLExecution();
	}
	
	/**
	 * @MethodName : Select_User_ID
	 * @Desc : ��ȿ�� ID üũ
	 * @param id
	 * @return
	 */
	public boolean Select_User_ID(String id){
		String sqlQuery = "SELECT PL_UI_ID, PL_UI_PASSWD FROM PLASMA.PL_USER_INFO " +
		"WHERE PL_UI_ID = ?";
		
		//Query �Ķ���� ����		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(id);

		ComResultSet rs = comSQL.executeSelect(sqlQuery, ve_1);
				
		boolean return_value = false;
		
		if(rs.next()){				
			return_value = true; //����
		}else{
			return_value = false; //�߸��� ID
		}
				
		return return_value;
	}
	
	/**
	 * @MethodName  : SelectUserName
	 * @Date   : 2010. 09. 30 
	 * @MethodDescription : ������� �̸��� �������� �޼ҵ�
	 * @param id
	 * @return
	 * @History  : - 
	 */
	public String SelectUserName(String id){
		String r_value = "";
		String sqlQuery = "SELECT PL_UI_NAME FROM PLASMA.PL_USER_INFO WHERE PL_UI_ID = ?";
		
		//Query �Ķ���� ����		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(id);

		r_value = comSQL.executeSelectOneStr(sqlQuery, ve_1);
				
		return r_value;
	}
	
	/**
	 * @MethodName : Select_User_Passwd
	 * @Desc : ��ȿ�� Password üũ
	 * @param id
	 * @param pwd
	 * @return
	 */
	public boolean Select_User_Passwd(String id, String pwd){
		
		String sqlQuery = "SELECT PL_UI_ID, PL_UI_PASSWD, PL_AI_GRADE FROM PLASMA.PL_USER_INFO " +
		"WHERE PL_UI_ID = ? AND PL_UI_PASSWD = ?";
		
		//Query �Ķ���� ����		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(id);
		ve_1.addElement(pwd);

		ComResultSet rs = comSQL.executeSelect(sqlQuery, ve_1);
				
		boolean return_value = false;
		
		if(rs.next()){				
			return_value = true; //����
		}else{
			return_value = false; //�߸��� ID
		}
				
		return return_value;
	}
	
	public User_Info Select_User_Info(String id, String pwd){
		String sqlQuery = "SELECT PL_UI_SEQ, PL_UI_ID, PL_UI_PASSWD, PL_UI_NAME, PL_UI_ORG, PL_UI_CPHONE," +
				" PL_UI_OPHONE, PL_UI_ADDRESS, PL_AI_GRADE, PL_UI_EMAIL" +
				" FROM PLASMA.PL_USER_INFO" +
				" WHERE PL_UI_ID = ? AND PL_UI_PASSWD = ?";
		
		//Query �Ķ���� ����		
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
	 * @Desc : ����ڰ� �ִ��� ���θ� üũ�ϴ� �޼ҵ�
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
					return_value = ComVar.USER_PWD_VAILD; //����
				}else{
					return_value = ComVar.USER_PWD_INVAILD; //�߸��� pwd
				}
			}else{
				return_value = ComVar.USER_ID_INVAILD; //�߸��� ID
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
