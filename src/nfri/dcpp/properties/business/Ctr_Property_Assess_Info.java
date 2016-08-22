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
 * @Description : ���� ������ ���ϱ� ���� �޼ҵ���� ����Ͻ� Ŭ����
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 06. 16
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @�����̷� : -
 *
 */
public class Ctr_Property_Assess_Info {
	public Ctr_Property_Assess_Info(){
		
	}
	
	/**
	 * @MethodName : selectPropertyList
	 * @Desc : �� �ʱ� ȭ�鿡 ������ ���� �򰡴�� ������ �������� �޼ҵ�
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
	 * @Desc : �ش� �������� ���� �������� �������� �޼ҵ�
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
	 * @MethodDescription : �ش� ���������� ���� �������� üũ�Ͽ� �⺻������ �������� �޼ҵ�
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
	 * @MethodDescription : ǥ���� ������ �޼ҵ�
	 * @param request
	 * @return
	 * @History  : - 
	 */
	public String makeAssessExpression(HttpServletRequest request){
		String return_value = "-";
		
		String expression = "";
		
		//Request Parameter : �Ի����� (Projectile)
		String req_pj_name = request.getParameter("projectile");
		//Request Parameter : �Ի����� ID �� (Projectile)
		String req_pj_name_id = request.getParameter("projectile_id");
		
		//Request Parameter : ǥ������ (Target)
		String req_tg_name = request.getParameter("tg_name");
		//Request Parameter : ǥ������ ID ��(Target)
		String req_tg_name_id = request.getParameter("tg_name_id");
		
		//Request Parameter : �̿�ȭ (Target)
		String req_tg_ion = ComUtil.isNullToNullString(request.getParameter("tg_ionic"));
		//Request Parameter : ���ڹ�ġ (Target)
		String req_tg_elec = ComUtil.isNullToNullString(request.getParameter("tg_elec"));
		//Request Parameter : �̼����� (Target)
		String req_tg_fine = ComUtil.isNullToNullString(request.getParameter("tg_fine"));
		
		//Request Parameter : �������� (Product)
		String req_pd_name = ComUtil.isNullToNullString(request.getParameter("pd_name"));
		//Request Parameter : �������� ID(Product)
		String req_pd_name_id = ComUtil.isNullToNullString(request.getParameter("pd_name_id"));
		
		//Request Parameter : �̿�ȭ (Product)
		String req_pd_ion = ComUtil.isNullToNullString(request.getParameter("pd_ionic"));
		//Request Parameter : ���ڹ�ġ (Product)
		String req_pd_elec = ComUtil.isNullToNullString(request.getParameter("pd_elec"));
		//Request Parameter : �̼����� (Product)
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
		
		//�Ի� ���� + ǥ������
		expression = req_pj_name + str_plus + req_tg_name;
		//ǥ������ �̿�ȭ üũ
		if(!req_tg_ion.equalsIgnoreCase("NULL")){
			expression = expression + ComUtil.getSupString(req_tg_ion);
		}
		//ǥ������ ���ڹ�ġ üũ
		if(!req_tg_elec.equalsIgnoreCase("NULL")){
			//ǥ������ �̼����� üũ
			if(!req_tg_fine.equalsIgnoreCase("NULL")){
				expression = expression + str_l_brace + req_tg_ion + str_comma + req_tg_fine + str_r_brace;
			}else{
				expression = expression + str_l_brace + req_tg_ion + str_r_brace;
			}
		}		
		
		//�������� üũ
		if(!req_pd_name.equalsIgnoreCase("NULL")){
			
			expression = expression + str_arrow;
			
			//�Ի� ���� + ǥ������
			expression = expression + req_pd_name;
			
			//�������� �̿�ȭ üũ
			if(!req_pd_ion.equalsIgnoreCase("NULL")){
				expression = expression + ComUtil.getSupString(req_pd_ion);
			}
			//�������� ���ڹ�ġ üũ
			if(!req_pd_elec.equalsIgnoreCase("NULL")){
				//ǥ������ �̼����� üũ
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
	 * @MethodDescription : ��� ���� ���� ������ ���� �˻� ���ǿ� ���� �ش� �������� �������� �޼ҵ�
	 * @param request
	 * @return
	 * @History  : - 
	 */
	public Vector<?> searchAssessPropertyListInfo(HttpServletRequest request){
		Vector<?> search_list = new Vector<Object>();
		
		/*
		 * SQL Query ���� ����
		 */		
		//String : Brace
		String str_left_brace = "(";
		String str_right_brace = ") EQUA";
		
		// * �� �ݵ�� �ԷµǴ� ��
		//SQL ���ǹ� : �浹��� (Impact) *
		String sql_ic_option = " AND BASIC.PL_BI_IMP_CLASS = ";
		//SQL ���ǹ� : �����μ��� (Main Process) *
		String sql_mp_option = " AND BASIC.PL_BI_MAIN_PROC = ";
		//SQL ���ǹ� : �����μ��� (Sub Process) *
		String sql_sp_option = " AND BASIC.PL_BI_SUB_PROC = ";
		
		//SQL ������
		String str_intersect = " INTERSECT ";
		
		//SQL ���ǹ� : ���� ����, ǥ������, �����԰� ���ö� �˻��������� ���̴� �⺻ SQL��
		String sql_equa_option = " SELECT PL_BI_DATA_NUM FROM PL_BASIC_EQUATION_INFO" +
				" WHERE PL_CPBI_ELE_NUM = ";

		//SQL ���ǹ� : �Ի����� ����(Projectile) : ���� ������ 1�̾�� �� *
		String sql_project_seq_option = " AND PL_BEI_SEQ = '1'";				
		//SQL ���ǹ� : ǥ������ ����(Target) : ���� ������ 2�̾�� �� *
		String sql_target_seq_option = " AND PL_BEI_SEQ = '2'";		
		//SQL ���ǹ� : �������� ����(Product) ���ڼ����� ������ (3, 4, 5, 6 )�̾�� �� *
		String sql_product_seq_option = " AND (PL_BEI_SEQ = '3' OR PL_BEI_SEQ = '4' OR PL_BEI_SEQ = '5' OR PL_BEI_SEQ = '6')";
		
		
		//SQL ���ǹ� : �̿�ȭ (Ionic State)
		String sql_ion_option = " AND PL_BEI_CHG_STATE = ";
		//SQL ���ǹ� : ���ڹ�ġ (Electron Configuration)
		String sql_elec_option = " AND PL_BEI_ELC_STATE = ";
		//SQL ���ǹ� : �̼����� (Fine Structure)
		String sql_fine_option = " AND PL_BEI_OTH_STRUC = ";
		
		//SQL ���ǹ� : X ���� (X Unit) *
		String sql_xu_option = " AND GRAPH.PL_BGI_X_AX_UNIT = ";
		//SQL ���ǹ� : Y ���� (Y Unit) *
		String sql_yu_option = " AND GRAPH.PL_BGI_Y_AX_UNIT = ";		
		
		//SQL WHERE �⺻ �Է¹�
		String sql_where_option = " WHERE BASIC.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM" +
				" AND BASIC.PL_BI_DATA_NUM = GRAPH.PL_BI_DATA_NUM" +
				" AND BASIC.PL_RAI_ARTCL_NUM = ARTCL.PL_RAI_ARTCL_NUM" +
				" AND BASIC.PL_BI_INSERT_FLAG = 'C'";
		
		//SQL ORDER BY �Է¹�
		String sql_order_by = " ORDER BY BASIC.PL_BI_MGMT_DATA_NUM ASC";
		//String sql_order_by = " ORDER BY BASIC.PL_BI_DATA_NUM ASC";
				
		//SQL ����° ���� : ǥ�� ���� / �Ի� ����
		//String sql_expresstion = " AND PL_BI_EXPRESSION LIKE ";
		//SQL GROUP BY �ɼ�
		//String sql_group_by = " GROUP BY GROUPING SETS(( PL_BI_IMP_CLASS, PL_BI_MAIN_PROC), (PL_BI_MAIN_PROC, PL_BI_SUB_PROC))";
		//String sql_group_by = " GROUP BY ROLLUP (PL_BI_IMP_CLASS, PL_BI_MAIN_PROC, PL_BI_SUB_PROC)";
		
		//Request Parameter : �浹���
		String req_ic_option = request.getParameter("ic_option");
		//Request Parameter : �����μ���
		String req_mp_option = request.getParameter("mp_option");
		//Request Parameter : �����μ���
		String req_sp_option = request.getParameter("sp_option");
		//Request Parameter : X����
		String req_xu_option = request.getParameter("xax_unit");
		//Request Parameter : Y����
		String req_yu_option = request.getParameter("xay_unit");
		
		//Request Parameter : �Ի����� (Projectile)
		String req_pj_name = request.getParameter("projectile");
		//Request Parameter : �Ի����� ID �� (Projectile)
		String req_pj_name_id = request.getParameter("projectile_id");
		
		//Request Parameter : ǥ������ (Target)
		String req_tg_name = request.getParameter("tg_name");
		//Request Parameter : ǥ������ ID ��(Target)
		String req_tg_name_id = request.getParameter("tg_name_id");
		
		//Request Parameter : �̿�ȭ (Target)
		String req_tg_ion = ComUtil.isNullToNullString(request.getParameter("tg_ionic"));
		//Request Parameter : ���ڹ�ġ (Target)
		String req_tg_elec = ComUtil.isNullToNullString(request.getParameter("tg_elec"));
		//Request Parameter : �̼����� (Target)
		String req_tg_fine = ComUtil.isNullToNullString(request.getParameter("tg_fine"));
		
		//Request Parameter : �������� (Product)
		String req_pd_name = ComUtil.isNullToNullString(request.getParameter("pd_name"));
		//Request Parameter : �������� ID(Product)
		String req_pd_name_id = ComUtil.isNullToNullString(request.getParameter("pd_name_id"));
		
		//Request Parameter : �̿�ȭ (Product)
		String req_pd_ion = ComUtil.isNullToNullString(request.getParameter("pd_ionic"));
		//Request Parameter : ���ڹ�ġ (Product)
		String req_pd_elec = ComUtil.isNullToNullString(request.getParameter("pd_elec"));
		//Request Parameter : �̼����� (Product)
		String req_pd_fine = ComUtil.isNullToNullString(request.getParameter("pd_fine"));
	
		
		//��ü SQL ���� ����
		//�Ի� ���ڿ� ǥ�� ���� ���� ������ ��� 
		String optionquery = str_left_brace + sql_equa_option + ComUtil.makeEqualOption(req_pj_name_id) + sql_project_seq_option
		            + str_intersect + sql_equa_option + ComUtil.makeEqualOption(req_tg_name_id) + sql_target_seq_option;

		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_tg_ion)){//ǥ������ �̿�ȭ
			optionquery = optionquery + sql_ion_option + ComUtil.makeEqualOption(req_tg_ion);
		}
		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_tg_elec)){//ǥ������ ���ڹ�ġ
			optionquery = optionquery + sql_elec_option + ComUtil.makeEqualOption(req_tg_elec);
		}
		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_tg_fine)){//ǥ������ �̼�����
			optionquery = optionquery + sql_fine_option + ComUtil.makeEqualOption(req_tg_fine);
		}
		//System.out.println("req_tg_name : " + req_tg_name);
		//System.out.println("req_pd_name : " + req_pd_name);
		
		//���� ���� ���� ������ ���
		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_name)){
			optionquery = optionquery + str_intersect + sql_equa_option + ComUtil.makeEqualOption(req_pd_name_id) + sql_product_seq_option;
			if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_ion)){//ǥ������ �̿�ȭ
				optionquery = optionquery + sql_ion_option + ComUtil.makeEqualOption(req_pd_ion);
			}
			if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_elec)){//ǥ������ ���ڹ�ġ
				optionquery = optionquery + sql_elec_option + ComUtil.makeEqualOption(req_pd_elec);
			}
			if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_fine)){//ǥ������ �̼�����
				optionquery = optionquery + sql_fine_option + ComUtil.makeEqualOption(req_pd_fine);
			}
		}
		//SQL�� FROM �� �ݱ�
		optionquery = optionquery + str_right_brace;
		
		//SQL�� WHERE �� 
		optionquery = optionquery + sql_where_option;
		
		//SQL�� WHERE �� �߰� Option : �浹���
		optionquery = optionquery + sql_ic_option + ComUtil.makeEqualOption(req_ic_option);
		
		//SQL�� WHERE �� �߰� Option : �����μ���
		optionquery = optionquery + sql_mp_option + ComUtil.makeEqualOption(req_mp_option);
		
		//SQL�� WHERE �� �߰� Option : �����μ���
		optionquery = optionquery + sql_sp_option + ComUtil.makeEqualOption(req_sp_option);
		
		//SQL�� WHERE �� �߰� Option : X����
		optionquery = optionquery + sql_xu_option + ComUtil.makeEqualOption(req_xu_option);
		
		//SQL�� WHERE �� �߰� Option : Y����
		optionquery = optionquery + sql_yu_option + ComUtil.makeEqualOption(req_yu_option);
				
		/*if(!ComVar.STRING_DASH.equalsIgnoreCase(req_srd_value)){//����ǥ������������
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
		//�������� ����Ʈ ��������
		DAO_PROPERTY_SEARCH_INFO search = new DAO_PROPERTY_SEARCH_INFO();
		search_list = search.selectSearchAssessList(optionquery);
		
		//���õǾ��� �ε���
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
	 * @MethodDescription : ���� �׷����� �׸��� ���� SQL ������ ����� �޼ҵ�
	 * @param request
	 * @return
	 * @History  : - 
	 */
	public String getConditionInfo(HttpServletRequest request){
		//String return_value = "";
		
		/*
		 * SQL Query ���� ����
		 */		
		//String : Brace
		String str_left_brace = ", PL_BASIC_GRAPH_INFO GRAPH, (";
		String str_right_brace = ") EQUA";
		
		// * �� �ݵ�� �ԷµǴ� ��
		//SQL ���ǹ� : �浹��� (Impact) *
		String sql_ic_option = " AND B.PL_BI_IMP_CLASS = ";
		//SQL ���ǹ� : �����μ��� (Main Process) *
		String sql_mp_option = " AND B.PL_BI_MAIN_PROC = ";
		//SQL ���ǹ� : �����μ��� (Sub Process) *
		String sql_sp_option = " AND B.PL_BI_SUB_PROC = ";
		
		//SQL ������
		String str_intersect = " INTERSECT ";
		
		//SQL ���ǹ� : ���� ����, ǥ������, �����԰� ���ö� �˻��������� ���̴� �⺻ SQL��
		String sql_equa_option = " SELECT PL_BI_DATA_NUM FROM PL_BASIC_EQUATION_INFO" +
				" WHERE PL_CPBI_ELE_NUM = ";

		//SQL ���ǹ� : �Ի����� ����(Projectile) : ���� ������ 1�̾�� �� *
		String sql_project_seq_option = " AND PL_BEI_SEQ = '1'";				
		//SQL ���ǹ� : ǥ������ ����(Target) : ���� ������ 2�̾�� �� *
		String sql_target_seq_option = " AND PL_BEI_SEQ = '2'";		
		//SQL ���ǹ� : �������� ����(Product) ���ڼ����� ������ (3, 4, 5, 6 )�̾�� �� *
		String sql_product_seq_option = " AND (PL_BEI_SEQ = '3' OR PL_BEI_SEQ = '4' OR PL_BEI_SEQ = '5' OR PL_BEI_SEQ = '6')";
		
		
		//SQL ���ǹ� : �̿�ȭ (Ionic State)
		String sql_ion_option = " AND PL_BEI_CHG_STATE = ";
		//SQL ���ǹ� : ���ڹ�ġ (Electron Configuration)
		String sql_elec_option = " AND PL_BEI_ELC_STATE = ";
		//SQL ���ǹ� : �̼����� (Fine Structure)
		String sql_fine_option = " AND PL_BEI_OTH_STRUC = ";
		
		//SQL ���ǹ� : X ���� (X Unit) *
		String sql_xu_option = " AND GRAPH.PL_BGI_X_AX_UNIT = ";
		//SQL ���ǹ� : Y ���� (Y Unit) *
		String sql_yu_option = " AND GRAPH.PL_BGI_Y_AX_UNIT = ";
		
		//SQL ���ǹ� : X MAX Value *
		String sql_xmax_option = " AND TO_NUMBER (A.PL_BGD_X_AX_VAL) < ";
		//SQL ���ǹ� : X MIN Value *
		String sql_xmin_option = " AND TO_NUMBER (A.PL_BGD_X_AX_VAL) > ";
		//SQL ���ǹ� : Y MAX Value *
		String sql_ymax_option = " AND TO_NUMBER (A.PL_BGD_Y_AX_VAL) < ";
		//SQL ���ǹ� : Y MIN Value *
		String sql_ymin_option = " AND TO_NUMBER (A.PL_BGD_Y_AX_VAL) > ";
				
		//SQL WHERE �⺻ �Է¹�
		String sql_where_option = " WHERE B.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM" +
				" AND B.PL_BI_DATA_NUM = GRAPH.PL_BI_DATA_NUM" +
				" AND B.PL_BI_INSERT_FLAG = 'C'";
		
		//SQL ����° ���� : ǥ�� ���� / �Ի� ����
		//String sql_expresstion = " AND PL_BI_EXPRESSION LIKE ";
		//SQL GROUP BY �ɼ�
		//String sql_group_by = " GROUP BY GROUPING SETS(( PL_BI_IMP_CLASS, PL_BI_MAIN_PROC), (PL_BI_MAIN_PROC, PL_BI_SUB_PROC))";
		//String sql_group_by = " GROUP BY ROLLUP (PL_BI_IMP_CLASS, PL_BI_MAIN_PROC, PL_BI_SUB_PROC)";
		
		//Request Parameter : �浹���
		String req_ic_option = request.getParameter("ic_option");
		//Request Parameter : �����μ���
		String req_mp_option = request.getParameter("mp_option");
		//Request Parameter : �����μ���
		String req_sp_option = request.getParameter("sp_option");
		//Request Parameter : X����
		String req_xu_option = request.getParameter("xax_unit");
		//Request Parameter : Y����
		String req_yu_option = request.getParameter("xay_unit");
		
		//Request Parameter : �Ի����� (Projectile)
		String req_pj_name = request.getParameter("projectile");
		//Request Parameter : �Ի����� ID �� (Projectile)
		String req_pj_name_id = request.getParameter("projectile_id");
		
		//Request Parameter : ǥ������ (Target)
		String req_tg_name = request.getParameter("tg_name");
		//Request Parameter : ǥ������ ID ��(Target)
		String req_tg_name_id = request.getParameter("tg_name_id");
		
		//Request Parameter : �̿�ȭ (Target)
		String req_tg_ion = ComUtil.isNullToNullString(request.getParameter("tg_ionic"));
		//Request Parameter : ���ڹ�ġ (Target)
		String req_tg_elec = ComUtil.isNullToNullString(request.getParameter("tg_elec"));
		//Request Parameter : �̼����� (Target)
		String req_tg_fine = ComUtil.isNullToNullString(request.getParameter("tg_fine"));
		
		//Request Parameter : �������� (Product)
		String req_pd_name = ComUtil.isNullToNullString(request.getParameter("pd_name"));
		//Request Parameter : �������� ID(Product)
		String req_pd_name_id = ComUtil.isNullToNullString(request.getParameter("pd_name_id"));
		
		//Request Parameter : �̿�ȭ (Product)
		String req_pd_ion = ComUtil.isNullToNullString(request.getParameter("pd_ionic"));
		//Request Parameter : ���ڹ�ġ (Product)
		String req_pd_elec = ComUtil.isNullToNullString(request.getParameter("pd_elec"));
		//Request Parameter : �̼����� (Product)
		String req_pd_fine = ComUtil.isNullToNullString(request.getParameter("pd_fine"));
	
		
		//��ü SQL ���� ����
		//�Ի� ���ڿ� ǥ�� ���� ���� ������ ��� 
		String optionquery = str_left_brace + sql_equa_option + ComUtil.makeEqualOption(req_pj_name_id) + sql_project_seq_option
		            + str_intersect + sql_equa_option + ComUtil.makeEqualOption(req_tg_name_id) + sql_target_seq_option;

		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_tg_ion)){//ǥ������ �̿�ȭ
			optionquery = optionquery + sql_ion_option + ComUtil.makeEqualOption(req_tg_ion);
		}
		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_tg_elec)){//ǥ������ ���ڹ�ġ
			optionquery = optionquery + sql_elec_option + ComUtil.makeEqualOption(req_tg_elec);
		}
		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_tg_fine)){//ǥ������ �̼�����
			optionquery = optionquery + sql_fine_option + ComUtil.makeEqualOption(req_tg_fine);
		}
		
		//���� ���� ���� ������ ���
		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_name)){
			optionquery = optionquery + str_intersect + sql_equa_option + ComUtil.makeEqualOption(req_pd_name_id) + sql_product_seq_option;
			if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_ion)){//ǥ������ �̿�ȭ
				optionquery = optionquery + sql_ion_option + ComUtil.makeEqualOption(req_pd_ion);
			}
			if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_elec)){//ǥ������ ���ڹ�ġ
				optionquery = optionquery + sql_elec_option + ComUtil.makeEqualOption(req_pd_elec);
			}
			if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_fine)){//ǥ������ �̼�����
				optionquery = optionquery + sql_fine_option + ComUtil.makeEqualOption(req_pd_fine);
			}
		}
		//SQL�� FROM �� �ݱ�
		optionquery = optionquery + str_right_brace;
		
		//SQL�� WHERE �� 
		optionquery = optionquery + sql_where_option;
		
		//SQL�� WHERE �� �߰� Option : �浹���
		optionquery = optionquery + sql_ic_option + ComUtil.makeEqualOption(req_ic_option);
		
		//SQL�� WHERE �� �߰� Option : �����μ���
		optionquery = optionquery + sql_mp_option + ComUtil.makeEqualOption(req_mp_option);
		
		//SQL�� WHERE �� �߰� Option : �����μ���
		optionquery = optionquery + sql_sp_option + ComUtil.makeEqualOption(req_sp_option);
		
		//SQL�� WHERE �� �߰� Option : X����
		optionquery = optionquery + sql_xu_option + ComUtil.makeEqualOption(req_xu_option);
		
		//SQL�� WHERE �� �߰� Option : Y����
		optionquery = optionquery + sql_yu_option + ComUtil.makeEqualOption(req_yu_option);
				
		//System.out.println("graph_xrange_value : " + request.getParameter("graph_xrange_value"));
		//if(ComUtil.isNullArray(request.getParameterValues("graph_xrange_value")) == ComVar.BOOL_FALSE){
		//	System.out.println("graph_xrange_value : " + request.getParameter("graph_xrange_value"));
		//}
		//if(ComUtil.isNullArray(request.getParameterValues("graph_yrange_value")) == ComVar.BOOL_FALSE){
		//	System.out.println("graph_yrange_value : " + request.getParameter("graph_yrange_value"));
		//}
		//X üũ�ڽ� Ȯ��
		//SQL�� WHERE �� �߰� Option : X MAX
		String req_x_max_v = ComUtil.isNullToDashString(request.getParameter("graph_xrange_max_value"));
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_x_max_v)){
			optionquery = optionquery + sql_xmax_option + req_x_max_v;
		}
		
		//SQL�� WHERE �� �߰� Option : X MIN
		String req_x_min_v = ComUtil.isNullToDashString(request.getParameter("graph_xrange_min_value"));
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_x_min_v)){
			optionquery = optionquery + sql_xmin_option + req_x_min_v;
		}

		//Y üũ�ڽ� ���� Ȯ��
		//SQL�� WHERE �� �߰� Option : Y MAX
		String req_y_max_v = ComUtil.isNullToDashString(request.getParameter("graph_yrange_max_value"));
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_y_max_v)){
			optionquery = optionquery + sql_ymax_option + req_y_max_v;
		}
		
		//SQL�� WHERE �� �߰� Option : Y MIN
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
	 * @MethodDescription : ���� ������� �� ���������� OZ �׷��� �׸��� ���� �������� �޼ҵ�
	 * @param pr_no_list
	 * @return
	 * @History  : - 
	 */
	public String getFinalCondition(String pr_no_list){
		String in_option = "";		
		/*
		 * SQL Query ���� ����
		 */		
		String str_start_option = "('";
		String str_middle_option = "','";
		String str_end_option = "')";

		//SQL ������
		
		//SQL ���ǹ� : �˻��������� ���̴� �⺻ SQL��
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
		 * SQL Query ���� ����
		 */		
		//String : Brace
		String str_left_brace = ", PL_BASIC_GRAPH_INFO GRAPH, (";
		String str_right_brace = ") EQUA";
		
		// * �� �ݵ�� �ԷµǴ� ��
		//SQL ���ǹ� : �浹��� (Impact) *
		String sql_ic_option = " AND B.PL_BI_IMP_CLASS = ";
		//SQL ���ǹ� : �����μ��� (Main Process) *
		String sql_mp_option = " AND B.PL_BI_MAIN_PROC = ";
		//SQL ���ǹ� : �����μ��� (Sub Process) *
		String sql_sp_option = " AND B.PL_BI_SUB_PROC = ";
		
		//SQL ������
		String str_intersect = " INTERSECT ";
		
		//SQL ���ǹ� : ���� ����, ǥ������, �����԰� ���ö� �˻��������� ���̴� �⺻ SQL��
		String sql_equa_option = " SELECT PL_BI_DATA_NUM FROM PL_BASIC_EQUATION_INFO" +
				" WHERE PL_CPBI_ELE_NUM = ";

		//SQL ���ǹ� : �Ի����� ����(Projectile) : ���� ������ 1�̾�� �� *
		String sql_project_seq_option = " AND PL_BEI_SEQ = '1'";				
		//SQL ���ǹ� : ǥ������ ����(Target) : ���� ������ 2�̾�� �� *
		String sql_target_seq_option = " AND PL_BEI_SEQ = '2'";		
		//SQL ���ǹ� : �������� ����(Product) ���ڼ����� ������ (3, 4, 5, 6 )�̾�� �� *
		String sql_product_seq_option = " AND (PL_BEI_SEQ = '3' OR PL_BEI_SEQ = '4' OR PL_BEI_SEQ = '5' OR PL_BEI_SEQ = '6')";
		
		
		//SQL ���ǹ� : �̿�ȭ (Ionic State)
		String sql_ion_option = " AND PL_BEI_CHG_STATE = ";
		//SQL ���ǹ� : ���ڹ�ġ (Electron Configuration)
		String sql_elec_option = " AND PL_BEI_ELC_STATE = ";
		//SQL ���ǹ� : �̼����� (Fine Structure)
		String sql_fine_option = " AND PL_BEI_OTH_STRUC = ";
		
		//SQL ���ǹ� : X ���� (X Unit) *
		String sql_xu_option = " AND GRAPH.PL_BGI_X_AX_UNIT = ";
		//SQL ���ǹ� : Y ���� (Y Unit) *
		String sql_yu_option = " AND GRAPH.PL_BGI_Y_AX_UNIT = ";
		
		//SQL ���ǹ� : X MAX Value *
		String sql_xmax_option = " AND TO_NUMBER (A.PL_BGD_X_AX_VAL) < ";
		//SQL ���ǹ� : X MIN Value *
		String sql_xmin_option = " AND TO_NUMBER (A.PL_BGD_X_AX_VAL) > ";
		//SQL ���ǹ� : Y MAX Value *
		String sql_ymax_option = " AND TO_NUMBER (A.PL_BGD_Y_AX_VAL) < ";
		//SQL ���ǹ� : Y MIN Value *
		String sql_ymin_option = " AND TO_NUMBER (A.PL_BGD_Y_AX_VAL) > ";
				
		//SQL WHERE �⺻ �Է¹�
		String sql_where_option = " WHERE B.PL_BI_DATA_NUM = EQUA.PL_BI_DATA_NUM" +
				" AND B.PL_BI_DATA_NUM = GRAPH.PL_BI_DATA_NUM" +
				" AND B.PL_BI_INSERT_FLAG = 'C'";
		
		//SQL ����° ���� : ǥ�� ���� / �Ի� ����
		//String sql_expresstion = " AND PL_BI_EXPRESSION LIKE ";
		//SQL GROUP BY �ɼ�
		//String sql_group_by = " GROUP BY GROUPING SETS(( PL_BI_IMP_CLASS, PL_BI_MAIN_PROC), (PL_BI_MAIN_PROC, PL_BI_SUB_PROC))";
		//String sql_group_by = " GROUP BY ROLLUP (PL_BI_IMP_CLASS, PL_BI_MAIN_PROC, PL_BI_SUB_PROC)";
		
		//Request Parameter : �浹���
		String req_ic_option = request.getParameter("ic_option");
		//Request Parameter : �����μ���
		String req_mp_option = request.getParameter("mp_option");
		//Request Parameter : �����μ���
		String req_sp_option = request.getParameter("sp_option");
		//Request Parameter : X����
		String req_xu_option = request.getParameter("xax_unit");
		//Request Parameter : Y����
		String req_yu_option = request.getParameter("xay_unit");
		
		//Request Parameter : �Ի����� (Projectile)
		String req_pj_name = request.getParameter("projectile");
		//Request Parameter : �Ի����� ID �� (Projectile)
		String req_pj_name_id = request.getParameter("projectile_id");
		
		//Request Parameter : ǥ������ (Target)
		String req_tg_name = request.getParameter("tg_name");
		//Request Parameter : ǥ������ ID ��(Target)
		String req_tg_name_id = request.getParameter("tg_name_id");
		
		//Request Parameter : �̿�ȭ (Target)
		String req_tg_ion = ComUtil.isNullToNullString(request.getParameter("tg_ionic"));
		//Request Parameter : ���ڹ�ġ (Target)
		String req_tg_elec = ComUtil.isNullToNullString(request.getParameter("tg_elec"));
		//Request Parameter : �̼����� (Target)
		String req_tg_fine = ComUtil.isNullToNullString(request.getParameter("tg_fine"));
		
		//Request Parameter : �������� (Product)
		String req_pd_name = ComUtil.isNullToNullString(request.getParameter("pd_name"));
		//Request Parameter : �������� ID(Product)
		String req_pd_name_id = ComUtil.isNullToNullString(request.getParameter("pd_name_id"));
		
		//Request Parameter : �̿�ȭ (Product)
		String req_pd_ion = ComUtil.isNullToNullString(request.getParameter("pd_ionic"));
		//Request Parameter : ���ڹ�ġ (Product)
		String req_pd_elec = ComUtil.isNullToNullString(request.getParameter("pd_elec"));
		//Request Parameter : �̼����� (Product)
		String req_pd_fine = ComUtil.isNullToNullString(request.getParameter("pd_fine"));
	
		
		//��ü SQL ���� ����
		//�Ի� ���ڿ� ǥ�� ���� ���� ������ ��� 
		String optionquery = str_left_brace + sql_equa_option + ComUtil.makeEqualOption(req_pj_name_id) + sql_project_seq_option
		            + str_intersect + sql_equa_option + ComUtil.makeEqualOption(req_tg_name_id) + sql_target_seq_option;

		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_tg_ion)){//ǥ������ �̿�ȭ
			optionquery = optionquery + sql_ion_option + ComUtil.makeEqualOption(req_tg_ion);
		}
		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_tg_elec)){//ǥ������ ���ڹ�ġ
			optionquery = optionquery + sql_elec_option + ComUtil.makeEqualOption(req_tg_elec);
		}
		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_tg_fine)){//ǥ������ �̼�����
			optionquery = optionquery + sql_fine_option + ComUtil.makeEqualOption(req_tg_fine);
		}
		
		//���� ���� ���� ������ ���
		if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_name)){
			optionquery = optionquery + str_intersect + sql_equa_option + ComUtil.makeEqualOption(req_pd_name_id) + sql_product_seq_option;
			if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_ion)){//ǥ������ �̿�ȭ
				optionquery = optionquery + sql_ion_option + ComUtil.makeEqualOption(req_pd_ion);
			}
			if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_elec)){//ǥ������ ���ڹ�ġ
				optionquery = optionquery + sql_elec_option + ComUtil.makeEqualOption(req_pd_elec);
			}
			if(!ComVar.STRING_NULL_B.equalsIgnoreCase(req_pd_fine)){//ǥ������ �̼�����
				optionquery = optionquery + sql_fine_option + ComUtil.makeEqualOption(req_pd_fine);
			}
		}
		//SQL�� FROM �� �ݱ�
		optionquery = optionquery + str_right_brace;
		
		//SQL�� WHERE �� 
		optionquery = optionquery + sql_where_option;
		
		//SQL�� WHERE �� �߰� Option : �浹���
		optionquery = optionquery + sql_ic_option + ComUtil.makeEqualOption(req_ic_option);
		
		//SQL�� WHERE �� �߰� Option : �����μ���
		optionquery = optionquery + sql_mp_option + ComUtil.makeEqualOption(req_mp_option);
		
		//SQL�� WHERE �� �߰� Option : �����μ���
		optionquery = optionquery + sql_sp_option + ComUtil.makeEqualOption(req_sp_option);
		
		//SQL�� WHERE �� �߰� Option : X����
		optionquery = optionquery + sql_xu_option + ComUtil.makeEqualOption(req_xu_option);
		
		//SQL�� WHERE �� �߰� Option : Y����
		optionquery = optionquery + sql_yu_option + ComUtil.makeEqualOption(req_yu_option);
				
		//System.out.println("graph_xrange_value : " + request.getParameter("graph_xrange_value"));
		//if(ComUtil.isNullArray(request.getParameterValues("graph_xrange_value")) == ComVar.BOOL_FALSE){
		//	System.out.println("graph_xrange_value : " + request.getParameter("graph_xrange_value"));
		//}
		//if(ComUtil.isNullArray(request.getParameterValues("graph_yrange_value")) == ComVar.BOOL_FALSE){
		//	System.out.println("graph_yrange_value : " + request.getParameter("graph_yrange_value"));
		//}
		//X üũ�ڽ� Ȯ��
		//SQL�� WHERE �� �߰� Option : X MAX
		String req_x_max_v = ComUtil.isNullToDashString(request.getParameter("graph_xrange_max_value"));
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_x_max_v)){
			optionquery = optionquery + sql_xmax_option + req_x_max_v;
		}
		
		//SQL�� WHERE �� �߰� Option : X MIN
		String req_x_min_v = ComUtil.isNullToDashString(request.getParameter("graph_xrange_min_value"));
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_x_min_v)){
			optionquery = optionquery + sql_xmin_option + req_x_min_v;
		}

		//Y üũ�ڽ� ���� Ȯ��
		//SQL�� WHERE �� �߰� Option : Y MAX
		String req_y_max_v = ComUtil.isNullToDashString(request.getParameter("graph_yrange_max_value"));
		if(!ComVar.STRING_DASH.equalsIgnoreCase(req_y_max_v)){
			optionquery = optionquery + sql_ymax_option + req_y_max_v;
		}
		
		//SQL�� WHERE �� �߰� Option : Y MIN
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
	 * @MethodDescription : ���� �� �����͸� ��ȸ�ϴ� �޼ҵ�
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
	 * @Desc : 1���� ������ DB�� �Է��ϴ� �޼ҵ�
	 * @param request
	 * @param user_id
	 * @return
	 */
	public boolean insertPropertyAssessInfo(Properties_Assess_Info info){
		boolean return_value = false;
		
		
		DAO_ASSESS_INFO assess_input = new DAO_ASSESS_INFO();
		
		String pr_no = info.getPL_BI_DATA_NUM();
		
		if(assess_input.selectAssessInfoExist(pr_no) == false){ //�������� ������ Insert
			return_value = assess_input.insertAssessInfo(info);
		}else{ //�����ϸ� update
			return_value = assess_input.updateAssessInfo(info);
		}
		
		return return_value;
	}
	
	/**
	 * @MethodName  : selectFinalAssessInfo
	 * @Date   : 2010. 10. 19 
	 * @MethodDescription : ������ ������ �������� ����Ͻ� �޼ҵ�
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
	 * @MethodDescription : ���� �� ������ �Է��ϴ� �޼ҵ�
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
		//String user_id = request.getParameter("user_id"); //�����ID
		String param_v_pro_no     = ComUtil.isNullToDashString(request.getParameter("v_pro_no")); //������� ������ȣ
		
		//1-3. ǥ����,�����μ���,�����μ���, ��  ���޹��� �Ķ���� ������ ����.
		String param_ic_option     = request.getParameter("ic_option"); // �浹���
		String param_projectile    = request.getParameter("projectile"); // �Ի�����
		String param_projectile_id = request.getParameter("projectile_id"); // �Ի�����ID
		String param_mp_option     = request.getParameter("mp_option"); // �����μ���
		String param_sp_option     = request.getParameter("sp_option"); // �����μ���
		
		String exp_param_mp_option = ctr_data.getExpName(param_mp_option);
		String exp_param_sp_option = ctr_data.getExpName(param_sp_option);
		String process_option = ComUtil.getAbbrString(exp_param_mp_option, 10) + ", " + ComUtil.getAbbrString(exp_param_sp_option, 10);
		
		//1-4.�Է��� ������������ ������
		Vector pd_info = searchAssessPropertyListInfo(request);
		
		//1-5. ������¹������� �Է��ϱ�		
		
		//������±׷�������
		String param_xax_unit      = request.getParameter("xax_unit"); // X ���� �ڵ�
		String param_xay_unit      = request.getParameter("xay_unit"); // Y ���� �ڵ�
		
		String param_xax_unit_name      = request.getParameter("x_title"); // X ���� �̸�
		String param_xay_unit_name      = request.getParameter("y_title"); // Y ���� �̸�
				
		String param_tg_name       = request.getParameter("tg_name"); // ǥ������
		String param_tg_name_id    = request.getParameter("tg_name_id"); // ǥ������ ID
		String param_tg_ionic      = ComUtil.isNullToDashString(request.getParameter("tg_ionic")); // ǥ������ �̿�ȭ
		String param_tg_elec       = ComUtil.isNullToDashString(request.getParameter("tg_elec")); // ǥ������ ���ڹ�ġ
		String param_tg_fine       = ComUtil.isNullToDashString(request.getParameter("tg_fine")); // ǥ������ �̼�����
		String param_pd_name       = ComUtil.isNullToDashString(request.getParameter("pd_name")); // �������� 
		String param_pd_name_id    = ComUtil.isNullToDashString(request.getParameter("pd_name_id")); // �������� ID 
		String param_pd_ionic      = ComUtil.isNullToDashString(request.getParameter("pd_ionic")); // �������� �̿�ȭ
		String param_pd_elec       = ComUtil.isNullToDashString(request.getParameter("pd_elec")); // �������� ���ڹ�ġ
		String param_pd_fine       = ComUtil.isNullToDashString(request.getParameter("pd_fine")); // �������� �̼�����
		
		//X, Y �ּ�, �ִ밪 üũ ����
		String param_graph_xrange_v      = ComUtil.isNullToDashString(request.getParameter("graph_xrange_value"));
		String param_graph_yrange_v      = ComUtil.isNullToDashString(request.getParameter("graph_yrange_value"));
		
		//X,Y �ּ�, �ִ밪 ����
		String param_x_min_v      = ComUtil.isNullToDashString(request.getParameter("graph_xrange_min_value")); // X�� �ּ�
		String param_x_max_v       = ComUtil.isNullToDashString(request.getParameter("graph_xrange_max_value")); // X�� �ִ�
		String param_y_min_v       = ComUtil.isNullToDashString(request.getParameter("graph_yrange_min_value")); // Y�� �ּ�
		String param_y_max_v       = ComUtil.isNullToDashString(request.getParameter("graph_yrange_max_value")); // Y�� �ִ� 
		
		//�� ��� �� ����
		String param_best_value = ComUtil.isNullToDashString(request.getParameter("final_best_value"));// Best value
		String param_average_value = ComUtil.isNullToDashString(request.getParameter("final_average_value"));// average value
		String param_final_assess = ComUtil.isNullToDashString(request.getParameter("final_assess"));// final_assess
		String param_final_col_period = ComUtil.isNullToDashString(request.getParameter("final_col_period"));// final_col_period
		String param_final_opinion = ComUtil.isNullToDashString(request.getParameter("final_opinion"));// final_opinion
		String param_final_std_ref_data = ComUtil.isNullToDashString(request.getParameter("final_std_ref_data"));// final_std_ref_data
		
		//ǥ���� �����
		String pd_expression = makeAssessExpression(request);
		//����������ȣ ����Ʈ �����
		String pro_no_list = getPro_No_List(pd_info);
		
		//1. ������� ���� �⺻ ���� �Է��ϱ�
		Inf_Grd_Properties_Basic_Info basic_info = new Inf_Grd_Properties_Basic_Info();		
		basic_info.setPL_IGBI_DATA_NUM(param_v_pro_no);
		basic_info.setPL_IGBI_MAIN_PROC(param_mp_option);
		basic_info.setPL_IGBI_SUB_PROC(param_sp_option);
		basic_info.setPL_IGBI_EXPRESSION(pd_expression);
		basic_info.setPL_UI_ID(user_id);
		basic_info.setPL_IGBI_DATA_NUM_LIST(pro_no_list);
		
		
		//������� ���� �⺻ ���� �Է� ��� ����
		boolean property_result = false;
		//��� ���� �׷��� ���� �Է� ��� ����
		boolean graph_result = false;
		boolean property_equation_result = false;
		boolean property_data_result = false;
		
		DAO_INF_GRD_PROPERTY_INFO dao_property = new DAO_INF_GRD_PROPERTY_INFO();
		property_result = dao_property.insertInfGrdBasicInfo(basic_info);
		
		//2. ��� ���� �׷��� ���� ���� �Է��ϱ� 
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
		
		
		//3. ������� ���� ���� ���� �Է��ϱ� ==> �̺κ� �� ���������� �̻���. ��������.
		if(graph_result == true){
			// ������� ���� ���� ���� �Է��ϱ�
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
		//4. �׷��� ������ �Է��ϱ�
		if(property_equation_result == true){
			DAO_BASIC_GRAPH_INFO graph = new DAO_BASIC_GRAPH_INFO();
			DAO_INF_GRD_PROPERTY_INFO dao = new DAO_INF_GRD_PROPERTY_INFO();
			for(int i = 0; i < pd_info.size(); i++){
			
				Properties_Assess_Search_List prot = (Properties_Assess_Search_List) pd_info.elementAt(i);
			  	String seach_pr_no = prot.getPL_BI_DATA_NUM();	 //������ȣ 	
			  	
			  	Vector v_option = new Vector();
				v_option.addElement(seach_pr_no);
				
				Vector result_v = graph.selectBasicGraphData(v_option);
			  	
				for(int j = 0; j < result_v.size(); j++){
			  		Graph_Data_Info g_data = (Graph_Data_Info)result_v.elementAt(j);	
					Inf_Grd_Graph_Data_Info data = new Inf_Grd_Graph_Data_Info();

					data.setPL_IGBI_DATA_NUM(param_v_pro_no);//������¹�ȣ
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
		
		String param_v_pro_no     = ComUtil.isNullToDashString(request.getParameter("v_pr_no")); //������� ������ȣ
		
		//�� ��� �� ����
		String param_best_value = ComUtil.isNullToDashString(request.getParameter("final_best_value"));// Best value
		String param_average_value = ComUtil.isNullToDashString(request.getParameter("final_average_value"));// average value
		String param_final_assess = ComUtil.isNullToDashString(request.getParameter("final_assess"));// final_assess
		String param_final_col_period = ComUtil.isNullToDashString(request.getParameter("final_col_period"));// final_col_period
		String param_final_opinion = ComUtil.isNullToDashString(request.getParameter("final_opinion"));// final_opinion
		String param_final_std_ref_data = ComUtil.isNullToDashString(request.getParameter("final_std_ref_data"));// final_std_ref_data
		//X Max & Min
		String param_final_x_max_v = ComUtil.isNullToDashString(request.getParameter("final_x_max_v"));
		String param_final_x_min_v = ComUtil.isNullToDashString(request.getParameter("final_x_min_v"));
		
		//�⺻ ���� ��������
		Ctr_Graph_Info_Process ctr_graph = new Ctr_Graph_Info_Process();
		Inf_Grd_Graph_Basic_Info graph = (Inf_Grd_Graph_Basic_Info)ctr_graph.selectViewInfGrdGraphInfo(param_v_pro_no);
		
		//X����
		String x_unit = graph.getPL_IGGI_X_AX_UNIT();
		//Y����
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
		// �� ���� �޾ƿ���		

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
			//�� �ð� �Է�
			final_assess.setPL_SEI_ASSESS_DATE(ComUtil.getTimeNow());
			//X ����
			final_assess.setPL_SEI_X_UNIT(x_unit);
			//Y ����
			final_assess.setPL_SEI_Y_UNIT(y_unit);
			//X �ִ밪
			final_assess.setPL_SEI_X_MAX(param_final_x_max_v);
			//X �ּҰ�
			final_assess.setPL_SEI_X_MIN(param_final_x_min_v);
			//����
			final_assess.setPL_SEI_ASSESS_USER(user_id);			
			
			/*
			 * ���� �� ������ �����ϱ�
			 * ������ ������ �����ϸ� ���� ������Ʈ �ϱ�
			 */
			DAO_ASSESS_INFO assess_input = new DAO_ASSESS_INFO();
			boolean i_value = false;
			i_value = assess_input.insertFinalAssessInfo(final_assess);
			if(i_value == true){
				return_value = updateStateFinalAssess(param_v_pro_no, "CC");
			}else{
				return_value = i_value;
			}
			//���� ���� ������� �����͸� �˻��� �����ϵ��� �����ؾ� ��.
	
		return return_value;
	}
	
	/**
	 * @MethodName  : updateStateFinalAssess
	 * @Date   : 2011. 6. 28. 
	 * @MethodDescription : ������ �Է»��¸� ������Ʈ �ϴ� �޼ҵ�
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
	 * @MethodDescription : ������� ������ DB�� �Է��ϰ� �򰡵� �����͸� �����ϴ� �޼ҵ�
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
		//String user_id = request.getParameter("user_id"); //�����ID
		//String param_v_pro_no     = ComUtil.isNullToDashString(request.getParameter("v_pro_no")); //������� ������ȣ
		
		//1-3. ǥ����,�����μ���,�����μ���, ��  ���޹��� �Ķ���� ������ ����.
		String param_ic_option     = request.getParameter("ic_option"); // �浹���
		String param_projectile    = request.getParameter("projectile"); // �Ի�����
		String param_projectile_id = request.getParameter("projectile_id"); // �Ի�����ID
		String param_mp_option     = request.getParameter("mp_option"); // �����μ���
		String param_sp_option     = request.getParameter("sp_option"); // �����μ���
		
		String exp_param_mp_option = ctr_data.getExpName(param_mp_option);
		String exp_param_sp_option = ctr_data.getExpName(param_sp_option);
		String process_option = ComUtil.getAbbrString(exp_param_mp_option, 10) + ", " + ComUtil.getAbbrString(exp_param_sp_option, 10);
		
		//1-4.�Է��� ������������ ������
		Vector<?> pd_info = searchAssessPropertyListInfo(request);
		
		//1-5. ������¹������� �Է��ϱ�		
		
		//������±׷�������
		String param_xax_unit      = request.getParameter("xax_unit"); // X ���� �ڵ�
		String param_xay_unit      = request.getParameter("xay_unit"); // Y ���� �ڵ�
		
		//String param_xax_unit_name      = request.getParameter("x_title"); // X ���� �̸�
		//String param_xay_unit_name      = request.getParameter("y_title"); // Y ���� �̸�
				
		String param_tg_name       = request.getParameter("tg_name"); // ǥ������
		String param_tg_name_id    = request.getParameter("tg_name_id"); // ǥ������ ID
		String param_tg_ionic      = ComUtil.isNullToNullString(request.getParameter("tg_ionic")); // ǥ������ �̿�ȭ
		String param_tg_elec       = ComUtil.isNullToNullString(request.getParameter("tg_elec")); // ǥ������ ���ڹ�ġ
		String param_tg_fine       = ComUtil.isNullToNullString(request.getParameter("tg_fine")); // ǥ������ �̼�����
		String param_pd_name       = ComUtil.isNullToNullString(request.getParameter("pd_name")); // �������� 
		String param_pd_name_id    = ComUtil.isNullToNullString(request.getParameter("pd_name_id")); // �������� ID 
		String param_pd_ionic      = ComUtil.isNullToNullString(request.getParameter("pd_ionic")); // �������� �̿�ȭ
		String param_pd_elec       = ComUtil.isNullToNullString(request.getParameter("pd_elec")); // �������� ���ڹ�ġ
		String param_pd_fine       = ComUtil.isNullToNullString(request.getParameter("pd_fine")); // �������� �̼�����
		
		//X, Y �ּ�, �ִ밪 üũ ����
		String param_graph_xrange_v      = ComUtil.isNullToNullString(request.getParameter("graph_xrange_value"));
		String param_graph_yrange_v      = ComUtil.isNullToNullString(request.getParameter("graph_yrange_value"));
		
		//X,Y �ּ�, �ִ밪 ����
		String param_x_min_v      = ComUtil.isNullToNullString(request.getParameter("graph_xrange_min_value")); // X�� �ּ�
		String param_x_max_v       = ComUtil.isNullToNullString(request.getParameter("graph_xrange_max_value")); // X�� �ִ�
		String param_y_min_v       = ComUtil.isNullToNullString(request.getParameter("graph_yrange_min_value")); // Y�� �ּ�
		String param_y_max_v       = ComUtil.isNullToNullString(request.getParameter("graph_yrange_max_value")); // Y�� �ִ� 

		//ǥ���� �����
		String pd_expression = makeAssessExpression(request);
		//����������ȣ ����Ʈ �����
		String pro_no_list = getPro_No_List(pd_info);	
		//����������ȣ ����Ʈ �����
		String pro_mgmt_list = getPro_Mgmt_List(pd_info);
		//����������ȣ ����
		String pro_no_cnt = Integer.toString(pd_info.size());
		//�������� �����帮��Ʈ �����
		String pro_artcl_list = getPro_Artcl_List(pd_info);
		
		//������� ���� �⺻ ���� �Է� ��� ����
		boolean property_result = false;
		//��� ���� �׷��� ���� �Է� ��� ����
		boolean graph_result = false;
		boolean property_equation_result = false;
		
		//1. ������� ���� �⺻ ���� �Է��ϱ�
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
		
		//2. ��� ���� �׷��� ���� ���� �Է��ϱ� 
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
		
		//3. ������� ���� ���� ���� �Է��ϱ�
		if(graph_result == true){
			//ǥ������ ����(Target)
	  		Inf_Grd_Properties_Equation_Info first_insert_info = new Inf_Grd_Properties_Equation_Info();
			first_insert_info.setPL_IGBI_DATA_NUM(param_v_pro_no);
			first_insert_info.setPL_CPBI_ELE_NUM(param_tg_name_id);
			first_insert_info.setPL_IGEI_SEQ("1");
			first_insert_info.setPL_IGEI_CHG_STATE(param_tg_ionic);
			first_insert_info.setPL_IGEI_ELC_STATE(param_tg_elec);
			first_insert_info.setPL_IGEI_OTH_STRUC(param_tg_fine); 		

	  		property_equation_result = dao_property.insertInfGrdEquation(first_insert_info);	  		
	  		
			//��������(Product)	  	
	  		
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
	  			  		
			// ������� ���� ���� ���� �Է��ϱ�
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
		//4. �׷��� ������ �Է��ϱ�
		if(property_equation_result == true){
			//String param_v_pro_no     = ComUtil.isNullToDashString(request.getParameter("v_pro_no")); //������� ������ȣ
			
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
				
				fw.write("========= ������¹�ȣ : "+ param_v_pro_no + " =========");
				fw.write("\r\n");
				
				for(int i = 0; i < pd_info.size(); i++){
					
					Properties_Assess_Search_List prot = (Properties_Assess_Search_List) pd_info.elementAt(i);
				  	String seach_pr_no = prot.getPL_BI_DATA_NUM();	 //������ȣ 	
				  	
				  	//System.out.println("������ȣ : "+ seach_pr_no);
				  	
				  	fw.write("---------------------------------------------");
				  	fw.write("\r\n");
				  	fw.write("������ȣ : "+ seach_pr_no);
				  	fw.write("\r\n");
				  	fw.write("---------------------------------------------");
				  	fw.write("\r\n");
				  	Vector<String> v_option = new Vector<String>();
					v_option.addElement(seach_pr_no);					
					/*
					 * ���� �̺κп��� �ִ� �ּҰ��� ����Ǿ�� ��.
					 * ������ ���� �������� ���θ� �������� ����.
					 * ��û�� ���� ��� �����͸� �ִ� �ּҰ��� �°� �����Ͽ� 
					 * ������ ������.
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
	 * @MethodDescription : ���� ������� ���������� �Է��Ҷ� �Ѱ��� �������� ��� �����͸� �̵���Ű�� �޼ҵ�
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
		//String user_id = request.getParameter("user_id"); //�����ID
		//String param_v_pro_no     = ComUtil.isNullToDashString(request.getParameter("v_pro_no")); //������� ������ȣ
		
		//1-3. ǥ����,�����μ���,�����μ���, ��  ���޹��� �Ķ���� ������ ����.
		String param_ic_option     = request.getParameter("ic_option"); // �浹���
		String param_projectile    = request.getParameter("projectile"); // �Ի�����
		String param_projectile_id = request.getParameter("projectile_id"); // �Ի�����ID
		String param_mp_option     = request.getParameter("mp_option"); // �����μ���
		String param_sp_option     = request.getParameter("sp_option"); // �����μ���
		
		String exp_param_mp_option = ctr_data.getExpName(param_mp_option);
		String exp_param_sp_option = ctr_data.getExpName(param_sp_option);
		String process_option = ComUtil.getAbbrString(exp_param_mp_option, 10) + ", " + ComUtil.getAbbrString(exp_param_sp_option, 10);
		
		//1-4.�Է��� ������������ ������
		//Vector pd_info = searchAssessPropertyListInfo(request);	
		
		//1-5. ������¹������� �Է��ϱ�		
		
		//������±׷�������
		String param_xax_unit      = request.getParameter("xax_unit"); // X ���� �ڵ�
		String param_xay_unit      = request.getParameter("xay_unit"); // Y ���� �ڵ�
		
		//String param_xax_unit_name      = request.getParameter("x_title"); // X ���� �̸�
		//String param_xay_unit_name      = request.getParameter("y_title"); // Y ���� �̸�
				
		String param_tg_name       = request.getParameter("tg_name"); // ǥ������
		String param_tg_name_id    = request.getParameter("tg_name_id"); // ǥ������ ID
		String param_tg_ionic      = ComUtil.isNullToNullString(request.getParameter("tg_ionic")); // ǥ������ �̿�ȭ
		String param_tg_elec       = ComUtil.isNullToNullString(request.getParameter("tg_elec")); // ǥ������ ���ڹ�ġ
		String param_tg_fine       = ComUtil.isNullToNullString(request.getParameter("tg_fine")); // ǥ������ �̼�����
		String param_pd_name       = ComUtil.isNullToNullString(request.getParameter("pd_name")); // �������� 
		String param_pd_name_id    = ComUtil.isNullToNullString(request.getParameter("pd_name_id")); // �������� ID 
		String param_pd_ionic      = ComUtil.isNullToNullString(request.getParameter("pd_ionic")); // �������� �̿�ȭ
		String param_pd_elec       = ComUtil.isNullToNullString(request.getParameter("pd_elec")); // �������� ���ڹ�ġ
		String param_pd_fine       = ComUtil.isNullToNullString(request.getParameter("pd_fine")); // �������� �̼�����
		
		//X, Y �ּ�, �ִ밪 üũ ����
		String param_graph_xrange_v      = ComUtil.isNullToNullString(request.getParameter("graph_xrange_value"));
		String param_graph_yrange_v      = ComUtil.isNullToNullString(request.getParameter("graph_yrange_value"));
		
		//X,Y �ּ�, �ִ밪 ����
		String param_x_min_v      = ComUtil.isNullToNullString(request.getParameter("graph_xrange_min_value")); // X�� �ּ�
		String param_x_max_v       = ComUtil.isNullToNullString(request.getParameter("graph_xrange_max_value")); // X�� �ִ�
		String param_y_min_v       = ComUtil.isNullToNullString(request.getParameter("graph_yrange_min_value")); // Y�� �ּ�
		String param_y_max_v       = ComUtil.isNullToNullString(request.getParameter("graph_yrange_max_value")); // Y�� �ִ� 

		//ǥ���� �����
		String pd_expression = makeAssessExpression(request);
		
		DAO_BASIC_PROPERTY_INFO dao_property = new DAO_BASIC_PROPERTY_INFO();
		Properties_Basic_Info basic_info = (Properties_Basic_Info)dao_property.selectPropertySpecInfo(search_pr_no);
		//����������ȣ ����Ʈ �����
		//String pro_no_list = getPro_No_List(pd_info);	
		//����������ȣ ����Ʈ �����
		String pro_mgmt_list = basic_info.getPL_BI_MGMT_DATA_NUM();
		//����������ȣ ����
		String pro_no_cnt = "1";
		//�������� �����帮��Ʈ �����
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

		//��ȣ ����
		str_artcl_list = str_artcl_list + str_fr_brace + "1" + str_ed_brace;
		//���� ��ȣ
		str_artcl_list = str_artcl_list + pro_mgmt_list + str_col;
		
		//�� ����
		str_artcl_list = str_artcl_list + info.getPL_RAI_ARTCL_TITLE();
		//�� ����
		str_artcl_list = str_artcl_list + str_comma + info.getPL_RAI_ARTCL_AUTH_ENAME();
		//���θ�
		str_artcl_list = str_artcl_list + str_comma + info.getPL_RAI_JOUR_TITLE();
		//����
		str_artcl_list = str_artcl_list + str_comma + info.getPL_RAI_JOUR_VOL();
		//�⵵
		str_artcl_list = str_artcl_list + str_comma + info.getPL_RAI_JOUR_YEAR();
		//����������
		str_artcl_list = str_artcl_list + str_page + info.getPL_RAI_ARTCL_ST_PAGE();
		
		
		//������� ���� �⺻ ���� �Է� ��� ����
		boolean property_result = false;
		//��� ���� �׷��� ���� �Է� ��� ����
		boolean graph_result = false;
		boolean property_equation_result = false;
		
		/*
		 * 1. ������� ���� �⺻ ���� �Է��ϱ�
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
		 * 2. ��� ���� �׷��� ���� ���� �Է��ϱ� 
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
		 * 3. ������� ���� ���� ���� �Է��ϱ�
		 */
		if(graph_result == true){
			//ǥ������ ����(Target)
	  		Inf_Grd_Properties_Equation_Info first_insert_info = new Inf_Grd_Properties_Equation_Info();
			first_insert_info.setPL_IGBI_DATA_NUM(param_v_pro_no);
			first_insert_info.setPL_CPBI_ELE_NUM(param_tg_name_id);
			first_insert_info.setPL_IGEI_SEQ("1");
			first_insert_info.setPL_IGEI_CHG_STATE(param_tg_ionic);
			first_insert_info.setPL_IGEI_ELC_STATE(param_tg_elec);
			first_insert_info.setPL_IGEI_OTH_STRUC(param_tg_fine); 		

	  		property_equation_result = inf_dao_property.insertInfGrdEquation(first_insert_info);	  		
	  		
			//��������(Product)	  	
	  		
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
	  			  		
			// ������� ���� ���� ���� �Է��ϱ�
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
		 * 4. ������� ���� �׷��� ������ �Է��ϱ�
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
		  	String seach_pr_no = prot.getPL_BI_DATA_NUM();	 //������ȣ 	
		  	//String artcl_no = prot.getPL_RAI_ARTCL_NUM(); //������ ��ȣ
		  	//String artcl_author = prot.getPL_RAI_ARTCL_AUTH_NAME(); //����������
		  	//String exp_option = prot.getPL_BI_EXP_THE_REC(); // ��������
		  	//String mgmt_num = prot.getPL_BI_MGMT_DATA_NUM(); //����������ǥ�ø�
		  	
		  	Vector v_option = new Vector();
			v_option.addElement(seach_pr_no);
			
			Vector result_v = graph.selectBasicGraphData(v_option);
		  	
			for(int j = 0; j < result_v.size(); j++){
		  		Graph_Data_Info g_data = (Graph_Data_Info)result_v.elementAt(j);	
		  		
		  		String seq = g_data.getPL_BGD_SEQ_NUM();
				String x_v = g_data.getPL_BGD_X_AX_VAL();
				String y_v = g_data.getPL_BGD_Y_AX_VAL();
				
				Fitting_Temp_Info temp = new Fitting_Temp_Info();
				temp.setPL_FTD_SEQ(seq);//������ ��ȣ
				temp.setPL_FTD_X_VAL(x_v); //X��
				temp.setPL_FTD_Y_VAL(y_v); //Y��
				temp.setPL_FBI_SEQ("-");//����������
				temp.setPL_UI_ID(user_id); //�����ID
				temp.setPL_IGBI_DATA_NUM(v_pr_no);//������¹�ȣ
		  		
				r_value = fitting.insertFittingTempData(temp);		  		
		  	}  	
		  	
		}	*/
		
		return r_value;
	}
	
	/**
	 * @MethodName  : checkFittingState
	 * @Date   : 2010. 07. 20 
	 * @MethodDescription : ����� ID�� �� �������� Fitting State �� �ִ����� ���� üũ�ϴ� �޼ҵ�
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
	 * @MethodDescription : Fitting�� ó���ϴ� ���°��� Insert �ϴ� �޼ҵ�
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
	 * @MethodDescription : Fitting�� ó���ϴ� ���°��� Update �ϴ� �޼ҵ�
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
	 * @MethodDescription : ����� ID�� �� �����͸� FittingTemp���� ����� �޼ҵ�
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
	 * @MethodDescription : Fitting�� ���� �ӽõ����͸� �̵���Ű�� �޼ҵ�.
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
		  	String seach_pr_no = prot.getPL_BI_DATA_NUM();	 //������ȣ 	
		  	//String artcl_no = prot.getPL_RAI_ARTCL_NUM(); //������ ��ȣ
		  	//String artcl_author = prot.getPL_RAI_ARTCL_AUTH_NAME(); //����������
		  	//String exp_option = prot.getPL_BI_EXP_THE_REC(); // ��������
		  	//String mgmt_num = prot.getPL_BI_MGMT_DATA_NUM(); //����������ǥ�ø�
		  	
		  	Vector<String> v_option = new Vector<String>();
			v_option.addElement(seach_pr_no);
			
			Vector<?> result_v = graph.selectBasicGraphData(v_option);
		  	
			for(int j = 0; j < result_v.size(); j++){
		  		Graph_Data_Info g_data = (Graph_Data_Info)result_v.elementAt(j);	
		  		
		  		String seq = g_data.getPL_BGD_SEQ_NUM();
				String x_v = g_data.getPL_BGD_X_AX_VAL();
				String y_v = g_data.getPL_BGD_Y_AX_VAL();
				
				Fitting_Temp_Info temp = new Fitting_Temp_Info();
				temp.setPL_FTD_SEQ(seq);//������ ��ȣ
				temp.setPL_FTD_X_VAL(x_v); //X��
				temp.setPL_FTD_Y_VAL(y_v); //Y��
				temp.setPL_FBI_SEQ("-");//����������
				temp.setPL_UI_ID(user_id); //�����ID
				temp.setPL_IGBI_DATA_NUM(v_pr_no);//������¹�ȣ
		  		
				r_value = fitting.insertFittingTempData(temp);		  		
		  	}  	
		  	
		}	
		
		return r_value;
	}

	/**
	 * @MethodName  : makeVaildPropertyNum
	 * @Date   : 2010. 08. 16 
	 * @MethodDescription : ��� ���� ���� ���� ��ȣ�� ������ Business �޼ҵ�
	 * @param ref_no
	 * @return
	 * @History  : - 
	 */
	public String makeVaildPropertyNum(String ref_no){
		String returnValue = "";
		String org_Date = ref_no;
		String in_Year = ComUtil.getYearInDate(org_Date);	
		String numType = "V"; //VALID PROPERTY �ϰ��

		//��ȣ�� �޾ƿ��� ���� SQL �� ����
		DAO_ASSESS_INFO seq = new DAO_ASSESS_INFO();
		
		String db_year = seq.selectValidProSaveYearInfo();
		
		if(!db_year.equalsIgnoreCase(in_Year)){
			//update			
			seq.updateValidProSaveYear(in_Year);
			seq.resetValidSeq();			
		}
		//seq.insertSaveYear(in_Year);
		
		String sqlValue = seq.selectValidSeq();
		
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
	 * @MethodName  : getPro_No_List
	 * @Date   : 2010. 08. 30 
	 * @MethodDescription : ������������Ʈ�� �������� �޼ҵ�
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
	 * @MethodDescription : ����������ȣ ����Ʈ�� ������ �޼ҵ�
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
	 * @MethodDescription : ������ ����Ʈ�� ����� ���� �޼ҵ�
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
			//��ȣ ����
			str_artcl_list = str_artcl_list + str_fr_brace + j + str_ed_brace;
			//���� ��ȣ
			str_artcl_list = str_artcl_list + search_mgmt_no + str_col;
			//�� ����
			str_artcl_list = str_artcl_list + info.getPL_RAI_ARTCL_TITLE();
			//�� ����
			str_artcl_list = str_artcl_list + str_comma + info.getPL_RAI_ARTCL_AUTH_ENAME();
			//���θ�
			str_artcl_list = str_artcl_list + str_comma + info.getPL_RAI_JOUR_TITLE();
			//����
			str_artcl_list = str_artcl_list + str_comma + info.getPL_RAI_JOUR_VOL();
			//�⵵
			str_artcl_list = str_artcl_list + str_comma + info.getPL_RAI_JOUR_YEAR();
			//����������
			str_artcl_list = str_artcl_list + str_page + info.getPL_RAI_ARTCL_ST_PAGE();
			
			if(i == size-1){
				//����X
				return str_artcl_list;
			}else{
				//����
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
		String numType = "B"; //BASIC PROPERTY �ϰ��

		//��ȣ�� �޾ƿ��� ���� SQL �� ����
		DAO_BASIC_PROPERTY_INFO seq = new DAO_BASIC_PROPERTY_INFO();
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
		return val_id;
		
	}*/
}
