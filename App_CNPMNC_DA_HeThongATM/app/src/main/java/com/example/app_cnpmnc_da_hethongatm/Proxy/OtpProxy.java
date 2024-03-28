package com.example.app_cnpmnc_da_hethongatm.Proxy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.app_cnpmnc_da_hethongatm.Extend.DbHelper;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class OtpProxy implements ProxyInterface {
    OtpRealObject realotp;
    public OtpProxy(){
        realotp = new OtpRealObject();
    }
    boolean flag = true;
    @Override
    public void getOTP(Context context1, String number) {
        flag = checkUserValid(context1,number);
        if(flag){
            realotp.getOTP(context1,number);
        }
        else {
            Toast.makeText(context1,"SDT không tồn tại trên hệ thống",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkUserValid(Context context,String number){
        DbHelper.firebaseDatabase.getReference("KhachHang").orderByChild("SoDienThoai").equalTo(number)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            flag = true;
                        }
                        else {
                            flag = false;
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return flag;
    }
    @Override
    public void UpdatePass(String pass1,Context context,String number,String pass2){
        if(pass1.equals(pass2)){
            realotp.UpdatePass(pass1,context,number,pass2);
        }
        else {
            Toast.makeText(context,"Mật khẩu không khớp",Toast.LENGTH_SHORT).show();
        }
    }

}
