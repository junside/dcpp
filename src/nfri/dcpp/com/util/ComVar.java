/**
 * 
 */
package nfri.dcpp.com.util;


/**
 *
 * @Project : dcpp_web
 * @Title : ComVar.java
 * @Description : 관련 상수들을 모아놓은 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 02. 24
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public abstract class ComVar {
	//Boolean 관련
	public static final boolean BOOL_TRUE = true;
	public static final boolean BOOL_FALSE = false;
	//MessageCode 관련
	public static final String MSG_ERROR = "ERROR";
	public static final String MSG_SUCCESS = "SUCCESS";
	public static final String MSG_OK = "OK";
	//String 관련
	public static final String STRING_EMPTY = "";
	public static final String STRING_ASC = "asc";
	public static final String STRING_DASH = "-";
	public static final String STRING_ZERO = "0";
	public static final String STRING_NULL_B = "NULL";
	public static final String STRING_NULL_S = "null";
	public static final int NULLINT = 0;
	public static final double NULLDOUBLE = 0;
	//File Extension 관련
	public static final String EXT_DOC = "DOC";
	public static final String EXT_XLS = "XLS";
	public static final String EXT_PPT = "PPT";
	public static final String EXT_DOCX = "DOCX";
	public static final String EXT_XLSX = "XLSX";
	public static final String EXT_PPTX = "PPTX";
	public static final String EXT_HWP = "HWP";
	public static final String EXT_PDF = "PDF";
	public static final String EXT_JPG = "JPG";
	public static final String EXT_GIF = "GIF";
	public static final String EXT_BMP = "BMP";
	public static final String EXT_TXT = "TXT";
	//Operation 관련
	public static final String OP_AND = "AND";
	public static final String OP_OR = "OR";
	public static final String OP_TRUE = "TRUE";
	public static final String OP_FALSE = "FALSE";	
	//Object Type 관련
	public static final int OBJ_STRING = 1;
	public static final int OBJ_INTEGER = 2;
	public static final int OBJ_DOUBLE = 3;
	//AddCode 관련
	public static final String addCodeList[] = {"+","->","+","+"};
	//USER 관련
	public static final String USER_ID_VAILD = "1"; //유효한 사용자
	public static final String USER_ID_INVAILD = "2"; //없는 사용자
	public static final String USER_PWD_VAILD = "3"; //유효한 pwd
	public static final String USER_PWD_INVAILD = "4"; //잘못된 pwd
	public static final String USER_AUTH_VAILD = "5"; //유효한 권한
	public static final String USER_AUTH_INVAILD = "6"; //잘못된 권한	
	public static final String USER_AUTH_CERTIFIED_ALL = "7"; //모든 권한
}
