package nfri.dcpp.properties.model;

/**
 *
 * @Project : dcpp_web
 * @Title : Properties_Basic_List_Info.java
 * @Description : �ö�� ���� �⺻ ���� ����Ʈ�� ó���ϴ� �� Ŭ���� 
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 25
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @�����̷� : -
 *
 */
public class Properties_Basic_List_Info {
	//����������ȣ
	private String PL_BI_DATA_NUM = "";
	//�������ȣ
	private String PL_RAI_ARTCL_NUM = "";
	//�������� ����
	private boolean PL_GRAPH_UNIT_DATA = false;
	//��ġ���� ����
	private boolean PL_GRAPH_DATA = false;
	//�Է� �ܰ� ����(I,C)
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
