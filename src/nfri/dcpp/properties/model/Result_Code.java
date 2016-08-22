package nfri.dcpp.properties.model;


/**
 *
 * @Project : dcpp_web
 * @Title : Result_Code.java
 * @Description : 결과코드 값을 지정하고 가져오는 클래스
 * @Author : JUNSIDE
 * @Date : 2008. 11. 10
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class Result_Code {
	//결과코드 ID
	private String RESULT_CODE_ID = "";
	//결과코드 설명
	private String RESULT_CODE_EXP = "";
		
	/**
	 * @MethodName : getPL_RACI_CODE_ID
	 * @Desc : 결과코드 ID를 가져오는 메소드
	 * @return
	 */
	public String getRESULT_CODE_ID(){
		return RESULT_CODE_ID;
	}
		
	/**
	 * @MethodName : getPL_RACI_CODE_EXP
	 * @Desc : 결과코드의 설명을 가져오는 메소드
	 * @return
	 */
	public String getRESULT_CODE_EXP(){
		return RESULT_CODE_EXP;
	}
		
	/**
	 * @MethodName : setPL_RACI_CODE_ID
	 * @Desc : 결과코드 ID를 지정하는 메소드
	 * @param id
	 */
	public void setRESULT_CODE_ID(String id){
		RESULT_CODE_ID = id;
	}
		
	/**
	 * @MethodName : setPL_RACI_CODE_EXP
	 * @Desc : 결과코드의 설명을 지정하는 메소드
	 * @param exp
	 */
	public void setRESULT_CODE_EXP(String exp){
		RESULT_CODE_EXP = exp;
	}
}
