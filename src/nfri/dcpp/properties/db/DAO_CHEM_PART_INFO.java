package nfri.dcpp.properties.db;

import java.util.Vector;

import nfri.dcpp.com.db.ComResultSet;
import nfri.dcpp.com.db.ComSQLExecution;
import nfri.dcpp.properties.model.Basic_Part_Info;

/**
 *
 * @Project : dcpp_web
 * @Title : DAO_CHEM_PART_INFO.java
 * @Description : 기본 입자 테이블과 관련된 처리를 하는 DAO 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 11
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class DAO_CHEM_PART_INFO {
	//DB 실행 클래스 선언
	ComSQLExecution comSQL;
	
	//생성자
	public DAO_CHEM_PART_INFO(){
		comSQL = new ComSQLExecution();
	}
	
	/**
	 * @MethodName : selectPartSeq
	 * @Desc : 입자번호를 만들기 위한 Sequence 번호를 DB에서 가져오는 메소드
	 * @return
	 */
	public String selectPartSeq(){
		String sqlQuery = "SELECT PL_PART_SEQ.nextval FROM DUAL";
		String resultStr = comSQL.executeSelectOneStr(sqlQuery);
		return resultStr;
	}
	
	/**
	 * @MethodName : selectPartInfo
	 * @Desc : 입자정보를 DB에서 가져오는 메소드
	 * @return
	 */
	public Vector<Basic_Part_Info> selectPartInfo(){
		Vector<Basic_Part_Info> values = new Vector<Basic_Part_Info>();
		
		String sqlQuery = "SELECT PL_CPBI_ELE_NUM, PL_CPBI_ELE_SYMBOL, PL_CPBI_ELE_NAME," +
		" PL_CPBI_ELE_MASS, PL_CPBI_ELE_TYPE, NVL(PL_CPBI_ELE_AMNUM, '0'), NVL(PL_CPBI_ELE_AMCOUNT, '0')" +
		" FROM PL_CHEM_PART_BASIC_INFO ORDER BY PL_CPBI_ELE_NUM DESC";///" FROM PL_CHEM_PART_BASIC_INFO ORDER BY PL_CPBI_ELE_SYMBOL ASC";
		ComResultSet rs = comSQL.executeSelect(sqlQuery);

		while(rs.next()){	
			Basic_Part_Info part_Info = new Basic_Part_Info();
			part_Info.setPL_CPBI_ELE_NUM(rs.getString(1));
			part_Info.setPL_CPBI_ELE_SYMBOL(rs.getString(2));
			part_Info.setPL_CPBI_ELE_NAME(rs.getString(3));
			part_Info.setPL_CPBI_ELE_MASS(rs.getString(4));
			part_Info.setPL_CPBI_ELE_TYPE(rs.getString(5));
			part_Info.setPL_CPBI_ELE_AMNUM(rs.getString(6));
			part_Info.setPL_CPBI_ELE_AMCOUNT(rs.getString(7));
			values.addElement(part_Info);
		}
		return values;
	}
	
	/**
	 * @MethodName  : selectPartInfo
	 * @Date   : 2013. 7. 15. 
	 * @MethodDescription : DB에서 하나의 파트 정보를 가져오는 메소드
	 * @param part_id
	 * @return
	 * @History  : - 
	 */
	public Basic_Part_Info selectPartInfo(String part_id){
		Vector<String> sqlOption = new Vector<String>();
		sqlOption.addElement(part_id);
		
		String sqlQuery = "SELECT PL_CPBI_ELE_NUM, PL_CPBI_ELE_SYMBOL, PL_CPBI_ELE_NAME," +
		" PL_CPBI_ELE_MASS, PL_CPBI_ELE_TYPE, NVL(PL_CPBI_ELE_AMNUM, '0'), NVL(PL_CPBI_ELE_AMCOUNT, '0')" +
		" FROM PL_CHEM_PART_BASIC_INFO" +
		" WHERE PL_CPBI_ELE_NUM = ?";
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery, sqlOption);
		Basic_Part_Info part_Info = new Basic_Part_Info();
		while(rs.next()){			
			part_Info.setPL_CPBI_ELE_NUM(rs.getString(1));
			part_Info.setPL_CPBI_ELE_SYMBOL(rs.getString(2));
			part_Info.setPL_CPBI_ELE_NAME(rs.getString(3));
			part_Info.setPL_CPBI_ELE_MASS(rs.getString(4));
			part_Info.setPL_CPBI_ELE_TYPE(rs.getString(5));
			part_Info.setPL_CPBI_ELE_AMNUM(rs.getString(6));
			part_Info.setPL_CPBI_ELE_AMCOUNT(rs.getString(7));
		}
		return part_Info;
	}
	
	/**
	 * @MethodName  : selectPartInfo
	 * @Date   : 2013. 7. 15. 
	 * @MethodDescription : Symbol을 비교하여 DB 정보를 가져오는 메소드
	 * @param sqlOption
	 * @return
	 * @History  : - 
	 */
	public Vector<Basic_Part_Info> selectPartInfo(Vector<String> sqlOption){
		Vector<Basic_Part_Info> values = new Vector<Basic_Part_Info>();
		
		String sqlQuery = "SELECT PL_CPBI_ELE_NUM, PL_CPBI_ELE_SYMBOL, PL_CPBI_ELE_NAME," +
		" PL_CPBI_ELE_MASS, PL_CPBI_ELE_TYPE, NVL(PL_CPBI_ELE_AMNUM, '0'), NVL(PL_CPBI_ELE_AMCOUNT, '0')" +
		" FROM PL_CHEM_PART_BASIC_INFO" +
		" WHERE PL_CPBI_ELE_SYMBOL LIKE ?" +
		" ORDER BY PL_CPBI_ELE_NUM";
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery, sqlOption);
		
		while(rs.next()){	
			Basic_Part_Info part_Info = new Basic_Part_Info();
			part_Info.setPL_CPBI_ELE_NUM(rs.getString(1));
			part_Info.setPL_CPBI_ELE_SYMBOL(rs.getString(2));
			part_Info.setPL_CPBI_ELE_NAME(rs.getString(3));
			part_Info.setPL_CPBI_ELE_MASS(rs.getString(4));
			part_Info.setPL_CPBI_ELE_TYPE(rs.getString(5));
			part_Info.setPL_CPBI_ELE_AMNUM(rs.getString(6));
			part_Info.setPL_CPBI_ELE_AMCOUNT(rs.getString(7));
			values.addElement(part_Info);
		}
		return values;
	}
	
	/**
	 * @MethodName : selectPartName
	 * @Desc : 입자 명을 DB에서 가져오는 메소드
	 * @param part_num
	 * @return
	 */
	public String selectPartName(String part_num){		
		Vector<String> queryOption = new Vector<String>();
		queryOption.addElement(part_num);
		
		String sqlQuery = "SELECT PL_CPBI_ELE_NAME" +
				" FROM PL_CHEM_PART_BASIC_INFO WHERE PL_CPBI_ELE_NUM = ?";
		
		String result = comSQL.executeSelectOneStr(sqlQuery, queryOption);		
		
		return result;
	}
	
	/**
	 * @MethodName : insertPartInfo
	 * @Desc : 입자정보를 DB에 저장하는 메소드
	 * @param partInfo
	 * @return
	 */
	public boolean insertPartInfo(Basic_Part_Info partInfo){
		boolean value = false;
		String sqlQuery = "INSERT INTO PLASMA.PL_CHEM_PART_BASIC_INFO " +
		"( PL_CPBI_ELE_NUM, PL_CPBI_ELE_SYMBOL, PL_CPBI_ELE_NAME," +
		" PL_CPBI_ELE_MASS, PL_CPBI_ELE_TYPE, PL_CPBI_ELE_AMNUM, PL_CPBI_ELE_AMCOUNT )" +
		" VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		value = comSQL.executeTransact(sqlQuery, partInfo.getDataList());
		
		return value;
	}
	
	/**
	 * @MethodName  : updatePartInfo
	 * @Date   : 2013. 7. 8. 
	 * @MethodDescription : 입자정보를 DB에 업데이트 하는 메소드
	 * @param partInfo
	 * @return
	 * @History  : - 
	 */
	public boolean updatePartInfo(Basic_Part_Info partInfo){
		boolean value = false;
		/*String sqlQuery = "INSERT INTO PLASMA.PL_CHEM_PART_BASIC_INFO " +
		"( PL_CPBI_ELE_NUM, PL_CPBI_ELE_SYMBOL, PL_CPBI_ELE_NAME," +
		" PL_CPBI_ELE_MASS, PL_CPBI_ELE_TYPE, PL_CPBI_ELE_AMNUM, PL_CPBI_ELE_AMCOUNT)" +
		" VALUES (?, ?, ?, ?, ?, ?, ?)";*/
		String sqlQuery = "UPDATE PLASMA.PL_CHEM_PART_BASIC_INFO" +
				" SET PL_CPBI_ELE_SYMBOL = ?," +
				" PL_CPBI_ELE_NAME = ?,"+
				" PL_CPBI_ELE_MASS = ?,"+
				" PL_CPBI_ELE_TYPE = ?,"+
				" PL_CPBI_ELE_AMNUM = ?,"+
				" PL_CPBI_ELE_AMCOUNT = ?"+
				" WHERE PL_CPBI_ELE_NUM = ?";
		
		value = comSQL.executeTransact(sqlQuery, partInfo.getDataList());
		
		return value;
	}
	
	/**
	 * @MethodName  : insertPartCountInfo
	 * @Date   : 2013. 7. 8. 
	 * @MethodDescription : 입자정보의 원자/분자 개수를 입력하는 메소드
	 * @param count
	 * @param ele_num
	 * @return
	 * @History  : - 
	 */
	public boolean insertPartCountInfo(String count, String ele_num){
		boolean value = false;
		/*String sqlQuery = "INSERT INTO PLASMA.PL_CHEM_PART_BASIC_INFO " +
		"( PL_CPBI_ELE_NUM, PL_CPBI_ELE_SYMBOL, PL_CPBI_ELE_NAME," +
		" PL_CPBI_ELE_MASS, PL_CPBI_ELE_TYPE, PL_CPBI_ELE_AMNUM, PL_CPBI_ELE_AMCOUNT)" +
		" VALUES (?, ?, ?, ?, ?, ?, ?)";*/
		Vector<String> sqlOption = new Vector<String>();
		sqlOption.addElement(count);
		sqlOption.addElement(ele_num);
		
		String sqlQuery = "INSERT INTO PLASMA.PL_CHEM_PART_BASIC_INFO " +
		"(PL_CPBI_ELE_AMCOUNT)" +
		" VALUES (?)" +
		" WHERE PL_CPBI_ELE_NUM = ?";
		
		value = comSQL.executeTransact(sqlQuery, sqlOption);
		
		return value;
	}

	/**
	 * @MethodName  : updatePartSymbolInfo
	 * @Date   : 2013. 7. 18. 
	 * @MethodDescription : 입자 기호 업데이트 하는 메소드
	 * @param option_query
	 * @return
	 * @History  : - 
	 */
	public boolean updatePartSymbolInfo(Vector<String> sqlOption) {
		boolean value = false;
		/*String sqlQuery = "INSERT INTO PLASMA.PL_CHEM_PART_BASIC_INFO " +
		"( PL_CPBI_ELE_NUM, PL_CPBI_ELE_SYMBOL, PL_CPBI_ELE_NAME," +
		" PL_CPBI_ELE_MASS, PL_CPBI_ELE_TYPE, PL_CPBI_ELE_AMNUM, PL_CPBI_ELE_AMCOUNT)" +
		" VALUES (?, ?, ?, ?, ?, ?, ?)";*/
		String sqlQuery = "UPDATE PLASMA.PL_CHEM_PART_BASIC_INFO" +			
				" SET PL_CPBI_ELE_SYMBOL = ?" +
				" WHERE PL_CPBI_ELE_NUM = ?";
		
		value = comSQL.executeTransact(sqlQuery, sqlOption);
		
		return value;

	}
	
	/**
	 * @MethodName  : updatePartNameInfo
	 * @Date   : 2013. 7. 18. 
	 * @MethodDescription : 입자 이름을 업데이트 하는 메소드
	 * @param sqlOption
	 * @return
	 * @History  : - 
	 */
	public boolean updatePartNameInfo(Vector<String> sqlOption) {
		boolean value = false;
		/*String sqlQuery = "INSERT INTO PLASMA.PL_CHEM_PART_BASIC_INFO " +
		"( PL_CPBI_ELE_NUM, PL_CPBI_ELE_SYMBOL, PL_CPBI_ELE_NAME," +
		" PL_CPBI_ELE_MASS, PL_CPBI_ELE_TYPE, PL_CPBI_ELE_AMNUM, PL_CPBI_ELE_AMCOUNT)" +
		" VALUES (?, ?, ?, ?, ?, ?, ?)";*/
		String sqlQuery = "UPDATE PLASMA.PL_CHEM_PART_BASIC_INFO" +			
				" SET PL_CPBI_ELE_NAME = ?" +
				" WHERE PL_CPBI_ELE_NUM = ?";
		
		value = comSQL.executeTransact(sqlQuery, sqlOption);
		
		return value;

	}
	
	/**
	 * @MethodName  : updatePartMassInfo
	 * @Date   : 2013. 7. 18. 
	 * @MethodDescription : 입자 질량을 업데이트 하는 메소드
	 * @param sqlOption
	 * @return
	 * @History  : - 
	 */
	public boolean updatePartMassInfo(Vector<String> sqlOption) {
		boolean value = false;
		/*String sqlQuery = "INSERT INTO PLASMA.PL_CHEM_PART_BASIC_INFO " +
		"( PL_CPBI_ELE_NUM, PL_CPBI_ELE_SYMBOL, PL_CPBI_ELE_NAME," +
		" PL_CPBI_ELE_MASS, PL_CPBI_ELE_TYPE, PL_CPBI_ELE_AMNUM, PL_CPBI_ELE_AMCOUNT)" +
		" VALUES (?, ?, ?, ?, ?, ?, ?)";*/
		String sqlQuery = "UPDATE PLASMA.PL_CHEM_PART_BASIC_INFO" +			
				" SET PL_CPBI_ELE_MASS = ?" +
				" WHERE PL_CPBI_ELE_NUM = ?";
		
		value = comSQL.executeTransact(sqlQuery, sqlOption);
		
		return value;

	}
	
	/**
	 * @MethodName  : updatePartTypeInfo
	 * @Date   : 2013. 7. 18. 
	 * @MethodDescription : 입자 유형을 업데이트 하는 메소드
	 * @param sqlOption
	 * @return
	 * @History  : - 
	 */
	public boolean updatePartTypeInfo(Vector<String> sqlOption) {
		boolean value = false;
		/*String sqlQuery = "INSERT INTO PLASMA.PL_CHEM_PART_BASIC_INFO " +
		"( PL_CPBI_ELE_NUM, PL_CPBI_ELE_SYMBOL, PL_CPBI_ELE_NAME," +
		" PL_CPBI_ELE_MASS, PL_CPBI_ELE_TYPE, PL_CPBI_ELE_AMNUM, PL_CPBI_ELE_AMCOUNT)" +
		" VALUES (?, ?, ?, ?, ?, ?, ?)";*/
		String sqlQuery = "UPDATE PLASMA.PL_CHEM_PART_BASIC_INFO" +			
				" SET PL_CPBI_ELE_TYPE = ?" +
				" WHERE PL_CPBI_ELE_NUM = ?";
		
		value = comSQL.executeTransact(sqlQuery, sqlOption);
		
		return value;

	}
	
	/**
	 * @MethodName  : updatePartAmnumInfo
	 * @Date   : 2013. 7. 18. 
	 * @MethodDescription : 원자 번호를 업데이트 하는 메소드
	 * @param sqlOption
	 * @return
	 * @History  : - 
	 */
	public boolean updatePartAmnumInfo(Vector<String> sqlOption) {
		boolean value = false;
		/*String sqlQuery = "INSERT INTO PLASMA.PL_CHEM_PART_BASIC_INFO " +
		"( PL_CPBI_ELE_NUM, PL_CPBI_ELE_SYMBOL, PL_CPBI_ELE_NAME," +
		" PL_CPBI_ELE_MASS, PL_CPBI_ELE_TYPE, PL_CPBI_ELE_AMNUM, PL_CPBI_ELE_AMCOUNT)" +
		" VALUES (?, ?, ?, ?, ?, ?, ?)";*/
		String sqlQuery = "UPDATE PLASMA.PL_CHEM_PART_BASIC_INFO" +			
				" SET PL_CPBI_ELE_AMNUM = ?" +
				" WHERE PL_CPBI_ELE_NUM = ?";
		
		value = comSQL.executeTransact(sqlQuery, sqlOption);
		
		return value;

	}
	
	/**
	 * @MethodName  : updatePartAmcountInfo
	 * @Date   : 2013. 7. 18. 
	 * @MethodDescription : 입자 개수를 업데이트 하는 메소드
	 * @param sqlOption
	 * @return
	 * @History  : - 
	 */
	public boolean updatePartAmcountInfo(Vector<String> sqlOption) {
		boolean value = false;
		/*String sqlQuery = "INSERT INTO PLASMA.PL_CHEM_PART_BASIC_INFO " +
		"( PL_CPBI_ELE_NUM, PL_CPBI_ELE_SYMBOL, PL_CPBI_ELE_NAME," +
		" PL_CPBI_ELE_MASS, PL_CPBI_ELE_TYPE, PL_CPBI_ELE_AMNUM, PL_CPBI_ELE_AMCOUNT)" +
		" VALUES (?, ?, ?, ?, ?, ?, ?)";*/
		String sqlQuery = "UPDATE PLASMA.PL_CHEM_PART_BASIC_INFO" +			
				" SET PL_CPBI_ELE_AMCOUNT = ?" +
				" WHERE PL_CPBI_ELE_NUM = ?";
		
		value = comSQL.executeTransact(sqlQuery, sqlOption);
		
		return value;

	}

}
