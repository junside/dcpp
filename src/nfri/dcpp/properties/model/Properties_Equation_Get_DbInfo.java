package nfri.dcpp.properties.model;

/**
 *
 * @Project : dcpp_web
 * @Title : Properties_Equation_Get_DbInfo.java
 * @Description : 반응식 정보와 추가정보(첨부코드 등)를 DB에서 가져올때 사용하는 모델 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 04. 04
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class Properties_Equation_Get_DbInfo {
	//물성정보번호
	private String PL_BI_DATA_NUM = "";
	//입자번호
	private String PL_CPBI_ELE_NUM = "";
	//입자순서
	private String PL_BEI_SEQ  = "";
	//입자이온화
	private String PL_BEI_CHG_STATE = "";
	//입자전자배치
	private String PL_BEI_ELC_STATE = "";
	//입자미세구조
	private String PL_BEI_OTH_STRUC = "";
	//입자 심볼
	private String PL_CPBI_ELE_SYMBOL = "";
	//첨부코드 ID
	private String PL_CI_ID = "";
	
	/**
	 * @MethodName : getPL_BI_DATA_NUM
	 * @Desc : 플라즈마 물성 반응식 테이블에서 물성정보번호를 가져오는 모델 메소드
	 * @return
	 */
	public String getPL_BI_DATA_NUM() {
		return PL_BI_DATA_NUM;
	}
	
	/**
	 * @MethodName : getPL_CPBI_ELE_NUM
	 * @Desc : 플라즈마 물성 반응식 테이블에서 입자번호를 가져오는 모델 메소드
	 * @return
	 */
	public String getPL_CPBI_ELE_NUM() {
		return PL_CPBI_ELE_NUM;
	}
	
	/**
	 * @MethodName : getPL_BEI_SEQ
	 * @Desc : 플라즈마 물성 반응식 테이블에서 입자순서를 가져오는 모델 메소드
	 * @return
	 */
	public String getPL_BEI_SEQ() {
		return PL_BEI_SEQ;
	}
	
	/**
	 * @MethodName : getPL_BEI_CHG_STATE
	 * @Desc : 플라즈마 물성 반응식 테이블에서 입자이온화를 가져오는 모델 메소드
	 * @return
	 */
	public String getPL_BEI_CHG_STATE() {
		return PL_BEI_CHG_STATE;
	}
	
	/**
	 * @MethodName : getPL_BEI_ELC_STATE
	 * @Desc : 플라즈마 물성 반응식 테이블에서 입자전자배치를 가져오는 모델 메소드
	 * @return
	 */
	public String getPL_BEI_ELC_STATE() {
		return PL_BEI_ELC_STATE;
	}
	
	/**
	 * @MethodName : getPL_BEI_OTH_STRUC
	 * @Desc : 플라즈마 물성 반응식 테이블에서 입자미세구조를 가져오는 모델 메소드
	 * @return
	 */
	public String getPL_BEI_OTH_STRUC() {
		return PL_BEI_OTH_STRUC;
	}
	

	public String getPL_CPBI_ELE_SYMBOL() {
		return PL_CPBI_ELE_SYMBOL;
	}

	public String getPL_CI_ID() {
		return PL_CI_ID;
	}
	
	/**
	 * @MethodName : setPL_BI_DATA_NUM
	 * @Desc : 플라즈마 물성 반응식 테이블에 물성정보번호를 지정하는 모델 메소드
	 * @param pl_bi_data_num
	 */
	public void setPL_BI_DATA_NUM(String pl_bi_data_num) {
		PL_BI_DATA_NUM = pl_bi_data_num;
	}
	
	/**
	 * @MethodName : setPL_CPBI_ELE_NUM
	 * @Desc : 플라즈마 물성 반응식 테이블에 입자번호를 지정하는 모델 메소드
	 * @param pl_cpbi_ele_num
	 */
	public void setPL_CPBI_ELE_NUM(String pl_cpbi_ele_num) {
		PL_CPBI_ELE_NUM = pl_cpbi_ele_num;
	}
	
	/**
	 * @MethodName : setPL_BEI_SEQ
	 * @Desc : 플라즈마 물성 반응식 테이블에 입자순서를 지정하는 모델 메소드
	 * @param pl_bei_seq
	 */
	public void setPL_BEI_SEQ(String pl_bei_seq) {
		PL_BEI_SEQ = pl_bei_seq;
	}
	
	/**
	 * @MethodName : setPL_BEI_CHG_STATE
	 * @Desc : 플라즈마 물성 반응식 테이블에 입자이온화를 지정하는 모델 메소드
	 * @param pl_bei_chg_state
	 */
	public void setPL_BEI_CHG_STATE(String pl_bei_chg_state) {
		PL_BEI_CHG_STATE = pl_bei_chg_state;
	}
	
	/**
	 * @MethodName : setPL_BEI_ELC_STATE
	 * @Desc : 플라즈마 물성 반응식 테이블에 입자전자배치를 지정하는 모델 메소드
	 * @param pl_bei_elc_state
	 */
	public void setPL_BEI_ELC_STATE(String pl_bei_elc_state) {
		PL_BEI_ELC_STATE = pl_bei_elc_state;
	}
	
	/**
	 * @MethodName : setPL_BEI_OTH_STRUC
	 * @Desc : 플라즈마 물성 반응식 테이블에 입자미세구조를 지정하는 모델 메소드
	 * @param pl_bei_oth_struc
	 */
	public void setPL_BEI_OTH_STRUC(String pl_bei_oth_struc) {
		PL_BEI_OTH_STRUC = pl_bei_oth_struc;
	}

	public void setPL_CPBI_ELE_SYMBOL(String pl_cpbi_ele_symbol) {
		PL_CPBI_ELE_SYMBOL = pl_cpbi_ele_symbol;
	}

	public void setPL_CI_ID(String pl_ci_id) {
		PL_CI_ID = pl_ci_id;
	}

}
