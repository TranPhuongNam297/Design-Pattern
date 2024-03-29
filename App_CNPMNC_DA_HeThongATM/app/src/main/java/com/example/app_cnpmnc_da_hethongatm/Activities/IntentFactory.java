package com.example.app_cnpmnc_da_hethongatm.Activities;

import static android.os.Build.USER;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import com.example.app_cnpmnc_da_hethongatm.Extend.DbHelper;
import com.example.app_cnpmnc_da_hethongatm.Model.ThuHuong;
import com.example.app_cnpmnc_da_hethongatm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

public class IntentFactory {
    public static final int USER = 3123;
    public static final int EDIT_BENEFICIARY_FLAG = 2;

    public static Intent getIntent(Context context, int itemId, ThuHuong thuHuong, DatabaseReference databaseReference) {
        switch (itemId) {
            case R.id.action_chuyentien:
                Intent intent1 = new Intent(context, TransferMoneyActivity.class);
                intent1.putExtra("flag", USER);
                intent1.putExtra("tkthuhuong", thuHuong);
                return intent1;
            case R.id.action_edit:
                Intent intent = new Intent(context, AddEditBeneficiaryActivity.class);
                intent.putExtra("flag", EDIT_BENEFICIARY_FLAG);
                intent.putExtra("thuHuongKey", databaseReference.getKey());
                intent.putExtra("editThuHuong", thuHuong);
                return intent;
            case R.id.action_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa người thụ hưởng này không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DbHelper.deleteBeneficiary(databaseReference.getKey(), new DbHelper.FirebaseListener() {
                            @Override
                            public void onSuccessListener() {
                                Toast.makeText(context,"Xóa người thụ hưởng thành công", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailureListener(Exception e) {
                                Toast.makeText(context,"Hệ thống lỗi. Thao tác thất bại!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onSuccessListener(DataSnapshot snapshot) {

                            }
                        });
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(context,"Đã hủy", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return null;
            default:
                return null;
        }
    }
}

