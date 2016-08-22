package nfri.dcpp.member.model;

/**
 *
 * @Project : dcpp_web
 * @Title : User_Info.java
 * @Description : 사용자 정보를 담는 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 01. 07
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class User_Info {
	
	//User 번호
	private String PL_UI_SEQ = null;
	//User 아이디
	private String PL_UI_ID = null;
	//User 패스워드
	private String PL_UI_PASSWD = null;
	//User 이름
	private String PL_UI_NAME = null;
	//User 부서명
	private String PL_UI_ORG = null;
	//User 연락처 (핸드폰)
	private String PL_UI_CPHONE = null;
	//User 연락처 (회사)
	private String PL_UI_OPHONE = null;
	//User 주소
	private String PL_UI_ADDRESS = null;
	//User 권한등급
	private String PL_AI_GRADE = null;
	//Usert 이메일
	private String PL_UI_EMAIL = null; 
	
	public void setPL_UI_SEQ(String seq){
		this.PL_UI_SEQ = seq;
	}
	public void setPL_UI_ID(String id){
		this.PL_UI_ID = id;
	}
	public void setPL_UI_PASSWD(String pwd){
		this.PL_UI_PASSWD = pwd;
	}
	public void setPL_UI_NAME(String name){
		this.PL_UI_NAME = name;
	}
	public void setPL_UI_ORG(String org){
		this.PL_UI_ORG = org;
	}
	public void setPL_UI_CPHONE(String cp){
		this.PL_UI_CPHONE = cp;
	}
	public void setPL_UI_OPHONE(String op){
		this.PL_UI_OPHONE = op;
	}
	public void setPL_UI_ADDRESS(String add){
		this.PL_UI_ADDRESS = add;
	}
	public void setPL_AI_GRADE(String grade){
		this.PL_AI_GRADE = grade;
	}
	public void setPL_UI_EMAIL(String pl_ui_email) {
		PL_UI_EMAIL = pl_ui_email;
	}
	
	public String getPL_UI_SEQ(){
		return this.PL_UI_SEQ;
	}
	public String getPL_UI_ID(){
		return this.PL_UI_ID;
	}
	public String getPL_UI_PASSWD(){
		return this.PL_UI_PASSWD;
	}
	public String getPL_UI_NAME(){
		return this.PL_UI_NAME;
	}
	public String getPL_UI_ORG(){
		return this.PL_UI_ORG;
	}
	public String getPL_UI_CPHONE(){
		return this.PL_UI_CPHONE;
	}
	public String getPL_UI_OPHONE(){
		return this.PL_UI_OPHONE;
	}
	public String getPL_UI_ADDRESS(){
		return this.PL_UI_ADDRESS;
	}
	public String getPL_AI_GRADE(){
		return this.PL_AI_GRADE;
	}
	public String getPL_UI_EMAIL() {
		return PL_UI_EMAIL;
	}	
}
