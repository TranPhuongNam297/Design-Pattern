package com.example.app_cnpmnc_da_hethongatm.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.app_cnpmnc_da_hethongatm.Adapter.BeneficiaryAdapter;
import com.example.app_cnpmnc_da_hethongatm.Extend.Config;
import com.example.app_cnpmnc_da_hethongatm.Extend.DbHelper;
import com.example.app_cnpmnc_da_hethongatm.Extend.UtilityClass;
import com.example.app_cnpmnc_da_hethongatm.Factory.IntentFactory;
import com.example.app_cnpmnc_da_hethongatm.Model.ThuHuong;
import com.example.app_cnpmnc_da_hethongatm.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

public class BeneficiaryManagementActivity extends AppCompatActivity implements BeneficiaryAdapter.Listener {
    // View
    RecyclerView rvBeneficiary;
    ImageView ivAddBeneficiary;
    Toolbar tbToolbar;

    // Adapter
    BeneficiaryAdapter beneficiaryAdapter;

    // Flag
    public static int ADD_BENEFICIARY_FLAG = 1;
    public static int EDIT_BENEFICIARY_FLAG = 2;

    public static int USER_NAME = 3123;

    Config config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiary_management);

        initUI();
        initData();
        initListener();
    }

    // Ánh xạ view
    private void initUI() {
        rvBeneficiary = findViewById(R.id.rvBeneficiary);
        ivAddBeneficiary = findViewById(R.id.ivAddBeneficiary);
        tbToolbar = findViewById(R.id.tbToolbar);
    }

    // Khởi tạo dữ liệu
    private void initData() {
        UtilityClass.setupToolbar(this, tbToolbar, "Thụ hưởng");

        config = new Config(this);

        FirebaseRecyclerOptions<ThuHuong> beneficiaryOptions = DbHelper.getBeneficiaries(config.getCustomerKey());
        beneficiaryAdapter = new BeneficiaryAdapter(beneficiaryOptions, this);
        rvBeneficiary.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvBeneficiary.setAdapter(beneficiaryAdapter);
    }

    // Xử lý sự kiện
    private void initListener() {
        // Xử lý ấn thêm thụ hưởng
        ivAddBeneficiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BeneficiaryManagementActivity.this, AddEditBeneficiaryActivity.class);
                intent.putExtra("flag", ADD_BENEFICIARY_FLAG);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        beneficiaryAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        beneficiaryAdapter.stopListening();
    }

    // Xử lý sự kiện khi bấm vào popup menu
    @Override
    public void setOnClickPopupListener(View view, ThuHuong thuHuong, DatabaseReference databaseReference) {
        showPopupMenu(view, thuHuong, databaseReference);
    }

    // Hiển thị menu (thêm/sửa/xóa)
    public void showPopupMenu(View view, ThuHuong thuHuong, DatabaseReference databaseReference) {
        PopupMenu popupMenu = new PopupMenu(BeneficiaryManagementActivity.this, view);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = IntentFactory.getIntent(BeneficiaryManagementActivity.this, item.getItemId(), thuHuong, databaseReference);
                if (intent != null) {
                    startActivity(intent);
                    return true;
                }
                return true;
            }
        });
        popupMenu.show();
    }
}
