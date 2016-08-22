package nfri.dcpp.properties.model;

/**
 *
 * @Project : dcpp_web
 * @Title : Article_Code.java
 * @Description : 참고문헌종류코드와 관련된 데이터 작업을 수행하는 모델 클래스
 * @Author : JUNSIDE
 * @Date : 2008. 11. 10
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class Article_Code {
	//참고문헌종류코드 ID
	private String PL_RACI_CODE_ID = "";
	//참고문헌종류코드 설명
	private String PL_RACI_CODE_EXP = "";
		
	/**
	 * @MethodName : getPL_RACI_CODE_ID
	 * @Desc : 참고문헌종류코드 ID를 가져오는 메소드
	 * @return
	 */
	public String getPL_RACI_CODE_ID(){
		return this.PL_RACI_CODE_ID;
	}
		
	/**
	 * @MethodName : getPL_RACI_CODE_EXP
	 * @Desc : 참고문헌종류코드의 설명을 가져오는 메소드
	 * @return
	 */
	public String getPL_RACI_CODE_EXP(){
		return this.PL_RACI_CODE_EXP;
	}
		
	/**
	 * @MethodName : setPL_RACI_CODE_ID
	 * @Desc : 참고문헌종류코드 ID를 지정하는 메소드
	 * @param id
	 */
	public void setPL_RACI_CODE_ID(String id){
		this.PL_RACI_CODE_ID = id;
	}
		
	/**
	 * @MethodName : setPL_RACI_CODE_EXP
	 * @Desc : 참고문헌종류코드의 설명을 지정하는 메소드
	 * @param exp
	 */
	public void setPL_RACI_CODE_EXP(String exp){
		this.PL_RACI_CODE_EXP = exp;
	}
}
