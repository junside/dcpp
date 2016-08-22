package nfri.dcpp.properties.business;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import nfri.dcpp.com.util.ComUtil;
import nfri.dcpp.com.util.ComVar;
import nfri.dcpp.properties.db.DAO_BASIC_GRAPH_INFO;
import nfri.dcpp.properties.db.DAO_INF_GRD_GRAPH_INFO;
import nfri.dcpp.properties.model.Common_Data;
import nfri.dcpp.properties.model.Graph_Basic_Info;
import nfri.dcpp.properties.model.Graph_Data_Info;
import nfri.dcpp.properties.model.Inf_Grd_Graph_Basic_Info;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.oreilly.servlet.MultipartRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @Project : dcpp_web
 * @Title : Ctr_Graph_Info_Process.java
 * @Description : �׷��������Ϳ� ���õ� ó���� �ϴ� ����Ͻ� ���� Ŭ����
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 24
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @�����̷� : -
 *
 */
public class Ctr_Graph_Info_Process {
	public Ctr_Graph_Info_Process(){
		
	}
	
	/**
	 * @MethodName : insertGraphBasicInfo
	 * @Desc : �׷��� �⺻ ������ �����ϴ� �޼ҵ�
	 * @param request
	 * @return
	 */
	public boolean insertGraphBasicInfo(HttpServletRequest request){
		boolean value = false;
		DAO_BASIC_GRAPH_INFO info = new DAO_BASIC_GRAPH_INFO();
		
		//�������� ��ȣ
		String pr_no = request.getParameter("pr_no");
		//X����
		String x_unit = request.getParameter("xax_unit");
		//Xȯ��
		String x_cal = request.getParameter("xax_cal");
		//Y����
		String y_unit = request.getParameter("xay_unit");
		//Yȯ��
		String y_cal = request.getParameter("xay_cal");
		//Y����
		String y_com = ComUtil.convertKorean(ComUtil.isNullToDashString(request.getParameter("xay_com")));
		
		Graph_Basic_Info g_info = new Graph_Basic_Info();
		g_info.setPL_BI_DATA_NUM(pr_no);
		g_info.setPL_BGI_X_AX_UNIT(x_unit);
		g_info.setPL_BGI_X_AX_CAL(x_cal);
		g_info.setPL_BGI_Y_AX_UNIT(y_unit);
		g_info.setPL_BGI_Y_AX_CAL(y_cal);
		g_info.setPL_BGI_Y_AX_COMM(y_com);
		
		value = info.insertBasicGraphInfo(g_info);
		
		return value;
	}
	
	/**
	 * @MethodName : insertGraphExcelData
	 * @Desc : Excel ���� ���ε� �ϴ� �޼ҵ�
	 * @param multi
	 * @return
	 */
	public boolean insertTestGraphExcelData(MultipartRequest multi, String contextpath){
		boolean value = false;			
		
		try{						
			//������ ���� ������ ������ ���
			Enumeration<?> files = multi.getFileNames();
		
			String pr_no = "1";
			
			//���� ������ �ִ� ���� ���� ��ƾ ó��
			while(files.hasMoreElements()){		
				
				//input �±��� �Ӽ��� file �� �±��� name �Ӽ��� : �Ķ���� �̸�
				String name = (String)files.nextElement();
				
				//������ ���� ���ε��  ���� �̸�
				String filename = multi.getFilesystemName(name);
				
				String filepath = contextpath;
				
				//���۵� ������ ���� Ÿ��
				//String type = ComUtil.getExtension(filename);
				
				//������ ���� ����Ǵ� ��� ����
				//String saveFolder = "D:/DEV/dcpp_web/public_html/data_file";
				//String saveFolder = request.getSession().getServletContext().getRealPath("/") + "article_file/" ;
				String saveFolder = filepath + "data_file";
				String savePath = saveFolder+"/"+filename;
				
				
				//���۵� �Ķ���� ArrayList
				ArrayList<Vector<Comparable>> totalParam = new ArrayList<Vector<Comparable>>();					
				
				//���۵� ���� �Ӽ��� file �±��� name �Ӽ����� �̿��� ���� ��ü ����
				File file = multi.getFile(name);
				
				boolean flag = ComUtil.moveExcelFile(file, saveFolder, filename);
				
				if(flag == true){	//���� ���� �̵� �Ǹ�,
					Workbook wb = WorkbookFactory.create(new FileInputStream(savePath));
					//���� ������ ����
					//XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(savePath));
					//��ũ�� ����
					//HSSFWorkbook workbook = new HSSFWorkbook(fs);
					//��Ʈ�̸��� ��Ʈ ��ȣ ����
					Sheet sheet = wb.getSheetAt(0);
					// ��Ʈ�� ������ �� ��ȣ ����
					int rows = sheet.getLastRowNum()+1;		
					
					DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
					//�߰� �Էº��� ���� ������ȣ�� ��ȸ�Ͽ� �״��� ��ȣ�� �Է��ϰ� ��.
					//int data_no = dao_graph.selectBasicGraphDataNum(pr_no)-1;
					//������ ����
					boolean isNull = false;
					//for (Row row : wb.getSheetAt(0)) {
					for (int r = 1; r < rows; r++) {
						// ��Ʈ�� ���� ���� �ϳ��� ����
						Row row = sheet.getRow(r);
						if (row != null) {
							int cells = row.getLastCellNum();						
							Vector<Comparable> v_list = new Vector<Comparable>();
							v_list.addElement(pr_no);						
							//System.out.println(row);
							v_list.addElement(r);
							
							//for (Cell cell : row) {
							for (int c = 0; c < cells; c++) {
								// �࿡���� ���� �ϳ��� �����Ͽ� �� Ÿ�Կ� ���� ó��
								Cell cell = row.getCell(c);							
								if (cell != null) {								
									switch (cell.getCellType()) {
										case Cell.CELL_TYPE_FORMULA :
											v_list.addElement(cell.getCellFormula());
											break;
										case Cell.CELL_TYPE_NUMERIC :
											if(DateUtil.isCellDateFormatted(cell)){
												v_list.addElement(cell.getDateCellValue().toString());//date
											}else{
												v_list.addElement(Integer.toString((int)cell.getNumericCellValue()));//double
											}
											break;
										case Cell.CELL_TYPE_STRING :
											v_list.addElement(cell.getRichStringCellValue().getString()); //String
											break;
										case Cell.CELL_TYPE_BLANK :
											v_list.addElement(ComVar.STRING_EMPTY);
											break;
										case Cell.CELL_TYPE_BOOLEAN :
											v_list.addElement(cell.getBooleanCellValue()); //boolean
											break;
										case Cell.CELL_TYPE_ERROR :
											v_list.addElement(cell.getErrorCellValue());// byte
											break;
										default :
											 isNull = true;
									}								
								}
							}
							totalParam.add(v_list);
						}
					}
					
					boolean flag2 = ComUtil.deleteExcelFile(file, saveFolder, filename);					
					
					value = dao_graph.insertTestBasicGraphExcelData(totalParam);
				}
			}
				
		}catch(IOException ioe){
			System.out.println(ioe);
		}catch(Exception ex){
			System.out.println(ex);
		}
		
		return value;
	}
	
	/**
	 * @MethodName : insertGraphExcelData
	 * @Desc : Excel ���� ���ε� �ϴ� �޼ҵ�
	 * @param multi
	 * @return
	 */
	public boolean insertGraphExcelData(MultipartRequest multi, String contextpath){
		boolean value = false;			
		
		try{						
			//������ ���� ������ ������ ���
			Enumeration<?> files = multi.getFileNames();
		
			String pr_no = multi.getParameter("pr_no");
			
			//���� ������ �ִ� ���� ���� ��ƾ ó��
			while(files.hasMoreElements()){		
				
				//input �±��� �Ӽ��� file �� �±��� name �Ӽ��� : �Ķ���� �̸�
				String name = (String)files.nextElement();
				
				//������ ���� ���ε��  ���� �̸�
				String filename = multi.getFilesystemName(name);
				
				String filepath = contextpath;
				
				//���۵� ������ ���� Ÿ��
				//String type = ComUtil.getExtension(filename);
				
				//������ ���� ����Ǵ� ��� ����
				//String saveFolder = "D:/DEV/dcpp_web/public_html/data_file";
				//String saveFolder = request.getSession().getServletContext().getRealPath("/") + "article_file/" ;
				String saveFolder = filepath + "data_file";
				String savePath = saveFolder+"/"+filename;
				
				
				//���۵� �Ķ���� ArrayList
				ArrayList<Vector<String>> totalParam = new ArrayList<Vector<String>>();					
				
				//���۵� ���� �Ӽ��� file �±��� name �Ӽ����� �̿��� ���� ��ü ����
				File file = multi.getFile(name);
				
				boolean flag = ComUtil.moveExcelFile(file, saveFolder, filename);
				
				if(flag == true){	//���� ���� �̵� �Ǹ�,	
					Workbook wb = WorkbookFactory.create(new FileInputStream(savePath));
					//���� ������ ����
					//HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(savePath));
					//��ũ�� ����
					//HSSFWorkbook workbook = new HSSFWorkbook(fs);
					//��Ʈ�̸��� ��Ʈ ��ȣ ����
					Sheet sheet = wb.getSheetAt(0);
					// ��Ʈ�� ������ �� ��ȣ ����
					int rows = sheet.getLastRowNum() + 1;		
					
					DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
					//�߰� �Էº��� ���� ������ȣ�� ��ȸ�Ͽ� �״��� ��ȣ�� �Է��ϰ� ��.
					int data_no = dao_graph.selectBasicGraphDataNum(pr_no)-1;					
					//������ ����
					boolean isNull = false;
					//for (Row row : wb.getSheetAt(0)) {
					for (int r = 1; r < rows; r++) {
						// ��Ʈ�� ���� ���� �ϳ��� ����
						Row row = sheet.getRow(r);
						if (row != null) {
							int cells = row.getLastCellNum();						
							Vector<String> v_list = new Vector<String>();
							v_list.addElement(pr_no);						
							//System.out.println(row);
							int add_seq_num = data_no + r;
							v_list.addElement(Integer.toString(add_seq_num));
							
							//for (Cell cell : row) {
							for (int c = 0; c < cells; c++) {
								// �࿡���� ���� �ϳ��� �����Ͽ� �� Ÿ�Կ� ���� ó��
								Cell cell = row.getCell(c);							
								if (cell != null) {								
									switch (cell.getCellType()) {
									case Cell.CELL_TYPE_FORMULA :
										//System.out.println("CELL_TYPE_FORMULA");
										v_list.addElement(cell.getCellFormula());
										break;
									case Cell.CELL_TYPE_NUMERIC :
										//System.out.println("CELL_TYPE_NUMERIC");
										if(DateUtil.isCellDateFormatted(cell)){
											v_list.addElement(cell.getDateCellValue().toString());//date
										}else{
											v_list.addElement(Double.toString(cell.getNumericCellValue()));//double
										}
										break;
									case Cell.CELL_TYPE_STRING :
										//System.out.println("CELL_TYPE_STRING");
										v_list.addElement(cell.getRichStringCellValue().getString()); //String
										break;
									case Cell.CELL_TYPE_BLANK :
										//System.out.println("CELL_TYPE_BLANK");
										v_list.addElement(ComVar.STRING_ZERO);
										break;
									case Cell.CELL_TYPE_BOOLEAN :
										//System.out.println("CELL_TYPE_BOOLEAN");
										v_list.addElement(Boolean.toString(cell.getBooleanCellValue())); //boolean
										break;
									case Cell.CELL_TYPE_ERROR :
										//System.out.println("CELL_TYPE_ERROR");
										v_list.addElement(Byte.toString(cell.getErrorCellValue()));// byte
										break;
									default :
										 isNull = true;
									}								
								}
							}
							totalParam.add(v_list);
						}
					}
					/*//���� ������ ����
					POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(savePath));
					//��ũ�� ����
					HSSFWorkbook workbook = new HSSFWorkbook(fs);
					//��Ʈ�̸��� ��Ʈ ��ȣ ����
					HSSFSheet sheet = workbook.getSheetAt(0);
					// ��Ʈ�� ���� ���� �ϳ��� ����
					int rows = sheet.getPhysicalNumberOfRows();		
					
					DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
					//�߰� �Էº��� ���� ������ȣ�� ��ȸ�Ͽ� �״��� ��ȣ�� �Է��ϰ� ��.
					int data_no = dao_graph.selectBasicGraphDataNum(pr_no)-1;
					//������ ����
					for (int r = 1; r < rows; r++) {
						// ��Ʈ�� ���� ���� �ϳ��� ����
						HSSFRow row = sheet.getRow(r);
						if (row != null) {
							int cells = row.getPhysicalNumberOfCells();						
							Vector v_list = new Vector();
							v_list.addElement(pr_no);						
							v_list.addElement(data_no + r);
							for (int c = 0; c < cells; c++) {
								// �࿡���� ���� �ϳ��� �����Ͽ� �� Ÿ�Կ� ���� ó��
								HSSFCell cell = row.getCell(c);							
								if (cell != null) {								
									switch (cell.getCellType()) {
										case HSSFCell.CELL_TYPE_FORMULA :
											v_list.addElement(cell.getCellFormula());
											break;
										case HSSFCell.CELL_TYPE_NUMERIC :
											v_list.addElement(cell.getNumericCellValue());//double
											break;
										case HSSFCell.CELL_TYPE_STRING :
											v_list.addElement(cell.getStringCellValue()); //String
											break;
										case HSSFCell.CELL_TYPE_BLANK :
											v_list.addElement(ComVar.STRING_EMPTY);
											break;
										case HSSFCell.CELL_TYPE_BOOLEAN :
											v_list.addElement(cell.getBooleanCellValue()); //boolean
											break;
										case HSSFCell.CELL_TYPE_ERROR :
											v_list.addElement(cell.getErrorCellValue());// byte
											break;
										default :
											v_list.addElement("0");// �⺻��"0"
											break;
									}								
								}
							}
							totalParam.add(v_list);
						}*/									
					
					value = dao_graph.insertBasicGraphExcelData(totalParam);
					
					boolean flag2 = ComUtil.deleteExcelFile(file, saveFolder, filename);
				}
			}
				
		}catch(IOException ioe){
			System.out.println(ioe);
		}catch(Exception ex){
			System.out.println(ex);
		}
		
		return value;
	}
	
	/**
	 * @MethodName  : inserFinalGraphExcelData
	 * @Date   : 2011. 6. 27. 
	 * @MethodDescription : ���� �򰡽� ������ �Ϸ�� ������(Excel)�� DB�� �����ϴ� �޼ҵ�
	 * @param multi
	 * @param contextpath
	 * @return
	 * @History  : - 
	 */
	public boolean inserFinalGraphExcelData(MultipartRequest multi, String contextpath){
		boolean value = false;			
		/*
		 * tmp_file
		 */
		try{						
			//������ ���� ������ ������ ���
			Enumeration<?> files = multi.getFileNames();
		
			String pr_no = multi.getParameter("v_pr_no");
			
			//���� ������ �ִ� ���� ���� ��ƾ ó��
			while(files.hasMoreElements()){		
				
				//input �±��� �Ӽ��� file �� �±��� name �Ӽ��� : �Ķ���� �̸�
				String name = (String)files.nextElement();
				
				//������ ���� ���ε��  ���� �̸�
				String filename = multi.getFilesystemName(name);
				
				String filepath = contextpath;
				
				//���۵� ������ ���� Ÿ��
				//String type = ComUtil.getExtension(filename);
				
				//������ ���� ����Ǵ� ��� ����
				//String saveFolder = "E:/DEV/dcpp/public_html/tmp_file";
				//String saveFolder = request.getSession().getServletContext().getRealPath("/") + "article_file/" ;
				String saveFolder = filepath + "data_file";
				String savePath = saveFolder+"/"+filename;
				
				//���۵� �Ķ���� ArrayList
				ArrayList<Vector<String>> totalParam = new ArrayList<Vector<String>>();					
				
				//���۵� ���� �Ӽ��� file �±��� name �Ӽ����� �̿��� ���� ��ü ����
				File file = multi.getFile(name);
				
				boolean flag = ComUtil.moveExcelFile(file, saveFolder, filename);
				
				//System.out.println("Excel File Creating Flag : " + flag);
				
				if(flag == true){	//���� ���� �̵� �Ǹ�,								
					Workbook wb = WorkbookFactory.create(new FileInputStream(savePath));
					//���� ������ ����
					//HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(savePath));
					//��ũ�� ����
					//HSSFWorkbook workbook = new HSSFWorkbook(fs);
					//��Ʈ�̸��� ��Ʈ ��ȣ ����
					Sheet sheet = wb.getSheetAt(0);
					// ��Ʈ�� ������ �� ��ȣ ����
					int rows = sheet.getLastRowNum() + 1;		
					
					DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
					//�߰� �Էº��� ���� ������ȣ�� ��ȸ�Ͽ� �״��� ��ȣ�� �Է��ϰ� ��.
					int data_no = dao_graph.selectInfGrdGraphDataNum(pr_no)-1;
					//������ ����
					boolean isNull = false;
					//for (Row row : wb.getSheetAt(0)) {
					for (int r = 1; r < rows; r++) {
						// ��Ʈ�� ���� ���� �ϳ��� ����
						Row row = sheet.getRow(r);
						if (row != null) {
							int cells = row.getLastCellNum();						
							Vector<String> v_list = new Vector<String>();
							v_list.addElement(pr_no);						
							//System.out.println(row);
							int add_seq_num = data_no + r;
							v_list.addElement(Integer.toString(add_seq_num));
							
							//for (Cell cell : row) {
							for (int c = 0; c < cells; c++) {
								// �࿡���� ���� �ϳ��� �����Ͽ� �� Ÿ�Կ� ���� ó��
								Cell cell = row.getCell(c);							
								if (cell != null) {								
									switch (cell.getCellType()) {
									case Cell.CELL_TYPE_FORMULA :
										v_list.addElement(cell.getCellFormula());
										break;
									case Cell.CELL_TYPE_NUMERIC :
										if(DateUtil.isCellDateFormatted(cell)){
											v_list.addElement(cell.getDateCellValue().toString());//date
										}else{
											v_list.addElement(Double.toString(cell.getNumericCellValue()));//double
										}
										break;
									case Cell.CELL_TYPE_STRING :
										v_list.addElement(cell.getRichStringCellValue().getString()); //String
										break;
									case Cell.CELL_TYPE_BLANK :
										v_list.addElement(ComVar.STRING_ZERO);
										break;
									case Cell.CELL_TYPE_BOOLEAN :
										v_list.addElement(Boolean.toString(cell.getBooleanCellValue())); //boolean
										break;
									case Cell.CELL_TYPE_ERROR :
										v_list.addElement(Byte.toString(cell.getErrorCellValue()));// byte
										break;
									default :
										 isNull = true;
									}								
								}
							}
							totalParam.add(v_list);
						}
					}
					value = dao_graph.insertFinalGraphExcelData(totalParam);
					boolean flag2 = ComUtil.deleteExcelFile(file, saveFolder, filename);			
					
				}
			}
				
		}catch(IOException ioe){
			System.out.println(ioe);
		}catch(Exception ex){
			System.out.println(ex);
		}
		
		return value;
		/*//���� ������ ����
					POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(savePath));
					//��ũ�� ����
					HSSFWorkbook workbook = new HSSFWorkbook(fs);
					//��Ʈ�̸��� ��Ʈ ��ȣ ����
					HSSFSheet sheet = workbook.getSheetAt(0);
					// ��Ʈ�� ���� ���� �ϳ��� ����
					int rows = sheet.getPhysicalNumberOfRows();		
					
					DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
					//�߰� �Էº��� ���� ������ȣ�� ��ȸ�Ͽ� �״��� ��ȣ�� �Է��ϰ� ��.
					int data_no = dao_graph.selectBasicGraphDataNum(pr_no)-1;
					//������ ����
					for (int r = 1; r < rows; r++) {
						// ��Ʈ�� ���� ���� �ϳ��� ����
						HSSFRow row = sheet.getRow(r);
						if (row != null) {
							int cells = row.getPhysicalNumberOfCells();						
							Vector v_list = new Vector();
							v_list.addElement(pr_no);						
							v_list.addElement(data_no + r);
							for (int c = 0; c < cells; c++) {
								// �࿡���� ���� �ϳ��� �����Ͽ� �� Ÿ�Կ� ���� ó��
								HSSFCell cell = row.getCell(c);							
								if (cell != null) {								
									switch (cell.getCellType()) {
										case HSSFCell.CELL_TYPE_FORMULA :
											v_list.addElement(cell.getCellFormula());
											break;
										case HSSFCell.CELL_TYPE_NUMERIC :
											v_list.addElement(cell.getNumericCellValue());//double
											break;
										case HSSFCell.CELL_TYPE_STRING :
											v_list.addElement(cell.getStringCellValue()); //String
											break;
										case HSSFCell.CELL_TYPE_BLANK :
											v_list.addElement(ComVar.STRING_EMPTY);
											break;
										case HSSFCell.CELL_TYPE_BOOLEAN :
											v_list.addElement(cell.getBooleanCellValue()); //boolean
											break;
										case HSSFCell.CELL_TYPE_ERROR :
											v_list.addElement(cell.getErrorCellValue());// byte
											break;
										default :
											v_list.addElement("0");// �⺻��"0"
											break;
									}								
								}
							}
							totalParam.add(v_list);
						}*/
	}
	
	/**
	 * @MethodName : insertGraphDataInfo
	 * @Desc : �׷��� ������ �Է��ϴ� �޼ҵ�(���������� ������ �������ϳ����� ���ε�)
	 * @param request
	 * @param context
	 * @return
	 */
	public boolean insertGraphDataInfo(HttpServletRequest request) throws IOException{
		boolean value = false;
		//÷�� ������ ���� ���
		
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();

		try{
			//�������� ��ȣ
			String pr_no = request.getParameter("pr_no");
			//������ ��ȣ
			String data_no = String.valueOf(dao_graph.selectBasicGraphDataNum(pr_no));
			//X��
			String x_val = request.getParameter("x_val");
			//Y��
			String y_val = request.getParameter("y_val");
			//X�� ������
			String x_err = ComUtil.isNullToZeroString(request.getParameter("x_err"));
			//Y�� �ִ������
			String y_err_max = ComUtil.isNullToZeroString(request.getParameter("y_err_max"));
			//Y�� �ּҿ�����
			String y_err_min = ComUtil.isNullToZeroString(request.getParameter("y_err_min"));
			//������
			String ratio = ComUtil.isNullToZeroString(request.getParameter("ratio"));
			//�з�
			String press = ComUtil.isNullToZeroString(request.getParameter("press"));
			//���������
			String back_data = ComUtil.isNullToEmptyString(request.getParameter("back_data"));
			//Xȯ��
			double xax_cal = Double.parseDouble(request.getParameter("xax_cal"));
			//Yȯ��
			double xay_cal = Double.parseDouble(request.getParameter("xay_cal"));
			//���� X��
			double t_x_val = Double.parseDouble(x_val) * xax_cal;
			//���� Y��
			double t_y_val = Double.parseDouble(y_val) * xay_cal;
			Graph_Data_Info info = new Graph_Data_Info();
			
			info.setPL_BI_DATA_NUM(pr_no);
			info.setPL_BGD_SEQ_NUM(data_no);
			info.setPL_BGD_X_AX_VAL(Double.toString(t_x_val));
			info.setPL_BGD_Y_AX_VAL(Double.toString(t_y_val));
			info.setPL_BGD_X_ERR(x_err);
			info.setPL_BGD_Y_ERR_MAX(y_err_max);
			info.setPL_BGD_Y_ERR_MIN(y_err_min);
			info.setPL_BGD_RATIO(ratio);
			info.setPL_BGD_PRESS(press);
			info.setPL_BGD_BACKUP_DATA(back_data);					
			
			value = dao_graph.insertBasicGraphData(info);
					
		}catch(Exception ex){
			System.out.println(ex);
		}
		
		return value;
	}
	
	
	/**
	 * @MethodName  : updateGraphDataTotalCountInfo
	 * @Date   : 2013. 12. 18. 
	 * @MethodDescription : �ö�������������� ��ġ ������ �⺻���̺� �Է��ϴ� �޼ҵ�
	 * @param st_pr_no
	 * @param ed_pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean updateGraphDataTotalCountInfo(String st_pr_no, String ed_pr_no){ 
		boolean value = false;
		//÷�� ������ ���� ���
		
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
		
		//ī��Ʈ ��������
		Vector<?> info = dao_graph.selectGraphDataTotalCount(st_pr_no, ed_pr_no);
		
		//������ ī��Ʈ�� ������ ���̽��� �ٽ� �Է��ϱ�
		for(int i = 0 ; i < info.size(); i++) {
			Common_Data data = (Common_Data)info.elementAt(i);
			
			String pr_no = data.getPL_CM_STR1(); //������ȣ
			String pr_cnt = data.getPL_CM_STR2(); //ī��Ʈ
			
			//System.out.println("������ȣ : " + pr_no);
			//System.out.println("��ġ���� : " + pr_cnt);
			//System.out.println(" ");
			
			value = dao_graph.updateGraphDataTotalCount(pr_no, pr_cnt);
			
			if(value == false){
				return value;
			}
		}
		
		
		//value = dao_graph.insertBasicGraphData(info);

		
		return value;
	}
	
	/**
	 * @MethodName  : updateGraphDataInfo
	 * @Date   : 2010. 04. 13 
	 * @MethodDescription : �ش� ��ġ�� �׷��� �����͸� ������Ʈ �ϴ� �޼ҵ� 
	 * @param request
	 * @return
	 * @throws IOException
	 * @History  : - 
	 */
	public boolean updateGraphDataInfo(HttpServletRequest request) throws IOException{
		boolean value = false;
		
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();

		try{
			//�������� ��ȣ
			String pr_no = request.getParameter("pr_no");
			//������ ��ȣ
			String data_no = request.getParameter("dt_no");//String.valueOf(dao_graph.selectBasicGraphDataNum());
			//X��
			String x_val = request.getParameter("x_val");
			//Y��
			String y_val = request.getParameter("y_val");
			//X�� ������
			String x_err = ComUtil.isNullToEmptyString(request.getParameter("x_err"));
			//Y�� �ִ������
			String y_err_max = ComUtil.isNullToEmptyString(request.getParameter("y_err_max"));
			//Y�� �ּҿ�����
			String y_err_min = ComUtil.isNullToEmptyString(request.getParameter("y_err_min"));
			//������
			String ratio = ComUtil.isNullToEmptyString(request.getParameter("ratio"));
			//�з�
			String press = ComUtil.isNullToEmptyString(request.getParameter("press"));
			//���������
			String back_data = ComUtil.isNullToEmptyString(request.getParameter("back_data"));
			//Xȯ��
			double xax_cal = Double.parseDouble(request.getParameter("xax_cal"));
			//Yȯ��
			double xay_cal = Double.parseDouble(request.getParameter("xay_cal"));
			//���� X��
			double t_x_val = Double.parseDouble(x_val) * xax_cal;
			//���� Y��
			double t_y_val = Double.parseDouble(y_val) * xay_cal;
			
			
			
			Graph_Data_Info info = new Graph_Data_Info();
			
			info.setPL_BI_DATA_NUM(pr_no);
			info.setPL_BGD_SEQ_NUM(data_no);
			info.setPL_BGD_X_AX_VAL(Double.toString(t_x_val));
			info.setPL_BGD_Y_AX_VAL(Double.toString(t_y_val));
			info.setPL_BGD_X_ERR(x_err);
			info.setPL_BGD_Y_ERR_MAX(y_err_max);
			info.setPL_BGD_Y_ERR_MIN(y_err_min);
			info.setPL_BGD_RATIO(ratio);
			info.setPL_BGD_PRESS(press);
			info.setPL_BGD_BACKUP_DATA(back_data);					
			
			value = dao_graph.updateBasicGraphData(info);
					
		}catch(Exception ex){
			System.out.println(ex);
		}
		
		return value;
	}
	
	/**
	 * @MethodName : selectGraphBasicData
	 * @Desc : �׷��� �����͸� �������� �޼ҵ�
	 * @return
	 */
	public Vector<?> selectGraphBasicData(String pr_no){
		Vector<?> v_list = new Vector<Object>();
		
		Vector<String> v_option = new Vector<String>();
		v_option.addElement(pr_no);
		
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
		
		v_list = dao_graph.selectBasicGraphData(v_option);
		
		/*try {
			//System.out.println("Flag : 1");
			String num_file = writeGraphBasicData(pr_no, v_list); //��ġ ���� ����
			
			String user_id = "test_user";
			String conf_file = writeGnuplotSingleConf(pr_no, user_id, num_file); //�ɼǿ� ���� gnuplot conf ����
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return v_list;
	}
	
	/**
	 * @MethodName  : selectGraphDataInXSAMS
	 * @Date   : 2014. 5. 14. 
	 * @MethodDescription : XSAMS ���� ������ �ʿ��� X��, Y�� ��ġ�� �޾ư��� �޼ҵ�
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public Hashtable selectGraphDataInXSAMS(String pr_no){
		//Hashtable r_value="";
		
		Vector<?> v_list = new Vector<Object>();
		
		Vector<String> v_option = new Vector<String>();
		v_option.addElement(pr_no);
		
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
		
		v_list = dao_graph.selectBasicGraphData(v_option);
		
		int v_dt_count = v_list.size();
		
		String x_value = "";
		//x_value + xval;
		String y_value = "";
		//y_value + yval;
		
		for(int i=0; i < v_dt_count; i++){
	  		Graph_Data_Info dti = (Graph_Data_Info) v_list.elementAt(i);
			//String pro_no = dti.getPL_BI_DATA_NUM();
			//String dt_no = dti.getPL_BGD_SEQ_NUM();
			String xval = dti.getPL_BGD_X_AX_VAL();
			String yval = dti.getPL_BGD_Y_AX_VAL();
						
			if(i>0){
				x_value = x_value + " " + xval;
				y_value = y_value + " " + yval;
			}
			
			//double t_yval = Math.log10(Double.valueOf(yval));
			//System.out.println(ComUtil.getStringByDoubleFormat(yval));
			
			//String xerr = dti.getPL_BGD_X_ERR();
			//String yerrmax = dti.getPL_BGD_Y_ERR_MAX();
			//String yerrmin = dti.getPL_BGD_Y_ERR_MIN();
			//String s_ratio = dti.getPL_BGD_RATIO();
			//String s_press = dti.getPL_BGD_PRESS();
			//String s_backdata = dti.getPL_BGD_BACKUP_DATA();
		}
		
		Hashtable<String, String> r_value = new Hashtable<String, String>();
		r_value.put("x", x_value);
		r_value.put("y", y_value);
		
		return r_value;		
	}
	
	/*public Vector selectGraphBasicData1(String pr_no, String user_id){
		Vector v_list = new Vector();
		
		Vector v_option = new Vector();
		v_option.addElement(pr_no);
		
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
		
		v_list = dao_graph.selectBasicGraphData(v_option);
		
		try {
			//System.out.println("Flag : 1");
			String num_file = writeGraphBasicData(pr_no, v_list); //��ġ ���� ����
			
			String user_id = "test_user";
			String conf_file = writeGnuplotSingleConf(pr_no, user_id, num_file); //�ɼǿ� ���� gnuplot conf ����
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return v_list;
	}*/
	
	/**
	 * @MethodName  : drawSingleGraphData
	 * @Date   : 2012. 12. 11. 
	 * @MethodDescription : �Ϲ� ��ȸ�� �������� 1�� �϶� �׷����� �׸��� �޼ҵ�
	 * @param pr_no
	 * @param user_id
	 * @return
	 * @History  : - 
	 */
	/*public String drawSingleGraphData(String pr_no){
		String r_value = ""; //��� ������, ���ϸ��� �ش�.
		Vector v_list = new Vector();
		
		Vector v_option = new Vector();
		v_option.addElement(pr_no);
		
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
		
		v_list = dao_graph.selectBasicGraphData(v_option);
		
		try {
			System.out.println("Flag : 1");
			
			
			writeGraphBasicData(pr_no, v_list);
			
			
			
			String usr_id= "";
			writeGnuplotSingleConf(pr_no, usr_id);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return r_value;
	}*/
	
	/**
	 * @MethodName  : drawMultiGraphData
	 * @Date   : 2012. 12. 12. 
	 * @MethodDescription : ��ȸ�� �������� ������ �϶� �׷����� �׸��� �޼ҵ�
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	/*public String drawMultiGraphData(String pr_no){
		String r_value = ""; //��� ������, ���ϸ��� �ش�.
		Vector v_list = new Vector();
		
		Vector v_option = new Vector();
		v_option.addElement(pr_no);
		
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
		
		v_list = dao_graph.selectBasicGraphData(v_option);
		
		try {
			System.out.println("Flag : 1");
			
			
			writeGraphBasicData(pr_no, v_list);
			
			
			
			String usr_id= "";
			writeGnuplotSingleConf(pr_no, usr_id);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return r_value;
	}*/
	
	/**
	 * @MethodName  : writeGraphBasicData
	 * @Date   : 2012. 11. 6. 
	 * @MethodDescription : gnuplot �� �����ϱ� ���� ��ġ �����Ͱ� ����Ǵ� ������ ����� �޼ҵ�
	 * @param v_list
	 * @throws IOException
	 * @History  : - 
	 */
	public String writeGraphBasicData(String pr_no, Vector<?> dt_info) throws IOException{
		//FileOutputStream fos = null;
		//DataOutputStream dos = null;
		
		//System.out.println("Flag : 2");
		
		//System.out.println(this.getClass().getResource("").getPath());
		
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmSS");
		String strFileLocation = "/var/www/html/dcpp_web/gnuplot_conf";
		String strCurrDate = formatter.format(new java.util.Date());
		String fname = "gnuplot_data_" + pr_no + "_"+strCurrDate +".txt";//+"_"+strCurrDate 
		String strFileGenLoc = strFileLocation + File.separator + fname;
		System.out.println("strFileLocation : " + strFileGenLoc);
		
		//String fullname = "";
		
		int v_dt_count = dt_info.size();
		
		try{
			File f = new File(strFileGenLoc);
			
			//System.out.println("path : " + f.getCanonicalPath());
		    
			OutputStream os = (OutputStream)new FileOutputStream(f);
		    String encoding = "ASCII";
		    OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
		    BufferedWriter bw = new BufferedWriter(osw);

		     bw.write("#X"+"\t"+"Y"+"\t"+"Xerr"+"\t"+"Yerr"+"\t"+"Yerr-"+"\r\n");
				//System.out.println("Flag : 3");
				for (int i = 0 ; i < v_dt_count; i++) {
					Graph_Data_Info dti = (Graph_Data_Info) dt_info.elementAt(i);
					String pro_no = dti.getPL_BI_DATA_NUM();
					String dt_no = dti.getPL_BGD_SEQ_NUM();
					String xval = dti.getPL_BGD_X_AX_VAL();
					String yval = dti.getPL_BGD_Y_AX_VAL();
					String xerr = dti.getPL_BGD_X_ERR();
					String yerrmax = dti.getPL_BGD_Y_ERR_MAX();
					String yerrmin = dti.getPL_BGD_Y_ERR_MIN();		
					bw.write(xval);
					bw.write("\t");
					bw.write(yval);
					bw.write("\t");
					bw.write(xerr);
					bw.write("\t");
					bw.write(yerrmax);
					bw.write("\t");
					bw.write(yerrmin);
					bw.write("\r\n");
				}	
				bw.flush();
				bw.close();
				//System.out.println("Flag : 4");
				
			/*fos = new FileOutputStream(new File(strFileGenLoc), false);
			dos = new DataOutputStream(fos);
			dos.writeChars("#X"+"\t"+"Y"+"\t"+"Xerr"+"\t"+"Yerr"+"\t"+"Yerr-"+"\r\n");
			System.out.println("Flag : 3");
			for (int i = 0 ; i < v_dt_count; i++) {
				Graph_Data_Info dti = (Graph_Data_Info) dt_info.elementAt(i);
				String pro_no = dti.getPL_BI_DATA_NUM();
				String dt_no = dti.getPL_BGD_SEQ_NUM();
				String xval = dti.getPL_BGD_X_AX_VAL();
				String yval = dti.getPL_BGD_Y_AX_VAL();
				String xerr = dti.getPL_BGD_X_ERR();
				String yerrmax = dti.getPL_BGD_Y_ERR_MAX();
				String yerrmin = dti.getPL_BGD_Y_ERR_MIN();		
				dos.writeChars(xval);
				dos.writeChars("\t");
				dos.writeChars(yval);
				dos.writeChars("\t");
				dos.writeChars(xerr);
				dos.writeChars("\t");
				dos.writeChars(yerrmax);
				dos.writeChars("\t");
				dos.writeChars(yerrmin);
				dos.writeChars("\r\n");
			}	
			dos.flush();
			dos.close();
			System.out.println("Flag : 4");*/
			
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
			System.out.println("�߸��� �����̸��� �Է��߽��ϴ�.");
		}
		
		return strFileGenLoc;
	}
	
	/**
	 * @MethodName  : writeGnuplotSingleConf
	 * @Date   : 2012. 12. 11. 
	 * @MethodDescription : ���������� 1�� �϶� gnuplot conf ����� �޼ҵ�
	 * @param pr_no
	 * @param user_id
	 * @throws IOException
	 * @History  : - 
	 */
	public String writeGnuplotSingleConf(String pr_no, String user_id, String num_file) throws IOException{
		//FileOutputStream fos = null;
		//DataOutputStream dos = null;
		
		//System.out.println("Flag : 2");
		
		//System.out.println(this.getClass().getResource("").getPath());
		//selectViewGraphBasicInfo
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmSS");
		//String strFileLocation = "G:\\test";
		String strConfFileLocation = "/var/www/html/dcpp_web/gnuplot_conf";
		//String strConfFileLocation = "G:\\test";
		String strGraphFileLocation = "/var/www/html/dcpp_web/gnuplot_fig";
		//String strGraphFileLocation = "G:\\test";
		String strCurrDate = formatter.format(new java.util.Date());
		String file_name = "gp_data_" + user_id +"_"+strCurrDate;
		String fname = file_name + ".conf";//+"_"+strCurrDate 
		String gname = file_name + ".png";
		String strConfFileGenLoc = strConfFileLocation + File.separator + fname;
		String strGraphFileGenLoc = strGraphFileLocation + File.separator + gname;
		//System.out.println("strConfFileGenLoc : " + strConfFileGenLoc);
		//System.out.println("strGraphFileGenLoc : " + strGraphFileGenLoc);
		
		
		Graph_Basic_Info info = selectViewGraphBasicInfo(pr_no);
		String x_unit = info.getPL_BGI_X_AX_UNIT();
		String y_unit = info.getPL_BGI_Y_AX_UNIT();
		//int v_dt_count = dt_info.size();
		/*String[] cmd = { "/usr/bin/gnuplot", "set term png", "set grid", 
				"set logscale", "set title 'Plasma Properties Graph'", "set xlabel '" +x_unit+ "'", 
				"set ylabel '" +y_unit+ "'", "set output '" +strGraphFileGenLoc+ "'", "plot '" +num_file+ "'"};*/
		try{
			File f = new File(strConfFileGenLoc);
			OutputStream os = (OutputStream)new FileOutputStream(f);
			String encoding = "ASCII";
			OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
			BufferedWriter bw = new BufferedWriter(osw);
			
			//bw.write("#X"+"\t"+"Y"+"\t"+"Xerr"+"\t"+"Yerr"+"\t"+"Yerr-"+"\r\n");
			bw.write("# GNUPLOT CONF : "+ fname +"\r\n");
			//�׷��� �������� ����
			bw.write("set term png "+"\r\n");
			//�׷��� �׸��� ����
			bw.write("set grid "+"\r\n");
			//�׷��� �α׽����� ����
			bw.write("set logscale "+"\r\n");		
			//�׷��� ���� ����
			bw.write("set title 'Plasma Properties Graph' "+"\r\n");
			//�׷��� X�� ���� ����
			bw.write("set xlabel '" +x_unit+ "' \r\n");
			//�׷��� Y�� ���� ���� 
			bw.write("set ylabel '" +y_unit+ "' \r\n");
			//�׷��� �����ġ ���� 
			bw.write("set output '" +strGraphFileGenLoc+ "' \r\n");
			//plot �κ�
			bw.write("plot '" +num_file+ "' \r\n");
			//
			bw.write("\r\n");
					
			bw.flush();
			bw.close();
				//System.out.println("Flag : 4");
				
			/*fos = new FileOutputStream(new File(strFileGenLoc), false);
			dos = new DataOutputStream(fos);
			dos.writeChars("#X"+"\t"+"Y"+"\t"+"Xerr"+"\t"+"Yerr"+"\t"+"Yerr-"+"\r\n");
			System.out.println("Flag : 3");
			for (int i = 0 ; i < v_dt_count; i++) {
				Graph_Data_Info dti = (Graph_Data_Info) dt_info.elementAt(i);
				String pro_no = dti.getPL_BI_DATA_NUM();
				String dt_no = dti.getPL_BGD_SEQ_NUM();
				String xval = dti.getPL_BGD_X_AX_VAL();
				String yval = dti.getPL_BGD_Y_AX_VAL();
				String xerr = dti.getPL_BGD_X_ERR();
				String yerrmax = dti.getPL_BGD_Y_ERR_MAX();
				String yerrmin = dti.getPL_BGD_Y_ERR_MIN();		
				dos.writeChars(xval);
				dos.writeChars("\t");
				dos.writeChars(yval);
				dos.writeChars("\t");
				dos.writeChars(xerr);
				dos.writeChars("\t");
				dos.writeChars(yerrmax);
				dos.writeChars("\t");
				dos.writeChars(yerrmin);
				dos.writeChars("\r\n");
			}	
			dos.flush();
			dos.close();
			System.out.println("Flag : 4");*/
			
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
			System.out.println("�߸��� �����̸��� �Է��߽��ϴ�.");
		}
		
		try{
			
				callShellCommand("gnuplot "+strConfFileGenLoc);
				callShellCommand("rm "+num_file);
				callShellCommand("rm "+strConfFileGenLoc);
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("��ɾ� ���࿡ �����߽��ϴ�.");
		}
		
		
		return strGraphFileGenLoc;
	}
	
	public void callShellCommand(String cmd) throws Exception {
		//String command = "gnuplot " + name;
		//System.out.println(command);
		//java.lang.Runtime runTime = java.lang.Runtime.getRuntime();
		/*for(int i=0; i < cmd.length; i++){
			java.lang.Runtime runTime = java.lang.Runtime.getRuntime();
			java.lang.Process process = runTime.exec(cmd[i]);
			
			System.out.println(process.waitFor());
		
		}*/
		//String command = "execute command";

		//java.lang.Runtime runTime = java.lang.Runtime.getRuntime();
		//java.lang.Process process = runTime.exec(command);
		
		//System.out.println(process.waitFor());
		
		String s;

		try {
		      //�ڹ� 1.4 ���Ͽ����� �̷���
		    Runtime runtime = Runtime.getRuntime();
		    Process process = runtime.exec(cmd);
		    System.out.println(process.waitFor());
//		    Process oProcess = oRuntime.exec("cmd /c dir /?");
		    

		   /* // �ڹ� 1.5 �̻󿡼��� �̷��� 1�ٷ�
		    //Process oProcess = new ProcessBuilder("gnuplot", "+strConfFileGenLoc").start();

		    // �ܺ� ���α׷� ��� �б�
		    BufferedReader stdOut   = new BufferedReader(new InputStreamReader(oProcess.getInputStream()));
		    BufferedReader stdError = new BufferedReader(new InputStreamReader(oProcess.getErrorStream()));

		    // "ǥ�� ���"�� "ǥ�� ���� ���"�� ���
		    while ((s =   stdOut.readLine()) != null) System.out.println(s);
		    while ((s = stdError.readLine()) != null) System.err.println(s);

		    // �ܺ� ���α׷� ��ȯ�� ��� (�� �κ��� �ʼ��� �ƴ�)
		    System.out.println("Exit Code: " + oProcess.exitValue());
		    System.exit(oProcess.exitValue()); // �ܺ� ���α׷��� ��ȯ����, �� �ڹ� ���α׷� ��ü�� ��ȯ������ ���
*/
		  } catch (IOException e) { // ���� ó��
		      System.err.println("����! �ܺ� ��� ���࿡ �����߽��ϴ�.\n" + e.getMessage());
		      System.exit(-1);
		  }

	}
	
	/**
	 * @MethodName  : writeGnuplotMultiConf
	 * @Date   : 2012. 12. 11. 
	 * @MethodDescription : ���������� ������ �϶� gnuplot conf ����� �޼ҵ�
	 * @param p_list : ���������� ����ִ� ����Ʈ
	 * @param user_id : ����� ID
	 * @throws IOException
	 * @History  : - 
	 */
	public void writeGnuplotMultiConf(Vector<?> p_list, String user_id) throws IOException{
		//FileOutputStream fos = null;
		//DataOutputStream dos = null;
		
		//System.out.println("Flag : 2");
		
		//System.out.println(this.getClass().getResource("").getPath());
		//selectViewGraphBasicInfo
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmSS");
		String strConfFileLocation = "../gnuplot_conf";
		String strGraphFileLocation = "../gnuplot_fig";
		String strCurrDate = formatter.format(new java.util.Date());
		String file_name = "gp_data_" + user_id +"_"+strCurrDate;
		String fname = file_name + ".conf";//+"_"+strCurrDate 
		String gname = file_name + ".png";
		String strConfFileGenLoc = strConfFileLocation + File.separator + fname;
		String strGraphFileGenLoc = strGraphFileLocation + File.separator + fname;
		System.out.println("strConfFileGenLoc : " + strConfFileGenLoc);
		System.out.println("strGraphFileGenLoc : " + strGraphFileGenLoc);
		
		String x_unit = "";
		String y_unit = "";
		
		for(int i = 0; i < p_list.size(); i++){
			String pr_no = (String)p_list.elementAt(i);
		
			Graph_Basic_Info info = selectViewGraphBasicInfo(pr_no);
			if(i == 0){
			 x_unit = info.getPL_BGI_X_AX_UNIT();
			 y_unit = info.getPL_BGI_Y_AX_UNIT();
			}
			//int v_dt_count = dt_info.size();
		
			
		}
		
		try{
			File f = new File(strConfFileGenLoc);
			OutputStream os = (OutputStream)new FileOutputStream(f);
			String encoding = "ASCII";
			OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
			BufferedWriter bw = new BufferedWriter(osw);
			
			bw.write("# GNUPLOT CONF : "+ fname +"\r\n");
			//�׷��� �������� ����
			bw.write("set term png "+"\r\n");
			//�׷��� �׸��� ����
			bw.write("set grid "+"\r\n");
			//�׷��� �α׽����� ����
			bw.write("set logscale "+"\r\n");		
			//�׷��� ���� ����
			bw.write("set title 'Plasma Properties Graph' "+"\r\n");
			//�׷��� X�� ���� ����
			bw.write("set xlabel '" +x_unit+ "' \r\n");
			//�׷��� Y�� ���� ���� 
			bw.write("set ylabel '" +y_unit+ "' \r\n");
			//�׷��� �����ġ ���� 
			bw.write("set output '" +strGraphFileGenLoc+ "' \r\n");
			//plot �κ�
			// ���⿡ ��ġ���� ����Ʈ�� ������ ��.
			//
			bw.write("\r\n");
					
			bw.flush();
			bw.close();
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
			System.out.println("�߸��� �����̸��� �Է��߽��ϴ�.");
		}
	}	
	
	
	/**
	 * @MethodName  : selectGraphBasicData
	 * @Date   : 2010. 04. 12 
	 * @MethodDescription : ����������ȣ�� ���� ��ȣ�� ���ؼ� �׷��� ������ �������� �޼ҵ�
	 * @param pr_no
	 * @param dt_no
	 * @return
	 * @History  : - 
	 */
	public Graph_Data_Info selectGraphBasicData(String pr_no, String dt_no){
		
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
		
		return dao_graph.selectBasicGraphData(pr_no, dt_no);
	}
	
	/**
	 * @MethodName : selectGraphBasicInfo
	 * @Desc : �׷��� �⺻ ������ �������� �޼ҵ�
	 * @param pr_no
	 * @return
	 */
	public Graph_Basic_Info selectGraphBasicInfo(String pr_no){
	
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
		
		return dao_graph.selectBasicGraphInfo(pr_no);
	}
	
	/**
	 * @MethodName : selectViewGraphBasicInfo
	 * @Desc : �׷��� �⺻ ���� Ȯ�� �������� �����͸� �������� �޼ҵ�
	 * @param pr_no
	 * @return
	 */
	public Graph_Basic_Info selectViewGraphBasicInfo(String pr_no){
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
		
		Graph_Basic_Info info = dao_graph.selectBasicGraphInfo(pr_no);
		
		Ctr_Option_Process ctr_option = new Ctr_Option_Process();
		//X����
		info.setPL_BGI_X_AX_UNIT(ctr_option.getExpName(info.getPL_BGI_X_AX_UNIT()));
		//Y����
		info.setPL_BGI_Y_AX_UNIT(ctr_option.getExpName(info.getPL_BGI_Y_AX_UNIT()));
		
		return info;
	}
	
	/**
	 * @MethodName  : selectViewInfGrdGraphInfo
	 * @Date   : 2010. 09. 15 
	 * @MethodDescription : ������� �׷��� �⺻ ���� Ȯ�� �������� �����͸� �������� �޼ҵ�
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public Inf_Grd_Graph_Basic_Info selectViewInfGrdGraphInfo(String pr_no){
		DAO_INF_GRD_GRAPH_INFO dao_graph = new DAO_INF_GRD_GRAPH_INFO();
		
		Inf_Grd_Graph_Basic_Info info = dao_graph.selectInfGrdGraphInfo(pr_no);
		
		Ctr_Option_Process ctr_option = new Ctr_Option_Process();
		//X����
		info.setPL_IGGI_X_AX_UNIT(ctr_option.getExpName(info.getPL_IGGI_X_AX_UNIT()));
		//Y����
		info.setPL_IGGI_Y_AX_UNIT(ctr_option.getExpName(info.getPL_IGGI_Y_AX_UNIT()));
		
		return info;
	}
	
	/**
	 * @MethodName  : selectViewInfGrdGraphData
	 * @Date   : 2010. 09. 15 
	 * @MethodDescription : ������� �׷��� ������ Ȯ�� �������� �����͸� �������� �޼ҵ�
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public Vector<?> selectViewInfGrdGraphData(String pr_no){
		Vector<?> v_list = new Vector<Object>();
		
		DAO_INF_GRD_GRAPH_INFO dao_graph = new DAO_INF_GRD_GRAPH_INFO();
		
		v_list = dao_graph.selectInfGrdGraphData(pr_no);
		
		return v_list;
	}
	
	/**
	 * @MethodName : selectGraphBasicInfoCount
	 * @Desc : �׷��� �⺻ ���� ������ �������� �޼ҵ�
	 * @param pr_no
	 * @return
	 */
	public int selectGraphBasicInfoCount(String pr_no){
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
		
		int count = dao_graph.selectBasicGraphInfoCount(pr_no);
		return count;
	}
	
	/**
	 * @MethodName  : deleteGraphData
	 * @Date   : 2012. 1. 13. 
	 * @MethodDescription : �׷��� ��ġ ���� �����ϴ� �޼ҵ�
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean deleteGraphData(String pr_no){
		boolean r_value = false;
		
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
		r_value = dao_graph.deleteGraphData(pr_no);
		
		return r_value;
	}
	
	/**
	 * @MethodName  : selectModifyNumberList
	 * @Date   : 2013. 8. 29. 
	 * @MethodDescription : �Ҽ��� ���� ������ ���� �޼ҵ�
	 * @return
	 * @History  : - 
	 */
	public Vector<String> selectModifyNumberList(String st_no, String ed_no){
		Vector<?> v_list = new Vector<Object>();
		
		//v_list�� �Ҽ��� �ڸ� �����ؾ� �� ��鸸 ��ư���.
		
		Vector<String> r_value = new Vector<String>();
		
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
		
		v_list = dao_graph.selectBasicGraphNO(st_no, ed_no);
		
		String old_pr_no = "";

		for(int i = 0; i < v_list.size(); i++){
			Graph_Data_Info data = (Graph_Data_Info)v_list.elementAt(i);
				
			String x_val = data.getPL_BGD_X_AX_VAL();
			String y_val = data.getPL_BGD_Y_AX_VAL();
			String y_err_max = data.getPL_BGD_Y_ERR_MAX();
			String y_err_min = data.getPL_BGD_Y_ERR_MIN();
			String seq = data.getPL_BGD_SEQ_NUM();			
			String pr_no = data.getPL_BI_DATA_NUM();
			
			if(!pr_no.equalsIgnoreCase(old_pr_no)){
				int x_point = x_val.indexOf(".");
				int y_point = y_val.indexOf(".");
				int y_max_point = y_err_max.indexOf(".");
				int y_min_point = y_err_min.indexOf(".");
				
				if(x_point == 0 || y_point == 0 || y_max_point == 0 || y_min_point == 0 ){
					System.out.println("++++++++++++++++++++++++++++ " + seq);	
					System.out.println("pr_no : " + pr_no);
					System.out.println("x_val : " + x_val);
					System.out.println("y_val : " + y_val);
					System.out.println("y_err_max : " + y_err_max);
					System.out.println("y_err_min : " + y_err_min);					
					System.out.println("============================");
					System.out.println(" ");
					r_value.addElement(pr_no);
				}
			}
			old_pr_no = pr_no;
			
			/*
			int x_point = x_val.indexOf(".");
			int y_point = y_val.indexOf(".");
			int y_max_point = y_err_max.indexOf(".");
			int y_min_point = y_err_min.indexOf(".");
			
			String new_x_val = "0." + x_val;
			String new_y_val = "0." + y_val;
			String new_y_max = "0." + y_err_max;4541234567890.
			String new_y_min = "0." + y_err_min;

			if(x_point == 0 || y_point == 0 || y_max_point == 0 || y_min_point == 0 ){
				System.out.println("++++++++++++++++++++++++++++ " + seq);	
				System.out.println("pr_no : " + pr_no);
				System.out.println("x_val : " + x_val);
				System.out.println("y_val : " + y_val);
				System.out.println("y_err_max : " + y_err_max);
				System.out.println("y_err_min : " + y_err_min);
				
				System.out.println("============================");
				
				r_value.addElement(pr_no);
			}
			if(x_point == 0){
				String new_x_val = "0" + x_val;
				System.out.println(">>>");
				System.out.println("new_x_val : " + new_x_val);
				
				//r_value = dao_graph.updateGraphXPointData(pr_no, new_x_val, seq);
			}
			
			if(y_point == 0){
				String new_y_val = "0" + y_val;
				System.out.println(">>>>>>");
				System.out.println("new_y_val : " + new_y_val);
				
				///r_value = dao_graph.updateGraphYPointData(pr_no, new_y_val, seq);
			}
			
			if(y_max_point == 0){
				String new_y_max = "0" + y_err_max;
				System.out.println(">>>>>>>>>>>>");
				System.out.println("new_y_max : " + new_y_max);
				
				//r_value = dao_graph.updateGraphYErrMaxPointData(pr_no, new_y_max, seq);
			}
			
			if(y_min_point == 0){
				String new_y_min = "0" + y_err_min;
				System.out.println(">>>>>>>>>>>>>>>>>>");
				System.out.println("new_y_min : " + new_y_min);

				//r_value = dao_graph.updateGraphYErrMinPointData(pr_no, new_y_min, seq);
			}*/
			
		}
		
		return r_value;
	}
	
	/**
	 * @MethodName  : modifyGraphPointData
	 * @Date   : 2013. 8. 26. 
	 * @MethodDescription : �׷��� ��ġ�� �Ҽ��� �ڸ� ����(.XXX -> 0.XXX)�ϴ� �޼ҵ�
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean modifyGraphPointData(String pr_no){
		boolean r_value = false;
		
		//�������� ��ü ��������
		
		Vector<?> v_list = new Vector<Object>();
		
		Vector<String> v_option = new Vector<String>();
		v_option.addElement(pr_no);
		
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
		
		v_list = dao_graph.selectBasicGraphData(v_option);

		for(int i = 0; i < v_list.size(); i++){
			Graph_Data_Info data = (Graph_Data_Info)v_list.elementAt(i);
				
			String x_val = data.getPL_BGD_X_AX_VAL();
			String y_val = data.getPL_BGD_Y_AX_VAL();
			String y_err_max = data.getPL_BGD_Y_ERR_MAX();
			String y_err_min = data.getPL_BGD_Y_ERR_MIN();
			String seq = data.getPL_BGD_SEQ_NUM();
			
			int x_point = x_val.indexOf(".");
			int y_point = y_val.indexOf(".");
			int y_max_point = y_err_max.indexOf(".");
			int y_min_point = y_err_min.indexOf(".");
			
/*			String new_x_val = "0." + x_val;
			String new_y_val = "0." + y_val;
			String new_y_max = "0." + y_err_max;4541234567890.
			String new_y_min = "0." + y_err_min;*/

			if(x_point == 0 || y_point == 0 || y_max_point == 0 || y_min_point == 0 ){
				System.out.println("++++++++++++++++++++++++++++ " + seq);	
				System.out.println("x_val : " + x_val);
				System.out.println("y_val : " + y_val);
				System.out.println("y_err_max : " + y_err_max);
				System.out.println("y_err_min : " + y_err_min);
				
				System.out.println("============================");
			}
			if(x_point == 0){
				String new_x_val = "0" + x_val;
				System.out.println(">>>");
				System.out.println("new_x_val : " + new_x_val);
				
				r_value = dao_graph.updateGraphXPointData(pr_no, new_x_val, seq);
			}
			
			if(y_point == 0){
				String new_y_val = "0" + y_val;
				System.out.println(">>>>>>");
				System.out.println("new_y_val : " + new_y_val);
				
				r_value = dao_graph.updateGraphYPointData(pr_no, new_y_val, seq);
			}
			
			if(y_max_point == 0){
				String new_y_max = "0" + y_err_max;
				System.out.println(">>>>>>>>>>>>");
				System.out.println("new_y_max : " + new_y_max);
				
				r_value = dao_graph.updateGraphYErrMaxPointData(pr_no, new_y_max, seq);
			}
			
			if(y_min_point == 0){
				String new_y_min = "0" + y_err_min;
				System.out.println(">>>>>>>>>>>>>>>>>>");
				System.out.println("new_y_min : " + new_y_min);

				r_value = dao_graph.updateGraphYErrMinPointData(pr_no, new_y_min, seq);
			}
			
			System.out.println(" ");

			
		}
				
		return r_value;
	}
	
}
