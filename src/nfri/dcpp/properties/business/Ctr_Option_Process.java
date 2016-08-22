package nfri.dcpp.properties.business;

import java.util.Vector;

import nfri.dcpp.properties.db.DAO_CODE_INFO;
import nfri.dcpp.properties.db.DAO_REF_ARTICLE_INFO;

/**
 *
 * @Project : dcpp_web
 * @Title : Ctr_Property_Option.java
 * @Description : ���� �⺻ ������ ������ �ɼ� �ڵ��� ��� ó���� ��Ʈ���ϴ� Ŭ����
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 01. 16
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @�����̷� : -
 *
 */
public class Ctr_Option_Process {
	
	//�⺻ ������
	public Ctr_Option_Process(){
	}
	
	/**
	 * @MethodName : getTBOption
	 * @Desc : DB���� ��з��� �� �׸��� �������� business �޼ҵ�
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
	 * @Desc : DB���� �����ͺз��� �� �׸��� �������� business �޼ҵ�
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
	 * @Desc : DB���� �浹��Ŀ� �� �׸��� �������� business �޼ҵ�
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
	 * @Desc : DB���� �����μ����� �� �׸��� �������� business �޼ҵ�
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
	 * @Desc : DB���� �����μ����� �� �׸��� �������� business �޼ҵ�
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
	 * @Desc : DB���� �������п� �� �׸��� �������� business �޼ҵ�
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
	 * @Desc : DB���� ÷���ڵ� ���ÿ� �� �׸��� �������� business �޼ҵ�
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
	 * @Desc : ��ȸ�� �ʿ��� �����ڸ� DB���� �������� business �޼ҵ�
	 * @return
	 */
	public Vector<?> getSOOption(){
		DAO_CODE_INFO code = new DAO_CODE_INFO();
		Vector<?> v = code.selectOptionList("SO");	
		return v;
	}
	
	/**
	 * @MethodName : getAMOption
	 * @Desc : ���� ���� �Է½� �ʿ��� ���� Ÿ���� DB���� �������� business �޼ҵ�
	 * @return
	 */
	public Vector<?> getAMOption(){
		DAO_CODE_INFO code = new DAO_CODE_INFO();
		Vector<?> v = code.selectOptionList("AM");	
		return v;
	}
	
	/**
	 * @MethodName : getXUOption
	 * @Desc : �׷��� X���� ������ DB���� �������� business �޼ҵ�
	 * @return
	 */
	public Vector<?> getXUOption(){
		DAO_CODE_INFO code = new DAO_CODE_INFO();
		Vector<?> v = code.selectOptionList("XU");	
		return v;
	}
	
	/**
	 * @MethodName : getYUOption
	 * @Desc : �׷��� Y���� ������ DB���� �������� business �޼ҵ�
	 * @return
	 */
	public Vector<?> getYUOption(){
		DAO_CODE_INFO code = new DAO_CODE_INFO();
		Vector<?> v = code.selectOptionList("YU");	
		return v;
	}
	
	/**
	 * @MethodName : getRefArticleCode
	 * @Desc : ������ �����ڵ带 �������� �޼ҵ�
	 * @return
	 */
	public Vector<?> getRefArticleCode(){
		DAO_REF_ARTICLE_INFO info = new DAO_REF_ARTICLE_INFO();
		Vector<?> v = info.selectArticleCode();
		return v;
	}
	
	/**
	 * @MethodName : getExpName
	 * @Desc : �ɼ� �̸� ���� ���� �������� business �޼ҵ�
	 * @param ci_id
	 * @return
	 */
	public String getExpName(String ci_id){
		DAO_CODE_INFO code = new DAO_CODE_INFO();
		return code.selectOptionExp(ci_id);
	}
}
