package com.cn.android.incomevsexpenses;

import android.Manifest;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.Date;
import java.util.List;


public class PromptActivity extends AppCompatActivity {

    //private double[] expenses = new double[8];
    private Expenses expenses = new Expenses();
    private double amount = 0;

    //todo use a dialog to prompt for the filename
    //private static final String fileName = "./data/account_testing.csv"; //+ promptForFilename();
    Uri selectedfile;


    private List<BankTransaction> transactions;
    private int index = 0;

    private TextView mTextView;

    private Button mPaycheckButton;
    private Button mUtilitiesButton;
    private Button mDiningButton;
    private Button mDepositsButton;
    private Button mFuelButton;
    private Button mMiscButton;
    private Button mRentButton;
    private Button mGroceriesButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        try {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1); //todo figure out how to properly grant permissions
        } catch (Exception e) {
            Log.e("CN_STATE", e.toString());
        }


        try {
            transactions = readStatement();
        } catch (Exception e) {
            Log.e("CN_STATE", e.getStackTrace().toString());
        }

     //   Log.d("CN_STATE", Arrays.toString(transactions.toArray()));

        mTextView = (TextView) findViewById(R.id.textView);
        initializeButtons();

        goToNextTransaction();

    }

    private void goToNextTransaction() {
        if (index == transactions.size()-1 ) {
            //todo ensure buttons are disabled
            amount = 0; // this is temporary. the purpose is to prevent users from adding amount to a category after the input is processed
            mTextView.setText(expenses.toString()); //todo go to summary screen instead
        } else {
            BankTransaction t = transactions.get(index);
            mTextView.setText("What type of transaction is this? \n " + t.toString());
            amount = t.amount;
            index++;
        }
    }

    //private void myClickHandler(View target) //todo create a click listener for all buttons to share

    public List<BankTransaction> readStatement() throws IOException, java.text.ParseException {

        List<BankTransaction> t = new ArrayList<BankTransaction>();

        if (!checkForReadPermissions()) {
            return t; //todo alert the user... Error out gracefully
        }

        //Intent intent = new Intent().setType("*/*").setAction(Intent.ACTION_GET_CONTENT);
        //startActivityForResult(Intent.createChooser(intent, "Select a file"), 123);
        //System.out.println(selectedfile.toString());

        File downloadsDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS); //getExternalStorageDirectory();

        File statement = new File(downloadsDirectory, "account_testing.csv");

        BufferedReader br;
        try {

            br = new BufferedReader(new FileReader(statement));
            String line;

            //skip the first three lines of the file
            br.readLine();
            br.readLine();
            br.readLine();

            //todo add a try catch around each line read in or decide to throw out all input
            while ((line = br.readLine()) != null) {
                double amount = Double.parseDouble(line.split(",")[1].replace("\"", "")); //get rid of double quotes
                String description = line.split(",")[2].replace("\"","");
                //String date = line.split(",")[0].replace("\"","");
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(line.split(",")[0].replace("\"",""));
                BankTransaction bt = new BankTransaction(description, amount, date);
                t.add(bt);
            }
        }
        catch (IOException e) {
            Log.e("CN_STATE", e.toString());
        }

        return t;
    }

    private boolean checkForReadPermissions() {
        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.v("CN_STATE", "Permission is granted");
            return true;
        } else {
            Log.v("CN_STATE", "No permission!!!");
            return false;
        }
    }

    private void initializeButtons() {

        //todo: create a factory for the button inits
        mPaycheckButton = (Button) findViewById(R.id.bPaycheck);
        mPaycheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenses.paycheck += amount;
                goToNextTransaction();
            }
        });

        mUtilitiesButton = (Button) findViewById(R.id.bUtilities);
        mUtilitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenses.utilities += amount;
                goToNextTransaction();
            }
        });

        mDiningButton = (Button) findViewById(R.id.bDining);
        mDiningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenses.dining += amount;
                goToNextTransaction();
            }
        });

        mDepositsButton = (Button) findViewById(R.id.bDeposits);
        mDepositsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenses.deposits += amount;
                goToNextTransaction();
            }
        });

        mFuelButton = (Button) findViewById(R.id.bFuel);
        mFuelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenses.fuel += amount;
                goToNextTransaction();
            }
        });

        mMiscButton = (Button) findViewById(R.id.bMisc);
        mMiscButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenses.misc += amount;
                goToNextTransaction();
            }
        });

        mRentButton = (Button) findViewById(R.id.bRent);
        mRentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenses.rent += amount;
                goToNextTransaction();
            }
        });

        mGroceriesButton = (Button) findViewById(R.id.bGroceries);
        mGroceriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenses.groceries += amount;
                goToNextTransaction();
            }
        });

        mPaycheckButton = (Button) findViewById(R.id.bRetirement);
        mPaycheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenses.retirement += amount;
                goToNextTransaction();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123 && resultCode==RESULT_OK) {
            selectedfile = data.getData(); //The uri with the location of the file
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prompt, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
