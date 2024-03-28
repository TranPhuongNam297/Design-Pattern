package com.example.app_cnpmnc_da_hethongatm.Proxy;

import android.content.Context;

interface ProxyInterface {

    public abstract void getOTP(Context context1,String number);
    public abstract void UpdatePass(String pass1,Context context,String number,String pass2);
}
