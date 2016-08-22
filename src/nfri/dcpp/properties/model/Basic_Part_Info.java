package nfri.dcpp.properties.model;

import java.util.Vector;

/**
 *
 * @Project : dcpp_web
 * @Title : Basic_Part_Info.java
 * @Description : 기본 입자 정보와 관련된 데이터 작업을 수행하는 모델 클래스 
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 02. 02
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력
 * 	- 2009.2.16 : DB TABLE 변경으로 인한 컬럼 항목 수정으로 메소드명 및 변수명 수정 
 *
 */
public class Basic_Part_Info {
	private String PL_CPBI_ELE_NUM = "";
	private String PL_CPBI_ELE_SYMBOL = ""; 
	private String PL_CPBI_ELE_MASS = "";
	private String PL_CPBI_ELE_NAME = "";
	private String PL_CPBI_ELE_TYPE = "";
	private String PL_CPBI_ELE_AMNUM = "";
	private String PL_CPBI_ELE_AMCOUNT = "";
	
	public String getPL_CPBI_ELE_NUM(){
		return this.PL_CPBI_ELE_NUM;
	}
	
	public String getPL_CPBI_ELE_SYMBOL(){
		return this.PL_CPBI_ELE_SYMBOL;
	}
	
	public String getPL_CPBI_ELE_MASS(){
		return this.PL_CPBI_ELE_MASS;
	}
	
	public String getPL_CPBI_ELE_NAME(){
		return this.PL_CPBI_ELE_NAME;
	}
	
	public String getPL_CPBI_ELE_TYPE(){
		return this.PL_CPBI_ELE_TYPE;
	}
	
	public String getPL_CPBI_ELE_AMNUM(){
		return this.PL_CPBI_ELE_AMNUM;
	}
	
	/**
	 * @return the pL_CPBI_ELE_AMCOUNT
	 */
	public String getPL_CPBI_ELE_AMCOUNT() {
		return PL_CPBI_ELE_AMCOUNT;
	}

	public void setPL_CPBI_ELE_NUM(String num){
		this.PL_CPBI_ELE_NUM = num;
	}
	
	public void setPL_CPBI_ELE_SYMBOL(String sym){
		this.PL_CPBI_ELE_SYMBOL = sym;
	}
	
	public void setPL_CPBI_ELE_MASS(String mass){
		this.PL_CPBI_ELE_MASS = mass;
	}
	
	public void setPL_CPBI_ELE_NAME(String name){
		this.PL_CPBI_ELE_NAME = name;
	}
	
	public void setPL_CPBI_ELE_TYPE(String type){
		this.PL_CPBI_ELE_TYPE = type;
	}
	
	public void setPL_CPBI_ELE_AMNUM(String amnum){
		this.PL_CPBI_ELE_AMNUM = amnum;
	}
	
	/**
	 * @param pL_CPBI_ELE_AMCOUNT the pL_CPBI_ELE_AMCOUNT to set
	 */
	public void setPL_CPBI_ELE_AMCOUNT(String pL_CPBI_ELE_AMCOUNT) {
		PL_CPBI_ELE_AMCOUNT = pL_CPBI_ELE_AMCOUNT;
	}
	
	public Vector<String> getDataList(){
		Vector<String> value = new Vector<String>();
		value.addElement(this.PL_CPBI_ELE_NUM);
		value.addElement(this.PL_CPBI_ELE_SYMBOL);
		value.addElement(this.PL_CPBI_ELE_NAME);
		value.addElement(this.PL_CPBI_ELE_MASS);
		value.addElement(this.PL_CPBI_ELE_TYPE);
		value.addElement(this.PL_CPBI_ELE_AMNUM);	
		value.addElement(this.PL_CPBI_ELE_AMCOUNT);
		return value;
	}
}
