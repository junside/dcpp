package nfri.dcpp.properties.business;

import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import nfri.dcpp.com.util.ComUtil;
import nfri.dcpp.properties.db.DAO_CHEM_PART_INFO;
import nfri.dcpp.properties.db.DAO_CODE_INFO;
import nfri.dcpp.properties.model.Basic_Part_Info;

/**
 *
 * @Project : dcpp_web
 * @Title : Ctr_Part_Info_Process.java
 * @Description : 기본 입자 정보와 관련된 처리를 하는 Business 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 02. 02
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력
 * 	- 2009.02.16 : PL_CHEM_PART_INFO 테이블 수정으로 인한 insert메소드 수정
 *
 */
public class Ctr_Part_Info_Process {
	//생성자
	public Ctr_Part_Info_Process(){
			
	}
	
	/**
	 * @MethodName  : selectPartInfo
	 * @Date   : 2013. 7. 15. 
	 * @MethodDescription : 입자정보 전체를 가져오는 메소드
	 * @return
	 * @History  : - 
	 */
	public Vector<?> selectPartInfo(){
		Vector<?> vecList = new Vector<Object>();
		DAO_CHEM_PART_INFO part = new DAO_CHEM_PART_INFO();
		vecList = part.selectPartInfo();
/*
		if(option.equalsIgnoreCase("-")){
				
		}else{
			option = "%"+option+"%";
			Vector v_option = new Vector();
			v_option.addElement(option);
			vecList = part.selectPartInfo(v_option);
		}
*/
		//System.out.println("------1------");
		return vecList;
	}
	
	/**
	 * @MethodName  : selectPartInfo
	 * @Date   : 2013. 7. 15. 
	 * @MethodDescription : 하나의 입자 정보 가져오는 메소드
	 * @param part_id
	 * @return
	 * @History  : - 
	 */
	public Basic_Part_Info selectPartInfo(String part_id){

		DAO_CHEM_PART_INFO part = new DAO_CHEM_PART_INFO();
		
		Basic_Part_Info part_info = part.selectPartInfo(part_id);

		return part_info;
	}
	
	/**
	 * @MethodName : selectPartInfo
	 * @Desc : 기본 입자 정보를 가져오는 메소드
	 * @return
	 */
	public Vector<?> selectPartInfo(HttpServletRequest request){
		String option = ComUtil.isNullToDashString(request.getParameter("part"));
		
		String first_option = ComUtil.isNullToDashString(request.getParameter("first"));
		
		
		//System.out.println("first_option : " + first_option);
		
		Vector<?> vecList = new Vector<Object>();
		DAO_CHEM_PART_INFO part = new DAO_CHEM_PART_INFO();
		
		if("-".equalsIgnoreCase(first_option)){//처음보이는 대로, 전체검색
			//System.out.println("------1 전체검색------");
			vecList = part.selectPartInfo();	
		}else{// 그외는 조건 검색
			if(option.equalsIgnoreCase("-")){
				vecList = part.selectPartInfo();	
			}else{
				//System.out.println("------2 특정검색------");
				//System.out.println("option : " + option);
				option = "%"+option+"%";
				Vector<String> v_option = new Vector<String>();
				v_option.addElement(option);
				vecList = part.selectPartInfo(v_option);
			}
			//option = "%"+option+"%";
			//Vector v_option = new Vector();
			//v_option.addElement(option);
			//vecList = part.selectPartInfo(v_option);
		}
		
		/*if(option.equalsIgnoreCase("-")){
			vecList = part.selectPartInfo();	
		}else{
			option = "%"+option+"%";
			Vector v_option = new Vector();
			v_option.addElement(option);
			vecList = part.selectPartInfo(v_option);
		}*/
		//System.out.println("------2------");
		return vecList;
	}
	
	/**
	 * @MethodName : insertPartInfo
	 * @Desc : 입자 정보 저장하는 메소드
	 * @param partInfo
	 * @return
	 * @throws SQLException
	 */
	public boolean insertPartInfo(Basic_Part_Info partInfo) throws SQLException{	
	
		//DB 입력 호출
		//Insert_PL_CHEM_PART_BASIC_INFO info = new Insert_PL_CHEM_PART_BASIC_INFO();	
		DAO_CHEM_PART_INFO info = new DAO_CHEM_PART_INFO();
				
		//코드ID를 Name 으로 변환하기 위한 매니져 호출
		DAO_CODE_INFO code_mgr = new DAO_CODE_INFO();

		String re_name = code_mgr.selectOptionName(partInfo.getPL_CPBI_ELE_TYPE());		

		//번호값 만들기
		String cp_num = makePartNum(re_name);
		
		//값 세팅하기
		partInfo.setPL_CPBI_ELE_NUM(cp_num);
		partInfo.setPL_CPBI_ELE_TYPE(re_name);
		
		boolean flag = info.insertPartInfo(partInfo);
		
		return flag;
	}	
	
	/**
	 * @MethodName  : updatePartInfo
	 * @Date   : 2013. 7. 18. 
	 * @MethodDescription : 원자/분자 정보를 업데이트 하는 메소드
	 * @param part_no
	 * @param seq
	 * @param value
	 * @return
	 * @throws SQLException
	 * @History  : - 
	 */
	public boolean updatePartInfo(String part_no, String seq, String value) throws SQLException{
		
		Vector<String> sqlOption = new Vector<String>();
		sqlOption.addElement(value);
		sqlOption.addElement(part_no);
		
		//DB 입력 호출	
		DAO_CHEM_PART_INFO info = new DAO_CHEM_PART_INFO();
		
		boolean flag = false; 

		if("0".equalsIgnoreCase(seq)){ //입자 기호
			//System.out.println("수정");
			flag = info.updatePartSymbolInfo(sqlOption);
		}else if("1".equalsIgnoreCase(seq)){ //입자 이름
			//System.out.println("입력");
			flag = info.updatePartNameInfo(sqlOption);
		}else if("2".equalsIgnoreCase(seq)){ //입자 질량
			//System.out.println("입력");
			flag = info.updatePartMassInfo(sqlOption);
		}else if("3".equalsIgnoreCase(seq)){ //입자 유형
			//System.out.println("입력");
			flag = info.updatePartTypeInfo(sqlOption);
		}else if("4".equalsIgnoreCase(seq)){ //입자 원자번호
			//System.out.println("입력");
			flag = info.updatePartAmnumInfo(sqlOption);
		}else if("5".equalsIgnoreCase(seq)){ //입자 개수
			//System.out.println("입력");
			flag = info.updatePartAmcountInfo(sqlOption);
		}		
		
		return flag;
	}
	
	/**
	 * @MethodName : makePartNum
	 * @Desc : 입자 번호를 만드는 메소드
	 * @param at_type
	 * @return
	 */
	public String makePartNum(String at_type){
		String returnValue = "";
		
		//번호를 받아오기 위한 SQL 문 실행
		DAO_CHEM_PART_INFO seq = new DAO_CHEM_PART_INFO();
		String sqlValue = seq.selectPartSeq();
		
		//번호 만들기
		int count = sqlValue.length();
		String zeroString = "0";
		String combineValue = "";
		for(int i = 4 ; i > count ; i--)
		{
			combineValue = combineValue + zeroString;
		}
		
		returnValue = at_type + combineValue + sqlValue;
		
		return returnValue;
	}
}
