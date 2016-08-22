package nfri.dcpp.properties.model;

import java.util.Vector;


/**
 *
 * @Project : dcpp_web
 * @Title : Properties_Basic.java
 * @Description : �ö�� ���� �⺻ ������ ó���ϴ� �� Ŭ����
 * @Author : JUNSIDE
 * @Date : 2008. 11. 10
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @�����̷� : -
 *
 */
public class Properties_Basic_Info {
	//����������ȣ
	private String PL_BI_DATA_NUM = "";
	//�������ȣ
	private String PL_RAI_ARTCL_NUM = "";
	//������¹�ȣ
	private String PL_IGBI_DATA_NUM = "";
	//��з�
	private String PL_BI_TOP_BRANCH = "";
	//�����ͺз�
	private String PL_BI_DATA_BRANCH = "";
	//�浹���
	private String PL_BI_IMP_CLASS = "";
	//�����μ���
	private String PL_BI_MAIN_PROC = "";
	//�����μ���
	private String PL_BI_SUB_PROC = "";
	//��������
	private String PL_BI_EXP_THE_REC = "";
	//���
	private String PL_BI_COMMENT = "";
	//�Է���
	private String PL_BI_INSERT_USER = "";
	//�Է� �÷���
	private String PL_BI_INSERT_FLAG = "";
	//ǥ����
	private String PL_BI_EXPRESSION = "";
	//���ο� ������ȣ(park2001a ����)
	private String PL_BI_MGMT_DATA_NUM = "";
	//�Է³�¥
	private String PL_BI_INSERT_DATE = "";
	//�׽�Ʈ�� ���յ� ǥ������ ��� ���� 
	private String PL_BI_COMB_EXP = "";
	
	
	/**
	 * @return the pL_BI_COMB_EXP
	 */
	public String getPL_BI_COMB_EXP() {
		return PL_BI_COMB_EXP;
	}

	/**
	 * @param pL_BI_COMB_EXP the pL_BI_COMB_EXP to set
	 */
	public void setPL_BI_COMB_EXP(String pL_BI_COMB_EXP) {
		PL_BI_COMB_EXP = pL_BI_COMB_EXP;
	}

	public Vector<String> getDataList(){
		Vector<String> value = new Vector<String>();
		value.addElement(this.PL_BI_DATA_NUM);
		value.addElement(this.PL_RAI_ARTCL_NUM);
		value.addElement(this.PL_IGBI_DATA_NUM);
		value.addElement(this.PL_BI_TOP_BRANCH);
		value.addElement(this.PL_BI_DATA_BRANCH);	
		value.addElement(this.PL_BI_IMP_CLASS);	
		value.addElement(this.PL_BI_MAIN_PROC);	
		value.addElement(this.PL_BI_SUB_PROC);	
		value.addElement(this.PL_BI_EXP_THE_REC);	
		value.addElement(this.PL_BI_COMMENT);	
		value.addElement(this.PL_BI_INSERT_USER);
		value.addElement(this.PL_BI_INSERT_FLAG);
		value.addElement(this.PL_BI_EXPRESSION);
		value.addElement(this.PL_BI_MGMT_DATA_NUM);
		value.addElement(this.PL_BI_INSERT_DATE);
		return value;
	}

	public void setPL_BI_DATA_NUM(String pl_bi_data_num) {
		PL_BI_DATA_NUM = pl_bi_data_num;
	}

	public void setPL_RAI_ARTCL_NUM(String pl_rai_artcl_num) {
		PL_RAI_ARTCL_NUM = pl_rai_artcl_num;
	}

	public void setPL_IGBI_DATA_NUM(String pl_igbi_data_num) {
		PL_IGBI_DATA_NUM = pl_igbi_data_num;
	}

	public void setPL_BI_TOP_BRANCH(String pl_bi_top_branch) {
		PL_BI_TOP_BRANCH = pl_bi_top_branch;
	}

	public void setPL_BI_DATA_BRANCH(String pl_bi_data_branch) {
		PL_BI_DATA_BRANCH = pl_bi_data_branch;
	}

	public void setPL_BI_IMP_CLASS(String pl_bi_imp_class) {
		PL_BI_IMP_CLASS = pl_bi_imp_class;
	}

	public void setPL_BI_MAIN_PROC(String pl_bi_main_proc) {
		PL_BI_MAIN_PROC = pl_bi_main_proc;
	}

	public void setPL_BI_SUB_PROC(String pl_bi_sub_proc) {
		PL_BI_SUB_PROC = pl_bi_sub_proc;
	}

	public void setPL_BI_EXP_THE_REC(String pl_bi_exp_the_rec) {
		PL_BI_EXP_THE_REC = pl_bi_exp_the_rec;
	}

	public void setPL_BI_COMMENT(String pl_bi_comment) {
		PL_BI_COMMENT = pl_bi_comment;
	}

	public void setPL_BI_INSERT_USER(String pl_bi_insert_user) {
		PL_BI_INSERT_USER = pl_bi_insert_user;
	}

	public String getPL_BI_DATA_NUM() {
		return PL_BI_DATA_NUM;
	}

	public String getPL_RAI_ARTCL_NUM() {
		return PL_RAI_ARTCL_NUM;
	}

	public String getPL_IGBI_DATA_NUM() {
		return PL_IGBI_DATA_NUM;
	}

	public String getPL_BI_TOP_BRANCH() {
		return PL_BI_TOP_BRANCH;
	}

	public String getPL_BI_DATA_BRANCH() {
		return PL_BI_DATA_BRANCH;
	}

	public String getPL_BI_IMP_CLASS() {
		return PL_BI_IMP_CLASS;
	}

	public String getPL_BI_MAIN_PROC() {
		return PL_BI_MAIN_PROC;
	}

	public String getPL_BI_SUB_PROC() {
		return PL_BI_SUB_PROC;
	}

	public String getPL_BI_EXP_THE_REC() {
		return PL_BI_EXP_THE_REC;
	}

	public String getPL_BI_COMMENT() {
		return PL_BI_COMMENT;
	}

	public String getPL_BI_INSERT_USER() {
		return PL_BI_INSERT_USER;
	}

	public String getPL_BI_INSERT_FLAG() {
		return PL_BI_INSERT_FLAG;
	}

	public void setPL_BI_INSERT_FLAG(String pl_bi_insert_flag) {
		PL_BI_INSERT_FLAG = pl_bi_insert_flag;
	}

	public String getPL_BI_EXPRESSION() {
		return PL_BI_EXPRESSION;
	}

	public void setPL_BI_EXPRESSION(String pl_bi_expression) {
		PL_BI_EXPRESSION = pl_bi_expression;
	}

	public String getPL_BI_MGMT_DATA_NUM() {
		return PL_BI_MGMT_DATA_NUM;
	}

	public void setPL_BI_MGMT_DATA_NUM(String pl_bi_mgmt_data_num) {
		PL_BI_MGMT_DATA_NUM = pl_bi_mgmt_data_num;
	}

	/**
	 * @param pL_BI_INSERT_DATE the pL_BI_INSERT_DATE to set
	 */
	public void setPL_BI_INSERT_DATE(String pL_BI_INSERT_DATE) {
		PL_BI_INSERT_DATE = pL_BI_INSERT_DATE;
	}

	/**
	 * @return the pL_BI_INSERT_DATE
	 */
	public String getPL_BI_INSERT_DATE() {
		return PL_BI_INSERT_DATE;
	}

}
