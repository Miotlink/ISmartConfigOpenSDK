package com.miotlink.commom.network.mlcc.parse;

import com.miotlink.commom.network.mlcc.utils.MLCCCodeConfig;
import com.miotlink.commom.network.mlcc.utils.MLCCReflectUtils;
import com.miotlink.common.network.mlcc.pojo.response.RespSetUartAck;

import java.util.Map;



class ParseMLCCImpl_SetUartAck implements ParseMLCCInterface<RespSetUartAck> {

	private ParseMLCCImpl_SetUartAck() {
	}

	private static ParseMLCCImpl_SetUartAck parseMLCCImpl_SetUartAck = null;

	public static ParseMLCCImpl_SetUartAck getInstance() {
		if (parseMLCCImpl_SetUartAck == null) {
			synchronized (ParseMLCCImpl_SetUartAck.class) {
				if (parseMLCCImpl_SetUartAck == null) {
					parseMLCCImpl_SetUartAck = new ParseMLCCImpl_SetUartAck();
				}
			}
		}
		return parseMLCCImpl_SetUartAck;
	}

	@Override
	public RespSetUartAck parse(Map<String, String> contentMap)
			throws Exception {
		RespSetUartAck respSetUartAck = (RespSetUartAck) MLCCReflectUtils
				.setBeanUtils(contentMap, RespSetUartAck.class);
		respSetUartAck.make(contentMap);
		return respSetUartAck;
	}

	@Override
	public String getMLCCCode() {
		return MLCCCodeConfig.MLCCCodeReturn.SET_UART_ACK;
	}

}
