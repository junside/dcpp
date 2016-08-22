package nfri.dcpp.com.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public abstract class ComUtil {
	
	/**
	 * @MethodName : convertBlob
	 * @Desc : 
	 * @param file
	 * @return
	 */
	/*public static BLOB convertBlob(File file){
		BLOB blob = null;
		//BLOB emptyBlob = null;
		FileInputStream filein = null;
		OutputStream fileout = null;
		
		try {
			filein = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return blob;
	}*/
	
	/**
	 * @MethodName : convertKorean
	 * @Desc : 한글처리를 위한 메소드
	 * @param str
	 * @return
	 */
	public static String convertKorean(String str){
		try {
			return new String(str.getBytes("ISO-8859-1"), "euc_kr");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * @MethodName  : callShellCommand
	 * @Date   : 2012. 11. 5. 
	 * @MethodDescription : 리눅스 쉘 명령어 실행하기
	 * @throws Exception
	 * @History  : - 
	 */
	public static void callShellCommand() throws Exception {
		String command = "execute command";
		java.lang.Runtime runTime = java.lang.Runtime.getRuntime();
		java.lang.Process process = runTime.exec(command);           
		System.out.println(process.waitFor());
	}
	
	/**
	 * @MethodName  : makeLikeOption
	 * @Date   : 2010. 1. 12. 
	 * @MethodDescription : -
	 * @param option
	 * @return
	 * @History  : - 
	 */
	public static String makeLikeOption(String option){
		String returnStr = "UPPER('%" + option + "%')";
		return returnStr;
	}
	
	/**
	 * @MethodName  : makeEqualOption
	 * @Date   : 2010. 1. 12. 
	 * @MethodDescription : -
	 * @param option
	 * @return
	 * @History  : - 
	 */
	public static String makeEqualOption(String option){
		String returnStr = "'" + option + "'";
		return returnStr;
	}
	
	/**
	 * @MethodName  : makeEquaLikeOption
	 * @Date   : 2010. 1. 12. 
	 * @MethodDescription : -
	 * @param option
	 * @return
	 * @History  : - 
	 */
	public static String makeEquaLikeOption(String option){
		String returnStr = "('%" + option + "%')";
		return returnStr;
	}
	
	/**
	 * @MethodName  : splitString
	 * @Date   : 2012. 1. 27. 
	 * @MethodDescription : 스트링을 특정 문자로 잘라 배열로 만드는 메소드
	 * @param option
	 * @param ch
	 * @return
	 * @History  : - 
	 */
	public static String[] splitString(String option, String ch){
		String[] r_value = option.split(ch);
		return r_value;
	}
	
	/**
	 * @MethodName : getAlphabetList
	 * @Desc : 카운트에 따라서 알파벳을 짜맞추어 리턴하는 메소드
	 * @param count
	 * @return
	 */
	public static String getAlphabetList(int count){
		String re_value = "";
		String[] str_sta_alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		String[] str_mid_alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		String[] str_last_alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		
		int alphabetcount = 26;
		int mok = count / alphabetcount;	//몫	
		int namuge = count % alphabetcount; //나머지
		
		//System.out.println("mok : " + mok);
		
		if(count < alphabetcount){ //개수가 알파벳 개수보다 작을 때
			String start_str = str_sta_alphabet[count];
			re_value = start_str;
		}else if(mok > 25){ //개수가 702개(26*26)+26 보다 클 때
			int mok2 = mok / 26; //첫번째 자리
			int namuge2 = mok % 26; //나머지 -> 중간 자리
			//mok = mok-26;
			String start_str = str_sta_alphabet[mok2-1];			
			String mid_str = str_mid_alphabet[namuge2];
			String last_str = str_last_alphabet[namuge];
			re_value = start_str+ mid_str+ last_str;
			
			//String start_str = str_alphabet[mok-1];	
			//re_value = start_str+str_alphabet[namuge];
		}else{	//그 외 aa~zz
			String start_str = str_sta_alphabet[mok-1];	
			String last_str= str_last_alphabet[namuge];
			re_value = start_str+last_str;
		}
		
		/*if(count >= 26 && count <= 52){
			re_value = "a"+str_alphabet[count-26];
		}else if(count > 52){
			re_value = "b"+str_alphabet[count-52];
		} else {
			re_value = str_alphabet[count];
		}*/
		
		return re_value;		
	}
	
	/**
	 * @MethodName : getExtension
	 * @Desc : 파일 확장자를 구하는 메소드
	 * @param filename
	 * @return
	 */
	public static String getExtension(String filename){
		try {
			return filename.substring(filename.lastIndexOf('.') + 1);
		} catch(Exception e) {}
		return ComVar.STRING_EMPTY;
	}
	
	/**
	 * @MethodName : getFileExtension
	 * @Desc : 파일 확장자를 구하는 메소드
	 * @param file
	 * @return
	 */
	public static String getFileExtension(File file){
		try {
			String fileName = file.getName();
			return fileName.substring(fileName.lastIndexOf('.') + 1);
		} catch(Exception e) {}
		return ComVar.STRING_EMPTY;
	}

    /**
     * @MethodName : moveFile
     * @Desc : 파일의 디렉토리를 이동
     * @param file
     * @param newDirectory
     * @return 성공하면 true, 실패하면 false
     * @throws IOException
     * @throws SecurityException
     */
    public static boolean moveFile(File file, String newDirectory) throws IOException, SecurityException {
        return moveFile(file, newDirectory, file.getName());
    }

    /**
     * @MethodName : moveFile
     * @Desc : 파일이름을 바꾸거나, 디렉토리를 이동
     * @param file 파일.
     * @param newPath 새로운 디렉토리.
     * @param newFileName 새로운 파일 이름
     * @return 성공하면 true, 실패하면 false
     * @throws SecurityException
     * @throws IOException
     */
    public static boolean moveFile(File file, String newDirectory, String newFileName) throws SecurityException, IOException {
        File pathDir = new File(newDirectory);
        pathDir.mkdirs();
        File newFile = new File(pathDir, newFileName);
        return file.renameTo(newFile);       // 이동 결과를 반환한다.
    }
    
    /**
     * @MethodName : moveFile
     * @Desc : 파일이름을 바꾸거나, 디렉토리를 이동
     * @param file 파일.
     * @param newPath 새로운 디렉토리.
     * @param newFileName 새로운 파일 이름
     * @param ext 새로운 파일 확장자
     * @return 성공하면 true, 실패하면 false
     * @throws SecurityException
     * @throws IOException
     */
    public static boolean moveFile(File file, String newDirectory, String newFileName, String ext) throws SecurityException, IOException {
        File pathDir = new File(newDirectory);
        pathDir.mkdirs();
        File newFile = new File(pathDir, newFileName+"."+ext);
        return file.renameTo(newFile);       // 이동 결과를 반환한다.
    }
    
    public static boolean moveExcelFile(File file, String newDirectory, String newFileName) throws SecurityException, IOException {
        File pathDir = new File(newDirectory);
        pathDir.mkdirs();
        File newFile = new File(pathDir, newFileName);
        return file.renameTo(newFile);       // 이동 결과를 반환한다.
    }
    
    /**
     * @MethodName  : deleteExcelFile
     * @Date   : 2014. 5. 20. 
     * @MethodDescription : 업로드 된 엑셀 파일 삭제하기
     * @param file
     * @param newDirectory
     * @param newFileName
     * @return
     * @throws SecurityException
     * @throws IOException
     * @History  : - 
     */
    public static boolean deleteExcelFile(File file, String newDirectory, String newFileName) throws SecurityException, IOException {
        boolean flag = false;
    	File pathDir = new File(newDirectory);
        //pathDir.mkdirs();
        File newFile = new File(pathDir, newFileName);
        if(newFile.exists()==true)
        {
        	flag = newFile.delete();
        }
        return flag;       // 이동 결과를 반환한다.
    }
    
    /**
     * @MethodName  : removeFile
     * @Date   : 2014. 5. 20. 
     * @MethodDescription : 파일 삭제하는 메소드
     * @param newDirectory
     * @param newFileName
     * @return
     * @throws SecurityException
     * @throws IOException
     * @History  : - 
     */
    public static boolean removeFile(String newDirectory, String newFileName) throws SecurityException, IOException {
        boolean flag = false;
    	File pathDir = new File(newDirectory);
        
        File newFile = new File(pathDir, newFileName);
        if(newFile.exists()==true)
        {
        	flag = newFile.delete();
        }
        return flag;       // 이동 결과를 반환한다.
    }
    
    /**
     * @MethodName : getFileNameWithoutExtension
     * @Desc : 확장자를 제외한 파일 이름을 얻는다.
     * @param file
     * @return 확장자를 제외한 파일 이름
     */
    public static String getFileNameWithoutExtension(File file) {
        try {
            String fileName = file.getName();
            return fileName.substring(0, fileName.lastIndexOf('.'));
        } catch(Exception e) {};
        return ComVar.STRING_EMPTY;
    }
	
	/**
	 * @MethodName : getTimeNow
	 * @Desc : 현재시간 구하기
	 * @return
	 */
	public static String getTimeNow(){
		try{
			Date nowTime = new Date();	
			return convertDateToString(nowTime);
		}catch(Exception e){};
		
		return ComVar.STRING_EMPTY;
	}
	
	/**
	 * @MethodName : convertDateToString
	 * @Desc : Date 형태를 yyyy-MM-dd HH:mm:ss 형태의 String으로 변환
	 * @param date
	 * @return
	 */
	public static String convertDateToString(Date date){
		try{
			SimpleDateFormat date_form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return date_form.format(date);
		}catch(Exception e){};
		
		return ComVar.STRING_EMPTY;
	}
	
	/**
	 * @MethodName : convertStringToDate
	 * @Desc : yyyy-MM-dd HH:mm:ss 형태의 String을 Date로 변환
	 * @param date
	 * @return
	 */
	public static Date convertStringToDate(String date){
		Date new_date = new Date();	
		
		try {					
			SimpleDateFormat date_form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			new_date = date_form.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		return new_date;		
	}
	
	/**
	 * @MethodName : getYearInDate
	 * @Desc : 현재 년도 짤라 내기
	 * @param date
	 * @return
	 */
	public static String getYearInDate(String date){
		return date.substring(0, 4);
	}
	
	/**
	 * @MethodName  : getYearMonthDayInDate
	 * @Date   : 2011. 3. 22. 
	 * @MethodDescription : 년 월 일 짤라내기
	 * @param date
	 * @return
	 * @History  : - 
	 */
	public static String getYearMonthDayInDate(String date){
		return date.substring(0, 10);
	}
	
	/**
	 * @MethodName  : getBetweenDateCount
	 * @Date   : 2011. 4. 5. 
	 * @MethodDescription : 현재 날짜로 부터 예전 날짜 까지 카운트 구하기
	 * @param date
	 * @return
	 * @History  : - 
	 */
	public static int getBetweenDateCount(String date){
		//int returnValue = 0;
		
		Date old_date = new Date();
		SimpleDateFormat date_form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			old_date = date_form.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long beforetime = old_date.getTime();
		Date now = new Date();	
		long nowTime = now.getTime();
		
		Date today = new Date ( );
		Calendar cal = Calendar.getInstance ( );
		cal.setTimeInMillis (nowTime);// 오늘로 설정. 

		Calendar cal2 = Calendar.getInstance ( );
		cal2.setTimeInMillis (beforetime); // 기준일로 설정

		int count = 0;
		while ( !cal2.after ( cal ) )
		{
		count++;
		cal2.add ( Calendar.DATE, 1 ); // 다음날로 바뀜

		//System.out.println ( cal2.get ( Calendar.YEAR ) + "년 " + ( cal2.get ( Calendar.MONTH ) + 1 ) + "월 " + cal2.get ( Calendar.DATE ) + "일" );
		}
		
		/*Date old_date = new Date();	
		
		Date now = new Date();	
		
		try {					
			SimpleDateFormat date_form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			old_date = date_form.parse(date);
			long beforetime = old_date.getTime();
			long nowTime = now.getTime();
			long count = nowTime - beforetime;
			
			Calendar cal = Calendar.getInstance ( );
			cal.setTimeInMillis( count );

			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return count;
	}
	
	/**
	 * @MethodName : getOperationStr
	 * @Desc : 연산자 코드에 따른 연산자 String 값을 리턴
	 * @param oper
	 * @return
	 */
	public static String getOperationStr(String oper){
		if(oper.equalsIgnoreCase("SO_01"))
		{
			return ComVar.OP_AND;
		}else if(oper.equalsIgnoreCase("SO_02"))
		{
			return ComVar.OP_OR;
		}
		else{
			return ComVar.MSG_ERROR;
		}
	}
	
	
	/**
	 * @MethodName  : isStringDouble
	 * @Date   : 2014. 10. 27. 
	 * @MethodDescription : 문자를 숫자로 변환이 가능한지 여부 (10진수만 가능)
	 * @param s
	 * @return
	 * @History  : - 
	 */
	public static boolean isStringDouble(String s) {
	    try {
	        Double.parseDouble(s);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	  }
	
	/**
	 * @MethodName : isNull
	 * @Desc : String 값의 NULL값 여부를 체크
	 * @param str
	 * @return null 이면 True || 아니면 False 
	 */
	public static boolean isNull(String str){
		return str == null || str.length() == 0;
	}
	
	public static boolean isNullArray(String[] str){
		return str == null || str.length == 0;
	}
	
	/**
	 * @MethodName : isNullToEmptyString
	 * @Desc : String 값의 NULL값 여부를 체크하여 NULL이면 ""값으로 리턴
	 * @param str
	 * @return String
	 */
	public static String isNullToEmptyString(String str) {
        if(str == null || str.length() == 0) return ComVar.STRING_EMPTY;
        return str;
    }
	
	/**
	 * @MethodName  : isNullToAscString
	 * @Date   : 2011. 5. 16. 
	 * @MethodDescription : String 값의 NULL값 여부를 체크하여 NULL이면 "asc"값으로 리턴
	 * @param str
	 * @return
	 * @History  : - 
	 */
	public static String isNullToAscString(String str) {
        if(str == null || str.length() == 0) return ComVar.STRING_ASC;
        return str;
    }
	
	/**
	 * @MethodName : isNullStringToEmptyString
	 * @Desc : String 값이 "NULL" 또는 "null" 인지 여부를 체크하여 ""값으로 리턴
	 * @param str
	 * @return
	 */
	public static String isNullStringToEmptyString(String str) {
        if(str.equalsIgnoreCase(ComVar.STRING_NULL_B) || str.equalsIgnoreCase(ComVar.STRING_NULL_B)) return ComVar.STRING_EMPTY;
        return str;
    }
	
	/**
	 * @MethodName : isNullToDashString
	 * @Desc : String 값의 NULL값 여부를 체크하여 NULL이면 "-"값으로 리턴
	 * @param str
	 * @return
	 */
	public static String isNullToDashString(String str) {
		if(str == null || str.length() == 0) return ComVar.STRING_DASH;
        return str;
	}
	
	/**
	 * @MethodName : isNullToDashString
	 * @Desc : String 값의 NULL값 여부를 체크하여 NULL이면 "-"값으로 리턴
	 * @param str
	 * @return
	 */
	public static String isNullToZeroString(String str) {
		if(str == null || str.length() == 0) return ComVar.STRING_ZERO;
        return str;
	}
	
	/**
	 * @MethodName : isNullToNullString
	 * @Desc : String 값의 NULL값 여부를 체크하여 NULL이면 String "NULL"값으로 리턴
	 * @param str
	 * @return String
	 */
	public static String isNullToNullString(String str) {
        if(str == null || str.length() == 0) return ComVar.STRING_NULL_B;
        return str;
    }
	
	/**
	 * @MethodName : inNullToIntZero
	 * @Desc : String 값의 NULL값 여부를 체크하여 NULL이면 int "0"값으로 리턴
	 * @param str
	 * @return
	 */
	public static int isNullToIntZero(String str) {
		if(str == null || str.length() == 0) return ComVar.NULLINT;
        return Integer.parseInt(str);
	}
	
	/**
	 * @MethodName : inNullToDoubleZero
	 * @Desc : String 값의 NULL값 여부를 체크하여 NULL이면 double "0"값으로 리턴
	 * @param str
	 * @return
	 */
	public static double isNullToDoubleZero(String str) {
		if(str == null || str.length() == 0) return ComVar.NULLDOUBLE;
        return Double.parseDouble(str);
	}
	
	/**
	 * @MethodName : getAbbrString
	 * @Desc : 넘어온 String 값을 특정 자리수만 남기고 이후는 ... 로 표시하는 메소드
	 * @param str
	 * @param len
	 * @return
	 */
	public static String getAbbrString(String str, int len)
    {
        if (str == null)
        {
            return "";
        }
        if ((str.length() <= len) || (len <= 0))
        {
            return str;
        }
        StringBuffer new_str = new StringBuffer(str.substring(0, len)); //설정된 길이만큼 붙이고, 나머지는 .. 으로 처리
        new_str.append("..");
        return new_str.toString();
    }

	/**
	 * @MethodName : getStringRight
	 * @Desc : 넘어온 String 값을 오른쪽에서부터 특정 자리수만 얻어오는 메소드
	 * @param str
	 * @param len
	 * @return
	 */
	public static String getStringRight(String str, int len)
	{
		if (str == null)
		{
			return "";
		}
		if ((str.length() <= len) || (len <= 0))
		{
			return str;
		}

		return str.substring(str.length()-len, str.length());
	}
	/**
	 * @MethodName : getStringLeft
	 * @Desc : 넘어온 String 값을 왼쪽에서부터 특정 자리수만 얻어오는 메소드
	 * @param str
	 * @param len
	 * @return
	 */
	public static String getStringLeft(String str, int len)
	{
		if (str == null)
		{
			return "";
		}
		if ((str.length() <= len) || (len <= 0))
		{
			return str;
		}
		return str.substring(0, len);
	}

	/**
	 * @MethodName : getSeparatedText
	 * @Desc : String에 " "(공백)에 의해 연속적으로 값이 들어가 있다면
	 * 이 값을 하나씩 짤라 내어 String을 가공하는 메소드
	 * @param text
	 * @return
	 */
	public static String[] getSeparatedText(String text)
	{
		StringTokenizer st = new StringTokenizer(text);
		int count = st.countTokens();
		String[] returnV = new String[count];
		int i = 0;
		while(st.hasMoreElements())
		{
			returnV[i] = (String)st.nextElement(); 
			//System.out.println("returnV[" + i + "] : " + returnV[i]);
			i++;
		}
		return returnV;
	}

	
	/**
	 * @MethodName : getFileImgName
	 * @Desc : 주어진 확장자에 따른 이미지파일 명을 리턴
	 * @param ext
	 * @return
	 */
	public static String getFileImgName(String ext){
		//리턴값
		String ext_value = ext.trim().toUpperCase();
		
		//이미지 파일 리스트
		String doc_img = "doc.jpg";
		String xls_img = "xls.jpg";
		String ppt_img = "ppt.jpg";
		String hwp_img = "hwp.jpg";
		String pdf_img = "pdf.jpg";
		String img_img = "pic.jpg";
		//String gif_img = "gif.jpg";
		//String bmp_img = "bmp.jpg";
		String txt_img = "txt.jpg";
		String file_img = "file.jpg";
		
		if(ext_value.endsWith(ComVar.EXT_DOC) || ext_value.endsWith(ComVar.EXT_DOCX)){
			return doc_img;
		}else if(ext_value.endsWith(ComVar.EXT_XLS) || ext_value.endsWith(ComVar.EXT_XLSX)){
			return xls_img;
		}else if(ext_value.endsWith(ComVar.EXT_PPT) || ext_value.endsWith(ComVar.EXT_PPTX)){
			return ppt_img;
		}else if(ext_value.endsWith(ComVar.EXT_HWP)){
			return hwp_img;
		}else if(ext_value.endsWith(ComVar.EXT_PDF)){
			return pdf_img;
		}else if(ext_value.endsWith(ComVar.EXT_JPG)){
			return img_img;
		}else if(ext_value.endsWith(ComVar.EXT_GIF)){
			return img_img;
		}else if(ext_value.endsWith(ComVar.EXT_BMP)){
			return img_img;
		}else if(ext_value.endsWith(ComVar.EXT_TXT)){
			return txt_img;
		}else{
			return file_img;
		}
	}
	
	/**
	 * @MethodName  : getCellData
	 * @Date   : 2012. 3. 12. 
	 * @MethodDescription : -
	 * @param cell
	 * @return
	 * @History  : - 
	 */
	public static String getCellData(Cell cell){
		String r_value = "";
		boolean isNull = false;
		if (cell != null) {								
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_FORMULA :
				r_value = cell.getCellFormula().toString();
				break;
			case Cell.CELL_TYPE_NUMERIC :
				if(DateUtil.isCellDateFormatted(cell)){
					r_value = cell.getDateCellValue().toString();//date
				}else{
					r_value = Double.toString(cell.getNumericCellValue());//double
				}
				break;
			case Cell.CELL_TYPE_STRING :
				r_value = cell.getRichStringCellValue().getString(); //String
				break;
			case Cell.CELL_TYPE_BLANK :
				r_value = ComVar.STRING_EMPTY;
				break;
			case Cell.CELL_TYPE_BOOLEAN :
				r_value = String.valueOf(cell.getBooleanCellValue()); //boolean
				break;
			case Cell.CELL_TYPE_ERROR :
				r_value = String.valueOf(cell.getErrorCellValue());// byte
				break;
			default :
				 isNull = true;
			}								
		}
		
		
		return r_value;
	}
	
	/**
	 * @MethodName : getFileURL
	 * @Desc : 
	 * @param path
	 * @param name
	 * @return
	 */
	public static String getFileURL(String path, String name){
		return path + "/" + name;
	}
	
	/**
	 * @MethodName : getFileType
	 * @Desc : 파일 타입 리턴 메소드
	 * @param org_obj
	 * @return
	 */
	public static int getFileType(Object org_obj){
		int value = 0;
		if(org_obj instanceof String)
		{
			return ComVar.OBJ_STRING;
		}
		else if(org_obj instanceof Integer)
		{
			return ComVar.OBJ_INTEGER;
		}
		else if(org_obj instanceof Double)
		{
			return ComVar.OBJ_DOUBLE;
		}
		return value;
	}
	
	/**
	 * @MethodName : getSupString
	 * @Desc : 위첨자 표현하는 메소드
	 * @param sup
	 * @return
	 */
	public static String getSupString(String sup){
		String value = "";
		String f_sup = "<sup>";
		String e_sup = "</sup>";
		value = f_sup + sup + e_sup;		
		return value;
	}
	
	
	/**
	 * @MethodName : convertSupTag
	 * @Desc : 윗 첨자로 변경하는 메소드
	 * @param str
	 * @return
	 */
	public static String convertSupTag(String str){
		if (str == null)
        {
            return "";
        }
		
		int index = str.indexOf("^");
		//앞부분 받기
		String sub_front = str.substring(0,index);
		String sub_end = str.substring(index+1);
		String sub_total = sub_front;
		
		StringTokenizer st = new StringTokenizer(sub_end);
		int count = st.countTokens();
		String[] returnV = new String[count];
		int i = 0;
		while(st.hasMoreElements())
		{
			returnV[i] = (String)st.nextElement();
			if(i == 0){
				sub_total = sub_total + "<sup>" +returnV[i]+"</sup>";
			}else{
				sub_total = sub_total + " " + returnV[i];
			}
			i++;
		}
		
		return sub_total;
	}
	
	/**
	 * @MethodName  : convertATag
	 * @Date   : 2010. 09. 17 
	 * @MethodDescription : Atag 만드는 메소드
	 * @param str
	 * @return
	 * @History  : - 
	 */
	public static String convertATag(String str, String a_front){
		if (str == null)
        {
            return "";
        }
		String[] str_array = str.split(", ");	
		//String a_front = "<a href=\"javascript:void(0);\"  onClick=\"viewPropertyInfo('";
		//String a_front = "<a href=\"javascript:viewPropertyInfo(\"";
		String a_middle = "'); return false;\">";
		String a_end = "</a>";
		
		String a_total = "";
		/*
		 * <a href=\"javascript:viewPropertyInfo('<%=v_pr_no%>');\"><%=dt_list%></a>
		 * <a href=\"javascript:void(0);\"  onClick=\"viewPropertyInfo('<%=v_pr_no%>'); return false;\">
		 */
		int length = str_array.length;
		for(int i = 0; i < str_array.length; i++){
			//String pt_value = str_array[i];	
			if(i == length-1){
				a_total = a_total + a_front + str_array[i] + a_middle + str_array[i] + a_end;
			}else{
				a_total = a_total + a_front + str_array[i] + a_middle + str_array[i] + a_end + ", ";
			}
			
			//System.out.println("a_total : " + a_total);
		}
		
		return a_total;
	}
	
	/**
	 * @MethodName : getStringByDoubleFormat
	 * @Desc : String 형태의 Double값을 받아서 Double형태(수정)으로 변환하는 메소드
	 * @param num
	 * @return
	 */
	public static String getStringByDoubleFormat(String num){
		String value = "";
		DecimalFormat df = new DecimalFormat("0.####E0");
		double num_value = Double.parseDouble(num);
		value = df.format(num_value);
		return value;
	}

	/**
	 * @MethodName : getStringByDecimalFormat
	 * @Desc : String 형태의 Double값을 받아서 지수승으로 변환하는 메소드
	 * @param num
	 * @return
	 */
	public static String getStringByDecimalFormat(String num){
		String value = "";
		DecimalFormat df = new DecimalFormat("#######.######");
		double num_value = Double.parseDouble(num);
		value = df.format(num_value);
		return value;
	}
	
	/**
	 * @MethodName : getDecimalFormat
	 * @Desc : Double 형태의 값을 받아서 지수승 형태의 String 값으로 변환하는 메소드
	 * @param num
	 * @return
	 */
	public static String getDoubleToStringByFormat(Double num){
		String value = "";
		DecimalFormat df = new DecimalFormat("0.####E0");
		value = df.format(num);		
		return value;
	}
	
	public static double getDoubleData(String num){
		double num_value = Double.parseDouble(num);		
		return num_value;
	}
}
