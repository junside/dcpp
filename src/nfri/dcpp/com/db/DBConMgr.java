package nfri.dcpp.com.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @Project : dcpp_web
 * @Title : DBConMgr.java
 * @Description : DB Connection 객체를 얻어오는 클래스(DB Connection Mgr 대체)
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 02. 27
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class DBConMgr {
	private static DBConMgr instance = null;
	
	public DBConMgr() {
    }

    public static DBConMgr getInstance() {
        if (instance == null) {
            synchronized (DBConMgr.class) {
                if (instance == null) {
                    instance = new DBConMgr();
                }
            }
        }

        return instance;
    }
    
    public synchronized Connection getConnection(){
    	//Context ctx = null;
		DataSource dataSource = null; 
		Connection con = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			//ctx = new InitialContext(); 
			
			dataSource = (DataSource) envCtx.lookup("jdbc/PlasmaDB");
			con = dataSource.getConnection();
			
		} catch (NamingException e) {
			
			System.out.println("Naming : " + e.toString());
			
		} catch (SQLException e) {
			
			System.out.println("Connection : " + e.toString());
			
		}
		
		return con;
    }

}
