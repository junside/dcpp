package nfri.dcpp.properties.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.oreilly.servlet.MultipartRequest;

import nfri.dcpp.com.db.ComResultSet;
import nfri.dcpp.com.db.ComSQLExecution;
import nfri.dcpp.com.util.ComUtil;
import nfri.dcpp.com.util.ComVar;
import nfri.dcpp.properties.model.Article_Code;
import nfri.dcpp.properties.model.Article_Info;

public class DAO_REF_ARTICLE_INFO {
	//DB ���� Ŭ���� ����
	ComSQLExecution comSQL;
	
	//������
	public DAO_REF_ARTICLE_INFO(){
		comSQL = new ComSQLExecution();
	}
	
	/**
	 * @MethodName : selectFirstYear
	 * @Desc : ������ ���̺��� ���� ����Ǿ� �ִ� ���� ������ �⵵�� �������� �޼ҵ�
	 * @return
	 */
	public int selectFirstYear(){
		String sqlQuery = "SELECT NVL(MIN(PL_RAI_JOUR_YEAR),'-') FROM PL_REF_ARTICLE_INFO";
		String resultStr = comSQL.executeSelectOneStr(sqlQuery);
		int resultInt = 0;
		if(resultStr.equalsIgnoreCase("-")){
			resultInt = 1900;
		}else{
			resultInt = Integer.parseInt(resultStr);
		}		
		return resultInt;
	}
	
	/**
	 * @MethodName : selectArticleSeq
	 * @Desc : ������ ��ȣ�� ����� ���� Sequence ��ȣ�� DB���� �������� �޼ҵ�
	 * @return
	 */
	public String selectArticleSeq(){
		String sqlQuery = "SELECT PL_ARTCL_SEQ.nextval FROM DUAL";
		String resultStr = comSQL.executeSelectOneStr(sqlQuery);
		return resultStr;
	}
	
	/**
	 * @MethodName  : selectArticleSaveYearInfo
	 * @Date   : 2011. 4. 6. 
	 * @MethodDescription : ����� ������ �Է³⵵�� DB���� �������� �޼ҵ�
	 * @return
	 * @History  : - 
	 */
	public String selectArticleSaveYearInfo(){
		String r_value = "";
		
		String sqlQuery = "SELECT P.PYI_ARTICLE_YEAR FROM PLASMA.PL_YEAR_INFO P";
		r_value = comSQL.executeSelectOneStr(sqlQuery);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : insertArticleSaveYearInfo
	 * @Date   : 2011. 4. 6. 
	 * @MethodDescription : DB�� ������ �Է³⵵ �Է��ϱ�
	 * @param year
	 * @return
	 * @History  : - 
	 */
	public boolean insertArticleSaveYearInfo(String year){
		boolean r_value = false;
		
		String sqlQuery = "INSERT INTO PLASMA.PL_YEAR_INFO (PYI_ARTICLE_YEAR) VALUES ( ? )";
		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(year);
		
		r_value = comSQL.executeTransact(sqlQuery, ve_1);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : updateArticleSaveYearInfo
	 * @Date   : 2011. 4. 6. 
	 * @MethodDescription : DB�� ������ �Է³⵵ ������Ʈ�ϱ�
	 * @param year
	 * @return
	 * @History  : - 
	 */
	public boolean updateArticleSaveYearInfo(String year){
		boolean r_value = false;
		
		String sqlQuery = "UPDATE PLASMA.PL_YEAR_INFO SET PYI_ARTICLE_YEAR = ?";
		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(year);
		
		r_value = comSQL.executeTransact(sqlQuery, ve_1);
		
		return r_value;
	}
	
	public boolean updateArticleDoiNumber(String article_number, String doi_number){
		boolean r_value = false;
		
		String sqlQuery = "UPDATE PL_REF_ARTICLE_INFO SET PL_RAI_ARTCL_DOI = ? WHERE PL_RAI_ARTCL_NUM = ?";
		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(doi_number);
		ve_1.addElement(article_number);
		
		r_value = comSQL.executeTransact(sqlQuery, ve_1);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : resetArticleSeq
	 * @Date   : 2011. 4. 6. 
	 * @MethodDescription : ������ �����ϴ� SEQ �����ϱ�
	 * @return
	 * @History  : - 
	 */
	public boolean resetArticleSeq(){
		boolean r_value = false;
		
		String sqlDrop = "DROP SEQUENCE PLASMA.PL_ARTCL_SEQ";

		String sqlCreate = "CREATE SEQUENCE PLASMA.PL_ARTCL_SEQ START WITH 1" +
				" MAXVALUE 100000000 MINVALUE 1" +
				" CYCLE NOCACHE ORDER";
		
		String sqlQuery = "ALTER SEQUENCE PLASMA.PL_ARTCL_SEQ" +
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
	 * @MethodName : selectArticleCode
	 * @Desc : ������ ���� �ڵ带 DB���� �������� �޼ҵ�
	 * @return
	 */
	public Vector<Article_Code> selectArticleCode(){
		Vector<Article_Code> values = new Vector<Article_Code>();
		String sqlQuery = "SELECT PL_RACI_CODE_ID, PL_RACI_CODE_EXP FROM PL_REF_ARTICLE_CODE_INFO";	
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery);

		while(rs.next()){		
			Article_Code artCode = new Article_Code();
			artCode.setPL_RACI_CODE_ID(rs.getString(1));
			artCode.setPL_RACI_CODE_EXP(rs.getString(2));	
			values.addElement(artCode);
		}
		return values;
	}
	
	/**
	 * @MethodName  : selectArticleInfo
	 * @Date   : 2011. 3. 2. 
	 * @MethodDescription : ������ ������ �����ֱ� ���� �����͸� DB���� �������� �޼ҵ�
	 * @param sqlOption
	 * @return
	 * @History  : - 
	 */
	public Article_Info selectArticleInfo(Vector<?> sqlOption){	
		
		String sqlQuery = "SELECT A.PL_RAI_ARTCL_NUM, A.PL_RAI_ARTCL_TITLE, A.PL_RAI_ARTCL_AUTH_FNAME," +
				" A.PL_RAI_JOUR_TITLE, NVL(A.PL_RAI_ISSN_P, '-'), NVL(A.PL_RAI_ISSN_N,'-')," +
				" NVL(A.PL_RAI_ISBN, '-'), NVL(A.PL_RAI_NDSL, '-'), A.PL_RAI_JOUR_VOL," +
				" NVL(A.PL_RAI_JOUR_NUM, '-'), A.PL_RAI_ARTCL_ST_PAGE, A.PL_RAI_JOUR_YEAR," +
				" NVL(A.PL_RAI_ARTCL_ED_PAGE, '-'), C.PL_RACI_CODE_EXP, A.PL_RAI_ORG_FILE_PATH," +
				" A.PL_RAI_ORG_FILE_NAME, A.PL_RAI_ORG_FILE_EXT, A.PL_RAI_INSERT_TIME, A.PL_RAI_ARTCL_AUTH_ENAME," +
				" C.PL_RACI_CODE_EXP_ENG, NVL(A.PL_RAI_ARTCL_DOI, '-')" +
				" FROM PL_REF_ARTICLE_INFO A, PL_REF_ARTICLE_CODE_INFO C" +
				" WHERE A.PL_RACI_CODE_ID = C.PL_RACI_CODE_ID AND A.PL_RAI_ARTCL_NUM = ? ";
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery, sqlOption);
		Article_Info artInfo = new Article_Info();
		while(rs.next()){					
			artInfo.setPL_RAI_ARTCL_NUM(rs.getString(1));
			artInfo.setPL_RAI_ARTCL_TITLE(rs.getString(2));
			artInfo.setPL_RAI_ARTCL_AUTH_FNAME(rs.getString(3));
			artInfo.setPL_RAI_JOUR_TITLE(rs.getString(4));
			artInfo.setPL_RAI_ISSN_P(rs.getString(5));
			artInfo.setPL_RAI_ISSN_N(rs.getString(6));
			artInfo.setPL_RAI_ISBN(rs.getString(7));
			artInfo.setPL_RAI_NDSL(rs.getString(8));
			artInfo.setPL_RAI_JOUR_VOL(rs.getString(9));
			artInfo.setPL_RAI_JOUR_NUM(rs.getString(10));
			artInfo.setPL_RAI_ARTCL_ST_PAGE(rs.getString(11));
			artInfo.setPL_RAI_JOUR_YEAR(rs.getString(12));
			artInfo.setPL_RAI_ARTCL_ED_PAGE(rs.getString(13));
			artInfo.setPL_RACI_CODE_ID(rs.getString(14));
			artInfo.setPL_RAI_ORG_FILE_PATH(rs.getString(15));
			artInfo.setPL_RAI_ORG_FILE_NAME(rs.getString(16));
			artInfo.setPL_RAI_ORG_FILE_EXT(rs.getString(17));
			artInfo.setPL_RAI_INSERT_TIME(rs.getString(18));	
			artInfo.setPL_RAI_ARTCL_AUTH_ENAME(rs.getString(19));
			artInfo.setPL_RACI_CODE_EXP_ENG(rs.getString(20));
			artInfo.setPL_RAI_ARTCL_DOI(rs.getString(21));
		}
		
		return artInfo;
	}

	/**
	 * @MethodName  : selectArticleInfo
	 * @Date   : 2011. 7. 13. 
	 * @MethodDescription : ������ ��ü ������ DB���� �������� �޼ҵ�
	 * @return
	 * @History  : - 
	 */
	public Vector<Article_Info> selectArticleInfo(){
		Vector<Article_Info> values = new Vector<Article_Info>();
		
		String sqlQuery = "SELECT PL_RAI_ARTCL_NUM, PL_RAI_ARTCL_TITLE, PL_RAI_ARTCL_AUTH_FNAME," +
		" PL_RAI_JOUR_TITLE, NVL(PL_RAI_ISSN_P, '-'), NVL(PL_RAI_ISSN_N, '-'), NVL(PL_RAI_ISBN, '-'), NVL( PL_RAI_NDSL, '-')," +
		" PL_RAI_JOUR_VOL, NVL(PL_RAI_JOUR_NUM, '-'), PL_RAI_ARTCL_ST_PAGE, PL_RAI_JOUR_YEAR," +
		" NVL(PL_RAI_ARTCL_ED_PAGE, '-'), PL_RACI_CODE_ID, PL_RAI_ORG_FILE_PATH," +
		" PL_RAI_ORG_FILE_NAME, PL_RAI_ORG_FILE_EXT, PL_RAI_INSERT_TIME, PL_RAI_ARTCL_AUTH_ENAME, NVL(PL_RAI_ARTCL_DOI, '-')" +
		" FROM PL_REF_ARTICLE_INFO ORDER BY PL_RAI_ARTCL_NUM";
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery);

		while(rs.next()){		
			Article_Info artInfo = new Article_Info();
			artInfo.setPL_RAI_ARTCL_NUM(rs.getString(1));
			artInfo.setPL_RAI_ARTCL_TITLE(rs.getString(2));
			artInfo.setPL_RAI_ARTCL_AUTH_FNAME(rs.getString(3));
			artInfo.setPL_RAI_JOUR_TITLE(rs.getString(4));
			artInfo.setPL_RAI_ISSN_P(rs.getString(5));
			artInfo.setPL_RAI_ISSN_N(rs.getString(6));
			artInfo.setPL_RAI_ISBN(rs.getString(7));
			artInfo.setPL_RAI_NDSL(rs.getString(8));
			artInfo.setPL_RAI_JOUR_VOL(rs.getString(9));
			artInfo.setPL_RAI_JOUR_NUM(rs.getString(10));
			artInfo.setPL_RAI_ARTCL_ST_PAGE(rs.getString(11));
			artInfo.setPL_RAI_JOUR_YEAR(rs.getString(12));
			artInfo.setPL_RAI_ARTCL_ED_PAGE(rs.getString(13));
			artInfo.setPL_RACI_CODE_ID(rs.getString(14));
			artInfo.setPL_RAI_ORG_FILE_PATH(rs.getString(15));
			artInfo.setPL_RAI_ORG_FILE_NAME(rs.getString(16));
			artInfo.setPL_RAI_ORG_FILE_EXT(rs.getString(17));
			artInfo.setPL_RAI_INSERT_TIME(rs.getString(18));
			artInfo.setPL_RAI_ARTCL_AUTH_ENAME(rs.getString(19));
			artInfo.setPL_RAI_ARTCL_DOI(rs.getString(20));
			values.addElement(artInfo);
		}
		
		return values;
	}

	/**
	 * @MethodName  : selectArticleInfo
	 * @Date   : 2011. 7. 13. 
	 * @MethodDescription : �ɼǿ� ���� ���� ����Ʈ�� �������� �޼ҵ�
	 * @param optionquery
	 * @return
	 * @History  : - 
	 */
	public Vector<Article_Info> selectArticleInfo(String optionquery){
		Vector<Article_Info> values = new Vector<Article_Info>();
		
		String sqlQuery = "SELECT PL_RAI_ARTCL_NUM, PL_RAI_ARTCL_TITLE, PL_RAI_ARTCL_AUTH_FNAME," +
		" PL_RAI_JOUR_TITLE, NVL(PL_RAI_ISSN_P, '-'), NVL(PL_RAI_ISSN_N, '-'), NVL(PL_RAI_ISBN, '-'), NVL( PL_RAI_NDSL, '-')," +
		" PL_RAI_JOUR_VOL, NVL(PL_RAI_JOUR_NUM, '-'), PL_RAI_ARTCL_ST_PAGE, PL_RAI_JOUR_YEAR," +
		" NVL(PL_RAI_ARTCL_ED_PAGE, '-'), PL_RACI_CODE_ID, PL_RAI_ORG_FILE_PATH," +
		" PL_RAI_ORG_FILE_NAME, PL_RAI_ORG_FILE_EXT, PL_RAI_INSERT_TIME, PL_RAI_ARTCL_AUTH_ENAME, NVL(PL_RAI_ARTCL_DOI, '-')" +
		" FROM PL_REF_ARTICLE_INFO ";

		//System.out.println("sql : " + sqlQuery + optionquery);
		ComResultSet rs = comSQL.executeSelect(sqlQuery + optionquery);

		while(rs.next()){		
			Article_Info artInfo = new Article_Info();
			artInfo.setPL_RAI_ARTCL_NUM(rs.getString(1));
			artInfo.setPL_RAI_ARTCL_TITLE(rs.getString(2));
			artInfo.setPL_RAI_ARTCL_AUTH_FNAME(rs.getString(3));
			artInfo.setPL_RAI_JOUR_TITLE(rs.getString(4));
			artInfo.setPL_RAI_ISSN_P(rs.getString(5));
			artInfo.setPL_RAI_ISSN_N(rs.getString(6));
			artInfo.setPL_RAI_ISBN(rs.getString(7));
			artInfo.setPL_RAI_NDSL(rs.getString(8));
			artInfo.setPL_RAI_JOUR_VOL(rs.getString(9));
			artInfo.setPL_RAI_JOUR_NUM(rs.getString(10));
			artInfo.setPL_RAI_ARTCL_ST_PAGE(rs.getString(11));
			artInfo.setPL_RAI_JOUR_YEAR(rs.getString(12));
			artInfo.setPL_RAI_ARTCL_ED_PAGE(rs.getString(13));
			artInfo.setPL_RACI_CODE_ID(rs.getString(14));
			artInfo.setPL_RAI_ORG_FILE_PATH(rs.getString(15));
			artInfo.setPL_RAI_ORG_FILE_NAME(rs.getString(16));
			artInfo.setPL_RAI_ORG_FILE_EXT(rs.getString(17));
			artInfo.setPL_RAI_INSERT_TIME(rs.getString(18));
			artInfo.setPL_RAI_ARTCL_AUTH_ENAME(rs.getString(19));
			artInfo.setPL_RAI_ARTCL_DOI(rs.getString(20));
			values.addElement(artInfo);
		}
		
		return values;
	}
	
	/**
	 * @MethodName  : selectAssessedArticleInfo
	 * @Date   : 2013. 8. 6. 
	 * @MethodDescription : �� �� �򰡵� ���������� �ִ��� ���θ� Ȯ���ϴ� �޼ҵ�
	 * @param optionQeury
	 * @return
	 * @History  : - 
	 */
	public Vector<Article_Info> selectAssessedArticleInfo(String optionquery){
		Vector<Article_Info> values = new Vector<Article_Info>();
		
		String sqlQuery = "SELECT B.PL_BI_DATA_NUM, A.PL_RAI_ARTCL_NUM, A.PL_RAI_ARTCL_TITLE," +
				" A.PL_RAI_ARTCL_AUTH_FNAME, A.PL_RAI_JOUR_TITLE, A.PL_RAI_JOUR_VOL, A.PL_RAI_ARTCL_ST_PAGE," +
				" A.PL_RAI_JOUR_YEAR, NVL(P.PL_PEI_FINAL_FLAG, 'N')" +
				" FROM PL_REF_ARTICLE_INFO A, PL_BASIC_INFO B,  PL_PRIM_EVAL_INFO P" +
				" WHERE B.PL_RAI_ARTCL_NUM = A.PL_RAI_ARTCL_NUM AND P.PL_BI_DATA_NUM(+) = B.PL_BI_DATA_NUM";

		//System.out.println("sql : " + sqlQuery + optionquery);
		ComResultSet rs = comSQL.executeSelect(sqlQuery + optionquery);

		while(rs.next()){		
			Article_Info artInfo = new Article_Info();
			artInfo.setPL_BI_DATA_NUM(rs.getString(1));
			artInfo.setPL_RAI_ARTCL_NUM(rs.getString(2));
			artInfo.setPL_RAI_ARTCL_TITLE(rs.getString(3));
			artInfo.setPL_RAI_ARTCL_AUTH_FNAME(rs.getString(4));
			artInfo.setPL_RAI_JOUR_TITLE(rs.getString(5));
			artInfo.setPL_RAI_JOUR_VOL(rs.getString(6));
			artInfo.setPL_RAI_ARTCL_ST_PAGE(rs.getString(7));
			artInfo.setPL_RAI_JOUR_YEAR(rs.getString(8));
			artInfo.setPL_PEI_FINAL_FLAG(rs.getString(9));
			values.addElement(artInfo);
		}
		
		return values;
	}
	
	/**
	 * @MethodName : insertArticleInfo
	 * @Desc : ���� ���� �Է��ϴ� �޼ҵ�
	 * @param artInfo
	 * @return
	 */
	public boolean insertArticleInfo(Article_Info artInfo){
		boolean value = false;
		
		String sqlQuery = "INSERT INTO PL_REF_ARTICLE_INFO " +
		"( PL_RAI_ARTCL_NUM, PL_RAI_ARTCL_TITLE, PL_RAI_ARTCL_AUTH_FNAME, PL_RAI_JOUR_TITLE, PL_RAI_ISSN_P, PL_RAI_ISSN_N," +
		" PL_RAI_ISBN, PL_RAI_NDSL, PL_RAI_JOUR_VOL, PL_RAI_JOUR_NUM, PL_RAI_ARTCL_ST_PAGE, PL_RAI_JOUR_YEAR," +
		" PL_RAI_ARTCL_ED_PAGE, PL_RACI_CODE_ID, PL_RAI_ORG_FILE_PATH, PL_RAI_ORG_FILE_NAME, PL_RAI_ORG_FILE_EXT, PL_RAI_INSERT_TIME, PL_RAI_ARTCL_AUTH_ENAME, PL_RAI_ARTCL_DOI)"+
		" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
		value = comSQL.executeTransact(sqlQuery, artInfo.getDataList());
		
		return value;
	}
	
	/**
	 * @MethodName : selectArticleCountInfo
	 * @Desc : �� ���ǿ� �´� ������ ī��Ʈ�� �������� �޼ҵ�
	 * @param sqlOption
	 * @return
	 */
	public int selectArticleCountInfo(Vector<?> sqlOption){
		int re_value = 0;
		
		String sqlQuery = "SELECT COUNT(*) FROM PL_REF_ARTICLE_INFO WHERE PL_RAI_JOUR_YEAR = ? AND PL_RAI_ARTCL_AUTH_FNAME = ?";
		ComResultSet rs = comSQL.executeSelect(sqlQuery, sqlOption);
		while(rs.next()){
			re_value = rs.getInt(1);
		}
		return re_value;
	}
	
	/**
	 * @MethodName : selectArticleAuthYear
	 * @Desc : �������� ���ڿ� �⵵�� �������� �޼ҵ�
	 * @param sqlOption
	 * @return
	 */
	public Article_Info selectArticleAuthYear(Vector<?> sqlOption){
		
		String sqlQuery = "SELECT A.PL_RAI_ARTCL_AUTH_FNAME, A.PL_RAI_JOUR_YEAR" +
			" FROM PL_REF_ARTICLE_INFO A, PL_BASIC_INFO B" +
			" WHERE A.PL_RAI_ARTCL_NUM = B.PL_RAI_ARTCL_NUM AND B.PL_BI_DATA_NUM = ? ";

		ComResultSet rs = comSQL.executeSelect(sqlQuery, sqlOption);
		Article_Info artInfo = new Article_Info();
		while(rs.next()){					
			artInfo.setPL_RAI_ARTCL_AUTH_FNAME(rs.getString(1));
			artInfo.setPL_RAI_JOUR_YEAR(rs.getString(2));			
		}
		
		return artInfo;
	}

	/**
	 * @MethodName : selectArticleFileInfo
	 * @Desc : ���� ���� ������ �������� �޼ҵ�
	 * @param sqlOption
	 * @return
	 */
	public Article_Info selectArticleFileInfo(Vector<?> sqlOption) {
		//Vector values = new Vector();
		
		String sqlQuery = "SELECT A.PL_RAI_ORG_FILE_PATH, A.PL_RAI_ORG_FILE_NAME, A.PL_RAI_ORG_FILE_EXT" +
				" FROM PL_REF_ARTICLE_INFO A, PL_BASIC_INFO B" +
				" WHERE A.PL_RAI_ARTCL_NUM = B.PL_RAI_ARTCL_NUM AND B.PL_BI_DATA_NUM = ? ";
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery, sqlOption);
		Article_Info artInfo = new Article_Info();
		while(rs.next()){					
			artInfo.setPL_RAI_ORG_FILE_PATH(rs.getString(1));
			artInfo.setPL_RAI_ORG_FILE_NAME(rs.getString(2));
			artInfo.setPL_RAI_ORG_FILE_EXT(rs.getString(3));			
		}
		
		return artInfo;
	}
}
