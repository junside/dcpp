package nfri.dcpp.member.business;

import java.util.Vector;

import nfri.dcpp.com.util.ComVar;
import nfri.dcpp.member.db.DAO_USER_INFO;
import nfri.dcpp.member.model.User_Info;

/**
 *
 * @Project : dcpp_web
 * @Title : Ctr_Member_Process.java
 * @Description : User�� ���õ� �۾��� ó���ϴ� Business Ŭ����
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 01. 07
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @�����̷� : -
 *
 */
public class Ctr_Member_Process { 
	
	//������
	public Ctr_Member_Process(){
			
	}
	
	/**
	 * @MethodName : selectUserCheck
	 * @Desc : ����� ������ Ȯ���ϴ� �޼ҵ�. DB�� �����ϴ� ����Ͻ� ����
	 * @param id
	 * @param pwd
	 * @return checkValue
	 */
	public String selectUserCheck(String id, String pwd, String auth){
		//String checkValue = "";
		DAO_USER_INFO userInfo = new DAO_USER_INFO();		
		
		//1. �����ϴ� ����� ID üũ
		if(userInfo.Select_User_ID(id) == false){ //��ȿ���� ���� �����
			
			return ComVar.USER_ID_INVAILD; 
			
		}else{ //��ȿ�� �����
			
			//2. ������ ��� ����� Password üũ
			if(userInfo.Select_User_Passwd(id, pwd) == false){ //��ȿ���� ���� �н�����
				
				return ComVar.USER_PWD_INVAILD; 
				
			}else{ //��ȿ�� �н�����
				
				//3. ����ڰ� �ش� ��ɿ� ���� ������ �ִ��� ���θ� üũ
				User_Info info = userInfo.Select_User_Info(id, pwd);
				String grade = info.getPL_AI_GRADE();
				if(grade.equalsIgnoreCase("UA001") || grade.equalsIgnoreCase("UA030")){
					//return ComVar.USER_AUTH_CERTIFIED_ALL; //��� ����	
					return ComVar.USER_AUTH_VAILD; //��� ����
				}
				
				if(checkUserAuth(auth, grade) == false){
					return ComVar.USER_AUTH_INVAILD; //��ȿ���� ���� ����� ����	
				}else{
					return ComVar.USER_AUTH_VAILD; //��ȿ�� ����� ����
				}
			}
		}
	}
	
	/**
	 * @MethodName : checkUserAuth
	 * @Desc : ����� ���� üũ �޼ҵ�
	 * @param func
	 * @param auth
	 * @return
	 */
	public boolean checkUserAuth(String func, String auth){
		boolean return_value = false;
		
		if(func.equalsIgnoreCase("IAU") || func.equalsIgnoreCase("AIU")){
			return_value = true;
		}else if(func.equalsIgnoreCase("IU")){ //�ɼ��� �Է� �ɼ��� ���
			if(auth.equalsIgnoreCase("UA001")){//1. ������ 
				return_value = true;
			}else if(auth.equalsIgnoreCase("UA010")){//2. �����Է¼������� 
				return_value = true;
			}else if(auth.equalsIgnoreCase("UA011")){//3. ������ȸ�� ����
				return_value = true;
			}else if(auth.equalsIgnoreCase("UA020")){//4. ���Է¼�������
				return_value = false;
			}else if(auth.equalsIgnoreCase("UA021")){//5. ����ȸ�� ����
				return_value = false;
			}else if(auth.equalsIgnoreCase("UA030")){//6. �����Է��򰡸�ΰ���
				return_value = true;
			}else if(auth.equalsIgnoreCase("UA031")){//7. ������ȸ�� �򰡰����ȸ�� ����
				return_value = false;
			}			
		}else{ //�ɼ��� �� �ɼ��� ���
			if(auth.equalsIgnoreCase("UA001")){//1. ������ 
				return_value = true;
			}else if(auth.equalsIgnoreCase("UA010")){//2. �����Է¼������� 
				return_value = false;
			}else if(auth.equalsIgnoreCase("UA011")){//3. ������ȸ�� ����
				return_value = false;
			}else if(auth.equalsIgnoreCase("UA020")){//4. ���Է¼�������
				return_value = true;
			}else if(auth.equalsIgnoreCase("UA021")){//5. ����ȸ�� ����
				return_value = true;
			}else if(auth.equalsIgnoreCase("UA030")){//6. �����Է��򰡸�ΰ���
				return_value = true;
			}else if(auth.equalsIgnoreCase("UA031")){//7. ������ȸ�� �򰡰����ȸ�� ����
				return_value = false;
			}
		}
		
		return return_value;		
		/*if(auth.equalsIgnoreCase("UA001")){ // ������ �ϰ��
			return_value = true;			
		}else if(auth.equalsIgnoreCase("UA010")){ // ���� �Է� ���� ���� �ϰ��
			if(func.equalsIgnoreCase("IU")){
				return_value = true;
			}else if(func.equalsIgnoreCase("AU")){
				return_value = false;
			}						
		}else if(auth.equalsIgnoreCase("UA011")){ // ���� ��ȸ�� �����ϰ��
			if(func.equalsIgnoreCase("IU")){
				return_value = true;
			}else if(func.equalsIgnoreCase("AU")){
				return_value = false;
			}			
		}else if(auth.equalsIgnoreCase("UA020")){ // �� �Է� ���� ���� �ϰ��
			if(func.equalsIgnoreCase("IU")){
				return_value = false;
			}else if(func.equalsIgnoreCase("AU")){
				return_value = true;
			}			
		}else if(auth.equalsIgnoreCase("UA021")){ // �� ��ȸ�� ���� �ϰ��
			if(func.equalsIgnoreCase("IU")){
				return_value = false;
			}else if(func.equalsIgnoreCase("AU")){
				return_value = true;
			}			
		}else if(auth.equalsIgnoreCase("UA030")){ // ���� �Է� �� �� ��� ���� �ϰ��
			return_value = true;			
		}else if(auth.equalsIgnoreCase("UA031")){ // ���� �� �� ��ȸ�� ���� �ϰ��
			if(func.equalsIgnoreCase("IU")){
				return_value = false;
			}else if(func.equalsIgnoreCase("AU")){
				return_value = false;
			}			
		}*/
		

	}
}
