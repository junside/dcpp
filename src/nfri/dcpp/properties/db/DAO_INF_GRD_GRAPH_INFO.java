/**
 * 
 */
package nfri.dcpp.properties.db;

import java.util.Vector;

import nfri.dcpp.com.db.ComResultSet;
import nfri.dcpp.com.db.ComSQLExecution;
import nfri.dcpp.properties.model.Graph_Basic_Info;
import nfri.dcpp.properties.model.Graph_Data_Info;
import nfri.dcpp.properties.model.Inf_Grd_Graph_Basic_Info;
import nfri.dcpp.properties.model.Inf_Grd_Graph_Data_Info;
import nfri.dcpp.properties.model.Properties_Assess_Search_List;

/**
 * @Project: dcpp
 * @Title  : DAO_INF_GRD_GRAPH_INFO.java
 * @Description : 등급유력 물성정보의 그래프와 관련된 DB 작업을 처리하는 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2010. 09. 01
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class DAO_INF_GRD_GRAPH_INFO {
	//DB 실행 클래스 선언
	ComSQLExecution comSQL;
	
	public DAO_INF_GRD_GRAPH_INFO() {
		comSQL = new ComSQLExecution();
	}
	
	/**
	 * @MethodName  : insertInfGrdGraphInfo
	 * @Date   : 2010. 09. 01 
	 * @MethodDescription : 등급유력 물성정보의 그래프 기본 정보를 입력하는 메소드
	 * @param info
	 * @return
	 * @History  : - 
	 */
	public boolean insertInfGrdGraphInfo(Inf_Grd_Graph_Basic_Info info){
		boolean r_value = false;
		String insertGraphInfoQuery = "INSERT INTO PLASMA.PL_INF_GRD_GRAPH_INFO (" +
				" PL_IGBI_DATA_NUM, PL_IGGI_X_AX_UNIT, PL_IGGI_X_AX_CAL," +
				" PL_IGGI_Y_AX_UNIT, PL_IGGI_Y_AX_CAL, PL_IGGI_Y_AX_COMM)" +
				" VALUES ( ? , ? , ? , ? , ? , ? )";
		
		Vector<String> sqlOptionGraph = new Vector<String>();
		sqlOptionGraph.addElement(info.getPL_IGBI_DATA_NUM());
		sqlOptionGraph.addElement(info.getPL_IGGI_X_AX_UNIT());
		sqlOptionGraph.addElement(info.getPL_IGGI_X_AX_CAL());
		sqlOptionGraph.addElement(info.getPL_IGGI_Y_AX_UNIT());
		sqlOptionGraph.addElement(info.getPL_IGGI_Y_AX_CAL());
		sqlOptionGraph.addElement(info.getPL_IGGI_Y_AX_COMM());
		
		r_value = comSQL.executeTransact(insertGraphInfoQuery, sqlOptionGraph);
		
		return r_value;
	}	
	
	/**
	 * @MethodName : selectBasicGraphInfo
	 * @Desc : DB에 기본 그래프 정보를 가져오는 메소드
	 * @param sqlOption
	 * @return
	 */
	public Inf_Grd_Graph_Basic_Info selectInfGrdGraphInfo(String pr_no){
		String sqlQuery = "SELECT PL_IGBI_DATA_NUM, PL_IGGI_X_AX_UNIT, PL_IGGI_X_AX_CAL," +
				" PL_IGGI_Y_AX_UNIT, PL_IGGI_Y_AX_CAL, PL_IGGI_Y_AX_COMM" +
				" FROM PLASMA.PL_INF_GRD_GRAPH_INFO WHERE PL_IGBI_DATA_NUM = ? ";
		//그래프 정보 Query 파라미터 벡터		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(pr_no);
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery, ve_1);

		Inf_Grd_Graph_Basic_Info info = new Inf_Grd_Graph_Basic_Info();
		//Ctr_Option_Process ctr_option = new Ctr_Option_Process();
		while(rs.next()){		
		
			info.setPL_IGBI_DATA_NUM(rs.getString(1));
			info.setPL_IGGI_X_AX_UNIT(rs.getString(2));
			info.setPL_IGGI_X_AX_CAL(rs.getString(3));
			info.setPL_IGGI_Y_AX_UNIT(rs.getString(4));
			info.setPL_IGGI_Y_AX_CAL(rs.getString(5));
			info.setPL_IGGI_Y_AX_COMM(rs.getString(6));

		}
		return info;
	}
	
	/**
	 * @MethodName  : selectInfGrdGraphData
	 * @Date   : 2010. 09. 15 
	 * @MethodDescription : DB에서 등급유력 물성정보에 대한 데이터 정보를 가져오는 메소드
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public Vector<Inf_Grd_Graph_Data_Info> selectInfGrdGraphData(String pr_no){
		String sqlQuery = "SELECT PL_IGBI_DATA_NUM, PL_IGGD_SEQ_NUM, NVL(PL_IGGD_X_AX_VAL, '0'), NVL(PL_IGGD_Y_AX_VAL, '0')," +
		" NVL(PL_IGGD_X_ERR, '0'), NVL(PL_IGGD_Y_ERR_MAX, '0'), NVL(PL_IGGD_Y_ERR_MIN, '0')," +
		" NVL(PL_IGGD_RATIO,'-'), NVL(PL_IGGD_PRESS,'-'), NVL(PL_IGGD_BACKUP_DATA,'-')" +
		" FROM PLASMA.PL_INF_GRD_GRAPH_DATA WHERE PL_IGBI_DATA_NUM = ? ORDER BY PL_IGGD_SEQ_NUM";
		
		Vector<Inf_Grd_Graph_Data_Info> return_values = new Vector<Inf_Grd_Graph_Data_Info>();
		
		Vector<String> ve_1 = new Vector<String>();
		ve_1.addElement(pr_no);
		
		ComResultSet rs = comSQL.executeSelect(sqlQuery, ve_1);
		
		while(rs.next()){		
			Inf_Grd_Graph_Data_Info info = new Inf_Grd_Graph_Data_Info();
			info.setPL_IGBI_DATA_NUM(rs.getString(1));
			info.setPL_IGGD_SEQ_NUM(rs.getString(2));
			info.setPL_IGGD_X_AX_VAL(rs.getString(3));
			info.setPL_IGGD_Y_AX_VAL(rs.getString(4));
			info.setPL_IGGD_X_ERR(rs.getString(5));
			info.setPL_IGGD_Y_ERR_MAX(rs.getString(6));
			info.setPL_IGGD_Y_ERR_MIN(rs.getString(7));
			info.setPL_IGGD_RATIO(rs.getString(8));
			info.setPL_IGGD_PRESS(rs.getString(9));
			info.setPL_IGGD_BACKUP_DATA(rs.getString(10));
			return_values.addElement(info);
		}
		return return_values;
		
		
	}
}
