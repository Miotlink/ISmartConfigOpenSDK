package com.miotlink.result;


import com.miotlink.sdk.entity.DeviceInfo;

import java.util.List;

/**
 * 
 * @author Administ
 *
 */
public interface SearchResult {
	

	public void success(List<DeviceInfo> deviceInfos);
	
	public void successMac(DeviceInfo deviceInfo);
	
	public void fail();
}
