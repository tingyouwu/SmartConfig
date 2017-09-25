package com.esp.smartconfig.sweet;

public class OnDismissCallbackListener implements SweetAlertDialog.OnSweetClickListener {
        public String msg;
        public int alertType = SweetAlertDialog.SUCCESS_TYPE;
        public OnDismissCallbackListener(String msg){
            this.msg = msg;
        }

       public  OnDismissCallbackListener(String msg,int alertType){
            this.msg = msg;
            this.alertType = alertType;
        }

        @Override
        public void onClick(SweetAlertDialog sweetAlertDialog) {
            sweetAlertDialog.cancel();
            onCallback();
        }

        public void onCallback(){}

}