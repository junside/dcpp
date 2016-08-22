package nfri.dcpp.properties.model;

/**
 *
 * @Project : dcpp_web
 * @Title : Properties_Basic_List_Info.java
 * @Description : 플라즈마 물성 기본 정보 리스트를 처리하는 모델 클래스 
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 25
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class Properties_Basic_List_Info {
	//물성정보번호
	private String PL_BI_DATA_NUM = "";
	//참고문헌번호
	private String PL_RAI_ARTCL_NUM = "";
	//단위정보 여부
	private boolean PL_GRAPH_UNIT_DATA = false;
	//수치정보 여부
	private boolean PL_GRAPH_DATA = false;
	//입력 단계 정보(I,C)
	private String PL_BI_INSERT_FLAG = "";
	
	public String getPL_BI_DATA_NUM() {
		return PL_BI_DATA_NUM;
	}

	public String getPL_RAI_ARTCL_NUM() {
		return PL_RAI_ARTCL_NUM;
	}
	
	public boolean isPL_GRAPH_UNIT_DATA() {
		return PL_GRAPH_UNIT_DATA;
	}
	
	public boolean isPL_GRAPH_DATA() {
		return PL_GRAPH_DATA;
	}

	public void setPL_BI_DATA_NUM(String pl_bi_data_num) {
		PL_BI_DATA_NUM = pl_bi_data_num;
	}

	public void setPL_RAI_ARTCL_NUM(String pl_rai_artcl_num) {
		PL_RAI_ARTCL_NUM = pl_rai_artcl_num;
	}
	public void setPL_GRAPH_DATA(boolean pl_graph_data) {
		PL_GRAPH_DATA = pl_graph_data;
	}

	public void setPL_GRAPH_UNIT_DATA(boolean pl_graph_unit_data) {
		PL_GRAPH_UNIT_DATA = pl_graph_unit_data;
	}

	public String getPL_BI_INSERT_FLAG() {
		return PL_BI_INSERT_FLAG;
	}

	public void setPL_BI_INSERT_FLAG(String pl_bi_insert_flag) {
		PL_BI_INSERT_FLAG = pl_bi_insert_flag;
	}

}
