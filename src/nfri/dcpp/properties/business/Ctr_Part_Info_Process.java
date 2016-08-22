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
 * @Description : �⺻ ���� ������ ���õ� ó���� �ϴ� Business Ŭ����
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 02. 02
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @�����̷�
 * 	- 2009.02.16 : PL_CHEM_PART_INFO ���̺� �������� ���� insert�޼ҵ� ����
 *
 */
public class Ctr_Part_Info_Process {
	//������
	public Ctr_Part_Info_Process(){
			
	}
	
	/**
	 * @MethodName  : selectPartInfo
	 * @Date   : 2013. 7. 15. 
	 * @MethodDescription : �������� ��ü�� �������� �޼ҵ�
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
	 * @MethodDescription : �ϳ��� ���� ���� �������� �޼ҵ�
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
	 * @Desc : �⺻ ���� ������ �������� �޼ҵ�
	 * @return
	 */
	public Vector<?> selectPartInfo(HttpServletRequest request){
		String option = ComUtil.isNullToDashString(request.getParameter("part"));
		
		String first_option = ComUtil.isNullToDashString(request.getParameter("first"));
		
		
		//System.out.println("first_option : " + first_option);
		
		Vector<?> vecList = new Vector<Object>();
		DAO_CHEM_PART_INFO part = new DAO_CHEM_PART_INFO();
		
		if("-".equalsIgnoreCase(first_option)){//ó�����̴� ���, ��ü�˻�
			//System.out.println("------1 ��ü�˻�------");
			vecList = part.selectPartInfo();	
		}else{// �׿ܴ� ���� �˻�
			if(option.equalsIgnoreCase("-")){
				vecList = part.selectPartInfo();	
			}else{
				//System.out.println("------2 Ư���˻�------");
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
	 * @Desc : ���� ���� �����ϴ� �޼ҵ�
	 * @param partInfo
	 * @return
	 * @throws SQLException
	 */
	public boolean insertPartInfo(Basic_Part_Info partInfo) throws SQLException{	
	
		//DB �Է� ȣ��
		//Insert_PL_CHEM_PART_BASIC_INFO info = new Insert_PL_CHEM_PART_BASIC_INFO();	
		DAO_CHEM_PART_INFO info = new DAO_CHEM_PART_INFO();
				
		//�ڵ�ID�� Name ���� ��ȯ�ϱ� ���� �Ŵ��� ȣ��
		DAO_CODE_INFO code_mgr = new DAO_CODE_INFO();

		String re_name = code_mgr.selectOptionName(partInfo.getPL_CPBI_ELE_TYPE());		

		//��ȣ�� �����
		String cp_num = makePartNum(re_name);
		
		//�� �����ϱ�
		partInfo.setPL_CPBI_ELE_NUM(cp_num);
		partInfo.setPL_CPBI_ELE_TYPE(re_name);
		
		boolean flag = info.insertPartInfo(partInfo);
		
		return flag;
	}	
	
	/**
	 * @MethodName  : updatePartInfo
	 * @Date   : 2013. 7. 18. 
	 * @MethodDescription : ����/���� ������ ������Ʈ �ϴ� �޼ҵ�
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
		
		//DB �Է� ȣ��	
		DAO_CHEM_PART_INFO info = new DAO_CHEM_PART_INFO();
		
		boolean flag = false; 

		if("0".equalsIgnoreCase(seq)){ //���� ��ȣ
			//System.out.println("����");
			flag = info.updatePartSymbolInfo(sqlOption);
		}else if("1".equalsIgnoreCase(seq)){ //���� �̸�
			//System.out.println("�Է�");
			flag = info.updatePartNameInfo(sqlOption);
		}else if("2".equalsIgnoreCase(seq)){ //���� ����
			//System.out.println("�Է�");
			flag = info.updatePartMassInfo(sqlOption);
		}else if("3".equalsIgnoreCase(seq)){ //���� ����
			//System.out.println("�Է�");
			flag = info.updatePartTypeInfo(sqlOption);
		}else if("4".equalsIgnoreCase(seq)){ //���� ���ڹ�ȣ
			//System.out.println("�Է�");
			flag = info.updatePartAmnumInfo(sqlOption);
		}else if("5".equalsIgnoreCase(seq)){ //���� ����
			//System.out.println("�Է�");
			flag = info.updatePartAmcountInfo(sqlOption);
		}		
		
		return flag;
	}
	
	/**
	 * @MethodName : makePartNum
	 * @Desc : ���� ��ȣ�� ����� �޼ҵ�
	 * @param at_type
	 * @return
	 */
	public String makePartNum(String at_type){
		String returnValue = "";
		
		//��ȣ�� �޾ƿ��� ���� SQL �� ����
		DAO_CHEM_PART_INFO seq = new DAO_CHEM_PART_INFO();
		String sqlValue = seq.selectPartSeq();
		
		//��ȣ �����
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
