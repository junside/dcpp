/**
 * 
 */
package nfri.dcpp.properties.db;

import java.util.ArrayList;
import java.util.Vector;

import nfri.dcpp.com.db.ComResultSet;
import nfri.dcpp.com.db.ComSQLExecution;
import nfri.dcpp.com.util.ComUtil;
import nfri.dcpp.properties.model.Properties_Basic_Info;
import nfri.dcpp.properties.model.Properties_Equation_Get_DbInfo;
import nfri.dcpp.properties.model.Properties_Equation_Info;
import nfri.dcpp.properties.model.Properties_Basic_List_Info;
import nfri.dcpp.properties.model.Properties_Save_Info;

/**
 *
 * @Project : dcpp_web
 * @Title : DAO_BASIC_PROPERTY_INFO.java
 * @Description : �⺻ �������� ���̺�� ���õ� �׸��� ó���ϴ� DAO Ŭ����
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 11
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @�����̷� : -
 *
 */
public class DAO_BASIC_PROPERTY_INFO {

	//DB ���� Ŭ���� ����
	ComSQLExecution comSQL;
	
	public DAO_BASIC_PROPERTY_INFO() {
		comSQL = new ComSQLExecution();
	}
	
	/**
	 * @MethodName : selectProperyList
	 * @Desc : ���������� �������� �޼ҵ�
	 * @return
	 */
	public Vector<Properties_Basic_List_Info> selectProperyList(String sqlOption){
		Vector<Properties_Basic_List_Info> values = new Vector<Properties_Basic_List_Info>();
		
		String sqlQuery = "SELECT BASIC.PL_BI_DATA_NUM, BASIC.PL_RAI_ARTCL_NUM, NVL(UNIT.PL_BGI_X_AX_UNIT,'FALSE')," +
				" GRAPH.DATACOUNT, BASIC.PL_BI_INSERT_FLAG" +
				" FROM (SELECT COUNT(G.PL_BI_DATA_NUM) AS DATACOUNT, " +
				"       B.PL_BI_DATA_NUM AS PL_BI_DATA_NUM FROM PL_BASIC_GRAPH_INFO G, PL_BASIC_INFO B" +
				"       WHERE G.PL_BI_DATA_NUM(+) = B.PL_BI_DATA_NUM GROUP BY B.PL_BI_DATA_NUM) GRAPH," +
				"       PL_BASIC_INFO BASIC, PL_BASIC_GRAPH_INFO UNIT" +
				" WHERE BASIC.PL_BI_DATA_NUM = GRAPH.PL_BI_DATA_NUM" +
				" AND BASIC.PL_BI_DATA_NUM = UNIT.PL_BI_DATA_NUM(+) ";
				//+" ORDER BY BASIC.PL_BI_DATA_NUM";
		sqlQuery = sqlQuery + sqlOption;
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery);
		
		while(rs.next()){	
			Properties_Basic_List_Info property_Info = new Properties_Basic_List_Info();
			property_Info.setPL_BI_DATA_NUM(rs.getString(1));
			property_Info.setPL_RAI_ARTCL_NUM(rs.getString(2));
			if(rs.getString(3).equalsIgnoreCase("FALSE")){
				property_Info.setPL_GRAPH_UNIT_DATA(false);	
			}else{
				property_Info.setPL_GRAPH_UNIT_DATA(true);
			}
			if(rs.getInt(4) > 0)
			{
				property_Info.setPL_GRAPH_DATA(true);
			}else{
				property_Info.setPL_GRAPH_DATA(false);
			}
			property_Info.setPL_BI_INSERT_FLAG(rs.getString(5));
			values.addElement(property_Info);
		}
		return values;
	}
		
	/**
	 * @MethodName  : selectPropertySaveYearInfo
	 * @Date   : 2011. 4. 6. 
	 * @MethodDescription : DB�� ����� �Է³⵵ ��������
	 * @return
	 * @History  : - 
	 */
	public String selectPropertySaveYearInfo(){
		String r_value = "";
		
		String sqlQuery = "SELECT P.PYI_PROPERTY_YEAR FROM PLASMA.PL_YEAR_INFO P";
		r_value = comSQL.executeSelectOneStr(sqlQuery);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : insertSaveYear
	 * @Date   : 2011. 4. 6. 
	 * @MethodDescription : DB�� �Է³⵵ �����ϱ�
	 * @param year
	 * @return
	 * @History  : - 
	 */
	public boolean insertPropertySaveYear(String year){
		boolean r_value = false;
		
		String sqlQuery = "INSERT INTO PLASMA.PL_YEAR_INFO (PYI_PROPERTY_YEAR) VALUES ( ? )";
		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(year);
		
		r_value = comSQL.executeTransact(sqlQuery, ve_1);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : updatePropertySaveYear
	 * @Date   : 2011. 4. 6. 
	 * @MethodDescription : DB�� �Է³⵵ ������Ʈ�ϱ�
	 * @param year
	 * @return
	 * @History  : - 
	 */
	public boolean updatePropertySaveYear(String year){
		boolean r_value = false;
		
		String sqlQuery = "UPDATE PLASMA.PL_YEAR_INFO SET    PYI_PROPERTY_YEAR = ?";
		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(year);
		
		r_value = comSQL.executeTransact(sqlQuery, ve_1);
		
		return r_value;
	}
	
	public Vector<Properties_Basic_Info> selectAllPropertyNumExp(String st_no, String en_no){
		
		//�� ���� ���� Query �Ķ���� ����		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(st_no);
		ve_1.addElement(en_no);
		
		Vector<Properties_Basic_Info> values = new Vector<Properties_Basic_Info>();
		String sqlQuery = "SELECT PL_BI_DATA_NUM, PL_BI_EXPRESSION FROM PL_BASIC_INFO" +
				" WHERE PL_BI_DATA_NUM BETWEEN ? AND ? ORDER BY PL_BI_DATA_NUM";
		
		//ComResultSet rs = comSQL.executeSelect(sqlQuery);
		ComResultSet rs = comSQL.executeSelect(sqlQuery, ve_1);
		
		while(rs.next()){	
			Properties_Basic_Info basic_Info = new Properties_Basic_Info();
			basic_Info.setPL_BI_DATA_NUM(rs.getString(1));
			basic_Info.setPL_BI_EXPRESSION(rs.getString(2));
			values.addElement(basic_Info);
		}
		
		return values;
	}
	
	/**
	 * @MethodName  : selectAllEquationList
	 * @Date   : 2012. 7. 31. 
	 * @MethodDescription : ��� ������ Equation ������ �������� �޼ҵ�
	 * @return
	 * @History  : - 
	 */
	public Vector<Properties_Equation_Get_DbInfo> selectAllEquationList(){

		Vector<Properties_Equation_Get_DbInfo> values = new Vector<Properties_Equation_Get_DbInfo>();
		String sqlQuery = "SELECT E.PL_BI_DATA_NUM, E.PL_CPBI_ELE_NUM, P.PL_CPBI_ELE_SYMBOL, E.PL_BEI_SEQ," +
				" NVL(E.PL_BEI_CHG_STATE,'-'), NVL(E.PL_BEI_ELC_STATE,'-'), NVL(E.PL_BEI_OTH_STRUC,'-'), A.PL_CI_ID" +
				" FROM PL_BASIC_EQUATION_INFO E, PL_CHEM_PART_BASIC_INFO P, PL_BASIC_EQUATION_ADDC_INFO A" +
				" WHERE E.PL_BI_DATA_NUM = A.PL_BI_DATA_NUM AND E.PL_CPBI_ELE_NUM = P.PL_CPBI_ELE_NUM" +
				" ORDER BY E.PL_BI_DATA_NUM, E.PL_BEI_SEQ";

		
		ComResultSet rs = comSQL.executeSelect(sqlQuery);
		
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
	}
	
	/**
	 * @MethodName : selectEquationList
	 * @Desc : ������ ������ �������� �޼ҵ�
	 * @param sqlOption(pro_no)
	 * @return
	 */
	public Vector<Properties_Equation_Get_DbInfo> selectEquationList(String pr_no){

		Vector<Properties_Equation_Get_DbInfo> values = new Vector<Properties_Equation_Get_DbInfo>();
		String sqlQuery = "SELECT E.PL_BI_DATA_NUM, E.PL_CPBI_ELE_NUM, P.PL_CPBI_ELE_SYMBOL, E.PL_BEI_SEQ," +
				" NVL(E.PL_BEI_CHG_STATE,'NULL'), NVL(E.PL_BEI_ELC_STATE,'NULL'), NVL(E.PL_BEI_OTH_STRUC,'NULL'), A.PL_CI_ID" +
				" FROM PL_BASIC_EQUATION_INFO E, PL_CHEM_PART_BASIC_INFO P, PL_BASIC_EQUATION_ADDC_INFO A" +
				" WHERE E.PL_BI_DATA_NUM = A.PL_BI_DATA_NUM AND E.PL_CPBI_ELE_NUM = P.PL_CPBI_ELE_NUM" +
				" AND E.PL_BI_DATA_NUM = ? ORDER BY E.PL_BEI_SEQ";
		
		//�� ���� ���� Query �Ķ���� ����		
		Vector<String> ve_1 = new Vector<String>();
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
	}
	
	/**
	 * @MethodName : selectPropertySeq
	 * @Desc : ������ȣ�� ����� ���� Sequence ��ȣ�� DB���� �������� �޼ҵ�
	 * @return
	 */
	public String selectPropertySeq(){
		String sqlQuery = "SELECT PL_PROPERTY_SEQ.nextval FROM DUAL";
		String resultStr = comSQL.executeSelectOneStr(sqlQuery);
		return resultStr;
	}
	
	/**
	 * @MethodName  : resetPropertySeq
	 * @Date   : 2011. 4. 6. 
	 * @MethodDescription : ���� ��ȣ Sequence ��ȣ�� �����ϱ�
	 * @return
	 * @History  : - 
	 */
	public boolean resetPropertySeq(){
		boolean r_value = false;
		
		String sqlDrop = "DROP SEQUENCE PLASMA.PL_PROPERTY_SEQ";

		String sqlCreate = "CREATE SEQUENCE PLASMA.PL_PROPERTY_SEQ START WITH 1" +
				" MAXVALUE 100000000 MINVALUE 1" +
				" CYCLE NOCACHE ORDER";
		
		String sqlQuery = "ALTER SEQUENCE PLASMA.PL_PROPERTY_SEQ" +
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
	 * @MethodName : selectNumberCountInfo
	 * @Desc : �ش� ���� ��ȣ �պκ��� ��� �����ϴ� �� �������� �޼ҵ�
	 * @param sqlOption
	 * @return
	 */
	public int selectNumberCountInfo(String sqlOption){
		int re_value = 0;
		
		String sqlQuery = "SELECT COUNT(*) FROM PL_BASIC_INFO WHERE UPPER(PL_BI_MGMT_DATA_NUM) LIKE UPPER('"+ sqlOption +"%')";
		//System.out.print(sqlQuery);
		re_value = comSQL.executeSelectOneInt(sqlQuery);
		
		return re_value;
	}
	
	/**
	 * @MethodName : selectPropertySpecInfo
	 * @Desc : �⺻ ���� ������ �������� DAO �޼ҵ�
	 * @param pr_no
	 * @return
	 */
	public Properties_Basic_Info selectPropertySpecInfo(String pr_no){
		
		// 1. �� ���� ���� Query
		String sqlQuery = "SELECT PL_BI_DATA_NUM, PL_BI_TOP_BRANCH, PL_BI_DATA_BRANCH," +
				" PL_BI_IMP_CLASS, PL_BI_MAIN_PROC, PL_BI_SUB_PROC, PL_BI_EXP_THE_REC," +
				" NVL(PL_BI_EXPRESSION, '-'), PL_BI_INSERT_FLAG, NVL(PL_BI_MGMT_DATA_NUM, '-')," +
				" PL_RAI_ARTCL_NUM, NVL(PL_BI_INSERT_DATE, '-')" +
				" FROM PL_BASIC_INFO WHERE PL_BI_DATA_NUM = ?";
		
		//�� ���� ���� Query �Ķ���� ����		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(pr_no);

		ComResultSet rs = comSQL.executeSelect(sqlQuery, ve_1);
		
		Properties_Basic_Info spec_Info = new Properties_Basic_Info();
		while(rs.next()){	
			spec_Info.setPL_BI_DATA_NUM(rs.getString(1));
			spec_Info.setPL_BI_TOP_BRANCH(rs.getString(2));
			spec_Info.setPL_BI_DATA_BRANCH(rs.getString(3));
			spec_Info.setPL_BI_IMP_CLASS(rs.getString(4));
			spec_Info.setPL_BI_MAIN_PROC(rs.getString(5));
			spec_Info.setPL_BI_SUB_PROC(rs.getString(6));
			spec_Info.setPL_BI_EXP_THE_REC(rs.getString(7));
			spec_Info.setPL_BI_EXPRESSION(rs.getString(8));
			spec_Info.setPL_BI_INSERT_FLAG(rs.getString(9));
			spec_Info.setPL_BI_MGMT_DATA_NUM(rs.getString(10));
			spec_Info.setPL_RAI_ARTCL_NUM(rs.getString(11));
			spec_Info.setPL_BI_INSERT_DATE(rs.getString(12));
		}
		
/*		// 2. ������ ���� Query
		Vector ve_equa = this.selectEquationList(ve_1);
		
		for(int i = 0 ; i < ve_equa.size(); i ++) {
			Properties_Equation_Get_DbInfo equa_obj = (Properties_Equation_Get_DbInfo)ve_equa.get(i);
			Vector ve_2 = new Vector();
			ve_2.addElement(equa_obj.getPL_BI_DATA_NUM());
			ve_2.addElement(equa_obj.getPL_CPBI_ELE_NUM());
			ve_2.addElement(equa_obj.getPL_BEI_SEQ());
			ve_2.addElement(equa_obj.getPL_BEI_CHG_STATE());
			ve_2.addElement(equa_obj.getPL_BEI_ELC_STATE());
			ve_2.addElement(equa_obj.getPL_BEI_OTH_STRUC());
		}*/

		// 5. �Է� ��� ����		
		return spec_Info;
	}
	/**
	 * @MethodName : insertDataUpdate
	 * @Desc : �Է��� �Ϸ��ϴ� �ʵ带 ������Ʈ�� �ϴ� �޼ҵ�
	 * @param pr_no
	 * @return
	 */
	public boolean insertDataUpdate(String pr_no, String flag){
		boolean r_value = false;
		
		String sql_Query = "UPDATE PLASMA.PL_BASIC_INFO SET PL_BI_INSERT_FLAG = ?" +
				" WHERE PL_BI_DATA_NUM = ?";
		Vector<String> sql_option = new Vector<String>();
		sql_option.addElement(flag);
		sql_option.addElement(pr_no);
		
		r_value = comSQL.executeTransact(sql_Query, sql_option);
		
		return r_value;
	}
	
	/**
	 * @MethodName : insertDataComplete
	 * @Desc : �Է��� �Ϸ��ϴ� �ʵ带 �Է��ϴ� �޼ҵ�
	 * @param pr_no
	 * @return
	 */
	public boolean insertDataComplete(String pr_no, String new_pr_no){
		boolean r_value = false;
		
		String sql_Query = "UPDATE PLASMA.PL_BASIC_INFO SET PL_BI_INSERT_FLAG = 'C', PL_BI_MGMT_DATA_NUM = ?" +
				" WHERE PL_BI_DATA_NUM = ?";
		Vector<String> sql_option = new Vector<String>();
		sql_option.addElement(new_pr_no);
		sql_option.addElement(pr_no);
		
		r_value = comSQL.executeTransact(sql_Query, sql_option);
		
		return r_value;
	}
	
	/**
	 * @MethodName : insertPropertyInfo
	 * @Desc : �⺻ ���� ������ �Է��ϴ� �޼ҵ�
	 * @param info
	 * @return
	 */
	public boolean insertPropertyInfo(Properties_Save_Info info){
		boolean value = false;
		
		ArrayList<Vector<String>> totalPram = new ArrayList<Vector<String>>();
		ArrayList<String> totalquery = new ArrayList<String>();
		
		// 1. �⺻ ���� ���� �Է� Query
		String sqlQuery = "INSERT INTO PLASMA.PL_BASIC_INFO (" +
		" PL_BI_DATA_NUM, PL_RAI_ARTCL_NUM, PL_BI_TOP_BRANCH, PL_BI_DATA_BRANCH, PL_BI_IMP_CLASS," +
		" PL_BI_MAIN_PROC, PL_BI_SUB_PROC, PL_BI_EXP_THE_REC, PL_BI_INSERT_USER, PL_BI_INSERT_FLAG," +
		" PL_BI_EXPRESSION, PL_BI_INSERT_DATE)" +
		" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		/*"INSERT INTO PL_BASIC_INFO (" +
		" PL_BI_DATA_NUM, PL_RAI_ARTCL_NUM, PL_BI_INSERT_USER)" +
		" VALUES ( ?, ?, ?)";*/
		
		//ù��° �Է¹� �Ķ���� ����
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(info.getPL_BI_DATA_NUM());
		ve_1.addElement(info.getPL_RAI_ARTCL_NUM());
		ve_1.addElement(info.getPL_BI_TOP_BRANCH());
		ve_1.addElement(info.getPL_BI_DATA_BRANCH());
		ve_1.addElement(info.getPL_BI_IMP_CLASS());
		ve_1.addElement(info.getPL_BI_MAIN_PROC());
		ve_1.addElement(info.getPL_BI_SUB_PROC());
		ve_1.addElement(info.getPL_BI_EXP_THE_REC());
		ve_1.addElement(info.getPL_BI_INSERT_USER());
		ve_1.addElement(info.getPL_BI_INSERT_FLAG());
		ve_1.addElement(info.getPL_BI_EXPRESSION());
		ve_1.addElement(info.getPL_BI_INSERT_DATE());
		totalquery.add(sqlQuery);
		totalPram.add(ve_1);
		
		// 2. �� ���� ���� �Է� Query
		/*String sqlQuery2 = "INSERT INTO PL_BASIC_SPEC_INFO (" +
				" PL_BI_DATA_NUM, PL_BSI_TOP_BRANCH, PL_BSI_DATA_BRANCH," +
				" PL_BSI_IMP_CLASS, PL_BSI_MAIN_PROC, PL_BSI_SUB_PROC, PL_BSI_EXP_THE_REC)" +
				" VALUES ( ?, ?, ?, ?, ?, ?, ?)";
		//�ι�° �Է¹� �Ķ���� ����
		Vector ve_2 = new Vector();
		ve_2.addElement(info.getPL_BI_DATA_NUM());
		ve_2.addElement(info.getPL_BSI_TOP_BRANCH());
		ve_2.addElement(info.getPL_BSI_DATA_BRANCH());
		ve_2.addElement(info.getPL_BSI_IMP_CLASS());
		ve_2.addElement(info.getPL_BSI_MAIN_PROC());
		ve_2.addElement(info.getPL_BSI_SUB_PROC());
		ve_2.addElement(info.getPL_BSI_EXP_THE_REC());
		
		totalquery.add(sqlQuery2);
		totalPram.add(ve_2);*/
		
		// 3. ������ ���� �Է� Query
		String sqlQuery3 = "INSERT INTO PL_BASIC_EQUATION_INFO (" +
				" PL_BI_DATA_NUM, PL_CPBI_ELE_NUM, PL_BEI_SEQ," +
				" PL_BEI_CHG_STATE, PL_BEI_ELC_STATE, PL_BEI_OTH_STRUC)" +
				" VALUES ( ?, ?, ?, ?, ?, ?)";
		//�Ѿ�� �����͸� ��� ����
		Vector<?> ve_equa = info.getPL_EQUA_INFO();
		
		for(int i = 0 ; i < ve_equa.size(); i ++) {
			Properties_Equation_Info equa_obj = (Properties_Equation_Info)ve_equa.get(i);
			Vector<String> ve_3 = new Vector<String>();
			ve_3.addElement(equa_obj.getPL_BI_DATA_NUM());
			ve_3.addElement(equa_obj.getPL_CPBI_ELE_NUM());
			ve_3.addElement(equa_obj.getPL_BEI_SEQ());
			ve_3.addElement(equa_obj.getPL_BEI_CHG_STATE());
			ve_3.addElement(equa_obj.getPL_BEI_ELC_STATE());
			ve_3.addElement(equa_obj.getPL_BEI_OTH_STRUC());
			
			totalquery.add(sqlQuery3);
			totalPram.add(ve_3);
		}
		
		// 4. ÷�� �ڵ� �Է� Query
		String sqlQuery4 = "INSERT INTO PL_BASIC_EQUATION_ADDC_INFO (" +
				" PL_BI_DATA_NUM, PL_BEAI_SEQ, PL_CI_ID)" +
				" VALUES ( ?, ?, ?)";
		Vector<String> ve_4 = new Vector<String>();
		ve_4.addElement(info.getPL_BI_DATA_NUM());
		ve_4.addElement("4");
		ve_4.addElement(info.getPL_CI_ID());
		totalquery.add(sqlQuery4);
		totalPram.add(ve_4);		
		
		value = comSQL.executeTransact(totalquery, totalPram);
		
		// 5. �Է� ��� ����		
		return value;
	}
	
	/**
	 * @MethodName  : updatePropertyExpression
	 * @Date   : 2012. 8. 22. 
	 * @MethodDescription : ǥ���� ������Ʈ �Լ�
	 * @param exp
	 * @return
	 * @History  : - 
	 */
	public boolean updatePropertyExpression(String exp, String pr_no){
		boolean r_value = false;
		
		String sqlQuery = "UPDATE PLASMA.PL_BASIC_INFO SET PL_BI_EXPRESSION = ? WHERE PL_BI_DATA_NUM = ?";
		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(exp);
		ve_1.addElement(pr_no);		
		
		r_value = comSQL.executeTransact(sqlQuery, ve_1);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : insertEquationInfo
	 * @Date   : 2012. 8. 2. 
	 * @MethodDescription : Equation ���� �Է� �޼ҵ�
	 * @param ve_equa
	 * @return
	 * @History  : - 
	 */
	public boolean insertEquationInfo(Vector<?> ve_equa){
		boolean r_value = false;
		
		// 3. ������ ���� �Է� Query
		String sqlQuery = "INSERT INTO PL_BASIC_EQUATION_INFO (" +
				" PL_BI_DATA_NUM, PL_CPBI_ELE_NUM, PL_BEI_SEQ," +
				" PL_BEI_CHG_STATE, PL_BEI_ELC_STATE, PL_BEI_OTH_STRUC)" +
				" VALUES ( ?, ?, ?, ?, ?, ?)";
		
		String countQuery = "  SELECT count(PL_BI_DATA_NUM) " +
				"  FROM PLASMA.PL_BASIC_EQUATION_INFO" +
				"  WHERE PL_BI_DATA_NUM = ? AND PL_BEI_SEQ = ?";
		
		String addCountQuery = "select count(*) from PL_BASIC_EQUATION_ADDC_INFO WHERE PL_BI_DATA_NUM = ?";
		
		//�Ѿ�� �����͸� ��� ����
		//Vector ve_equa = info.getPL_EQUA_INFO();
		
		for(int i = 0 ; i < ve_equa.size(); i ++) {
			Properties_Equation_Info equa_obj = (Properties_Equation_Info)ve_equa.get(i);
			
			System.out.println(">> 1 ");
			
			//ī��Ʈ üũ�Ͽ� ������ �۾����� �ʰ� �������� �Է�
			String pr_no = equa_obj.getPL_BI_DATA_NUM();
			String seq_no = equa_obj.getPL_BEI_SEQ();			
			Vector<String> ve_count = new Vector<String>();		
			ve_count.addElement(pr_no);
			ve_count.addElement(seq_no);
			
			String id_null = equa_obj.getPL_CPBI_ELE_NUM();	
			System.out.println(">> 1-1 ELE_NUM : " + id_null);
			
			if(!"null".equalsIgnoreCase(id_null)){
			
				int count = comSQL.executeSelectOneInt(countQuery, ve_count);
				System.out.println(">> 2 : " + count);
				if(count == 0){ //�ش� ������ ���� ������ �Է�
					
					System.out.println(">> 2-1 Equation ���� �Է� : " + seq_no);
					Vector<String> ve_3 = new Vector<String>();
					ve_3.addElement(equa_obj.getPL_BI_DATA_NUM());
					ve_3.addElement(equa_obj.getPL_CPBI_ELE_NUM());
					ve_3.addElement(equa_obj.getPL_BEI_SEQ());
					ve_3.addElement(equa_obj.getPL_BEI_CHG_STATE());
					ve_3.addElement(equa_obj.getPL_BEI_ELC_STATE());
					ve_3.addElement(equa_obj.getPL_BEI_OTH_STRUC());
					
					r_value = comSQL.executeTransact(sqlQuery, ve_3);				
					
					int addCount = comSQL.executeSelectOneInt(addCountQuery, pr_no);
					
					if(addCount == 0){ //÷���ڵ嵵 ������ �Է���.
						System.out.println(">> 3 ÷���ڵ� �Է�");
						String addCodeQuery = "INSERT INTO PL_BASIC_EQUATION_ADDC_INFO (" +
						" PL_BI_DATA_NUM, PL_BEAI_SEQ, PL_CI_ID)" +
						" VALUES ( ?, ?, ?)";
						
						Vector<String> ve_4 = new Vector<String>();
						ve_4.addElement(equa_obj.getPL_BI_DATA_NUM());
						ve_4.addElement("4");
						ve_4.addElement(equa_obj.getPL_CI_ID());
						
						r_value = comSQL.executeTransact(addCodeQuery, ve_4);
					}
				}
			}
		}
		
		// 5. �Է� ��� ����		
		return r_value;
	}
	
	/**
	 * @MethodName  : insertEquationInfo
	 * @Date   : 2014. 5. 30. 
	 * @MethodDescription : �������� ����
	 * @param equa_obj
	 * @return
	 * @History  : - 
	 */
	public boolean insertEquationInfo(Properties_Equation_Info equa_obj){
		boolean r_value = false;
		
		// 3. ������ ���� �Է� Query
		String sqlQuery = "INSERT INTO PL_BASIC_EQUATION_INFO (" +
				" PL_BI_DATA_NUM, PL_CPBI_ELE_NUM, PL_BEI_SEQ," +
				" PL_BEI_CHG_STATE, PL_BEI_ELC_STATE, PL_BEI_OTH_STRUC)" +
				" VALUES ( ?, ?, ?, ?, ?, ?)";
		
		/*String countQuery = "  SELECT count(PL_BI_DATA_NUM) " +
				"  FROM PLASMA.PL_BASIC_EQUATION_INFO" +
				"  WHERE PL_BI_DATA_NUM = ? AND PL_BEI_SEQ = ?";*/
		
		String addCountQuery = "select count(*) from PL_BASIC_EQUATION_ADDC_INFO WHERE PL_BI_DATA_NUM = ?";
		
		String pr_no = equa_obj.getPL_BI_DATA_NUM();
		String seq_no = equa_obj.getPL_BEI_SEQ();	
		
		//System.out.println(">> 2-1 Equation ���� �Է� : " + seq_no);
		Vector ve_3 = new Vector();
		ve_3.addElement(equa_obj.getPL_BI_DATA_NUM());
		ve_3.addElement(equa_obj.getPL_CPBI_ELE_NUM());
		ve_3.addElement(equa_obj.getPL_BEI_SEQ());
		ve_3.addElement(equa_obj.getPL_BEI_CHG_STATE());
		ve_3.addElement(equa_obj.getPL_BEI_ELC_STATE());
		ve_3.addElement(equa_obj.getPL_BEI_OTH_STRUC());
		
		r_value = comSQL.executeTransact(sqlQuery, ve_3);				
		
		int addCount = comSQL.executeSelectOneInt(addCountQuery, pr_no);
		
		if(addCount == 0){ //÷���ڵ嵵 ������ �Է���.
			//System.out.println(">> 3 ÷���ڵ� �Է�");
			String addCodeQuery = "INSERT INTO PL_BASIC_EQUATION_ADDC_INFO (" +
			" PL_BI_DATA_NUM, PL_BEAI_SEQ, PL_CI_ID)" +
			" VALUES ( ?, ?, ?)";
			
			Vector ve_4 = new Vector();
			ve_4.addElement(equa_obj.getPL_BI_DATA_NUM());
			ve_4.addElement("4");
			ve_4.addElement(equa_obj.getPL_CI_ID());
			
			r_value = comSQL.executeTransact(addCodeQuery, ve_4);
		}else{
			r_value = updateEquationAddCode(pr_no, equa_obj.getPL_CI_ID());
		}
		
		/*System.out.println(">> 1 ");
		
		//ī��Ʈ üũ�Ͽ� ������ �۾����� �ʰ� �������� �Է�
		String pr_no = equa_obj.getPL_BI_DATA_NUM();
		String seq_no = equa_obj.getPL_BEI_SEQ();			
		Vector ve_count = new Vector();		
		ve_count.addElement(pr_no);
		ve_count.addElement(seq_no);
		
		String id_null = equa_obj.getPL_CPBI_ELE_NUM();	
		System.out.println(">> 1-1 ELE_NUM : " + id_null);
		
		if(!"null".equalsIgnoreCase(id_null)){
		
			int count = comSQL.executeSelectOneInt(countQuery, ve_count);
			System.out.println(">> 2 : " + count);
			if(count == 0){ //�ش� ������ ���� ������ �Է�
				
				System.out.println(">> 2-1 Equation ���� �Է� : " + seq_no);
				Vector ve_3 = new Vector();
				ve_3.addElement(equa_obj.getPL_BI_DATA_NUM());
				ve_3.addElement(equa_obj.getPL_CPBI_ELE_NUM());
				ve_3.addElement(equa_obj.getPL_BEI_SEQ());
				ve_3.addElement(equa_obj.getPL_BEI_CHG_STATE());
				ve_3.addElement(equa_obj.getPL_BEI_ELC_STATE());
				ve_3.addElement(equa_obj.getPL_BEI_OTH_STRUC());
				
				r_value = comSQL.executeTransact(sqlQuery, ve_3);				
				
				int addCount = comSQL.executeSelectOneInt(addCountQuery, pr_no);
				
				if(addCount == 0){ //÷���ڵ嵵 ������ �Է���.
					System.out.println(">> 3 ÷���ڵ� �Է�");
					String addCodeQuery = "INSERT INTO PL_BASIC_EQUATION_ADDC_INFO (" +
					" PL_BI_DATA_NUM, PL_BEAI_SEQ, PL_CI_ID)" +
					" VALUES ( ?, ?, ?)";
					
					Vector ve_4 = new Vector();
					ve_4.addElement(equa_obj.getPL_BI_DATA_NUM());
					ve_4.addElement("4");
					ve_4.addElement(equa_obj.getPL_CI_ID());
					
					r_value = comSQL.executeTransact(addCodeQuery, ve_4);
				}
			}
		}
*/
		
		return r_value;
	}
	
	public boolean updateEquationInfo(Properties_Equation_Info equa){
		boolean r_value = false;
		
		// 3. ������ ���� �Է� Query
		String sqlQuery = "UPDATE PLASMA.PL_BASIC_EQUATION_INFO" +
				" SET  PL_CPBI_ELE_NUM  = ?, " +				
				" PL_BEI_CHG_STATE = ?, " +
				" PL_BEI_ELC_STATE = ?, " +
				" PL_BEI_OTH_STRUC = ? WHERE PL_BI_DATA_NUM = ? AND PL_BEI_SEQ = ? ";

		Vector ve_3 = new Vector();
		ve_3.addElement(equa.getPL_CPBI_ELE_NUM());
		ve_3.addElement(equa.getPL_BEI_CHG_STATE());
		ve_3.addElement(equa.getPL_BEI_ELC_STATE());
		ve_3.addElement(equa.getPL_BEI_OTH_STRUC());
		ve_3.addElement(equa.getPL_BI_DATA_NUM());
		ve_3.addElement(equa.getPL_BEI_SEQ());
		
		r_value = comSQL.executeTransact(sqlQuery, ve_3);
		
		return r_value;
	}
	
	public boolean updateEquationAddCode(String pr_no, String ci_id){
		boolean r_value = false;
		
		String sqlQuery = "UPDATE PLASMA.PL_BASIC_EQUATION_ADDC_INFO" +
				" SET PL_BI_DATA_NUM = ?, " +
				" PL_BEAI_SEQ    = ?, " +
				" PL_CI_ID       = ?  WHERE PL_BI_DATA_NUM = ?";
		
		Vector ve_4 = new Vector();		
		ve_4.addElement(pr_no);
		ve_4.addElement("4");
		ve_4.addElement(ci_id);
		ve_4.addElement(pr_no);
		//totalquery.add(sqlQuery4);
		//totalPram.add(ve_4);		
		
		r_value = comSQL.executeTransact(sqlQuery, ve_4);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : deletePropertyInfo
	 * @Date   : 2012. 1. 12. 
	 * @MethodDescription : ���������� Equation ������  �����ϴ� DAO�޼ҵ�
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean deletePropertyInfo(String pr_no){
		boolean r_value = false;
		
		String sql_Query = "delete from PL_BASIC_INFO where PL_BI_DATA_NUM = ?";
		Vector sql_option = new Vector();
		sql_option.addElement(pr_no);
		
		r_value = comSQL.executeTransact(sql_Query, sql_option);
		
		if(r_value == true){
			r_value = deletePropertyEquationInfo(pr_no);
		}
		
		return r_value;
		
	}
	
	/**
	 * @MethodName  : modifyPropertyInsertFlag
	 * @Date   : 2012. 1. 12. 
	 * @MethodDescription : �������� �Է� �÷��׸� �����ϴ� DAO �޼ҵ�
	 * @param flag
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean modifyPropertyInsertFlag(String flag, String pr_no){
		boolean r_value = false;
		String sql_Query = "UPDATE PLASMA.PL_BASIC_INFO SET PL_BI_INSERT_FLAG = ?" +
		" WHERE PL_BI_DATA_NUM = ?";
		Vector sql_option = new Vector();
		sql_option.addElement(flag);
		sql_option.addElement(pr_no);

		r_value = comSQL.executeTransact(sql_Query, sql_option);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : deletePropertyEquationInfo
	 * @Date   : 2012. 1. 13. 
	 * @MethodDescription : ���������� Equation ������ �����ϴ� DAO �޼ҵ�
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean deletePropertyEquationInfo(String pr_no){
		boolean r_value = false;
		
		String s_Query = "delete from PL_BASIC_EQUATION_INFO where PL_BI_DATA_NUM = ?";
		String s_Query2 = "delete from PL_BASIC_EQUATION_ADDC_INFO where PL_BI_DATA_NUM = ?";
		ArrayList sql_Query = new ArrayList();
		sql_Query.add(s_Query);
		sql_Query.add(s_Query2);
		
		Vector sql_option = new Vector();
		sql_option.addElement(pr_no);
		
		r_value = comSQL.executeTransact(sql_Query, sql_option);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : modifyPropertyDB
	 * @Date   : 2012. 1. 20. 
	 * @MethodDescription : �������� �����ͺз� ���� �����ϴ� DAO �޼ҵ�
	 * @param m_value
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean modifyPropertyDB(String m_value, String pr_no){
		boolean r_value = false;
		String sql_Query = "UPDATE PLASMA.PL_BASIC_INFO SET PL_BI_DATA_BRANCH = ?" +
		" WHERE PL_BI_DATA_NUM = ?";
		Vector sql_option = new Vector();
		sql_option.addElement(m_value);
		sql_option.addElement(pr_no);

		r_value = comSQL.executeTransact(sql_Query, sql_option);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : modifyPropertyMP
	 * @Date   : 2012. 1. 20. 
	 * @MethodDescription : �������� �����μ����� �����ϴ� DAO �޼ҵ�
	 * @param m_value
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean modifyPropertyMP(String m_value, String pr_no){
		boolean r_value = false;
		String sql_Query = "UPDATE PLASMA.PL_BASIC_INFO SET PL_BI_MAIN_PROC = ?" +
		" WHERE PL_BI_DATA_NUM = ?";
		Vector sql_option = new Vector();
		sql_option.addElement(m_value);
		sql_option.addElement(pr_no);

		r_value = comSQL.executeTransact(sql_Query, sql_option);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : modifyPropertySP
	 * @Date   : 2012. 1. 20. 
	 * @MethodDescription : �������� �����μ����� �����ϴ� DAO �޼ҵ�
	 * @param m_value
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean modifyPropertySP(String m_value, String pr_no){
		boolean r_value = false;
		String sql_Query = "UPDATE PLASMA.PL_BASIC_INFO SET PL_BI_SUB_PROC = ?" +
		" WHERE PL_BI_DATA_NUM = ?";
		Vector sql_option = new Vector();
		sql_option.addElement(m_value);
		sql_option.addElement(pr_no);

		r_value = comSQL.executeTransact(sql_Query, sql_option);
		
		return r_value;
	}
	/**
	 * @MethodName  : modifyPropertyIC
	 * @Date   : 2012. 2. 8. 
	 * @MethodDescription : �������� �浹��ĸ� �����ϴ� DAO �޼ҵ�
	 * @param m_value
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean modifyPropertyIC(String m_value, String pr_no){
		boolean r_value = false;
		String sql_Query = "UPDATE PLASMA.PL_BASIC_INFO SET PL_BI_IMP_CLASS = ?" +
		" WHERE PL_BI_DATA_NUM = ?";
		Vector sql_option = new Vector();
		sql_option.addElement(m_value);
		sql_option.addElement(pr_no);

		r_value = comSQL.executeTransact(sql_Query, sql_option);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : modifyPropertyEXP_THE_REC
	 * @Date   : 2012. 1. 13. 
	 * @MethodDescription : �������� ���������� �����ϴ� DAO �޼ҵ�
	 * @param m_value
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean modifyPropertyEXP_THE_REC(String m_value, String pr_no){
		boolean r_value = false;
		String sql_Query = "UPDATE PLASMA.PL_BASIC_INFO SET PL_BI_EXP_THE_REC = ?" +
		" WHERE PL_BI_DATA_NUM = ?";
		Vector sql_option = new Vector();
		sql_option.addElement(m_value);
		sql_option.addElement(pr_no);

		r_value = comSQL.executeTransact(sql_Query, sql_option);
		
		return r_value;
	}
}
