package nfri.dcpp.properties.business;

import java.util.Vector;
import java.io.* ;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import nfri.dcpp.com.util.ComUtil;
import nfri.dcpp.com.util.ComVar;
import nfri.dcpp.properties.db.DAO_ASSESS_INFO;
import nfri.dcpp.properties.db.DAO_BASIC_GRAPH_INFO;
import nfri.dcpp.properties.db.DAO_BASIC_PROPERTY_INFO;
import nfri.dcpp.properties.db.DAO_FITTING_INFO;
import nfri.dcpp.properties.db.DAO_INF_GRD_GRAPH_INFO;
import nfri.dcpp.properties.db.DAO_INF_GRD_PROPERTY_INFO;
import nfri.dcpp.properties.db.DAO_PROPERTY_SEARCH_INFO;
import nfri.dcpp.properties.db.DAO_REF_ARTICLE_INFO;
import nfri.dcpp.properties.model.Article_Info;
import nfri.dcpp.properties.model.Fitting_Temp_Info;
import nfri.dcpp.properties.model.Graph_Basic_Info;
import nfri.dcpp.properties.model.Graph_Data_Info;
import nfri.dcpp.properties.model.Inf_Grd_Graph_Basic_Info;
import nfri.dcpp.properties.model.Inf_Grd_Graph_Data_Info;
import nfri.dcpp.properties.model.Inf_Grd_Properties_Basic_Info;
import nfri.dcpp.properties.model.Inf_Grd_Properties_Equation_Info;
import nfri.dcpp.properties.model.Properties_Assess_Info;
import nfri.dcpp.properties.model.Properties_Assess_List_Info;
import nfri.dcpp.properties.model.Properties_Assess_Search_List;
import nfri.dcpp.properties.model.Properties_Basic_Info;
import nfri.dcpp.properties.model.Properties_Equation_Get_DbInfo;
import nfri.dcpp.properties.model.Properties_Final_Assess_Info;

/**
 *
 * @Project : dcpp_web
 * @Title : Ctr_Property_Assess_Info.java
 * @Description : 물성 정보를 평가하기 위한 메소드들의 비즈니스 클리스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 06. 16
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class Ctr_Property_Assess_Info {
	public Ctr_Property_Assess_Info(){
		
	}
	
	/**
	 * @MethodName : selectPropertyList
	 * @Desc : 평가 초기 화면에 보여줄 물성 평가대상 정보를 가져오는 메소드
	 * @return
	 */
	public Vector<?> selectAssessList(){
		Vector<?> vecList = new Vector<Object>();
		DAO_ASSESS_INFO info = new DAO_ASSESS_INFO();
		vecList = info.selectAssessList();
		return vecList;
	}
	
	/**
	 * @MethodName : selectAssessInfo
	 * @Desc : 해당 물성정보 대한 평가정보를 가져오는 메소드
	 * @param pr_no
	 * @return
	 */
	public Properties_Assess_Info selectAssessInfo(String pr_no){

		DAO_ASSESS_INFO info = new DAO_ASSESS_INFO();
		
		return info.selectAssessInfo(pr_no);
	}
	
	/**
	 * @MethodName  : selectAssessBasicInfo
	 * @Date   : 2010. 04. 16 
	 * @MethodDescription : 해당 물성정보에 대한 평가정보를 체크하여 기본정보만 가져오는 메소드
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public Properties_Assess_List_Info selectAssessBasicInfo(String pr_no){

		DAO_ASSESS_INFO info = new DAO_ASSESS_INFO();
		
		return info.selectAssessBasicInfo(pr_no);
	}
	
	/**
	 * @MethodName  : makeAssessExpression
	 * @Date   : 2011. 4. 19. 
	 * @MethodDescription : 표현식 만들어내는 메소드
	 * @param request
	 * @return
	 * @History  : - 
	 */
	public String makeAssessExpression(HttpServletRequest request){
		String return_value = "-";
		
		String expression = "";
		
		//Request Parameter : 입사입자 (Projectile)
		String req_pj_name = request.getParameter("projectile");
		//Request Parameter : 입사입자 ID 값 (Projectile)
		String req_pj_name_id = request.getParameter("projectile_id");
		
		//Request Parameter : 표적입자 (Target)
		String req_tg_name = request.getParameter("tg_name");
		//Request Parameter : 표적입자 ID 값(Target)
		String req_tg_name_id = request.getParameter("tg_name_id");
		
		//Request Parameter : 이온화 (Target)
		String req_tg_ion = ComUtil.isNullToNullString(request.getParameter("tg_ionic"));
		//Request Parameter : 전자배치 (Target)
		String req_tg_elec = ComUtil.isNullToNullString(request.getParameter("tg_elec"));
		//Request Parameter : 미세구조 (Target)
		String req_tg_fine = ComUtil.isNullToNullString(request.getParameter("tg_fine"));
		
		//Request Parameter : 생성입자 (Product)
		String req_pd_name = ComUtil.isNullToNullString(request.getParameter("pd_name"));
		//Request Parameter : 생성입자 ID(Product)
		String req_pd_name_id = ComUtil.isNullToNullString(request.getParameter("pd_name_id"));
		
		//Request Parameter : 이온화 (Product)
		String req_pd_ion = ComUtil.isNullToNullString(request.getParameter("pd_ionic"));
		//Request Parameter : 전자배치 (Product)
		String req_pd_elec = ComUtil.isNullToNullString(request.getParameter("pd_elec"));
		//Request Parameter : 미세구조 (Product)
		String req_pd_fine = ComUtil.isNullToNullString(request.getParameter("pd_fine"));
/*				
		System.out.println("-------pj------- ");
		System.out.println("req_pj_name : " +req_pj_name);
		System.out.println("req_pj_name_id : " +req_pj_name_id);
		System.out.println("-------tg------- ");
		System.out.println("req_tg_name : " +req_tg_name);
		System.out.println("req_tg_name_id : " +req_tg_name_id);
		System.out.println("req_tg_ion : " +req_tg_ion);
		System.out.println("req_tg_elec : " +req_tg_elec);
		System.out.println("req_tg_fine : " +req_tg_fine);
		System.out.println("-------pd------- ");
		System.out.println("req_pd_name : " +req_pd_name);
		System.out.println("req_pd_name_id : " +req_pd_name_id);
		System.out.println("req_pd_ion : " +req_pd_ion);
		System.out.println("req_pd_elec : " +req_pd_elec);
		System.out.println("req_pd_fine : " +req_pd_fine);
		*/
		String str_plus  = " + ";
		String str_arrow = " -&gt; ";
		String str_l_brace = "(";
		String str_r_brace = ")";
		String str_comma = ",";
		
		//입사 입자 + 표적입자
		expression = req_pj_name + str_plus + req_tg_name;
		//표적입자 이온화 체크
		if(!req_tg_ion.equalsIgnoreCase("NULL")){
			expression = expression + ComUtil.getSupString(req_tg_ion);
		}
		//표적입자 전자배치 체크
		if(!req_tg_elec.equalsIgnoreCase("NULL")){
			//표적입자 미세구조 체크
			if(!req_tg_fine.equalsIgnoreCase("NULL")){
				expression = expression + str_l_brace + req_tg_ion + str_comma + req_tg_fine + str_r_brace;
			}else{
				expression = expression + str_l_brace + req_tg_ion + str_r_brace;
			}
		}		
		
		//생성입자 체크
		if(!req_pd_name.equalsIgnoreCase("NULL")){
			
			expression = expression + str_arrow;
			
			//입사 입자 + 표적입자
			expression = expression + req_pd_name;
			
			//생성입자 이온화 체크
			if(!req_pd_ion.equalsIgnoreCase("NULL")){
				expression = expression + ComUtil.getSupString(req_pd_ion);
			}
			//생성입자 전자배치 체크
			if(!req_pd_elec.equalsIgnoreCase("NULL")){
				//표적입자 미세구조 체크
				if(!req_pd_fine.equalsIgnoreCase("NULL")){
					expression = expression + str_l_brace + req_pd_ion + str_comma + req_pd_fine + str_r_brace;
				}else{
					expression = expression + str_l_brace + req_pd_ion + str_r_brace;
				}
			}
		
		}/*else{
			expression = expression + str_arrow;
		}*/
		
		return expression;		
	}
	
	/**
	 * @MethodName  : searchAssessPropertyListInfo
	 * @Date   : 2010. 05. 04 
	 * @MethodDescription : 등급 유력 물성 정보에 대한 검색 조건에 따라 해당 정보들을 가져오는 메소드
	 * @param request
	 * @return
	 * @History  : - 
	 */
	public Vector<?> searchAssessPropertyListInfo(HttpServletRequest request){
		Vector<?> search_list = new Vector<Object>();
		
		/*
		 * SQL Query 변수 선언
		 */		
		//String : Brace
		String str_left_brace = "(";
		String str_right_brace = ") EQUA";
		
		// * 는 반드시 입력되는 값
		//SQL 조건문 : 충돌방식 (Impact) *
		String sql_ic_option = " AND BASIC.PL_BI_IMP_CLASS = ";
		//SQL 조건문 : 주프로세스 (Main Process) *
		String sql_mp_option = " AND BASIC.PL_BI_MAIN_PROC = ";
		//SQL 조건문 : 부프로세스 (Sub Process) *
		String sql_sp_option = " AND BASIC.PL_BI_SUB_PROC = ";
		
		//SQL 연산자
		String str_intersect = " INTERSECT ";
		
		//SQL 조건문 : 입자 정보, 표적입자, 생성입가 들어올때 검색조건으로 쓰이는 기본 SQL문
		String sql_equa_option = " SELECT PL_BI_DATA_NUM FROM PL_BASIC_EQUATION_INFO" +
				" WHERE PL_CPBI_ELE_NUM = ";

		//SQL 조건문 : 입사입자 순서(Projectile) : 입자 순서가 1이어야 됨 *
		String sql_project_seq_option = " AND PL_BEI_SEQ = '1'";				
		//SQL 조건문 : 표적입자 순서(Target) : 입자 순서가 2이어야 됨 *
		String sql_target_seq_option = " AND PL_BEI_SEQ = '2'";		
		//SQL 조건문 : 생성입자 순서(Product) 입자순서가 나머지 (3, 4, 5, 6 )이어야 됨 *
		String sql_product_seq_option = " AND (PL_BEI_SEQ = '3' OR PL_BEI_SEQ = '4' OR PL_BEI_SEQ = '5' OR PL_BEI_SEQ = '6')";
		
		
		//SQL 조건문 : 이온화 (Ionic State)
		String sql_ion_option = " AND PL_BEI_CHG_STATE = ";
		//SQL 조건문 : 전자배치 (Electron Configuration)
		String sql_elec_option = " AND PL_BEI_ELC_STATE = ";
		//SQL 조건문 : 미세구조 (Fine Structure)
		String sql_fine_option = " AND PL_BEI_OTH_STRUC = ";
		
		//SQL 조건문 : X 단위 (X Unit) *
		String sql_xu_option = " AND GRAPH.PL_BGI_X_AX_UNIT = ";
		//SQL 조건문 : Y 단위 (Y Unit) *
		String sql_yu_option = " AND GRAPH.PL_BGI_Y_AX_UNIT = ";		
		
		//SQL WHERE 기본 입력문
		String sql_where_option = " WHERE BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM" +
				" AND BASIC.PL_BI_DATA_NUM = GRAPH.PL_BI_DATA_NUM" +
				" AND BASIC.PL_RAI_ARTCL_NUM = ARTCL.PL_RAI_ARTCL_NUM" +
				" AND BASIC.PL_BI_INSERT_FLAG = 'C'";
		
		//SQL ORDER BY 입력문
		String sql_order_by = " ORDER BY BASIC.PL_BI_MGMT_DATA_NUM ASC";
		//String sql_order_by = " ORDER BY BASIC.PL_BI_DATA_NUM ASC";
				
		//SQL 세번째 조건 : 표적 입자 / 입사 입자
		//String sql_expresstion = " AND PL_BI_EXPRESSION LIKE ";
		//SQL GROUP BY 옵션
		//String sql_group_by = " GROUP BY GROUPING SETS(( PL_BI_IMP_CLASS, PL_BI_MAIN_PROC), (PL_BI_MAIN_PROC, PL_BI_SUB_PROC))";
		//String sql_group_by = " GROUP BY ROLLUP (PL_BI_IMP_CLASS, PL_BI_MAIN_PROC, PL_BI_SUB_PROC)";
		
		//Request Parameter : 충돌방식
		String req_ic_option = request.getParameter("ic_option");
		//Request Parameter : 주프로세스
		String req_mp_option = request.getParameter("mp_option");
		//Request Parameter : 부프로세스
		String req_sp_option = request.getParameter("sp_option");
		//Request Parameter : X단위
		String req_xu_option = request.getParameter("xax_unit");
		//Request Parameter : Y단위
		String req_yu_option = request.getParameter("xay_unit");
		
		//Request Parameter : 입사입자 (Projectile)
		String req_pj_name = request.getParameter("projectile");
		//Request Parameter : 입사입자 ID 값 (Projectile)
		String req_pj_name_id = request.getParameter("projectile_id");
		
		//Request Parameter : 표적입자 (Target)
		String req_tg_name = request.getParameter("tg_name");
		//Request Parameter : 표적입자 ID 값(Target)
		String req_tg_name_id = request.getParameter("tg_name_id");
		
		//Request Parameter : 이온화 (Target)
		String req_tg_ion = ComUtil.isNullToNullString(request.getParameter("tg_ionic"));
		//Request Parameter : 전자배치 (Target)
		String req_tg_elec = ComUtil.isNullToNullString(request.getParameter("tg_elec"));
		//Request Parameter : 미세구조 (Target)
		String req_tg_fine = ComUtil.isNullToNullString(request.getParameter("tg_fine"));
		
		//Request Parameter : 생성입자 (Product)
		String req_pd_name = ComUtil.isNullToNullString(request.getParameter("pd_name"));
		//Request Parameter : 생성입자 ID(Product)
		String req_pd_name_id = ComUtil.isNullToNullString(request.getParameter("pd_name_id"));
		
		//Request Parameter : 이온화 (Product)
		String req_pd_ion = ComUtil.isNullToNullString(request.getParameter("pd_ionic"));
		//Request Parameter : 전자배치 (Product)
		String req_pd_elec = ComUtil.isNullToNullString(request.getParameter("pd_elec"));
		//Request Parameter : 미세구조 (Product)
		String req_pd_fine = ComUtil.isNullToNullString(request.getParameter("pd_fine"));
	
		
		//전체 SQL 저장 변수
		//입사 입자와 표적 입자 값이 들어오는 경우 
		String optionquery = str_left_brace + sql_equa_option + ComUtil.makeEqualOption(req_pj_name_id) + sql_project_seq_option
		            + str_intersect + sql_equa_option + ComUtil.makeEqualOption(req_tg_name_id) + sql_target_seq_option;

		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_tg_ion)){//표적입자 이온화
			optionquery = optionquery + sql_ion_option + ComUtil.makeEqualOption(req_tg_ion);
		}
		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_tg_elec)){//표적입자 전자배치
			optionquery = optionquery + sql_elec_option + ComUtil.makeEqualOption(req_tg_elec);
		}
		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_tg_fine)){//표적입자 미세구조
			optionquery = optionquery + sql_fine_option + ComUtil.makeEqualOption(req_tg_fine);
		}
		//System.out.println("req_tg_name : " + req_tg_name);
		//System.out.println("req_pd_name : " + req_pd_name);
		
		//생성 입자 값이 들어오는 경우
		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_name)){
			optionquery = optionquery + str_intersect + sql_equa_option + ComUtil.makeEqualOption(req_pd_name_id) + sql_product_seq_option;
			if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_ion)){//표적입자 이온화
				optionquery = optionquery + sql_ion_option + ComUtil.makeEqualOption(req_pd_ion);
			}
			if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_elec)){//표적입자 전자배치
				optionquery = optionquery + sql_elec_option + ComUtil.makeEqualOption(req_pd_elec);
			}
			if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_fine)){//표적입자 미세구조
				optionquery = optionquery + sql_fine_option + ComUtil.makeEqualOption(req_pd_fine);
			}
		}
		//SQL문 FROM 절 닫기
		optionquery = optionquery + str_right_brace;
		
		//SQL문 WHERE 절 
		optionquery = optionquery + sql_where_option;
		
		//SQL문 WHERE 절 추가 Option : 충돌방식
		optionquery = optionquery + sql_ic_option + ComUtil.makeEqualOption(req_ic_option);
		
		//SQL문 WHERE 절 추가 Option : 주프로세스
		optionquery = optionquery + sql_mp_option + ComUtil.makeEqualOption(req_mp_option);
		
		//SQL문 WHERE 절 추가 Option : 부프로세스
		optionquery = optionquery + sql_sp_option + ComUtil.makeEqualOption(req_sp_option);
		
		//SQL문 WHERE 절 추가 Option : X단위
		optionquery = optionquery + sql_xu_option + ComUtil.makeEqualOption(req_xu_option);
		
		//SQL문 WHERE 절 추가 Option : Y단위
		optionquery = optionquery + sql_yu_option + ComUtil.makeEqualOption(req_yu_option);
				
		/*if(!ComVar.STRING_DASH.equalsIgnoreCase(req_srd_value)){//참조표준인증데이터
			optionquery = optionquery + ComUtil.makeEqualOption(req_srd_value);
		}*/
		
		String sql_add_option = " AND BASIC.PL_BI_DATA_NUM IN ";
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
		
		optionquery = optionquery + sql_order_by;
		//System.out.println("optionquery : " +optionquery);
		//물성정보 리스트 가져오기
		DAO_PROPERTY_SEARCH_INFO search = new DAO_PROPERTY_SEARCH_INFO();
		search_list = search.selectSearchAssessList(optionquery);
		
		//선택되어진 인덱스
/*
		
		String sql_order_by = " ORDER BY PL_BI_DATA_NUM";
		System.out.println("-------option------- ");
		System.out.println("req_ic_option : " +req_ic_option);
		System.out.println("req_mp_option : " +req_mp_option);
		System.out.println("req_sp_option : " +req_sp_option);
		System.out.println("req_xu_option : " +req_xu_option);
		System.out.println("req_yu_option : " +req_yu_option);
		System.out.println("-------pj------- ");
		System.out.println("req_pj_name : " +req_pj_name);
		System.out.println("req_pj_name_id : " +req_pj_name_id);
		System.out.println("-------tg------- ");
		System.out.println("req_tg_name : " +req_tg_name);
		System.out.println("req_tg_name_id : " +req_tg_name_id);
		System.out.println("req_tg_ion : " +req_tg_ion);
		System.out.println("req_tg_elec : " +req_tg_elec);
		System.out.println("req_tg_fine : " +req_tg_fine);
		System.out.println("-------pd------- ");
		System.out.println("req_pd_name : " +req_pd_name);
		System.out.println("req_pd_name_id : " +req_pd_name_id);
		System.out.println("req_pd_ion : " +req_pd_ion);
		System.out.println("req_pd_elec : " +req_pd_elec);
		System.out.println("req_pd_fine : " +req_pd_fine);
		System.out.println("-------sql option------- ");
		System.out.println("optionquery : " +optionquery);*/

		return search_list;
	}
	
	/**
	 * @MethodName  : getConditionInfo
	 * @Date   : 2010. 06. 07 
	 * @MethodDescription : 오즈 그래프를 그리기 위한 SQL 조건을 만드는 메소드
	 * @param request
	 * @return
	 * @History  : - 
	 */
	public String getConditionInfo(HttpServletRequest request){
		//String return_value = "";
		
		/*
		 * SQL Query 변수 선언
		 */		
		//String : Brace
		String str_left_brace = ", PL_BASIC_GRAPH_INFO GRAPH, (";
		String str_right_brace = ") EQUA";
		
		// * 는 반드시 입력되는 값
		//SQL 조건문 : 충돌방식 (Impact) *
		String sql_ic_option = " AND B.PL_BI_IMP_CLASS = ";
		//SQL 조건문 : 주프로세스 (Main Process) *
		String sql_mp_option = " AND B.PL_BI_MAIN_PROC = ";
		//SQL 조건문 : 부프로세스 (Sub Process) *
		String sql_sp_option = " AND B.PL_BI_SUB_PROC = ";
		
		//SQL 연산자
		String str_intersect = " INTERSECT ";
		
		//SQL 조건문 : 입자 정보, 표적입자, 생성입가 들어올때 검색조건으로 쓰이는 기본 SQL문
		String sql_equa_option = " SELECT PL_BI_DATA_NUM FROM PL_BASIC_EQUATION_INFO" +
				" WHERE PL_CPBI_ELE_NUM = ";

		//SQL 조건문 : 입사입자 순서(Projectile) : 입자 순서가 1이어야 됨 *
		String sql_project_seq_option = " AND PL_BEI_SEQ = '1'";				
		//SQL 조건문 : 표적입자 순서(Target) : 입자 순서가 2이어야 됨 *
		String sql_target_seq_option = " AND PL_BEI_SEQ = '2'";		
		//SQL 조건문 : 생성입자 순서(Product) 입자순서가 나머지 (3, 4, 5, 6 )이어야 됨 *
		String sql_product_seq_option = " AND (PL_BEI_SEQ = '3' OR PL_BEI_SEQ = '4' OR PL_BEI_SEQ = '5' OR PL_BEI_SEQ = '6')";
		
		
		//SQL 조건문 : 이온화 (Ionic State)
		String sql_ion_option = " AND PL_BEI_CHG_STATE = ";
		//SQL 조건문 : 전자배치 (Electron Configuration)
		String sql_elec_option = " AND PL_BEI_ELC_STATE = ";
		//SQL 조건문 : 미세구조 (Fine Structure)
		String sql_fine_option = " AND PL_BEI_OTH_STRUC = ";
		
		//SQL 조건문 : X 단위 (X Unit) *
		String sql_xu_option = " AND GRAPH.PL_BGI_X_AX_UNIT = ";
		//SQL 조건문 : Y 단위 (Y Unit) *
		String sql_yu_option = " AND GRAPH.PL_BGI_Y_AX_UNIT = ";
		
		//SQL 조건문 : X MAX Value *
		String sql_xmax_option = " AND TO_NUMBER (A.PL_BGD_X_AX_VAL) < ";
		//SQL 조건문 : X MIN Value *
		String sql_xmin_option = " AND TO_NUMBER (A.PL_BGD_X_AX_VAL) > ";
		//SQL 조건문 : Y MAX Value *
		String sql_ymax_option = " AND TO_NUMBER (A.PL_BGD_Y_AX_VAL) < ";
		//SQL 조건문 : Y MIN Value *
		String sql_ymin_option = " AND TO_NUMBER (A.PL_BGD_Y_AX_VAL) > ";
				
		//SQL WHERE 기본 입력문
		String sql_where_option = " WHERE B.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM" +
				" AND B.PL_BI_DATA_NUM = GRAPH.PL_BI_DATA_NUM" +
				" AND B.PL_BI_INSERT_FLAG = 'C'";
		
		//SQL 세번째 조건 : 표적 입자 / 입사 입자
		//String sql_expresstion = " AND PL_BI_EXPRESSION LIKE ";
		//SQL GROUP BY 옵션
		//String sql_group_by = " GROUP BY GROUPING SETS(( PL_BI_IMP_CLASS, PL_BI_MAIN_PROC), (PL_BI_MAIN_PROC, PL_BI_SUB_PROC))";
		//String sql_group_by = " GROUP BY ROLLUP (PL_BI_IMP_CLASS, PL_BI_MAIN_PROC, PL_BI_SUB_PROC)";
		
		//Request Parameter : 충돌방식
		String req_ic_option = request.getParameter("ic_option");
		//Request Parameter : 주프로세스
		String req_mp_option = request.getParameter("mp_option");
		//Request Parameter : 부프로세스
		String req_sp_option = request.getParameter("sp_option");
		//Request Parameter : X단위
		String req_xu_option = request.getParameter("xax_unit");
		//Request Parameter : Y단위
		String req_yu_option = request.getParameter("xay_unit");
		
		//Request Parameter : 입사입자 (Projectile)
		String req_pj_name = request.getParameter("projectile");
		//Request Parameter : 입사입자 ID 값 (Projectile)
		String req_pj_name_id = request.getParameter("projectile_id");
		
		//Request Parameter : 표적입자 (Target)
		String req_tg_name = request.getParameter("tg_name");
		//Request Parameter : 표적입자 ID 값(Target)
		String req_tg_name_id = request.getParameter("tg_name_id");
		
		//Request Parameter : 이온화 (Target)
		String req_tg_ion = ComUtil.isNullToNullString(request.getParameter("tg_ionic"));
		//Request Parameter : 전자배치 (Target)
		String req_tg_elec = ComUtil.isNullToNullString(request.getParameter("tg_elec"));
		//Request Parameter : 미세구조 (Target)
		String req_tg_fine = ComUtil.isNullToNullString(request.getParameter("tg_fine"));
		
		//Request Parameter : 생성입자 (Product)
		String req_pd_name = ComUtil.isNullToNullString(request.getParameter("pd_name"));
		//Request Parameter : 생성입자 ID(Product)
		String req_pd_name_id = ComUtil.isNullToNullString(request.getParameter("pd_name_id"));
		
		//Request Parameter : 이온화 (Product)
		String req_pd_ion = ComUtil.isNullToNullString(request.getParameter("pd_ionic"));
		//Request Parameter : 전자배치 (Product)
		String req_pd_elec = ComUtil.isNullToNullString(request.getParameter("pd_elec"));
		//Request Parameter : 미세구조 (Product)
		String req_pd_fine = ComUtil.isNullToNullString(request.getParameter("pd_fine"));
	
		
		//전체 SQL 저장 변수
		//입사 입자와 표적 입자 값이 들어오는 경우 
		String optionquery = str_left_brace + sql_equa_option + ComUtil.makeEqualOption(req_pj_name_id) + sql_project_seq_option
		            + str_intersect + sql_equa_option + ComUtil.makeEqualOption(req_tg_name_id) + sql_target_seq_option;

		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_tg_ion)){//표적입자 이온화
			optionquery = optionquery + sql_ion_option + ComUtil.makeEqualOption(req_tg_ion);
		}
		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_tg_elec)){//표적입자 전자배치
			optionquery = optionquery + sql_elec_option + ComUtil.makeEqualOption(req_tg_elec);
		}
		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_tg_fine)){//표적입자 미세구조
			optionquery = optionquery + sql_fine_option + ComUtil.makeEqualOption(req_tg_fine);
		}
		
		//생성 입자 값이 들어오는 경우
		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_name)){
			optionquery = optionquery + str_intersect + sql_equa_option + ComUtil.makeEqualOption(req_pd_name_id) + sql_product_seq_option;
			if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_ion)){//표적입자 이온화
				optionquery = optionquery + sql_ion_option + ComUtil.makeEqualOption(req_pd_ion);
			}
			if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_elec)){//표적입자 전자배치
				optionquery = optionquery + sql_elec_option + ComUtil.makeEqualOption(req_pd_elec);
			}
			if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_fine)){//표적입자 미세구조
				optionquery = optionquery + sql_fine_option + ComUtil.makeEqualOption(req_pd_fine);
			}
		}
		//SQL문 FROM 절 닫기
		optionquery = optionquery + str_right_brace;
		
		//SQL문 WHERE 절 
		optionquery = optionquery + sql_where_option;
		
		//SQL문 WHERE 절 추가 Option : 충돌방식
		optionquery = optionquery + sql_ic_option + ComUtil.makeEqualOption(req_ic_option);
		
		//SQL문 WHERE 절 추가 Option : 주프로세스
		optionquery = optionquery + sql_mp_option + ComUtil.makeEqualOption(req_mp_option);
		
		//SQL문 WHERE 절 추가 Option : 부프로세스
		optionquery = optionquery + sql_sp_option + ComUtil.makeEqualOption(req_sp_option);
		
		//SQL문 WHERE 절 추가 Option : X단위
		optionquery = optionquery + sql_xu_option + ComUtil.makeEqualOption(req_xu_option);
		
		//SQL문 WHERE 절 추가 Option : Y단위
		optionquery = optionquery + sql_yu_option + ComUtil.makeEqualOption(req_yu_option);
				
		//System.out.println("graph_xrange_value : " + request.getParameter("graph_xrange_value"));
		//if(ComUtil.isNullArray(request.getParameterValues("graph_xrange_value")) == ComVar.BOOL_FALSE){
		//	System.out.println("graph_xrange_value : " + request.getParameter("graph_xrange_value"));
		//}
		//if(ComUtil.isNullArray(request.getParameterValues("graph_yrange_value")) == ComVar.BOOL_FALSE){
		//	System.out.println("graph_yrange_value : " + request.getParameter("graph_yrange_value"));
		//}
		//X 체크박스 확인
		//SQL문 WHERE 절 추가 Option : X MAX
		String req_x_max_v = ComUtil.isNullToDashString(request.getParameter("graph_xrange_max_value"));
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_x_max_v)){
			optionquery = optionquery + sql_xmax_option + req_x_max_v;
		}
		
		//SQL문 WHERE 절 추가 Option : X MIN
		String req_x_min_v = ComUtil.isNullToDashString(request.getParameter("graph_xrange_min_value"));
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_x_min_v)){
			optionquery = optionquery + sql_xmin_option + req_x_min_v;
		}

		//Y 체크박스 여부 확인
		//SQL문 WHERE 절 추가 Option : Y MAX
		String req_y_max_v = ComUtil.isNullToDashString(request.getParameter("graph_yrange_max_value"));
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_y_max_v)){
			optionquery = optionquery + sql_ymax_option + req_y_max_v;
		}
		
		//SQL문 WHERE 절 추가 Option : Y MIN
		String req_y_min_v = ComUtil.isNullToDashString(request.getParameter("graph_yrange_min_value"));
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_y_min_v)){
			optionquery = optionquery + sql_ymin_option + req_y_min_v;
		}
		
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
		return optionquery;
	}
	
	/**
	 * @MethodName  : getFinalCondition
	 * @Date   : 2011. 7. 7. 
	 * @MethodDescription : 최종 등급유력 평가 페이지에서 OZ 그래프 그리는 조건 가져오는 메소드
	 * @param pr_no_list
	 * @return
	 * @History  : - 
	 */
	public String getFinalCondition(String pr_no_list){
		String in_option = "";		
		/*
		 * SQL Query 변수 선언
		 */		
		String str_start_option = "('";
		String str_middle_option = "','";
		String str_end_option = "')";

		//SQL 연산자
		
		//SQL 조건문 : 검색조건으로 쓰이는 기본 SQL문
		String sql_basic_option = " UNION" +
				" SELECT PL_BI_DATA_NUM, TO_NUMBER(PL_BGD_X_AX_VAL)," +
				" TO_NUMBER(PL_BGD_Y_AX_VAL), TO_NUMBER(PL_BGD_X_ERR), TO_NUMBER(PL_BGD_Y_ERR_MAX)," +
				" TO_NUMBER(PL_BGD_Y_ERR_MIN) FROM PL_BASIC_GRAPH_DATA " +
				" WHERE PL_BI_DATA_NUM IN ";
		
		in_option = str_start_option;
		
		//System.out.println("pr_no_list : " + pr_no_list);
		
		String[] temp = pr_no_list.split(", ");
		int temp_length = temp.length;
		
		//System.out.println("temp_length : " + temp_length);
		
		for(int i = 0; i < temp_length; i++){
			//System.out.println("temp["+i+"]"+" : "+temp[i]);
			if(i == temp_length-1){
				in_option = in_option + temp[i];
			}else{
				in_option = in_option + temp[i] + str_middle_option;
			}
			//System.out.println("in_option : " + in_option);
		}
		in_option = in_option + str_end_option;
		
		//System.out.println("in_option : " + in_option);
		
		//sql_basic_option = sql_basic_option + in_option;
		
		//System.out.println("sql_basic_option : " + sql_basic_option);
		
		return in_option;
	}
	
	/**
	 * @MethodName  : getFinalConditionInfo
	 * @Date   : 2011. 7. 6. 
	 * @MethodDescription : -
	 * @param request
	 * @return
	 * @History  : - 
	 */
	public String getFinalConditionInfo(HttpServletRequest request){
		//String return_value = "";
		
		/*
		 * SQL Query 변수 선언
		 */		
		//String : Brace
		String str_left_brace = ", PL_BASIC_GRAPH_INFO GRAPH, (";
		String str_right_brace = ") EQUA";
		
		// * 는 반드시 입력되는 값
		//SQL 조건문 : 충돌방식 (Impact) *
		String sql_ic_option = " AND B.PL_BI_IMP_CLASS = ";
		//SQL 조건문 : 주프로세스 (Main Process) *
		String sql_mp_option = " AND B.PL_BI_MAIN_PROC = ";
		//SQL 조건문 : 부프로세스 (Sub Process) *
		String sql_sp_option = " AND B.PL_BI_SUB_PROC = ";
		
		//SQL 연산자
		String str_intersect = " INTERSECT ";
		
		//SQL 조건문 : 입자 정보, 표적입자, 생성입가 들어올때 검색조건으로 쓰이는 기본 SQL문
		String sql_equa_option = " SELECT PL_BI_DATA_NUM FROM PL_BASIC_EQUATION_INFO" +
				" WHERE PL_CPBI_ELE_NUM = ";

		//SQL 조건문 : 입사입자 순서(Projectile) : 입자 순서가 1이어야 됨 *
		String sql_project_seq_option = " AND PL_BEI_SEQ = '1'";				
		//SQL 조건문 : 표적입자 순서(Target) : 입자 순서가 2이어야 됨 *
		String sql_target_seq_option = " AND PL_BEI_SEQ = '2'";		
		//SQL 조건문 : 생성입자 순서(Product) 입자순서가 나머지 (3, 4, 5, 6 )이어야 됨 *
		String sql_product_seq_option = " AND (PL_BEI_SEQ = '3' OR PL_BEI_SEQ = '4' OR PL_BEI_SEQ = '5' OR PL_BEI_SEQ = '6')";
		
		
		//SQL 조건문 : 이온화 (Ionic State)
		String sql_ion_option = " AND PL_BEI_CHG_STATE = ";
		//SQL 조건문 : 전자배치 (Electron Configuration)
		String sql_elec_option = " AND PL_BEI_ELC_STATE = ";
		//SQL 조건문 : 미세구조 (Fine Structure)
		String sql_fine_option = " AND PL_BEI_OTH_STRUC = ";
		
		//SQL 조건문 : X 단위 (X Unit) *
		String sql_xu_option = " AND GRAPH.PL_BGI_X_AX_UNIT = ";
		//SQL 조건문 : Y 단위 (Y Unit) *
		String sql_yu_option = " AND GRAPH.PL_BGI_Y_AX_UNIT = ";
		
		//SQL 조건문 : X MAX Value *
		String sql_xmax_option = " AND TO_NUMBER (A.PL_BGD_X_AX_VAL) < ";
		//SQL 조건문 : X MIN Value *
		String sql_xmin_option = " AND TO_NUMBER (A.PL_BGD_X_AX_VAL) > ";
		//SQL 조건문 : Y MAX Value *
		String sql_ymax_option = " AND TO_NUMBER (A.PL_BGD_Y_AX_VAL) < ";
		//SQL 조건문 : Y MIN Value *
		String sql_ymin_option = " AND TO_NUMBER (A.PL_BGD_Y_AX_VAL) > ";
				
		//SQL WHERE 기본 입력문
		String sql_where_option = " WHERE B.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM" +
				" AND B.PL_BI_DATA_NUM = GRAPH.PL_BI_DATA_NUM" +
				" AND B.PL_BI_INSERT_FLAG = 'C'";
		
		//SQL 세번째 조건 : 표적 입자 / 입사 입자
		//String sql_expresstion = " AND PL_BI_EXPRESSION LIKE ";
		//SQL GROUP BY 옵션
		//String sql_group_by = " GROUP BY GROUPING SETS(( PL_BI_IMP_CLASS, PL_BI_MAIN_PROC), (PL_BI_MAIN_PROC, PL_BI_SUB_PROC))";
		//String sql_group_by = " GROUP BY ROLLUP (PL_BI_IMP_CLASS, PL_BI_MAIN_PROC, PL_BI_SUB_PROC)";
		
		//Request Parameter : 충돌방식
		String req_ic_option = request.getParameter("ic_option");
		//Request Parameter : 주프로세스
		String req_mp_option = request.getParameter("mp_option");
		//Request Parameter : 부프로세스
		String req_sp_option = request.getParameter("sp_option");
		//Request Parameter : X단위
		String req_xu_option = request.getParameter("xax_unit");
		//Request Parameter : Y단위
		String req_yu_option = request.getParameter("xay_unit");
		
		//Request Parameter : 입사입자 (Projectile)
		String req_pj_name = request.getParameter("projectile");
		//Request Parameter : 입사입자 ID 값 (Projectile)
		String req_pj_name_id = request.getParameter("projectile_id");
		
		//Request Parameter : 표적입자 (Target)
		String req_tg_name = request.getParameter("tg_name");
		//Request Parameter : 표적입자 ID 값(Target)
		String req_tg_name_id = request.getParameter("tg_name_id");
		
		//Request Parameter : 이온화 (Target)
		String req_tg_ion = ComUtil.isNullToNullString(request.getParameter("tg_ionic"));
		//Request Parameter : 전자배치 (Target)
		String req_tg_elec = ComUtil.isNullToNullString(request.getParameter("tg_elec"));
		//Request Parameter : 미세구조 (Target)
		String req_tg_fine = ComUtil.isNullToNullString(request.getParameter("tg_fine"));
		
		//Request Parameter : 생성입자 (Product)
		String req_pd_name = ComUtil.isNullToNullString(request.getParameter("pd_name"));
		//Request Parameter : 생성입자 ID(Product)
		String req_pd_name_id = ComUtil.isNullToNullString(request.getParameter("pd_name_id"));
		
		//Request Parameter : 이온화 (Product)
		String req_pd_ion = ComUtil.isNullToNullString(request.getParameter("pd_ionic"));
		//Request Parameter : 전자배치 (Product)
		String req_pd_elec = ComUtil.isNullToNullString(request.getParameter("pd_elec"));
		//Request Parameter : 미세구조 (Product)
		String req_pd_fine = ComUtil.isNullToNullString(request.getParameter("pd_fine"));
	
		
		//전체 SQL 저장 변수
		//입사 입자와 표적 입자 값이 들어오는 경우 
		String optionquery = str_left_brace + sql_equa_option + ComUtil.makeEqualOption(req_pj_name_id) + sql_project_seq_option
		            + str_intersect + sql_equa_option + ComUtil.makeEqualOption(req_tg_name_id) + sql_target_seq_option;

		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_tg_ion)){//표적입자 이온화
			optionquery = optionquery + sql_ion_option + ComUtil.makeEqualOption(req_tg_ion);
		}
		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_tg_elec)){//표적입자 전자배치
			optionquery = optionquery + sql_elec_option + ComUtil.makeEqualOption(req_tg_elec);
		}
		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_tg_fine)){//표적입자 미세구조
			optionquery = optionquery + sql_fine_option + ComUtil.makeEqualOption(req_tg_fine);
		}
		
		//생성 입자 값이 들어오는 경우
		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_name)){
			optionquery = optionquery + str_intersect + sql_equa_option + ComUtil.makeEqualOption(req_pd_name_id) + sql_product_seq_option;
			if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_ion)){//표적입자 이온화
				optionquery = optionquery + sql_ion_option + ComUtil.makeEqualOption(req_pd_ion);
			}
			if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_elec)){//표적입자 전자배치
				optionquery = optionquery + sql_elec_option + ComUtil.makeEqualOption(req_pd_elec);
			}
			if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_fine)){//표적입자 미세구조
				optionquery = optionquery + sql_fine_option + ComUtil.makeEqualOption(req_pd_fine);
			}
		}
		//SQL문 FROM 절 닫기
		optionquery = optionquery + str_right_brace;
		
		//SQL문 WHERE 절 
		optionquery = optionquery + sql_where_option;
		
		//SQL문 WHERE 절 추가 Option : 충돌방식
		optionquery = optionquery + sql_ic_option + ComUtil.makeEqualOption(req_ic_option);
		
		//SQL문 WHERE 절 추가 Option : 주프로세스
		optionquery = optionquery + sql_mp_option + ComUtil.makeEqualOption(req_mp_option);
		
		//SQL문 WHERE 절 추가 Option : 부프로세스
		optionquery = optionquery + sql_sp_option + ComUtil.makeEqualOption(req_sp_option);
		
		//SQL문 WHERE 절 추가 Option : X단위
		optionquery = optionquery + sql_xu_option + ComUtil.makeEqualOption(req_xu_option);
		
		//SQL문 WHERE 절 추가 Option : Y단위
		optionquery = optionquery + sql_yu_option + ComUtil.makeEqualOption(req_yu_option);
				
		//System.out.println("graph_xrange_value : " + request.getParameter("graph_xrange_value"));
		//if(ComUtil.isNullArray(request.getParameterValues("graph_xrange_value")) == ComVar.BOOL_FALSE){
		//	System.out.println("graph_xrange_value : " + request.getParameter("graph_xrange_value"));
		//}
		//if(ComUtil.isNullArray(request.getParameterValues("graph_yrange_value")) == ComVar.BOOL_FALSE){
		//	System.out.println("graph_yrange_value : " + request.getParameter("graph_yrange_value"));
		//}
		//X 체크박스 확인
		//SQL문 WHERE 절 추가 Option : X MAX
		String req_x_max_v = ComUtil.isNullToDashString(request.getParameter("graph_xrange_max_value"));
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_x_max_v)){
			optionquery = optionquery + sql_xmax_option + req_x_max_v;
		}
		
		//SQL문 WHERE 절 추가 Option : X MIN
		String req_x_min_v = ComUtil.isNullToDashString(request.getParameter("graph_xrange_min_value"));
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_x_min_v)){
			optionquery = optionquery + sql_xmin_option + req_x_min_v;
		}

		//Y 체크박스 여부 확인
		//SQL문 WHERE 절 추가 Option : Y MAX
		String req_y_max_v = ComUtil.isNullToDashString(request.getParameter("graph_yrange_max_value"));
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_y_max_v)){
			optionquery = optionquery + sql_ymax_option + req_y_max_v;
		}
		
		//SQL문 WHERE 절 추가 Option : Y MIN
		String req_y_min_v = ComUtil.isNullToDashString(request.getParameter("graph_yrange_min_value"));
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_y_min_v)){
			optionquery = optionquery + sql_ymin_option + req_y_min_v;
		}
		
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
		return optionquery;
	}
	
	/**
	 * @MethodName  : selectFinalAssessList
	 * @Date   : 2010. 09. 09 
	 * @MethodDescription : 최종 평가 데이터를 조회하는 메소드
	 * @return
	 * @History  : - 
	 */
	public Vector<?> selectFinalAssessList(){
		Vector<?> vecList = new Vector<Object>();
		DAO_ASSESS_INFO info = new DAO_ASSESS_INFO();
		vecList = info.selectFinalAssessList();
		return vecList;
	}
	
	/**
	 * @MethodName : insertPropertyAssessInfo
	 * @Desc : 1차평가 정보를 DB에 입력하는 메소드
	 * @param request
	 * @param user_id
	 * @return
	 */
	public boolean insertPropertyAssessInfo(Properties_Assess_Info info){
		boolean return_value = false;
		
		
		DAO_ASSESS_INFO assess_input = new DAO_ASSESS_INFO();
		
		String pr_no = info.getPL_BI_DATA_NUM();
		
		if(assess_input.selectAssessInfoExist(pr_no) == false){ //존재하지 않으면 Insert
			return_value = assess_input.insertAssessInfo(info);
		}else{ //존재하면 update
			return_value = assess_input.updateAssessInfo(info);
		}
		
		return return_value;
	}
	
	/**
	 * @MethodName  : selectFinalAssessInfo
	 * @Date   : 2010. 10. 19 
	 * @MethodDescription : 최종평가 정보를 가져오는 비즈니스 메소드
	 * @param v_pr_no
	 * @return
	 * @History  : - 
	 */
	public Properties_Final_Assess_Info selectFinalAssessInfo(String v_pr_no){
		
		DAO_ASSESS_INFO info = new DAO_ASSESS_INFO();
		
		return info.selectFinalAssessInfo(v_pr_no);
	}
	
	/**
	 * @MethodName  : insertPropertyFinalAssessInfo
	 * @Date   : 2010. 08. 31 
	 * @MethodDescription : 최종 평가 정보를 입력하는 메소드
	 * @param request
	 * @param user_id
	 * @return
	 * @History  : - 
	 */
	/**
	 * @MethodName  : insertPropertyFinalAssessInfo
	 * @Date   : 2011. 7. 7. 
	 * @MethodDescription : -
	 * @param request
	 * @param user_id
	 * @return
	 * @History  : - 
	 */
	public boolean insertPropertyFinalAssessInfo(HttpServletRequest request, String user_id){
		boolean return_value = false;
		
		/*Ctr_Option_Process ctr_data = new Ctr_Option_Process();
		//String user_id = request.getParameter("user_id"); //사용자ID
		String param_v_pro_no     = ComUtil.isNullToDashString(request.getParameter("v_pro_no")); //등급유력 물성번호
		
		//1-3. 표현식,주프로세스,부프로세스, 등  전달받은 파라미터 변수에 저장.
		String param_ic_option     = request.getParameter("ic_option"); // 충돌방식
		String param_projectile    = request.getParameter("projectile"); // 입사입자
		String param_projectile_id = request.getParameter("projectile_id"); // 입사입자ID
		String param_mp_option     = request.getParameter("mp_option"); // 주프로세스
		String param_sp_option     = request.getParameter("sp_option"); // 부프로세스
		
		String exp_param_mp_option = ctr_data.getExpName(param_mp_option);
		String exp_param_sp_option = ctr_data.getExpName(param_sp_option);
		String process_option = ComUtil.getAbbrString(exp_param_mp_option, 10) + ", " + ComUtil.getAbbrString(exp_param_sp_option, 10);
		
		//1-4.입력할 물성정보들을 가져옴
		Vector pd_info = searchAssessPropertyListInfo(request);
		
		//1-5. 등급유력물성정보 입력하기		
		
		//등급유력그래프정보
		String param_xax_unit      = request.getParameter("xax_unit"); // X 단위 코드
		String param_xay_unit      = request.getParameter("xay_unit"); // Y 단위 코드
		
		String param_xax_unit_name      = request.getParameter("x_title"); // X 단위 이름
		String param_xay_unit_name      = request.getParameter("y_title"); // Y 단위 이름
				
		String param_tg_name       = request.getParameter("tg_name"); // 표적입자
		String param_tg_name_id    = request.getParameter("tg_name_id"); // 표적입자 ID
		String param_tg_ionic      = ComUtil.isNullToDashString(request.getParameter("tg_ionic")); // 표적입자 이온화
		String param_tg_elec       = ComUtil.isNullToDashString(request.getParameter("tg_elec")); // 표적입자 전자배치
		String param_tg_fine       = ComUtil.isNullToDashString(request.getParameter("tg_fine")); // 표적입자 미세구조
		String param_pd_name       = ComUtil.isNullToDashString(request.getParameter("pd_name")); // 생성입자 
		String param_pd_name_id    = ComUtil.isNullToDashString(request.getParameter("pd_name_id")); // 생성입자 ID 
		String param_pd_ionic      = ComUtil.isNullToDashString(request.getParameter("pd_ionic")); // 생성입자 이온화
		String param_pd_elec       = ComUtil.isNullToDashString(request.getParameter("pd_elec")); // 생성입자 전자배치
		String param_pd_fine       = ComUtil.isNullToDashString(request.getParameter("pd_fine")); // 생성입자 미세구조
		
		//X, Y 최소, 최대값 체크 설정
		String param_graph_xrange_v      = ComUtil.isNullToDashString(request.getParameter("graph_xrange_value"));
		String param_graph_yrange_v      = ComUtil.isNullToDashString(request.getParameter("graph_yrange_value"));
		
		//X,Y 최소, 최대값 설정
		String param_x_min_v      = ComUtil.isNullToDashString(request.getParameter("graph_xrange_min_value")); // X축 최소
		String param_x_max_v       = ComUtil.isNullToDashString(request.getParameter("graph_xrange_max_value")); // X축 최대
		String param_y_min_v       = ComUtil.isNullToDashString(request.getParameter("graph_yrange_min_value")); // Y축 최소
		String param_y_max_v       = ComUtil.isNullToDashString(request.getParameter("graph_yrange_max_value")); // Y축 최대 
		
		//평가 결과 값 저장
		String param_best_value = ComUtil.isNullToDashString(request.getParameter("final_best_value"));// Best value
		String param_average_value = ComUtil.isNullToDashString(request.getParameter("final_average_value"));// average value
		String param_final_assess = ComUtil.isNullToDashString(request.getParameter("final_assess"));// final_assess
		String param_final_col_period = ComUtil.isNullToDashString(request.getParameter("final_col_period"));// final_col_period
		String param_final_opinion = ComUtil.isNullToDashString(request.getParameter("final_opinion"));// final_opinion
		String param_final_std_ref_data = ComUtil.isNullToDashString(request.getParameter("final_std_ref_data"));// final_std_ref_data
		
		//표현식 만들기
		String pd_expression = makeAssessExpression(request);
		//물성정보번호 리스트 만들기
		String pro_no_list = getPro_No_List(pd_info);
		
		//1. 등급유력 물성 기본 정보 입력하기
		Inf_Grd_Properties_Basic_Info basic_info = new Inf_Grd_Properties_Basic_Info();		
		basic_info.setPL_IGBI_DATA_NUM(param_v_pro_no);
		basic_info.setPL_IGBI_MAIN_PROC(param_mp_option);
		basic_info.setPL_IGBI_SUB_PROC(param_sp_option);
		basic_info.setPL_IGBI_EXPRESSION(pd_expression);
		basic_info.setPL_UI_ID(user_id);
		basic_info.setPL_IGBI_DATA_NUM_LIST(pro_no_list);
		
		
		//등급유력 물성 기본 정보 입력 결과 변수
		boolean property_result = false;
		//등급 유력 그래프 정보 입력 결과 변수
		boolean graph_result = false;
		boolean property_equation_result = false;
		boolean property_data_result = false;
		
		DAO_INF_GRD_PROPERTY_INFO dao_property = new DAO_INF_GRD_PROPERTY_INFO();
		property_result = dao_property.insertInfGrdBasicInfo(basic_info);
		
		//2. 등급 유력 그래프 단위 정보 입력하기 
		if(property_result == true){			
			Inf_Grd_Graph_Basic_Info graph_info = new Inf_Grd_Graph_Basic_Info();
			graph_info.setPL_IGBI_DATA_NUM(param_v_pro_no);
			graph_info.setPL_IGGI_X_AX_UNIT(param_xax_unit);
			graph_info.setPL_IGGI_X_AX_CAL("-");
			graph_info.setPL_IGGI_Y_AX_UNIT(param_xay_unit);
			graph_info.setPL_IGGI_Y_AX_CAL("-");
			graph_info.setPL_IGGI_Y_AX_COMM("-");
			
			DAO_INF_GRD_GRAPH_INFO dao_graph = new DAO_INF_GRD_GRAPH_INFO();
			graph_result = dao_graph.insertInfGrdGraphInfo(graph_info);
		}
		
		
		//3. 등급유력 물성 입자 정보 입력하기 ==> 이부분 좀 로직상으로 이상함. 고려대상임.
		if(graph_result == true){
			// 등급유력 물성 입자 정보 입력하기
			//Vector pd_list = info.getPD_LIST();
			Properties_Assess_Search_List prot = (Properties_Assess_Search_List) pd_info.elementAt(0);
		  	String pr_no = prot.getPL_BI_DATA_NUM();
		  	
		  	DAO_BASIC_PROPERTY_INFO equation_info = new DAO_BASIC_PROPERTY_INFO();
		  	Vector equation_list = equation_info.selectEquationList(pr_no);
		  	
		  	Vector insert_list = new Vector();
		  	for(int i = 0; i < equation_list.size(); i++){
		  		Properties_Equation_Get_DbInfo info = (Properties_Equation_Get_DbInfo)equation_list.elementAt(i);
		  		Inf_Grd_Properties_Equation_Info insert_info = new Inf_Grd_Properties_Equation_Info();
		  		insert_info.setPL_IGBI_DATA_NUM(param_v_pro_no);
		  		insert_info.setPL_CPBI_ELE_NUM(info.getPL_CPBI_ELE_NUM());
		  		insert_info.setPL_IGEI_SEQ(info.getPL_BEI_SEQ());
		  		insert_info.setPL_IGEI_CHG_STATE(info.getPL_BEI_CHG_STATE());
		  		insert_info.setPL_IGEI_ELC_STATE(info.getPL_BEI_ELC_STATE());
		  		insert_info.setPL_IGEI_OTH_STRUC(info.getPL_BEI_OTH_STRUC());
		  		
		  		insert_list.addElement(insert_info);
		  	}
		  	
		  	property_equation_result = dao_property.insertInfGrdEquation(insert_list);  

		}
		//4. 그래프 데이터 입력하기
		if(property_equation_result == true){
			DAO_BASIC_GRAPH_INFO graph = new DAO_BASIC_GRAPH_INFO();
			DAO_INF_GRD_PROPERTY_INFO dao = new DAO_INF_GRD_PROPERTY_INFO();
			for(int i = 0; i < pd_info.size(); i++){
			
				Properties_Assess_Search_List prot = (Properties_Assess_Search_List) pd_info.elementAt(i);
			  	String seach_pr_no = prot.getPL_BI_DATA_NUM();	 //물성번호 	
			  	
			  	Vector v_option = new Vector();
				v_option.addElement(seach_pr_no);
				
				Vector result_v = graph.selectBasicGraphData(v_option);
			  	
				for(int j = 0; j < result_v.size(); j++){
			  		Graph_Data_Info g_data = (Graph_Data_Info)result_v.elementAt(j);	
					Inf_Grd_Graph_Data_Info data = new Inf_Grd_Graph_Data_Info();

					data.setPL_IGBI_DATA_NUM(param_v_pro_no);//등급유력번호
					data.setPL_IGGD_SEQ_NUM(g_data.getPL_BGD_SEQ_NUM());
					data.setPL_IGGD_X_AX_VAL(g_data.getPL_BGD_X_AX_VAL());
					data.setPL_IGGD_Y_AX_VAL(g_data.getPL_BGD_Y_AX_VAL());
					data.setPL_IGGD_X_ERR(g_data.getPL_BGD_X_ERR());
					data.setPL_IGGD_Y_ERR_MAX(g_data.getPL_BGD_Y_ERR_MAX());
					data.setPL_IGGD_Y_ERR_MIN(g_data.getPL_BGD_Y_ERR_MIN());
					data.setPL_IGGD_RATIO(g_data.getPL_BGD_RATIO());
					data.setPL_IGGD_PRESS(g_data.getPL_BGD_PRESS());
					data.setPL_IGGD_BACKUP_DATA(g_data.getPL_BGD_BACKUP_DATA());
			  		
					property_data_result = dao.insertInfGraphData(data);	  		
			  	} 
			}
		}*/
		
		String param_v_pro_no     = ComUtil.isNullToDashString(request.getParameter("v_pr_no")); //등급유력 물성번호
		
		//평가 결과 값 저장
		String param_best_value = ComUtil.isNullToDashString(request.getParameter("final_best_value"));// Best value
		String param_average_value = ComUtil.isNullToDashString(request.getParameter("final_average_value"));// average value
		String param_final_assess = ComUtil.isNullToDashString(request.getParameter("final_assess"));// final_assess
		String param_final_col_period = ComUtil.isNullToDashString(request.getParameter("final_col_period"));// final_col_period
		String param_final_opinion = ComUtil.isNullToDashString(request.getParameter("final_opinion"));// final_opinion
		String param_final_std_ref_data = ComUtil.isNullToDashString(request.getParameter("final_std_ref_data"));// final_std_ref_data
		//X Max & Min
		String param_final_x_max_v = ComUtil.isNullToDashString(request.getParameter("final_x_max_v"));
		String param_final_x_min_v = ComUtil.isNullToDashString(request.getParameter("final_x_min_v"));
		
		//기본 정보 가져오기
		Ctr_Graph_Info_Process ctr_graph = new Ctr_Graph_Info_Process();
		Inf_Grd_Graph_Basic_Info graph = (Inf_Grd_Graph_Basic_Info)ctr_graph.selectViewInfGrdGraphInfo(param_v_pro_no);
		
		//X단위
		String x_unit = graph.getPL_IGGI_X_AX_UNIT();
		//Y단위
		String y_unit = graph.getPL_IGGI_Y_AX_UNIT();
		
		/*System.out.println("param_v_pro_no : " + param_v_pro_no);
		System.out.println("param_best_value : " + param_best_value);
		System.out.println("param_average_value : " + param_average_value);
		System.out.println("param_final_assess : " + param_final_assess);
		System.out.println("param_final_col_period : " + param_final_col_period);
		System.out.println("param_final_opinion : " + param_final_opinion);
		System.out.println("param_final_std_ref_data : " + param_final_std_ref_data);
		System.out.println("param_final_x_max_v : " + param_final_x_max_v);
		System.out.println("param_final_x_min_v : " + param_final_x_min_v);
		System.out.println("x_unit : " + x_unit);
		System.out.println("y_unit : " + y_unit);
		System.out.println("user_id : " + user_id);
		*/
		// 평가 정보 받아오기		

			Properties_Final_Assess_Info final_assess = new Properties_Final_Assess_Info();
			final_assess.setPL_IGBI_DATA_NUM(param_v_pro_no);
			//Indirect Production Method
			if("-".equalsIgnoreCase(param_best_value)){
				final_assess.setPL_SEI_PROD_METHOD_AVERAGE(param_average_value);
				final_assess.setPL_SEI_PROD_METHOD_BEST("-");
			}else if("-".equalsIgnoreCase(param_average_value)){
				final_assess.setPL_SEI_PROD_METHOD_BEST(param_best_value);
				final_assess.setPL_SEI_PROD_METHOD_AVERAGE("-");
			}
			//Assessment
			final_assess.setPL_SEI_ASSESS_CONTENT(param_final_assess);
			//Energy Range
			final_assess.setPL_SEI_ENERGY_RANGE(param_final_x_min_v + "," + param_final_x_max_v);
			//Collection Period
			final_assess.setPL_SEI_COLLECT_PERIOD(param_final_col_period);
			//Final Opinion
			final_assess.setPL_SEI_FINAL_OPINION(param_final_opinion);
			//Standard Reference Data
			final_assess.setPL_SEI_STD_REF_DATA(param_final_std_ref_data);
			//평가 시간 입력
			final_assess.setPL_SEI_ASSESS_DATE(ComUtil.getTimeNow());
			//X 단위
			final_assess.setPL_SEI_X_UNIT(x_unit);
			//Y 단위
			final_assess.setPL_SEI_Y_UNIT(y_unit);
			//X 최대값
			final_assess.setPL_SEI_X_MAX(param_final_x_max_v);
			//X 최소값
			final_assess.setPL_SEI_X_MIN(param_final_x_min_v);
			//평가자
			final_assess.setPL_SEI_ASSESS_USER(user_id);			
			
			/*
			 * 실제 평가 데이터 저장하기
			 * 데이터 저장이 성공하면 상태 업데이트 하기
			 */
			DAO_ASSESS_INFO assess_input = new DAO_ASSESS_INFO();
			boolean i_value = false;
			i_value = assess_input.insertFinalAssessInfo(final_assess);
			if(i_value == true){
				return_value = updateStateFinalAssess(param_v_pro_no, "CC");
			}else{
				return_value = i_value;
			}
			//추후 새로 만들어진 데이터를 검색이 가능하도록 수정해야 함.
	
		return return_value;
	}
	
	/**
	 * @MethodName  : updateStateFinalAssess
	 * @Date   : 2011. 6. 28. 
	 * @MethodDescription : 평가정보 입력상태를 업데이트 하는 메소드
	 * @param v_pr_no
	 * @param state
	 * @return
	 * @History  : - 
	 */
	public boolean updateStateFinalAssess(String v_pr_no, String state){
		boolean return_value=false;
		
		DAO_INF_GRD_PROPERTY_INFO dao_property = new DAO_INF_GRD_PROPERTY_INFO();
		
		return_value = dao_property.updateInfGrdInsertStateInfo(v_pr_no, state);
		
		return return_value;
	}
	
	/**
	 * @MethodName  : makeAssessDataInfo
	 * @Date   : 2011. 6. 28. 
	 * @MethodDescription : 등급유력 정보를 DB에 입력하고 평가될 데이터를 생성하는 메소드
	 * @param request
	 * @param context
	 * @param param_v_pro_no
	 * @return
	 * @History  : - 
	 */
	public boolean makeAssessDataInfo(HttpServletRequest request, ServletContext context, String param_v_pro_no, String user_id){
		
		boolean return_value = false;
		
		//boolean return_value = false;
		
		Ctr_Option_Process ctr_data = new Ctr_Option_Process();
		//String user_id = request.getParameter("user_id"); //사용자ID
		//String param_v_pro_no     = ComUtil.isNullToDashString(request.getParameter("v_pro_no")); //등급유력 물성번호
		
		//1-3. 표현식,주프로세스,부프로세스, 등  전달받은 파라미터 변수에 저장.
		String param_ic_option     = request.getParameter("ic_option"); // 충돌방식
		String param_projectile    = request.getParameter("projectile"); // 입사입자
		String param_projectile_id = request.getParameter("projectile_id"); // 입사입자ID
		String param_mp_option     = request.getParameter("mp_option"); // 주프로세스
		String param_sp_option     = request.getParameter("sp_option"); // 부프로세스
		
		String exp_param_mp_option = ctr_data.getExpName(param_mp_option);
		String exp_param_sp_option = ctr_data.getExpName(param_sp_option);
		String process_option = ComUtil.getAbbrString(exp_param_mp_option, 10) + ", " + ComUtil.getAbbrString(exp_param_sp_option, 10);
		
		//1-4.입력할 물성정보들을 가져옴
		Vector<?> pd_info = searchAssessPropertyListInfo(request);
		
		//1-5. 등급유력물성정보 입력하기		
		
		//등급유력그래프정보
		String param_xax_unit      = request.getParameter("xax_unit"); // X 단위 코드
		String param_xay_unit      = request.getParameter("xay_unit"); // Y 단위 코드
		
		//String param_xax_unit_name      = request.getParameter("x_title"); // X 단위 이름
		//String param_xay_unit_name      = request.getParameter("y_title"); // Y 단위 이름
				
		String param_tg_name       = request.getParameter("tg_name"); // 표적입자
		String param_tg_name_id    = request.getParameter("tg_name_id"); // 표적입자 ID
		String param_tg_ionic      = ComUtil.isNullToNullString(request.getParameter("tg_ionic")); // 표적입자 이온화
		String param_tg_elec       = ComUtil.isNullToNullString(request.getParameter("tg_elec")); // 표적입자 전자배치
		String param_tg_fine       = ComUtil.isNullToNullString(request.getParameter("tg_fine")); // 표적입자 미세구조
		String param_pd_name       = ComUtil.isNullToNullString(request.getParameter("pd_name")); // 생성입자 
		String param_pd_name_id    = ComUtil.isNullToNullString(request.getParameter("pd_name_id")); // 생성입자 ID 
		String param_pd_ionic      = ComUtil.isNullToNullString(request.getParameter("pd_ionic")); // 생성입자 이온화
		String param_pd_elec       = ComUtil.isNullToNullString(request.getParameter("pd_elec")); // 생성입자 전자배치
		String param_pd_fine       = ComUtil.isNullToNullString(request.getParameter("pd_fine")); // 생성입자 미세구조
		
		//X, Y 최소, 최대값 체크 설정
		String param_graph_xrange_v      = ComUtil.isNullToNullString(request.getParameter("graph_xrange_value"));
		String param_graph_yrange_v      = ComUtil.isNullToNullString(request.getParameter("graph_yrange_value"));
		
		//X,Y 최소, 최대값 설정
		String param_x_min_v      = ComUtil.isNullToNullString(request.getParameter("graph_xrange_min_value")); // X축 최소
		String param_x_max_v       = ComUtil.isNullToNullString(request.getParameter("graph_xrange_max_value")); // X축 최대
		String param_y_min_v       = ComUtil.isNullToNullString(request.getParameter("graph_yrange_min_value")); // Y축 최소
		String param_y_max_v       = ComUtil.isNullToNullString(request.getParameter("graph_yrange_max_value")); // Y축 최대 

		//표현식 만들기
		String pd_expression = makeAssessExpression(request);
		//물성정보번호 리스트 만들기
		String pro_no_list = getPro_No_List(pd_info);	
		//물성관리번호 리스트 만들기
		String pro_mgmt_list = getPro_Mgmt_List(pd_info);
		//물성정보번호 개수
		String pro_no_cnt = Integer.toString(pd_info.size());
		//물성정보 참고문헌리스트 만들기
		String pro_artcl_list = getPro_Artcl_List(pd_info);
		
		//등급유력 물성 기본 정보 입력 결과 변수
		boolean property_result = false;
		//등급 유력 그래프 정보 입력 결과 변수
		boolean graph_result = false;
		boolean property_equation_result = false;
		
		//1. 등급유력 물성 기본 정보 입력하기
		Inf_Grd_Properties_Basic_Info basic_info = new Inf_Grd_Properties_Basic_Info();		
		basic_info.setPL_IGBI_DATA_NUM(param_v_pro_no);
		basic_info.setPL_IGBI_MAIN_PROC(param_mp_option);
		basic_info.setPL_IGBI_SUB_PROC(param_sp_option);
		basic_info.setPL_IGBI_EXPRESSION(pd_expression);
		basic_info.setPL_UI_ID(user_id);
		basic_info.setPL_IGBI_DATA_NUM_LIST(pro_no_list);
		basic_info.setPL_IGBI_INSERT_FLAG("NI");	
		basic_info.setPL_IGBI_IMP_CLASS(param_ic_option);	
		basic_info.setPL_IGBI_DATA_NUM_CNT(pro_no_cnt);
		basic_info.setPL_IGBI_MGMT_NUM_LIST(pro_mgmt_list);
		basic_info.setPL_IGBI_REF_ARTICLE_LIST(pro_artcl_list);
		
		DAO_INF_GRD_PROPERTY_INFO dao_property = new DAO_INF_GRD_PROPERTY_INFO();
		property_result = dao_property.insertInfGrdBasicInfo(basic_info);
		
		//2. 등급 유력 그래프 단위 정보 입력하기 
		if(property_result == true){			
			Inf_Grd_Graph_Basic_Info graph_info = new Inf_Grd_Graph_Basic_Info();
			graph_info.setPL_IGBI_DATA_NUM(param_v_pro_no);
			graph_info.setPL_IGGI_X_AX_UNIT(param_xax_unit);
			graph_info.setPL_IGGI_X_AX_CAL("-");
			graph_info.setPL_IGGI_Y_AX_UNIT(param_xay_unit);
			graph_info.setPL_IGGI_Y_AX_CAL("-");
			graph_info.setPL_IGGI_Y_AX_COMM("-");
			
			DAO_INF_GRD_GRAPH_INFO dao_graph = new DAO_INF_GRD_GRAPH_INFO();
			graph_result = dao_graph.insertInfGrdGraphInfo(graph_info);
		}
		
		//3. 등급유력 물성 입자 정보 입력하기
		if(graph_result == true){
			//표적입자 저장(Target)
	  		Inf_Grd_Properties_Equation_Info first_insert_info = new Inf_Grd_Properties_Equation_Info();
			first_insert_info.setPL_IGBI_DATA_NUM(param_v_pro_no);
			first_insert_info.setPL_CPBI_ELE_NUM(param_tg_name_id);
			first_insert_info.setPL_IGEI_SEQ("1");
			first_insert_info.setPL_IGEI_CHG_STATE(param_tg_ionic);
			first_insert_info.setPL_IGEI_ELC_STATE(param_tg_elec);
			first_insert_info.setPL_IGEI_OTH_STRUC(param_tg_fine); 		

	  		property_equation_result = dao_property.insertInfGrdEquation(first_insert_info);	  		
	  		
			//생성입자(Product)	  	
	  		
	  		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(param_pd_name)){
	  			Inf_Grd_Properties_Equation_Info second_insert_info = new Inf_Grd_Properties_Equation_Info();
		  		second_insert_info.setPL_IGBI_DATA_NUM(param_v_pro_no);
		  		second_insert_info.setPL_CPBI_ELE_NUM(param_pd_name_id);
		  		second_insert_info.setPL_IGEI_SEQ("2");
		  		second_insert_info.setPL_IGEI_CHG_STATE(param_pd_ionic);
		  		second_insert_info.setPL_IGEI_ELC_STATE(param_pd_elec);
		  		second_insert_info.setPL_IGEI_OTH_STRUC(param_pd_fine); 		

		  		property_equation_result = dao_property.insertInfGrdEquation(second_insert_info); 	
	  		}
	  			  		
			// 등급유력 물성 입자 정보 입력하기
			//Vector pd_list = info.getPD_LIST();
			/*Properties_Assess_Search_List prot = (Properties_Assess_Search_List) pd_info.elementAt(0);
		  	String pr_no = prot.getPL_BI_DATA_NUM();
		  	
		  	DAO_BASIC_PROPERTY_INFO equation_info = new DAO_BASIC_PROPERTY_INFO();
		  	Vector equation_list = equation_info.selectEquationList(pr_no);
		  	
		  	Vector insert_list = new Vector();
		  	for(int i = 0; i < equation_list.size(); i++){
		  		Properties_Equation_Get_DbInfo info = (Properties_Equation_Get_DbInfo)equation_list.elementAt(i);
		  		Inf_Grd_Properties_Equation_Info insert_info = new Inf_Grd_Properties_Equation_Info();
		  		insert_info.setPL_IGBI_DATA_NUM(param_v_pro_no);
		  		insert_info.setPL_CPBI_ELE_NUM(info.getPL_CPBI_ELE_NUM());
		  		insert_info.setPL_IGEI_SEQ(info.getPL_BEI_SEQ());
		  		insert_info.setPL_IGEI_CHG_STATE(info.getPL_BEI_CHG_STATE());
		  		insert_info.setPL_IGEI_ELC_STATE(info.getPL_BEI_ELC_STATE());
		  		insert_info.setPL_IGEI_OTH_STRUC(info.getPL_BEI_OTH_STRUC());
		  		
		  		insert_list.addElement(insert_info);
		  	}*/
		  	
		  	//property_equation_result = dao_property.insertInfGrdEquation(insert_list);  

		}
		//4. 그래프 데이터 입력하기
		if(property_equation_result == true){
			//String param_v_pro_no     = ComUtil.isNullToDashString(request.getParameter("v_pro_no")); //등급유력 물성번호
			
			//Vector pd_info = searchAssessPropertyListInfo(request);
			
			DAO_BASIC_GRAPH_INFO graph = new DAO_BASIC_GRAPH_INFO();
			DAO_INF_GRD_PROPERTY_INFO dao = new DAO_INF_GRD_PROPERTY_INFO();
			
			String contextpath = context.getRealPath("/");
			//String saveFolder = request.getSession().getServletContext().getRealPath("/") + "article_file/" ;
			String saveFolder = contextpath + "tmp_file";
			//String saveFolder = "E:/DEV/dcpp/public_html/tmp_file";
			String filename = param_v_pro_no + ".txt";
			
			File f = new File(saveFolder, filename);
			
			try {
				FileWriter fw = new FileWriter(f, true);
				
				fw.write("========= 등급유력번호 : "+ param_v_pro_no + " =========");
				fw.write("\r\n");
				
				for(int i = 0; i < pd_info.size(); i++){
					
					Properties_Assess_Search_List prot = (Properties_Assess_Search_List) pd_info.elementAt(i);
				  	String seach_pr_no = prot.getPL_BI_DATA_NUM();	 //물성번호 	
				  	
				  	//System.out.println("물성번호 : "+ seach_pr_no);
				  	
				  	fw.write("---------------------------------------------");
				  	fw.write("\r\n");
				  	fw.write("물성번호 : "+ seach_pr_no);
				  	fw.write("\r\n");
				  	fw.write("---------------------------------------------");
				  	fw.write("\r\n");
				  	Vector<String> v_option = new Vector<String>();
					v_option.addElement(seach_pr_no);					
					/*
					 * 원래 이부분에서 최대 최소값이 적용되어야 함.
					 * 하지만 아직 적용할지 여부를 결정하지 않음.
					 * 요청이 있을 경우 데이터를 최대 최소값에 맞게 추출하여 
					 * 저장할 예정임.
					 */
					
					Vector<?> result_v = graph.selectBasicGraphData(v_option);
					
					fw.write("X_V");fw.write("\t");fw.write("Y_V");fw.write("\t");
					fw.write("X_E");fw.write("\t");fw.write("Y_MAE");fw.write("\t");fw.write("Y_MIE");fw.write("\t");
				  	fw.write("\r\n");
					fw.write("---------------------------------------------");
				  	fw.write("\r\n");
				  				
					for(int j = 0; j < result_v.size(); j++){
				  		Graph_Data_Info g_data = (Graph_Data_Info)result_v.elementAt(j);
				  		
				  		fw.write(g_data.getPL_BGD_X_AX_VAL());fw.write("\t");
				  		fw.write(g_data.getPL_BGD_Y_AX_VAL());fw.write("\t");
				  		fw.write(g_data.getPL_BGD_X_ERR());fw.write("\t");
				  		fw.write(g_data.getPL_BGD_Y_ERR_MAX());fw.write("\t");
				  		fw.write(g_data.getPL_BGD_Y_ERR_MIN());
				  		fw.write("\r\n");			  		  		
				  	} 
				}
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return_value = f.exists();
					
		}
		return return_value;
	}
	
	/**
	 * @MethodName  : insertOneFinalPropertyData
	 * @Date   : 2011. 7. 6. 
	 * @MethodDescription : 최종 등급유력 물성정보를 입력할때 한개만 선택했을 경우 데이터를 이동시키는 메소드
	 * @param v_pr_no
	 * @param user_id
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean insertOneFinalPropertyData(HttpServletRequest request, String param_v_pro_no, String search_pr_no){
	//public boolean insertOneFinalPropertyData(String v_pr_no, String user_id, String pr_no){
		boolean r_value = false;
				
		//String param_v_pro_no     = request.getParameter("v_pr_no");
		
		//System.out.println("param_v_pro_no : "+param_v_pro_no);	
		
		String param_user_id     = request.getParameter("user_id");

		Ctr_Option_Process ctr_data = new Ctr_Option_Process();
		//String user_id = request.getParameter("user_id"); //사용자ID
		//String param_v_pro_no     = ComUtil.isNullToDashString(request.getParameter("v_pro_no")); //등급유력 물성번호
		
		//1-3. 표현식,주프로세스,부프로세스, 등  전달받은 파라미터 변수에 저장.
		String param_ic_option     = request.getParameter("ic_option"); // 충돌방식
		String param_projectile    = request.getParameter("projectile"); // 입사입자
		String param_projectile_id = request.getParameter("projectile_id"); // 입사입자ID
		String param_mp_option     = request.getParameter("mp_option"); // 주프로세스
		String param_sp_option     = request.getParameter("sp_option"); // 부프로세스
		
		String exp_param_mp_option = ctr_data.getExpName(param_mp_option);
		String exp_param_sp_option = ctr_data.getExpName(param_sp_option);
		String process_option = ComUtil.getAbbrString(exp_param_mp_option, 10) + ", " + ComUtil.getAbbrString(exp_param_sp_option, 10);
		
		//1-4.입력할 물성정보들을 가져옴
		//Vector pd_info = searchAssessPropertyListInfo(request);	
		
		//1-5. 등급유력물성정보 입력하기		
		
		//등급유력그래프정보
		String param_xax_unit      = request.getParameter("xax_unit"); // X 단위 코드
		String param_xay_unit      = request.getParameter("xay_unit"); // Y 단위 코드
		
		//String param_xax_unit_name      = request.getParameter("x_title"); // X 단위 이름
		//String param_xay_unit_name      = request.getParameter("y_title"); // Y 단위 이름
				
		String param_tg_name       = request.getParameter("tg_name"); // 표적입자
		String param_tg_name_id    = request.getParameter("tg_name_id"); // 표적입자 ID
		String param_tg_ionic      = ComUtil.isNullToNullString(request.getParameter("tg_ionic")); // 표적입자 이온화
		String param_tg_elec       = ComUtil.isNullToNullString(request.getParameter("tg_elec")); // 표적입자 전자배치
		String param_tg_fine       = ComUtil.isNullToNullString(request.getParameter("tg_fine")); // 표적입자 미세구조
		String param_pd_name       = ComUtil.isNullToNullString(request.getParameter("pd_name")); // 생성입자 
		String param_pd_name_id    = ComUtil.isNullToNullString(request.getParameter("pd_name_id")); // 생성입자 ID 
		String param_pd_ionic      = ComUtil.isNullToNullString(request.getParameter("pd_ionic")); // 생성입자 이온화
		String param_pd_elec       = ComUtil.isNullToNullString(request.getParameter("pd_elec")); // 생성입자 전자배치
		String param_pd_fine       = ComUtil.isNullToNullString(request.getParameter("pd_fine")); // 생성입자 미세구조
		
		//X, Y 최소, 최대값 체크 설정
		String param_graph_xrange_v      = ComUtil.isNullToNullString(request.getParameter("graph_xrange_value"));
		String param_graph_yrange_v      = ComUtil.isNullToNullString(request.getParameter("graph_yrange_value"));
		
		//X,Y 최소, 최대값 설정
		String param_x_min_v      = ComUtil.isNullToNullString(request.getParameter("graph_xrange_min_value")); // X축 최소
		String param_x_max_v       = ComUtil.isNullToNullString(request.getParameter("graph_xrange_max_value")); // X축 최대
		String param_y_min_v       = ComUtil.isNullToNullString(request.getParameter("graph_yrange_min_value")); // Y축 최소
		String param_y_max_v       = ComUtil.isNullToNullString(request.getParameter("graph_yrange_max_value")); // Y축 최대 

		//표현식 만들기
		String pd_expression = makeAssessExpression(request);
		
		DAO_BASIC_PROPERTY_INFO dao_property = new DAO_BASIC_PROPERTY_INFO();
		Properties_Basic_Info basic_info = (Properties_Basic_Info)dao_property.selectPropertySpecInfo(search_pr_no);
		//물성정보번호 리스트 만들기
		//String pro_no_list = getPro_No_List(pd_info);	
		//물성관리번호 리스트 만들기
		String pro_mgmt_list = basic_info.getPL_BI_MGMT_DATA_NUM();
		//물성정보번호 개수
		String pro_no_cnt = "1";
		//물성정보 참고문헌리스트 만들기
		String pro_artcl_list = basic_info.getPL_RAI_ARTCL_NUM();//getPro_Artcl_List(pd_info);
		
		String str_artcl_list = "";
		String str_comma = ", ";
		//String str_enter = "\n";
		String str_page = ", pp.";	  	
		String str_fr_brace = "[";
		String str_ed_brace = "] ";
		String str_col = " : ";
	  	
	  	Vector<String> v_option = new Vector<String>();
		v_option.addElement(pro_artcl_list);
		
		DAO_REF_ARTICLE_INFO dao_info = new DAO_REF_ARTICLE_INFO();
		
		Article_Info info = new Article_Info();

		info = dao_info.selectArticleInfo(v_option);

		//번호 시작
		str_artcl_list = str_artcl_list + str_fr_brace + "1" + str_ed_brace;
		//관리 번호
		str_artcl_list = str_artcl_list + pro_mgmt_list + str_col;
		
		//논문 제목
		str_artcl_list = str_artcl_list + info.getPL_RAI_ARTCL_TITLE();
		//논문 저자
		str_artcl_list = str_artcl_list + str_comma + info.getPL_RAI_ARTCL_AUTH_ENAME();
		//저널명
		str_artcl_list = str_artcl_list + str_comma + info.getPL_RAI_JOUR_TITLE();
		//볼륨
		str_artcl_list = str_artcl_list + str_comma + info.getPL_RAI_JOUR_VOL();
		//년도
		str_artcl_list = str_artcl_list + str_comma + info.getPL_RAI_JOUR_YEAR();
		//시작페이지
		str_artcl_list = str_artcl_list + str_page + info.getPL_RAI_ARTCL_ST_PAGE();
		
		
		//등급유력 물성 기본 정보 입력 결과 변수
		boolean property_result = false;
		//등급 유력 그래프 정보 입력 결과 변수
		boolean graph_result = false;
		boolean property_equation_result = false;
		
		/*
		 * 1. 등급유력 물성 기본 정보 입력하기
		 */
		Inf_Grd_Properties_Basic_Info inf_basic_info = new Inf_Grd_Properties_Basic_Info();		
		inf_basic_info.setPL_IGBI_DATA_NUM(param_v_pro_no);
		inf_basic_info.setPL_IGBI_MAIN_PROC(param_mp_option);
		inf_basic_info.setPL_IGBI_SUB_PROC(param_sp_option);
		inf_basic_info.setPL_IGBI_EXPRESSION(pd_expression);
		inf_basic_info.setPL_UI_ID(param_user_id);
		inf_basic_info.setPL_IGBI_DATA_NUM_LIST(search_pr_no);
		inf_basic_info.setPL_IGBI_INSERT_FLAG("AI");	
		inf_basic_info.setPL_IGBI_IMP_CLASS(param_ic_option);	
		inf_basic_info.setPL_IGBI_DATA_NUM_CNT(pro_no_cnt);
		inf_basic_info.setPL_IGBI_MGMT_NUM_LIST(pro_mgmt_list);
		inf_basic_info.setPL_IGBI_REF_ARTICLE_LIST(str_artcl_list);
		
		DAO_INF_GRD_PROPERTY_INFO inf_dao_property = new DAO_INF_GRD_PROPERTY_INFO();
		property_result = inf_dao_property.insertInfGrdBasicInfo(inf_basic_info);
	
		/*
		 * 2. 등급 유력 그래프 단위 정보 입력하기 
		 */
		if(property_result == true){			
			Inf_Grd_Graph_Basic_Info graph_info = new Inf_Grd_Graph_Basic_Info();
			graph_info.setPL_IGBI_DATA_NUM(param_v_pro_no);
			graph_info.setPL_IGGI_X_AX_UNIT(param_xax_unit);
			graph_info.setPL_IGGI_X_AX_CAL("-");
			graph_info.setPL_IGGI_Y_AX_UNIT(param_xay_unit);
			graph_info.setPL_IGGI_Y_AX_CAL("-");
			graph_info.setPL_IGGI_Y_AX_COMM("-");
			
			DAO_INF_GRD_GRAPH_INFO dao_graph = new DAO_INF_GRD_GRAPH_INFO();
			graph_result = dao_graph.insertInfGrdGraphInfo(graph_info);
		}

		/*
		 * 3. 등급유력 물성 입자 정보 입력하기
		 */
		if(graph_result == true){
			//표적입자 저장(Target)
	  		Inf_Grd_Properties_Equation_Info first_insert_info = new Inf_Grd_Properties_Equation_Info();
			first_insert_info.setPL_IGBI_DATA_NUM(param_v_pro_no);
			first_insert_info.setPL_CPBI_ELE_NUM(param_tg_name_id);
			first_insert_info.setPL_IGEI_SEQ("1");
			first_insert_info.setPL_IGEI_CHG_STATE(param_tg_ionic);
			first_insert_info.setPL_IGEI_ELC_STATE(param_tg_elec);
			first_insert_info.setPL_IGEI_OTH_STRUC(param_tg_fine); 		

	  		property_equation_result = inf_dao_property.insertInfGrdEquation(first_insert_info);	  		
	  		
			//생성입자(Product)	  	
	  		
	  		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(param_pd_name)){
	  			Inf_Grd_Properties_Equation_Info second_insert_info = new Inf_Grd_Properties_Equation_Info();
		  		second_insert_info.setPL_IGBI_DATA_NUM(param_v_pro_no);
		  		second_insert_info.setPL_CPBI_ELE_NUM(param_pd_name_id);
		  		second_insert_info.setPL_IGEI_SEQ("2");
		  		second_insert_info.setPL_IGEI_CHG_STATE(param_pd_ionic);
		  		second_insert_info.setPL_IGEI_ELC_STATE(param_pd_elec);
		  		second_insert_info.setPL_IGEI_OTH_STRUC(param_pd_fine); 		

		  		property_equation_result = inf_dao_property.insertInfGrdEquation(second_insert_info); 	
	  		}
	  			  		
			// 등급유력 물성 입자 정보 입력하기
			//Vector pd_list = info.getPD_LIST();
			/*Properties_Assess_Search_List prot = (Properties_Assess_Search_List) pd_info.elementAt(0);
		  	String pr_no = prot.getPL_BI_DATA_NUM();
		  	
		  	DAO_BASIC_PROPERTY_INFO equation_info = new DAO_BASIC_PROPERTY_INFO();
		  	Vector equation_list = equation_info.selectEquationList(pr_no);
		  	
		  	Vector insert_list = new Vector();
		  	for(int i = 0; i < equation_list.size(); i++){
		  		Properties_Equation_Get_DbInfo info = (Properties_Equation_Get_DbInfo)equation_list.elementAt(i);
		  		Inf_Grd_Properties_Equation_Info insert_info = new Inf_Grd_Properties_Equation_Info();
		  		insert_info.setPL_IGBI_DATA_NUM(param_v_pro_no);
		  		insert_info.setPL_CPBI_ELE_NUM(info.getPL_CPBI_ELE_NUM());
		  		insert_info.setPL_IGEI_SEQ(info.getPL_BEI_SEQ());
		  		insert_info.setPL_IGEI_CHG_STATE(info.getPL_BEI_CHG_STATE());
		  		insert_info.setPL_IGEI_ELC_STATE(info.getPL_BEI_ELC_STATE());
		  		insert_info.setPL_IGEI_OTH_STRUC(info.getPL_BEI_OTH_STRUC());
		  		
		  		insert_list.addElement(insert_info);
		  	}*/
		  	
		  	//property_equation_result = dao_property.insertInfGrdEquation(insert_list);  

		}
		/*
		 * 4. 등급유력 물성 그래프 데이터 입력하기
		 */
		if(property_equation_result == true){
			DAO_BASIC_GRAPH_INFO graph = new DAO_BASIC_GRAPH_INFO();
			Vector<String> graph_option = new Vector<String>();
			graph_option.addElement(search_pr_no);
			
			Vector<?> result_v = graph.selectBasicGraphData(graph_option);
			
			for(int j = 0; j < result_v.size(); j++){
		  		Graph_Data_Info g_data = (Graph_Data_Info)result_v.elementAt(j);
		  		g_data.setPL_BI_DATA_NUM(param_v_pro_no);
		  		r_value = graph.insertFinalGraphData(g_data);		  		
			}
		}

		
		/*DAO_BASIC_GRAPH_INFO graph = new DAO_BASIC_GRAPH_INFO();
		DAO_FITTING_INFO fitting = new DAO_FITTING_INFO();
		for(int i = 0; i < pd_info.size(); i++){
		
			Properties_Assess_Search_List prot = (Properties_Assess_Search_List) pd_info.elementAt(i);
		  	String seach_pr_no = prot.getPL_BI_DATA_NUM();	 //물성번호 	
		  	//String artcl_no = prot.getPL_RAI_ARTCL_NUM(); //참고문헌 번호
		  	//String artcl_author = prot.getPL_RAI_ARTCL_AUTH_NAME(); //참고문헌저자
		  	//String exp_option = prot.getPL_BI_EXP_THE_REC(); // 검증구분
		  	//String mgmt_num = prot.getPL_BI_MGMT_DATA_NUM(); //물성데이터표시명
		  	
		  	Vector v_option = new Vector();
			v_option.addElement(seach_pr_no);
			
			Vector result_v = graph.selectBasicGraphData(v_option);
		  	
			for(int j = 0; j < result_v.size(); j++){
		  		Graph_Data_Info g_data = (Graph_Data_Info)result_v.elementAt(j);	
		  		
		  		String seq = g_data.getPL_BGD_SEQ_NUM();
				String x_v = g_data.getPL_BGD_X_AX_VAL();
				String y_v = g_data.getPL_BGD_Y_AX_VAL();
				
				Fitting_Temp_Info temp = new Fitting_Temp_Info();
				temp.setPL_FTD_SEQ(seq);//데이터 번호
				temp.setPL_FTD_X_VAL(x_v); //X값
				temp.setPL_FTD_Y_VAL(y_v); //Y값
				temp.setPL_FBI_SEQ("-");//구간데이터
				temp.setPL_UI_ID(user_id); //사용자ID
				temp.setPL_IGBI_DATA_NUM(v_pr_no);//등급유력번호
		  		
				r_value = fitting.insertFittingTempData(temp);		  		
		  	}  	
		  	
		}	*/
		
		return r_value;
	}
	
	/**
	 * @MethodName  : checkFittingState
	 * @Date   : 2010. 07. 20 
	 * @MethodDescription : 사용자 ID로 된 진행중인 Fitting State 가 있는지의 여부 체크하는 메소드
	 * @param user_id
	 * @return
	 * @History  : - 
	 */
	public boolean checkFittingState(String user_id, String block_state){
		boolean r_value = false;
		int count = 0;
		DAO_FITTING_INFO fitting = new DAO_FITTING_INFO();
		
		count = fitting.checkFittingState(user_id, block_state);
		
		if(count > 0){
			r_value = true;
		}
		
		return r_value;
	}
	
	/**
	 * @MethodName  : insertFittingState
	 * @Date   : 2010. 07. 20 
	 * @MethodDescription : Fitting을 처리하는 상태값을 Insert 하는 메소드
	 * @param v_pro_no
	 * @param user_id
	 * @param block_seq
	 * @param block_state
	 * @return
	 * @History  : - 
	 */
	public boolean insertFittingState(String v_pro_no, String user_id, String block_seq, String block_state){
		boolean r_value = false;
		
		DAO_FITTING_INFO fitting = new DAO_FITTING_INFO();
		
		r_value = fitting.insertFittingState(v_pro_no, user_id, block_seq, block_state);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : updateFittingState
	 * @Date   : 2010. 07. 15 
	 * @MethodDescription : Fitting을 처리하는 상태값을 Update 하는 메소드
	 * @param v_pro_no
	 * @param user_id
	 * @param block_seq
	 * @param block_state
	 * @return
	 * @History  : - 
	 */
	public boolean updateFittingState(String v_pro_no, String user_id, String block_seq, String block_state){
		boolean r_value = false;
		
		DAO_FITTING_INFO fitting = new DAO_FITTING_INFO();
		
		r_value = fitting.updateFittingState(v_pro_no, user_id, block_seq, block_state);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : deleteFittingTempData
	 * @Date   : 2010. 07. 20 
	 * @MethodDescription : 사용자 ID로 된 데이터를 FittingTemp에서 지우는 메소드
	 * @param user_id
	 * @return
	 * @History  : - 
	 */
	public boolean deleteFittingTempData(String user_id){
		boolean r_value = false;
		
		DAO_FITTING_INFO fitting = new DAO_FITTING_INFO();
		
		r_value = fitting.deleteFittingTempData(user_id);		
		
		return r_value;
	}	
	/**
	 * @MethodName  : makeFittingTemp
	 * @Date   : 2010. 07. 15 
	 * @MethodDescription : Fitting을 위한 임시데이터를 이동시키는 메소드.
	 * @param v_pr_no
	 * @param user_id
	 * @param pd_info
	 * @return
	 * @History  : - 
	 */
	public boolean makeFittingTemp(String v_pr_no, String user_id, Vector<?> pd_info){
		boolean r_value = false;
		
		DAO_BASIC_GRAPH_INFO graph = new DAO_BASIC_GRAPH_INFO();
		DAO_FITTING_INFO fitting = new DAO_FITTING_INFO();
		for(int i = 0; i < pd_info.size(); i++){
		
			Properties_Assess_Search_List prot = (Properties_Assess_Search_List) pd_info.elementAt(i);
		  	String seach_pr_no = prot.getPL_BI_DATA_NUM();	 //물성번호 	
		  	//String artcl_no = prot.getPL_RAI_ARTCL_NUM(); //참고문헌 번호
		  	//String artcl_author = prot.getPL_RAI_ARTCL_AUTH_NAME(); //참고문헌저자
		  	//String exp_option = prot.getPL_BI_EXP_THE_REC(); // 검증구분
		  	//String mgmt_num = prot.getPL_BI_MGMT_DATA_NUM(); //물성데이터표시명
		  	
		  	Vector<String> v_option = new Vector<String>();
			v_option.addElement(seach_pr_no);
			
			Vector<?> result_v = graph.selectBasicGraphData(v_option);
		  	
			for(int j = 0; j < result_v.size(); j++){
		  		Graph_Data_Info g_data = (Graph_Data_Info)result_v.elementAt(j);	
		  		
		  		String seq = g_data.getPL_BGD_SEQ_NUM();
				String x_v = g_data.getPL_BGD_X_AX_VAL();
				String y_v = g_data.getPL_BGD_Y_AX_VAL();
				
				Fitting_Temp_Info temp = new Fitting_Temp_Info();
				temp.setPL_FTD_SEQ(seq);//데이터 번호
				temp.setPL_FTD_X_VAL(x_v); //X값
				temp.setPL_FTD_Y_VAL(y_v); //Y값
				temp.setPL_FBI_SEQ("-");//구간데이터
				temp.setPL_UI_ID(user_id); //사용자ID
				temp.setPL_IGBI_DATA_NUM(v_pr_no);//등급유력번호
		  		
				r_value = fitting.insertFittingTempData(temp);		  		
		  	}  	
		  	
		}	
		
		return r_value;
	}

	/**
	 * @MethodName  : makeVaildPropertyNum
	 * @Date   : 2010. 08. 16 
	 * @MethodDescription : 등급 유력 물성 정보 번호를 만들어내는 Business 메소드
	 * @param ref_no
	 * @return
	 * @History  : - 
	 */
	public String makeVaildPropertyNum(String ref_no){
		String returnValue = "";
		String org_Date = ref_no;
		String in_Year = ComUtil.getYearInDate(org_Date);	
		String numType = "V"; //VALID PROPERTY 일경우

		//번호를 받아오기 위한 SQL 문 실행
		DAO_ASSESS_INFO seq = new DAO_ASSESS_INFO();
		
		String db_year = seq.selectValidProSaveYearInfo();
		
		if(!db_year.equalsIgnoreCase(in_Year)){
			//update			
			seq.updateValidProSaveYear(in_Year);
			seq.resetValidSeq();			
		}
		//seq.insertSaveYear(in_Year);
		
		String sqlValue = seq.selectValidSeq();
		
		//번호 만들기
		int count = sqlValue.length();
		String zeroString = "0";
		String combineValue = "";
		for(int i = 5 ; i > count ; i--)
		{
			combineValue = combineValue + zeroString;
		}
		
		returnValue = numType + in_Year + combineValue + sqlValue;
		
		return returnValue;
	}
	
	/**
	 * @MethodName  : getPro_No_List
	 * @Date   : 2010. 08. 30 
	 * @MethodDescription : 물성정보리스트를 가져오는 메소드
	 * @param pd_info
	 * @return
	 * @History  : - 
	 */
	public String getPro_No_List(Vector<?> pd_info){
		String return_value = "-";
		int size = pd_info.size();
		
		for (int i = 0 ; i < size; i++) {
		  	Properties_Assess_Search_List prot = (Properties_Assess_Search_List) pd_info.elementAt(i);
		  	String seach_pr_no = prot.getPL_BI_DATA_NUM();
		  	//String seach_pr_no = prot.getPL_BI_MGMT_DATA_NUM();
		  	
		  	if(return_value.equalsIgnoreCase("-")){
		  		return_value = seach_pr_no;
		  	}else{
		  		return_value = return_value + ", "+seach_pr_no;
		  	}
		  	/*return_value = return_value + seach_pr_no;
		  	
		  	
		  	
		  	if(i > 1){
		  	 	if(i == size){
		  	 		return_value = return_value + seach_pr_no;
		  	 	}else{
		  	 		return_value = return_value + seach_pr_no + ","; 		
		  	 	}
		  	}else{
		  		return_value = seach_pr_no;
		  	}*/		  	
		}
		return return_value;
	}
	
	/**
	 * @MethodName  : getPro_Mgmt_List
	 * @Date   : 2011. 7. 13. 
	 * @MethodDescription : 물성관리번호 리스트를 만들어내는 메소드
	 * @param pd_info
	 * @return
	 * @History  : - 
	 */
	public String getPro_Mgmt_List(Vector<?> pd_info){
		String return_value = "-";
		int size = pd_info.size();
		
		for (int i = 0 ; i < size; i++) {
		  	Properties_Assess_Search_List prot = (Properties_Assess_Search_List) pd_info.elementAt(i);
		  	//String seach_pr_no = prot.getPL_BI_DATA_NUM();
		  	String seach_pr_no = prot.getPL_BI_MGMT_DATA_NUM();
		  	
		  	if(return_value.equalsIgnoreCase("-")){
		  		return_value = seach_pr_no;
		  	}else{
		  		return_value = return_value + ", "+seach_pr_no;
		  	}
		  	/*return_value = return_value + seach_pr_no;
		  	
		  	
		  	
		  	if(i > 1){
		  	 	if(i == size){
		  	 		return_value = return_value + seach_pr_no;
		  	 	}else{
		  	 		return_value = return_value + seach_pr_no + ","; 		
		  	 	}
		  	}else{
		  		return_value = seach_pr_no;
		  	}*/		  	
		}
		return return_value;
	}
	
	/**
	 * @MethodName  : getPro_Artcl_List
	 * @Date   : 2011. 7. 13. 
	 * @MethodDescription : 참고문헌 리스트를 만들어 내는 메소드
	 * @param pd_info
	 * @return
	 * @History  : - 
	 */
	public String getPro_Artcl_List(Vector<?> pd_info){
		
		String str_artcl_list = "";
		String str_comma = ", ";
		String str_enter = "<br>";
		String str_page = ", pp.";
		String str_fr_brace = "[";
		String str_ed_brace = "] ";
		String str_col = " : ";
		
		int size = pd_info.size();
		
		for (int i = 0 ; i < size; i++) {
		  	Properties_Assess_Search_List prot = (Properties_Assess_Search_List) pd_info.elementAt(i);
		  	String search_artcl_no = prot.getPL_RAI_ARTCL_NUM();
		  	String search_mgmt_no = prot.getPL_BI_MGMT_DATA_NUM();
		  	
		  	Vector<String> v_option = new Vector<String>();
			v_option.addElement(search_artcl_no);
			
			DAO_REF_ARTICLE_INFO dao_info = new DAO_REF_ARTICLE_INFO();
			
			Article_Info info = new Article_Info();
			
			info = dao_info.selectArticleInfo(v_option);
			int j = i+1;
			//번호 시작
			str_artcl_list = str_artcl_list + str_fr_brace + j + str_ed_brace;
			//관리 번호
			str_artcl_list = str_artcl_list + search_mgmt_no + str_col;
			//논문 제목
			str_artcl_list = str_artcl_list + info.getPL_RAI_ARTCL_TITLE();
			//논문 저자
			str_artcl_list = str_artcl_list + str_comma + info.getPL_RAI_ARTCL_AUTH_ENAME();
			//저널명
			str_artcl_list = str_artcl_list + str_comma + info.getPL_RAI_JOUR_TITLE();
			//볼륨
			str_artcl_list = str_artcl_list + str_comma + info.getPL_RAI_JOUR_VOL();
			//년도
			str_artcl_list = str_artcl_list + str_comma + info.getPL_RAI_JOUR_YEAR();
			//시작페이지
			str_artcl_list = str_artcl_list + str_page + info.getPL_RAI_ARTCL_ST_PAGE();
			
			if(i == size-1){
				//엔터X
				return str_artcl_list;
			}else{
				//엔터
				str_artcl_list = str_artcl_list + str_enter;	
			}					  	
		}	
		
		return str_artcl_list;
	}
	
	//public boolean insertINF_GRD_BASIC_INFO()
	
/*	public String makeValidateID(){
		
		String val_id = "";
		String returnValue = "";
		String org_Date = ref_no;
		String in_Year = ComUtil.getYearInDate(org_Date);	
		String numType = "B"; //BASIC PROPERTY 일경우

		//번호를 받아오기 위한 SQL 문 실행
		DAO_BASIC_PROPERTY_INFO seq = new DAO_BASIC_PROPERTY_INFO();
		String sqlValue = seq.selectPropertySeq();		
		
		//번호 만들기
		int count = sqlValue.length();
		String zeroString = "0";
		String combineValue = "";
		for(int i = 5 ; i > count ; i--)
		{
			combineValue = combineValue + zeroString;
		}
		
		returnValue = numType + in_Year + combineValue + sqlValue;
		
		return returnValue;
		return val_id;
		
	}*/
}
