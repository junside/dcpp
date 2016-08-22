/**
 * 
 */
package nfri.dcpp.properties.model;

/**
 * @Project: dcpp
 * @Title  : Properties_Part_Count_Info.java
 * @Description : 물성정보 입자분자 별 카운트 정보를 담는 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2012. 1. 17.
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class Properties_Part_Count_Info {
	private String PL_CPBI_ELE_NUM = "";
	private String COUNT_NUM = "";
	private String PL_CPBI_ELE_SYMBOL = "";
	private String PL_CPBI_ELE_AMCOUNT = "";
	private String PL_BI_MAIN_PROC = "";
	private String PL_BI_MAIN_PROC_EXP = "";
	
	/**
	 * @return the pL_CPBI_ELE_AMCOUNT
	 */
	public String getPL_CPBI_ELE_AMCOUNT() {
		return PL_CPBI_ELE_AMCOUNT;
	}
	/**
	 * @param pL_CPBI_ELE_AMCOUNT the pL_CPBI_ELE_AMCOUNT to set
	 */
	public void setPL_CPBI_ELE_AMCOUNT(String pL_CPBI_ELE_AMCOUNT) {
		PL_CPBI_ELE_AMCOUNT = pL_CPBI_ELE_AMCOUNT;
	}
	/**
	 * @return the pL_CPBI_ELE_NUM
	 */
	public String getPL_CPBI_ELE_NUM() {
		return PL_CPBI_ELE_NUM;
	}
	/**
	 * @return the cOUNT_NUM
	 */
	public String getCOUNT_NUM() {
		return COUNT_NUM;
	}
	/**
	 * @return the pL_CPBI_ELE_SYMBOL
	 */
	public String getPL_CPBI_ELE_SYMBOL() {
		return PL_CPBI_ELE_SYMBOL;
	}
	/**
	 * @param pL_CPBI_ELE_NUM the pL_CPBI_ELE_NUM to set
	 */
	public void setPL_CPBI_ELE_NUM(String pL_CPBI_ELE_NUM) {
		PL_CPBI_ELE_NUM = pL_CPBI_ELE_NUM;
	}
	/**
	 * @param cOUNT_NUM the cOUNT_NUM to set
	 */
	public void setCOUNT_NUM(String cOUNT_NUM) {
		COUNT_NUM = cOUNT_NUM;
	}
	/**
	 * @param pL_CPBI_ELE_SYMBOL the pL_CPBI_ELE_SYMBOL to set
	 */
	public void setPL_CPBI_ELE_SYMBOL(String pL_CPBI_ELE_SYMBOL) {
		PL_CPBI_ELE_SYMBOL = pL_CPBI_ELE_SYMBOL;
	}
	/**
	 * @return the pL_BI_MAIN_PROC
	 */
	public String getPL_BI_MAIN_PROC() {
		return PL_BI_MAIN_PROC;
	}
	/**
	 * @param pL_BI_MAIN_PROC the pL_BI_MAIN_PROC to set
	 */
	public void setPL_BI_MAIN_PROC(String pL_BI_MAIN_PROC) {
		PL_BI_MAIN_PROC = pL_BI_MAIN_PROC;
	}
	/**
	 * @return the pL_BI_MAIN_PROC_EXP
	 */
	public String getPL_BI_MAIN_PROC_EXP() {
		return PL_BI_MAIN_PROC_EXP;
	}
	/**
	 * @param pL_BI_MAIN_PROC_EXP the pL_BI_MAIN_PROC_EXP to set
	 */
	public void setPL_BI_MAIN_PROC_EXP(String pL_BI_MAIN_PROC_EXP) {
		PL_BI_MAIN_PROC_EXP = pL_BI_MAIN_PROC_EXP;
	}
	
	
}
