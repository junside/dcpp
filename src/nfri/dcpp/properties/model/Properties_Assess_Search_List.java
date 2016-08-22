/**
 * 
 */
package nfri.dcpp.properties.model;

/**
 * @Project: dcpp
 * @Title  : Properties_Assess_Search_List.java
 * @Description : 평가에 필요한 정보를 조회할 때 데이터를 담는 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2010. 06. 07
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class Properties_Assess_Search_List {
	//물성정보번호
	private String PL_BI_DATA_NUM = "";
	//참고문헌 번호
	private String PL_RAI_ARTCL_NUM = "";
	//참고문헌저자명
	private String PL_RAI_ARTCL_AUTH_NAME = "";
	//검증구분
	private String PL_BI_EXP_THE_REC = "";
	//데이터분류
	private String PL_BI_DATA_BRANCH = "";
	//물성데이터 표시명
	private String PL_BI_MGMT_DATA_NUM = "";
	
	/**
	 * @return the pL_BI_DATA_NUM
	 */
	public String getPL_BI_DATA_NUM() {
		return PL_BI_DATA_NUM;
	}	
	/**
	 * @return the pL_RAI_ARTCL_NUM
	 */
	public String getPL_RAI_ARTCL_NUM() {
		return PL_RAI_ARTCL_NUM;
	}
	/**
	 * @return the pL_RAI_ARTCL_AUTH_NAME
	 */
	public String getPL_RAI_ARTCL_AUTH_NAME() {
		return PL_RAI_ARTCL_AUTH_NAME;
	}
	/**
	 * @return the pL_BI_EXP_THE_REC
	 */
	public String getPL_BI_EXP_THE_REC() {
		return PL_BI_EXP_THE_REC;
	}
	/**
	 * @return the pL_BI_DATA_BRANCH
	 */
	public String getPL_BI_DATA_BRANCH() {
		return PL_BI_DATA_BRANCH;
	}
	/**
	 * @return the pL_BI_MGMT_DATA_NUM
	 */
	public String getPL_BI_MGMT_DATA_NUM() {
		return PL_BI_MGMT_DATA_NUM;
	}
	/**
	 * @param pl_bi_data_num the pL_BI_DATA_NUM to set
	 */
	public void setPL_BI_DATA_NUM(String pl_bi_data_num) {
		PL_BI_DATA_NUM = pl_bi_data_num;
	}
	/**
	 * @param pl_rai_artcl_num the pL_RAI_ARTCL_NUM to set
	 */
	public void setPL_RAI_ARTCL_NUM(String pl_rai_artcl_num) {
		PL_RAI_ARTCL_NUM = pl_rai_artcl_num;
	}
	/**
	 * @param pl_rai_artcl_auth_name the pL_RAI_ARTCL_AUTH_NAME to set
	 */
	public void setPL_RAI_ARTCL_AUTH_NAME(String pl_rai_artcl_auth_name) {
		PL_RAI_ARTCL_AUTH_NAME = pl_rai_artcl_auth_name;
	}
	/**
	 * @param pl_bi_exp_the_rec the pL_BI_EXP_THE_REC to set
	 */
	public void setPL_BI_EXP_THE_REC(String pl_bi_exp_the_rec) {
		PL_BI_EXP_THE_REC = pl_bi_exp_the_rec;
	}
	/**
	 * @param pl_bi_data_branch the pL_BI_DATA_BRANCH to set
	 */
	public void setPL_BI_DATA_BRANCH(String pl_bi_data_branch) {
		PL_BI_DATA_BRANCH = pl_bi_data_branch;
	}
	/**
	 * @param pl_bi_mgmt_data_num the pL_BI_MGMT_DATA_NUM to set
	 */
	public void setPL_BI_MGMT_DATA_NUM(String pl_bi_mgmt_data_num) {
		PL_BI_MGMT_DATA_NUM = pl_bi_mgmt_data_num;
	}
}
