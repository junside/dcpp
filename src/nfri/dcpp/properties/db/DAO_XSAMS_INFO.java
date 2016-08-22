/**
 * 
 */
package nfri.dcpp.properties.db;

import java.util.ArrayList;
import java.util.Vector;

import nfri.dcpp.com.db.ComResultSet;
import nfri.dcpp.com.db.ComSQLExecution;
import nfri.dcpp.properties.model.Basic_Part_Info;
import nfri.dcpp.properties.model.Part_Inchikey_Info;

/**
 * @Project: dcpp
 * @Title  : DAO_XSAMS_INFO.java
 * @Description : -
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2014. 5. 13.
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class DAO_XSAMS_INFO {
	//DB 실행 클래스 선언
	ComSQLExecution comSQL;
	/**
	 * 
	 */
	public DAO_XSAMS_INFO() {
		// TODO Auto-generated constructor stub
		comSQL = new ComSQLExecution();
	}

	/**
	 * @MethodName  : insertInchiKeyExcelData
	 * @Date   : 2014. 5. 14. 
	 * @MethodDescription : Excel 형태의 InchiKey 파일을 저장하는 DAO 메소드
	 * @param info
	 * @return
	 * @History  : - 
	 */
	public boolean insertInchiKeyExcelData(ArrayList<?> info){
		boolean value = false;
		String sqlQuery = "INSERT INTO PLASMA.PL_CHEM_PART_INCHI_INFO ("
				+ "PL_CPBI_ELE_NUM, PL_CPBI_ELE_SYMBOL, PL_BEI_CHG_STATE, "
				+ "PL_CPII_ION_CHARGE, PL_CPII_TXT_FOMULA, PL_CPII_NFRI_FOMULA, "
				+ "PL_CPII_INCHI, PL_CPII_INCHI_KEY, PL_CPII_STOCHIOMETRIC, "
				+ "PL_BEI_ELC_STATE, PL_BEI_OTH_STRUC) "
				+ "VALUES ( ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?)";
		
		value = comSQL.executeTransact(sqlQuery, info);
		
		return value;
	}
	
	/**
	 * @MethodName  : selectInchiKeyInfo
	 * @Date   : 2014. 6. 12. 
	 * @MethodDescription : inchi key를 조회하는 DAO 메소드
	 * @param sqlOption
	 * @return
	 * @History  : - 
	 */
	public Part_Inchikey_Info selectInchiKeyInfo(Vector<String> sqlOption){		
		
		String sqlQuery = "SELECT P.PL_CPBI_ELE_NUM, P.PL_CPBI_ELE_SYMBOL, P.PL_BEI_CHG_STATE,"
				+ " P.PL_CPII_ION_CHARGE, P.PL_CPII_TXT_FOMULA, P.PL_CPII_NFRI_FOMULA,"
				+ " P.PL_CPII_INCHI, P.PL_CPII_INCHI_KEY, P.PL_CPII_STOCHIOMETRIC,"
				+ " P.PL_BEI_ELC_STATE, P.PL_BEI_OTH_STRUC"
				+ " FROM PLASMA.PL_CHEM_PART_INCHI_INFO P"
				+ " WHERE P.PL_CPBI_ELE_NUM= ? and P.PL_BEI_CHG_STATE = ? ";
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery, sqlOption);
		Part_Inchikey_Info inchi_Info = new Part_Inchikey_Info();
		
		while(rs.next()){	
			//Part_Inchikey_Info inchi_Info = new Part_Inchikey_Info();
			inchi_Info.setPL_CPBI_ELE_NUM(rs.getString(1));
			inchi_Info.setPL_CPBI_ELE_SYMBOL(rs.getString(2));
			inchi_Info.setPL_BEI_CHG_STATE(rs.getString(3));
			inchi_Info.setPL_CPII_ION_CHARGE(rs.getString(4));
			inchi_Info.setPL_CPII_TXT_FOMULA(rs.getString(5));
			inchi_Info.setPL_CPII_NFRI_FOMULA(rs.getString(6));
			inchi_Info.setPL_CPII_INCHI(rs.getString(7));
			inchi_Info.setPL_CPII_INCHI_KEY(rs.getString(8));
			inchi_Info.setPL_CPII_STOCHIOMETRIC(rs.getString(9));
			inchi_Info.setPL_BEI_ELC_STATE(rs.getString(10));
			inchi_Info.setPL_BEI_OTH_STRUC(rs.getString(11));
		}
		return inchi_Info;
	}
}
