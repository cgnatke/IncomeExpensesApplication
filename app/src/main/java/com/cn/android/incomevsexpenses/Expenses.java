package com.cn.android.incomevsexpenses;

/**
 * Created by chris on 6/19/2017.
 */
public class Expenses {

    double paycheck;
    double deposits;
    double rent;
    double utilities;
    double fuel;
    double groceries;
    double dining;
    double retirement;
    double misc;

    public Expenses() {
        this.paycheck = 0;
        this.deposits = 0;
        this.rent = 0;
        this.utilities = 0;
        this.fuel = 0;
        this.groceries = 0;
        this.dining = 0;
        this.retirement = 0;
        this.misc = 0;

    }

    public String toString() {
        double totalExpenses = this.rent + this.utilities + this.fuel + this.groceries + this.dining + this.retirement + this.misc;
        double net = this.paycheck + this.deposits + totalExpenses;

        return "Paycheck: " + this.paycheck + "\n" +
                "Deposits: " + this.deposits + "\n" +
                "Rent: "+ this.rent + "\n" +
                "Utilities: " + this.utilities + "\n" +
                "Fuel: " + this.fuel + "\n" +
                "Groceries: " + this.groceries  + "\n" +
                "Dining: " + this.dining + "\n" +
                "Retirement: " + this.retirement + "\n" +
                "Misc Exp: " +  this.misc  + "\n" +
                "----------\n" +
                "Total Income and Deposits: " + this.paycheck + this.deposits + "\n" +
                "Total Expenses: " + totalExpenses + "\n" +
                "Net:" + net


                ;

    }

}
