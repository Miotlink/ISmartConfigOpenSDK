package com.miotlink.commom.network.mlcc.parse;

import com.miotlink.commom.network.mlcc.utils.MLCCCodeConfig;
import com.miotlink.commom.network.mlcc.utils.MLCCReflectUtils;
import com.miotlink.common.network.mlcc.pojo.response.RespGetGpioAck;

import java.util.Map;



class ParseMLCCImpl_GetGpioAck implements
		ParseMLCCInterface<RespGetGpioAck> {
	private ParseMLCCImpl_GetGpioAck(){}
	private static ParseMLCCImpl_GetGpioAck parseMLCCImpl_GetGpioAck = null;
	public static ParseMLCCImpl_GetGpioAck getInstance(){
		if(parseMLCCImpl_GetGpioAck == null){
			synchronized (ParseMLCCImpl_GetGpioAck.class) {
				if(parseMLCCImpl_GetGpioAck == null){
					parseMLCCImpl_GetGpioAck = new ParseMLCCImpl_GetGpioAck();
				}
			}
		}
		return parseMLCCImpl_GetGpioAck;
	}
	
	@Override
	public RespGetGpioAck parse(Map<String, String> contentMap)
			throws Exception {
		RespGetGpioAck respGetGpioAck = (RespGetGpioAck) MLCCReflectUtils.setBeanUtils(contentMap,
				RespGetGpioAck.class);
		respGetGpioAck.make(contentMap);
		return respGetGpioAck;
	}

	@Override
	public String getMLCCCode() {
		return MLCCCodeConfig.MLCCCodeReturn.GET_GPIO_ACK;
	}

}
