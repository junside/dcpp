package nfri.dcpp.com.lang;

import java.net.URLDecoder;
import java.net.URLEncoder;


public final class Lang
{
    private Lang()
    {
    }
 // hanEncoding=========================================
  	public static String hanEncode(String src) {
    	if(src == null) {   return "";   }
        String result = null;
    	try {
      		result = URLEncoder.encode(ksc(src));
    	}catch(Exception e) {
      		result = null;
    	}
    	return result;  
  	}
  
  	// hanDecoding ========================================
  	public static String hanDecode(String src) {
		if(src == null) {  return "";   }
        String result = null;
    	try {
      		result = han(URLDecoder.decode(src));
    	}catch(Exception e) {
      		result = null;
    	}
    	return result;    
  	}
  
  	// KSC ================================================
  	public static String han(String str)  {
    	if(str == null) {  return new String("");    }
        try{  
      		str = new String(str.getBytes("8859_1"),"KSC5601");
    	}catch(Exception e){  }
    	return str;
  	} 

	// ISO ================================================
  	public static String ksc(String str)  {
    	if(str == null) { return new String("");  }
        try{
      		str = new String(str.getBytes("KSC5601"),"8859_1");
    	}catch(Exception e){    }
    	return str;
  	}  

}
