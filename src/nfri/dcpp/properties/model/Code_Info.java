package nfri.dcpp.properties.model;

/**
 *
 * @Project : dcpp_web
 * @Title : Code_Info.java
 * @Description : �ڵ� ������ ��� �� Ŭ����
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2008. 11. 16
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @�����̷� : -
 *
 */
public class Code_Info {
	//�ڵ� ID
	private String PL_CI_ID = "";
	//�ڵ� ��
	private String PL_CI_NAME = "";
	//�ڵ� ����
	private String PL_CI_ID_EXP = "";
	//�ڵ� �׷�
	private String PL_CI_GROUP = "";
	
	public String getPL_CI_ID(){
		return PL_CI_ID;
	}
	
	public String getPL_CI_NAME(){
		return PL_CI_NAME;
	}
	
	public String getPL_CI_GROUP(){
		return PL_CI_GROUP;
	}
	
	public String getPL_CI_ID_EXP(){
		return PL_CI_ID_EXP;
	}
	
	public void setPL_CI_ID(String id){
		this.PL_CI_ID = id;
	}
	
	public void setPL_CI_NAME(String name){
		this.PL_CI_NAME = name;
	}
	
	public void setPL_CI_ID_EXP(String exp){
		this.PL_CI_ID_EXP = exp;
	}
	
	public void setPL_CI_GROUP(String group){
		this.PL_CI_GROUP = group;
	}
}
