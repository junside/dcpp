package nfri.dcpp.com.control;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Ctr_Basic extends HttpServlet { 
	
	@Override
	public void init(ServletConfig config) throws ServletException { 
	      super.init(config);             
	   } 
	    
	   @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {          
	      doProcess(request, response); 
	   } 
	    
	   @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {          
	      doProcess(request, response);       
	   } 
	    
	   public void doProcess(HttpServletRequest request, HttpServletResponse response) { 
		   System.out.println("Ctr_Basic 컨트롤러 호출");
	   } 
}
