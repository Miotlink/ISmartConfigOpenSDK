package com.miotlink.commom.network.mlcc.parse;

import com.miotlink.commom.network.mlcc.utils.MLCCCodeConfig;
import com.miotlink.commom.network.mlcc.utils.MLCCReflectUtils;
import com.miotlink.common.network.mlcc.pojo.response.RespSetGpioAck;

import java.util.Map;



class ParseMLCCImpl_SetGpioAck implements ParseMLCCInterface<RespSetGpioAck> {
	private ParseMLCCImpl_SetGpioAck(){}
	private static ParseMLCCImpl_SetGpioAck parseMLCCImpl_SetGpioAck = null;
	public static ParseMLCCImpl_SetGpioAck getInstance(){
		if(parseMLCCImpl_SetGpioAck == null){
			synchronized (ParseMLCCImpl_SetGpioAck.class) {
				if(parseMLCCImpl_SetGpioAck == null){
					parseMLCCImpl_SetGpioAck = new ParseMLCCImpl_SetGpioAck();
				}
			}
		}
		return parseMLCCImpl_SetGpioAck;
	}
	
	
	@Override
	public RespSetGpioAck parse(Map<String, String> contentMap)
			throws Exception {
		RespSetGpioAck respSetGpioAck = (RespSetGpioAck) MLCCReflectUtils.setBeanUtils(contentMap, RespSetGpioAck.class);
		respSetGpioAck.make(contentMap);
		return respSetGpioAck;
	}

	@Override
	public String getMLCCCode() {
		return MLCCCodeConfig.MLCCCodeReturn.SET_GPIO_ACK;
	}

}
