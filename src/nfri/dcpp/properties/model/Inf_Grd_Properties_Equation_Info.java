/**
 * 
 */
package nfri.dcpp.properties.model;

/**
 * @Project: dcpp
 * @Title  : Inf_Grd_Properties_Equation_Info.java
 * @Description : ������� ���� ������ ���� ���� ������ ������ ó���ϴ� �� Ŭ����
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2010. 09. 01
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class Inf_Grd_Properties_Equation_Info {
	//����������ȣ
	private String PL_IGBI_DATA_NUM = "";
	//���ڹ�ȣ
	private String PL_CPBI_ELE_NUM = "";
	//���ڼ���
	private String PL_IGEI_SEQ  = "";
	//�����̿�ȭ
	private String PL_IGEI_CHG_STATE = "";
	//�������ڹ�ġ
	private String PL_IGEI_ELC_STATE = "";
	//���ڹ̼�����
	private String PL_IGEI_OTH_STRUC = "";
	
	/**
	 * @MethodName : getPL_IGBI_DATA_NUM
	 * @Desc : ������� ���� ������ ���̺��� ����������ȣ�� �������� �� �޼ҵ�
	 * @return
	 */
	public String getPL_IGBI_DATA_NUM() {
		return PL_IGBI_DATA_NUM;
	}
	
	/**
	 * @MethodName : getPL_CPBI_ELE_NUM
	 * @Desc : ������� ���� ������ ���̺��� ���ڹ�ȣ�� �������� �� �޼ҵ�
	 * @return
	 */
	public String getPL_CPBI_ELE_NUM() {
		return PL_CPBI_ELE_NUM;
	}
	
	/**
	 * @MethodName : getPL_IGEI_SEQ
	 * @Desc : ������� ���� ������ ���̺��� ���ڼ����� �������� �� �޼ҵ�
	 * @return
	 */
	public String getPL_IGEI_SEQ() {
		return PL_IGEI_SEQ;
	}
	
	/**
	 * @MethodName : getPL_IGEI_CHG_STATE
	 * @Desc : ������� ���� ������ ���̺��� �����̿�ȭ�� �������� �� �޼ҵ�
	 * @return
	 */
	public String getPL_IGEI_CHG_STATE() {
		return PL_IGEI_CHG_STATE;
	}
	
	/**
	 * @MethodName : getPL_IGEI_ELC_STATE
	 * @Desc : ������� ���� ������ ���̺��� �������ڹ�ġ�� �������� �� �޼ҵ�
	 * @return
	 */
	public String getPL_IGEI_ELC_STATE() {
		return PL_IGEI_ELC_STATE;
	}
	
	/**
	 * @MethodName : getPL_IGEI_OTH_STRUC
	 * @Desc : ������� ���� ������ ���̺��� ���ڹ̼������� �������� �� �޼ҵ�
	 * @return
	 */
	public String getPL_IGEI_OTH_STRUC() {
		return PL_IGEI_OTH_STRUC;
	}
	
	/**
	 * @MethodName : setPL_IGBI_DATA_NUM
	 * @Desc : ������� ���� ������ ���̺� ����������ȣ�� �����ϴ� �� �޼ҵ�
	 * @param pl_bi_data_num
	 */
	public void setPL_IGBI_DATA_NUM(String pl_igbi_data_num) {
		PL_IGBI_DATA_NUM = pl_igbi_data_num;
	}
	
	/**
	 * @MethodName : setPL_CPBI_ELE_NUM
	 * @Desc : ������� ���� ������ ���̺� ���ڹ�ȣ�� �����ϴ� �� �޼ҵ�
	 * @param pl_cpbi_ele_num
	 */
	public void setPL_CPBI_ELE_NUM(String pl_cpbi_ele_num) {
		PL_CPBI_ELE_NUM = pl_cpbi_ele_num;
	}
	
	/**
	 * @MethodName : setPL_IGEI_SEQ
	 * @Desc : ������� ���� ������ ���̺� ���ڼ����� �����ϴ� �� �޼ҵ�
	 * @param pl_bei_seq
	 */
	public void setPL_IGEI_SEQ(String pl_bei_seq) {
		PL_IGEI_SEQ = pl_bei_seq;
	}
	
	/**
	 * @MethodName : setPL_IGEI_CHG_STATE
	 * @Desc : ������� ���� ������ ���̺� �����̿�ȭ�� �����ϴ� �� �޼ҵ�
	 * @param pl_bei_chg_state
	 */
	public void setPL_IGEI_CHG_STATE(String pl_bei_chg_state) {
		PL_IGEI_CHG_STATE = pl_bei_chg_state;
	}
	
	/**
	 * @MethodName : setPL_IGEI_ELC_STATE
	 * @Desc : ������� ���� ������ ���̺� �������ڹ�ġ�� �����ϴ� �� �޼ҵ�
	 * @param pl_bei_elc_state
	 */
	public void setPL_IGEI_ELC_STATE(String pl_bei_elc_state) {
		PL_IGEI_ELC_STATE = pl_bei_elc_state;
	}
	
	/**
	 * @MethodName : setPL_IGEI_OTH_STRUC
	 * @Desc : ������� ���� ������ ���̺� ���ڹ̼������� �����ϴ� �� �޼ҵ�
	 * @param pl_bei_oth_struc
	 */
	public void setPL_IGEI_OTH_STRUC(String pl_igei_oth_struc) {
		PL_IGEI_OTH_STRUC = pl_igei_oth_struc;
	}
}
