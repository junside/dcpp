/**
 * 
 */
package nfri.icamdata.common;

import java.util.Vector;

import nfri.dcpp.com.db.ComResultSet;
import nfri.dcpp.com.db.ComSQLExecution;


/**
 * @Project: dcpp
 * @Title  : DAO_ICAM_USER_INFO.java
 * @Description : -
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2016. 5. 17.
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class DAO_ICAM_USER_INFO {
	//DB 실행 클래스 선언
		ComSQLExecution comSQL;
		
		//생성자
		public DAO_ICAM_USER_INFO(){
			comSQL = new ComSQLExecution();
		}
		
		/**
		 * @MethodName  : insertUserInfo
		 * @Date   : 2016. 6. 14. 
		 * @MethodDescription : 학회 참석자 등록
		 * @param userInfo
		 * @return
		 * @History  : - 
		 */
		public boolean insertUserInfo(Basic_Icam_User_Info userInfo){
			boolean value = false;
			String sqlQuery = "INSERT INTO PLASMA.ICAMDATA_USER_INFO " +
			"( PL_ICAM_FIRST_NAME, PL_ICAM_LAST_NAME, PL_ICAM_AFFILIATION," +
			" PL_ICAM_EMAIL, PL_ICAM_COUNTRY, PL_ICAM_SALUTATION, PL_ICAM_COMMENTS," +
			" PL_ICAM_REG_DATE, PL_ICAM_REG_PROCESS)" +
			" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			value = comSQL.executeTransact(sqlQuery, userInfo.getDataList());
			
			return value;
		}
		
		/**
		 * @MethodName  : searchUserInfo
		 * @Date   : 2016. 6. 14. 
		 * @MethodDescription : 학회 참석자 검색
		 * @return
		 * @History  : - 
		 */
		public Vector<Basic_Icam_User_Info> searchUserInfo(){
			
			String sqlQuery = " SELECT I.PL_ICAM_FIRST_NAME, I.PL_ICAM_LAST_NAME, I.PL_ICAM_AFFILIATION,"
					+ " I.PL_ICAM_EMAIL, I.PL_ICAM_COUNTRY, I.PL_ICAM_SALUTATION,"
					+ " I.PL_ICAM_COMMENTS, I.PL_ICAM_REG_DATE, I.PL_ICAM_REG_PROCESS"
					+ " FROM PLASMA.ICAMDATA_USER_INFO I "
					+ " ORDER BY I.PL_ICAM_REG_DATE ASC";
	

			ComResultSet rs = comSQL.executeSelect(sqlQuery);	
			
			Vector<Basic_Icam_User_Info> values = new Vector<Basic_Icam_User_Info>();
			
			while(rs.next()){		
				Basic_Icam_User_Info count_info = new Basic_Icam_User_Info();
				count_info.setICAM_First_Name(rs.getString(1));
				count_info.setICAM_Last_Name(rs.getString(2)); 
				count_info.setICAM_Affiliation(rs.getString(3));
				count_info.setICAM_Email(rs.getString(4));
				count_info.setICAM_Country(rs.getString(5));
				count_info.setICAM_Salutation(rs.getString(6));
				count_info.setICAM_Comments(rs.getString(7));
				count_info.setICAM_Reg_Date(rs.getString(8));
				count_info.setICAM_Reg_Process(rs.getString(9));
				
				values.addElement(count_info);
			}
			
			return values;
		}
		
		/**
		 * @MethodName  : updateUserStatus
		 * @Date   : 2016. 6. 14. 
		 * @MethodDescription : 학회 참석자 상태 수정
		 * @param sqlOption
		 * @return
		 * @History  : - 
		 */
		public boolean updateUserStatus(String email, String time) {
			boolean value = false;
			System.out.println("DB");
			Vector<String> sqlOption = new Vector<String>();
			sqlOption.addElement(time);
			sqlOption.addElement(email);
			
			String sqlQuery = "UPDATE PLASMA.ICAMDATA_USER_INFO" +			
					" SET PL_ICAM_REG_DATE = ?, PL_ICAM_REG_PROCESS = 'C' " +
					" WHERE PL_ICAM_EMAIL = ? ";
			
			value = comSQL.executeTransact(sqlQuery, sqlOption);
			
			return value;

		}
}
