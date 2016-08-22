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
 * @Description : 그래프데이터와 관련된 처리를 하는 비즈니스 로직 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2009. 03. 24
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력 : -
 *
 */
public class Ctr_Graph_Info_Process {
	public Ctr_Graph_Info_Process(){
		
	}
	
	/**
	 * @MethodName : insertGraphBasicInfo
	 * @Desc : 그래프 기본 정보를 저장하는 메소드
	 * @param request
	 * @return
	 */
	public boolean insertGraphBasicInfo(HttpServletRequest request){
		boolean value = false;
		DAO_BASIC_GRAPH_INFO info = new DAO_BASIC_GRAPH_INFO();
		
		//물성정보 번호
		String pr_no = request.getParameter("pr_no");
		//X단위
		String x_unit = request.getParameter("xax_unit");
		//X환산
		String x_cal = request.getParameter("xax_cal");
		//Y단위
		String y_unit = request.getParameter("xay_unit");
		//Y환산
		String y_cal = request.getParameter("xay_cal");
		//Y참고
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
	 * @Desc : Excel 파일 업로드 하는 메소드
	 * @param multi
	 * @return
	 */
	public boolean insertTestGraphExcelData(MultipartRequest multi, String contextpath){
		boolean value = false;			
		
		try{						
			//전송할 파일 정보를 가져와 출력
			Enumeration<?> files = multi.getFileNames();
		
			String pr_no = "1";
			
			//파일 정보가 있는 동안 다음 루틴 처리
			while(files.hasMoreElements()){		
				
				//input 태그의 속성이 file 인 태그의 name 속성값 : 파라미터 이름
				String name = (String)files.nextElement();
				
				//서버에 실제 업로드된  파일 이름
				String filename = multi.getFilesystemName(name);
				
				String filepath = contextpath;
				
				//전송된 파일의 내용 타입
				//String type = ComUtil.getExtension(filename);
				
				//파일이 실제 저장되는 경로 지정
				//String saveFolder = "D:/DEV/dcpp_web/public_html/data_file";
				//String saveFolder = request.getSession().getServletContext().getRealPath("/") + "article_file/" ;
				String saveFolder = filepath + "data_file";
				String savePath = saveFolder+"/"+filename;
				
				
				//전송될 파라미터 ArrayList
				ArrayList<Vector<Comparable>> totalParam = new ArrayList<Vector<Comparable>>();					
				
				//전송된 파일 속성이 file 태그의 name 속성값을 이용해 파일 객체 생성
				File file = multi.getFile(name);
				
				boolean flag = ComUtil.moveExcelFile(file, saveFolder, filename);
				
				if(flag == true){	//파일 정상 이동 되면,
					Workbook wb = WorkbookFactory.create(new FileInputStream(savePath));
					//엑셀 데이터 추출
					//XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(savePath));
					//워크북 생성
					//HSSFWorkbook workbook = new HSSFWorkbook(fs);
					//시트이름과 시트 번호 추출
					Sheet sheet = wb.getSheetAt(0);
					// 시트의 마지막 줄 번호 리턴
					int rows = sheet.getLastRowNum()+1;		
					
					DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
					//추가 입력분을 위해 순서번호를 조회하여 그다음 번호로 입력하게 함.
					//int data_no = dao_graph.selectBasicGraphDataNum(pr_no)-1;
					//데이터 추출
					boolean isNull = false;
					//for (Row row : wb.getSheetAt(0)) {
					for (int r = 1; r < rows; r++) {
						// 시트에 대한 행을 하나씩 추출
						Row row = sheet.getRow(r);
						if (row != null) {
							int cells = row.getLastCellNum();						
							Vector<Comparable> v_list = new Vector<Comparable>();
							v_list.addElement(pr_no);						
							//System.out.println(row);
							v_list.addElement(r);
							
							//for (Cell cell : row) {
							for (int c = 0; c < cells; c++) {
								// 행에대한 셀을 하나씩 추출하여 셀 타입에 따라 처리
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
	 * @Desc : Excel 파일 업로드 하는 메소드
	 * @param multi
	 * @return
	 */
	public boolean insertGraphExcelData(MultipartRequest multi, String contextpath){
		boolean value = false;			
		
		try{						
			//전송할 파일 정보를 가져와 출력
			Enumeration<?> files = multi.getFileNames();
		
			String pr_no = multi.getParameter("pr_no");
			
			//파일 정보가 있는 동안 다음 루틴 처리
			while(files.hasMoreElements()){		
				
				//input 태그의 속성이 file 인 태그의 name 속성값 : 파라미터 이름
				String name = (String)files.nextElement();
				
				//서버에 실제 업로드된  파일 이름
				String filename = multi.getFilesystemName(name);
				
				String filepath = contextpath;
				
				//전송된 파일의 내용 타입
				//String type = ComUtil.getExtension(filename);
				
				//파일이 실제 저장되는 경로 지정
				//String saveFolder = "D:/DEV/dcpp_web/public_html/data_file";
				//String saveFolder = request.getSession().getServletContext().getRealPath("/") + "article_file/" ;
				String saveFolder = filepath + "data_file";
				String savePath = saveFolder+"/"+filename;
				
				
				//전송될 파라미터 ArrayList
				ArrayList<Vector<String>> totalParam = new ArrayList<Vector<String>>();					
				
				//전송된 파일 속성이 file 태그의 name 속성값을 이용해 파일 객체 생성
				File file = multi.getFile(name);
				
				boolean flag = ComUtil.moveExcelFile(file, saveFolder, filename);
				
				if(flag == true){	//파일 정상 이동 되면,	
					Workbook wb = WorkbookFactory.create(new FileInputStream(savePath));
					//엑셀 데이터 추출
					//HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(savePath));
					//워크북 생성
					//HSSFWorkbook workbook = new HSSFWorkbook(fs);
					//시트이름과 시트 번호 추출
					Sheet sheet = wb.getSheetAt(0);
					// 시트의 마지막 줄 번호 리턴
					int rows = sheet.getLastRowNum() + 1;		
					
					DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
					//추가 입력분을 위해 순서번호를 조회하여 그다음 번호로 입력하게 함.
					int data_no = dao_graph.selectBasicGraphDataNum(pr_no)-1;					
					//데이터 추출
					boolean isNull = false;
					//for (Row row : wb.getSheetAt(0)) {
					for (int r = 1; r < rows; r++) {
						// 시트에 대한 행을 하나씩 추출
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
								// 행에대한 셀을 하나씩 추출하여 셀 타입에 따라 처리
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
					/*//엑셀 데이터 추출
					POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(savePath));
					//워크북 생성
					HSSFWorkbook workbook = new HSSFWorkbook(fs);
					//시트이름과 시트 번호 추출
					HSSFSheet sheet = workbook.getSheetAt(0);
					// 시트에 대한 행을 하나씩 추출
					int rows = sheet.getPhysicalNumberOfRows();		
					
					DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
					//추가 입력분을 위해 순서번호를 조회하여 그다음 번호로 입력하게 함.
					int data_no = dao_graph.selectBasicGraphDataNum(pr_no)-1;
					//데이터 추출
					for (int r = 1; r < rows; r++) {
						// 시트에 대한 행을 하나씩 추출
						HSSFRow row = sheet.getRow(r);
						if (row != null) {
							int cells = row.getPhysicalNumberOfCells();						
							Vector v_list = new Vector();
							v_list.addElement(pr_no);						
							v_list.addElement(data_no + r);
							for (int c = 0; c < cells; c++) {
								// 행에대한 셀을 하나씩 추출하여 셀 타입에 따라 처리
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
											v_list.addElement("0");// 기본값"0"
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
	 * @MethodDescription : 최종 평가시 생성이 완료된 데이터(Excel)를 DB에 저장하는 메소드
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
			//전송할 파일 정보를 가져와 출력
			Enumeration<?> files = multi.getFileNames();
		
			String pr_no = multi.getParameter("v_pr_no");
			
			//파일 정보가 있는 동안 다음 루틴 처리
			while(files.hasMoreElements()){		
				
				//input 태그의 속성이 file 인 태그의 name 속성값 : 파라미터 이름
				String name = (String)files.nextElement();
				
				//서버에 실제 업로드된  파일 이름
				String filename = multi.getFilesystemName(name);
				
				String filepath = contextpath;
				
				//전송된 파일의 내용 타입
				//String type = ComUtil.getExtension(filename);
				
				//파일이 실제 저장되는 경로 지정
				//String saveFolder = "E:/DEV/dcpp/public_html/tmp_file";
				//String saveFolder = request.getSession().getServletContext().getRealPath("/") + "article_file/" ;
				String saveFolder = filepath + "data_file";
				String savePath = saveFolder+"/"+filename;
				
				//전송될 파라미터 ArrayList
				ArrayList<Vector<String>> totalParam = new ArrayList<Vector<String>>();					
				
				//전송된 파일 속성이 file 태그의 name 속성값을 이용해 파일 객체 생성
				File file = multi.getFile(name);
				
				boolean flag = ComUtil.moveExcelFile(file, saveFolder, filename);
				
				//System.out.println("Excel File Creating Flag : " + flag);
				
				if(flag == true){	//파일 정상 이동 되면,								
					Workbook wb = WorkbookFactory.create(new FileInputStream(savePath));
					//엑셀 데이터 추출
					//HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(savePath));
					//워크북 생성
					//HSSFWorkbook workbook = new HSSFWorkbook(fs);
					//시트이름과 시트 번호 추출
					Sheet sheet = wb.getSheetAt(0);
					// 시트의 마지막 줄 번호 리턴
					int rows = sheet.getLastRowNum() + 1;		
					
					DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
					//추가 입력분을 위해 순서번호를 조회하여 그다음 번호로 입력하게 함.
					int data_no = dao_graph.selectInfGrdGraphDataNum(pr_no)-1;
					//데이터 추출
					boolean isNull = false;
					//for (Row row : wb.getSheetAt(0)) {
					for (int r = 1; r < rows; r++) {
						// 시트에 대한 행을 하나씩 추출
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
								// 행에대한 셀을 하나씩 추출하여 셀 타입에 따라 처리
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
		/*//엑셀 데이터 추출
					POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(savePath));
					//워크북 생성
					HSSFWorkbook workbook = new HSSFWorkbook(fs);
					//시트이름과 시트 번호 추출
					HSSFSheet sheet = workbook.getSheetAt(0);
					// 시트에 대한 행을 하나씩 추출
					int rows = sheet.getPhysicalNumberOfRows();		
					
					DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
					//추가 입력분을 위해 순서번호를 조회하여 그다음 번호로 입력하게 함.
					int data_no = dao_graph.selectBasicGraphDataNum(pr_no)-1;
					//데이터 추출
					for (int r = 1; r < rows; r++) {
						// 시트에 대한 행을 하나씩 추출
						HSSFRow row = sheet.getRow(r);
						if (row != null) {
							int cells = row.getPhysicalNumberOfCells();						
							Vector v_list = new Vector();
							v_list.addElement(pr_no);						
							v_list.addElement(data_no + r);
							for (int c = 0; c < cells; c++) {
								// 행에대한 셀을 하나씩 추출하여 셀 타입에 따라 처리
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
											v_list.addElement("0");// 기본값"0"
											break;
									}								
								}
							}
							totalParam.add(v_list);
						}*/
	}
	
	/**
	 * @MethodName : insertGraphDataInfo
	 * @Desc : 그래프 정보를 입력하는 메소드(엑셀파일이 들어오면 엑셀파일내용을 업로드)
	 * @param request
	 * @param context
	 * @return
	 */
	public boolean insertGraphDataInfo(HttpServletRequest request) throws IOException{
		boolean value = false;
		//첨부 파일이 없을 경우
		
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();

		try{
			//물성정보 번호
			String pr_no = request.getParameter("pr_no");
			//데이터 번호
			String data_no = String.valueOf(dao_graph.selectBasicGraphDataNum(pr_no));
			//X값
			String x_val = request.getParameter("x_val");
			//Y값
			String y_val = request.getParameter("y_val");
			//X축 오차값
			String x_err = ComUtil.isNullToZeroString(request.getParameter("x_err"));
			//Y축 최대오차값
			String y_err_max = ComUtil.isNullToZeroString(request.getParameter("y_err_max"));
			//Y축 최소오차값
			String y_err_min = ComUtil.isNullToZeroString(request.getParameter("y_err_min"));
			//생성비
			String ratio = ComUtil.isNullToZeroString(request.getParameter("ratio"));
			//압력
			String press = ComUtil.isNullToZeroString(request.getParameter("press"));
			//백업데이터
			String back_data = ComUtil.isNullToEmptyString(request.getParameter("back_data"));
			//X환산
			double xax_cal = Double.parseDouble(request.getParameter("xax_cal"));
			//Y환산
			double xay_cal = Double.parseDouble(request.getParameter("xay_cal"));
			//최종 X값
			double t_x_val = Double.parseDouble(x_val) * xax_cal;
			//최종 Y값
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
	 * @MethodDescription : 플라즈마물성데이터의 수치 개수를 기본테이블에 입력하는 메소드
	 * @param st_pr_no
	 * @param ed_pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean updateGraphDataTotalCountInfo(String st_pr_no, String ed_pr_no){ 
		boolean value = false;
		//첨부 파일이 없을 경우
		
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
		
		//카운트 가져오기
		Vector<?> info = dao_graph.selectGraphDataTotalCount(st_pr_no, ed_pr_no);
		
		//가져온 카운트를 데이터 베이스에 다시 입력하기
		for(int i = 0 ; i < info.size(); i++) {
			Common_Data data = (Common_Data)info.elementAt(i);
			
			String pr_no = data.getPL_CM_STR1(); //물성번호
			String pr_cnt = data.getPL_CM_STR2(); //카운트
			
			//System.out.println("물성번호 : " + pr_no);
			//System.out.println("수치개수 : " + pr_cnt);
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
	 * @MethodDescription : 해당 위치의 그래프 데이터를 업데이트 하는 메소드 
	 * @param request
	 * @return
	 * @throws IOException
	 * @History  : - 
	 */
	public boolean updateGraphDataInfo(HttpServletRequest request) throws IOException{
		boolean value = false;
		
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();

		try{
			//물성정보 번호
			String pr_no = request.getParameter("pr_no");
			//데이터 번호
			String data_no = request.getParameter("dt_no");//String.valueOf(dao_graph.selectBasicGraphDataNum());
			//X값
			String x_val = request.getParameter("x_val");
			//Y값
			String y_val = request.getParameter("y_val");
			//X축 오차값
			String x_err = ComUtil.isNullToEmptyString(request.getParameter("x_err"));
			//Y축 최대오차값
			String y_err_max = ComUtil.isNullToEmptyString(request.getParameter("y_err_max"));
			//Y축 최소오차값
			String y_err_min = ComUtil.isNullToEmptyString(request.getParameter("y_err_min"));
			//생성비
			String ratio = ComUtil.isNullToEmptyString(request.getParameter("ratio"));
			//압력
			String press = ComUtil.isNullToEmptyString(request.getParameter("press"));
			//백업데이터
			String back_data = ComUtil.isNullToEmptyString(request.getParameter("back_data"));
			//X환산
			double xax_cal = Double.parseDouble(request.getParameter("xax_cal"));
			//Y환산
			double xay_cal = Double.parseDouble(request.getParameter("xay_cal"));
			//최종 X값
			double t_x_val = Double.parseDouble(x_val) * xax_cal;
			//최종 Y값
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
	 * @Desc : 그래프 데이터를 가져오는 메소드
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
			String num_file = writeGraphBasicData(pr_no, v_list); //수치 파일 저장
			
			String user_id = "test_user";
			String conf_file = writeGnuplotSingleConf(pr_no, user_id, num_file); //옵션에 따른 gnuplot conf 저장
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return v_list;
	}
	
	/**
	 * @MethodName  : selectGraphDataInXSAMS
	 * @Date   : 2014. 5. 14. 
	 * @MethodDescription : XSAMS 파일 생성에 필요한 X값, Y값 수치를 받아가는 메소드
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
			String num_file = writeGraphBasicData(pr_no, v_list); //수치 파일 저장
			
			String user_id = "test_user";
			String conf_file = writeGnuplotSingleConf(pr_no, user_id, num_file); //옵션에 따른 gnuplot conf 저장
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return v_list;
	}*/
	
	/**
	 * @MethodName  : drawSingleGraphData
	 * @Date   : 2012. 12. 11. 
	 * @MethodDescription : 일반 조회시 물성정보 1개 일때 그래프를 그리는 메소드
	 * @param pr_no
	 * @param user_id
	 * @return
	 * @History  : - 
	 */
	/*public String drawSingleGraphData(String pr_no){
		String r_value = ""; //결과 값으로, 파일명을 준다.
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
	 * @MethodDescription : 조회시 물성정보 여러개 일때 그래프를 그리는 메소드
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	/*public String drawMultiGraphData(String pr_no){
		String r_value = ""; //결과 값으로, 파일명을 준다.
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
	 * @MethodDescription : gnuplot 을 실행하기 위한 수치 데이터가 저장되는 파일을 만드는 메소드
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
			System.out.println("잘못된 파일이름을 입력했습니다.");
		}
		
		return strFileGenLoc;
	}
	
	/**
	 * @MethodName  : writeGnuplotSingleConf
	 * @Date   : 2012. 12. 11. 
	 * @MethodDescription : 물성정보가 1개 일때 gnuplot conf 만드는 메소드
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
			//그래프 파일유형 지정
			bw.write("set term png "+"\r\n");
			//그래프 그리드 지정
			bw.write("set grid "+"\r\n");
			//그래프 로그스케일 지정
			bw.write("set logscale "+"\r\n");		
			//그래프 제목 지정
			bw.write("set title 'Plasma Properties Graph' "+"\r\n");
			//그래프 X축 제목 지정
			bw.write("set xlabel '" +x_unit+ "' \r\n");
			//그래프 Y축 제목 지정 
			bw.write("set ylabel '" +y_unit+ "' \r\n");
			//그래프 출력위치 지정 
			bw.write("set output '" +strGraphFileGenLoc+ "' \r\n");
			//plot 부분
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
			System.out.println("잘못된 파일이름을 입력했습니다.");
		}
		
		try{
			
				callShellCommand("gnuplot "+strConfFileGenLoc);
				callShellCommand("rm "+num_file);
				callShellCommand("rm "+strConfFileGenLoc);
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("명령어 실행에 실패했습니다.");
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
		      //자바 1.4 이하에서는 이렇게
		    Runtime runtime = Runtime.getRuntime();
		    Process process = runtime.exec(cmd);
		    System.out.println(process.waitFor());
//		    Process oProcess = oRuntime.exec("cmd /c dir /?");
		    

		   /* // 자바 1.5 이상에서는 이렇게 1줄로
		    //Process oProcess = new ProcessBuilder("gnuplot", "+strConfFileGenLoc").start();

		    // 외부 프로그램 출력 읽기
		    BufferedReader stdOut   = new BufferedReader(new InputStreamReader(oProcess.getInputStream()));
		    BufferedReader stdError = new BufferedReader(new InputStreamReader(oProcess.getErrorStream()));

		    // "표준 출력"과 "표준 에러 출력"을 출력
		    while ((s =   stdOut.readLine()) != null) System.out.println(s);
		    while ((s = stdError.readLine()) != null) System.err.println(s);

		    // 외부 프로그램 반환값 출력 (이 부분은 필수가 아님)
		    System.out.println("Exit Code: " + oProcess.exitValue());
		    System.exit(oProcess.exitValue()); // 외부 프로그램의 반환값을, 이 자바 프로그램 자체의 반환값으로 삼기
*/
		  } catch (IOException e) { // 에러 처리
		      System.err.println("에러! 외부 명령 실행에 실패했습니다.\n" + e.getMessage());
		      System.exit(-1);
		  }

	}
	
	/**
	 * @MethodName  : writeGnuplotMultiConf
	 * @Date   : 2012. 12. 11. 
	 * @MethodDescription : 물성정보가 여러개 일때 gnuplot conf 만드는 메소드
	 * @param p_list : 물성정보가 들어있는 리스트
	 * @param user_id : 사용자 ID
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
			//그래프 파일유형 지정
			bw.write("set term png "+"\r\n");
			//그래프 그리드 지정
			bw.write("set grid "+"\r\n");
			//그래프 로그스케일 지정
			bw.write("set logscale "+"\r\n");		
			//그래프 제목 지정
			bw.write("set title 'Plasma Properties Graph' "+"\r\n");
			//그래프 X축 제목 지정
			bw.write("set xlabel '" +x_unit+ "' \r\n");
			//그래프 Y축 제목 지정 
			bw.write("set ylabel '" +y_unit+ "' \r\n");
			//그래프 출력위치 지정 
			bw.write("set output '" +strGraphFileGenLoc+ "' \r\n");
			//plot 부분
			// 여기에 수치파일 리스트를 만들어야 함.
			//
			bw.write("\r\n");
					
			bw.flush();
			bw.close();
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
			System.out.println("잘못된 파일이름을 입력했습니다.");
		}
	}	
	
	
	/**
	 * @MethodName  : selectGraphBasicData
	 * @Date   : 2010. 04. 12 
	 * @MethodDescription : 물성정보번호와 순서 번호를 통해서 그래프 정보를 가져오는 메소드
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
	 * @Desc : 그래프 기본 정보를 가져오는 메소드
	 * @param pr_no
	 * @return
	 */
	public Graph_Basic_Info selectGraphBasicInfo(String pr_no){
	
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
		
		return dao_graph.selectBasicGraphInfo(pr_no);
	}
	
	/**
	 * @MethodName : selectViewGraphBasicInfo
	 * @Desc : 그래프 기본 정보 확인 페이지에 데이터를 가져오는 메소드
	 * @param pr_no
	 * @return
	 */
	public Graph_Basic_Info selectViewGraphBasicInfo(String pr_no){
		DAO_BASIC_GRAPH_INFO dao_graph = new DAO_BASIC_GRAPH_INFO();
		
		Graph_Basic_Info info = dao_graph.selectBasicGraphInfo(pr_no);
		
		Ctr_Option_Process ctr_option = new Ctr_Option_Process();
		//X단위
		info.setPL_BGI_X_AX_UNIT(ctr_option.getExpName(info.getPL_BGI_X_AX_UNIT()));
		//Y단위
		info.setPL_BGI_Y_AX_UNIT(ctr_option.getExpName(info.getPL_BGI_Y_AX_UNIT()));
		
		return info;
	}
	
	/**
	 * @MethodName  : selectViewInfGrdGraphInfo
	 * @Date   : 2010. 09. 15 
	 * @MethodDescription : 등급유력 그래프 기본 정보 확인 페이지에 데이터를 가져오는 메소드
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public Inf_Grd_Graph_Basic_Info selectViewInfGrdGraphInfo(String pr_no){
		DAO_INF_GRD_GRAPH_INFO dao_graph = new DAO_INF_GRD_GRAPH_INFO();
		
		Inf_Grd_Graph_Basic_Info info = dao_graph.selectInfGrdGraphInfo(pr_no);
		
		Ctr_Option_Process ctr_option = new Ctr_Option_Process();
		//X단위
		info.setPL_IGGI_X_AX_UNIT(ctr_option.getExpName(info.getPL_IGGI_X_AX_UNIT()));
		//Y단위
		info.setPL_IGGI_Y_AX_UNIT(ctr_option.getExpName(info.getPL_IGGI_Y_AX_UNIT()));
		
		return info;
	}
	
	/**
	 * @MethodName  : selectViewInfGrdGraphData
	 * @Date   : 2010. 09. 15 
	 * @MethodDescription : 등급유력 그래프 데이터 확인 페이지에 데이터를 가져오는 메소드
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
	 * @Desc : 그래프 기본 정보 개수를 가져오는 메소드
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
	 * @MethodDescription : 그래프 수치 정보 삭제하는 메소드
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
	 * @MethodDescription : 소수점 정보 수정을 위한 메소드
	 * @return
	 * @History  : - 
	 */
	public Vector<String> selectModifyNumberList(String st_no, String ed_no){
		Vector<?> v_list = new Vector<Object>();
		
		//v_list에 소수점 자리 수정해야 할 놈들만 담아가기.
		
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
	 * @MethodDescription : 그래프 수치의 소수점 자리 수정(.XXX -> 0.XXX)하는 메소드
	 * @param pr_no
	 * @return
	 * @History  : - 
	 */
	public boolean modifyGraphPointData(String pr_no){
		boolean r_value = false;
		
		//물성정보 전체 가져오기
		
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
