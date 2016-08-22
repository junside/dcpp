package nfri.dcpp.properties.db;

import java.util.Vector;

import nfri.dcpp.com.db.ComResultSet;
import nfri.dcpp.com.db.ComSQLExecution;
import nfri.dcpp.properties.model.Fitting_Temp_Info;
import nfri.dcpp.properties.model.Graph_Data_Info;
import nfri.dcpp.properties.model.Inf_Grd_Properties_Basic_Info;
import nfri.dcpp.properties.model.Properties_Assess_Info;
import nfri.dcpp.properties.model.Properties_Assess_List_Info;
import nfri.dcpp.properties.model.Properties_Assess_Search_List;
import nfri.dcpp.properties.model.Properties_Final_Assess_Info;

/**
 * @Project: dcpp
 * @Title  : DAO_ASSESS_INFO.java
 * @Description : 데이터 평가와 관련된 테이블에 대하여 SQL을 처리하는 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2010. 07. 15
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */
public class DAO_ASSESS_INFO {
	
	//DB 실행 클래스 선언
	ComSQLExecution comSQL;
	
	public DAO_ASSESS_INFO() {
		comSQL = new ComSQLExecution();
	}
	
	/**
	 * @MethodName : selectProperyList
	 * @Desc : 물성평가 대상정보를 가져오는 메소드
	 * @return
	 */
	public Vector<Properties_Assess_List_Info> selectAssessList(){
		Vector<Properties_Assess_List_Info> values = new Vector<Properties_Assess_List_Info>();
		
		String sqlQuery = "  SELECT BASIC.PL_BI_DATA_NUM, BASIC.PL_RAI_ARTCL_NUM, NVL(EVAL.PL_PEI_FINAL_FLAG,'N')" +
				" FROM PL_BASIC_INFO BASIC, PL_PRIM_EVAL_INFO EVAL" +
				" WHERE EVAL.PL_BI_DATA_NUM(+) = BASIC.PL_BI_DATA_NUM" +
				" AND BASIC.PL_BI_INSERT_FLAG = 'C'" +
				" ORDER BY BASIC.PL_BI_DATA_NUM";
		ComResultSet rs = comSQL.executeSelect(sqlQuery);
		
		while(rs.next()){	
			Properties_Assess_List_Info property_Info = new Properties_Assess_List_Info();
			property_Info.setPL_BI_DATA_NUM(rs.getString(1));
			property_Info.setPL_RAI_ARTCL_NUM(rs.getString(2));
			property_Info.setPL_ASSESS_DATA(rs.getString(3));
			values.addElement(property_Info);
		}
		return values;
	}
	
	/**
	 * @MethodName  : selectFinalAssessList
	 * @Date   : 2010. 09. 09 
	 * @MethodDescription : 최종평가 데이터를 조회하는 메소드
	 * @return
	 * @History  : - 
	 */
	public Vector<Inf_Grd_Properties_Basic_Info> selectFinalAssessList(){
		Vector<Inf_Grd_Properties_Basic_Info> values = new Vector<Inf_Grd_Properties_Basic_Info>();
		
		String sqlQuery = "  SELECT P.PL_IGBI_DATA_NUM, P.PL_IGBI_MAIN_PROC, P.PL_IGBI_SUB_PROC," +
				" P.PL_IGBI_EXPRESSION, P.PL_UI_ID, P.PL_IGBI_DATA_NUM_LIST, P.PL_IGBI_INSERT_FLAG" +
				" FROM PLASMA.PL_INF_GRD_BASIC_INFO P" +
				" ORDER BY P.PL_IGBI_DATA_NUM";
		ComResultSet rs = comSQL.executeSelect(sqlQuery);
		
		while(rs.next()){	
			Inf_Grd_Properties_Basic_Info property_Info = new Inf_Grd_Properties_Basic_Info();
			property_Info.setPL_IGBI_DATA_NUM(rs.getString(1));
			property_Info.setPL_IGBI_MAIN_PROC(rs.getString(2));
			property_Info.setPL_IGBI_SUB_PROC(rs.getString(3));
			property_Info.setPL_IGBI_EXPRESSION(rs.getString(4));
			property_Info.setPL_UI_ID(rs.getString(5));
			property_Info.setPL_IGBI_DATA_NUM_LIST(rs.getString(6));
			property_Info.setPL_IGBI_INSERT_FLAG(rs.getString(7));
			values.addElement(property_Info);
		}
		return values;
	}
	
	/**
	 * @MethodName  : selectAssessBasicInfo
	 * @Date   : 2010. 10. 19 
	 * @MethodDescription : 평가 기본 데이터 가져오는 메소드
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public Properties_Assess_List_Info selectAssessBasicInfo(String pr_no){
		Vector<String> sqlOption = new Vector<String>();
		sqlOption.addElement(pr_no);
		
		String sqlQuery = "  SELECT BASIC.PL_BI_DATA_NUM, BASIC.PL_RAI_ARTCL_NUM, NVL(EVAL.PL_PEI_FINAL_FLAG,'N')" +
				" FROM PL_BASIC_INFO BASIC, PL_PRIM_EVAL_INFO EVAL" +
				" WHERE EVAL.PL_BI_DATA_NUM(+) = BASIC.PL_BI_DATA_NUM" +
				" AND BASIC.PL_BI_INSERT_FLAG = 'C'" +
				" AND BASIC.PL_BI_DATA_NUM = ?";
		ComResultSet rs = comSQL.executeSelect(sqlQuery, sqlOption);
		Properties_Assess_List_Info property_Info = new Properties_Assess_List_Info();

		while(rs.next()){	
			//Properties_Assess_List_Info property_Info = new Properties_Assess_List_Info();
			property_Info.setPL_BI_DATA_NUM(rs.getString(1));
			property_Info.setPL_RAI_ARTCL_NUM(rs.getString(2));
			property_Info.setPL_ASSESS_DATA(rs.getString(3));
			//values.addElement(property_Info);
		}
		return property_Info;
	}
	/**
	 * @MethodName : selectAssessDocNumber
	 * @Desc : 평가문서에 포함될 평가문서번호를 따내는 메소드
	 * @return
	 */
	public String selectAssessDocNumber(){
		//결과
		String resultStr = "";
		//DB에 저장된 시퀀스 가져오기
		//String table_seqQuery = "SELECT SEQ_NUM FROM ";
		//현재 날짜 가져오기
		String cur_dateQuery = "SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD') FROM DUAL";
		//DB에 저장된 날짜 가져오기
		String table_dateQuery = "SELECT PL_SML_SEQ_DATE FROM PL_SEQ_MGT_LIST";
		
		String dateStr = comSQL.executeSelectOneStr(cur_dateQuery);
		
		//DB에 저장된 날짜를 가져와서 현재 날짜와 비교하여 이전 날짜 이면 
		//DB에 시퀀스를 다시 리셋 후 번호를 받음.
		
		//시퀀스 가져오기
		String cur_seqQuery = "SELECT PL_ASSESS_DOC_SEQ.nextval FROM DUAL";
		String seqStr = comSQL.executeSelectOneStr(cur_seqQuery);
		
		//테이블에 시퀀스 이름과 날짜 입력하기
		
		return resultStr;
	}
	
	/**
	 * @MethodName : selectAssessInfo
	 * @Desc : 평가 정보를 가져오는 메소드
	 * @param pr_no
	 * @return
	 */
	public Properties_Assess_Info selectAssessInfo(String pr_no){
		String sqlQuery = "SELECT PL_BI_DATA_NUM, PL_PEI_1STEP, PL_PEI_2STEP, PL_PEI_3STEP, PL_PEI_4STEP, PL_PEI_5STEP," +
				" PL_PEI_6STEP, PL_PEI_PRIM_EVAL, PL_PEI_METHOD, PL_PEI_SCIENT_BASIS, PL_PEI_SCIENT_LIMIT, PL_PEI_PRIMARY_FACT," +
				" PL_PEI_DATA_ANALY, PL_PEI_MEASUREMENT, PL_PEI_DIRECT, PL_PEI_INDIRECT, PL_PEI_FINAL_FLAG, PL_PEI_ASSESS_USER" +
				" FROM PL_PRIM_EVAL_INFO" +
				" WHERE PL_BI_DATA_NUM = ?";
		
		Vector<String> sqlOption = new Vector<String>();
		sqlOption.addElement(pr_no);
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery, sqlOption);
		Properties_Assess_Info property_Info = new Properties_Assess_Info();
		
		while(rs.next()){			
			property_Info.setPL_BI_DATA_NUM(rs.getString(1));
			property_Info.setPL_PEI_1STEP(rs.getString(2));
			property_Info.setPL_PEI_2STEP(rs.getString(3));
			property_Info.setPL_PEI_3STEP(rs.getString(4));
			property_Info.setPL_PEI_4STEP(rs.getString(5));
			property_Info.setPL_PEI_5STEP(rs.getString(6));
			property_Info.setPL_PEI_6STEP(rs.getString(7));
			property_Info.setPL_PEI_PRIM_EVAL(rs.getString(8));
			property_Info.setPL_PEI_METHOD(rs.getString(9));
			property_Info.setPL_PEI_SCIENT_BASIS(rs.getString(10));
			property_Info.setPL_PEI_SCIENT_LIMIT(rs.getString(11));
			property_Info.setPL_PEI_PRIMARY_FACT(rs.getString(12));
			property_Info.setPL_PEI_DATA_ANALY(rs.getString(13));
			property_Info.setPL_PEI_MEASUREMENT(rs.getString(14));
			property_Info.setPL_PEI_DIRECT(rs.getString(15));
			property_Info.setPL_PEI_INDIRECT(rs.getString(16));
			property_Info.setPL_PEI_FINAL_FLAG(rs.getString(17));
			property_Info.setPL_PEI_ASSESS_USER(rs.getString(18));

		}
		return property_Info;
	}
	
	/**
	 * @MethodName : selectAssessInfoExist
	 * @Desc : 해당 물성 정보에 대한 평가 정보의 카운트를 가져오는 메소드
	 * @param pr_no
	 * @return
	 */
	public boolean selectAssessInfoExist(String pr_no){
		boolean r_value = false;
		String sqlQuery ="SELECT COUNT(*) FROM PL_PRIM_EVAL_INFO" +
				" WHERE PL_BI_DATA_NUM = ?";
		
		Vector<String> sqlOption = new Vector<String>();
		sqlOption.addElement(pr_no);
		
		int count = comSQL.executeSelectOneInt(sqlQuery, sqlOption);
		
		if(count > 0){
			r_value = true;
		}
		
		return r_value;
	}
	
	/**
	 * @MethodName : insertAssessInfo
	 * @Desc : 평가 정보를 DB에 입력하는 메소드
	 * @param info
	 * @return
	 */
	public boolean insertAssessInfo(Properties_Assess_Info info){
		boolean r_value = false;
		
		String sqlQuery = "INSERT INTO PL_PRIM_EVAL_INFO (" +
				" PL_BI_DATA_NUM, PL_PEI_1STEP, PL_PEI_2STEP," +
				" PL_PEI_3STEP, PL_PEI_4STEP, PL_PEI_5STEP," +
				" PL_PEI_6STEP, PL_PEI_PRIM_EVAL, PL_PEI_METHOD," +
				" PL_PEI_SCIENT_BASIS, PL_PEI_SCIENT_LIMIT, PL_PEI_PRIMARY_FACT," +
				" PL_PEI_DATA_ANALY, PL_PEI_MEASUREMENT, PL_PEI_DIRECT," +
				" PL_PEI_INDIRECT, PL_PEI_FINAL_FLAG, PL_PEI_ASSESS_USER," +
				" PL_PEI_ASSESS_DATE, PL_PEI_DATA_BRANCH, PL_PEI_PROCESS," +
				" PL_PEI_EXP_THE_REC, PL_PEI_X_UNIT, PL_PEI_Y_UNIT," +
				" PL_PEI_EXPRESS)" +
				" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Vector<String> sqlOption = new Vector<String>();
		sqlOption.addElement(info.getPL_BI_DATA_NUM());
		sqlOption.addElement(info.getPL_PEI_1STEP());
		sqlOption.addElement(info.getPL_PEI_2STEP());
		sqlOption.addElement(info.getPL_PEI_3STEP());
		sqlOption.addElement(info.getPL_PEI_4STEP());
		sqlOption.addElement(info.getPL_PEI_5STEP());
		sqlOption.addElement(info.getPL_PEI_6STEP());
		sqlOption.addElement(info.getPL_PEI_PRIM_EVAL());
		sqlOption.addElement(info.getPL_PEI_METHOD());
		sqlOption.addElement(info.getPL_PEI_SCIENT_BASIS());
		sqlOption.addElement(info.getPL_PEI_SCIENT_LIMIT());
		sqlOption.addElement(info.getPL_PEI_PRIMARY_FACT());
		sqlOption.addElement(info.getPL_PEI_DATA_ANALY());
		sqlOption.addElement(info.getPL_PEI_MEASUREMENT());
		sqlOption.addElement(info.getPL_PEI_DIRECT());
		sqlOption.addElement(info.getPL_PEI_INDIRECT());
		sqlOption.addElement(info.getPL_PEI_FINAL_FLAG());
		sqlOption.addElement(info.getPL_PEI_ASSESS_USER());
		sqlOption.addElement(info.getPL_PEI_ASSESS_DATE());
		sqlOption.addElement(info.getPL_PEI_DATA_BRANCH());
		sqlOption.addElement(info.getPL_PEI_PROCESS());
		sqlOption.addElement(info.getPL_PEI_EXP_THE_REC());
		sqlOption.addElement(info.getPL_PEI_X_UNIT());
		sqlOption.addElement(info.getPL_PEI_Y_UNIT());
		sqlOption.addElement(info.getPL_PEI_EXPRESS());
		r_value = comSQL.executeTransact(sqlQuery, sqlOption);
		
		return r_value;
	}
	
	/**
	 * @MethodName : updateAssessInfo
	 * @Desc : 평가 정보를 DB에 업데이트 하는 메소드
	 * @param info
	 * @return
	 */
	public boolean updateAssessInfo(Properties_Assess_Info info){
			boolean r_value = false;
			
			String sqlQuery = "UPDATE PLASMA.PL_PRIM_EVAL_INFO" +
					" SET   PL_PEI_1STEP        = ?," +
					"       PL_PEI_2STEP        = ?," +
					"       PL_PEI_3STEP        = ?," +
					"       PL_PEI_4STEP        = ?," +
					"       PL_PEI_5STEP        = ?," +
					"       PL_PEI_6STEP        = ?," +
					"       PL_PEI_PRIM_EVAL    = ?," +
					"       PL_PEI_METHOD       = ?," +
					"       PL_PEI_SCIENT_BASIS = ?," +
					"       PL_PEI_SCIENT_LIMIT = ?," +
					"       PL_PEI_PRIMARY_FACT = ?," +
					"       PL_PEI_DATA_ANALY   = ?," +
					"       PL_PEI_MEASUREMENT  = ?," +
					"       PL_PEI_DIRECT       = ?," +
					"       PL_PEI_INDIRECT     = ?," +
					"       PL_PEI_FINAL_FLAG   = ?," +
					"       PL_PEI_ASSESS_USER  = ?," +
					"       PL_PEI_ASSESS_DATE  = ?," +
					"		PL_PEI_DATA_BRANCH  = ?," +
					"		PL_PEI_PROCESS  	= ?," +
					" 		PL_PEI_EXP_THE_REC  = ?," +
					"		PL_PEI_X_UNIT		= ?," +
					"		PL_PEI_Y_UNIT  		= ?," +
					"		PL_PEI_EXPRESS  = ?" +
					" WHERE PL_BI_DATA_NUM =?";
			
			Vector<String> sqlOption = new Vector<String>();			
			sqlOption.addElement(info.getPL_PEI_1STEP());
			sqlOption.addElement(info.getPL_PEI_2STEP());
			sqlOption.addElement(info.getPL_PEI_3STEP());
			sqlOption.addElement(info.getPL_PEI_4STEP());
			sqlOption.addElement(info.getPL_PEI_5STEP());
			sqlOption.addElement(info.getPL_PEI_6STEP());
			sqlOption.addElement(info.getPL_PEI_PRIM_EVAL());
			sqlOption.addElement(info.getPL_PEI_METHOD());
			sqlOption.addElement(info.getPL_PEI_SCIENT_BASIS());
			sqlOption.addElement(info.getPL_PEI_SCIENT_LIMIT());
			sqlOption.addElement(info.getPL_PEI_PRIMARY_FACT());
			sqlOption.addElement(info.getPL_PEI_DATA_ANALY());
			sqlOption.addElement(info.getPL_PEI_MEASUREMENT());
			sqlOption.addElement(info.getPL_PEI_DIRECT());
			sqlOption.addElement(info.getPL_PEI_INDIRECT());
			sqlOption.addElement(info.getPL_PEI_FINAL_FLAG());
			sqlOption.addElement(info.getPL_PEI_ASSESS_USER());
			sqlOption.addElement(info.getPL_PEI_ASSESS_DATE());
			sqlOption.addElement(info.getPL_PEI_DATA_BRANCH());
			sqlOption.addElement(info.getPL_PEI_PROCESS());
			sqlOption.addElement(info.getPL_PEI_EXP_THE_REC());
			sqlOption.addElement(info.getPL_PEI_X_UNIT());
			sqlOption.addElement(info.getPL_PEI_Y_UNIT());
			sqlOption.addElement(info.getPL_PEI_EXPRESS());			
			sqlOption.addElement(info.getPL_BI_DATA_NUM());
			r_value = comSQL.executeTransact(sqlQuery, sqlOption);
			
			return r_value;
	}
	
	/**
	 * @MethodName  : selectValidSeq
	 * @Date   : 2010. 06. 21 
	 * @MethodDescription : 등급유력물성번호를 만들기 위한SEQ를 가져오는 메소드
	 * @return
	 * @History  : - 
	 */
	public String selectValidSeq(){
		String sqlQuery = "SELECT PL_INF_GRD_SEQ.nextval FROM DUAL";
		String resultStr = comSQL.executeSelectOneStr(sqlQuery);
		return resultStr;
	}
	
	/**
	 * @MethodName  : selectValidProSaveYearInfo
	 * @Date   : 2011. 4. 6. 
	 * @MethodDescription : DB에서 입력년도 가져오기
	 * @return
	 * @History  : - 
	 */
	public String selectValidProSaveYearInfo(){
		String r_value = "";
		
		String sqlQuery = "SELECT P.PYI_INF_GRD_YEAR FROM PLASMA.PL_YEAR_INFO P";
		r_value = comSQL.executeSelectOneStr(sqlQuery);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : inserValidProtSaveYear
	 * @Date   : 2011. 4. 6. 
	 * @MethodDescription : DB에 입력년도 저장하기
	 * @param year
	 * @return
	 * @History  : - 
	 */
	public boolean insertValidProSaveYear(String year){
		boolean r_value = false;
		
		String sqlQuery = "INSERT INTO PLASMA.PL_YEAR_INFO (PYI_INF_GRD_YEAR) VALUES ( ? )";
		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(year);
		
		r_value = comSQL.executeTransact(sqlQuery, ve_1);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : updateValidProSaveYear
	 * @Date   : 2011. 4. 6. 
	 * @MethodDescription : DB에 입력년도 업데이트하기
	 * @param year
	 * @return
	 * @History  : - 
	 */
	public boolean updateValidProSaveYear(String year){
		boolean r_value = false;
		
		String sqlQuery = "UPDATE PLASMA.PL_YEAR_INFO SET    PYI_INF_GRD_YEAR = ?";
		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(year);
		
		r_value = comSQL.executeTransact(sqlQuery, ve_1);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : resetValidSeq
	 * @Date   : 2011. 4. 6. 
	 * @MethodDescription : 등급유력물성번호 생성하는 SEQ 리셋하는 메소드
	 * @return
	 * @History  : - 
	 */
	public boolean resetValidSeq(){
		boolean r_value = false;
		
		String sqlDrop = "DROP SEQUENCE PLASMA.PL_PROPERTY_SEQ";

		String sqlCreate = "CREATE SEQUENCE PLASMA.PL_INF_GRD_SEQ START WITH 1" +
				" MAXVALUE 100000000 MINVALUE 1" +
				" CYCLE NOCACHE ORDER";
		
		String sqlQuery = "ALTER SEQUENCE PLASMA.PL_INF_GRD_SEQ" +
				"  START WITH 1" +
				"  MAXVALUE 100000000" +
				"  MINVALUE 1" +
				"  CYCLE" +
				"  NOCACHE" +
				"  ORDER";
		
		r_value = comSQL.executeTransact(sqlDrop);
		
		if(r_value == true){
			r_value = comSQL.executeTransact(sqlCreate);
		}
		
		return r_value;
	}
	
	/**
	 * @MethodName  : selectFinalAssessInfo
	 * @Date   : 2010. 10. 19 
	 * @MethodDescription : 최종평가 정보를 가져오는 메소드
	 * @param v_pr_no
	 * @return
	 * @History  : - 
	 */
	public Properties_Final_Assess_Info selectFinalAssessInfo(String v_pr_no){
		String sqlQuery = "  SELECT PL_IGBI_DATA_NUM, PL_SEI_PROD_METHOD_BEST, PL_SEI_ASSESS_CONTENT," +
				" PL_SEI_ENERGY_RANGE, PL_SEI_FINAL_OPINION, PL_SEI_STD_REF_DATA," +
				" PL_SEI_COLLECT_PERIOD, PL_SEI_PROD_METHOD_AVERAGE, PL_SEI_ASSESS_DATE," +
				" PL_SEI_X_UNIT, PL_SEI_Y_UNIT, PL_SEI_X_MAX, PL_SEI_X_MIN" +
				" FROM PL_SECOND_EVAL_INFO" +
				" WHERE PL_IGBI_DATA_NUM = ?";
		
		Vector<String> sqlOption = new Vector<String>();
		sqlOption.addElement(v_pr_no);
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery, sqlOption);
		Properties_Final_Assess_Info assess_info = new Properties_Final_Assess_Info();

		while(rs.next()){	
			assess_info.setPL_IGBI_DATA_NUM(rs.getString(1));
			assess_info.setPL_SEI_PROD_METHOD_BEST(rs.getString(2));
			assess_info.setPL_SEI_ASSESS_CONTENT(rs.getString(3));
			assess_info.setPL_SEI_ENERGY_RANGE(rs.getString(4));
			assess_info.setPL_SEI_FINAL_OPINION(rs.getString(5));
			assess_info.setPL_SEI_STD_REF_DATA(rs.getString(6));
			assess_info.setPL_SEI_COLLECT_PERIOD(rs.getString(7));
			assess_info.setPL_SEI_PROD_METHOD_AVERAGE(rs.getString(8));
			assess_info.setPL_SEI_ASSESS_DATE(rs.getString(9));
			assess_info.setPL_SEI_X_UNIT(rs.getString(10));
			assess_info.setPL_SEI_Y_UNIT(rs.getString(11));
			assess_info.setPL_SEI_X_MAX(rs.getString(12));
			assess_info.setPL_SEI_X_MIN(rs.getString(13));
		}
		
		return assess_info;
	}

	/**
	 * @MethodName  : insertFinalAssessInfo
	 * @Date   : 2010. 08. 31 
	 * @MethodDescription : 최종 평가 결과를 입력하는 메소드
	 * @param final_assess
	 * @return
	 * @History  : - 
	 */
	public boolean insertFinalAssessInfo(Properties_Final_Assess_Info info) {
		boolean r_value = false;

		//4. 평가 정보 입력하기
		String insertAssessInfoQuery = "INSERT INTO PLASMA.PL_SECOND_EVAL_INFO (" +
				" PL_IGBI_DATA_NUM, PL_SEI_PROD_METHOD_BEST, PL_SEI_ASSESS_CONTENT," +
				" PL_SEI_ENERGY_RANGE, PL_SEI_FINAL_OPINION, PL_SEI_STD_REF_DATA," +
				" PL_SEI_COLLECT_PERIOD, PL_SEI_PROD_METHOD_AVERAGE, PL_SEI_ASSESS_DATE," +
				" PL_SEI_X_UNIT, PL_SEI_Y_UNIT, PL_SEI_X_MAX, PL_SEI_X_MIN, PL_SEI_ASSESS_USER)" +
				" VALUES (  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Vector<String> sqlOptionAssess = new Vector<String>();
		sqlOptionAssess.addElement(info.getPL_IGBI_DATA_NUM());
		sqlOptionAssess.addElement(info.getPL_SEI_PROD_METHOD_BEST());
		sqlOptionAssess.addElement(info.getPL_SEI_ASSESS_CONTENT());
		sqlOptionAssess.addElement(info.getPL_SEI_ENERGY_RANGE());
		sqlOptionAssess.addElement(info.getPL_SEI_FINAL_OPINION());
		sqlOptionAssess.addElement(info.getPL_SEI_STD_REF_DATA());
		sqlOptionAssess.addElement(info.getPL_SEI_COLLECT_PERIOD());
		sqlOptionAssess.addElement(info.getPL_SEI_PROD_METHOD_AVERAGE());
		sqlOptionAssess.addElement(info.getPL_SEI_ASSESS_DATE());
		sqlOptionAssess.addElement(info.getPL_SEI_X_UNIT());
		sqlOptionAssess.addElement(info.getPL_SEI_Y_UNIT());
		sqlOptionAssess.addElement(info.getPL_SEI_X_MAX());
		sqlOptionAssess.addElement(info.getPL_SEI_X_MIN());
		sqlOptionAssess.addElement(info.getPL_SEI_ASSESS_USER());
		
		r_value = comSQL.executeTransact(insertAssessInfoQuery, sqlOptionAssess);

		return r_value;
	}
}
