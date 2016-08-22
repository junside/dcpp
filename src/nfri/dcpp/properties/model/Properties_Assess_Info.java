package nfri.dcpp.properties.model;

/**
 *
 * @Project : dcpp_web
 * @Title : Properties_Assess_Info.java
 * @Description : 물성 정보에 대한 평가 정보를 담는 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 06. 16
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class Properties_Assess_Info {
	
	//물성 정보 번호
	private String PL_BI_DATA_NUM = "";
	//측정 대상
	private String PL_PEI_1STEP = "";
	//측정 방법
	private String PL_PEI_2STEP = "";
	//소급성
	private String PL_PEI_3STEP = "";
	//정확도
	private String PL_PEI_4STEP = "";
	//재현성
	private String PL_PEI_5STEP = "";
	//일관성
	private String PL_PEI_6STEP = "";
	//수집평가(결과)
	private String PL_PEI_PRIM_EVAL = "";
	//측정방식
	private String PL_PEI_METHOD = "";
	//근거
	private String PL_PEI_SCIENT_BASIS = "";
	//한계
	private String PL_PEI_SCIENT_LIMIT = "";
	//변수
	private String PL_PEI_PRIMARY_FACT = "";
	//분석
	private String PL_PEI_DATA_ANALY = "";
	//측정값
	private String PL_PEI_MEASUREMENT = "";
	//직접 확인
	private String PL_PEI_DIRECT = "";
	//간접확인
	private String PL_PEI_INDIRECT = "";
	//평가결과
	private String PL_PEI_FINAL_FLAG = "";
	//평가자
	private String PL_PEI_ASSESS_USER = "";
	//평가일
	private String PL_PEI_ASSESS_DATE = "";
	//데이터분류값
	private String PL_PEI_DATA_BRANCH = "";		
	//프로세스값
	private String PL_PEI_PROCESS = "";		
	//검증구분값
	private String PL_PEI_EXP_THE_REC = "";		
	//X단위값
	private String PL_PEI_X_UNIT = "";		
	//Y단위값
	private String PL_PEI_Y_UNIT = "";	
	//표현식
	private String PL_PEI_EXPRESS = "";
	
	public void setPL_BI_DATA_NUM(String pl_bi_data_num) {
		PL_BI_DATA_NUM = pl_bi_data_num;
	}
	public void setPL_PEI_1STEP(String pl_pei_1step) {
		PL_PEI_1STEP = pl_pei_1step;
	}
	public void setPL_PEI_2STEP(String pl_pei_2step) {
		PL_PEI_2STEP = pl_pei_2step;
	}
	public void setPL_PEI_3STEP(String pl_pei_3step) {
		PL_PEI_3STEP = pl_pei_3step;
	}
	public void setPL_PEI_4STEP(String pl_pei_4step) {
		PL_PEI_4STEP = pl_pei_4step;
	}
	public void setPL_PEI_5STEP(String pl_pei_5step) {
		PL_PEI_5STEP = pl_pei_5step;
	}
	public void setPL_PEI_6STEP(String pl_pei_6step) {
		PL_PEI_6STEP = pl_pei_6step;
	}
	public void setPL_PEI_PRIM_EVAL(String pl_pei_prim_eval) {
		PL_PEI_PRIM_EVAL = pl_pei_prim_eval;
	}
	public void setPL_PEI_METHOD(String pl_pei_method) {
		PL_PEI_METHOD = pl_pei_method;
	}
	public void setPL_PEI_SCIENT_BASIS(String pl_pei_scient_basis) {
		PL_PEI_SCIENT_BASIS = pl_pei_scient_basis;
	}
	public void setPL_PEI_SCIENT_LIMIT(String pl_pei_scient_limit) {
		PL_PEI_SCIENT_LIMIT = pl_pei_scient_limit;
	}
	public void setPL_PEI_PRIMARY_FACT(String pl_pei_primary_fact) {
		PL_PEI_PRIMARY_FACT = pl_pei_primary_fact;
	}
	public void setPL_PEI_DATA_ANALY(String pl_pei_data_analy) {
		PL_PEI_DATA_ANALY = pl_pei_data_analy;
	}
	public void setPL_PEI_MEASUREMENT(String pl_pei_measurement) {
		PL_PEI_MEASUREMENT = pl_pei_measurement;
	}
	public void setPL_PEI_DIRECT(String pl_pei_direct) {
		PL_PEI_DIRECT = pl_pei_direct;
	}
	public void setPL_PEI_INDIRECT(String pl_pei_indirect) {
		PL_PEI_INDIRECT = pl_pei_indirect;
	}
	public void setPL_PEI_FINAL_FLAG(String pl_pei_reject_flag) {
		PL_PEI_FINAL_FLAG = pl_pei_reject_flag;
	}
	public void setPL_PEI_ASSESS_USER(String pl_pei_assess_user) {
		PL_PEI_ASSESS_USER = pl_pei_assess_user;
	}
	public String getPL_BI_DATA_NUM() {
		return PL_BI_DATA_NUM;
	}
	public String getPL_PEI_1STEP() {
		return PL_PEI_1STEP;
	}
	public String getPL_PEI_2STEP() {
		return PL_PEI_2STEP;
	}
	public String getPL_PEI_3STEP() {
		return PL_PEI_3STEP;
	}
	public String getPL_PEI_4STEP() {
		return PL_PEI_4STEP;
	}
	public String getPL_PEI_5STEP() {
		return PL_PEI_5STEP;
	}
	public String getPL_PEI_6STEP() {
		return PL_PEI_6STEP;
	}
	public String getPL_PEI_PRIM_EVAL() {
		return PL_PEI_PRIM_EVAL;
	}
	public String getPL_PEI_METHOD() {
		return PL_PEI_METHOD;
	}
	public String getPL_PEI_SCIENT_BASIS() {
		return PL_PEI_SCIENT_BASIS;
	}
	public String getPL_PEI_SCIENT_LIMIT() {
		return PL_PEI_SCIENT_LIMIT;
	}
	public String getPL_PEI_PRIMARY_FACT() {
		return PL_PEI_PRIMARY_FACT;
	}
	public String getPL_PEI_DATA_ANALY() {
		return PL_PEI_DATA_ANALY;
	}
	public String getPL_PEI_MEASUREMENT() {
		return PL_PEI_MEASUREMENT;
	}
	public String getPL_PEI_DIRECT() {
		return PL_PEI_DIRECT;
	}
	public String getPL_PEI_INDIRECT() {
		return PL_PEI_INDIRECT;
	}
	public String getPL_PEI_FINAL_FLAG() {
		return PL_PEI_FINAL_FLAG;
	}
	public String getPL_PEI_ASSESS_USER() {
		return PL_PEI_ASSESS_USER;
	}
	public String getPL_PEI_ASSESS_DATE() {
		return PL_PEI_ASSESS_DATE;
	}
	public void setPL_PEI_ASSESS_DATE(String pl_pei_assess_date) {
		PL_PEI_ASSESS_DATE = pl_pei_assess_date;
	}
	public String getPL_PEI_DATA_BRANCH() {
		return PL_PEI_DATA_BRANCH;
	}
	public void setPL_PEI_DATA_BRANCH(String pl_pei_data_branch) {
		PL_PEI_DATA_BRANCH = pl_pei_data_branch;
	}
	public String getPL_PEI_PROCESS() {
		return PL_PEI_PROCESS;
	}
	public void setPL_PEI_PROCESS(String pl_pei_process) {
		PL_PEI_PROCESS = pl_pei_process;
	}
	public String getPL_PEI_EXP_THE_REC() {
		return PL_PEI_EXP_THE_REC;
	}
	public void setPL_PEI_EXP_THE_REC(String pl_pei_exp_the_rec) {
		PL_PEI_EXP_THE_REC = pl_pei_exp_the_rec;
	}
	public String getPL_PEI_X_UNIT() {
		return PL_PEI_X_UNIT;
	}
	public void setPL_PEI_X_UNIT(String pl_pei_x_unit) {
		PL_PEI_X_UNIT = pl_pei_x_unit;
	}
	public String getPL_PEI_Y_UNIT() {
		return PL_PEI_Y_UNIT;
	}
	public void setPL_PEI_Y_UNIT(String pl_pei_y_unit) {
		PL_PEI_Y_UNIT = pl_pei_y_unit;
	}
	public String getPL_PEI_EXPRESS() {
		return PL_PEI_EXPRESS;
	}
	public void setPL_PEI_EXPRESS(String pl_pei_express) {
		PL_PEI_EXPRESS = pl_pei_express;
	}
}
