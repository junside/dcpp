/**
 * 
 */
package nfri.dcpp.properties.model;

import java.util.Vector;

/**
 * @Project: dcpp
 * @Title  : Fitting_Temp_Info.java
 * @Description : 임시 Fitting 데이터를 담는 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2010. 07. 15
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class Fitting_Temp_Info {
	//Data 순서
	private String PL_FTD_SEQ = "";
	//X값
	private String PL_FTD_X_VAL = "";
	//Y값
	private String PL_FTD_Y_VAL = "";
	//Fitting 구간 번호
	private String PL_FBI_SEQ = "";
	//사용자 ID
	private String PL_UI_ID = "";
	//등급유력번호
	private String PL_IGBI_DATA_NUM = "";
	/**
	 * @return the pL_FTD_SEQ
	 */
	public String getPL_FTD_SEQ() {
		return PL_FTD_SEQ;
	}
	/**
	 * @return the pL_FTD_X_VAL
	 */
	public String getPL_FTD_X_VAL() {
		return PL_FTD_X_VAL;
	}
	/**
	 * @return the pL_FTD_Y_VAL
	 */
	public String getPL_FTD_Y_VAL() {
		return PL_FTD_Y_VAL;
	}
	/**
	 * @return the pL_FBI_SEQ
	 */
	public String getPL_FBI_SEQ() {
		return PL_FBI_SEQ;
	}
	/**
	 * @return the pL_UI_ID
	 */
	public String getPL_UI_ID() {
		return PL_UI_ID;
	}
	/**
	 * @return the pL_IGBI_DATA_NUM
	 */
	public String getPL_IGBI_DATA_NUM() {
		return PL_IGBI_DATA_NUM;
	}
	/**
	 * @param pl_ftd_seq the pL_FTD_SEQ to set
	 */
	public void setPL_FTD_SEQ(String pl_ftd_seq) {
		PL_FTD_SEQ = pl_ftd_seq;
	}
	/**
	 * @param pl_ftd_x_val the pL_FTD_X_VAL to set
	 */
	public void setPL_FTD_X_VAL(String pl_ftd_x_val) {
		PL_FTD_X_VAL = pl_ftd_x_val;
	}
	/**
	 * @param pl_ftd_y_val the pL_FTD_Y_VAL to set
	 */
	public void setPL_FTD_Y_VAL(String pl_ftd_y_val) {
		PL_FTD_Y_VAL = pl_ftd_y_val;
	}
	/**
	 * @param pl_fbi_seq the pL_FBI_SEQ to set
	 */
	public void setPL_FBI_SEQ(String pl_fbi_seq) {
		PL_FBI_SEQ = pl_fbi_seq;
	}
	/**
	 * @param pl_ui_id the pL_UI_ID to set
	 */
	public void setPL_UI_ID(String pl_ui_id) {
		PL_UI_ID = pl_ui_id;
	}
	/**
	 * @param pl_igbi_data_num the pL_IGBI_DATA_NUM to set
	 */
	public void setPL_IGBI_DATA_NUM(String pl_igbi_data_num) {
		PL_IGBI_DATA_NUM = pl_igbi_data_num;
	}
	
	public Vector<String> getDataList(){
		Vector<String> value = new Vector<String>();
		value.addElement(this.PL_FTD_SEQ);
		value.addElement(this.PL_FTD_X_VAL);
		value.addElement(this.PL_FTD_Y_VAL);
		value.addElement(this.PL_FBI_SEQ);
		value.addElement(this.PL_UI_ID);
		value.addElement(this.PL_IGBI_DATA_NUM);		
		return value;
	}
}
