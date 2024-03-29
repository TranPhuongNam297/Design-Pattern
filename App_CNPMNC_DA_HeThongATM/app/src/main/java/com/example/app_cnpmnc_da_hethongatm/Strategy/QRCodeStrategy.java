package com.example.app_cnpmnc_da_hethongatm.Strategy;

import android.graphics.Bitmap;

public interface QRCodeStrategy {
    Bitmap generateQRCode(String data);
}
