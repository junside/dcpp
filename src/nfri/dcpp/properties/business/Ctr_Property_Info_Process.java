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
 * @Description : ���������� ���õ� ó���� �ϴ� ����Ͻ� ���� Ŭ����
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 02
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @�����̷� : -
 *
 */
public class Ctr_Property_Info_Process {
	
	public Ctr_Property_Info_Process(){
		
	}
	
	/**
	 * @MethodName : selectPropertyList
	 * @Desc : �ʱ� ȭ�鿡 ������ ���� ���� ������ �������� �޼ҵ�
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
	 * @Desc :�⺻ ���� ���� ȭ�鿡 ������ �󼼵����͸� �������� �޼ҵ� 
	 * @param pr_no
	 * @return
	 */
	public Properties_Basic_Info selectViewPropertySpecInfo(String pr_no){
		DAO_BASIC_PROPERTY_INFO info = new DAO_BASIC_PROPERTY_INFO();
		
		Properties_Basic_Info return_value = info.selectPropertySpecInfo(pr_no);
		Ctr_Option_Process ctr_option = new Ctr_Option_Process();
		
		//��з�
		return_value.setPL_BI_TOP_BRANCH(ctr_option.getExpName(return_value.getPL_BI_TOP_BRANCH()));
		//�����ͺз�
		return_value.setPL_BI_DATA_BRANCH(ctr_option.getExpName(return_value.getPL_BI_DATA_BRANCH()));
		//�����μ���
		return_value.setPL_BI_MAIN_PROC(ctr_option.getExpName(return_value.getPL_BI_MAIN_PROC()));
		//�����μ���
		return_value.setPL_BI_SUB_PROC(ctr_option.getExpName(return_value.getPL_BI_SUB_PROC()));
		//�浹���
		return_value.setPL_BI_IMP_CLASS(ctr_option.getExpName(return_value.getPL_BI_IMP_CLASS()));
		//��������
		return_value.setPL_BI_EXP_THE_REC(ctr_option.getExpName(return_value.getPL_BI_EXP_THE_REC()));
		//������ȣ
		return_value.setPL_BI_MGMT_DATA_NUM(return_value.getPL_BI_MGMT_DATA_NUM());
		//�Է³�¥
		return_value.setPL_BI_INSERT_DATE(return_value.getPL_BI_INSERT_DATE());
		
		return_value.setPL_BI_EXPRESSION(return_value.getPL_BI_EXPRESSION());	
		
		return return_value;//info.selectPropertySpecInfo(pr_no);
	}
	
	public Vector<Properties_Basic_Info> getPropertyListInfo(String pr_st_no, String pr_ed_no){
		//���� �ϳ��� ����������ȣ�� ������ ������ ��´�.
		DAO_BASIC_PROPERTY_INFO dao_basic = new DAO_BASIC_PROPERTY_INFO();
		
		Vector<?> basic_list = new Vector<Object>();
		
		Vector<Properties_Basic_Info> re_list = new Vector<Properties_Basic_Info>();
		
		//1. ��ü ���������� ��ȣ�� ǥ������ �����´�.
		//System.out.println("1. ��ü ���������� ��ȣ�� ǥ������ �����´�.");
		
		basic_list = dao_basic.selectAllPropertyNumExp(pr_st_no, pr_ed_no);
		
		//System.out.println("������ ���� ���� : " + basic_list.size());
		
		//2. ������ �������� �ϳ��� ���� �ٽ� Equation ������ �����´�.
		//int size = basic_list.size();
		for(int i = 0; i < basic_list.size(); i++){
			//System.out.println(i + "��° ����");
			Properties_Basic_Info basic_info = (Properties_Basic_Info)basic_list.get(i);
			String basic_pr_no = basic_info.getPL_BI_DATA_NUM();
			String basic_pr_exp = basic_info.getPL_BI_EXPRESSION();
			String comb_exp = selectEquationData(basic_pr_no);
			
			//System.out.println("�������� : " + basic_pr_no);
			//System.out.println("���� ǥ���� : " + basic_pr_exp);
			//System.out.println("���� ǥ���� : " + comb_exp);
			
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
				
		//4. �ٽ� �ϳ��� ��ü�� ��Ƽ� ���ͷ� ��ȯ�Ѵ�.	
		
		return re_list;
		
	}
	
	public boolean updateAllExpressionChange(String pr_st_no, String pr_ed_no){
		boolean r_value = false;
		DAO_BASIC_PROPERTY_INFO dao_basic = new DAO_BASIC_PROPERTY_INFO();
		
		Vector<?> basic_list = new Vector<Object>();
		
		Vector<?> re_list = new Vector<Object>();
		
		//1. ��ü ���������� ��ȣ�� ǥ������ �����´�.
		//System.out.println("1. ��ü ���������� ��ȣ�� ǥ������ �����´�.");
		
		basic_list = dao_basic.selectAllPropertyNumExp(pr_st_no, pr_ed_no);
		
		System.out.println("������ ���� ���� : " + basic_list.size());
		
		//2. ������ �������� �ϳ��� ���� �ٽ� Equation ������ �����´�.
		//int size = basic_list.size();
		for(int i = 0; i < basic_list.size(); i++){
			//System.out.println(i + "��° ����");
			Properties_Basic_Info basic_info = (Properties_Basic_Info)basic_list.get(i);
			String basic_pr_no = basic_info.getPL_BI_DATA_NUM();
			String basic_pr_exp = basic_info.getPL_BI_EXPRESSION();
			String comb_exp = selectEquationData(basic_pr_no);
			
			//System.out.println("�������� : " + basic_pr_no);
			//System.out.println("���� ǥ���� : " + basic_pr_exp);
			//System.out.println("���� ǥ���� : " + comb_exp);
			
			/*Properties_Basic_Info re_info = new Properties_Basic_Info();
			re_info.setPL_BI_DATA_NUM(basic_pr_no);
			re_info.setPL_BI_EXPRESSION(basic_pr_exp);
			re_info.setPL_BI_COMB_EXP(comb_exp);
			re_list.addElement(re_info);*/
			
			r_value = dao_basic.updatePropertyExpression(comb_exp, basic_pr_no);
			
			if(r_value == false){
				System.out.println("������Ʈ ���� : " + basic_pr_no);
			}else{
				System.out.println("������Ʈ ���� !! ");
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
	 * @Desc : ���� ������ ���� ������ ������ �������� �޼ҵ�
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
	 * @MethodDescription : Equation ���� ������ ���� ������������
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
	 * @MethodDescription : Equation ������ ������Ʈ �ϴ� �޼ҵ�
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
		// ������ ���� ��Ʈ
		// ���� �̸�
		//String new_part_no = request.getParameter("new_part_no");
		// ���� ��ȣ
		//String new_part_id = request.getParameter("new_part_id");
		// �̿�ȭ
		//String new_part_chg = request.getParameter("new_part_chg");
		// ���ڹ�ġ
		//String new_part_elc = request.getParameter("new_part_elc");
		// �̼�����
		//String new_part_oth = request.getParameter("new_part_oth");
		// ��Ʈ�ɼ�(->, +)
		//String new_part_seq = request.getParameter("new_part_seq");	
		//String addcode_id = request.getParameter("addcode_id");
		
		String [] part_no = request.getParameterValues("part_no");
		// ���� ��ȣ
		String [] part_id = request.getParameterValues("part_id");
		// �̿�ȭ
		String [] part_chg = request.getParameterValues("part_chg");
		// ���ڹ�ġ
		String [] part_elc = request.getParameterValues("part_elc");
		// �̼�����
		String [] part_oth = request.getParameterValues("part_oth");
		// ��Ʈ�ɼ�(->, +)
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
		
		/*if("1".equalsIgnoreCase(flag)){ //����
			System.out.println("����");
			r_value = basic_info.updateEquationInfo(equa_info);	
		}else if("2".equalsIgnoreCase(flag)){ //�Է�
			System.out.println("�Է�");
			r_value = basic_info.insertEquationInfo(equa_info);
		}*/
		r_value = basic_info.updateEquationInfo(equa_info);	
		
		if(r_value==true){
			String exp = ComUtil.convertKorean(ComUtil.isNullToDashString(request.getParameter("pr_expression")));
			//System.out.println("exp : " + exp);
			r_value = basic_info.updatePropertyExpression(exp, property_num);
		}
		
		if(i == 4){ //÷���ڵ� ������Ʈ
			r_value = basic_info.updateEquationAddCode(property_num, part_sp_option);
		}
		
		return r_value;

	}
	
	/**
	 * @MethodName  : insertEquationInfo
	 * @Date   : 2012. 8. 2. 
	 * @MethodDescription : Equation ������ �Է� �ϴ� �޼ҵ�
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
		// ������ ���� ��Ʈ
		// ���� �̸�
		//String new_part_no = request.getParameter("new_part_no");
		// ���� ��ȣ
		//String new_part_id = request.getParameter("new_part_id");
		// �̿�ȭ
		//String new_part_chg = request.getParameter("new_part_chg");
		// ���ڹ�ġ
		//String new_part_elc = request.getParameter("new_part_elc");
		// �̼�����
		//String new_part_oth = request.getParameter("new_part_oth");
		// ��Ʈ�ɼ�(->, +)			
		//String addcode_id = request.getParameter("addcode_id");
		
		String [] part_no = request.getParameterValues("part_no");
		// ���� ��ȣ
		String [] part_id = request.getParameterValues("part_id");
		// �̿�ȭ
		String [] part_chg = request.getParameterValues("part_chg");
		// ���ڹ�ġ
		String [] part_elc = request.getParameterValues("part_elc");
		// �̼�����
		String [] part_oth = request.getParameterValues("part_oth");
		// ��Ʈ�ɼ�(->, +)
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

		//System.out.println("�Է�");
		
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
	 * @Desc : ���� ������ ���� �� ���� ������ �������� �޼ҵ�
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
	 * @Desc : ���� ������ DB�� �Է��ϴ� Business �޼ҵ�
	 * @param request
	 * @return
	 */
	//public String insertPropertyInfo(String pr_ref_artcl_no, String pr_insert_user, String tb_option, String db_option, String mp_option, String sp_option, String ic_option, String et_option, String [] part_no, String [] part_id, String [] part_chg, String [] part_elc, String [] part_oth, String part_sp_option, String func)
	public String insertPropertyInfo(HttpServletRequest request)
	{	
		String value = "";
		boolean r_value = false;
		// ���� �⺻ ���� ��Ʈ
		// ���� ���� ��ȣ
		//String pr_no = request.getParameter("pr_no");
		// ���� ���� ��ȣ
		String pr_ref_artcl_no = request.getParameter("pr_ref_artcl_no");
		// �Է���
		String pr_insert_user = request.getParameter("user_id");
		// ��з�
		String tb_option = request.getParameter("tb_option");
		// �����ͺз�
		String db_option = request.getParameter("db_option");
		// �����μ���
		String mp_option = request.getParameter("mp_option");
		// �����μ���
		String sp_option = request.getParameter("sp_option");
		// �浹 ���
		String ic_option = request.getParameter("ic_option");
		// ���� ����
		String et_option = request.getParameter("et_option");
		
		// ������ ���� ��Ʈ
		// ���� �̸�
		String [] part_no = request.getParameterValues("part_no");
		// ���� ��ȣ
		String [] part_id = request.getParameterValues("part_id");
		// �̿�ȭ
		String [] part_chg = request.getParameterValues("part_chg");
		// ���ڹ�ġ
		String [] part_elc = request.getParameterValues("part_elc");
		// �̼�����
		String [] part_oth = request.getParameterValues("part_oth");
		// ��Ʈ�ɼ�(->, +)
		String part_sp_option = request.getParameter("part_sp_option");	
		//String addcode_id = request.getParameter("addcode_id");
		//�Լ���
		String func = ComUtil.convertKorean(ComUtil.isNullToDashString(request.getParameter("pr_expression")));
		//�Է³�¥
		String in_date = ComUtil.getTimeNow();
		
		//1. �������� ��ȣ �����
		//����ð� �˾Ƴ���
		String now_time = ComUtil.getTimeNow();
		String property_num = makePropertyNum(now_time);
				
		Properties_Save_Info info = new Properties_Save_Info();
		//2. ���� �⺻ ���� �Է�
		info.setPL_BI_DATA_NUM(property_num);//����������ȣ
		info.setPL_RAI_ARTCL_NUM(pr_ref_artcl_no);//�������ȣ ����		
		info.setPL_BI_TOP_BRANCH(tb_option);//��з�
		info.setPL_BI_DATA_BRANCH(db_option);//�����ͺз�
		info.setPL_BI_IMP_CLASS(ic_option);//�浹���
		info.setPL_BI_MAIN_PROC(mp_option);//�����μ���
		info.setPL_BI_SUB_PROC(sp_option);//�����μ���
		info.setPL_BI_EXP_THE_REC(et_option);//��������
		info.setPL_BI_INSERT_USER(pr_insert_user);//�Է��� ����
		info.setPL_BI_INSERT_FLAG("I");//�Է��÷��� �⺻�� : "I"
		info.setPL_BI_EXPRESSION(func);//�Լ��� �Է�
		info.setPL_BI_INSERT_DATE(in_date);//�Է³�¥
		
		//4. �⺻ ������ ���� �Է�
		
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
	 * @Desc : ���� ������ �Է��� �Ϸ� �ϴ� �޼ҵ�
	 * @param pr_no
	 * @return
	 */
	public boolean insertInfoComplete(String pr_no){
		boolean r_value = false;
		
		DAO_BASIC_PROPERTY_INFO basic_info = new DAO_BASIC_PROPERTY_INFO();
		//System.out.println("------------- New ");
		//System.out.println("�Է½ð� : " + ComUtil.getTimeNow());
		String new_pr_no = makeNewPropertyName(pr_no);
		//System.out.println("������ȣ : "+pr_no);
		//System.out.println("������ȣ : "+new_pr_no);
		
		r_value = basic_info.insertDataComplete(pr_no, new_pr_no);
		//System.out.println("------------- End ");
		return r_value;
	}
	
	/**
	 * @MethodName : makeNewPropertyName
	 * @Desc : ���� ������ �Է��� �Ϸ��ϰ� ���ο� ���� ������ ���� ��ȣ�� ����� �޼ҵ�
	 *         "����+���ǳ⵵+a" ����
	 * @param pr_no
	 * @return
	 */
	public String makeNewPropertyName(String pr_no){
		String returnValue = "";
		
		//1. ������ ��ȸ �� ���ڿ� �⵵ ��������		
		Vector<String> sqlOption = new Vector<String>();
		sqlOption.add(pr_no);
		
		DAO_REF_ARTICLE_INFO dao_article = new DAO_REF_ARTICLE_INFO();
		Article_Info atcl_info = dao_article.selectArticleAuthYear(sqlOption);
		
		//������ �� ��������
		String atcl_user = atcl_info.getPL_RAI_ARTCL_AUTH_FNAME();
		//���� ���ǳ⵵ ��������
		String atcl_year = atcl_info.getPL_RAI_JOUR_YEAR();
		
		//
		String article_user = atcl_user+atcl_year;
		
		//2. ���� ���� ������ ������ �⺻ ���� ���̺��� �ش� ������ ���� �պκ��� ���� ���� ����� ��ȸ
		DAO_BASIC_PROPERTY_INFO basic_info = new DAO_BASIC_PROPERTY_INFO();
		int count = basic_info.selectNumberCountInfo(article_user);
		//System.out.println("------------- New ");
		//System.out.println("������ count : " + count);
		//DAO_REF_ARTICLE_INFO dao_article = new DAO_REF_ARTICLE_INFO();
		//int artcl_count = dao_article.selectArticleCountInfo(sqlOption);
		String alphabetString = ComUtil.getAlphabetList(count);
		//System.out.println("alphabet count : " + alphabetString);
		returnValue = article_user + alphabetString;
		
		return returnValue;
	}
	
	/**
	 * @MethodName : makePropertyNum
	 * @Desc : ���� ���� ��ȣ�� ������ Business �޼ҵ�
	 * @param ref_no
	 * @return
	 */
	public String makePropertyNum(String date){
		String returnValue = "";
		//String org_Date = ref_no;
		String in_Year = ComUtil.getYearInDate(date);	
		String numType = "B"; //BASIC PROPERTY �ϰ��		

		//��ȣ�� �޾ƿ��� ���� SQL �� ����
		DAO_BASIC_PROPERTY_INFO seq = new DAO_BASIC_PROPERTY_INFO();
		
		
		String db_year = seq.selectPropertySaveYearInfo();
		
		if(!db_year.equalsIgnoreCase(in_Year)){
			//update			
			seq.updatePropertySaveYear(in_Year);
			seq.resetPropertySeq();			
		}
		//seq.insertPropertySaveYear(in_Year);
		
		String sqlValue = seq.selectPropertySeq();
		
		//��ȣ �����
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
	 * @Desc : �������� ǥ������ ����� ���� Business �޼ҵ�
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
			
			//�̿�ȭ ��
			if(!str_Chg_State.equalsIgnoreCase("NULL")){ //�̿�ȭ ���� ������,
				//String str_f_sup = "<sup>";
				//String str_e_sup = "</sup>";
				//String str_sup_chg = ComUtil.getSupString(str_Chg_State);//str_f_sup + str_Chg_State + str_e_sup;				
				part_express = part_express + ComUtil.getSupString(str_Chg_State);//str_sup_chg;
			}
			
			//���ڹ�ġ ��
			if(!str_Elc_State.equalsIgnoreCase("NULL")){ //���ڹ�ġ ���� ������,
				//�̼����� ��
				if(!str_Oth_Struc.equalsIgnoreCase("NULL")){ //�̼����� ���� ������,
					part_express = part_express + "(" + str_Elc_State;
					part_express = part_express + "," + str_Oth_Struc;
					part_express = part_express + ")";
				}else{ //�̼����� ���� ������,
					part_express = part_express + "(" + str_Elc_State;
					part_express = part_express + ")";
				}
			}else{ //���ڹ�ġ ���� ������,
				if(!str_Oth_Struc.equalsIgnoreCase("NULL")){ //�̼����� ���� ������
					part_express = part_express + "(" + str_Oth_Struc;
					part_express = part_express + ")";
				}
			}			
		}
		
		//System.out.println("���ս� : " + part_express);
		
		return part_express;
	}
	
	/**
	 * @MethodName  : deletePropertyInfo
	 * @Date   : 2012. 1. 13. 
	 * @MethodDescription : �ö�� �������� �����ϴ� �޼ҵ�
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
	 * @MethodDescription : �ö�� �������� �Է� �÷��� ������Ʈ �޼ҵ�
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
	 * @MethodDescription : �ö�� ���� ������ �����ͱ��� ���� �����ϴ� �޼ҵ�
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
	 * @MethodDescription : �ö�� ���� ������ �����μ��� ���� �����ϴ� �޼ҵ�
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
	 * @MethodDescription : �ö�� ���� ������ �����μ��� ���� �����ϴ� �޼ҵ�
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
	 * @MethodDescription : �ö�� ���� ������ �浹��� ���� �����ϴ� �޼ҵ�
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
	 * @MethodDescription : �ö�� ���� ������ ���� ���� ���� �����ϴ� �޼ҵ� 
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
