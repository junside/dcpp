package nfri.dcpp.properties.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import nfri.dcpp.com.util.ComUtil;
import nfri.dcpp.com.util.ComVar;
import nfri.dcpp.properties.db.DAO_BASIC_GRAPH_INFO;
import nfri.dcpp.properties.db.DAO_COMSYS_INFO;
import nfri.dcpp.properties.db.DAO_REF_ARTICLE_INFO;
import nfri.dcpp.properties.model.Article_Info;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 *
 * @Project : dcpp_web
 * @Title : Ctr_Article_Process.java
 * @Description : 
 * @Author : J.H Park (JUNSiDE)
 * @Date : 2008. 11. 19
 * @Company : Data Center for Plasma Properties.
 *            NFRI.
 *
 * @변경이력
 * 	- 2009.02.18 : 참고 문헌 조회 기능 부분(조건에 따른) 추가
 *
 */
public class Ctr_Article_Process {
	
	//생성자
	public Ctr_Article_Process(){
			
	}	
	
	/**
	 * @MethodName : selectRefArticle
	 * @Desc : DB에서 참고문헌 전체를 가져오는 메소드
	 * @return : @return
	 */
	public Vector<?> selectRefArticleAll(){
		Vector<?> vecList = new Vector<Object>();
		
		DAO_REF_ARTICLE_INFO info = new DAO_REF_ARTICLE_INFO();
		vecList = info.selectArticleInfo();
		
		return vecList;
	}
	
	public Article_Info searchInfo(String artcle_no){
		Vector<String> v_option = new Vector<String>();
		v_option.addElement(artcle_no);
		
		DAO_REF_ARTICLE_INFO info = new DAO_REF_ARTICLE_INFO();
		
		return info.selectArticleInfo(v_option);
	}
	
	/**
	 * @MethodName : searchInfo
	 * @Desc : DB에서 조회 조건에 따라 참고문헌 내용을 가져오는 메소드
	 * @param request
	 * @return
	 */
	public Vector<?> searchInfo(HttpServletRequest request){
		Vector<?> artcl_list = new Vector<Object>();
		
		//변수 선언
		//SQL문을 담을 변수

		//첫번째 조건 : 논문 제목
		String op_artcl_title = ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("pl_ra_artcl_title")));
		//두번째 조건 : 논문 저자
		String op_artcl_author_f = ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("pl_ra_artcl_auth_fname")));
		//세번째 조건 : 논문 저자
		String op_artcl_author_e = ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("pl_ra_artcl_auth_ename")));
		//네번째 조건 : 논문 저널명
		String op_jn_title = ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("pl_ra_jour_title")));
		//다섯번째 조건 : 출판연도(From)
		String op_year_from = ComUtil.isNullToNullString(request.getParameter("publish_year_from"));
		//여섯번째 조건 : 출판연도(To)
		String op_year_to = ComUtil.isNullToNullString(request.getParameter("publish_year_to"));
		//일곱번째 조건 : 참고문헌 종류코드
		String op_jn_type = ComUtil.isNullToNullString(request.getParameter("pl_raci_code_id"));
		//연산자 조건
		String op_oper1 = ComUtil.getOperationStr(request.getParameter("option1_oper"));
		String op_oper2 = ComUtil.getOperationStr(request.getParameter("option2_oper"));
		String op_oper3 = ComUtil.getOperationStr(request.getParameter("option3_oper"));
		String op_oper4 = ComUtil.getOperationStr(request.getParameter("option4_oper"));
		String op_oper5 = ComUtil.getOperationStr(request.getParameter("option5_oper"));
		
		//Order 조건
		String op_odr_col = ComUtil.isNullToNullString(request.getParameter("_oCol"));
		String op_odr_dir = ComUtil.isNullToNullString(request.getParameter("_oDir"));
			
		
		//각 항목별 조건 데이터 유무에 따른 체크 필드
		String op_code = "N";
		
		//각 조건 항목별 분리된 조건 내용 담을 배열 선언
		String[] ar_artcl_title = ComUtil.getSeparatedText(op_artcl_title); 
		String[] ar_artcl_author_f = ComUtil.getSeparatedText(op_artcl_author_f); 
		String[] ar_artcl_author_e = ComUtil.getSeparatedText(op_artcl_author_e); 
		String[] ar_jn_title = ComUtil.getSeparatedText(op_jn_title); 
		
		/*
		 * SQL Query 변수 선언
		 */
		//전체 SQL문 저장 변수
		String optionquery = "";
				/*"SELECT PL_RAI_ARTCL_NUM, PL_RAI_ARTCL_TITLE," +
				" PL_RAI_ARTCL_AUTH, PL_RAI_JOUR_TITLE, PL_RAI_ARTCL_ST_PAGE," +
				" PL_RAI_JOUR_YEAR, PL_RAI_ORG_FILE_PATH, PL_RAI_ORG_FILE_NAME," +
				" PL_RAI_ORG_FILE_EXT FROM PL_REF_ARTICLE_INFO ";*/
		//SQL WHERE 저장 변수
		String sqlQueryWhere = " WHERE ";
		//SQL WHERE 저장 변수
		String sqlQueryOR = " OR ";
		//SQL Brace 저장 변수
		String sqlStartBrace = " (";
		String sqlEndBrace = ") ";
		//SQL End 기호 저장 변수
		//String sqlQueryOrderBy = " ORDER BY PL_RAI_ARTCL_NUM ";
		//SQL 첫번째 조건 : 논문 제목
		String sql_ar_title = " UPPER(PL_RAI_ARTCL_TITLE) LIKE "; 
		//SQL 두번째 조건 : 논문 저자
		String sql_ar_author_f = " UPPER(PL_RAI_ARTCL_AUTH_FNAME) LIKE ";
		//SQL 세번째 조건 : 논문 저자
		String sql_ar_author_e = " UPPER(PL_RAI_ARTCL_AUTH_ENAME) LIKE ";
		//SQL 네번째 조건 : 논문 저널명
		String sql_jn_title = " UPPER(PL_RAI_JOUR_TITLE) LIKE ";
		//SQL 다섯번째 조건 : 출판연도(From)
		String sql_year_from = " TO_NUMBER(PL_RAI_JOUR_YEAR) >= ";
		//SQL 여섯번째 조건 : 출판연도(To)
		String sql_year_to = " AND TO_NUMBER(PL_RAI_JOUR_YEAR) <= ";
		//SQL 일곱번째 조건 : 참고문헌 종류코드
		String sql_jn_type = " PL_RACI_CODE_ID = ";
		
		//첫번째 조건 : 논문 제목 Check
		if(!op_artcl_title.equalsIgnoreCase("NULL")){			
			optionquery = optionquery + sqlQueryWhere + sqlStartBrace;
			for(int i = 0; i < ar_artcl_title.length; i++){
				if(i == ar_artcl_title.length -1){
					optionquery = optionquery + sql_ar_title + ComUtil.makeLikeOption(ar_artcl_title[i]) + sqlEndBrace;	
				}else{
					optionquery = optionquery + sql_ar_title + ComUtil.makeLikeOption(ar_artcl_title[i]) + sqlQueryOR;
				}				
			}			
			op_code = "Y";
		}
		
		//두번째 조건 : 논문 저자 성 Check
		if(!op_artcl_author_f.equalsIgnoreCase("NULL")){
			if(op_code.equalsIgnoreCase("Y")){
				optionquery = optionquery + op_oper1 + sqlStartBrace;
			}else{
				optionquery = optionquery + sqlQueryWhere + sqlStartBrace;
			}
			
			for(int i = 0; i < ar_artcl_author_f.length; i++){
				if(i == ar_artcl_author_f.length -1){
					optionquery = optionquery + sql_ar_author_f + ComUtil.makeLikeOption(ar_artcl_author_f[i]) + sqlEndBrace;	
				}else{
					optionquery = optionquery + sql_ar_author_f + ComUtil.makeLikeOption(ar_artcl_author_f[i]) + sqlQueryOR;
				}				
			}
			
			op_code = "Y";
		}
		
		//세번째 조건 : 논문 저자 이름 Check
		if(!op_artcl_author_e.equalsIgnoreCase("NULL")){
			if(op_code.equalsIgnoreCase("Y")){
				optionquery = optionquery + op_oper1 + sqlStartBrace;
			}else{
				optionquery = optionquery + sqlQueryWhere + sqlStartBrace;
			}
			
			for(int i = 0; i < ar_artcl_author_e.length; i++){
				if(i == ar_artcl_author_e.length -1){
					optionquery = optionquery + sql_ar_author_e + ComUtil.makeLikeOption(ar_artcl_author_e[i]) + sqlEndBrace;	
				}else{
					optionquery = optionquery + sql_ar_author_e + ComUtil.makeLikeOption(ar_artcl_author_e[i]) + sqlQueryOR;
				}				
			}
			
			op_code = "Y";
		}
		
		//네번째 조건 : 논문 저널명 Check
		if(!op_jn_title.equalsIgnoreCase("NULL")){
			if(op_code.equalsIgnoreCase("Y")){
				optionquery = optionquery + op_oper2 + sqlStartBrace;
			}else{
				optionquery = optionquery + sqlQueryWhere + sqlStartBrace;
			}
			
			for(int i = 0; i < ar_jn_title.length; i++){
				if(i == ar_jn_title.length -1){
					optionquery = optionquery + sql_jn_title + ComUtil.makeLikeOption(ar_jn_title[i]) + sqlEndBrace;	
				}else{
					optionquery = optionquery + sql_jn_title + ComUtil.makeLikeOption(ar_jn_title[i]) + sqlQueryOR;
				}				
			}
			
			op_code = "Y";
		}
		//다섯번째 조건 : 출판 연도 시작 Check
		if(!op_year_from.equalsIgnoreCase("ALL"))
		{
			if(op_code.equalsIgnoreCase("Y")){
				//optionquery = optionquery + op_oper3 + sqlStartBrace + sql_year_from + "TO_NUMBER('" +op_year_from +"')";
				optionquery = optionquery + op_oper3 + sql_year_from + op_year_from;
			}else{
				//optionquery = optionquery + sqlQueryWhere + sqlStartBrace + sql_year_from + "TO_NUMBER('" +op_year_from +"')" + sqlEndBrace;
				optionquery = optionquery + sqlQueryWhere + sql_year_from + op_year_from;
			}
			
			//다섯번째 조건 : 출판 연도 끝 Check
			if(!op_year_to.equalsIgnoreCase("ALL"))
			{			
				//optionquery = optionquery + sqlStartBrace + sql_year_to + op_year_to + sqlEndBrace;
				optionquery = optionquery + sql_year_to + op_year_to;
			}
			
			op_code = "Y";
		}		
		//여섯번째 조건 : 참고문헌 종류코드 Check
		if(!op_jn_type.equalsIgnoreCase("NULL")){
			if(op_code.equalsIgnoreCase("Y")){
				optionquery = optionquery + op_oper4 + sqlStartBrace + sql_jn_type + ComUtil.makeEqualOption(op_jn_type) + sqlEndBrace;
			}else{
				optionquery = optionquery + sqlQueryWhere + sqlStartBrace + sql_jn_type + ComUtil.makeEqualOption(op_jn_type) + sqlEndBrace;
			}
		}
				
		//Query 마지막 Ordering 추가
		optionquery = optionquery + makeOrderBy(op_odr_col, op_odr_dir);
		
		//SQL문 실행
		DAO_REF_ARTICLE_INFO info = new DAO_REF_ARTICLE_INFO();
		artcl_list = info.selectArticleInfo(optionquery);
		
		return artcl_list;
	}
	
	/**
	 * @MethodName  : searchJournal_AssessInfo
	 * @Date   : 2013. 7. 29. 
	 * @MethodDescription : 검색 조건에 따라 평가 유무를 확인할 수 있는 메소드
	 * @param request
	 * @return
	 * @History  : - 
	 */
	public Vector<?> searchJournal_AssessInfo(HttpServletRequest request){
		Vector<?> artcl_list = new Vector<Object>();
		//1번째 조건 : 논문 저널명
		String op_jn_title = ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("jour_title")));
		//2번째 조건 : 볼륨
		String op_jn_vol = ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("jour_vol")));
		//3번째 조건 : 시작페이지
		String op_jn_stpage = ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("jour_st_page")));
		//4번째 조건 : 출판연도
		String op_jn_year = ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("publish_year")));
		
		/*
		 * SQL Query 변수 선언
		 */
		//전체 SQL문 저장 변수
		String optionquery = "";
				/*SELECT 
					B.PL_BI_DATA_NUM,
					A.PL_RAI_ARTCL_NUM,
					A.PL_RAI_ARTCL_TITLE,
					A.PL_RAI_ARTCL_AUTH_FNAME,
					A.PL_RAI_JOUR_TITLE,
					A.PL_RAI_JOUR_VOL,
					A.PL_RAI_ARTCL_ST_PAGE,
					A.PL_RAI_JOUR_YEAR,
					NVL(P.PL_PEI_FINAL_FLAG, '-')
					FROM PL_REF_ARTICLE_INFO A, PL_BASIC_INFO B,  PL_PRIM_EVAL_INFO P
					WHERE B.PL_RAI_ARTCL_NUM = A.PL_RAI_ARTCL_NUM AND P.PL_BI_DATA_NUM(+) = B.PL_BI_DATA_NUM*/
		//SQL WHERE 저장 변수
		//String sqlQueryWhere = " WHERE";
		//SQL OR 저장 변수
		String sqlQueryOR = " OR";
		//SQL AND 저장 변수
		String sqlQueryAND = " AND";
		//SQL Brace 저장 변수
		String sqlStartBrace = " (";
		String sqlEndBrace = ") ";

		//SQL 1번째 조건 : 논문 저널명
		String sql_jn_title = " UPPER(A.PL_RAI_JOUR_TITLE) LIKE ";
		//SQL 2번째 조건 : 논문 볼륨
		String sql_jn_vol = " A.PL_RAI_JOUR_VOL = ";
		//SQL 3번째 조건 : 논문 시작페이지
		String sql_jn_stpage = " A.PL_RAI_ARTCL_ST_PAGE = ";
		//SQL 4번째 조건 : 출판연도
		String sql_jn_year = " A.PL_RAI_JOUR_YEAR = ";
		
		//SQL 5번째 조건 : 정렬기준
		String sql_odr_by = " ORDER BY B.PL_BI_DATA_NUM";
		
		//각 항목별 조건 데이터 유무에 따른 체크 필드
		String op_code = "N";
		
		if(!op_jn_title.equalsIgnoreCase("NULL")){				
			optionquery = optionquery + sqlQueryAND + sql_jn_title + ComUtil.makeLikeOption(op_jn_title);
		}
		if(!op_jn_vol.equalsIgnoreCase("NULL")){
			optionquery = optionquery + sqlQueryAND + sql_jn_vol + ComUtil.makeEqualOption(op_jn_vol);
		}
		
		if(!op_jn_stpage.equalsIgnoreCase("NULL")){
			optionquery = optionquery + sqlQueryAND + sql_jn_stpage + ComUtil.makeEqualOption(op_jn_stpage);
		}
		
		if(!op_jn_year.equalsIgnoreCase("NULL")){
			optionquery = optionquery + sqlQueryAND + sql_jn_year + ComUtil.makeEqualOption(op_jn_year);
		}
		
		/*if(!op_jn_vol.equalsIgnoreCase("NULL")){
			if(op_code.equalsIgnoreCase("Y")){
				optionquery = optionquery + sqlQueryAND + sql_jn_vol + op_jn_vol;
			}else{
				optionquery = optionquery +  + sql_jn_vol + op_jn_vol;
			}			
			op_code = "Y";
		}
		
		if(!op_jn_stpage.equalsIgnoreCase("NULL")){
			if(op_code.equalsIgnoreCase("Y")){
				optionquery = optionquery + sqlQueryAND + sql_jn_stpage + op_jn_stpage;
			}else{
				optionquery = optionquery + sqlQueryWhere + sql_jn_stpage + op_jn_stpage;
			}			
			op_code = "Y";
		}
		
		if(!op_jn_year.equalsIgnoreCase("NULL")){
			if(op_code.equalsIgnoreCase("Y")){
				optionquery = optionquery + sqlQueryAND + sql_jn_year + op_jn_year;
			}else{
				optionquery = optionquery + sqlQueryWhere + sql_jn_year + op_jn_year;
			}			
			op_code = "Y";
		}*/
		
		optionquery = optionquery + sql_odr_by;
		
		//Query 마지막 Ordering 추가
		//optionquery = optionquery + makeOrderBy(op_odr_col, op_odr_dir);
		//System.out.println("optionquery : "+ optionquery);
		
		//SQL문 실행
		DAO_REF_ARTICLE_INFO info = new DAO_REF_ARTICLE_INFO();
		artcl_list = info.selectAssessedArticleInfo(optionquery);
		
		return artcl_list;
	}
		
	/**
	 * @MethodName : insertRefArticle
	 * @Desc : 입력받은 참고문헌 정보를 DB에 저장하는 business 메소드
	 * @param artInfo
	 * @return
	 * @throws SQLException 
	 */
	public String insertRefArticle(HttpServletRequest request, ServletContext context) throws SQLException{			
		//Web Application 절대 경로 지정
		//String realFolder = "";
		
		//파일이 업로드 되는 경로 지정
		String uploadFolder = "article_file";
		
		//파일이 실제 저장되는 경로 지정
		String realFolder = "E:/DEV/dcpp/public_html/tmp_file";
		
		//인코딩 타입 지정
		String encType ="euc-kr";		
		
		//최대 업로드될 파일 크기 지정 = 50MB		
		int maxSize = 50*1024*1024;

		//현재 페이지의 Web Application 상의 절대 경로
		//realFolder = context.getRealPath(uploadFolder);
		//request.getSession().ServletContext().getRealPath(uploadFolder);
		
		String contextpath = context.getRealPath("/");
		//String path = request.getSession().getServletContext().getRealPath("/") + "article_file/" ;
		
		//전송된 파일의 내용 타입
		//String type = ComUtil.getExtension(filename);
		
		//파일이 실제 저장되는 경로 지정
		String saveFolder = "E:/DEV/dcpp/public_html/data_file";
		//String saveFolder = request.getSession().getServletContext().getRealPath("/") + "article_file/" ;
		////System.out.println(println("saveFolder : " + saveFolder);

		//문서 번호 저장 변수
		String artcl_num = "";
		//입력 성공 여부 확인 변수
		boolean flag = false;
		
		try{
			//MultipartRequest multi= null;
			
			/*
			* 전송을 담당할 컴포넌트 생성
			* 전송할 파일명을 가지고 있는 객체. 서버상의 절대 경로, 최대 업로드될 파일 크기
			* 문자코드, 기본 보안 적용
			*/ 
			 MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
			
			//전송할 파일 정보를 가져와 출력
			Enumeration<?> files = multi.getFileNames();
			
			//파일 정보가 있는 동안 다음 루틴 처리
			while(files.hasMoreElements()){		
				
				//현재시간 알아내기
				String now_time = ComUtil.getTimeNow();
				//문서번호 따기
				artcl_num = makeRefArticleNum(now_time);
				
				//input 태그의 속성이 file 인 태그의 name 속성값 : 파라미터 이름
				String name = (String)files.nextElement();
				
				//서버에 저장되기 전  파일 이름
				String filename = multi.getFilesystemName(name);
				
				//전송 전 원래의 파일 이름
				//String original = multi.getOriginalFileName(name);
				
				//전송된 파일의 내용 타입
				String type = ComUtil.getExtension(filename);//multi.getContentType(name);
				
				//전송된 파일 속성이 file 태그의 name 속성값을 이용해 파일 객체 생성
				File file = multi.getFile(name);	
				
				//서버에 저장되기 전  파일 이름
				//String new_filename = multi.getFilesystemName(name);
				
				Article_Info artcle_info = new Article_Info();
				artcle_info.setPL_RAI_ARTCL_NUM(artcl_num);
				artcle_info.setPL_RACI_CODE_ID(ComUtil.convertKorean(multi.getParameter("pl_raci_code_id")));
				artcle_info.setPL_RAI_ARTCL_TITLE(ComUtil.convertKorean(multi.getParameter("pl_ra_artcl_title")));
				artcle_info.setPL_RAI_ARTCL_AUTH_FNAME(ComUtil.convertKorean(multi.getParameter("pl_ra_artcl_auth_fname")));
				artcle_info.setPL_RAI_JOUR_TITLE(ComUtil.convertKorean(multi.getParameter("pl_ra_jour_title")));
				artcle_info.setPL_RAI_ISSN_P(ComUtil.convertKorean(multi.getParameter("pl_ra_issn_p")));
				artcle_info.setPL_RAI_ISSN_N(ComUtil.convertKorean(multi.getParameter("pl_ra_issn_n")));
				artcle_info.setPL_RAI_ISBN(ComUtil.convertKorean(multi.getParameter("pl_ra_isbn")));
				artcle_info.setPL_RAI_NDSL(ComUtil.convertKorean(multi.getParameter("pl_ra_ndsl")));
				artcle_info.setPL_RAI_JOUR_VOL(ComUtil.convertKorean(multi.getParameter("pl_ra_jour_vol")));
				artcle_info.setPL_RAI_JOUR_NUM(ComUtil.convertKorean(multi.getParameter("pl_ra_jour_num")));
				artcle_info.setPL_RAI_ARTCL_ST_PAGE(multi.getParameter("pl_ra_artcl_st_page"));
				artcle_info.setPL_RAI_ARTCL_ED_PAGE(multi.getParameter("pl_ra_artcl_ed_page"));
				artcle_info.setPL_RAI_JOUR_YEAR(multi.getParameter("pl_ra_jour_year"));
				artcle_info.setPL_RAI_ORG_FILE_PATH(uploadFolder);
				artcle_info.setPL_RAI_ORG_FILE_NAME(artcl_num);
				artcle_info.setPL_RAI_ORG_FILE_EXT(type);
				artcle_info.setPL_RAI_INSERT_TIME(now_time);
				artcle_info.setPL_RAI_ARTCL_AUTH_ENAME(ComUtil.convertKorean(multi.getParameter("pl_ra_artcl_auth_ename")));
				artcle_info.setPL_RAI_ARTCL_DOI(ComUtil.convertKorean(multi.getParameter("pl_ra_artcl_doi")));
				
				DAO_REF_ARTICLE_INFO info = new DAO_REF_ARTICLE_INFO();
				
				flag = info.insertArticleInfo(artcle_info);

				if(flag == true){
					//파일 이동 시키기
					ComUtil.moveFile(file, saveFolder, artcl_num, type);
				}
			}		
		}catch(IOException ioe){
			System.out.println(ioe);
		}catch(Exception ex){
			System.out.println(ex);
		}
		
		if(flag == true)
		{
			//System.out.println("------------- New Article ");
			//System.out.println("입력시간 : " + ComUtil.getTimeNow());
			//System.out.println("참고문헌번호 : " + artcl_num);
			//System.out.println("------------- End Article ");
			return artcl_num;
		}else{
			return ComVar.MSG_ERROR;
		}
	}
	
	/**
	 * @MethodName  : insertDOINumber
	 * @Date   : 2012. 3. 12. 
	 * @MethodDescription : DOI 데이터를 하나씩 입력하는 메소드
	 * @param article_number
	 * @param doi_number
	 * @return
	 * @History  : - 
	 */
	public boolean insertDOINumber(String article_number, String doi_number){
		boolean r_value = false;
		
		DAO_REF_ARTICLE_INFO info = new DAO_REF_ARTICLE_INFO();
		
		//System.out.println("                           ");
		//System.out.println("----- 한개만 입력");
		//System.out.println("참고문헌 번호 : " + article_number);
		//System.out.println("DOI 번호 : " + doi_number);
		r_value = info.updateArticleDoiNumber(article_number, doi_number);
		return r_value;		
	}
	
	/**
	 * @MethodName  : insertDOIExcelData
	 * @Date   : 2012. 3. 12. 
	 * @MethodDescription : DOI 데이터를 Excel로 저장하는 메소드
	 * @param multi
	 * @param contextpath
	 * @return
	 * @History  : - 
	 */
	public boolean insertDOIExcelData(MultipartRequest multi, String contextpath){
		boolean value = false;			
		
		try{						
			//전송할 파일 정보를 가져와 출력
			Enumeration<?> files = multi.getFileNames();
			
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
				//String saveFolder = request.getSession().getServletContext().getRealPath("/") + "article_file/";
				String saveFolder = filepath + "data_file";
				String savePath = saveFolder+"/"+filename;
				
				//전송된 파일 속성이 file 태그의 name 속성값을 이용해 파일 객체 생성
				File file = multi.getFile(name);
				
				boolean flag = ComUtil.moveExcelFile(file, saveFolder, filename);
				
				if(flag == true){	//파일 정상 이동 되면,	
					Workbook wb = WorkbookFactory.create(new FileInputStream(savePath));
					//시트이름과 시트 번호 추출
					Sheet sheet = wb.getSheetAt(0);
					// 시트의 마지막 줄 번호 리턴
					int rows = sheet.getLastRowNum() + 1;			

					//데이터 추출
					boolean isNull = false;
					//for (Row row : wb.getSheetAt(0)) {
					for (int r = 1; r < rows; r++) {
						// 시트에 대한 행을 하나씩 추출
						Row row = sheet.getRow(r);
						if (row != null) {
							Cell article_number_cell = row.getCell(0);
							Cell doi_number_cell = row.getCell(1);
							
							String article_number = ComUtil.getCellData(article_number_cell);
							String doi_number = ComUtil.getCellData(doi_number_cell);
							DAO_REF_ARTICLE_INFO info = new DAO_REF_ARTICLE_INFO();
							value = info.updateArticleDoiNumber(article_number, doi_number);										
							if(value==false){
								System.out.println("                           ");
								System.out.println("----- " + r + " 번째 줄 데이터");
								System.out.println("참고문헌 번호 : " + article_number);
								System.out.println("DOI 번호 : " + doi_number);
								return value;
							}
						}	
					}
				}
					
				boolean flag2 = ComUtil.deleteExcelFile(file, saveFolder, filename);
			}
		}catch(IOException ioe){
			System.out.println(ioe);
		}catch(Exception ex){
			System.out.println(ex);
		}
		
		return value;
	}
		/*
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
						v_list.addElement(cell.getNumericCellValue());//double
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
			
		}*/
		//totalParam.add(v_list);

	/**
	 * @MethodName : makeRefArticleNum
	 * @Desc : 참고문헌번호를 만들어 내는 메소드
	 * @param artInfo
	 * @return
	 */
	public String makeRefArticleNum(String date){
		String returnValue = "";
		String org_Date = date;
		//현재년도 알아내기
		String in_Year = ComUtil.getYearInDate(org_Date);
		//String sqlValue = "";

		//번호를 받아오기 위한 SQL 문 실행
		DAO_REF_ARTICLE_INFO seq = new DAO_REF_ARTICLE_INFO();
		//sqlValue = seq.selectArticleSeq();
		
		String db_year = seq.selectArticleSaveYearInfo();
		
		if(!db_year.equalsIgnoreCase(in_Year)){
			//update			
			seq.updateArticleSaveYearInfo(in_Year);
			seq.resetArticleSeq();			
		}
		//seq.insertArticleSaveYearInfo(in_Year);
		
		String sqlValue = seq.selectArticleSeq();
		
		
		//번호 만들기
		int count = sqlValue.length();
		String zeroString = "0";
		String combineValue = "";
		for(int i = 6 ; i > count ; i--)
		{
			combineValue = combineValue + zeroString;
		}
		
		returnValue = in_Year + combineValue + sqlValue;
		
		return returnValue;
	}
	
	public String makeOrderBy(String col, String dir) {		
		String _oDir = "ASC";
		
		if("desc".equalsIgnoreCase(dir)) {
			_oDir = "DESC";
		}
		if("artcl_no".equals(col)) {
			return " ORDER BY PL_RAI_ARTCL_NUM" + " " + _oDir;				
		}
		else if("artcl_name".equals(col))
		{
			return " ORDER BY PL_RAI_ARTCL_TITLE" + " " + _oDir;
		}
		else if("artcl_author".equals(col))
		{
			return " ORDER BY PL_RAI_ARTCL_AUTH_ENAME" + " " + _oDir;
		}
		else if("artcl_jn_name".equals(col))
		{
			return " ORDER BY PL_RAI_JOUR_TITLE" + " " + _oDir;
		}
		else if("artcl_jn_year".equals(col))
		{
			return " ORDER BY PL_RAI_JOUR_YEAR" + " " + _oDir;
		}
		else{
			return " ORDER BY PL_RAI_ARTCL_NUM" + " " + _oDir;
		}
	}
	
	public HashMap<String, String[]> getYearList(){		
		int firstYear = getFirstYear();	//초기 년도
		int year = getCurYear();
		int durYear = year - firstYear + 2;	//전체 년도 수
		HashMap<String, String[]> data = new HashMap<String, String[]>();
		String[] strYearKey = new String[durYear];
		String[] strYear = new String[durYear];
		int i = 0;

		while(year != firstYear-1)
		{
			if(i == 0)
			{
					strYear[i] = "전체";
					strYearKey[i] = "ALL";
			}
			else{
				strYearKey[i] = Integer.toString(year);
				strYear[i] = Integer.toString(year);
				year--;
			}
			i++;
		}
		data.put("yearKey", strYearKey);
		data.put("year", strYear);
		return data;
	} 
	
	public int getCurYear(){
		//int curYear = 1900; //초기값
		DAO_COMSYS_INFO year = new DAO_COMSYS_INFO();
		int data = year.selectCurYear();
		return data;
	}
	
	public int getFirstYear(){
		//int firstYear = 1900; //초기값
		DAO_REF_ARTICLE_INFO year = new DAO_REF_ARTICLE_INFO();
		int data = year.selectFirstYear();		
		return data;
	}
}
