package com.esp.smartconfig.sweet;

import java.io.Serializable;

public class SweetAlertButton implements Serializable {
    public SweetAlertButton(String text, SweetCallback callback){
            this.text = text;
            this.callback = callback;
        }
        public String text;
        public SweetCallback callback;
    }