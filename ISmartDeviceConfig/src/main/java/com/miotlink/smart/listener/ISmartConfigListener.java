package com.miotlink.smart.listener;

import java.util.Map;

public interface ISmartConfigListener {

    public void onISmartConfigReceiver(int errorCode, String errorMessage, Map<String,Object> data)throws Exception;
}
