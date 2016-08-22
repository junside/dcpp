package nfri.dcpp.properties.model;

import java.util.Vector;

public class Properties_Save_Info {
	
	//�⺻ ����	
	//����������ȣ
	private String PL_BI_DATA_NUM = "";
	//���
	private String PL_BI_COMMENT = "";
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
	//�Է���
	private String PL_BI_INSERT_USER = "";
	//�Է��÷���
	private String PL_BI_INSERT_FLAG = "";
	//ǥ����
	private String PL_BI_EXPRESSION = "";
	//�Է³�¥
	private String PL_BI_INSERT_DATE = "";
	
	private Vector<?> PL_EQUA_INFO = new Vector<Object>();

	//÷���ڵ� ����
	//�ڵ� ID
	private String PL_CI_ID = "";
	
	public String getPL_BI_DATA_NUM() {
		return PL_BI_DATA_NUM;
	}
	
	public String getPL_BI_COMMENT() {
		return PL_BI_COMMENT;
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
	
	public Vector<?> getPL_EQUA_INFO(){
		return this.PL_EQUA_INFO;
	}
	
	public String getPL_CI_ID() {
		return PL_CI_ID;
	}
	
	public void setPL_BI_DATA_NUM(String pl_bi_data_num) {
		PL_BI_DATA_NUM = pl_bi_data_num;
	}
	
	public void setPL_BI_COMMENT(String pl_bi_comment) {
		PL_BI_COMMENT = pl_bi_comment;
	}
	
	public void setPL_RAI_ARTCL_NUM(String pl_rai_artcl_num) {
		PL_RAI_ARTCL_NUM = pl_rai_artcl_num;
	}
	
	public void setPL_IGBI_DATA_NUM(String pl_igbi_data_num) {
		PL_IGBI_DATA_NUM = pl_igbi_data_num;
	}
	
	public void setPL_BI_TOP_BRANCH(String pl_bsi_top_branch) {
		PL_BI_TOP_BRANCH = pl_bsi_top_branch;
	}
	
	public void setPL_BI_DATA_BRANCH(String pl_bsi_data_branch) {
		PL_BI_DATA_BRANCH = pl_bsi_data_branch;
	}
	
	public void setPL_BI_IMP_CLASS(String pl_bsi_imp_class) {
		PL_BI_IMP_CLASS = pl_bsi_imp_class;
	}
	
	public void setPL_BI_MAIN_PROC(String pl_bsi_main_proc) {
		PL_BI_MAIN_PROC = pl_bsi_main_proc;
	}
	
	public void setPL_BI_SUB_PROC(String pl_bsi_sub_proc) {
		PL_BI_SUB_PROC = pl_bsi_sub_proc;
	}
	
	public void setPL_BI_EXP_THE_REC(String pl_bsi_exp_the_rec) {
		PL_BI_EXP_THE_REC = pl_bsi_exp_the_rec;
	}
	
	public void setPL_EQUA_INFO(Vector<?> pl_equa_info){
		PL_EQUA_INFO = pl_equa_info;
	}
	
	public void setPL_CI_ID(String pl_ci_id) {
		PL_CI_ID = pl_ci_id;
	}

	public String getPL_BI_INSERT_USER() {
		return PL_BI_INSERT_USER;
	}

	public void setPL_BI_INSERT_USER(String pl_bi_insert_user) {
		PL_BI_INSERT_USER = pl_bi_insert_user;
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
