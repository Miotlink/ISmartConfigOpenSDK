package com.miotlink.commom.network.mlcc.parse;

import com.miotlink.commom.network.mlcc.utils.MLCCCodeConfig;
import com.miotlink.commom.network.mlcc.utils.MLCCStringUtils;
import com.miotlink.common.network.mlcc.pojo.response.RespBaseAck;

import java.util.Map;



/**
 * @���ܣ�����TTP���ݵ�������Ӧ�ã������Ψһ�Ķ���Ľ�����
 * @author szf
 * 
 */
public class MLCCParseAgent extends BaseParseMLCCService {
	private static volatile MLCCParseAgent applicationContextByParseTTP = null;

	private MLCCParseAgent() {
	}

	/**
	 * @function ʵ��������TTP����Ӧ��������
	 * @return ʵ��������
	 */
	public static MLCCParseAgent getInstance() {
		if (applicationContextByParseTTP == null) {
			synchronized (MLCCParseAgent.class) {
				if (applicationContextByParseTTP == null) {
					applicationContextByParseTTP = new MLCCParseAgent();
				}
			}
		}
		return applicationContextByParseTTP;
	}

	/**
	 * 
	 * @param mlccContentPackage
	 *            vsp����������� ttpContent���ă��ݣ�����vsp������������
	 * @param startPos
	 *            byte���鿪ʼλ��
	 * @param length
	 *            byte���ݵ�ʵ�ʽ�������
	 * @return ������������TTP Code������
	 * @throws Exception
	 *             when parse error or params error
	 */
	public RespBaseAck parse(byte[] mlccContentPackage, int startPos, int length)
			throws Exception {
		if (length < 1) {
			throw new Exception("Param is error [ttpLength < 1]");
		}
		if (mlccContentPackage == null || mlccContentPackage.length < 1) {
			throw new NullPointerException(
					"Param is error [ttpPackage is null or ttpPackage length < 1]");
		}
		if (mlccContentPackage.length < length) {
			throw new Exception("Param is error [ttpPackage length '"
					+ mlccContentPackage.length + "' < param ttpLength '"
					+ length + "']");
		}


		Map<String, String> mlccContentMap = parseMLCC(mlccContentPackage,
				startPos, length);
		return parse(mlccContentMap);
	}

	/**
	 * 
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public RespBaseAck parse(Map<String, String> requestMap) throws Exception {
		String mlccCode = MLCCStringUtils.getStringByCodeFromMap(requestMap,
				MLCCCodeConfig.MLCCKeyCodeConfig.CODE_NAME);

		ProxyParseMLCCEnum proxyEnum = ProxyParseMLCCEnum
				.getByMLCCCodeName(mlccCode);
		if (proxyEnum == null) {
			throw new NullPointerException(
					"There is not proxy parse impl by mlcc code[" + mlccCode
							+ "] cause by ProxyParseTTPEnum is null.");
		}
		ParseMLCCInterface<?> parseTTPInterface = proxyEnum
				.getParseTTPInterface();
		if (parseTTPInterface == null) {
			throw new NullPointerException(
					"There is not proxy parse impl by ttp code[" + mlccCode
							+ "] cause by ParseTTPInterface is null.");
		}
		return (RespBaseAck) parseTTPInterface.parse(requestMap);
	}
}
