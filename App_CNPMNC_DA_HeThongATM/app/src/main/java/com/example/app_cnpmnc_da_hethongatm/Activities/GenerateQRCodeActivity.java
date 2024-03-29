package com.example.app_cnpmnc_da_hethongatm.Activities;

import com.example.app_cnpmnc_da_hethongatm.Strategy.BaseGenerateQRCode;
import com.example.app_cnpmnc_da_hethongatm.Strategy.BasicQRCodeStrategy;
import com.example.app_cnpmnc_da_hethongatm.Strategy.QRCodeStrategy;


public class GenerateQRCodeActivity extends BaseGenerateQRCode {
    @Override
    protected QRCodeStrategy createQRCodeGenerator() {
        return new BasicQRCodeStrategy();
    }
}