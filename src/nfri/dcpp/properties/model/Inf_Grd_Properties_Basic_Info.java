/**
 * 
 */
package nfri.dcpp.properties.model;

/**
 * @Project: dcpp
 * @Title  : Inf_Grd_Properties_Basic_Info.java
 * @Description : 등급유력 물성 기본 정보를 담는 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2010. 09. 01
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class Inf_Grd_Properties_Basic_Info {
	//등급유력번호
	private String PL_IGBI_DATA_NUM = "";
	//등급유력 물성정보 주프로세스
	private String PL_IGBI_MAIN_PROC = "";
	//등급유력 물성정보 부프로세스
	private String PL_IGBI_SUB_PROC = "";
	//등급유력 물성정보 표현식
	private String PL_IGBI_EXPRESSION = "";
	//등급유력 물성정보 생성자 ID
	private String PL_UI_ID = "";
	//물성정보번호 리스트
	private String PL_IGBI_DATA_NUM_LIST = "";
	//등급유력 물성정보 입력플래그
	private String PL_IGBI_INSERT_FLAG = "";
	//등급유력 물성정보 충돌방식
	private String PL_IGBI_IMP_CLASS = "";
	//물성정보번호 개수
	private String PL_IGBI_DATA_NUM_CNT = "";
	//물성관리번호 리스트
	private String PL_IGBI_MGMT_NUM_LIST = "";
	/**
	 * @return the pL_IGBI_MGMT_NUM_LIST
	 */
	public String getPL_IGBI_MGMT_NUM_LIST() {
		return PL_IGBI_MGMT_NUM_LIST;
	}
	/**
	 * @param pLIGBIMGMTNUMLIST the pL_IGBI_MGMT_NUM_LIST to set
	 */
	public void setPL_IGBI_MGMT_NUM_LIST(String pLIGBIMGMTNUMLIST) {
		PL_IGBI_MGMT_NUM_LIST = pLIGBIMGMTNUMLIST;
	}
	/**
	 * @return the pL_IGBI_REF_ARTICLE_LIST
	 */
	public String getPL_IGBI_REF_ARTICLE_LIST() {
		return PL_IGBI_REF_ARTICLE_LIST;
	}
	/**
	 * @param pLIGBIREFARTICLELIST the pL_IGBI_REF_ARTICLE_LIST to set
	 */
	public void setPL_IGBI_REF_ARTICLE_LIST(String pLIGBIREFARTICLELIST) {
		PL_IGBI_REF_ARTICLE_LIST = pLIGBIREFARTICLELIST;
	}
	//물성참고번호 리스트
	private String PL_IGBI_REF_ARTICLE_LIST = "";
	
	/**
	 * @return the pL_IGBI_DATA_NUM
	 */
	public String getPL_IGBI_DATA_NUM() {
		return PL_IGBI_DATA_NUM;
	}
	/**
	 * @return the pL_IGBI_MAIN_PROC
	 */
	public String getPL_IGBI_MAIN_PROC() {
		return PL_IGBI_MAIN_PROC;
	}
	/**
	 * @return the pL_IGBI_SUB_PROC
	 */
	public String getPL_IGBI_SUB_PROC() {
		return PL_IGBI_SUB_PROC;
	}
	/**
	 * @return the pL_IGBI_EXPRESSION
	 */
	public String getPL_IGBI_EXPRESSION() {
		return PL_IGBI_EXPRESSION;
	}
	/**
	 * @return the pL_UI_ID
	 */
	public String getPL_UI_ID() {
		return PL_UI_ID;
	}
	/**
	 * @return the pL_IGBI_DATA_NUM_LIST
	 */
	public String getPL_IGBI_DATA_NUM_LIST() {
		return PL_IGBI_DATA_NUM_LIST;
	}
	/**
	 * @return the pL_IGBI_INSERT_FLAG
	 */
	public String getPL_IGBI_INSERT_FLAG() {
		return PL_IGBI_INSERT_FLAG;
	}
	/**
	 * @return the pL_IGBI_IMP_CLASS
	 */
	public String getPL_IGBI_IMP_CLASS() {
		return PL_IGBI_IMP_CLASS;
	}
	/**
	 * @return the pL_IGBI_DATA_NUM_CNT
	 */
	public String getPL_IGBI_DATA_NUM_CNT() {
		return PL_IGBI_DATA_NUM_CNT;
	}
	
	/**
	 * @param pl_igbi_data_num the pL_IGBI_DATA_NUM to set
	 */
	public void setPL_IGBI_DATA_NUM(String pl_igbi_data_num) {
		PL_IGBI_DATA_NUM = pl_igbi_data_num;
	}
	/**
	 * @param pl_igbi_main_proc the pL_IGBI_MAIN_PROC to set
	 */
	public void setPL_IGBI_MAIN_PROC(String pl_igbi_main_proc) {
		PL_IGBI_MAIN_PROC = pl_igbi_main_proc;
	}
	/**
	 * @param pl_igbi_sub_proc the pL_IGBI_SUB_PROC to set
	 */
	public void setPL_IGBI_SUB_PROC(String pl_igbi_sub_proc) {
		PL_IGBI_SUB_PROC = pl_igbi_sub_proc;
	}
	/**
	 * @param pl_igbi_expression the pL_IGBI_EXPRESSION to set
	 */
	public void setPL_IGBI_EXPRESSION(String pl_igbi_expression) {
		PL_IGBI_EXPRESSION = pl_igbi_expression;
	}
	/**
	 * @param pl_ui_id the pL_UI_ID to set
	 */
	public void setPL_UI_ID(String pl_ui_id) {
		PL_UI_ID = pl_ui_id;
	}
	/**
	 * @param pl_igbi_data_num_list the pL_IGBI_DATA_NUM_LIST to set
	 */
	public void setPL_IGBI_DATA_NUM_LIST(String pl_igbi_data_num_list) {
		PL_IGBI_DATA_NUM_LIST = pl_igbi_data_num_list;
	}
	/**
	 * @param pL_IGBI_INSERT_FLAG the pL_IGBI_INSERT_FLAG to set
	 */
	public void setPL_IGBI_INSERT_FLAG(String pL_IGBI_INSERT_FLAG) {
		PL_IGBI_INSERT_FLAG = pL_IGBI_INSERT_FLAG;
	}
	/**
	 * @param pL_IGBI_IMP_CLASS the pL_IGBI_IMP_CLASS to set
	 */
	public void setPL_IGBI_IMP_CLASS(String pL_IGBI_IMP_CLASS) {
		PL_IGBI_IMP_CLASS = pL_IGBI_IMP_CLASS;
	}
	/**
	 * @param pL_IGBI_DATA_NUM_CNT the pL_IGBI_DATA_NUM_CNT to set
	 */
	public void setPL_IGBI_DATA_NUM_CNT(String pL_IGBI_DATA_NUM_CNT) {
		PL_IGBI_DATA_NUM_CNT = pL_IGBI_DATA_NUM_CNT;
	}
		
}
