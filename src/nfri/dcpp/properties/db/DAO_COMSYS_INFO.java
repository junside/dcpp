package nfri.dcpp.properties.db;

import nfri.dcpp.com.db.ComSQLExecution;

/**
 *
 * @Project : dcpp_web
 * @Title : DAO_COMSYS_INFO.java
 * @Description : �ý��۰� ���õ� �������� �͵��� �������� DAO Ŭ����
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 09
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @�����̷� : -
 *
 */
public class DAO_COMSYS_INFO {
	//DB ���� Ŭ���� ����
	ComSQLExecution comSQL;
	
	public DAO_COMSYS_INFO(){
		comSQL = new ComSQLExecution();
	}
	
	/**
	 * @MethodName : selectCurYear
	 * @Desc : 
	 * @return
	 */
	public int selectCurYear(){
		String sqlQuery = "SELECT TO_NUMBER(EXTRACT(YEAR FROM SYSDATE)) FROM DUAL";
		int resultInt = comSQL.executeSelectOneInt(sqlQuery);
		return resultInt;
	}
	
}
