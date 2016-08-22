package nfri.dcpp.properties.model;

/**
 *
 * @Project : dcpp_web
 * @Title : Article_Code.java
 * @Description : �����������ڵ�� ���õ� ������ �۾��� �����ϴ� �� Ŭ����
 * @Author : JUNSIDE
 * @Date : 2008. 11. 10
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @�����̷� : -
 *
 */
public class Article_Code {
	//�����������ڵ� ID
	private String PL_RACI_CODE_ID = "";
	//�����������ڵ� ����
	private String PL_RACI_CODE_EXP = "";
		
	/**
	 * @MethodName : getPL_RACI_CODE_ID
	 * @Desc : �����������ڵ� ID�� �������� �޼ҵ�
	 * @return
	 */
	public String getPL_RACI_CODE_ID(){
		return this.PL_RACI_CODE_ID;
	}
		
	/**
	 * @MethodName : getPL_RACI_CODE_EXP
	 * @Desc : �����������ڵ��� ������ �������� �޼ҵ�
	 * @return
	 */
	public String getPL_RACI_CODE_EXP(){
		return this.PL_RACI_CODE_EXP;
	}
		
	/**
	 * @MethodName : setPL_RACI_CODE_ID
	 * @Desc : �����������ڵ� ID�� �����ϴ� �޼ҵ�
	 * @param id
	 */
	public void setPL_RACI_CODE_ID(String id){
		this.PL_RACI_CODE_ID = id;
	}
		
	/**
	 * @MethodName : setPL_RACI_CODE_EXP
	 * @Desc : �����������ڵ��� ������ �����ϴ� �޼ҵ�
	 * @param exp
	 */
	public void setPL_RACI_CODE_EXP(String exp){
		this.PL_RACI_CODE_EXP = exp;
	}
}
