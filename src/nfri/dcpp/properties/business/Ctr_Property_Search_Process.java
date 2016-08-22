package nfri.dcpp.properties.business;

import java.util.Vector;
import nfri.dcpp.com.util.ComUtil;
import nfri.dcpp.com.util.ComVar;
import nfri.dcpp.properties.db.DAO_PROPERTY_SEARCH_INFO;
import nfri.dcpp.properties.model.Properties_Basic_Count;
import nfri.dcpp.properties.model.Properties_IC_MP_SP;

import javax.servlet.http.HttpServletRequest;

/**
*
* @Project : dcpp_web
* @Title : Ctr_Property_Search_Process.java
* @Description : 물성정보검색과 관련된 처리를 하는 비즈니스 로직 클래스
* @Author : J.H Park (JUNSiDE)
* @Date : 2009. 03. 02
* @Company : Data Center for Plasma Properties.
*            NFRI.
*
* @변경이력 : -
*
*/

public class Ctr_Property_Search_Process {

	public Ctr_Property_Search_Process(){
		
	}	
	
	//SQL 첫번째 조건 : 대분류(TB)
	String sql_tb_option = " WHERE PL_BI_TOP_BRANCH = "; 
	//SQL 두번째 조건 : 데이터분류 (DB)
	String sql_db_option = " AND PL_BI_DATA_BRANCH = ";
	//SQL 세번째 조건 : 표적 입자 / 입사 입자
	String sql_expresstion = " AND PL_BI_EXPRESSION LIKE ";
	String sql_part_1 = "";
	String sql_part_2 = "";
	//SQL 네번째 조건 : 주프로세스
	String sql_mp_option = " AND PL_BI_MAIN_PROC = ";
	//SQL 다섯번째 조건 : 부프로세스
	String sql_sp_option = " AND PL_BI_SUB_PROC = ";
	//SQL 여섯번째 조건 : 충돌방식
	String sql_ic_option = " AND PL_BI_IMP_CLASS = ";
	//SQL GROUP BY 옵션
	//String sql_group_by = " GROUP BY GROUPING SETS(( PL_BI_IMP_CLASS, PL_BI_MAIN_PROC), (PL_BI_MAIN_PROC, PL_BI_SUB_PROC))";
	String sql_group_by = " GROUP BY ROLLUP (PL_BI_IMP_CLASS, PL_BI_MAIN_PROC, PL_BI_SUB_PROC)";
	

	/**
	 * @MethodName  : selectSearchNewPropertyList
	 * @Date   : 2011. 1. 10. 
	 * @MethodDescription : 메인 화면에 보여줄 새로 입력된 물성 데이터(5개)를 가져오는 메소드
	 * @param : -
	 * @return : -
	 * @History  : - 
	 */
	public Vector<?> selectSearchNewPropertyList(){
		Vector<?> vecList = new Vector<Object>();
		DAO_PROPERTY_SEARCH_INFO info = new DAO_PROPERTY_SEARCH_INFO();
		vecList = info.selectSearchNewPropertyList();
		return vecList;
	}
	
	/**
	 * @MethodName  : selectIAEASearchPropertyList
	 * @Date   : 2012. 6. 8. 
	 * @MethodDescription : IAEA 검색 요청시 처음에 보여줄 데이터 리스트 가져오는 메소드
	 * @param doi_no
	 * @return
	 * @History  : - 
	 */
	public Vector<?> selectIAEASearchPropertyList(String doi_no){
		Vector<?> vecList = new Vector<Object>();
		DAO_PROPERTY_SEARCH_INFO info = new DAO_PROPERTY_SEARCH_INFO();
		vecList = info.selectIAEASearchPropertyList(doi_no);
		return vecList;
	}
	/**
	 * @MethodName  : searchPropertyPartCountInfo
	 * @Date   : 2012. 1. 17. 
	 * @MethodDescription : 입자/분자별 입력 카운트 정보를 가져오는 메소드
	 * @return
	 * @History  : - 
	 */
	public Vector<?> searchPropertyPartCountInfo(){
		Vector<?> search_list = new Vector<Object>();
		DAO_PROPERTY_SEARCH_INFO info = new DAO_PROPERTY_SEARCH_INFO();
		//Properties_Part_Count_Info count = (Properties_Part_Count_Info)info.searchPropertyPartCountInfo();
		search_list = info.selectSearchPropertyPartCountInfo();		
		return search_list;
	}
	
	/**
	 * @MethodName  : searchPropertyPartCountInfo
	 * @Date   : 2013. 7. 1. 
	 * @MethodDescription : 표적입자와 관련한 데이터분류와 주프로세스에 따라 타겟 입자/분자별 입력 카운트 정보를 가져오는 메소드
	 * @param db 데이터분류
	 * @param mp 주프로세스
	 * @param id 표적입자 ID
	 * @return
	 * @History  : - 
	 */
	public Vector<?> searchPropertyPartCountInfo(String db, String mp, String id){
		Vector<?> search_list = new Vector<Object>();
		DAO_PROPERTY_SEARCH_INFO info = new DAO_PROPERTY_SEARCH_INFO();
		//Properties_Part_Count_Info count = (Properties_Part_Count_Info)info.searchPropertyPartCountInfo();
		search_list = info.selectSearchPropertyPartCountInfo(db, mp, id);		
		return search_list;
	}
	
	/**
	 * @MethodName  : searchPropertyPartCountInfo
	 * @Date   : 2014. 11. 6. 
	 * @MethodDescription : 데이터분류에 따라 주프로세스/입자/분자별 입력 카운트 정보를 가져오는 메소드
	 * @param db : 데이터분류
	 * @return
	 * @History  : - 
	 */
	public Vector<?> searchPropertyPartCountInfo(String db){
		Vector<?> search_list = new Vector<Object>();
		DAO_PROPERTY_SEARCH_INFO info = new DAO_PROPERTY_SEARCH_INFO();
		//Properties_Part_Count_Info count = (Properties_Part_Count_Info)info.searchPropertyPartCountInfo();
		search_list = info.selectSearchPropertyPartCountInfo(db);		
		return search_list;
	}
	
	/**
	 * @MethodName  : searchPropertyTargetPartCountInfo
	 * @Date   : 2013. 12. 11. 
	 * @MethodDescription : 데이터분류와 주프로세스, 표적입자에 따라 입자/분자별 입력 카운트 정보를 가져오는 메소드
	 * @param db 데이터분류
	 * @param mp 주프로세스
	 * @param t_id 표적입자 ID
	 * @return
	 * @History  : - 
	 */
	public Vector<?> searchPropertyTargetPartCountInfo(String db, String mp, String t_id){
		Vector<?> search_list = new Vector<Object>();
		DAO_PROPERTY_SEARCH_INFO info = new DAO_PROPERTY_SEARCH_INFO();
		//Properties_Part_Count_Info count = (Properties_Part_Count_Info)info.searchPropertyPartCountInfo();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//search_list = info.selectSearchPropertyProjecilePartCountInfo(db, mp, seq, t_id);		
		return search_list;
	}

	/**
	 * @MethodName  : searchPropertyAllInfo
	 * @Date   : 2010. 1. 11. 
	 * @MethodDescription : 입력된 조건을 토대로 물성 데이터 카운트를 검색하여 리턴
	 * 						Part2 여부에 따라 e, hv, 입자별 검색 가능
	 * @param request
	 * @return
	 * @History  : - 
	 */
	public Properties_IC_MP_SP searchPropertyAllInfo(HttpServletRequest request){ 
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
		Vector<?> search_list = new Vector<Object>();
		//대분류
		String req_tb_option = request.getParameter("tb_option");
		//데이터분류
		String req_db_option = request.getParameter("db_option");
		//표적입자
		String req_part1 = request.getParameter("part_1_id");
		//입사입자
		String req_part2 = ComUtil.isNullToDashString(request.getParameter("part_2_id"));
		//주프로세스
		String req_mp_option = request.getParameter("mp_option");
		//참조표준인증데이터
		String req_srd_value = ComUtil.isNullToDashString(request.getParameter("srd_value"));
		
		String str_where_start = " WHERE ";
		String str_exist = "EXISTS" +
				" (SELECT EQUA2.PL_BI_DATA_NUM" +
				" FROM PL_BASIC_EQUATION_INFO EQUA2" +
				" WHERE  EQUA.PL_BI_DATA_NUM = EQUA2.PL_BI_DATA_NUM " +
				" AND EQUA2.PL_BEI_SEQ = '1'";
		String str_exist_option_ele_num = " AND EQUA2.PL_CPBI_ELE_NUM = ";	
		String str_if_part2_where_option = " ) AND BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM";
		String str_basic_where_option = " BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM";
		//String str_where_option_data_num = " AND BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM";
		String str_where_option_tb = " AND BASIC.PL_BI_TOP_BRANCH = ";
		String str_where_option_db = " AND BASIC.PL_BI_DATA_BRANCH = ";
		String str_where_option_mp = " AND BASIC.PL_BI_MAIN_PROC = ";
		String str_where_option_ele_seq = " AND EQUA.PL_BEI_SEQ = '2'";
		String str_where_option_ele_num = " AND EQUA.PL_CPBI_ELE_NUM = ";
		String str_where_group_by = " GROUP BY ROLLUP (PL_BI_IMP_CLASS, PL_BI_MAIN_PROC, PL_BI_SUB_PROC)";

		/*
		 * SQL Query 변수 선언
		 */
		//전체 SQL문 저장 변수
		String optionquery = str_where_start;
		//입사입자 처리
		if(!req_part2.equalsIgnoreCase("-")){
			//입사입자 있으면 EXIST 구문 추가
			optionquery = optionquery + str_exist + str_exist_option_ele_num + ComUtil.makeEqualOption(req_part2);
			optionquery = optionquery + str_if_part2_where_option;
		}else{
			optionquery = optionquery + str_basic_where_option;
		}
		//대분류 처리
		optionquery = optionquery + str_where_option_tb + ComUtil.makeEqualOption(req_tb_option);
		//데이터분류 처리
		optionquery = optionquery + str_where_option_db + ComUtil.makeEqualOption(req_db_option);
		//주프로세스 처리
		if(!req_mp_option.equalsIgnoreCase("MP_00")){//주프로세스
			optionquery = optionquery + str_where_option_mp + ComUtil.makeEqualOption(req_mp_option);
		}
		//표적입자 처리
		optionquery = optionquery + str_where_option_ele_seq + str_where_option_ele_num + ComUtil.makeEqualOption(req_part1);
		optionquery = optionquery + str_where_group_by;
		
		/*String optionquery = sql_tb_option + ComUtil.makeEqualOption(req_tb_option) 
		            + sql_db_option + ComUtil.makeEqualOption(req_db_option)
		            + sql_expresstion + ComUtil.makeEquaLikeOption(req_part1);*/

		//if(!req_mp_option.equalsIgnoreCase("MP_00")){//주프로세스
		//	optionquery = optionquery + sql_mp_option + ComUtil.makeEqualOption(req_mp_option);
		//}
		/*if(!ComVar.STRING_DASH.equalsIgnoreCase(req_srd_value)){//참조표준인증데이터
			optionquery = optionquery + ComUtil.makeEqualOption(req_srd_value);
		}*/
		
		//optionquery = optionquery + sql_group_by;
		System.out.println(optionquery);
		
		DAO_PROPERTY_SEARCH_INFO info = new DAO_PROPERTY_SEARCH_INFO();
		search_list = info.selectPropertyListCount(optionquery);
		
		Properties_IC_MP_SP count_sum = searchSummary(search_list);
		
		
		return count_sum;
	}

	/**
	 * @MethodName  : searchPropertyElectronInfo
	 * @Date   : 2010. 1. 11. 
	 * @MethodDescription : 입력된 e 값을 토대로  Electron Impact  카운트데이터를 검색하여 리턴
	 * @param request
	 * @return Vector
	 * @History  : - 
	 */
	public Properties_IC_MP_SP searchPropertyElectronInfo(HttpServletRequest request){
		Vector<?> search_list = new Vector<Object>();
		//대분류
		String req_tb_option = request.getParameter("tb_option");
		//데이터분류
		String req_db_option = request.getParameter("db_option");
		//표적입자
		String req_part1 = request.getParameter("part_1");
		//입사입자
		String req_part2 = ComUtil.isNullToDashString(request.getParameter("part_2"));
		//주프로세스
		String req_mp_option = request.getParameter("mp_option");
		//참조표준인증데이터
		String req_srd_value = ComUtil.isNullToDashString(request.getParameter("srd_value"));
		
		/*
		 * SQL Query 변수 선언
		 */
		//전체 SQL문 저장 변수
		String optionquery = sql_tb_option + ComUtil.makeEqualOption(req_tb_option) 
		            + sql_db_option + ComUtil.makeEqualOption(req_db_option)
		            + sql_expresstion + ComUtil.makeEquaLikeOption(req_part1);
	
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_part2)){//입사입자
			optionquery = optionquery + sql_expresstion + ComUtil.makeEquaLikeOption(req_part2);
		}
		if(!req_mp_option.equalsIgnoreCase("MP_00")){//주프로세스
			optionquery = optionquery + sql_mp_option + ComUtil.makeEqualOption(req_mp_option);
		}
		/*if(!ComVar.STRING_DASH.equalsIgnoreCase(req_srd_value)){//참조표준인증데이터
			optionquery = optionquery + ComUtil.makeEqualOption(req_srd_value);
		}*/
		
		optionquery = optionquery + sql_group_by;
		
		DAO_PROPERTY_SEARCH_INFO info = new DAO_PROPERTY_SEARCH_INFO();
		search_list = info.selectPropertyListCount(optionquery);
		
		Properties_IC_MP_SP count_sum = searchSummary(search_list);
		
		
		return count_sum;
	}

	/**
	 * @MethodName  : searchPropertyPhotonInfo
	 * @Date   : 2010. 1. 11. 
	 * @MethodDescription : 입력된 hv 값을 토대로  Photon Impact  카운트데이터를 검색하여 리턴
	 * @param request
	 * @return
	 * @History  : - 
	 */
	public Properties_IC_MP_SP searchPropertyPhotonInfo(HttpServletRequest request){
		Vector<?> search_list = new Vector<Object>();
		//대분류
		String req_tb_option = request.getParameter("tb_option");
		//데이터분류
		String req_db_option = request.getParameter("db_option");
		//표적입자
		String req_part1 = request.getParameter("part_1");
		//입사입자
		String req_part2 = ComUtil.isNullToDashString(request.getParameter("part_2"));
		//주프로세스
		String req_mp_option = request.getParameter("mp_option");
		//참조표준인증데이터
		String req_srd_value = ComUtil.isNullToDashString(request.getParameter("srd_value"));
		
		/*
		 * SQL Query 변수 선언
		 */
		//전체 SQL문 저장 변수
		String optionquery = sql_tb_option + ComUtil.makeEqualOption(req_tb_option) 
		            + sql_db_option + ComUtil.makeEqualOption(req_db_option)
		            + sql_expresstion + ComUtil.makeEquaLikeOption(req_part1);
	
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_part2)){//입사입자
			optionquery = optionquery + sql_expresstion + ComUtil.makeEquaLikeOption(req_part2);
		}
		if(!req_mp_option.equalsIgnoreCase("MP_00")){//주프로세스
			optionquery = optionquery + sql_mp_option + ComUtil.makeEqualOption(req_mp_option);
		}
		/*if(!ComVar.STRING_DASH.equalsIgnoreCase(req_srd_value)){//참조표준인증데이터
			optionquery = optionquery + ComUtil.makeEqualOption(req_srd_value);
		}*/
		
		optionquery = optionquery + sql_group_by;
		
		DAO_PROPERTY_SEARCH_INFO info = new DAO_PROPERTY_SEARCH_INFO();
		search_list = info.selectPropertyListCount(optionquery);
		
		Properties_IC_MP_SP count_sum = searchSummary(search_list);
		
		
		return count_sum;
	}
	
	/**
	 * @MethodName  : searchPropertyHeavyParticleInfo
	 * @Date   : 2010. 1. 11. 
	 * @MethodDescription : 입력된 입자 코드를 토대로 Heavy particle Impact  카운트데이터를 검색하여 리턴
	 * @param request
	 * @return
	 * @History  : - 
	 */
	public Properties_IC_MP_SP searchPropertyHeavyParticleInfo(HttpServletRequest request){
		Vector<?> search_list = new Vector<Object>();
		//대분류
		String req_tb_option = request.getParameter("tb_option");
		//데이터분류
		String req_db_option = request.getParameter("db_option");
		//표적입자
		String req_part1 = request.getParameter("part_1");
		//입사입자
		String req_part2 = ComUtil.isNullToDashString(request.getParameter("part_2"));
		//주프로세스
		String req_mp_option = request.getParameter("mp_option");
		//참조표준인증데이터
		String req_srd_value = ComUtil.isNullToDashString(request.getParameter("srd_value"));
		
		/*
		 * SQL Query 변수 선언
		 */
		//전체 SQL문 저장 변수
		String optionquery = sql_tb_option + ComUtil.makeEqualOption(req_tb_option) 
		            + sql_db_option + ComUtil.makeEqualOption(req_db_option)
		            + sql_expresstion + ComUtil.makeEquaLikeOption(req_part1);
	
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_part2)){//입사입자
			optionquery = optionquery + sql_expresstion + ComUtil.makeEquaLikeOption(req_part2);
		}
		if(!req_mp_option.equalsIgnoreCase("MP_00")){//주프로세스
			optionquery = optionquery + sql_mp_option + ComUtil.makeEqualOption(req_mp_option);
		}
		/*if(!ComVar.STRING_DASH.equalsIgnoreCase(req_srd_value)){//참조표준인증데이터
			optionquery = optionquery + ComUtil.makeEqualOption(req_srd_value);
		}*/
		
		optionquery = optionquery + sql_group_by;
		
		DAO_PROPERTY_SEARCH_INFO info = new DAO_PROPERTY_SEARCH_INFO();
		search_list = info.selectPropertyListCount(optionquery);
		
		Properties_IC_MP_SP count_sum = searchSummary(search_list);
		
		
		return count_sum;
	}
	
	/**
	 * @MethodName  : makeSearchQueryOption
	 * @Date   : 2012. 1. 20. 
	 * @MethodDescription : 검색 옵션별로 Query문을 만들어 String으로 반환하는 메소드
	 * @param request
	 * @return
	 * @History  : - 
	 */
	public String makeSearchQueryOption(HttpServletRequest request){
		//String r_value = "";
		/*
		 * SELECT DISTINCT BASIC.PL_BI_DATA_NUM,
			                BASIC.PL_RAI_ARTCL_NUM,
			                BASIC.PL_BI_EXPRESSION,
			                BASIC.PL_BI_MAIN_PROC,
			                BASIC.PL_BI_SUB_PROC,
			                BASIC.PL_BI_EXP_THE_REC,
			                BASIC.PL_BI_INSERT_FLAG,
			                BASIC.PL_BI_INSERT_DATE
			  FROM PL_BASIC_INFO BASIC, PL_BASIC_EQUATION_INFO EQUA
			 WHERE     BASIC.PL_BI_TOP_BRANCH = 'TB_01'
			       AND BASIC.PL_BI_DATA_BRANCH = 'DB_02'
			       AND BASIC.PL_BI_IMP_CLASS = 'IC_01'
			       AND BASIC.PL_BI_MAIN_PROC = 'MP_02'
			       AND BASIC.PL_BI_SUB_PROC = 'SP_07'
			       AND BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM
			       AND EQUA.PL_CPBI_ELE_NUM = 'A0084'
			       AND EQUA.PL_BEI_SEQ = '2'
		 */
		//대분류
		String req_tb_option = request.getParameter("tb_option");
		//데이터분류
		String req_db_option = request.getParameter("db_option");
		//주프로세스
		String req_mp_option = request.getParameter("mp_option");
		
		//표적입자
		String req_part1 = request.getParameter("part_1_id");
		//System.out.println("표적입자 : " + req_part1);
		//입사입자
		String req_part2 = ComUtil.isNullToDashString(request.getParameter("part_2_id"));

		String req_part2_sym = ComUtil.isNullToDashString(request.getParameter("part_2_sym")).toLowerCase();
		//System.out.println("param_part2 : " + req_part2);
		//System.out.println("param_part2_sym : " + req_part2_sym);
		
		if("-".equalsIgnoreCase(req_part2)){
			if("e".equalsIgnoreCase(req_part2_sym)){ //Electron Impact 
				req_part2 = "E0021";
			}else if("hv".equalsIgnoreCase(req_part2_sym)){ //Photon Impact 
				req_part2 = "P0067";
			}
		}
		
		//System.out.println("param_part2 : " + req_part2);
		//System.out.println("입사입자 : " + req_part2);
		//주프로세스
		//String req_mp_option = request.getParameter("mp_option");
		//참조표준인증데이터
		//String req_srd_value = ComUtil.isNullToDashString(request.getParameter("srd_value"));
		//선택되어진 부프로세스 인덱스
		//String sp_index = ComUtil.isNullToDashString(request.getParameter("gotoindex"));
		//선택되어진 놈으로 그림 그릴지 말지 여부
		//String draw_index = ComUtil.isNullToDashString(request.getParameter("checkDraw"));
		String draw_checkList = ComUtil.isNullToDashString(request.getParameter("checkList"));
		
		//String sql_order_by = " ORDER BY PL_BI_DATA_NUM";
		
		//Properties_Basic_Count info = getTransCode(sp_index);
		
		//충돌방식
		//String req_ic_option = info.getPL_BI_IMP_CLASS();
		//주프로세스
		//String req_mp_option = info.getPL_BI_MAIN_PROC();
		//부프로세스
		//String req_sp_option = info.getPL_BI_SUB_PROC();		
		
		//Order 조건
		String op_odr_col = ComUtil.isNullToNullString(request.getParameter("_oCol"));
		String op_odr_dir = ComUtil.isNullToNullString(request.getParameter("_oDir"));
		
		String str_where_start = " WHERE ";
		String str_exist = "EXISTS" +
						" (SELECT EQUA2.PL_BI_DATA_NUM" +
						" FROM PL_BASIC_EQUATION_INFO EQUA2" +
						" WHERE  EQUA.PL_BI_DATA_NUM = EQUA2.PL_BI_DATA_NUM " +
						" AND EQUA2.PL_BEI_SEQ = '1'";
		String str_exist_option_ele_num = " AND EQUA2.PL_CPBI_ELE_NUM = ";	
		String str_if_part2_where_option = " ) AND BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM";
		String str_basic_where_option = " BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM";
		//String str_where_option_data_num = " AND BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM";
		String str_where_option_tb = " AND BASIC.PL_BI_TOP_BRANCH = ";
		String str_where_option_db = " AND BASIC.PL_BI_DATA_BRANCH = ";
		String str_where_option_ic = " AND BASIC.PL_BI_IMP_CLASS = ";
		String str_where_option_mp = " AND BASIC.PL_BI_MAIN_PROC = ";
		//String str_where_option_sp = " AND BASIC.PL_BI_SUB_PROC = ";
		String str_where_option_ele_num = " AND EQUA.PL_CPBI_ELE_NUM = ";
		String str_where_option_ele_seq = " AND EQUA.PL_BEI_SEQ = '2'";
		String str_where_option_pr_in = " AND BASIC.PL_BI_DATA_NUM IN ";	
		//String str_where_option_order_by = " ORDER BY BASIC.PL_BI_DATA_NUM, BASIC.PL_BI_IMP_CLASS ASC ";
		//충돌방식
		String req_ic_option = "";
		
		
		/*
		 * SQL Query 변수 선언
		 */
		//전체 SQL문 저장 변수	
		String sql_option_query = "";
		sql_option_query = str_where_start;
		//입사입자 처리
		if(!req_part2.equalsIgnoreCase("-")){
			//입사입자 있으면 EXIST 구문 추가
			sql_option_query = sql_option_query + str_exist + str_exist_option_ele_num + ComUtil.makeEqualOption(req_part2);
			sql_option_query = sql_option_query + str_if_part2_where_option;
			
		}else{
			sql_option_query = sql_option_query + str_basic_where_option;
		}
		//대분류 처리
		sql_option_query = sql_option_query + str_where_option_tb + ComUtil.makeEqualOption(req_tb_option);
		//데이터분류 처리
		sql_option_query = sql_option_query + str_where_option_db + ComUtil.makeEqualOption(req_db_option);
		//충돌방식 처리
		//sql_option_query = sql_option_query + str_where_option_ic + ComUtil.makeEqualOption(req_ic_option);
		//주프로세스 처리
		sql_option_query = sql_option_query + str_where_option_mp + ComUtil.makeEqualOption(req_mp_option);
		//부프로세스 처리
		//sql_option_query = sql_option_query + str_where_option_sp + ComUtil.makeEqualOption(req_sp_option);
		//표적입자 처리
		sql_option_query = sql_option_query + str_where_option_ele_seq + str_where_option_ele_num + ComUtil.makeEqualOption(req_part1);
		
		
		//String sql_search_orderby = "AND A.PL_BI_DATA_NUM = B.PL_BI_DATA_NUM ORDER BY A.PL_BI_DATA_NUM ASC";
		//String[] addCheck = ComUtil.isNullArray(request.getParameterValues("check_pr_no"));
		if(ComUtil.isNullArray(request.getParameterValues("check_pr_no")) == ComVar.BOOL_FALSE){
				/*int count = 0;
				String[] addCheck = request.getParameterValues("check_pr_no");
				String addOption = "";
				for(int i = 0; i < addCheck.length; i++){
					///System.out.println("addCheck[ "+i+" ] : " + addCheck[i]);
					if(i == 0){
						addOption = "('" + addCheck[i];
					}else{
						addOption = addOption + addCheck[i];
					}
					if(i != addCheck.length-1){
						addOption = addOption + "','";
					}
				}
				addOption = "";
				addOption = addOption + "')";*/
				String[] s_string = ComUtil.splitString(draw_checkList, ",");
				String pr_list = "("; 
				for(int i = 0; i < s_string.length; i++){
					if(i > 0){
						pr_list = pr_list + ",";
					}
					pr_list = pr_list + "'" + s_string[i] + "'";					
				}
				pr_list = pr_list + ")"; 
				sql_option_query = sql_option_query + str_where_option_pr_in + pr_list;	
		}
		
		//sql_option_query = sql_option_query + str_where_option_order_by;
		
		//System.out.println("sql_option_query : " + sql_option_query);
		
		return sql_option_query;
	}
	
	/**
	 * @MethodName  : searchPropertyListInfo
	 * @Date   : 2010. 1. 15. 
	 * @MethodDescription : 입력 받은 값을 토대로 실제 물성정보 값을 가져오는 메소드
	 * @param request
	 * @return
	 * @History  : - sql_option_query
	 */
	//public Vector searchPropertyListInfo(HttpServletRequest request){
	public Vector<?> searchPropertyListInfo(String sql_option_query){
		Vector<?> search_list = new Vector<Object>();
		/*
		 * SELECT DISTINCT BASIC.PL_BI_DATA_NUM,
			                BASIC.PL_RAI_ARTCL_NUM,
			                BASIC.PL_BI_EXPRESSION,
			                BASIC.PL_BI_MAIN_PROC,
			                BASIC.PL_BI_SUB_PROC,
			                BASIC.PL_BI_EXP_THE_REC,
			                BASIC.PL_BI_INSERT_FLAG,
			                BASIC.PL_BI_INSERT_DATE
			  FROM PL_BASIC_INFO BASIC, PL_BASIC_EQUATION_INFO EQUA
			 WHERE     BASIC.PL_BI_TOP_BRANCH = 'TB_01'
			       AND BASIC.PL_BI_DATA_BRANCH = 'DB_02'
			       AND BASIC.PL_BI_IMP_CLASS = 'IC_01'
			       AND BASIC.PL_BI_MAIN_PROC = 'MP_02'
			       AND BASIC.PL_BI_SUB_PROC = 'SP_07'
			       AND BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM
			       AND EQUA.PL_CPBI_ELE_NUM = 'A0084'
			       AND EQUA.PL_BEI_SEQ = '2'
		 
		//대분류
		String req_tb_option = request.getParameter("tb_option");
		//데이터분류
		String req_db_option = request.getParameter("db_option");
		//표적입자
		String req_part1 = request.getParameter("part_1");
		//입사입자
		String req_part2 = ComUtil.isNullToDashString(request.getParameter("part_2"));
		//주프로세스
		//String req_mp_option = request.getParameter("mp_option");
		//참조표준인증데이터
		String req_srd_value = ComUtil.isNullToDashString(request.getParameter("srd_value"));
		//선택되어진 인덱스
		String gotoindex = ComUtil.isNullToDashString(request.getParameter("gotoindex"));
		
		String sql_order_by = " ORDER BY PL_BI_DATA_NUM";
		
		Properties_Basic_Count info = getTransCode(gotoindex);
		
		//충돌방식
		String req_ic_option = info.getPL_BI_IMP_CLASS();
		//주프로세스
		String req_mp_option = info.getPL_BI_MAIN_PROC();
		//부프로세스
		String req_sp_option = info.getPL_BI_SUB_PROC();			
		
		String str_where_start = " WHERE ";
		String str_exist = "EXISTS" +
						" (SELECT EQUA2.PL_BI_DATA_NUM" +
						" FROM PL_BASIC_EQUATION_INFO EQUA2" +
						" WHERE  EQUA.PL_BI_DATA_NUM = EQUA2.PL_BI_DATA_NUM " +
						" AND EQUA2.PL_BEI_SEQ = '1'";
		String str_exist_option_ele_num = " AND EQUA2.PL_CPBI_ELE_NUM = ";	
		String str_if_part2_where_option = " ) AND BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM";
		String str_basic_where_option = " BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM";
		//String str_where_option_data_num = " AND BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM";
		String str_where_option_tb = " AND BASIC.PL_BI_TOP_BRANCH = ";
		String str_where_option_db = " AND BASIC.PL_BI_DATA_BRANCH = ";
		String str_where_option_ic = " AND BASIC.PL_BI_IMP_CLASS = ";
		String str_where_option_mp = " AND BASIC.PL_BI_MAIN_PROC = ";
		String str_where_option_sp = " AND BASIC.PL_BI_SUB_PROC = ";
		String str_where_option_ele_num = " AND EQUA.PL_CPBI_ELE_NUM = ";
		String str_where_option_ele_seq = " AND EQUA.PL_BEI_SEQ = '2'";
		 */
		/*
		 * SQL Query 변수 선언
		 
		//전체 SQL문 저장 변수	
		String sql_option_query = "";
		sql_option_query = str_where_start;
		//입사입자 처리
		if(!req_part2.equalsIgnoreCase("-")){
			//입사입자 있으면 EXIST 구문 추가
			sql_option_query = sql_option_query + str_exist + str_exist_option_ele_num + ComUtil.makeEqualOption(req_part2);
			sql_option_query = sql_option_query + str_if_part2_where_option;
		}else{
			sql_option_query = sql_option_query + str_basic_where_option;
		}
		//대분류 처리
		sql_option_query = sql_option_query + str_where_option_tb + ComUtil.makeEqualOption(req_tb_option);
		//데이터분류 처리
		sql_option_query = sql_option_query + str_where_option_db + ComUtil.makeEqualOption(req_db_option);
		//주프로세스 처리
		sql_option_query = sql_option_query + str_where_option_mp + ComUtil.makeEqualOption(req_mp_option);
		//충돌방식 처리
		sql_option_query = sql_option_query + str_where_option_ic + ComUtil.makeEqualOption(req_ic_option);
		
		//표적입자 처리
		sql_option_query = sql_option_query + str_where_option_ele_seq + str_where_option_ele_num + ComUtil.makeEqualOption(req_part1);

		*/
		/*String optionquery = sql_tb_option + ComUtil.makeEqualOption(req_tb_option) 
		            + sql_db_option + ComUtil.makeEqualOption(req_db_option)
		            + sql_expresstion + ComUtil.makeEquaLikeOption(req_part1);
	
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_part2)){//입사입자
			optionquery = optionquery + sql_expresstion + ComUtil.makeEquaLikeOption(req_part2);
		}
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_ic_option)){//충돌방식
			optionquery = optionquery + sql_ic_option + ComUtil.makeEqualOption(req_ic_option);
		}
		if(!req_mp_option.equalsIgnoreCase("MP_00")){//주프로세스
			optionquery = optionquery + sql_mp_option + ComUtil.makeEqualOption(req_mp_option);
		}
		if(!req_sp_option.equalsIgnoreCase("SP_00")){//부프로세스
			optionquery = optionquery + sql_sp_option + ComUtil.makeEqualOption(req_sp_option);
		}*/
		/*if(!ComVar.STRING_DASH.equalsIgnoreCase(req_srd_value)){//참조표준인증데이터
			optionquery = optionquery + ComUtil.makeEqualOption(req_srd_value);
		}*/
		
		//String sql_add_option = " AND PL_BI_DATA_NUM IN ";
		//String sql_search_orderby = "AND A.PL_BI_DATA_NUM = B.PL_BI_DATA_NUM ORDER BY A.PL_BI_DATA_NUM ASC";
		//String[] addCheck = ComUtil.isNullArray(request.getParameterValues("check_pr_no"));
		/*if(ComUtil.isNullArray(request.getParameterValues("check_pr_no")) == ComVar.BOOL_FALSE){
			int count = 0;
			String[] addCheck = request.getParameterValues("check_pr_no");
			String addOption = "";
			for(int i = 0; i < addCheck.length; i++){
				///System.out.println("addCheck[ "+i+" ] : " + addCheck[i]);
				if(i == 0){
					addOption = "('" + addCheck[i];
				}else{
					addOption = addOption + addCheck[i];
				}
				if(i != addCheck.length-1){
					addOption = addOption + "','";
				}
			}
			addOption = addOption + "')";
			optionquery = optionquery + sql_add_option + addOption;
		}
		
		optionquery = optionquery + sql_order_by;*/
		//System.out.println("sql_option_query : " +sql_option_query);
		//물성정보 리스트 가져오기
		DAO_PROPERTY_SEARCH_INFO search = new DAO_PROPERTY_SEARCH_INFO();
		search_list = search.selectSearchPropertyList(sql_option_query);
		
		return search_list;
	}
	
	/**
	 * @MethodName  : getConditionInfo
	 * @Date   : 2010. 1. 15. 
	 * @MethodDescription : 오즈 챠트를 그리기 위한 조건을 만드는 메소드
	 * @param request
	 * @return
	 * @History  : - 
	 */
	public String getConditionInfo(HttpServletRequest request){
		
		//대분류
		String req_tb_option = request.getParameter("tb_option");
		//데이터분류
		String req_db_option = request.getParameter("db_option");
		//표적입자
		String req_part1 = ComUtil.convertKorean(request.getParameter("part_1"));
		//입사입자
		String req_part2 = ComUtil.isNullToDashString(request.getParameter("part_2"));
		//주프로세스
		//String req_mp_option = request.getParameter("mp_option");
		//참조표준인증데이터
		String req_srd_value = ComUtil.isNullToDashString(request.getParameter("srd_value"));

		//선택되어진 인덱스
		String gotoindex = ComUtil.isNullToDashString(request.getParameter("gotoindex"));		
		
		Properties_Basic_Count info = getTransCode(gotoindex);
		
		//충돌방식
		String req_ic_option = info.getPL_BI_IMP_CLASS();
		//주프로세스
		String req_mp_option = info.getPL_BI_MAIN_PROC();
		//부프로세스
		String req_sp_option = info.getPL_BI_SUB_PROC();		
		
		/*
		 * SQL Query 변수 선언
		 */
		//전체 SQL문 저장 변수
		String optionquery = sql_tb_option + ComUtil.makeEqualOption(req_tb_option) 
		            + sql_db_option + ComUtil.makeEqualOption(req_db_option)
		            + sql_expresstion + ComUtil.makeEquaLikeOption(req_part1);
	
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_part2)){//입사입자
			optionquery = optionquery + sql_expresstion + ComUtil.makeEquaLikeOption(req_part2);
		}
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_ic_option)){//충돌방식
			optionquery = optionquery + sql_ic_option + ComUtil.makeEqualOption(req_ic_option);
		}
		if(!req_mp_option.equalsIgnoreCase("MP_00")){//주프로세스
			optionquery = optionquery + sql_mp_option + ComUtil.makeEqualOption(req_mp_option);
		}
		if(!req_sp_option.equalsIgnoreCase("SP_00")){//부프로세스
			optionquery = optionquery + sql_sp_option + ComUtil.makeEqualOption(req_sp_option);
		}
		/*if(!ComVar.STRING_DASH.equalsIgnoreCase(req_srd_value)){//참조표준인증데이터
			optionquery = optionquery + ComUtil.makeEqualOption(req_srd_value);
		}*/
		String sql_add_option = " AND A.PL_BI_DATA_NUM IN ";
		//String sql_search_orderby = "AND A.PL_BI_DATA_NUM = B.PL_BI_DATA_NUM ORDER BY A.PL_BI_DATA_NUM ASC";
		//String[] addCheck = ComUtil.isNullArray(request.getParameterValues("check_pr_no"));
		if(ComUtil.isNullArray(request.getParameterValues("check_pr_no")) == ComVar.BOOL_FALSE){
			int count = 0;
			String[] addCheck = request.getParameterValues("check_pr_no");
			String addOption = "";
			for(int i = 0; i < addCheck.length; i++){
				///System.out.println("addCheck[ "+i+" ] : " + addCheck[i]);
				if(i == 0){
					addOption = "('" + addCheck[i];
				}else{
					addOption = addOption + addCheck[i];
				}
				if(i != addCheck.length-1){
					addOption = addOption + "','";
				}
			}
			addOption = addOption + "')";
			optionquery = optionquery + sql_add_option + addOption;
		}
		
		
		//추가 조건 SQL 문
		
		//int count = 0;
		/*for(int i = 0; i < addCheck.length; i++){
			if(addCheck[i].equals(ComVar.BOOL_TRUE) == ComVar.BOOL_TRUE){
				count ++;
			}
		}
		if(count > 0){		

		}*/
		
/*   #OZParam.PM_WHERE_CONDITION#
         AND A.PL_BI_DATA_NUM = B.PL_BI_DATA_NUM 
ORDER BY A.PL_BI_DATA_NUM ASC = "; 
*/		
		
		//optionquery = optionquery + sql_order_by;
		
		return optionquery;
	}
	
	/**
	 * @MethodName  : searchSummary
	 * @Date   : 2010. 1. 15. 
	 * @MethodDescription : 입력받은 값을 토대로 다시 코드별로 Count를 뽑아내는 메소드
	 * @param search_list
	 * @return
	 * @History  : - 
	 */
	public Properties_IC_MP_SP searchSummary(Vector<?> search_list){
		Properties_IC_MP_SP sum = new Properties_IC_MP_SP();
		String html_code_f = "<a href='javascript:void(0);'  onClick='searchview('";
		String html_code_m = "'); return false;'> ";
		String html_code_e = "건</a>";
		int count = search_list.size();
		for(int i = 0; i < count; i++){
			Properties_Basic_Count info = (Properties_Basic_Count)search_list.elementAt(i);
			
			if(info.getPL_BI_IMP_CLASS().equalsIgnoreCase("IC_01")){ //Electron Impact
				if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_01")){ //Scattering
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						String html_code = html_code_f + "ESCAT_Total" + html_code_f + info.getCOUNT() + html_code_e;
						sum.setESCAT_Total(html_code);
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setESCAT_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setESCAT_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setESCAT_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setESCAT_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setESCAT_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setESCAT_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setESCAT_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setESCAT_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setESCAT_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setESCAT_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setESCAT_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setESCAT_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setESCAT_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setESCAT_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setESCAT_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setESCAT_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setESCAT_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setESCAT_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setESCAT_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setESCAT_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setESCAT_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setESCAT_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setESCAT_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setESCAT_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setESCAT_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setESCAT_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setESCAT_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setESCAT_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setESCAT_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setESCAT_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setESCAT_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setESCAT_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setESCAT_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setESCAT_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setESCAT_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setESCAT_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setESCAT_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setESCAT_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_02")){ //Ionization
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setEIONI_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setEIONI_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setEIONI_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setEIONI_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setEIONI_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setEIONI_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setEIONI_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setEIONI_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setEIONI_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setEIONI_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setEIONI_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setEIONI_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setEIONI_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setEIONI_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setEIONI_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setEIONI_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setEIONI_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setEIONI_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setEIONI_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setEIONI_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setEIONI_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setEIONI_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setEIONI_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setEIONI_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setEIONI_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setEIONI_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setEIONI_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setEIONI_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setEIONI_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setEIONI_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setEIONI_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setEIONI_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setEIONI_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setEIONI_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setEIONI_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setEIONI_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setEIONI_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setEIONI_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setEIONI_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_03")){ //Excitation
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setEEXCI_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setEEXCI_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setEEXCI_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setEEXCI_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setEEXCI_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setEEXCI_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setEEXCI_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setEEXCI_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setEEXCI_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setEEXCI_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setEEXCI_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setEEXCI_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setEEXCI_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setEEXCI_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setEEXCI_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setEEXCI_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setEEXCI_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setEEXCI_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setEEXCI_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setEEXCI_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setEEXCI_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setEEXCI_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setEEXCI_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setEEXCI_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setEEXCI_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setEEXCI_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setEEXCI_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setEEXCI_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setEEXCI_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setEEXCI_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setEEXCI_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setEEXCI_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setEEXCI_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setEEXCI_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setEEXCI_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setEEXCI_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setEEXCI_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setEEXCI_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setEEXCI_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_04")){ //Recombination
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setERECO_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setERECO_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setERECO_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setERECO_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setERECO_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setERECO_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setERECO_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setERECO_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setERECO_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setERECO_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setERECO_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setERECO_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setERECO_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setERECO_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setERECO_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setERECO_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setERECO_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setERECO_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setERECO_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setERECO_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setERECO_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setERECO_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setERECO_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setERECO_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setERECO_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setERECO_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setERECO_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setERECO_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setERECO_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setERECO_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setERECO_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setERECO_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setERECO_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setERECO_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setERECO_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setERECO_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setERECO_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setERECO_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setERECO_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_05")){ //Charge Transfer
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setECHTR_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setECHTR_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setECHTR_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setECHTR_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setECHTR_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setECHTR_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setECHTR_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setECHTR_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setECHTR_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setECHTR_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setECHTR_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setECHTR_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setECHTR_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setECHTR_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setECHTR_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setECHTR_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setECHTR_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setECHTR_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setECHTR_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setECHTR_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setECHTR_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setECHTR_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setECHTR_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setECHTR_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setECHTR_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setECHTR_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setECHTR_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setECHTR_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setECHTR_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setECHTR_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setECHTR_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setECHTR_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setECHTR_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setECHTR_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setECHTR_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setECHTR_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setECHTR_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setECHTR_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setECHTR_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_06")){ //Dissociation
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setEDISS_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setEDISS_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setEDISS_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setEDISS_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setEDISS_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setEDISS_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setEDISS_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setEDISS_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setEDISS_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setEDISS_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setEDISS_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setEDISS_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setEDISS_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setEDISS_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setEDISS_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setEDISS_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setEDISS_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setEDISS_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setEDISS_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setEDISS_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setEDISS_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setEDISS_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setEDISS_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setEDISS_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setEDISS_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setEDISS_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setEDISS_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setEDISS_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setEDISS_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setEDISS_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setEDISS_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setEDISS_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setEDISS_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setEDISS_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setEDISS_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setEDISS_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setEDISS_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setEDISS_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setEDISS_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_07")){ //Attachment
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setEATTA_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setEATTA_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setEATTA_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setEATTA_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setEATTA_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setEATTA_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setEATTA_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setEATTA_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setEATTA_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setEATTA_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setEATTA_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setEATTA_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setEATTA_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setEATTA_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setEATTA_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setEATTA_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setEATTA_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setEATTA_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setEATTA_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setEATTA_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setEATTA_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setEATTA_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setEATTA_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setEATTA_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setEATTA_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setEATTA_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setEATTA_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setEATTA_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setEATTA_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setEATTA_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setEATTA_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setEATTA_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setEATTA_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setEATTA_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setEATTA_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setEATTA_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setEATTA_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setEATTA_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setEATTA_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_08")){ //Reaction
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setEREAC_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setEREAC_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setEREAC_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setEREAC_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setEREAC_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setEREAC_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setEREAC_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setEREAC_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setEREAC_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setEREAC_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setEREAC_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setEREAC_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setEREAC_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setEREAC_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setEREAC_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setEREAC_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setEREAC_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setEREAC_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setEREAC_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setEREAC_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setEREAC_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setEREAC_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setEREAC_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setEREAC_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setEREAC_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setEREAC_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setEREAC_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setEREAC_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setEREAC_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setEREAC_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setEREAC_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setEREAC_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setEREAC_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setEREAC_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setEREAC_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setEREAC_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setEREAC_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setEREAC_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setEREAC_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_09")){ //Detachment
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setEDETA_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setEDETA_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setEDETA_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setEDETA_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setEDETA_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setEDETA_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setEDETA_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setEDETA_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setEDETA_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setEDETA_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setEDETA_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setEDETA_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setEDETA_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setEDETA_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setEDETA_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setEDETA_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setEDETA_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setEDETA_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setEDETA_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setEDETA_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setEDETA_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setEDETA_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setEDETA_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setEDETA_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setEDETA_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setEDETA_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setEDETA_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setEDETA_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setEDETA_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setEDETA_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setEDETA_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setEDETA_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setEDETA_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setEDETA_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setEDETA_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setEDETA_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setEDETA_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setEDETA_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setEDETA_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_10")){ //Absorption
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setEABSO_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setEABSO_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setEABSO_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setEABSO_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setEABSO_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setEABSO_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setEABSO_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setEABSO_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setEABSO_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setEABSO_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setEABSO_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setEABSO_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setEABSO_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setEABSO_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setEABSO_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setEABSO_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setEABSO_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setEABSO_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setEABSO_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setEABSO_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setEABSO_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setEABSO_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setEABSO_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setEABSO_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setEABSO_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setEABSO_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setEABSO_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setEABSO_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setEABSO_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setEABSO_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setEABSO_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setEABSO_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setEABSO_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setEABSO_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setEABSO_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setEABSO_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setEABSO_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setEABSO_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setEABSO_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("-")){
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("-")){
						sum.setE_COUNT(info.getCOUNT());
					}
				}
			}else if(info.getPL_BI_IMP_CLASS().equalsIgnoreCase("IC_02")){ //Photon Impact
				if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_01")){ //Scattering
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPSCAT_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPSCAT_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPSCAT_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPSCAT_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPSCAT_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPSCAT_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPSCAT_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPSCAT_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPSCAT_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPSCAT_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPSCAT_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPSCAT_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPSCAT_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPSCAT_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPSCAT_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPSCAT_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPSCAT_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPSCAT_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPSCAT_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPSCAT_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPSCAT_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPSCAT_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPSCAT_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPSCAT_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPSCAT_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPSCAT_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPSCAT_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPSCAT_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPSCAT_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPSCAT_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPSCAT_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPSCAT_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPSCAT_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPSCAT_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPSCAT_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPSCAT_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPSCAT_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPSCAT_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPSCAT_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_02")){ //Ionization
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPIONI_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPIONI_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPIONI_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPIONI_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPIONI_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPIONI_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPIONI_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPIONI_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPIONI_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPIONI_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPIONI_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPIONI_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPIONI_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPIONI_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPIONI_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPIONI_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPIONI_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPIONI_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPIONI_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPIONI_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPIONI_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPIONI_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPIONI_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPIONI_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPIONI_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPIONI_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPIONI_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPIONI_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPIONI_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPIONI_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPIONI_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPIONI_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPIONI_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPIONI_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPIONI_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPIONI_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPIONI_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPIONI_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPIONI_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_03")){ //Excitation
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPEXCI_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPEXCI_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPEXCI_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPEXCI_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPEXCI_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPEXCI_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPEXCI_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPEXCI_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPEXCI_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPEXCI_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPEXCI_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPEXCI_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPEXCI_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPEXCI_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPEXCI_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPEXCI_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPEXCI_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPEXCI_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPEXCI_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPEXCI_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPEXCI_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPEXCI_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPEXCI_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPEXCI_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPEXCI_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPEXCI_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPEXCI_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPEXCI_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPEXCI_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPEXCI_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPEXCI_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPEXCI_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPEXCI_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPEXCI_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPEXCI_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPEXCI_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPEXCI_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPEXCI_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPEXCI_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_04")){ //Recombination
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPRECO_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPRECO_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPRECO_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPRECO_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPRECO_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPRECO_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPRECO_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPRECO_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPRECO_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPRECO_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPRECO_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPRECO_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPRECO_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPRECO_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPRECO_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPRECO_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPRECO_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPRECO_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPRECO_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPRECO_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPRECO_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPRECO_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPRECO_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPRECO_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPRECO_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPRECO_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPRECO_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPRECO_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPRECO_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPRECO_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPRECO_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPRECO_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPRECO_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPRECO_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPRECO_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPRECO_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPRECO_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPRECO_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPRECO_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_05")){ //Charge Transfer
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPCHTR_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPCHTR_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPCHTR_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPCHTR_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPCHTR_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPCHTR_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPCHTR_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPCHTR_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPCHTR_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPCHTR_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPCHTR_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPCHTR_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPCHTR_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPCHTR_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPCHTR_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPCHTR_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPCHTR_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPCHTR_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPCHTR_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPCHTR_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPCHTR_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPCHTR_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPCHTR_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPCHTR_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPCHTR_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPCHTR_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPCHTR_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPCHTR_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPCHTR_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPCHTR_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPCHTR_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPCHTR_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPCHTR_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPCHTR_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPCHTR_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPCHTR_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPCHTR_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPCHTR_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPCHTR_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_06")){ //Dissociation
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPDISS_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPDISS_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPDISS_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPDISS_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPDISS_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPDISS_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPDISS_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPDISS_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPDISS_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPDISS_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPDISS_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPDISS_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPDISS_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPDISS_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPDISS_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPDISS_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPDISS_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPDISS_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPDISS_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPDISS_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPDISS_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPDISS_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPDISS_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPDISS_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPDISS_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPDISS_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPDISS_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPDISS_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPDISS_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPDISS_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPDISS_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPDISS_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPDISS_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPDISS_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPDISS_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPDISS_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPDISS_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPDISS_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPDISS_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_07")){ //Attachment
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPATTA_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPATTA_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPATTA_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPATTA_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPATTA_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPATTA_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPATTA_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPATTA_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPATTA_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPATTA_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPATTA_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPATTA_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPATTA_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPATTA_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPATTA_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPATTA_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPATTA_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPATTA_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPATTA_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPATTA_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPATTA_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPATTA_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPATTA_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPATTA_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPATTA_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPATTA_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPATTA_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPATTA_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPATTA_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPATTA_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPATTA_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPATTA_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPATTA_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPATTA_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPATTA_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPATTA_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPATTA_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPATTA_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPATTA_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_08")){ //Reaction
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPREAC_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPREAC_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPREAC_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPREAC_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPREAC_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPREAC_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPREAC_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPREAC_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPREAC_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPREAC_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPREAC_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPREAC_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPREAC_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPREAC_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPREAC_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPREAC_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPREAC_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPREAC_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPREAC_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPREAC_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPREAC_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPREAC_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPREAC_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPREAC_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPREAC_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPREAC_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPREAC_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPREAC_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPREAC_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPREAC_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPREAC_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPREAC_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPREAC_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPREAC_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPREAC_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPREAC_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPREAC_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPREAC_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPREAC_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_09")){ //Detachment
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPDETA_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPDETA_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPDETA_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPDETA_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPDETA_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPDETA_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPDETA_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPDETA_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPDETA_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPDETA_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPDETA_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPDETA_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPDETA_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPDETA_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPDETA_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPDETA_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPDETA_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPDETA_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPDETA_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPDETA_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPDETA_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPDETA_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPDETA_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPDETA_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPDETA_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPDETA_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPDETA_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPDETA_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPDETA_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPDETA_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPDETA_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPDETA_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPDETA_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPDETA_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPDETA_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPDETA_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPDETA_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPDETA_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPDETA_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_10")){ //Absorption
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPABSO_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPABSO_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPABSO_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPABSO_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPABSO_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPABSO_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPABSO_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPABSO_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPABSO_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPABSO_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPABSO_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPABSO_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPABSO_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPABSO_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPABSO_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPABSO_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPABSO_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPABSO_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPABSO_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPABSO_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPABSO_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPABSO_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPABSO_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPABSO_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPABSO_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPABSO_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPABSO_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPABSO_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPABSO_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPABSO_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPABSO_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPABSO_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPABSO_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPABSO_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPABSO_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPABSO_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPABSO_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPABSO_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPABSO_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("-")){
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("-")){
						sum.setP_COUNT(info.getCOUNT());
					}
				}
			}else if(info.getPL_BI_IMP_CLASS().equalsIgnoreCase("IC_03")){ //Heavy Particle Impact
				if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_01")){ //Scattering
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHSCAT_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHSCAT_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHSCAT_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHSCAT_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHSCAT_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHSCAT_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHSCAT_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHSCAT_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHSCAT_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHSCAT_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHSCAT_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHSCAT_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHSCAT_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHSCAT_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHSCAT_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHSCAT_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHSCAT_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHSCAT_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHSCAT_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHSCAT_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHSCAT_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHSCAT_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHSCAT_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHSCAT_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHSCAT_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHSCAT_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHSCAT_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHSCAT_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHSCAT_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHSCAT_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHSCAT_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHSCAT_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHSCAT_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHSCAT_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHSCAT_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHSCAT_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHSCAT_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHSCAT_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHSCAT_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_02")){ //Ionization
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHIONI_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHIONI_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHIONI_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHIONI_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHIONI_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHIONI_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHIONI_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHIONI_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHIONI_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHIONI_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHIONI_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHIONI_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHIONI_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHIONI_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHIONI_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHIONI_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHIONI_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHIONI_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHIONI_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHIONI_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHIONI_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHIONI_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHIONI_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHIONI_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHIONI_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHIONI_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHIONI_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHIONI_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHIONI_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHIONI_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHIONI_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHIONI_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHIONI_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHIONI_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHIONI_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHIONI_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHIONI_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHIONI_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHIONI_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_03")){ //Excitation
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHEXCI_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHEXCI_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHEXCI_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHEXCI_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHEXCI_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHEXCI_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHEXCI_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHEXCI_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHEXCI_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHEXCI_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHEXCI_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHEXCI_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHEXCI_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHEXCI_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHEXCI_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHEXCI_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHEXCI_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHEXCI_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHEXCI_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHEXCI_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHEXCI_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHEXCI_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHEXCI_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHEXCI_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHEXCI_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHEXCI_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHEXCI_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHEXCI_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHEXCI_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHEXCI_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHEXCI_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHEXCI_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHEXCI_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHEXCI_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHEXCI_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHEXCI_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHEXCI_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHEXCI_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHEXCI_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_04")){ //Recombination
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHRECO_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHRECO_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHRECO_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHRECO_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHRECO_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHRECO_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHRECO_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHRECO_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHRECO_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHRECO_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHRECO_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHRECO_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHRECO_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHRECO_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHRECO_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHRECO_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHRECO_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHRECO_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHRECO_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHRECO_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHRECO_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHRECO_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHRECO_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHRECO_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHRECO_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHRECO_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHRECO_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHRECO_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHRECO_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHRECO_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHRECO_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHRECO_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHRECO_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHRECO_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHRECO_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHRECO_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHRECO_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHRECO_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHRECO_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_05")){ //Charge Transfer
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHCHTR_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHCHTR_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHCHTR_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHCHTR_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHCHTR_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHCHTR_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHCHTR_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHCHTR_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHCHTR_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHCHTR_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHCHTR_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHCHTR_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHCHTR_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHCHTR_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHCHTR_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHCHTR_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHCHTR_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHCHTR_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHCHTR_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHCHTR_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHCHTR_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHCHTR_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHCHTR_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHCHTR_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHCHTR_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHCHTR_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHCHTR_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHCHTR_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHCHTR_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHCHTR_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHCHTR_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHCHTR_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHCHTR_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHCHTR_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHCHTR_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHCHTR_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHCHTR_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHCHTR_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHCHTR_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_06")){ //Dissociation
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHDISS_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHDISS_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHDISS_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHDISS_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHDISS_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHDISS_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHDISS_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHDISS_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHDISS_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHDISS_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHDISS_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHDISS_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHDISS_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHDISS_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHDISS_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHDISS_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHDISS_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHDISS_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHDISS_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHDISS_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHDISS_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHDISS_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHDISS_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHDISS_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHDISS_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHDISS_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHDISS_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHDISS_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHDISS_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHDISS_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHDISS_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHDISS_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHDISS_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHDISS_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHDISS_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHDISS_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHDISS_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHDISS_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHDISS_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_07")){ //Attachment
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHATTA_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHATTA_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHATTA_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHATTA_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHATTA_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHATTA_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHATTA_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHATTA_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHATTA_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHATTA_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHATTA_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHATTA_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHATTA_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHATTA_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHATTA_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHATTA_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHATTA_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHATTA_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHATTA_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHATTA_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHATTA_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHATTA_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHATTA_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHATTA_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHATTA_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHATTA_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHATTA_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHATTA_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHATTA_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHATTA_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHATTA_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHATTA_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHATTA_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHATTA_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHATTA_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHATTA_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHATTA_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHATTA_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHATTA_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_08")){ //Reaction
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHREAC_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHREAC_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHREAC_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHREAC_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHREAC_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHREAC_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHREAC_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHREAC_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHREAC_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHREAC_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHREAC_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHREAC_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHREAC_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHREAC_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHREAC_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHREAC_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHREAC_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHREAC_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHREAC_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHREAC_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHREAC_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHREAC_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHREAC_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHREAC_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHREAC_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHREAC_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHREAC_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHREAC_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHREAC_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHREAC_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHREAC_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHREAC_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHREAC_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHREAC_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHREAC_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHREAC_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHREAC_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHREAC_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHREAC_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_09")){ //Detachment
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHDETA_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHDETA_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHDETA_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHDETA_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHDETA_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHDETA_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHDETA_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHDETA_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHDETA_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHDETA_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHDETA_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHDETA_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHDETA_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHDETA_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHDETA_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHDETA_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHDETA_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHDETA_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHDETA_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHDETA_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHDETA_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHDETA_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHDETA_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHDETA_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHDETA_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHDETA_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHDETA_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHDETA_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHDETA_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHDETA_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHDETA_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHDETA_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHDETA_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHDETA_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHDETA_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHDETA_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHDETA_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHDETA_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHDETA_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_10")){ //Absorption
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHABSO_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHABSO_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHABSO_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHABSO_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHABSO_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHABSO_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHABSO_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHABSO_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHABSO_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHABSO_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHABSO_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHABSO_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHABSO_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHABSO_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHABSO_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHABSO_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHABSO_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHABSO_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHABSO_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHABSO_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHABSO_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHABSO_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHABSO_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHABSO_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHABSO_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHABSO_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHABSO_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHABSO_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHABSO_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHABSO_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHABSO_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHABSO_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHABSO_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHABSO_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHABSO_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHABSO_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHABSO_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHABSO_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHABSO_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("-")){
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("-")){
						sum.setH_COUNT(info.getCOUNT());
					}
				}
			}
			
		}
		
		return sum;
	}
	/*public Properties_IC_MP_SP searchSummary(Vector search_list){
		Properties_IC_MP_SP sum = new Properties_IC_MP_SP();
		
		int count = search_list.size();
		for(int i = 0; i < count; i++){
			Properties_Basic_Count info = (Properties_Basic_Count)search_list.elementAt(i);
			
			if(info.getPL_BI_IMP_CLASS().equalsIgnoreCase("IC_01")){ //Electron Impact
				if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_01")){ //Scattering
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setESCAT_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setESCAT_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setESCAT_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setESCAT_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setESCAT_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setESCAT_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setESCAT_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setESCAT_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setESCAT_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setESCAT_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setESCAT_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setESCAT_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setESCAT_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setESCAT_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setESCAT_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setESCAT_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setESCAT_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setESCAT_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setESCAT_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setESCAT_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setESCAT_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setESCAT_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setESCAT_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setESCAT_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setESCAT_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setESCAT_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setESCAT_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setESCAT_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setESCAT_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setESCAT_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setESCAT_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setESCAT_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setESCAT_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setESCAT_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setESCAT_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setESCAT_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setESCAT_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setESCAT_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setESCAT_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_02")){ //Ionization
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setEIONI_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setEIONI_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setEIONI_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setEIONI_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setEIONI_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setEIONI_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setEIONI_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setEIONI_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setEIONI_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setEIONI_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setEIONI_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setEIONI_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setEIONI_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setEIONI_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setEIONI_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setEIONI_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setEIONI_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setEIONI_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setEIONI_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setEIONI_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setEIONI_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setEIONI_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setEIONI_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setEIONI_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setEIONI_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setEIONI_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setEIONI_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setEIONI_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setEIONI_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setEIONI_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setEIONI_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setEIONI_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setEIONI_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setEIONI_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setEIONI_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setEIONI_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setEIONI_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setEIONI_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setEIONI_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_03")){ //Excitation
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setEEXCI_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setEEXCI_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setEEXCI_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setEEXCI_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setEEXCI_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setEEXCI_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setEEXCI_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setEEXCI_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setEEXCI_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setEEXCI_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setEEXCI_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setEEXCI_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setEEXCI_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setEEXCI_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setEEXCI_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setEEXCI_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setEEXCI_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setEEXCI_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setEEXCI_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setEEXCI_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setEEXCI_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setEEXCI_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setEEXCI_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setEEXCI_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setEEXCI_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setEEXCI_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setEEXCI_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setEEXCI_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setEEXCI_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setEEXCI_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setEEXCI_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setEEXCI_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setEEXCI_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setEEXCI_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setEEXCI_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setEEXCI_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setEEXCI_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setEEXCI_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setEEXCI_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_04")){ //Recombination
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setERECO_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setERECO_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setERECO_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setERECO_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setERECO_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setERECO_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setERECO_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setERECO_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setERECO_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setERECO_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setERECO_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setERECO_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setERECO_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setERECO_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setERECO_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setERECO_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setERECO_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setERECO_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setERECO_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setERECO_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setERECO_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setERECO_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setERECO_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setERECO_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setERECO_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setERECO_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setERECO_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setERECO_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setERECO_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setERECO_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setERECO_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setERECO_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setERECO_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setERECO_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setERECO_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setERECO_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setERECO_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setERECO_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setERECO_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_05")){ //Charge Transfer
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setECHTR_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setECHTR_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setECHTR_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setECHTR_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setECHTR_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setECHTR_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setECHTR_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setECHTR_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setECHTR_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setECHTR_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setECHTR_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setECHTR_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setECHTR_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setECHTR_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setECHTR_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setECHTR_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setECHTR_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setECHTR_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setECHTR_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setECHTR_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setECHTR_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setECHTR_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setECHTR_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setECHTR_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setECHTR_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setECHTR_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setECHTR_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setECHTR_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setECHTR_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setECHTR_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setECHTR_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setECHTR_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setECHTR_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setECHTR_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setECHTR_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setECHTR_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setECHTR_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setECHTR_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setECHTR_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_06")){ //Dissociation
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setEDISS_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setEDISS_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setEDISS_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setEDISS_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setEDISS_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setEDISS_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setEDISS_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setEDISS_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setEDISS_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setEDISS_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setEDISS_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setEDISS_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setEDISS_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setEDISS_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setEDISS_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setEDISS_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setEDISS_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setEDISS_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setEDISS_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setEDISS_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setEDISS_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setEDISS_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setEDISS_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setEDISS_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setEDISS_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setEDISS_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setEDISS_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setEDISS_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setEDISS_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setEDISS_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setEDISS_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setEDISS_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setEDISS_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setEDISS_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setEDISS_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setEDISS_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setEDISS_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setEDISS_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setEDISS_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_07")){ //Attachment
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setEATTA_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setEATTA_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setEATTA_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setEATTA_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setEATTA_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setEATTA_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setEATTA_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setEATTA_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setEATTA_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setEATTA_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setEATTA_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setEATTA_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setEATTA_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setEATTA_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setEATTA_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setEATTA_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setEATTA_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setEATTA_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setEATTA_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setEATTA_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setEATTA_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setEATTA_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setEATTA_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setEATTA_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setEATTA_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setEATTA_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setEATTA_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setEATTA_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setEATTA_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setEATTA_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setEATTA_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setEATTA_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setEATTA_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setEATTA_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setEATTA_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setEATTA_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setEATTA_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setEATTA_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setEATTA_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_08")){ //Reaction
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setEREAC_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setEREAC_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setEREAC_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setEREAC_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setEREAC_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setEREAC_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setEREAC_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setEREAC_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setEREAC_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setEREAC_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setEREAC_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setEREAC_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setEREAC_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setEREAC_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setEREAC_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setEREAC_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setEREAC_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setEREAC_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setEREAC_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setEREAC_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setEREAC_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setEREAC_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setEREAC_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setEREAC_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setEREAC_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setEREAC_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setEREAC_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setEREAC_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setEREAC_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setEREAC_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setEREAC_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setEREAC_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setEREAC_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setEREAC_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setEREAC_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setEREAC_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setEREAC_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setEREAC_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setEREAC_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_09")){ //Detachment
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setEDETA_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setEDETA_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setEDETA_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setEDETA_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setEDETA_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setEDETA_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setEDETA_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setEDETA_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setEDETA_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setEDETA_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setEDETA_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setEDETA_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setEDETA_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setEDETA_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setEDETA_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setEDETA_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setEDETA_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setEDETA_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setEDETA_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setEDETA_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setEDETA_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setEDETA_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setEDETA_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setEDETA_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setEDETA_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setEDETA_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setEDETA_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setEDETA_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setEDETA_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setEDETA_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setEDETA_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setEDETA_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setEDETA_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setEDETA_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setEDETA_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setEDETA_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setEDETA_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setEDETA_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setEDETA_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_10")){ //Absorption
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setEABSO_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setEABSO_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setEABSO_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setEABSO_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setEABSO_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setEABSO_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setEABSO_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setEABSO_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setEABSO_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setEABSO_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setEABSO_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setEABSO_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setEABSO_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setEABSO_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setEABSO_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setEABSO_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setEABSO_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setEABSO_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setEABSO_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setEABSO_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setEABSO_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setEABSO_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setEABSO_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setEABSO_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setEABSO_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setEABSO_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setEABSO_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setEABSO_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setEABSO_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setEABSO_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setEABSO_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setEABSO_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setEABSO_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setEABSO_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setEABSO_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setEABSO_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setEABSO_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setEABSO_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setEABSO_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("-")){
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("-")){
						sum.setE_COUNT(info.getCOUNT());
					}
				}
			}else if(info.getPL_BI_IMP_CLASS().equalsIgnoreCase("IC_02")){ //Photon Impact
				if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_01")){ //Scattering
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPSCAT_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPSCAT_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPSCAT_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPSCAT_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPSCAT_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPSCAT_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPSCAT_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPSCAT_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPSCAT_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPSCAT_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPSCAT_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPSCAT_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPSCAT_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPSCAT_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPSCAT_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPSCAT_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPSCAT_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPSCAT_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPSCAT_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPSCAT_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPSCAT_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPSCAT_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPSCAT_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPSCAT_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPSCAT_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPSCAT_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPSCAT_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPSCAT_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPSCAT_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPSCAT_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPSCAT_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPSCAT_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPSCAT_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPSCAT_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPSCAT_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPSCAT_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPSCAT_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPSCAT_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPSCAT_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_02")){ //Ionization
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPIONI_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPIONI_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPIONI_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPIONI_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPIONI_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPIONI_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPIONI_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPIONI_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPIONI_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPIONI_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPIONI_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPIONI_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPIONI_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPIONI_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPIONI_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPIONI_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPIONI_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPIONI_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPIONI_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPIONI_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPIONI_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPIONI_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPIONI_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPIONI_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPIONI_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPIONI_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPIONI_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPIONI_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPIONI_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPIONI_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPIONI_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPIONI_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPIONI_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPIONI_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPIONI_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPIONI_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPIONI_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPIONI_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPIONI_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_03")){ //Excitation
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPEXCI_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPEXCI_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPEXCI_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPEXCI_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPEXCI_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPEXCI_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPEXCI_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPEXCI_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPEXCI_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPEXCI_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPEXCI_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPEXCI_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPEXCI_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPEXCI_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPEXCI_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPEXCI_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPEXCI_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPEXCI_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPEXCI_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPEXCI_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPEXCI_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPEXCI_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPEXCI_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPEXCI_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPEXCI_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPEXCI_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPEXCI_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPEXCI_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPEXCI_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPEXCI_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPEXCI_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPEXCI_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPEXCI_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPEXCI_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPEXCI_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPEXCI_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPEXCI_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPEXCI_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPEXCI_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_04")){ //Recombination
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPRECO_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPRECO_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPRECO_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPRECO_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPRECO_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPRECO_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPRECO_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPRECO_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPRECO_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPRECO_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPRECO_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPRECO_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPRECO_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPRECO_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPRECO_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPRECO_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPRECO_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPRECO_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPRECO_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPRECO_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPRECO_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPRECO_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPRECO_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPRECO_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPRECO_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPRECO_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPRECO_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPRECO_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPRECO_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPRECO_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPRECO_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPRECO_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPRECO_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPRECO_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPRECO_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPRECO_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPRECO_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPRECO_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPRECO_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_05")){ //Charge Transfer
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPCHTR_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPCHTR_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPCHTR_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPCHTR_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPCHTR_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPCHTR_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPCHTR_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPCHTR_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPCHTR_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPCHTR_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPCHTR_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPCHTR_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPCHTR_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPCHTR_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPCHTR_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPCHTR_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPCHTR_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPCHTR_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPCHTR_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPCHTR_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPCHTR_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPCHTR_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPCHTR_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPCHTR_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPCHTR_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPCHTR_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPCHTR_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPCHTR_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPCHTR_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPCHTR_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPCHTR_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPCHTR_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPCHTR_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPCHTR_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPCHTR_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPCHTR_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPCHTR_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPCHTR_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPCHTR_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_06")){ //Dissociation
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPDISS_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPDISS_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPDISS_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPDISS_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPDISS_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPDISS_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPDISS_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPDISS_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPDISS_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPDISS_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPDISS_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPDISS_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPDISS_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPDISS_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPDISS_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPDISS_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPDISS_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPDISS_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPDISS_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPDISS_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPDISS_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPDISS_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPDISS_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPDISS_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPDISS_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPDISS_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPDISS_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPDISS_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPDISS_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPDISS_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPDISS_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPDISS_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPDISS_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPDISS_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPDISS_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPDISS_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPDISS_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPDISS_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPDISS_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_07")){ //Attachment
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPATTA_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPATTA_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPATTA_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPATTA_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPATTA_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPATTA_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPATTA_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPATTA_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPATTA_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPATTA_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPATTA_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPATTA_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPATTA_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPATTA_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPATTA_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPATTA_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPATTA_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPATTA_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPATTA_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPATTA_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPATTA_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPATTA_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPATTA_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPATTA_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPATTA_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPATTA_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPATTA_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPATTA_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPATTA_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPATTA_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPATTA_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPATTA_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPATTA_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPATTA_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPATTA_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPATTA_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPATTA_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPATTA_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPATTA_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_08")){ //Reaction
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPREAC_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPREAC_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPREAC_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPREAC_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPREAC_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPREAC_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPREAC_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPREAC_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPREAC_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPREAC_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPREAC_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPREAC_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPREAC_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPREAC_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPREAC_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPREAC_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPREAC_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPREAC_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPREAC_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPREAC_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPREAC_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPREAC_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPREAC_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPREAC_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPREAC_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPREAC_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPREAC_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPREAC_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPREAC_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPREAC_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPREAC_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPREAC_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPREAC_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPREAC_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPREAC_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPREAC_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPREAC_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPREAC_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPREAC_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_09")){ //Detachment
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPDETA_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPDETA_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPDETA_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPDETA_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPDETA_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPDETA_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPDETA_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPDETA_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPDETA_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPDETA_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPDETA_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPDETA_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPDETA_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPDETA_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPDETA_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPDETA_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPDETA_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPDETA_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPDETA_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPDETA_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPDETA_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPDETA_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPDETA_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPDETA_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPDETA_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPDETA_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPDETA_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPDETA_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPDETA_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPDETA_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPDETA_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPDETA_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPDETA_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPDETA_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPDETA_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPDETA_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPDETA_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPDETA_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPDETA_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_10")){ //Absorption
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setPABSO_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setPABSO_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setPABSO_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setPABSO_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setPABSO_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setPABSO_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setPABSO_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setPABSO_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setPABSO_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setPABSO_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setPABSO_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setPABSO_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setPABSO_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setPABSO_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setPABSO_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setPABSO_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setPABSO_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setPABSO_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setPABSO_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setPABSO_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setPABSO_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setPABSO_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setPABSO_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setPABSO_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setPABSO_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setPABSO_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setPABSO_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setPABSO_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setPABSO_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setPABSO_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setPABSO_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setPABSO_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setPABSO_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setPABSO_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setPABSO_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setPABSO_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setPABSO_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setPABSO_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setPABSO_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("-")){
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("-")){
						sum.setP_COUNT(info.getCOUNT());
					}
				}
			}else if(info.getPL_BI_IMP_CLASS().equalsIgnoreCase("IC_03")){ //Heavy Particle Impact
				if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_01")){ //Scattering
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHSCAT_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHSCAT_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHSCAT_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHSCAT_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHSCAT_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHSCAT_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHSCAT_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHSCAT_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHSCAT_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHSCAT_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHSCAT_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHSCAT_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHSCAT_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHSCAT_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHSCAT_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHSCAT_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHSCAT_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHSCAT_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHSCAT_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHSCAT_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHSCAT_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHSCAT_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHSCAT_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHSCAT_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHSCAT_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHSCAT_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHSCAT_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHSCAT_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHSCAT_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHSCAT_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHSCAT_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHSCAT_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHSCAT_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHSCAT_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHSCAT_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHSCAT_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHSCAT_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHSCAT_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHSCAT_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_02")){ //Ionization
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHIONI_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHIONI_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHIONI_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHIONI_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHIONI_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHIONI_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHIONI_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHIONI_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHIONI_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHIONI_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHIONI_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHIONI_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHIONI_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHIONI_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHIONI_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHIONI_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHIONI_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHIONI_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHIONI_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHIONI_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHIONI_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHIONI_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHIONI_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHIONI_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHIONI_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHIONI_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHIONI_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHIONI_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHIONI_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHIONI_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHIONI_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHIONI_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHIONI_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHIONI_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHIONI_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHIONI_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHIONI_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHIONI_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHIONI_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_03")){ //Excitation
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHEXCI_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHEXCI_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHEXCI_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHEXCI_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHEXCI_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHEXCI_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHEXCI_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHEXCI_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHEXCI_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHEXCI_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHEXCI_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHEXCI_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHEXCI_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHEXCI_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHEXCI_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHEXCI_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHEXCI_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHEXCI_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHEXCI_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHEXCI_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHEXCI_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHEXCI_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHEXCI_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHEXCI_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHEXCI_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHEXCI_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHEXCI_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHEXCI_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHEXCI_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHEXCI_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHEXCI_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHEXCI_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHEXCI_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHEXCI_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHEXCI_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHEXCI_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHEXCI_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHEXCI_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHEXCI_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_04")){ //Recombination
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHRECO_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHRECO_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHRECO_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHRECO_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHRECO_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHRECO_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHRECO_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHRECO_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHRECO_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHRECO_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHRECO_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHRECO_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHRECO_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHRECO_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHRECO_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHRECO_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHRECO_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHRECO_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHRECO_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHRECO_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHRECO_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHRECO_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHRECO_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHRECO_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHRECO_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHRECO_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHRECO_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHRECO_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHRECO_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHRECO_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHRECO_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHRECO_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHRECO_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHRECO_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHRECO_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHRECO_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHRECO_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHRECO_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHRECO_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_05")){ //Charge Transfer
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHCHTR_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHCHTR_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHCHTR_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHCHTR_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHCHTR_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHCHTR_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHCHTR_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHCHTR_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHCHTR_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHCHTR_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHCHTR_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHCHTR_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHCHTR_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHCHTR_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHCHTR_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHCHTR_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHCHTR_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHCHTR_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHCHTR_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHCHTR_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHCHTR_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHCHTR_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHCHTR_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHCHTR_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHCHTR_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHCHTR_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHCHTR_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHCHTR_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHCHTR_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHCHTR_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHCHTR_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHCHTR_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHCHTR_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHCHTR_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHCHTR_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHCHTR_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHCHTR_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHCHTR_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHCHTR_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_06")){ //Dissociation
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHDISS_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHDISS_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHDISS_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHDISS_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHDISS_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHDISS_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHDISS_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHDISS_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHDISS_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHDISS_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHDISS_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHDISS_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHDISS_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHDISS_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHDISS_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHDISS_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHDISS_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHDISS_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHDISS_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHDISS_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHDISS_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHDISS_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHDISS_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHDISS_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHDISS_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHDISS_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHDISS_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHDISS_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHDISS_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHDISS_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHDISS_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHDISS_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHDISS_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHDISS_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHDISS_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHDISS_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHDISS_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHDISS_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHDISS_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_07")){ //Attachment
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHATTA_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHATTA_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHATTA_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHATTA_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHATTA_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHATTA_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHATTA_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHATTA_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHATTA_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHATTA_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHATTA_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHATTA_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHATTA_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHATTA_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHATTA_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHATTA_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHATTA_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHATTA_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHATTA_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHATTA_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHATTA_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHATTA_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHATTA_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHATTA_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHATTA_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHATTA_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHATTA_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHATTA_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHATTA_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHATTA_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHATTA_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHATTA_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHATTA_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHATTA_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHATTA_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHATTA_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHATTA_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHATTA_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHATTA_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_08")){ //Reaction
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHREAC_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHREAC_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHREAC_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHREAC_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHREAC_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHREAC_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHREAC_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHREAC_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHREAC_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHREAC_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHREAC_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHREAC_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHREAC_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHREAC_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHREAC_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHREAC_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHREAC_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHREAC_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHREAC_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHREAC_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHREAC_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHREAC_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHREAC_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHREAC_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHREAC_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHREAC_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHREAC_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHREAC_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHREAC_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHREAC_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHREAC_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHREAC_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHREAC_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHREAC_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHREAC_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHREAC_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHREAC_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHREAC_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHREAC_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_09")){ //Detachment
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHDETA_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHDETA_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHDETA_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHDETA_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHDETA_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHDETA_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHDETA_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHDETA_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHDETA_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHDETA_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHDETA_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHDETA_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHDETA_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHDETA_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHDETA_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHDETA_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHDETA_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHDETA_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHDETA_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHDETA_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHDETA_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHDETA_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHDETA_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHDETA_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHDETA_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHDETA_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHDETA_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHDETA_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHDETA_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHDETA_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHDETA_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHDETA_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHDETA_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHDETA_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHDETA_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHDETA_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHDETA_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHDETA_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHDETA_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_10")){ //Absorption
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
						sum.setHABSO_Total(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
						sum.setHABSO_Elastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
						sum.setHABSO_Momentum_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
						sum.setHABSO_Single(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
						sum.setHABSO_Double(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
						sum.setHABSO_Multiple(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
						sum.setHABSO_Partial(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
						sum.setHABSO_Associative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
						sum.setHABSO_Penning(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
						sum.setHABSO_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
						sum.setHABSO_Electronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
						sum.setHABSO_Vibrational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
						sum.setHABSO_Rotational(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
						sum.setHABSO_Radiative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
						sum.setHABSO_Dielectronic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
						sum.setHABSO_Three_Body(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
						sum.setHABSO_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
						sum.setHABSO_Transfer_Ionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
						sum.setHABSO_Thermal_Electron(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
						sum.setHABSO_Electron_Loss(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
						sum.setHABSO_Particle_Interchange(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
						sum.setHABSO_Total_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
						sum.setHABSO_Neutral_Fragments(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
						sum.setHABSO_Total_Dissociative(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
						sum.setHABSO_Detachment(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
						sum.setHABSO_Autoionization(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
						sum.setHABSO_Quenching(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
						sum.setHABSO_X_ray_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
						sum.setHABSO_Relacxation_Reaction(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
						sum.setHABSO_State_Selectivity(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
						sum.setHABSO_Photon(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
						sum.setHABSO_Ion_Pair_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
						sum.setHABSO_Charge_Transfer(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
						sum.setHABSO_de_Excitation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
						sum.setHABSO_Neutral_Product_Dissociation(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
						sum.setHABSO_Attachement(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
						sum.setHABSO_Inelastic(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
						sum.setHABSO_Electron_Production(info.getCOUNT());
					}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
						sum.setHABSO_Vibrotational(info.getCOUNT());
					}
				}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("-")){
					if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("-")){
						sum.setH_COUNT(info.getCOUNT());
					}
				}
			}
			
			}else if(info.getPL_BI_MAIN_PROC().equalsIgnoreCase("MP_05")){ //ChargeTransfer
			if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_01")){   //Total
				sum.setEABSO_Total(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_02")){   //Elastic
				sum.setEABSO_Elastic(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_03")){   //Momentum Transfer 
				sum.setEABSO_Momentum_Transfer(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_04")){   //Single
				sum.setEABSO_Single(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_05")){   //Double
				sum.setEABSO_Double(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_06")){   //Multiple
				sum.setEABSO_Multiple(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_07")){   //Partial
				sum.setEABSO_Partial(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_08")){   //Associative
				sum.setEABSO_Associative(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_09")){   //Penning
				sum.setEABSO_Penning(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_10")){   //Reaction
				sum.setEABSO_Reaction(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_11")){   //Electronic
				sum.setEABSO_Electronic(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_12")){   //Vibrational
				sum.setEABSO_Vibrational(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_13")){   //Rotational
				sum.setEABSO_Rotational(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_14")){   //Radiative
				sum.setEABSO_Radiative(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_15")){   //Dielectronic
				sum.setEABSO_Dielectronic(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_16")){   //Three body
				sum.setEABSO_Three_Body(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_17")){   //Dissociative
				sum.setEABSO_Dissociative(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_18")){   //Transfer Ionization 
				sum.setEABSO_Transfer_Ionization(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_19")){   //Thermal Electron 
				sum.setEABSO_Thermal_Electron(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_20")){   //Electron Loss 
				sum.setEABSO_Electron_Loss(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_21")){   //Particle Interchange 
				sum.setEABSO_Particle_Interchange(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_22")){   //Total Neutral Fragments
				sum.setEABSO_Total_Neutral_Fragments(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_23")){   //Neutral Fragments
				sum.setEABSO_Neutral_Fragments(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_24")){   //Total Dissociative
				sum.setEABSO_Total_Dissociative(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_25")){   //Detachment
				sum.setEABSO_Detachment(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_26")){   //Autoionization
				sum.setEABSO_Autoionization(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_27")){   //Quenching
				sum.setEABSO_Quenching(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_28")){   //X-ray Production
				sum.setEABSO_X_ray_Production(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_29")){   //Relacxation Reaction
				sum.setEABSO_Relacxation_Reaction(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_30")){   //State Selectivity
				sum.setEABSO_State_Selectivity(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_31")){   //Photon
				sum.setEABSO_Photon(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_32")){   //Ion Pair Production
				sum.setEABSO_Ion_Pair_Production(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_33")){   //Charge Transfer
				sum.setEABSO_Charge_Transfer(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_34")){   //de-Excitation
				sum.setEABSO_de_Excitation(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_35")){   //Neutral Product Dissociation
				sum.setEABSO_Neutral_Product_Dissociation(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_36")){   //Attachement
				sum.setEABSO_Attachement(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_37")){   //Inelastic
				sum.setEABSO_Inelastic(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_38")){   //Electron Production
				sum.setEABSO_Electron_Production(info.getCOUNT());
			}else if(info.getPL_BI_SUB_PROC().equalsIgnoreCase("SP_39")){   //Vibrotational
				sum.setEABSO_Vibrotational(info.getCOUNT());
			}
		}
		
		return sum;
	}*/
	
	/**
	 * @MethodName  : getTransCode
	 * @Date   : 2010. 1. 15. 
	 * @MethodDescription : 입력받은 index를 토대로 검색 조건을 만들어내는 메소드
	 * @param gotoindex
	 * @return
	 * @History  : - 
	 */
	public Properties_Basic_Count getTransCode(String gotoindex){
		Properties_Basic_Count info = new Properties_Basic_Count();
			
		//////////////////////////////////////////
		//////////////////////////////////////////
		//////////////////////////////////////////
		//////////////////////////////////////////
		//Electron Impact - Scattering
		//////////////////////////////////////////
		//////////////////////////////////////////
		//////////////////////////////////////////
		//////////////////////////////////////////
		if(gotoindex.equalsIgnoreCase("ESCAT_Total")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Single")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Double")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Partial")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Associative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Penning")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Photon")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("ESCAT_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Electron Impact - Ionization
		}else if(gotoindex.equalsIgnoreCase("EIONI_Total")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Single")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Double")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Partial")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Associative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Penning")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("EIONI_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("EIONI_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Photon")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("EIONI_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("EIONI_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Electron Impact - Excitation
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Total")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Single")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Double")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Partial")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Associative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Penning")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Photon")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("EEXCI_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Electron Impact - Recombination
		}else if(gotoindex.equalsIgnoreCase("ERECO_Total")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Single")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Double")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Partial")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Associative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Penning")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("ERECO_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("ERECO_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Photon")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("ERECO_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("ERECO_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Electron Impact - Charge Transfer
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Total")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Single")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Double")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Partial")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Associative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Penning")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Photon")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("ECHTR_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Electron Impact - Dissociation
		}else if(gotoindex.equalsIgnoreCase("EDISS_Total")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Single")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Double")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Partial")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Associative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Penning")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("EDISS_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("EDISS_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Photon")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("EDISS_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("EDISS_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Electron Impact - Attachment
		}else if(gotoindex.equalsIgnoreCase("EATTA_Total")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Single")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Double")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Partial")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Associative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Penning")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("EATTA_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("EATTA_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Photon")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("EATTA_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("EATTA_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Electron Impact - Reaction
		}else if(gotoindex.equalsIgnoreCase("EREAC_Total")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Single")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Double")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Partial")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Associative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Penning")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("EREAC_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("EREAC_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Photon")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("EREAC_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("EREAC_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Electron Impact - Detachment     
		}else if(gotoindex.equalsIgnoreCase("EDETA_Total")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Single")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Double")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Partial")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Associative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Penning")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("EDETA_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("EDETA_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Photon")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("EDETA_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("EDETA_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Electron Impact - Absorption 
		}else if(gotoindex.equalsIgnoreCase("EABSO_Total")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Single")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Double")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Partial")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Associative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Penning")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("EABSO_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("EABSO_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Photon")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("EABSO_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("EABSO_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//////////////////////////////////////////
		//////////////////////////////////////////
		//////////////////////////////////////////
		//////////////////////////////////////////
		//Photon Impact - Scattering
		//////////////////////////////////////////
		//////////////////////////////////////////
		//////////////////////////////////////////
		//////////////////////////////////////////
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Total")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Single")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Double")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Partial")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Associative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Penning")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Photon")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("PSCAT_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Photon Impact - Ionization
		}else if(gotoindex.equalsIgnoreCase("PIONI_Total")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Single")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Double")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Partial")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Associative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Penning")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("PIONI_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("PIONI_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Photon")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("PIONI_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("PIONI_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Photon Impact - Excitation
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Total")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Single")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Double")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Partial")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Associative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Penning")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Photon")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("PEXCI_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Photon Impact - Recombination
		}else if(gotoindex.equalsIgnoreCase("PRECO_Total")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Single")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Double")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Partial")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Associative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Penning")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("PRECO_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("PRECO_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Photon")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("PRECO_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("PRECO_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Photon Impact - Charge Transfer
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Total")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Single")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Double")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Partial")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Associative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Penning")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Photon")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("PCHTR_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Photon Impact - Dissociation
		}else if(gotoindex.equalsIgnoreCase("PDISS_Total")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Single")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Double")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Partial")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Associative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Penning")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("PDISS_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("PDISS_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Photon")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("PDISS_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("PDISS_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Photon Impact - Attachment
		}else if(gotoindex.equalsIgnoreCase("PATTA_Total")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Single")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Double")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Partial")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Associative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Penning")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("PATTA_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("PATTA_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Photon")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("PATTA_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("PATTA_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Photon Impact - Reaction
		}else if(gotoindex.equalsIgnoreCase("PREAC_Total")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Single")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Double")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Partial")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Associative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Penning")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("PREAC_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("PREAC_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Photon")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("PREAC_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("PREAC_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Photon Impact - Detachment     
		}else if(gotoindex.equalsIgnoreCase("PDETA_Total")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Single")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Double")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Partial")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Associative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Penning")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("PDETA_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("PDETA_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Photon")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("PDETA_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("PDETA_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Photon Impact - Absorption 
		}else if(gotoindex.equalsIgnoreCase("PABSO_Total")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Single")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Double")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Partial")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Associative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Penning")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("PABSO_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("PABSO_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Photon")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("PABSO_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("PABSO_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//////////////////////////////////////////
		//////////////////////////////////////////
		//////////////////////////////////////////
		//////////////////////////////////////////
		//Heavy Particle Impact - Scattering
		//////////////////////////////////////////
		//////////////////////////////////////////
		//////////////////////////////////////////
		//////////////////////////////////////////
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Total")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Single")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Double")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Partial")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Associative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Penning")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Photon")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("HSCAT_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Heavy Particle Impact - Ionization
		}else if(gotoindex.equalsIgnoreCase("HIONI_Total")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Single")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Double")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Partial")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Associative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Penning")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("HIONI_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("HIONI_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Photon")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("HIONI_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("HIONI_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Heavy Particle Impact - Excitation
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Total")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Single")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Double")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Partial")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Associative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Penning")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Photon")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("HEXCI_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Heavy Particle Impact - Recombination
		}else if(gotoindex.equalsIgnoreCase("HRECO_Total")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Single")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Double")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Partial")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Associative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Penning")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("HRECO_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("HRECO_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Photon")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("HRECO_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("HRECO_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Heavy Particle Impact - Charge Transfer
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Total")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Single")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Double")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Partial")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Associative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Penning")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Photon")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("HCHTR_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_05");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Heavy Particle Impact - Dissociation
		}else if(gotoindex.equalsIgnoreCase("HDISS_Total")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Single")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Double")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Partial")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Associative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Penning")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("HDISS_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("HDISS_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Photon")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("HDISS_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("HDISS_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Heavy Particle Impact - Attachment
		}else if(gotoindex.equalsIgnoreCase("HATTA_Total")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Single")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Double")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Partial")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Associative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Penning")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("HATTA_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("HATTA_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Photon")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("HATTA_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("HATTA_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Heavy Particle Impact - Reaction
		}else if(gotoindex.equalsIgnoreCase("HREAC_Total")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Single")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Double")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Partial")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Associative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Penning")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("HREAC_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("HREAC_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Photon")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("HREAC_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("HREAC_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Heavy Particle Impact - Detachment     
		}else if(gotoindex.equalsIgnoreCase("HDETA_Total")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Single")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Double")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Partial")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Associative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Penning")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("HDETA_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("HDETA_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Photon")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("HDETA_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("HDETA_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_09");
		info.setPL_BI_SUB_PROC("SP_39");
		
		//Heavy Particle Impact - Absorption 
		}else if(gotoindex.equalsIgnoreCase("HABSO_Total")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_01");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_02");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Momentum_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_03");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Single")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_04");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Double")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_05");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_06");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Partial")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_07");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Associative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_08");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Penning")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_09");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_10");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Electronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_11");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_12");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_13");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_14");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_15");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Three_Body")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_16");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_17");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Transfer_Ionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_18");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Thermal_Electron")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_19");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Electron_Loss")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_20");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Particle_Interchange")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_21");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_22");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_23");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_24");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Detachment")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_25");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Autoionization")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_26");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Quenching")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_27");
		}else if(gotoindex.equalsIgnoreCase("HABSO_X_ray_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_28");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Relacxation_Reaction")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_29");
		}else if(gotoindex.equalsIgnoreCase("HABSO_State_Selectivity")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_30");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Photon")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_31");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Ion_Pair_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_32");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Charge_Transfer")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_33");
		}else if(gotoindex.equalsIgnoreCase("HABSO_de_Excitation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_34");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Neutral_Product_Dissociation")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_35");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Attachement")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_36");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Inelastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_37");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Electron_Production")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_38");
		}else if(gotoindex.equalsIgnoreCase("HABSO_Vibrotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_10");
		info.setPL_BI_SUB_PROC("SP_39"); 
		}
		return info;
		/*if(gotoindex.equalsIgnoreCase("ES_Total")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_01");
	}else if(gotoindex.equalsIgnoreCase("EI_Total")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_01");
	}else if(gotoindex.equalsIgnoreCase("EE_Total")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_01");
	}else if(gotoindex.equalsIgnoreCase("ERECO_Total")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_01");
	}else if(gotoindex.equalsIgnoreCase("ES_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_02");
	}else if(gotoindex.equalsIgnoreCase("EI_Single")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_04");
	}else if(gotoindex.equalsIgnoreCase("EE_Electrical")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_11");
	}else if(gotoindex.equalsIgnoreCase("ERECO_Raidative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_14");
	}else if(gotoindex.equalsIgnoreCase("ES_Momentum")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_03");
	}else if(gotoindex.equalsIgnoreCase("EI_Double")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_05");
	}else if(gotoindex.equalsIgnoreCase("EE_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_12");
	}else if(gotoindex.equalsIgnoreCase("ERECO_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_15");
	}else if(gotoindex.equalsIgnoreCase("EI_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_06");
	}else if(gotoindex.equalsIgnoreCase("EE_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_13");
	}else if(gotoindex.equalsIgnoreCase("ERECO_Threebody")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_16");
	}else if(gotoindex.equalsIgnoreCase("EI_Partial")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_07");
	}else if(gotoindex.equalsIgnoreCase("EE_Associative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_08");
	}else if(gotoindex.equalsIgnoreCase("ERECO_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_17");
	}else if(gotoindex.equalsIgnoreCase("EI_Associative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_08");
	}else if(gotoindex.equalsIgnoreCase("EE_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_14");
	}else if(gotoindex.equalsIgnoreCase("EI_Penning")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_09");
	}else if(gotoindex.equalsIgnoreCase("EE_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_17");
	}else if(gotoindex.equalsIgnoreCase("ED_Total")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_01");
	}else if(gotoindex.equalsIgnoreCase("EA_Total")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_01");
	}else if(gotoindex.equalsIgnoreCase("EREAC_Electronloss")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_20");
	}else if(gotoindex.equalsIgnoreCase("ED_Partial")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_07");
	}else if(gotoindex.equalsIgnoreCase("EA_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_17");
	}else if(gotoindex.equalsIgnoreCase("EREAC_ParticleInterchange")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_21");
	}else if(gotoindex.equalsIgnoreCase("EA_Associative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_08");
	}else if(gotoindex.equalsIgnoreCase("EA_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_14");
	}else if(gotoindex.equalsIgnoreCase("EA_Thermalelectorn")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_19");
	}else if(gotoindex.equalsIgnoreCase("ED_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_22");
	}else if(gotoindex.equalsIgnoreCase("ED_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_23");
	}else if(gotoindex.equalsIgnoreCase("EA_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_01");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_24");
	}else if(gotoindex.equalsIgnoreCase("PS_Total")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_01");
	}else if(gotoindex.equalsIgnoreCase("PI_Total")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_01");
	}else if(gotoindex.equalsIgnoreCase("PE_Total")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_01");
	}else if(gotoindex.equalsIgnoreCase("PRECO_Total")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_01");
	}else if(gotoindex.equalsIgnoreCase("PS_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_02");
	}else if(gotoindex.equalsIgnoreCase("PI_Single")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_04");
	}else if(gotoindex.equalsIgnoreCase("PE_Electrical")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_11");
	}else if(gotoindex.equalsIgnoreCase("PRECO_Raidative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_14");
	}else if(gotoindex.equalsIgnoreCase("PS_Momentum")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_03");
	}else if(gotoindex.equalsIgnoreCase("PI_Double")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_05");
	}else if(gotoindex.equalsIgnoreCase("PE_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_12");
	}else if(gotoindex.equalsIgnoreCase("PRECO_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_15");
	}else if(gotoindex.equalsIgnoreCase("PI_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_06");
	}else if(gotoindex.equalsIgnoreCase("PE_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_13");
	}else if(gotoindex.equalsIgnoreCase("PRECO_Threebody")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_16");
	}else if(gotoindex.equalsIgnoreCase("PI_Partial")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_07");
	}else if(gotoindex.equalsIgnoreCase("PE_Associative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_08");
	}else if(gotoindex.equalsIgnoreCase("PRECO_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_17");
	}else if(gotoindex.equalsIgnoreCase("PI_Associative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_08");
	}else if(gotoindex.equalsIgnoreCase("PE_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_14");
	}else if(gotoindex.equalsIgnoreCase("PI_Penning")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_09");
	}else if(gotoindex.equalsIgnoreCase("PE_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_17");
	}else if(gotoindex.equalsIgnoreCase("PD_Total")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_01");
	}else if(gotoindex.equalsIgnoreCase("PA_Total")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_01");
	}else if(gotoindex.equalsIgnoreCase("PREAC_Electronloss")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_20");
	}else if(gotoindex.equalsIgnoreCase("PD_Partial")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_07");
	}else if(gotoindex.equalsIgnoreCase("PA_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_17");
	}else if(gotoindex.equalsIgnoreCase("PREAC_ParticleInterchange")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_21");
	}else if(gotoindex.equalsIgnoreCase("PA_Associative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_08");
	}else if(gotoindex.equalsIgnoreCase("PA_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_14");
	}else if(gotoindex.equalsIgnoreCase("PA_Thermalelectorn")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_19");
	}else if(gotoindex.equalsIgnoreCase("PD_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_22");
	}else if(gotoindex.equalsIgnoreCase("PD_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_23");
	}else if(gotoindex.equalsIgnoreCase("PA_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_02");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_24");
	}else if(gotoindex.equalsIgnoreCase("HS_Total")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_01");
	}else if(gotoindex.equalsIgnoreCase("HI_Total")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_01");
	}else if(gotoindex.equalsIgnoreCase("HE_Total")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_01");
	}else if(gotoindex.equalsIgnoreCase("HRECO_Total")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_01");
	}else if(gotoindex.equalsIgnoreCase("HS_Elastic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_02");
	}else if(gotoindex.equalsIgnoreCase("HI_Single")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_04");
	}else if(gotoindex.equalsIgnoreCase("HE_Electrical")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_11");
	}else if(gotoindex.equalsIgnoreCase("HRECO_Raidative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_14");
	}else if(gotoindex.equalsIgnoreCase("HS_Momentum")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_01");
		info.setPL_BI_SUB_PROC("SP_03");
	}else if(gotoindex.equalsIgnoreCase("HI_Double")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_05");
	}else if(gotoindex.equalsIgnoreCase("HE_Vibrational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_12");
	}else if(gotoindex.equalsIgnoreCase("HRECO_Dielectronic")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_15");
	}else if(gotoindex.equalsIgnoreCase("HI_Multiple")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_06");
	}else if(gotoindex.equalsIgnoreCase("HE_Rotational")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_13");
	}else if(gotoindex.equalsIgnoreCase("HRECO_Threebody")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_16");
	}else if(gotoindex.equalsIgnoreCase("HI_Partial")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_07");
	}else if(gotoindex.equalsIgnoreCase("HE_Associative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_08");
	}else if(gotoindex.equalsIgnoreCase("HRECO_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_04");
		info.setPL_BI_SUB_PROC("SP_17");
	}else if(gotoindex.equalsIgnoreCase("HI_Associative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_08");
	}else if(gotoindex.equalsIgnoreCase("HE_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_14");
	}else if(gotoindex.equalsIgnoreCase("HI_Penning")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_02");
		info.setPL_BI_SUB_PROC("SP_09");
	}else if(gotoindex.equalsIgnoreCase("HE_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_03");
		info.setPL_BI_SUB_PROC("SP_17");
	}else if(gotoindex.equalsIgnoreCase("HD_Total")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_01");
	}else if(gotoindex.equalsIgnoreCase("HA_Total")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_01");
	}else if(gotoindex.equalsIgnoreCase("HREAC_Electronloss")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_20");
	}else if(gotoindex.equalsIgnoreCase("HD_Partial")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_07");
	}else if(gotoindex.equalsIgnoreCase("HA_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_17");
	}else if(gotoindex.equalsIgnoreCase("HREAC_ParticleInterchange")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_08");
		info.setPL_BI_SUB_PROC("SP_21");
	}else if(gotoindex.equalsIgnoreCase("HA_Associative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_08");
	}else if(gotoindex.equalsIgnoreCase("HA_Radiative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_14");
	}else if(gotoindex.equalsIgnoreCase("HA_Thermalelectorn")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_19");
	}else if(gotoindex.equalsIgnoreCase("HD_Total_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_22");
	}else if(gotoindex.equalsIgnoreCase("HD_Neutral_Fragments")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_06");
		info.setPL_BI_SUB_PROC("SP_23");
	}else if(gotoindex.equalsIgnoreCase("HA_Total_Dissociative")){
		info.setPL_BI_IMP_CLASS("IC_03");
		info.setPL_BI_MAIN_PROC("MP_07");
		info.setPL_BI_SUB_PROC("SP_24");
	}		
	return info;*/

	}
}
		
