package nfri.dcpp.properties.business;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import nfri.dcpp.com.util.ComUtil;
import nfri.dcpp.properties.db.DAO_BASIC_PROPERTY_INFO;
import nfri.dcpp.properties.db.DAO_REF_ARTICLE_INFO;
import nfri.dcpp.properties.model.Article_Info;
import nfri.dcpp.properties.model.Properties_Basic_Info;
import nfri.dcpp.properties.model.Properties_Equation_Get_DbInfo;
import nfri.dcpp.properties.model.Properties_Equation_Info;
import nfri.dcpp.properties.model.Properties_Save_Info;

/**
 *
 * @Project : dcpp_web
 * @Title : Ctr_Property_Info_Process.java
 * @Description : 물성정보와 관련된 처리를 하는 비즈니스 로직 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 02
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class Ctr_Property_Info_Process {
	
	public Ctr_Property_Info_Process(){
		
	}
	
	/**
	 * @MethodName : selectPropertyList
	 * @Desc : 초기 화면에 보여줄 물성 통합 정보를 가져오는 메소드
	 * @return
	 */
	public Vector<?> selectPropertyList(String oCol, String oDir){
		Vector<?> vecList = new Vector<Object>();
		DAO_BASIC_PROPERTY_INFO info = new DAO_BASIC_PROPERTY_INFO();
		
		//String oCol = request.getParameter("_oCol");
		//String oDir = request.getParameter("_oDir");
		
		String sql_option = getOrderSql(oCol, oDir);

		vecList = info.selectProperyList(sql_option);
		return vecList;
	}
	
	public Vector<?> selectPropertyInfo(String pr_no){
		Vector<?> vecList = new Vector<Object>();
		DAO_BASIC_PROPERTY_INFO info = new DAO_BASIC_PROPERTY_INFO();
		
		//String oCol = request.getParameter("_oCol");
		//String oDir = request.getParameter("_oDir");
		
		String sql_option = " AND BASIC.PL_BI_DATA_NUM = " + ComUtil.makeEqualOption(pr_no);

		vecList = info.selectProperyList(sql_option);
		return vecList;
	}
	
	public String getOrderSql(String param_oCol, String param_oDir) {		
		String _oDir = "ASC";
		String _oCol = param_oCol;
		
		//System.out.println("oDir : " + param_oDir);
		
		if("desc".equals(param_oDir)) {
			_oDir = "DESC";
		}
		if("pr_no".equals(_oCol)) {
			return "ORDER BY BASIC.PL_BI_DATA_NUM" + " " + _oDir;				
		}
		else if("assess_state".equals(_oCol))
		{
			return "ORDER BY BASIC.PL_BI_INSERT_FLAG" + " " + _oDir;
		}
		else{
			return "ORDER BY BASIC.PL_BI_DATA_NUM" + " " + _oDir;
		}
	}
	
	/**
	 * @MethodName : selectViewBasicPropertyInfo
	 * @Desc :기본 정보 보기 화면에 보여줄 상세데이터를 가져오는 메소드 
	 * @param pr_no
	 * @return
	 */
	public Properties_Basic_Info selectViewPropertySpecInfo(String pr_no){
		DAO_BASIC_PROPERTY_INFO info = new DAO_BASIC_PROPERTY_INFO();
		
		Properties_Basic_Info return_value = info.selectPropertySpecInfo(pr_no);
		Ctr_Option_Process ctr_option = new Ctr_Option_Process();
		
		//대분류
		return_value.setPL_BI_TOP_BRANCH(ctr_option.getExpName(return_value.getPL_BI_TOP_BRANCH()));
		//데이터분류
		return_value.setPL_BI_DATA_BRANCH(ctr_option.getExpName(return_value.getPL_BI_DATA_BRANCH()));
		//주프로세스
		return_value.setPL_BI_MAIN_PROC(ctr_option.getExpName(return_value.getPL_BI_MAIN_PROC()));
		//부프로세스
		return_value.setPL_BI_SUB_PROC(ctr_option.getExpName(return_value.getPL_BI_SUB_PROC()));
		//충돌방식
		return_value.setPL_BI_IMP_CLASS(ctr_option.getExpName(return_value.getPL_BI_IMP_CLASS()));
		//검증구분
		return_value.setPL_BI_EXP_THE_REC(ctr_option.getExpName(return_value.getPL_BI_EXP_THE_REC()));
		//관리번호
		return_value.setPL_BI_MGMT_DATA_NUM(return_value.getPL_BI_MGMT_DATA_NUM());
		//입력날짜
		return_value.setPL_BI_INSERT_DATE(return_value.getPL_BI_INSERT_DATE());
		
		return_value.setPL_BI_EXPRESSION(return_value.getPL_BI_EXPRESSION());	
		
		return return_value;//info.selectPropertySpecInfo(pr_no);
	}
	
	public Vector<Properties_Basic_Info> getPropertyListInfo(String pr_st_no, String pr_ed_no){
		//정보 하나에 물성정보번호와 나머지 정보를 담는다.
		DAO_BASIC_PROPERTY_INFO dao_basic = new DAO_BASIC_PROPERTY_INFO();
		
		Vector<?> basic_list = new Vector<Object>();
		
		Vector<Properties_Basic_Info> re_list = new Vector<Properties_Basic_Info>();
		
		//1. 전체 물성정보의 번호와 표현식을 가져온다.
		//System.out.println("1. 전체 물성정보의 번호와 표현식을 가져온다.");
		
		basic_list = dao_basic.selectAllPropertyNumExp(pr_st_no, pr_ed_no);
		
		//System.out.println("가져온 물성 개수 : " + basic_list.size());
		
		//2. 가져온 물성정보 하나에 대해 다시 Equation 정보를 가져온다.
		//int size = basic_list.size();
		for(int i = 0; i < basic_list.size(); i++){
			//System.out.println(i + "번째 시작");
			Properties_Basic_Info basic_info = (Properties_Basic_Info)basic_list.get(i);
			String basic_pr_no = basic_info.getPL_BI_DATA_NUM();
			String basic_pr_exp = basic_info.getPL_BI_EXPRESSION();
			String comb_exp = selectEquationData(basic_pr_no);
			
			//System.out.println("물성정보 : " + basic_pr_no);
			//System.out.println("원래 표현식 : " + basic_pr_exp);
			//System.out.println("조합 표현식 : " + comb_exp);
			
			/*Properties_Basic_Info re_info = new Properties_Basic_Info();
			re_info.setPL_BI_DATA_NUM(basic_pr_no);
			re_info.setPL_BI_EXPRESSION(basic_pr_exp);
			re_info.setPL_BI_COMB_EXP(comb_exp);
			re_list.addElement(re_info);*/
			
			if(!comb_exp.equalsIgnoreCase(basic_pr_exp)){
				Properties_Basic_Info re_info = new Properties_Basic_Info();
				re_info.setPL_BI_DATA_NUM(basic_pr_no);
				re_info.setPL_BI_EXPRESSION(basic_pr_exp);
				re_info.setPL_BI_COMB_EXP(comb_exp);
				re_list.addElement(re_info);
			}			
		}
				
		//4. 다시 하나의 객체에 담아서 벡터로 반환한다.	
		
		return re_list;
		
	}
	
	public boolean updateAllExpressionChange(String pr_st_no, String pr_ed_no){
		boolean r_value = false;
		DAO_BASIC_PROPERTY_INFO dao_basic = new DAO_BASIC_PROPERTY_INFO();
		
		Vector<?> basic_list = new Vector<Object>();
		
		Vector<?> re_list = new Vector<Object>();
		
		//1. 전체 물성정보의 번호와 표현식을 가져온다.
		//System.out.println("1. 전체 물성정보의 번호와 표현식을 가져온다.");
		
		basic_list = dao_basic.selectAllPropertyNumExp(pr_st_no, pr_ed_no);
		
		System.out.println("가져온 물성 개수 : " + basic_list.size());
		
		//2. 가져온 물성정보 하나에 대해 다시 Equation 정보를 가져온다.
		//int size = basic_list.size();
		for(int i = 0; i < basic_list.size(); i++){
			//System.out.println(i + "번째 시작");
			Properties_Basic_Info basic_info = (Properties_Basic_Info)basic_list.get(i);
			String basic_pr_no = basic_info.getPL_BI_DATA_NUM();
			String basic_pr_exp = basic_info.getPL_BI_EXPRESSION();
			String comb_exp = selectEquationData(basic_pr_no);
			
			//System.out.println("물성정보 : " + basic_pr_no);
			//System.out.println("원래 표현식 : " + basic_pr_exp);
			//System.out.println("조합 표현식 : " + comb_exp);
			
			/*Properties_Basic_Info re_info = new Properties_Basic_Info();
			re_info.setPL_BI_DATA_NUM(basic_pr_no);
			re_info.setPL_BI_EXPRESSION(basic_pr_exp);
			re_info.setPL_BI_COMB_EXP(comb_exp);
			re_list.addElement(re_info);*/
			
			r_value = dao_basic.updatePropertyExpression(comb_exp, basic_pr_no);
			
			if(r_value == false){
				System.out.println("업데이트 실패 : " + basic_pr_no);
			}else{
				System.out.println("업데이트 성공 !! ");
			}
			/*if(!comb_exp.equalsIgnoreCase(basic_pr_exp)){
				Properties_Basic_Info re_info = new Properties_Basic_Info();
				re_info.setPL_BI_DATA_NUM(basic_pr_no);
				re_info.setPL_BI_EXPRESSION(basic_pr_exp);
				re_info.setPL_BI_COMB_EXP(comb_exp);
				re_list.addElement(re_info);
			}*/			
		}
		
		
		return r_value;
	}
	
	/**
	 * @MethodName : selectEquationData
	 * @Desc : 물성 정보에 대한 반응식 정보를 가져오는 메소드
	 * @param pr_no
	 * @return
	 */
	public String selectEquationData(String pr_no){
		String value = "";
		Vector<?> vecList = new Vector<Object>();
		DAO_BASIC_PROPERTY_INFO info = new DAO_BASIC_PROPERTY_INFO();
		vecList = info.selectEquationList(pr_no);
		value = makeEquationExpress(vecList);
		return value;
	}
	
	/**
	 * @MethodName  : selectEquationInfo
	 * @Date   : 2012. 8. 1. 
	 * @MethodDescription : Equation 정보 수정을 위한 정보가져오기
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public Vector<?> selectEquationInfo(String pr_no){
		Vector<?> vecList = new Vector<Object>();
		DAO_BASIC_PROPERTY_INFO info = new DAO_BASIC_PROPERTY_INFO();
		vecList = info.selectEquationList(pr_no);
		return vecList;
	}
	
	/**
	 * @MethodName  : updateEquationInfo
	 * @Date   : 2012. 8. 30. 
	 * @MethodDescription : Equation 정보를 업데이트 하는 메소드
	 * @param request
	 * @return
	 * @History  : - 
	 */
	public boolean updateEquationInfo(HttpServletRequest request){
		boolean r_value = false;
		
		String property_num = request.getParameter("pr_no");
		String flag = request.getParameter("flag");
		String new_part_seq = request.getParameter("seq");
		//System.out.println("pr_no : " + property_num);
		//System.out.println("flag : " + flag);
		//System.out.println("new_part_seq : " + new_part_seq);
		// 반응식 정보 파트
		// 입자 이름
		//String new_part_no = request.getParameter("new_part_no");
		// 입자 번호
		//String new_part_id = request.getParameter("new_part_id");
		// 이온화
		//String new_part_chg = request.getParameter("new_part_chg");
		// 전자배치
		//String new_part_elc = request.getParameter("new_part_elc");
		// 미세구조
		//String new_part_oth = request.getParameter("new_part_oth");
		// 파트옵션(->, +)
		//String new_part_seq = request.getParameter("new_part_seq");	
		//String addcode_id = request.getParameter("addcode_id");
		
		String [] part_no = request.getParameterValues("part_no");
		// 입자 번호
		String [] part_id = request.getParameterValues("part_id");
		// 이온화
		String [] part_chg = request.getParameterValues("part_chg");
		// 전자배치
		String [] part_elc = request.getParameterValues("part_elc");
		// 미세구조
		String [] part_oth = request.getParameterValues("part_oth");
		// 파트옵션(->, +)
		String part_sp_option = request.getParameter("part_sp_option");	
		
		int i = Integer.parseInt(new_part_seq);
		//Vector equa_info = new Vector();	
		Properties_Equation_Info equa_info = new Properties_Equation_Info();
		equa_info.setPL_BI_DATA_NUM(property_num);
		equa_info.setPL_CPBI_ELE_NUM(ComUtil.isNullToEmptyString(part_id[i]));
		equa_info.setPL_BEI_SEQ(Integer.toString(i+1));
		equa_info.setPL_BEI_CHG_STATE(ComUtil.isNullToEmptyString(part_chg[i]));
		equa_info.setPL_BEI_ELC_STATE(ComUtil.isNullToEmptyString(part_elc[i]));
		equa_info.setPL_BEI_OTH_STRUC(ComUtil.isNullToEmptyString(part_oth[i]));
		equa_info.setPL_CI_ID(part_sp_option);
		
		//System.out.println("SEQ : " + Integer.toString(i+1));
		//System.out.println("ELE_NUM["+ i + "] : " + ComUtil.convertKorean(ComUtil.isNullToEmptyString(part_id[i])));
		//System.out.println("part_chg["+ i + "] : " + ComUtil.isNullToEmptyString(part_chg[i]));
		//System.out.println("part_elc["+ i + "] : " + ComUtil.isNullToEmptyString(part_elc[i]));
		//System.out.println("part_oth["+ i + "] : " + ComUtil.isNullToEmptyString(part_oth[i]));
		//System.out.println("update : part_sp_option : " + part_sp_option);
		
		DAO_BASIC_PROPERTY_INFO basic_info = new DAO_BASIC_PROPERTY_INFO();
		
		/*if("1".equalsIgnoreCase(flag)){ //수정
			System.out.println("수정");
			r_value = basic_info.updateEquationInfo(equa_info);	
		}else if("2".equalsIgnoreCase(flag)){ //입력
			System.out.println("입력");
			r_value = basic_info.insertEquationInfo(equa_info);
		}*/
		r_value = basic_info.updateEquationInfo(equa_info);	
		
		if(r_value==true){
			String exp = ComUtil.convertKorean(ComUtil.isNullToDashString(request.getParameter("pr_expression")));
			//System.out.println("exp : " + exp);
			r_value = basic_info.updatePropertyExpression(exp, property_num);
		}
		
		if(i == 4){ //첨부코드 업데이트
			r_value = basic_info.updateEquationAddCode(property_num, part_sp_option);
		}
		
		return r_value;

	}
	
	/**
	 * @MethodName  : insertEquationInfo
	 * @Date   : 2012. 8. 2. 
	 * @MethodDescription : Equation 정보를 입력 하는 메소드
	 * @param request
	 * @return
	 * @History  : - 
	 */
	public boolean insertEquationInfo(HttpServletRequest request){
		boolean r_value = false;
		
		String property_num = request.getParameter("pr_no");
		String flag = request.getParameter("flag");
		String new_part_seq = request.getParameter("seq");
		//System.out.println("pr_no : " + property_num);
		//System.out.println("flag : " + flag);
		//System.out.println("new_part_seq : " + new_part_seq);
		//System.out.println("pr_no : " + property_num);
		// 반응식 정보 파트
		// 입자 이름
		//String new_part_no = request.getParameter("new_part_no");
		// 입자 번호
		//String new_part_id = request.getParameter("new_part_id");
		// 이온화
		//String new_part_chg = request.getParameter("new_part_chg");
		// 전자배치
		//String new_part_elc = request.getParameter("new_part_elc");
		// 미세구조
		//String new_part_oth = request.getParameter("new_part_oth");
		// 파트옵션(->, +)			
		//String addcode_id = request.getParameter("addcode_id");
		
		String [] part_no = request.getParameterValues("part_no");
		// 입자 번호
		String [] part_id = request.getParameterValues("part_id");
		// 이온화
		String [] part_chg = request.getParameterValues("part_chg");
		// 전자배치
		String [] part_elc = request.getParameterValues("part_elc");
		// 미세구조
		String [] part_oth = request.getParameterValues("part_oth");
		// 파트옵션(->, +)
		String part_sp_option = request.getParameter("part_sp_option");	
		
		int i = Integer.parseInt(new_part_seq);
		//Vector equa_info = new Vector();	
		Properties_Equation_Info equa_info = new Properties_Equation_Info();
		equa_info.setPL_BI_DATA_NUM(property_num);
		equa_info.setPL_CPBI_ELE_NUM(ComUtil.isNullToEmptyString(part_id[i]));
		equa_info.setPL_BEI_SEQ(Integer.toString(i+1));
		equa_info.setPL_BEI_CHG_STATE(ComUtil.isNullToEmptyString(part_chg[i]));
		equa_info.setPL_BEI_ELC_STATE(ComUtil.isNullToEmptyString(part_elc[i]));
		equa_info.setPL_BEI_OTH_STRUC(ComUtil.isNullToEmptyString(part_oth[i]));
		equa_info.setPL_CI_ID(part_sp_option);
		
		//System.out.println("SEQ : " + Integer.toString(i+1));
		//System.out.println("ELE_NUM["+ i + "] : " + ComUtil.convertKorean(ComUtil.isNullToEmptyString(part_id[i])));
		//System.out.println("part_chg["+ i + "] : " + ComUtil.isNullToEmptyString(part_chg[i]));
		//System.out.println("part_elc["+ i + "] : " + ComUtil.isNullToEmptyString(part_elc[i]));
		//System.out.println("part_oth["+ i + "] : " + ComUtil.isNullToEmptyString(part_oth[i]));
		//System.out.println("insert : part_sp_option : " + part_sp_option);
		
		DAO_BASIC_PROPERTY_INFO basic_info = new DAO_BASIC_PROPERTY_INFO();

		//System.out.println("입력");
		
		r_value = basic_info.insertEquationInfo(equa_info);
		
		if(r_value==true){
			String exp = ComUtil.convertKorean(ComUtil.isNullToDashString(request.getParameter("pr_expression")));
			//System.out.println("exp : " + exp);
			r_value = basic_info.updatePropertyExpression(exp, property_num);
		}
		
		return r_value;
	}
	
	/**
	 * @MethodName : selectArticleInfo
	 * @Desc : 물성 정보에 대한 논문 파일 정보를 가져오는 메소드
	 * @param pr_no
	 * @return
	 */
	public Article_Info selectArticleInfo(String pr_no){
		Vector<String> v_option = new Vector<String>();
		v_option.addElement(pr_no);
		
		DAO_REF_ARTICLE_INFO info = new DAO_REF_ARTICLE_INFO();
		
		return info.selectArticleFileInfo(v_option);
	}
	
	/**
	 * @MethodName : insertPropertyInfo
	 * @Desc : 물성 정보를 DB에 입력하는 Business 메소드
	 * @param request
	 * @return
	 */
	//public String insertPropertyInfo(String pr_ref_artcl_no, String pr_insert_user, String tb_option, String db_option, String mp_option, String sp_option, String ic_option, String et_option, String [] part_no, String [] part_id, String [] part_chg, String [] part_elc, String [] part_oth, String part_sp_option, String func)
	public String insertPropertyInfo(HttpServletRequest request)
	{	
		String value = "";
		boolean r_value = false;
		// 물성 기본 정보 파트
		// 물성 정보 번호
		//String pr_no = request.getParameter("pr_no");
		// 참고 문헌 번호
		String pr_ref_artcl_no = request.getParameter("pr_ref_artcl_no");
		// 입력자
		String pr_insert_user = request.getParameter("user_id");
		// 대분류
		String tb_option = request.getParameter("tb_option");
		// 데이터분류
		String db_option = request.getParameter("db_option");
		// 주프로세스
		String mp_option = request.getParameter("mp_option");
		// 부프로세스
		String sp_option = request.getParameter("sp_option");
		// 충돌 방식
		String ic_option = request.getParameter("ic_option");
		// 검증 구분
		String et_option = request.getParameter("et_option");
		
		// 반응식 정보 파트
		// 입자 이름
		String [] part_no = request.getParameterValues("part_no");
		// 입자 번호
		String [] part_id = request.getParameterValues("part_id");
		// 이온화
		String [] part_chg = request.getParameterValues("part_chg");
		// 전자배치
		String [] part_elc = request.getParameterValues("part_elc");
		// 미세구조
		String [] part_oth = request.getParameterValues("part_oth");
		// 파트옵션(->, +)
		String part_sp_option = request.getParameter("part_sp_option");	
		//String addcode_id = request.getParameter("addcode_id");
		//함수식
		String func = ComUtil.convertKorean(ComUtil.isNullToDashString(request.getParameter("pr_expression")));
		//입력날짜
		String in_date = ComUtil.getTimeNow();
		
		//1. 물성정보 번호 만들기
		//현재시간 알아내기
		String now_time = ComUtil.getTimeNow();
		String property_num = makePropertyNum(now_time);
				
		Properties_Save_Info info = new Properties_Save_Info();
		//2. 물성 기본 정보 입력
		info.setPL_BI_DATA_NUM(property_num);//물성정보번호
		info.setPL_RAI_ARTCL_NUM(pr_ref_artcl_no);//참고문헌번호 저장		
		info.setPL_BI_TOP_BRANCH(tb_option);//대분류
		info.setPL_BI_DATA_BRANCH(db_option);//데이터분류
		info.setPL_BI_IMP_CLASS(ic_option);//충돌방식
		info.setPL_BI_MAIN_PROC(mp_option);//주프로세스
		info.setPL_BI_SUB_PROC(sp_option);//부프로세스
		info.setPL_BI_EXP_THE_REC(et_option);//검증구분
		info.setPL_BI_INSERT_USER(pr_insert_user);//입력자 저장
		info.setPL_BI_INSERT_FLAG("I");//입력플래그 기본값 : "I"
		info.setPL_BI_EXPRESSION(func);//함수식 입력
		info.setPL_BI_INSERT_DATE(in_date);//입력날짜
		
		//4. 기본 반응식 정보 입력
		
		Vector<Properties_Equation_Info> equa_info = new Vector<Properties_Equation_Info>();	
		//System.out.println("part_no.length : " + part_no.length);
		for(int i = 0 ; i < part_no.length ; i++){
			if(!ComUtil.isNull(part_id[i])){
				Properties_Equation_Info equa = new Properties_Equation_Info();
				equa.setPL_BI_DATA_NUM(property_num);
				equa.setPL_CPBI_ELE_NUM(ComUtil.convertKorean(ComUtil.isNullToEmptyString(part_id[i])));
				equa.setPL_BEI_SEQ(Integer.toString(i+1));
				equa.setPL_BEI_CHG_STATE(ComUtil.isNullToEmptyString(part_chg[i]));
				equa.setPL_BEI_ELC_STATE(ComUtil.isNullToEmptyString(part_elc[i]));
				equa.setPL_BEI_OTH_STRUC(ComUtil.isNullToEmptyString(part_oth[i]));
				equa_info.addElement(equa);
				
				//System.out.println("SEQ : " + i);
				//System.out.println("ELE_NUM["+ i + "] : " + ComUtil.convertKorean(ComUtil.isNullToEmptyString(part_id[i])));
				//System.out.println("part_chg["+ i + "] : " + ComUtil.isNullToEmptyString(part_chg[i]));
				//System.out.println("part_elc["+ i + "] : " + ComUtil.isNullToEmptyString(part_elc[i]));
				//System.out.println("part_oth["+ i + "] : " + ComUtil.isNullToEmptyString(part_oth[i]));
			}
		}	

		info.setPL_EQUA_INFO(equa_info);
		info.setPL_CI_ID(part_sp_option);
		
		DAO_BASIC_PROPERTY_INFO basic_info = new DAO_BASIC_PROPERTY_INFO();
		
		r_value = basic_info.insertPropertyInfo(info);
		
		if(r_value == true){
			value = property_num;
		}else{
			value = "false";
		}
		
		return value;
	}
	
	
	/**
	 * @MethodName : insertInfoComplete
	 * @Desc : 물성 데이터 입력을 완료 하는 메소드
	 * @param pr_no
	 * @return
	 */
	public boolean insertInfoComplete(String pr_no){
		boolean r_value = false;
		
		DAO_BASIC_PROPERTY_INFO basic_info = new DAO_BASIC_PROPERTY_INFO();
		//System.out.println("------------- New ");
		//System.out.println("입력시간 : " + ComUtil.getTimeNow());
		String new_pr_no = makeNewPropertyName(pr_no);
		//System.out.println("물성번호 : "+pr_no);
		//System.out.println("관리번호 : "+new_pr_no);
		
		r_value = basic_info.insertDataComplete(pr_no, new_pr_no);
		//System.out.println("------------- End ");
		return r_value;
	}
	
	/**
	 * @MethodName : makeNewPropertyName
	 * @Desc : 물성 데이터 입력을 완료하고 새로운 물성 데이터 관리 번호를 만드는 메소드
	 *         "저자+출판년도+a" 형태
	 * @param pr_no
	 * @return
	 */
	public String makeNewPropertyName(String pr_no){
		String returnValue = "";
		
		//1. 참고문헌 조회 후 저자와 년도 가져오기		
		Vector<String> sqlOption = new Vector<String>();
		sqlOption.add(pr_no);
		
		DAO_REF_ARTICLE_INFO dao_article = new DAO_REF_ARTICLE_INFO();
		Article_Info atcl_info = dao_article.selectArticleAuthYear(sqlOption);
		
		//저자의 성 가져오기
		String atcl_user = atcl_info.getPL_RAI_ARTCL_AUTH_FNAME();
		//논문의 출판년도 가져오기
		String atcl_year = atcl_info.getPL_RAI_JOUR_YEAR();
		
		//
		String article_user = atcl_user+atcl_year;
		
		//2. 참고 문헌 정보를 가지고 기본 정보 테이블에서 해당 정보와 같은 앞부분을 가진 것이 몇개인지 조회
		DAO_BASIC_PROPERTY_INFO basic_info = new DAO_BASIC_PROPERTY_INFO();
		int count = basic_info.selectNumberCountInfo(article_user);
		//System.out.println("------------- New ");
		//System.out.println("참고문헌 count : " + count);
		//DAO_REF_ARTICLE_INFO dao_article = new DAO_REF_ARTICLE_INFO();
		//int artcl_count = dao_article.selectArticleCountInfo(sqlOption);
		String alphabetString = ComUtil.getAlphabetList(count);
		//System.out.println("alphabet count : " + alphabetString);
		returnValue = article_user + alphabetString;
		
		return returnValue;
	}
	
	/**
	 * @MethodName : makePropertyNum
	 * @Desc : 물성 정보 번호를 만들어내는 Business 메소드
	 * @param ref_no
	 * @return
	 */
	public String makePropertyNum(String date){
		String returnValue = "";
		//String org_Date = ref_no;
		String in_Year = ComUtil.getYearInDate(date);	
		String numType = "B"; //BASIC PROPERTY 일경우		

		//번호를 받아오기 위한 SQL 문 실행
		DAO_BASIC_PROPERTY_INFO seq = new DAO_BASIC_PROPERTY_INFO();
		
		
		String db_year = seq.selectPropertySaveYearInfo();
		
		if(!db_year.equalsIgnoreCase(in_Year)){
			//update			
			seq.updatePropertySaveYear(in_Year);
			seq.resetPropertySeq();			
		}
		//seq.insertPropertySaveYear(in_Year);
		
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
	}
	
	/**
	 * @MethodName : makeEquationExpress
	 * @Desc : 물성정보 표현식을 만들어 내는 Business 메소드
	 * @param equa
	 * @return
	 */
	public String makeEquationExpress(Vector<?> equa){
		String part_express = "";
		
		for(int i = 0 ; i < equa.size(); i++){
			
			Properties_Equation_Get_DbInfo equa_Info = (Properties_Equation_Get_DbInfo)equa.get(i);
			
			String str_Pl_Ci_Id = equa_Info.getPL_CI_ID();
			String str_Symbol = equa_Info.getPL_CPBI_ELE_SYMBOL();
			String str_Chg_State = equa_Info.getPL_BEI_CHG_STATE();
			String str_Elc_State = equa_Info.getPL_BEI_ELC_STATE();
			String str_Oth_Struc = equa_Info.getPL_BEI_OTH_STRUC();
			int str_Part_Seq = Integer.parseInt(equa_Info.getPL_BEI_SEQ());
			
			
			/*if(i==1 || i==3 || i==5){
				part_express = part_express + " + ";
			}else if(i==2){
				part_express = part_express + " -&gt; ";
			}else if(i==4){
				part_express = part_express + " " + str_Pl_Ci_Id + " ";
			}*/
			if(str_Part_Seq==2 || str_Part_Seq==4 || str_Part_Seq==6){
				part_express = part_express + " + ";
			}else if(str_Part_Seq==3){
				part_express = part_express + " -&gt; ";
			}else if(str_Part_Seq==5){
				part_express = part_express + " " + str_Pl_Ci_Id + " ";
			}
		
			part_express = part_express + str_Symbol;
			
			//이온화 비교
			if(!str_Chg_State.equalsIgnoreCase("NULL")){ //이온화 값이 있으면,
				//String str_f_sup = "<sup>";
				//String str_e_sup = "</sup>";
				//String str_sup_chg = ComUtil.getSupString(str_Chg_State);//str_f_sup + str_Chg_State + str_e_sup;				
				part_express = part_express + ComUtil.getSupString(str_Chg_State);//str_sup_chg;
			}
			
			//전자배치 비교
			if(!str_Elc_State.equalsIgnoreCase("NULL")){ //전자배치 값이 있으면,
				//미세구조 비교
				if(!str_Oth_Struc.equalsIgnoreCase("NULL")){ //미세구조 값이 있으면,
					part_express = part_express + "(" + str_Elc_State;
					part_express = part_express + "," + str_Oth_Struc;
					part_express = part_express + ")";
				}else{ //미세구조 값이 없으면,
					part_express = part_express + "(" + str_Elc_State;
					part_express = part_express + ")";
				}
			}else{ //전자배치 값이 없으면,
				if(!str_Oth_Struc.equalsIgnoreCase("NULL")){ //미세구조 값이 있으면
					part_express = part_express + "(" + str_Oth_Struc;
					part_express = part_express + ")";
				}
			}			
		}
		
		//System.out.println("조합식 : " + part_express);
		
		return part_express;
	}
	
	/**
	 * @MethodName  : deletePropertyInfo
	 * @Date   : 2012. 1. 13. 
	 * @MethodDescription : 플라즈마 물성정보 삭제하는 메소드
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean deletePropertyInfo(String pr_no){
		boolean return_value = false;
		
		DAO_BASIC_PROPERTY_INFO basic_info = new DAO_BASIC_PROPERTY_INFO();
		
		return_value = basic_info.deletePropertyInfo(pr_no);
		
		return return_value;
	}
	
	/**
	 * @MethodName  : modifyPropertyFlag
	 * @Date   : 2012. 1. 13. 
	 * @MethodDescription : 플라즈마 물성정보 입력 플래그 업데이트 메소드
	 * @param flag
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean modifyPropertyFlag(String flag, String pr_no){
		boolean return_value = false;
		
		DAO_BASIC_PROPERTY_INFO basic_info = new DAO_BASIC_PROPERTY_INFO();
		
		return_value = basic_info.modifyPropertyInsertFlag(flag, pr_no);
		
		return return_value;
	}
	
	/**
	 * @MethodName  : modifyPropertyDB
	 * @Date   : 2012. 1. 20. 
	 * @MethodDescription : 플라즈마 물성 정보의 데이터구분 값을 변경하는 메소드
	 * @param m_value
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean modifyPropertyDB(String m_value, String pr_no){
		boolean return_value = false;
		
		DAO_BASIC_PROPERTY_INFO basic_info = new DAO_BASIC_PROPERTY_INFO();
		
		return_value = basic_info.modifyPropertyDB(m_value, pr_no);
		
		return return_value;
	}
	
	/**
	 * @MethodName  : modifyPropertyMP
	 * @Date   : 2012. 1. 20. 
	 * @MethodDescription : 플라즈마 물성 정보의 주프로세스 값을 변경하는 메소드
	 * @param m_value
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean modifyPropertyMP(String m_value, String pr_no){
		boolean return_value = false;
		
		DAO_BASIC_PROPERTY_INFO basic_info = new DAO_BASIC_PROPERTY_INFO();
		
		return_value = basic_info.modifyPropertyMP(m_value, pr_no);
		
		return return_value;
	}
	
	/**
	 * @MethodName  : modifyPropertySP
	 * @Date   : 2012. 1. 20. 
	 * @MethodDescription : 플라즈마 물성 정보의 부프로세스 값을 변경하는 메소드
	 * @param m_value
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean modifyPropertySP(String m_value, String pr_no){
		boolean return_value = false;
		
		DAO_BASIC_PROPERTY_INFO basic_info = new DAO_BASIC_PROPERTY_INFO();
		
		return_value = basic_info.modifyPropertySP(m_value, pr_no);
		
		return return_value;
	}
	/**
	 * @MethodName  : modifyPropertyIC
	 * @Date   : 2012. 2. 8. 
	 * @MethodDescription : 플라즈마 물성 정보의 충돌방식 값을 변경하는 메소드
	 * @param m_value
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean modifyPropertyIC(String m_value, String pr_no){
		boolean return_value = false;
		
		DAO_BASIC_PROPERTY_INFO basic_info = new DAO_BASIC_PROPERTY_INFO();
		
		return_value = basic_info.modifyPropertyIC(m_value, pr_no);
		
		return return_value;
	}
	
	/**
	 * @MethodName  : modifyEXP_THE_REC
	 * @Date   : 2012. 1. 13. 
	 * @MethodDescription : 플라즈마 물성 정보의 검증 구분 값을 변경하는 메소드 
	 * @param m_value
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean modifyPropertyEXP_THE_REC(String m_value, String pr_no){
		boolean return_value = false;
		
		DAO_BASIC_PROPERTY_INFO basic_info = new DAO_BASIC_PROPERTY_INFO();
		
		return_value = basic_info.modifyPropertyEXP_THE_REC(m_value, pr_no);
		
		return return_value;
	}
}
