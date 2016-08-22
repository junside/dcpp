package nfri.dcpp.properties.model;

/**
 *
 * @Project : dcpp_web
 * @Title : Properties_Equation_AddCode_Info.java
 * @Description : �ö�� ���� ������ ���� ���� ������ ÷���ڵ带 ó���ϴ� �� Ŭ����
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 05
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @�����̷� : -
 *
 */
public class Properties_Equation_AddCode_Info {
	//�������� ��ȣ
	private String PL_BI_DATA_NUM = "";
	//�ڵ� ����
	private String PL_BEAI_SEQ = "";
	//�ڵ� ID
	private String PL_CI_ID = "";

	/**
	 * @MethodName : getPL_BI_DATA_NUM
	 * @Desc : �ö�� ���� ������ ÷���ڵ� ���̺��� ����������ȣ�� �������� �� �޼ҵ�
	 * @return
	 */
	public String getPL_BI_DATA_NUM() {
		return PL_BI_DATA_NUM;
	}
	
	/**
	 * @MethodName : setPL_BI_DATA_NUM
	 * @Desc : �ö�� ���� ������ ÷���ڵ� ���̺��� ����������ȣ�� �����ϴ� �� �޼ҵ�
	 * @param pl_bi_data_num
	 */
	public void setPL_BI_DATA_NUM(String pl_bi_data_num) {
		PL_BI_DATA_NUM = pl_bi_data_num;
	}
	
	/**
	 * @MethodName : getPL_BEAI_SEQ
	 * @Desc : �ö�� ���� ������ ÷���ڵ� ���̺��� �ڵ� ������ �������� �� �޼ҵ�
	 * @return
	 */
	public String getPL_BEAI_SEQ() {
		return PL_BEAI_SEQ;
	}
	
	/**
	 * @MethodName : setPL_BEAI_SEQ
	 * @Desc : �ö�� ���� ������ ÷���ڵ� ���̺��� �ڵ������ �����ϴ� �� �޼ҵ�
	 * @param pl_beai_seq
	 */
	public void setPL_BEAI_SEQ(String pl_beai_seq) {
		PL_BEAI_SEQ = pl_beai_seq;
	}
	
	/**
	 * @MethodName : getPL_CI_ID
	 * @Desc : �ö�� ���� ������ ÷���ڵ� ���̺��� �ڵ�ID�� �������� �� �޼ҵ�
	 * @return
	 */
	
	public String getPL_CI_ID() {
		return PL_CI_ID;
	}
	
	/**
	 * @MethodName : setPL_CI_ID
	 * @Desc : �ö�� ���� ������ ÷���ڵ� ���̺��� �ڵ�ID�� �����ϴ� �� �޼ҵ�
	 * @param pl_ci_id
	 */
	public void setPL_CI_ID(String pl_ci_id) {
		PL_CI_ID = pl_ci_id;
	}
}
