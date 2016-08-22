/**
 * 
 */
package nfri.dcpp.properties.model;

/**
 * @Project: dcpp
 * @Title  : Inf_Grd_Properties_Equation_Info.java
 * @Description : 등급유력 물성 정보에 대한 물성 반응식 정보를 처리하는 모델 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2010. 09. 01
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class Inf_Grd_Properties_Equation_Info {
	//물성정보번호
	private String PL_IGBI_DATA_NUM = "";
	//입자번호
	private String PL_CPBI_ELE_NUM = "";
	//입자순서
	private String PL_IGEI_SEQ  = "";
	//입자이온화
	private String PL_IGEI_CHG_STATE = "";
	//입자전자배치
	private String PL_IGEI_ELC_STATE = "";
	//입자미세구조
	private String PL_IGEI_OTH_STRUC = "";
	
	/**
	 * @MethodName : getPL_IGBI_DATA_NUM
	 * @Desc : 등급유력 물성 반응식 테이블에서 물성정보번호를 가져오는 모델 메소드
	 * @return
	 */
	public String getPL_IGBI_DATA_NUM() {
		return PL_IGBI_DATA_NUM;
	}
	
	/**
	 * @MethodName : getPL_CPBI_ELE_NUM
	 * @Desc : 등급유력 물성 반응식 테이블에서 입자번호를 가져오는 모델 메소드
	 * @return
	 */
	public String getPL_CPBI_ELE_NUM() {
		return PL_CPBI_ELE_NUM;
	}
	
	/**
	 * @MethodName : getPL_IGEI_SEQ
	 * @Desc : 등급유력 물성 반응식 테이블에서 입자순서를 가져오는 모델 메소드
	 * @return
	 */
	public String getPL_IGEI_SEQ() {
		return PL_IGEI_SEQ;
	}
	
	/**
	 * @MethodName : getPL_IGEI_CHG_STATE
	 * @Desc : 등급유력 물성 반응식 테이블에서 입자이온화를 가져오는 모델 메소드
	 * @return
	 */
	public String getPL_IGEI_CHG_STATE() {
		return PL_IGEI_CHG_STATE;
	}
	
	/**
	 * @MethodName : getPL_IGEI_ELC_STATE
	 * @Desc : 등급유력 물성 반응식 테이블에서 입자전자배치를 가져오는 모델 메소드
	 * @return
	 */
	public String getPL_IGEI_ELC_STATE() {
		return PL_IGEI_ELC_STATE;
	}
	
	/**
	 * @MethodName : getPL_IGEI_OTH_STRUC
	 * @Desc : 등급유력 물성 반응식 테이블에서 입자미세구조를 가져오는 모델 메소드
	 * @return
	 */
	public String getPL_IGEI_OTH_STRUC() {
		return PL_IGEI_OTH_STRUC;
	}
	
	/**
	 * @MethodName : setPL_IGBI_DATA_NUM
	 * @Desc : 등급유력 물성 반응식 테이블에 물성정보번호를 지정하는 모델 메소드
	 * @param pl_bi_data_num
	 */
	public void setPL_IGBI_DATA_NUM(String pl_igbi_data_num) {
		PL_IGBI_DATA_NUM = pl_igbi_data_num;
	}
	
	/**
	 * @MethodName : setPL_CPBI_ELE_NUM
	 * @Desc : 등급유력 물성 반응식 테이블에 입자번호를 지정하는 모델 메소드
	 * @param pl_cpbi_ele_num
	 */
	public void setPL_CPBI_ELE_NUM(String pl_cpbi_ele_num) {
		PL_CPBI_ELE_NUM = pl_cpbi_ele_num;
	}
	
	/**
	 * @MethodName : setPL_IGEI_SEQ
	 * @Desc : 등급유력 물성 반응식 테이블에 입자순서를 지정하는 모델 메소드
	 * @param pl_bei_seq
	 */
	public void setPL_IGEI_SEQ(String pl_bei_seq) {
		PL_IGEI_SEQ = pl_bei_seq;
	}
	
	/**
	 * @MethodName : setPL_IGEI_CHG_STATE
	 * @Desc : 등급유력 물성 반응식 테이블에 입자이온화를 지정하는 모델 메소드
	 * @param pl_bei_chg_state
	 */
	public void setPL_IGEI_CHG_STATE(String pl_bei_chg_state) {
		PL_IGEI_CHG_STATE = pl_bei_chg_state;
	}
	
	/**
	 * @MethodName : setPL_IGEI_ELC_STATE
	 * @Desc : 등급유력 물성 반응식 테이블에 입자전자배치를 지정하는 모델 메소드
	 * @param pl_bei_elc_state
	 */
	public void setPL_IGEI_ELC_STATE(String pl_bei_elc_state) {
		PL_IGEI_ELC_STATE = pl_bei_elc_state;
	}
	
	/**
	 * @MethodName : setPL_IGEI_OTH_STRUC
	 * @Desc : 등급유력 물성 반응식 테이블에 입자미세구조를 지정하는 모델 메소드
	 * @param pl_bei_oth_struc
	 */
	public void setPL_IGEI_OTH_STRUC(String pl_igei_oth_struc) {
		PL_IGEI_OTH_STRUC = pl_igei_oth_struc;
	}
}
