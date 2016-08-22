package nfri.dcpp.properties.db;

import java.util.Vector;

import nfri.dcpp.com.db.ComResultSet;
import nfri.dcpp.com.db.ComSQLExecution;
import nfri.dcpp.properties.model.Code_Info;

/**
 *
 * @Project : dcpp_web
 * @Title : DAO_CODE_INFO.java
 * @Description : CODE 테이블과 관련된 항목을 처리하는 DAO 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 09
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class DAO_CODE_INFO {
	
	//DB 실행 클래스 선언
	ComSQLExecution comSQL;

	public DAO_CODE_INFO(){
		comSQL = new ComSQLExecution();
	}
	
	public Vector<Code_Info> selectOptionList(String ocode){
		Vector<Code_Info> values = new Vector<Code_Info>();
		String sqlQuery = "SELECT PL_CI_ID," +
						" PL_CI_NAME," +
						" PL_CI_ID_EXP," +
						" PL_CI_GROUP " +
						"FROM PL_CODE_INFO WHERE PL_CI_GROUP = '"+ ocode +"' ORDER BY PL_CI_ID";	
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery);

		while(rs.next()){				
			Code_Info code = new Code_Info();
			code.setPL_CI_ID(rs.getString(1));
			code.setPL_CI_NAME(rs.getString(2));
			code.setPL_CI_ID_EXP(rs.getString(3));
			code.setPL_CI_GROUP(rs.getString(4));
			/*code.setPL_CI_ID(rs.getString("PL_CI_ID"));
			code.setPL_CI_NAME(rs.getString("PL_CI_NAME"));
			code.setPL_CI_ID_EXP(rs.getString("PL_CI_ID_EXP"));
			code.setPL_CI_GROUP(rs.getString("PL_CI_GROUP"));*/
			values.addElement(code);
		}
		return values;
	}
		
	/**
	 * @MethodName : executeSelectAMName
	 * @Desc : 코드 ID에 대한 이름 값을 가져오는 메소드
	 * @param code
	 * @return
	 */
	public String selectOptionName(String code){
		String sqlQuery = "SELECT PL_CI_NAME " +
						"FROM PL_CODE_INFO WHERE PL_CI_ID = '"+ code +"'";			
		String rs = comSQL.executeSelectOneStr(sqlQuery);	
		return rs;
	}
	
	/**
	 * @MethodName : selectOptionExp
	 * @Desc : 코드 ID에 대한 이름 설명을 가져오는 메소드
	 * @param code
	 * @return
	 */
	public String selectOptionExp(String code){
		String sqlQuery = "SELECT PL_CI_ID_EXP " +
						"FROM PL_CODE_INFO WHERE PL_CI_ID = '"+ code +"'";			
		String rs = comSQL.executeSelectOneStr(sqlQuery);	
		return rs;
	}	
}
