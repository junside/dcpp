/**
 * 
 */
package nfri.dcpp.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nfri.dcpp.board.model.Board_Info;
import nfri.dcpp.com.db.ComResultSet;
import nfri.dcpp.com.db.ComSQLExecution;
import nfri.dcpp.properties.model.Basic_Part_Info;


/**
 * @Project: dcpp
 * @Title  : DAO_BOARD_INFO.java
 * @Description : 게시판과 관련된 DB 작업을 처리하는 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2011. 02. 07
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */
public class DAO_BOARD_INFO {
	
	//DB 실행 클래스 선언
	ComSQLExecution comSQL;
    
    public DAO_BOARD_INFO() {
    	comSQL = new ComSQLExecution();
    }
    
    /**
     * @MethodName  : insert_QA_Article
     * @Date   : 2011. 3. 17. 
     * @MethodDescription : QA 보드 게시글 등록하는 메소드
     * @param boardInfo
     * @return
     * @History  : - 
     */
    public boolean insert_QA_Article(Board_Info boardInfo){
		boolean value = false;
		
		String sqlQuery = "INSERT INTO PL_QA_BOARD(PL_QB_NUM, PL_QB_WRITER, PL_QB_EMAIL, PL_QB_SUBJECT, PL_QB_PASSWD," +
				" PL_QB_REG_DATE, PL_QB_REF, PL_QB_RE_STEP, PL_QB_RE_LEVEL, PL_QB_CONTENT, PL_QB_IP)" +
				" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		value = comSQL.executeTransact(sqlQuery, boardInfo.getDataList());
				
		return value;
    }
    
    /**
     * @MethodName  : select_QA_MaxNumber
     * @Date   : 2011. 3. 17. 
     * @MethodDescription : QA 게시판 게시글의 글번호를 만들기 위해 최대수를 가져오는 메소드
     * @return
     * @History  : - 
     */
    public int select_QA_MaxNumber(){
    	int value = 0;
    	
    	String sqlQuery = "SELECT MAX(PL_QB_NUM) FROM PL_QA_BOARD";
    	
    	value = comSQL.executeSelectOneInt(sqlQuery);
    	
    	if(value==0){
    		value = 1;
    	}else{
    		value = value + 1;
    	}
    	
    	return value;
    }
    
    /**
     * @MethodName  : get_QA_ArticleCount
     * @Date   : 2011. 3. 17. 
     * @MethodDescription : QA 게시판의 글 전체 개수를 가져오는 메소드 
     * @return
     * @History  : - 
     */
    public int get_QA_ArticleCount(){
    	int value = 0;
    	
    	String sqlQuery = "SELECT COUNT(*) FROM PL_QA_BOARD";
    	
    	value = comSQL.executeSelectOneInt(sqlQuery);
    	    	
    	return value;
    }
    
    /**
     * @MethodName  : update_QA_Reference
     * @Date   : 2011. 3. 17. 
     * @MethodDescription : QA 게시판 Ref, Restep 업데이트
     * @param ref
     * @param reStep
     * @return
     * @History  : - 
     */
    public boolean update_QA_Reference(int ref, int reStep){
    	boolean value = false;
    	Vector<Integer> sqlOption = new Vector<Integer>();
    	sqlOption.addElement(ref);
    	sqlOption.addElement(reStep);		
		
    	String sqlQuery = "UPDATE PL_QA_BOARD SET PL_QB_RE_STEP=PL_QB_RE_STEP+1 WHERE PL_QB_REF= ? AND PL_QB_RE_STEP> ?";
    	
    	value = comSQL.executeTransact(sqlQuery, sqlOption);    	
    	
    	return value;
    }
    
    /**
     * @MethodName  : update_QA_ReadCount
     * @Date   : 2011. 3. 22. 
     * @MethodDescription : QA 게시판 게시물의 카운트 업데이트하는 메소드
     * @param num
     * @return
     * @History  : - 
     */
    public boolean update_QA_ReadCount(int num){
    	boolean value = false;
    	Vector<Integer> sqlOption = new Vector<Integer>();
    	sqlOption.addElement(num);   			
		
    	String sqlQuery = "UPDATE PL_QA_BOARD SET PL_QB_READCOUNT=PL_QB_READCOUNT+1 WHERE PL_QB_NUM = ?";
    	
    	value = comSQL.executeTransact(sqlQuery, sqlOption);    	
    	
    	return value;
    }

	/**
	 * @MethodName  : select_QA_Article
	 * @Date   : 2011. 3. 17. 
	 * @MethodDescription : QA 게시판에서 전체 게시글 가져오기
	 * @return
	 * @History  : - 
	 */
	public Vector<Board_Info> select_QA_Article() {
		Vector<Board_Info> values = new Vector<Board_Info>();
		String sqlQuery = "SELECT PL_QB_NUM, PL_QB_WRITER, PL_QB_EMAIL, PL_QB_SUBJECT, PL_QB_PASSWD, PL_QB_REG_DATE," +
				" PL_QB_READCOUNT, PL_QB_REF, PL_QB_RE_STEP, PL_QB_RE_LEVEL, PL_QB_CONTENT, PL_QB_IP" +
				" FROM PL_QA_BOARD ORDER BY PL_QB_REF DESC, PL_QB_RE_STEP ASC";
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery);
		
		while(rs.next()){	
			Board_Info article_Info = new Board_Info();
			article_Info.setNum(rs.getInt(1));
			article_Info.setWriter(rs.getString(2));
			article_Info.setEmail(rs.getString(3));
			article_Info.setSubject(rs.getString(4));
			article_Info.setPasswd(rs.getString(5));
			article_Info.setReg_date(rs.getString(6));
			article_Info.setReadcount(rs.getInt(7));
			article_Info.setRef(rs.getInt(8));
			article_Info.setRe_step(rs.getInt(9));
			article_Info.setRe_level(rs.getInt(10));
			article_Info.setContent(rs.getString(11));
			article_Info.setIp(rs.getString(12)); 
			
			values.addElement(article_Info);
		}
		return values;
	}

	/**
	 * @MethodName  : getArticleContent
	 * @Date   : 2011. 3. 21. 
	 * @MethodDescription : QA 게시판 게시물의 내용을 가져오는 메소드
	 * @param num
	 * @return
	 * @History  : - 
	 */
	public Board_Info select_QA_ArticleContent(int num){
			
		boolean update_value = update_QA_ReadCount(num);
		Vector<Integer> sqlOption = new Vector<Integer>();
		sqlOption.addElement(num);
		
		String sqlQuery = "SELECT PL_QB_NUM, PL_QB_WRITER, PL_QB_EMAIL, PL_QB_SUBJECT, PL_QB_PASSWD, PL_QB_REG_DATE," +
				" PL_QB_READCOUNT, PL_QB_REF, PL_QB_RE_STEP, PL_QB_RE_LEVEL, PL_QB_CONTENT, PL_QB_IP" +
				" FROM PL_QA_BOARD WHERE PL_QB_NUM = ?";
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery, sqlOption);
		Board_Info article_Info = new Board_Info();
		while(rs.next()){				
			article_Info.setNum(rs.getInt(1));
			article_Info.setWriter(rs.getString(2));
			article_Info.setEmail(rs.getString(3));
			article_Info.setSubject(rs.getString(4));
			article_Info.setPasswd(rs.getString(5));
			article_Info.setReg_date(rs.getString(6));
			article_Info.setReadcount(rs.getInt(7));
			article_Info.setRef(rs.getInt(8));
			article_Info.setRe_step(rs.getInt(9));
			article_Info.setRe_level(rs.getInt(10));
			article_Info.setContent(rs.getString(11));
			article_Info.setIp(rs.getString(12)); 
		}
		return article_Info;
    }
	
	/**
	 * @MethodName  : select_QA_Passwd
	 * @Date   : 2011. 3. 22. 
	 * @MethodDescription : QA 게시판 게시물의 패스워드를 가져오는 메소드
	 * @param num
	 * @return
	 * @History  : - 
	 */
	public String select_QA_Passwd(int num){
		String value = "";
		Vector<Integer> sqlOption = new Vector<Integer>();
		sqlOption.addElement(num);
		
		String sqlQuery ="SELECT PL_QB_PASSWD FROM PL_QA_BOARD WHERE PL_QB_NUM = ?";
		
		value = comSQL.executeSelectOneStr(sqlQuery, sqlOption);		
		
		return value;
	}

	/**
	 * @MethodName  : update_QA_Article
	 * @Date   : 2011. 3. 22. 
	 * @MethodDescription : QA 게시판 게시물 내용을 업데이트 하는 메소드
	 * @param vList
	 * @return
	 * @History  : - 
	 */
	public boolean update_QA_Article(Vector<Integer> sqlOption) {
		boolean value = false;
		String sqlQuery = "UPDATE PL_QA_BOARD SET PL_QB_WRITER=?, PL_QB_EMAIL=?," +
				" PL_QB_SUBJECT=?, PL_QB_PASSWD=?, PL_QB_CONTENT=? WHERE PL_QB_NUM=?";
		
		value = comSQL.executeTransact(sqlQuery, sqlOption);
			    	
		return value;
	}

	/**
	 * @MethodName  : delete_QA_Article
	 * @Date   : 2011. 3. 22. 
	 * @MethodDescription : QA 게시판 게시물 내용을 삭제하는 메소드
	 * @param num
	 * @return
	 * @History  : - 
	 */
	public boolean delete_QA_Article(Vector<Integer> sqlOption) {
		boolean value = false;
		String sqlQuery = "DELETE FROM PL_QA_BOARD WHERE PL_QB_NUM=?";
		
		value = comSQL.executeTransact(sqlQuery, sqlOption);
			    	
		return value;
	}
	
	/**
     * @MethodName  : insert_HP_Article
     * @Date   : 2011. 3. 17. 
     * @MethodDescription : HP 보드 게시글 등록하는 메소드
     * @param boardInfo
     * @return
     * @History  : - 
     */
    public boolean insert_HP_Article(Board_Info boardInfo){
		boolean value = false;
		
		String sqlQuery = "INSERT INTO PL_HELP_BOARD(PL_HB_NUM, PL_HB_WRITER, PL_HB_EMAIL, PL_HB_SUBJECT, PL_HB_PASSWD," +
				" PL_HB_REG_DATE, PL_HB_REF, PL_HB_RE_STEP, PL_HB_RE_LEVEL, PL_HB_CONTENT, PL_HB_IP)" +
				" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		value = comSQL.executeTransact(sqlQuery, boardInfo.getDataList());
				
		return value;
    }
    
    /**
     * @MethodName  : select_HP_MaxNumber
     * @Date   : 2011. 3. 17. 
     * @MethodDescription : HP 게시판 게시글의 글번호를 만들기 위해 최대수를 가져오는 메소드
     * @return
     * @History  : - 
     */
    public int select_HP_MaxNumber(){
    	int value = 0;
    	
    	String sqlQuery = "SELECT MAX(PL_HB_NUM) FROM PL_HELP_BOARD";
    	
    	value = comSQL.executeSelectOneInt(sqlQuery);
    	
    	if(value==0){
    		value = 1;
    	}else{
    		value = value + 1;
    	}
    	
    	return value;
    }
    
    /**
     * @MethodName  : get_HP_ArticleCount
     * @Date   : 2011. 3. 17. 
     * @MethodDescription : HP 게시판의 글 전체 개수를 가져오는 메소드 
     * @return
     * @History  : - 
     */
    public int get_HP_ArticleCount(){
    	int value = 0;
    	
    	String sqlQuery = "SELECT COUNT(*) FROM PL_HELP_BOARD";
    	
    	value = comSQL.executeSelectOneInt(sqlQuery);
    	    	
    	return value;
    }
    
    /**
     * @MethodName  : update_HP_Reference
     * @Date   : 2011. 3. 17. 
     * @MethodDescription : HP 게시판 Ref, Restep 업데이트
     * @param ref
     * @param reStep
     * @return
     * @History  : - 
     */
    public boolean update_HP_Reference(int ref, int reStep){
    	boolean value = false;
    	Vector<Integer> sqlOption = new Vector<Integer>();
    	sqlOption.addElement(ref);
    	sqlOption.addElement(reStep);		
		
    	String sqlQuery = "UPDATE PL_HELP_BOARD SET PL_HB_RE_STEP=PL_HB_RE_STEP+1 WHERE PL_HB_REF= ? AND PL_HB_RE_STEP> ?";
    	
    	value = comSQL.executeTransact(sqlQuery, sqlOption);    	
    	
    	return value;
    }
    
    /**
     * @MethodName  : update_HP_ReadCount
     * @Date   : 2011. 3. 22. 
     * @MethodDescription : HP 게시판 게시물의 카운트 업데이트하는 메소드
     * @param num
     * @return
     * @History  : - 
     */
    public boolean update_HP_ReadCount(int num){
    	boolean value = false;
    	Vector<Integer> sqlOption = new Vector<Integer>();
    	sqlOption.addElement(num);   			
		
    	String sqlQuery = "UPDATE PL_HELP_BOARD SET PL_HB_READCOUNT=PL_HB_READCOUNT+1 WHERE PL_HB_NUM = ?";
    	
    	value = comSQL.executeTransact(sqlQuery, sqlOption);    	
    	
    	return value;
    }

	/**
	 * @MethodName  : select_HP_Article
	 * @Date   : 2011. 3. 17. 
	 * @MethodDescription : HP 게시판에서 전체 게시글 가져오기
	 * @return
	 * @History  : - 
	 */
	public Vector<Board_Info> select_HP_Article() {
		Vector<Board_Info> values = new Vector<Board_Info>();
		String sqlQuery = "SELECT PL_HB_NUM, PL_HB_WRITER, PL_HB_EMAIL, PL_HB_SUBJECT, PL_HB_PASSWD, PL_HB_REG_DATE," +
				" PL_HB_READCOUNT, PL_HB_REF, PL_HB_RE_STEP, PL_HB_RE_LEVEL, PL_HB_CONTENT, PL_HB_IP" +
				" FROM PL_HELP_BOARD ORDER BY PL_HB_REF DESC, PL_HB_RE_STEP ASC";
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery);
		
		while(rs.next()){	
			Board_Info article_Info = new Board_Info();
			article_Info.setNum(rs.getInt(1));
			article_Info.setWriter(rs.getString(2));
			article_Info.setEmail(rs.getString(3));
			article_Info.setSubject(rs.getString(4));
			article_Info.setPasswd(rs.getString(5));
			article_Info.setReg_date(rs.getString(6));
			article_Info.setReadcount(rs.getInt(7));
			article_Info.setRef(rs.getInt(8));
			article_Info.setRe_step(rs.getInt(9));
			article_Info.setRe_level(rs.getInt(10));
			article_Info.setContent(rs.getString(11));
			article_Info.setIp(rs.getString(12)); 
			
			values.addElement(article_Info);
		}
		return values;
	}

	/**
	 * @MethodName  : select_HP_ArticleContent
	 * @Date   : 2011. 3. 21. 
	 * @MethodDescription : HP 게시판 게시물의 내용을 가져오는 메소드
	 * @param num
	 * @return
	 * @History  : - 
	 */
	public Board_Info select_HP_ArticleContent(int num){
			
		boolean update_value = update_HP_ReadCount(num);
		Vector<Integer> sqlOption = new Vector<Integer>();
		sqlOption.addElement(num);
		
		String sqlQuery = "SELECT PL_HB_NUM, PL_HB_WRITER, PL_HB_EMAIL, PL_HB_SUBJECT, PL_HB_PASSWD, PL_HB_REG_DATE," +
				" PL_HB_READCOUNT, PL_HB_REF, PL_HB_RE_STEP, PL_HB_RE_LEVEL, PL_HB_CONTENT, PL_HB_IP" +
				" FROM PL_HELP_BOARD WHERE PL_HB_NUM = ?";
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery, sqlOption);
		Board_Info article_Info = new Board_Info();
		while(rs.next()){				
			article_Info.setNum(rs.getInt(1));
			article_Info.setWriter(rs.getString(2));
			article_Info.setEmail(rs.getString(3));
			article_Info.setSubject(rs.getString(4));
			article_Info.setPasswd(rs.getString(5));
			article_Info.setReg_date(rs.getString(6));
			article_Info.setReadcount(rs.getInt(7));
			article_Info.setRef(rs.getInt(8));
			article_Info.setRe_step(rs.getInt(9));
			article_Info.setRe_level(rs.getInt(10));
			article_Info.setContent(rs.getString(11));
			article_Info.setIp(rs.getString(12)); 
		}
		return article_Info;
    }
	
	/**
	 * @MethodName  : select_HP_Passwd
	 * @Date   : 2011. 3. 22. 
	 * @MethodDescription : HP 게시판 게시물의 패스워드를 가져오는 메소드
	 * @param num
	 * @return
	 * @History  : - 
	 */
	public String select_HP_Passwd(int num){
		String value = "";
		Vector<Integer> sqlOption = new Vector<Integer>();
		sqlOption.addElement(num);
		
		String sqlQuery ="SELECT PL_HB_PASSWD FROM PL_HELP_BOARD WHERE PL_HB_NUM = ?";
		
		value = comSQL.executeSelectOneStr(sqlQuery, sqlOption);		
		
		return value;
	}

	/**
	 * @MethodName  : update_HP_Article
	 * @Date   : 2011. 3. 22. 
	 * @MethodDescription : HP 게시판 게시물 내용을 업데이트 하는 메소드
	 * @param vList
	 * @return
	 * @History  : - 
	 */
	public boolean update_HP_Article(Vector<Integer> sqlOption) {
		boolean value = false;
		String sqlQuery = "UPDATE PL_HELP_BOARD SET PL_HB_WRITER=?, PL_HB_EMAIL=?," +
				" PL_HB_SUBJECT=?, PL_HB_PASSWD=?, PL_HB_CONTENT=? WHERE PL_HB_NUM=?";
		
		value = comSQL.executeTransact(sqlQuery, sqlOption);
			    	
		return value;
	}

	/**
	 * @MethodName  : delete_HP_Article
	 * @Date   : 2011. 3. 22. 
	 * @MethodDescription : HP 게시판 게시물 내용을 삭제하는 메소드
	 * @param num
	 * @return
	 * @History  : - 
	 */
	public boolean delete_HP_Article(Vector<Integer> sqlOption) {
		boolean value = false;
		String sqlQuery = "DELETE FROM PL_HELP_BOARD WHERE PL_HB_NUM=?";
		
		value = comSQL.executeTransact(sqlQuery, sqlOption);
			    	
		return value;
	}
	
	/**
     * @MethodName  : insert_NT_Article
     * @Date   : 2011. 3. 17. 
     * @MethodDescription : NT 보드 게시글 등록하는 메소드
     * @param boardInfo
     * @return
     * @History  : - 
     */
    public boolean insert_NT_Article(Board_Info boardInfo){
		boolean value = false;
		
		String sqlQuery = "INSERT INTO PL_NOTICE_BOARD(PL_NB_NUM, PL_NB_WRITER, PL_NB_EMAIL, PL_NB_SUBJECT, PL_NB_PASSWD," +
				" PL_NB_REG_DATE, PL_NB_REF, PL_NB_RE_STEP, PL_NB_RE_LEVEL, PL_NB_CONTENT, PL_NB_IP)" +
				" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		value = comSQL.executeTransact(sqlQuery, boardInfo.getDataList());
				
		return value;
    }
    
    /**
     * @MethodName  : select_NT_MaxNumber
     * @Date   : 2011. 3. 17. 
     * @MethodDescription : NT 게시판 게시글의 글번호를 만들기 위해 최대수를 가져오는 메소드
     * @return
     * @History  : - 
     */
    public int select_NT_MaxNumber(){
    	int value = 0;
    	
    	String sqlQuery = "SELECT MAX(PL_NB_NUM) FROM PL_NOTICE_BOARD";
    	
    	value = comSQL.executeSelectOneInt(sqlQuery);
    	
    	if(value==0){
    		value = 1;
    	}else{
    		value = value + 1;
    	}
    	
    	return value;
    }
    
    /**
     * @MethodName  : get_NT_ArticleCount
     * @Date   : 2011. 3. 17. 
     * @MethodDescription : NT 게시판의 글 전체 개수를 가져오는 메소드 
     * @return
     * @History  : - 
     */
    public int get_NT_ArticleCount(){
    	int value = 0;
    	
    	String sqlQuery = "SELECT COUNT(*) FROM PL_NOTICE_BOARD";
    	
    	value = comSQL.executeSelectOneInt(sqlQuery);
    	    	
    	return value;
    }
    
    /**
     * @MethodName  : update_NT_Reference
     * @Date   : 2011. 3. 17. 
     * @MethodDescription : NT 게시판 Ref, Restep 업데이트
     * @param ref
     * @param reStep
     * @return
     * @History  : - 
     */
    public boolean update_NT_Reference(int ref, int reStep){
    	boolean value = false;
    	Vector<Integer> sqlOption = new Vector<Integer>();
    	sqlOption.addElement(ref);
    	sqlOption.addElement(reStep);		
		
    	String sqlQuery = "UPDATE PL_NOTICE_BOARD SET PL_NB_RE_STEP=PL_NB_RE_STEP+1 WHERE PL_NB_REF= ? AND PL_NB_RE_STEP> ?";
    	
    	value = comSQL.executeTransact(sqlQuery, sqlOption);    	
    	
    	return value;
    }
    
    /**
     * @MethodName  : update_NT_ReadCount
     * @Date   : 2011. 3. 22. 
     * @MethodDescription : NT 게시판 게시물의 카운트 업데이트하는 메소드
     * @param num
     * @return
     * @History  : - 
     */
    public boolean update_NT_ReadCount(int num){
    	boolean value = false;
    	Vector<Integer> sqlOption = new Vector<Integer>();
    	sqlOption.addElement(num);   			
		
    	String sqlQuery = "UPDATE PL_NOTICE_BOARD SET PL_NB_READCOUNT=PL_NB_READCOUNT+1 WHERE PL_NB_NUM = ?";
    	
    	value = comSQL.executeTransact(sqlQuery, sqlOption);    	
    	
    	return value;
    }

	/**
	 * @MethodName  : select_NT_Article
	 * @Date   : 2011. 3. 17. 
	 * @MethodDescription : NT 게시판에서 전체 게시글 가져오기
	 * @return
	 * @History  : - 
	 */
	public Vector<Board_Info> select_NT_Article() {
		Vector<Board_Info> values = new Vector<Board_Info>();
		String sqlQuery = "SELECT PL_NB_NUM, PL_NB_WRITER, PL_NB_EMAIL, PL_NB_SUBJECT, PL_NB_PASSWD, PL_NB_REG_DATE," +
				" PL_NB_READCOUNT, PL_NB_REF, PL_NB_RE_STEP, PL_NB_RE_LEVEL, PL_NB_CONTENT, PL_NB_IP" +
				" FROM PL_NOTICE_BOARD ORDER BY PL_NB_REF DESC, PL_NB_RE_STEP ASC";
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery);
		
		while(rs.next()){	
			Board_Info article_Info = new Board_Info();
			article_Info.setNum(rs.getInt(1));
			article_Info.setWriter(rs.getString(2));
			article_Info.setEmail(rs.getString(3));
			article_Info.setSubject(rs.getString(4));
			article_Info.setPasswd(rs.getString(5));
			article_Info.setReg_date(rs.getString(6));
			article_Info.setReadcount(rs.getInt(7));
			article_Info.setRef(rs.getInt(8));
			article_Info.setRe_step(rs.getInt(9));
			article_Info.setRe_level(rs.getInt(10));
			article_Info.setContent(rs.getString(11));
			article_Info.setIp(rs.getString(12)); 
			
			values.addElement(article_Info);
		}
		return values;
	}

	/**
	 * @MethodName  : select_NT_ArticleContent
	 * @Date   : 2011. 3. 21. 
	 * @MethodDescription : NT 게시판 게시물의 내용을 가져오는 메소드
	 * @param num
	 * @return
	 * @History  : - 
	 */
	public Board_Info select_NT_ArticleContent(int num){
			
		boolean update_value = update_NT_ReadCount(num);
		Vector<Integer> sqlOption = new Vector<Integer>();
		sqlOption.addElement(num);
		
		String sqlQuery = "SELECT PL_NB_NUM, PL_NB_WRITER, PL_NB_EMAIL, PL_NB_SUBJECT, PL_NB_PASSWD, PL_NB_REG_DATE," +
				" PL_NB_READCOUNT, PL_NB_REF, PL_NB_RE_STEP, PL_NB_RE_LEVEL, PL_NB_CONTENT, PL_NB_IP" +
				" FROM PL_NOTICE_BOARD WHERE PL_NB_NUM = ?";
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery, sqlOption);
		Board_Info article_Info = new Board_Info();
		while(rs.next()){				
			article_Info.setNum(rs.getInt(1));
			article_Info.setWriter(rs.getString(2));
			article_Info.setEmail(rs.getString(3));
			article_Info.setSubject(rs.getString(4));
			article_Info.setPasswd(rs.getString(5));
			article_Info.setReg_date(rs.getString(6));
			article_Info.setReadcount(rs.getInt(7));
			article_Info.setRef(rs.getInt(8));
			article_Info.setRe_step(rs.getInt(9));
			article_Info.setRe_level(rs.getInt(10));
			article_Info.setContent(rs.getString(11));
			article_Info.setIp(rs.getString(12)); 
		}
		return article_Info;
    }
	
	/**
	 * @MethodName  : select_NT_Passwd
	 * @Date   : 2011. 3. 22. 
	 * @MethodDescription : NT 게시판 게시물의 패스워드를 가져오는 메소드
	 * @param num
	 * @return
	 * @History  : - 
	 */
	public String select_NT_Passwd(int num){
		String value = "";
		Vector<Integer> sqlOption = new Vector<Integer>();
		sqlOption.addElement(num);
		
		String sqlQuery ="SELECT PL_NB_PASSWD FROM PL_NOTICE_BOARD WHERE PL_NB_NUM = ?";
		
		value = comSQL.executeSelectOneStr(sqlQuery, sqlOption);		
		
		return value;
	}

	/**
	 * @MethodName  : update_NT_Article
	 * @Date   : 2011. 3. 22. 
	 * @MethodDescription : NT 게시판 게시물 내용을 업데이트 하는 메소드
	 * @param vList
	 * @return
	 * @History  : - 
	 */
	public boolean update_NT_Article(Vector<Integer> sqlOption) {
		boolean value = false;
		String sqlQuery = "UPDATE PL_NOTICE_BOARD SET PL_NB_WRITER=?, PL_NB_EMAIL=?," +
				" PL_NB_SUBJECT=?, PL_NB_PASSWD=?, PL_NB_CONTENT=? WHERE PL_NB_NUM=?";
		
		value = comSQL.executeTransact(sqlQuery, sqlOption);
			    	
		return value;
	}

	/**
	 * @MethodName  : delete_NT_Article
	 * @Date   : 2011. 3. 22. 
	 * @MethodDescription : NT 게시판 게시물 내용을 삭제하는 메소드
	 * @param num
	 * @return
	 * @History  : - 
	 */
	public boolean delete_NT_Article(Vector<Integer> sqlOption) {
		boolean value = false;
		String sqlQuery = "DELETE FROM PL_NOTICE_BOARD WHERE PL_NB_NUM=?";
		
		value = comSQL.executeTransact(sqlQuery, sqlOption);
			    	
		return value;
	}
}
