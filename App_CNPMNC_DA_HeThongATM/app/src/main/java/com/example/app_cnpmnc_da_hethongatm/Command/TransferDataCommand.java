package com.example.app_cnpmnc_da_hethongatm.Command;

import android.app.Activity;
import android.content.Intent;

import com.example.app_cnpmnc_da_hethongatm.Activities.TransferMoneyActivity;
import com.example.app_cnpmnc_da_hethongatm.Extend.ResultCode;

public class TransferDataCommand implements Command {

    private final Activity activity;
    private final String SoTaiKhoan;
    private final String amount;
    private final String message;

    public TransferDataCommand(Activity activity, String SoTaiKhoan, String amount, String message) {
        this.activity = activity;
        this.SoTaiKhoan = SoTaiKhoan;
        this.amount = amount;
        this.message = message;
    }

    @Override
    public void execute() {
        Intent intent = new Intent(activity, TransferMoneyActivity.class);
        intent.putExtra("SoTaiKhoan", SoTaiKhoan);
        intent.putExtra("amount", amount);
        intent.putExtra("message", message);
        intent.putExtra("flag", ResultCode.SCAN_QR);
        activity.startActivity(intent);
    }
}


