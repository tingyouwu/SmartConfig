package com.esp.smartconfig;

import android.content.DialogInterface;

import com.esp.smartconfig.sweet.OnDismissCallbackListener;
import com.esp.smartconfig.sweet.SweetAlertDialog;

public abstract class SimpleDialogTask extends SimpleTask{
    SweetDialogActivity context;
    String loadingMessage;
    OnDismissCallbackListener dismissCallback;

    public SimpleDialogTask(SweetDialogActivity context){
        this.context = context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(context!=null){
            context.loading(loadingMessage);
            context.dialog
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialog) {
                            // 取消事件监听， 同时取消现场访问
                            System.out.println("setOnCancelListener..........");
                            if (!isCancelled()) {
                                System.out.println(" cancel:  CommandTaskEvent");
                                cancel(true);
                            }
                        }
                    });
        }

    }

    @Override
    protected Object doInBackground(String... params) {
        return onAsync();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        onResult(o);
        if(dismissCallback!=null){
            context.dismissLoading(dismissCallback);
        }else{
            context.dismissLoading();
        }
    }

    public void setDismissCallback(String msg,int type){
        this.dismissCallback = new OnDismissCallbackListener(msg, type);
    }

    public void setDismissCallback(String msg){
        this.setDismissCallback(msg, SweetAlertDialog.SUCCESS_TYPE);
    }

    public void setDismissCallback(OnDismissCallbackListener dismissCallback){
        this.dismissCallback = dismissCallback;
    }

    public void startTask(String loadingMessage) {
        this.loadingMessage = loadingMessage;
        super.startTask();
    }

    public void startTask(String loadingMessage,OnDismissCallbackListener dismissCallback) {
        this.loadingMessage = loadingMessage;
        this.dismissCallback = dismissCallback;
        super.startTask();
    }

    public abstract Object onAsync();

    public abstract void onResult(Object obj);


}
