package nfri.dcpp.properties.model;


/**
 *
 * @Project : dcpp_web
 * @Title : Result_Code.java
 * @Description : ����ڵ� ���� �����ϰ� �������� Ŭ����
 * @Author : JUNSIDE
 * @Date : 2008. 11. 10
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @�����̷� : -
 *
 */
public class Result_Code {
	//����ڵ� ID
	private String RESULT_CODE_ID = "";
	//����ڵ� ����
	private String RESULT_CODE_EXP = "";
		
	/**
	 * @MethodName : getPL_RACI_CODE_ID
	 * @Desc : ����ڵ� ID�� �������� �޼ҵ�
	 * @return
	 */
	public String getRESULT_CODE_ID(){
		return RESULT_CODE_ID;
	}
		
	/**
	 * @MethodName : getPL_RACI_CODE_EXP
	 * @Desc : ����ڵ��� ������ �������� �޼ҵ�
	 * @return
	 */
	public String getRESULT_CODE_EXP(){
		return RESULT_CODE_EXP;
	}
		
	/**
	 * @MethodName : setPL_RACI_CODE_ID
	 * @Desc : ����ڵ� ID�� �����ϴ� �޼ҵ�
	 * @param id
	 */
	public void setRESULT_CODE_ID(String id){
		RESULT_CODE_ID = id;
	}
		
	/**
	 * @MethodName : setPL_RACI_CODE_EXP
	 * @Desc : ����ڵ��� ������ �����ϴ� �޼ҵ�
	 * @param exp
	 */
	public void setRESULT_CODE_EXP(String exp){
		RESULT_CODE_EXP = exp;
	}
}
