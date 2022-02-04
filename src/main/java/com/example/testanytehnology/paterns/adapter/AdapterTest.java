package com.example.testanytehnology.paterns.adapter;

public class AdapterTest {//тест
    public static void main(String[] args) {
        PBank pbank = new PBank();
        pbank.getBalance();
     
        PBankAdapter abank = new PBankAdapter(new ABank());
        abank.getBalance();
    }
}
