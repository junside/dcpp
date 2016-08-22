package nfri.dcpp.com.util;

import java.util.HashMap;

/**
 * <p>
 * Title: 데이터 컨트롤을 위한 공통의 abstract class
 * </p>
 * <p>
 * Description:데이터 컨트롤을 위한 공통의 메소드 정의
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: NFRI. 기초플라즈마연구팀
 * </p>
 * 
 * @author Park Jun-Hyoung(JUNSiDE)
 * @version 1.0
 */
public class ComData implements Cloneable{
	private String value = null;
	private HashMap<String, Object> map = new HashMap<String, Object>();
	
	/**
	 * @MethodName : setAttributeStr
	 * @Desc : String 저장
	 * @param key
	 * @param value
	 */
	public void setAttributeStr( String key, String value )
	{
		map.put(key, value);
	}

	/**
	 * @MethodName : setAttributeObj
	 * @Desc : Object 저장
	 * @param key
	 * @param value
	 */
	public void setAttributeObj( String key, Object value )
	{
		map.put(key, value);
	}
	
	/**
	 * @MethodName : setAttributeInt
	 * @Desc : Int 저장
	 * @param key
	 * @param value
	 */
	public void setAttributeInt( String key, int value )
	{
		map.put(key, Integer.toString(value));
	}
	
	/**
	 * @MethodName : setAttributeBool
	 * @Desc : Boolean 저장
	 * @param key
	 * @param value
	 */
	public void setAttributeBool( String key, boolean value )
	{
		map.put(key, new Boolean(value));
	}
	
	/**
	 * 항목 추출
	 * 
	 * @param key
	 * @return
	 */
	public String getAttributeStr( String key )
	{
		if (map.containsKey(key))
		{
			return (String) map.get(key);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * 항목 추출
	 * 
	 * @param key
	 * @return
	 */
	public Object getAttributeObj( String key )
	{
		if (map.containsKey(key))
		{
			return map.get(key);
		}
		else
		{
			return null;
		}
	}
	
	public int getAttributeInt( String key )
	{
		if (map.containsKey(key))
		{
			return Integer.parseInt((map.get(key).toString()));
		}
		else
		{
			return 0;
		}
	}
	
	/**
	 * 항목 추출
	 * 
	 * @param key
	 * @return
	 */
	public boolean getAttributeBool( String key )
	{
		if (map.containsKey(key))
		{
			return ((Boolean) (map.get(key))).booleanValue();
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 보여줄 데이터의 String 값 리턴
	 */
	@Override
	public String toString()
	{
		return this.value;
	}
	
	public int getSize()
	{
		return map.size();
	}
}
