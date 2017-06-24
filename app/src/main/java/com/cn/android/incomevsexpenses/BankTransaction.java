package com.cn.android.incomevsexpenses;

import java.util.Date;

/**
 * Created by chris on 6/10/2017.
 */
public class BankTransaction {

    //members
    double amount;
    String description;
    Date date;

    public BankTransaction(String description, double amount, Date date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    //TODO: change the date format- Do not include the time
    public String toString() {

        return "Date: " + this.date + "\n"
                + "Description: " + this.description + "\n"
                + "Amount: " + this.amount;
    }

}
