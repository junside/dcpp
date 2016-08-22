/**
 * 
 */
package nfri.dcpp.com.util;


/**
 *
 * @Project : dcpp_web
 * @Title : ComVar.java
 * @Description : ���� ������� ��Ƴ��� Ŭ����
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 02. 24
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @�����̷� : -
 *
 */
public abstract class ComVar {
	//Boolean ����
	public static final boolean BOOL_TRUE = true;
	public static final boolean BOOL_FALSE = false;
	//MessageCode ����
	public static final String MSG_ERROR = "ERROR";
	public static final String MSG_SUCCESS = "SUCCESS";
	public static final String MSG_OK = "OK";
	//String ����
	public static final String STRING_EMPTY = "";
	public static final String STRING_ASC = "asc";
	public static final String STRING_DASH = "-";
	public static final String STRING_ZERO = "0";
	public static final String STRING_NULL_B = "NULL";
	public static final String STRING_NULL_S = "null";
	public static final int NULLINT = 0;
	public static final double NULLDOUBLE = 0;
	//File Extension ����
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
	//Operation ����
	public static final String OP_AND = "AND";
	public static final String OP_OR = "OR";
	public static final String OP_TRUE = "TRUE";
	public static final String OP_FALSE = "FALSE";	
	//Object Type ����
	public static final int OBJ_STRING = 1;
	public static final int OBJ_INTEGER = 2;
	public static final int OBJ_DOUBLE = 3;
	//AddCode ����
	public static final String addCodeList[] = {"+","->","+","+"};
	//USER ����
	public static final String USER_ID_VAILD = "1"; //��ȿ�� �����
	public static final String USER_ID_INVAILD = "2"; //���� �����
	public static final String USER_PWD_VAILD = "3"; //��ȿ�� pwd
	public static final String USER_PWD_INVAILD = "4"; //�߸��� pwd
	public static final String USER_AUTH_VAILD = "5"; //��ȿ�� ����
	public static final String USER_AUTH_INVAILD = "6"; //�߸��� ����	
	public static final String USER_AUTH_CERTIFIED_ALL = "7"; //��� ����
}
