/**
 * 
 */
package nfri.dcpp.properties.model;

import java.util.Vector;

/**
 * @Project: dcpp
 * @Title  : Inf_Grd_Graph_Data_Info.java
 * @Description : ������� �����׷��� �����͸� ��� Ŭ����
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2010. 09. 15
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class Inf_Grd_Graph_Data_Info {
	//������¹�����ȣ
	private String PL_IGBI_DATA_NUM = "";
	//�����͹�ȣ
	private String PL_IGGD_SEQ_NUM = "";
	//X��
	private String PL_IGGD_X_AX_VAL = "";
	//Y��
	private String PL_IGGD_Y_AX_VAL = "";
	//X�� ������
	private String PL_IGGD_X_ERR = "";
	//Y�� �ִ� ������
	private String PL_IGGD_Y_ERR_MAX = "";
	//Y�� �ּ� ������
	private String PL_IGGD_Y_ERR_MIN = "";
	//������
	private String PL_IGGD_RATIO = "";
	//�з�
	private String PL_IGGD_PRESS = "";
	//��� ������
	private String PL_IGGD_BACKUP_DATA = "";
	
	public String getPL_IGBI_DATA_NUM() {
		return PL_IGBI_DATA_NUM;
	}
	public String getPL_IGGD_SEQ_NUM() {
		return PL_IGGD_SEQ_NUM;
	}	
	public String getPL_IGGD_X_AX_VAL() {
		return PL_IGGD_X_AX_VAL;
	}
	public String getPL_IGGD_Y_AX_VAL() {
		return PL_IGGD_Y_AX_VAL;
	}
	public String getPL_IGGD_X_ERR() {
		return PL_IGGD_X_ERR;
	}
	public String getPL_IGGD_Y_ERR_MAX() {
		return PL_IGGD_Y_ERR_MAX;
	}
	public String getPL_IGGD_Y_ERR_MIN() {
		return PL_IGGD_Y_ERR_MIN;
	}
	public String getPL_IGGD_RATIO() {
		return PL_IGGD_RATIO;
	}
	public String getPL_IGGD_PRESS() {
		return PL_IGGD_PRESS;
	}
	public String getPL_IGGD_BACKUP_DATA() {
		return PL_IGGD_BACKUP_DATA;
	}
	public void setPL_IGBI_DATA_NUM(String pl_IGBI_data_num) {
		PL_IGBI_DATA_NUM = pl_IGBI_data_num;
	}
	public void setPL_IGGD_SEQ_NUM(String pl_IGGD_seq_num) {
		PL_IGGD_SEQ_NUM = pl_IGGD_seq_num;
	}
	public void setPL_IGGD_X_AX_VAL(String pl_IGGD_x_ax_val) {
		PL_IGGD_X_AX_VAL = pl_IGGD_x_ax_val;
	}
	public void setPL_IGGD_Y_AX_VAL(String pl_IGGD_y_ax_val) {
		PL_IGGD_Y_AX_VAL = pl_IGGD_y_ax_val;
	}
	public void setPL_IGGD_X_ERR(String pl_IGGD_x_err) {
		PL_IGGD_X_ERR = pl_IGGD_x_err;
	}
	public void setPL_IGGD_Y_ERR_MAX(String pl_IGGD_y_err_max) {
		PL_IGGD_Y_ERR_MAX = pl_IGGD_y_err_max;
	}
	public void setPL_IGGD_Y_ERR_MIN(String pl_IGGD_y_err_min) {
		PL_IGGD_Y_ERR_MIN = pl_IGGD_y_err_min;
	}
	public void setPL_IGGD_RATIO(String pl_IGGD_ratio) {
		PL_IGGD_RATIO = pl_IGGD_ratio;
	}
	public void setPL_IGGD_PRESS(String pl_IGGD_press) {
		PL_IGGD_PRESS = pl_IGGD_press;
	}
	public void setPL_IGGD_BACKUP_DATA(String pl_IGGD_backup_data) {
		PL_IGGD_BACKUP_DATA = pl_IGGD_backup_data;
	}
	
	public Vector<String> getDataList(){
		Vector<String> value = new Vector<String>();
		value.addElement(this.PL_IGBI_DATA_NUM);
		value.addElement(this.PL_IGGD_SEQ_NUM);
		value.addElement(this.PL_IGGD_X_AX_VAL);
		value.addElement(this.PL_IGGD_Y_AX_VAL);
		value.addElement(this.PL_IGGD_X_ERR);
		value.addElement(this.PL_IGGD_Y_ERR_MAX);
		value.addElement(this.PL_IGGD_Y_ERR_MIN);
		value.addElement(this.PL_IGGD_RATIO);
		value.addElement(this.PL_IGGD_PRESS);
		value.addElement(this.PL_IGGD_BACKUP_DATA);	
		return value;
	}
}
