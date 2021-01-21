package com.miotlink.sdk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.os.Handler;

import com.miotlink.commom.network.mlcc.agent.Parse;
import com.miotlink.common.network.mlcc.pojo.response.RespSearchAck;
import com.miotlink.result.SearchResult;
import com.miotlink.sdk.entity.DeviceInfo;
import com.miotlink.sdk.socket.MiotlinkTools;


/**
 * 搜索设备方法 根据mac 地址搜索 
 * @author 
 *
 */
public class MiotSearchDevice {

	private List<DeviceInfo> list = null;

	private DeviceInfo deviceInfo = null;

	private String mac = "";

	private boolean isRuning = true;

	private SearchResult searchResult = null;

	private Context context;

	public static MiotSearchDevice instance = null;
	
	private int search=0;

	public static synchronized MiotSearchDevice getInstance(Context context) {
		if (instance == null) {
			instance = new MiotSearchDevice(context);

		}
		return instance;
	}

	public MiotSearchDevice(Context context) {
		this.context = context;
	}

	public void setSearchResult(SearchResult searchResult) {
		this.searchResult = searchResult;
	}

	/**
	 * 根据mac 搜索设备
	 * 
	 * @param mac
	 */
	public void searchDevice(String mac) {
		this.mac=mac;
		MiotlinkTools.initial(context, 1);
		MiotlinkTools.setSearcHandler(handler);
		list=new ArrayList<DeviceInfo>();
		search=1;
		new MyThread().start();

	}

	/**
	 * 搜索路由器下 所有的设备
	 */
	public void searchAllDevice() {
		search=2;
		MiotlinkTools.initial(context, 1);
		MiotlinkTools.setSearcHandler(handler);
		list=new ArrayList<DeviceInfo>();
		new MyThread().start();
	}

	class MyThread extends Thread {
		@Override
		public void run() {
			MiotlinkTools.MiotSearch();
			while (isRuning) {
				try {
					sleep(3000);
					isRuning = false;
					handler.sendEmptyMessage(1005);
				} catch (Exception e) {
				}
			}
			super.run();
		}
	}
	

	private RespSearchAck respSearchAck;
	@SuppressWarnings("unchecked")
	Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case MiotlinkTools.MW_SET_SEARCH_ACK:
				Map<String, String> map = (Map<String, String>) msg.obj;
				try {
					respSearchAck = (RespSearchAck) Parse.parseMLCCPackage(map);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (!mac.equals("")) {
					if (respSearchAck.getMac().equals(mac)) {
						deviceInfo=new DeviceInfo();
						deviceInfo.setIp(respSearchAck.getModeAndIp().getIp());
						deviceInfo.setMac(respSearchAck.getMac());
						deviceInfo.setUart(respSearchAck.getUartInfoConfig().getiBaud()+"");
					}
				} else {
					DeviceInfo deviceInfo=new DeviceInfo();
					deviceInfo.setIp(respSearchAck.getModeAndIp().getIp());
					deviceInfo.setMac(respSearchAck.getMac());
					deviceInfo.setUart(respSearchAck.getUartInfoConfig().getiBaud()+"");
					list.add(deviceInfo);
				}
				break;
			case 1005:
				if (search==1&&searchResult!=null) {
					if (deviceInfo==null) {
						searchResult.fail();
						return;
					}
					searchResult.successMac(deviceInfo);
				}
				if (search==2&&searchResult!=null) {
					if (list==null&&list.size()==0) {
						searchResult.fail();
						return;
					}
					searchResult.success(list);
				}
				
				break;

			}
		};
	};

}
