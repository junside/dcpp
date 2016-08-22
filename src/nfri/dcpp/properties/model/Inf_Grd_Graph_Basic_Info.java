/**
 * 
 */
package nfri.dcpp.properties.model;

import java.util.Vector;

/**
 * @Project: dcpp
 * @Title  : Inf_Grd_Graph_Basic_Info.java
 * @Description : 기본물성정보에 따른 그래프 기본 정보를 처리하는 모델 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2010. 09. 01
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class Inf_Grd_Graph_Basic_Info {
	//등급유력번호
	private String PL_IGBI_DATA_NUM = "";
	//X단위
	private String PL_IGGI_X_AX_UNIT = "";
	//X환산
	private String PL_IGGI_X_AX_CAL = "";
	//Y단위
	private String PL_IGGI_Y_AX_UNIT = "";
	//Y환산
	private String PL_IGGI_Y_AX_CAL = "";
	//Y참고
	private String PL_IGGI_Y_AX_COMM = "";
	
	public String getPL_IGBI_DATA_NUM() {
		return PL_IGBI_DATA_NUM;
	}
	public String getPL_IGGI_X_AX_UNIT() {
		return PL_IGGI_X_AX_UNIT;
	}
	public String getPL_IGGI_X_AX_CAL() {
		return PL_IGGI_X_AX_CAL;
	}
	public String getPL_IGGI_Y_AX_UNIT() {
		return PL_IGGI_Y_AX_UNIT;
	}
	public String getPL_IGGI_Y_AX_CAL() {
		return PL_IGGI_Y_AX_CAL;
	}
	public String getPL_IGGI_Y_AX_COMM() {
		return PL_IGGI_Y_AX_COMM;
	}
	public void setPL_IGBI_DATA_NUM(String pl_igbi_data_num) {
		PL_IGBI_DATA_NUM = pl_igbi_data_num;
	}
	public void setPL_IGGI_X_AX_UNIT(String pl_bgi_x_ax_unit) {
		PL_IGGI_X_AX_UNIT = pl_bgi_x_ax_unit;
	}
	public void setPL_IGGI_X_AX_CAL(String pl_bgi_x_ax_cal) {
		PL_IGGI_X_AX_CAL = pl_bgi_x_ax_cal;
	}
	public void setPL_IGGI_Y_AX_UNIT(String pl_bgi_y_ax_unit) {
		PL_IGGI_Y_AX_UNIT = pl_bgi_y_ax_unit;
	}
	public void setPL_IGGI_Y_AX_CAL(String pl_bgi_y_ax_cal) {
		PL_IGGI_Y_AX_CAL = pl_bgi_y_ax_cal;
	}
	public void setPL_IGGI_Y_AX_COMM(String pl_bgi_y_ax_comm) {
		PL_IGGI_Y_AX_COMM = pl_bgi_y_ax_comm;
	}
	
	public Vector<String> getDataList(){
		Vector<String> value = new Vector<String>();
		value.addElement(this.PL_IGBI_DATA_NUM);
		value.addElement(this.PL_IGGI_X_AX_UNIT);
		value.addElement(this.PL_IGGI_X_AX_CAL);
		value.addElement(this.PL_IGGI_Y_AX_UNIT);
		value.addElement(this.PL_IGGI_Y_AX_CAL);
		value.addElement(this.PL_IGGI_Y_AX_COMM);		
		return value;
	}
}
