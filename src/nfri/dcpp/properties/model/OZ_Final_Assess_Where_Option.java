package nfri.dcpp.properties.model;

import nfri.dcpp.com.util.ComVar;



/**
 * @Project: dcpp
 * @Title  : OZ_Final_Assess_Where_Option.java
 * @Description : 오즈 그래프를 위한 SQL 옵션을 처리하는 클래스 
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2011. 7. 6.
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class OZ_Final_Assess_Where_Option {
	//Parameter : 충돌방식
	String param_ic_option = ComVar.STRING_NULL_B;
	//Parameter : 주프로세스
	String param_mp_option = ComVar.STRING_NULL_B;
	//Parameter : 부프로세스
	String param_sp_option = ComVar.STRING_NULL_B;
	//Parameter : X단위
	String param_xu_option = ComVar.STRING_NULL_B;
	//Parameter : Y단위
	String param_yu_option = ComVar.STRING_NULL_B;

	//Parameter : 입사입자 (Projectile)
	String param_pj_name = ComVar.STRING_NULL_B;
	//Parameter : 입사입자 ID 값 (Projectile)
	String param_pj_name_id = ComVar.STRING_NULL_B;

	//Parameter : 표적입자 (Target)
	String param_tg_name = ComVar.STRING_NULL_B;
	//Parameter : 표적입자 ID 값(Target)
	String param_tg_name_id = ComVar.STRING_NULL_B;

	//Parameter : 이온화 (Target)
	String param_tg_ion = ComVar.STRING_NULL_B;
	//Parameter : 전자배치 (Target)
	String param_tg_elec = ComVar.STRING_NULL_B;
	//Parameter : 미세구조 (Target)
	String param_tg_fine = ComVar.STRING_NULL_B;

	////Parameter : 생성입자입력여부 (Product)
	boolean param_pd_check = ComVar.BOOL_FALSE;

	//Parameter : 생성입자 (Product)
	String param_pd_name = ComVar.STRING_NULL_B;
	//Parameter : 생성입자 ID(Product)
	String param_pd_name_id = ComVar.STRING_NULL_B;

	//Parameter : 이온화 (Product)
	String param_pd_ion = ComVar.STRING_NULL_B;
	//Parameter : 전자배치 (Product)
	String param_pd_elec = ComVar.STRING_NULL_B;
	//Parameter : 미세구조 (Product)
	String param_pd_fine = ComVar.STRING_NULL_B;

	//Parameter : X MAX
	String param_x_max_v = ComVar.STRING_NULL_B;
	//Parameter : X MIN
	String param_x_min_v = ComVar.STRING_NULL_B;
	//Parameter : Y MAX
	String param_y_max_v = ComVar.STRING_NULL_B;
	//Parameter : Y MIN
	String param_y_min_v = ComVar.STRING_NULL_B;
	/**
	 * @return the param_ic_option
	 */
	public String getParam_ic_option() {
		return param_ic_option;
	}
	/**
	 * @return the param_mp_option
	 */
	public String getParam_mp_option() {
		return param_mp_option;
	}
	/**
	 * @return the param_sp_option
	 */
	public String getParam_sp_option() {
		return param_sp_option;
	}
	/**
	 * @return the param_xu_option
	 */
	public String getParam_xu_option() {
		return param_xu_option;
	}
	/**
	 * @return the param_yu_option
	 */
	public String getParam_yu_option() {
		return param_yu_option;
	}
	/**
	 * @return the param_pj_name
	 */
	public String getParam_pj_name() {
		return param_pj_name;
	}
	/**
	 * @return the param_pj_name_id
	 */
	public String getParam_pj_name_id() {
		return param_pj_name_id;
	}
	/**
	 * @return the param_tg_name
	 */
	public String getParam_tg_name() {
		return param_tg_name;
	}
	/**
	 * @return the param_tg_name_id
	 */
	public String getParam_tg_name_id() {
		return param_tg_name_id;
	}
	/**
	 * @return the param_tg_ion
	 */
	public String getParam_tg_ion() {
		return param_tg_ion;
	}
	/**
	 * @return the param_tg_elec
	 */
	public String getParam_tg_elec() {
		return param_tg_elec;
	}
	/**
	 * @return the param_tg_fine
	 */
	public String getParam_tg_fine() {
		return param_tg_fine;
	}
	/**
	 * @return the param_pd_check
	 */
	public boolean isParam_pd_check() {
		return param_pd_check;
	}
	/**
	 * @return the param_pd_name
	 */
	public String getParam_pd_name() {
		return param_pd_name;
	}
	/**
	 * @return the param_pd_name_id
	 */
	public String getParam_pd_name_id() {
		return param_pd_name_id;
	}
	/**
	 * @return the param_pd_ion
	 */
	public String getParam_pd_ion() {
		return param_pd_ion;
	}
	/**
	 * @return the param_pd_elec
	 */
	public String getParam_pd_elec() {
		return param_pd_elec;
	}
	/**
	 * @return the param_pd_fine
	 */
	public String getParam_pd_fine() {
		return param_pd_fine;
	}
	/**
	 * @return the param_x_max_v
	 */
	public String getParam_x_max_v() {
		return param_x_max_v;
	}
	/**
	 * @return the param_x_min_v
	 */
	public String getParam_x_min_v() {
		return param_x_min_v;
	}
	/**
	 * @return the param_y_max_v
	 */
	public String getParam_y_max_v() {
		return param_y_max_v;
	}
	/**
	 * @return the param_y_min_v
	 */
	public String getParam_y_min_v() {
		return param_y_min_v;
	}
	/**
	 * @param paramIcOption the param_ic_option to set
	 */
	public void setParam_ic_option(String paramIcOption) {
		param_ic_option = paramIcOption;
	}
	/**
	 * @param paramMpOption the param_mp_option to set
	 */
	public void setParam_mp_option(String paramMpOption) {
		param_mp_option = paramMpOption;
	}
	/**
	 * @param paramSpOption the param_sp_option to set
	 */
	public void setParam_sp_option(String paramSpOption) {
		param_sp_option = paramSpOption;
	}
	/**
	 * @param paramXuOption the param_xu_option to set
	 */
	public void setParam_xu_option(String paramXuOption) {
		param_xu_option = paramXuOption;
	}
	/**
	 * @param paramYuOption the param_yu_option to set
	 */
	public void setParam_yu_option(String paramYuOption) {
		param_yu_option = paramYuOption;
	}
	/**
	 * @param paramPjName the param_pj_name to set
	 */
	public void setParam_pj_name(String paramPjName) {
		param_pj_name = paramPjName;
	}
	/**
	 * @param paramPjNameId the param_pj_name_id to set
	 */
	public void setParam_pj_name_id(String paramPjNameId) {
		param_pj_name_id = paramPjNameId;
	}
	/**
	 * @param paramTgName the param_tg_name to set
	 */
	public void setParam_tg_name(String paramTgName) {
		param_tg_name = paramTgName;
	}
	/**
	 * @param paramTgNameId the param_tg_name_id to set
	 */
	public void setParam_tg_name_id(String paramTgNameId) {
		param_tg_name_id = paramTgNameId;
	}
	/**
	 * @param paramTgIon the param_tg_ion to set
	 */
	public void setParam_tg_ion(String paramTgIon) {
		param_tg_ion = paramTgIon;
	}
	/**
	 * @param paramTgElec the param_tg_elec to set
	 */
	public void setParam_tg_elec(String paramTgElec) {
		param_tg_elec = paramTgElec;
	}
	/**
	 * @param paramTgFine the param_tg_fine to set
	 */
	public void setParam_tg_fine(String paramTgFine) {
		param_tg_fine = paramTgFine;
	}
	/**
	 * @param paramPdCheck the param_pd_check to set
	 */
	public void setParam_pd_check(boolean paramPdCheck) {
		param_pd_check = paramPdCheck;
	}
	/**
	 * @param paramPdName the param_pd_name to set
	 */
	public void setParam_pd_name(String paramPdName) {
		param_pd_name = paramPdName;
	}
	/**
	 * @param paramPdNameId the param_pd_name_id to set
	 */
	public void setParam_pd_name_id(String paramPdNameId) {
		param_pd_name_id = paramPdNameId;
	}
	/**
	 * @param paramPdIon the param_pd_ion to set
	 */
	public void setParam_pd_ion(String paramPdIon) {
		param_pd_ion = paramPdIon;
	}
	/**
	 * @param paramPdElec the param_pd_elec to set
	 */
	public void setParam_pd_elec(String paramPdElec) {
		param_pd_elec = paramPdElec;
	}
	/**
	 * @param paramPdFine the param_pd_fine to set
	 */
	public void setParam_pd_fine(String paramPdFine) {
		param_pd_fine = paramPdFine;
	}
	/**
	 * @param paramXMaxV the param_x_max_v to set
	 */
	public void setParam_x_max_v(String paramXMaxV) {
		param_x_max_v = paramXMaxV;
	}
	/**
	 * @param paramXMinV the param_x_min_v to set
	 */
	public void setParam_x_min_v(String paramXMinV) {
		param_x_min_v = paramXMinV;
	}
	/**
	 * @param paramYMaxV the param_y_max_v to set
	 */
	public void setParam_y_max_v(String paramYMaxV) {
		param_y_max_v = paramYMaxV;
	}
	/**
	 * @param paramYMinV the param_y_min_v to set
	 */
	public void setParam_y_min_v(String paramYMinV) {
		param_y_min_v = paramYMinV;
	}	
}