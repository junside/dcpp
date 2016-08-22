/**
 * 
 */
package nfri.dcpp.properties.model;

/**
 * @Project: dcpp_web
 * @Title  : Properties_Basic_Count.java
 * @Description : �ö�� ������ �˻��� ���� ���� �� ����� �����ϴ� Ŭ����
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2010. 1. 12.
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class Properties_Basic_Count {
	
	private String COUNT = "";
	private String PL_BI_IMP_CLASS = ""; //�浹���
	private String PL_BI_MAIN_PROC = ""; //�����μ���
	private String PL_BI_SUB_PROC = ""; //�����μ���
	
	/**
	 * @param pLBIIMPCLASS the pL_BI_IMP_CLASS to set
	 */
	public void setPL_BI_IMP_CLASS(String pLBIIMPCLASS) {
		PL_BI_IMP_CLASS = pLBIIMPCLASS;
	}
	/**
	 * @param pLBIMAINPROC the pL_BI_MAIN_PROC to set
	 */
	public void setPL_BI_MAIN_PROC(String pLBIMAINPROC) {
		PL_BI_MAIN_PROC = pLBIMAINPROC;
	}
	/**
	 * @param pLBISUBPROC the pL_BI_SUB_PROC to set
	 */
	public void setPL_BI_SUB_PROC(String pLBISUBPROC) {
		PL_BI_SUB_PROC = pLBISUBPROC;
	}
	/**
	 * @param cOUNT the cOUNT to set
	 */
	public void setCOUNT(String cOUNT) {
		COUNT = cOUNT;
	}
	
	/**
	 * @return the pL_BI_IMP_CLASS
	 */
	public String getPL_BI_IMP_CLASS() {
		return PL_BI_IMP_CLASS;
	}
	/**
	 * @return the pL_BI_MAIN_PROC
	 */
	public String getPL_BI_MAIN_PROC() {
		return PL_BI_MAIN_PROC;
	}
	/**
	 * @return the pL_BI_SUB_PROC
	 */
	public String getPL_BI_SUB_PROC() {
		return PL_BI_SUB_PROC;
	}
	/**
	 * @return the cOUNT
	 */
	public String getCOUNT() {
		return COUNT;
	}

}
