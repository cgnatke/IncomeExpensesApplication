package com.cn.android.incomevsexpenses;

import java.util.Date;

/**
 * Created by chris on 6/10/2017.
 */
public class BankTransaction {

    //members
    double amount;
    String description;
    String date; //todo change type to an actual date

    public BankTransaction(String description, double amount, String date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public String toString() {

        return "Date: " + this.date + "\n"
                + "Description: " + this.description + "\n"
                + "Amount: " + this.amount;
    }

}
