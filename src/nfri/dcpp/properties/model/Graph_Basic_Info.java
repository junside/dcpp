/**
 * 
 */
package nfri.dcpp.properties.model;

import java.util.Vector;

/**
 *
 * @Project : dcpp_web
 * @Title : Graph_Basic_Info.java
 * @Description : 기본물성정보에 따른 그래프 기본 정보를 처리하는 모델 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 24
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class Graph_Basic_Info {
	//물성정보번호
	private String PL_BI_DATA_NUM = "";
	//X단위
	private String PL_BGI_X_AX_UNIT = "";
	//X환산
	private String PL_BGI_X_AX_CAL = "";
	//Y단위
	private String PL_BGI_Y_AX_UNIT = "";
	//Y환산
	private String PL_BGI_Y_AX_CAL = "";
	//Y참고
	private String PL_BGI_Y_AX_COMM = "";
	
	public String getPL_BI_DATA_NUM() {
		return PL_BI_DATA_NUM;
	}
	public String getPL_BGI_X_AX_UNIT() {
		return PL_BGI_X_AX_UNIT;
	}
	public String getPL_BGI_X_AX_CAL() {
		return PL_BGI_X_AX_CAL;
	}
	public String getPL_BGI_Y_AX_UNIT() {
		return PL_BGI_Y_AX_UNIT;
	}
	public String getPL_BGI_Y_AX_CAL() {
		return PL_BGI_Y_AX_CAL;
	}
	public String getPL_BGI_Y_AX_COMM() {
		return PL_BGI_Y_AX_COMM;
	}
	public void setPL_BI_DATA_NUM(String pl_bi_data_num) {
		PL_BI_DATA_NUM = pl_bi_data_num;
	}
	public void setPL_BGI_X_AX_UNIT(String pl_bgi_x_ax_unit) {
		PL_BGI_X_AX_UNIT = pl_bgi_x_ax_unit;
	}
	public void setPL_BGI_X_AX_CAL(String pl_bgi_x_ax_cal) {
		PL_BGI_X_AX_CAL = pl_bgi_x_ax_cal;
	}
	public void setPL_BGI_Y_AX_UNIT(String pl_bgi_y_ax_unit) {
		PL_BGI_Y_AX_UNIT = pl_bgi_y_ax_unit;
	}
	public void setPL_BGI_Y_AX_CAL(String pl_bgi_y_ax_cal) {
		PL_BGI_Y_AX_CAL = pl_bgi_y_ax_cal;
	}
	public void setPL_BGI_Y_AX_COMM(String pl_bgi_y_ax_comm) {
		PL_BGI_Y_AX_COMM = pl_bgi_y_ax_comm;
	}
	
	public Vector<String> getDataList(){
		Vector<String> value = new Vector<String>();
		value.addElement(this.PL_BI_DATA_NUM);
		value.addElement(this.PL_BGI_X_AX_UNIT);
		value.addElement(this.PL_BGI_X_AX_CAL);
		value.addElement(this.PL_BGI_Y_AX_UNIT);
		value.addElement(this.PL_BGI_Y_AX_CAL);
		value.addElement(this.PL_BGI_Y_AX_COMM);		
		return value;
	}
}
