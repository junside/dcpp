/**
 * 
 */
package nfri.dcpp.properties.model;

import java.util.Vector;

/**
 *
 * @Project : dcpp_web
 * @Title : Graph_Data_Info.java
 * @Description : �⺻���������� ���� �׷��� ������ ������ ó���ϴ� �� Ŭ����
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 25
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @�����̷� : -
 *
 */
public class Graph_Data_Info {
	//����������ȣ
	private String PL_BI_DATA_NUM = "";
	//�����͹�ȣ
	private String PL_BGD_SEQ_NUM = "";
	//X��
	private String PL_BGD_X_AX_VAL = "";
	//Y��
	private String PL_BGD_Y_AX_VAL = "";
	//X�� ������
	private String PL_BGD_X_ERR = "";
	//Y�� �ִ� ������
	private String PL_BGD_Y_ERR_MAX = "";
	//Y�� �ּ� ������
	private String PL_BGD_Y_ERR_MIN = "";
	//������
	private String PL_BGD_RATIO = "";
	//�з�
	private String PL_BGD_PRESS = "";
	//��� ������
	private String PL_BGD_BACKUP_DATA = "";
	
	public String getPL_BI_DATA_NUM() {
		return PL_BI_DATA_NUM;
	}
	public String getPL_BGD_SEQ_NUM() {
		return PL_BGD_SEQ_NUM;
	}	
	public String getPL_BGD_X_AX_VAL() {
		return PL_BGD_X_AX_VAL;
	}
	public String getPL_BGD_Y_AX_VAL() {
		return PL_BGD_Y_AX_VAL;
	}
	public String getPL_BGD_X_ERR() {
		return PL_BGD_X_ERR;
	}
	public String getPL_BGD_Y_ERR_MAX() {
		return PL_BGD_Y_ERR_MAX;
	}
	public String getPL_BGD_Y_ERR_MIN() {
		return PL_BGD_Y_ERR_MIN;
	}
	public String getPL_BGD_RATIO() {
		return PL_BGD_RATIO;
	}
	public String getPL_BGD_PRESS() {
		return PL_BGD_PRESS;
	}
	public String getPL_BGD_BACKUP_DATA() {
		return PL_BGD_BACKUP_DATA;
	}
	public void setPL_BI_DATA_NUM(String pl_bi_data_num) {
		PL_BI_DATA_NUM = pl_bi_data_num;
	}
	public void setPL_BGD_SEQ_NUM(String pl_bgd_seq_num) {
		PL_BGD_SEQ_NUM = pl_bgd_seq_num;
	}
	public void setPL_BGD_X_AX_VAL(String pl_bgd_x_ax_val) {
		PL_BGD_X_AX_VAL = pl_bgd_x_ax_val;
	}
	public void setPL_BGD_Y_AX_VAL(String pl_bgd_y_ax_val) {
		PL_BGD_Y_AX_VAL = pl_bgd_y_ax_val;
	}
	public void setPL_BGD_X_ERR(String pl_bgd_x_err) {
		PL_BGD_X_ERR = pl_bgd_x_err;
	}
	public void setPL_BGD_Y_ERR_MAX(String pl_bgd_y_err_max) {
		PL_BGD_Y_ERR_MAX = pl_bgd_y_err_max;
	}
	public void setPL_BGD_Y_ERR_MIN(String pl_bgd_y_err_min) {
		PL_BGD_Y_ERR_MIN = pl_bgd_y_err_min;
	}
	public void setPL_BGD_RATIO(String pl_bgd_ratio) {
		PL_BGD_RATIO = pl_bgd_ratio;
	}
	public void setPL_BGD_PRESS(String pl_bgd_press) {
		PL_BGD_PRESS = pl_bgd_press;
	}
	public void setPL_BGD_BACKUP_DATA(String pl_bgd_backup_data) {
		PL_BGD_BACKUP_DATA = pl_bgd_backup_data;
	}
	
	public Vector<String> getDataList(){
		Vector<String> value = new Vector<String>();
		value.addElement(this.PL_BI_DATA_NUM);
		value.addElement(this.PL_BGD_SEQ_NUM);
		value.addElement(this.PL_BGD_X_AX_VAL);
		value.addElement(this.PL_BGD_Y_AX_VAL);
		value.addElement(this.PL_BGD_X_ERR);
		value.addElement(this.PL_BGD_Y_ERR_MAX);
		value.addElement(this.PL_BGD_Y_ERR_MIN);
		value.addElement(this.PL_BGD_RATIO);
		value.addElement(this.PL_BGD_PRESS);
		value.addElement(this.PL_BGD_BACKUP_DATA);	
		return value;
	}
}
