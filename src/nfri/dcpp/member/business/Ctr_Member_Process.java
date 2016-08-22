package nfri.dcpp.member.business;

import java.util.Vector;

import nfri.dcpp.com.util.ComVar;
import nfri.dcpp.member.db.DAO_USER_INFO;
import nfri.dcpp.member.model.User_Info;

/**
 *
 * @Project : dcpp_web
 * @Title : Ctr_Member_Process.java
 * @Description : User와 관련된 작업을 처리하는 Business 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 01. 07
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class Ctr_Member_Process { 
	
	//생성자
	public Ctr_Member_Process(){
			
	}
	
	/**
	 * @MethodName : selectUserCheck
	 * @Desc : 사용자 정보를 확인하는 메소드. DB와 연결하는 비즈니스 로직
	 * @param id
	 * @param pwd
	 * @return checkValue
	 */
	public String selectUserCheck(String id, String pwd, String auth){
		//String checkValue = "";
		DAO_USER_INFO userInfo = new DAO_USER_INFO();		
		
		//1. 존재하는 사용자 ID 체크
		if(userInfo.Select_User_ID(id) == false){ //유효하지 않은 사용자
			
			return ComVar.USER_ID_INVAILD; 
			
		}else{ //유효한 사용자
			
			//2. 존재할 경우 사용자 Password 체크
			if(userInfo.Select_User_Passwd(id, pwd) == false){ //유효하지 않은 패스워드
				
				return ComVar.USER_PWD_INVAILD; 
				
			}else{ //유효한 패스워드
				
				//3. 사용자가 해당 기능에 대한 권한이 있는지 여부를 체크
				User_Info info = userInfo.Select_User_Info(id, pwd);
				String grade = info.getPL_AI_GRADE();
				if(grade.equalsIgnoreCase("UA001") || grade.equalsIgnoreCase("UA030")){
					//return ComVar.USER_AUTH_CERTIFIED_ALL; //모든 권한	
					return ComVar.USER_AUTH_VAILD; //모든 권한
				}
				
				if(checkUserAuth(auth, grade) == false){
					return ComVar.USER_AUTH_INVAILD; //유효하지 않은 사용자 권한	
				}else{
					return ComVar.USER_AUTH_VAILD; //유효한 사용자 권한
				}
			}
		}
	}
	
	/**
	 * @MethodName : checkUserAuth
	 * @Desc : 사용자 권한 체크 메소드
	 * @param func
	 * @param auth
	 * @return
	 */
	public boolean checkUserAuth(String func, String auth){
		boolean return_value = false;
		
		if(func.equalsIgnoreCase("IAU") || func.equalsIgnoreCase("AIU")){
			return_value = true;
		}else if(func.equalsIgnoreCase("IU")){ //옵션이 입력 옵션일 경우
			if(auth.equalsIgnoreCase("UA001")){//1. 관리자 
				return_value = true;
			}else if(auth.equalsIgnoreCase("UA010")){//2. 물성입력수정가능 
				return_value = true;
			}else if(auth.equalsIgnoreCase("UA011")){//3. 물성조회만 가능
				return_value = true;
			}else if(auth.equalsIgnoreCase("UA020")){//4. 평가입력수정가능
				return_value = false;
			}else if(auth.equalsIgnoreCase("UA021")){//5. 평가조회만 가능
				return_value = false;
			}else if(auth.equalsIgnoreCase("UA030")){//6. 물성입력평가모두가능
				return_value = true;
			}else if(auth.equalsIgnoreCase("UA031")){//7. 물성조회및 평가결과조회만 가능
				return_value = false;
			}			
		}else{ //옵션이 평가 옵션일 경우
			if(auth.equalsIgnoreCase("UA001")){//1. 관리자 
				return_value = true;
			}else if(auth.equalsIgnoreCase("UA010")){//2. 물성입력수정가능 
				return_value = false;
			}else if(auth.equalsIgnoreCase("UA011")){//3. 물성조회만 가능
				return_value = false;
			}else if(auth.equalsIgnoreCase("UA020")){//4. 평가입력수정가능
				return_value = true;
			}else if(auth.equalsIgnoreCase("UA021")){//5. 평가조회만 가능
				return_value = true;
			}else if(auth.equalsIgnoreCase("UA030")){//6. 물성입력평가모두가능
				return_value = true;
			}else if(auth.equalsIgnoreCase("UA031")){//7. 물성조회및 평가결과조회만 가능
				return_value = false;
			}
		}
		
		return return_value;		
		/*if(auth.equalsIgnoreCase("UA001")){ // 관리자 일경우
			return_value = true;			
		}else if(auth.equalsIgnoreCase("UA010")){ // 물성 입력 수정 가능 일경우
			if(func.equalsIgnoreCase("IU")){
				return_value = true;
			}else if(func.equalsIgnoreCase("AU")){
				return_value = false;
			}						
		}else if(auth.equalsIgnoreCase("UA011")){ // 물성 조회만 가능일경우
			if(func.equalsIgnoreCase("IU")){
				return_value = true;
			}else if(func.equalsIgnoreCase("AU")){
				return_value = false;
			}			
		}else if(auth.equalsIgnoreCase("UA020")){ // 평가 입력 수정 가능 일경우
			if(func.equalsIgnoreCase("IU")){
				return_value = false;
			}else if(func.equalsIgnoreCase("AU")){
				return_value = true;
			}			
		}else if(auth.equalsIgnoreCase("UA021")){ // 평가 조회만 가능 일경우
			if(func.equalsIgnoreCase("IU")){
				return_value = false;
			}else if(func.equalsIgnoreCase("AU")){
				return_value = true;
			}			
		}else if(auth.equalsIgnoreCase("UA030")){ // 물성 입력 및 평가 모두 가능 일경우
			return_value = true;			
		}else if(auth.equalsIgnoreCase("UA031")){ // 물성 및 평가 조회만 가능 일경우
			if(func.equalsIgnoreCase("IU")){
				return_value = false;
			}else if(func.equalsIgnoreCase("AU")){
				return_value = false;
			}			
		}*/
		

	}
}
