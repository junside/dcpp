/**
 * 
 */
package nfri.dcpp.properties.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import nfri.dcpp.com.util.ComUtil;
import nfri.dcpp.com.util.ComVar;
import nfri.dcpp.properties.db.DAO_XSAMS_INFO;
import nfri.dcpp.properties.model.Part_Inchikey_Info;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.oreilly.servlet.MultipartRequest;

/**
 * @Project: dcpp
 * @Title  : Ctr_XSAMS_Process.java
 * @Description : XSAMS와 관련하여 작업되는 컨트롤 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2014. 5. 13.
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class Ctr_XSAMS_Process {

	public Ctr_XSAMS_Process(){
		
	}	
	
	/**
	 * @MethodName  : insertInchikeyExcelData
	 * @Date   : 2014. 5. 13. 
	 * @MethodDescription : Inchikey 데이터(Excel 형태)를 파일로 DB에 넣는 메소드
	 * @param multi
	 * @param contextpath
	 * @return
	 * @History  : - 
	 */
	public boolean insertInchikeyExcelData(MultipartRequest multi, String contextpath){
		boolean value = false;			
		
		try{						
			//전송할 파일 정보를 가져와 출력
			Enumeration<?> files = multi.getFileNames();
		
			//String pr_no = multi.getParameter("pr_no");
			
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
					
					DAO_XSAMS_INFO dao_xsams = new DAO_XSAMS_INFO();
					//추가 입력분을 위해 순서번호를 조회하여 그다음 번호로 입력하게 함.
					//int data_no = 0;//dao_graph.selectBasicGraphDataNum(pr_no)-1;					
					//데이터 추출
					boolean isNull = false;
					//for (Row row : wb.getSheetAt(0)) {
					for (int r = 1; r < rows; r++) {
						// 시트에 대한 행을 하나씩 추출
						Row row = sheet.getRow(r);
						if (row != null) {
							int cells = row.getLastCellNum();						
							Vector<String> v_list = new Vector<String>();
							//v_list.addElement(pr_no);						
							System.out.println(row);
							//int add_seq_num = data_no + r;
							//v_list.addElement(Integer.toString(add_seq_num));
							
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
									case Cell.CELL_TYPE_NUMERIC : //숫자도 무조건 텍스트 형태로 저장
										//System.out.println("CELL_TYPE_NUMERIC");
										Double a = cell.getNumericCellValue();										
										v_list.addElement(Integer.toString(a.intValue()));
										//if(DateUtil.isCellDateFormatted(cell)){
										//	v_list.addElement(cell.getDateCellValue().toString());//date
										//}else{
										//	v_list.addElement(Double.toString(cell.getNumericCellValue()));//double
										//}
										break;
									case Cell.CELL_TYPE_STRING :
										//System.out.println("CELL_TYPE_STRING");
										v_list.addElement(cell.getRichStringCellValue().getString()); //String
										break;
									case Cell.CELL_TYPE_BLANK :
										//System.out.println("CELL_TYPE_BLANK");
										v_list.addElement(ComVar.STRING_NULL_B);
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
					
					value = dao_xsams.insertInchiKeyExcelData(totalParam);
					
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
	 * @MethodName  : selectInchiKeyInfo
	 * @Date   : 2014. 6. 12. 
	 * @MethodDescription : Inchi Key를 가져오는 메소드
	 * @param ele_num
	 * @param chg_state
	 * @return
	 * @History  : - 
	 */
	public Part_Inchikey_Info selectInchiKeyInfo(String ele_num, String chg_state){
		
		Vector<String> sqlOption = new Vector<String>();
		sqlOption.addElement(ele_num);
		sqlOption.addElement(chg_state);
		
		DAO_XSAMS_INFO dao_xsams = new DAO_XSAMS_INFO();
		
		Part_Inchikey_Info info = dao_xsams.selectInchiKeyInfo(sqlOption);
		
		return info;
	}
	
	
}
