package com.example.app_cnpmnc_da_hethongatm.Activities;

public interface    WithdrawSavingsAdapter {
    void withdrawSavings(String savingsAccountKey);
    void updateAccountBalance(long accountNumber, double newBalance);
}