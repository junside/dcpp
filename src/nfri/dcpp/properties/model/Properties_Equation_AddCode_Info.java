package nfri.dcpp.properties.model;

/**
 *
 * @Project : dcpp_web
 * @Title : Properties_Equation_AddCode_Info.java
 * @Description : 플라즈마 물성 정보에 대한 물성 반응식 첨부코드를 처리하는 모델 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 05
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class Properties_Equation_AddCode_Info {
	//물성정보 번호
	private String PL_BI_DATA_NUM = "";
	//코드 순서
	private String PL_BEAI_SEQ = "";
	//코드 ID
	private String PL_CI_ID = "";

	/**
	 * @MethodName : getPL_BI_DATA_NUM
	 * @Desc : 플라즈마 물성 반응식 첨부코드 테이블에서 물성정보번호를 가져오는 모델 메소드
	 * @return
	 */
	public String getPL_BI_DATA_NUM() {
		return PL_BI_DATA_NUM;
	}
	
	/**
	 * @MethodName : setPL_BI_DATA_NUM
	 * @Desc : 플라즈마 물성 반응식 첨부코드 테이블에서 물성정보번호를 지정하는 모델 메소드
	 * @param pl_bi_data_num
	 */
	public void setPL_BI_DATA_NUM(String pl_bi_data_num) {
		PL_BI_DATA_NUM = pl_bi_data_num;
	}
	
	/**
	 * @MethodName : getPL_BEAI_SEQ
	 * @Desc : 플라즈마 물성 반응식 첨부코드 테이블에서 코드 순서를 가져오는 모델 메소드
	 * @return
	 */
	public String getPL_BEAI_SEQ() {
		return PL_BEAI_SEQ;
	}
	
	/**
	 * @MethodName : setPL_BEAI_SEQ
	 * @Desc : 플라즈마 물성 반응식 첨부코드 테이블에서 코드순서를 지정하는 모델 메소드
	 * @param pl_beai_seq
	 */
	public void setPL_BEAI_SEQ(String pl_beai_seq) {
		PL_BEAI_SEQ = pl_beai_seq;
	}
	
	/**
	 * @MethodName : getPL_CI_ID
	 * @Desc : 플라즈마 물성 반응식 첨부코드 테이블에서 코드ID를 가져오는 모델 메소드
	 * @return
	 */
	
	public String getPL_CI_ID() {
		return PL_CI_ID;
	}
	
	/**
	 * @MethodName : setPL_CI_ID
	 * @Desc : 플라즈마 물성 반응식 첨부코드 테이블에서 코드ID를 지정하는 모델 메소드
	 * @param pl_ci_id
	 */
	public void setPL_CI_ID(String pl_ci_id) {
		PL_CI_ID = pl_ci_id;
	}
}
