package nfri.dcpp.properties.model;

import java.util.Vector;


/**
 *
 * @Project : dcpp_web
 * @Title : Article_Info.java
 * @Description : 참고 문헌 정보를 담당할 모델 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2008. 11. 18
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class Article_Info {
	//참고 문헌 번호
	String PL_RAI_ARTCL_NUM = "";
	//참고 문헌 종류 코드
	String PL_RACI_CODE_ID = "";
	//참고 문헌 종류 코드 설명 (한글)
	String PL_RACI_CODE_EXP = "";
	//참고 문헌 종류 코드 설명 (영문)
	String PL_RACI_CODE_EXP_ENG = "";
	//논문 제목
	String PL_RAI_ARTCL_TITLE = "";
	//논문 저자 Family
	String PL_RAI_ARTCL_AUTH_FNAME = "";
	//논문 저자 End
	String PL_RAI_ARTCL_AUTH_ENAME = "";
	//저널명
	String PL_RAI_JOUR_TITLE = "";
	//ISSN_P
	String PL_RAI_ISSN_P = "";
	//ISSN_N
	String PL_RAI_ISSN_N = "";
	//ISBN
	String PL_RAI_ISBN = "";
	//NDSL
	String PL_RAI_NDSL = "";
	//저널 볼륨
	String PL_RAI_JOUR_VOL = "";
	//저널 호
	String PL_RAI_JOUR_NUM = "";
	//시작페이지
	String PL_RAI_ARTCL_ST_PAGE = "";
	//끝페이지
	String PL_RAI_ARTCL_ED_PAGE = "";
	//출판연도
	String PL_RAI_JOUR_YEAR = "";
	//원문파일 위치
	String PL_RAI_ORG_FILE_PATH = "";
	//원문파일 이름
	String PL_RAI_ORG_FILE_NAME = "";
	//원문파일 확장자
	String PL_RAI_ORG_FILE_EXT = "";
	//원문입력 시간
	String PL_RAI_INSERT_TIME = "";
	//원문 DOI
	String PL_RAI_ARTCL_DOI = "";
	
	//물성번호
	String PL_BI_DATA_NUM = "";
	//평가 정보
	String PL_PEI_FINAL_FLAG= "";
	
	/**
	 * @return the pL_BI_DATA_NUM
	 */
	public String getPL_BI_DATA_NUM() {
		return PL_BI_DATA_NUM;
	}

	/**
	 * @param pL_BI_DATA_NUM the pL_BI_DATA_NUM to set
	 */
	public void setPL_BI_DATA_NUM(String pL_BI_DATA_NUM) {
		PL_BI_DATA_NUM = pL_BI_DATA_NUM;
	}

	/**
	 * @return the pL_PEI_FINAL_FLAG
	 */
	public String getPL_PEI_FINAL_FLAG() {
		return PL_PEI_FINAL_FLAG;
	}

	/**
	 * @param pL_PEI_FINAL_FLAG the pL_PEI_FINAL_FLAG to set
	 */
	public void setPL_PEI_FINAL_FLAG(String pL_PEI_FINAL_FLAG) {
		PL_PEI_FINAL_FLAG = pL_PEI_FINAL_FLAG;
	}

	/**
	 * @return the pL_RAI_ARTCL_DOI
	 */
	public String getPL_RAI_ARTCL_DOI() {
		return PL_RAI_ARTCL_DOI;
	}

	public String getPL_RAI_ARTCL_NUM(){
		return this.PL_RAI_ARTCL_NUM;
	}
	
	public String getPL_RACI_CODE_ID(){
		return this.PL_RACI_CODE_ID;
	}
	
	public String getPL_RAI_ARTCL_TITLE(){
		return this.PL_RAI_ARTCL_TITLE;
	}
	
	public String getPL_RAI_ARTCL_AUTH_FNAME(){
		return this.PL_RAI_ARTCL_AUTH_FNAME;
	}
	
	public String getPL_RAI_ARTCL_AUTH_ENAME(){
		return this.PL_RAI_ARTCL_AUTH_ENAME;
	}
	
	public String getPL_RAI_JOUR_TITLE(){
		return this.PL_RAI_JOUR_TITLE;
	}
	
	public String getPL_RAI_ISSN_P(){
		return this.PL_RAI_ISSN_P;
	}
	
	public String getPL_RAI_ISSN_N(){
		return this.PL_RAI_ISSN_N;
	}
	
	public String getPL_RAI_ISBN(){
		return this.PL_RAI_ISBN;
	}
	
	public String getPL_RAI_NDSL(){
		return this.PL_RAI_NDSL;
	}
	
	public String getPL_RAI_JOUR_VOL(){
		return this.PL_RAI_JOUR_VOL;
	}
	
	public String getPL_RAI_JOUR_NUM(){
		return this.PL_RAI_JOUR_NUM;
	}
	
	public String getPL_RAI_ARTCL_ST_PAGE(){
		return this.PL_RAI_ARTCL_ST_PAGE;
	}
	
	public String getPL_RAI_ARTCL_ED_PAGE(){
		return this.PL_RAI_ARTCL_ED_PAGE;
	}
	
	public String getPL_RAI_JOUR_YEAR(){
		return this.PL_RAI_JOUR_YEAR;
	}

	public String getPL_RAI_ORG_FILE_PATH(){
		return this.PL_RAI_ORG_FILE_PATH;
	}
	
	public String getPL_RAI_ORG_FILE_NAME(){
		return this.PL_RAI_ORG_FILE_NAME;
	}
	
	public String getPL_RAI_ORG_FILE_EXT(){
		return this.PL_RAI_ORG_FILE_EXT;
	}
	
	public String getPL_RAI_INSERT_TIME(){
		return this.PL_RAI_INSERT_TIME;
	}
	/**
	 * @return the pL_RACI_CODE_EXP
	 */
	public String getPL_RACI_CODE_EXP() {
		return PL_RACI_CODE_EXP;
	}

	/**
	 * @param pLRACICODEEXP the pL_RACI_CODE_EXP to set
	 */
	public void setPL_RACI_CODE_EXP(String pLRACICODEEXP) {
		PL_RACI_CODE_EXP = pLRACICODEEXP;
	}

	/**
	 * @return the pL_RACI_CODE_EXP_ENG
	 */
	public String getPL_RACI_CODE_EXP_ENG() {
		return PL_RACI_CODE_EXP_ENG;
	}

	/**
	 * @param pLRACICODEEXPENG the pL_RACI_CODE_EXP_ENG to set
	 */
	public void setPL_RACI_CODE_EXP_ENG(String pLRACICODEEXPENG) {
		PL_RACI_CODE_EXP_ENG = pLRACICODEEXPENG;
	}
	public void setPL_RAI_ARTCL_NUM(String pl_rai_artcl_num){
		this.PL_RAI_ARTCL_NUM = pl_rai_artcl_num;
	}
	
	public void setPL_RACI_CODE_ID(String pl_raci_code_id){
		this.PL_RACI_CODE_ID = pl_raci_code_id;
	}
	
	public void setPL_RAI_ARTCL_TITLE(String pl_rai_artcl_title){
		this.PL_RAI_ARTCL_TITLE = pl_rai_artcl_title;
	}
	
	public void setPL_RAI_ARTCL_AUTH_FNAME(String pl_rai_artcl_auth_fname){
		this.PL_RAI_ARTCL_AUTH_FNAME = pl_rai_artcl_auth_fname;
	}
	
	public void setPL_RAI_ARTCL_AUTH_ENAME(String pl_rai_artcl_auth_ename){
		this.PL_RAI_ARTCL_AUTH_ENAME = pl_rai_artcl_auth_ename;
	}
	
	public void setPL_RAI_JOUR_TITLE(String pl_rai_jour_title){
		this.PL_RAI_JOUR_TITLE = pl_rai_jour_title;
	}
	
	public void setPL_RAI_ISSN_P(String pl_rai_issn_p){
		this.PL_RAI_ISSN_P = pl_rai_issn_p;
	}
	
	public void setPL_RAI_ISSN_N(String pl_rai_issn_n){
		this.PL_RAI_ISSN_N = pl_rai_issn_n;
	}
	
	public void setPL_RAI_ISBN(String pl_rai_isbn){
		this.PL_RAI_ISBN = pl_rai_isbn;
	}
	
	public void setPL_RAI_NDSL(String pl_rai_ndsl){
		this.PL_RAI_NDSL = pl_rai_ndsl;
	}
	
	public void setPL_RAI_JOUR_VOL(String pl_rai_jour_vol){
		this.PL_RAI_JOUR_VOL = pl_rai_jour_vol;
	}
	
	public void setPL_RAI_JOUR_NUM(String pl_rai_jour_num){
		this.PL_RAI_JOUR_NUM = pl_rai_jour_num;
	}
	
	public void setPL_RAI_ARTCL_ST_PAGE(String pl_rai_artcl_st_page){
		this.PL_RAI_ARTCL_ST_PAGE = pl_rai_artcl_st_page;
	}
	
	public void setPL_RAI_ARTCL_ED_PAGE(String pl_rai_artcl_ed_page){
		this.PL_RAI_ARTCL_ED_PAGE = pl_rai_artcl_ed_page;
	}
	
	public void setPL_RAI_JOUR_YEAR(String pl_rai_jour_year){
		this.PL_RAI_JOUR_YEAR = pl_rai_jour_year;
	}
	
	public void setPL_RAI_ORG_FILE_PATH(String pl_rai_org_file_path){		
		this.PL_RAI_ORG_FILE_PATH = pl_rai_org_file_path;
	}
	
	public void setPL_RAI_ORG_FILE_NAME(String pl_rai_org_file_name){
		this.PL_RAI_ORG_FILE_NAME = pl_rai_org_file_name;
	}

	public void setPL_RAI_ORG_FILE_EXT(String pl_rai_org_file_ext){
		this.PL_RAI_ORG_FILE_EXT = pl_rai_org_file_ext;
	}
	
	public void setPL_RAI_INSERT_TIME(String pl_rai_org_file_ext){
		this.PL_RAI_INSERT_TIME = pl_rai_org_file_ext;
	}
	/**
	 * @param pL_RAI_ARTCL_DOI the pL_RAI_ARTCL_DOI to set
	 */
	public void setPL_RAI_ARTCL_DOI(String pL_RAI_ARTCL_DOI) {
		PL_RAI_ARTCL_DOI = pL_RAI_ARTCL_DOI;
	}
	
	public Vector<String> getDataList(){
		Vector<String> value = new Vector<String>();
		value.addElement(this.PL_RAI_ARTCL_NUM);
		value.addElement(this.PL_RAI_ARTCL_TITLE);
		value.addElement(this.PL_RAI_ARTCL_AUTH_FNAME);
		value.addElement(this.PL_RAI_JOUR_TITLE);
		value.addElement(this.PL_RAI_ISSN_P);
		value.addElement(this.PL_RAI_ISSN_N);		
		value.addElement(this.PL_RAI_ISBN);
		value.addElement(this.PL_RAI_NDSL);
		value.addElement(this.PL_RAI_JOUR_VOL);
		value.addElement(this.PL_RAI_JOUR_NUM);
		value.addElement(this.PL_RAI_ARTCL_ST_PAGE);
		value.addElement(this.PL_RAI_JOUR_YEAR);	
		value.addElement(this.PL_RAI_ARTCL_ED_PAGE);		
		value.addElement(this.PL_RACI_CODE_ID);
		value.addElement(this.PL_RAI_ORG_FILE_PATH);
		value.addElement(this.PL_RAI_ORG_FILE_NAME);
		value.addElement(this.PL_RAI_ORG_FILE_EXT);
		value.addElement(this.PL_RAI_INSERT_TIME);
		value.addElement(this.PL_RAI_ARTCL_AUTH_ENAME);
		value.addElement(this.PL_RAI_ARTCL_DOI);
		return value;		
	}
}
