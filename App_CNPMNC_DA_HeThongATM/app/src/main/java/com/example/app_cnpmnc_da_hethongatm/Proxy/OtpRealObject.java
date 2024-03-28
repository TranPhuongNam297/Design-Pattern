package com.example.app_cnpmnc_da_hethongatm.Proxy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.app_cnpmnc_da_hethongatm.Activities.LoginActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OtpRealObject implements ProxyInterface {
    FirebaseAuth mauth = FirebaseAuth.getInstance();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    @Override
    public void getOTP(Context context1, String number) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mauth)
                .setPhoneNumber("+84"+number)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity((Activity) context1)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        Log.d("onVerificationCompleted", "onVerificationCompleted: ");
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(context1,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        Intent intent = new Intent(context1,VerifyOTPActivity.class);
                        intent.putExtra("mobile",number);
                        intent.putExtra("OTP",s);
                        Log.d("onCodeSent", "onCodeSent: "+s);
                        context1.startActivity(intent);
                    }
                }).build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    @Override
    public void UpdatePass(String pass1,Context context,String number,String pass2){
        Map<String, Object> map = new HashMap<>();
        map.put("MatKhau",pass1);
        firebaseDatabase.getReference("KhachHang").child(number).updateChildren(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(context, LoginActivity.class);
                        context.startActivity(intent1);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context,"Mật khẩu xác nhận phải giống nhau",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
