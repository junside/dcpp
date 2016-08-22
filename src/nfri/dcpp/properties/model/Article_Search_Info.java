package nfri.dcpp.properties.model;

/**
 *
 * @Project : dcpp_web
 * @Title : Article_Search_Info.java
 * @Description : 참고 문헌 조회를 위한 조회 데이터를 담는 모델 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 02. 18
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class Article_Search_Info {
	
	//참고 문헌 종류 코드
	String PL_RACI_CODE_ID = "";
	//논문 제목
	String PL_RA_ARTCL_TITLE = "";
	//논문 저자
	String PL_RA_ARTCL_AUTH = "";
	//저널명
	String PL_RA_JOUR_TITLE = "";	
	//출판연도(START)
	int PL_RA_JOUR_YEAR_START = 0;
	//출판연도(END)
	int PL_RA_JOUR_YEAR_END = 0;
	
	public String getPL_RACI_CODE_ID(){
		return PL_RACI_CODE_ID;
	}
	
	public String getPL_RA_ARTCL_TITLE(){
		return PL_RA_ARTCL_TITLE;
	}
	
	public String getPL_RA_ARTCL_AUTH(){
		return PL_RA_ARTCL_AUTH;
	}
	
	public String getPL_RA_JOUR_TITLE(){
		return PL_RA_JOUR_TITLE;
	}	
	
	public int getPL_RA_JOUR_YEAR_START(){
		return PL_RA_JOUR_YEAR_START;
	}
	
	public int getPL_RA_JOUR_YEAR_END(){
		return PL_RA_JOUR_YEAR_END;
	}

	public void setPL_RACI_CODE_ID(String pl_raci_code_id){
		PL_RACI_CODE_ID = pl_raci_code_id;
	}
	
	public void setPL_RA_ARTCL_TITLE(String pl_ra_artcl_title){
		PL_RA_ARTCL_TITLE = pl_ra_artcl_title;
	}
	
	public void setPL_RA_ARTCL_AUTH(String pl_ra_artcl_auth){
		PL_RA_ARTCL_AUTH = pl_ra_artcl_auth;
	}
	
	public void setPL_RA_JOUR_TITLE(String pl_ra_jour_title){
		PL_RA_JOUR_TITLE = pl_ra_jour_title;
	}
	
	public void setPL_RA_JOUR_YEAR_START(int pl_ra_jour_year_start){
		PL_RA_JOUR_YEAR_START = pl_ra_jour_year_start;
	}
	
	public void setPL_RA_JOUR_YEAR_END(int pl_ra_jour_year_end){
		PL_RA_JOUR_YEAR_END = pl_ra_jour_year_end;
	}
}
