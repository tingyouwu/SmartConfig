package com.wty.app.smartconfig.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author wty
 * ShareP 工具类  请在Application oncreate初始化
 **/
public class PreferenceUtil {

	private static volatile PreferenceUtil sInstance = null;

	private static String PREFERENCES_NAME = "Preferences";//preference名字
	public static String IP = "ip";//ip地址
	public static String PORT = "port";//端口
	public static String PSW = "psw";//端口

	private SharedPreferences mSharedPreferences;

	/**
	 * 单例模式，获取instance实例
	 * @return
	 */
	public synchronized static PreferenceUtil getInstance() {
		if (sInstance == null) {
			throw new RuntimeException("please init first!");
		}
		return sInstance;
	}

	/**
	 * context用AppContext
	 **/
	public static void init(Context context) {
		if (sInstance == null) {
			synchronized (PreferenceUtil.class) {
				if (sInstance == null) {
					sInstance = new PreferenceUtil(context);
				}
			}
		}
	}

	private PreferenceUtil(Context context) {
		mSharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
	}

	public String getIP(){
		return mSharedPreferences.getString(IP,"192.168.4.1");
	}

	public String getPSW(){
		return mSharedPreferences.getString(PSW,"");
	}

	public int getPort(){
		return mSharedPreferences.getInt(PORT,8266);
	}

	/**
	 *	功能描述:保存到sharedPreferences
	 */
	public void writePreferences(String key, String value){
		mSharedPreferences.edit().putString(key, value).commit();// 提交修改;
	}

	public void writePreferences(String key, Boolean value){
		mSharedPreferences.edit().putBoolean(key, value).commit();// 提交修改;
	}

	public void writePreferences(String key, int value){
		mSharedPreferences.edit().putInt(key, value).commit();// 提交修改;
	}

}
