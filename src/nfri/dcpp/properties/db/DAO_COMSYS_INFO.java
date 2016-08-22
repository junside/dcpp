package nfri.dcpp.properties.db;

import nfri.dcpp.com.db.ComSQLExecution;

/**
 *
 * @Project : dcpp_web
 * @Title : DAO_COMSYS_INFO.java
 * @Description : 시스템과 관련된 공통적인 것들을 가져오는 DAO 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 09
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class DAO_COMSYS_INFO {
	//DB 실행 클래스 선언
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
