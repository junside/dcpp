package nfri.dcpp.com.util;

import java.util.HashMap;

/**
 * <p>
 * Title: ������ ��Ʈ���� ���� ������ abstract class
 * </p>
 * <p>
 * Description:������ ��Ʈ���� ���� ������ �޼ҵ� ����
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: NFRI. �����ö��������
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
	 * @Desc : String ����
	 * @param key
	 * @param value
	 */
	public void setAttributeStr( String key, String value )
	{
		map.put(key, value);
	}

	/**
	 * @MethodName : setAttributeObj
	 * @Desc : Object ����
	 * @param key
	 * @param value
	 */
	public void setAttributeObj( String key, Object value )
	{
		map.put(key, value);
	}
	
	/**
	 * @MethodName : setAttributeInt
	 * @Desc : Int ����
	 * @param key
	 * @param value
	 */
	public void setAttributeInt( String key, int value )
	{
		map.put(key, Integer.toString(value));
	}
	
	/**
	 * @MethodName : setAttributeBool
	 * @Desc : Boolean ����
	 * @param key
	 * @param value
	 */
	public void setAttributeBool( String key, boolean value )
	{
		map.put(key, new Boolean(value));
	}
	
	/**
	 * �׸� ����
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
	 * �׸� ����
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
	 * �׸� ����
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
	 * ������ �������� String �� ����
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
