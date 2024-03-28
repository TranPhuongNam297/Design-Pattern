package com.example.app_cnpmnc_da_hethongatm.Strategy;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_cnpmnc_da_hethongatm.Adapter.ListAccountQRCodeAdapter;
import com.example.app_cnpmnc_da_hethongatm.Extend.Config;
import com.example.app_cnpmnc_da_hethongatm.Extend.UtilityClass;
import com.example.app_cnpmnc_da_hethongatm.Model.TaiKhoanLienKet;
import com.example.app_cnpmnc_da_hethongatm.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public abstract class BaseGenerateQRCode extends AppCompatActivity {
    protected Config config;
    protected EditText edit_amount, edit_message, edit_tkqr;
    protected ImageView img_select;
    protected Toolbar toolbar;
    protected QRCodeStrategy qrCodeStrategy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qrcode);
        Button button = findViewById(R.id.btn_backhome);
        ImageView imageView = findViewById(R.id.qr_code);
        edit_amount = findViewById(R.id.edit_amount);
        edit_amount.setCursorVisible(false);
        edit_message = findViewById(R.id.edit_message);
        edit_message.setCursorVisible(false);
        edit_tkqr = findViewById(R.id.edit_list);
        img_select = findViewById(R.id.img_select);
        toolbar = findViewById(R.id.tbToolbar);
        config = new Config(this);

        UtilityClass.setupToolbar(this, toolbar, "Tạo mã QR");
        qrCodeStrategy = createQRCodeGenerator();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SoTaiKhoan = edit_tkqr.getText().toString();
                String amountString = edit_amount.getText().toString();
                long amount = amountString.isEmpty() ? 0 : Long.parseLong(amountString);
                String tien = String.valueOf(amount);
                String message = edit_message.getText().toString();
                String qrCodeData = SoTaiKhoan + "," + tien + "," + message;
                Bitmap bitmap = qrCodeStrategy.generateQRCode(qrCodeData);
                imageView.setImageBitmap(bitmap);
            }
        });

        img_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseRecyclerOptions<TaiKhoanLienKet> options =
                        new FirebaseRecyclerOptions.Builder<TaiKhoanLienKet>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("TaiKhoanLienKet").orderByChild("MaKHKey").equalTo(config.getCustomerKey()), TaiKhoanLienKet.class)
                                .build();

                ListAccountQRCodeAdapter listAccountQRCodeAdapter = new ListAccountQRCodeAdapter(options);

                DialogPlus dialogPlus = DialogPlus.newDialog(BaseGenerateQRCode.this)
                        .setContentHolder(new ViewHolder(R.layout.activity_list_accountqr))
                        .setExpanded(true, 800)
                        .setOverlayBackgroundResource(android.R.color.transparent) // Đặt màu nền trong suốt
                        .create();

                // Tìm RecyclerView trong layout của DialogPlus
                RecyclerView recyclerView = dialogPlus.getHolderView().findViewById(R.id.rc_listaccountqr);

                // Thiết lập ListAccountQRCodeAdapter cho RecyclerView
                recyclerView.setLayoutManager(new LinearLayoutManager(BaseGenerateQRCode.this));
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        DividerItemDecoration.VERTICAL);
                recyclerView.addItemDecoration(dividerItemDecoration);
                recyclerView.setAdapter(listAccountQRCodeAdapter);

                listAccountQRCodeAdapter.startListening();

                // Thiết lập sự kiện click cho mỗi item
                listAccountQRCodeAdapter.setOnItemClickListener(new ListAccountQRCodeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(TaiKhoanLienKet model) {
                        long soTaiKhoan = model.getSoTaiKhoan();
                        String stkstring = Long.toString(soTaiKhoan);
                        edit_tkqr.setText(stkstring);
                        dialogPlus.dismiss();// đóng dialogplus
                    }
                });

                dialogPlus.show();
            }
        });
    }

    protected abstract QRCodeStrategy createQRCodeGenerator();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();  // Kết thúc Activity hiện tại và quay lại Activity trước đó
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
