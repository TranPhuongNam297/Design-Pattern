package com.example.app_cnpmnc_da_hethongatm.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class WithdrawSavingsAdapterImpl implements WithdrawSavingsAdapter {
    private FirebaseDatabase database;

    public WithdrawSavingsAdapterImpl() {
        this.database = FirebaseDatabase.getInstance();
    }

    @Override
    public void withdrawSavings(String savingsAccountKey) {
        DatabaseReference savingsRef = database.getReference().child("GuiTietKiem").child(savingsAccountKey);
        savingsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    double savingsAmount = snapshot.child("TienGui").getValue(Double.class);
                    long linkedAccountNumber = snapshot.child("TaiKhoanNguon").getValue(Long.class);

                    updateAccountBalance(linkedAccountNumber, savingsAmount);
                    snapshot.getRef().child("TienGui").setValue(0);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }

    @Override
    public void updateAccountBalance(long accountNumber, double newBalance) {
        DatabaseReference accountRef = database.getReference().child("TaiKhoanLienKet")
                .orderByChild("SoTaiKhoan")
                .equalTo(accountNumber)
                .getRef();

        accountRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot accountSnapshot : snapshot.getChildren()) {
                        accountSnapshot.getRef().child("SoDu").setValue(newBalance);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }
}
