package com.wty.app.smartconfig.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.wty.app.smartconfig.R;

import java.util.List;

/**
 * 功能描述：wifi列表
 * @author wty
 */
public class WifiListAdapter extends BaseViewCommonAdapter<ScanResult> {

    private String currentWifiSSID;

    public WifiListAdapter(Context context, List<ScanResult> data){
        super(context, R.layout.item_wifi_list,data);
    }

    @Override
    protected void convert(BaseViewHolder holder, ScanResult scanResult, int position) {
        TextView tv_name = holder.getView(R.id.wifi_name);

        if(!TextUtils.isEmpty(currentWifiSSID) && (currentWifiSSID.equals(scanResult.SSID) || currentWifiSSID.equals("\"" + scanResult.SSID + "\""))){
            tv_name.setTextColor(mContext.getResources().getColor(R.color.app_main_yellow));
            tv_name.setText(scanResult.SSID+"\n(已连接)");
        }else{
            tv_name.setText(scanResult.SSID);
            tv_name.setTextColor(mContext.getResources().getColor(R.color.white));
        }

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                mContext.startActivity(intent);
            }
        });
    }

    public void setCurrentWifiSSID(String ssid){
        this.currentWifiSSID = ssid;
    }
}
