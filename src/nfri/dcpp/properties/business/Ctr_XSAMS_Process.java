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
 * @Description : XSAMS�� �����Ͽ� �۾��Ǵ� ��Ʈ�� Ŭ����
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
	 * @MethodDescription : Inchikey ������(Excel ����)�� ���Ϸ� DB�� �ִ� �޼ҵ�
	 * @param multi
	 * @param contextpath
	 * @return
	 * @History  : - 
	 */
	public boolean insertInchikeyExcelData(MultipartRequest multi, String contextpath){
		boolean value = false;			
		
		try{						
			//������ ���� ������ ������ ���
			Enumeration<?> files = multi.getFileNames();
		
			//String pr_no = multi.getParameter("pr_no");
			
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
					
					DAO_XSAMS_INFO dao_xsams = new DAO_XSAMS_INFO();
					//�߰� �Էº��� ���� ������ȣ�� ��ȸ�Ͽ� �״��� ��ȣ�� �Է��ϰ� ��.
					//int data_no = 0;//dao_graph.selectBasicGraphDataNum(pr_no)-1;					
					//������ ����
					boolean isNull = false;
					//for (Row row : wb.getSheetAt(0)) {
					for (int r = 1; r < rows; r++) {
						// ��Ʈ�� ���� ���� �ϳ��� ����
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
								// �࿡���� ���� �ϳ��� �����Ͽ� �� Ÿ�Կ� ���� ó��
								Cell cell = row.getCell(c);							
								if (cell != null) {								
									switch (cell.getCellType()) {
									case Cell.CELL_TYPE_FORMULA :
										//System.out.println("CELL_TYPE_FORMULA");
										v_list.addElement(cell.getCellFormula());
										break;
									case Cell.CELL_TYPE_NUMERIC : //���ڵ� ������ �ؽ�Ʈ ���·� ����
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
	 * @MethodDescription : Inchi Key�� �������� �޼ҵ�
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
