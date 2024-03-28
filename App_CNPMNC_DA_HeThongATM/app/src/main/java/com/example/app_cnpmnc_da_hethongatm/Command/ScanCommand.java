package com.example.app_cnpmnc_da_hethongatm.Command;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;

public class ScanCommand implements Command {

    private final Activity activity;

    public ScanCommand(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void execute() {
        try {
            IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
            intentIntegrator.setOrientationLocked(true);
            intentIntegrator.setPrompt("Quét mã tại đây để giao dịch");
            intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
            intentIntegrator.initiateScan();
            intentIntegrator.setBeepEnabled(false);
        } catch (Exception e) {
            Log.e("ScanCommand", "Lỗi khởi tạo quét mã: " + e.getMessage());
            Toast.makeText(activity, "Lỗi khởi tạo quét mã!", Toast.LENGTH_SHORT).show();
        }
    }
}

