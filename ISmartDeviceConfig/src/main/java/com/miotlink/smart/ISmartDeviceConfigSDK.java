package com.miotlink.smart;

import android.content.Context;
import android.text.TextUtils;

import com.miotlink.result.ConfigResult;
import com.miotlink.sdk.MiotHFSmartConfig;
import com.miotlink.sdk.MiotSmartConfig;
import com.miotlink.sdk.Miot_AP_SmartConfig;
import com.miotlink.sdk.util.NoFormatConsts;
import com.miotlink.smart.listener.ISmartConfigListener;
import com.miotlink.smart.listener.ISmartInitListener;
import com.miotlink.smart.utils.Utils;

import java.util.Map;

public class ISmartDeviceConfigSDK implements ConfigResult {

   private static volatile ISmartDeviceConfigSDK instance=null;

    public static synchronized ISmartDeviceConfigSDK getInstance() {
        if (instance==null){
            synchronized (ISmartDeviceConfigSDK.class){
                if (instance==null){
                    instance=new ISmartDeviceConfigSDK();
                }
            }
        }
        return instance;
    }
    private Context mContext;
    private String appKey;
    private String address="";
    private String uart="";
    private int port=0;
    private MiotSmartConfig miotSmartConfig=null;
    private MiotHFSmartConfig miotHFSmartConfig=null;
    private ISmartConfigListener mISmartConfigListener=null;

    /**
     *
     * @param mContext
     * @param appKey 默认妙联
     * @param iSmartConfigInitListener
     * @throws Exception
     */
    public void init(Context mContext, String appKey, ISmartInitListener iSmartConfigInitListener)throws Exception{
        this.mContext=mContext;
    }

    /**
     *
     * @param configType 配置类型
     * @param ssid 路由器名称
     * @param password 路由器密码
     * @param timeOut 配置超时时间
     * @param iSmartConfigListener
     * @throws Exception
     */
    public void start(int configType,
                      String ssid,
                      String password,
                      int timeOut, ISmartConfigListener iSmartConfigListener)throws Exception{
        this.mISmartConfigListener=iSmartConfigListener;
        if (configType==0 || TextUtils.isEmpty(ssid) || TextUtils.isEmpty(password)){
            throw new Exception("params is null");
        }
        if (timeOut<60){
            throw new Exception("time not set <60");
        }
        switch (configType){
            case Utils.Consts.SMART_TYPE_SA_1:
            case Utils.Consts.SMART_TYPE_7681:
            case Utils.Consts.SMART_TYPE_SA_4:
                NoFormatConsts.setPlatform(address, port);
                NoFormatConsts.setUart(uart);
                miotSmartConfig=MiotSmartConfig.getInstance(mContext);
                miotSmartConfig.init(appKey);
                miotSmartConfig.SmartConfig(configType, ssid, password, timeOut);
                miotSmartConfig.setConfigResult(this);
                break;
            case Utils.Consts.SMART_TYPE_HF:
                NoFormatConsts.addSmartConfigHF(address,port,uart);
                miotHFSmartConfig=MiotHFSmartConfig.getInstance(mContext);
                miotHFSmartConfig.hFSmartConfig(configType,ssid,password,timeOut);
                miotHFSmartConfig.setConfigResult(this);
                break;
            default:
                throw new Exception("configType error");

        }
    }

    /**
     *
     * @param configType 配置类型
     * @param ssid 路由器名称
     * @param password 路由器密码
     * @param configInfo 配置信息JSON格式
     * @param timeOut 配置超时时间
     * @param iSmartConfigListener
     * @throws Exception
     */
    public void start(int configType,
                      String ssid,
                      String password,
                      String configInfo,
                      int timeOut, ISmartConfigListener iSmartConfigListener)throws Exception{
        this.mISmartConfigListener=iSmartConfigListener;
        if (configType==0 || TextUtils.isEmpty(ssid) || TextUtils.isEmpty(password)){
            throw new Exception("params is null");
        }
        if (timeOut<60){
            throw new Exception("time not set <60");
        }
        switch (configType){
            case Utils.Consts.SMART_TYPE_SA_1:
            case Utils.Consts.SMART_TYPE_7681:
            case Utils.Consts.SMART_TYPE_SA_4:
                miotSmartConfig=MiotSmartConfig.getInstance(mContext);
                miotSmartConfig.init(appKey);
                miotSmartConfig.SmartConfig(configType, ssid, password,configInfo, timeOut);
                miotSmartConfig.setConfigResult(this);
                break;
            case Utils.Consts.SMART_TYPE_HF:
                miotHFSmartConfig=MiotHFSmartConfig.getInstance(mContext);
                miotHFSmartConfig.hFSmartConfig(configType,ssid,password,configInfo,timeOut);
                miotHFSmartConfig.setConfigResult(this);
                break;
        }
    }

    /**
     * 设置客户平台配置信息
     * @param platfrom 客户平台地址
     * @param port 客户平台端口
     * @throws Exception
     */
    public void setPlatfrom(String platfrom,int port)throws Exception{
        this.address=platfrom;
        this.port=port;
    }

    /**
     * 设置配置波特率
     * @param uart
     * @throws Exception
     */
    public void setUart(String uart)throws Exception{
        this.uart=uart;
    }



    public void onStop()throws Exception{
        if (miotSmartConfig!=null){
            miotSmartConfig.stopSmartConfig();
        }
        if (miotHFSmartConfig!=null){
            miotHFSmartConfig.stopHfSmartConfig();
        }
    }

    public void onDestory()throws Exception{

        if (miotSmartConfig!=null){
            miotSmartConfig.stopSmartConfig();
        }
        if (miotHFSmartConfig!=null){
            miotHFSmartConfig.stopHfSmartConfig();
        }
    }

    @Override
    public void resultOk(Map<String, Object> map) {
        if (mISmartConfigListener!=null){
            try {
                mISmartConfigListener.onISmartConfigReceiver(1, "success",map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void resultFail(int failCode, String failReason) {
        if (mISmartConfigListener!=null){
            try {
                mISmartConfigListener.onISmartConfigReceiver(failCode, failReason,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
