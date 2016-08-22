/**
 * 
 */
package nfri.dcpp.properties.business;

import java.util.Vector;
import nfri.dcpp.com.util.ComUtil;
import nfri.dcpp.member.db.DAO_USER_INFO;
import nfri.dcpp.properties.db.DAO_BASIC_GRAPH_INFO;
import nfri.dcpp.properties.db.DAO_INF_GRD_PROPERTY_INFO;
import nfri.dcpp.properties.model.Graph_Data_Info;
import nfri.dcpp.properties.model.Inf_Grd_Graph_Basic_Info;
import nfri.dcpp.properties.model.Inf_Grd_Graph_Data_Info;
import nfri.dcpp.properties.model.Inf_Grd_Properties_Basic_Info;
import nfri.dcpp.properties.model.Properties_Assess_Search_List;

/**
 * @Project: dcpp
 * @Title  : Ctr_Inf_Grd_Property_Info_Process.java
 * @Description : 등급유력 물성정보와 관련된 작업을 처리하는 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2010. 09. 14
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class Ctr_Inf_Grd_Property_Info_Process {
	public Ctr_Inf_Grd_Property_Info_Process(){
			
		}

	/**
	 * @MethodName  : selectViewInfGrdPropertyInfo
	 * @Date   : 2010. 09. 14 
	 * @MethodDescription : 등급유력 물성정보의 기본 정보를 가져오는 메소드
	 * @param v_pr_no
	 * @return
	 * @History  : - 
	 */
	public Inf_Grd_Properties_Basic_Info selectViewInfGrdPropertyInfo(String v_pr_no){

		DAO_INF_GRD_PROPERTY_INFO dao = new DAO_INF_GRD_PROPERTY_INFO();
		DAO_USER_INFO user_dao = new DAO_USER_INFO();
		Ctr_Option_Process ctr_option = new Ctr_Option_Process();
		
		Inf_Grd_Properties_Basic_Info info = dao.selectInfGrdPropertyBasicInfo(v_pr_no);
		
		//String list = info.getPL_IGBI_DATA_NUM_LIST();
		
		//주프로세스
		info.setPL_IGBI_MAIN_PROC(ctr_option.getExpName(info.getPL_IGBI_MAIN_PROC()));
		//부프로세스
		info.setPL_IGBI_SUB_PROC(ctr_option.getExpName(info.getPL_IGBI_SUB_PROC()));
		//사용자명 전환
		//System.out.println("user_id : " + info.getPL_UI_ID());
		//System.out.println("user_name : " + user_dao.SelectUserName(info.getPL_UI_ID()));
		//충돌
		info.setPL_IGBI_IMP_CLASS(ctr_option.getExpName(info.getPL_IGBI_IMP_CLASS()));
		
		info.setPL_UI_ID(user_dao.SelectUserName(info.getPL_UI_ID()));
	
		return info;

	}
	
	/**
	 * @MethodName  : makeReferenceLink
	 * @Date   : 2010. 09. 17 
	 * @MethodDescription : 물성정보 리스트를 가지고 <A> 태그 생성하는 메소드
	 * @param pt_list
	 * @return
	 * @History  : - 
	 */
	public String makeReferenceLink(String pt_list){
		String return_value = "";

		String a_front = "<a href=\"javascript:void(0);\"  onClick=\"viewPropertyInfo('";
		
		return_value = ComUtil.convertATag(pt_list, a_front);
		
		//System.out.println("return_value : " + return_value); 
	
		//javascript:void(0);"  onClick="viewPropertyInfo('<%=v_pr_no%>'); return false;"><%=dt_list%></a>
		
		/*
		 * <a href="javascript:void(0);"  onClick="viewPropertyInfo('<%=v_pr_no%>'); return false;"><%=dt_list%></a>
		 */
		
		return return_value;		
	}
	
	/**
	 * @MethodName  : moveFinalGraphData
	 * @Date   : 2010. 09. 16 
	 * @MethodDescription : 최종 평가시에 그래프 데이터를 등급유력 그래프 데이터 테이블로 이동
	 * @param v_pr_no
	 * @param user_id
	 * @param pd_info
	 * @return
	 * @History  : - 
	 */
	/*public boolean moveFinalGraphData(String v_pr_no, String user_id, Vector pd_info){
		boolean r_value = false;	

		DAO_BASIC_GRAPH_INFO graph = new DAO_BASIC_GRAPH_INFO();
		DAO_INF_GRD_PROPERTY_INFO dao = new DAO_INF_GRD_PROPERTY_INFO();
		for(int i = 0; i < pd_info.size(); i++){
		
			Properties_Assess_Search_List prot = (Properties_Assess_Search_List) pd_info.elementAt(i);
		  	String seach_pr_no = prot.getPL_BI_DATA_NUM();	 //물성번호 	
		  	
		  	Vector v_option = new Vector();
			v_option.addElement(seach_pr_no);
			
			Vector result_v = graph.selectBasicGraphData(v_option);
		  	
			for(int j = 0; j < result_v.size(); j++){
		  		Graph_Data_Info g_data = (Graph_Data_Info)result_v.elementAt(j);	
				Inf_Grd_Graph_Data_Info data = new Inf_Grd_Graph_Data_Info();

				data.setPL_IGBI_DATA_NUM(v_pr_no);//등급유력번호
				data.setPL_IGGD_SEQ_NUM(g_data.getPL_BGD_SEQ_NUM());
				data.setPL_IGGD_X_AX_VAL(g_data.getPL_BGD_X_AX_VAL());
				data.setPL_IGGD_Y_AX_VAL(g_data.getPL_BGD_Y_AX_VAL());
				data.setPL_IGGD_X_ERR(g_data.getPL_BGD_X_ERR());
				data.setPL_IGGD_Y_ERR_MAX(g_data.getPL_BGD_Y_ERR_MAX());
				data.setPL_IGGD_Y_ERR_MIN(g_data.getPL_BGD_Y_ERR_MIN());
				data.setPL_IGGD_RATIO(g_data.getPL_BGD_RATIO());
				data.setPL_IGGD_PRESS(g_data.getPL_BGD_PRESS());
				data.setPL_IGGD_BACKUP_DATA(g_data.getPL_BGD_BACKUP_DATA());
		  		
				r_value = dao.insertInfGraphData(data);	  		
		  	}  	
		  	
		}	
		return r_value;
	}*/
}
