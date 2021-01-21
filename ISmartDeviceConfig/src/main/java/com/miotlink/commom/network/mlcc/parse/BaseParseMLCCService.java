package com.miotlink.commom.network.mlcc.parse;

import com.miotlink.commom.network.mlcc.utils.MLCCStringUtils;
import com.miotlink.common.network.mlcc.pojo.response.RespBaseAck;

import java.util.Map;



/**
 * 
 * @function ����MLCC contentӦ�������Ļ�����
 * @date 2014-06-16
 * @author szf
 * 
 */
abstract class BaseParseMLCCService {
	
	abstract RespBaseAck parse(byte[] ttpPackage, int startPos, int length)
			throws Exception;

	/**
	 * 
	 * @param mlccContentPackage
	 * @param startPos
	 * @param length
	 * @return
	 * @throws Exception
	 */
	Map<String, String> parseMLCC(byte[] mlccContentPackage, int startPos,
			int length) throws Exception {
		return MLCCStringUtils.parseMLCC2Map(mlccContentPackage, startPos,
				length);
	}

}
