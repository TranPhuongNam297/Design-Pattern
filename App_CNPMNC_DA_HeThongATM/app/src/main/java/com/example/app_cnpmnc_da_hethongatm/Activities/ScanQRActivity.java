package com.example.app_cnpmnc_da_hethongatm.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_cnpmnc_da_hethongatm.Command.Invoker;
import com.example.app_cnpmnc_da_hethongatm.Command.ScanCommand;
import com.example.app_cnpmnc_da_hethongatm.Command.TransferDataCommand;
import com.example.app_cnpmnc_da_hethongatm.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanQRActivity extends AppCompatActivity {

    private Invoker invoker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qractivity);

        invoker = new Invoker(new ScanCommand(this));

        invoker.execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (intentResult != null){
                String qrCodeData = intentResult.getContents();
                String[] parts = qrCodeData.split(",");
                String SoTaiKhoan = parts[0];
                String amount = "";
                String message = "";
                if (parts.length > 1) {
                    amount = parts[1];
                }
                if (parts.length > 2) {
                    message = parts[2];
                }
                invoker = new Invoker(new TransferDataCommand(this, SoTaiKhoan, amount, message));
                invoker.execute();
            }else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        } else {
            finish();
        }
    }
}
