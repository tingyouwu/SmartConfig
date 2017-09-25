package com.esp.smartconfig.sweet;

public class SessionFailedCallback extends SweetCallback {

    @Override
    public void onClick(SweetAlertDialog dialog) {
        dialog.dismiss();
        // TODO: 2017/1/12 这个没有使用到，需要用的时候再配一下CrmAppcontext
        //CrmAppContext.getInstance().registerLogion();
    }

}