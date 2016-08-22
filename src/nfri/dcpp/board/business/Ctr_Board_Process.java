/**
 * 
 */
package nfri.dcpp.board.business;

import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import nfri.dcpp.board.db.DAO_BOARD_INFO;
import nfri.dcpp.board.model.Board_Info;
import nfri.dcpp.properties.db.DAO_BASIC_PROPERTY_INFO;
import nfri.dcpp.com.util.ComUtil;
/**
 * @Project: dcpp
 * @Title  : Ctr_Board_Process.java
 * @Description : 게시판과 관련된 로직을 처리하는 비즈니스 클래스
 * @Author : J.H Park (JUNSiDE)
 * @Date   : 2011. 02. 07
 * @Company : Data Center for Plasma Properties.
 *            NFRI. 
 * @History : -
 */

public class Ctr_Board_Process {
	//생성자
	public Ctr_Board_Process(){
			
	}
	
	/**
	 * @MethodName  : insert_Article_Info
	 * @Date   : 2011. 3. 17. 
	 * @MethodDescription : QA 게시글 입력하는 메소드
	 * @param article
	 * @return
	 * @throws SQLException
	 * @History  : - 
	 */
	public boolean insert_QA_Article(HttpServletRequest request) throws SQLException{			
		
		boolean r_value = false;
		DAO_BOARD_INFO dao_board = new DAO_BOARD_INFO();
		Board_Info article = new Board_Info();	    
		
		int num=Integer.parseInt(request.getParameter("num"));//article.getNum();
		int ref=Integer.parseInt(request.getParameter("ref"));//article.getRef();
		int re_step=Integer.parseInt(request.getParameter("re_step"));//article.getRe_step();
		int re_level=Integer.parseInt(request.getParameter("re_level"));//article.getRe_level();
		
		
		//int number=0;
		//번호 가져오기
		int number = dao_board.select_QA_MaxNumber();
		
		if(num!=0){//Ref UPDATE
			boolean re_value = dao_board.update_QA_Reference(ref, re_step);	
			re_step = re_step + 1;
			re_level = re_level + 1;
		}else{
			ref = number;
			re_step = 0;
			re_level = 0;
		}
			
		//게시물 입력하기	
		article.setNum(number);
		article.setWriter(ComUtil.convertKorean(request.getParameter("writer")));
		article.setEmail(request.getParameter("email"));
		article.setSubject(ComUtil.convertKorean(request.getParameter("subject")));
		article.setPasswd(request.getParameter("passwd"));
		article.setReg_date(ComUtil.getTimeNow());
		article.setRef(ref);
		article.setRe_step(re_step);
		article.setRe_level(re_level);
		article.setContent(ComUtil.convertKorean(request.getParameter("content")));
		article.setIp(request.getRemoteAddr());
		r_value = dao_board.insert_QA_Article(article);	
		return r_value;
	}
	
	/**
	 * @MethodName  : update_QA_Article
	 * @Date   : 2011. 3. 22. 
	 * @MethodDescription : QA 게시판의 게시물 업데이트 메소드
	 * @param request
	 * @return
	 * @throws SQLException
	 * @History  : - 
	 */
	public boolean update_QA_Article(HttpServletRequest request) throws SQLException{
		boolean r_value = false;
		DAO_BOARD_INFO dao_board = new DAO_BOARD_INFO();
		
		int num=Integer.parseInt(request.getParameter("num"));
		
		String db_passwd = dao_board.select_QA_Passwd(num);
		String in_passwd = request.getParameter("passwd");
		
		String in_writer = ComUtil.convertKorean(request.getParameter("writer"));
		String in_email = request.getParameter("email");
		String in_subject = ComUtil.convertKorean(request.getParameter("subject"));
		String in_content = ComUtil.convertKorean(request.getParameter("content"));
		
		
		if(!db_passwd.equalsIgnoreCase(in_passwd)){
			r_value = false;
		}else{
			Vector v_list = new Vector();
			v_list.addElement(in_writer);
			v_list.addElement(in_email);
			v_list.addElement(in_subject);
			v_list.addElement(in_passwd);
			v_list.addElement(in_content);
			v_list.addElement(num);
			
			r_value = dao_board.update_QA_Article(v_list);
		}		
		return r_value;
	}
	
	/**
	 * @MethodName  : select_QA_Article
	 * @Date   : 2011. 3. 17. 
	 * @MethodDescription : QA 게시글 가져오는 메소드
	 * @return
	 * @throws SQLException
	 * @History  : - 
	 */
	public Vector select_QA_Article() throws SQLException{			
				
		Vector vecList = new Vector();
		DAO_BOARD_INFO info = new DAO_BOARD_INFO();
		vecList = info.select_QA_Article();
		return vecList;

	}
	
	/**
	 * @MethodName  : select_QA_Article_Content
	 * @Date   : 2011. 3. 21. 
	 * @MethodDescription : QA 게시물 내용을 가져오는 메소드
	 * @param num
	 * @return
	 * @History  : - 
	 */
	public Board_Info select_QA_Article_Content(int num){
		Board_Info b_info = new Board_Info();
		DAO_BOARD_INFO info = new DAO_BOARD_INFO();
		b_info = info.select_QA_ArticleContent(num);
		
		return b_info;
	}
	
	/**
	 * @MethodName  : delete_QA_Article
	 * @Date   : 2011. 3. 22. 
	 * @MethodDescription : QA 게시물을 삭제하는 메소드
	 * @param num
	 * @param in_passwd
	 * @return
	 * @History  : - 
	 */
	public boolean delete_QA_Article(int num, String in_passwd){
		boolean r_value = false;
		DAO_BOARD_INFO dao_board = new DAO_BOARD_INFO();
		String db_passwd = dao_board.select_QA_Passwd(num);
		
		if(!db_passwd.equalsIgnoreCase(in_passwd)){
			r_value = false;
		}else{				
			Vector v_list = new Vector();
			v_list.addElement(num);
			r_value = dao_board.delete_QA_Article(v_list);
		}		
		return r_value;
	}
	
	/**
	 * @MethodName  : insert_HP_Article
	 * @Date   : 2011. 3. 17. 
	 * @MethodDescription : HP 게시글 입력하는 메소드
	 * @param article
	 * @return
	 * @throws SQLException
	 * @History  : - 
	 */
	public boolean insert_HP_Article(HttpServletRequest request) throws SQLException{			
		
		boolean r_value = false;
		DAO_BOARD_INFO dao_board = new DAO_BOARD_INFO();
		Board_Info article = new Board_Info();	    
		
		int num=Integer.parseInt(request.getParameter("num"));//article.getNum();
		int ref=Integer.parseInt(request.getParameter("ref"));//article.getRef();
		int re_step=Integer.parseInt(request.getParameter("re_step"));//article.getRe_step();
		int re_level=Integer.parseInt(request.getParameter("re_level"));//article.getRe_level();
		
		
		//int number=0;
		//번호 가져오기
		int number = dao_board.select_HP_MaxNumber();
		
		if(num!=0){//Ref UPDATE
			boolean re_value = dao_board.update_HP_Reference(ref, re_step);	
			re_step = re_step + 1;
			re_level = re_level + 1;
		}else{
			ref = number;
			re_step = 0;
			re_level = 0;
		}
			
		//게시물 입력하기	
		article.setNum(number);
		article.setWriter(ComUtil.convertKorean(request.getParameter("writer")));
		article.setEmail(request.getParameter("email"));
		article.setSubject(ComUtil.convertKorean(request.getParameter("subject")));
		article.setPasswd(request.getParameter("passwd"));
		article.setReg_date(ComUtil.getTimeNow());
		article.setRef(ref);
		article.setRe_step(re_step);
		article.setRe_level(re_level);
		article.setContent(ComUtil.convertKorean(request.getParameter("content")));
		article.setIp(request.getRemoteAddr());
		r_value = dao_board.insert_HP_Article(article);	
		return r_value;
	}
	
	/**
	 * @MethodName  : update_HP_Article
	 * @Date   : 2011. 3. 22. 
	 * @MethodDescription : HP 게시판의 게시물 업데이트 메소드
	 * @param request
	 * @return
	 * @throws SQLException
	 * @History  : - 
	 */
	public boolean update_HP_Article(HttpServletRequest request) throws SQLException{
		boolean r_value = false;
		DAO_BOARD_INFO dao_board = new DAO_BOARD_INFO();
		
		int num=Integer.parseInt(request.getParameter("num"));
		
		String db_passwd = dao_board.select_HP_Passwd(num);
		String in_passwd = request.getParameter("passwd");
		
		String in_writer = ComUtil.convertKorean(request.getParameter("writer"));
		String in_email = request.getParameter("email");
		String in_subject = ComUtil.convertKorean(request.getParameter("subject"));
		String in_content = ComUtil.convertKorean(request.getParameter("content"));
		
		
		if(!db_passwd.equalsIgnoreCase(in_passwd)){
			r_value = false;
		}else{
			Vector v_list = new Vector();
			v_list.addElement(in_writer);
			v_list.addElement(in_email);
			v_list.addElement(in_subject);
			v_list.addElement(in_passwd);
			v_list.addElement(in_content);
			v_list.addElement(num);
			
			r_value = dao_board.update_HP_Article(v_list);
		}		
		return r_value;
	}
	
	/**
	 * @MethodName  : select_HP_Article
	 * @Date   : 2011. 3. 17. 
	 * @MethodDescription : HP 게시글 가져오는 메소드
	 * @return
	 * @throws SQLException
	 * @History  : - 
	 */
	public Vector select_HP_Article() throws SQLException{			
				
		Vector vecList = new Vector();
		DAO_BOARD_INFO info = new DAO_BOARD_INFO();
		vecList = info.select_HP_Article();
		return vecList;

	}
	
	/**
	 * @MethodName  : select_HP_Article_Content
	 * @Date   : 2011. 3. 21. 
	 * @MethodDescription : HP 게시물 내용을 가져오는 메소드
	 * @param num
	 * @return
	 * @History  : - 
	 */
	public Board_Info select_HP_Article_Content(int num){
		Board_Info b_info = new Board_Info();
		DAO_BOARD_INFO info = new DAO_BOARD_INFO();
		b_info = info.select_HP_ArticleContent(num);
		
		return b_info;
	}
	
	/**
	 * @MethodName  : delete_HP_Article
	 * @Date   : 2011. 3. 22. 
	 * @MethodDescription : HP 게시물을 삭제하는 메소드
	 * @param num
	 * @param in_passwd
	 * @return
	 * @History  : - 
	 */
	public boolean delete_HP_Article(int num, String in_passwd){
		boolean r_value = false;
		DAO_BOARD_INFO dao_board = new DAO_BOARD_INFO();
		String db_passwd = dao_board.select_HP_Passwd(num);
		
		if(!db_passwd.equalsIgnoreCase(in_passwd)){
			r_value = false;
		}else{				
			Vector v_list = new Vector();
			v_list.addElement(num);
			r_value = dao_board.delete_HP_Article(v_list);
		}		
		return r_value;
	}
	
	/**
	 * @MethodName  : insert_NT_Article
	 * @Date   : 2011. 3. 17. 
	 * @MethodDescription : NT 게시글 입력하는 메소드
	 * @param article
	 * @return
	 * @throws SQLException
	 * @History  : - 
	 */
	public boolean insert_NT_Article(HttpServletRequest request) throws SQLException{			
		
		boolean r_value = false;
		DAO_BOARD_INFO dao_board = new DAO_BOARD_INFO();
		Board_Info article = new Board_Info();	    
		
		int num=Integer.parseInt(request.getParameter("num"));//article.getNum();
		int ref=Integer.parseInt(request.getParameter("ref"));//article.getRef();
		int re_step=Integer.parseInt(request.getParameter("re_step"));//article.getRe_step();
		int re_level=Integer.parseInt(request.getParameter("re_level"));//article.getRe_level();
		
		
		//int number=0;
		//번호 가져오기
		int number = dao_board.select_NT_MaxNumber();
		
		if(num!=0){//Ref UPDATE
			boolean re_value = dao_board.update_NT_Reference(ref, re_step);	
			re_step = re_step + 1;
			re_level = re_level + 1;
		}else{
			ref = number;
			re_step = 0;
			re_level = 0;
		}
			
		//게시물 입력하기	
		article.setNum(number);
		article.setWriter(ComUtil.convertKorean(request.getParameter("writer")));
		article.setEmail(request.getParameter("email"));
		article.setSubject(ComUtil.convertKorean(request.getParameter("subject")));
		article.setPasswd(request.getParameter("passwd"));
		article.setReg_date(ComUtil.getTimeNow());
		article.setRef(ref);
		article.setRe_step(re_step);
		article.setRe_level(re_level);
		article.setContent(ComUtil.convertKorean(request.getParameter("content")));
		article.setIp(request.getRemoteAddr());
		r_value = dao_board.insert_NT_Article(article);	
		return r_value;
	}
	
	/**
	 * @MethodName  : update_NT_Article
	 * @Date   : 2011. 3. 22. 
	 * @MethodDescription : NT 게시판의 게시물 업데이트 메소드
	 * @param request
	 * @return
	 * @throws SQLException
	 * @History  : - 
	 */
	public boolean update_NT_Article(HttpServletRequest request) throws SQLException{
		boolean r_value = false;
		DAO_BOARD_INFO dao_board = new DAO_BOARD_INFO();
		
		int num=Integer.parseInt(request.getParameter("num"));
		
		String db_passwd = dao_board.select_NT_Passwd(num);
		String in_passwd = request.getParameter("passwd");
		
		String in_writer = ComUtil.convertKorean(request.getParameter("writer"));
		String in_email = request.getParameter("email");
		String in_subject = ComUtil.convertKorean(request.getParameter("subject"));
		String in_content = ComUtil.convertKorean(request.getParameter("content"));
		
		
		if(!db_passwd.equalsIgnoreCase(in_passwd)){
			r_value = false;
		}else{
			Vector v_list = new Vector();
			v_list.addElement(in_writer);
			v_list.addElement(in_email);
			v_list.addElement(in_subject);
			v_list.addElement(in_passwd);
			v_list.addElement(in_content);
			v_list.addElement(num);
			
			r_value = dao_board.update_NT_Article(v_list);
		}		
		return r_value;
	}
	
	/**
	 * @MethodName  : select_NT_Article
	 * @Date   : 2011. 3. 17. 
	 * @MethodDescription : NT 게시글 가져오는 메소드
	 * @return
	 * @throws SQLException
	 * @History  : - 
	 */
	public Vector select_NT_Article() throws SQLException{			
				
		Vector vecList = new Vector();
		DAO_BOARD_INFO info = new DAO_BOARD_INFO();
		vecList = info.select_NT_Article();
		return vecList;

	}
	
	/**
	 * @MethodName  : select_NT_Article_Content
	 * @Date   : 2011. 3. 21. 
	 * @MethodDescription : NT 게시물 내용을 가져오는 메소드
	 * @param num
	 * @return
	 * @History  : - 
	 */
	public Board_Info select_NT_Article_Content(int num){
		Board_Info b_info = new Board_Info();
		DAO_BOARD_INFO info = new DAO_BOARD_INFO();
		b_info = info.select_NT_ArticleContent(num);
		
		return b_info;
	}
	
	/**
	 * @MethodName  : delete_NT_Article
	 * @Date   : 2011. 3. 22. 
	 * @MethodDescription : NT 게시물을 삭제하는 메소드
	 * @param num
	 * @param in_passwd
	 * @return
	 * @History  : - 
	 */
	public boolean delete_NT_Article(int num, String in_passwd){
		boolean r_value = false;
		DAO_BOARD_INFO dao_board = new DAO_BOARD_INFO();
		String db_passwd = dao_board.select_NT_Passwd(num);
		
		if(!db_passwd.equalsIgnoreCase(in_passwd)){
			r_value = false;
		}else{				
			Vector v_list = new Vector();
			v_list.addElement(num);
			r_value = dao_board.delete_NT_Article(v_list);
		}		
		return r_value;
	}
}
