package com.example.app_cnpmnc_da_hethongatm.Activities;
import android.content.Context;
import com.example.app_cnpmnc_da_hethongatm.Extend.DbHelper;
import com.example.app_cnpmnc_da_hethongatm.Model.MauChuyenTien;
import com.example.app_cnpmnc_da_hethongatm.Model.TaiKhoanLienKet;
public class SaveBillFacade {
    private static SaveBillFacade instance;

    private SaveBillFacade() {
        // Private constructor to prevent instantiation from outside
    }

    public static SaveBillFacade getInstance() {
        if (instance == null) {
            instance = new SaveBillFacade();
        }
        return instance;
    }

    public void saveBill(TaiKhoanLienKet nguoiNhan, TaiKhoanLienKet nguoiGui, String ngayGui, String gioGui, String noiDung, String maGd, double tienGD) {
        MauChuyenTien mauChuyenTien = new MauChuyenTien(nguoiNhan.getTenTK(), nguoiNhan.getSoTaiKhoan(), tienGD, noiDung, nguoiGui.getMaKHKey());
        DbHelper.SaveBill(mauChuyenTien);
    }

    public void showBillSavedMessage(Context context) {
        DbHelper.BuilderXinXo(context, "Lưu thành công");
    }
}