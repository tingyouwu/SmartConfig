package com.esp.smartconfig;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.esp.smartconfig.sweet.OnDismissCallbackListener;
import com.esp.smartconfig.sweet.SweetAlertDialog;

public class SweetDialogActivity extends AppCompatActivity {

    protected SweetAlertDialog dialog;
    protected SweetAlertDialog toastDialog;
    /**
     * 即时按下返回键是的次数当进度时
     */
    private int countDestroyDialogKeycodeBack = 0;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (toastDialog != null) {
            toastDialog.dismiss();
        }
        if (dialog != null) {
            dialog.dismiss();
        }
        TaskManager.getInstance().shutdown();
    }

    public void onToast(String msg){
        onToast(new OnDismissCallbackListener(msg, SweetAlertDialog.ERROR_TYPE));
    }

    public void onToast(OnDismissCallbackListener callback){
        try{
            if(dialog!=null && dialog.isShowing()){
                dialog.cancel();
            }
            if(toastDialog==null || !toastDialog.isShowing()){
                toastDialog = new SweetAlertDialog(this, callback.alertType);
                toastDialog.show();
            }
            toastDialog.setTitleText(callback.msg)
                    .setConfirmText("确定")
                    .setConfirmClickListener(callback)
                    .changeAlertType(callback.alertType);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void onToastSuccess(final String msg){
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                onToast(new OnDismissCallbackListener(msg,SweetAlertDialog.SUCCESS_TYPE));
            }
        });
    }

    public void onToastErrorMsg(final String msg){
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                onToast(new OnDismissCallbackListener(msg,SweetAlertDialog.ERROR_TYPE));
            }
        });
    }

    /**
     * 提示进度条 必须在handler或者控件触发 调用才有效
     *
     */
    public void loading(String msg) {
        try {

            if (this.isFinishing()) {
                return;
            }
            if (dialog == null || !dialog.isShowing()) {
                countDestroyDialogKeycodeBack = 0;
                dialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText(msg);

                dialog.show();

                dialog.setCancelable(false);
                dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialogs, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            countDestroyDialogKeycodeBack++;
                            if (countDestroyDialogKeycodeBack == 6)// 3次取消
                            {
                                dialog.dismiss();
                            }
                        }
                        return false;
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismissLoading(){
        try {
            if (dialog != null && dialog.isShowing()) {
                CountDownTimer timer = new CountDownTimer(500,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        dialog.dismiss();
                    }
                };
                timer.start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dismissLoading(final OnDismissCallbackListener callback){
        if(dialog!=null && dialog.isShowing()){
            new CountDownTimer(500,1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    dialog.setTitleText(callback.msg)
                            .setConfirmText("确定")
                            .setConfirmClickListener(callback)
                            .changeAlertType(callback.alertType);

                    dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                        @Override
                        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                            if(keyCode == KeyEvent.KEYCODE_BACK){
                                callback.onCallback();
                            }
                            return false;
                        }
                    });
                }
            }.start();


        }
    }

}
