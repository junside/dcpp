/**
 * 
 */
package nfri.dcpp.properties.model;

import java.util.Vector;

/**
 * @Project: dcpp
 * @Title  : Part_Inchikey_Info.java
 * @Description : Inchikey를 다루기 위한 저장 모델
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2014. 6. 2.
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class Part_Inchikey_Info {

	private String PL_CPBI_ELE_NUM = ""; //입자번호
	private String PL_CPBI_ELE_SYMBOL = ""; //입자 기호
	private String PL_BEI_CHG_STATE = ""; //입자 이온화
	private String PL_CPII_ION_CHARGE = ""; //Ion Charge
	private String PL_CPII_TXT_FOMULA = ""; //Text Formula
	private String PL_CPII_NFRI_FOMULA = ""; //NFRI Formula
	private String PL_CPII_INCHI = ""; // Inchi 값
	private String PL_CPII_INCHI_KEY = ""; //Inchi Key 값
	private String PL_CPII_STOCHIOMETRIC = ""; //Stochiometric 값
	private String PL_BEI_ELC_STATE = ""; //입자 전자배치
	private String PL_BEI_OTH_STRUC = ""; //입자 미세구조
	/**
	 * @return the pL_CPBI_ELE_NUM
	 */
	public String getPL_CPBI_ELE_NUM() {
		return PL_CPBI_ELE_NUM;
	}
	/**
	 * @return the pL_CPBI_ELE_SYMBOL
	 */
	public String getPL_CPBI_ELE_SYMBOL() {
		return PL_CPBI_ELE_SYMBOL;
	}
	/**
	 * @return the pL_BEI_CHG_STATE
	 */
	public String getPL_BEI_CHG_STATE() {
		return PL_BEI_CHG_STATE;
	}
	/**
	 * @return the pL_CPII_ION_CHARGE
	 */
	public String getPL_CPII_ION_CHARGE() {
		return PL_CPII_ION_CHARGE;
	}
	/**
	 * @return the pL_CPII_TXT_FOMULA
	 */
	public String getPL_CPII_TXT_FOMULA() {
		return PL_CPII_TXT_FOMULA;
	}
	/**
	 * @return the pL_CPII_NFRI_FOMULA
	 */
	public String getPL_CPII_NFRI_FOMULA() {
		return PL_CPII_NFRI_FOMULA;
	}
	/**
	 * @return the pL_CPII_INCHI
	 */
	public String getPL_CPII_INCHI() {
		return PL_CPII_INCHI;
	}
	/**
	 * @return the pL_CPII_INCHI_KEY
	 */
	public String getPL_CPII_INCHI_KEY() {
		return PL_CPII_INCHI_KEY;
	}
	/**
	 * @return the pL_CPII_STOCHIOMETRIC
	 */
	public String getPL_CPII_STOCHIOMETRIC() {
		return PL_CPII_STOCHIOMETRIC;
	}
	/**
	 * @return the pL_BEI_ELC_STATE
	 */
	public String getPL_BEI_ELC_STATE() {
		return PL_BEI_ELC_STATE;
	}
	/**
	 * @return the pL_BEI_OTH_STRUC
	 */
	public String getPL_BEI_OTH_STRUC() {
		return PL_BEI_OTH_STRUC;
	}
	/**
	 * @param pL_CPBI_ELE_NUM the pL_CPBI_ELE_NUM to set
	 */
	public void setPL_CPBI_ELE_NUM(String pL_CPBI_ELE_NUM) {
		PL_CPBI_ELE_NUM = pL_CPBI_ELE_NUM;
	}
	/**
	 * @param pL_CPBI_ELE_SYMBOL the pL_CPBI_ELE_SYMBOL to set
	 */
	public void setPL_CPBI_ELE_SYMBOL(String pL_CPBI_ELE_SYMBOL) {
		PL_CPBI_ELE_SYMBOL = pL_CPBI_ELE_SYMBOL;
	}
	/**
	 * @param pL_BEI_CHG_STATE the pL_BEI_CHG_STATE to set
	 */
	public void setPL_BEI_CHG_STATE(String pL_BEI_CHG_STATE) {
		PL_BEI_CHG_STATE = pL_BEI_CHG_STATE;
	}
	/**
	 * @param pL_CPII_ION_CHARGE the pL_CPII_ION_CHARGE to set
	 */
	public void setPL_CPII_ION_CHARGE(String pL_CPII_ION_CHARGE) {
		PL_CPII_ION_CHARGE = pL_CPII_ION_CHARGE;
	}
	/**
	 * @param pL_CPII_TXT_FOMULA the pL_CPII_TXT_FOMULA to set
	 */
	public void setPL_CPII_TXT_FOMULA(String pL_CPII_TXT_FOMULA) {
		PL_CPII_TXT_FOMULA = pL_CPII_TXT_FOMULA;
	}
	/**
	 * @param pL_CPII_NFRI_FOMULA the pL_CPII_NFRI_FOMULA to set
	 */
	public void setPL_CPII_NFRI_FOMULA(String pL_CPII_NFRI_FOMULA) {
		PL_CPII_NFRI_FOMULA = pL_CPII_NFRI_FOMULA;
	}
	/**
	 * @param pL_CPII_INCHI the pL_CPII_INCHI to set
	 */
	public void setPL_CPII_INCHI(String pL_CPII_INCHI) {
		PL_CPII_INCHI = pL_CPII_INCHI;
	}
	/**
	 * @param pL_CPII_INCHI_KEY the pL_CPII_INCHI_KEY to set
	 */
	public void setPL_CPII_INCHI_KEY(String pL_CPII_INCHI_KEY) {
		PL_CPII_INCHI_KEY = pL_CPII_INCHI_KEY;
	}
	/**
	 * @param pL_CPII_STOCHIOMETRIC the pL_CPII_STOCHIOMETRIC to set
	 */
	public void setPL_CPII_STOCHIOMETRIC(String pL_CPII_STOCHIOMETRIC) {
		PL_CPII_STOCHIOMETRIC = pL_CPII_STOCHIOMETRIC;
	}
	/**
	 * @param pL_BEI_ELC_STATE the pL_BEI_ELC_STATE to set
	 */
	public void setPL_BEI_ELC_STATE(String pL_BEI_ELC_STATE) {
		PL_BEI_ELC_STATE = pL_BEI_ELC_STATE;
	}
	/**
	 * @param pL_BEI_OTH_STRUC the pL_BEI_OTH_STRUC to set
	 */
	public void setPL_BEI_OTH_STRUC(String pL_BEI_OTH_STRUC) {
		PL_BEI_OTH_STRUC = pL_BEI_OTH_STRUC;
	}
	
	public Vector<String> getDataList(){
		Vector<String> value = new Vector<String>();
		value.addElement(this.PL_CPBI_ELE_NUM);
		value.addElement(this.PL_CPBI_ELE_SYMBOL);
		value.addElement(this.PL_BEI_CHG_STATE);
		value.addElement(this.PL_CPII_ION_CHARGE);
		value.addElement(this.PL_CPII_TXT_FOMULA);
		value.addElement(this.PL_CPII_NFRI_FOMULA);	
		value.addElement(this.PL_CPII_INCHI);
		value.addElement(this.PL_CPII_INCHI_KEY);
		value.addElement(this.PL_CPII_STOCHIOMETRIC);
		value.addElement(this.PL_BEI_ELC_STATE);
		value.addElement(this.PL_BEI_OTH_STRUC);
		return value;
	}
}
