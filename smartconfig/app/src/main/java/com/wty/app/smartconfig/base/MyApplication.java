package com.wty.app.smartconfig.base;

import android.app.Application;

import com.wty.app.smartconfig.utils.PreferenceUtil;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		PreferenceUtil.init(this);
	}

}
