package com.miotlink.commom.network.mlcc.parse;

import java.util.Map;

/**
 * TTP content parse interface
 * 
 * @date 2014-06-16
 * @author szf
 * 
 */
interface ParseMLCCInterface<T> {

	/**
	 * 
	 * @param contentMap
	 * @return ���ص��ǽ���֮��Ķ������� ����������ʵ�������
	 * @throws Exception
	 */
	T parse(Map<String, String> contentMap) throws Exception;

	/**
	 * 
	 * @return ����TTP����Ψһ��ʶ 
	 */
	String getMLCCCode();
	
	
}
