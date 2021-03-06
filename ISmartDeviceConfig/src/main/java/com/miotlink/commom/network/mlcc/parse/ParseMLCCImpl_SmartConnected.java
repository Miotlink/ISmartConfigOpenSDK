package com.miotlink.commom.network.mlcc.parse;

import com.miotlink.commom.network.mlcc.utils.MLCCCodeConfig;
import com.miotlink.commom.network.mlcc.utils.MLCCReflectUtils;
import com.miotlink.common.network.mlcc.pojo.response.RespSmartConnectedAck;

import java.util.Map;



public class ParseMLCCImpl_SmartConnected implements ParseMLCCInterface<RespSmartConnectedAck> {

	public static ParseMLCCImpl_SmartConnected parseMLCCImpl_SmartConnected;
	
	public static ParseMLCCImpl_SmartConnected getInstance(){
		if (parseMLCCImpl_SmartConnected == null) {
			synchronized (ParseMLCCImpl_SmartConnected.class) {
				if (parseMLCCImpl_SmartConnected == null) {
					parseMLCCImpl_SmartConnected = new ParseMLCCImpl_SmartConnected();
				}
			}
		}
		return parseMLCCImpl_SmartConnected;
	}
	
	@Override
	public RespSmartConnectedAck parse(Map<String, String> contentMap) throws Exception {
		RespSmartConnectedAck respSmartConnectedAck = (RespSmartConnectedAck) MLCCReflectUtils
				.setBeanUtils(contentMap, RespSmartConnectedAck.class);
		respSmartConnectedAck.make(contentMap);
		return respSmartConnectedAck;
	}

	@Override
	public String getMLCCCode() {
		// TODO Auto-generated method stub
		return MLCCCodeConfig.MLCCCodeReturn.SMART_CONNECTED;
	}

}
