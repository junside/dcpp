package nfri.dcpp.com.util;

/**
 * <p>
 * Title: Object 데이터 컨트롤을 위한 공통의 abstract class
 * </p>
 * <p>
 * Description: Object 데이터 컨트롤 위한 메소드 정의
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
abstract class ComDataObject {
	Object[] value;
	
	
	public Object[] setObjectData( Object data )
	{
		return value;
	}
}
