/**
 * 
 */
package nfri.icamdata.common;

import java.util.Vector;

/**
 * @Project: dcpp
 * @Title  : Basic_Icam_User_Info.java
 * @Description : -
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2016. 5. 17.
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class Basic_Icam_User_Info {
	private String ICAM_First_Name = "";
	private String ICAM_Last_Name = ""; 
	private String ICAM_Affiliation = "";
	private String ICAM_Email = "";
	private String ICAM_Country = "";
	private String ICAM_Salutation = "";
	private String ICAM_Comments = "";
	private String ICAM_Reg_Date = "";
	private String ICAM_Reg_Process = "";

	/**
	 * @return the iCAM_First_Name
	 */
	public String getICAM_First_Name() {
		return ICAM_First_Name;
	}
	/**
	 * @return the iCAM_Last_Name
	 */
	public String getICAM_Last_Name() {
		return ICAM_Last_Name;
	}
	/**
	 * @return the iCAM_Affilation
	 */
	public String getICAM_Affiliation() {
		return ICAM_Affiliation;
	}
	/**
	 * @return the iCAM_Email
	 */
	public String getICAM_Email() {
		return ICAM_Email;
	}
	/**
	 * @return the iCAM_Country
	 */
	public String getICAM_Country() {
		return ICAM_Country;
	}
	/**
	 * @return the iCAM_Salutation
	 */
	public String getICAM_Salutation() {
		return ICAM_Salutation;
	}
	/**
	 * @return the iCAM_Comments
	 */
	public String getICAM_Comments() {
		return ICAM_Comments;
	}
	/**
	 * @return the iCAM_Reg_Date
	 */
	public String getICAM_Reg_Date() {
		return ICAM_Reg_Date;
	}	
	/**
	 * @return the iCAM_Reg_Process
	 */
	public String getICAM_Reg_Process() {
		return ICAM_Reg_Process;
	}
	/**
	 * @param iCAM_First_Name the iCAM_First_Name to set
	 */
	public void setICAM_First_Name(String iCAM_First_Name) {
		ICAM_First_Name = iCAM_First_Name;
	}
	/**
	 * @param iCAM_Last_Name the iCAM_Last_Name to set
	 */
	public void setICAM_Last_Name(String iCAM_Last_Name) {
		ICAM_Last_Name = iCAM_Last_Name;
	}
	/**
	 * @param iCAM_Affilation the iCAM_Affilation to set
	 */
	public void setICAM_Affiliation(String iCAM_Affiliation) {
		ICAM_Affiliation = iCAM_Affiliation;
	}
	/**
	 * @param iCAM_Email the iCAM_Email to set
	 */
	public void setICAM_Email(String iCAM_Email) {
		ICAM_Email = iCAM_Email;
	}
	/**
	 * @param iCAM_Country the iCAM_Country to set
	 */
	public void setICAM_Country(String iCAM_Country) {
		ICAM_Country = iCAM_Country;
	}
	/**
	 * @param iCAM_Salutation the iCAM_Salutation to set
	 */
	public void setICAM_Salutation(String iCAM_Salutation) {
		ICAM_Salutation = iCAM_Salutation;
	}
	/**
	 * @param iCAM_Comments the iCAM_Comments to set
	 */
	public void setICAM_Comments(String iCAM_Comments) {
		ICAM_Comments = iCAM_Comments;
	}	
	/**
	 * @param iCAM_Reg_Date the iCAM_Reg_Date to set
	 */
	public void setICAM_Reg_Date(String iCAM_Reg_Date) {
		ICAM_Reg_Date = iCAM_Reg_Date;
	}
	/**
	 * @param iCAM_Reg_Process the iCAM_Reg_Process to set
	 */
	public void setICAM_Reg_Process(String iCAM_Reg_Process) {
		ICAM_Reg_Process = iCAM_Reg_Process;
	}
	
	public Vector<String> getDataList(){
		Vector<String> value = new Vector<String>();
		value.addElement(this.ICAM_First_Name);
		value.addElement(this.ICAM_Last_Name);
		value.addElement(this.ICAM_Affiliation);
		value.addElement(this.ICAM_Email);
		value.addElement(this.ICAM_Country);
		value.addElement(this.ICAM_Salutation);	
		value.addElement(this.ICAM_Comments);
		value.addElement(this.ICAM_Reg_Date);
		value.addElement(this.ICAM_Reg_Process);
		return value;
	}
}
