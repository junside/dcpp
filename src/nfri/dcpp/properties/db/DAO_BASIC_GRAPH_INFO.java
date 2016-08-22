package nfri.dcpp.properties.db;

import java.util.ArrayList;
import java.util.Vector;

import nfri.dcpp.com.db.ComResultSet;
import nfri.dcpp.com.db.ComSQLExecution;
import nfri.dcpp.com.util.ComUtil;
import nfri.dcpp.properties.model.Common_Data;
import nfri.dcpp.properties.model.Graph_Basic_Info;
import nfri.dcpp.properties.model.Graph_Data_Info;

/**
 *
 * @Project : dcpp_web
 * @Title : DAO_BASIC_GRAPH_INFO.java
 * @Description : 기본 그래프정보 테이블과 관련된 항목을 처리하는 DAO 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 24
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class DAO_BASIC_GRAPH_INFO {
	//DB 실행 클래스 선언
	ComSQLExecution comSQL;
	
	public DAO_BASIC_GRAPH_INFO(){
		comSQL = new ComSQLExecution();
	}
	
	/**
	 * @MethodName : selectBasicGraphData
	 * @Desc : DB에서 그래프 데이터 정보를 가져오는 메소드
	 * @return
	 */
	public Vector<Graph_Data_Info> selectBasicGraphData(Vector<String> sqlOption){
		Vector<Graph_Data_Info> values = new Vector<Graph_Data_Info>();
		String sqlQuery = "SELECT PL_BI_DATA_NUM, PL_BGD_SEQ_NUM, NVL(PL_BGD_X_AX_VAL, '0'), NVL(PL_BGD_Y_AX_VAL, '0')," +
				" NVL(PL_BGD_X_ERR, '0'), NVL(PL_BGD_Y_ERR_MAX, '0'), NVL(PL_BGD_Y_ERR_MIN, '0')," +
				" NVL(PL_BGD_RATIO,'-'), NVL(PL_BGD_PRESS,'-'), NVL(PL_BGD_BACKUP_DATA,'-')" +
				" FROM PLASMA.PL_BASIC_GRAPH_DATA WHERE PL_BI_DATA_NUM = ? ORDER BY TO_NUMBER(PL_BGD_X_AX_VAL)";
				//" FROM PLASMA.PL_BASIC_GRAPH_DATA WHERE PL_BI_DATA_NUM = ? ORDER BY TO_NUMBER(PL_BGD_SEQ_NUM)";
		ComResultSet rs = comSQL.executeSelect(sqlQuery, sqlOption);
		while(rs.next()){
			Graph_Data_Info info = new Graph_Data_Info();		
			info.setPL_BI_DATA_NUM(rs.getString(1));
			info.setPL_BGD_SEQ_NUM(rs.getString(2));
			info.setPL_BGD_X_AX_VAL(rs.getString(3));
			info.setPL_BGD_Y_AX_VAL(rs.getString(4));
			info.setPL_BGD_X_ERR(rs.getString(5));
			info.setPL_BGD_Y_ERR_MAX(rs.getString(6));
			info.setPL_BGD_Y_ERR_MIN(rs.getString(7));
			info.setPL_BGD_RATIO(rs.getString(8));
			info.setPL_BGD_PRESS(rs.getString(9));
			info.setPL_BGD_BACKUP_DATA(rs.getString(10));
			
			values.addElement(info);
		}
		return values;
	}
	
	public Vector<Graph_Data_Info> selectBasicGraphNO(String st_no, String en_no){
		Vector<Graph_Data_Info> values = new Vector<Graph_Data_Info>();
		
		//상세 물성 정보 Query 파라미터 벡터		
		Vector<String> sqlOption = new Vector<String>();
		sqlOption.addElement(st_no);
		sqlOption.addElement(en_no);
		
		//Vector values = new Vector();
/*		String sqlQuery = "SELECT PL_BI_DATA_NUM, PL_BI_EXPRESSION FROM PL_BASIC_INFO" +
				" WHERE PL_BI_DATA_NUM BETWEEN ? AND ? ORDER BY PL_BI_DATA_NUM";*/
		
		String sqlQuery = "SELECT PL_BI_DATA_NUM, PL_BGD_SEQ_NUM, NVL(PL_BGD_X_AX_VAL, '0'), NVL(PL_BGD_Y_AX_VAL, '0')," +
				" NVL(PL_BGD_X_ERR, '0'), NVL(PL_BGD_Y_ERR_MAX, '0'), NVL(PL_BGD_Y_ERR_MIN, '0')," +
				" NVL(PL_BGD_RATIO,'-'), NVL(PL_BGD_PRESS,'-'), NVL(PL_BGD_BACKUP_DATA,'-')" +
				" FROM PLASMA.PL_BASIC_GRAPH_DATA WHERE PL_BI_DATA_NUM BETWEEN ? AND ? ORDER BY PL_BI_DATA_NUM";
				//" FROM PLASMA.PL_BASIC_GRAPH_DATA WHERE PL_BI_DATA_NUM = ? ORDER BY TO_NUMBER(PL_BGD_SEQ_NUM)";
		ComResultSet rs = comSQL.executeSelect(sqlQuery, sqlOption);
		while(rs.next()){
			Graph_Data_Info info = new Graph_Data_Info();		
			info.setPL_BI_DATA_NUM(rs.getString(1));
			info.setPL_BGD_SEQ_NUM(rs.getString(2));
			info.setPL_BGD_X_AX_VAL(rs.getString(3));
			info.setPL_BGD_Y_AX_VAL(rs.getString(4));
			info.setPL_BGD_X_ERR(rs.getString(5));
			info.setPL_BGD_Y_ERR_MAX(rs.getString(6));
			info.setPL_BGD_Y_ERR_MIN(rs.getString(7));
			info.setPL_BGD_RATIO(rs.getString(8));
			info.setPL_BGD_PRESS(rs.getString(9));
			info.setPL_BGD_BACKUP_DATA(rs.getString(10));
			
			values.addElement(info);
		}
		return values;
	}
	
	/**
	 * @MethodName  : selectBasicGraphData
	 * @Date   : 2010. 04. 12 
	 * @MethodDescription : DB에서 pr_no와 dt_no를 통해 그래프 데이터를 가져오는 메소드
	 * @param pr_no
	 * @param dt_no
	 * @return
	 * @History  : - 
	 */
	public Graph_Data_Info selectBasicGraphData(String pr_no, String dt_no){

		Vector<String> sqlOption = new Vector<String>();
		sqlOption.addElement(pr_no);
		sqlOption.addElement(dt_no);
		
		String sqlQuery = "SELECT PL_BI_DATA_NUM, PL_BGD_SEQ_NUM, NVL(PL_BGD_X_AX_VAL, '0'), NVL(PL_BGD_Y_AX_VAL, '0')," +
				" NVL(PL_BGD_X_ERR, '0'), NVL(PL_BGD_Y_ERR_MAX, '0'), NVL(PL_BGD_Y_ERR_MIN, '0')," +
				" NVL(PL_BGD_RATIO,'-'), NVL(PL_BGD_PRESS,'-'), NVL(PL_BGD_BACKUP_DATA,'-')" +
				" FROM PLASMA.PL_BASIC_GRAPH_DATA WHERE PL_BI_DATA_NUM = ? AND PL_BGD_SEQ_NUM = ?";
		ComResultSet rs = comSQL.executeSelect(sqlQuery, sqlOption);
		Graph_Data_Info info = new Graph_Data_Info();
		while(rs.next()){
			info.setPL_BI_DATA_NUM(rs.getString(1));
			info.setPL_BGD_SEQ_NUM(rs.getString(2));
			info.setPL_BGD_X_AX_VAL(rs.getString(3));
			info.setPL_BGD_Y_AX_VAL(rs.getString(4));
			info.setPL_BGD_X_ERR(rs.getString(5));
			info.setPL_BGD_Y_ERR_MAX(rs.getString(6));
			info.setPL_BGD_Y_ERR_MIN(rs.getString(7));
			info.setPL_BGD_RATIO(rs.getString(8));
			info.setPL_BGD_PRESS(rs.getString(9));
			info.setPL_BGD_BACKUP_DATA(rs.getString(10));
		}
		return info;
	}
	
	/**
	 * @MethodName  : selectGraphDataTotalCount
	 * @Date   : 2013. 12. 18. 
	 * @MethodDescription : 물성정보의 시작값과 끝값을 통해 각 물성정보의 그래프 데이터 카운트를 가져오는 메소드
	 * @param st_pr_no
	 * @param ed_pr_no
	 * @return
	 * @History  : - 
	 */
	public Vector<Common_Data> selectGraphDataTotalCount(String st_pr_no, String ed_pr_no){
		Vector<Common_Data> values = new Vector<Common_Data>();
		
		Vector<String> sqlOption = new Vector<String>();
		sqlOption.addElement(st_pr_no);
		sqlOption.addElement(ed_pr_no);
		
		String sqlQuery = "  SELECT PL_BI_DATA_NUM, COUNT (PL_BGD_X_AX_VAL)" +
				" FROM PL_BASIC_GRAPH_DATA" +
				" WHERE PL_BI_DATA_NUM BETWEEN ? AND ? " +
				" GROUP BY PL_BI_DATA_NUM" +
				" ORDER BY PL_BI_DATA_NUM";
		ComResultSet rs = comSQL.executeSelect(sqlQuery, sqlOption);
		
		while(rs.next()){
			Common_Data info = new Common_Data();
			info.setPL_CM_STR1(rs.getString(1)); // 물성번호
			info.setPL_CM_STR2(rs.getString(2)); // 카운트값
			
			values.addElement(info);
		}
		return values;
	}
	
	/**
	 * @MethodName  : updateGraphDataTotalCount
	 * @Date   : 2013. 12. 18. 
	 * @MethodDescription : 기본 테이블에 해당 물성의 수치데이터 개수를 넣는 메소드
	 * @param pr_no
	 * @param pr_cnt
	 * @return
	 * @History  : - 
	 */
	public boolean updateGraphDataTotalCount(String pr_no, String pr_cnt){
		boolean value = false;
		String updateQuery = "UPDATE PLASMA.PL_BASIC_INFO SET PL_BI_NUM_TOTAL_CNT = ? " +
		" WHERE PL_BI_DATA_NUM = ? ";
		
		Vector<String> update_option = new Vector<String>();
		update_option.addElement(pr_cnt);
		update_option.addElement(pr_no);
		value = comSQL.executeTransact(updateQuery, update_option);
		return value;
	}
	
	/**
	 * @MethodName : selectBasicGraphInfoCount
	 * @Desc : 기본 그래프 정보의 개수를 가져오는 메소드
	 * @param pr_no
	 * @return
	 */
	public int selectBasicGraphInfoCount(String pr_no){
		String sqlQuery = "SELECT COUNT(*) FROM PL_BASIC_GRAPH_INFO WHERE PL_BI_DATA_NUM = ?";
		//그래프 정보 Query 파라미터 벡터		
		Vector<String> select_option = new Vector<String>();
		select_option.addElement(pr_no);
		
		int count = comSQL.executeSelectOneInt(sqlQuery, select_option);
		return count;
	}
	
	/**
	 * @MethodName : selectBasicGraphInfo
	 * @Desc : DB에 기본 그래프 정보를 가져오는 메소드
	 * @param sqlOption
	 * @return
	 */
	public Graph_Basic_Info selectBasicGraphInfo(String pr_no){
		String sqlQuery = "SELECT PL_BI_DATA_NUM, PL_BGI_X_AX_UNIT, PL_BGI_X_AX_CAL, PL_BGI_Y_AX_UNIT, PL_BGI_Y_AX_CAL, NVL(PL_BGI_Y_AX_COMM,'-')" +
				" FROM PLASMA.PL_BASIC_GRAPH_INFO WHERE PL_BI_DATA_NUM = ? ";
		//그래프 정보 Query 파라미터 벡터		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(pr_no);
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery, ve_1);

		Graph_Basic_Info info = new Graph_Basic_Info();
		//Ctr_Option_Process ctr_option = new Ctr_Option_Process();
		while(rs.next()){		
		
			info.setPL_BI_DATA_NUM(rs.getString(1));
			info.setPL_BGI_X_AX_UNIT(rs.getString(2));
			info.setPL_BGI_X_AX_CAL(rs.getString(3));
			info.setPL_BGI_Y_AX_UNIT(rs.getString(4));
			info.setPL_BGI_Y_AX_CAL(rs.getString(5));
			info.setPL_BGI_Y_AX_COMM(rs.getString(6));

		}
		return info;
	}
	
	/**
	 * @MethodName : insertBasicGraphInfo
	 * @Desc : DB에 기본 그래프 정보를 입력하는 메소드
	 * 데이터가 있으면, update, 없으면, insert
	 * @param info
	 * @return
	 */
	public boolean insertBasicGraphInfo(Graph_Basic_Info info){
		boolean value = false;
		String selectQuery = "SELECT COUNT(*) FROM PL_BASIC_GRAPH_INFO WHERE PL_BI_DATA_NUM = ?";
		
		Vector<String> select_option = new Vector<String>();
		select_option.addElement(info.getPL_BI_DATA_NUM());
		
		int count = comSQL.executeSelectOneInt(selectQuery, select_option);
		if(count > 0){ //update
			
			String updateQuery = "UPDATE PLASMA.PL_BASIC_GRAPH_INFO SET PL_BI_DATA_NUM = ?, " +
					" PL_BGI_X_AX_UNIT = ?, PL_BGI_X_AX_CAL = ?, PL_BGI_Y_AX_UNIT = ?, " +
					"PL_BGI_Y_AX_CAL = ?, PL_BGI_Y_AX_COMM = ? WHERE PL_BI_DATA_NUM = ? ";
			
			Vector<String> update_option = new Vector<String>();
			update_option.addElement(info.getPL_BI_DATA_NUM());
			update_option.addElement(info.getPL_BGI_X_AX_UNIT());
			update_option.addElement(info.getPL_BGI_X_AX_CAL());
			update_option.addElement(info.getPL_BGI_Y_AX_UNIT());
			update_option.addElement(info.getPL_BGI_Y_AX_CAL());
			update_option.addElement(ComUtil.isNullToDashString(info.getPL_BGI_Y_AX_COMM()));
			update_option.addElement(info.getPL_BI_DATA_NUM());			
			value = comSQL.executeTransact(updateQuery, update_option);
			
		}else{ //insert
	
			String insertQuery = "INSERT INTO PLASMA.PL_BASIC_GRAPH_INFO (" +
			" PL_BI_DATA_NUM, PL_BGI_X_AX_UNIT, PL_BGI_X_AX_CAL," +
			" PL_BGI_Y_AX_UNIT, PL_BGI_Y_AX_CAL, PL_BGI_Y_AX_COMM)" +
			" VALUES ( ?, ?, ?, ?, ?, ?)";
			
			Vector<String> insert_option = new Vector<String>();
			insert_option.addElement(info.getPL_BI_DATA_NUM());
			insert_option.addElement(info.getPL_BGI_X_AX_UNIT());
			insert_option.addElement(info.getPL_BGI_X_AX_CAL());
			insert_option.addElement(info.getPL_BGI_Y_AX_UNIT());
			insert_option.addElement(info.getPL_BGI_Y_AX_CAL());
			insert_option.addElement(ComUtil.isNullToDashString(info.getPL_BGI_Y_AX_COMM()));
			value = comSQL.executeTransact(insertQuery, insert_option);
		}	
		
		return value;
	}
	
	/**
	 * @MethodName : selectBasicGraphDataNum
	 * @Desc : DB에 그래프 데이터의 순번을 가져오는 함수
	 * @return
	 */
	public int selectBasicGraphDataNum(String pr_no){
		int value = 0;
		//그래프 정보 Query 파라미터 벡터		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(pr_no);
			
		String sqlQuery = "SELECT MAX(TO_NUMBER(PL_BGD_SEQ_NUM))+1 FROM PL_BASIC_GRAPH_DATA" +
				" WHERE PL_BI_DATA_NUM = ?";
		
		value = comSQL.executeSelectOneInt(sqlQuery, ve_1);//comSQL.executeSelectOneInt(sqlQuery);
		
		return value;
	}
	
	/**
	 * @MethodName : selectInfGrdGraphDataNum
	 * @Desc : DB에 등급유력 그래프 데이터의 순번을 가져오는 함수
	 * @return
	 */
	public int selectInfGrdGraphDataNum(String pr_no){
		int value = 0;
		//그래프 정보 Query 파라미터 벡터		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(pr_no);
			
		String sqlQuery = "SELECT MAX(TO_NUMBER(PL_IGGD_SEQ_NUM))+1 FROM PLASMA.PL_INF_GRD_GRAPH_DATA" +
				" WHERE PL_IGBI_DATA_NUM = ?";
		
		value = comSQL.executeSelectOneInt(sqlQuery, ve_1);//comSQL.executeSelectOneInt(sqlQuery);
		
		return value;
	}
	
	/**
	 * @MethodName : insertBasicGraphData
	 * @Desc : DB에 기본 그래프 데이터를 입력하는 메소드
	 * @param info
	 * @return
	 */
	public boolean insertBasicGraphData(Graph_Data_Info info){
		boolean value = false;
		String sqlQuery = "INSERT INTO PLASMA.PL_BASIC_GRAPH_DATA (" +
				" PL_BI_DATA_NUM, PL_BGD_SEQ_NUM, PL_BGD_X_AX_VAL, PL_BGD_Y_AX_VAL," +
				" PL_BGD_X_ERR, PL_BGD_Y_ERR_MAX, PL_BGD_Y_ERR_MIN," +
				" PL_BGD_RATIO, PL_BGD_PRESS, PL_BGD_BACKUP_DATA)" +
				" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		value = comSQL.executeTransact(sqlQuery, info.getDataList());
		
		return value;
	}
	
	public boolean updateBasicGraphData(Graph_Data_Info info){
		boolean value = false;
		String updateQuery = "UPDATE PLASMA.PL_BASIC_GRAPH_DATA" +
				" SET	PL_BI_DATA_NUM = ?," +
				"	PL_BGD_SEQ_NUM = ?," +
				"	PL_BGD_X_AX_VAL = ?," +
				"	PL_BGD_Y_AX_VAL = ?," +
				"	PL_BGD_X_ERR = ?," +
				"	PL_BGD_Y_ERR_MAX = ?," +
				"	PL_BGD_Y_ERR_MIN = ?," +
				"	PL_BGD_RATIO = ?," +
				"	PL_BGD_PRESS = ?," +
				"	PL_BGD_BACKUP_DATA = ? " +
				"WHERE PL_BI_DATA_NUM = ? AND PL_BGD_SEQ_NUM = ?";
		
		Vector<String> update_option = new Vector<String>();
		update_option.addElement(info.getPL_BI_DATA_NUM());
		update_option.addElement(info.getPL_BGD_SEQ_NUM());
		update_option.addElement(info.getPL_BGD_X_AX_VAL());
		update_option.addElement(info.getPL_BGD_Y_AX_VAL());
		update_option.addElement(info.getPL_BGD_X_ERR());
		update_option.addElement(info.getPL_BGD_Y_ERR_MAX());
		update_option.addElement(info.getPL_BGD_Y_ERR_MIN());
		update_option.addElement(info.getPL_BGD_RATIO());
		update_option.addElement(info.getPL_BGD_PRESS());
		update_option.addElement(info.getPL_BGD_BACKUP_DATA());
		update_option.addElement(info.getPL_BI_DATA_NUM());
		update_option.addElement(info.getPL_BGD_SEQ_NUM());
			
		value = comSQL.executeTransact(updateQuery, update_option);
		
		return value;
	}
	
	public boolean insertTestBasicGraphExcelData(ArrayList<?> info){
		boolean value = false;
		String sqlQuery = "INSERT INTO PLASMA.PL_TEST_GRAPH_DATA (" +
				" PL_BI_DATA_NUM, PL_TGD_SEQ_NUM, PL_TGD_X_AX_VAL, PL_TGD_Y_AX_VAL," +
				" PL_TGD_X_ERR, PL_TGD_Y_ERR_MAX, PL_TGD_Y_ERR_MIN, PL_TGD_RATIO, PL_TGD_PRESS)" +
				" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		value = comSQL.executeTransact(sqlQuery, info);
		
		return value;
	}
	/**
	 * @MethodName  : insertBasicGraphExcelData
	 * @Date   : 2011. 6. 27. 
	 * @MethodDescription : 기본 그래프 데이터(Excel)를 DB에 입력하는 메소드
	 * @param info
	 * @return
	 * @History  : - 
	 */
	public boolean insertBasicGraphExcelData(ArrayList<?> info){
		boolean value = false;
		String sqlQuery = "INSERT INTO PLASMA.PL_BASIC_GRAPH_DATA (" +
				" PL_BI_DATA_NUM, PL_BGD_SEQ_NUM, PL_BGD_X_AX_VAL, PL_BGD_Y_AX_VAL," +
				" PL_BGD_X_ERR, PL_BGD_Y_ERR_MAX, PL_BGD_Y_ERR_MIN, PL_BGD_RATIO, PL_BGD_PRESS)" +
				" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		value = comSQL.executeTransact(sqlQuery, info);
		
		return value;
	}
	
	/**
	 * @MethodName  : insertFinalGraphExcelData
	 * @Date   : 2011. 6. 27. 
	 * @MethodDescription : 최종 그래프 데이터(Excel)를 DB에 입력하는 메소드
	 * @param info
	 * @return
	 * @History  : - 
	 */
	public boolean insertFinalGraphExcelData(ArrayList<?> info){
		boolean value = false;
		String sqlQuery = "INSERT INTO PLASMA.PL_INF_GRD_GRAPH_DATA (" +
				" PL_IGBI_DATA_NUM, PL_IGGD_SEQ_NUM, PL_IGGD_X_AX_VAL, PL_IGGD_Y_AX_VAL," +
				" PL_IGGD_X_ERR, PL_IGGD_Y_ERR_MAX, PL_IGGD_Y_ERR_MIN, PL_BGD_RATIO, PL_BGD_PRESS)" +
				" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		value = comSQL.executeTransact(sqlQuery, info);
		
		return value;
	}
	
	/**
	 * @MethodName  : insertFinalGraphData
	 * @Date   : 2011. 7. 6. 
	 * @MethodDescription : 최종 그래프 데이터를 DB에 입력하는 메소드 
	 * @param info
	 * @return
	 * @History  : - 
	 */
	public boolean insertFinalGraphData(Graph_Data_Info info){
		boolean value = false;
		
		String sqlQuery = "INSERT INTO PLASMA.PL_INF_GRD_GRAPH_DATA (" +
		" PL_IGBI_DATA_NUM, PL_IGGD_SEQ_NUM, PL_IGGD_X_AX_VAL, PL_IGGD_Y_AX_VAL," +
		" PL_IGGD_X_ERR, PL_IGGD_Y_ERR_MAX, PL_IGGD_Y_ERR_MIN," +
		" PL_IGGD_RATIO, PL_IGGD_PRESS, PL_IGGD_BACKUP_DATA)" +
		" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		value = comSQL.executeTransact(sqlQuery, info.getDataList());
			
		return value;
	}
	
	/**
	 * @MethodName  : deleteGraphData
	 * @Date   : 2012. 1. 12. 
	 * @MethodDescription : 수치데이터 삭제
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean deleteGraphData(String pr_no){
		
		boolean r_value = false;
		String sql_Query = "delete from PLASMA.PL_BASIC_GRAPH_DATA where PL_BI_DATA_NUM = ?";
		Vector<String> sql_option = new Vector<String>();
		sql_option.addElement(pr_no);
		
		r_value = comSQL.executeTransact(sql_Query, sql_option);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : updateGraphXPointData
	 * @Date   : 2013. 8. 26. 
	 * @MethodDescription : X축 데이터의 소수점 자리 수정하는 메소드
	 * @param pr_no
	 * @param x_val
	 * @param seq
	 * @return
	 * @History  : - 
	 */
	public boolean updateGraphXPointData(String pr_no, String x_val, String seq){
		boolean r_value = false;
		//String sql_Query = "delete from PLASMA.PL_BASIC_GRAPH_DATA where PL_BI_DATA_NUM = ?";
		
		String sql_Query = "UPDATE PL_BASIC_GRAPH_DATA SET PL_BGD_X_AX_VAL = ? WHERE PL_BI_DATA_NUM = ? AND PL_BGD_SEQ_NUM= ? ";
		
		Vector<String> sql_option = new Vector<String>();
		sql_option.addElement(x_val);
		sql_option.addElement(pr_no);
		sql_option.addElement(seq);
		
		r_value = comSQL.executeTransact(sql_Query, sql_option);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : updateGraphYPointData
	 * @Date   : 2013. 8. 26. 
	 * @MethodDescription : Y축 데이터의 소수점 자리 수정하는 메소드
	 * @param pr_no
	 * @param y_val
	 * @param seq
	 * @return
	 * @History  : - 
	 */
	public boolean updateGraphYPointData(String pr_no, String y_val, String seq){
		boolean r_value = false;
		//String sql_Query = "delete from PLASMA.PL_BASIC_GRAPH_DATA where PL_BI_DATA_NUM = ?";
		
		String sql_Query = "UPDATE PL_BASIC_GRAPH_DATA SET PL_BGD_Y_AX_VAL = ? WHERE PL_BI_DATA_NUM = ? AND PL_BGD_SEQ_NUM= ? ";
		
		Vector<String> sql_option = new Vector<String>();
		sql_option.addElement(y_val);
		sql_option.addElement(pr_no);
		sql_option.addElement(seq);
		
		r_value = comSQL.executeTransact(sql_Query, sql_option);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : updateGraphYErrMaxPointData
	 * @Date   : 2013. 8. 26. 
	 * @MethodDescription : Y축 데이터 Max 오차값의 소수점 자리 수정하는 메소드
	 * @param pr_no
	 * @param y_max_val
	 * @param seq
	 * @return
	 * @History  : - 
	 */
	public boolean updateGraphYErrMaxPointData(String pr_no, String y_max_val, String seq){
		boolean r_value = false;
		//String sql_Query = "delete from PLASMA.PL_BASIC_GRAPH_DATA where PL_BI_DATA_NUM = ?";
		
		String sql_Query = "UPDATE PL_BASIC_GRAPH_DATA SET PL_BGD_Y_ERR_MAX = ? WHERE PL_BI_DATA_NUM = ? AND PL_BGD_SEQ_NUM= ? ";
		
		Vector<String> sql_option = new Vector<String>();
		sql_option.addElement(y_max_val);
		sql_option.addElement(pr_no);
		sql_option.addElement(seq);
		
		r_value = comSQL.executeTransact(sql_Query, sql_option);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : updateGraphYErrMinPointData
	 * @Date   : 2013. 8. 26. 
	 * @MethodDescription : Y축 데이터 Min 오차값의 소수점 자리 수정하는 메소드
	 * @param pr_no
	 * @param y_min_val
	 * @param seq
	 * @return
	 * @History  : - 
	 */
	public boolean updateGraphYErrMinPointData(String pr_no, String y_min_val, String seq){
		boolean r_value = false;
		//String sql_Query = "delete from PLASMA.PL_BASIC_GRAPH_DATA where PL_BI_DATA_NUM = ?";
		
		String sql_Query = "UPDATE PL_BASIC_GRAPH_DATA SET PL_BGD_Y_ERR_MIN = ? WHERE PL_BI_DATA_NUM = ? AND PL_BGD_SEQ_NUM= ? ";
		
		Vector<String> sql_option = new Vector<String>();
		sql_option.addElement(y_min_val);
		sql_option.addElement(pr_no);
		sql_option.addElement(seq);
		
		r_value = comSQL.executeTransact(sql_Query, sql_option);
		
		return r_value;
	}
}
