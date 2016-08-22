package nfri.dcpp.properties.model;

/**
*
* @Project : dcpp_web
* @Title : Properties_Assess_List_Info.java
* @Description : 플라즈마 물성 평가 정보 리스트를 처리하는 모델 클래스 
* @Author : J.H Park (JUNSiDE)
* @Date : 2009. 03. 25
* @Company : Data Center for Plasma Properties.
*            NFRI.
*
* @변경이력 : -
*
*/
public class Properties_Assess_List_Info {
	//물성정보번호
	private String PL_BI_DATA_NUM = "";
	//참고문헌번호
	private String PL_RAI_ARTCL_NUM = "";
	//평가정보 여부
	private String PL_ASSESS_DATA = "";
	
	public String getPL_BI_DATA_NUM() {
		return PL_BI_DATA_NUM;
	}
	public String getPL_RAI_ARTCL_NUM() {
		return PL_RAI_ARTCL_NUM;
	}
	public String getPL_ASSESS_DATA() {
		return PL_ASSESS_DATA;
	}
	public void setPL_BI_DATA_NUM(String pl_bi_data_num) {
		PL_BI_DATA_NUM = pl_bi_data_num;
	}
	public void setPL_RAI_ARTCL_NUM(String pl_rai_artcl_num) {
		PL_RAI_ARTCL_NUM = pl_rai_artcl_num;
	}
	public void setPL_ASSESS_DATA(String pl_assess_data) {
		PL_ASSESS_DATA = pl_assess_data;
	}
}
