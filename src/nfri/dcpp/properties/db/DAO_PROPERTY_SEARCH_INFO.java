package nfri.dcpp.properties.db;

import java.util.Vector;

import nfri.dcpp.com.db.ComResultSet;
import nfri.dcpp.com.db.ComSQLExecution;
import nfri.dcpp.com.util.ComData;
import nfri.dcpp.com.util.ComUtil;
import nfri.dcpp.properties.business.Ctr_Option_Process;
import nfri.dcpp.properties.model.Properties_Assess_Search_List;
import nfri.dcpp.properties.model.Properties_Basic_Count;
import nfri.dcpp.properties.model.Properties_Basic_Info;
import nfri.dcpp.properties.model.Properties_Part_Count_Info;

/**
 *
 * @Project : dcpp_web
 * @Title : DAO_PROPERTY_SEARCH_INFO.java
 * @Description : 물성정보 검색과 관련된 작업을 처리하는 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 11. 19
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class DAO_PROPERTY_SEARCH_INFO {
	//DB 실행 클래스 선언
	ComSQLExecution comSQL;	
	
	public DAO_PROPERTY_SEARCH_INFO() {
		comSQL = new ComSQLExecution();
	}
	
	public ComData selectSearchPropery(){
		ComData comData = new ComData();
		return comData;
	}
	
	public Vector<Properties_Basic_Count> selectPropertyListCount(String optionquery){
		
		Vector<Properties_Basic_Count> values = new Vector<Properties_Basic_Count>();
		/*
		 *     SELECT BASIC.PL_BI_IMP_CLASS,
			         GROUPING (BASIC.PL_BI_IMP_CLASS),
			         BASIC.PL_BI_MAIN_PROC,
			         GROUPING (BASIC.PL_BI_MAIN_PROC),
			         BASIC.PL_BI_SUB_PROC,
			         GROUPING (BASIC.PL_BI_SUB_PROC),
			         COUNT ( * )
			    FROM PL_BASIC_INFO BASIC, PL_BASIC_EQUATION_INFO EQUA
			   WHERE 
			         EXISTS
			            (SELECT EQUA2.PL_BI_DATA_NUM
			               FROM PL_BASIC_EQUATION_INFO EQUA2
			              WHERE  EQUA.PL_BI_DATA_NUM = EQUA2.PL_BI_DATA_NUM
			                     AND EQUA2.PL_BEI_SEQ = '1'
			                    AND EQUA2.PL_CPBI_ELE_NUM = 'E0021')   
			         AND BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM
			         AND BASIC.PL_BI_TOP_BRANCH = 'TB_01'
			         AND BASIC.PL_BI_DATA_BRANCH = 'DB_02'
			         AND EQUA.PL_BEI_SEQ = '2'
			         AND EQUA.PL_CPBI_ELE_NUM = 'M0116'         
			GROUP BY ROLLUP (PL_BI_IMP_CLASS, PL_BI_MAIN_PROC, PL_BI_SUB_PROC)
		 */
		/*String sqlQuery = "SELECT DISTINCT NVL(PL_BI_IMP_CLASS, '-')," +
				" GROUPING (PL_BI_IMP_CLASS), NVL(PL_BI_MAIN_PROC,'-')," +
				" GROUPING (PL_BI_MAIN_PROC), NVL( PL_BI_SUB_PROC,'-')," +
				" GROUPING (PL_BI_SUB_PROC), COUNT ( * ) AS CNT" +
				" FROM PL_BASIC_INFO";*/
		String sqlQuery = " SELECT NVL(BASIC.PL_BI_IMP_CLASS,'-')," +
				" GROUPING (BASIC.PL_BI_IMP_CLASS)," +
				" NVL(BASIC.PL_BI_MAIN_PROC,'-')," +
				" GROUPING (BASIC.PL_BI_MAIN_PROC)," +
				" NVL(BASIC.PL_BI_SUB_PROC,'-')," +
				" GROUPING (BASIC.PL_BI_SUB_PROC)," +
				" COUNT ( * )" +
				" FROM PL_BASIC_INFO BASIC, PL_BASIC_EQUATION_INFO EQUA ";
		//System.out.println(sqlQuery + optionquery);
		ComResultSet rs = comSQL.executeSelect(sqlQuery + optionquery);

		while(rs.next()){		
			Properties_Basic_Count artInfo = new Properties_Basic_Count();
			artInfo.setPL_BI_IMP_CLASS(rs.getString(1));
			artInfo.setPL_BI_MAIN_PROC(rs.getString(3));
			artInfo.setPL_BI_SUB_PROC(rs.getString(5));
			artInfo.setCOUNT(rs.getString(7));			
			values.addElement(artInfo);
		}
		
		return values;
	}
	
	/**
	 * @MethodName  : selectSearchNewPropertyList
	 * @Date   : 2011. 01. 04 
	 * @MethodDescription : 메인화면에 뿌려질 새로운 물성정보 5개를 가져오는 메소드
	 * @return
	 * @History  : - 
	 */
	public Vector<Properties_Basic_Info> selectSearchNewPropertyList(){
		
		Vector<Properties_Basic_Info> values = new Vector<Properties_Basic_Info>();
		
		String sqlQuery = " SELECT PL_BI_DATA_NUM, PL_RAI_ARTCL_NUM," +
				" PL_BI_EXPRESSION, PL_BI_INSERT_DATE" +
				" FROM PL_BASIC_INFO WHERE PL_BI_INSERT_FLAG = 'C' AND ROWNUM <= 5" +
				" ORDER BY PL_BI_DATA_NUM DESC";
		//System.out.println(sqlQuery + optionquery);
		//ComResultSet rs = comSQL.executeSelect(sqlQuery + optionquery);
		ComResultSet rs = comSQL.executeSelect(sqlQuery);
		
		//Ctr_Option_Process ctr_option = new Ctr_Option_Process();
		
		while(rs.next()){		
			Properties_Basic_Info basic_info = new Properties_Basic_Info();
			basic_info.setPL_BI_DATA_NUM(rs.getString(1));
			basic_info.setPL_RAI_ARTCL_NUM(rs.getString(2));
			basic_info.setPL_BI_EXPRESSION(rs.getString(3));
			basic_info.setPL_BI_INSERT_DATE(rs.getString(4));
			values.addElement(basic_info);
		}
		
		return values;
	}
	
	/**
	 * @MethodName  : selectSearchPropertyPartCountInfo
	 * @Date   : 2012. 1. 17. 
	 * @MethodDescription : 입자/분자별 DB 입력 개수 가져오는 메소드 (표적입자에서만 가져오면 됨)
	 * @return
	 * @History  : - 
	 */
	public Vector<Properties_Part_Count_Info> selectSearchPropertyPartCountInfo(){
		Vector<Properties_Part_Count_Info> values = new Vector<Properties_Part_Count_Info>();
		String sqlQuery = " SELECT TOTAL.PL_CPBI_ELE_NUM, TOTAL.COUNT_NUM, PART.PL_CPBI_ELE_SYMBOL" +
				" FROM PL_CHEM_PART_BASIC_INFO PART," +
				" ( SELECT EQUA.PL_CPBI_ELE_NUM, COUNT (EQUA.PL_CPBI_ELE_NUM) COUNT_NUM" +
				" FROM PL_BASIC_EQUATION_INFO EQUA" +
				" WHERE EQUA.PL_BEI_SEQ = '2'" +
				" GROUP BY EQUA.PL_CPBI_ELE_NUM ) TOTAL" +
				" WHERE PART.PL_CPBI_ELE_NUM = TOTAL.PL_CPBI_ELE_NUM" +
				" ORDER BY PART.PL_CPBI_ELE_SYMBOL ASC ";
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery);
		
		while(rs.next()){		
			Properties_Part_Count_Info count_info = new Properties_Part_Count_Info();
			count_info.setPL_CPBI_ELE_NUM(rs.getString(1));
			count_info.setCOUNT_NUM(rs.getString(2));
			count_info.setPL_CPBI_ELE_SYMBOL(rs.getString(3));
			values.addElement(count_info);
		}
		
		return values;
	}
	
	/**
	 * @MethodName  : selectSearchPropertyPartCountInfo
	 * @Date   : 2013. 7. 1. 
	 * @MethodDescription : 데이터분류, 주프로세스에 따라 입자/분자별 DB 입력 개수 가져오는 메소드
	 * @param db
	 * @param mp
	 * @return
	 * @History  : - 
	 */
	public Vector<Properties_Part_Count_Info> selectSearchPropertyPartCountInfo(String db, String mp, String id){
		
		/*String sqlQuery = " SELECT TOTAL.PL_CPBI_ELE_NUM, TOTAL.PL_BI_MAIN_PROC, TOTAL.COUNT_NUM, PART.PL_CPBI_ELE_SYMBOL, PART.PL_CPBI_ELE_AMCOUNT" +
				" FROM PL_CHEM_PART_BASIC_INFO PART," +
				" (  SELECT EQUA.PL_CPBI_ELE_NUM, BASIC.PL_BI_MAIN_PROC, COUNT (EQUA.PL_CPBI_ELE_NUM) COUNT_NUM" +
				" FROM PL_BASIC_EQUATION_INFO EQUA," +
				"   (SELECT PL_BI_DATA_NUM, PL_BI_MAIN_PROC" +
				"     FROM PL_BASIC_INFO" +
				"    WHERE PL_BI_DATA_BRANCH = ? " +
				"    AND PL_BI_MAIN_PROC = ? ) BASIC" +
				"   WHERE EQUA.PL_BEI_SEQ = ? " +
				"   AND BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM" +
				"   GROUP BY EQUA.PL_CPBI_ELE_NUM, BASIC.PL_BI_MAIN_PROC) TOTAL" +
				" WHERE PART.PL_CPBI_ELE_NUM = TOTAL.PL_CPBI_ELE_NUM" +
				" GROUP BY TOTAL.PL_CPBI_ELE_NUM, TOTAL.PL_BI_MAIN_PROC, TOTAL.COUNT_NUM, PART.PL_CPBI_ELE_SYMBOL, PART.PL_CPBI_ELE_AMCOUNT" +
				" ORDER BY TOTAL.PL_BI_MAIN_PROC, TO_NUMBER(PART.PL_CPBI_ELE_AMCOUNT), PART.PL_CPBI_ELE_SYMBOL ASC";
		*/
		String sqlQuery = " SELECT EQUA.PL_CPBI_ELE_NUM, COUNT (EQUA.PL_CPBI_ELE_NUM) COUNT_NUM, PART.PL_CPBI_ELE_SYMBOL, PART.PL_CPBI_ELE_AMCOUNT"
				+ "    FROM PL_BASIC_EQUATION_INFO EQUA, PL_CHEM_PART_BASIC_INFO PART"
				+ "   WHERE PART.PL_CPBI_ELE_NUM = EQUA.PL_CPBI_ELE_NUM AND EQUA.PL_BEI_SEQ = '1'"
				+ "         AND PL_BI_DATA_NUM IN"
				+ "                  (SELECT EQUA.PL_BI_DATA_NUM"
				+ "                     FROM PL_BASIC_EQUATION_INFO EQUA,"
				+ "                          (SELECT PL_BI_DATA_NUM, PL_BI_MAIN_PROC"
				+ "                             FROM PL_BASIC_INFO"
				+ "                            WHERE PL_BI_DATA_BRANCH = ? "
				+ "                                  AND PL_BI_MAIN_PROC = ? ) BASIC"
				+ "                    WHERE     EQUA.PL_BEI_SEQ = '2' "
				+ "                          AND EQUA.PL_CPBI_ELE_NUM = ? "
				+ "                          AND BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM)"
				+ " GROUP BY EQUA.PL_CPBI_ELE_NUM, PART.PL_CPBI_ELE_SYMBOL, PART.PL_CPBI_ELE_AMCOUNT";    
			  
		
		
		Vector<String> ve_1 = new Vector<String>();
		
		ve_1.addElement(db);
		ve_1.addElement(mp);
		ve_1.addElement(id);

		//System.out.println("sqlQuery : " + sqlQuery);
		ComResultSet rs = comSQL.executeSelect(sqlQuery, ve_1);	
		
		Vector<Properties_Part_Count_Info> values = new Vector<Properties_Part_Count_Info>();
		
		while(rs.next()){		
			Properties_Part_Count_Info count_info = new Properties_Part_Count_Info();
			count_info.setPL_CPBI_ELE_NUM(rs.getString(1));
			count_info.setPL_BI_MAIN_PROC(mp);
			count_info.setCOUNT_NUM(rs.getString(2));
			count_info.setPL_CPBI_ELE_SYMBOL(rs.getString(3));
			if(rs.getString(4).equalsIgnoreCase("10000")){
				count_info.setPL_CPBI_ELE_AMCOUNT("기타");
			}else{
				count_info.setPL_CPBI_ELE_AMCOUNT(rs.getString(4));
			}
			values.addElement(count_info);
		}
		
		return values;
	}
	
	/**
	 * @MethodName  : selectSearchPropertyPartCountInfo
	 * @Date   : 2014. 11. 6. 
	 * @MethodDescription : 데이터분류에 따라 주프로세스별/입자/분자 카운트 가져오는 DB 메소드
	 * @param db : 데이터분류
	 * @return
	 * @History  : - 
	 */
	public Vector<Properties_Part_Count_Info> selectSearchPropertyPartCountInfo(String db){
		
		String sqlQuery = " SELECT TOTAL.PL_CPBI_ELE_NUM, TOTAL.PL_BI_MAIN_PROC, TOTAL.PL_CI_ID_EXP,"
				+ " TOTAL.COUNT_NUM, PART.PL_CPBI_ELE_SYMBOL, PART.PL_CPBI_ELE_AMCOUNT"
				+ " FROM PL_CHEM_PART_BASIC_INFO PART,"
				+ " (  SELECT EQUA.PL_CPBI_ELE_NUM, CODE.PL_CI_ID_EXP, BASIC.PL_BI_MAIN_PROC, COUNT (EQUA.PL_CPBI_ELE_NUM) COUNT_NUM"
				+ "      FROM PL_BASIC_EQUATION_INFO EQUA,PL_CODE_INFO CODE,"
				+ "      	(SELECT PL_BI_DATA_NUM, PL_BI_MAIN_PROC"
				+ "            FROM PL_BASIC_INFO"
				+ "			  WHERE PL_BI_DATA_BRANCH = ? ) BASIC"
				+ "      WHERE EQUA.PL_BEI_SEQ = '2'"
				+ "        AND BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM AND BASIC.PL_BI_MAIN_PROC = CODE.PL_CI_ID"
				+ "      GROUP BY EQUA.PL_CPBI_ELE_NUM, CODE.PL_CI_ID_EXP, BASIC.PL_BI_MAIN_PROC) TOTAL"
				+ "    WHERE PART.PL_CPBI_ELE_NUM = TOTAL.PL_CPBI_ELE_NUM"
				+ "    GROUP BY TOTAL.PL_CPBI_ELE_NUM, TOTAL.PL_BI_MAIN_PROC, TOTAL.PL_CI_ID_EXP,"
				+ "             TOTAL.COUNT_NUM, PART.PL_CPBI_ELE_SYMBOL, PART.PL_CPBI_ELE_AMCOUNT"
				+ "    ORDER BY  TOTAL.PL_CI_ID_EXP, TO_NUMBER (PART.PL_CPBI_ELE_AMCOUNT), PART.PL_CPBI_ELE_SYMBOL ASC";
		
		Vector<String> ve_1 = new Vector<String>();
		
		ve_1.addElement(db);		

		ComResultSet rs = comSQL.executeSelect(sqlQuery, ve_1);	
		
		Vector<Properties_Part_Count_Info> values = new Vector<Properties_Part_Count_Info>();
		
		while(rs.next()){		
			Properties_Part_Count_Info count_info = new Properties_Part_Count_Info();
			count_info.setPL_CPBI_ELE_NUM(rs.getString(1));
			count_info.setPL_BI_MAIN_PROC(rs.getString(2));
			count_info.setPL_BI_MAIN_PROC_EXP(rs.getString(3));
			count_info.setCOUNT_NUM(rs.getString(4));
			count_info.setPL_CPBI_ELE_SYMBOL(rs.getString(5));
			if(rs.getString(6).equalsIgnoreCase("10000")){
				count_info.setPL_CPBI_ELE_AMCOUNT("기타");
			}else{
				count_info.setPL_CPBI_ELE_AMCOUNT(rs.getString(6));
			}
			
			values.addElement(count_info);
		}
		
		return values;
	}
	
	/**
	 * @MethodName  : selectSearchPropertyTargetPartCountInfo
	 * @Date   : 2013. 12. 11. 
	 * @MethodDescription : 데이터분류, 주프로세스, 입사입자 에 따라 입자/분자별 DB 입력 개수 가져오는 메소드
	 * @param db
	 * @param mp
	 * @param t_id
	 * @return
	 * @History  : - 
	 */
	public Vector<Properties_Part_Count_Info> selectSearchPropertyProjecilePartCountInfo(String db, String mp){
		
		String sqlQuery = " SELECT TOTAL.PL_CPBI_ELE_NUM, TOTAL.COUNT_NUM, PART.PL_CPBI_ELE_SYMBOL" +
				" FROM PL_CHEM_PART_BASIC_INFO PART," +
				" (  SELECT EQUA.PL_CPBI_ELE_NUM, COUNT (EQUA.PL_CPBI_ELE_NUM) COUNT_NUM" +
				" FROM PL_BASIC_EQUATION_INFO EQUA," +
				"   (SELECT PL_BI_DATA_NUM" +
				"     FROM PL_BASIC_INFO" +
				"    WHERE PL_BI_DATA_BRANCH = ? " +
				"    AND PL_BI_MAIN_PROC = ? ) BASIC" +
				"   WHERE EQUA.PL_BEI_SEQ = '2'" +
				"   AND BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM" +
				"   GROUP BY EQUA.PL_CPBI_ELE_NUM) TOTAL" +
				" WHERE PART.PL_CPBI_ELE_NUM = TOTAL.PL_CPBI_ELE_NUM" +
				" ORDER BY PART.PL_CPBI_ELE_SYMBOL ASC ";
		
		Vector<String> ve_1 = new Vector<String>();
		
		ve_1.addElement(db);
		ve_1.addElement(mp);
		//ve_1.addElement(seq);===========================================

		ComResultSet rs = comSQL.executeSelect(sqlQuery, ve_1);	
		
		Vector<Properties_Part_Count_Info> values = new Vector<Properties_Part_Count_Info>();
		
		while(rs.next()){		
			Properties_Part_Count_Info count_info = new Properties_Part_Count_Info();
			count_info.setPL_CPBI_ELE_NUM(rs.getString(1));
			count_info.setPL_BI_MAIN_PROC(rs.getString(2));
			count_info.setCOUNT_NUM(rs.getString(3));
			count_info.setPL_CPBI_ELE_SYMBOL(rs.getString(4));
			if(rs.getString(5).equalsIgnoreCase("10000")){
				count_info.setPL_CPBI_ELE_AMCOUNT("기타");
			}else{
				count_info.setPL_CPBI_ELE_AMCOUNT(rs.getString(5));
			}
			values.addElement(count_info);
		}
		
		return values;
		
		/*
		 * 				
		String sqlQuery = " SELECT TOTAL.PL_CPBI_ELE_NUM, TOTAL.PL_BI_MAIN_PROC, TOTAL.COUNT_NUM, PART.PL_CPBI_ELE_SYMBOL, PART.PL_CPBI_ELE_AMCOUNT" +
				" FROM PL_CHEM_PART_BASIC_INFO PART," +
				" (  SELECT EQUA.PL_CPBI_ELE_NUM, BASIC.PL_BI_MAIN_PROC, COUNT (EQUA.PL_CPBI_ELE_NUM) COUNT_NUM" +
				" FROM PL_BASIC_EQUATION_INFO EQUA," +
				"   (SELECT PL_BI_DATA_NUM, PL_BI_MAIN_PROC" +
				"     FROM PL_BASIC_INFO" +
				"    WHERE PL_BI_DATA_BRANCH = ? " +
				"    AND PL_BI_MAIN_PROC = ? ) BASIC" +
				"   WHERE EQUA.PL_BEI_SEQ = ? " +
				"   AND BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM" +
				"   GROUP BY EQUA.PL_CPBI_ELE_NUM, BASIC.PL_BI_MAIN_PROC) TOTAL" +
				" WHERE PART.PL_CPBI_ELE_NUM = TOTAL.PL_CPBI_ELE_NUM" +
				" GROUP BY TOTAL.PL_CPBI_ELE_NUM, TOTAL.PL_BI_MAIN_PROC, TOTAL.COUNT_NUM, PART.PL_CPBI_ELE_SYMBOL, PART.PL_CPBI_ELE_AMCOUNT" +
				" ORDER BY TOTAL.PL_BI_MAIN_PROC, TO_NUMBER(PART.PL_CPBI_ELE_AMCOUNT), PART.PL_CPBI_ELE_SYMBOL ASC";
		
		 */
	}
	
	/**
	 * @MethodName  : selectIAEASearchPropertyList
	 * @Date   : 2012. 6. 8. 
	 * @MethodDescription : IAEA 검색용 DB 메소드
	 * @param doi_no
	 * @return
	 * @History  : - 
	 */
	public Vector<Properties_Basic_Info> selectIAEASearchPropertyList(String doi_no){
		Vector<Properties_Basic_Info> values = new Vector<Properties_Basic_Info>();
		
		String sqlQuery = " SELECT  DISTINCT BASIC.PL_BI_DATA_NUM," +
				" BASIC.PL_RAI_ARTCL_NUM, BASIC.PL_BI_EXPRESSION," +
				" BASIC.PL_BI_MAIN_PROC, BASIC.PL_BI_SUB_PROC," +
				" BASIC.PL_BI_EXP_THE_REC, BASIC.PL_BI_INSERT_FLAG," +
				" BASIC.PL_BI_INSERT_DATE" +
				" FROM PL_BASIC_INFO BASIC, PL_BASIC_EQUATION_INFO EQUA" +
				" WHERE BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM" +
				" AND BASIC.PL_BI_INSERT_FLAG = 'C'" +
				" AND BASIC.PL_RAI_ARTCL_NUM IN (SELECT PL_RAI_ARTCL_NUM" +
				"                                FROM PL_REF_ARTICLE_INFO" +
				"                                WHERE PL_RAI_ARTCL_DOI LIKE ?)";
		
		Vector<String> ve_1 = new Vector<String>();
		doi_no = doi_no + "%";
		//System.out.println("2. doi_no : "+ doi_no);
		ve_1.addElement(doi_no);

		ComResultSet rs = comSQL.executeSelect(sqlQuery, ve_1);	
		
		Ctr_Option_Process ctr_option = new Ctr_Option_Process();
		
		while(rs.next()){		
			Properties_Basic_Info basic_info = new Properties_Basic_Info();
			basic_info.setPL_BI_DATA_NUM(rs.getString(1));
			basic_info.setPL_RAI_ARTCL_NUM(rs.getString(2));
			basic_info.setPL_BI_EXPRESSION(rs.getString(3));
			basic_info.setPL_BI_MAIN_PROC(ctr_option.getExpName(rs.getString(4)));
			basic_info.setPL_BI_SUB_PROC(ctr_option.getExpName(rs.getString(5)));
			basic_info.setPL_BI_EXP_THE_REC(ctr_option.getExpName(rs.getString(6)));
			basic_info.setPL_BI_INSERT_FLAG(rs.getString(7));
			basic_info.setPL_BI_INSERT_DATE(rs.getString(8));
			values.addElement(basic_info);
		}
		
		return values;
	}
	
	/**
	 * @MethodName  : selectSearchPropertyList
	 * @Date   : 2010. 1. 14. 
	 * @MethodDescription : 최종 검색 결과를 가져가는 메소드
	 * @param optionquery
	 * @return
	 * @History  : - 
	 */
	public Vector<Properties_Basic_Info> selectSearchPropertyList(String optionquery){
		
		Vector<Properties_Basic_Info> values = new Vector<Properties_Basic_Info>();
		
		String sqlQuery = " SELECT DISTINCT BASIC.PL_BI_DATA_NUM," +
				" BASIC.PL_RAI_ARTCL_NUM, BASIC.PL_BI_EXPRESSION, BASIC.PL_BI_IMP_CLASS, " +
				" BASIC.PL_BI_MAIN_PROC, BASIC.PL_BI_SUB_PROC," +
				" BASIC.PL_BI_EXP_THE_REC, BASIC.PL_BI_INSERT_FLAG," +
				" BASIC.PL_BI_INSERT_DATE" +
				" FROM PL_BASIC_INFO BASIC, PL_BASIC_EQUATION_INFO EQUA ";
		//System.out.println(sqlQuery + optionquery);
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery + optionquery);

		Ctr_Option_Process ctr_option = new Ctr_Option_Process();
		
		while(rs.next()){		
			Properties_Basic_Info basic_info = new Properties_Basic_Info();
			basic_info.setPL_BI_DATA_NUM(rs.getString(1));
			basic_info.setPL_RAI_ARTCL_NUM(rs.getString(2));
			basic_info.setPL_BI_EXPRESSION(rs.getString(3));
			basic_info.setPL_BI_IMP_CLASS(ctr_option.getExpName(rs.getString(4)));
			basic_info.setPL_BI_MAIN_PROC(ctr_option.getExpName(rs.getString(5)));
			basic_info.setPL_BI_SUB_PROC(ctr_option.getExpName(rs.getString(6)));
			basic_info.setPL_BI_EXP_THE_REC(ctr_option.getExpName(rs.getString(7)));
			basic_info.setPL_BI_INSERT_FLAG(rs.getString(8));
			basic_info.setPL_BI_INSERT_DATE(rs.getString(9));
			values.addElement(basic_info);
		}
		
		return values;
	}
	
	/**
	 * @MethodName  : selectSearchAssessList
	 * @Date   : 2010. 06. 03 
	 * @MethodDescription : 2차 
	 * @param optQuery
	 * @return
	 * @History  : - 
	 */
	public Vector<Properties_Assess_Search_List> selectSearchAssessList(String optQuery){
		Vector<Properties_Assess_Search_List> values = new Vector<Properties_Assess_Search_List>();
		String sqlQuery = "SELECT DISTINCT BASIC.PL_BI_DATA_NUM, BASIC.PL_RAI_ARTCL_NUM, " +
				"CONCAT(CONCAT(ARTCL.PL_RAI_ARTCL_AUTH_ENAME, ', '), ARTCL.PL_RAI_ARTCL_AUTH_FNAME), " +
				"BASIC.PL_BI_EXP_THE_REC, BASIC.PL_BI_DATA_BRANCH, BASIC.PL_BI_MGMT_DATA_NUM " +
				"FROM PL_BASIC_INFO BASIC, PL_REF_ARTICLE_INFO ARTCL, " +
				"PL_BASIC_GRAPH_INFO GRAPH,";

		//System.out.println("QUERY : " + sqlQuery + optQuery);
		ComResultSet rs = comSQL.executeSelect(sqlQuery + optQuery);
		
		//System.out.println("selectSearchAssessList QUERY : " + sqlQuery + optQuery);

		Ctr_Option_Process ctr_option = new Ctr_Option_Process();
		
		while(rs.next()){		
			Properties_Assess_Search_List assess_list = new Properties_Assess_Search_List();
			assess_list.setPL_BI_DATA_NUM(rs.getString(1));
			assess_list.setPL_RAI_ARTCL_NUM(rs.getString(2));
			assess_list.setPL_RAI_ARTCL_AUTH_NAME(rs.getString(3));
			assess_list.setPL_BI_EXP_THE_REC(ctr_option.getExpName(rs.getString(4)));
			assess_list.setPL_BI_DATA_BRANCH(ctr_option.getExpName(rs.getString(5)));
			assess_list.setPL_BI_MGMT_DATA_NUM(rs.getString(6));
			values.addElement(assess_list);
		}
		
		return values;

	}
		
	/**
	 * @MethodName : selectEquationList
	 * @Desc : 반응식 정보를 가져오는 메소드
	 * @param sqlOption(pro_no)
	 * @return
	 */
	/*public Vector selectPropertyList(String pr_no){

		Vector values = new Vector();
		String sqlQuery = "SELECT E.PL_BI_DATA_NUM, E.PL_CPBI_ELE_NUM, P.PL_CPBI_ELE_SYMBOL, E.PL_BEI_SEQ," +
				" NVL(E.PL_BEI_CHG_STATE,'0'), NVL(E.PL_BEI_ELC_STATE,'0'), NVL(E.PL_BEI_OTH_STRUC,'0'), A.PL_CI_ID" +
				" FROM PL_BASIC_EQUATION_INFO E, PL_CHEM_PART_BASIC_INFO P, PL_BASIC_EQUATION_ADDC_INFO A" +
				" WHERE E.PL_BI_DATA_NUM = A.PL_BI_DATA_NUM AND E.PL_CPBI_ELE_NUM = P.PL_CPBI_ELE_NUM" +
				" AND E.PL_BI_DATA_NUM = ? ORDER BY E.PL_BEI_SEQ";
		
		//상세 물성 정보 Query 파라미터 벡터		
		Vector ve_1 = new Vector();
		ve_1.addElement(pr_no);
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery, ve_1);
		
		while(rs.next()){	
			Properties_Equation_Get_DbInfo equa_Info = new Properties_Equation_Get_DbInfo();
			equa_Info.setPL_BI_DATA_NUM(rs.getString(1));
			equa_Info.setPL_CPBI_ELE_NUM(rs.getString(2));
			equa_Info.setPL_CPBI_ELE_SYMBOL(rs.getString(3));
			equa_Info.setPL_BEI_SEQ(rs.getString(4));
			equa_Info.setPL_BEI_CHG_STATE(rs.getString(5));
			equa_Info.setPL_BEI_ELC_STATE(rs.getString(6));
			equa_Info.setPL_BEI_OTH_STRUC(rs.getString(7));
			equa_Info.setPL_CI_ID(rs.getString(8));
			values.addElement(equa_Info);
		}
		
		return values;
	}*/
}
