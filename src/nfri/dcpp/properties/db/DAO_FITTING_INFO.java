/**
 * 
 */
package nfri.dcpp.properties.db;

import java.util.Vector;

import nfri.dcpp.com.db.ComSQLExecution;
import nfri.dcpp.properties.model.Fitting_Temp_Info;

/**
 * @Project: dcpp
 * @Title  : DAO_FITTING_INFO.java
 * @Description : Fitting�� ������ ���̺��� SQL�� ó���ϴ� Ŭ����
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2010. 07. 15
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class DAO_FITTING_INFO {
	//DB ���� Ŭ���� ����
	ComSQLExecution comSQL;
	
	public DAO_FITTING_INFO(){
		comSQL = new ComSQLExecution();
	}
	
	/**
	 * @MethodName  : insertFittingTempData
	 * @Date   : 2010. 07. 15 
	 * @MethodDescription : Fitting�� ���� �ӽ� �����͸� �����ϴ� �޼ҵ�
	 * @param info
	 * @return
	 * @History  : - 
	 */
	public boolean insertFittingTempData(Fitting_Temp_Info info){
		boolean value = false;
		String sqlQuery = "INSERT INTO PLASMA.PL_FITTING_TEMP_DATA (" +
				" PL_FTD_SEQ, PL_FTD_X_VAL, PL_FTD_Y_VAL, PL_FBI_SEQ," +
				" PL_UI_ID, PL_IGBI_DATA_NUM)" +
				" VALUES ( ? , ? , ? , ? , ? , ? )";
		
		value = comSQL.executeTransact(sqlQuery, info.getDataList());
		
		return value;
	}
	
	/**
	 * @MethodName  : deleteFittingTempData
	 * @Date   : 2010. 07. 20 
	 * @MethodDescription : FittingTemp�� �ִ� ����� ID�� �� �����͵��� ����� �޼ҵ�
	 * @param user_id
	 * @return
	 * @History  : - 
	 */
	public boolean deleteFittingTempData(String user_id) {
		boolean r_value = false;
		
		Vector<String> count_option = new Vector<String>();
		count_option.addElement(user_id);	
		
		String countQuery = "SELECT COUNT(*) FROM PL_FITTING_TEMP_DATA WHERE PL_UI_ID = ?";
		
		int count = comSQL.executeSelectOneInt(countQuery, count_option);
		
		
		if(count > 0){
			Vector<String> delete_option = new Vector<String>();
			delete_option.addElement(user_id);
			
			String sqlQuery = "DELETE FROM PL_FITTING_TEMP_DATA WHERE PL_UI_ID = ? ";

			r_value = comSQL.executeTransact(sqlQuery, delete_option);

		}else{
			r_value = true;
		}		
		return r_value;
	}
	
	/**
	 * @MethodName  : checkFittingState
	 * @Date   : 2010. 07. 20 
	 * @MethodDescription : ����� ID�� ó���ǰ� �ִ� Fitting State �� �ִ����� ���θ� Ȯ��
	 * @param user_id
	 * @return
	 * @History  : - 
	 */
	public int checkFittingState(String user_id, String block_state){
		String selectQuery = "SELECT COUNT(*) FROM PL_FITTING_STATE_INFO WHERE PL_UI_ID = ? AND PL_FSI_RESULT = ? ";
		
		Vector<String> select_option = new Vector<String>();
		select_option.addElement(user_id);
		select_option.addElement(block_state);
		
		int count = comSQL.executeSelectOneInt(selectQuery, select_option);
			
		return count;
	}
	
	/**
	 * @MethodName  : insertFittingState
	 * @Date   : 2010. 07. 16 
	 * @MethodDescription : Fitting �۾��� ���� ���°��� Insert �ϴ� �޼ҵ�
	 * @param v_pro_no
	 * @param user_id
	 * @param block_seq
	 * @param block_state
	 * @return
	 * @History  : - 
	 */
	public boolean insertFittingState(String v_pro_no, String user_id, String block_seq, String block_state){
		boolean value = false;

		String insertQuery = "INSERT INTO PLASMA.PL_FITTING_STATE_INFO (" +
				" PL_IGBI_DATA_NUM, PL_UI_ID, PL_FBI_SEQ, PL_FSI_RESULT)" +
				" VALUES ( ?, ?, ?, ? )";
		
		Vector<String> insert_option = new Vector<String>();
		insert_option.addElement(v_pro_no);
		insert_option.addElement(user_id);
		insert_option.addElement(block_seq);
		insert_option.addElement(block_state);
		value = comSQL.executeTransact(insertQuery, insert_option);
		
		return value;
	}
	
	/**
	 * @MethodName  : updateFittingState
	 * @Date   : 2010. 07. 20 
	 * @MethodDescription : Fitting �۾��� ���� ���°��� Update �ϴ� �޼ҵ�
	 * @param v_pro_no
	 * @param user_id
	 * @param block_seq
	 * @param block_state
	 * @return
	 * @History  : - 
	 */
	public boolean updateFittingState(String v_pro_no, String user_id, String block_seq, String block_state){
		boolean value = false;	
		
		String updateQuery = "UPDATE PLASMA.PL_FITTING_STATE_INFO SET PL_IGBI_DATA_NUM = ?," +
				" PL_UI_ID = ?, PL_FBI_SEQ = ?, PL_FSI_RESULT = ?" +
				" WHERE PL_UI_ID = ? ";
		
		Vector<String> update_option = new Vector<String>();
		update_option.addElement(v_pro_no);
		update_option.addElement(user_id);
		update_option.addElement(block_seq);
		update_option.addElement(block_state);
		update_option.addElement(user_id);
		value = comSQL.executeTransact(updateQuery, update_option);	
	
		return value;
	}
}
