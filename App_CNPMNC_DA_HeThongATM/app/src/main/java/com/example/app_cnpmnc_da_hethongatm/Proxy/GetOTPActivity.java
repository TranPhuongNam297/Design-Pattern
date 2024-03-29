package com.example.app_cnpmnc_da_hethongatm.Proxy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_cnpmnc_da_hethongatm.Activities.formUserRegister;
import com.example.app_cnpmnc_da_hethongatm.Extend.DbHelper;
import com.example.app_cnpmnc_da_hethongatm.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class GetOTPActivity extends AppCompatActivity {
    EditText input_phone;
    Button btn_getotp;
    ProgressBar wait_otp;
    ProxyInterface otpsend = new OtpProxy();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);
        InitUI();
        GetOTP();
    }
    private void InitUI(){
        input_phone = findViewById(R.id.input_phone);
        btn_getotp = findViewById(R.id.btn_getotp);
        wait_otp=findViewById(R.id.wait_otp);
    }
    private void GetOTP(){
        btn_getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!input_phone.getText().toString().trim().isEmpty()){
                    if(input_phone.getText().toString().trim().length() == 9){
                        wait_otp.setVisibility(View.VISIBLE);
                        btn_getotp.setVisibility(View.INVISIBLE);
                        otpsend.getOTP(GetOTPActivity.this,input_phone.getText().toString().trim());
                    }
                    else {
                        Toast.makeText(GetOTPActivity.this,"Vui lòng nhập đúng định dạng sdt",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });
    }
//    private boolean CheckInputnumber(){
//        DbHelper.firebaseDatabase.getReference("KhachHang").orderByChild("SoDienThoai").equalTo("0"+input_phone.getText().toString().trim())
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if(snapshot.exists()){
//                            flag = true;
//                        }
//                        else {
//                            Toast.makeText(GetOTPActivity.this,"SDT không tồn tại trên hệ thống",Toast.LENGTH_SHORT).show();
//                            flag = false;
//                        }
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//        return flag;
//    }
}