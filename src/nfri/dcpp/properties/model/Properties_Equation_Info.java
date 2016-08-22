package nfri.dcpp.properties.model;

/**
 *
 * @Project : dcpp_web
 * @Title : Properties_Equation_Info.java
 * @Description : �ö�� ���� ������ ���� ���� ������ ������ ó���ϴ� �� Ŭ����
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 05
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @�����̷� : -
 *
 */
public class Properties_Equation_Info {
	//����������ȣ
	private String PL_BI_DATA_NUM = "";
	//���ڹ�ȣ
	private String PL_CPBI_ELE_NUM = "";
	//���ڼ���
	private String PL_BEI_SEQ  = "";
	//�����̿�ȭ
	private String PL_BEI_CHG_STATE = "";
	//�������ڹ�ġ
	private String PL_BEI_ELC_STATE = "";
	//���ڹ̼�����
	private String PL_BEI_OTH_STRUC = "";
	
	private String PL_CI_ID = "";
	/**
	 * @MethodName : getPL_BI_DATA_NUM
	 * @Desc : �ö�� ���� ������ ���̺��� ����������ȣ�� �������� �� �޼ҵ�
	 * @return
	 */
	public String getPL_BI_DATA_NUM() {
		return PL_BI_DATA_NUM;
	}
	
	/**
	 * @MethodName : getPL_CPBI_ELE_NUM
	 * @Desc : �ö�� ���� ������ ���̺��� ���ڹ�ȣ�� �������� �� �޼ҵ�
	 * @return
	 */
	public String getPL_CPBI_ELE_NUM() {
		return PL_CPBI_ELE_NUM;
	}
	
	/**
	 * @MethodName : getPL_BEI_SEQ
	 * @Desc : �ö�� ���� ������ ���̺��� ���ڼ����� �������� �� �޼ҵ�
	 * @return
	 */
	public String getPL_BEI_SEQ() {
		return PL_BEI_SEQ;
	}
	
	/**
	 * @MethodName : getPL_BEI_CHG_STATE
	 * @Desc : �ö�� ���� ������ ���̺��� �����̿�ȭ�� �������� �� �޼ҵ�
	 * @return
	 */
	public String getPL_BEI_CHG_STATE() {
		return PL_BEI_CHG_STATE;
	}
	
	/**
	 * @MethodName : getPL_BEI_ELC_STATE
	 * @Desc : �ö�� ���� ������ ���̺��� �������ڹ�ġ�� �������� �� �޼ҵ�
	 * @return
	 */
	public String getPL_BEI_ELC_STATE() {
		return PL_BEI_ELC_STATE;
	}
	
	/**
	 * @MethodName : getPL_BEI_OTH_STRUC
	 * @Desc : �ö�� ���� ������ ���̺��� ���ڹ̼������� �������� �� �޼ҵ�
	 * @return
	 */
	public String getPL_BEI_OTH_STRUC() {
		return PL_BEI_OTH_STRUC;
	}
	
	/**
	 * @MethodName : setPL_BI_DATA_NUM
	 * @Desc : �ö�� ���� ������ ���̺� ����������ȣ�� �����ϴ� �� �޼ҵ�
	 * @param pl_bi_data_num
	 */
	public void setPL_BI_DATA_NUM(String pl_bi_data_num) {
		PL_BI_DATA_NUM = pl_bi_data_num;
	}
	
	/**
	 * @MethodName : setPL_CPBI_ELE_NUM
	 * @Desc : �ö�� ���� ������ ���̺� ���ڹ�ȣ�� �����ϴ� �� �޼ҵ�
	 * @param pl_cpbi_ele_num
	 */
	public void setPL_CPBI_ELE_NUM(String pl_cpbi_ele_num) {
		PL_CPBI_ELE_NUM = pl_cpbi_ele_num;
	}
	
	/**
	 * @MethodName : setPL_BEI_SEQ
	 * @Desc : �ö�� ���� ������ ���̺� ���ڼ����� �����ϴ� �� �޼ҵ�
	 * @param pl_bei_seq
	 */
	public void setPL_BEI_SEQ(String pl_bei_seq) {
		PL_BEI_SEQ = pl_bei_seq;
	}
	
	/**
	 * @MethodName : setPL_BEI_CHG_STATE
	 * @Desc : �ö�� ���� ������ ���̺� �����̿�ȭ�� �����ϴ� �� �޼ҵ�
	 * @param pl_bei_chg_state
	 */
	public void setPL_BEI_CHG_STATE(String pl_bei_chg_state) {
		PL_BEI_CHG_STATE = pl_bei_chg_state;
	}
	
	/**
	 * @MethodName : setPL_BEI_ELC_STATE
	 * @Desc : �ö�� ���� ������ ���̺� �������ڹ�ġ�� �����ϴ� �� �޼ҵ�
	 * @param pl_bei_elc_state
	 */
	public void setPL_BEI_ELC_STATE(String pl_bei_elc_state) {
		PL_BEI_ELC_STATE = pl_bei_elc_state;
	}
	
	/**
	 * @MethodName : setPL_BEI_OTH_STRUC
	 * @Desc : �ö�� ���� ������ ���̺� ���ڹ̼������� �����ϴ� �� �޼ҵ�
	 * @param pl_bei_oth_struc
	 */
	public void setPL_BEI_OTH_STRUC(String pl_bei_oth_struc) {
		PL_BEI_OTH_STRUC = pl_bei_oth_struc;
	}

	/**
	 * @param pL_CI_ID the pL_CI_ID to set
	 */
	public void setPL_CI_ID(String pL_CI_ID) {
		PL_CI_ID = pL_CI_ID;
	}

	/**
	 * @return the pL_CI_ID
	 */
	public String getPL_CI_ID() {
		return PL_CI_ID;
	}
}
