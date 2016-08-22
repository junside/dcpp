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
 * @�����̷�
 * 	- 2009.02.18 : ���� ���� ��ȸ ��� �κ�(���ǿ� ����) �߰�
 *
 */
public class Ctr_Article_Process {
	
	//������
	public Ctr_Article_Process(){
			
	}	
	
	/**
	 * @MethodName : selectRefArticle
	 * @Desc : DB���� ������ ��ü�� �������� �޼ҵ�
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
	 * @Desc : DB���� ��ȸ ���ǿ� ���� ������ ������ �������� �޼ҵ�
	 * @param request
	 * @return
	 */
	public Vector<?> searchInfo(HttpServletRequest request){
		Vector<?> artcl_list = new Vector<Object>();
		
		//���� ����
		//SQL���� ���� ����

		//ù��° ���� : �� ����
		String op_artcl_title = ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("pl_ra_artcl_title")));
		//�ι�° ���� : �� ����
		String op_artcl_author_f = ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("pl_ra_artcl_auth_fname")));
		//����° ���� : �� ����
		String op_artcl_author_e = ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("pl_ra_artcl_auth_ename")));
		//�׹�° ���� : �� ���θ�
		String op_jn_title = ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("pl_ra_jour_title")));
		//�ټ���° ���� : ���ǿ���(From)
		String op_year_from = ComUtil.isNullToNullString(request.getParameter("publish_year_from"));
		//������° ���� : ���ǿ���(To)
		String op_year_to = ComUtil.isNullToNullString(request.getParameter("publish_year_to"));
		//�ϰ���° ���� : ������ �����ڵ�
		String op_jn_type = ComUtil.isNullToNullString(request.getParameter("pl_raci_code_id"));
		//������ ����
		String op_oper1 = ComUtil.getOperationStr(request.getParameter("option1_oper"));
		String op_oper2 = ComUtil.getOperationStr(request.getParameter("option2_oper"));
		String op_oper3 = ComUtil.getOperationStr(request.getParameter("option3_oper"));
		String op_oper4 = ComUtil.getOperationStr(request.getParameter("option4_oper"));
		String op_oper5 = ComUtil.getOperationStr(request.getParameter("option5_oper"));
		
		//Order ����
		String op_odr_col = ComUtil.isNullToNullString(request.getParameter("_oCol"));
		String op_odr_dir = ComUtil.isNullToNullString(request.getParameter("_oDir"));
			
		
		//�� �׸� ���� ������ ������ ���� üũ �ʵ�
		String op_code = "N";
		
		//�� ���� �׸� �и��� ���� ���� ���� �迭 ����
		String[] ar_artcl_title = ComUtil.getSeparatedText(op_artcl_title); 
		String[] ar_artcl_author_f = ComUtil.getSeparatedText(op_artcl_author_f); 
		String[] ar_artcl_author_e = ComUtil.getSeparatedText(op_artcl_author_e); 
		String[] ar_jn_title = ComUtil.getSeparatedText(op_jn_title); 
		
		/*
		 * SQL Query ���� ����
		 */
		//��ü SQL�� ���� ����
		String optionquery = "";
				/*"SELECT PL_RAI_ARTCL_NUM, PL_RAI_ARTCL_TITLE," +
				" PL_RAI_ARTCL_AUTH, PL_RAI_JOUR_TITLE, PL_RAI_ARTCL_ST_PAGE," +
				" PL_RAI_JOUR_YEAR, PL_RAI_ORG_FILE_PATH, PL_RAI_ORG_FILE_NAME," +
				" PL_RAI_ORG_FILE_EXT FROM PL_REF_ARTICLE_INFO ";*/
		//SQL WHERE ���� ����
		String sqlQueryWhere = " WHERE ";
		//SQL WHERE ���� ����
		String sqlQueryOR = " OR ";
		//SQL Brace ���� ����
		String sqlStartBrace = " (";
		String sqlEndBrace = ") ";
		//SQL End ��ȣ ���� ����
		//String sqlQueryOrderBy = " ORDER BY PL_RAI_ARTCL_NUM ";
		//SQL ù��° ���� : �� ����
		String sql_ar_title = " UPPER(PL_RAI_ARTCL_TITLE) LIKE "; 
		//SQL �ι�° ���� : �� ����
		String sql_ar_author_f = " UPPER(PL_RAI_ARTCL_AUTH_FNAME) LIKE ";
		//SQL ����° ���� : �� ����
		String sql_ar_author_e = " UPPER(PL_RAI_ARTCL_AUTH_ENAME) LIKE ";
		//SQL �׹�° ���� : �� ���θ�
		String sql_jn_title = " UPPER(PL_RAI_JOUR_TITLE) LIKE ";
		//SQL �ټ���° ���� : ���ǿ���(From)
		String sql_year_from = " TO_NUMBER(PL_RAI_JOUR_YEAR) >= ";
		//SQL ������° ���� : ���ǿ���(To)
		String sql_year_to = " AND TO_NUMBER(PL_RAI_JOUR_YEAR) <= ";
		//SQL �ϰ���° ���� : ������ �����ڵ�
		String sql_jn_type = " PL_RACI_CODE_ID = ";
		
		//ù��° ���� : �� ���� Check
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
		
		//�ι�° ���� : �� ���� �� Check
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
		
		//����° ���� : �� ���� �̸� Check
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
		
		//�׹�° ���� : �� ���θ� Check
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
		//�ټ���° ���� : ���� ���� ���� Check
		if(!op_year_from.equalsIgnoreCase("ALL"))
		{
			if(op_code.equalsIgnoreCase("Y")){
				//optionquery = optionquery + op_oper3 + sqlStartBrace + sql_year_from + "TO_NUMBER('" +op_year_from +"')";
				optionquery = optionquery + op_oper3 + sql_year_from + op_year_from;
			}else{
				//optionquery = optionquery + sqlQueryWhere + sqlStartBrace + sql_year_from + "TO_NUMBER('" +op_year_from +"')" + sqlEndBrace;
				optionquery = optionquery + sqlQueryWhere + sql_year_from + op_year_from;
			}
			
			//�ټ���° ���� : ���� ���� �� Check
			if(!op_year_to.equalsIgnoreCase("ALL"))
			{			
				//optionquery = optionquery + sqlStartBrace + sql_year_to + op_year_to + sqlEndBrace;
				optionquery = optionquery + sql_year_to + op_year_to;
			}
			
			op_code = "Y";
		}		
		//������° ���� : ������ �����ڵ� Check
		if(!op_jn_type.equalsIgnoreCase("NULL")){
			if(op_code.equalsIgnoreCase("Y")){
				optionquery = optionquery + op_oper4 + sqlStartBrace + sql_jn_type + ComUtil.makeEqualOption(op_jn_type) + sqlEndBrace;
			}else{
				optionquery = optionquery + sqlQueryWhere + sqlStartBrace + sql_jn_type + ComUtil.makeEqualOption(op_jn_type) + sqlEndBrace;
			}
		}
				
		//Query ������ Ordering �߰�
		optionquery = optionquery + makeOrderBy(op_odr_col, op_odr_dir);
		
		//SQL�� ����
		DAO_REF_ARTICLE_INFO info = new DAO_REF_ARTICLE_INFO();
		artcl_list = info.selectArticleInfo(optionquery);
		
		return artcl_list;
	}
	
	/**
	 * @MethodName  : searchJournal_AssessInfo
	 * @Date   : 2013. 7. 29. 
	 * @MethodDescription : �˻� ���ǿ� ���� �� ������ Ȯ���� �� �ִ� �޼ҵ�
	 * @param request
	 * @return
	 * @History  : - 
	 */
	public Vector<?> searchJournal_AssessInfo(HttpServletRequest request){
		Vector<?> artcl_list = new Vector<Object>();
		//1��° ���� : �� ���θ�
		String op_jn_title = ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("jour_title")));
		//2��° ���� : ����
		String op_jn_vol = ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("jour_vol")));
		//3��° ���� : ����������
		String op_jn_stpage = ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("jour_st_page")));
		//4��° ���� : ���ǿ���
		String op_jn_year = ComUtil.convertKorean(ComUtil.isNullToNullString(request.getParameter("publish_year")));
		
		/*
		 * SQL Query ���� ����
		 */
		//��ü SQL�� ���� ����
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
		//SQL WHERE ���� ����
		//String sqlQueryWhere = " WHERE";
		//SQL OR ���� ����
		String sqlQueryOR = " OR";
		//SQL AND ���� ����
		String sqlQueryAND = " AND";
		//SQL Brace ���� ����
		String sqlStartBrace = " (";
		String sqlEndBrace = ") ";

		//SQL 1��° ���� : �� ���θ�
		String sql_jn_title = " UPPER(A.PL_RAI_JOUR_TITLE) LIKE ";
		//SQL 2��° ���� : �� ����
		String sql_jn_vol = " A.PL_RAI_JOUR_VOL = ";
		//SQL 3��° ���� : �� ����������
		String sql_jn_stpage = " A.PL_RAI_ARTCL_ST_PAGE = ";
		//SQL 4��° ���� : ���ǿ���
		String sql_jn_year = " A.PL_RAI_JOUR_YEAR = ";
		
		//SQL 5��° ���� : ���ı���
		String sql_odr_by = " ORDER BY B.PL_BI_DATA_NUM";
		
		//�� �׸� ���� ������ ������ ���� üũ �ʵ�
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
		
		//Query ������ Ordering �߰�
		//optionquery = optionquery + makeOrderBy(op_odr_col, op_odr_dir);
		//System.out.println("optionquery : "+ optionquery);
		
		//SQL�� ����
		DAO_REF_ARTICLE_INFO info = new DAO_REF_ARTICLE_INFO();
		artcl_list = info.selectAssessedArticleInfo(optionquery);
		
		return artcl_list;
	}
		
	/**
	 * @MethodName : insertRefArticle
	 * @Desc : �Է¹��� ������ ������ DB�� �����ϴ� business �޼ҵ�
	 * @param artInfo
	 * @return
	 * @throws SQLException 
	 */
	public String insertRefArticle(HttpServletRequest request, ServletContext context) throws SQLException{			
		//Web Application ���� ��� ����
		//String realFolder = "";
		
		//������ ���ε� �Ǵ� ��� ����
		String uploadFolder = "article_file";
		
		//������ ���� ����Ǵ� ��� ����
		String realFolder = "E:/DEV/dcpp/public_html/tmp_file";
		
		//���ڵ� Ÿ�� ����
		String encType ="euc-kr";		
		
		//�ִ� ���ε�� ���� ũ�� ���� = 50MB		
		int maxSize = 50*1024*1024;

		//���� �������� Web Application ���� ���� ���
		//realFolder = context.getRealPath(uploadFolder);
		//request.getSession().ServletContext().getRealPath(uploadFolder);
		
		String contextpath = context.getRealPath("/");
		//String path = request.getSession().getServletContext().getRealPath("/") + "article_file/" ;
		
		//���۵� ������ ���� Ÿ��
		//String type = ComUtil.getExtension(filename);
		
		//������ ���� ����Ǵ� ��� ����
		String saveFolder = "E:/DEV/dcpp/public_html/data_file";
		//String saveFolder = request.getSession().getServletContext().getRealPath("/") + "article_file/" ;
		////System.out.println(println("saveFolder : " + saveFolder);

		//���� ��ȣ ���� ����
		String artcl_num = "";
		//�Է� ���� ���� Ȯ�� ����
		boolean flag = false;
		
		try{
			//MultipartRequest multi= null;
			
			/*
			* ������ ����� ������Ʈ ����
			* ������ ���ϸ��� ������ �ִ� ��ü. �������� ���� ���, �ִ� ���ε�� ���� ũ��
			* �����ڵ�, �⺻ ���� ����
			*/ 
			 MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
			
			//������ ���� ������ ������ ���
			Enumeration<?> files = multi.getFileNames();
			
			//���� ������ �ִ� ���� ���� ��ƾ ó��
			while(files.hasMoreElements()){		
				
				//����ð� �˾Ƴ���
				String now_time = ComUtil.getTimeNow();
				//������ȣ ����
				artcl_num = makeRefArticleNum(now_time);
				
				//input �±��� �Ӽ��� file �� �±��� name �Ӽ��� : �Ķ���� �̸�
				String name = (String)files.nextElement();
				
				//������ ����Ǳ� ��  ���� �̸�
				String filename = multi.getFilesystemName(name);
				
				//���� �� ������ ���� �̸�
				//String original = multi.getOriginalFileName(name);
				
				//���۵� ������ ���� Ÿ��
				String type = ComUtil.getExtension(filename);//multi.getContentType(name);
				
				//���۵� ���� �Ӽ��� file �±��� name �Ӽ����� �̿��� ���� ��ü ����
				File file = multi.getFile(name);	
				
				//������ ����Ǳ� ��  ���� �̸�
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
					//���� �̵� ��Ű��
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
			//System.out.println("�Է½ð� : " + ComUtil.getTimeNow());
			//System.out.println("�������ȣ : " + artcl_num);
			//System.out.println("------------- End Article ");
			return artcl_num;
		}else{
			return ComVar.MSG_ERROR;
		}
	}
	
	/**
	 * @MethodName  : insertDOINumber
	 * @Date   : 2012. 3. 12. 
	 * @MethodDescription : DOI �����͸� �ϳ��� �Է��ϴ� �޼ҵ�
	 * @param article_number
	 * @param doi_number
	 * @return
	 * @History  : - 
	 */
	public boolean insertDOINumber(String article_number, String doi_number){
		boolean r_value = false;
		
		DAO_REF_ARTICLE_INFO info = new DAO_REF_ARTICLE_INFO();
		
		//System.out.println("                           ");
		//System.out.println("----- �Ѱ��� �Է�");
		//System.out.println("������ ��ȣ : " + article_number);
		//System.out.println("DOI ��ȣ : " + doi_number);
		r_value = info.updateArticleDoiNumber(article_number, doi_number);
		return r_value;		
	}
	
	/**
	 * @MethodName  : insertDOIExcelData
	 * @Date   : 2012. 3. 12. 
	 * @MethodDescription : DOI �����͸� Excel�� �����ϴ� �޼ҵ�
	 * @param multi
	 * @param contextpath
	 * @return
	 * @History  : - 
	 */
	public boolean insertDOIExcelData(MultipartRequest multi, String contextpath){
		boolean value = false;			
		
		try{						
			//������ ���� ������ ������ ���
			Enumeration<?> files = multi.getFileNames();
			
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
				//String saveFolder = request.getSession().getServletContext().getRealPath("/") + "article_file/";
				String saveFolder = filepath + "data_file";
				String savePath = saveFolder+"/"+filename;
				
				//���۵� ���� �Ӽ��� file �±��� name �Ӽ����� �̿��� ���� ��ü ����
				File file = multi.getFile(name);
				
				boolean flag = ComUtil.moveExcelFile(file, saveFolder, filename);
				
				if(flag == true){	//���� ���� �̵� �Ǹ�,	
					Workbook wb = WorkbookFactory.create(new FileInputStream(savePath));
					//��Ʈ�̸��� ��Ʈ ��ȣ ����
					Sheet sheet = wb.getSheetAt(0);
					// ��Ʈ�� ������ �� ��ȣ ����
					int rows = sheet.getLastRowNum() + 1;			

					//������ ����
					boolean isNull = false;
					//for (Row row : wb.getSheetAt(0)) {
					for (int r = 1; r < rows; r++) {
						// ��Ʈ�� ���� ���� �ϳ��� ����
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
								System.out.println("----- " + r + " ��° �� ������");
								System.out.println("������ ��ȣ : " + article_number);
								System.out.println("DOI ��ȣ : " + doi_number);
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
	 * @Desc : �������ȣ�� ����� ���� �޼ҵ�
	 * @param artInfo
	 * @return
	 */
	public String makeRefArticleNum(String date){
		String returnValue = "";
		String org_Date = date;
		//����⵵ �˾Ƴ���
		String in_Year = ComUtil.getYearInDate(org_Date);
		//String sqlValue = "";

		//��ȣ�� �޾ƿ��� ���� SQL �� ����
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
		
		
		//��ȣ �����
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
		int firstYear = getFirstYear();	//�ʱ� �⵵
		int year = getCurYear();
		int durYear = year - firstYear + 2;	//��ü �⵵ ��
		HashMap<String, String[]> data = new HashMap<String, String[]>();
		String[] strYearKey = new String[durYear];
		String[] strYear = new String[durYear];
		int i = 0;

		while(year != firstYear-1)
		{
			if(i == 0)
			{
					strYear[i] = "��ü";
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
		//int curYear = 1900; //�ʱⰪ
		DAO_COMSYS_INFO year = new DAO_COMSYS_INFO();
		int data = year.selectCurYear();
		return data;
	}
	
	public int getFirstYear(){
		//int firstYear = 1900; //�ʱⰪ
		DAO_REF_ARTICLE_INFO year = new DAO_REF_ARTICLE_INFO();
		int data = year.selectFirstYear();		
		return data;
	}
}
