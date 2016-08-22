/**
 * 
 */
package nfri.dcpp.properties.model;

import java.util.Vector;

/**
 *
 * @Project : dcpp_web
 * @Title : Graph_Basic_Info.java
 * @Description : �⺻���������� ���� �׷��� �⺻ ������ ó���ϴ� �� Ŭ����
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 24
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @�����̷� : -
 *
 */
public class Graph_Basic_Info {
	//����������ȣ
	private String PL_BI_DATA_NUM = "";
	//X����
	private String PL_BGI_X_AX_UNIT = "";
	//Xȯ��
	private String PL_BGI_X_AX_CAL = "";
	//Y����
	private String PL_BGI_Y_AX_UNIT = "";
	//Yȯ��
	private String PL_BGI_Y_AX_CAL = "";
	//Y����
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
