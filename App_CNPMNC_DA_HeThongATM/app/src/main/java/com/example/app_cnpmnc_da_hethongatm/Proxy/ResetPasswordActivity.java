package com.example.app_cnpmnc_da_hethongatm.Proxy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_cnpmnc_da_hethongatm.Activities.LoginActivity;
import com.example.app_cnpmnc_da_hethongatm.R;
import com.google.android.material.textfield.TextInputEditText;

public class ResetPasswordActivity extends AppCompatActivity {
    TextInputEditText pass,cm_pass;
    Button btn_submit;
    Intent intent;
    OtpProxy OTPSubmit = new OtpProxy();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpass);
        InitUI();
        submit();
    }
    private void InitUI(){
        pass = findViewById(R.id.ip_pass);
        cm_pass = findViewById(R.id.commit_pass);
        btn_submit = findViewById(R.id.btn_xacnhan);
    }
    private void submit(){
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = getIntent();
                String Sdt = intent.getStringExtra("Sdt");
                String pass1 = pass.getText().toString().trim();
                String cm_pass1 = cm_pass.getText().toString().trim();
                OTPSubmit.UpdatePass(pass1,ResetPasswordActivity.this,Sdt,cm_pass1);
            }
        });
    }
}
