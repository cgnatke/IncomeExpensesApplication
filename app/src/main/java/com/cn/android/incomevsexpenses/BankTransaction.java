package com.cn.android.incomevsexpenses;

import java.util.Date;

/**
 * Created by chris on 6/10/2017.
 */
public class BankTransaction {

    //members
    double amount;// = Double.parseDouble(transaction.split(",")[1].replace("\"","")); //get rid of double quotes
    String description; //= transaction.split(",")[2].replace("\"","");

    public BankTransaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String toString() {

        return this.description + ": " + this.amount;
    }

}
