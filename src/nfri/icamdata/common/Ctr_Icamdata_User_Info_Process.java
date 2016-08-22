/**
 * 
 */
package nfri.icamdata.common;

import java.sql.SQLException;
import java.util.Vector;



/**
 * @Project: dcpp
 * @Title  : Ctr_Icamdata_User_Info_Process.java
 * @Description : -
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2016. 5. 17.
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class Ctr_Icamdata_User_Info_Process {

	/**
	 * 
	 */
	public Ctr_Icamdata_User_Info_Process() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @MethodName  : insertIcamUserInfo
	 * @Date   : 2016. 6. 14. 
	 * @MethodDescription : 학회 등록자 입력 메소드
	 * @param userInfo
	 * @return
	 * @throws SQLException
	 * @History  : - 
	 */
	public boolean insertIcamUserInfo(Basic_Icam_User_Info userInfo) throws SQLException{	

		DAO_ICAM_USER_INFO info = new DAO_ICAM_USER_INFO();
	
		boolean flag = info.insertUserInfo(userInfo);
		
		return flag;
	}
	
	/**
	 * @MethodName  : searchUserInfo
	 * @Date   : 2016. 6. 14. 
	 * @MethodDescription : 학회 등록자 검색 메소드
	 * @return
	 * @History  : - 
	 */
	public Vector<?> searchUserInfo(){
		Vector<?> search_list = new Vector<Object>();

		DAO_ICAM_USER_INFO info = new DAO_ICAM_USER_INFO();
		
		search_list = info.searchUserInfo();		
		
		return search_list;
	}

	/**
	 * @MethodName  : updateUserStatus
	 * @Date   : 2016. 6. 14. 
	 * @MethodDescription : 학회 등록자 상태 변경 메소드
	 * @param email
	 * @param time
	 * @return
	 * @History  : - 
	 */
	public boolean updateUserStatus(String email, String time){
		DAO_ICAM_USER_INFO info = new DAO_ICAM_USER_INFO();
		
		System.out.println("Ctr");
		boolean flag = info.updateUserStatus(email, time);
		
		return flag;	
		
	}

}
