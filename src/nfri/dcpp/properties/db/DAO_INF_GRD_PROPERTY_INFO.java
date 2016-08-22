/**
 * 
 */
package nfri.dcpp.properties.db;

import java.util.Vector;

import nfri.dcpp.com.db.ComResultSet;
import nfri.dcpp.com.db.ComSQLExecution;
import nfri.dcpp.properties.business.Ctr_Option_Process;
import nfri.dcpp.properties.model.Fitting_Temp_Info;
import nfri.dcpp.properties.model.Graph_Data_Info;
import nfri.dcpp.properties.model.Inf_Grd_Graph_Data_Info;
import nfri.dcpp.properties.model.Inf_Grd_Properties_Basic_Info;
import nfri.dcpp.properties.model.Inf_Grd_Properties_Equation_Info;
import nfri.dcpp.properties.model.Properties_Assess_Search_List;
import nfri.dcpp.properties.model.Properties_Basic_Info;
import nfri.dcpp.properties.model.Properties_Equation_Info;

/**
 * @Project: dcpp
 * @Title  : DAO_INF_GRD_PROPERTY_INFO.java
 * @Description : 등급유력 물성정보와 관련하여 DB에 입력하는 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2010. 09. 01
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class DAO_INF_GRD_PROPERTY_INFO {
	//DB 실행 클래스 선언
	ComSQLExecution comSQL;
	
	public DAO_INF_GRD_PROPERTY_INFO() {
		comSQL = new ComSQLExecution();
	}
	
	/**
	 * @MethodName  : insertInfGrdBasicInfo
	 * @Date   : 2010. 09. 01 
	 * @MethodDescription : 등급유력 기본 정보 입력하는 메소드
	 * @param info
	 * @return
	 * @History  : - 
	 */
	public boolean insertInfGrdBasicInfo(Inf_Grd_Properties_Basic_Info info){
		boolean r_value = false;
		//1. 등급유력 물성 기본 정보 입력하기
		String insertBasicQuery = "INSERT INTO PLASMA.PL_INF_GRD_BASIC_INFO (" +
				" PL_IGBI_DATA_NUM, PL_IGBI_MAIN_PROC, PL_IGBI_SUB_PROC," +
				" PL_IGBI_EXPRESSION, PL_UI_ID, PL_IGBI_DATA_NUM_LIST," +
				" PL_IGBI_INSERT_FLAG, PL_IGBI_IMP_CLASS, PL_IGBI_DATA_NUM_CNT," +
				" PL_IGBI_MGMT_NUM_LIST, PL_IGBI_REF_ARTICLE_LIST)" +
				" VALUES (  ? , ? , ? , ? , ? , ?, ?, ?, ?, ?, ? )";
		Vector<String> sqlOptionBasic = new Vector<String>();
		sqlOptionBasic.addElement(info.getPL_IGBI_DATA_NUM());
		sqlOptionBasic.addElement(info.getPL_IGBI_MAIN_PROC());
		sqlOptionBasic.addElement(info.getPL_IGBI_SUB_PROC());
		sqlOptionBasic.addElement(info.getPL_IGBI_EXPRESSION());
		sqlOptionBasic.addElement(info.getPL_UI_ID());
		sqlOptionBasic.addElement(info.getPL_IGBI_DATA_NUM_LIST());
		sqlOptionBasic.addElement(info.getPL_IGBI_INSERT_FLAG());
		sqlOptionBasic.addElement(info.getPL_IGBI_IMP_CLASS());
		sqlOptionBasic.addElement(info.getPL_IGBI_DATA_NUM_CNT());
		sqlOptionBasic.addElement(info.getPL_IGBI_MGMT_NUM_LIST());
		sqlOptionBasic.addElement(info.getPL_IGBI_REF_ARTICLE_LIST());
		
		r_value = comSQL.executeTransact(insertBasicQuery, sqlOptionBasic);
		
		return r_value;
	}
	
	public boolean insertInfGrdEquation(Inf_Grd_Properties_Equation_Info equa_obj){
		boolean r_value = false;
		String insertQuery = "INSERT INTO PLASMA.PL_INF_GRD_EQUATION_INFO (" +
				" PL_IGBI_DATA_NUM, PL_CPBI_ELE_NUM, PL_IGEI_SEQ," +
				" PL_IGEI_CHG_STATE, PL_IGEI_ELC_STATE, PL_IGEI_OTH_STRUC)" +
				" VALUES (  ? , ? , ? , ? , ? , ? )";
		//넘어온 데이터를 담는 벡터
		//Vector ve_equa = info.getPL_EQUA_INFO();
		
		Vector<String> sqlInsertOption = new Vector<String>();
		sqlInsertOption.addElement(equa_obj.getPL_IGBI_DATA_NUM());
		sqlInsertOption.addElement(equa_obj.getPL_CPBI_ELE_NUM());
		sqlInsertOption.addElement(equa_obj.getPL_IGEI_SEQ());
		sqlInsertOption.addElement(equa_obj.getPL_IGEI_CHG_STATE());
		sqlInsertOption.addElement(equa_obj.getPL_IGEI_ELC_STATE());
		sqlInsertOption.addElement(equa_obj.getPL_IGEI_OTH_STRUC());
		
		r_value = comSQL.executeTransact(insertQuery, sqlInsertOption);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : insertInfGrdEquation
	 * @Date   : 2010. 09. 01 
	 * @MethodDescription : 등급유력 물성정보의 화학식 입자 정보를 입력하는 메소드
	 * @param info
	 * @return
	 * @History  : - 
	 */
	public boolean insertInfGrdEquation(Vector<?> ve_equa){
		boolean r_value = false;
		String insertQuery = "INSERT INTO PLASMA.PL_INF_GRD_EQUATION_INFO (" +
				" PL_IGBI_DATA_NUM, PL_CPBI_ELE_NUM, PL_IGEI_SEQ," +
				" PL_IGEI_CHG_STATE, PL_IGEI_ELC_STATE, PL_IGEI_OTH_STRUC)" +
				" VALUES (  ? , ? , ? , ? , ? , ? )";
		//넘어온 데이터를 담는 벡터
		//Vector ve_equa = info.getPL_EQUA_INFO();
		
		for(int i = 0 ; i < ve_equa.size(); i ++) {
			Inf_Grd_Properties_Equation_Info equa_obj = (Inf_Grd_Properties_Equation_Info)ve_equa.elementAt(i);
			Vector<String> sqlInsertOption = new Vector<String>();
			sqlInsertOption.addElement(equa_obj.getPL_IGBI_DATA_NUM());
			sqlInsertOption.addElement(equa_obj.getPL_CPBI_ELE_NUM());
			sqlInsertOption.addElement(equa_obj.getPL_IGEI_SEQ());
			sqlInsertOption.addElement(equa_obj.getPL_IGEI_CHG_STATE());
			sqlInsertOption.addElement(equa_obj.getPL_IGEI_ELC_STATE());
			sqlInsertOption.addElement(equa_obj.getPL_IGEI_OTH_STRUC());
			
			r_value = comSQL.executeTransact(insertQuery, sqlInsertOption);

		}
		
		return r_value;
	}
	
	/**
	 * @MethodName  : insertInfGraphData
	 * @Date   : 2010. 09. 16 
	 * @MethodDescription : 등급유력 물성 그래프 데이터정보를 입력하는 메소드
	 * @param info
	 * @return
	 * @History  : - 
	 */
	public boolean insertInfGraphData(Inf_Grd_Graph_Data_Info info){
		boolean value = false;
		String sqlQuery = "INSERT INTO PLASMA.PL_INF_GRD_GRAPH_DATA (" +
				" PL_IGBI_DATA_NUM, PL_IGGD_X_AX_VAL, PL_IGGD_Y_AX_VAL," +
				" PL_IGGD_X_ERR, PL_IGGD_Y_ERR_MAX, PL_IGGD_Y_ERR_MIN," +
				" PL_IGGD_RATIO, PL_IGGD_PRESS, PL_IGGD_BACKUP_DATA, PL_IGGD_SEQ_NUM)" +
				" VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
		
		value = comSQL.executeTransact(sqlQuery, info.getDataList());
		
		return value;
	}
	
	/**
	 * @MethodName  : insertInfGraphData
	 * @Date   : 2010. 09. 02 
	 * @MethodDescription : 등급유력 물성 그래프 데이터정보를 입력하는 메소드
	 * @param igbi_data_num
	 * @param pd_list
	 * @return
	 * @History  : - 
	 */
	/*public boolean insertInfGraphData(String igbi_data_num, Vector pd_list){
		boolean r_value = false;

		//3. 등급유력 물성 그래프 데이터 정보 입력하기
		String insertGraphDataQuery = "INSERT INTO PLASMA.PL_INF_GRD_GRAPH_DATA (" +
				" PL_IGBI_DATA_NUM, PL_IGGD_X_AX_VAL, PL_IGGD_Y_AX_VAL," +
				" PL_IGGD_X_ERR, PL_IGGD_Y_ERR_MAX, PL_IGGD_Y_ERR_MIN," +
				" PL_IGGD_RATIO, PL_IGGD_PRESS, PL_IGGD_BACKUP_DATA," +
				" PL_IGGD_SEQ_NUM)" +
				" VALUES ( ? , ? , ? , ? , ? , ?, ?, ?, ? , ? )";	
		
		//Vector pd_list = info.getPD_LIST();
		
		for(int i = 0; i < pd_list.size(); i++) {
			Properties_Assess_Search_List prot = (Properties_Assess_Search_List) pd_list.elementAt(i);
		  	String pr_no = prot.getPL_BI_DATA_NUM();	 //물성번호 
		  	
		  	Vector sqlOption = new Vector();
		  	sqlOption.addElement(pr_no);
		  	
		  	DAO_BASIC_GRAPH_INFO graph_info = new DAO_BASIC_GRAPH_INFO();
		  	Vector result = graph_info.selectBasicGraphData(sqlOption);		  	
		  	
		  	for(int j = 0 ; j < result.size(); j ++) {
		  		Graph_Data_Info data = (Graph_Data_Info)result.elementAt(i);
		  		Vector sqlInsertOption = new Vector();
		  		sqlInsertOption.addElement(igbi_data_num);
		  		sqlInsertOption.addElement(data.getPL_BGD_X_AX_VAL());
		  		sqlInsertOption.addElement(data.getPL_BGD_Y_AX_VAL());
		  		sqlInsertOption.addElement(data.getPL_BGD_X_ERR());
		  		sqlInsertOption.addElement(data.getPL_BGD_Y_ERR_MAX());
		  		sqlInsertOption.addElement(data.getPL_BGD_Y_ERR_MIN());
		  		sqlInsertOption.addElement(data.getPL_BGD_RATIO());
		  		sqlInsertOption.addElement(data.getPL_BGD_PRESS());
		  		sqlInsertOption.addElement(data.getPL_BGD_BACKUP_DATA());
		  		sqlInsertOption.addElement(data.getPL_BGD_SEQ_NUM());
		  		
		  		r_value = comSQL.executeTransact(insertGraphDataQuery, sqlInsertOption);
		  	}			  	
		}

		return r_value;
	}*/

	/**
	 * @MethodName  : selectInfGrdPropertyBasicInfo
	 * @Date   : 2010. 09. 14 
	 * @MethodDescription : 등급유력 기본 정보를 조회하는 메소드
	 * @param v_pr_no
	 * @return
	 * @History  : - 
	 */
	public Inf_Grd_Properties_Basic_Info selectInfGrdPropertyBasicInfo(String v_pr_no) {
		// 1. 상세 물성 정보 Query
		String query = "SELECT PL_IGBI_DATA_NUM, PL_IGBI_MAIN_PROC, PL_IGBI_SUB_PROC," +
				" PL_IGBI_EXPRESSION, PL_UI_ID, PL_IGBI_DATA_NUM_LIST, PL_IGBI_DATA_NUM_CNT," +
				" PL_IGBI_MGMT_NUM_LIST, PL_IGBI_REF_ARTICLE_LIST" +
				" FROM PL_INF_GRD_BASIC_INFO" +
				" WHERE PL_IGBI_DATA_NUM = ?";
		
		//상세 물성 정보 Query 파라미터 벡터		
		Vector<String> option = new Vector<String>();
		option.addElement(v_pr_no);		

		ComResultSet rs = comSQL.executeSelect(query, option);
		
		Inf_Grd_Properties_Basic_Info spec_Info = new Inf_Grd_Properties_Basic_Info();
		
		while(rs.next()){	
			spec_Info.setPL_IGBI_DATA_NUM(rs.getString(1));
			spec_Info.setPL_IGBI_MAIN_PROC(rs.getString(2));
			spec_Info.setPL_IGBI_SUB_PROC(rs.getString(3));
			spec_Info.setPL_IGBI_EXPRESSION(rs.getString(4));
			spec_Info.setPL_UI_ID(rs.getString(5));
			spec_Info.setPL_IGBI_DATA_NUM_LIST(rs.getString(6));
			spec_Info.setPL_IGBI_DATA_NUM_CNT(rs.getString(7));
			spec_Info.setPL_IGBI_MGMT_NUM_LIST(rs.getString(8));
			spec_Info.setPL_IGBI_REF_ARTICLE_LIST(rs.getString(9));
			
		}
		return spec_Info;
	}

	/**
	 * @MethodName  : updateInfGrdInsertStateInfo
	 * @Date   : 2011. 6. 28. 
	 * @MethodDescription : 평가정보 입력 상태를 업데이트 하는 메소드
	 * @param state
	 * @return
	 * @History  : - 
	 */
	public boolean updateInfGrdInsertStateInfo(String v_pr_no, String state) {
		boolean r_value = false;
		
		String sqlQuery = "UPDATE PL_INF_GRD_BASIC_INFO" +
				" SET   PL_IGBI_INSERT_FLAG = ?" +
				" WHERE PL_IGBI_DATA_NUM =?";
		
		Vector<String> sqlOption = new Vector<String>();			
		sqlOption.addElement(state);
		sqlOption.addElement(v_pr_no);
		
		r_value = comSQL.executeTransact(sqlQuery, sqlOption);
		
		return r_value;
	}
}
