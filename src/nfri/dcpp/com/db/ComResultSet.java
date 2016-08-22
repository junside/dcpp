/**
 * 
 */
package nfri.dcpp.com.db;

import java.util.Hashtable;
import java.util.Vector;

import nfri.dcpp.com.util.ComVar;

/**
 *
 * @Project : dcpp_web
 * @Title : ComResultSet.java
 * @Description : Query 결과를 받는 ResultSet과 유사한 공통의 Query ResultSet 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 09
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class ComResultSet {
	private Vector<?> rowData;
	private int rowPos = -1;
	private int colCnt = 0;
	
	public void setRowData(Vector<?> rowData){
		this.rowData = rowData;
	}
	
	/*public int getInt(String columnName){
		String columnValue = this.getString(columnName);
		try{
			int val = Integer.parseInt(columnValue);
			return val;
		}catch(Exception e){
			return -1;
		}
	}*/
	
	/*public int getInt(int columnName){
		String columnValue = this.getString(columnName);
		try{
			int val = Integer.parseInt(columnValue);
			return val;
		}catch(Exception e){
			return -1;
		}
	}*/
	
	public int getInt(int columnName){
		String columnValue = this.getString(columnName);
		try{
			int val = Integer.parseInt(columnValue);
			return val;
		}catch(Exception e){
			return -1;
		}
	}
	
	public double getDouble(int columnName){
		String columnValue = this.getString(columnName);
		try{
			double val = Double.valueOf(columnValue);
			return val;
		}catch(Exception e){
			return -1;
		}
	}
	
	/*public String getString(String columnName){
		Hashtable columnData = (Hashtable)this.rowData.elementAt(this.rowPos);		
		if(columnData.containsKey(columnName)){
			String columnValue = (String)columnData.get(columnName);
			return columnValue;
		}else{
			System.out.println("컬럼명이 존재하지 않습니다.");
			return ComVar.STRING_EMPTY;
		}
	}*/
	
	public String getString(int columnName){
		Hashtable<?, ?> columnData = (Hashtable<?, ?>)this.rowData.elementAt(this.rowPos);		
		if(columnData.containsKey(columnName)){
			String columnValue = (String)columnData.get(columnName);
			return columnValue;
		}else{
			System.out.println("컬럼명이 존재하지 않습니다.");
			return ComVar.STRING_EMPTY;
		}
	}
	
	public boolean next(){
		if(this.rowPos == this.rowData.size()-1){
			return false;
		}else{
			this.rowPos++;
			return true;
		}
	}
	
	public int getRowCount(){
		return this.rowData.size();
	}

	public void setColCount(int columnCount) {
		// TODO Auto-generated method stub
		this.colCnt = columnCount;
	}
	
	public int getColCount(){
		return this.colCnt;
	}
}
