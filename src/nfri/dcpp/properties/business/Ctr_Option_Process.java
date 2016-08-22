package nfri.dcpp.properties.business;

import java.util.Vector;

import nfri.dcpp.properties.db.DAO_CODE_INFO;
import nfri.dcpp.properties.db.DAO_REF_ARTICLE_INFO;

/**
 *
 * @Project : dcpp_web
 * @Title : Ctr_Property_Option.java
 * @Description : 물성 기본 정보와 관련한 옵션 코드의 모든 처리를 컨트롤하는 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 01. 16
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class Ctr_Option_Process {
	
	//기본 생성자
	public Ctr_Option_Process(){
	}
	
	/**
	 * @MethodName : getTBOption
	 * @Desc : DB에서 대분류에 들어갈 항목을 가져오는 business 메소드
	 * @return
	 */
	public Vector<?> getTBOption(){
		DAO_CODE_INFO code = new DAO_CODE_INFO();
		//Select_PL_CODE_INFO_TB info = new Select_PL_CODE_INFO_TB();
		Vector<?> v = code.selectOptionList("TB");//info.executeSelect();	
		return v;		
	}
	
	/**
	 * @MethodName : getDBOption
	 * @Desc : DB에서 데이터분류에 들어갈 항목을 가져오는 business 메소드
	 * @return
	 */
	public Vector<?> getDBOption(){
		DAO_CODE_INFO code = new DAO_CODE_INFO();
		Vector<?> v = code.selectOptionList("DB");	
		return v;
		/*Select_PL_CODE_INFO_DB info = new Select_PL_CODE_INFO_DB();
		Vector v = info.executeSelect();	
		return v;*/
	}
	
	/**
	 * @MethodName : getICOption
	 * @Desc : DB에서 충돌방식에 들어갈 항목을 가져오는 business 메소드
	 * @return
	 */
	public Vector<?> getICOption(){
		DAO_CODE_INFO code = new DAO_CODE_INFO();
		Vector<?> v = code.selectOptionList("IC");	
		return v;
		/*Select_PL_CODE_INFO_IC info = new Select_PL_CODE_INFO_IC();
		Vector v = info.executeSelect();	
		return v;*/
	}
	
	/**
	 * @MethodName : getMPOption
	 * @Desc : DB에서 주프로세스에 들어갈 항목을 가져오는 business 메소드
	 * @return
	 */
	public Vector<?> getMPOption(){
		DAO_CODE_INFO code = new DAO_CODE_INFO();
		Vector<?> v = code.selectOptionList("MP");	
		return v;
		/*Select_PL_CODE_INFO_MP info = new Select_PL_CODE_INFO_MP();
		Vector v = info.executeSelect();	
		return v;*/
	}
	
	/**
	 * @MethodName : getSPOption
	 * @Desc : DB에서 부프로세스에 들어갈 항목을 가져오는 business 메소드
	 * @return
	 */
	public Vector<?> getSPOption(){
		DAO_CODE_INFO code = new DAO_CODE_INFO();
		Vector<?> v = code.selectOptionList("SP");	
		return v;
		/*Select_PL_CODE_INFO_SP info = new Select_PL_CODE_INFO_SP();
		Vector v = info.executeSelect();	
		return v;*/
	}
	
	/**
	 * @MethodName : getETOption
	 * @Desc : DB에서 검증구분에 들어갈 항목을 가져오는 business 메소드
	 * @return
	 */
	public Vector<?> getETOption(){
		DAO_CODE_INFO code = new DAO_CODE_INFO();
		Vector<?> v = code.selectOptionList("ET");	
		return v;
		/*Select_PL_CODE_INFO_ET info = new Select_PL_CODE_INFO_ET();
		Vector v = info.executeSelect();	
		return v;*/
	}
	
	/**
	 * @MethodName : getAPOption
	 * @Desc : DB에서 첨부코드 선택에 들어갈 항목을 가져오는 business 메소드
	 * @return
	 */
	public Vector<?> getAPOption(){
		DAO_CODE_INFO code = new DAO_CODE_INFO();
		Vector<?> v = code.selectOptionList("AP");	
		return v;
		/*Select_PL_CODE_INFO_AP info = new Select_PL_CODE_INFO_AP();
		Vector v = info.executeSelect();	
		return v;*/
	}
	
	/**
	 * @MethodName : getSOOption
	 * @Desc : 조회시 필요한 연산자를 DB에서 가져오는 business 메소드
	 * @return
	 */
	public Vector<?> getSOOption(){
		DAO_CODE_INFO code = new DAO_CODE_INFO();
		Vector<?> v = code.selectOptionList("SO");	
		return v;
	}
	
	/**
	 * @MethodName : getAMOption
	 * @Desc : 입자 정보 입력시 필요한 구분 타입을 DB에서 가져오는 business 메소드
	 * @return
	 */
	public Vector<?> getAMOption(){
		DAO_CODE_INFO code = new DAO_CODE_INFO();
		Vector<?> v = code.selectOptionList("AM");	
		return v;
	}
	
	/**
	 * @MethodName : getXUOption
	 * @Desc : 그래프 X단위 정보를 DB에서 가져오는 business 메소드
	 * @return
	 */
	public Vector<?> getXUOption(){
		DAO_CODE_INFO code = new DAO_CODE_INFO();
		Vector<?> v = code.selectOptionList("XU");	
		return v;
	}
	
	/**
	 * @MethodName : getYUOption
	 * @Desc : 그래프 Y단위 정보를 DB에서 가져오는 business 메소드
	 * @return
	 */
	public Vector<?> getYUOption(){
		DAO_CODE_INFO code = new DAO_CODE_INFO();
		Vector<?> v = code.selectOptionList("YU");	
		return v;
	}
	
	/**
	 * @MethodName : getRefArticleCode
	 * @Desc : 참고문헌 종류코드를 가져오는 메소드
	 * @return
	 */
	public Vector<?> getRefArticleCode(){
		DAO_REF_ARTICLE_INFO info = new DAO_REF_ARTICLE_INFO();
		Vector<?> v = info.selectArticleCode();
		return v;
	}
	
	/**
	 * @MethodName : getExpName
	 * @Desc : 옵션 이름 설명 값을 가져오는 business 메소드
	 * @param ci_id
	 * @return
	 */
	public String getExpName(String ci_id){
		DAO_CODE_INFO code = new DAO_CODE_INFO();
		return code.selectOptionExp(ci_id);
	}
}
